/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.utils.ListToString;
import com.thinkgem.jeesite.modules.oa.dao.OaApplyOfficeroomDao;
import com.thinkgem.jeesite.modules.oa.dao.OfficeRoomDao;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 会议室Service
 * 
 * @author lisp
 * @version 2017-09-22
 */
@Service
@Transactional(readOnly = true)
public class OfficeRoomService extends CrudService<OfficeRoomDao, OfficeRoom> {
	@Autowired
	private OfficeRoomDao officeRoomDao;

	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private OaApplyOfficeroomDao oaApplyOfficeroomDao;
	@Autowired
	private OfficeService officeService;

	public OfficeRoom get(String id) {
		return super.get(id);
	}

	public List<OfficeRoom> findList(OfficeRoom officeRoom) {
		return super.findList(officeRoom);
	}

	public Page<OfficeRoom> findPage(Page<OfficeRoom> page,
			OfficeRoom officeRoom) {
		return super.findPage(page, officeRoom);
	}

	@Transactional(readOnly = false)
	public void save(OfficeRoom officeRoom) {
		super.save(officeRoom);
	}

	@Transactional(readOnly = false)
	public void delete(OfficeRoom officeRoom) {
		super.delete(officeRoom);
	}

	public List<OfficeRoom> findAllRoom(OfficeRoom officeRoom) {
		return officeRoomDao.findAllRoom(officeRoom);
	}

