/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.oa.service.OfficeRoomService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 会议室Controller
 * @author lisp
 * @version 2017-09-22
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/officeRoom")
public class AppOfficeRoomController extends BaseController {

	@Autowired
	private OfficeRoomService officeRoomService;
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private OaApplyOfficeroomService oaApplyOfficeroomService;
	
	@ModelAttribute
	public OfficeRoom get(@RequestParam(required=false) String id) {
		OfficeRoom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = officeRoomService.get(id);
		}
		if (entity == null){
			entity = new OfficeRoom();
		}
		return entity;
	}
	
	
	/*@RequestMapping(value = {"showAll", ""})
	public String showAll(OfficeRoom officeRoom, HttpServletRequest request, HttpServletResponse response, Model model) {
		officeRoom.setCompany(UserUtils.getUser().getCompany().getId());
		Page<OfficeRoom> page = officeRoomService.findPage(new Page<OfficeRoom>(request, response), officeRoom); 
		model.addAttribute("page", page);
		return "modules/oa/officeRoomList";
	}*/
	
	
	//@RequiresPermissions("oa:officeRoom:view")
	@RequestMapping(value = {"list", ""})
	@ResponseBody
	public JsonResult<Map<String,Object>> list(OaApplyOfficeroom officeRoom, HttpServletRequest request, HttpServletResponse response, Model model) {
		officeRoom.setCompanyId(UserUtils.getUser().getCompany().getId());
		officeRoom.setCompany(UserUtils.getUser().getCompany());
		OfficeRoom room = new OfficeRoom();
		room.setCompany(UserUtils.getUser().getCompany().getId());
		//List<OfficeRoom> list1 = officeRoomService.findAllRoom(room);
		
		//List<OaApplyOfficeroom> list2 = oaApplyOfficeroomService.findList2(officeRoom);
		
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("officeRoom");
		//获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService.findApprovalRuleSelective(appr);
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		
		map.put("appList", appList);
		
		
		return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"查看成功",map);
	}

	//@RequiresPermissions("oa:officeRoom:view")
	/*@RequestMapping(value = "form")
	public String form(OfficeRoom officeRoom,String type, Model model) {
		if("add".equals(type)){
			model.addAttribute("type", type);
			return "modules/oa/officeRoomForm";
		}else{
			OfficeRoom room = officeRoomService.get(officeRoom);
			model.addAttribute("officeRoom", room);
			model.addAttribute("type", type);
			return "modules/oa/officeRoomForm";
		}
	}*/

	//@RequiresPermissions("oa:officeRoom:edit")
	/*@RequestMapping(value = "save")
	public String save(OfficeRoom officeRoom, Model model, RedirectAttributes redirectAttributes) {
		officeRoom.setCompany(UserUtils.getUser().getCompany().getId());
		if (!beanValidator(model, officeRoom)){
			return "redirect:"+adminPath+"/oa/officeRoom/showAll";
		}
		officeRoomService.save(officeRoom);
		addMessage(redirectAttributes, "保存officeRoom成功");
		return "redirect:"+adminPath+"/oa/officeRoom/showAll";
	}*/
	
	//@RequiresPermissions("oa:officeRoom:edit")
	/*@RequestMapping(value = "delete")
	public String delete(OfficeRoom officeRoom, RedirectAttributes redirectAttributes) {
		officeRoomService.delete(officeRoom);
		addMessage(redirectAttributes, "删除officeRoom成功");
		return "redirect:"+adminPath+"/oa/officeRoom/showAll";
	}
	*/
	/*@RequestMapping("detail")
	public String detail(OfficeRoom officeRoom,Model model){
		OfficeRoom room = officeRoomService.get(officeRoom);
		model.addAttribute("officeRoom", room);
		return "modules/oa/officeRoomDetail";
		
	}*/
	
}