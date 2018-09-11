package com.thinkgem.jeesite.modules.oa.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.service.ApprovalRuleService;
import com.thinkgem.jeesite.modules.sys.entity.SysRank;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SysRankService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/oa/approvalRule")
public class ApprovalRuleController extends BaseController {

	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SysRankService sysRankService;

	/**
	 * 查询已设置审批
	 */
	@RequestMapping("alreadyProcess")
	public String alreadyProcess(ModelMap map, String type) {
		ApprovalRule rule = new ApprovalRule();
		rule.setApprovalType(type);
		rule.setApprovalCompany(UserUtils.getUser().getCompany().getId());
		List<ApprovalRule> list1 = approvalRuleService
				.findApprovalRuleSelective(rule);
		if (list1.size() == 0) {
			map.put("flag", "error");
			return "error/error";
		}
		rule.setApprovalProcess(list1.get(list1.size() - 1)
				.getApprovalProcess());
		List<ApprovalRule> list = approvalRuleService
				.findApprovalRuleSelective(rule);
		if ("leave".equals(type)) {
			for (ApprovalRule app : list) {
				if (app.getApprovalPerson() != null
						&& app.getApprovalRank() == null) {
					String[] split = app.getApprovalPerson().split(",");
					StringBuffer sb = new StringBuffer();
					StringBuffer sb2 = new StringBuffer();
					for (int i = 0; i < split.length; i++) {
						User u = systemService.getUser(split[i]);
						if (u != null) {
							sb.append(u.getName() + ",");
							sb2.append(u.getId() + ",");
						}
					}
					app.setApprovalPerson(sb.toString());
					app.setApprovalYuliu1(sb2.toString());
				}
				if (app.getApprovalPerson() == null
						&& app.getApprovalRank() != null) {
					SysRank sysRank = sysRankService.get(app.getApprovalRank());
					app.setApprovalRank(sysRank.getName());
					// app.setApprovalRank("zhiji");
				}

			}
			map.put("list", list);
		} else {
			for (ApprovalRule app : list1) {
				if (app.getApprovalPerson() != null
						&& app.getApprovalRank() == null) {
					String[] split = app.getApprovalPerson().split(",");
					StringBuffer sb = new StringBuffer();
					StringBuffer sb2 = new StringBuffer();
					for (int i = 0; i < split.length; i++) {
						User u = systemService.getUser(split[i]);
						if (u != null) {
							sb.append(u.getName() + ",");
							sb2.append(u.getId() + ",");
						}
					}
					app.setApprovalPerson(sb.toString());
					app.setApprovalYuliu1(sb2.toString());
				}
				if (app.getApprovalPerson() == null
						&& app.getApprovalRank() != null) {
					SysRank sysRank = sysRankService.get(app.getApprovalRank());
					app.setApprovalRank(sysRank.getName());
				}
			}
			map.put("list", list1);
		}
		map.put("type", type);
		return "modules/oa/approvalProcessAdmin2";
	}

