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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.oa.service.OaOvertimeService;

/**
 * 加班申请Controller
 * @author JHM
 * @version 2017-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaOvertime")
public class OaOvertimeController extends BaseController {

	@Autowired
	private OaOvertimeService oaOvertimeService;
	
	@ModelAttribute
	public OaOvertime get(@RequestParam(required=false) String id) {
		OaOvertime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaOvertimeService.get(id);
		}
		if (entity == null){
			entity = new OaOvertime();
		}
		return entity;
	}
	@RequiresPermissions("oa:oaApplyProjector:view")
	@RequestMapping(value = {"report"})
	public String reportList(OaOvertime oaOvertime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaOvertime> page = oaOvertimeService.findWithNames(new Page<OaOvertime>(request, response), oaOvertime); 
//   List<OaOvertime> list = page.getList();
//	    for(OaOvertime o :list){
//	    	String firState =o.getAuditFirState();
//   //	String secState =o.getAuditSecState();
//	  //  	String thrState = o.getAuditThrState();
//	   // 	String fouState = o.getAuditFouState();
//	    	if(firState==null||"".equals(firState)){
//	    		o.setAuditFirState("待审核");
//	    	}else if("y".equals(firState)){
//	    		o.setAuditFirState("通过");
//	    	}else if("n".equals(firState)){
//	    		o.setAuditFirState("未通过");
//	    	}
//	    	
//	    	
//	    }
		model.addAttribute("page", page);
		return "modules/oa/overTimeReport";
	}

	
	@RequiresPermissions("oa:oaOvertime:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaOvertime oaOvertime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaOvertime> page = oaOvertimeService.findPage(new Page<OaOvertime>(request, response), oaOvertime); 
		model.addAttribute("page", page);
		return "modules/oa/oaOvertimeList";
	}

	@RequiresPermissions("oa:oaOvertime:view")
	@RequestMapping(value = "form")
	public String form(OaOvertime oaOvertime, Model model) {
		model.addAttribute("oaOvertime", oaOvertime);
		return "modules/oa/oaOvertimeForm";
	}

	@RequiresPermissions("oa:oaOvertime:edit")
	@RequestMapping(value = "save")
	public String save(OaOvertime oaOvertime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaOvertime)){
			return form(oaOvertime, model);
		}
		oaOvertimeService.save(oaOvertime);
		addMessage(redirectAttributes, "保存加班申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaOvertime/?repage";
	}
	
	@RequiresPermissions("oa:oaOvertime:edit")
	@RequestMapping(value = "delete")
	public String delete(OaOvertime oaOvertime, RedirectAttributes redirectAttributes) {
		oaOvertimeService.delete(oaOvertime);
		addMessage(redirectAttributes, "删除加班申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaOvertime/?repage";
	}

	
	@RequiresPermissions("oa:oaOvertime:view")
	@RequestMapping(value = "export", method=RequestMethod.POST)
	public String exportFile(OaOvertime oaOvertime, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
	        String fileName ="加班申请表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	        Page<OaOvertime> page = oaOvertimeService.findWithNames(new Page<OaOvertime>(request, response, -1), oaOvertime);
	        System.out.println("page"+page);
			new ExportExcel("加班申请表", OaOvertime.class).setDataList(page.getList()).writeSelectBrower(response,request,fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出加班申请报表失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/overTimeReport?repage";
	}

}