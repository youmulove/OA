package com.thinkgem.jeesite.modules.oa.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyMeetingService;



@Controller
@RequestMapping(value = "${adminPath}/oa/meetingReport")
public class MeetingReportController extends BaseController {
@Autowired
private OaNotifyMeetingService meetReportService;
@ModelAttribute
public OaNotifyMeeting get(@RequestParam(required=false) String id) {
	OaNotifyMeeting entity = null;
	if (StringUtils.isNotBlank(id)){
		entity = meetReportService.get(id);
	}
	if (entity == null){
		entity = new OaNotifyMeeting();
	}
	return entity;
}
@RequiresPermissions("oa:meetingReport:view")
@RequestMapping(value = {"list", ""})
public String  getAllMeetingReportList(OaNotifyMeeting oaNotifyMeeting, HttpServletRequest request, HttpServletResponse response, Model model){
	//ModelAndView mv = new ModelAndView();
	
	Page<OaNotifyMeeting> page = meetReportService.findPage(new Page<OaNotifyMeeting>(request, response), oaNotifyMeeting);
	List<OaNotifyMeeting> meetingList =  meetReportService.findListWithAccept();
	Map<String,Object> meetWithAccept = new HashMap<String,Object>();
	Iterator<OaNotifyMeeting> iter = meetingList.iterator();
	while(iter.hasNext()){
		OaNotifyMeeting notifyMeeting = iter.next();
		meetWithAccept.put(notifyMeeting.getId(), notifyMeeting.getOaNotifyMeetingAcceptNames());
		
	}
	model.addAttribute("meetWithAccept",meetWithAccept);
	model.addAttribute("page", page);
	return "modules/oa/meetingReport";	
}
@RequiresPermissions("oa:oaNotifyMeeting:view")
@RequestMapping(value = "details")
public String form(OaNotifyMeeting oaNotifyMeeting, Model model) {
	
	if(StringUtils.isNoneBlank(oaNotifyMeeting.getId())){
		oaNotifyMeeting = meetReportService.getMeetingRecordList(oaNotifyMeeting);
	}
	model.addAttribute("oaNotifyMeeting", oaNotifyMeeting);
	return "modules/oa/meetingDetails";
}
/**
 * 
 * @param oaNotifyMeeting
 * @param request
 * @param response
 * @param redirectAttributes
 * @return
 */
@RequiresPermissions("sys:user:view")
@RequestMapping(value = "export", method=RequestMethod.POST)
public String exportFile(OaNotifyMeeting oaNotifyMeeting, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
	//System.out.println("导出会议报表数据开始");
	  response.setContentType("application/octet-stream;charset=utf-8");  
	  response.setCharacterEncoding("utf-8");  

	try {
      //String fileName="预约会议";
       // fileName += DateUtils.getDate("yyyyMMddHHmmss")+".xls";
       
        String fileName ="预约会议报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
       // System.out.println("导出会议数据文件名"+fileName);
        //Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
        Page<OaNotifyMeeting> page = meetReportService.findPageWithSendee(new Page<OaNotifyMeeting>(request, response, -1), oaNotifyMeeting);
        System.out.println("page"+page);
		new ExportExcel("预约会议报表", OaNotifyMeeting.class).setDataList(page.getList()).writeSelectBrower(response,request,fileName).dispose();
		return null;
	} catch (Exception e) {
		addMessage(redirectAttributes, "导出会议报表失败！失败信息："+e.getMessage());
	}
	return "redirect:" + adminPath + "/oa/meetingReport?repage";
}






}
