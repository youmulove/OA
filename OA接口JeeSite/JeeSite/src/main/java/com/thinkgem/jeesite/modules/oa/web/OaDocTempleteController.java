/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaDocTemplete;
import com.thinkgem.jeesite.modules.oa.service.OaDocTempleteService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 公文模板Controller
 * @author lisp
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaDocTemplete")
public class OaDocTempleteController extends BaseController {

	@Autowired
	private OaDocTempleteService oaDocTempleteService;
	
	@ModelAttribute
	public OaDocTemplete get(@RequestParam(required=false) String id) {
		OaDocTemplete entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaDocTempleteService.get(id);
		}
		if (entity == null){
			entity = new OaDocTemplete();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaDocTemplete:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaDocTemplete oaDocTemplete, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaDocTemplete> page = oaDocTempleteService.findPage(new Page<OaDocTemplete>(request, response), oaDocTemplete); 
		for (OaDocTemplete tem : page.getList()) {
			tem.setCreateBy(UserUtils.get(tem.getCreateBy().getId()));
			if(tem.getFilePath()!=null && !("".equals(tem.getFilePath()))){
				tem.setFilePath(tem.getFilePath().replace("|", ""));
			}
		}
		model.addAttribute("page", page);
		return "modules/oa/document/oaDocTempleteList";
	}

	@RequiresPermissions("oa:oaDocTemplete:view")
	@RequestMapping(value = "form")
	public String form(OaDocTemplete oaDocTemplete, Model model) {
		model.addAttribute("oaDocTemplete", oaDocTemplete);
		return "modules/oa/document/oaDocTempleteForm";
	}

	@RequiresPermissions("oa:oaDocTemplete:edit")
	@RequestMapping(value = "save")
	public String save(OaDocTemplete oaDocTemplete, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaDocTemplete)){
			return form(oaDocTemplete, model);
		}
		oaDocTemplete.setCompanyId(UserUtils.getUser().getCompany().getId());
		oaDocTempleteService.save(oaDocTemplete);
		addMessage(redirectAttributes, "保存公文模板成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaDocTemplete/?repage";
	}
	
	@RequiresPermissions("oa:oaDocTemplete:edit")
	@RequestMapping(value = "delete")
	public String delete(OaDocTemplete oaDocTemplete, RedirectAttributes redirectAttributes) {
		oaDocTempleteService.delete(oaDocTemplete);
		addMessage(redirectAttributes, "删除公文模板成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaDocTemplete/?repage";
	}

}