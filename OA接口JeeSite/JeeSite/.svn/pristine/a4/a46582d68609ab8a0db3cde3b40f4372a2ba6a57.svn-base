/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 请假申请Entity
 * @author jhm
 * @version 2017-09-28
 */
public class OaLeaved extends DataEntity<OaLeaved> {
	
	private static final long serialVersionUID = 1L;
	private String auditId;		// 审批ID
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String leaveType;		// 请假类型
	private String reason;		// 请假理由
	private Date applyTime;		// 申请时间
	private Date realityStartTime;		// 实际开始时间
	private Date realityEndTime;		// 实际结束时间
	private String leavedTime;		// 请假时长
	private String auditNo;		// 审批编号
	private String auditFirInfo;		// 第一审批人意见
	private String auditSecInfo;		// 第二审批人意见
	private String auditThrInfo;		// 第三审批人意见
	private Date auditFirTime;		// 第一审批人申请时间
	private Date auditSecTime;		// 第二审批人申请时间
	private Date auditThrTime;		// 第三审批人申请时间
	private String processInstanceId;		// 流程实例编号
	private String auditFirState;		// 第一审批人审批状态
	private String auditFirId;		// 第一审批人ID
	private String auditSecId;		// 第二审批人ID
	private String auditThrId;		// 第三审批人ID
	private String copytoId;		// 抄送人(多个ID逗号隔开)
	private String auditSecState;		// 第二审批人审批状态
	private String auditThrState;		// 第三审批人审批状态
	private String auditFouState;		// 第四审批人状态
	private String auditFouInfo;		// 第四审批人意见
	private Date auditFouTime;		// 第四审批人审批时间
	private String auditFouId;		// 第四审批人ID
	private String startendTime;		// 申请起止时间
	private String yuliu1;		// yuliu1
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// 请假时长
	private String hours;		// 天
	private String applyName;//申请人姓名
	
	
	private Office company; // 归属公司
	private Office office; // 归属部门
	private String officeNames;//所属部门名称（包括父级名称）
	private String endAuditState;//最终审核状态
	List<User> applyUserList = Lists
			.newArrayList();
	
	public OaLeaved() {
		super();
	}

