/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公章使用记录Entity
 * @author lisp
 * @version 2017-11-02
 */
public class OaSealRecord extends DataEntity<OaSealRecord> {
	
	private static final long serialVersionUID = 1L;
	private String oaSealId;		// 公章id
	private String oaDispatchId;		// 使用事项（公文发文单id）
	private String yuliu1;		// yuliu1
	private String yuliu2;		// yuliu2
	private String yuliu3;		// yuliu3
	private String yuliu4;		// yuliu4
	private String yuliu5;		// yuliu5
	
	private OaSeal oaSeal;
	private OaDispatch oaDispatch;
	
	public OaDispatch getOaDispatch() {
		return oaDispatch;
	}

	public void setOaDispatch(OaDispatch oaDispatch) {
		this.oaDispatch = oaDispatch;
	}

	public OaSeal getOaSeal() {
		return oaSeal;
	}

	public void setOaSeal(OaSeal oaSeal) {
		this.oaSeal = oaSeal;
	}

	public OaSealRecord() {
		super();
	}

	public OaSealRecord(String id){
		super(id);
	}

	@Length(min=1, max=255, message="公章id长度必须介于 1 和 255 之间")
	public String getOaSealId() {
		return oaSealId;
	}

	public void setOaSealId(String oaSealId) {
		this.oaSealId = oaSealId;
	}
	
	@Length(min=1, max=255, message="使用事项（公文发文单id）长度必须介于 1 和 255 之间")
	public String getOaDispatchId() {
		return oaDispatchId;
	}

	public void setOaDispatchId(String oaDispatchId) {
		this.oaDispatchId = oaDispatchId;
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