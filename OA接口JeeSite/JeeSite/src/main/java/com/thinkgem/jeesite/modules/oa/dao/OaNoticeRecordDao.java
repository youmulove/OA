/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNoticeRecord;

/**
 * 公告DAO接口
 * 
 * @author jhm
 * @version 2017-10-10
 */
@MyBatisDao
public interface OaNoticeRecordDao extends CrudDao<OaNoticeRecord> {
	/**
	 * 插入公告记录
	 * 
	 * @param oaNotifyRecordList
	 * @return
	 */
	public int insertAll(List<OaNoticeRecord> OaNoticeRecordList);

	/**
	 * 根据通知ID删除公告记录
	 * 
	 * @param oaNotifyId
	 * 
	 * @return
	 */
	public int deleteByOaNoticeId(String oaNoticeId);

}