	/**
	 * 会议室申请
	 */
	public void officeRoomApply(OaApplyOfficeroom officeRoom,
			String oaNotifyRecordIds, String sysapp1, String sysapp2,
			String sysapp3, String sysapp4) {
		OfficeRoom room = officeRoomDao.get(officeRoom.getOfficeroomId());
		officeRoom.setYuliu1(room.getOfficeRoomName());
		officeRoom.setCompanyId(UserUtils.getUser().getCompany().getId());
		officeRoom.setOfficeId(UserUtils.getUser().getOffice().getId());
		officeRoom.setYuliu2(officeService.findAllDeptByUser(
				UserUtils.getUser()).getParentIds());
		officeRoom.setDepartment(UserUtils.getUser().getOffice().getName());
		// 设置查询条件 本公司
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("officeRoom");
		// 获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);
		// 选择对应的流程图信息
		String processDefinitionKey = appList.get(0).getApprovalProcess();
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		if(appList.get(0).getApprovalProcess().equals("0")){
			if(sysapp1!=null&&!sysapp1.equals("")){
				str1=sysapp1;
				officeRoom.setAuditFirId(sysapp1);
				processDefinitionKey="1";	
			}
	       if(sysapp2!=null&&!sysapp2.equals("")){
	    	   str2=sysapp2;
	    	   officeRoom.setAuditSecId(sysapp2);
	    	   processDefinitionKey="2";
				}
	       if(sysapp3!=null&&!sysapp3.equals("")){
	    	   str3=sysapp3;
	    	   officeRoom.setAuditThrId(sysapp3);
	    	   processDefinitionKey="3";
	        }
				
	       if(sysapp4!=null&&!sysapp4.equals("")){
	    	   str4=sysapp4;
	    	   officeRoom.setAuditFouId(sysapp4);
	    	   processDefinitionKey="4";
	         }	
				
			}else{
		for (ApprovalRule app : appList) {
			// 判断是几级审批
			if (app.getApprovalRole().equals("1")) {
				// 判断是根据职级确定审批人还是指定具体人
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					if (sysapp1 != null) {
						User user2 = systemService.getUser(sysapp1);
						str1 = user2.getId();
					} else {
						List<User> list = systemService
								.findUserBysystrankId(app.getApprovalRank());
						ArrayList<String> list2 = new ArrayList<String>();
						for (User u : list) {
							list2.add(u.getId());
						}
						str1 = ListToString.listToString(list2);
					}
				} else {
					// str1 = app.getApprovalPerson();
					str1 = returnStr(app.getApprovalPerson().split(","));
				}
				officeRoom.setAuditFirId(str1);// 设置第一审批人
			} else if (app.getApprovalRole().equals("2")) {
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					if (sysapp2 != null) {
						User user2 = systemService.getUser(sysapp2);
						str2 = user2.getId();
					} else {
						List<User> list = systemService
								.findUserBysystrankId(app.getApprovalRank());
						ArrayList<String> list2 = new ArrayList<String>();
						for (User u : list) {
							list2.add(u.getId());
						}
						str2 = ListToString.listToString(list2);
					}
				} else {
					// str2 = app.getApprovalPerson();
					str2 = returnStr(app.getApprovalPerson().split(","));
				}
				officeRoom.setAuditSecId(str2);// 第二审批人
			} else if (app.getApprovalRole().equals("3")) {
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************\
					if (sysapp3 != null) {
						User user2 = systemService.getUser(sysapp3);
						str3 = user2.getId();
					} else {
						List<User> list = systemService
								.findUserBysystrankId(app.getApprovalRank());
						ArrayList<String> list2 = new ArrayList<String>();
						for (User u : list) {
							list2.add(u.getId());
						}
						str3 = ListToString.listToString(list2);
					}
				} else {
					// str3 = app.getApprovalPerson();
					str3 = returnStr(app.getApprovalPerson().split(","));
				}
				officeRoom.setAuditThrId(str3);// 第三审批人
			} else if (app.getApprovalRole().equals("4")) {
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					if (sysapp4 != null) {
						User user2 = systemService.getUser(sysapp4);
						str4 = user2.getId();
					} else {
						List<User> list = systemService
								.findUserBysystrankId(app.getApprovalRank());
						ArrayList<String> list2 = new ArrayList<String>();
						for (User u : list) {
							list2.add(u.getId());
						}
						str4 = ListToString.listToString(list2);
					}
				} else {
					// str4 = app.getApprovalPerson();
					str4 = returnStr(app.getApprovalPerson().split(","));
				}
				officeRoom.setAuditFouId(str4);
			}
		}
			}

		officeRoom.setId(UUID.randomUUID().toString().replace("-", ""));
		officeRoom.setCreateBy(user);
		officeRoom.setApplyName(user.getName());
		officeRoom.setCreateDate(new Date());
		// officeRoom.setCopytoId(oaNotifyRecordIds);
		officeRoom.setApplyTime(new Date());
		officeRoom.setUpdateBy(user);
		officeRoom.setUpdateDate(new Date());
		officeRoom.setCopytoId(oaNotifyRecordIds);

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("type", "officeRoom");
		variables.put("applyUserId", user.getId());// 设置流程发起人

		// 存入流程启动人
		identityService.setAuthenticatedUserId(user.getId());
		// 启动流程
		if (processDefinitionKey.equals("1")) {
			processDefinitionKey = "oneLevel";
		}
		if (processDefinitionKey.equals("2")) {
			processDefinitionKey = "twoLevel";
		}
		if (processDefinitionKey.equals("3")) {
			processDefinitionKey = "threeLevel";
		}
		if (processDefinitionKey.equals("4")) {
			processDefinitionKey = "fourLevel";
		}
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey, variables);
		officeRoom.setProcessInstanceId(processInstance.getId());
		// 存入申请人角色信息
		if (Arrays.asList(str1.split(",")).contains(user.getId())) {
			variables.put("role", "one");
		} else if (Arrays.asList(str2.split(",")).contains(user.getId())) {
			variables.put("role", "two");
		} else if (Arrays.asList(str3.split(",")).contains(user.getId())) {
			variables.put("role", "three");
		} else if (Arrays.asList(str4.split(",")).contains(user.getId())) {
			variables.put("role", "four");
		} else {
			variables.put("role", "user");
		}
		variables.put("applyName", user.getName());
		variables.put("officeRoom", officeRoom); // 类型为会议室申请类型
		variables.put("copytoId", oaNotifyRecordIds);
		variables.put("oneLevelApproval", str1);
		variables.put("twoLevelApproval", str2);
		variables.put("threeLevelApproval", str3);
		variables.put("fourLevelApproval", str4);
		variables.put("title", user.getName() + "的会议室申请");
		variables.put("nowposition", "run");
		// 完成申请
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee(UserUtils.getUser().getId())
				.orderByTaskCreateTime().desc().list();
		taskService.complete(list.get(0).getId(), variables);
		// 插入会议室申请记录表
		officeRoom.setIsNewRecord(true);
		oaApplyOfficeroomDao.insert(officeRoom);
	}

	public String returnStr(String[] split) {
		User user = UserUtils.getUser();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
			User u = systemService.getUser(split[i]);
			if(u==null){
				continue;
			}
			if ((user.getOffice().getId()).equals(u.getOffice().getId())) {
				sb.append(split[i] + ",");
			} else {
				String parentIds = officeService.findAllDeptByUser(user)
						.getParentIds();
				if (ArrayUtils.contains(parentIds.split(","), u.getOffice()
						.getId())) {
					sb.append(split[i] + ",");
				}
			}
		}
		if ("".equals(sb.toString()) || (sb.toString()) == null) {
			for (int i = 0; i < split.length; i++) {
				sb.append(split[i] + ",");
			}
		}
		return sb.toString();
	}
}