/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaMsgRecord;
import com.thinkgem.jeesite.modules.oa.dao.OaMsgRecordDao;

/**
 * 消息提醒Service
 * @author wangyl
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class OaMsgRecordService extends CrudService<OaMsgRecordDao, OaMsgRecord> {

	public OaMsgRecord get(String id) {
		return super.get(id);
	}
	
	public List<OaMsgRecord> findList(OaMsgRecord oaMsgRecord) {
		return super.findList(oaMsgRecord);
	}
	
	public Page<OaMsgRecord> findPage(Page<OaMsgRecord> page, OaMsgRecord oaMsgRecord) {
		return super.findPage(page, oaMsgRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(OaMsgRecord oaMsgRecord) {
		super.save(oaMsgRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaMsgRecord oaMsgRecord) {
		super.delete(oaMsgRecord);
	}
	
}