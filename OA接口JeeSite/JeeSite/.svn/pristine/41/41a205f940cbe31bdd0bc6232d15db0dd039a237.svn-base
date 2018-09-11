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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaTravelRecord;
import com.thinkgem.jeesite.modules.oa.service.OaTravelRecordService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 我的行程Controller
 * 
 * @author 行程
 * @version 2017-09-13
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaTravelRecord")
public class OaTravelRecordController extends BaseController {

	@Autowired
	private OaTravelRecordService oaTravelRecordService;

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

	@RequiresPermissions("oa:oaTravelRecord:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaTravelRecord oaTravelRecord,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		//System.out.println("当前用户所属公司id"+UserUtils.getUser().getCompany().getId());
		oaTravelRecord.setCompanyId(UserUtils.getUser().getCompany().getId());	
		System.out.println("cunchuzhihou当前用户所属公司id"+oaTravelRecord.getCompanyId());
		Page<OaTravelRecord> page = oaTravelRecordService.findPage(
				new Page<OaTravelRecord>(request, response), oaTravelRecord);
		model.addAttribute("page", page);
		return "modules/oa/oaTravelRecordList";
	}

	@RequiresPermissions("oa:oaTravelRecord:view")
	@RequestMapping(value = "form")
	public String form(OaTravelRecord oaTravelRecord, Model model) {
		String userNo = UserUtils.getUser().getNo();
		model.addAttribute("userNo", userNo);
		System.out.println("用户ID" + userNo);
		model.addAttribute("oaTravelRecord", oaTravelRecord);
		return "modules/oa/oaTravelRecordForm";
	}

	@RequiresPermissions("oa:oaTravelRecord:edit")
	@RequestMapping(value = "save")
	public String save(OaTravelRecord oaTravelRecord, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaTravelRecord)) {
			return form(oaTravelRecord, model);
		}
		oaTravelRecord.setCompanyId(UserUtils.getUser().getCompany().getId());	
		//System.out.println("save---当前用户所属公司id"+oaTravelRecord.getCompanyId());
		System.out.println("员工工号" + oaTravelRecord.getUser().getNo());
		oaTravelRecordService.save(oaTravelRecord);
		addMessage(redirectAttributes, "保存我的行程成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaTravelRecord/?repage";
	}

	@RequiresPermissions("oa:oaTravelRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(OaTravelRecord oaTravelRecord,
			RedirectAttributes redirectAttributes) {
		oaTravelRecordService.delete(oaTravelRecord);
		addMessage(redirectAttributes, "删除我的行程成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaTravelRecord/?repage";
	}

	/**
	 * 我的行程记录
	 */
	@RequestMapping(value = "self")
	public String selfList(OaTravelRecord oaTravelRecord,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		oaTravelRecord.setSelf(true);
		System.out.println("只查询自己的行程记录");
		Page<OaTravelRecord> page = oaTravelRecordService.findPageByself(
				new Page<OaTravelRecord>(request, response), oaTravelRecord);
		model.addAttribute("page", page);
		return "modules/oa/oaTravelRecordList";
	}

	/**
	 * 
	 * @param oaTravelRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return 查询其他人的的行程记录
	 */
	@RequestMapping(value = "others")
	public String othersList(OaTravelRecord oaTravelRecord,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		System.out.println("只查询其他人的的行程记录");
		oaTravelRecord.setCompanyId(UserUtils.getUser().getCompany().getId());	
		Page<OaTravelRecord> page = oaTravelRecordService.findListOthers(
				new Page<OaTravelRecord>(request, response), oaTravelRecord);
		model.addAttribute("page", page);
		return "modules/oa/oaTravelRecordList";
	}

}