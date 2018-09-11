/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.web;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.modules.act.utils.Variable;
import com.thinkgem.jeesite.modules.oa.dao.OaNoticeDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNoticeRecordDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyDao;
import com.thinkgem.jeesite.modules.oa.entity.Currency;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.entity.OaNotice;
import com.thinkgem.jeesite.modules.oa.entity.OaNoticeRecord;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 流程个人任务相关Controller
 * @author ThinkGem
 * @version 2013-11-03
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/act/task")
public class AppActTaskController extends BaseController {

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
	private OaNotifyDao oaNotifyDao;
	@Autowired
	private OaNoticeDao oaNoticeDao;
	@Autowired
	private OaNoticeRecordDao oaNoticeRecordDao;
	@Autowired
	private ActProcessService actProcessService;
	@Autowired
	private OaApplyOfficeroomService oaApplyOfficeroomService;
	/**
	 * 获取待办列表
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = {"todo", ""})
	@ResponseBody
	public JsonResult<Map<String,Object>> todo( HttpServletResponse response, Model model) throws Exception {
		//查询所有待办任务列表
		Act act3 = new Act();
		Act act=new Act();
		act3.setBeginDate(act.getBeginDate());
		act3.setEndDate(act.getEndDate());
		User user=UserUtils.getUser();
		List<Act> list = actTaskService.todoList(act3);
		List<Act> arrayList = new ArrayList<Act>();
			for (Act act2 : list) {
				Task task = act2.getTask();
				String title = (String)taskService.getVariable(task.getId(), "title");
				act2.setTitle(title);
				String type=(String)taskService.getVariable(task.getId(), "type");
				act2.setType(type);
				String comment=(String)taskService.getVariable(task.getId(), "nowposition");
				String applyName=(String)taskService.getVariable(task.getId(), "applyName");
				act2.setApplyName(applyName);
				act2.setComment(comment);
				if(type.equals("leave")){
					OaLeaved oaLeaved=(OaLeaved)taskService.getVariable(task.getId(), type);
				   	act2.setStartTime(oaLeaved.getStartTime());
				   	act2.setEndTime(oaLeaved.getEndTime());
				   	act2.setLeaveType(oaLeaved.getLeaveType());
				}else if(type.equals("overTime")){
					OaOvertime oaOvertime=(OaOvertime)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaOvertime.getStartTime());
					act2.setEndTime(oaOvertime.getEndTime());
				}else if(type.equals("officeRoom")){
					OaApplyOfficeroom oaApplyOfficeRoom=(OaApplyOfficeroom)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaApplyOfficeRoom.getStartTime());
					act2.setEndTime(oaApplyOfficeRoom.getEndTime());
				}else if(type.equals("projector")){
					OaApplyProjector oaApplyProjector=(OaApplyProjector)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaApplyProjector.getStartTime());
					act2.setEndTime(oaApplyProjector.getEndTime());
				}else if(type.equals("currency")){
					OaApply oaApply=(OaApply)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaApply.getStartTime());
					act2.setEndTime(oaApply.getEndTime());	
				}
			}
			arrayList = list;
		for(Act act5:arrayList){
			act5.setVars(new Variable());
		}
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d= sdf.format(0L);
		Date date=sdf.parse(d);
		for(Act ac:arrayList){ 
			Map<String,Object> map2=new HashMap<String,Object>();
			map2.put("title", ac.getTitle());
			map2.put("comment", ac.getComment());
			map2.put("taskId", ac.getTaskId());
			map2.put("type", ac.getType());
			map2.put("startTime", ac.getStartTime()==null?date:ac.getStartTime());
			map2.put("endTime", ac.getEndTime()==null?date:ac.getEndTime());
			map2.put("leaveType", ac.getLeaveType());
			map2.put("applyUserId", ac.getApplyUserId());
			map2.put("taskCreateDate", ac.getTaskCreateDate());
			map2.put("applyName", ac.getApplyName());
			map2.put("taskName", ac.getTaskName());
			listmap.add(map2);
			}
		map.put("list", listmap);
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
	}
	
	/**
	 * 获取待办任务条数  ztime 0.04
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public JsonResult<Map<String,Object>> selfCount(Act act, HttpServletResponse response, Model model) {
		long a=taskService.createTaskQuery().taskAssignee(UserUtils.getUser().getId()).count();
		long b=taskService.createTaskQuery().taskCandidateUser(UserUtils.getUser().getId()).count();
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("arrayListSize", a+b+"");
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
	}
	
	
	
	/**
	 * 获取已办任务
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = "historic")
	@ResponseBody
	public JsonResult<Map<String,Object>> historic( HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Act act3 = new Act();
		Act act = new Act();
		//User user=UserUtils.getUser();
		act3.setBeginDate(act.getBeginDate());
		// String b= request.getSession().getId();
		act3.setEndDate(act.getEndDate());	
		String UserId=UserUtils.getUser().getId();
		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act3);		
		Page<Act> page2 = new Page<Act>(request, response);	
			List<Act> list = page.getList();
			for (Act act2 : list) {
				HistoricTaskInstance task = act2.getHistTask();
				act2.setTaskId(task.getId());
				
				List<HistoricVariableInstance> list2 = historyService.createHistoricVariableInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
				for (HistoricVariableInstance his : list2) {
					if(his.getVariableName().equals("title")){
						act2.setTitle(his.getValue().toString());
					}
					if(his.getVariableName().equals("nowposition")){
						act2.setComment(his.getValue().toString());
					}
					if(his.getVariableName().equals("type")){
						act2.setType(his.getValue().toString());
					}
					if(his.getVariableName().equals("applyUserId")){
						act2.setApplyUserId(his.getValue().toString());
					}
					if(his.getVariableName().equals("applyName")){
						act2.setApplyName(his.getValue().toString());
					}
					if(his.getVariableName().equals("leave")){
					   	OaLeaved oaLeaved=(OaLeaved)his.getValue();
					   	act2.setStartTime(oaLeaved.getStartTime());
					   	act2.setEndTime(oaLeaved.getEndTime());
					   	act2.setLeaveType(oaLeaved.getLeaveType());
					   	if((oaLeaved.getAuditFirId()!=null&&oaLeaved.getAuditFirId().equals(UserId))||
					   			(oaLeaved.getAuditSecId()!=null&&oaLeaved.getAuditSecId().equals(UserId))||	
					   			(oaLeaved.getAuditThrId()!=null&&oaLeaved.getAuditThrId().equals(UserId))||
					   			(oaLeaved.getAuditFouId()!=null&&oaLeaved.getAuditFouId().equals(UserId))
					   		){
					   		act2.setProcessStatus("true");
					   	}else{
					   		act2.setProcessStatus("false");
					   	}
					}else if(his.getVariableName().equals("overTime")){
						OaOvertime oaOvertime=(OaOvertime)his.getValue();
						act2.setStartTime(oaOvertime.getStartTime());
						act2.setEndTime(oaOvertime.getEndTime());
						if((oaOvertime.getAuditFirId()!=null&&oaOvertime.getAuditFirId().equals(UserId))||
					   			(oaOvertime.getAuditSecId()!=null&&oaOvertime.getAuditSecId().equals(UserId))||	
					   			(oaOvertime.getAuditThrId()!=null&&oaOvertime.getAuditThrId().equals(UserId))||
					   			(oaOvertime.getAuditFouId()!=null&&oaOvertime.getAuditFouId().equals(UserId))
					   		){
							act2.setProcessStatus("true");
					   	}else{
					   		act2.setProcessStatus("false");
					   	}
						
					}else if(his.getVariableName().equals("officeRoom")){
						OaApplyOfficeroom oaApplyOfficeRoom=(OaApplyOfficeroom)his.getValue();
						act2.setStartTime(oaApplyOfficeRoom.getStartTime());
						act2.setEndTime(oaApplyOfficeRoom.getEndTime());
						if((oaApplyOfficeRoom.getAuditFirId()!=null&&oaApplyOfficeRoom.getAuditFirId().equals(UserId))||
					   			(oaApplyOfficeRoom.getAuditSecId()!=null&&oaApplyOfficeRoom.getAuditSecId().equals(UserId))||	
					   			(oaApplyOfficeRoom.getAuditThrId()!=null&&oaApplyOfficeRoom.getAuditThrId().equals(UserId))||
					   			(oaApplyOfficeRoom.getAuditFouId()!=null&&oaApplyOfficeRoom.getAuditFouId().equals(UserId))
					   		){
							act2.setProcessStatus("true");
					   	}else{
					   		act2.setProcessStatus("false");
					   	}
						
						
						
						
					}else if(his.getVariableName().equals("projector")){
						OaApplyProjector oaApplyProjector=(OaApplyProjector)his.getValue();
						act2.setStartTime(oaApplyProjector.getStartTime());
						act2.setEndTime(oaApplyProjector.getEndTime());
						if((oaApplyProjector.getAuditFirId()!=null&&oaApplyProjector.getAuditFirId().equals(UserId))||
					   			(oaApplyProjector.getAuditSecId()!=null&&oaApplyProjector.getAuditSecId().equals(UserId))||	
					   			(oaApplyProjector.getAuditThrId()!=null&&oaApplyProjector.getAuditThrId().equals(UserId))||
					   			(oaApplyProjector.getAuditFouId()!=null&&oaApplyProjector.getAuditFouId().equals(UserId))
					   		){
							act2.setProcessStatus("true");
					   	}else{
					   		act2.setProcessStatus("false");
					   	}
						
						
						
						
					}else if(his.getVariableName().equals("currency")){
						OaApply oaApply=(OaApply)his.getValue();
						act2.setStartTime(oaApply.getStartTime());
						act2.setEndTime(oaApply.getEndTime());
						if((oaApply.getAuditFirId()!=null&&oaApply.getAuditFirId().equals(UserId))||
					   			(oaApply.getAuditSecId()!=null&&oaApply.getAuditSecId().equals(UserId))||	
					   			(oaApply.getAuditThrId()!=null&&oaApply.getAuditThrId().equals(UserId))||
					   			(oaApply.getAuditFouId()!=null&&oaApply.getAuditFouId().equals(UserId))
					   		){
							act2.setProcessStatus("true");
					   	}else{
					   		act2.setProcessStatus("false");
					   	}	
					}
				}
			}
			page2.setList(list);
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> Listmap=new ArrayList<Map<String,Object>>();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d= sdf.format(0L);
		Date date=sdf.parse(d);  
		
		for(Act act1:page2.getList()){
		if((act1.getApplyUserId()!=null&&!act1.getApplyUserId().equals(UserId))||
				(act1.getProcessStatus()!=null&&act1.getProcessStatus().equals("true"))){
		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("title", act1.getTitle());
		map2.put("processDefinitionId", act1.getProcDef().getId());
		map2.put("taskEndDate", act1.getHistTask().getStartTime());	
	    map2.put("taskId", act1.getHistTask().getId());
	    map2.put("taskName", act1.getHistTask().getName());
	    map2.put("taskDefKey", act1.getHistTask().getTaskDefinitionKey());
	    map2.put("procInsId", act1.getHistTask().getProcessInstanceId());
	    map2.put("procDefId", act1.getHistTask().getProcessDefinitionId());
	    map2.put("procDefName", act1.getProcDef().getName());
	    map2.put("status", act1.getStatus());
	    map2.put("type", act1.getType());
	    map2.put("startTime", act1.getStartTime()==null?date:act1.getStartTime());
	    map2.put("endTime",act1.getEndTime()==null?date:act1.getEndTime());
	    map2.put("comment", act1.getComment());
	    map2.put("leaveType", act1.getLeaveType());
	    map2.put("applyName", act1.getApplyName());
	    Listmap.add(map2);
		}
		}
	    map.put("ListMap", Listmap);

		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查询成功",map);
	}


	
	/**
	 * 完成任务
	 * @param taskId 任务ID
	 * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
	 * @param comment 任务提交意见的内容
	 * @param vars 任务流程变量，如下
	 * 		vars.keys=flag,pass
	 * 		vars.values=1,true
	 * 		vars.types=S,B  @see com.thinkgem.jeesite.modules.act.utils.PropertyType
	 */
	@RequestMapping(value = "complete")
	@ResponseBody
	public String complete(Act act) {
		actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), act.getVars().getVariableMap());
		return "true";//adminPath + "/act/task";
	}
	

	
	/**
	 * 办理任务
	 */
	
	@RequestMapping(value = "handleTask")
	@ResponseBody
	public JsonResult<Map<String,Object>> handleTask(String taskId,String flag,String approvalResult) {
		String userId = UserUtils.getUser().getId();
		actTaskService.claim(taskId, userId);//先签收任务
		Map<String,Object> map=taskService.getVariables(taskId);
		String executionId=actTaskService.getTask(taskId).getExecutionId();
		actTaskService.handleTask(taskId,flag,approvalResult);//处理签收完的任务
		                      
		if(flag.equals("n")){//直接处理退回的申请
			//HashMap<String, Object> variables = new HashMap<String,Object>();
			//获取任务获取id结束任务
		List<Task> task=taskService.createTaskQuery().executionId(executionId).list();
		map.put("nowposition", "rejectEnd");
		taskService.complete(task.get(0).getId(),map);
		}
		return new JsonResult<Map<String,Object>>("0","办理成功",null);
	}
	
	/**
	 * 待办任务详情
	 *
	 */
	@RequestMapping("detail")
	@ResponseBody
	public JsonResult<Map<String,Object>> taskDetail(String taskId){
		Task task = actTaskService.taskDetail(taskId);
		Map<String,Object> map=new HashMap<String,Object>();
		//map.put("task", task);
		map.put("detailFlag", "todo");//区分待办还是未办
		Map<String, Object> variables = taskService.getVariables(taskId);
		map.put("applyUserId", variables.get("applyUserId").toString());
		map.put("currentUser", UserUtils.getUser().getId());
		map.put("title", variables.get("title"));
		map.put("applyName", variables.get("applyName"));
		if((variables.get("type").toString()).equals("leave")){
			    OaLeaved leave=	(OaLeaved)variables.get("leave");
			    OaLeaved leave1=new OaLeaved();
			    leave1.setApplyName(leave.getApplyName());
			    leave1.setApplyTime(leave.getApplyTime());
			    leave1.setAuditFirId(leave.getAuditFirId()==null?"":UserIdToName(leave.getAuditFirId()));
			    leave1.setAuditSecId(leave.getAuditSecId()==null?"":UserIdToName(leave.getAuditSecId()));
			    leave1.setAuditThrId(leave.getAuditThrId()==null?"":UserIdToName(leave.getAuditThrId()));
			    leave1.setAuditFouId(leave.getAuditFouId()==null?"":UserIdToName(leave.getAuditFouId()));
			    leave1.setAuditFirState(leave.getAuditFirState());
			    leave1.setAuditSecState(leave.getAuditSecState());
			    leave1.setAuditThrState(leave.getAuditThrState());
			    leave1.setAuditFouState(leave.getAuditFouState());
			    leave1.setEndAuditState(leave.getEndAuditState());
			    leave1.setStartTime(leave.getStartTime());
			    leave1.setAuditFirInfo(leave.getAuditFirInfo());
			    leave1.setAuditSecInfo(leave.getAuditSecInfo());
			    leave1.setAuditThrInfo(leave.getAuditThrInfo());
			    leave1.setAuditFouInfo(leave.getAuditFouInfo());
			    leave1.setEndTime(leave.getEndTime());
			    leave1.setCompany(leave.getCompany());
			    leave1.setCopytoId((leave.getCopytoId()==null||leave.getCopytoId().equals(""))?"":UserIdToName(leave.getCopytoId()));
			    leave1.setLeaveType(leave.getLeaveType());
			    leave1.setHours(leave.getHours());
			    leave1.setOffice(leave.getOffice());
			    leave1.setReason(leave.getReason());
				map.put("leave", leave1);
		}else if((variables.get("type").toString()).equals("overTime")){
			    OaOvertime oaOvertime=(OaOvertime)variables.get("overTime");
			    OaOvertime oaOvertime1=new OaOvertime();
                oaOvertime1.setAccountType(oaOvertime.getAccountType());
			    oaOvertime1.setApplyName(oaOvertime.getApplyName());
			    oaOvertime1.setApplyTime(oaOvertime.getApplyTime());
			    oaOvertime1.setAuditFirId(oaOvertime.getAuditFirId()==null?"":UserIdToName(oaOvertime.getAuditFirId()));
			    oaOvertime1.setAuditSecId(oaOvertime.getAuditSecId()==null?"":UserIdToName(oaOvertime.getAuditSecId()));
			    oaOvertime1.setAuditThrId(oaOvertime.getAuditThrId()==null?"":UserIdToName(oaOvertime.getAuditThrId()));
			    oaOvertime1.setAuditFouId(oaOvertime.getAuditFouId()==null?"":UserIdToName(oaOvertime.getAuditFouId()));
			    oaOvertime1.setAuditFirState(oaOvertime.getAuditFirState());
			    oaOvertime1.setAuditSecState(oaOvertime.getAuditSecState());
			    oaOvertime1.setAuditThrState(oaOvertime.getAuditThrState());
			    oaOvertime1.setAuditFouState(oaOvertime.getAuditFouState());
			    oaOvertime1.setEndAuditState(oaOvertime.getEndAuditState());
			    oaOvertime1.setStartTime(oaOvertime.getStartTime());
			    oaOvertime1.setAuditFirInfo(oaOvertime.getAuditFirInfo());
			    oaOvertime1.setAuditSecInfo(oaOvertime.getAuditSecInfo());
			    oaOvertime1.setAuditThrInfo(oaOvertime.getAuditThrInfo());
			    oaOvertime1.setAuditFouInfo(oaOvertime.getAuditFouInfo());
			    oaOvertime1.setEndTime(oaOvertime.getEndTime());
			    oaOvertime1.setCompany(oaOvertime.getCompany());
			    oaOvertime1.setCopytoId((oaOvertime.getCopytoId()==null||oaOvertime.getCopytoId().equals(""))?"":UserIdToName(oaOvertime.getCopytoId()));
			    oaOvertime1.setHours(oaOvertime.getHours());
			    oaOvertime1.setOffice(oaOvertime.getOffice());
			    oaOvertime1.setReason(oaOvertime.getReason());
			    oaOvertime1.setIsHoliday(oaOvertime.getIsHoliday());
			    map.put("overTime", oaOvertime1);
		}else if((variables.get("type").toString()).equals("officeRoom")){
			OaApplyOfficeroom oaApplyOfficeroom=(OaApplyOfficeroom)variables.get("officeRoom");
			OaApplyOfficeroom oaApplyOfficeroom1=new OaApplyOfficeroom();
			oaApplyOfficeroom1.setApplyName(oaApplyOfficeroom.getApplyName());
			oaApplyOfficeroom1.setApplyTime(oaApplyOfficeroom.getApplyTime());
			oaApplyOfficeroom1.setAuditFirId(oaApplyOfficeroom.getAuditFirId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditFirId()));
			oaApplyOfficeroom1.setAuditSecId(oaApplyOfficeroom.getAuditSecId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditSecId()));
			oaApplyOfficeroom1.setAuditThrId(oaApplyOfficeroom.getAuditThrId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditThrId()));
			oaApplyOfficeroom1.setAuditFouId(oaApplyOfficeroom.getAuditFouId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditFouId()));
			oaApplyOfficeroom1.setAuditFstate(oaApplyOfficeroom.getAuditFstate());
			oaApplyOfficeroom1.setAuditSstate(oaApplyOfficeroom.getAuditSstate());
			oaApplyOfficeroom1.setAuditTstate(oaApplyOfficeroom.getAuditTstate());
			oaApplyOfficeroom1.setAuditFouState(oaApplyOfficeroom.getAuditFouState());
			oaApplyOfficeroom1.setEndAuditState(oaApplyOfficeroom.getEndAuditState());
			oaApplyOfficeroom1.setStartTime(oaApplyOfficeroom.getStartTime());
			oaApplyOfficeroom1.setOfficeroomId(oaApplyOfficeroom.getOfficeroomId());
			oaApplyOfficeroom1.setAuditFirInfo(oaApplyOfficeroom.getAuditFirInfo());
			oaApplyOfficeroom1.setAuditSecInfo(oaApplyOfficeroom.getAuditSecInfo());
			oaApplyOfficeroom1.setAuditThrInfo(oaApplyOfficeroom.getAuditThrInfo());
			oaApplyOfficeroom1.setAuditFouInfo(oaApplyOfficeroom.getAuditFouInfo());
			oaApplyOfficeroom1.setEndTime(oaApplyOfficeroom.getEndTime());
			oaApplyOfficeroom1.setCompany(oaApplyOfficeroom.getCompany());
			oaApplyOfficeroom1.setCopytoId((oaApplyOfficeroom.getCopytoId()==null||oaApplyOfficeroom.getCopytoId().equals(""))?"":UserIdToName(oaApplyOfficeroom.getCopytoId()));		
			oaApplyOfficeroom1.setHours(oaApplyOfficeroom.getHours());
			oaApplyOfficeroom1.setOffice(oaApplyOfficeroom.getOffice());
			oaApplyOfficeroom1.setReason(oaApplyOfficeroom.getReason());
            map.put("officeRoom", oaApplyOfficeroom1);

		}else if((variables.get("type").toString()).equals("projector")){
			OaApplyProjector oaApplyProjector=(OaApplyProjector)variables.get("projector");
			OaApplyProjector oaApplyProjector1=new OaApplyProjector();
			oaApplyProjector1.setOfficeNames(oaApplyProjector.getOfficeNames());
			oaApplyProjector1.setOfficeroomId(oaApplyProjector.getOfficeroomId());
			oaApplyProjector1.setApplyTime(oaApplyProjector.getApplyTime());
			oaApplyProjector1.setAuditFirId(oaApplyProjector.getAuditFirId()==null?"":UserIdToName(oaApplyProjector.getAuditFirId()));
			oaApplyProjector1.setAuditSecId(oaApplyProjector.getAuditSecId()==null?"":UserIdToName(oaApplyProjector.getAuditSecId()));
			oaApplyProjector1.setAuditThrId(oaApplyProjector.getAuditThrId()==null?"":UserIdToName(oaApplyProjector.getAuditThrId()));
			oaApplyProjector1.setAuditFouId(oaApplyProjector.getAuditFouId()==null?"":UserIdToName(oaApplyProjector.getAuditFouId()));
			oaApplyProjector1.setAuditFstate(oaApplyProjector.getAuditFstate());
			oaApplyProjector1.setAuditSstate(oaApplyProjector.getAuditSstate());
			oaApplyProjector1.setAuditTstate(oaApplyProjector.getAuditTstate());
			oaApplyProjector1.setAuditFouState(oaApplyProjector.getAuditFouState());
			oaApplyProjector1.setEndAuditState(oaApplyProjector.getEndAuditState());
			oaApplyProjector1.setStartTime(oaApplyProjector.getStartTime());
			oaApplyProjector1.setAuditFirInfo(oaApplyProjector.getAuditFirInfo());
			oaApplyProjector1.setAuditSecInfo(oaApplyProjector.getAuditSecInfo());
			oaApplyProjector1.setAuditThrInfo(oaApplyProjector.getAuditThrInfo());
			oaApplyProjector1.setAuditFouInfo(oaApplyProjector.getAuditFouInfo());
			oaApplyProjector1.setEndTime(oaApplyProjector.getEndTime());
			oaApplyProjector1.setCompany(oaApplyProjector.getCompany());
			oaApplyProjector1.setCopytoId((oaApplyProjector.getCopytoId()==null||oaApplyProjector.getCopytoId().equals(""))?"":UserIdToName(oaApplyProjector.getCopytoId()));
			oaApplyProjector1.setHours(oaApplyProjector.getHours());
			oaApplyProjector1.setOffice(oaApplyProjector.getOffice());
			oaApplyProjector1.setReason(oaApplyProjector.getReason());
			oaApplyProjector1.setAdapterId(oaApplyProjector.getAdapterId());
            map.put("projector", oaApplyProjector1);
		}else if((variables.get("type").toString()).equals("currency")){
			OaApply oaApply=(OaApply)variables.get("currency");
			OaApply oaApply1=new OaApply();
			oaApply1.setOfficeNames(oaApply.getOfficeNames());
			oaApply1.setApplyTime(oaApply.getApplyTime());
			oaApply1.setAuditFirId(oaApply.getAuditFirId()==null?"":UserIdToName(oaApply.getAuditFirId()));
			oaApply1.setAuditSecId(oaApply.getAuditSecId()==null?"":UserIdToName(oaApply.getAuditSecId()));
			oaApply1.setAuditThrId(oaApply.getAuditThrId()==null?"":UserIdToName(oaApply.getAuditThrId()));
			oaApply1.setAuditFouId(oaApply.getAuditFouId()==null?"":UserIdToName(oaApply.getAuditFouId()));
			oaApply1.setAuditFirState(oaApply.getAuditFirState());
			oaApply1.setAuditSecState(oaApply.getAuditSecState());
			oaApply1.setAuditThrState(oaApply.getAuditThrState());
			oaApply1.setAuditFouState(oaApply.getAuditFouState());
			oaApply1.setEndAuditState(oaApply.getEndAuditState());
			oaApply1.setStartTime(oaApply.getStartTime());
			oaApply1.setAuditFirInfo(oaApply.getAuditFirInfo());
			oaApply1.setAuditSecInfo(oaApply.getAuditSecInfo());
			oaApply1.setAuditThrInfo(oaApply.getAuditThrInfo());
			oaApply1.setAuditFouInfo(oaApply.getAuditFouInfo());
			oaApply1.setEndTime(oaApply.getEndTime());
			oaApply1.setApplyItem(oaApply.getApplyItem());
			oaApply1.setCompany(oaApply.getCompany());
			oaApply1.setCopytoId((oaApply.getCopytoId()==null||oaApply.getCopytoId().equals(""))?"":UserIdToName(oaApply.getCopytoId()));
			oaApply1.setOffice(oaApply.getOffice());
			oaApply1.setReason(oaApply.getReason());
			oaApply1.setFiles(oaApply.getFiles());
			map.put("currency",oaApply1);
		}else{

		}
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"请求成功",map);
	}
	
	/**
	 * 已办任务详情
	 */
	@RequestMapping("alreadyDetail")
	@ResponseBody
	public JsonResult<Map<String,Object>> alreadyDetail(String taskId){
		Map<String,Object> map=new HashMap<String,Object>();
		HistoricTaskInstance result = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		String instanceId = result.getProcessInstanceId();         
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(instanceId).list();
		map.put("detailFlag", "already");//区分待办还是未办
		for (int i = 0; i < list.size(); i++) {
			if("flag".equals(list.get(i).getVariableName())){
				map.put("flag", list.get(i).getValue());
			}
			if("applyName".equals(list.get(i).getVariableName())){
				map.put("applyName", list.get(i).getValue());
			}
			if("applyUserId".equals(list.get(i).getVariableName())){
				map.put("applyUserId", list.get(i).getValue());
			}
			if("title".equals(list.get(i).getVariableName())){
				map.put("title", list.get(i).getValue());
			}
			
			if("leave".equals(list.get(i).getVariableName())){
			    OaLeaved leave=	(OaLeaved)list.get(i).getValue();
			    OaLeaved leave1=new OaLeaved();
			    leave1.setApplyName(leave.getApplyName());
			    leave1.setApplyTime(leave.getApplyTime());
			    leave1.setAuditFirId(leave.getAuditFirId()==null?"":UserIdToName(leave.getAuditFirId()));
			    leave1.setAuditSecId(leave.getAuditSecId()==null?"":UserIdToName(leave.getAuditSecId()));
			    leave1.setAuditThrId(leave.getAuditThrId()==null?"":UserIdToName(leave.getAuditThrId()));
			    leave1.setAuditFouId(leave.getAuditFouId()==null?"":UserIdToName(leave.getAuditFouId()));
			    leave1.setAuditFirState(leave.getAuditFirState());
			    leave1.setAuditSecState(leave.getAuditSecState());
			    leave1.setAuditThrState(leave.getAuditThrState());
			    leave1.setAuditFouState(leave.getAuditFouState());
			    leave1.setEndAuditState(leave.getEndAuditState());
			    leave1.setStartTime(leave.getStartTime());
			    leave1.setAuditFirInfo(leave.getAuditFirInfo());
			    leave1.setAuditSecInfo(leave.getAuditSecInfo());
			    leave1.setAuditThrInfo(leave.getAuditThrInfo());
			    leave1.setAuditFouInfo(leave.getAuditFouInfo());
			    leave1.setEndTime(leave.getEndTime());
			    leave1.setCompany(leave.getCompany());
			    leave1.setCopytoId(leave.getCopytoId()==null?"":UserIdToName(leave.getCopytoId()));
			    leave1.setLeaveType(leave.getLeaveType());
			    leave1.setHours(leave.getHours());
			    leave1.setOffice(leave.getOffice());
			    leave1.setReason(leave.getReason());
				map.put("leave", leave1);
			}else if("overTime".equals(list.get(i).getVariableName())){
				OaOvertime oaOvertime=(OaOvertime)list.get(i).getValue();
				OaOvertime oaOvertime1=new OaOvertime();
                oaOvertime1.setAccountType(oaOvertime.getAccountType());
				oaOvertime1.setApplyName(oaOvertime.getApplyName());
				oaOvertime1.setApplyTime(oaOvertime.getApplyTime());
				oaOvertime1.setAuditFirId(oaOvertime.getAuditFirId()==null?"":UserIdToName(oaOvertime.getAuditFirId()));
				oaOvertime1.setAuditSecId(oaOvertime.getAuditSecId()==null?"":UserIdToName(oaOvertime.getAuditSecId()));
				oaOvertime1.setAuditThrId(oaOvertime.getAuditThrId()==null?"":UserIdToName(oaOvertime.getAuditThrId()));
				oaOvertime1.setAuditFouId(oaOvertime.getAuditFouId()==null?"":UserIdToName(oaOvertime.getAuditFouId()));
				oaOvertime1.setAuditFirState(oaOvertime.getAuditFirState());
				oaOvertime1.setAuditSecState(oaOvertime.getAuditSecState());
				oaOvertime1.setAuditThrState(oaOvertime.getAuditThrState());
				oaOvertime1.setAuditFouState(oaOvertime.getAuditFouState());
				oaOvertime1.setEndAuditState(oaOvertime.getEndAuditState());
				oaOvertime1.setStartTime(oaOvertime.getStartTime());
				oaOvertime1.setAuditFirInfo(oaOvertime.getAuditFirInfo());
				oaOvertime1.setAuditSecInfo(oaOvertime.getAuditSecInfo());
				oaOvertime1.setAuditThrInfo(oaOvertime.getAuditThrInfo());
				oaOvertime1.setAuditFouInfo(oaOvertime.getAuditFouInfo());
				oaOvertime1.setEndTime(oaOvertime.getEndTime());
				oaOvertime1.setCompany(oaOvertime.getCompany());
				oaOvertime1.setCopytoId((oaOvertime.getCopytoId()==null||oaOvertime.getCopytoId().equals(""))?"":UserIdToName(oaOvertime.getCopytoId()));
				oaOvertime1.setHours(oaOvertime.getHours());
				oaOvertime1.setOffice(oaOvertime.getOffice());
				oaOvertime1.setReason(oaOvertime.getReason());
				oaOvertime1.setIsHoliday(oaOvertime.getIsHoliday());
				map.put("overTime", oaOvertime1);
			}else if("officeRoom".equals(list.get(i).getVariableName())){
				OaApplyOfficeroom oaApplyOfficeroom=(OaApplyOfficeroom)list.get(i).getValue();
				OaApplyOfficeroom oaApplyOfficeroom1=new OaApplyOfficeroom();
				oaApplyOfficeroom1.setApplyName(oaApplyOfficeroom.getApplyName());
				oaApplyOfficeroom1.setApplyTime(oaApplyOfficeroom.getApplyTime());
				oaApplyOfficeroom1.setAuditFirId(oaApplyOfficeroom.getAuditFirId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditFirId()));
				oaApplyOfficeroom1.setAuditSecId(oaApplyOfficeroom.getAuditSecId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditSecId()));
				oaApplyOfficeroom1.setAuditThrId(oaApplyOfficeroom.getAuditThrId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditThrId()));
				oaApplyOfficeroom1.setAuditFouId(oaApplyOfficeroom.getAuditFouId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditFouId()));
				oaApplyOfficeroom1.setAuditFstate(oaApplyOfficeroom.getAuditFstate());
				oaApplyOfficeroom1.setAuditSstate(oaApplyOfficeroom.getAuditSstate());
				oaApplyOfficeroom1.setAuditTstate(oaApplyOfficeroom.getAuditTstate());
				oaApplyOfficeroom1.setAuditFouState(oaApplyOfficeroom.getAuditFouState());
				oaApplyOfficeroom1.setEndAuditState(oaApplyOfficeroom.getEndAuditState());
				oaApplyOfficeroom1.setStartTime(oaApplyOfficeroom.getStartTime());
				oaApplyOfficeroom1.setOfficeroomId(oaApplyOfficeroom.getOfficeroomId());
				oaApplyOfficeroom1.setAuditFirInfo(oaApplyOfficeroom.getAuditFirInfo());
				oaApplyOfficeroom1.setAuditSecInfo(oaApplyOfficeroom.getAuditSecInfo());
				oaApplyOfficeroom1.setAuditThrInfo(oaApplyOfficeroom.getAuditThrInfo());
				oaApplyOfficeroom1.setAuditFouInfo(oaApplyOfficeroom.getAuditFouInfo());
				oaApplyOfficeroom1.setEndTime(oaApplyOfficeroom.getEndTime());
				oaApplyOfficeroom1.setCompany(oaApplyOfficeroom.getCompany());
				oaApplyOfficeroom1.setCopytoId((oaApplyOfficeroom.getCopytoId()==null||oaApplyOfficeroom.getCopytoId().equals(""))?"":UserIdToName(oaApplyOfficeroom.getCopytoId()));			
				oaApplyOfficeroom1.setHours(oaApplyOfficeroom.getHours());
				oaApplyOfficeroom1.setOffice(oaApplyOfficeroom.getOffice());
				oaApplyOfficeroom1.setReason(oaApplyOfficeroom.getReason());
                map.put("officeRoom", oaApplyOfficeroom1);
			}else if("projector".equals(list.get(i).getVariableName())){
				OaApplyProjector oaApplyProjector=(OaApplyProjector)list.get(i).getValue();
				OaApplyProjector oaApplyProjector1=new OaApplyProjector();
				oaApplyProjector1.setOfficeNames(oaApplyProjector.getOfficeNames());
				oaApplyProjector1.setOfficeroomId(oaApplyProjector.getOfficeroomId());
				oaApplyProjector1.setApplyTime(oaApplyProjector.getApplyTime());
				oaApplyProjector1.setAuditFirId(oaApplyProjector.getAuditFirId()==null?"":UserIdToName(oaApplyProjector.getAuditFirId()));
				oaApplyProjector1.setAuditSecId(oaApplyProjector.getAuditSecId()==null?"":UserIdToName(oaApplyProjector.getAuditSecId()));
				oaApplyProjector1.setAuditThrId(oaApplyProjector.getAuditThrId()==null?"":UserIdToName(oaApplyProjector.getAuditThrId()));
				oaApplyProjector1.setAuditFouId(oaApplyProjector.getAuditFouId()==null?"":UserIdToName(oaApplyProjector.getAuditFouId()));
				oaApplyProjector1.setAuditFstate(oaApplyProjector.getAuditFstate());
				oaApplyProjector1.setAuditSstate(oaApplyProjector.getAuditSstate());
				oaApplyProjector1.setAuditTstate(oaApplyProjector.getAuditTstate());
				oaApplyProjector1.setAuditFouState(oaApplyProjector.getAuditFouState());
				oaApplyProjector1.setEndAuditState(oaApplyProjector.getEndAuditState());
				oaApplyProjector1.setStartTime(oaApplyProjector.getStartTime());
				oaApplyProjector1.setAuditFirInfo(oaApplyProjector.getAuditFirInfo());
				oaApplyProjector1.setAuditSecInfo(oaApplyProjector.getAuditSecInfo());
				oaApplyProjector1.setAuditThrInfo(oaApplyProjector.getAuditThrInfo());
				oaApplyProjector1.setAuditFouInfo(oaApplyProjector.getAuditFouInfo());
				oaApplyProjector1.setEndTime(oaApplyProjector.getEndTime());
				oaApplyProjector1.setCompany(oaApplyProjector.getCompany());
				oaApplyProjector1.setCopytoId((oaApplyProjector.getCopytoId()==null||oaApplyProjector.getCopytoId().equals(""))?"":UserIdToName(oaApplyProjector.getCopytoId()));
				oaApplyProjector1.setHours(oaApplyProjector.getHours());
				oaApplyProjector1.setOffice(oaApplyProjector.getOffice());
				oaApplyProjector1.setReason(oaApplyProjector.getReason());
                map.put("projector", oaApplyProjector1);
			}else if("currency".equals(list.get(i).getVariableName())){
				
				OaApply oaApply=(OaApply)list.get(i).getValue();
				OaApply oaApply1=new OaApply();
				oaApply1.setOfficeNames(oaApply.getOfficeNames());
				oaApply1.setApplyTime(oaApply.getApplyTime());
				oaApply1.setAuditFirId(oaApply.getAuditFirId()==null?"":UserIdToName(oaApply.getAuditFirId()));
				oaApply1.setAuditSecId(oaApply.getAuditSecId()==null?"":UserIdToName(oaApply.getAuditSecId()));
				oaApply1.setAuditThrId(oaApply.getAuditThrId()==null?"":UserIdToName(oaApply.getAuditThrId()));
				oaApply1.setAuditFouId(oaApply.getAuditFouId()==null?"":UserIdToName(oaApply.getAuditFouId()));
				oaApply1.setAuditFirState(oaApply.getAuditFirState());
				oaApply1.setAuditSecState(oaApply.getAuditSecState());
				oaApply1.setAuditThrState(oaApply.getAuditThrState());
				oaApply1.setAuditFouState(oaApply.getAuditFouState());
				oaApply1.setEndAuditState(oaApply.getEndAuditState());
				oaApply1.setStartTime(oaApply.getStartTime());
				oaApply1.setApplyItem(oaApply.getApplyItem());
				oaApply1.setAuditFirInfo(oaApply.getAuditFirInfo());
				oaApply1.setAuditSecInfo(oaApply.getAuditSecInfo());
				oaApply1.setAuditThrInfo(oaApply.getAuditThrInfo());
				oaApply1.setAuditFouInfo(oaApply.getAuditFouInfo());
				oaApply1.setEndTime(oaApply.getEndTime());
				oaApply1.setCompany(oaApply.getCompany());
				oaApply1.setCopytoId((oaApply.getCopytoId()==null||oaApply.getCopytoId().equals(""))?"":UserIdToName(oaApply.getCopytoId()));
				oaApply1.setOffice(oaApply.getOffice());
				oaApply1.setReason(oaApply.getReason());
				map.put("currency",oaApply1);
			}
		}
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"请求成功",map);
	}
	/**
	 * 已办任务对于抄送我的详情
	 */
	@RequestMapping("alreadyDetailForCopyToId")
	@ResponseBody
	public JsonResult<Map<String,Object>> alreadyDetailForCopyToId(String processInstanceId){
		Map<String,Object> map=new HashMap<String,Object>();        
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
		map.put("detailFlag", "already");//区分待办还是未办
		for (int i = 0; i < list.size(); i++) {
			if("flag".equals(list.get(i).getVariableName())){
				map.put("flag", list.get(i).getValue());
			}
			if("applyName".equals(list.get(i).getVariableName())){
				map.put("applyName", list.get(i).getValue());
			}
			if("applyUserId".equals(list.get(i).getVariableName())){
				map.put("applyUserId", list.get(i).getValue());
			}
			if("title".equals(list.get(i).getVariableName())){
				map.put("title", list.get(i).getValue());
			}
			if("nowposition".equals(list.get(i).getVariableName())){
				map.put("comment", list.get(i).getValue());
			}
			if("leave".equals(list.get(i).getVariableName())){
			    OaLeaved leave=	(OaLeaved)list.get(i).getValue();
			    OaLeaved leave1=new OaLeaved();
			    leave1.setApplyName(leave.getApplyName());
			    leave1.setApplyTime(leave.getApplyTime());
			    leave1.setAuditFirId(leave.getAuditFirId()==null?"":UserIdToName(leave.getAuditFirId()));
			    leave1.setAuditSecId(leave.getAuditSecId()==null?"":UserIdToName(leave.getAuditSecId()));
			    leave1.setAuditThrId(leave.getAuditThrId()==null?"":UserIdToName(leave.getAuditThrId()));
			    leave1.setAuditFouId(leave.getAuditFouId()==null?"":UserIdToName(leave.getAuditFouId()));
			    leave1.setAuditFirState(leave.getAuditFirState());
			    leave1.setAuditSecState(leave.getAuditSecState());
			    leave1.setAuditThrState(leave.getAuditThrState());
			    leave1.setAuditFouState(leave.getAuditFouState());
			    leave1.setEndAuditState(leave.getEndAuditState());
			    leave1.setStartTime(leave.getStartTime());
			    leave1.setAuditFirInfo(leave.getAuditFirInfo());
			    leave1.setAuditSecInfo(leave.getAuditSecInfo());
			    leave1.setAuditThrInfo(leave.getAuditThrInfo());
			    leave1.setAuditFouInfo(leave.getAuditFouInfo());
			    leave1.setEndTime(leave.getEndTime());
			    leave1.setCompany(leave.getCompany());
			    leave1.setCopytoId(leave.getCopytoId()==null?"":UserIdToName(leave.getCopytoId()));
			    leave1.setLeaveType(leave.getLeaveType());
			    leave1.setHours(leave.getHours());
			    leave1.setOffice(leave.getOffice());
			    leave1.setReason(leave.getReason());
				map.put("leave", leave1);
			}else if("overTime".equals(list.get(i).getVariableName())){
				OaOvertime oaOvertime=(OaOvertime)list.get(i).getValue();
				OaOvertime oaOvertime1=new OaOvertime();
                oaOvertime1.setAccountType(oaOvertime.getAccountType());
				oaOvertime1.setApplyName(oaOvertime.getApplyName());
				oaOvertime1.setApplyTime(oaOvertime.getApplyTime());
				oaOvertime1.setAuditFirId(oaOvertime.getAuditFirId()==null?"":UserIdToName(oaOvertime.getAuditFirId()));
				oaOvertime1.setAuditSecId(oaOvertime.getAuditSecId()==null?"":UserIdToName(oaOvertime.getAuditSecId()));
				oaOvertime1.setAuditThrId(oaOvertime.getAuditThrId()==null?"":UserIdToName(oaOvertime.getAuditThrId()));
				oaOvertime1.setAuditFouId(oaOvertime.getAuditFouId()==null?"":UserIdToName(oaOvertime.getAuditFouId()));
				oaOvertime1.setAuditFirState(oaOvertime.getAuditFirState());
				oaOvertime1.setAuditSecState(oaOvertime.getAuditSecState());
				oaOvertime1.setAuditThrState(oaOvertime.getAuditThrState());
				oaOvertime1.setAuditFouState(oaOvertime.getAuditFouState());
				oaOvertime1.setEndAuditState(oaOvertime.getEndAuditState());
				oaOvertime1.setStartTime(oaOvertime.getStartTime());
				oaOvertime1.setAuditFirInfo(oaOvertime.getAuditFirInfo());
				oaOvertime1.setAuditSecInfo(oaOvertime.getAuditSecInfo());
				oaOvertime1.setAuditThrInfo(oaOvertime.getAuditThrInfo());
				oaOvertime1.setAuditFouInfo(oaOvertime.getAuditFouInfo());
				oaOvertime1.setEndTime(oaOvertime.getEndTime());
				oaOvertime1.setCompany(oaOvertime.getCompany());
				oaOvertime1.setCopytoId((oaOvertime.getCopytoId()==null||oaOvertime.getCopytoId().equals(""))?"":UserIdToName(oaOvertime.getCopytoId()));
				oaOvertime1.setHours(oaOvertime.getHours());
				oaOvertime1.setOffice(oaOvertime.getOffice());
				oaOvertime1.setReason(oaOvertime.getReason());
				oaOvertime1.setIsHoliday(oaOvertime.getIsHoliday());
				map.put("overTime", oaOvertime1);
			}else if("officeRoom".equals(list.get(i).getVariableName())){
				OaApplyOfficeroom oaApplyOfficeroom=(OaApplyOfficeroom)list.get(i).getValue();
				OaApplyOfficeroom oaApplyOfficeroom1=new OaApplyOfficeroom();
				oaApplyOfficeroom1.setApplyName(oaApplyOfficeroom.getApplyName());
				oaApplyOfficeroom1.setApplyTime(oaApplyOfficeroom.getApplyTime());
				oaApplyOfficeroom1.setAuditFirId(oaApplyOfficeroom.getAuditFirId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditFirId()));
				oaApplyOfficeroom1.setAuditSecId(oaApplyOfficeroom.getAuditSecId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditSecId()));
				oaApplyOfficeroom1.setAuditThrId(oaApplyOfficeroom.getAuditThrId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditThrId()));
				oaApplyOfficeroom1.setAuditFouId(oaApplyOfficeroom.getAuditFouId()==null?"":UserIdToName(oaApplyOfficeroom.getAuditFouId()));
				oaApplyOfficeroom1.setAuditFstate(oaApplyOfficeroom.getAuditFstate());
				oaApplyOfficeroom1.setAuditSstate(oaApplyOfficeroom.getAuditSstate());
				oaApplyOfficeroom1.setAuditTstate(oaApplyOfficeroom.getAuditTstate());
				oaApplyOfficeroom1.setAuditFouState(oaApplyOfficeroom.getAuditFouState());
				oaApplyOfficeroom1.setEndAuditState(oaApplyOfficeroom.getEndAuditState());
				oaApplyOfficeroom1.setStartTime(oaApplyOfficeroom.getStartTime());
				oaApplyOfficeroom1.setOfficeroomId(oaApplyOfficeroom.getOfficeroomId());
				oaApplyOfficeroom1.setAuditFirInfo(oaApplyOfficeroom.getAuditFirInfo());
				oaApplyOfficeroom1.setAuditSecInfo(oaApplyOfficeroom.getAuditSecInfo());
				oaApplyOfficeroom1.setAuditThrInfo(oaApplyOfficeroom.getAuditThrInfo());
				oaApplyOfficeroom1.setAuditFouInfo(oaApplyOfficeroom.getAuditFouInfo());
				oaApplyOfficeroom1.setEndTime(oaApplyOfficeroom.getEndTime());
				oaApplyOfficeroom1.setCompany(oaApplyOfficeroom.getCompany());
				oaApplyOfficeroom1.setCopytoId((oaApplyOfficeroom.getCopytoId()==null||oaApplyOfficeroom.getCopytoId().equals(""))?"":UserIdToName(oaApplyOfficeroom.getCopytoId()));			
				oaApplyOfficeroom1.setHours(oaApplyOfficeroom.getHours());
				oaApplyOfficeroom1.setOffice(oaApplyOfficeroom.getOffice());
				oaApplyOfficeroom1.setReason(oaApplyOfficeroom.getReason());
                map.put("officeRoom", oaApplyOfficeroom1);
			}else if("projector".equals(list.get(i).getVariableName())){
				OaApplyProjector oaApplyProjector=(OaApplyProjector)list.get(i).getValue();
				OaApplyProjector oaApplyProjector1=new OaApplyProjector();
				oaApplyProjector1.setOfficeNames(oaApplyProjector.getOfficeNames());
				oaApplyProjector1.setOfficeroomId(oaApplyProjector.getOfficeroomId());
				oaApplyProjector1.setApplyTime(oaApplyProjector.getApplyTime());
				oaApplyProjector1.setAuditFirId(oaApplyProjector.getAuditFirId()==null?"":UserIdToName(oaApplyProjector.getAuditFirId()));
				oaApplyProjector1.setAuditSecId(oaApplyProjector.getAuditSecId()==null?"":UserIdToName(oaApplyProjector.getAuditSecId()));
				oaApplyProjector1.setAuditThrId(oaApplyProjector.getAuditThrId()==null?"":UserIdToName(oaApplyProjector.getAuditThrId()));
				oaApplyProjector1.setAuditFouId(oaApplyProjector.getAuditFouId()==null?"":UserIdToName(oaApplyProjector.getAuditFouId()));
				oaApplyProjector1.setAuditFstate(oaApplyProjector.getAuditFstate());
				oaApplyProjector1.setAuditSstate(oaApplyProjector.getAuditSstate());
				oaApplyProjector1.setAuditTstate(oaApplyProjector.getAuditTstate());
				oaApplyProjector1.setAuditFouState(oaApplyProjector.getAuditFouState());
				oaApplyProjector1.setEndAuditState(oaApplyProjector.getEndAuditState());
				oaApplyProjector1.setStartTime(oaApplyProjector.getStartTime());
				oaApplyProjector1.setAuditFirInfo(oaApplyProjector.getAuditFirInfo());
				oaApplyProjector1.setAuditSecInfo(oaApplyProjector.getAuditSecInfo());
				oaApplyProjector1.setAuditThrInfo(oaApplyProjector.getAuditThrInfo());
				oaApplyProjector1.setAuditFouInfo(oaApplyProjector.getAuditFouInfo());
				oaApplyProjector1.setEndTime(oaApplyProjector.getEndTime());
				oaApplyProjector1.setCompany(oaApplyProjector.getCompany());
				oaApplyProjector1.setCopytoId((oaApplyProjector.getCopytoId()==null||oaApplyProjector.getCopytoId().equals(""))?"":UserIdToName(oaApplyProjector.getCopytoId()));
				oaApplyProjector1.setHours(oaApplyProjector.getHours());
				oaApplyProjector1.setOffice(oaApplyProjector.getOffice());
				oaApplyProjector1.setReason(oaApplyProjector.getReason());
                map.put("projector", oaApplyProjector1);
			}else if("currency".equals(list.get(i).getVariableName())){
				
				OaApply oaApply=(OaApply)list.get(i).getValue();
				OaApply oaApply1=new OaApply();
				oaApply1.setOfficeNames(oaApply.getOfficeNames());
				oaApply1.setApplyTime(oaApply.getApplyTime());
				oaApply1.setAuditFirId(oaApply.getAuditFirId()==null?"":UserIdToName(oaApply.getAuditFirId()));
				oaApply1.setAuditSecId(oaApply.getAuditSecId()==null?"":UserIdToName(oaApply.getAuditSecId()));
				oaApply1.setAuditThrId(oaApply.getAuditThrId()==null?"":UserIdToName(oaApply.getAuditThrId()));
				oaApply1.setAuditFouId(oaApply.getAuditFouId()==null?"":UserIdToName(oaApply.getAuditFouId()));
				oaApply1.setAuditFirState(oaApply.getAuditFirState());
				oaApply1.setAuditSecState(oaApply.getAuditSecState());
				oaApply1.setAuditThrState(oaApply.getAuditThrState());
				oaApply1.setAuditFouState(oaApply.getAuditFouState());
				oaApply1.setEndAuditState(oaApply.getEndAuditState());
				oaApply1.setStartTime(oaApply.getStartTime());
				oaApply1.setAuditFirInfo(oaApply.getAuditFirInfo());
				oaApply1.setAuditSecInfo(oaApply.getAuditSecInfo());
				oaApply1.setAuditThrInfo(oaApply.getAuditThrInfo());
				oaApply1.setAuditFouInfo(oaApply.getAuditFouInfo());
				oaApply1.setEndTime(oaApply.getEndTime());
				oaApply1.setApplyItem(oaApply.getApplyItem());
				oaApply1.setCompany(oaApply.getCompany());
				oaApply1.setCopytoId((oaApply.getCopytoId()==null||oaApply.getCopytoId().equals(""))?"":UserIdToName(oaApply.getCopytoId()));
				oaApply1.setOffice(oaApply.getOffice());
				oaApply1.setReason(oaApply.getReason());
				map.put("currency",oaApply1);
			}
		}
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"请求成功",map);
	}
	
	
	/**
	 * 查询我发起的流程
	 * @throws ParseException 
	 * 
	 */
    @RequestMapping("myProcess")
	@ResponseBody
	public JsonResult<Map<String,Object>> myProcess(ModelMap map,String type,Act act,HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
		
		ArrayList<Act> arrayList1 = new ArrayList<Act>();
	    System.out.println(System.currentTimeMillis());
	    Map<String,Object> map1=new HashMap<String,Object>();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		
		Page<Act> page = new Page<Act>(request, response);
		page = actTaskService.historicList(page, act);
		List<Act> allList = page.getList();
		
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().startedBy(UserUtils.getUser().getId()).list();
	
			for (HistoricProcessInstance his : list) {
				Act a=new Act();
				for(Act act4:allList){
					if((act4.getHistTask().getProcessInstanceId()).equals(his.getId()))
					{
						a.setTaskId(act4.getHistTask().getId());
					}
				}
			   
			    a.setTaskTime(his.getStartTime());  
			  List<HistoricVariableInstance> varlist = historyService.createHistoricVariableInstanceQuery().processInstanceId(his.getId()).list();
			  
		      HistoricVariableInstanceQuery	l=  historyService.createHistoricVariableInstanceQuery().processInstanceId(his.getId());
			  
				for (HistoricVariableInstance hi : varlist) {
					if("title".equals(hi.getVariableName())){
						a.setTitle(hi.getValue().toString());
					}
					 if("nowposition".equals(hi.getVariableName())){
						a.setComment(hi.getValue().toString());	
					}
					  if("type".equals(hi.getVariableName())){
					    a.setType((String)hi.getValue());	
					}
					  if("applyUserId".equals(hi.getVariableName())){
						a.setApplyUserId((String)hi.getValue());
					} if("applyName".equals(hi.getVariableName())){
						a.setApplyName((String)hi.getValue());
					}
					  if(hi.getVariableName().equals("leave")){
					   	OaLeaved oaLeaved=(OaLeaved)hi.getValue();
					    a.setLeaveType(oaLeaved.getLeaveType());
					   	a.setStartTime(oaLeaved.getStartTime());
					   	a.setEndTime(oaLeaved.getEndTime());
					}else if(hi.getVariableName().equals("overTime")){
						OaOvertime oaOvertime=(OaOvertime)hi.getValue();
						a.setStartTime(oaOvertime.getStartTime());
						a.setEndTime(oaOvertime.getEndTime());
					}else if(hi.getVariableName().equals("officeRoom")){
						OaApplyOfficeroom oaApplyOfficeRoom=(OaApplyOfficeroom)hi.getValue();
						a.setStartTime(oaApplyOfficeRoom.getStartTime());
						a.setEndTime(oaApplyOfficeRoom.getEndTime());
						
					}else if(hi.getVariableName().equals("projector")){
						OaApplyProjector oaApplyProjector=(OaApplyProjector)hi.getValue();
						a.setStartTime(oaApplyProjector.getStartTime());
						a.setEndTime(oaApplyProjector.getEndTime());
					}else if(hi.getVariableName().equals("currency")){
						OaApply oaApply=(OaApply)hi.getValue();
						a.setStartTime(oaApply.getStartTime());
						a.setEndTime(oaApply.getEndTime());
					}
				}
				arrayList1.add(a);
				}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d= sdf.format(0L);
			Date date=sdf.parse(d);
		for(Act ac:arrayList1){ 
			Map<String,Object> map2=new HashMap<String,Object>();
			map2.put("title", ac.getTitle());
			map2.put("comment", ac.getComment());
			map2.put("taskId", ac.getTaskId());
			map2.put("type", ac.getType());
			map2.put("startTime", ac.getStartTime()==null?date:ac.getStartTime());
			map2.put("endTime", ac.getEndTime()==null?date:ac.getEndTime());
			map2.put("leaveType", ac.getLeaveType());
			map2.put("applyUserId", ac.getApplyUserId());
			map2.put("taskTime", ac.getTaskTime());
			map2.put("applyName", ac.getApplyName());
			listmap.add(map2);
			}
	
		map1.put("arrayList", listmap);
		System.out.println(System.currentTimeMillis());
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
	}
    
    
    //我发出的申请加审批完成加公告列表加抄送我的
    @RequestMapping("myProcessEnd")
 	@ResponseBody
 	public JsonResult<Map<String,Object>> myProcessEnd(ModelMap map,Act act,HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
    	
    	//待我审批的
    	Act act3 = new Act();
    	List<Act> list1 = actTaskService.todoList(act3);
		List<Act> arrayList = new ArrayList<Act>();
		System.out.println(System.currentTimeMillis());
			for (Act act2 : list1) {
				Task task = act2.getTask();
				String title = (String)taskService.getVariable(task.getId(), "title");
				act2.setTitle(title);
				String type=(String)taskService.getVariable(task.getId(), "type");
				act2.setType(type);
				String comment=(String)taskService.getVariable(task.getId(), "nowposition");
				String applyName=(String)taskService.getVariable(task.getId(), "applyName");
				act2.setApplyName(applyName);
				act2.setComment(comment);
				if(type.equals("leave")){
					OaLeaved oaLeaved=(OaLeaved)taskService.getVariable(task.getId(), type);
				   	act2.setStartTime(oaLeaved.getStartTime());
				   	act2.setEndTime(oaLeaved.getEndTime());
				   	act2.setLeaveType(oaLeaved.getLeaveType());
				}else if(type.equals("overTime")){
					OaOvertime oaOvertime=(OaOvertime)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaOvertime.getStartTime());
					act2.setEndTime(oaOvertime.getEndTime());
				}else if(type.equals("officeRoom")){
					OaApplyOfficeroom oaApplyOfficeRoom=(OaApplyOfficeroom)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaApplyOfficeRoom.getStartTime());
					act2.setEndTime(oaApplyOfficeRoom.getEndTime());
				}else if(type.equals("projector")){
					OaApplyProjector oaApplyProjector=(OaApplyProjector)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaApplyProjector.getStartTime());
					act2.setEndTime(oaApplyProjector.getEndTime());
				}else if(type.equals("currency")){
					OaApply oaApply=(OaApply)taskService.getVariable(task.getId(), type);
					act2.setStartTime(oaApply.getStartTime());
					act2.setEndTime(oaApply.getEndTime());	
				}

			}
			arrayList = list1;
		for(Act act5:arrayList){
			act5.setVars(new Variable());
		}
		List<Map<String,Object>> listmap1=new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d= sdf.format(0L);
		Date date=sdf.parse(d);
		for(Act ac:arrayList){ 
			Map<String,Object> map2=new HashMap<String,Object>();
			map2.put("title", ac.getTitle());
			map2.put("comment", ac.getComment());
			map2.put("taskId", ac.getTaskId());
			map2.put("type", ac.getType());
			map2.put("startTime", ac.getStartTime()==null?date:ac.getStartTime());
			map2.put("endTime", ac.getEndTime()==null?date:ac.getEndTime());
			map2.put("leaveType", ac.getLeaveType());
			map2.put("applyUserId", ac.getApplyUserId());
			map2.put("taskCreateDate", ac.getTaskCreateDate());
			map2.put("applyName", ac.getApplyName());
			map2.put("taskName", ac.getTaskName());
			listmap1.add(map2);
			}
		
		
		
    	//我申请的被办理完了
		ArrayList<Act> arrayList1 = new ArrayList<Act>();
	    System.out.println(System.currentTimeMillis());
	    Map<String,Object> map1=new HashMap<String,Object>();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		
		Page<Act> page11 = new Page<Act>(request, response);
		page11 = actTaskService.historicList(page11, act);
		List<Act> allList = page11.getList();
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().startedBy(UserUtils.getUser().getId()).list();
		for (HistoricProcessInstance his : list) {
			Act a=new Act();
			for(Act act4:allList){
				if((act4.getHistTask().getProcessInstanceId()).equals(his.getId()))
				{
					a.setTaskId(act4.getHistTask().getId());
				}
			}
		    a.setTaskTime(his.getStartTime());  
		  List<HistoricVariableInstance> varlist = historyService.createHistoricVariableInstanceQuery().processInstanceId(his.getId()).list();
		  
	      HistoricVariableInstanceQuery	l=  historyService.createHistoricVariableInstanceQuery().processInstanceId(his.getId());
		  
			for (HistoricVariableInstance hi : varlist) {
				if("title".equals(hi.getVariableName())){
					a.setTitle(hi.getValue().toString());
				}
				 if("nowposition".equals(hi.getVariableName())){
					a.setComment(hi.getValue().toString());	
				}
				  if("type".equals(hi.getVariableName())){
				    a.setType((String)hi.getValue());	
				}
				  if("applyUserId".equals(hi.getVariableName())){
					a.setApplyUserId((String)hi.getValue());
				} if("applyName".equals(hi.getVariableName())){
					a.setApplyName((String)hi.getValue());
				}
				  if(hi.getVariableName().equals("leave")){
				   	OaLeaved oaLeaved=(OaLeaved)hi.getValue();
				    a.setLeaveType(oaLeaved.getLeaveType());
				   	a.setStartTime(oaLeaved.getStartTime());
				   	a.setEndTime(oaLeaved.getEndTime());
				}else if(hi.getVariableName().equals("overTime")){
					OaOvertime oaOvertime=(OaOvertime)hi.getValue();
					a.setStartTime(oaOvertime.getStartTime());
					a.setEndTime(oaOvertime.getEndTime());
				}else if(hi.getVariableName().equals("officeRoom")){
					OaApplyOfficeroom oaApplyOfficeRoom=(OaApplyOfficeroom)hi.getValue();
					a.setStartTime(oaApplyOfficeRoom.getStartTime());
					a.setEndTime(oaApplyOfficeRoom.getEndTime());
					
				}else if(hi.getVariableName().equals("projector")){
					OaApplyProjector oaApplyProjector=(OaApplyProjector)hi.getValue();
					a.setStartTime(oaApplyProjector.getStartTime());
					a.setEndTime(oaApplyProjector.getEndTime());
				}else if(hi.getVariableName().equals("currency")){
					OaApply oaApply=(OaApply)hi.getValue();
					a.setStartTime(oaApply.getStartTime());
					a.setEndTime(oaApply.getEndTime());
				}
			}
			arrayList1.add(a);
			}
		for(Act ac:arrayList1){ 
			if(ac.getComment()!=null&&(ac.getComment().equals("successEnd")||ac.getComment().equals("rejectEnd"))){
			Map<String,Object> map2=new HashMap<String,Object>();
			map2.put("title", ac.getTitle());
			map2.put("comment", ac.getComment());
			map2.put("taskId", ac.getTaskId());
			map2.put("type", ac.getType());
			map2.put("startTime", ac.getStartTime()==null?date:ac.getStartTime());
			map2.put("endTime", ac.getEndTime()==null?date:ac.getEndTime());
			map2.put("leaveType", ac.getLeaveType());
			map2.put("applyUserId", ac.getApplyUserId());
			map2.put("taskTime", ac.getTaskTime());
			map2.put("applyName", ac.getApplyName());
			listmap.add(map2);
			}
			}
 		map1.put("arrayList", listmap);
 		map1.put("arrayListTODo", listmap1);
 		//我接收到的公告
 	    OaNotice oaNotice=new OaNotice();
 	    oaNotice.setSelf(true);
		List<OaNotice> oaNoticeList1=oaNoticeDao.findList(oaNotice);
		List<Map<String,Object>> oaNoticeList=new ArrayList<Map<String,Object>>();
	    for(OaNotice oa:oaNoticeList1){
	    	Map<String,Object> map6=new HashMap<String,Object>();
	    	map6.put("id",oa.getId());
	    	map6.put("content", oa.getContent());
	    	map6.put("createDate",oa.getCreateDate().getTime() );
	    	map6.put("updateDate", oa.getUpdateDate().getTime());
	    	map6.put("title", oa.getTitle());
	    	map6.put("type", oa.getType());
	    	map6.put("receiveUser", oa.getReceiveUser());
	    	map6.put("filePath",oa.getFilePath());
	        map6.put("status", oa.getStatus());
	        map6.put("oaNoticeRecordIds", oa.getOaNoticeRecordIds());
	        map6.put("oaNoticeRecordNames", oa.getOaNoticeRecordNames());
	        map6.put("readNum", oa.getReadNum());
	        map6.put("unReadNum", oa.getUnReadNum());
	        oaNoticeList.add(map6);
	    }
		map1.put("oaNoticeList", oaNoticeList);
 		System.out.println(System.currentTimeMillis());
 		OaNotify oaNotify=new OaNotify();
 		oaNotify.setSelf(true);
		oaNotify.setType("spcs");
		Page<OaNotify> page = oaNotifyService.find2(new Page<OaNotify>(request, response), oaNotify); 
		List<Map<String,Object>> oaNotifyList=new ArrayList<Map<String,Object>>();
		for(OaNotify oa:page.getList()){
		 oa.getCreateBy().getId();
		 Map<String,Object> map4=new HashMap<String,Object>();
		 map4.put("ApplyName", UserUtils.get(oa.getCreateBy().getId()).getName());
		 map4.put("id", oa.getId());
		 map4.put("isNewRecord", oa.getIsNewRecord());
		 map4.put("createDate", oa.getCreateDate());
		 map4.put("updateDate", oa.getUpdateDate());
		 map4.put("type", oa.getType());
		 map4.put("title", oa.getTitle());
		 map4.put("content", oa.getContent());
		 map4.put("status", oa.getStatus());
		 map4.put("readNum", oa.getReadNum());
		 map4.put("unReadNum", oa.getUnReadNum());
		 map4.put("oaNotifyRecordIds", oa.getOaNotifyRecordIds());
		 map4.put("oaNotifyRecordNames", oa.getOaNotifyRecordNames());
		 oaNotifyList.add(map4);
		}
		map1.put("oaNotifyList",oaNotifyList);
 		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
 	}
    //公告详情
    @RequestMapping("viewNoticeDetail")
    @ResponseBody
    public JsonResult<Map<String,Object>> viewNoticeDetail(OaNotice oaNotice){
    	OaNoticeRecord oaNoticeRecord=new OaNoticeRecord();
    	oaNoticeRecord.setOaNotice(oaNotice);
    	oaNoticeRecord.setUser(UserUtils.getUser());
    	oaNoticeRecord.setReadDate(new Date());
    	oaNoticeRecord.setReadFlag("1");//1是已读
    	oaNoticeRecordDao.update(oaNoticeRecord);
        OaNotice oa=oaNoticeDao.get(oaNotice);
        Map<String,Object> map6=new HashMap<String,Object>();
        map6.put("id",oa.getId());
    	map6.put("content", oa.getContent());
    	map6.put("createDate",oa.getCreateDate().getTime());
    	map6.put("updateDate", oa.getUpdateDate().getTime());
    	map6.put("title", oa.getTitle());
    	map6.put("type", oa.getType());
    	map6.put("receiveUser", oa.getReceiveUser());
    	map6.put("filePath",oa.getFilePath());
        map6.put("status", oa.getStatus());
        map6.put("oaNoticeRecordIds", oa.getOaNoticeRecordIds());
        map6.put("oaNoticeRecordNames", oa.getOaNoticeRecordNames());
        map6.put("readNum", oa.getReadNum());
        map6.put("unReadNum", oa.getUnReadNum());
    	return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查询成功",map6);
    }
    
    
    
    
    
	/***
	 * 我发起的详情
	 *//*
	@RequestMapping("myProcessDetail")
	@ResponseBody
	public JsonResult<Map<String,Object>> myProcessDetail(String taskId,ModelMap map,String type){
		Task task = actTaskService.taskDetail(taskId);
		Map<String,Object> map1=new HashMap<String,Object>();
		String title = "";
		if(null==task || "".equals(task) || "null".equals(task)){
			HistoricTaskInstance result = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
			List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(result.getProcessInstanceId()).list();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getVariableName()+"---"+list.get(i).getValue());
				if("applyUserId".equals(list.get(i).getVariableName())){
					map1.put("applyUserId", list.get(i).getValue());
				}
				if("title".equals(list.get(i).getVariableName())){
					title = list.get(i).getValue().toString();
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if(type.equals("leave")){
					
					if(type.equals(list.get(i).getVariableName())){
						OaLeaved lea = (OaLeaved)list.get(i).getValue();
						lea.setYuliu5(title);
						map1.put("lea", lea);
							
					return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
					}
				}else if(type.equals("overTime")){
					
					if(type.equals(list.get(i).getVariableName())){
						OaOvertime over = (OaOvertime)list.get(i).getValue();
						over.setYuliu5(title);
						map1.put("over", over);
					return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
					}
				}else if(type.equals("officeRoom")){
					
					if(type.equals(list.get(i).getVariableName())){
						OaApplyOfficeroom room = (OaApplyOfficeroom)list.get(i).getValue();
						room.setYuliu5(title);
						map1.put("room",room);
	     			
					
					return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
					}
				}else if(type.equals("projector")){
					
					if(type.equals(list.get(i).getVariableName())){
						OaApplyProjector pro = (OaApplyProjector)list.get(i).getValue();
						pro.setYuliu5(title);
						map1.put("pro", pro);
					return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
					}	
				}else if(type.equals("currency")){
					
					if(type.equals(list.get(i).getVariableName())){
						OaApply app = (OaApply)list.get(i).getValue();
						app.setYuliu5(title);
						map1.put("app", app);
					return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
					}	
				}
			}
			return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
		}else{
			return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
		}
	}
	*/
	/**
	 * 抄送我的
	 */
	@RequestMapping(value = "self")
	@ResponseBody
	public JsonResult<Map<String,Object>> self(OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response) {

		oaNotify.setSelf(true);
		oaNotify.setType("spcs");
		Page<OaNotify> page = oaNotifyService.find2(new Page<OaNotify>(request, response), oaNotify); 
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(OaNotify oa:page.getList()){
			Map<String,Object> map4=new HashMap<String,Object>();
		 map4.put("ApplyName", UserUtils.get(oa.getCreateBy().getId()).getName());
		 map4.put("id", oa.getId());
		 map4.put("isNewRecord", oa.getIsNewRecord());
		 map4.put("createDate", oa.getCreateDate());
		 map4.put("updateDate", oa.getUpdateDate());
		 map4.put("type", oa.getType());
		 map4.put("title", oa.getTitle());
		 map4.put("content", oa.getContent());
		 map4.put("status", oa.getStatus());
		 map4.put("readNum", oa.getReadNum());
		 map4.put("unReadNum", oa.getUnReadNum());
		 map4.put("oaNotifyRecordIds", oa.getOaNotifyRecordIds());
		 map4.put("oaNotifyRecordNames", oa.getOaNotifyRecordNames());
		 listMap.add(map4);
		}
		map.put("list",listMap);
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看抄送我的成功",map);
	}
	//查看我已经取消的会议室申请
	@RequestMapping(value="viewMyCancleOfficeroom")
	@ResponseBody
	public JsonResult<Map<String,Object>> viewMyCancleOfficeroom(){
		User user=UserUtils.getUser();
		               user.getId();
	    OaApplyOfficeroom oaApplyOfficeroom=new OaApplyOfficeroom();             
		List<OaApplyOfficeroom> list=oaApplyOfficeroomService.findList(oaApplyOfficeroom);
		List<OaApplyOfficeroom> list1=new ArrayList<OaApplyOfficeroom>();
		for(OaApplyOfficeroom oaroom:list){
		    if(oaroom.getDelFlag().equals(oaroom.DEL_FLAG_DELETE)){
		    	list1.add(oaroom);
		    }	
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list1", list1);	
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看我已取消的会议室成功",map);
	}
	
    //取消会议室申请
	@RequestMapping(value="cancleOfficeroomApply")
	@ResponseBody
	public JsonResult<Map<String,Object>> cancleOfficeroomApply(String reason,String procInsId){
		User user=UserUtils.getUser();
	    reason=(reason==null?"":reason);
		String message="取消会议室申请成功";
		OaApplyOfficeroom oaApplyOfficeroom=new OaApplyOfficeroom();
		oaApplyOfficeroom.setProcessInstanceId(procInsId);
		OaApplyOfficeroom oaApplyOfficeroom1= oaApplyOfficeroomService.selectOaApplyOfficeroomByprocInsId(oaApplyOfficeroom);
		if(oaApplyOfficeroom1!=null&&oaApplyOfficeroom1.getCreateBy().equals(user.getId())){
		actProcessService.deleteProcIns(procInsId, reason);
		oaApplyOfficeroomService.deleteOaApplyOfficeroomByprocInsId(oaApplyOfficeroom);
		}else{
			message="您不是此会议室申请的发起者，或者无此会议室申请";
		}
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,message,null);
	}
	
	
	public static String LoginnameToName(String loginName){
		String[] list=loginName.split(",");
		String Name="";
		for(int i=0;i<list.length;i++){
		String name=UserUtils.getByLoginName(list[i]).getName();
		if(i==list.length-1){
			Name=Name+name;
		}else{
			Name=Name+name+",";
		}		
		}
		return Name;
	}
	public static String UserIdToName(String Ids){
		if(Ids.equals("")){
			return "";
		}
		String[] list=Ids.split(",");
		String Names="";
		for(int i=0;i<list.length;i++){
			User user=UserUtils.get(list[i]);
			if(i<list.length-1){
				Names=Names+user.getName()+",";
			}else{
				Names=Names+user.getName();
			}
			}
		return Names;
	}
    
	
	
	
	

}
