/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaDocTemplete;
import com.thinkgem.jeesite.modules.oa.dao.OaDocTempleteDao;

/**
 * 公文模板Service
 * @author lisp
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class OaDocTempleteService extends CrudService<OaDocTempleteDao, OaDocTemplete> {

	public OaDocTemplete get(String id) {
		return super.get(id);
	}
	
	public List<OaDocTemplete> findList(OaDocTemplete oaDocTemplete) {
		return super.findList(oaDocTemplete);
	}
	
	public Page<OaDocTemplete> findPage(Page<OaDocTemplete> page, OaDocTemplete oaDocTemplete) {
		return super.findPage(page, oaDocTemplete);
	}
	
	@Transactional(readOnly = false)
	public void save(OaDocTemplete oaDocTemplete) {
		super.save(oaDocTemplete);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaDocTemplete oaDocTemplete) {
		super.delete(oaDocTemplete);
	}
	
}