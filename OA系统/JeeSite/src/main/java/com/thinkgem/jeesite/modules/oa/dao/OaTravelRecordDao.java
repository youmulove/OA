/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaTravelRecord;

/**
 * 我的行程DAO接口
 * @author 行程
 * @version 2017-09-13
 */
@MyBatisDao
public interface OaTravelRecordDao extends CrudDao<OaTravelRecord> {
	
	 List<OaTravelRecord>   findSomeOne(OaTravelRecord oaTravelRecord);
	
}