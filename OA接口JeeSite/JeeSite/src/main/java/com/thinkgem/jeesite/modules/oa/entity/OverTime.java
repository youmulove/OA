package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 加班实体
 */
public class OverTime extends DataEntity<OverTime> {
	
	private static final long serialVersionUID = 1L;
	
	private String processInstanceId; 	// 流程实例编号
	private String startendTime;		// 加班起止时间
	private String hours;				// 加班时长
	private String reason;				// 加班理由（内容）
	private Date realityStartTime;		// 实际开始时间 
	private Date realityEndTime;		// 实际结束时间
	private String realStartEndTime;	// 实际起止时间
	private String isHoliday;			//是否为节假日
	private String oneLevelApprovalResult;
	private String twoLevelApprovalResult;
	private String threeLevelApprovalResult;
	private String fourLevelApprovalResult;
	
	
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Date applyTime;		// 申请时间
	private String accountType;		// 加班核算方式
	private String auditFirInfo;		// 第一审批人意见
	private String auditSecInfo;		// 第二审批人意见
	private String auditThrInfo;		// 第三审批人意见
	private String auditForInfo;		// 第四审批人意见
	private Date auditFirTime;		// 第一审批人审批时间
	private Date auditSecTime;		// 第二审批人审批时间
	private Date auditThrTime;		// 第三审批人审批时间
    private Date auditFouTime;		// 第四审批人审批时间
	private String auditFirId;		// 第一审批人ID
	private String auditSecId;		// 第二审批人ID
	private String auditThrId;		// 第三审批人ID
	private String auditFourId;		// 第四审批人ID
	private String auditFstate;		// 第一审批人审批状态
	private String auditSstate;		// 第二审批人审批状态
	private String auditTstate;		// 第三审批人审批状态
	private String auditFourState;	// 第四审批人审批状态
	
	
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
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getStartendTime() {
		return startendTime;
	}
	public void setStartendTime(String startendTime) {
		this.startendTime = startendTime;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getRealityStartTime() {
		return realityStartTime;
	}
	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}
	public Date getRealityEndTime() {
		return realityEndTime;
	}
	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}
	public String getRealStartEndTime() {
		return realStartEndTime;
	}
	public void setRealStartEndTime(String realStartEndTime) {
		this.realStartEndTime = realStartEndTime;
	}
	public String getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Length(min=0, max=30, message="加班核算方式长度必须介于 0 和 30 之间")
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Length(min=0, max=150, message="第一审批人意见长度必须介于 0 和 150 之间")
	public String getAuditFirInfo() {
		return auditFirInfo;
	}

	public void setAuditFirInfo(String auditFirInfo) {
		this.auditFirInfo = auditFirInfo;
	}
	
	@Length(min=0, max=150, message="第二审批人意见长度必须介于 0 和 150 之间")
	public String getAuditSecInfo() {
		return auditSecInfo;
	}

	public void setAuditSecInfo(String auditSecInfo) {
		this.auditSecInfo = auditSecInfo;
	}
	
	@Length(min=0, max=150, message="第三审批人意见长度必须介于 0 和 150 之间")
	public String getAuditThrInfo() {
		return auditThrInfo;
	}

	public void setAuditThrInfo(String auditThrInfo) {
		this.auditThrInfo = auditThrInfo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditFirTime() {
		return auditFirTime;
	}

	public void setAuditFirTime(Date auditFirTime) {
		this.auditFirTime = auditFirTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditThrTime() {
		return auditThrTime;
	}

	public void setAuditThrTime(Date auditThrTime) {
		this.auditThrTime = auditThrTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditSecTime() {
		return auditSecTime;
	}

	public void setAuditSecTime(Date auditSecTime) {
		this.auditSecTime = auditSecTime;
	}
	
	@Length(min=0, max=64, message="第一审批人id长度必须介于 0 和 64 之间")
	public String getAuditFirId() {
		return auditFirId;
	}

	public void setAuditFirId(String auditFirId) {
		this.auditFirId = auditFirId;
	}
	
	@Length(min=0, max=64, message="第二审批人id长度必须介于 0 和 64 之间")
	public String getAuditSecId() {
		return auditSecId;
	}

	public void setAuditSecId(String auditSecId) {
		this.auditSecId = auditSecId;
	}
	
	@Length(min=1, max=64, message="第三审批人ID长度必须介于 1 和 64 之间")
	public String getAuditThrId() {
		return auditThrId;
	}

	public void setAuditThrId(String auditThrId) {
		this.auditThrId = auditThrId;
	}
	
	@Length(min=0, max=1, message="第一审批人审批状态长度必须介于 0 和 1 之间")
	public String getAuditFstate() {
		return auditFstate;
	}

	public void setAuditFstate(String auditFstate) {
		this.auditFstate = auditFstate;
	}
	
	@Length(min=0, max=1, message="第二审批人审批状态长度必须介于 0 和 1 之间")
	public String getAuditSstate() {
		return auditSstate;
	}

	public void setAuditSstate(String auditSstate) {
		this.auditSstate = auditSstate;
	}
	
	@Length(min=0, max=1, message="第三审批人审批状态长度必须介于 0 和 1 之间")
	public String getAuditTstate() {
		return auditTstate;
	}

	public void setAuditTstate(String auditTstate) {
		this.auditTstate = auditTstate;
	}
	

	@Length(min=0, max=150, message="第四审批人意见长度必须介于 0 和 150 之间")
	public String getAuditForInfo() {
		return auditForInfo;
	}

	public void setAuditForInfo(String auditForInfo) {
		this.auditForInfo = auditForInfo;
	}
	
	@Length(min=0, max=64, message="第四审批人ID长度必须介于 0 和 64 之间")
	public String getAuditFourId() {
		return auditFourId;
	}

	public void setAuditFourId(String auditFourId) {
		this.auditFourId = auditFourId;
	}
	
	@Length(min=0, max=1, message="第四审批人审批状态长度必须介于 0 和 1 之间")
	public String getAuditFourState() {
		return auditFourState;
	}

	public void setAuditFourState(String auditFourState) {
		this.auditFourState = auditFourState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditFouTime() {
		return auditFouTime;
	}

	public void setAuditFouTime(Date auditFouTime) {
		this.auditFouTime = auditFouTime;
	}
	
	
	public OverTime(String processInstanceId, String startendTime,
			String hours, String reason, Date realityStartTime,
			Date realityEndTime, String realStartEndTime, String isHoliday,
			String oneLevelApprovalResult, String twoLevelApprovalResult,
			String threeLevelApprovalResult, String fourLevelApprovalResult,
			Date startTime, Date endTime, Date applyTime, String accountType,
			String auditFirInfo, String auditSecInfo, String auditThrInfo,
			String auditForInfo, Date auditFirTime, Date auditSecTime,
			Date auditThrTime, Date auditFouTime, String auditFirId,
			String auditSecId, String auditThrId, String auditFourId,
			String auditFstate, String auditSstate, String auditTstate,
			String auditFourState) {
		super();
		this.processInstanceId = processInstanceId;
		this.startendTime = startendTime;
		this.hours = hours;
		this.reason = reason;
		this.realityStartTime = realityStartTime;
		this.realityEndTime = realityEndTime;
		this.realStartEndTime = realStartEndTime;
		this.isHoliday = isHoliday;
		this.oneLevelApprovalResult = oneLevelApprovalResult;
		this.twoLevelApprovalResult = twoLevelApprovalResult;
		this.threeLevelApprovalResult = threeLevelApprovalResult;
		this.fourLevelApprovalResult = fourLevelApprovalResult;
		this.startTime = startTime;
		this.endTime = endTime;
		this.applyTime = applyTime;
		this.accountType = accountType;
		this.auditFirInfo = auditFirInfo;
		this.auditSecInfo = auditSecInfo;
		this.auditThrInfo = auditThrInfo;
		this.auditForInfo = auditForInfo;
		this.auditFirTime = auditFirTime;
		this.auditSecTime = auditSecTime;
		this.auditThrTime = auditThrTime;
		this.auditFouTime = auditFouTime;
		this.auditFirId = auditFirId;
		this.auditSecId = auditSecId;
		this.auditThrId = auditThrId;
		this.auditFourId = auditFourId;
		this.auditFstate = auditFstate;
		this.auditSstate = auditSstate;
		this.auditTstate = auditTstate;
		this.auditFourState = auditFourState;
	}
	public OverTime() {
		super();
	}
	
	public OverTime(String id){
		super(id);
	}
	
}
