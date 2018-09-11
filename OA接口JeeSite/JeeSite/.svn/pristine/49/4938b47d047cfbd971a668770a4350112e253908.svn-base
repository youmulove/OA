/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;

@MyBatisDao
public interface ApprovalRuleDao extends CrudDao<ApprovalRule> {

	public List<ApprovalRule> findApprovalRuleSelective(
			ApprovalRule approvalRule);

	public void insertApprovalRule(ApprovalRule approvalRule);

	public void updateApproval(ApprovalRule approvalRule);

	public void deleteApprovalById(String approvalId);

	public void deleteByApprovalRule(ApprovalRule approvalRule);

	public List<ApprovalRule> findAllApprovalRules();

}
