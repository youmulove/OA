/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 投影仪申请Entity
 * 
 * @author jhm
 * @version 2017-09-28
 */
public class OaApplyProjector extends DataEntity<OaApplyProjector> {

	private static final long serialVersionUID = 1L;
	private String processInstanceId; // 流程实例编号
	private Date startTime; // 开始时间
	private Date endTime; // 结束时间
	private String reason; // 加班理由
	private String hours; // 加班时长
	private String adapter; // 是否需要转换头
	// private String companyId; // 所属公司
	private Date applyTime; // 申请时间
	private Date realityStartTime; // 实际开始时间
	private Date realityEndTime; // 实际结束时间
	private String auditFirInfo; // 第一审批人意见
	private String auditSecInfo; // 第二审批人意见
	private String auditThrInfo; // 第三审批人意见
	private Date auditFirTime; // 第一审批人审批时间
	private Date auditThrTime; // 第三审批人时间
	private Date auditSecTime; // 第二审批人时间
	private String auditFirId; // 第一审批人id
	private String auditSecId; // 第二审批人id
	private String auditThrId; // 第三审批人ID
	private String auditFstate; // 第一审批人审批状态
	private String auditSstate; // 第二审批人审批状态
	private String auditTstate; // 第三审批人审批状态
	private String startendTime; // 请假起止时间
	private String auditFouInfo; // 第四审批人意见
	private String auditFouId; // 第四审批人ID
	private String auditFouState; // 第四审批人审批状态
	private Date auditFouTime; // 第四审批人审批时间
	private String department; // 所属部门
	private String adapterId; // 投影仪ID
	private String copytoId; // 抄送人ID(多个之间用逗号隔开)
	private String officeroomId; // 会议室ID
	private String yuliu1; // yuliu1
	private String yuliu2; // yuliu2
	private String yuliu3; // yuliu3
	private String yuliu4; // yuliu4
	private String yuliu5; // yuliu5

	private String applyUserid; // 申请人
	private String oneLevelApprovalResult;
	private String twoLevelApprovalResult;
	private String threeLevelApprovalResult;
	private String fourLevelApprovalResult;
	private String roomName;// 会议室名
	private Office company; // 归属公司
	private Office office; // 归属部门
	private String officeNames;// 所属部门名称（包括父级名称）
	private String endAuditState;// 最终审核状态

	public OaApplyProjector() {
		super();
	}

	public OaApplyProjector(String id) {
		super(id);
	}

