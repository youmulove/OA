/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 机构DAO接口
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<Office> findDept(User user);

	public List<Office> getChilidOffices(Office office);
	 
	public List<Office> getChilidOfficesByParentIds(Office office);

	public Office getOfficeByName(String name);

	public List<Office> getChilidOffice(Office office);

	public List<Office> getOfficeByNames(String name);

	public Office findAllDeptByUser(User user);

	public Office getOfficeByCompany(String name);
}
