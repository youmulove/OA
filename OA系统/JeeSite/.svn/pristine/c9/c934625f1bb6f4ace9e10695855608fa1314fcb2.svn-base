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
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTask;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyTaskService;

/**
 * 新建任务Controller
 * 
 * @author 新建任务
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaNotifyTask")
public class OaNotifyTaskController extends BaseController {

	@Autowired
	private OaNotifyTaskService oaNotifyTaskService;

	@ModelAttribute
	public OaNotifyTask get(@RequestParam(required = false) String id) {
		OaNotifyTask entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaNotifyTaskService.get(id);
		}
		if (entity == null) {
			entity = new OaNotifyTask();
		}
		return entity;
	}

	@RequiresPermissions("oa:oaNotifyTask:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaNotifyTask oaNotifyTask, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<OaNotifyTask> page = oaNotifyTaskService.findPage(
				new Page<OaNotifyTask>(request, response), oaNotifyTask);
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyTaskList";
	}

	@RequiresPermissions("oa:oaNotifyTask:view")
	@RequestMapping(value = "form")
	public String form(OaNotifyTask oaNotifyTask, Model model) {

		if (StringUtils.isNoneBlank(oaNotifyTask.getId())) {
			oaNotifyTask = oaNotifyTaskService.getTaskRecordList(oaNotifyTask);
			oaNotifyTaskService.updateReadFlag(oaNotifyTask);
		}
		model.addAttribute("oaNotifyTask", oaNotifyTask);
		return "modules/oa/oaNotifyTaskForm";
	}

	@RequiresPermissions("oa:oaNotifyTask:edit")
	@RequestMapping(value = "save")
	public String save(OaNotifyTask oaNotifyTask, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaNotifyTask)) {
			return form(oaNotifyTask, model);
		}
		// 如果是修改，则状态为发布时，则不能在进行操作
		if (StringUtils.isNotBlank(oaNotifyTask.getId())) {
			OaNotifyTask m = oaNotifyTaskService.get(oaNotifyTask.getId());
			if ("1".equals(m.getStatus())) {
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/oa/oaNotifyTask/form?id="
						+ oaNotifyTask.getId();
			}
		}
		oaNotifyTaskService.save(oaNotifyTask);
		addMessage(redirectAttributes, "保存任务" + "" + "成功");
		return "redirect:" + adminPath + "/oa/oaNotifyTask/?repage";
	}

	@RequestMapping(value = "self")
	public String selfList(OaNotifyTask oaNotifyTask,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		oaNotifyTask.setSelf(true);
		Page<OaNotifyTask> page = oaNotifyTaskService.findPage(
				new Page<OaNotifyTask>(request, response), oaNotifyTask);
		model.addAttribute("page", page);
		return "modules/oa/oaNotifyTaskList";
	}

	/**
	 * 查看我的任务
	 */
	@RequestMapping(value = "view")
	public String view(OaNotifyTask oaNotifyTask, Model model) {
		if (StringUtils.isNotBlank(oaNotifyTask.getId())) {
			oaNotifyTaskService.updateReadFlag(oaNotifyTask);
			oaNotifyTask = oaNotifyTaskService.getTaskRecordList(oaNotifyTask);
			System.out.println("oanotifymeeting3" + oaNotifyTask);
			model.addAttribute("oaNotifyTask", oaNotifyTask);
			return "modules/oa/oaNotifyMeetingForm";
		}
		return "redirect:" + adminPath + "/oa/oaNotifyTask/self?repage";
	}

	/**
	 * 查看我的任务-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public OaNotifyTask viewRecordData(OaNotifyTask oaNotifyTask, Model model) {
		if (StringUtils.isNotBlank(oaNotifyTask.getId())) {
			oaNotifyTask = oaNotifyTaskService.getTaskRecordList(oaNotifyTask);
			return oaNotifyTask;
		}
		return null;
	}

	// 获取任务数目

	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(OaNotifyTask oaNotifyTask, Model model) {
		oaNotifyTask.setSelf(true);
		oaNotifyTask.setReadFlag("0");
		return String.valueOf(oaNotifyTaskService.findCount(oaNotifyTask));
	}

	// 删除任务
	@RequiresPermissions("oa:oaNotifyTask:edit")
	@RequestMapping(value = "delete")
	public String delete(OaNotifyTask oaNotifyTask,
			RedirectAttributes redirectAttributes) {
		oaNotifyTaskService.delete(oaNotifyTask);
		addMessage(redirectAttributes, "删除任务成功");
		return "redirect:" + adminPath + "/oa/oaNotifyTask/?repage";
	}

	/**
	 * 未读任务列表
	 */
	@RequestMapping(value = "oaNotifyTaskList")
	@ResponseBody
	public String oaNotifyTaskList(OaNotifyTask oaNotifyTask,
			HttpServletRequest request, HttpServletResponse response,
			String time) {
		Long time1 = Long.parseLong(time);
		long time2 = (long) time1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		oaNotifyTask.setSelf(true);
		Page<OaNotifyTask> page = oaNotifyTaskService.findNoRead(
				new Page<OaNotifyTask>(request, response), oaNotifyTask);
		ArrayList<OaNotifyTask> list = new ArrayList<OaNotifyTask>();
		for (OaNotifyTask oaNotifyTask2 : page.getList()) {
			if (oaNotifyTask2.getUpdateDate().getTime() > time2) {
				list.add(oaNotifyTask2);
			}
		}
		map.put("list", list);
		return new Gson().toJson(map);
	}

}