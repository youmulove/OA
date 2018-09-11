/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公文模板Entity
 * @author lisp
 * @version 2017-11-02
 */
public class OaDocTemplete extends DataEntity<OaDocTemplete> {
	
	private static final long serialVersionUID = 1L;
	private String templeteName;		// 模板名称
	private String companyId;		// 所属公司
	private String filePath;		// 文件路径
	private String yuliu1;		// yuliu1
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// yuliu5
	
	public OaDocTemplete() {
		super();
	}

	public OaDocTemplete(String id){
		super(id);
	}

	@Length(min=0, max=255, message="模板名称长度必须介于 0 和 255 之间")
	public String getTempleteName() {
		return templeteName;
	}

	public void setTempleteName(String templeteName) {
		this.templeteName = templeteName;
	}
	
	@Length(min=0, max=255, message="所属公司长度必须介于 0 和 255 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Length(min=0, max=255, message="文件路径长度必须介于 0 和 255 之间")
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