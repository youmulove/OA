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
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyTaskAcceptDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyTaskDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTask;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTaskAccept;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 新建任务Service
 * 
 * @author 新建任务
 * @version 2017-09-14
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyTaskService extends
		CrudService<OaNotifyTaskDao, OaNotifyTask> {

	@Autowired
	private OaNotifyTaskAcceptDao oaNotifyTaskAcceptDao;

	@Autowired
	private OaNotifyTaskDao oaNotifyTaskDao;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private UserDao userDao;

	public OaNotifyTask get(String id) {
		OaNotifyTask oaNotifyTask = super.get(id);
		oaNotifyTask.setOaNotifyTaskAcceptList(oaNotifyTaskAcceptDao
				.findList(new OaNotifyTaskAccept(oaNotifyTask)));
		return oaNotifyTask;
	}

	public List<OaNotifyTask> findList(OaNotifyTask oaNotifyTask) {
		return super.findList(oaNotifyTask);
	}

	public Page<OaNotifyTask> findPage(Page<OaNotifyTask> page,
			OaNotifyTask oaNotifyTask) {
		return super.findPage(page, oaNotifyTask);
	}

	// 获取含有任务接收人信息的列表

	public List<OaNotifyTask> findListWithAccept() {
		List<OaNotifyTask> oanotifyLists = new ArrayList<OaNotifyTask>();
		List<OaNotifyTask> userIds = oaNotifyTaskDao.findTaskId();
		for (OaNotifyTask oaNotifyTask : userIds) {
			oanotifyLists.add(this.get(oaNotifyTask.getId()));
		}
		return oanotifyLists;
	}

	// 保存任务信息及接收记录
	@Transactional(readOnly = false)
	public void save(OaNotifyTask oaNotifyTask) {
		// 保存任务表信息
		super.save(oaNotifyTask);
		// 更新发送人记录
		oaNotifyTaskAcceptDao.deleteByOaTaskId(oaNotifyTask.getId());
		int t = oaNotifyTask.getOaNotifyTaskAcceptList().size();
		if (t > 0) {
			oaNotifyTaskAcceptDao.insertAll(oaNotifyTask
					.getOaNotifyTaskAcceptList());
		}

	}

	/*
	 * @Transactional(readOnly = false) public void save(OaNotifyTask
	 * oaNotifyTask) { super.save(oaNotifyTask); for (OaNotifyTaskAccept
	 * oaNotifyTaskAccept : oaNotifyTask .getOaNotifyTaskAcceptList()) { if
	 * (oaNotifyTaskAccept.getId() == null) { continue; } if
	 * (OaNotifyTaskAccept.DEL_FLAG_NORMAL.equals(oaNotifyTaskAccept
	 * .getDelFlag())) { if (StringUtils.isBlank(oaNotifyTaskAccept.getId())) {
	 * oaNotifyTaskAccept.setTaskId(oaNotifyTask);
	 * oaNotifyTaskAccept.preInsert();
	 * oaNotifyTaskAcceptDao.insert(oaNotifyTaskAccept); } else {
	 * oaNotifyTaskAccept.preUpdate();
	 * oaNotifyTaskAcceptDao.update(oaNotifyTaskAccept); } } else {
	 * oaNotifyTaskAcceptDao.delete(oaNotifyTaskAccept); } } }
	 */

	/*
	 * @Transactional(readOnly = false) public void delete(OaNotifyTask
	 * oaNotifyTask) { super.delete(oaNotifyTask);
	 * oaNotifyTaskAcceptDao.delete(new OaNotifyTaskAccept(oaNotifyTask)); }
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
			return fir + office.getName();
		} else {
			return null;
		}

	}

	/*
	 * 获取任务发送记录
	 */
	public OaNotifyTask getTaskRecordList(OaNotifyTask oaNotifyTask) {
		/*
		 * oaNotifyTask.setOaNotifyTaskAcceptList(oaNotifyTaskAcceptDao
		 * .findList(new OaNotifyTaskAccept(oaNotifyTask)));
		 */
		List<OaNotifyTaskAccept> findList = oaNotifyTaskAcceptDao
				.findList(new OaNotifyTaskAccept(oaNotifyTask));
		List<OaNotifyTaskAccept> oaNotifyTaskList = new ArrayList<OaNotifyTaskAccept>();
		for (OaNotifyTaskAccept oaNotifyTaskAccept : findList) {
			User user = userDao.get1(oaNotifyTaskAccept.getUser().getId());
			if (user != null) {
				oaNotifyTaskAccept.setUser(user);
				oaNotifyTaskAccept.setOfficeNames(this.getParentsNames(user));
				oaNotifyTaskList.add(oaNotifyTaskAccept);
			} else {
				continue;
			}
		}
		oaNotifyTask.setOaNotifyTaskAcceptList(oaNotifyTaskList);
		return oaNotifyTask;
	}

	/**
	 * 获取任务数目
	 * 
	 * @param oaNotifyMeeting
	 * @return
	 */
	public Long findCount(OaNotifyTask oaNotifyTask) {
		return dao.findCount(oaNotifyTask);
	}

	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotifyTask oaNotifyTask) {
		OaNotifyTaskAccept oaNotifyTaskAccept = new OaNotifyTaskAccept(
				oaNotifyTask);
		oaNotifyTaskAccept.setUser(oaNotifyTaskAccept.getCurrentUser());
		oaNotifyTaskAccept.setReadDate(new Date());
		oaNotifyTaskAccept.setReadFlag("1");
		oaNotifyTaskAcceptDao.update(oaNotifyTaskAccept);
	}

	/**
	 * 
	 * @param page
	 * @param oaNotifyMeettingAccept
	 * @return 获得用户接收的任务数据
	 */
	public Page<OaNotifyTaskAccept> findTaskAccept(
			Page<OaNotifyTaskAccept> page, OaNotifyTaskAccept oaNotifyTaskAccept) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		// user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(),
		// "o", "a"));
		// 设置分页参数
		oaNotifyTaskAccept.setPage(page);
		// 执行分页查询
		page.setList(oaNotifyTaskAcceptDao.findList(oaNotifyTaskAccept));
		return page;
	}

	public Page<OaNotifyTask> find(Page<OaNotifyTask> page,
			OaNotifyTask oaNotifyTask) {
		oaNotifyTask.setPage(page);
		page.setList(dao.findList(oaNotifyTask));
		return page;
	}

	public Page<OaNotifyTask> findNoRead(Page<OaNotifyTask> page,
			OaNotifyTask oaNotifyTask) {
		oaNotifyTask.setPage(page);
		page.setList(dao.findNoRead(oaNotifyTask));
		return page;
	}

}
