/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 新建任务Entity
 * 
 * @author 新建任务
 * @version 2017-09-14
 */
public class OaNotifyTask extends DataEntity<OaNotifyTask> {

	private static final long serialVersionUID = 1L;
	private String type; // 类型
	private String title; // 标题
	private String content; // 内容
	private String files; // 附件
	private String status; // 状态
	private Date endTime; // 结束时间
	private String readNum; // 已读
	private String unReadNum; // 未读
	private boolean isSelf;// 是否只查询自己的任务通知
	private String readFlag; // 本人阅读状态
	private String userName;// 发送人名字

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(String unReadNum) {
		this.unReadNum = unReadNum;
	}

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private List<OaNotifyTaskAccept> oaNotifyTaskAcceptList = Lists
			.newArrayList(); // 子表列表

	public OaNotifyTask() {
		super();
	}

	public OaNotifyTask(String id) {
		super(id);
	}

	@Length(min = 0, max = 1, message = "类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 200, message = "标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min = 0, max = 2000, message = "内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min = 0, max = 2000, message = "附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Length(min = 0, max = 1, message = "状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<OaNotifyTaskAccept> getOaNotifyTaskAcceptList() {
		return oaNotifyTaskAcceptList;
	}

	public void setOaNotifyTaskAcceptList(
			List<OaNotifyTaskAccept> oaNotifyTaskAcceptList) {
		this.oaNotifyTaskAcceptList = oaNotifyTaskAcceptList;
	}

	/**
	 * 获取任务发送记录用户ID
	 * 
	 * @return
	 */
	public String getOaNotifyTaskAcceptIds() {
		return Collections3.extractToString(oaNotifyTaskAcceptList, "user.id",
				",");
	}

	/**
	 * 设置任务发送记录用户ID
	 * 
	 * @return
	 */

	public void setOaNotifyTaskAcceptIds(String oaNotifyTaskAccept) {
		this.oaNotifyTaskAcceptList = Lists.newArrayList();
		for (String id : StringUtils.split(oaNotifyTaskAccept, ",")) {
			OaNotifyTaskAccept entity = new OaNotifyTaskAccept();
			entity.setId(IdGen.uuid());
			entity.setTaskId(this);
			entity.setUser(new User(id));
			entity.setReadFlag("0");
			this.oaNotifyTaskAcceptList.add(entity);
		}
	}

	/**
	 * 获取任务发送记录用户Name
	 * 
	 * @return
	 */

	public String getOaNotifyTaskAcceptNames() {
		return Collections3.extractToString(oaNotifyTaskAcceptList,
				"user.name", ",");
	}

	/**
	 * 设置任务发送记录用户Name
	 * 
	 * @return
	 */
	public void setOaNotifyTaskAcceptNames(String oaNotifyTaskAccept) {
		// 什么也不做
	}
}