/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.oa.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Leave;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.oa.service.LeaveService;
import com.thinkgem.jeesite.modules.oa.service.OverTimeService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.JsonResult;

/**
 * 请假Controller
 * 
 * @author liuj
 * @version 2013-04-05
 */
@Controller
@RequestMapping(value = "mobile/${adminPath}/oa/leave")
public class AppLeaveController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected LeaveService leaveService;
	@Autowired
	private OverTimeService overTimeService;
	// @Autowired
	// private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;

	/*@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = { "form" })
	public String form(Leave leave, Model model) {
		model.addAttribute("leave", leave);
		return "modules/oa/leaveForm";
	}*/

	// /**
	// * 启动请假流程 不要了
	// * @param leave
	// */
	// @RequestMapping(value = "save", method = RequestMethod.POST)
	// @ResponseBody
	// public JsonResult<Map<String,Object>> save(Leave leave,String loginName)
	// {
	// String userId = UserUtils.getUser().getLoginName();
	// try {
	// Map<String, Object> variables = Maps.newHashMap();
	// leaveService.save(leave, variables);
	//
	// } catch (Exception e) {
	// logger.error("启动请假流程失败：", e);
	//
	// }
	// return new JsonResult<Map<String,Object>>(JsonResult.SUCCESS,"申请成功等待审核");
	// }

	/**
	 * 任务列表
	 * 
	 * @param leave
	 */

	// -------------------------------------------------------------------------------------------------------
	/**
	 * 提交请假申请
	 */
	@RequestMapping("leaveApply")
	@ResponseBody
	public JsonResult<Map<String, Object>> leaveApply(OaLeaved leave,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String oaNotifyRecordIds) {
		try {
			leave.setCopytoId(oaNotifyRecordIds);
			System.out.println(UserUtils.getUser().getLoginName());
			leaveService.leaveApply(leave, oaNotifyRecordIds,sysapp1,sysapp2,sysapp3,sysapp4);
			return new JsonResult<Map<String, Object>>("0", "提交成功了啊",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR, "提交失败",null);
		}
	}

	/**
	 * 再次提交申请
	 */
	/*@RequestMapping("leaveApplyAgain")
	public String leaveApplyAgain(OaLeaved leave, String taskId,
			String oaNotifyRecordIds) {
		leave.setCopytoId(oaNotifyRecordIds);
		leaveService.leaveApply(leave, oaNotifyRecordIds);
		return "redirect:" + adminPath + "/oa/leave/form";
	}*/
}
