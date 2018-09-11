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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.service.OaApplyService;

/**
 * 通用审批报表Controller
 * @author jhm
 * @version 2017-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaApply")
public class OaApplyController extends BaseController {

	@Autowired
	private OaApplyService oaApplyService;
	
	@ModelAttribute
	public OaApply get(@RequestParam(required=false) String id) {
		OaApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaApplyService.get(id);
		}
		if (entity == null){
			entity = new OaApply();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaApply oaApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaApply> page = oaApplyService.findPage(new Page<OaApply>(request, response), oaApply); 
		model.addAttribute("page", page);
		return "modules/oa/oaApplyList";
	}

	@RequiresPermissions("oa:oaApply:view")
	@RequestMapping(value = "form")
	public String form(OaApply oaApply, Model model) {
		model.addAttribute("oaApply", oaApply);
		return "modules/oa/oaApplyForm";
	}

	@RequiresPermissions("oa:oaApply:edit")
	@RequestMapping(value = "save")
	public String save(OaApply oaApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaApply)){
			return form(oaApply, model);
		}
		oaApplyService.save(oaApply);
		addMessage(redirectAttributes, "保存通用审批成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaApply/?repage";
	}
	
	@RequiresPermissions("oa:oaApply:edit")
	@RequestMapping(value = "delete")
	public String delete(OaApply oaApply, RedirectAttributes redirectAttributes) {
		oaApplyService.delete(oaApply);
		addMessage(redirectAttributes, "删除通用审批成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaApply/?repage";
	}
	@RequiresPermissions("oa:oaApply:view")
	@RequestMapping(value = "reportList")
	public String reportList(OaApply oaApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaApply> page = oaApplyService.findWithNames(new Page<OaApply>(request, response), oaApply); 
		model.addAttribute("page", page);
		return "modules/oa/oaApplyReport";
	}
	//通用申请报表导出
		@RequiresPermissions("oa:oaApply:view")
		@RequestMapping(value = "export", method=RequestMethod.POST)
		public String exportFile(OaApply oaApply, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			System.out.println("导出通用申请报表开始");
			try {
		        String fileName ="通用申请报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		        Page<OaApply> page = oaApplyService.findWithNames(new Page<OaApply>(request, response, -1), oaApply);
		        System.out.println("page"+page);
				new ExportExcel("通用申请报表", OaApply.class).setDataList(page.getList()).writeSelectBrower(response,request,fileName).dispose();
				return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出通用申请报表失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/oa/oaApplyReport?repage";
		}

}