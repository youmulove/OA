/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public List<Office> findAll() {
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll) {
		if (isAll != null && isAll) {
			return UserUtils.getOfficeAllList();
		} else {
			return UserUtils.getOfficeList();
		}
	}

	public List<Office> findList1(Boolean isAll) {
		if (isAll != null && isAll) {
			return UserUtils.getOfficeAllList();
		} else {
			return UserUtils.getOfficeList1();
		}
	}

	@Transactional(readOnly = true)
	public List<Office> findList(Office office) {
		if (office != null) {
			office.setParentIds(office.getParentIds() + "%");
			return dao.findByParentIdsLike(office);
		}
		return new ArrayList<Office>();
	}

	@Transactional(readOnly = true)
	public List<Office> findByParentIds(Office office) {
		if (office != null) {
			return dao.findByParentIdsLike(office);
		}
		return new ArrayList<Office>();
	}

	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_ALL_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_ALL_LIST);
	}

	@Transactional(readOnly = false)
	public List<Office> getChilidOffices(Office office) {
		if (office != null) {
			return dao.getChilidOffices(office);
		}
		return new ArrayList<Office>();
	}
	@Transactional(readOnly = false)
	public List<Office> getChilidOfficesByParentIds(Office office) {
		if (office != null) {
			return dao.getChilidOfficesByParentIds(office);
		}
		return new ArrayList<Office>();
	}

	@Transactional(readOnly = true)
	public Office getOfficeByName(String name) {
		if (name != null) {
			return dao.getOfficeByName(name);
		}
		return new Office();
	}

	@Transactional(readOnly = true)
	public List<Office> getChilidOffice(Office office) {
		if (office != null) {
			return dao.getChilidOffice(office);
		}
		return new ArrayList<Office>();
	}

	@Transactional(readOnly = true)
	public List<Office> getOfficeByNames(String name) {
		if (name != null) {
			return dao.getOfficeByNames(name);
		}
		return new ArrayList<Office>();
	}

	public Office findAllDeptByUser(User user) {
		return dao.findAllDeptByUser(user);

	}
}
