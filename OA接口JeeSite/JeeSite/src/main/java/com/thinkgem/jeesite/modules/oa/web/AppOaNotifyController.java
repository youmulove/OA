/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyMeetingDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyMeettingAcceptDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyRecordDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyTaskAcceptDao;
import com.thinkgem.jeesite.modules.oa.dao.OaNotifyTaskDao;
import com.thinkgem.jeesite.modules.oa.entity.OaMsgRecord;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeettingAccept;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTask;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyTaskAccept;
import com.thinkgem.jeesite.modules.oa.service.OaMsgRecordService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyMeetingService;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;


/**
 * 通知通告Controller
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/Appoa/oaNotify")
public class AppOaNotifyController extends BaseController {

	@Autowired
	private OaNotifyService oaNotifyService;
	@Autowired
	private OaNotifyDao oaNotifyDao;
	@Autowired
	private OaNotifyMeetingDao oaNotifyMeetingDao;
	@Autowired
	private OaNotifyMeetingService oaNotifyMeetingService;
	@Autowired
	private OaNotifyTaskDao oaNotifyTaskDao;
	@Autowired
	private OaNotifyRecordDao oaNotifyRecordDao;
	@Autowired
	private OaNotifyMeettingAcceptDao oaNotifyMeettingAcceptDao;
	@Autowired
	private OaNotifyTaskAcceptDao oaNotifyTaskAcceptDao;
	@Autowired
	private OaMsgRecordService oaMsgRecordService;
	@ModelAttribute
	public OaNotify get(@RequestParam(required=false) String id) {
		OaNotify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaNotifyService.get(id);
		}
		if (entity == null){
			entity = new OaNotify();
		}
		return entity;
	}
	
	



   /** 获取我发出的通知条数
 * @param oaNotify createBy.id
 * @return
 */
@RequestMapping(value="MySendNotify")
   @ResponseBody
   public JsonResult<Map<String,Object>> MySendNotify(OaNotify oaNotify){
	   try{
	   oaNotify.setCreateBy(UserUtils.getUser());                
		   
	   List<OaNotify> list=oaNotifyDao.selectOaNotifyByCreateBy(oaNotify);
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("page", list);
	   return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看我的通知成功",map);
	   }catch(Exception e){
		   e.printStackTrace();
		   return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
	   }
   } 
