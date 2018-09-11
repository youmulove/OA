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
import com.thinkgem.jeesite.modules.oa.dao.OaNoticeDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNoticeRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotice;
import com.thinkgem.jeesite.modules.oa.entity.OaNoticeRecord;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 公告Service
 * 
 * @author jhm
 * @version 2017-10-10
 */
@Service
@Transactional(readOnly = true)
public class OaNoticeService extends CrudService<OaNoticeDao, OaNotice> {

	@Autowired
	private OaNoticeRecordDao oaNoticeRecordDao;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private UserDao userDao;

	/*
	 * public OaNotice get(String id) { OaNotice entity = dao.get(id); return
	 * entity; }
	 *//**
	 * 获取公告发送记录
	 * 
	 * @param OaNotice
	 * @return
	 */
	/*
	 * public OaNotice getRecordList(OaNotice oaNotice) {
	 * oaNotice.setOaNoticeRecordList(oaNoticeRecordDao .findList(new
	 * OaNoticeRecord(oaNotice))); return oaNotice; }
	 * 
	 * public Page<OaNotice> find(Page<OaNotice> page, OaNotice oaNotice) {
	 * oaNotice.setPage(page); page.setList(dao.findList(oaNotice)); return
	 * page; }
	 *//**
	 * 获取公告数目
	 * 
	 * @param OaNotice
	 * @return
	 */
	/*
	 * public Long findCount(OaNotice oaNotice) { return
	 * dao.findCount(oaNotice); }
	 * 
	 * @Transactional(readOnly = false) public void save(OaNotice oaNotice) {
	 * super.save(oaNotice);
	 * 
	 * // 更新发送接受人记录 oaNoticeRecordDao.deleteByOaNoticeId(oaNotice.getId()); if
	 * (oaNotice.getOaNoticeRecordList().size() > 0) {
	 * oaNoticeRecordDao.insertAll(oaNotice.getOaNoticeRecordList()); } }
	 *//**
	 * 更新阅读状态
	 */
	/*
	 * @Transactional(readOnly = false) public void updateReadFlag(OaNotice
	 * oaNotice) { OaNoticeRecord oaNoticeRecord = new OaNoticeRecord(oaNotice);
	 * oaNoticeRecord.setUser(oaNoticeRecord.getCurrentUser());
	 * oaNoticeRecord.setReadDate(new Date()); oaNoticeRecord.setReadFlag("1");
	 * oaNoticeRecordDao.update(oaNoticeRecord); }
	 */

	public OaNotice get(String id) {
		OaNotice entity = dao.get(id);
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
	 * @param OaNotice
	 * @return
	 */
	public OaNotice getRecordList(OaNotice OaNotice) {
		/*
		 * OaNotice.setOaNoticeRecordList(oaNoticeRecordDao .findList(new
		 * OaNoticeRecord(OaNotice)));
		 */
		List<OaNoticeRecord> findList = oaNoticeRecordDao
				.findList(new OaNoticeRecord(OaNotice));
		for (OaNoticeRecord oaNoticeRecord : findList) {
			User user = userDao.get1(oaNoticeRecord.getUser().getId());
			oaNoticeRecord.setUser(user);
			oaNoticeRecord.setOfficeNames(this.getParentsNames(user));
		}
		OaNotice.setOaNoticeRecordList(findList);
		return OaNotice;
	}

	public Page<OaNotice> find(Page<OaNotice> page, OaNotice OaNotice) {
		OaNotice.setPage(page);
		page.setList(dao.findList(OaNotice));
		return page;
	}

	public Page<OaNotice> findNoRead(Page<OaNotice> page, OaNotice OaNotice) {
		OaNotice.setPage(page);
		page.setList(dao.findNoRead(OaNotice));
		return page;
	}

	/**
	 * 获取通知数目
	 * 
	 * @param OaNotice
	 * @return
	 */
	public Long findCount(OaNotice OaNotice) {
		return dao.findCount(OaNotice);
	}

	@Transactional(readOnly = false)
	public void save(OaNotice OaNotice) {
		super.save(OaNotice);

		// 更新发送接受人记录
		oaNoticeRecordDao.deleteByOaNoticeId(OaNotice.getId());
		if (OaNotice.getOaNoticeRecordList().size() > 0) {
			oaNoticeRecordDao.insertAll(OaNotice.getOaNoticeRecordList());
		}
	}

	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotice OaNotice) {
		OaNoticeRecord OaNoticeRecord = new OaNoticeRecord(OaNotice);
		OaNoticeRecord.setUser(OaNoticeRecord.getCurrentUser());
		OaNoticeRecord.setReadDate(new Date());
		OaNoticeRecord.setReadFlag("1");
		oaNoticeRecordDao.update(OaNoticeRecord);
	}

}