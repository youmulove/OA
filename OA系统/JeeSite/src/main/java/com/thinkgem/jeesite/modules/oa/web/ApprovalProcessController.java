package com.thinkgem.jeesite.modules.oa.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApply;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.oa.entity.OaDispatch;
import com.thinkgem.jeesite.modules.oa.entity.OaDocApproval;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.oa.service.CurrencyService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyOfficeroomService;
import com.thinkgem.jeesite.modules.oa.service.OaApplyProjectorService;
import com.thinkgem.jeesite.modules.oa.service.OaDispatchService;
import com.thinkgem.jeesite.modules.oa.service.OaDocApprovalService;
import com.thinkgem.jeesite.modules.oa.service.OfficeRoomService;
import com.thinkgem.jeesite.modules.oa.service.OverTimeService;
import com.thinkgem.jeesite.modules.oa.service.ProjectorService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/oa/approvalProcess")
public class ApprovalProcessController extends BaseController {
	@Autowired
	private OverTimeService overTimeService;
	@Autowired
	private OfficeRoomService officeRoomService;
	@Autowired
	private ProjectorService projectorService;
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private OaApplyOfficeroomService oaApplyOfficeroomService;
	@Autowired
	private OaApplyProjectorService oaApplyProjectorService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private OaDocApprovalService oaDocApprovalService;
	@Autowired
	private OaDispatchService oaDispatchService;

	@RequestMapping("returnPage")
	public String returnPage() {
		return "modules/oa/approvalProcess";
	}

