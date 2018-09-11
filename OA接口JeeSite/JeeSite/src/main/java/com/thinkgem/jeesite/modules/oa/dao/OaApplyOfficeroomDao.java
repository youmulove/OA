/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;

/**
 * 办公室申请DAO接口
 * @author jhm
 * @version 2017-09-28
 */
@MyBatisDao
public interface OaApplyOfficeroomDao extends CrudDao<OaApplyOfficeroom> {
	public List<OaApplyOfficeroom> findAllRoom();
	
	public List<OaApplyOfficeroom> findList2(OaApplyOfficeroom oaApplyOfficeroom);
	public List<OaApplyOfficeroom> selectOaApplyOfficeroomByofficeroomId(OaApplyOfficeroom oaApplyOfficeroom);
	
	
	int deleteOaApplyOfficeroomByprocInsId(OaApplyOfficeroom oaApplyOfficeroom);
	
	OaApplyOfficeroom selectOaApplyOfficeroomByprocInsId(OaApplyOfficeroom oaApplyOfficeroom);
}