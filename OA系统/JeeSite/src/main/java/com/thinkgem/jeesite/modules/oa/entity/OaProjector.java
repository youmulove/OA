/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 投影仪管理Entity
 * @author jhm
 * @version 2017-10-16
 */
public class OaProjector extends DataEntity<OaProjector> {
	
	private static final long serialVersionUID = 1L;
	private String projectorName;		// 投影仪名称
	private String projectoroNo;		// 投影仪编号
	private String describe;		// 描述
	private String yuliu1;		// yuliu1所在公司的id
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// yuliu5
	
	public OaProjector() {
		super();
	}

	public OaProjector(String id){
		super(id);
	}

	@Length(min=0, max=50, message="投影仪名称长度必须介于 0 和 50 之间")
	public String getProjectorName() {
		return projectorName;
	}

	public void setProjectorName(String projectorName) {
		this.projectorName = projectorName;
	}
	
	@Length(min=0, max=50, message="投影仪编号长度必须介于 0 和 50 之间")
	public String getProjectoroNo() {
		return projectoroNo;
	}

	public void setProjectoroNo(String projectoroNo) {
		this.projectoroNo = projectoroNo;
	}
	
	@Length(min=0, max=150, message="描述长度必须介于 0 和 150 之间")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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