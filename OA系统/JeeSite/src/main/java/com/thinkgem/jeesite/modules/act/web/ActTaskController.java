/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActProcessService;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.oa.entity.OaDispatch;
import com.thinkgem.jeesite.modules.oa.entity.OaDocApproval;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyProjectorService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyService;
import com.thinkgem.jeesite.modules.oa.service.OaDispatchService;
import com.thinkgem.jeesite.modules.oa.service.OaLeavedService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.oa.service.OaOvertimeService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 流程个人任务相关Controller
 * 
 * @author ThinkGem
 * @version 2013-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/act/task")
public class ActTaskController extends BaseController {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private OaLeavedService oaLeavedService;
	@Autowired
	private OaOvertimeService oaOvertimeService;
	@Autowired
	private OaApplyOfficeroomService oaApplyOfficeroomService;
	@Autowired
	private OaApplyProjectorService oaApplyProjectorService;
	@Autowired
	private OaApplyService oaApplyService;
	@Autowired
	private OaDispatchService oaDispatchService;
	@Autowired
	private ActProcessService actProcessService;

	/**
	 * 获取待办列表
	 * 
	 * @param procDefKey
	 *            流程定义标识
	 * @return
	 */
	@RequestMapping(value = { "todo", "" })
	public String todoList(Act act, HttpServletResponse response, Model model)
			throws Exception {
		// 查询所有待办任务列表
		Act act3 = new Act();
		act3.setBeginDate(act.getBeginDate());
		act3.setEndDate(act.getEndDate());
		List<Act> list = actTaskService.todoList(act3);
		List<Act> arrayList = new ArrayList<Act>();
		ArrayList<String> list2 = new ArrayList<String>();
		ArrayList<OaDocApproval> list3 = new ArrayList<OaDocApproval>();
		if ("".equals(act.getProcDefKey()) || act.getProcDefKey() == null) {
			for (Act act2 : list) {
				Task task = act2.getTask();
				String title = (String) taskService.getVariable(task.getId(),
						"title");
				OaDocApproval oaDocApproval = (OaDocApproval) taskService
						.getVariable(task.getId(), "oaDocApproval");
				String flag = (String) taskService.getVariable(task.getId(),
						"flag");//
				list3.add(oaDocApproval);
				// String userName =
				// (String)taskService.getVariable(act2.getTaskId(),
				// "applyUserId");
				String userName = historyService
						.createHistoricProcessInstanceQuery()
						.processInstanceId(act2.getProcInsId()).singleResult()
						.getStartUserId();
				list2.add(userName);
				act2.setTitle(title);
				act2.setComment(flag);
			}
			arrayList = list;
		} else {
			for (Act act2 : list) {
				Task task = act2.getTask();
				String type = (String) taskService.getVariable(task.getId(),
						"type");
				String title = (String) taskService.getVariable(task.getId(),
						"title");
				String userName = historyService
						.createHistoricProcessInstanceQuery()
						.processInstanceId(act2.getProcInsId()).singleResult()
						.getStartUserId();
				list2.add(userName);
				act2.setTitle(title);
				if (type.equals(act.getProcDefKey())) {
					arrayList.add(act2);
				}
			}
		}
		// act.setProcDefKey(procDefKey);
		// List<Act> list = actTaskService.todoList(act);
		model.addAttribute("list", arrayList);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("currentLoginName", UserUtils.getUser().getId());

		if (UserUtils.getPrincipal().isMobileLogin()) {
			return renderString(response, arrayList);
		}
		return "modules/act/actTaskTodoList";
	}

	/**
	 * 获取待办任务条数
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(Act act, HttpServletResponse response, Model model) {
		Act act3 = new Act();
		act3.setBeginDate(act.getBeginDate());
		act3.setEndDate(act.getEndDate());
		List<Act> list = actTaskService.todoList(act3);
		List<Act> arrayList = new ArrayList<Act>();
		if ("".equals(act.getProcDefKey()) || act.getProcDefKey() == null) {
			arrayList = list;
		} else {
			for (Act act2 : list) {
				Task task = act2.getTask();
				String type = (String) taskService.getVariable(task.getId(),
						"type");
				String title = (String) taskService.getVariable(task.getId(),
						"title");
				act2.setTitle(title);
				if (type.equals(act.getProcDefKey())) {
					arrayList.add(act2);
				}
			}
		}
		return String.valueOf(arrayList.size());
	}

	/**
	 * 获取已办任务
	 * 
	 * @param page
	 * @param procDefKey
	 *            流程定义标识
	 * @return
	 */
	@RequestMapping(value = "historic")
	public String historicList(Act act, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		Act act3 = new Act();
		act3.setBeginDate(act.getBeginDate());
		act3.setEndDate(act.getEndDate());
		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act3);
		Page<Act> page2 = new Page<Act>(request, response);
		if ("".equals(act.getProcDefKey()) || act.getProcDefKey() == null) {
			List<Act> list = page.getList();
			for (Act act2 : list) {
				HistoricTaskInstance task = act2.getHistTask();
				List<HistoricVariableInstance> list2 = historyService
						.createHistoricVariableInstanceQuery()
						.processInstanceId(task.getProcessInstanceId()).list();
				for (HistoricVariableInstance his : list2) {
					if (his.getVariableName().equals("title")) {
						act2.setTitle(his.getValue().toString());
					}
					if (his.getVariableName().equals("nowposition")) {
						act2.setComment(his.getValue().toString());
					}
				}
			}
			page2.setList(list);
		} else {
			for (Act act2 : page.getList()) {
				HistoricTaskInstance task = act2.getHistTask();
				List<HistoricVariableInstance> list = historyService
						.createHistoricVariableInstanceQuery()
						.processInstanceId(task.getProcessInstanceId()).list();
				for (HistoricVariableInstance his : list) {
					if (his.getVariableName().equals("type")
							&& act.getProcDefKey().equals(his.getValue())) {
						page2.getList().add(act2);
					}
					if (his.getVariableName().equals("title")) {
						act2.setTitle(his.getValue().toString());
					}
					if (his.getVariableName().equals("nowposition")) {
						act2.setComment(his.getValue().toString());
					}
				}
			}
		}

