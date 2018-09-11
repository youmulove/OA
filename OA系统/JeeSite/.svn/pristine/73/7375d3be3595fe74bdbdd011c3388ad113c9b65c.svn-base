/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.SysRank;
import com.thinkgem.jeesite.modules.sys.service.SysRankService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 职级Controller
 * 
 * @author wangyl
 * @version 2017-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysRank")
public class SysRankController extends BaseController {

	@Autowired
	private SysRankService sysRankService;
	@Autowired
	private SystemService systemService;

	@ModelAttribute
	public SysRank get(@RequestParam(required = false) String id) {
		SysRank entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = sysRankService.get(id);
		}
		if (entity == null) {
			entity = new SysRank();
		}
		return entity;
	}

	@RequiresPermissions("sys:sysRank:view")
	@RequestMapping(value = { "list", "" })
	public String list(SysRank sysRank, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SysRank> page = sysRankService.findPage(new Page<SysRank>(request,
				response), sysRank);
		model.addAttribute("page", page);
		return "modules/sys/sysRankList";
		/*
		 * model.addAttribute("list", sysRankService.findList(sysRank)); return
		 * "modules/sys/sysRankList";
		 */
	}

	@RequiresPermissions("sys:sysRank:view")
	@RequestMapping(value = "form")
	public String form(SysRank sysRank, Model model) {
		/*
		 * SysRank sysRank1 = new SysRank(sysRank.getId());
		 * System.out.println(sysRank1); if (sysRank.getCompany() == null ||
		 * sysRank.getCompany().getId() == null) { sysRank.setCompany(new
		 * SysRank(sysRank.getId()).getCompany()); } if (sysRank.getName() ==
		 * null) { sysRank.setName(new SysRank(sysRank.getId()).getName()); }
		 */
		model.addAttribute("sysRank", sysRank);
		return "modules/sys/sysRankForm";
	}

	@RequiresPermissions("sys:sysRank:edit")
	@RequestMapping(value = "save")
	public String save(SysRank sysRank, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysRank)) {
			return form(sysRank, model);
		}
		sysRankService.save(sysRank);
		addMessage(redirectAttributes, "保存职级成功");
		return "redirect:" + Global.getAdminPath() + "/sys/sysRank/?repage";
	}

	@RequiresPermissions("sys:sysRank:edit")
	@RequestMapping(value = "delete")
	public String delete(SysRank sysRank, RedirectAttributes redirectAttributes) {
		sysRankService.delete(sysRank);
		addMessage(redirectAttributes, "删除职级成功");
		return "redirect:" + Global.getAdminPath() + "/sys/sysRank/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "rankData")
	public List<Map<String, Object>> rankData(
			@RequestParam(required = false) String officeId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SysRank> list = systemService.findsysRankByOfficeId(officeId);
		for (int i = 0; i < list.size(); i++) {
			SysRank e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);

		}
		return mapList;
	}

}