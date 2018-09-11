/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaDispatch;
import com.thinkgem.jeesite.modules.oa.entity.OaDocApproval;
import com.thinkgem.jeesite.modules.oa.entity.OaSealRecord;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.oa.service.OaDispatchService;
import com.thinkgem.jeesite.modules.oa.service.OaDocApprovalService;
import com.thinkgem.jeesite.modules.oa.service.OaSealRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 公文审批记录Controller
 * 
 * @author lisp
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaDocApproval")
public class OaDocApprovalController extends BaseController {

	@Autowired
	private OaDocApprovalService oaDocApprovalService;
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OaDispatchService oaDispatchService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private OaSealRecordService oaSealRecordService;

	@ModelAttribute
	public OaDocApproval get(@RequestParam(required = false) String id) {
		OaDocApproval entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaDocApprovalService.get(id);
		}
		if (entity == null) {
			entity = new OaDocApproval();
		}
		return entity;
	}

	@RequiresPermissions("oa:oaDocApproval:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaDocApproval oaDocApproval, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<OaDocApproval> page = oaDocApprovalService.findPage(
				new Page<OaDocApproval>(request, response), oaDocApproval);
		model.addAttribute("page", page);
		return "modules/oa/document/oaDocApprovalList";
	}

	@RequiresPermissions("oa:oaDocApproval:view")
	@RequestMapping(value = "form")
	public String form(OaDocApproval oaDocApproval, Model model) {
		model.addAttribute("oaDocApproval", oaDocApproval);
		return "modules/oa/document/oaDocApprovalForm";
	}

	@RequiresPermissions("oa:oaDocApproval:edit")
	@RequestMapping(value = "save")
	public String save(OaDocApproval oaDocApproval, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaDocApproval)) {
			return form(oaDocApproval, model);
		}
		oaDocApprovalService.save(oaDocApproval);
		addMessage(redirectAttributes, "保存公文审批记录成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaDocApproval/?repage";
	}

	@RequiresPermissions("oa:oaDocApproval:edit")
	@RequestMapping(value = "delete")
	public String delete(OaDocApproval oaDocApproval,
			RedirectAttributes redirectAttributes) {
		oaDocApprovalService.delete(oaDocApproval);
		addMessage(redirectAttributes, "删除公文审批记录成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaDocApproval/?repage";
	}

	// 页面跳转
	@RequestMapping("returnPage")
	public String returnPage() {
		return "modules/oa/document/setDocApprovalRule";
	}

	// 公文审批规则设置
	@RequestMapping("setDocApprovalRule")
	public String setDocApprovalRule(RedirectAttributes redirectAttributes,
			String sgId, String hgId, String signId, String officeApprovalId,
			String qfId, String checkId, String useSealId) {
		ApprovalRule rule = new ApprovalRule();
		rule.setApprovalType("doc");// 设置规则类型为公文审批类型
		rule.setApprovalCompany(UserUtils.getUser().getCompany().getId());
		approvalRuleService.deleteByApprovalRule(rule);
		rule.setApprovalProcess("7");
		// 设置审稿人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("1");
		rule.setApprovalPerson(returnLoginName(sgId));
		approvalRuleService.insertApprovalRule(rule);
		// 设置核稿人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("2");
		rule.setApprovalPerson(returnLoginName(hgId));
		approvalRuleService.insertApprovalRule(rule);
		// 设置会签人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("3");
		rule.setApprovalPerson(returnLoginName(signId));
		approvalRuleService.insertApprovalRule(rule);
		// 设置办公室审批人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("4");
		rule.setApprovalPerson(returnLoginName(officeApprovalId));
		approvalRuleService.insertApprovalRule(rule);
		// 设置签发人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("5");
		rule.setApprovalPerson(returnLoginName(qfId));
		approvalRuleService.insertApprovalRule(rule);
		// 设置校对人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("6");
		rule.setApprovalPerson(returnLoginName(checkId));
		approvalRuleService.insertApprovalRule(rule);
		// 设置用印人
		rule.setApprovaleId(UUID.randomUUID().toString().replace("-", ""));
		rule.setApprovalRole("7");
		rule.setApprovalPerson(returnLoginName(useSealId));
		approvalRuleService.insertApprovalRule(rule);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaDocApproval/returnPage/?repage";
	}

	/**
	 * 审批规则展示
	 */
	@RequestMapping("showApprovalRule")
	public String showApprovalRule(ModelMap map) {
		ApprovalRule approvalRule = new ApprovalRule();
		approvalRule.setApprovalType("doc");
		List<ApprovalRule> list = approvalRuleService
				.findApprovalRuleSelective(approvalRule);
		for (ApprovalRule app : list) {
			String[] split = app.getApprovalPerson().split(",");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				// User u = systemService.getUserByLoginName(split[i]);
				User u = UserUtils.get(split[i]);
				if (u != null) {
					sb.append(u.getName() + ",");
				}
			}
			app.setApprovalPerson(sb.toString());
		}
		map.put("list", list);
		return "modules/oa/document/docApprovalRuleList";
	}

	/**
	 * 修改公文审批规则页面弹框跳转
	 */
	@RequestMapping("modifyPage")
	public String modifyPage(String approvalRole, ModelMap map) {
		ApprovalRule approvalRule = new ApprovalRule();
		approvalRule.setApprovalType("doc");
		approvalRule.setApprovalRole(approvalRole);
		List<ApprovalRule> list = approvalRuleService
				.findApprovalRuleSelective(approvalRule);
		map.put("rule", list.get(0));
		return "modules/oa/document/modifyDocApprovalRule";
	}

	/**
	 * 修改公文审批规则
	 */
	@RequestMapping("modifyRule")
	@ResponseBody
	public String modifyRule(String approvalRole, String oaNotifyRecordIds) {
		try {
			ApprovalRule approvalRule = new ApprovalRule();
			approvalRule.setApprovalType("doc");
			approvalRule.setApprovalRole(approvalRole);
			approvalRule.setApprovalPerson(returnLoginName(oaNotifyRecordIds));
			approvalRuleService.updateApproval(approvalRule);
		} catch (Exception e) {
			return new Gson().toJson("error");
		}
		return new Gson().toJson("success");
	}

	public static String returnLoginName(String string) {
		String[] split = string.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			sb.append(UserUtils.get(split[i]).getId()).append(",");
		}
		return sb.toString();
	}

	/**
	 * 已办公文列表展示
	 */
	@RequestMapping("alreadyDoc")
	public String alreadyDoc(ModelMap map, Act act, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Act act3 = new Act();
		act3.setBeginDate(act.getBeginDate());
		act3.setEndDate(act.getEndDate());
		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act3);
		Page<Act> page2 = new Page<Act>(request, response);
		ArrayList<Act> arrayList = new ArrayList<Act>();
		List<Act> list = page.getList();
		for (Act act2 : list) {
			HistoricTaskInstance task = act2.getHistTask();
			List<HistoricVariableInstance> list2 = historyService
					.createHistoricVariableInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).list();
			for (HistoricVariableInstance his : list2) {
				if (his.getVariableName().equals("type")) {
					if ("doc".equals(his.getValue().toString())) {
						arrayList.add(act2);
					}
				}
				if (his.getVariableName().equals("title")) {
					act2.setTitle(his.getValue().toString());
				}
				if (his.getVariableName().equals("nowposition")) {
					act2.setComment(his.getValue().toString());
				}
				// if("oaDocApproval".equals(his.getVariableName())){model.addAttribute("doc",his.getValue());}
			}
		}
		page2.setList(arrayList);
		model.addAttribute("page", page2);

		/*
		 * String loginName = UserUtils.getUser().getId(); OaDocApproval
		 * oaDocApproval = new OaDocApproval(); ArrayList<OaDocApproval> list =
		 * new ArrayList<OaDocApproval>(); List<OaDocApproval> findList =
		 * oaDocApprovalService.findList(oaDocApproval); for (OaDocApproval doc
		 * : findList) {
		 * doc.setOaDispatch(oaDispatchService.get(doc.getOaDispatchId()));
		 * if(ArrayUtils.contains(doc.getSgId().split(","), loginName)){
		 * if(doc.getSgFlag()!=null &&
		 * !("".equals(doc.getSgFlag()))){list.add(doc);} continue; }else
		 * if(ArrayUtils.contains(doc.getHgId().split(","), loginName)){
		 * if(doc.getHgFlag()!=null &&
		 * !("".equals(doc.getHgFlag()))){list.add(doc);} continue; }else
		 * if(ArrayUtils.contains(doc.getSignId().split(","), loginName)){
		 * if(doc.getSignFlag()!=null &&
		 * !("".equals(doc.getSignFlag()))){list.add(doc);} continue; }else
		 * if(ArrayUtils.contains(doc.getOfficeApprovalId().split(","),
		 * loginName)){ if(doc.getOfficeFlag()!=null &&
		 * !("".equals(doc.getOfficeFlag()))){list.add(doc);} continue; }else
		 * if(ArrayUtils.contains(doc.getQfId().split(","), loginName)){
		 * if(doc.getQfFlag()!=null &&
		 * !("".equals(doc.getQfFlag()))){list.add(doc);} continue; }else
		 * if(ArrayUtils.contains(doc.getCheckId().split(","), loginName)){
		 * if(doc.getCheckFlag()!=null &&
		 * !("".equals(doc.getCheckFlag()))){list.add(doc);} continue; }else
		 * if(ArrayUtils.contains(doc.getUseSealId().split(","), loginName)){
		 * if(doc.getUseSealFlag()!=null &&
		 * !("".equals(doc.getUseSealFlag()))){list.add(doc);} continue; } }
		 * map.put("list", list);
		 */
		return "modules/oa/document/alreadyDocList";
	}

	/**
	 * 删除已办公文
	 */
	@RequestMapping(value = "deleteAlreadyTask")
	public String deleteAlreadyTask(String taskId, String reason,
			RedirectAttributes redirectAttributes) {
		taskService.deleteTask(taskId, true);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaDocApproval/alreadyDoc";
	}

	/**
	 * 保存文档
	 */
	@RequestMapping("saveFileDoc")
	public String saveFileDoc(
			HttpServletRequest request,
			@RequestParam(value = "DocContent", required = false) MultipartFile DocContent,
			String dispatchId) {
		OaDispatch oaDispatch = oaDispatchService.get(dispatchId);
		String filePath = oaDispatch.getFilePath().replace("|", "");
		try {
			String[] split = filePath.split("/");
			String fname = split[split.length - 1];
			String path = request.getSession().getServletContext()
					.getRealPath("/document");
			File file = new File(path, fname);
			DocContent.transferTo(file);
			// 修改发文单中公文路径
			OaDispatch dispatch = oaDispatchService.get(dispatchId);
			dispatch.setFilePath(request.getContextPath() + "/document" + "/"
					+ fname);
			// dispatch.setOaSealId("");
			oaDispatchService.save(dispatch);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 公章使用记录表插入数据
		OaSealRecord oaSealRecord = new OaSealRecord();
		oaSealRecord.setOaDispatchId(dispatchId);
		// oaSealRecord.setOaSeal(oaSeal);
		oaSealRecordService.save(oaSealRecord);

		return "redirect:" + Global.getAdminPath() + "/act/task/todo";
	}

	/**
	 * 弹框跳转
	 */
	@RequestMapping("returnCheckSeal")
	public String returnCheckSeal(String sealId, ModelMap map) {
		map.put("sealId", sealId);
		return "modules/oa/document/checkSeal";
	}

}