/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyTaskDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTask;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyMeetingService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyTaskService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 新建任务Controller
 * 
 * @author 新建任务
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/oaNotifyTask")
public class AppOaNotifyTaskController extends BaseController {

	@Autowired
	private OaNotifyTaskService oaNotifyTaskService;
    @Autowired
    private OaNotifyTaskDao oaNotifyTaskDao;
    @Autowired
    private OaNotifyService  oaNotifyService;
    @Autowired
    private OaNotifyMeetingService oaNotifyMeetingService;
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
//我接收的任务列表
//	@RequiresPermissions("oa:oaNotifyTask:view")
	@RequestMapping(value = "list")
	public JsonResult<Map<String,Object>> list(OaNotifyTask oaNotifyTask, HttpServletRequest request,
			HttpServletResponse response, Model model) {
       List<OaNotifyTask> list=oaNotifyTaskDao.findList(oaNotifyTask);
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("page", list);
       return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查询成功",null);		
	}

//	@RequiresPermissions("oa:oaNotifyTask:view")
	@RequestMapping(value = "form")
	public String form(OaNotifyTask oaNotifyTask, Model model) {
		if (StringUtils.isNoneBlank(oaNotifyTask.getId())) {
			oaNotifyTask = oaNotifyTaskService.getTaskRecordList(oaNotifyTask);
		}
		model.addAttribute("oaNotifyTask", oaNotifyTask);
		return "modules/oa/oaNotifyTaskForm";
	}
	//新建和修改任务接口已经测试新建成功
	@RequestMapping(value = "save")
	@ResponseBody
	public JsonResult<Map<String,Object>> save(OaNotifyTask oaNotifyTask, Model model) {
		try{
		User user=UserUtils.getUser();
		String userId=user.getId();
		if (!beanValidator(model, oaNotifyTask)) {
			return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"数据格式不正确",null);
		}
		oaNotifyTask.setType("1");
		// 如果是修改，则状态为发布时，则不能在进行操作
		if (StringUtils.isNotBlank(oaNotifyTask.getId())) {
			OaNotifyTask m = oaNotifyTaskService.get(oaNotifyTask.getId());
			if ("1".equals(m.getStatus())) {
				return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"已经发布不能操作",null);
			}
		}

		oaNotifyTaskService.save(oaNotifyTask);

		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"发布成功",null);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"发布失败",null);
		}
	}
    //查看我接收到的任务
	@RequestMapping(value = "self")
	@ResponseBody
	public JsonResult<Map<String,Object>> selfList(OaNotifyTask oaNotifyTask,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		oaNotifyTask.setSelf(true);
		List<OaNotifyTask> list=oaNotifyTaskDao.findList(oaNotifyTask);
        Map<String,Object> map=new HashMap<String,Object>();
		map.put("page",list);
		return new JsonResult<Map<String,Object>>("0","查看成功",map);
	}

	/**
	 * 查看我的任务
	 */
	@RequestMapping(value = "view")
	public String view(OaNotifyTask oaNotifyTask, Model model) {
		if (StringUtils.isNotBlank(oaNotifyTask.getId())) {
			oaNotifyTaskService.updateReadFlag(oaNotifyTask);
			oaNotifyTask = oaNotifyTaskService.getTaskRecordList(oaNotifyTask);
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
   
	
	
	// 获取我的各种消息的数目
    //App接口
	@RequestMapping(value = "self/count")
	@ResponseBody
	public JsonResult<Map<String,Object>> selfCount(OaNotifyTask oaNotifyTask,OaNotify oaNotify,
			OaNotifyMeeting oaNotifyMeeting,
			Model model) {
		User user=UserUtils.getUser();
		oaNotifyTask.setSelf(true);
		oaNotifyTask.setReadFlag("0");
		oaNotifyTask.setCurrentUser(user);
		oaNotify.setSelf(true);
		oaNotify.setReadFlag("0");
		oaNotify.setCurrentUser(user);
		oaNotifyMeeting.setSelf(true);
		oaNotifyMeeting.setReadFlag("0");
		oaNotifyMeeting.setCurrentUser(user);
        String oaNotifyTaskCount=String.valueOf(oaNotifyTaskService.findCount(oaNotifyTask));
        String oaNotifyCount=String.valueOf(oaNotifyService.findCount(oaNotify));
        String oaNotifyMeetingCount=String.valueOf(oaNotifyMeetingService.findCount(oaNotifyMeeting));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("oaNotifyTaskCount", oaNotifyTaskCount);
        map.put("oaNotifyCount", oaNotifyCount);
        map.put("oaNotifyMeetingCount", oaNotifyMeetingCount);
       return new JsonResult<Map<String,Object>>("0","查看成功",map);
	}
/*	@RequiresPermissions("oa:oaNotifyTask:edit")
	@RequestMapping(value = "delete")
	public String delete(OaNotifyTask oaNotifyTask,
			RedirectAttributes redirectAttributes) {
		oaNotifyTaskService.delete(oaNotifyTask);
		addMessage(redirectAttributes, "删除任务成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaNotifyTask/?repage";
	}*/

}