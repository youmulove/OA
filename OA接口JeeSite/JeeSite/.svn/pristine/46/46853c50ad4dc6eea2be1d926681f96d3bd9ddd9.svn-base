/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTaskAccept;

/**
 * 新建任务DAO接口
 * 
 * @author 新建任务
 * @version 2017-09-14
 */
@MyBatisDao
public interface OaNotifyTaskAcceptDao extends CrudDao<OaNotifyTaskAccept> {
	/**
	 * 根据任务id删除记录
	 * 
	 * @param oaMeetingId
	 * @return
	 */
	public int deleteByOaTaskId(String oaTaskId);

	/**
	 * 插入任务接受记录
	 * 
	 * @param oaNotifyMeettingAcceptList
	 * @return
	 */
	public int insertAll(List<OaNotifyTaskAccept> oaNotifyTaskAcceptList);

}
