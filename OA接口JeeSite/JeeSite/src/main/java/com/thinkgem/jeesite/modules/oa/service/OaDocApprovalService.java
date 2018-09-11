/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaDocApproval;
import com.thinkgem.jeesite.modules.oa.dao.OaDocApprovalDao;

/**
 * 公文审批记录Service
 * @author lisp
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class OaDocApprovalService extends CrudService<OaDocApprovalDao, OaDocApproval> {
	
	@Autowired
	private OaDocApprovalDao oaDocApprovalDao;
	
	public OaDocApproval get(String id) {
		return super.get(id);
	}
	
	public List<OaDocApproval> findList(OaDocApproval oaDocApproval) {
		return super.findList(oaDocApproval);
	}
	
	public Page<OaDocApproval> findPage(Page<OaDocApproval> page, OaDocApproval oaDocApproval) {
		return super.findPage(page, oaDocApproval);
	}
	
	@Transactional(readOnly = false)
	public void save(OaDocApproval oaDocApproval) {
		super.save(oaDocApproval);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaDocApproval oaDocApproval) {
		super.delete(oaDocApproval);
	}
	
	@Transactional(readOnly = false)
	public OaDocApproval getByDispatchId(String oaDispatchId){
		return oaDocApprovalDao.getByDispatchId(oaDispatchId);
	}
	
}