/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.utils.ListToString;
import com.thinkgem.jeesite.modules.oa.dao.OaApplyOfficeroomDao;
import com.thinkgem.jeesite.modules.oa.entity.ApprovalRule;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyOfficeroom;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 办公室申请Service
 * 
 * @author jhm
 * @version 2017-09-28
 */
@Service
@Transactional(readOnly = true)
public class OaApplyOfficeroomService extends
		CrudService<OaApplyOfficeroomDao, OaApplyOfficeroom> {
	@Autowired
	private OaApplyOfficeroomDao OaApplyOfficeroomDao;
	@Autowired
	private ApprovalRuleService approvalRuleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private OfficeService officeService;

	public OaApplyOfficeroom get(String id) {
		return super.get(id);
	}

	public List<OaApplyOfficeroom> findList(OaApplyOfficeroom oaApplyOfficeroom) {
		return super.findList(oaApplyOfficeroom);
	}

	public List<OaApplyOfficeroom> findList2(OaApplyOfficeroom oaApplyOfficeroom) {
		return OaApplyOfficeroomDao.findList2(oaApplyOfficeroom);
	}

	public Page<OaApplyOfficeroom> findPage(Page<OaApplyOfficeroom> page,
			OaApplyOfficeroom oaApplyOfficeroom) {
		return super.findPage(page, oaApplyOfficeroom);
	}

	@Transactional(readOnly = false)
	public void save(OaApplyOfficeroom oaApplyOfficeroom) {
		super.save(oaApplyOfficeroom);
	}

	@Transactional(readOnly = false)
	public void delete(OaApplyOfficeroom oaApplyOfficeroom) {
		super.delete(oaApplyOfficeroom);
	}

	public List<OaApplyOfficeroom> findAllRoom() {
		return OaApplyOfficeroomDao.findAllRoom();
	}

	/**
	 * 会议室申请
	 */
	public void officeRoomApply(OaApplyOfficeroom oaApplyOfficeroom) {
		// 设置查询条件 本公司
		User user = UserUtils.getUser();
		ApprovalRule appr = new ApprovalRule();
		appr.setApprovalCompany(user.getCompany().getName());
		appr.setApprovalType("officeRoom");
		// 获取审批规则信息列表
		List<ApprovalRule> appList = approvalRuleService
				.findApprovalRuleSelective(appr);
		// 选择对应的流程图信息
		String processDefinitionKey = appList.get(0).getApprovalProcess();
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		for (ApprovalRule app : appList) {
			// 判断是几级审批
			if (app.getApprovalRole().equals("1")) {
				// 判断是根据职级确定审批人还是指定具体人
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					List<User> list = systemService.findUserBysystrankId(app
							.getApprovalRank());
					ArrayList<String> list2 = new ArrayList<String>();
					for (User u : list) {
						list2.add(u.getId());
					}
					str1 = ListToString.listToString(list2);
				} else {
					str1 = app.getApprovalPerson();
				}
			} else if (app.getApprovalRole().equals("2")) {
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					List<User> list = systemService.findUserBysystrankId(app
							.getApprovalRank());
					ArrayList<String> list2 = new ArrayList<String>();
					for (User u : list) {
						list2.add(u.getId());
					}
					str2 = ListToString.listToString(list2);
				} else {
					str2 = app.getApprovalPerson();
				}
			} else if (app.getApprovalRole().equals("3")) {
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					List<User> list = systemService.findUserBysystrankId(app
							.getApprovalRank());
					ArrayList<String> list2 = new ArrayList<String>();
					for (User u : list) {
						list2.add(u.getId());
					}
					str3 = ListToString.listToString(list2);
				} else {
					str3 = app.getApprovalPerson();
				}
			} else if (app.getApprovalRole().equals("4")) {
				if (app.getApprovalRank() != null
						&& !app.getApprovalRank().equals("")) {
					// ***********************
					List<User> list = systemService.findUserBysystrankId(app
							.getApprovalRank());
					ArrayList<String> list2 = new ArrayList<String>();
					for (User u : list) {
						list2.add(u.getId());
					}
					str4 = ListToString.listToString(list2);
				} else {
					str4 = app.getApprovalPerson();
				}
			}
		}
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("applyUserId", user.getId());// 设置流程发起人
		variables.put("applyName", user.getName());
		variables.put("officeRoom", oaApplyOfficeroom); // 类型为会议室申请类型
		variables.put("type", "officeRoom");
		variables.put("oneLevelApproval", str1);
		variables.put("twoLevelApproval", str2);
		variables.put("threeLevelApproval", str3);
		variables.put("fourLevelApproval", str4);
		// 存入流程启动人
		identityService.setAuthenticatedUserId(user.getId());
		// 启动流程
		runtimeService.startProcessInstanceByKey(processDefinitionKey,
				variables);
		// 完成申请
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee(UserUtils.getUser().getId())
				.orderByTaskCreateTime().desc().list();
		taskService.complete(list.get(0).getId());
		// 插入会议室申请记录表
		super.save(oaApplyOfficeroom);
	}

	/**
	 * 返回当前用户所在公司的所有部门
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> findApplyOfficeroom(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allDept", officeDao.findDept(user));

		return map;
	}

	/**
	 * 含有审批人的信息同用户个人信息导出
	 * 
	 */
	public String getApplyNames(String auditIds) {
		String fir = "";
		if (auditIds != null) {
			String[] firSplit = auditIds.split(",");
			for (int i = 0; i < firSplit.length; i++) {
				User user = UserUtils.get(firSplit[i]);
				if (user == null) {
					continue;
				}
				fir += user.getName() + ",";
			}
			if (fir.length() > 0) {
				fir = fir.substring(0, fir.length() - 1);
			}
		}
		return fir;
	}

	public String getApplyOfficeNames(String officeIds) {
		String[] firSplit = officeIds.split(",");
		String fir = "";
		// StringBuffer buf = new StringBuffer();
		for (int i = 2; i < firSplit.length; i++) {
			Office office = officeService.get(firSplit[i]);
			if (office == null) {
				continue;
			}
			fir += office.getName() + "--";
		}
		if (fir.length() > 0) {
			fir = fir.substring(0, fir.length() - 2);

		}
		return fir;
	}

	public List<OaApplyOfficeroom> getAllApplyNames(
			OaApplyOfficeroom oaApplyOfficeroom) {
		List<OaApplyOfficeroom> reportList = OaApplyOfficeroomDao
				.findList3(oaApplyOfficeroom);
		for (OaApplyOfficeroom officeRoom : reportList) {
			String firId = officeRoom.getAuditFirId();
			String secId = officeRoom.getAuditSecId();
			String thrId = officeRoom.getAuditThrId();
			String fouId = officeRoom.getAuditFouId();
			String copyId = officeRoom.getCopytoId();
			String firState = officeRoom.getAuditFstate();
			String secState = officeRoom.getAuditSstate();
			String thrState = officeRoom.getAuditTstate();
			String fouState = officeRoom.getAuditFouState();
			String officeParentId = officeRoom.getOfficeId();
			String officeIds = officeRoom.getYuliu2();
			String allOfficeIds = officeIds + "," + officeParentId;

			// SimpleDateFormat sdf = new SimpleDateFormat(
			// " yyyy-MM-dd HH:mm:ss " );
			// String endTime = sdf.format(officeRoom.getEndTime());
			// System.out.println("结束日期"+endTime);
			if (firId == null && secId == null && thrId == null
					&& fouId == null) {
				// System.out.println("一级审批都没有");
			} else {
				if (firId != null && secId == null && thrId == null
						&& fouId == null) {
					// System.out.println("有一级审批");
					officeRoom.setAuditFirId(this.getApplyNames(firId));
					if ("y".equals(firState)) {
						officeRoom.setAuditFstate("通过");
						officeRoom.setEndAuditState("通过");
					} else if ("n".equals(firState)) {
						officeRoom.setAuditFstate("未通过");
						officeRoom.setEndAuditState("驳回");
					} else {
						officeRoom.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId == null
						&& fouId == null) {
					// System.out.println("有二级审批");

					officeRoom.setAuditFirId(this.getApplyNames(firId));
					officeRoom.setAuditSecId(this.getApplyNames(secId));

					if (firState != null && secState != null) {
						if ("y".equals(firState) && "y".equals(secState)) {
							officeRoom.setAuditFstate("通过");
							officeRoom.setAuditSstate("通过");
							officeRoom.setEndAuditState("通过");
						} else {
							officeRoom.setAuditFstate((firState == "y") ? "通过"
									: "未通过");
							officeRoom.setAuditSstate((secState == "y") ? "通过"
									: "未通过");
							officeRoom.setEndAuditState("驳回");
						}

					} else if (firState == null && secState == null) {
						officeRoom.setEndAuditState("待审核");
					} else if (firState != null && secState == null) {
						officeRoom.setAuditFstate(("y".equals(firState)) ? "通过"
								: "未通过");
						officeRoom
								.setEndAuditState(("y".equals(firState)) ? "待审核"
										: "驳回");
					} else if (firState == null && secState != null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate(("y".equals(secState)) ? "通过"
								: "未通过");
						officeRoom
								.setEndAuditState(("y".equals(secState)) ? "通过"
										: "驳回");
					}

				} else if (firId != null && secId != null && thrId != null
						&& fouId == null) {
					// System.out.println("有三级审批");
					officeRoom.setAuditFirId(this.getApplyNames(firId));
					officeRoom.setAuditSecId(this.getApplyNames(secId));
					officeRoom.setAuditThrId(this.getApplyNames(thrId));
					if (firState != null && secState != null
							&& thrState != null) {
						if ("y".equals(firState) && "y".equals(secState)
								&& "y".equals(thrState)) {
							officeRoom.setAuditFstate("通过");
							officeRoom.setAuditSstate("通过");
							officeRoom.setAuditTstate("通过");
							officeRoom.setEndAuditState("通过");
						} else if ("n".equals(firState) || "n".equals(secState)
								|| "n".equals(thrState)) {
							officeRoom
									.setAuditFstate("y".equals(firState) ? "通过"
											: "未通过");
							officeRoom
									.setAuditSstate("y".equals(secState) ? "通过"
											: "未通过");
							officeRoom
									.setAuditTstate("y".equals(thrState) ? "通过"
											: "未通过");
							officeRoom.setEndAuditState("驳回");
						}
					} else if (firState != null && secState != null
							&& thrState == null) {
						officeRoom.setAuditFstate("y".equals(firState) ? "通过"
								: "未通过");
						officeRoom.setAuditSstate("y".equals(secState) ? "通过"
								: "未通过");
						// officeRoom.setAuditTstate("y".equals(thrState)?"通过":"未通过");
						if ("y".equals(firState) && "y".equals(secState)) {

							officeRoom.setAuditTstate("待审核");
						}
						officeRoom.setEndAuditState("驳回");
					} else if (firState != null && secState == null
							&& thrState == null) {
						officeRoom.setAuditFstate("y".equals(firState) ? "通过"
								: "未通过");
						// officeRoom.setAuditSstate(secState=="y"?"通过":"未通过");
						officeRoom
								.setEndAuditState(("y".equals(firState)) ? "待审核"
										: "驳回");

					} else if (firState == null && secState == null
							&& thrState == null) {
						officeRoom.setEndAuditState("待审核");
					} else if (firState == null && secState == null
							&& thrState != null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate("无");
						officeRoom.setAuditTstate("y".equals(thrState) ? "通过"
								: "未通过");
						officeRoom
								.setEndAuditState(("y".equals(thrState)) ? "通过"
										: "驳回");
					} else if (firState == null && secState != null
							&& thrState != null) {
						officeRoom.setAuditFstate("无");
						if ("y".equals(secState) && "y".equals(thrState)) {
							officeRoom.setAuditSstate("通过");
							officeRoom.setAuditTstate("通过");
							officeRoom.setEndAuditState("通过");
						} else {
							officeRoom.setAuditSstate("通过");
							officeRoom
									.setAuditTstate("y".equals(thrState) ? "通过"
											: "未通过");
							officeRoom
									.setEndAuditState(("y".equals(thrState)) ? "通过"
											: "驳回");
						}
					} else if (firState == null && secState != null
							&& thrState == null) {
						officeRoom.setAuditFstate("无");
						if ("y".equals(secState)) {
							officeRoom.setAuditSstate("通过");
							officeRoom.setEndAuditState("待审核");
						} else {
							officeRoom.setAuditSstate("未通过");
							officeRoom.setEndAuditState("驳回");
						}
					} else {
						officeRoom.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId != null
						&& fouId != null) {
					// System.out.println("有四级审批");
					officeRoom.setAuditFirId(this.getApplyNames(firId));
					officeRoom.setAuditSecId(this.getApplyNames(secId));
					officeRoom.setAuditThrId(this.getApplyNames(thrId));
					officeRoom.setAuditFouId(this.getApplyNames(fouId));
					if (firState != null && secState != null
							&& thrState != null && thrState != null) {
						if ("y".equals(firState) && "y".equals(secState)
								&& "y".equals(thrState) && "y".equals(fouState)) {
							officeRoom.setAuditFstate("通过");
							officeRoom.setAuditSstate("通过");
							officeRoom.setAuditTstate("通过");
							officeRoom.setAuditFouState("通过");
							officeRoom.setEndAuditState("通过");
						} else if ("n".equals(firState) || "n".equals(secState)
								|| "n".equals(thrState) || "n".equals(fouState)) {
							officeRoom.setAuditFstate(firState == "y" ? "通过"
									: "未通过");
							officeRoom.setAuditSstate(secState == "y" ? "通过"
									: "未通过");
							officeRoom.setAuditTstate(thrState == "y" ? "通过"
									: "未通过");
							officeRoom.setAuditFouState(fouState == "y" ? "通过"
									: "未通过");
							officeRoom.setEndAuditState("驳回");
						}
					} else if (firState == null && secState == null
							&& thrState == null && fouState == null) {
						officeRoom.setEndAuditState("待审核");
					} else if (firState != null && secState == null
							&& thrState == null && fouState == null) {
						officeRoom.setAuditFstate(firState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(firState == "y" ? "待审核"
								: "驳回");
					} else if (firState != null && secState != null
							&& thrState == null && fouState == null) {
						officeRoom.setAuditFstate("通过");
						officeRoom.setAuditSstate(secState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(secState == "y" ? "待审核"
								: "驳回");
					} else if (firState != null && secState != null
							&& thrState != null && fouState == null) {
						officeRoom.setAuditFstate("通过");
						officeRoom.setAuditSstate("通过");
						officeRoom.setAuditTstate(thrState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(thrState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState == null
							&& thrState == null && fouState != null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate("无");
						officeRoom.setAuditTstate("无");
						officeRoom.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(fouState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState == null
							&& thrState != null && fouState == null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate("无");
						officeRoom.setAuditTstate(thrState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(thrState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState == null
							&& thrState != null && fouState != null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate("无");
						officeRoom.setAuditTstate("通过");
						officeRoom.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(fouState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState != null
							&& thrState == null && fouState == null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate(secState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(secState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState != null
							&& thrState != null && fouState == null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate("通过");
						officeRoom.setAuditTstate(thrState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(thrState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState != null
							&& thrState != null && fouState != null) {
						officeRoom.setAuditFstate("无");
						officeRoom.setAuditSstate("通过");
						officeRoom.setAuditTstate("通过");
						officeRoom.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						officeRoom.setEndAuditState(fouState == "y" ? "待审核"
								: "驳回");
					} else {
						officeRoom.setEndAuditState("待审核");
					}
				}
			}

			if ("".equals(copyId) || copyId == null) {

			} else {
				String[] firSplit = copyId.split(",");
				String fir = "";
				for (int i = 0; i < firSplit.length; i++) {
					User user = UserUtils.get(firSplit[i]);
					if (user == null) {
						continue;
					}
					fir += user.getName() + ",";
				}
				officeRoom.setCopytoId(fir);
			}

			if (officeIds == null || ("").equals(officeIds)) {

			} else {
				officeRoom.setOfficeNames(this
						.getApplyOfficeNames(allOfficeIds));
			}

		}
		return reportList;

	}

	public Page<OaApplyOfficeroom> findWithNames(Page<OaApplyOfficeroom> page,
			OaApplyOfficeroom oaApplyOfficeroom) {
		oaApplyOfficeroom.setPage(page);
		page.setList(this.getAllApplyNames(oaApplyOfficeroom));
		return page;

	}

	public String returnFlag(OaApplyOfficeroom oaApplyOfficeroom) {
		String onePerson = oaApplyOfficeroom.getAuditFirId();
		String oneFlag = oaApplyOfficeroom.getAuditFstate();
		String twoPerson = oaApplyOfficeroom.getAuditSecId();
		String twoFlag = oaApplyOfficeroom.getAuditSstate();
		String threePerson = oaApplyOfficeroom.getAuditThrId();
		String threeFlag = oaApplyOfficeroom.getAuditTstate();
		String fourPerson = oaApplyOfficeroom.getAuditFouId();
		String fourFlag = oaApplyOfficeroom.getAuditFouState();
		if (fourPerson == null || "".equals(fourPerson)) {
			if (threePerson == null || "".equals(threePerson)) {
				if (twoPerson == null || "".equals(twoPerson)) {
					if (onePerson == null || "".equals(onePerson)) {
						return "error";
					} else {
						if (oneFlag != null && !("".equals(oneFlag))) {
							if (oneFlag.equals("y")) {
								return "y";
							} else {
								return "n";
							}
						} else {
							return "z";
						}
					}
				} else {
					if (twoFlag != null && !("".equals(twoFlag))) {
						if (twoFlag.equals("y")) {
							return "y";
						} else {
							return "n";
						}
					} else {
						return "z";
					}
				}
			} else {
				if (threeFlag != null && !("".equals(threeFlag))) {
					if (threeFlag.equals("y")) {
						return "y";
					} else {
						return "n";
					}
				} else {
					return "z";
				}
			}
		} else {
			if (fourFlag != null && !("".equals(fourFlag))) {
				if (fourFlag.equals("y")) {
					return "y";
				} else {
					return "n";
				}
			} else {
				return "z";
			}
		}
	}

}