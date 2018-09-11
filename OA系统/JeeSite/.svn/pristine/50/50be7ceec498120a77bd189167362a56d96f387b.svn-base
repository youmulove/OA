/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.List;

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

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaProjector;
import com.thinkgem.jeesite.modules.oa.service.OaProjectorService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 投影仪管理Controller
 * @author jhm
 * @version 2017-10-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProjector")
public class OaProjectorController extends BaseController {

	@Autowired
	private OaProjectorService oaProjectorService;
	
	@ModelAttribute
	public OaProjector get(@RequestParam(required=false) String id) {
		OaProjector entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProjectorService.get(id);
		}
		if (entity == null){
			entity = new OaProjector();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaProjector:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProjector oaProjector, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProjector> page = oaProjectorService.findPage(new Page<OaProjector>(request, response), oaProjector); 
		model.addAttribute("page", page);
		return "modules/oa/oaProjectorList";
	}

	@RequiresPermissions("oa:oaProjector:view")
	@RequestMapping(value = "form")
	public String form(OaProjector oaProjector, Model model) {
		model.addAttribute("oaProjector", oaProjector);
		return "modules/oa/oaProjectorForm";
	}

	@RequiresPermissions("oa:oaProjector:edit")
	@RequestMapping(value = "save")
	public String save(OaProjector oaProjector, Model model, RedirectAttributes redirectAttributes) {
		oaProjector.setYuliu1(UserUtils.getUser().getCompany().getId());
		if (!beanValidator(model, oaProjector)){
			return form(oaProjector, model);
		}
		oaProjectorService.save(oaProjector);
		addMessage(redirectAttributes, "保存投影仪管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProjector/?repage";
	}
	
	@RequiresPermissions("oa:oaProjector:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProjector oaProjector, RedirectAttributes redirectAttributes) {
		oaProjectorService.delete(oaProjector);
		addMessage(redirectAttributes, "删除投影仪管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProjector/?repage";
	}
	
	@RequestMapping("showAll")
	@ResponseBody
	public String showAll(){
		OaProjector projector = new OaProjector();
		projector.setYuliu1(UserUtils.getUser().getCompany().getId());
		projector.setDelFlag("0");
		List<OaProjector> list = oaProjectorService.findList(projector);
		return new Gson().toJson(list);
	}

}