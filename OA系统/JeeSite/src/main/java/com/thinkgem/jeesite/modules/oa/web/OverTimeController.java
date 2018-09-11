
package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OverTime;
import com.thinkgem.jeesite.modules.oa.service.OverTimeService;

/**
 * 加班申请Controller
 * @author jhm
 * @version 2017-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/overTime")
public class OverTimeController extends BaseController {

	@Autowired
	private OverTimeService overTimeService;
	
	@ModelAttribute
	public OverTime get(@RequestParam(required=false) String id) {
		OverTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = overTimeService.get(id);
		}
		if (entity == null){
			entity = new OverTime();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:overTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(OverTime overTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OverTime> page = overTimeService.findPage(new Page<OverTime>(request, response), overTime); 
		model.addAttribute("page", page);
		return "modules/oa/overTimeList";
	}

	@RequiresPermissions("oa:overTime:view")
	@RequestMapping(value = "form")
	public String form(OverTime overTime, Model model) {
		model.addAttribute("overTime", overTime);
		return "modules/oa/overTimeForm";
	}

	@RequiresPermissions("oa:overTime:edit")
	@RequestMapping(value = "save")
	public String save(OverTime overTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, overTime)){
			return form(overTime, model);
		}
		overTimeService.save(overTime);
		addMessage(redirectAttributes, "保存加班申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/overTime/?repage";
	}
	
	@RequiresPermissions("oa:overTime:edit")
	@RequestMapping(value = "delete")
	public String delete(OverTime overTime, RedirectAttributes redirectAttributes) {
		overTimeService.delete(overTime);
		addMessage(redirectAttributes, "删除加班申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/overTime/?repage";
	}

}