	/**
	 * 查询请假申请的审批人信息
	 */
	@RequestMapping("leaveApproval")
	// @ResponseBody
	public String leaveApproval(String day, ModelMap map) {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getId());
		appr.setApprovalType("leave");
		appr.setApprovalProcess("0");
		List<ApprovalRule> freeRule = approvalRuleService
				.findApprovalRuleSelective(appr);
		map.put("freeRule", freeRule);
		appr.setApprovalProcess("1");
		// 获取审批规则列表
		List<ApprovalRule> oneLevelList = approvalRuleService
				.findApprovalRuleSelective(appr);
		appr.setApprovalProcess("2");
		List<ApprovalRule> twoLevelList = approvalRuleService
				.findApprovalRuleSelective(appr);
		appr.setApprovalProcess("3");
		List<ApprovalRule> threeLevelList = approvalRuleService
				.findApprovalRuleSelective(appr);
		appr.setApprovalProcess("4");
		List<ApprovalRule> fourLevelList = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<ApprovalRule> list = new ArrayList<ApprovalRule>();
		if (oneLevelList.size() > 0) {
			if ((Double.parseDouble(day)) > (Double.parseDouble(oneLevelList
					.get(0).getApprovalStart()))
					&& (Double.parseDouble(day)) <= (Double
							.parseDouble(oneLevelList.get(0).getApprovalEnd()))) {
				list = oneLevelList;
			}
		}
		if (twoLevelList.size() > 0) {
			if ((Double.parseDouble(day)) > (Double.parseDouble(twoLevelList
					.get(0).getApprovalStart()))
					&& (Double.parseDouble(day)) <= (Double
							.parseDouble(twoLevelList.get(0).getApprovalEnd()))) {
				list = twoLevelList;
			}
		}
		if (threeLevelList.size() > 0) {
			if ((Double.parseDouble(day)) > (Double.parseDouble(threeLevelList
					.get(0).getApprovalStart()))
					&& (Double.parseDouble(day)) <= (Double
							.parseDouble(threeLevelList.get(0).getApprovalEnd()))) {
				list = threeLevelList;
			}
		}
		if (fourLevelList.size() > 0) {
			if ((Double.parseDouble(day)) > (Double.parseDouble(fourLevelList
					.get(0).getApprovalStart()))
					&& (Double.parseDouble(day)) <= (Double
							.parseDouble(fourLevelList.get(0).getApprovalEnd()))) {
				list = fourLevelList;
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		for (ApprovalRule app : list) {
			sb.setLength(0);
			if (app.getApprovalPerson() != null
					&& !("").equals(app.getApprovalPerson())) {
				String[] split = app.getApprovalPerson().split(",");
				for (int i = 0; i < split.length; i++) {
					// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
					User u = systemService.getUser(split[i]);
					if ((user.getOffice().getId())
							.equals(u.getOffice().getId())) {
						sb.append(u.getName() + ",");
						sb1.append(u.getId() + ",");
					} else {
						String parentIds = officeService
								.findAllDeptByUser(user).getParentIds();
						if (ArrayUtils.contains(parentIds.split(","), u
								.getOffice().getId())) {
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						}
					}
				}
				if ("".equals(sb.toString()) || (sb.toString()) == null) {
					for (int i = 0; i < split.length; i++) {
						User u = systemService.getUser(split[i]);
						sb.append(u.getName() + ",");
						sb1.append(u.getId() + ",");
					}
				}
				app.setApprovalPerson(sb.toString());
				app.setApprovalYuliu5(sb1.toString());
			}
			if (app.getApprovalRank() != null
					&& !("").equals(app.getApprovalRank())) {
				String split = app.getApprovalRank().toString();
				// app.setApprovalRank(sb.toString());
				app.setApprovalRank(split.toString());
			}

		}
		System.out.println(list);
		map.put("list", list);
		return "modules/oa/approvalUser2";
		// return new Gson().toJson(list);
	}

	/**
	 * 加班申请
	 */
	@RequestMapping("overTimeApply")
	@ResponseBody
	public String overTimeApply(OaOvertime overTime, String oaNotifyRecordIds,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String freePerson1, String freePerson2, String freePerson3,
			String freePerson4) {
		overTime.setCopytoId(oaNotifyRecordIds);

		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("overTime");
		appr.setApprovalStart(overTime.getIsHoliday());// 是否是法定节假日
		// 获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);

		overTimeService.overTimeApply(overTime, oaNotifyRecordIds, sysapp1,
				sysapp2, sysapp3, sysapp4, freePerson1, freePerson2,
				freePerson3, freePerson4);
		// return "modules/oa/approvalProcess";
		return new Gson().toJson("success");
	}

	/**
	 * 查询加班流程的审批人信息
	 */
	@RequestMapping("overTimeApproval")
	// @ResponseBody
	public String overTimeApproval(String isHoliday, ModelMap map) {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("overTime");
		appr.setApprovalStart(isHoliday);// 是否是法定节假日
		// 获取审批规则信息列表
		List<ApprovalRule> list1 = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<ApprovalRule> list = new ArrayList<ApprovalRule>();

		for (ApprovalRule approvalRule : list1) {
			if (!approvalRule.getApprovalRole().equals("0")) {
				list.add(approvalRule);
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		if (list.size() > 0) {
			for (ApprovalRule app : list) {
				sb.setLength(0);
				if (app.getApprovalPerson() != null
						&& !("").equals(app.getApprovalPerson())) {
					String[] split = app.getApprovalPerson().split(",");
					for (int i = 0; i < split.length; i++) {
						// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
						User u = systemService.getUser(split[i]);
						if ((user.getOffice().getId()).equals(u.getOffice()
								.getId())) {
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						} else {
							String parentIds = officeService.findAllDeptByUser(
									user).getParentIds();
							if (ArrayUtils.contains(parentIds.split(","), u
									.getOffice().getId())) {
								sb.append(u.getName() + ",");
								sb1.append(u.getId() + ",");
							}
						}
					}
					if ("".equals(sb.toString()) || (sb.toString()) == null) {
						for (int i = 0; i < split.length; i++) {
							User u = systemService.getUser(split[i]);
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						}
					}
					app.setApprovalPerson(sb.toString());
					app.setApprovalYuliu5(sb1.toString());
				}
				if (app.getApprovalRank() != null
						&& !("").equals(app.getApprovalRank())) {
					String split = app.getApprovalRank().toString();
					app.setApprovalRank(split.toString());
				}
			}

		}

		System.out.println(list);
		map.put("list", list);
		return "modules/oa/approvalUser";
		// return new Gson().toJson(appList);
	}

	/**
	 * 处理退回加班申请
	 */
	@RequestMapping("modifyOverTime")
	public String modifyOverTime(String taskId, ModelMap map, String flag,
			String nowposition) {
		Map<String, Object> variables = taskService.getVariablesLocal(taskId);
		variables.put("nowposition", nowposition);
		taskService.complete(taskId, variables);
		return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
	}

	/**
	 * 会议室申请
	 */
	@RequestMapping("officeRoomApply")
	@ResponseBody
	public String officeRoomApply(OaApplyOfficeroom officeRoom,
			String oaNotifyRecordIds, String sysapp1, String sysapp2,
			String sysapp3, String sysapp4, String freePerson1,
			String freePerson2, String freePerson3, String freePerson4) {
		OaApplyOfficeroom officeroom2 = new OaApplyOfficeroom();
		officeroom2.setOfficeroomId(officeRoom.getOfficeroomId());
		officeroom2.setCompanyId(UserUtils.getUser().getCompany().getId());
		officeroom2.setCompany(UserUtils.getUser().getCompany());
		officeroom2.setOfficeId(officeRoom.getOfficeroomId());
		List<OaApplyOfficeroom> list2 = oaApplyOfficeroomService
				.findList2(officeroom2);
		for (OaApplyOfficeroom oaroom : list2) {
			boolean b1 = belongCalendar(officeRoom.getStartTime(),
					oaroom.getStartTime(), oaroom.getEndTime());
			boolean b2 = belongCalendar(officeRoom.getEndTime(),
					oaroom.getStartTime(), oaroom.getEndTime());
			boolean b3 = belongCalendar(oaroom.getStartTime(),
					officeRoom.getStartTime(), officeRoom.getEndTime());
			boolean b4 = belongCalendar(oaroom.getEndTime(),
					officeRoom.getStartTime(), officeRoom.getEndTime());
			if (b1 || b2 || b3 || b4) {
				return new Gson().toJson("error");
			}
		}
		officeRoom.setCopytoId(oaNotifyRecordIds);
		officeRoomService.officeRoomApply(officeRoom, oaNotifyRecordIds,
				sysapp1, sysapp2, sysapp3, sysapp4, freePerson1, freePerson2,
				freePerson3, freePerson4);
		// return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
		return new Gson().toJson("ok");
	}

	@RequestMapping("officeRoomApplyPage")
	public String officeRoomApplyPage() {
		return "modules/oa/officeRoomApply";
	}

	/**
	 * 处理退回的会议室申请
	 */
	@RequestMapping("modifyOfficeRoom")
	public String modifyOfficeRoom(String taskId, ModelMap map, String flag,
			String nowposition) {
		Map<String, Object> variables = taskService.getVariablesLocal(taskId);
		variables.put("nowposition", nowposition);
		taskService.complete(taskId, variables);
		return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
	}

	/**
	 * 投影仪申请
	 */
	@RequestMapping("projectorApply")
	@ResponseBody
	public String projectorApply(OaApplyProjector projector,
			String oaNotifyRecordIds, String projectoroNo, String sysapp1,
			String sysapp2, String sysapp3, String sysapp4, String freePerson1,
			String freePerson2, String freePerson3, String freePerson4) {// projectoroNo传的是id
		OaApplyProjector oap = new OaApplyProjector();
		oap.setCompany(UserUtils.getUser().getCompany());
		oap.setAdapterId(projectoroNo);
		List<OaApplyProjector> list = oaApplyProjectorService.findList(oap);
		for (OaApplyProjector pro : list) {
			boolean b1 = belongCalendar(projector.getStartTime(),
					pro.getStartTime(), pro.getEndTime());
			boolean b2 = belongCalendar(projector.getEndTime(),
					pro.getStartTime(), pro.getEndTime());
			boolean b3 = belongCalendar(pro.getStartTime(),
					projector.getStartTime(), projector.getEndTime());
			boolean b4 = belongCalendar(pro.getEndTime(),
					projector.getStartTime(), projector.getEndTime());
			if (b1 || b2 || b3 || b4) {
				return new Gson().toJson("error");
			}
		}
		projector.setCopytoId(oaNotifyRecordIds);
		projectorService.projectorApply(projector, oaNotifyRecordIds,
				projectoroNo, sysapp1, sysapp2, sysapp3, sysapp4, freePerson1,
				freePerson2, freePerson3, freePerson4);
		return new Gson().toJson("ok");
	}

	/***
	 * 查询投影仪申请的审批人信息
	 */
	@RequestMapping("projectorApproval")
	// @ResponseBody
	public String projectorApproval(ModelMap map) {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("projector");
		// 获取审批规则信息列表
		List<ApprovalRule> list1 = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<ApprovalRule> list = new ArrayList<ApprovalRule>();

		for (ApprovalRule approvalRule : list1) {
			if (!approvalRule.getApprovalRole().equals("0")) {
				list.add(approvalRule);
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		if (list.size() > 0) {
			for (ApprovalRule app : list) {
				sb.setLength(0);
				if (app.getApprovalPerson() != null
						&& !("").equals(app.getApprovalPerson())) {
					String[] split = app.getApprovalPerson().split(",");
					for (int i = 0; i < split.length; i++) {
						// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
						User u = systemService.getUser(split[i]);
						if ((user.getOffice().getId()).equals(u.getOffice()
								.getId())) {
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						} else {
							String parentIds = officeService.findAllDeptByUser(
									user).getParentIds();
							if (ArrayUtils.contains(parentIds.split(","), u
									.getOffice().getId())) {
								sb.append(u.getName() + ",");
								sb1.append(u.getId() + ",");
							}
						}
					}
					if ("".equals(sb.toString()) || (sb.toString()) == null) {
						for (int i = 0; i < split.length; i++) {
							User u = systemService.getUser(split[i]);
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						}
					}
					app.setApprovalPerson(sb.toString());
					app.setApprovalYuliu5(sb1.toString());
				}
				if (app.getApprovalRank() != null
						&& !("").equals(app.getApprovalRank())) {
					String split = app.getApprovalRank().toString();
					app.setApprovalRank(split.toString());
				}
			}
		}
		System.out.println(list);
		map.put("list", list);
		return "modules/oa/approvalUser";
		// return new Gson().toJson(appList);
	}

	/**
	 * 处理退回的投影仪申请
	 */
	@RequestMapping("modifyProjector")
	public String modifyProjector(String taskId, ModelMap map, String flag,
			String nowposition) {
		Map<String, Object> variables = taskService.getVariablesLocal(taskId);
		variables.put("nowposition", nowposition);
		taskService.complete(taskId, variables);
		return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
	}

	/**
	 * 用章申请
	 */
	@RequestMapping("currencyApply")
	@ResponseBody
	public String currencyApply(OaApply currency, String oaNotifyRecordIds,
			String sysapp1, String sysapp2, String sysapp3, String sysapp4,
			String freePerson1, String freePerson2, String freePerson3,
			String freePerson4) {
		currency.setApplyName(UserUtils.getUser().getId());
		currencyService.currencyApply(currency, oaNotifyRecordIds, sysapp1,
				sysapp2, sysapp3, sysapp4, freePerson1, freePerson2,
				freePerson3, freePerson4);
		// return "modules/oa/approvalProcess";
		return new Gson().toJson("success");
	}

	/**
	 * 用章申请的审批人查询
	 */
	@RequestMapping("currencyApproval")
	// @ResponseBody
	public String currencyApproval(ModelMap map) {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("currency");
		// 获取审批规则信息列表
		List<ApprovalRule> list1 = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<ApprovalRule> list = new ArrayList<ApprovalRule>();

		for (ApprovalRule approvalRule : list1) {
			if (!approvalRule.getApprovalRole().equals("0")) {
				list.add(approvalRule);
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		if (list.size() > 0) {
			for (ApprovalRule app : list) {
				sb.setLength(0);
				if (app.getApprovalPerson() != null
						&& !("").equals(app.getApprovalPerson())) {
					String[] split = app.getApprovalPerson().split(",");
					for (int i = 0; i < split.length; i++) {
						// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
						User u = systemService.getUser(split[i]);
						if ((user.getOffice().getId()).equals(u.getOffice()
								.getId())) {
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						} else {
							String parentIds = officeService.findAllDeptByUser(
									user).getParentIds();
							if (ArrayUtils.contains(parentIds.split(","), u
									.getOffice().getId())) {
								sb.append(u.getName() + ",");
								sb1.append(u.getId() + ",");
							}
						}
					}
					if ("".equals(sb.toString()) || (sb.toString()) == null) {
						for (int i = 0; i < split.length; i++) {
							User u = systemService.getUser(split[i]);
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						}
					}
					app.setApprovalPerson(sb.toString());
					app.setApprovalYuliu5(sb1.toString());
				}
				if (app.getApprovalRank() != null
						&& !("").equals(app.getApprovalRank())) {
					String split = app.getApprovalRank().toString();
					app.setApprovalRank(split.toString());
				}
			}
		}
		System.out.println(list);
		map.put("list", list);
		return "modules/oa/approvalUser";
		// return new Gson().toJson(list);
	}

	/**
	 * 处理退回的用章申请
	 */
	@RequestMapping("modifyCurrency")
	public String modifyCurrency(String taskId, ModelMap map, String flag,
			String nowposition) {
		Map<String, Object> variables = taskService.getVariablesLocal(taskId);
		variables.put("nowposition", nowposition);
		taskService.complete(taskId, variables);
		return "redirect:" + adminPath + "/oa/approvalProcess/returnPage";
	}

	public static boolean belongCalendar(Date time, Date from, Date to) {
		if (from == null || to == null) {
			return false;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(time);

		Calendar after = Calendar.getInstance();
		after.setTime(from);

		Calendar before = Calendar.getInstance();
		before.setTime(to);

		if (date.after(after) && date.before(before)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 */
	@RequestMapping("modifyDocument")
	public String modifyDocument(HttpServletRequest request, String taskId,
			ModelMap map, String flag, String nowposition) {
		// 修改发文单发起状态
		Map<String, Object> variables = taskService.getVariables(taskId);
		OaDocApproval oaDocApproval = (OaDocApproval) variables
				.get("oaDocApproval");
		OaDispatch dispatch = oaDispatchService.get(oaDocApproval
				.getOaDispatchId());
		dispatch.setYuliu1("2");
		oaDispatchService.save(dispatch);
		// map.put("taskId", taskId);
		request.getSession().setAttribute("taskId", taskId);
		return "redirect:" + adminPath + "/oa/oaDispatch/list";
	}

	/**
	 * 办公室申请的审批人查询
	 */
	@RequestMapping("offficeroomApproval")
	public String offficeroomApproval(ModelMap map) {
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("officeRoom");
		// 获取审批规则信息列表
		List<ApprovalRule> list1 = approvalRuleService
				.findApprovalRuleSelective(appr);
		List<ApprovalRule> list = new ArrayList<ApprovalRule>();

		for (ApprovalRule approvalRule : list1) {
			if (!approvalRule.getApprovalRole().equals("0")) {
				list.add(approvalRule);
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		if (list.size() > 0) {
			for (ApprovalRule app : list) {
				sb.setLength(0);
				if (app.getApprovalPerson() != null
						&& !("").equals(app.getApprovalPerson())) {
					String[] split = app.getApprovalPerson().split(",");
					for (int i = 0; i < split.length; i++) {
						// 根据申请人所在部门和公司展示审批人 查询申请人的部门父ids和审批人的部门id
						User u = systemService.getUser(split[i]);
						if ((user.getOffice().getId()).equals(u.getOffice()
								.getId())) {
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						} else {
							String parentIds = officeService.findAllDeptByUser(
									user).getParentIds();
							if (ArrayUtils.contains(parentIds.split(","), u
									.getOffice().getId())) {
								sb.append(u.getName() + ",");
								sb1.append(u.getId() + ",");
							}
						}
					}
					if ("".equals(sb.toString()) || (sb.toString()) == null) {
						for (int i = 0; i < split.length; i++) {
							User u = systemService.getUser(split[i]);
							sb.append(u.getName() + ",");
							sb1.append(u.getId() + ",");
						}
					}
					app.setApprovalPerson(sb.toString());
					app.setApprovalYuliu5(sb1.toString());
				}
				if (app.getApprovalRank() != null
						&& !("").equals(app.getApprovalRank())) {
					String split = app.getApprovalRank().toString();
					app.setApprovalRank(split.toString());
				}
			}
		}
		System.out.println(list);
		map.put("list", list);
		return "modules/oa/approvalUser1";
	}
}
