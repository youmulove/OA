package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.oa.dao.ApprovalRuleDao;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class ApprovalRuleService extends BaseService {

	@Autowired
	private ApprovalRuleDao approvalRuleDao;

	public List<ApprovalRule> findApprovalRuleSelective(
			ApprovalRule approvalRule) {
		approvalRule.setApprovalCompany(UserUtils.getUser().getCompany()
				.getId());
		return approvalRuleDao.findApprovalRuleSelective(approvalRule);
	};

	public void insertApprovalRule(ApprovalRule approvalRule) {
		approvalRule.setApprovalCompany(UserUtils.getUser().getCompany()
				.getId());
		approvalRuleDao.insertApprovalRule(approvalRule);
	};

	public void updateApproval(ApprovalRule approvalRule) {
		approvalRuleDao.updateApproval(approvalRule);
	};

	public void deleteApprovalById(String approvalId) {
		approvalRuleDao.deleteApprovalById(approvalId);
	};

	public void deleteByApprovalRule(ApprovalRule approvalRule) {
		approvalRuleDao.deleteByApprovalRule(approvalRule);
	}

	public List<ApprovalRule> findAllApprovalRules() {
		return approvalRuleDao.findAllApprovalRules();
	}

}
