/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeettingAccept;

/**
 * 主子表生成DAO接口
 * @author ThinkGem
 * @version 2017-09-13
 */
@MyBatisDao
public interface OaNotifyMeettingAcceptDao extends CrudDao<OaNotifyMeettingAccept> {

	/**
	 * 根据会议id删除记录
	 * @param oaMeetingId
	 * @return
	 */
	public int deleteByOaMeetingId(String oaMeetingId);
	/**
	 * 插入预约会议接受记录
	 * @param oaNotifyMeettingAcceptList
	 * @return
	 */
	public int insertAll(
			List<OaNotifyMeettingAccept> oaNotifyMeettingAcceptList);
	
}