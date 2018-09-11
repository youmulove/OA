/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaSealRecord;
import com.thinkgem.jeesite.modules.oa.dao.OaSealRecordDao;

/**
 * 公章使用记录Service
 * @author lisp
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class OaSealRecordService extends CrudService<OaSealRecordDao, OaSealRecord> {
	
	@Autowired
	private OaSealRecordDao oaSealRecordDao;
	
	public OaSealRecord get(String id) {
		return super.get(id);
	}
	
	public List<OaSealRecord> getBySealId(String sealId) {
		return oaSealRecordDao.getBySealId(sealId);
	}
	
	public List<OaSealRecord> getByDiapatchId(String dispatchId) {
		return oaSealRecordDao.getByDispatchId(dispatchId);
	}
	
	public List<OaSealRecord> findList(OaSealRecord oaSealRecord) {
		return super.findList(oaSealRecord);
	}
	
	public Page<OaSealRecord> findPage(Page<OaSealRecord> page, OaSealRecord oaSealRecord) {
		return super.findPage(page, oaSealRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(OaSealRecord oaSealRecord) {
		super.save(oaSealRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaSealRecord oaSealRecord) {
		super.delete(oaSealRecord);
	}
	
	
}