	/**
	 * 设置本公司审批规则
	 */
	@RequestMapping("addApprovalRule")
	@ResponseBody
	public String addApprovalRule(ApprovalRule approvalRule,
			String oaNotifyRecordIds1, String oaNotifyRecordIds2,
			String oaNotifyRecordIds3, String oaNotifyRecordIds4,
			String oaNotifyRecordIds5, String oaNotifyRecordIds6,
			String oaNotifyRecordIds7, String oaNotifyRecordIds8,
			String oaNotifyRecordIds9, String oaNotifyRecordIds10,
			String oaNotifyRecordIds11, String oaNotifyRecordIds12,
			String oaNotifyRecordIds13, String oaNotifyRecordIds14,
			String oaNotifyRecordIds15, String oaNotifyRecordIds16,
			String oaNotifyRecordIds17, String oaNotifyRecordIds18,
			String oaNotifyRecordIds19, String oaNotifyRecordIds20,
			String start1, String end1, String start2, String end2,
			String start3, String end3, String start4, String end4,
			String userType1, String userType2, String userType3,
			String userType4) {
		// 先查询该审批类型是否存在
		ApprovalRule rule = new ApprovalRule();
		rule.setApprovalType(approvalRule.getApprovalType());
		rule.setApprovalCompany(UserUtils.getUser().getCompany().getId());
		if ("overTime".equals(approvalRule.getApprovalType())) {
			rule.setApprovalStart(approvalRule.getApprovalStart());
			approvalRuleService.deleteByApprovalRule(rule);
		} else {
			approvalRuleService.deleteByApprovalRule(rule);
		}
		/*
		 * if("leave".equals(approvalRule.getApprovalType())){
		 * approvalRuleService.deleteByApprovalRule(rule); }else{
		 * rule.setApprovalProcess(approvalRule.getApprovalProcess());
		 * approvalRuleService.deleteByApprovalRule(rule); }
		 */

		if (oaNotifyRecordIds1 == null || oaNotifyRecordIds1.equals("")) {
			if (oaNotifyRecordIds5 != null && !(oaNotifyRecordIds5.equals(""))) {
				oaNotifyRecordIds1 = oaNotifyRecordIds5;
				oaNotifyRecordIds2 = oaNotifyRecordIds6;
				oaNotifyRecordIds3 = oaNotifyRecordIds7;
				oaNotifyRecordIds4 = oaNotifyRecordIds8;
			} else if (oaNotifyRecordIds9 != null
					&& !(oaNotifyRecordIds9.equals(""))) {
				oaNotifyRecordIds1 = oaNotifyRecordIds9;
				oaNotifyRecordIds2 = oaNotifyRecordIds10;
				oaNotifyRecordIds3 = oaNotifyRecordIds11;
				oaNotifyRecordIds4 = oaNotifyRecordIds12;
			} else if (oaNotifyRecordIds13 != null
					&& !(oaNotifyRecordIds13.equals(""))) {
				oaNotifyRecordIds1 = oaNotifyRecordIds13;
				oaNotifyRecordIds2 = oaNotifyRecordIds14;
				oaNotifyRecordIds3 = oaNotifyRecordIds15;
				oaNotifyRecordIds4 = oaNotifyRecordIds16;
			} else if (oaNotifyRecordIds17 != null
					&& !(oaNotifyRecordIds17.equals(""))) {
				oaNotifyRecordIds1 = oaNotifyRecordIds17;
				oaNotifyRecordIds2 = oaNotifyRecordIds18;
				oaNotifyRecordIds3 = oaNotifyRecordIds19;
				oaNotifyRecordIds4 = oaNotifyRecordIds20;
			}
		}

		// 如果审批人为职级划分直接插入职级名称 若是具体到人，以逗号隔开进行插入
		approvalRule.setApprovalCompany(UserUtils.getUser().getCompany()
				.getId());// 设置审批规则对应的公司
		if (approvalRule.getApprovalProcess().equals("1")) {
			if ("1".equals(userType1)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds1));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds1);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("1");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
		}
		if (approvalRule.getApprovalProcess().equals("2")) {
			if ("leave".equals(approvalRule.getApprovalType())) {
				approvalRule.setApprovalProcess("1");// ---
				if ("1".equals(userType1)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds1));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds1);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("1");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
				approvalRuleService.insertApprovalRule(approvalRule);
			}
			approvalRule.setApprovalProcess("2");// ---
			if ("1".equals(userType1)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds1));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds1);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("1");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
			if ("1".equals(userType2)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds2));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds2);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("2");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start2);
				approvalRule.setApprovalEnd(end2);
			}
			approvalRuleService.insertApprovalRule(approvalRule);

		}
		if (approvalRule.getApprovalProcess().equals("3")) {
			if ("leave".equals(approvalRule.getApprovalType())) {
				approvalRule.setApprovalProcess("1");// ---
				if ("1".equals(userType1)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds1));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds1);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("1");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
				approvalRuleService.insertApprovalRule(approvalRule);
				// ----
				approvalRule.setApprovalProcess("2");// ---
				if ("1".equals(userType1)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds1));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds1);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("1");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
				approvalRuleService.insertApprovalRule(approvalRule);
				if ("1".equals(userType2)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds2));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds2);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("2");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start2);
				approvalRule.setApprovalEnd(end2);
				approvalRuleService.insertApprovalRule(approvalRule);
			}
			approvalRule.setApprovalProcess("3");// ---
			if ("1".equals(userType1)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds1));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds1);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("1");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
			if ("1".equals(userType2)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds2));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds2);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("2");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start2);
				approvalRule.setApprovalEnd(end2);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
			if ("1".equals(userType3)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds3));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds3);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("3");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start3);
				approvalRule.setApprovalEnd(end3);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
		}
		if (approvalRule.getApprovalProcess().equals("4")) {
			if ("leave".equals(approvalRule.getApprovalType())) {
				approvalRule.setApprovalProcess("1");// ---
				if ("1".equals(userType1)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds1));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds1);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("1");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
				approvalRuleService.insertApprovalRule(approvalRule);
				// ----
				approvalRule.setApprovalProcess("2");// ---
				if ("1".equals(userType1)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds1));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds1);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("1");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
				approvalRuleService.insertApprovalRule(approvalRule);
				if ("1".equals(userType2)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds2));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds2);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("2");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start2);
				approvalRule.setApprovalEnd(end2);
				approvalRuleService.insertApprovalRule(approvalRule);
				// ----
				approvalRule.setApprovalProcess("3");// ---
				if ("1".equals(userType1)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds1));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds1);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("1");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
				approvalRuleService.insertApprovalRule(approvalRule);
				if ("1".equals(userType2)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds2));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds2);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("2");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start2);
				approvalRule.setApprovalEnd(end2);
				approvalRuleService.insertApprovalRule(approvalRule);
				if ("1".equals(userType3)) {
					approvalRule
							.setApprovalPerson(returnStr(oaNotifyRecordIds3));
					approvalRule.setApprovalRank("");
				} else {
					approvalRule.setApprovalRank(oaNotifyRecordIds3);
					approvalRule.setApprovalPerson("");
				}
				approvalRule.setApprovalRole("3");
				approvalRule.setApprovaleId(UUID.randomUUID().toString()
						.replace("-", ""));
				approvalRule.setApprovalStart(start3);
				approvalRule.setApprovalEnd(end3);
				approvalRuleService.insertApprovalRule(approvalRule);
			}
			approvalRule.setApprovalProcess("4");// ---
			if ("1".equals(userType1)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds1));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds1);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("1");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start1);
				approvalRule.setApprovalEnd(end1);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
			if ("1".equals(userType2)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds2));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds2);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("2");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start2);
				approvalRule.setApprovalEnd(end2);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
			if ("1".equals(userType3)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds3));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds3);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("3");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start3);
				approvalRule.setApprovalEnd(end3);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
			if ("1".equals(userType4)) {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds4));
				approvalRule.setApprovalRank("");
			} else {
				approvalRule.setApprovalRank(oaNotifyRecordIds4);
				approvalRule.setApprovalPerson("");
			}
			approvalRule.setApprovalRole("4");
			approvalRule.setApprovaleId(UUID.randomUUID().toString()
					.replace("-", ""));
			if (!("overTime".equals(approvalRule.getApprovalType()))) {
				approvalRule.setApprovalStart(start4);
				approvalRule.setApprovalEnd(end4);
			}
			approvalRuleService.insertApprovalRule(approvalRule);
		}
		// return "redirect:" + adminPath +
		// "/oa/approvalProcessAdmin/returnPage";
		return new Gson().toJson("success");
	}

	/**
	 * 修改审批人页面弹框
	 */
	@RequestMapping("modifyPage")
	public String modifyPage(String aa, String approvalRole, ModelMap map,
			String isOver, String approvalRank) {
		ApprovalRule approvalRule = new ApprovalRule();
		approvalRule.setApprovalType(aa);
		approvalRule.setApprovalRole(approvalRole);
		approvalRule.setApprovalCompany(UserUtils.getUser().getCompany()
				.getId());
		List<ApprovalRule> list = approvalRuleService
				.findApprovalRuleSelective(approvalRule);
		if (null != approvalRank && !("").equals(approvalRank)) {
			String sid = list.get(0).getApprovalRank() == null ? list.get(1)
					.getApprovalRank() : list.get(0).getApprovalRank();
			String sname = sysRankService.get(sid).getName();
			map.put("sid", sid);
			map.put("sname", sname);
		}
		if (null == approvalRank || ("").equals(approvalRank)) {
			String[] split = list.get(0).getApprovalPerson().split(",");
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				User u = systemService.getUser(split[i]);
				sb.append(u.getName() + ",");
				sb2.append(u.getId() + ",");
			}
			map.put("id", sb2.toString());
			map.put("name", sb.toString());
		}
		map.put("type", aa);
		map.put("approvalRole", approvalRole);
		map.put("rule", list.get(0));
		map.put("isOver", isOver);
		return "modules/oa/modifyProcessRule";
	}

	/**
	 * 修改本公司审批规则
	 */
	@RequestMapping("modifyApprovalRule")
	@ResponseBody
	public String modifyApprovalRule(String type, String isOver, String start,
			String approvalRole, String end, String oaNotifyRecordIds,
			String approvalRankIds) {
		ApprovalRule approvalRule = new ApprovalRule();
		approvalRule.setApprovalType(type);
		if ("leave".equals(type)) {
			approvalRule.setApprovalStart(start);
			approvalRule.setApprovalEnd(end);
			approvalRule.setApprovalRole(approvalRole);
			if (approvalRankIds != null) {
				approvalRule.setApprovalRank(approvalRankIds);
			} else {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds));
			}
		} else if ("overTime".equals(type)) {
			approvalRule.setApprovalStart(isOver);
			approvalRule.setApprovalRole(approvalRole);
			if (approvalRankIds != null) {
				approvalRule.setApprovalRank(approvalRankIds);
			} else {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds));
			}
		} else if ("officeRoom".equals(type)) {
			approvalRule.setApprovalRole(approvalRole);
			if (approvalRankIds != null) {
				approvalRule.setApprovalRank(approvalRankIds);
			} else {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds));
			}
		} else if ("projector".equals(type)) {
			approvalRule.setApprovalRole(approvalRole);
			if (approvalRankIds != null) {
				approvalRule.setApprovalRank(approvalRankIds);
			} else {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds));
			}
		} else /* if("currency".equlas(type)) */{
			approvalRule.setApprovalRole(approvalRole);
			if (approvalRankIds != null) {
				approvalRule.setApprovalRank(approvalRankIds);
			} else {
				approvalRule.setApprovalPerson(returnStr(oaNotifyRecordIds));
			}
		}
		approvalRuleService.updateApproval(approvalRule);
		return new Gson().toJson("ok");
	}

	public static String returnStr(String string) {
		String[] split = string.split(",");
		String str = "";
		for (int i = 0; i < split.length; i++) {
			str += UserUtils.get(split[i]).getId() + ",";
		}
		return str;
	}

	/**
	 * 审批人弹框
	 */
	@RequestMapping("addApprover")
	public String addApprover(ModelMap map, String type, String nth) {
		map.put("type", type);
		map.put("nth", nth);
		return "modules/oa/addApprover";
	}

	/**
	 * 设置自由审批规则
	 */
	@RequestMapping("freeApprovalRule")
	@ResponseBody
	public String freeApprovalRule(ApprovalRule approvalRule) {
		// 先查询该审批类型是否存在
		ApprovalRule rule = new ApprovalRule();
		rule.setApprovalType(approvalRule.getApprovalType());
		rule.setApprovalCompany(UserUtils.getUser().getCompany().getId());
		if ("overTime".equals(approvalRule.getApprovalType())) {
			rule.setApprovalStart(approvalRule.getApprovalStart());
			approvalRuleService.deleteByApprovalRule(rule);
		} else {
			approvalRuleService.deleteByApprovalRule(rule);
		}
		approvalRule.setApprovalCompany(UserUtils.getUser().getCompany()
				.getId());// 设置审批规则对应的公司
		approvalRule.setApprovaleId(UUID.randomUUID().toString()
				.replace("-", ""));
		approvalRule.setApprovalProcess("0");
		approvalRule.setApprovalRank("free");
		approvalRule.setApprovalPerson("free");
		if (("leave".equals(approvalRule.getApprovalType()))) {
			approvalRule.setApprovalStart("0");
			approvalRule.setApprovalEnd("1000");
		}
		approvalRule.setApprovalRole("0");
		approvalRuleService.insertApprovalRule(approvalRule);
		return new Gson().toJson("success");
	}

}
