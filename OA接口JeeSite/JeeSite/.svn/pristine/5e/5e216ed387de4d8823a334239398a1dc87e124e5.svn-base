/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 公告Entity
 * 
 * @author jhm
 * @version 2017-10-10
 */
public class OaNotice extends DataEntity<OaNotice> {

	private static final long serialVersionUID = 1L;
	private String type; // 类型

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String title; // 标题
	private String content; // 内容
	private String filePath; // 文件路径
	private String receiveUser; // 接受人/部门
	private String readNum; // 已读
	private String unReadNum; // 未读
	private boolean isSelf; // 是否只查询自己的通知
	private String readFlag;

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String files; // 附件
	private String status; // 状态
	private List<OaNoticeRecord> oaNoticeRecordList = Lists.newArrayList(); // 子表列表

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

	public OaNotice() {
		super();
	}

	public OaNotice(String id) {
		super(id);
	}

	@Length(min = 0, max = 2000, message = "标题长度必须介于 0 和 2000 之间")
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

	@Length(min = 0, max = 255, message = "文件路径长度必须介于 0 和 255 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Length(min = 0, max = 2000, message = "接受人/部门长度必须介于 0 和 2000 之间")
	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public List<OaNoticeRecord> getOaNoticeRecordList() {
		return oaNoticeRecordList;
	}

	public void setOaNoticeRecordList(List<OaNoticeRecord> oaNoticeRecordList) {
		this.oaNoticeRecordList = oaNoticeRecordList;
	}

	/**
	 * 获取公告发送记录用户ID
	 * 
	 * @return
	 */
	public String getOaNoticeRecordIds() {
		return Collections3.extractToString(oaNoticeRecordList, "user.id", ",");
	}

	/**
	 * 设置公告发送记录用户ID
	 * 
	 * @return
	 */
	public void setOaNoticeRecordIds(String oaNoticeRecord) {
		this.oaNoticeRecordList = Lists.newArrayList();
		for (String id : StringUtils.split(oaNoticeRecord, ",")) {
			OaNoticeRecord entity = new OaNoticeRecord();
			entity.setId(IdGen.uuid());
			entity.setOaNotice(this);
			entity.setUser(new User(id));
			entity.setReadFlag("0");
			this.oaNoticeRecordList.add(entity);
		}
	}

	/**
	 * 获取公告发送记录用户Name
	 * 
	 * @return
	 */
	public String getOaNoticeRecordNames() {
		return Collections3.extractToString(oaNoticeRecordList, "user.name",
				",");
	}

	/**
	 * 设置公告发送记录用户Name
	 * 
	 * @return
	 */
	public void setOaNoticeRecordNames(String oaNoticeRecord) {
		// 什么也不做
	}
}