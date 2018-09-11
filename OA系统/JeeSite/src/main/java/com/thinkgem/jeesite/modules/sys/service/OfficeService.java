/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
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

	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = false)
	public List<Office> findAll() {
		return UserUtils.getOfficeList();
	}

	@Transactional(readOnly = false)
	public List<Office> findList(Boolean isAll) {
		if (isAll != null && isAll) {
			return UserUtils.getOfficeAllList();
		} else {
			return UserUtils.getOfficeList();
		}
	}

	@Transactional(readOnly = false)
	public List<Office> findList1(Boolean isAll) {
		if (isAll != null && isAll) {
			return UserUtils.getOfficeAllList();
		} else {
			return UserUtils.getOfficeList1();
		}
	}

	@Transactional(readOnly = false)
	public List<Office> findList(Office office) {
		if (office != null) {
			office.setParentIds(office.getParentIds() + "%");
			return dao.findByParentIdsLike(office);
		}
		return new ArrayList<Office>();
	}

	@Transactional(readOnly = false)
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
	public Office getChilids(Office office) {
		if (office != null) {
			return dao.getChilids(office);
		}
		return office;
	}

	public int getCount1(Office office) {
		if (office != null) {
			User user = new User();
			user.setOffice(office);
			List<User> findUserByOfficeId = userDao.findUserByOfficeId(user);
			return findUserByOfficeId.size();
		}
		return 0;
	}

	public int getCount(Office office) {
		if (office != null) {
			int count1 = this.getCount1(office);
			if (office.getParentId().equals("0")) {
				count1 = count1 - 1;
			}
			String chilidOfficesIds = this.getChilidOfficesIds(office);
			if (chilidOfficesIds != null && !("").equals(chilidOfficesIds)) {
				List<Office> chilidOffices = this.getChilidOffices(office);
				if (chilidOffices.size() > 0) {
					for (Office office2 : chilidOffices) {
						int count2 = this.getCount1(office2);
						count1 += count2;
					}
				}
			}
			return count1;
		}
		return 0;

	}

	@Transactional(readOnly = false)
	public Office getOfficeByName(String name) {
		if (name != null) {
			return dao.getOfficeByName(name);
		}
		return new Office();
	}

	@Transactional(readOnly = false)
	public List<Office> getChilidOffice(Office office) {
		if (office != null) {
			return dao.getChilidOffice(office);
		}
		return new ArrayList<Office>();
	}

	@Transactional(readOnly = false)
	public List<Office> getOfficeByNames(String name) {
		if (name != null) {
			return dao.getOfficeByNames(name);
		}
		return new ArrayList<Office>();
	}

	public Office findAllDeptByUser(User user) {
		return dao.findAllDeptByUser(user);

	}

	public String getChilidOfficesIds(Office office) {
		return officeDao.getChilidOfficesIds(office);
	}
}
