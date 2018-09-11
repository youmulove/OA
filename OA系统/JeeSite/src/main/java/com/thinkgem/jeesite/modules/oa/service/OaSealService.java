/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaSeal;
import com.thinkgem.jeesite.modules.oa.dao.OaSealDao;

/**
 * 公章Service
 * @author lisp
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class OaSealService extends CrudService<OaSealDao, OaSeal> {

	public OaSeal get(String id) {
		return super.get(id);
	}
	
	public List<OaSeal> findList(OaSeal oaSeal) {
		return super.findList(oaSeal);
	}
	
	public Page<OaSeal> findPage(Page<OaSeal> page, OaSeal oaSeal) {
		return super.findPage(page, oaSeal);
	}
	
	@Transactional(readOnly = false)
	public void save(OaSeal oaSeal) {
		super.save(oaSeal);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaSeal oaSeal) {
		super.delete(oaSeal);
	}
	
}