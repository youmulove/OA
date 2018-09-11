/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.security.shiro.cache.JedisCacheManager;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Controller
 * 
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/sys/office")
public class AppOfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;

	@Autowired
	private SystemService systemService;
	@Autowired
	private UserDao userDao;
    @Autowired
    private JedisCacheManager jedisCacheManager;
	@ModelAttribute("office")
	public Office get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return officeService.get(id);
		} else {
			return new Office();
		}
	}

	// 查看某公司以及公司下面的二级部门
	@RequestMapping(value = { "SystemOfficeView" })
	@ResponseBody
	public JsonResult<Map<String, Object>> SystemOfficeView(Office office,
			Model model) {
		List<Office> listOfficeAll = new ArrayList<Office>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (UserUtils.getUser().getCompany() == null) {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"该用户没有所在公司或者未登陆", map);
		}
		List<Office> listOffice2 = officeService.getChilidOffice(UserUtils
				.getUser().getCompany());
		for (Office office11 : listOffice2) {
			listOfficeAll.add(office11);
		}

		map.put("listOfficeAll", listOfficeAll);
		map.put("company", UserUtils.getUser().getCompany());
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "请求成功",
				map);
	}
    
	@RequestMapping(value = "SearchUserByOffice")
	@ResponseBody
	public JsonResult<Map<String, Object>> SearchUserByOffice(Model model,
			String officeId) {
		Office office = new Office();
		office.setId(officeId);
	    Office office2=officeService.get(officeId);
		String parentIds=office2.getParentIds()+office2.getId()+",";
		Office office3=new Office();
		office3.setParentIds(parentIds);
		List<Office> listOffice = officeService.getChilidOfficesByParentIds(office3);
		List<User> listUser = new ArrayList<User>();
		User userNew = new User();
		userNew.setOffice(office);
		List<User> Listuser2 = userDao.findUserByOfficeId(userNew);
		for (User user : Listuser2) {
			listUser.add(user);
		}
		for (Office office1 : listOffice) {
			List<User> Listuser1 = systemService.findUserByOfficeId(office1.getId());
			for (User user1 : Listuser1) {
				listUser.add(user1);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listUser", listUser);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "请求成功",
				map);
	}

	@RequestMapping("/loginfailer")
	@ResponseBody
	public JsonResult<Map<String,Object>> ExceptionH() {
		return new JsonResult<Map<String,Object>>("1","认证失败",null);
	}

}
