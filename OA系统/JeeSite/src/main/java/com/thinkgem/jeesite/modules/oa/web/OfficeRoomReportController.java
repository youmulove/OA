package com.thinkgem.jeesite.modules.oa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.oa.service.OfficeRoomService;

@Controller
@RequestMapping(value = "${adminPath}/oa/officeRoomReport")
public class OfficeRoomReportController extends BaseController {
	@Autowired
	private OfficeRoomService officeRoomService;

	@ModelAttribute
	public OfficeRoom get(@RequestParam(required = false) String id) {
		OfficeRoom entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = officeRoomService.get(id);
		}
		if (entity == null) {
			entity = new OfficeRoom();
		}
		return entity;
	}

	// @RequiresPermissions("oa:officeRoom:view")

}
