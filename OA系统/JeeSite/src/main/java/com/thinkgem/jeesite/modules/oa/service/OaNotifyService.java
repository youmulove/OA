/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 通知通告Service
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyService extends CrudService<OaNotifyDao, OaNotify> {

	@Autowired
	private OaNotifyRecordDao oaNotifyRecordDao;
	@Autowired
	private OaNotifyDao oaNotifyDao;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private UserDao userDao;

	public OaNotify get(String id) {
		OaNotify entity = dao.get(id);
		return entity;
	}

	/**
	 * 查询用户所在部门的所有父部门的名称
	 */
	public String getParentsNames(User user) {
		Office office = user.getOffice();
		if (office != null) {
			String parentIdString = office.getParentIds();
			String[] firSplit = parentIdString.split(",");
			String fir = "";
			if (firSplit.length == 1) {
				System.out.println(office.getName());
				return office.getName();
			}
			for (int i = 2; i < firSplit.length; i++) {
				Office office1 = officeDao.get(firSplit[i].toString());
				fir += office1.getName() + "---";
			}
			return fir + office.getName();
		} else {
			return null;
		}

	}

	/**
	 * 获取通知发送记录
	 * 
	 * @param oaNotify
	 * @return
	 */
	public OaNotify getRecordList(OaNotify oaNotify) {
		// oaNotify.setOaNotifyRecordList(oaNotifyRecordDao.findList(new
		// OaNotifyRecord(oaNotify)));
		List<OaNotifyRecord> findList = oaNotifyRecordDao
				.findList(new OaNotifyRecord(oaNotify));
		for (OaNotifyRecord oaNotifyRecord : findList) {
			User user = userDao.get1(oaNotifyRecord.getUser().getId());
			oaNotifyRecord.setUser(user);
			oaNotifyRecord.setOfficeNames(this.getParentsNames(user));
		}
		oaNotify.setOaNotifyRecordList(findList);
		return oaNotify;
	}

	public Page<OaNotify> find(Page<OaNotify> page, OaNotify oaNotify) {
		oaNotify.setPage(page);
		page.setList(dao.findList(oaNotify));
		return page;
	}

	public Page<OaNotify> findNoRead(Page<OaNotify> page, OaNotify oaNotify) {
		oaNotify.setPage(page);
		page.setList(dao.findNoRead(oaNotify));
		return page;
	}

	public Page<OaNotify> find2(Page<OaNotify> page, OaNotify oaNotify) {
		oaNotify.setPage(page);
		List<OaNotify> list = oaNotifyDao.findList2(oaNotify);
		for (OaNotify oaNotify2 : list) {
			if (oaNotify2.getFiles() != null
					&& !("".equals(oaNotify2.getFiles()))) {
				oaNotify2.setFiles(oaNotify2.getFiles().replace("|", ""));
			}
		}
		page.setList(list);
		return page;
	}

	/**
	 * 获取通知数目
	 * 
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify) {
		return dao.findCount(oaNotify);
	}

	@Transactional(readOnly = false)
	public void save(OaNotify oaNotify) {
		super.save(oaNotify);

		// 更新发送接受人记录
		oaNotifyRecordDao.deleteByOaNotifyId(oaNotify.getId());
		if (oaNotify.getOaNotifyRecordList().size() > 0) {
			oaNotifyRecordDao.insertAll(oaNotify.getOaNotifyRecordList());
		}
	}

	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotify oaNotify) {
		OaNotifyRecord oaNotifyRecord = new OaNotifyRecord(oaNotify);
		oaNotifyRecord.setUser(oaNotifyRecord.getCurrentUser());
		oaNotifyRecord.setReadDate(new Date());
		oaNotifyRecord.setReadFlag("1");
		oaNotifyRecordDao.update(oaNotifyRecord);
	}
}