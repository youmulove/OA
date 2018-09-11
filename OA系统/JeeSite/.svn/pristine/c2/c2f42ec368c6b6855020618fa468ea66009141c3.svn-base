/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公文审批记录Entity
 * @author lisp
 * @version 2017-11-02
 */
public class OaDocApproval extends DataEntity<OaDocApproval> {
	
	private static final long serialVersionUID = 1L;
	private String oaDispatchId;		// 发文单id
	private String ngId;		// 拟稿人id
	private String ngName;		// 拟稿人名字
	private String sgId;		// 审稿人id
	private String sgName;		// 审稿人名字
	private String sgOption;		// 审稿人意见
	private String sgFlag;		// 审稿状态
	private String hgId;		// 核稿人id
	private String hgName;		// 核稿人名字
	private String hgOption;		// 核稿人意见
	private String hgFlag;		// 核稿人审批状态
	private String signId;		// 会签人id
	private String signName;		// 会签人名字
	private String signOption;		// 会签意见
	private String signFlag;		// 会签状态
	private String officeApprovalId;		// 办公室审批人id
	private String officeApprovalName;		// 办公室审批人名字
	private String officeOption;		// 办公室审批意见
	private String officeFlag;		// 办公室审批状态
	private String qfId;		// 签发人id
	private String qfName;		// 签发人名字
	private String qfOption;		// 签发人意见
	private String qfFlag;		// 签发状态
	private String checkId;		// 校对人id
	private String checkName;		// 校对人名字
	private String checkOption;		// 校对人意见
	private String checkFlag;		// 校对状态
	private String useSealId;		// 用印人id
	private String useSealName;		// 用印人名字
	private String useSealOption;		// 用印人意见
	private String useSealFlag;		// 用印人审批状态
	private String yuliu1;		// yuliu1
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// yuliu5
	private String endFlag;		// 审批结束标识(y|n）
	private String processInstanceId;		// process_instance_id
	
	private OaDispatch oaDispatch;
	

	public OaDispatch getOaDispatch() {
		return oaDispatch;
	}

	public void setOaDispatch(OaDispatch oaDispatch) {
		this.oaDispatch = oaDispatch;
	}

	public OaDocApproval() {
		super();
	}

	public OaDocApproval(String id){
		super(id);
	}

	@Length(min=0, max=255, message="发文单id长度必须介于 0 和 255 之间")
	public String getOaDispatchId() {
		return oaDispatchId;
	}

	public void setOaDispatchId(String oaDispatchId) {
		this.oaDispatchId = oaDispatchId;
	}
	
	@Length(min=0, max=255, message="拟稿人id长度必须介于 0 和 255 之间")
	public String getNgId() {
		return ngId;
	}

	public void setNgId(String ngId) {
		this.ngId = ngId;
	}
	
	@Length(min=0, max=255, message="拟稿人名字长度必须介于 0 和 255 之间")
	public String getNgName() {
		return ngName;
	}

	public void setNgName(String ngName) {
		this.ngName = ngName;
	}
	
	@Length(min=0, max=4000, message="审稿人id长度必须介于 0 和 4000 之间")
	public String getSgId() {
		return sgId;
	}

	public void setSgId(String sgId) {
		this.sgId = sgId;
	}
	
	@Length(min=0, max=4000, message="审稿人名字长度必须介于 0 和 4000 之间")
	public String getSgName() {
		return sgName;
	}

	public void setSgName(String sgName) {
		this.sgName = sgName;
	}
	
	@Length(min=0, max=4000, message="审稿人意见长度必须介于 0 和 4000 之间")
	public String getSgOption() {
		return sgOption;
	}

	public void setSgOption(String sgOption) {
		this.sgOption = sgOption;
	}
	
	@Length(min=0, max=255, message="审稿状态长度必须介于 0 和 255 之间")
	public String getSgFlag() {
		return sgFlag;
	}

	public void setSgFlag(String sgFlag) {
		this.sgFlag = sgFlag;
	}
	
	@Length(min=0, max=4000, message="核稿人id长度必须介于 0 和 4000 之间")
	public String getHgId() {
		return hgId;
	}

	public void setHgId(String hgId) {
		this.hgId = hgId;
	}
	
	@Length(min=0, max=4000, message="核稿人名字长度必须介于 0 和 4000 之间")
	public String getHgName() {
		return hgName;
	}

	public void setHgName(String hgName) {
		this.hgName = hgName;
	}
	
	@Length(min=0, max=4000, message="核稿人意见长度必须介于 0 和 4000 之间")
	public String getHgOption() {
		return hgOption;
	}

	public void setHgOption(String hgOption) {
		this.hgOption = hgOption;
	}
	