	public OaLeaved(String id){
		super(id);
	}
	@Length(min=0, max=64, message="流程实例编号长度必须介于 0 和 64 之间")
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
	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName==null?"":applyName.trim();
	}
	@ExcelField(title = "所属公司", align = 2, sort = 30)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	public Office getOffice() {
		return office;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}
	@Length(min=0, max=1, message="请假类型长度必须介于 0 和 1 之间")
	@ExcelField(title = "请假类型", align = 2, sort = 50)
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType==null?"":leaveType.trim();
	}
	@Length(min=0, max=255, message="请假理由长度必须介于 0 和 255 之间")
	@ExcelField(title = "申请原因", align = 2, sort = 60)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason==null?"":reason.trim();
	}
	@Length(min=0, max=64, message="申请起止时间长度必须介于 0 和 64 之间")
	public String getStartendTime() {
		return startendTime;
	}

	public void setStartendTime(String startendTime) {
		this.startendTime = startendTime==null?"":startendTime.trim();
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "申请开始时间", align = 2, sort = 70)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "申请结束时间", align = 2, sort = 75)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Length(min=0, max=255, message="hours长度必须介于 0 和 255 之间")
	@ExcelField(title = "时长", align = 2, sort = 80)
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours==null?"":hours.trim();
	}
	@Length(min=0, max=64, message="第一审批人ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "第一审批人", align = 2, sort = 90)
	public String getAuditFirId() {
		return auditFirId;
	}

	public void setAuditFirId(String auditFirId) {
		this.auditFirId = auditFirId==null?"":auditFirId.trim();
	}
	@Length(min=0, max=1, message="第一审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第一审批人状态", align = 2, sort = 100)
	public String getAuditFirState() {
		return auditFirState;
	}

	public void setAuditFirState(String auditFirState) {
		this.auditFirState = auditFirState==null?"":auditFirState.trim();
	}
	@Length(min=0, max=150, message="第一审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第一审批人意见", align = 2, sort = 110)
	public String getAuditFirInfo() {
		return auditFirInfo;
	}

	public void setAuditFirInfo(String auditFirInfo) {
		this.auditFirInfo = auditFirInfo==null?"":auditFirInfo.trim();
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditFirTime() {
		return auditFirTime;
	}

	public void setAuditFirTime(Date auditFirTime) {
		this.auditFirTime = auditFirTime;
	}
	@Length(min=0, max=64, message="第二审批人ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "第二审批人", align = 2, sort = 120)
	public String getAuditSecId() {
		return auditSecId;
	}
	
	public void setAuditSecId(String auditSecId) {
		this.auditSecId = auditSecId==null?"":auditSecId.trim();
	}
	@Length(min=0, max=1, message="第二审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第二审批人状态", align = 2, sort = 130)
	public String getAuditSecState() {
		return auditSecState;
	}

	public void setAuditSecState(String auditSecState) {
		this.auditSecState = auditSecState==null?"":auditSecState.trim();
	}

	@Length(min=0, max=150, message="第二审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第二审批人意见", align = 2, sort = 140)
	public String getAuditSecInfo() {
		return auditSecInfo;
	}
	public void setAuditSecInfo(String auditSecInfo) {
		this.auditSecInfo = auditSecInfo==null?"":auditSecInfo.trim();
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditSecTime() {
		return auditSecTime;
	}

	public void setAuditSecTime(Date auditSecTime) {
		this.auditSecTime = auditSecTime;
	}
	
	@Length(min=0, max=64, message="第三审批人ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "第三审批人", align = 2, sort = 150)
	public String getAuditThrId() {
		return auditThrId;
	}
	public void setAuditThrId(String auditThrId) {
		this.auditThrId = auditThrId==null?"":auditThrId.trim();
	}

	@Length(min=0, max=1, message="第三审批人审批状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第三审批人审批状态", align = 2, sort = 160)
	public String getAuditThrState() {
		return auditThrState;
	}
	public void setAuditThrState(String auditThrState) {
		this.auditThrState = auditThrState==null?"":auditThrState.trim();
	}
	@Length(min=0, max=150, message="第三审批人意见长度必须介于 0 和 150 之间")
	@ExcelField(title = "第三审批人意见", align = 2, sort = 170)
	public String getAuditThrInfo() {
		return auditThrInfo;
	}
	public void setAuditThrInfo(String auditThrInfo) {
		this.auditThrInfo = auditThrInfo==null?"":auditThrInfo.trim();
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditThrTime() {
		return auditThrTime;
	}

	public void setAuditThrTime(Date auditThrTime) {
		this.auditThrTime = auditThrTime;
	}
	
	@Length(min=0, max=64, message="第四审批人ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "第四审批人", align = 2, sort = 180)
	public String getAuditFouId() {
		return auditFouId;
	}
	public void setAuditFouId(String auditFouId) {
		this.auditFouId = auditFouId ;
	}
	@Length(min=0, max=1, message="第四审批人状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "第四审批人状态", align = 2, sort = 190)
	public String getAuditFouState() {
		return auditFouState;
	}

	public void setAuditFouState(String auditFouState) {
		this.auditFouState = auditFouState==null?"":auditFouState.trim();
	}
	@Length(min=0, max=255, message="第四审批人意见长度必须介于 0 和 255 之间")
	@ExcelField(title = "第四审批人意见", align = 2, sort = 200)
	public String getAuditFouInfo() {
		return auditFouInfo;
	}

	public void setAuditFouInfo(String auditFouInfo) {
		this.auditFouInfo = auditFouInfo==null?"":auditFouInfo.trim();
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditFouTime() {
		return auditFouTime;
	}

	public void setAuditFouTime(Date auditFouTime) {
		this.auditFouTime = auditFouTime;
	}
	@Length(min=0, max=300, message="抄送人(多个ID逗号隔开)长度必须介于 0 和 300 之间")
	@ExcelField(title = "抄送人", align = 2, sort = 210)
	public String getCopytoId() {
		return copytoId;
	}

	public void setCopytoId(String copytoId) {
		this.copytoId = copytoId;
	}
	
	@Length(min=0, max=64, message="审批ID长度必须介于 0 和 64 之间")
	
	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId==null?"":auditId.trim();
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
	
	@Length(min=0, max=30, message="请假时长长度必须介于 0 和 30 之间")
	public String getLeavedTime() {
		return leavedTime;
	}

	public void setLeavedTime(String leavedTime) {
		this.leavedTime = leavedTime;
	}
	
	@Length(min=0, max=50, message="审批编号长度必须介于 0 和 50 之间")
	public String getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(String auditNo) {
		this.auditNo = auditNo;
	}
	@Length(min=0, max=255, message="yuliu1长度必须介于 0 和 255 之间")
	public String getYuliu1() {
		return yuliu1;
	}

	public void setYuliu1(String yuliu1) {
		this.yuliu1 = yuliu1;
	}
	
	@Length(min=0, max=255, message="yuliu2长度必须介于 0 和 255 之间")
	public String getYuliu2() {
		return yuliu2;
	}

	public void setYuliu2(String yuliu2) {
		this.yuliu2 = yuliu2;
	}
	
	@Length(min=0, max=255, message="yuliu3长度必须介于 0 和 255 之间")
	public String getYuliu3() {
		return yuliu3;
	}

	public void setYuliu3(String yuliu3) {
		this.yuliu3 = yuliu3;
	}
	
	@Length(min=0, max=255, message="yuliu4长度必须介于 0 和 255 之间")
	public String getYuliu4() {
		return yuliu4;
	}

	public void setYuliu4(String yuliu4) {
		this.yuliu4 = yuliu4;
	}
	
	@Length(min=0, max=255, message="请假时长长度必须介于 0 和 255 之间")
	public String getYuliu5() {
		return yuliu5;
	}

	public void setYuliu5(String yuliu5) {
		this.yuliu5 = yuliu5;
	}
	
	
	@ExcelField(title = "所属部门", align = 2, sort = 40)
    public String getOfficeNames() {
		return officeNames;
	}

	public void setOfficeNames(String officeNames) {
		this.officeNames = officeNames;
	}
	@ExcelField(title = "最终审核状态", align = 2, sort = 220)
    public String getEndAuditState() {
		return endAuditState;
	}

	public void setEndAuditState(String endAuditState) {
		this.endAuditState = endAuditState;
	}

/**
 * 获得审批人或者抄送人的姓名
 * @return
 */
	
	public String getLeaveApplyNames() {
		return Collections3.extractToString(applyUserList,
				"user.name", ",");
	}
	public void setLeaveApplyNames(String user) {
		// 什么也不做
	}
}