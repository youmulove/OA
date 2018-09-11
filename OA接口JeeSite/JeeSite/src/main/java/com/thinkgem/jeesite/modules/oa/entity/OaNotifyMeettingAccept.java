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
 * Entity
 * 
 * @author ThinkGem
 * @version 2017-09-13
 */
public class OaNotifyMeettingAccept extends DataEntity<OaNotifyMeettingAccept> {

	private static final long serialVersionUID = 1L;
	private OaNotifyMeeting oaNotifyMeeting; // 会议申请的id 父类
	private User user; // 接收人id
	private String readFlag; // 阅读标记
	private Date readDate; // 阅读日期
	private String officeNames;

	public OaNotifyMeettingAccept() {
		super();
	}

	public OaNotifyMeettingAccept(String id) {
		super(id);
	}

	public OaNotifyMeettingAccept(OaNotifyMeeting oaNotifyMeeting) {
		this.oaNotifyMeeting = oaNotifyMeeting;
	}

	/**
	 * @Length(min=0, max=64, message="会议申请的id长度必须介于 0 和 64 之间") public
	 *                OaNotifyMeeting getMeetingId() { return meetingId; }
	 * 
	 *                public void setMeetingId(OaNotifyMeeting meetingId) {
	 *                this.meetingId = meetingId; }
	 */
	public User getUser() {
		return user;
	}

	public OaNotifyMeeting getOaNotifyMeeting() {
		return oaNotifyMeeting;
	}

	public void setOaNotifyMeeting(OaNotifyMeeting oaNotifyMeeting) {
		this.oaNotifyMeeting = oaNotifyMeeting;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 0, max = 1, message = "阅读标记（0：未读；1：已读）长度必须介于 0 和 1 之间")
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