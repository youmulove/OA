/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.OaDispatchDao;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaDispatch;
import com.thinkgem.jeesite.modules.oa.entity.OaDocApproval;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 发文单Service
 * 
 * @author lisp
 * @version 2017-11-03
 */
@Service
@Transactional(readOnly = true)
public class OaDispatchService extends CrudService<OaDispatchDao, OaDispatch> {
	@Autowired
	private OaDocApprovalService oaDocApprovalService;
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private OaDispatchDao oaDispatchDao;

	public OaDispatch get(String id) {
		return super.get(id);
	}

	public OaDispatch getByDocApprovalId(String oaDocApprovalId) {
		return oaDispatchDao.getByDocApprovalId(oaDocApprovalId);
	}

	public List<OaDispatch> findList(OaDispatch oaDispatch) {
		return super.findList(oaDispatch);
	}

	public Page<OaDispatch> findPage(Page<OaDispatch> page,
			OaDispatch oaDispatch) {
		return super.findPage(page, oaDispatch);
	}

	@Transactional(readOnly = false)
	public void save(OaDispatch oaDispatch) {
		super.save(oaDispatch);
	}

	@Transactional(readOnly = false)
	public void delete(OaDispatch oaDispatch) {
		super.delete(oaDispatch);
	}

	/**
	 * 发起公文申请
	 * 
	 * @param oaDispatch
	 */
	@Transactional
	public void docApply(OaDispatch oaDispatch) {
		User user = UserUtils.getUser();
		// 1.保存申请信息
		OaDocApproval docApproval = new OaDocApproval();
		docApproval.setOaDispatchId(oaDispatch.getId());// 存入发文单ID
		// 查询审批规则信息 存入各级审批人信息
		ApprovalRule approvalRule = new ApprovalRule();
		approvalRule.setApprovalType("doc");
		approvalRule.setApprovalCompany(user.getCompany().getId());
		List<ApprovalRule> ruleList = approvalRuleService
				.findApprovalRuleSelective(approvalRule);
		docApproval.setNgId(user.getId());
		docApproval.setNgName(user.getName());
		for (ApprovalRule app : ruleList) {
			if ("1".equals(app.getApprovalRole())) {
				docApproval.setSgId(app.getApprovalPerson());
				docApproval.setSgName(returnUsername(app.getApprovalPerson()));
			} else if ("2".equals(app.getApprovalRole())) {
				docApproval.setHgId(app.getApprovalPerson());
				docApproval.setHgName(returnUsername(app.getApprovalPerson()));
			} else if ("3".equals(app.getApprovalRole())) {
				docApproval.setSignId(app.getApprovalPerson());
				docApproval
						.setSignName(returnUsername(app.getApprovalPerson()));
			} else if ("4".equals(app.getApprovalRole())) {
				docApproval.setOfficeApprovalId(app.getApprovalPerson());
				docApproval.setOfficeApprovalName(returnUsername(app
						.getApprovalPerson()));
			} else if ("5".equals(app.getApprovalRole())) {
				docApproval.setQfId(app.getApprovalPerson());
				docApproval.setQfName(returnUsername(app.getApprovalPerson()));
			} else if ("6".equals(app.getApprovalRole())) {
				docApproval.setCheckId(app.getApprovalPerson());
				docApproval
						.setCheckName(returnUsername(app.getApprovalPerson()));
			} else if ("7".equals(app.getApprovalRole())) {
				docApproval.setUseSealId(app.getApprovalPerson());
				docApproval.setUseSealName(returnUsername(app
						.getApprovalPerson()));
			}
		}
		// docApproval.setEndFlag("0");//设置流程结束标识
		identityService.setAuthenticatedUserId(user.getId());// 存入流程启动人
		String processDefinitionKey = "document";
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("type", "doc");
		variables.put("applyUser", user.getId());
		variables.put("title", oaDispatch.getTitle());// 存入标题
		variables.put("nowposition", "run");
		List<String> asList = Arrays.asList(docApproval.getSignId().split(","));
		variables.put("assigneeList", asList);// 存入会签人
		oaDocApprovalService.save(docApproval);// 保存公文申请信息
		OaDocApproval approval = oaDocApprovalService
				.getByDispatchId(oaDispatch.getId());// 根据发文单ID查询审批信息
		variables.put("oaDocApproval", approval);// 将申请信息存入流程变量
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey, variables);
		docApproval.setProcessInstanceId(processInstance.getId());// 存入流程实例ID
		oaDocApprovalService.save(docApproval);// 保存公文申请信息
		// 完成发起任务
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee(UserUtils.getUser().getId())
				.orderByTaskCreateTime().desc().list();
		taskService.complete(list.get(0).getId(), variables);

		// 2.修改发文单信息
		// OaDocApproval approval =
		// oaDocApprovalService.getByDispatchId(oaDispatch.getId());//根据发文单ID查询审批信息
		oaDispatch.setYuliu1("1");// 修改发文单状态
		oaDispatch.setOaDocApprovalId(approval.getId());// 存入申请信息id
		save(oaDispatch);// 修改发文单信息

	}

	public static String returnUsername(String string) {
		String[] split = string.split(",");
		String str = "";
		for (int i = 0; i < split.length; i++) {
			str += UserUtils.get(split[i]).getName() + ",";
		}
		return str;
	}
}