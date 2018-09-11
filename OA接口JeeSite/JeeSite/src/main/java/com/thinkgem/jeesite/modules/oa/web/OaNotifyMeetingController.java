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
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyMeetingService;

/**
 * 主子表生成Controller
 * 
 * @author ThinkGem
 * @version 2017-09-13
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaNotifyMeeting")
public class OaNotifyMeetingController extends BaseController {

	@Autowired
	private OaNotifyMeetingService oaNotifyMeetingService;

	@ModelAttribute
	public OaNotifyMeeting get(@RequestParam(required = false) String id) {
		OaNotifyMeeting entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaNotifyMeetingService.get(id);
		}
		if (entity == null) {
			entity = new OaNotifyMeeting();
		}
		return entity;
	}

	@RequiresPermissions("oa:oaNotifyMeeting:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaNotifyMeeting oaNotifyMeeting,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<OaNotifyMeeting> page = oaNotifyMeetingService.findPage(
				new Page<OaNotifyMeeting>(request, response), oaNotifyMeeting);
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyMeetingList";
	}

	@RequiresPermissions("oa:oaNotifyMeeting:view")
	@RequestMapping(value = "form")
	public String form(OaNotifyMeeting oaNotifyMeeting, Model model) {

		if (StringUtils.isNoneBlank(oaNotifyMeeting.getId())) {
			oaNotifyMeeting = oaNotifyMeetingService
					.getMeetingRecordList(oaNotifyMeeting);
			System.out.println("oanotifymeeting1" + oaNotifyMeeting);
		}
		oaNotifyMeetingService.updateReadFlag(oaNotifyMeeting);
		System.out.println("oanotifymeeting2" + oaNotifyMeeting);
		model.addAttribute("oaNotifyMeeting", oaNotifyMeeting);
		return "modules/oa/oaNotifyMeetingForm";
	}

	/*
	 * 保存数据
	 */

	@RequiresPermissions("oa:oaNotifyMeeting:edit")
	@RequestMapping(value = "save")
	public String save(OaNotifyMeeting oaNotifyMeeting, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("会议ID" + oaNotifyMeeting.getId());
		if (!beanValidator(model, oaNotifyMeeting)) {
			return form(oaNotifyMeeting, model);
		}
		// 如果是修改，则状态为发布时，则不能在进行操作
		if (StringUtils.isNotBlank(oaNotifyMeeting.getId())) {
			OaNotifyMeeting m = oaNotifyMeetingService.get(oaNotifyMeeting
					.getId());
			if ("1".equals(m.getStatus())) {
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/oa/oaNotifyMeeting/form?id="
						+ oaNotifyMeeting.getId();
			}
		}
		oaNotifyMeetingService.save(oaNotifyMeeting);
		System.out.println("会议ID2" + oaNotifyMeeting.getId());
		addMessage(redirectAttributes, "保存会议'" + oaNotifyMeeting.getTitle()
				+ "'成功");
		return "redirect:" + adminPath + "/oa/oaNotifyMeeting/?repage";
	}

	/**
	 * 
	 * @param oaNotifyMeeting
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @RequiresPermissions("oa:oaNotifyMeeting:edit")
	 * @RequestMapping(value = "save") public String save(OaNotifyMeeting
	 *                       oaNotifyMeeting, Model model, RedirectAttributes
	 *                       redirectAttributes) { if (!beanValidator(model,
	 *                       oaNotifyMeeting)){ return form(oaNotifyMeeting,
	 *                       model); }
	 *                       oaNotifyMeetingService.save(oaNotifyMeeting);
	 *                       addMessage(redirectAttributes, "保存主子表成功"); return
	 *                       "redirect:"+Global.getAdminPath()+
	 *                       "/oa/oaNotifyMeeting/?repage"; }
	 */
	@RequiresPermissions("oa:oaNotifyMeeting:edit")
	@RequestMapping(value = "delete")
	public String delete(OaNotifyMeeting oaNotifyMeeting,
			RedirectAttributes redirectAttributes) {
		oaNotifyMeetingService.delete(oaNotifyMeeting);
		addMessage(redirectAttributes, "删除会议成功");
		return "redirect:" + adminPath + "/oa/oaNotifyMeeting/?repage";
	}

	@RequestMapping(value = "self")
	public String selfList(OaNotifyMeeting oaNotifyMeeting,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		oaNotifyMeeting.setSelf(true);
		Page<OaNotifyMeeting> page = oaNotifyMeetingService.findPage(
				new Page<OaNotifyMeeting>(request, response), oaNotifyMeeting);
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyMeetingList";
	}

	/**
	 * 查看我的预约会议
	 */
	@RequestMapping(value = "view")
	public String view(OaNotifyMeeting oaNotifyMeeting, Model model) {
		if (StringUtils.isNotBlank(oaNotifyMeeting.getId())) {
			oaNotifyMeetingService.updateReadFlag(oaNotifyMeeting);
			oaNotifyMeeting = oaNotifyMeetingService
					.getMeetingRecordList(oaNotifyMeeting);
			System.out.println("oanotifymeeting3" + oaNotifyMeeting);
			model.addAttribute("oaNotifyMeeting", oaNotifyMeeting);
			return "modules/oa/oaNotifyMeetingForm";
		}
		System.out.println("oanotifymeeting4" + oaNotifyMeeting);
		return "redirect:" + adminPath + "/oa/oaNotifyMeeting/self?repage";
	}

	/**
	 * 查看我的预约会议-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public OaNotifyMeeting viewRecordData(OaNotifyMeeting oaNotifyMeeting,
			Model model) {
		if (StringUtils.isNotBlank(oaNotifyMeeting.getId())) {
			oaNotifyMeeting = oaNotifyMeetingService
					.getMeetingRecordList(oaNotifyMeeting);
			return oaNotifyMeeting;
		}
		return null;
	}

	/**
	 * 获取我的预约会议数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(OaNotifyMeeting oaNotifyMeeting, Model model) {
		oaNotifyMeeting.setSelf(true);
		oaNotifyMeeting.setReadFlag("0");
		return String
				.valueOf(oaNotifyMeetingService.findCount(oaNotifyMeeting));
	}

	/**
	 * 未读预约会议
	 */
	@RequestMapping(value = "oaNotifyMeetingList")
	@ResponseBody
	public String oaNotifyMeetingList(OaNotifyMeeting oaNotifyMeeting,
			HttpServletRequest request, HttpServletResponse response,
			String time) {
		Long time1 = Long.parseLong(time);
		long time2 = (long) time1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		oaNotifyMeeting.setSelf(true);
		Page<OaNotifyMeeting> page = oaNotifyMeetingService.findPage(
				new Page<OaNotifyMeeting>(request, response, -1),
				oaNotifyMeeting);
		ArrayList<OaNotifyMeeting> list = new ArrayList<OaNotifyMeeting>();
		for (OaNotifyMeeting oaNotifyMeeting2 : page.getList()) {
			if (oaNotifyMeeting2.getUpdateDate().getTime() > time2) {
				list.add(oaNotifyMeeting2);
			}
		}
		map.put("list", list);
		return new Gson().toJson(map);
	}

}