		model.addAttribute("page", page2);
		if (UserUtils.getPrincipal().isMobileLogin()) {
			return renderString(response, page);
		}

		return "modules/act/actTaskHistoricList";
	}

	/**
	 * 我发起的任务
	 */
	/*
	 * @RequestMapping("myApply") public String myApply(Act
	 * act,HttpServletRequest request, HttpServletResponse response, Model
	 * model){ Page<Act> page = new Page<Act>(request, response); page =
	 * actTaskService.myApply(page, act); model.addAttribute("page", page); if
	 * (UserUtils.getPrincipal().isMobileLogin()){ return renderString(response,
	 * page); } return "modules/act/myApplyList"; }
	 */

	/**
	 * 获取流转历史列表
	 * 
	 * @param procInsId
	 *            流程实例
	 * @param startAct
	 *            开始活动节点名称
	 * @param endAct
	 *            结束活动节点名称
	 */
	@RequestMapping(value = "histoicFlow")
	public String histoicFlow(Act act, String startAct, String endAct,
			Model model) {
		if (StringUtils.isNotBlank(act.getProcInsId())) {
			List<Act> histoicFlowList = actTaskService.histoicFlowList(
					act.getProcInsId(), startAct, endAct);
			model.addAttribute("histoicFlowList", histoicFlowList);
		}
		return "modules/act/actTaskHistoricFlow";
	}

	/**
	 * 获取流程列表
	 * 
	 * @param category
	 *            流程分类
	 */
	@RequestMapping(value = "process")
	public String processList(String category, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Object[]> page = new Page<Object[]>(request, response);
		page = actTaskService.processList(page, category);
		model.addAttribute("page", page);
		model.addAttribute("category", category);
		return "modules/act/actTaskProcessList";
	}

	/**
	 * 获取流程表单
	 * 
	 * @param taskId
	 *            任务ID
	 * @param taskName
	 *            任务名称
	 * @param taskDefKey
	 *            任务环节标识
	 * @param procInsId
	 *            流程实例ID
	 * @param procDefId
	 *            流程定义ID
	 */
	@RequestMapping(value = "form")
	public String form(Act act, HttpServletRequest request, Model model) {

		// 获取流程XML上的表单KEY
		String formKey = actTaskService.getFormKey(act.getProcDefId(),
				act.getTaskDefKey());

		// 获取流程实例对象
		if (act.getProcInsId() != null) {
			// System.out.println(act.getProcInsId()+"--------");
			// System.out.println(actTaskService.getProcIns(act.getProcInsId()).getBusinessKey());
			act.setProcIns(actTaskService.getProcIns(act.getProcInsId()));
		}

		return "redirect:" + ActUtils.getFormUrl(formKey, act);

		// // 传递参数到视图
		// model.addAttribute("act", act);
		// model.addAttribute("formUrl", formUrl);
		// return "modules/act/actTaskForm";
	}

	/**
	 * 启动流程
	 * 
	 * @param procDefKey
	 *            流程定义KEY
	 * @param businessTable
	 *            业务表表名
	 * @param businessId
	 *            业务表编号
	 */
	@RequestMapping(value = "start")
	@ResponseBody
	public String start(Act act, String table, String id, Model model)
			throws Exception {
		actTaskService.startProcess(act.getProcDefKey(), act.getBusinessId(),
				act.getBusinessTable(), act.getTitle());
		return "true";// adminPath + "/act/task";
	}

	/**
	 * 签收任务
	 * 
	 * @param taskId
	 *            任务ID
	 */
	@RequestMapping(value = "claim")
	@ResponseBody
	public String claim(Act act) {
		String userId = UserUtils.getUser().getId();// ObjectUtils.toString(UserUtils.getUser().getId());
		actTaskService.claim(act.getTaskId(), userId);
		return "true";// adminPath + "/act/task";
	}

	/**
	 * 批量签收任务
	 */
	@RequestMapping(value = "claimAll")
	@ResponseBody
	public String claimAll(String taskIds) {
		String userId = UserUtils.getUser().getId();// ObjectUtils.toString(UserUtils.getUser().getId());
		for (String taskId : taskIds.split(",")) {
			actTaskService.claim(taskId, userId);
		}
		return "true";// adminPath + "/act/task";
	}

	/**
	 * 完成任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @param procInsId
	 *            流程实例ID，如果为空，则不保存任务提交意见
	 * @param comment
	 *            任务提交意见的内容
	 * @param vars
	 *            任务流程变量，如下 vars.keys=flag,pass vars.values=1,true
	 *            vars.types=S,B @see
	 *            com.thinkgem.jeesite.modules.act.utils.PropertyType
	 */
	@RequestMapping(value = "complete")
	@ResponseBody
	public String complete(Act act) {
		actTaskService.complete(act.getTaskId(), act.getProcInsId(),
				act.getComment(), act.getVars().getVariableMap());
		return "true";// adminPath + "/act/task";
	}

	/**
	 * 读取带跟踪的图片
	 */
	@RequestMapping(value = "trace/photo/{procDefId}/{execId}")
	public void tracePhoto(@PathVariable("procDefId") String procDefId,
			@PathVariable("execId") String execId, HttpServletResponse response)
			throws Exception {
		InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);

		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 输出跟踪流程信息
	 * 
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "trace/info/{proInsId}")
	public List<Map<String, Object>> traceInfo(
			@PathVariable("proInsId") String proInsId) throws Exception {
		List<Map<String, Object>> activityInfos = actTaskService
				.traceProcess(proInsId);
		return activityInfos;
	}

	/**
	 * 显示流程图
	 * 
	 * @RequestMapping(value = "processPic") public void processPic(String
	 *                       procDefId, HttpServletResponse response) throws
	 *                       Exception { ProcessDefinition procDef =
	 *                       repositoryService.createProcessDefinitionQuery().
	 *                       processDefinitionId(procDefId).singleResult();
	 *                       String diagramResourceName =
	 *                       procDef.getDiagramResourceName(); InputStream
	 *                       imageStream =
	 *                       repositoryService.getResourceAsStream(
	 *                       procDef.getDeploymentId(), diagramResourceName);
	 *                       byte[] b = new byte[1024]; int len = -1; while
	 *                       ((len = imageStream.read(b, 0, 1024)) != -1) {
	 *                       response.getOutputStream().write(b, 0, len); } }
	 */

	/**
	 * 获取跟踪信息
	 * 
	 * @RequestMapping(value = "processMap") public String processMap(String
	 *                       procDefId, String proInstId, Model model) throws
	 *                       Exception { List<ActivityImpl> actImpls = new
	 *                       ArrayList<ActivityImpl>(); ProcessDefinition
	 *                       processDefinition = repositoryService
	 *                       .createProcessDefinitionQuery
	 *                       ().processDefinitionId(procDefId) .singleResult();
	 *                       ProcessDefinitionImpl pdImpl =
	 *                       (ProcessDefinitionImpl) processDefinition; String
	 *                       processDefinitionId = pdImpl.getId();// 流程标识
	 *                       ProcessDefinitionEntity def =
	 *                       (ProcessDefinitionEntity) ((RepositoryServiceImpl)
	 *                       repositoryService)
	 *                       .getDeployedProcessDefinition(processDefinitionId);
	 *                       List<ActivityImpl> activitiList =
	 *                       def.getActivities();// 获得当前任务的所有节点 List<String>
	 *                       activeActivityIds =
	 *                       runtimeService.getActiveActivityIds(proInstId); for
	 *                       (String activeId : activeActivityIds) { for
	 *                       (ActivityImpl activityImpl : activitiList) { String
	 *                       id = activityImpl.getId(); if
	 *                       (activityImpl.isScope()) { if
	 *                       (activityImpl.getActivities().size() > 1) {
	 *                       List<ActivityImpl> subAcList = activityImpl
	 *                       .getActivities(); for (ActivityImpl subActImpl :
	 *                       subAcList) { String subid = subActImpl.getId();
	 *                       System.out.println("subImpl:" + subid); if
	 *                       (activeId.equals(subid)) {// 获得执行到那个节点
	 *                       actImpls.add(subActImpl); break; } } } } if
	 *                       (activeId.equals(id)) {// 获得执行到那个节点
	 *                       actImpls.add(activityImpl); System.out.println(id);
	 *                       } } } model.addAttribute("procDefId", procDefId);
	 *                       model.addAttribute("proInstId", proInstId);
	 *                       model.addAttribute("actImpls", actImpls); return
	 *                       "modules/act/actTaskMap"; }
	 */

	/**
	 * 删除任务
	 * 
	 * @param taskId
	 *            流程实例ID
	 * @param reason
	 *            删除原因
	 */
	// @RequiresPermissions("act:process:edit")
	@RequestMapping(value = "deleteTask")
	public String deleteTask(String taskId, String reason,
			RedirectAttributes redirectAttributes) {
		if (StringUtils.isBlank(reason)) {
			addMessage(redirectAttributes, "请填写删除原因");
		} else {
			/*
			 * actTaskService.deleteTask(taskId, reason);
			 * addMessage(redirectAttributes, "删除任务成功，任务ID=" + taskId);
			 */
			taskService.deleteTask(taskId, true);
		}
		return "redirect:" + adminPath + "/act/task";
	}

	/**
	 * 办理任务
	 */
	@RequestMapping(value = "handleTask")
	@ResponseBody
	public String handleTask(String taskId, String flag, String approvalResult) {
		actTaskService.handleTask(taskId, flag, approvalResult);
		return new Gson().toJson("ok");
	}

	/**
	 * 批量办理任务
	 */
	@RequestMapping(value = "handleTaskAll")
	@ResponseBody
	public String handleTaskAll(String taskIds, String flag,
			String approvalResult) {
		for (String taskId : taskIds.split(",")) {
			actTaskService.handleTask(taskId, flag, approvalResult);
		}
		return new Gson().toJson("ok");
	}

	/**
	 * 批量处理待办任务列表填写审批意见界面
	 */
	@RequestMapping("detailAll")
	public String detailAll(String taskIds, ModelMap map) {
		map.put("detailFlag", "todo");// 区分待办还是未办
		map.put("taskIds", taskIds);
		return "modules/oa/leaveDetail3";
	}

	/**
	 * 批量处理待办任务跳转方法
	 */
	@ResponseBody
	@RequestMapping("transfer")
	public String transfer(String taskIds) {
		for (String taskId : taskIds.split(",")) {
			if ("退回".equals(actTaskService.getTask(taskId).getName())) {
				return "back";
			}
			break;
		}
		return "true";
	}

	/**
	 * 待办任务详情
	 * 
	 */
	@RequestMapping("detail")
	public String taskDetail(String taskId, ModelMap map, String proDefName,
			String typeFlag) {
		// oa/document/returnPage
		Task task = actTaskService.taskDetail(taskId);
		map.put("task", task);
		map.put("detailFlag", "todo");// 区分待办还是未办
		Map<String, Object> variables = taskService.getVariables(taskId);
		if (variables.get("applyUserId") == null) {
			map.put("applyUserId", variables.get("applyUser").toString());
		} else {
			map.put("applyUserId", variables.get("applyUserId").toString());
		}
		map.put("currentUser", UserUtils.getUser().getId());
		map.put("title", variables.get("title").toString());
		if ((variables.get("type").toString()).equals("leave")) {
			OaLeaved leave = (OaLeaved) variables.get("leave");
			map.put("leave", leave);
			map.put("oneLevel", returnName(leave.getAuditFirId()));
			map.put("twoLevel", returnName(leave.getAuditSecId()));
			map.put("threeLevel", returnName(leave.getAuditThrId()));
			map.put("fourLevel", returnName(leave.getAuditFouId()));
			map.put("type", "leave");
		} else if ((variables.get("type").toString()).equals("overTime")) {
			OaOvertime overTime = (OaOvertime) variables.get("overTime");
			map.put("overTime", overTime);
			map.put("oneLevel", returnName(overTime.getAuditFirId()));
			map.put("twoLevel", returnName(overTime.getAuditSecId()));
			map.put("threeLevel", returnName(overTime.getAuditThrId()));
			map.put("fourLevel", returnName(overTime.getAuditFouId()));
			map.put("type", "overTime");
		} else if ((variables.get("type").toString()).equals("officeRoom")) {
			OaApplyOfficeroom officeRoom = (OaApplyOfficeroom) variables
					.get("officeRoom");
			map.put("officeRoom", officeRoom);
			map.put("oneLevel", returnName(officeRoom.getAuditFirId()));
			map.put("twoLevel", returnName(officeRoom.getAuditSecId()));
			map.put("threeLevel", returnName(officeRoom.getAuditThrId()));
			map.put("fourLevel", returnName(officeRoom.getAuditFouId()));
			map.put("type", "officeRoom");
		} else if ((variables.get("type").toString()).equals("projector")) {
			OaApplyProjector projector = (OaApplyProjector) variables
					.get("projector");
			map.put("projector", projector);
			map.put("oneLevel", returnName(projector.getAuditFirId()));
			map.put("twoLevel", returnName(projector.getAuditSecId()));
			map.put("threeLevel", returnName(projector.getAuditThrId()));
			map.put("fourLevel", returnName(projector.getAuditFouId()));
			map.put("type", "projector");
		} else if ((variables.get("type").toString()).equals("currency")) {
			OaApply currency = (OaApply) variables.get("currency");
			map.put("currency", currency);
			map.put("oneLevel", returnName(currency.getAuditFirId()));
			map.put("twoLevel", returnName(currency.getAuditSecId()));
			map.put("threeLevel", returnName(currency.getAuditThrId()));
			map.put("fourLevel", returnName(currency.getAuditFouId()));
			map.put("type", "currency");
		} else if ((variables.get("type").toString()).equals("doc")) {
			OaDocApproval doc = (OaDocApproval) variables.get("oaDocApproval");
			map.put("doc", doc);
			map.put("type", "doc");
			OaDispatch dispatch = oaDispatchService.getByDocApprovalId(doc
					.getId());
			// map.put("filePath", dispatch.getFilePath().replace("|", ""));
			String path = dispatch.getFilePath().replace("|", "");
			map.put("filePath", path);
			map.put("fileName", dispatch.getTitle());
			map.put("dispatchId", dispatch.getId());
			if ("y".equals(typeFlag)) {
				// return "doc/index";
				return "modules/oa/document/index";
			}
		}
		return "modules/oa/leaveDetail2";
	}

	/**
	 * 已办任务详情
	 */
	@RequestMapping("alreadyDetail")
	public String alreadyDetail(String taskId, ModelMap map) {

		HistoricTaskInstance result = historyService
				.createHistoricTaskInstanceQuery().taskId(taskId)
				.singleResult();
		String instanceId = result.getProcessInstanceId();
		// HistoricProcessInstance instance =
		// historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
		List<HistoricVariableInstance> list = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(instanceId).list();
		List<HistoricVariableInstance> vlist = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(instanceId).list();
		for (HistoricVariableInstance his : vlist) {
			if ("flag".equals(his.getVariableName())) {
				map.put("flag", his.getValue());
			}
		}
		map.put("task", result);
		map.put("detailFlag", "already");// 区分待办还是未办
		for (int i = 0; i < list.size(); i++) {

			if ("applyUserId".equals(list.get(i).getVariableName())) {
				map.put("applyUserId", list.get(i).getValue());
			}
			if ("title".equals(list.get(i).getVariableName())) {
				map.put("title", list.get(i).getValue());
			}
			if ("leave".equals(list.get(i).getVariableName())) {
				OaLeaved leave = (OaLeaved) list.get(i).getValue();
				map.put("leave", leave);
				map.put("oneLevel", returnName(leave.getAuditFirId()));
				map.put("twoLevel", returnName(leave.getAuditSecId()));
				map.put("threeLevel", returnName(leave.getAuditThrId()));
				map.put("fourLevel", returnName(leave.getAuditFouId()));
				map.put("type", "leave");
			} else if ("overTime".equals(list.get(i).getVariableName())) {
				OaOvertime overtime = (OaOvertime) list.get(i).getValue();
				map.put("overTime", overtime);
				map.put("oneLevel", returnName(overtime.getAuditFirId()));
				map.put("twoLevel", returnName(overtime.getAuditSecId()));
				map.put("threeLevel", returnName(overtime.getAuditThrId()));
				map.put("fourLevel", returnName(overtime.getAuditFouId()));
				map.put("type", "overTime");
			} else if ("officeRoom".equals(list.get(i).getVariableName())) {
				OaApplyOfficeroom officeRoom = (OaApplyOfficeroom) list.get(i)
						.getValue();
				map.put("officeRoom", officeRoom);
				map.put("oneLevel", returnName(officeRoom.getAuditFirId()));
				map.put("twoLevel", returnName(officeRoom.getAuditSecId()));
				map.put("threeLevel", returnName(officeRoom.getAuditThrId()));
				map.put("fourLevel", returnName(officeRoom.getAuditFouId()));
				map.put("type", "officeRoom");
			} else if ("projector".equals(list.get(i).getVariableName())) {
				OaApplyProjector projector = (OaApplyProjector) list.get(i)
						.getValue();
				map.put("projector", projector);
				map.put("oneLevel", returnName(projector.getAuditFirId()));
				map.put("twoLevel", returnName(projector.getAuditSecId()));
				map.put("threeLevel", returnName(projector.getAuditThrId()));
				map.put("fourLevel", returnName(projector.getAuditFouId()));
				map.put("type", "projector");
			} else if ("currency".equals(list.get(i).getVariableName())) {
				OaApply currency = (OaApply) list.get(i).getValue();
				map.put("currency", currency);
				map.put("oneLevel", returnName(currency.getAuditFirId()));
				map.put("twoLevel", returnName(currency.getAuditSecId()));
				map.put("threeLevel", returnName(currency.getAuditThrId()));
				map.put("fourLevel", returnName(currency.getAuditFouId()));
				map.put("type", "currency");
			} else if ("oaDocApproval".equals(list.get(i).getVariableName())) {
				OaDocApproval doc = (OaDocApproval) list.get(i).getValue();
				map.put("doc", doc);
				map.put("type", "doc");
				OaDispatch dispatch = oaDispatchService.getByDocApprovalId(doc
						.getId());
				map.put("filePath", dispatch.getFilePath().replace("|", ""));
				map.put("fileName", dispatch.getTitle());
			}
		}
		return "modules/oa/leaveDetail";
	}

	/**
	 * 修改任务 选择重新发起火结束
	 */
	@RequestMapping("modifyTask")
	public String modifyTask(String flag, String taskId,
			RedirectAttributes attribute) {
		String type = (String) taskService.getVariable(taskId, "type");
		attribute.addAttribute("taskId", taskId);
		attribute.addAttribute("flag", flag);
		attribute.addAttribute("nowposition", "rejectEnd");
		if (type.equals("leave")) {
			return "redirect:" + adminPath + "/oa/leave/modifyLeave";
		} else if (type.equals("overTime")) {
			return "redirect:" + adminPath
					+ "/oa/approvalProcess/modifyOverTime";
		} else if (type.equals("officeRoom")) {
			return "redirect:" + adminPath
					+ "/oa/approvalProcess/modifyOfficeRoom";
		} else if (type.equals("projector")) {
			return "redirect:" + adminPath
					+ "/oa/approvalProcess/modifyProjector";
		} else if (type.equals("currency")) {
			return "redirect:" + adminPath
					+ "/oa/approvalProcess/modifyCurrency";
		} else if (type.equals("doc")) {
			return "redirect:" + adminPath
					+ "/oa/approvalProcess/modifyDocument";
		}
		return null;
	}

	/**
	 * 抄送我的
	 */
	@RequestMapping(value = "self")
	public String selfList(OaNotify oaNotify, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		oaNotify.setSelf(true);
		oaNotify.setType("spcs");
		Page<OaNotify> page = oaNotifyService.find2(new Page<OaNotify>(request,
				response), oaNotify);
		model.addAttribute("page", page);
		return "modules/act/oaNotifyList";
	}

	/**
	 * 抄送我的详情
	 */
	@RequestMapping(value = "view")
	public String view(OaNotify oaNotify, Model model) {
		// if (StringUtils.isNotBlank(oaNotify.getId())){
		// oaNotifyService.updateReadFlag(oaNotify);
		OaNotify notify = oaNotifyService.get(oaNotify);
		oaNotify = oaNotifyService.getRecordList(oaNotify);
		notify.setOaNotifyRecordList(oaNotify.getOaNotifyRecordList());
		notify.setStatus("1");
		model.addAttribute("oaNotify", notify);
		return "modules/act/oaNotifyForm";
		// }
		// return "redirect:" + adminPath + "/act/task/self";
	}

	/**
	 * 删除抄送我的
	 */
	@RequestMapping(value = "deleteSelf")
	public String delete(OaNotify oaNotify,
			RedirectAttributes redirectAttributes) {
		oaNotifyService.delete(oaNotify);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/act/task/self";
	}

	/**
	 * 查询我发起的流程
	 */
	@RequestMapping("myProcess")
	public String selectMyProcess(ModelMap map, String type, Act act,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		if ("leave".equals(type)) {
			OaLeaved leave = new OaLeaved();
			leave.setCreateBy(user);
			List<OaLeaved> list = oaLeavedService.findList(leave);
			for (OaLeaved oaLeaved : list) {
				String flag = oaLeavedService.returnFlag(oaLeaved);// y通过 n未通过
																	// z审批中
				oaLeaved.setYuliu5(user.getName() + "的请假申请");
				oaLeaved.setYuliu4(flag);
			}
			map.put("list", list);
		} else if ("overTime".equals(type)) {
			OaOvertime overtime = new OaOvertime();
			overtime.setCreateBy(user);
			List<OaOvertime> list = oaOvertimeService.findList(overtime);
			for (OaOvertime oaOvertime : list) {
				String flag = oaOvertimeService.returnFlag(oaOvertime);// y通过
																		// n未通过
																		// z审批中
				oaOvertime.setYuliu5(user.getName() + "的加班申请");
				oaOvertime.setYuliu4(flag);
			}
			map.put("list", list);
		} else if ("officeRoom".equals(type)) {
			OaApplyOfficeroom officeroom = new OaApplyOfficeroom();
			officeroom.setCreateBy(user);
			List<OaApplyOfficeroom> list = oaApplyOfficeroomService
					.findList(officeroom);
			for (OaApplyOfficeroom oaApplyOfficeroom : list) {
				String flag = oaApplyOfficeroomService
						.returnFlag(oaApplyOfficeroom);// y通过 n未通过 z审批中
				oaApplyOfficeroom.setYuliu5(user.getName() + "的会议室申请");
				oaApplyOfficeroom.setYuliu4(flag);
			}
			map.put("list", list);
		} else if ("projector".equals(type)) {
			OaApplyProjector projector = new OaApplyProjector();
			projector.setCreateBy(user);
			List<OaApplyProjector> list = oaApplyProjectorService
					.findList(projector);
			for (OaApplyProjector oaApplyProjector : list) {
				String flag = oaApplyProjectorService
						.returnFlag(oaApplyProjector);// y通过 n未通过 z审批中
				oaApplyProjector.setYuliu5(user.getName() + "的投影仪申请");
				oaApplyProjector.setYuliu4(flag);
			}
			map.put("list", list);
		} else /* if("currency".equals(type)) */{
			OaApply oaApply = new OaApply();
			oaApply.setCreateBy(user);
			List<OaApply> list = oaApplyService.findList(oaApply);
			for (OaApply apply : list) {
				String flag = oaApplyService.returnFlag(apply);// y通过 n未通过 z审批中
				apply.setYuliu5(user.getName() + "的用章申请");
				apply.setYuliu4(flag);
			}
			map.put("list", list);
		}
		map.put("type", type);

		// ------------------------
		/*
		 * Page<Act> page = new Page<Act>(request, response); page =
		 * actTaskService.historicList(page, act); List<Act> allList =
		 * page.getList(); ArrayList<Act> arrayList = new ArrayList<Act>();
		 * List<HistoricProcessInstance> list =
		 * historyService.createHistoricProcessInstanceQuery
		 * ().startedBy(UserUtils.getUser().getId()).list(); for (Act a :
		 * allList) { for (HistoricProcessInstance his : list) {
		 * if((a.getHistTask().getProcessInstanceId()).equals(his.getId())){
		 * Variable vars = a.getVars(); System.out.println(vars.getKeys());
		 * List<HistoricVariableInstance> varlist =
		 * historyService.createHistoricVariableInstanceQuery
		 * ().processInstanceId(his.getId()).list(); for
		 * (HistoricVariableInstance hi : varlist) {
		 * if("title".equals(hi.getVariableName())){
		 * a.setTitle(hi.getValue().toString()); }
		 * if("nowpositon".equals(hi.getVariableName())){
		 * a.setComment(hi.getValue().toString()); }
		 * if("type".equals(hi.getVariableName()) &&
		 * type.equals(hi.getValue().toString())){
		 * if("leave".equals(type)){a.setTitle
		 * (UserUtils.getUser().getName()+"的请假申请");} else
		 * if("overTime".equals(type
		 * )){a.setTitle(UserUtils.getUser().getName()+"的加班申请");} else
		 * if("officeRoom"
		 * .equals(type)){a.setTitle(UserUtils.getUser().getName()+"的会议室申请");}
		 * else
		 * if("projector".equals(type)){a.setTitle(UserUtils.getUser().getName
		 * ()+"的投影仪申请");}
		 * else("currency".equals(type)){a.setTitle(UserUtils.getUser
		 * ().getName()+"的申请");} arrayList.add(a); } }
		 * 
		 * } } } model.addAttribute("type",type);
		 * model.addAttribute("list",arrayList);
		 */
		return "modules/oa/approvalProcess2";
	}

	/**
	 * 删除我发起的审批通过的会议室申请
	 */
	@RequestMapping("deleteOffice")
	@ResponseBody
	public String deleteOffice(String taskId, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		OaApplyOfficeroom officeRoom = oaApplyOfficeroomService
				.get(new OaApplyOfficeroom(taskId));
		String procInsId = officeRoom.getProcessInstanceId();
		if (procInsId != null && !("").equals(procInsId)) {
			/** 判断流程是否结束，查询正在执行的执行对象表 */
			ProcessEngine processEngine = ProcessEngines
					.getDefaultProcessEngine();
			ProcessInstance rpi = processEngine.getRuntimeService()//
					.createProcessInstanceQuery()// 创建流程实例查询对象
					.processInstanceId(procInsId).singleResult();
			if (rpi != null) {
				actProcessService.deleteProcIns(procInsId, "删除正在执行的流程实例!");
				OaNotify oaNotify = oaNotifyService.get(procInsId);
				if (oaNotify != null || !("").equals(oaNotify)) {
					oaNotifyService.delete(oaNotify);
				}
				oaApplyOfficeroomService.delete(officeRoom);
				return new Gson().toJson("success");
			} else {
				historyService.deleteHistoricProcessInstance(procInsId);
				OaNotify oaNotify = oaNotifyService.get(procInsId);
				if (oaNotify != null || !("").equals(oaNotify)) {
					oaNotifyService.delete(oaNotify);
				}
				oaApplyOfficeroomService.delete(officeRoom);
				// addMessage(redirectAttributes, "取消流程成功，实例ID=" + procInsId);
				return new Gson().toJson("success");
			}
		}
		return new Gson().toJson("success");
	}

	/***
	 * 我发起的详情
	 */
	@RequestMapping("myProcessDetail")
	@ResponseBody
	public String myProcessDetail(String taskId, ModelMap map, String type) {
		User user = UserUtils.getUser();
		if ("leave".equals(type)) {
			OaLeaved leave = oaLeavedService.get(new OaLeaved(taskId));
			leave.setYuliu5(user.getName() + "的请假申请");
			String flag = oaLeavedService.returnFlag(leave);// y通过 n未通过 z审批中
			leave.setYuliu4(flag);
			return new Gson().toJson(leave);
		} else if ("overTime".equals(type)) {
			OaOvertime overTime = oaOvertimeService.get(new OaOvertime(taskId));
			overTime.setYuliu5(user.getName() + "的加班申请");
			String flag = oaOvertimeService.returnFlag(overTime);// y通过 n未通过
																	// z审批中
			overTime.setYuliu4(flag);
			return new Gson().toJson(overTime);
		} else if ("officeRoom".equals(type)) {
			OaApplyOfficeroom officeRoom = oaApplyOfficeroomService
					.get(new OaApplyOfficeroom(taskId));
			officeRoom.setYuliu5(user.getName() + "的会议室申请");
			String flag = oaApplyOfficeroomService.returnFlag(officeRoom);// y通过
																			// n未通过
																			// z审批中
			officeRoom.setYuliu4(flag);
			return new Gson().toJson(officeRoom);
		} else if ("projector".equals(type)) {
			OaApplyProjector projector = oaApplyProjectorService
					.get(new OaApplyProjector(taskId));
			projector.setYuliu5(user.getName() + "的投影仪申请");
			String flag = oaApplyProjectorService.returnFlag(projector);// y通过
																		// n未通过
																		// z审批中
			projector.setYuliu4(flag);
			return new Gson().toJson(projector);
		} else /* if("currency".equals(type)) */{
			OaApply apply = oaApplyService.get(new OaApply(taskId));
			apply.setYuliu5(user.getName() + "的用章申请");
			String flag = oaApplyService.returnFlag(apply);// y通过 n未通过 z审批中
			apply.setYuliu4(flag);
			return new Gson().toJson(apply);
		}

		/*
		 * Task task = actTaskService.taskDetail(taskId); String title = "";
		 * if(null==task || "".equals(task) || "null".equals(task)){
		 * HistoricTaskInstance result =
		 * historyService.createHistoricTaskInstanceQuery
		 * ().taskId(taskId).singleResult(); List<HistoricVariableInstance> list
		 * =
		 * historyService.createHistoricVariableInstanceQuery().processInstanceId
		 * (result.getProcessInstanceId()).list(); for (int i = 0; i <
		 * list.size(); i++) {
		 * //System.out.println(list.get(i).getVariableName()
		 * +"---"+list.get(i).getValue());
		 * if("applyUserId".equals(list.get(i).getVariableName())){
		 * map.put("applyUserId", list.get(i).getValue()); }
		 * if("title".equals(list.get(i).getVariableName())){ title =
		 * list.get(i).getValue().toString(); } } for (int i = 0; i <
		 * list.size(); i++) { if(type.equals("leave")){
		 * if(type.equals(list.get(i).getVariableName())){ OaLeaved lea =
		 * (OaLeaved)list.get(i).getValue(); lea.setYuliu5(title); return new
		 * Gson().toJson(lea); } }else if(type.equals("overTime")){
		 * if(type.equals(list.get(i).getVariableName())){ OaOvertime over =
		 * (OaOvertime)list.get(i).getValue(); over.setYuliu5(title); return new
		 * Gson().toJson(over); } }else if(type.equals("officeRoom")){
		 * if(type.equals(list.get(i).getVariableName())){ OaApplyOfficeroom
		 * room = (OaApplyOfficeroom)list.get(i).getValue();
		 * room.setYuliu5(title); return new Gson().toJson(room); } }else
		 * if(type.equals("projector")){
		 * if(type.equals(list.get(i).getVariableName())){ OaApplyProjector pro
		 * = (OaApplyProjector)list.get(i).getValue(); pro.setYuliu5(title);
		 * return new Gson().toJson(pro); } }else if(type.equals("currency")){
		 * if(type.equals(list.get(i).getVariableName())){ OaApply app =
		 * (OaApply)list.get(i).getValue(); app.setYuliu5(title); return new
		 * Gson().toJson(app); } } } return new Gson().toJson(""); }else{ return
		 * new Gson().toJson(""); }
		 */
	}

	public static String returnName(String string) {
		StringBuffer sb = new StringBuffer();
		if ("".equals(string) || string == null) {
			return "";
		} else {
			String[] split = string.split(",");
			for (int i = 0; i < split.length; i++) {
				sb.append(UserUtils.get(split[i]).getName()).append(",");
			}
			return sb.toString();
		}
	}

}
