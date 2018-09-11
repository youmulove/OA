/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaSealRecord;

/**
 * 公章使用记录DAO接口
 * @author lisp
 * @version 2017-11-02
 */
@MyBatisDao
public interface OaSealRecordDao extends CrudDao<OaSealRecord> {
	
	public List<OaSealRecord> getBySealId(String sealId);
	
	public List<OaSealRecord> getByDispatchId(String dispatchId);		
	
}