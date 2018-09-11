/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;

/**
 * 用章审批报表DAO接口
 * 
 * @author jhm
 * @version 2017-10-11
 */
@MyBatisDao
public interface OaApplyDao extends CrudDao<OaApply> {

}