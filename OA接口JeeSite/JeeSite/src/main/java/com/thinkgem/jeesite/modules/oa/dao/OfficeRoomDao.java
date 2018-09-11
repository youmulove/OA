/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;

/**
 * 会议室DAO接口
 * @author lisp
 * @version 2017-09-22
 */
@MyBatisDao
public interface OfficeRoomDao extends CrudDao<OfficeRoom> {
	
	public List<OfficeRoom> findAllRoom(OfficeRoom officeRoom);
	

	
}