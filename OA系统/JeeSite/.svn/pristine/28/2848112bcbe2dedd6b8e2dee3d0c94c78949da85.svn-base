/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.List;
import java.util.UUID;

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
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaSeal;
import com.thinkgem.jeesite.modules.oa.entity.OaSealRecord;
import com.thinkgem.jeesite.modules.oa.service.OaSealRecordService;
import com.thinkgem.jeesite.modules.oa.service.OaSealService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 公章Controller
 * 
 * @author lisp
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaSeal")
public class OaSealController extends BaseController {

	@Autowired
	private OaSealService oaSealService;
	@Autowired
	private OaSealRecordService oaSealRecordService;

	@ModelAttribute
	public OaSeal get(@RequestParam(required = false) String id) {
		OaSeal entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaSealService.get(id);
		}
		if (entity == null) {
			entity = new OaSeal();
		}
		return entity;
	}

	@RequiresPermissions("oa:oaSeal:view")
	@RequestMapping(value = { "list", "" })
	public String list(OaSeal oaSeal, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<OaSeal> page = oaSealService.findPage(new Page<OaSeal>(request,
				response), oaSeal);
		model.addAttribute("page", page);
		return "modules/oa/document/oaSealList";
	}

	@RequiresPermissions("oa:oaSeal:view")
	@RequestMapping(value = "form")
	public String form(OaSeal oaSeal, Model model) {
		model.addAttribute("oaSeal", oaSeal);
		return "modules/oa/document/oaSealForm";
	}

	@RequiresPermissions("oa:oaSeal:edit")
	@RequestMapping(value = "save")
	public String save(OaSeal oaSeal, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaSeal)) {
			return form(oaSeal, model);
		}
		oaSeal.setOwnerName(UserUtils.get(oaSeal.getOwnerId()).getName());
		oaSealService.save(oaSeal);
		addMessage(redirectAttributes, "保存公章成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaSeal/?repage";
	}

	@RequiresPermissions("oa:oaSeal:edit")
	@RequestMapping(value = "delete")
	public String delete(OaSeal oaSeal, RedirectAttributes redirectAttributes) {
		oaSealService.delete(oaSeal);
		addMessage(redirectAttributes, "删除公章成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaSeal/?repage";
	}

	@RequestMapping("showAllSeal")
	@ResponseBody
	public String showAllSeal() {
		List<OaSeal> list = oaSealService.findList(new OaSeal());
		for (OaSeal oaSeal : list) {
			if (oaSeal.getFilePath() != null
					&& !("".equals(oaSeal.getFilePath()))) {
				oaSeal.setFilePath(oaSeal.getFilePath().replace("|", ""));
			}
		}
		return new Gson().toJson(list);
	}

	/**
	 * 校验印章密码
	 */
	@RequestMapping("checkPassword")
	@ResponseBody
	public String checkPassword(String sealId, String password) {
		OaSeal oaSeal = oaSealService.get(sealId);
		if (oaSeal != null && password.equals(oaSeal.getUsePassword())) {
			OaSealRecord oaSealRecord = new OaSealRecord();
			oaSealRecord.setId(UUID.randomUUID().toString().replace("-", ""));
			oaSealRecord.setOaSealId(sealId);
			oaSealRecord.setIsNewRecord(true);
			oaSealRecordService.save(oaSealRecord);
			return new Gson().toJson("success");
		} else {
			return new Gson().toJson("error");
		}
	}

	/**
	 * 绑定公章历史记录
	 */
	@RequestMapping("modifySealRecord")
	@ResponseBody
	public String modifySealRecord(String sealId, String dispatchId) {
		List<OaSealRecord> list = oaSealRecordService.getBySealId(sealId);
		for (OaSealRecord record : list) {
			if ("".equals(record.getOaDispatchId())
					|| record.getOaDispatchId() == null) {
				record.setOaDispatchId(dispatchId);
				oaSealRecordService.save(record);
			}
		}
		return new Gson().toJson("success");
	}

	/**
	 * 查询公章路径
	 */
	@RequestMapping("returnSealPath")
	@ResponseBody
	public String returnSealPath(String sealId) {
		OaSeal oaSeal = oaSealService.get(sealId);
		String str = oaSeal.getFilePath().replace("|", "");
		return new Gson().toJson(str);
	}

}