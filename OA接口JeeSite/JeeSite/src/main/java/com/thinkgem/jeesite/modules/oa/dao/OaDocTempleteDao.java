/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaDocTemplete;

/**
 * 公文模板DAO接口
 * @author lisp
 * @version 2017-11-02
 */
@MyBatisDao
public interface OaDocTempleteDao extends CrudDao<OaDocTemplete> {
	
}