	@Length(min=0, max=255, message="核稿人审批状态长度必须介于 0 和 255 之间")
	public String getHgFlag() {
		return hgFlag;
	}

	public void setHgFlag(String hgFlag) {
		this.hgFlag = hgFlag;
	}
	
	@Length(min=0, max=4000, message="会签人id长度必须介于 0 和 4000 之间")
	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}
	
	@Length(min=0, max=4000, message="会签人名字长度必须介于 0 和 4000 之间")
	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}
	
	@Length(min=0, max=4000, message="会签意见长度必须介于 0 和 4000 之间")
	public String getSignOption() {
		return signOption;
	}

	public void setSignOption(String signOption) {
		this.signOption = signOption;
	}
	
	@Length(min=0, max=255, message="会签状态长度必须介于 0 和 255 之间")
	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
	
	@Length(min=0, max=4000, message="办公室审批人id长度必须介于 0 和 4000 之间")
	public String getOfficeApprovalId() {
		return officeApprovalId;
	}

	public void setOfficeApprovalId(String officeApprovalId) {
		this.officeApprovalId = officeApprovalId;
	}
	
	@Length(min=0, max=4000, message="办公室审批人名字长度必须介于 0 和 4000 之间")
	public String getOfficeApprovalName() {
		return officeApprovalName;
	}

	public void setOfficeApprovalName(String officeApprovalName) {
		this.officeApprovalName = officeApprovalName;
	}
	
	@Length(min=0, max=4000, message="办公室审批意见长度必须介于 0 和 4000 之间")
	public String getOfficeOption() {
		return officeOption;
	}

	public void setOfficeOption(String officeOption) {
		this.officeOption = officeOption;
	}
	
	@Length(min=0, max=255, message="办公室审批状态长度必须介于 0 和 255 之间")
	public String getOfficeFlag() {
		return officeFlag;
	}

	public void setOfficeFlag(String officeFlag) {
		this.officeFlag = officeFlag;
	}
	
	@Length(min=0, max=4000, message="签发人id长度必须介于 0 和 4000 之间")
	public String getQfId() {
		return qfId;
	}

	public void setQfId(String qfId) {
		this.qfId = qfId;
	}
	
	@Length(min=0, max=4000, message="签发人名字长度必须介于 0 和 4000 之间")
	public String getQfName() {
		return qfName;
	}

	public void setQfName(String qfName) {
		this.qfName = qfName;
	}
	
	@Length(min=0, max=4000, message="签发人意见长度必须介于 0 和 4000 之间")
	public String getQfOption() {
		return qfOption;
	}

	public void setQfOption(String qfOption) {
		this.qfOption = qfOption;
	}
	
	@Length(min=0, max=255, message="签发状态长度必须介于 0 和 255 之间")
	public String getQfFlag() {
		return qfFlag;
	}

	public void setQfFlag(String qfFlag) {
		this.qfFlag = qfFlag;
	}
	
	@Length(min=0, max=4000, message="校对人id长度必须介于 0 和 4000 之间")
	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	
	@Length(min=0, max=4000, message="校对人名字长度必须介于 0 和 4000 之间")
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	@Length(min=0, max=4000, message="校对人意见长度必须介于 0 和 4000 之间")
	public String getCheckOption() {
		return checkOption;
	}

	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}
	
	@Length(min=0, max=255, message="校对状态长度必须介于 0 和 255 之间")
	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	@Length(min=0, max=4000, message="用印人id长度必须介于 0 和 4000 之间")
	public String getUseSealId() {
		return useSealId;
	}

	public void setUseSealId(String useSealId) {
		this.useSealId = useSealId;
	}
	
	@Length(min=0, max=4000, message="用印人名字长度必须介于 0 和 4000 之间")
	public String getUseSealName() {
		return useSealName;
	}

	public void setUseSealName(String useSealName) {
		this.useSealName = useSealName;
	}
	
	@Length(min=0, max=4000, message="用印人意见长度必须介于 0 和 4000 之间")
	public String getUseSealOption() {
		return useSealOption;
	}

	public void setUseSealOption(String useSealOption) {
		this.useSealOption = useSealOption;
	}
	
	@Length(min=0, max=255, message="用印人审批状态长度必须介于 0 和 255 之间")
	public String getUseSealFlag() {
		return useSealFlag;
	}

	public void setUseSealFlag(String useSealFlag) {
		this.useSealFlag = useSealFlag;
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
	
	@Length(min=0, max=255, message="审批结束标识(y|n）长度必须介于 0 和 255 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}
	
	@Length(min=0, max=255, message="process_instance_id长度必须介于 0 和 255 之间")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}