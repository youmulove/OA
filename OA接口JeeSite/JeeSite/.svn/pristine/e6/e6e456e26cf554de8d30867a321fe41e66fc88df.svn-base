/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.service.OaLeavedService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 请假申请Controller
 * @author jhm
 * @version 2017-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaLeaved")
public class OaLeavedController extends BaseController {

	@Autowired
	private OaLeavedService oaLeavedService;
	
	@ModelAttribute
	public OaLeaved get(@RequestParam(required=false) String id) {
		OaLeaved entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaLeavedService.get(id);
		}
		if (entity == null){
			entity = new OaLeaved();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaLeaved:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaLeaved oaLeaved, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaLeaved> page = oaLeavedService.findPage(new Page<OaLeaved>(request, response), oaLeaved); 
		model.addAttribute("page", page);
		return "modules/oa/oaLeavedList";
	}

	@RequiresPermissions("oa:oaLeaved:view")
	@RequestMapping(value = {"report"})
	public String reportList(OaLeaved oaLeaved, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaLeaved> page = oaLeavedService.findWithNames(new Page<OaLeaved>(request, response), oaLeaved); 
		model.addAttribute("page", page);
		return "modules/oa/leaveReport";
	}
	
	@RequiresPermissions("oa:oaLeaved:view")
	@RequestMapping(value = "form")
	public String form(OaLeaved oaLeaved, Model model) {
		model.addAttribute("oaLeaved", oaLeaved);
		return "modules/oa/oaLeavedForm";
	}

	@RequiresPermissions("oa:oaLeaved:edit")
	@RequestMapping(value = "save")
	public String save(OaLeaved oaLeaved, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaLeaved)){
			return form(oaLeaved, model);
		}
		oaLeavedService.save(oaLeaved);
		addMessage(redirectAttributes, "保存请假申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaLeaved/?repage";
	}
	
	@RequiresPermissions("oa:oaLeaved:edit")
	@RequestMapping(value = "delete")
	public String delete(OaLeaved oaLeaved, RedirectAttributes redirectAttributes) {
		oaLeavedService.delete(oaLeaved);
		addMessage(redirectAttributes, "删除请假申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaLeaved/?repage";
	}
	
	//请假报表导出
	@RequiresPermissions("oa:oaLeaved:view")
	@RequestMapping(value = "export", method=RequestMethod.POST)
	public String exportFile(OaLeaved oaLeaved, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
	    
	       
	        String fileName ="请假报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	        Page<OaLeaved> page = oaLeavedService.findWithNames(new Page<OaLeaved>(request, response, -1), oaLeaved);
	        System.out.println("page"+page);
			new ExportExcel("请假报表", OaLeaved.class).setDataList(page.getList()).writeSelectBrower(response,request,fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出请假报表失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/leaveReport?repage";
	}
	
}