/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公章Entity
 * @author lisp
 * @version 2017-11-02
 */
public class OaSeal extends DataEntity<OaSeal> {
	
	private static final long serialVersionUID = 1L;
	private String ownerId;		// 拥有人
	private String ownerName;		// 拥有人名字
	private String usePassword;		// 使用密码
	private String filePath;		// 文件路径
	private String yuliu1;		// yuliu1
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// yuliu5
	private String sealName;		// seal_name
	
	public OaSeal() {
		super();
	}

	public OaSeal(String id){
		super(id);
	}

	@Length(min=1, max=255, message="拥有人长度必须介于 1 和 255 之间")
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	@Length(min=1, max=255, message="拥有人名字长度必须介于 1 和 255 之间")
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	@Length(min=1, max=255, message="使用密码长度必须介于 1 和 255 之间")
	public String getUsePassword() {
		return usePassword;
	}

	public void setUsePassword(String usePassword) {
		this.usePassword = usePassword;
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
	
	@Length(min=0, max=255, message="seal_name长度必须介于 0 和 255 之间")
	public String getSealName() {
		return sealName;
	}

	public void setSealName(String sealName) {
		this.sealName = sealName;
	}
	
}