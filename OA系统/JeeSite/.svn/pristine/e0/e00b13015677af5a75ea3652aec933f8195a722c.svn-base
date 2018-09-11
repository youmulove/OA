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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaSealRecord;
import com.thinkgem.jeesite.modules.oa.service.OaSealRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 公章使用记录Controller
 * @author lisp
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaSealRecord")
public class OaSealRecordController extends BaseController {

	@Autowired
	private OaSealRecordService oaSealRecordService;
	
	@ModelAttribute
	public OaSealRecord get(@RequestParam(required=false) String id) {
		OaSealRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaSealRecordService.get(id);
		}
		if (entity == null){
			entity = new OaSealRecord();
		}
		return entity;
	}
	
	//@RequiresPermissions("oa:oaSealRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaSealRecord oaSealRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaSealRecord> page = oaSealRecordService.findPage(new Page<OaSealRecord>(request, response), oaSealRecord); 
		for (OaSealRecord record : page.getList()) {
			record.setCreateBy(UserUtils.get(record.getCreateBy().getId()));
		}
		model.addAttribute("page", page);
		return "modules/oa/document/oaSealRecordList";
	}

	//@RequiresPermissions("oa:oaSealRecord:view")
	@RequestMapping(value = "form")
	public String form(OaSealRecord oaSealRecord, Model model) {
		oaSealRecord.setCreateBy(UserUtils.get(oaSealRecord.getCreateBy().getId()));
		model.addAttribute("oaSealRecord", oaSealRecord);
		return "modules/oa/document/oaSealRecordForm";
	}

	@RequiresPermissions("oa:oaSealRecord:edit")
	@RequestMapping(value = "save")
	public String save(OaSealRecord oaSealRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaSealRecord)){
			return form(oaSealRecord, model);
		}
		oaSealRecordService.save(oaSealRecord);
		addMessage(redirectAttributes, "保存公章使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaSealRecord/?repage";
	}
	
	//@RequiresPermissions("oa:oaSealRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(OaSealRecord oaSealRecord, RedirectAttributes redirectAttributes) {
		oaSealRecordService.delete(oaSealRecord);
		addMessage(redirectAttributes, "删除公章使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaSealRecord/?repage";
	}

}