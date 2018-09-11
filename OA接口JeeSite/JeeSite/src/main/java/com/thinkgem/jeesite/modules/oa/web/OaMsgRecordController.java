/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;



//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.thinkgem.jeesite.common.config.Global;
//import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
//import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaMsgRecord;
import com.thinkgem.jeesite.modules.oa.service.OaMsgRecordService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 消息提醒Controller
 * @author wangyl
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/oaMsgRecord")
public class OaMsgRecordController extends BaseController {

	@Autowired
	private OaMsgRecordService oaMsgRecordService;
	
//	@ModelAttribute
//	public OaMsgRecord get(@RequestParam(required=false) String id) {
//		OaMsgRecord entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = oaMsgRecordService.get(id);
//		}
//		if (entity == null){
//			entity = new OaMsgRecord();
//		}
//		return entity;
//	}
	@RequestMapping(value="viewMsgRecordBycreateUserId")
	@ResponseBody
	public JsonResult<Map<String,Object>> viewMsgRecordBycreateUserId(String msgId){
		OaMsgRecord oaMsgRecord=new OaMsgRecord();
		oaMsgRecord.setCreateUserId(UserUtils.getUser());
		oaMsgRecord.setMsgId(msgId);
	    List<OaMsgRecord> list=oaMsgRecordService.findListBycreateUserId(oaMsgRecord);
		if(list.size()>0){
			for(OaMsgRecord MsgRecord:list){
				oaMsgRecordService.delete(oaMsgRecord);
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("listSize", list.size());
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"新提醒内容查看成功",null);
	}
	@RequestMapping(value="viewMsgCountByMsgId")
	@ResponseBody
	public JsonResult<Map<String,Object>>  viewMsgCountByMsgId(){
		User user=UserUtils.getUser();
		OaMsgRecord oaMsgRecord=new OaMsgRecord();
		oaMsgRecord.setCreateUserId(user);
		List<OaMsgRecord> list=oaMsgRecordService.MsgByCreateUserIdGroupByMsgId(oaMsgRecord);
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(OaMsgRecord OaMsgRecord1:list){
			int l=oaMsgRecordService.CountBycreateUserIdMsgId(OaMsgRecord1);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("count", l);
			map.put("msgId", OaMsgRecord1.getMsgId());
			map.put("msg", OaMsgRecord1.getMsg());
			map.put("msgType", OaMsgRecord1.getMsgType());
			listMap.add(map);
		}
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("listMap", listMap);
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map1);
	}
	
	@RequestMapping(value="viewAllTheMagNews")
	@ResponseBody
	public JsonResult<Map<String,Object>> viewAllTheMagNews(){
		OaMsgRecord oaMsgRecord=new OaMsgRecord();
		oaMsgRecord.setCreateUserId(UserUtils.getUser());
		List<OaMsgRecord> list=oaMsgRecordService.findListBycreateUserId(oaMsgRecord);
		Map<String,Object>  map=new HashMap<String,Object>();
		map.put("listSize", list.size());
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",null);
	}
	
//	@RequiresPermissions("oa:oaMsgRecord:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(OaMsgRecord oaMsgRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<OaMsgRecord> page = oaMsgRecordService.findPage(new Page<OaMsgRecord>(request, response), oaMsgRecord); 
//		model.addAttribute("page", page);
//		return "modules/oa/oaMsgRecordList";
//	}

//	@RequiresPermissions("oa:oaMsgRecord:view")
//	@RequestMapping(value = "form")
//	public String form(OaMsgRecord oaMsgRecord, Model model) {
//		model.addAttribute("oaMsgRecord", oaMsgRecord);
//		return "modules/oa/oaMsgRecordForm";
//	}
//
//	@RequiresPermissions("oa:oaMsgRecord:edit")
//	@RequestMapping(value = "save")
//	public String save(OaMsgRecord oaMsgRecord, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, oaMsgRecord)){
//			return form(oaMsgRecord, model);
//		}
//		oaMsgRecordService.save(oaMsgRecord);
//		addMessage(redirectAttributes, "保存消息提醒成功");
//		return "redirect:"+Global.getAdminPath()+"/oa/oaMsgRecord/?repage";
//	}
//	
//	@RequiresPermissions("oa:oaMsgRecord:edit")
//	@RequestMapping(value = "delete")
//	public String delete(OaMsgRecord oaMsgRecord, RedirectAttributes redirectAttributes) {
//		oaMsgRecordService.delete(oaMsgRecord);
//		addMessage(redirectAttributes, "删除消息提醒成功");
//		return "redirect:"+Global.getAdminPath()+"/oa/oaMsgRecord/?repage";
//	}

}