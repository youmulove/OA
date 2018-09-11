/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyMeetingDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyMeettingAcceptDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeettingAccept;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 主子表生成Service
 * 
 * @author ThinkGem
 * @version 2017-09-13
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyMeetingService extends
		CrudService<OaNotifyMeetingDao, OaNotifyMeeting> {

	@Autowired
	private OaNotifyMeettingAcceptDao oaNotifyMeettingAcceptDao;
	@Autowired
	private OaNotifyMeetingDao oaNotifyMeettingDao;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private UserDao userDao;

	public OaNotifyMeeting get(String id) {
		OaNotifyMeeting oaNotifyMeeting = super.get(id);
		oaNotifyMeeting.setOaNotifyMeettingAcceptList(oaNotifyMeettingAcceptDao
				.findList(new OaNotifyMeettingAccept(oaNotifyMeeting)));
		return oaNotifyMeeting;
	}

	public List<OaNotifyMeeting> findList(OaNotifyMeeting oaNotifyMeeting) {
		return super.findList(oaNotifyMeeting);
	}

	public Page<OaNotifyMeeting> findPage(Page<OaNotifyMeeting> page,
			OaNotifyMeeting oaNotifyMeeting) {
		/*
		 * oaNotifyMeeting.getSqlMap().put("dsf",
		 * dataScopeFilter(oaNotifyMeeting.getCurrentUser(), "o", "a"));
		 */
		oaNotifyMeeting.setPage(page);
		return super.findPage(page, oaNotifyMeeting);
	}

	public Page<OaNotifyMeeting> findNoRead(Page<OaNotifyMeeting> page,
			OaNotifyMeeting oaNotifyMeeting) {
		/*
		 * oaNotifyMeeting.getSqlMap().put("dsf",
		 * dataScopeFilter(oaNotifyMeeting.getCurrentUser(), "o", "a"));
		 */
		oaNotifyMeeting.setPage(page);
		return super.findNoRead(page, oaNotifyMeeting);
	}

	public Page<OaNotifyMeeting> findPageWithSendee(Page<OaNotifyMeeting> page,
			OaNotifyMeeting oaNotifyMeeting) {
		List<OaNotifyMeeting> oanotifyLists = new ArrayList<OaNotifyMeeting>();
		List<OaNotifyMeeting> meetingIds = oaNotifyMeettingDao
				.findList(oaNotifyMeeting);
		// List<User> userRoles = new ArrayList<User>();
		for (OaNotifyMeeting notifyMeeting : meetingIds) {
			oanotifyLists.add(this.get(notifyMeeting.getId()));
		}

		page.setList(oanotifyLists);
		return page;

	}

	// 获取含有预约会议接收人信息的列表

	public List<OaNotifyMeeting> findListWithAccept() {
		List<OaNotifyMeeting> oanotifyLists = new ArrayList<OaNotifyMeeting>();
		List<OaNotifyMeeting> userIds = oaNotifyMeettingDao.findMeetingId();
		for (OaNotifyMeeting oaNotifyMeeting : userIds) {
			oanotifyLists.add(this.get(oaNotifyMeeting.getId()));
		}
		return oanotifyLists;
	}

	// 保存预约会议信息及接收记录
	@Transactional(readOnly = false)
	public void save(OaNotifyMeeting oaNotifyMeeting) {
		// 保存预约会议表信息
		super.save(oaNotifyMeeting);
		// 更新发送人记录
		oaNotifyMeettingAcceptDao.deleteByOaMeetingId(oaNotifyMeeting.getId());
		int t = oaNotifyMeeting.getOaNotifyMeettingAcceptList().size();
		if (t > 0) {
			oaNotifyMeettingAcceptDao.insertAll(oaNotifyMeeting
					.getOaNotifyMeettingAcceptList());
		}

	}

	/*
	 * public void save(OaNotifyMeeting oaNotifyMeeting) {
	 * super.save(oaNotifyMeeting); for (OaNotifyMeettingAccept
	 * oaNotifyMeettingAccept :
	 * oaNotifyMeeting.getOaNotifyMeettingAcceptList()){ if
	 * (oaNotifyMeettingAccept.getId() == null){ continue; } if
	 * (OaNotifyMeettingAccept
	 * .DEL_FLAG_NORMAL.equals(oaNotifyMeettingAccept.getDelFlag())){ if
	 * (StringUtils.isBlank(oaNotifyMeettingAccept.getId())){
	 * //oaNotifyMeettingAccept.setMeetingId(oaNotifyMeeting);
	 * oaNotifyMeettingAccept.preInsert();
	 * oaNotifyMeettingAcceptDao.insert(oaNotifyMeettingAccept); }else{
	 * oaNotifyMeettingAccept.preUpdate();
	 * oaNotifyMeettingAcceptDao.update(oaNotifyMeettingAccept); } }else{
	 * oaNotifyMeettingAcceptDao.delete(oaNotifyMeettingAccept); } } }
	 */

	/*
	 * @Transactional(readOnly = false) public void delete(OaNotifyMeeting
	 * oaNotifyMeeting) { super.delete(oaNotifyMeeting);
	 * oaNotifyMeettingAcceptDao.delete(new
	 * OaNotifyMeettingAccept(oaNotifyMeeting)); }
	 */
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
			System.out.println(fir);
			return fir + office.getName();

		} else {
			return null;
		}
	}

	/*
	 * 获取预约会议发送记录
	 */
	public OaNotifyMeeting getMeetingRecordList(OaNotifyMeeting oaNotifyMeeting) {

		/*
		 * oaNotifyMeeting.setOaNotifyMeettingAcceptList(oaNotifyMeettingAcceptDao
		 * .findList(new OaNotifyMeettingAccept(oaNotifyMeeting)));
		 */
		List<OaNotifyMeettingAccept> findList = oaNotifyMeettingAcceptDao
				.findList(new OaNotifyMeettingAccept(oaNotifyMeeting));
		List<OaNotifyMeettingAccept> oaNotifyMeettingList = new ArrayList<OaNotifyMeettingAccept>();
		for (OaNotifyMeettingAccept oaNotifyMeettingAccept : findList) {
			User user = userDao.get1(oaNotifyMeettingAccept.getUser().getId());
			if (user != null) {
				oaNotifyMeettingAccept.setUser(user);
				oaNotifyMeettingAccept.setOfficeNames(this
						.getParentsNames(user));
				oaNotifyMeettingList.add(oaNotifyMeettingAccept);
			} else {
				continue;
			}
		}
		oaNotifyMeeting.setOaNotifyMeettingAcceptList(oaNotifyMeettingList);
		return oaNotifyMeeting;
	}

	/**
	 * 获取预约会议数目
	 * 
	 * @param oaNotifyMeeting
	 * @return
	 */
	public Long findCount(OaNotifyMeeting oaNotifyMeeting) {
		return dao.findCount(oaNotifyMeeting);
	}

	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotifyMeeting oaNotifyMeeting) {
		OaNotifyMeettingAccept oaNotifyMeettingAccept = new OaNotifyMeettingAccept(
				oaNotifyMeeting);
		oaNotifyMeettingAccept.setUser(oaNotifyMeettingAccept.getCurrentUser());
		oaNotifyMeettingAccept.setReadDate(new Date());
		oaNotifyMeettingAccept.setReadFlag("1");
		oaNotifyMeettingAcceptDao.update(oaNotifyMeettingAccept);
	}

	/**
	 * 
	 * @param page
	 * @param oaNotifyMeettingAccept
	 * @return 获得用户接收的会议记录数据
	 */
	public Page<OaNotifyMeettingAccept> findMeetingAccept(
			Page<OaNotifyMeettingAccept> page,
			OaNotifyMeettingAccept oaNotifyMeettingAccept) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		// user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(),
		// "o", "a"));
		// 设置分页参数
		oaNotifyMeettingAccept.setPage(page);
		// 执行分页查询
		page.setList(oaNotifyMeettingAcceptDao.findList(oaNotifyMeettingAccept));
		return page;
	}

	public Page<OaNotifyMeeting> find(Page<OaNotifyMeeting> page,
			OaNotifyMeeting oaNotifyMeeting) {
		oaNotifyMeeting.setPage(page);
		page.setList(dao.findList(oaNotifyMeeting));
		return page;
	}

}