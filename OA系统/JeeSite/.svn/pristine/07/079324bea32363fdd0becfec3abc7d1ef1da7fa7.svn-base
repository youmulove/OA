/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 新建任务Entity
 * 
 * @author 新建任务
 * @version 2017-09-14
 */
public class OaNotifyTaskAccept extends DataEntity<OaNotifyTaskAccept> {

	private static final long serialVersionUID = 1L;
	private OaNotifyTask taskId; // 任务接收的id 父类
	private User user; // 接收人id
	private String readFlag; // 阅读标记
	private Date readDate; // 阅读日期
	private String officeNames;

	public OaNotifyTaskAccept() {
		super();
	}

	public OaNotifyTaskAccept(String id) {
		super(id);
	}

	public OaNotifyTaskAccept(OaNotifyTask taskId) {
		this.taskId = taskId;
	}

	@Length(min = 0, max = 64, message = "任务接收的id长度必须介于 0 和 64 之间")
	public OaNotifyTask getTaskId() {
		return taskId;
	}

	public void setTaskId(OaNotifyTask taskId) {
		this.taskId = taskId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 0, max = 1, message = "阅读标记长度必须介于 0 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public String getOfficeNames() {
		return officeNames;
	}

	public void setOfficeNames(String officeNames) {
		this.officeNames = officeNames;
	}

}