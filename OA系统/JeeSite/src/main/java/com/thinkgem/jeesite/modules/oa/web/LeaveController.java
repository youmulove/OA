/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Leave;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.oa.service.LeaveService;
import com.thinkgem.jeesite.modules.oa.service.OverTimeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 请假Controller
 * 
 * @author liuj
 * @version 2013-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/leave")
public class LeaveController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected LeaveService leaveService;
	@Autowired
	private OverTimeService overTimeService;
	// @Autowired
	// private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;

	@Autowired
	private ApprovalRuleService approvalRuleService;

	@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = { "form" })
	public String form(Leave leave, Model model) {
		model.addAttribute("leave", leave);
		return "modules/oa/leaveForm";
	}

	/**
	 * 启动请假流程
	 * 
	 * @param leave
	 */
	@RequiresPermissions("oa:leave:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Leave leave, RedirectAttributes redirectAttributes) {
		// String userId = UserUtils.getUser().getId();
		try {
			Map<String, Object> variables = Maps.newHashMap();
			leaveService.save(leave, variables);
			addMessage(redirectAttributes,
					"流程已启动，流程ID：" + leave.getProcessInstanceId());
		} catch (Exception e) {
			logger.error("启动请假流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:" + adminPath + "/oa/leave/form";
	}

	/**
	 * 任务列表
	 * 
	 * @param leave
	 */
	@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = { "list/task", "" })
	public String taskList(HttpSession session, Model model) {
		String userId = UserUtils.getUser().getId();// ObjectUtils.toString(UserUtils.getUser().getId());
		List<Leave> results = leaveService.findTodoTasks(userId);
		model.addAttribute("leaves", results);
		return "modules/oa/leaveTask";
	}

	/**
	 * 读取所有流程
	 * 
	 * @return
	 */
	@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = { "list" })
	public String list(Leave leave, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Leave> page = leaveService.find(
				new Page<Leave>(request, response), leave);
		model.addAttribute("page", page);
		return "modules/oa/leaveList";
	}

	/**
	 * 读取详细数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail/{id}")
	@ResponseBody
	public String getLeave(@PathVariable("id") String id) {
		Leave leave = leaveService.get(id);
		return JsonMapper.getInstance().toJson(leave);
	}

	/**
	 * 读取详细数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail-with-vars/{id}/{taskId}")
	@ResponseBody
	public String getLeaveWithVars(@PathVariable("id") String id,
			@PathVariable("taskId") String taskId) {
		Leave leave = leaveService.get(id);
		Map<String, Object> variables = taskService.getVariables(taskId);
		leave.setVariables(variables);
		return JsonMapper.getInstance().toJson(leave);
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * 提交请假申请
	 */
	@RequestMapping("leaveApply")
	@ResponseBody
	public String leaveApply(OaLeaved leave, String oaNotifyRecordIds,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String freePerson1, String freePerson2, String freePerson3,
			String freePerson4) {
		leave.setCopytoId(oaNotifyRecordIds);
		leaveService.leaveApply(leave, oaNotifyRecordIds, sysapp1, sysapp2,
				sysapp3, sysapp4, freePerson1, freePerson2, freePerson3,
				freePerson4);
		// return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
		return new Gson().toJson("success");
	}

	/**
	 * 提交请假申请之前校验申请人是否为最高审批人
	 */
	/*
	 * @RequestMapping("leaveApplyCheck")
	 * 
	 * @ResponseBody public String leaveApplyCheck(OaLeaved leave, String
	 * sysapp1, String sysapp2, String sysapp3, String sysapp4) { User user =
	 * UserUtils.getUser(); String userId = user.getId(); ApprovalRule appr =
	 * new ApprovalRule(); appr.setApprovalCompany(user.getCompany().getId());
	 * appr.setApprovalType("leave"); List<ApprovalRule> appList =
	 * approvalRuleService .findApprovalRuleSelective(appr); String
	 * processDefinitionKey = ""; for (ApprovalRule app : appList) { if
	 * ("".equals(processDefinitionKey)) { // 根据请假天数判断需要使用的请假流程图 if
	 * ((Double.parseDouble(leave.getHours())) > (Double
	 * .parseDouble(app.getApprovalStart())) &&
	 * (Double.parseDouble(leave.getHours())) <= (Double
	 * .parseDouble(app.getApprovalEnd()))) { processDefinitionKey =
	 * app.getApprovalProcess(); appr.setApprovalProcess(processDefinitionKey);
	 * } } } appr.setApprovalRole(appr.getApprovalProcess()); List<ApprovalRule>
	 * appList2 = approvalRuleService .findApprovalRuleSelective(appr); if
	 * (appList2.size() > 0) { ApprovalRule lastApprovalRule = appList2.get(0);
	 * String approvalPerson = lastApprovalRule.getApprovalPerson(); String
	 * approvalRank = lastApprovalRule.getApprovalRank(); if
	 * (!("").equals(approvalPerson)) { if (approvalPerson.contains(userId)) {
	 * return new Gson().toJson("false"); } } else if
	 * (!("").equals(approvalRank)) {
	 * 
	 * } } return "null"; }
	 */

	/**
	 * 选择结束或者重新发起,
	 */
	@RequestMapping("modifyLeave")
	public String modifyLeave(String taskId, ModelMap map, String flag,
			String nowposition) {
		Map<String, Object> variables = taskService.getVariablesLocal(taskId);
		variables.put("nowposition", nowposition);
		taskService.complete(taskId, variables);
		return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
	}

	/**
	 * 再次提交申请
	 */
	@RequestMapping("leaveApplyAgain")
	public String leaveApplyAgain(OaLeaved leave, String taskId,
			String oaNotifyRecordIds, String sysapp1, String sysapp2,
			String sysapp3, String sysapp4, String freePerson1,
			String freePerson2, String freePerson3, String freePerson4) {
		leave.setCopytoId(oaNotifyRecordIds);
		leaveService.leaveApply(leave, oaNotifyRecordIds, sysapp1, sysapp2,
				sysapp3, sysapp4, freePerson1, freePerson2, freePerson3,
				freePerson4);
		return "redirect:" + adminPath + "/oa/leave/form";
	}
}
