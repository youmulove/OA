/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;

/**
 * 请假申请DAO接口
 * @author jhm
 * @version 2017-09-28
 */
@MyBatisDao
public interface OaLeavedDao extends CrudDao<OaLeaved> {
	
}