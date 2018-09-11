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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyMeetingDao;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeettingAccept;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyMeetingService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 主子表生成Controller
 * @author ThinkGem
 * @version 2017-09-13
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/oaNotifyMeeting")
public class AppOaNotifyMeetingController extends BaseController {

	@Autowired
	private OaNotifyMeetingService oaNotifyMeetingService;
	@Autowired
	private OaNotifyMeetingDao oaNotifyMeetingDao;
	@ModelAttribute
	public OaNotifyMeeting get(@RequestParam(required=false) String id) {
		OaNotifyMeeting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaNotifyMeetingService.get(id);
		}
		if (entity == null){
			entity = new OaNotifyMeeting();
		}
		return entity;
	}
	//查看所有预约会议
	@RequestMapping(value = "list")
	@ResponseBody
	public JsonResult<Map<String,Object>> list(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
	   try{
		   Map<String,Object> map=new HashMap<String,Object>();
		   User user=UserUtils.getUser();
	       OaNotifyMeeting  oaNotifyMeeting=new OaNotifyMeeting();
		   OaNotifyMeeting  oaNotifyMeeting1=new OaNotifyMeeting();
		   oaNotifyMeeting.setCurrentUser(user);
		   oaNotifyMeeting1.setCurrentUser(user);
		   oaNotifyMeeting.setSelf(true);
	       List<OaNotifyMeeting> list=oaNotifyMeetingDao.findList(oaNotifyMeeting);
	       List<OaNotifyMeeting> listOaNotifyMeeting=oaNotifyMeetingDao.findList(oaNotifyMeeting);
	       List<OaNotifyMeeting> listOaNotifyMeeting1=oaNotifyMeetingDao.findList(oaNotifyMeeting1);
	       map.put("createoaNotifyMeeting", listOaNotifyMeeting1);
	       map.put("acceptoaNotifyMeeting", listOaNotifyMeeting);
	       map.put("page", list);
	   return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
	   }catch(Exception e){
		   e.printStackTrace();
		   return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
	   }
	}
    //申请创建预约会议
	/**
	 * @param oaNotifyMeeting
	 * type  title content status oaNotifyMeettingAcceptList[0].user.id createBy.id
	 * updateBy.id  oaNotifyMeettingAcceptList[0].createBy.id   oaNotifyMeettingAcceptList[0].updateBy.id
	 * oaNotifyMeettingAcceptList[0].id(此值不传但是得有空字符串) startTime endTime
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public JsonResult<Map<String,Object>> save(OaNotifyMeeting oaNotifyMeeting, Model model, RedirectAttributes redirectAttributes) {
		if(!beanValidator(model,oaNotifyMeeting)){
			return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"数据格式不正确",null);
		}
		if(oaNotifyMeeting.getOfficeRoom()==null){
		 OfficeRoom officeRoom=new OfficeRoom();
		 officeRoom.setId("请选择会议室");
		 oaNotifyMeeting.setOfficeRoom(officeRoom);
		}
		oaNotifyMeeting.setType("1");
	    oaNotifyMeetingService.save(oaNotifyMeeting);

		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"添加成功",null);
	}
	
	
	
	//查看我发送的会议
	/**
	 * @param oaNotifyMeeting  currentUser.id
	 * 
	 * @return isSelf=false//执行sql
	 */

	@RequestMapping(value="MySendMeeting")
	@ResponseBody
	public JsonResult<Map<String,Object>> MySendMeeting(OaNotifyMeeting oaNotifyMeeting){
		try{
		//Page<OaNotifyMeeting> page=oaNotifyMeetingService.findPage(new Page<OaNotifyMeeting>(), oaNotifyMeeting);
			oaNotifyMeeting.setSelf(false);
		List<OaNotifyMeeting> list=oaNotifyMeetingDao.findList(oaNotifyMeeting);	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", list);
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
		}catch(Exception e){
			e.printStackTrace();
		return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
		}
	}
	/** 我接收到的预约会议
	 * @param  currentUser.id
	 * isSelf=true//执行sql
	 * @return
	 */
	@RequestMapping(value="MyAccept")
	@ResponseBody
	public JsonResult<Map<String,Object>> MyAccept(OaNotifyMeeting oaNotifyMeeting){
		try{
		//OaNotifyMeeting oas=new OaNotifyMeeting();
		//User user=new User();
		//user.setId(id);
		oaNotifyMeeting.setSelf(true);
		//oas.setCurrentUser(user);
		List<OaNotifyMeeting> oa=oaNotifyMeetingDao.findList(oaNotifyMeeting);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("oa", oa);
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
		}
	}
	/*//删除预约的会议
	@RequestMapping(value = "delete")
	@ResponseBody
	public JsonResult<Map<String,Object>> delete(OaNotifyMeeting oaNotifyMeeting, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		System.out.println(request.getSession().getId());
		oaNotifyMeetingService.delete(oaNotifyMeeting);
		return new JsonResult<Map<String,Object>>();			
	}*/

}