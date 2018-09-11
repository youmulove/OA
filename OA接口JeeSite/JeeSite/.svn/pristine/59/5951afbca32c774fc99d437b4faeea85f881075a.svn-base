/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.SysRankDao;
import com.thinkgem.jeesite.modules.sys.entity.SysRank;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 职级Service
 * 
 * @author wangyl
 * @version 2017-09-19
 */
@Service
@Transactional(readOnly = true)
public class SysRankService extends CrudService<SysRankDao, SysRank> {

	public SysRank get(String id) {
		return super.get(id);
	}

	public List<SysRank> findAll() {
		return UserUtils.getsysrankAllList();
	}

	public List<SysRank> findList(SysRank sysRank) {
		return super.findList(sysRank);
	}

	public Page<SysRank> findPage(Page<SysRank> page, SysRank sysRank) {
		sysRank.getSqlMap().put("dsf",
				dataScopeFilter(sysRank.getCurrentUser(), "o", "a"));
		return super.findPage(page, sysRank);
	}

	@Transactional(readOnly = false)
	public void save(SysRank sysRank) {
		super.save(sysRank);
	}

	@Transactional(readOnly = false)
	public void delete(SysRank sysRank) {
		super.delete(sysRank);
	}

}