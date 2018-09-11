package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用章申请审批实体
 * 
 * @author lisp
 * 
 */
public class Currency extends DataEntity<Leave> {
	private static final long serialVersionUID = 1L;

	private String startendtime;
	private String applyItem;
	private String reason;
	private String applyUserId;
	private String oneLevelApprovalResult;
	private String twoLevelApprovalResult;
	private String threeLevelApprovalResult;
	private String fourLevelApprovalResult;

	public String getOneLevelApprovalResult() {
		return oneLevelApprovalResult;
	}

	public void setOneLevelApprovalResult(String oneLevelApprovalResult) {
		this.oneLevelApprovalResult = oneLevelApprovalResult;
	}

	public String getTwoLevelApprovalResult() {
		return twoLevelApprovalResult;
	}

	public void setTwoLevelApprovalResult(String twoLevelApprovalResult) {
		this.twoLevelApprovalResult = twoLevelApprovalResult;
	}

	public String getThreeLevelApprovalResult() {
		return threeLevelApprovalResult;
	}

	public void setThreeLevelApprovalResult(String threeLevelApprovalResult) {
		this.threeLevelApprovalResult = threeLevelApprovalResult;
	}

	public String getFourLevelApprovalResult() {
		return fourLevelApprovalResult;
	}

	public void setFourLevelApprovalResult(String fourLevelApprovalResult) {
		this.fourLevelApprovalResult = fourLevelApprovalResult;
	}

	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getStartendtime() {
		return startendtime;
	}

	public void setStartendtime(String startendtime) {
		this.startendtime = startendtime;
	}

	public String getApplyItem() {
		return applyItem;
	}

	public void setApplyItem(String applyItem) {
		this.applyItem = applyItem;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Currency(String startendtime, String applyItem, String reason) {
		super();
		this.startendtime = startendtime;
		this.applyItem = applyItem;
		this.reason = reason;
	}

	public Currency() {
		super();
	}

}
