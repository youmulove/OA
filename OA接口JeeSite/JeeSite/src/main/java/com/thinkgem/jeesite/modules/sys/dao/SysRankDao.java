/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.SysRank;

/**
 * 职级DAO接口
 * 
 * @author wangyl
 * @version 2017-09-19
 */
@MyBatisDao
public interface SysRankDao extends CrudDao<SysRank> {

	List<SysRank> findsysRankByOfficeId(SysRank sysRank);

}