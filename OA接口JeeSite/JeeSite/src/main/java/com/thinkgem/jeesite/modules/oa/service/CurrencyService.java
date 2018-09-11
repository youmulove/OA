package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.act.utils.ListToString;
import com.thinkgem.jeesite.modules.oa.dao.OaApplyDao;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class CurrencyService extends BaseService {
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OaApplyDao oaApplyDao;
	@Autowired
	private OfficeService officeService;

	/**
	 * 通用申请
	 */
	public void currencyApply(OaApply currency, String oaNotifyRecordIds,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4) {
		// 设置查询条件 本公司
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("currency");
		// 获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);
		// 选择对应的流程图信息
		String processDefinitionKey = appList.get(0).getApprovalProcess();
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		if (appList.get(0).getApprovalProcess().equals("0")) {
			if (sysapp1 != null&&!sysapp1.equals("")) {
				str1 = sysapp1;
				currency.setAuditFirId(sysapp1);
				processDefinitionKey = "1";
			}
			if (sysapp2 != null&&!sysapp2.equals("")) {
				str2 = sysapp2;
				currency.setAuditSecId(sysapp2);
				processDefinitionKey = "2";
			}
			if (sysapp3 != null&&!sysapp3.equals("")) {
				str3 = sysapp3;
				currency.setAuditThrId(sysapp3);
				processDefinitionKey = "3";
			}

			if (sysapp4 != null&&!sysapp4.equals("")) {
				str4 = sysapp4;
				currency.setAuditFouId(sysapp4);
				processDefinitionKey = "4";
			}
		} else {
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
					currency.setAuditFirId(str1);// 设置第一审批人
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
					currency.setAuditSecId(str2);// 第二审批人
				} else if (app.getApprovalRole().equals("3")) {
					if (app.getApprovalRank() != null
							&& !app.getApprovalRank().equals("")) {
						// ***********************
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
					currency.setAuditThrId(str3);// 第三审批人
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
					currency.setAuditFouId(str4);
				}
			}
		}
		currency.setId(UUID.randomUUID().toString().replace("-", ""));
		currency.setCreateBy(user);
		currency.setApplyName(user.getName());
		currency.setCreateDate(new Date());
		currency.setCopytoId(oaNotifyRecordIds);
		currency.setApplyTime(new Date());
		currency.setUpdateBy(user);
		currency.setUpdateDate(new Date());
		currency.setCompany(UserUtils.getUser().getCompany());
		// currency.setOffice(UserUtils.getUser().getOffice());
		currency.setOffice(officeService.findAllDeptByUser(user));

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("applyUserId", user.getId());// 设置流程发起人
		variables.put("type", "currency");
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
		runtimeService.startProcessInstanceByKey(processDefinitionKey,
				variables);
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
		variables.put("currency", currency); // 类型为通用类型
		variables.put("oneLevelApproval", str1);
		variables.put("twoLevelApproval", str2);
		variables.put("threeLevelApproval", str3);
		variables.put("fourLevelApproval", str4);
		variables.put("copytoId", oaNotifyRecordIds);
		variables.put("title", user.getName() + "的通用申请");
		variables.put("nowposition", "run");
		// 完成申请
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee(UserUtils.getUser().getId())
				.orderByTaskCreateTime().desc().list();
		taskService.complete(list.get(0).getId(), variables);

		currency.setIsNewRecord(true);
		oaApplyDao.insert(currency);
	}

	public String returnStr(String[] split) {
		User user = UserUtils.getUser();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
			User u = systemService.getUser(split[i]);
			if (u == null) {
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
