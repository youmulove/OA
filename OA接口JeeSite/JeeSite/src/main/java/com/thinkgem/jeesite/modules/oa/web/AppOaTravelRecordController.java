/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.dao.OaTravelRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaTravelRecord;
import com.thinkgem.jeesite.modules.oa.service.OaTravelRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 我的行程Controller
 * 
 * @author 行程
 * @version 2017-09-13
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/oaTravelRecord")
public class AppOaTravelRecordController extends BaseController {

	@Autowired
	private OaTravelRecordService oaTravelRecordService;
	@Autowired
	private OaTravelRecordDao oaTravelRecordDao;

	@ModelAttribute
	public OaTravelRecord get(@RequestParam(required = false) String id) {
		OaTravelRecord entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaTravelRecordService.get(id);
		}
		if (entity == null) {
			entity = new OaTravelRecord();
		}
		return entity;
	}

	// 查看所有行程
	@RequestMapping(value = "list")
	@ResponseBody
	public JsonResult<Map<String, Object>> list(HttpServletRequest request,
			HttpServletResponse response, OaTravelRecord oaTravelRecord) {
		oaTravelRecord.setCompanyId(UserUtils.getUser().getCompany().getId());
		List<OaTravelRecord> list = oaTravelRecordDao.findList(oaTravelRecord);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", list);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"查看所有行程成功", map);
	}

	/**
	 * 新建我的行程
	 * 
	 * @param oaTravelRecord
	 *            id user.id name startTime [2017-09-12 08:45:51] endTime
	 *            describe
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public JsonResult<Map<String, Object>> save(OaTravelRecord oaTravelRecord,
			Model model) {
		oaTravelRecord.setUser(UserUtils.getUser());
		if (!beanValidator(model, oaTravelRecord)) {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"数据格式不正确", null);
		}
		oaTravelRecord.setCompanyId(UserUtils.getUser().getCompany().getId());
		oaTravelRecord.setName(UserUtils.getUser().getName());
		oaTravelRecordService.save(oaTravelRecord);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "新建成功",
				null);
	}

	/**
	 * @param oaTravelRecord
	 *            name
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "self")
	@ResponseBody
	public JsonResult<Map<String, Object>> selfList(
			OaTravelRecord oaTravelRecord, HttpServletRequest request,
			HttpServletResponse response) {
		// oaTravelRecord.setName(UserUtils.getUser().getName());
		oaTravelRecord.setSelf(true);
		List<OaTravelRecord> list = oaTravelRecordDao
				.findListByself(oaTravelRecord);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", list);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"查看我的行程成功", map);
	}

	/**
	 * @param oaTravelRecord
	 *            currentUser.id 目前此接口的sql语句编写有误
	 * @return
	 */
	@RequestMapping(value = "others")
	@ResponseBody
	public JsonResult<Map<String, Object>> othersList(
			OaTravelRecord oaTravelRecord) {
		try {
			// Page<OaTravelRecord>
			// page=oaTravelRecordService.findListOthers(new
			// Page<OaTravelRecord>(), oaTravelRecord);

			List<OaTravelRecord> list = oaTravelRecordDao
					.findListOthers(oaTravelRecord);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("page", list);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"查看成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"查看失败", null);
		}
	}

	/**
	 * 搜索他人的行程
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "searchOnesTravel")
	@ResponseBody
	public JsonResult<Map<String, Object>> searchOnesTravel(String name) {
		try {
			OaTravelRecord oa = new OaTravelRecord();
			oa.setName(name);
			List<OaTravelRecord> oat = oaTravelRecordDao.findSomeOne(oa);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("OaTravelRecord", oat);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR, "查询失败",null);
		}
	}

	/**
	 * 搜索他人的行程
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "searchOnesTravelById")
	@ResponseBody
	public JsonResult<Map<String, Object>> searchOnesTravelById(String Id) {
		try {
			OaTravelRecord oa = new OaTravelRecord();
			User user = new User();
			user.setId(Id);
			oa.setUser(user);
			List<OaTravelRecord> oat = oaTravelRecordDao.findSomeOne(oa);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("OaTravelRecord", oat);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR, "查询失败",null);
		}
	}

}