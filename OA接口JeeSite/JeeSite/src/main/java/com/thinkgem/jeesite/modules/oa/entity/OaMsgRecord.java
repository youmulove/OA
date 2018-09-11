/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 消息提醒Entity
 * @author wangyl
 * @version 2018-01-12
 */
public class OaMsgRecord extends DataEntity<OaMsgRecord> {
	
	private static final long serialVersionUID = 1L;
	private String msgType;		// msg_type
	private String msgId;		// msg_id
	private User createUserId;		// create_user_id
	private User accerpUserId;		// accerp_user_id
	private String readFlag;		// read_flag
	private Date readDate;		// read_date
	private String msg;		// msg
	
	public OaMsgRecord() {
		super();
	}

	public OaMsgRecord(String id){
		super(id);
	}

	@Length(min=1, max=255, message="msg_type长度必须介于 1 和 255 之间")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Length(min=1, max=64, message="msg_id长度必须介于 1 和 64 之间")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	@NotNull(message="create_user_id不能为空")
	public User getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(User createUserId) {
		this.createUserId = createUserId;
	}
	
	@NotNull(message="accerp_user_id不能为空")
	public User getAccerpUserId() {
		return accerpUserId;
	}

	public void setAccerpUserId(User accerpUserId) {
		this.accerpUserId = accerpUserId;
	}
	
	@Length(min=1, max=1, message="read_flag长度必须介于 1 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="read_date不能为空")
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
	@Length(min=0, max=2000, message="msg长度必须介于 0 和 2000 之间")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}