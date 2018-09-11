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
import com.thinkgem.jeesite.modules.oa.entity.OaMsgRecord;
import com.thinkgem.jeesite.modules.oa.service.OaMsgRecordService;

/**
 * 消息提醒Controller
 * @author wangyl
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaMsgRecord")
public class OaMsgRecordController extends BaseController {

	@Autowired
	private OaMsgRecordService oaMsgRecordService;
	
	@ModelAttribute
	public OaMsgRecord get(@RequestParam(required=false) String id) {
		OaMsgRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaMsgRecordService.get(id);
		}
		if (entity == null){
			entity = new OaMsgRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaMsgRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaMsgRecord oaMsgRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaMsgRecord> page = oaMsgRecordService.findPage(new Page<OaMsgRecord>(request, response), oaMsgRecord); 
		model.addAttribute("page", page);
		return "modules/oa/oaMsgRecordList";
	}

	@RequiresPermissions("oa:oaMsgRecord:view")
	@RequestMapping(value = "form")
	public String form(OaMsgRecord oaMsgRecord, Model model) {
		model.addAttribute("oaMsgRecord", oaMsgRecord);
		return "modules/oa/oaMsgRecordForm";
	}

	@RequiresPermissions("oa:oaMsgRecord:edit")
	@RequestMapping(value = "save")
	public String save(OaMsgRecord oaMsgRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaMsgRecord)){
			return form(oaMsgRecord, model);
		}
		oaMsgRecordService.save(oaMsgRecord);
		addMessage(redirectAttributes, "保存消息提醒成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaMsgRecord/?repage";
	}
	
	@RequiresPermissions("oa:oaMsgRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(OaMsgRecord oaMsgRecord, RedirectAttributes redirectAttributes) {
		oaMsgRecordService.delete(oaMsgRecord);
		addMessage(redirectAttributes, "删除消息提醒成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaMsgRecord/?repage";
	}

}