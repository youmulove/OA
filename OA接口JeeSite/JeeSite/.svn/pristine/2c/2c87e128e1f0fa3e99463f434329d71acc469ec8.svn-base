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
 * 公告Entity
 * 
 * @author jhm
 * @version 2017-10-10
 */
public class OaNoticeRecord extends DataEntity<OaNoticeRecord> {

	private static final long serialVersionUID = 1L;
	private OaNotice oaNotice; // 公告id 父类
	private User user; // 接收人
	private String readFlag; // 阅读标记
	private Date readDate; // 阅读时间
	private String officeNames;

	public OaNoticeRecord() {
		super();
	}

	public OaNoticeRecord(String id) {
		super(id);
	}

	public OaNoticeRecord(OaNotice oaNotice) {
		this.oaNotice = oaNotice;
	}

	@Length(min = 0, max = 64, message = "公告id长度必须介于 0 和 64 之间")
	public OaNotice getOaNotice() {
		return oaNotice;
	}

	public void setOaNotice(OaNotice oaNotice) {
		this.oaNotice = oaNotice;
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