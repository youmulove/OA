package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyMeeting;
import com.thinkgem.jeesite.modules.oa.entity.OfficeRoom;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.oa.service.OfficeRoomService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.persistence.Page;
@Controller
@RequestMapping(value = "${adminPath}/oa/officeRoomReport")
public class OfficeRoomReportController extends BaseController{
	@Autowired
	private OfficeRoomService officeRoomService;
	@Autowired
	private OaApplyOfficeroomService oAofficeRoomService;
	@Autowired
	private HistoryService historyService;
	

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
	
	//@RequiresPermissions("oa:officeRoom:view")
	
	
	

}
