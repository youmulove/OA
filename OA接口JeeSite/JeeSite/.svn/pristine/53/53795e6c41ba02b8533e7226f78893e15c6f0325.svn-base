/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaNotice;
import com.thinkgem.jeesite.modules.oa.service.OaNoticeService;

/**
 * 公告Controller
 * 
 * @author jhm
 * @version 2017-10-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaNotice")
public class OaNoticeController extends BaseController {

	@Autowired
	private OaNoticeService oaNoticeService;

	/*
	 * @ModelAttribute public OaNotice get(@RequestParam(required = false)
	 * String id) { OaNotice entity = null; if (StringUtils.isNotBlank(id)) {
	 * entity = oaNoticeService.get(id); } if (entity == null) { entity = new
	 * OaNotice(); } return entity; }
	 * 
	 * @RequiresPermissions("oa:oaNotice:view")
	 * 
	 * @RequestMapping(value = { "list", "" }) public String list(OaNotice
	 * oaNotice, HttpServletRequest request, HttpServletResponse response, Model
	 * model) { Page<OaNotice> page = oaNoticeService.findPage(new
	 * Page<OaNotice>( request, response), oaNotice); model.addAttribute("page",
	 * page); return "modules/oa/oaNoticeList"; }
	 * 
	 * @RequiresPermissions("oa:oaNotice:view")
	 * 
	 * @RequestMapping(value = "form") public String form(OaNotice oaNotice,
	 * Model model) { model.addAttribute("oaNotice", oaNotice); return
	 * "modules/oa/oaNoticeForm"; }
	 * 
	 * @RequiresPermissions("oa:oaNotice:edit")
	 * 
	 * @RequestMapping(value = "save") public String save(OaNotice oaNotice,
	 * Model model, RedirectAttributes redirectAttributes) { if
	 * (!beanValidator(model, oaNotice)) { return form(oaNotice, model); }
	 * oaNoticeService.save(oaNotice); addMessage(redirectAttributes, "保存公告成功");
	 * return "redirect:" + Global.getAdminPath() + "/oa/oaNotice/?repage"; }
	 * 
	 * @RequiresPermissions("oa:oaNotice:edit")
	 * 
	 * @RequestMapping(value = "delete") public String delete(OaNotice oaNotice,
	 * RedirectAttributes redirectAttributes) {
	 * oaNoticeService.delete(oaNotice); addMessage(redirectAttributes,
	 * "删除公告成功"); return "redirect:" + Global.getAdminPath() +
	 * "/oa/oaNotice/?repage"; }
	 */

	/**
	 * 我的公告列表
	 */
	/*
	 * @RequestMapping(value = "self") public String selfList(OaNotice oaNotice,
	 * HttpServletRequest request, HttpServletResponse response, Model model) {
	 * oaNotice.setSelf(true); Page<OaNotice> page = oaNoticeService.find(new
	 * Page<OaNotice>(request, response), oaNotice); model.addAttribute("page",
	 * page); return "modules/oa/oaNoticeList"; }
	 *//**
	 * 我的公告列表-数据
	 */
	/*
	 * @RequiresPermissions("oa:oaNotice:view")
	 * 
	 * @RequestMapping(value = "selfData")
	 * 
	 * @ResponseBody public Page<OaNotice> listData(OaNotice oaNotice,
	 * HttpServletRequest request, HttpServletResponse response, Model model) {
	 * oaNotice.setSelf(true); Page<OaNotice> page = oaNoticeService.find(new
	 * Page<OaNotice>(request, response), oaNotice); return page; }
	 *//**
	 * 查看我的公告
	 */
	/*
	 * @RequestMapping(value = "view") public String view(OaNotice oaNotice,
	 * Model model) { if (StringUtils.isNotBlank(oaNotice.getId())) {
	 * oaNoticeService.updateReadFlag(oaNotice); oaNotice =
	 * oaNoticeService.getRecordList(oaNotice); model.addAttribute("oaNotice",
	 * oaNotice); return "modules/oa/oaNoticeForm"; } return "redirect:" +
	 * adminPath + "/oa/oaNotice/self?repage"; }
	 *//**
	 * 查看我的公告-数据
	 */
	/*
	 * @RequestMapping(value = "viewData")
	 * 
	 * @ResponseBody public OaNotice viewData(OaNotice oaNotice, Model model) {
	 * if (StringUtils.isNotBlank(oaNotice.getId())) {
	 * oaNoticeService.updateReadFlag(oaNotice); return oaNotice; } return null;
	 * }
	 *//**
	 * 查看我的公告-发送记录
	 */
	/*
	 * @RequestMapping(value = "viewRecordData")
	 * 
	 * @ResponseBody public OaNotice viewRecordData(OaNotice oaNotice, Model
	 * model) { if (StringUtils.isNotBlank(oaNotice.getId())) { oaNotice =
	 * oaNoticeService.getRecordList(oaNotice); return oaNotice; } return null;
	 * }
	 *//**
	 * 获取我的通知数目
	 */
	/*
	 * @RequestMapping(value = "self/count")
	 * 
	 * @ResponseBody public String selfCount(OaNotice oaNotice, Model model) {
	 * oaNotice.setSelf(true); oaNotice.setReadFlag("0"); return
	 * String.valueOf(oaNoticeService.findCount(oaNotice)); }
	 */

	@ModelAttribute
	public OaNotice get(@RequestParam(required = false) String id) {
		OaNotice entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaNoticeService.get(id);
		}
		if (entity == null) {
			entity = new OaNotice();
		}
		return entity;
	}

	@RequiresPermissions("oa:OaNotice:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaNotice oaNotice, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<OaNotice> page = oaNoticeService.find(new Page<OaNotice>(request,
				response), oaNotice);
		model.addAttribute("page", page);
		return "modules/oa/oaNoticeList";
	}

	@RequiresPermissions("oa:OaNotice:view")
	@RequestMapping(value = "form")
	public String form(OaNotice oaNotice, Model model, String isSelf) {
		if (StringUtils.isNotBlank(oaNotice.getId())) {
			oaNotice = oaNoticeService.getRecordList(oaNotice);
		}
		model.addAttribute("OaNotice", oaNotice);
		model.addAttribute("isSelf", isSelf);
		return "modules/oa/oaNoticeForm";
	}

	@RequiresPermissions("oa:OaNotice:edit")
	@RequestMapping(value = "save")
	public String save(OaNotice oaNotice, Model model,
			RedirectAttributes redirectAttributes, String isSelf) {
		if (!beanValidator(model, oaNotice)) {
			return form(oaNotice, model, isSelf);
		}
		// 如果是修改，则状态为已发布，则不能再进行操作
		if (StringUtils.isNotBlank(oaNotice.getId())) {
			OaNotice e = oaNoticeService.get(oaNotice.getId());
			if ("1".equals(e.getStatus())) {
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/oa/oaNotice/form?id="
						+ oaNotice.getId();
			}
		}
		oaNoticeService.save(oaNotice);
		addMessage(redirectAttributes, "保存公告'" + oaNotice.getTitle() + "'成功");
		return "redirect:" + adminPath + "/oa/oaNotice/?repage";
	}

	@RequiresPermissions("oa:OaNotice:edit")
	@RequestMapping(value = "delete")
	public String delete(OaNotice oaNotice,
			RedirectAttributes redirectAttributes) {
		oaNoticeService.delete(oaNotice);
		addMessage(redirectAttributes, "删除公告成功");
		return "redirect:" + adminPath + "/oa/oaNotice/?repage";
	}

	/**
	 * 我的公告列表
	 */
	@RequestMapping(value = "self")
	public String selfList(OaNotice oaNotice, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		oaNotice.setSelf(true);
		Page<OaNotice> page = oaNoticeService.find(new Page<OaNotice>(request,
				response), oaNotice);
		model.addAttribute("page", page);
		model.addAttribute("isSelf", "self");
		return "modules/oa/oaNoticeList";
	}

	/**
	 * 我的公告列表-数据
	 */
	@RequiresPermissions("oa:OaNotice:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<OaNotice> listData(OaNotice oaNotice,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		oaNotice.setSelf(true);
		Page<OaNotice> page = oaNoticeService.find(new Page<OaNotice>(request,
				response), oaNotice);
		return page;
	}

	/**
	 * 查看我的公告
	 */
	@RequestMapping(value = "view")
	public String view(OaNotice oaNotice, Model model, String isSelf) {
		if (StringUtils.isNotBlank(oaNotice.getId())) {
			oaNoticeService.updateReadFlag(oaNotice);
			oaNotice = oaNoticeService.getRecordList(oaNotice);
			model.addAttribute("OaNotice", oaNotice);
			model.addAttribute("isSelf", isSelf);
			return "modules/oa/oaNoticeForm";
		}
		return "redirect:" + adminPath + "/oa/OaNotice/self?repage";
	}

	/**
	 * 查看我的公告-数据
	 */
	@RequestMapping(value = "viewData")
	@ResponseBody
	public OaNotice viewData(OaNotice oaNotice, Model model) {
		if (StringUtils.isNotBlank(oaNotice.getId())) {
			oaNoticeService.updateReadFlag(oaNotice);
			return oaNotice;
		}
		return null;
	}

	/**
	 * 查看我的公告-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public OaNotice viewRecordData(OaNotice oaNotice, Model model) {
		if (StringUtils.isNotBlank(oaNotice.getId())) {
			oaNotice = oaNoticeService.getRecordList(oaNotice);
			return oaNotice;
		}
		return null;
	}

	/**
	 * 获取我的公告数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(OaNotice oaNotice, Model model) {
		oaNotice.setSelf(true);
		oaNotice.setReadFlag("0");
		return String.valueOf(oaNoticeService.findCount(oaNotice));
	}

	/**
	 * 未读
	 */
	@RequestMapping(value = "oaNoticeList")
	@ResponseBody
	public String oaNoticeList(OaNotice oaNotice, HttpServletRequest request,
			HttpServletResponse response, String time) {
		Long time1 = Long.parseLong(time);
		long time2 = (long) time1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		oaNotice.setSelf(true);
		Page<OaNotice> page = oaNoticeService.findNoRead(new Page<OaNotice>(
				request, response), oaNotice);
		ArrayList<OaNotice> list = new ArrayList<OaNotice>();
		for (OaNotice oaNotice2 : page.getList()) {
			if (oaNotice2.getUpdateDate().getTime() > time2) {
				list.add(oaNotice2);
			}
		}
		map.put("list", list);
		return new Gson().toJson(map);
	}

}