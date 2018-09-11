/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.dao.OaLeavedDao;
import com.thinkgem.jeesite.modules.oa.entity.OaLeaved;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 请假申请Service
 * 
 * @author jhm
 * @version 2017-09-28
 */
@Service
@Transactional(readOnly = true)
public class OaLeavedService extends CrudService<OaLeavedDao, OaLeaved> {

	@Autowired
	private OaLeavedDao oaLeavedDao;
	@Autowired
	private OfficeService officeService;

	public OaLeaved get(String id) {
		return super.get(id);
	}

	public List<OaLeaved> findList(OaLeaved oaLeaved) {
		return super.findList(oaLeaved);
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

	public List<OaLeaved> getAllApplyNames(OaLeaved oaLeaved) {
		List<OaLeaved> reportList = oaLeavedDao.findList(oaLeaved);
		System.out.println(reportList.size());
		for (OaLeaved leave : reportList) {
			if (leave == null) {
				continue;
			}
			// System.out.println("1");
			String firId = leave.getAuditFirId();
			String secId = leave.getAuditSecId();
			String thrId = leave.getAuditThrId();
			String fouId = leave.getAuditFouId();
			String copyId = leave.getCopytoId();
			String firState = leave.getAuditFirState();
			String secState = leave.getAuditSecState();
			String thrState = leave.getAuditThrState();
			String fouState = leave.getAuditFouState();
			String officeParentId = leave.getOffice().getId();
			String officeIds = leave.getYuliu1();
			String allOfficeIds = officeIds + "," + officeParentId;
			// SimpleDateFormat sdf = new SimpleDateFormat(
			// " yyyy-MM-dd HH:mm:ss " );
			// String endTime = sdf.format(overtime.getEndTime());
			// System.out.println("结束日期"+endTime);
			if (firId == null && secId == null && thrId == null
					&& fouId == null) {
				// System.out.println("一级审批都没有");
			} else {
				if (firId != null && secId == null && thrId == null
						&& fouId == null) {
					// System.out.println("有一级审批");
					leave.setAuditFirId(this.getApplyNames(firId));
					if ("y".equals(firState)) {
						leave.setAuditFirState("通过");
						leave.setEndAuditState("通过");
					} else if ("n".equals(firState)) {
						leave.setAuditFirState("未通过");
						leave.setEndAuditState("驳回");
					} else {
						leave.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId == null
						&& fouId == null) {
					// System.out.println("有二级审批");

					leave.setAuditFirId(this.getApplyNames(firId));
					leave.setAuditSecId(this.getApplyNames(secId));

					if (firState != null && secState != null) {
						if ("y".equals(firState) && "y".equals(secState)) {
							leave.setAuditFirState("通过");
							leave.setAuditSecState("通过");
							leave.setEndAuditState("通过");
						} else {
							leave.setAuditFirState((firState == "y") ? "通过"
									: "未通过");
							leave.setAuditSecState((secState == "y") ? "通过"
									: "未通过");
							leave.setEndAuditState("驳回");
						}

					} else if (firState == null && secState == null) {
						leave.setEndAuditState("待审核");

					} else if (firState != null && secState == null) {

						leave.setAuditFirState(("y".equals(firState)) ? "通过"
								: "未通过");
						leave.setEndAuditState(("y".equals(firState)) ? "待审核"
								: "驳回");

					} else if (firState == null && secState != null) {
						leave.setAuditSecState(("y".equals(secState)) ? "通过"
								: "未通过");
						leave.setEndAuditState(("y".equals(secState)) ? "待审核"
								: "驳回");
					}

				} else if (firId != null && secId != null && thrId != null
						&& fouId == null) {
					// System.out.println("有三级审批");
					leave.setAuditFirId(this.getApplyNames(firId));
					leave.setAuditSecId(this.getApplyNames(secId));
					leave.setAuditThrId(this.getApplyNames(thrId));
					if (firState != null && secState != null
							&& thrState != null) {
						if ("y".equals(firState) && "y".equals(secState)
								&& "y".equals(thrState)) {
							leave.setAuditFirState("通过");
							leave.setAuditSecState("通过");
							leave.setAuditThrState("通过");
							leave.setEndAuditState("通过");
						} else if ("n".equals(firState) || "n".equals(secState)
								|| "n".equals(thrState)) {
							leave.setAuditFirState("y".equals(firState) ? "通过"
									: "未通过");
							leave.setAuditSecState("y".equals(secState) ? "通过"
									: "未通过");
							leave.setAuditThrState("y".equals(thrState) ? "通过"
									: "未通过");
							leave.setEndAuditState("驳回");
						}
					} else if (firState != null && secState != null
							&& thrState == null) {
						leave.setAuditFirState("y".equals(firState) ? "通过"
								: "未通过");
						leave.setAuditSecState("y".equals(secState) ? "通过"
								: "未通过");
						// overtime.setAuditThrState("y".equals(thrState)?"通过":"未通过");
						if ("y".equals(firState) && "y".equals(secState)) {

							leave.setAuditThrState("待审核");
						}
						leave.setEndAuditState("驳回");
					} else if (firState != null && secState == null
							&& thrState == null) {
						leave.setAuditFirState("y".equals(firState) ? "通过"
								: "未通过");
						// overtime.setAuditSecState(secState=="y"?"通过":"未通过");
						leave.setEndAuditState(("y".equals(firState)) ? "待审核"
								: "驳回");

					} else {
						leave.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId != null
						&& fouId != null) {
					// System.out.println("有四级审批");
					leave.setAuditFirId(this.getApplyNames(firId));
					leave.setAuditSecId(this.getApplyNames(secId));
					leave.setAuditThrId(this.getApplyNames(thrId));
					leave.setAuditFouId(this.getApplyNames(fouId));

					if (firState != null && secState != null
							&& thrState != null && thrState != null) {
						leave.setAuditFirState("通过");
						leave.setAuditSecState("通过");
						leave.setAuditThrState("通过");
						leave.setAuditFouState("通过");
						leave.setEndAuditState("通过");

					} else if ("n".equals(firState) || "n".equals(secState)
							|| "n".equals(thrState) || "n".equals(fouState)) {
						leave.setAuditFirState(firState == "y" ? "通过" : "未通过");
						leave.setAuditSecState(secState == "y" ? "通过" : "未通过");
						leave.setAuditThrState(thrState == "y" ? "通过" : "未通过");
						leave.setAuditFouState(fouState == "y" ? "通过" : "未通过");
						leave.setEndAuditState("驳回");
					}
				} else if ("n".equals(firState) || "n".equals(secState)
						|| "n".equals(thrState) || "n".equals(fouState)) {
					leave.setEndAuditState("驳回");
				} else {

					leave.setEndAuditState("待审核");
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
				leave.setCopytoId(fir);
			}
			if (officeIds == null || ("").equals(officeIds)) {

			} else {
				leave.setOfficeNames(this.getApplyOfficeNames(allOfficeIds));
			}

		}
		return reportList;

	}

	public Page<OaLeaved> findWithNames(Page<OaLeaved> page, OaLeaved oaLeaved) {
		oaLeaved.setPage(page);
		page.setList(this.getAllApplyNames(oaLeaved));
		return page;

	}

	public Page<OaLeaved> findPage(Page<OaLeaved> page, OaLeaved oaLeaved) {
		return super.findPage(page, oaLeaved);
	}

	@Transactional(readOnly = false)
	public void save(OaLeaved oaLeaved) {
		super.save(oaLeaved);
	}

	@Transactional(readOnly = false)
	public void delete(OaLeaved oaLeaved) {
		super.delete(oaLeaved);
	}

	/**
	 * 我发起的请假流程 判断审批状态
	 * 
	 * @return
	 */
	public String returnFlag(OaLeaved oaLeaved) {
		String onePerson = oaLeaved.getAuditFirId();
		String oneFlag = oaLeaved.getAuditFirState();
		String twoPerson = oaLeaved.getAuditSecId();
		String twoFlag = oaLeaved.getAuditSecState();
		String threePerson = oaLeaved.getAuditThrId();
		String threeFlag = oaLeaved.getAuditThrState();
		String fourPerson = oaLeaved.getAuditFouId();
		String fourFlag = oaLeaved.getAuditFouState();
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
					if (oneFlag != null && !("".equals(oneFlag))
							&& twoFlag != null && !("".equals(twoFlag))) {
						if (oneFlag.equals("y") && twoFlag.equals("y")) {
							return "y";
						} else {
							return "n";
						}
					} else {
						return "z";
					}
				}
			} else {
				if (oneFlag != null && !("".equals(oneFlag)) && twoFlag != null
						&& !("".equals(twoFlag)) && threeFlag != null
						&& !("".equals(threeFlag))) {
					if (oneFlag.equals("y") && twoFlag.equals("y")
							&& threeFlag.equals("y")) {
						return "y";
					} else {
						return "n";
					}
				} else {
					return "z";
				}
			}
		} else {
			if (oneFlag != null && !("".equals(oneFlag)) && twoFlag != null
					&& !("".equals(twoFlag)) && threeFlag != null
					&& !("".equals(threeFlag)) && fourFlag != null
					&& !("".equals(fourFlag))) {
				if (oneFlag.equals("y") && twoFlag.equals("y")
						&& threeFlag.equals("y") && fourFlag.equals("y")) {
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