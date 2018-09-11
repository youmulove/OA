/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 职级Entity
 * 
 * @author wangyl
 * @version 2017-09-19
 */
public class SysRank extends DataEntity<SysRank> {

	private static final long serialVersionUID = 1L;
	private Office company; // company
	private String name; // 职级

	public SysRank() {
		super();
	}

	public SysRank(String id) {
		super(id);
	}

	@JsonIgnore
	@NotNull(message = "归属公司不能为空")
	@ExcelField(title = "归属公司", align = 2, sort = 20)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	/*
	 * @JsonIgnore
	 * 
	 * @NotNull(message="归属部门不能为空")
	 * 
	 * @ExcelField(title="归属部门", align=2, sort=25) public Office getOffice() {
	 * return office; }
	 * 
	 * public void setOffice(Office office) { this.office = office; }
	 */

	@Length(min = 0, max = 100, message = "职级长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}