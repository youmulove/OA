/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 会议室Entity
 * @author lisp
 * @version 2017-09-22
 */
public class OfficeRoom extends DataEntity<OfficeRoom> {
	
	private static final long serialVersionUID = 1L;
	private String officeRoomName;		// 会议室名称
	private String officeRoomPosition;		// 会议室位置
	private String describe;		// 描述
	private String applyUserid;		// 申请人
	private String startendtime;		// 申请起止时间
	private String hours;		// 时长
	private String reason;
	private String company;
	private String department;
	private String oneLevelApprovalResult;
	private String twoLevelApprovalResult;
	private String threeLevelApprovalResult;
	private String fourLevelApprovalResult;
	

	
	//private String applyName;
	private String startTime;
	private String endTime;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public OfficeRoom() {
		super();
	}

	public OfficeRoom(String id){
		super(id);
	}

	@Length(min=1, max=50, message="会议室名称长度必须介于 1 和 50 之间")
	@ExcelField(title="会议室名称", align=2, sort=15)
	public String getOfficeRoomName() {
		return officeRoomName;
	}

	@ExcelField(title="部门", align=2, sort=25)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setOfficeRoomName(String officeRoomName) {
		this.officeRoomName = officeRoomName;
	}
	
	@Length(min=0, max=50, message="会议室位置长度必须介于 0 和 50 之间")
	@ExcelField(title="会议室位置", align=2, sort=35)
	public String getOfficeRoomPosition() {
		return officeRoomPosition;
	}
	@ExcelField(title="所属公司", align=2, sort=45)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setOfficeRoomPosition(String officeRoomPosition) {
		this.officeRoomPosition = officeRoomPosition;
	}
	
	@Length(min=0, max=150, message="描述长度必须介于 0 和 150 之间")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@Length(min=0, max=64, message="申请人长度必须介于 0 和 64 之间")
	public String getApplyUserid() {
		return applyUserid;
	}

	public void setApplyUserid(String applyUserid) {
		this.applyUserid = applyUserid;
	}
	
	@Length(min=0, max=64, message="申请起止时间长度必须介于 0 和 64 之间")
	@ExcelField(title="申请起止时间", align=2, sort=55)
	public String getStartendtime() {
		return startendtime;
	}

	public void setStartendtime(String startendtime) {
		this.startendtime = startendtime;
	}
	
	@Length(min=0, max=255, message="时长长度必须介于 0 和 255 之间")
	@ExcelField(title="时长", align=2, sort=65)
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

	
}