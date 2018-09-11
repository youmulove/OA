/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaTravelRecord;
import com.thinkgem.jeesite.modules.oa.dao.OaTravelRecordDao;

/**
 * 我的行程Service
 * @author 行程
 * @version 2017-09-13
 */
@Service
@Transactional(readOnly = true)
public class OaTravelRecordService extends CrudService<OaTravelRecordDao, OaTravelRecord> {

	public OaTravelRecord get(String id) {
		return super.get(id);
	}
	
	public List<OaTravelRecord> findList(OaTravelRecord oaTravelRecord) {
		return super.findList(oaTravelRecord);
	}
	
	public Page<OaTravelRecord> findPage(Page<OaTravelRecord> page, OaTravelRecord oaTravelRecord) {
		return super.findPage(page, oaTravelRecord);
	}
	
	public Page<OaTravelRecord> findPageByself(Page<OaTravelRecord> page, OaTravelRecord oaTravelRecord) {
		return super.findPageByself(page, oaTravelRecord);
	}
	@Transactional(readOnly = false)
	public void save(OaTravelRecord oaTravelRecord) {
		super.save(oaTravelRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaTravelRecord oaTravelRecord) {
		super.delete(oaTravelRecord);
	}

	public Page<OaTravelRecord> findListOthers(Page<OaTravelRecord> page,
			OaTravelRecord oaTravelRecord) {
		// TODO Auto-generated method stub
		return super.findPageOthers(page, oaTravelRecord);
	}
	
}