/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaProjector;
import com.thinkgem.jeesite.modules.oa.dao.OaProjectorDao;

/**
 * 投影仪管理Service
 * @author jhm
 * @version 2017-10-16
 */
@Service
@Transactional(readOnly = true)
public class OaProjectorService extends CrudService<OaProjectorDao, OaProjector> {

	public OaProjector get(String id) {
		return super.get(id);
	}
	
	public List<OaProjector> findList(OaProjector oaProjector) {
		return super.findList(oaProjector);
	}
	
	public Page<OaProjector> findPage(Page<OaProjector> page, OaProjector oaProjector) {
		return super.findPage(page, oaProjector);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProjector oaProjector) {
		super.save(oaProjector);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProjector oaProjector) {
		super.delete(oaProjector);
	}
	
}