	@Length(min = 0, max = 8, message = "流程实例编号长度必须介于 0 和 8 之间")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "申请时间", align = 2, sort = 10)
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@ExcelField(title = "申请人", align = 2, sort = 20)
	public String getApplyUserid() {
		return applyUserid;
	}

	public void setApplyUserid(String applyUserid) {
		this.applyUserid = applyUserid;
	}

	@Length(min = 0, max = 64, message = "所属公司长度必须介于 0 和 64 之间")
	@ExcelField(title = "所属公司", align = 2, sort = 30)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	@ExcelField(title = "申请部门", align = 2, sort = 35)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min = 0, max = 64, message = "投影仪ID长度必须介于 0 和 64 之间")
	// @ExcelField(title = "投影仪名称", align = 2, sort = 45)
	public String getAdapterId() {
		return adapterId;
	}

	public void setAdapterId(String adapterId) {
		this.adapterId = adapterId;
	}

	@Length(min = 0, max = 30, message = "所属部门长度必须介于 0 和 30 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Length(min = 0, max = 64, message = "会议室ID长度必须介于 0 和 64 之间")
	public String getOfficeroomId() {
		return officeroomId;
	}

	public void setOfficeroomId(String officeroomId) {
		this.officeroomId = officeroomId;
	}

	@Length(min = 0, max = 255, message = "yuliu1长度必须介于 0 和 255 之间")
	@ExcelField(title = "投影仪名称", align = 2, sort = 55)
	public String getYuliu1() {
		return yuliu1;
	}

	public void setYuliu1(String yuliu1) {
		this.yuliu1 = yuliu1 == null ? "" : yuliu1.trim();
	}

	@Length(min = 0, max = 150, message = "申请理由长度必须介于 0 和 150 之间")
	@ExcelField(title = "申请理由", align = 2, sort = 65)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? "" : reason.trim();
	}

	@Length(min = 0, max = 64, message = "申请起止时间长度必须介于 0 和 64 之间")
	public String getStartendTime() {
		return startendTime;
	}

	public void setStartendTime(String startendTime) {
		this.startendTime = startendTime == null ? "" : startendTime.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "申请开始时间", align = 2, sort = 75)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "申请结束时间", align = 2, sort = 80)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Length(min = 0, max = 60, message = "申请时长长度必须介于 0 和 60 之间")
	@ExcelField(title = "时长", align = 2, sort = 85)
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours == null ? "" : hours.trim();
	}

	@Length(min = 0, max = 1, message = "是否需要转换头长度必须介于 0 和 1 之间")
	@ExcelField(title = "是否需要转接头", align = 2, sort = 95)
	public String getAdapter() {
		return adapter;
	}

	public void setAdapter(String adapter) {
		this.adapter = adapter == null ? "" : adapter.trim();
	}

	@Length(min = 0, max = 64, message = "第一审批人id长度必须介于 0 和 64 之间")
	@ExcelField(title = "第一审批人", align = 2, sort = 110)
	public String getAuditFirId() {
		return auditFirId;
	}

	public void setAuditFirId(String auditFirId) {
		this.auditFirId = auditFirId == null ? "" : auditFirId.trim();
	}

	@Length(min = 0, max = 1, message = "第一审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第一审批人审批状态", align = 2, sort = 120)
	public String getAuditFstate() {
		return auditFstate;
	}

	public void setAuditFstate(String auditFstate) {
		this.auditFstate = auditFstate == null ? "" : auditFstate.trim();
	}

	@Length(min = 0, max = 150, message = "第一审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第一审批人意见", align = 2, sort = 130)
	public String getAuditFirInfo() {
		return auditFirInfo;
	}

	public void setAuditFirInfo(String auditFirInfo) {
		this.auditFirInfo = auditFirInfo == null ? "" : auditFirInfo.trim();
	}

	@Length(min = 0, max = 64, message = "第二审批人id长度必须介于 0 和 64 之间")
	@ExcelField(title = "第二审批人", align = 2, sort = 140)
	public String getAuditSecId() {
		return auditSecId;
	}

	public void setAuditSecId(String auditSecId) {
		this.auditSecId = auditSecId == null ? "" : auditSecId.trim();
	}

	@Length(min = 0, max = 1, message = "第二审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第二审批人审批状态", align = 2, sort = 150)
	public String getAuditSstate() {
		return auditSstate;
	}

	public void setAuditSstate(String auditSstate) {
		this.auditSstate = auditSstate == null ? "" : auditSstate.trim();
	}

	@Length(min = 0, max = 150, message = "第二审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第二审批人意见", align = 2, sort = 160)
	public String getAuditSecInfo() {
		return auditSecInfo;
	}

	public void setAuditSecInfo(String auditSecInfo) {
		this.auditSecInfo = auditSecInfo == null ? "" : auditSecInfo.trim();
	}

	@Length(min = 0, max = 64, message = "第三审批人ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "第三审批人", align = 2, sort = 170)
	public String getAuditThrId() {
		return auditThrId;
	}

	public void setAuditThrId(String auditThrId) {
		this.auditThrId = auditThrId == null ? "" : auditThrId.trim();
	}

	@Length(min = 0, max = 1, message = "第三审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第三审批人审批状态", align = 2, sort = 180)
	public String getAuditTstate() {
		return auditTstate;
	}

	public void setAuditTstate(String auditTstate) {
		this.auditTstate = auditTstate == null ? "" : auditTstate.trim();
	}

	@Length(min = 0, max = 150, message = "第三审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第三审批人意见", align = 2, sort = 190)
	public String getAuditThrInfo() {
		return auditThrInfo;
	}

	public void setAuditThrInfo(String auditThrInfo) {
		this.auditThrInfo = auditThrInfo == null ? "" : auditThrInfo.trim();
	}

	@Length(min = 0, max = 64, message = "第四审批人ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "第四审批人", align = 2, sort = 200)
	public String getAuditFouId() {
		return auditFouId;
	}

	public void setAuditFouId(String auditFouId) {
		this.auditFouId = auditFouId == null ? "" : auditFouId.trim();
	}

	@Length(min = 0, max = 1, message = "第四审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第四审批人审批状态", align = 2, sort = 210)
	public String getAuditFouState() {
		return auditFouState;
	}

	public void setAuditFouState(String auditFouState) {
		this.auditFouState = auditFouState == null ? "" : auditFouState.trim();
	}

	@Length(min = 0, max = 150, message = "第四审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第四审批人意见", align = 2, sort = 220)
	public String getAuditFouInfo() {
		return auditFouInfo;
	}

	public void setAuditFouInfo(String auditFouInfo) {
		this.auditFouInfo = auditFouInfo == null ? "" : auditFouInfo.trim();
	}

	@Length(min = 0, max = 300, message = "抄送人ID(多个之间用逗号隔开)长度必须介于 0 和 300 之间")
	@ExcelField(title = "抄送人", align = 2, sort = 230)
	public String getCopytoId() {
		return copytoId;
	}

	public void setCopytoId(String copytoId) {
		this.copytoId = copytoId == null ? "" : copytoId.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealityStartTime() {
		return realityStartTime;
	}

	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealityEndTime() {
		return realityEndTime;
	}

	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditFirTime() {
		return auditFirTime;
	}

	public void setAuditFirTime(Date auditFirTime) {
		this.auditFirTime = auditFirTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditSecTime() {
		return auditSecTime;
	}

	public void setAuditSecTime(Date auditSecTime) {
		this.auditSecTime = auditSecTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditThrTime() {
		return auditThrTime;
	}

	public void setAuditThrTime(Date auditThrTime) {
		this.auditThrTime = auditThrTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditFouTime() {
		return auditFouTime;
	}

	public void setAuditFouTime(Date auditFouTime) {
		this.auditFouTime = auditFouTime;
	}

	@Length(min = 0, max = 255, message = "yuliu2长度必须介于 0 和 255 之间")
	public String getYuliu2() {
		return yuliu2;
	}

	public void setYuliu2(String yuliu2) {
		this.yuliu2 = yuliu2;
	}

	@Length(min = 0, max = 255, message = "yuliu3长度必须介于 0 和 255 之间")
	public String getYuliu3() {
		return yuliu3;
	}

	public void setYuliu3(String yuliu3) {
		this.yuliu3 = yuliu3;
	}

	@Length(min = 0, max = 255, message = "yuliu4长度必须介于 0 和 255 之间")
	public String getYuliu4() {
		return yuliu4;
	}

	public void setYuliu4(String yuliu4) {
		this.yuliu4 = yuliu4;
	}

	@Length(min = 0, max = 255, message = "yuliu5长度必须介于 0 和 255 之间")
	public String getYuliu5() {
		return yuliu5;
	}

	public void setYuliu5(String yuliu5) {
		this.yuliu5 = yuliu5;
	}

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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getOfficeNames() {
		return officeNames;
	}

	public void setOfficeNames(String officeNames) {
		this.officeNames = officeNames;
	}

	@ExcelField(title = "最终审核状态", align = 2, sort = 240)
	public String getEndAuditState() {
		return endAuditState;
	}

	public void setEndAuditState(String endAuditState) {
		this.endAuditState = endAuditState;
	}

}