//我接收到的消息
//我接收和我发出的通告
@RequestMapping(value="MyNotify")
@ResponseBody
public JsonResult<Map<String,Object>> MyNotify(String id){
	   try{
	   User user=UserUtils.getUser();
	   OaNotify  oaNotify=new OaNotify();
	   oaNotify.setCreateBy(user);
	   oaNotify.setCurrentUser(user);
	   OaNotify  oaNotify1=new OaNotify();
	   oaNotify1.setCurrentUser(user);
	   oaNotify.setSelf(true);
	   OaNotifyMeeting  oaNotifyMeeting=new OaNotifyMeeting();
	   OaNotifyMeeting  oaNotifyMeeting1=new OaNotifyMeeting();
	   oaNotifyMeeting.setCurrentUser(user);
	   oaNotifyMeeting1.setCurrentUser(user);
	   oaNotifyMeeting.setSelf(true);
	   OaNotifyTask oaNotifyTask=new OaNotifyTask();
	   OaNotifyTask oaNotifyTask1=new OaNotifyTask();
	   oaNotifyTask.setCurrentUser(user);
	   oaNotifyTask1.setCurrentUser(user);
	   oaNotifyTask.setSelf(true);
	   List<OaNotify> list=oaNotifyDao.findList(oaNotify);
	   List<OaNotify> list1=oaNotifyDao.findList(oaNotify1);
	   List<OaNotifyMeeting> listOaNotifyMeeting=oaNotifyMeetingDao.findList(oaNotifyMeeting);
	   List<OaNotifyMeeting> listOaNotifyMeeting1=oaNotifyMeetingDao.findList(oaNotifyMeeting1);
	   List<OaNotifyTask> listOaNotifyTask=oaNotifyTaskDao.findList(oaNotifyTask);
	   List<OaNotifyTask> listOaNotifyTask1=oaNotifyTaskDao.findList(oaNotifyTask1);
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("createOaNotify", list1);
	   map.put("acceptOaNotify", list);
	   map.put("createoaNotifyMeeting", listOaNotifyMeeting1);
	   map.put("acceptoaNotifyMeeting", listOaNotifyMeeting);
	   map.put("createoaNotifyTask", listOaNotifyTask1);
	   map.put("acceptoaNotifyTask", listOaNotifyTask);
	   return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看我的消息成功",map);
	   }catch(Exception e){
		   e.printStackTrace();
		   return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
	   }
} 
@RequestMapping(value="MyAcceptNotify")
@ResponseBody
public JsonResult<Map<String,Object>> MyAcceptNotify(String id){
	   try{
	   User user=UserUtils.getUser();
	   OaNotify  oaNotify=new OaNotify();
	   oaNotify.setCreateBy(user);
	   oaNotify.setCurrentUser(user);
	   OaNotify  oaNotify1=new OaNotify();
	   oaNotify1.setCurrentUser(user);
	   oaNotify.setSelf(true);
	   OaNotifyMeeting  oaNotifyMeeting=new OaNotifyMeeting();
	   OaNotifyMeeting  oaNotifyMeeting1=new OaNotifyMeeting();
	   oaNotifyMeeting.setCurrentUser(user);
	   oaNotifyMeeting1.setCurrentUser(user);
	   oaNotifyMeeting.setSelf(true);
	   OaNotifyTask oaNotifyTask=new OaNotifyTask();
	   OaNotifyTask oaNotifyTask1=new OaNotifyTask();
	   oaNotifyTask.setCurrentUser(user);
	   oaNotifyTask1.setCurrentUser(user);
	   oaNotifyTask.setSelf(true);
	   List<OaNotify> list1=oaNotifyDao.findList(oaNotify);
	   List<OaNotifyMeeting> listOaNotifyMeeting1=oaNotifyMeetingDao.findList(oaNotifyMeeting);
	   List<OaNotifyTask> listOaNotifyTask1=oaNotifyTaskDao.findList(oaNotifyTask);
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("acceptOaNotify", list1);
	   map.put("acceptoaNotifyMeeting", listOaNotifyMeeting1);
	   map.put("acceptoaNotifyTask", listOaNotifyTask1);
	   return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看我的消息成功",map);
	   }catch(Exception e){
		   e.printStackTrace();
		   return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
	   }
} 
@RequestMapping(value="MySendNotify1")
@ResponseBody
public JsonResult<Map<String,Object>> MySendNotify1(String id){
	   try{
	   User user=UserUtils.getUser();
	   OaNotify  oaNotify=new OaNotify();
	   oaNotify.setCreateBy(user);
	   oaNotify.setCurrentUser(user);
	   OaNotify  oaNotify1=new OaNotify();
	   oaNotify1.setCurrentUser(user);
	   oaNotify.setSelf(true);
	   OaNotifyMeeting  oaNotifyMeeting=new OaNotifyMeeting();
	   OaNotifyMeeting  oaNotifyMeeting1=new OaNotifyMeeting();
	   oaNotifyMeeting.setCreateBy(user);
	   oaNotifyMeeting1.setCurrentUser(user);
	   oaNotifyMeeting.setSelf(true);
	   OaNotifyTask oaNotifyTask=new OaNotifyTask();
	   OaNotifyTask oaNotifyTask1=new OaNotifyTask();
	   oaNotifyTask.setCreateBy(user);
	   oaNotifyTask1.setCurrentUser(user);
	   oaNotifyTask.setSelf(true);
	   List<OaNotify> list=oaNotifyDao.findList(oaNotify1);
	   List<OaNotifyMeeting> listOaNotifyMeeting=oaNotifyMeetingDao.findList(oaNotifyMeeting1);
	   List<OaNotifyTask> listOaNotifyTask=oaNotifyTaskDao.findList(oaNotifyTask1);
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("createOaNotify", list);
	   map.put("createoaNotifyMeeting", listOaNotifyMeeting);
	   map.put("createoaNotifyTask", listOaNotifyTask);
	   return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看我的消息成功",map);
	   }catch(Exception e){
		   e.printStackTrace();
		   return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"查看失败",null);
	   }
} 

	
	/**王鹏亮
	 * @param oaNotify
	 * @param model  id  null  type title content files status oaNotifyRecordIds[] oaNotifyRecordNames[] createBy.id   updateBy.id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public JsonResult<Map<String,Object>> save(OaNotify oaNotify, Model model,HttpServletRequest request) {
	
		if (!beanValidator(model, oaNotify)){
			return new JsonResult<Map<String,Object>>(JsonResult.ERROR,"没有通过验证",null);
		}
		oaNotify.setType("oa");
		// 如果是修改，则状态为已发布，则不能再进行操作
		if (StringUtils.isNotBlank(oaNotify.getId())){
			OaNotify e = oaNotifyService.get(oaNotify.getId());
			if ("1".equals(e.getStatus())){
				return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"你已经发布该通知不能修改",null);
			}
		}
		oaNotifyService.save(oaNotify);
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"通知发布成功",null);
	}
	//查看我接收到的公告列表
    /**王鹏亮(我发出的接口)
     * @param oaNotify
     * @param request
     * @param response
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="AppList")
    @ResponseBody
    public JsonResult<Map<String,Object>> applist(OaNotify oaNotify,HttpServletRequest request,HttpServletResponse  response,Model model,String id){
    	User user=new User();
    	user.setId(id);
    	oaNotify.setCurrentUser(user);
    	oaNotify.setSelf(true);//true执行自己发出的sql语句
    	List<OaNotify> list=oaNotifyDao.findList(oaNotify);
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("page", list);
    	return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
    }
   //查看预约会议，通知，会议的详情接口
    @RequestMapping(value="viewDetail")
	@ResponseBody
	public JsonResult<Map<String,Object>> viewDetail(String type,String id){    	  
        if(type.equals("oaNotify")){
        	OaNotifyRecord  oaNotifyRecord =new OaNotifyRecord();
        	OaNotify oaNotify=new OaNotify();
        	oaNotify.setId(id);
        	oaNotifyRecord.setOaNotify(oaNotify);
        	oaNotifyRecord.setUser(UserUtils.getUser());
        	oaNotifyRecord.setReadFlag("1");
        	oaNotifyRecord.setReadDate(new Date());
        	OaNotify oaNotify1=oaNotifyService.get(oaNotify);
        	int i=oaNotifyRecordDao.update(oaNotifyRecord);
        	if(i>0){
         		OaMsgRecord oaMsgRecord=new OaMsgRecord();
         		oaMsgRecord.setMsgId(id);
         		oaMsgRecord.setMsgType("oaNotify");
         		oaMsgRecord.setAccerpUserId(UserUtils.getUser());
         		oaMsgRecord.setCreateUserId(oaNotify1.getCreateBy());
         		oaMsgRecord.setReadFlag("0");
         		oaMsgRecord.setReadDate(new Date());
          		oaMsgRecord.setCreateDate(new Date());
         		oaMsgRecordService.save(oaMsgRecord);
         	 }
        	Map<String,Object> map=new HashMap<String,Object>();
        	OaNotify oaNotifynew=oaNotifyDao.get(oaNotify);
        	List<OaNotifyRecord> list=oaNotifyRecordDao.findList(oaNotifyRecord);
        	List<Map<String,Object>> listOaNotifyRecord=new ArrayList<Map<String,Object>>();
        	
        	
        	for(OaNotifyRecord oaNotifyRecordnew:list){
        		Map<String,Object>  mapnew=new HashMap<String,Object>();
        		mapnew.put("id",oaNotifyRecordnew.getId());
        		mapnew.put("userId", oaNotifyRecordnew.getUser().getId());
        		mapnew.put("userName", UserUtils.get(oaNotifyRecordnew.getUser().getId())!=null?UserUtils.get(oaNotifyRecordnew.getUser().getId()).getName():"");
        		listOaNotifyRecord.add(mapnew);	
        	}
        	map.put("type", "oaNotify");
        	map.put("oaNotify", oaNotifynew);
        	map.put("listOaNotifyRecord", listOaNotifyRecord);
        	return new JsonResult<Map<String,Object>>("0","查看成功",map);
        }else if(type.equals("oaNotifyMeeting")){
        	OaNotifyMeettingAccept oaNotifyMeetingAccept=new OaNotifyMeettingAccept();
        	OaNotifyMeeting oaNotifyMeeting=new OaNotifyMeeting();
        	oaNotifyMeeting.setId(id);
        	oaNotifyMeetingAccept.setOaNotifyMeeting(oaNotifyMeeting);
        	oaNotifyMeetingAccept.setUser(UserUtils.getUser());
        	oaNotifyMeetingAccept.setReadFlag("1");
        	oaNotifyMeetingAccept.setReadDate(new Date());
        	OaNotifyMeeting oaNotifyMeeting1=oaNotifyMeetingService.get(oaNotifyMeeting);
        	int i= oaNotifyMeettingAcceptDao.update(oaNotifyMeetingAccept);
        	if(i>0){
         		OaMsgRecord oaMsgRecord=new OaMsgRecord();
         		oaMsgRecord.setMsgId(id);
         		oaMsgRecord.setMsgType("oaNotify");
         		oaMsgRecord.setAccerpUserId(UserUtils.getUser());
         		oaMsgRecord.setReadFlag("0");
         		oaMsgRecord.setReadDate(new Date());
          		oaMsgRecord.setCreateDate(new Date());
         		oaMsgRecord.setCreateUserId(oaNotifyMeeting1.getCreateBy());
    
         		oaMsgRecordService.save(oaMsgRecord);
         	    }
        	Map<String,Object> map=new HashMap<String,Object>();
        	OaNotifyMeeting  oaNotifyMeetingNew=oaNotifyMeetingDao.get(oaNotifyMeeting);
        	List<OaNotifyMeettingAccept> list=oaNotifyMeettingAcceptDao.findList(oaNotifyMeetingAccept);
        	List<Map<String,Object>>  listOaNotifyMeettingAccept=new ArrayList<Map<String,Object>>();
        	for(OaNotifyMeettingAccept oaNotifyMeettingAcceptnew:list){
        		Map<String,Object> mapnew=new HashMap<String,Object>();
        		mapnew.put("id", oaNotifyMeettingAcceptnew.getId());
        		mapnew.put("userId", oaNotifyMeettingAcceptnew.getUser().getId());
        		mapnew.put("userName", UserUtils.get(oaNotifyMeettingAcceptnew.getUser().getId())!=null?UserUtils.get(oaNotifyMeettingAcceptnew.getUser().getId()).getName():"");
        		listOaNotifyMeettingAccept.add(mapnew);
        	}
        	map.put("listOaNotifyMeettingAccept", listOaNotifyMeettingAccept);
        	map.put("type", "oaNotifyMeeting");
        	map.put("oaNotifyMeeting", oaNotifyMeetingNew);
        	return new JsonResult<Map<String,Object>>("0","查看成功",map);
        }else if(type.equals("oaNotifyTask")){
        	OaNotifyTaskAccept oaNotifyTaskAccept=new OaNotifyTaskAccept(); 
        	OaNotifyTask oaNotifyTask=new OaNotifyTask();
        	oaNotifyTask.setId(id);
        	oaNotifyTaskAccept.setTaskId(oaNotifyTask);
        	oaNotifyTaskAccept.setUser(UserUtils.getUser());
        	oaNotifyTaskAccept.setReadFlag("1");
        	oaNotifyTaskAccept.setReadDate(new Date());
        	OaNotifyTask oaNotifyTask1=oaNotifyTaskDao.get(oaNotifyTask);
        	int i=  oaNotifyTaskAcceptDao.update(oaNotifyTaskAccept);
        	 if(i>0){
          		OaMsgRecord oaMsgRecord=new OaMsgRecord();
          		oaMsgRecord.setMsgId(id);
          		oaMsgRecord.setMsgType("oaNotify");
          		oaMsgRecord.setAccerpUserId(UserUtils.getUser());
          		oaMsgRecord.setCreateUserId(oaNotifyTask1.getCreateBy());
          		oaMsgRecord.setReadFlag("0");
          		oaMsgRecord.setReadDate(new Date());
          		oaMsgRecord.setCreateDate(new Date());
          		oaMsgRecordService.save(oaMsgRecord);
          	    }
        	OaNotifyTask oaNotifyTaskNew=oaNotifyTaskDao.get(oaNotifyTask);
        	List<OaNotifyTaskAccept> list=oaNotifyTaskAcceptDao.findList(oaNotifyTaskAccept);
        	List<Map<String,Object>>  listOaNotifyTaskAccept=new ArrayList<Map<String,Object>>();
        	for(OaNotifyTaskAccept oaNotifyTaskAcceptnew:list){
              Map<String,Object> mapnew=new HashMap<String,Object>();
              mapnew.put("id", oaNotifyTaskAcceptnew.getId());
              mapnew.put("userId", oaNotifyTaskAcceptnew.getUser().getId());
              mapnew.put("userName",UserUtils.get(oaNotifyTaskAcceptnew.getUser().getId())!=null?UserUtils.get(oaNotifyTaskAcceptnew.getUser().getId()).getName():"");
        	}
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("type", "oaNotifyTask");
        	map.put("listOaNotifyTaskAccept", listOaNotifyTaskAccept);
        	map.put("OaNotifyTask", oaNotifyTaskNew);
        	return new JsonResult<Map<String,Object>>("0","查看成功",map);
        }
        return new JsonResult<Map<String,Object>>("0","查看成功",null);
    	
    }
     
	@RequestMapping(value="appSelf/count")
	@ResponseBody
	public JsonResult<Map<String,Object>> appSelfCount(OaNotify oaNotify,Model model,String id,String __sid){
		oaNotify.setSelf(true);
		oaNotify.setReadFlag("0");
		User user=new User();
		user.setId(id);
		oaNotify.setCurrentUser(user);
	    String a=String.valueOf(oaNotifyService.findCount(oaNotify));
	    Map<String,Object> map=new HashMap<String,Object>();
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
	}

	
}