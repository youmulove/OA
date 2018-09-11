/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;

/**
 * 加班申请DAO接口
 * @author JHM
 * @version 2017-09-28
 */
@MyBatisDao
public interface OaOvertimeDao extends CrudDao<OaOvertime> {
	
}