/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaMsgRecord;

/**
 * 消息提醒DAO接口
 * @author wangyl
 * @version 2018-01-12
 */
@MyBatisDao
public interface OaMsgRecordDao extends CrudDao<OaMsgRecord> {
	
	
	List<OaMsgRecord> findListBycreateUserId(OaMsgRecord oaMsgRecord);
	
	List<OaMsgRecord> MsgByCreateUserIdGroupByMsgId(OaMsgRecord oaMsgRecord);
	
	int  CountBycreateUserIdMsgId(OaMsgRecord oaMsgRecord);
}