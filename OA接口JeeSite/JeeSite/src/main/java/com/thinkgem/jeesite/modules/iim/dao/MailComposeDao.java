/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.iim.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.iim.entity.MailCompose;

/**
 * 发件箱DAO接口
 * 
 * @author jeeplus
 * @version 2015-11-15
 */
@MyBatisDao
public interface MailComposeDao extends CrudDao<MailCompose> {
	public int getCount(MailCompose entity);
}