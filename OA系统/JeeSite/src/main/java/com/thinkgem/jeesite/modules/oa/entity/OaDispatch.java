/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 发文单Entity
 * @author lisp
 * @version 2017-11-03
 */
public class OaDispatch extends DataEntity<OaDispatch> {
	
	private static final long serialVersionUID = 1L;
	private String oaDocApprovalId;		// 公文审批表id
	private String oaSealId;		// 公章id
	private String title;		// 标题
	private String docNumber;		// 发文号
	private String docType;		// 文种
	private String dispatchNum;		// 发文份数
	private Date dispatchDate;		// 发文日期
	private String secretLevel;		// 密级（一般、秘密、机密、绝密）
	private String urgency;		// 紧急程度（普通、重要、紧急）
	private String subjectHeading;		// 主题词
	private String dispatchCopy;		// 抄送人
	private String filePath;		// 附件公文
	private String yuliu1;		// yuliu1
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// yuliu5
	
	public OaDispatch() {
		super();
	}

	public OaDispatch(String id){
		super(id);
	}

	@Length(min=1, max=255, message="公文审批表id长度必须介于 1 和 255 之间")
	public String getOaDocApprovalId() {
		return oaDocApprovalId;
	}

	public void setOaDocApprovalId(String oaDocApprovalId) {
		this.oaDocApprovalId = oaDocApprovalId;
	}
	
	@Length(min=1, max=255, message="公章id长度必须介于 1 和 255 之间")
	public String getOaSealId() {
		return oaSealId;
	}

	public void setOaSealId(String oaSealId) {
		this.oaSealId = oaSealId;
	}
	
	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="发文号长度必须介于 0 和 255 之间")
	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	
	@Length(min=0, max=255, message="文种长度必须介于 0 和 255 之间")
	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Length(min=0, max=255, message="发文份数长度必须介于 0 和 255 之间")
	public String getDispatchNum() {
		return dispatchNum;
	}

	public void setDispatchNum(String dispatchNum) {
		this.dispatchNum = dispatchNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	
	@Length(min=0, max=255, message="密级（一般、秘密、机密、绝密）长度必须介于 0 和 255 之间")
	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}
	
	@Length(min=0, max=255, message="紧急程度（普通、重要、紧急）长度必须介于 0 和 255 之间")
	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	@Length(min=0, max=255, message="主题词长度必须介于 0 和 255 之间")
	public String getSubjectHeading() {
		return subjectHeading;
	}

	public void setSubjectHeading(String subjectHeading) {
		this.subjectHeading = subjectHeading;
	}
	
	@Length(min=0, max=255, message="抄送人长度必须介于 0 和 255 之间")
	public String getDispatchCopy() {
		return dispatchCopy;
	}

	public void setDispatchCopy(String dispatchCopy) {
		this.dispatchCopy = dispatchCopy;
	}
	
	@Length(min=0, max=255, message="附件公文长度必须介于 0 和 255 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	
	@Length(min=0, max=255, message="yuliu5长度必须介于 0 和 255 之间")
	public String getYuliu5() {
		return yuliu5;
	}

	public void setYuliu5(String yuliu5) {
		this.yuliu5 = yuliu5;
	}
	
}