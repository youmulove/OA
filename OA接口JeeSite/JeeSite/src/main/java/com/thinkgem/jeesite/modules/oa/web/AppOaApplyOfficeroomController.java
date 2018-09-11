/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.modules.oa.dao.OaApplyOfficeroomDao;
import com.thinkgem.jeesite.modules.oa.dao.OfficeRoomDao;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 办公室申请Controller
 * 
 * @author jhm
 * @version 2017-09-28
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/oaApplyOfficeroom")
public class AppOaApplyOfficeroomController extends BaseController {
	@Autowired
	private OaApplyOfficeroomDao oaApplyOfficeroomDao;
	@Autowired
	private OaApplyOfficeroomService oaApplyOfficeroomService;
	@Autowired
	OfficeService officeService;
	@Autowired
	private OfficeRoomDao officeRoomDao;
	@Autowired
	private ApprovalRuleService approvalRuleService;

	@ModelAttribute
	public OaApplyOfficeroom get(@RequestParam(required = false) String id) {
		OaApplyOfficeroom entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaApplyOfficeroomService.get(id);
		}
		if (entity == null) {
			entity = new OaApplyOfficeroom();
		}
		return entity;
	}

	/*@RequestMapping(value = "reportList")
	public ModelAndView reportList(OaApplyOfficeroom oaApplyOfficeroom,
			HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("得到当前用户所在公司信息"+UserUtils.getUser().getCompany());
		// oaApplyOfficeroom.setCompany(UserUtils.getUser().getCompany().getName());
		// oaApplyOfficeroom.setDepartment(UserUtils.getUser().getOffice().getName());
		String companyId = request.getParameter("company");
		String officeId = request.getParameter("office");
		Office company = officeService.get(companyId);
		Office office = officeService.get(officeId);
		System.out.println("得到当前用户所在部门信息"
				+ UserUtils.getUser().getOffice().getName());
		// oaApplyOfficeroom.setDepartment(UserUtils.getUser().getOffice().getName());
		ModelAndView mav = new ModelAndView();
		Page<OaApplyOfficeroom> page = oaApplyOfficeroomService.findWithNames(
				new Page<OaApplyOfficeroom>(request, response),
				oaApplyOfficeroom);
		mav.addObject("page", page);
		mav.addObject("company", company);
		mav.addObject("office", office);
		mav.setViewName("modules/oa/officeRoomReport");
		return mav;
	}*/

	// ch
	@RequestMapping(value = { "list", "" })
	@ResponseBody
	public JsonResult<Map<String, Object>> list(String officeroomId,
			OaApplyOfficeroom oaApplyOfficeroom, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		oaApplyOfficeroom
				.setCompanyId(UserUtils.getUser().getCompany().getId());
		OfficeRoom officeroom = new OfficeRoom();
		officeroom.setCompany(UserUtils.getUser().getCompany().getId());
		List<OfficeRoom> list1 = officeRoomDao.findList(officeroom);
		// List<OaApplyOfficeroom> list2 =
		// oaApplyOfficeroomService.findList(oaApplyOfficeroom);
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("officeRoom");
		// 获取审批规则信息列表
		// List<ApprovalRule> appList =
		// approvalRuleService.findApprovalRuleSelective(appr);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (officeroomId != null) {
			OaApplyOfficeroom oaApplyOfficeroom1 = new OaApplyOfficeroom();
			oaApplyOfficeroom1.setOfficeroomId(officeroomId);
			List<OaApplyOfficeroom> list2 = oaApplyOfficeroomDao
					.selectOaApplyOfficeroomByofficeroomId(oaApplyOfficeroom1);
			map.put("OaApplyOfficeroomList", list2);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"查看成功", map);
		}
		map.put("list1", list1);

		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "查看成功",
				map);
	}

	/*@RequiresPermissions("oa:oaApplyOfficeroom:view")
	@RequestMapping(value = "form")
	public String form(OaApplyOfficeroom oaApplyOfficeroom, Model model) {
		model.addAttribute("oaApplyOfficeroom", oaApplyOfficeroom);
		return "modules/oa/oaApplyOfficeroom";
	}*/

	/*@RequiresPermissions("oa:oaApplyOfficeroom:edit")
	@RequestMapping(value = "save")
	public String save(OaApplyOfficeroom oaApplyOfficeroom, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaApplyOfficeroom)) {
			return form(oaApplyOfficeroom, model);
		}
		oaApplyOfficeroomService.save(oaApplyOfficeroom);
		addMessage(redirectAttributes, "保存办公室申请成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaApplyOfficeroom/?repage";
	}*/

	/*@RequiresPermissions("oa:oaApplyOfficeroom:edit")
	@RequestMapping(value = "delete")
	public String delete(OaApplyOfficeroom oaApplyOfficeroom,
			RedirectAttributes redirectAttributes) {
		oaApplyOfficeroomService.delete(oaApplyOfficeroom);
		addMessage(redirectAttributes, "删除办公室申请成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaApplyOfficeroom/?repage";
	}*/

	/*@RequiresPermissions("oa:oaApplyOfficeroom:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(OaApplyOfficeroom oaApplyOfficeroom,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {

		try {

			String fileName = "办公室申请表" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			System.out.println("办公室申请表" + fileName);
			// Page<User> page = systemService.findUser(new Page<User>(request,
			// response, -1), user);
			Page<OaApplyOfficeroom> page = oaApplyOfficeroomService
					.findWithNames(new Page<OaApplyOfficeroom>(request,
							response, -1), oaApplyOfficeroom);
			System.out.println("page" + page);
			new ExportExcel("办公室申请表", OaApplyOfficeroom.class)
					.setDataList(page.getList())
					.writeSelectBrower(response, request, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出办公室表失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/officeRoomReport?repage";
	}*/

}