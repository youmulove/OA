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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.oa.dao.OaApplyProjectorDao;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.oa.service.OaApplyProjectorService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 投影仪申请Controller
 * 
 * @author jhm
 * @version 2017-09-28
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/oaApplyProjector")
public class AppOaApplyProjectorController extends BaseController {

	@Autowired
	private OaApplyProjectorService oaApplyProjectorService;
	@Autowired
	private OaApplyProjectorDao oaApplyProjectorDao;

	@ModelAttribute
	public OaApplyProjector get(@RequestParam(required = false) String id) {
		OaApplyProjector entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaApplyProjectorService.get(id);
		}
		if (entity == null) {
			entity = new OaApplyProjector();
		}
		return entity;
	}

	/*@RequiresPermissions("oa:oaApplyProjector:view")
	@RequestMapping(value = { "report" })
	public String reportList(OaApplyProjector oaApplyProjector,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<OaApplyProjector> page = oaApplyProjectorService
				.findWithNames(new Page<OaApplyProjector>(request, response),
						oaApplyProjector);
		model.addAttribute("page", page);
		return "modules/oa/projectorReport";
	}*/

	@RequestMapping(value = { "list", "" })
	@ResponseBody
	public JsonResult<Map<String, Object>> list(
			OaApplyProjector oaApplyProjector) {
		User user = UserUtils.getUser();
		List<OaApplyProjector> list = oaApplyProjectorDao
				.findList(oaApplyProjector);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS, "查看成功",
				map);
	}

	/*@RequiresPermissions("oa:oaApplyProjector:view")
	@RequestMapping(value = "form")
	public String form(OaApplyProjector oaApplyProjector, Model model) {
		model.addAttribute("oaApplyProjector", oaApplyProjector);
		return "modules/oa/oaApplyProjectorForm";
	}*/

	/*@RequiresPermissions("oa:oaApplyProjector:edit")
	@RequestMapping(value = "save")
	public String save(OaApplyProjector oaApplyProjector, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaApplyProjector)) {
			return form(oaApplyProjector, model);
		}
		oaApplyProjectorService.save(oaApplyProjector);
		addMessage(redirectAttributes, "保存投影仪申请成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaApplyProjector/?repage";
	}*/

	/*@RequiresPermissions("oa:oaApplyProjector:edit")
	@RequestMapping(value = "delete")
	public String delete(OaApplyProjector oaApplyProjector,
			RedirectAttributes redirectAttributes) {
		oaApplyProjectorService.delete(oaApplyProjector);
		addMessage(redirectAttributes, "删除投影仪申请成功");
		return "redirect:" + Global.getAdminPath()
				+ "/oa/oaApplyProjector/?repage";
	}*/

	/*@RequiresPermissions("oa:oaApplyProjector:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(OaApplyProjector oaApplyProjector,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "投影仪申请表" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			System.out.println("投影仪申请表" + fileName);
			Page<OaApplyProjector> page = oaApplyProjectorService
					.findWithNames(new Page<OaApplyProjector>(request,
							response, -1), oaApplyProjector);
			System.out.println("page" + page);
			new ExportExcel("投影仪申请表", OaApplyProjector.class)
					.setDataList(page.getList())
					.writeSelectBrower(response, request, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出投影仪表失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/projectorReport?repage";
	}*/

}