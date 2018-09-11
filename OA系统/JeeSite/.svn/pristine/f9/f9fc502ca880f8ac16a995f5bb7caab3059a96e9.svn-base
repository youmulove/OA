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
import com.thinkgem.jeesite.modules.oa.dao.OaOvertimeDao;
import com.thinkgem.jeesite.modules.oa.entity.OaOvertime;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 加班申请Service
 * 
 * @author JHM
 * @version 2017-09-28
 */
@Service
@Transactional(readOnly = true)
public class OaOvertimeService extends CrudService<OaOvertimeDao, OaOvertime> {

	@Autowired
	private OaOvertimeDao oaOvertimeDao;
	@Autowired
	private OfficeService officeService;

	public OaOvertime get(String id) {
		return super.get(id);
	}

	public List<OaOvertime> findList(OaOvertime oaOvertime) {
		return super.findList(oaOvertime);
	}

	public Page<OaOvertime> findPage(Page<OaOvertime> page,
			OaOvertime oaOvertime) {
		return super.findPage(page, oaOvertime);
	}

	@Transactional(readOnly = false)
	public void save(OaOvertime oaOvertime) {
		super.save(oaOvertime);
	}

	@Transactional(readOnly = false)
	public void delete(OaOvertime oaOvertime) {
		super.delete(oaOvertime);
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

	public List<OaOvertime> getAllApplyNames(OaOvertime oaOvertime) {
		List<OaOvertime> reportList = oaOvertimeDao.findList(oaOvertime);
		for (OaOvertime overtime : reportList) {
			String firId = overtime.getAuditFirId();
			String secId = overtime.getAuditSecId();
			String thrId = overtime.getAuditThrId();
			String fouId = overtime.getAuditFouId();
			String copyId = overtime.getCopytoId();
			String firState = overtime.getAuditFirState();
			String secState = overtime.getAuditSecState();
			String thrState = overtime.getAuditThrState();
			String fouState = overtime.getAuditFouState();
			String isHoliday = overtime.getIsHoliday();
			String officeParentId = overtime.getOffice().getId();
			String officeIds = overtime.getYuliu1();
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
					overtime.setAuditFirId(this.getApplyNames(firId));
					if ("y".equals(firState)) {
						overtime.setAuditFirState("通过");
						overtime.setEndAuditState("通过");
					} else if ("n".equals(firState)) {
						overtime.setAuditFirState("未通过");
						overtime.setEndAuditState("驳回");
					} else {
						overtime.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId == null
						&& fouId == null) {
					// System.out.println("有二级审批");

					overtime.setAuditFirId(this.getApplyNames(firId));
					overtime.setAuditSecId(this.getApplyNames(secId));

					if (firState != null && secState != null) {
						if ("y".equals(firState) && "y".equals(secState)) {
							overtime.setAuditFirState("通过");
							overtime.setAuditSecState("通过");
							overtime.setEndAuditState("通过");
						} else {
							overtime.setAuditFirState((firState == "y") ? "通过"
									: "未通过");
							overtime.setAuditSecState((secState == "y") ? "通过"
									: "未通过");
							overtime.setEndAuditState("驳回");
						}

					} else if (firState == null && secState == null) {
						overtime.setEndAuditState("待审核");

					} else if (firState != null && secState == null) {

						overtime.setAuditFirState(("y".equals(firState)) ? "通过"
								: "未通过");
						overtime.setEndAuditState(("y".equals(firState)) ? "待审核"
								: "驳回");

					} else if (firState == null && secState != null) {
						overtime.setAuditSecState(("y".equals(secState)) ? "通过"
								: "未通过");
						overtime.setEndAuditState(("y".equals(secState)) ? "待审核"
								: "驳回");
					}

				} else if (firId != null && secId != null && thrId != null
						&& fouId == null) {
					// System.out.println("有三级审批");
					overtime.setAuditFirId(this.getApplyNames(firId));
					overtime.setAuditSecId(this.getApplyNames(secId));
					overtime.setAuditThrId(this.getApplyNames(thrId));
					if (firState != null && secState != null
							&& thrState != null) {
						if ("y".equals(firState) && "y".equals(secState)
								&& "y".equals(thrState)) {
							overtime.setAuditFirState("通过");
							overtime.setAuditSecState("通过");
							overtime.setAuditThrState("通过");
							overtime.setEndAuditState("通过");
						} else if ("n".equals(firState) || "n".equals(secState)
								|| "n".equals(thrState)) {
							overtime.setAuditFirState("y".equals(firState) ? "通过"
									: "未通过");
							overtime.setAuditSecState("y".equals(secState) ? "通过"
									: "未通过");
							overtime.setAuditThrState("y".equals(thrState) ? "通过"
									: "未通过");
							overtime.setEndAuditState("驳回");
						}
					} else if (firState != null && secState != null
							&& thrState == null) {
						overtime.setAuditFirState("y".equals(firState) ? "通过"
								: "未通过");
						overtime.setAuditSecState("y".equals(secState) ? "通过"
								: "未通过");
						// overtime.setAuditThrState("y".equals(thrState)?"通过":"未通过");
						if ("y".equals(firState) && "y".equals(secState)) {

							overtime.setAuditThrState("待审核");
						}
						overtime.setEndAuditState("驳回");
					} else if (firState != null && secState == null
							&& thrState == null) {
						overtime.setAuditFirState("y".equals(firState) ? "通过"
								: "未通过");
						// overtime.setAuditSecState(secState=="y"?"通过":"未通过");
						overtime.setEndAuditState(("y".equals(firState)) ? "待审核"
								: "驳回");

					} else {
						overtime.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId != null
						&& fouId != null) {
					// System.out.println("有四级审批");
					overtime.setAuditFirId(this.getApplyNames(firId));
					overtime.setAuditSecId(this.getApplyNames(secId));
					overtime.setAuditThrId(this.getApplyNames(thrId));
					overtime.setAuditFouId(this.getApplyNames(fouId));

					if (firState != null && secState != null
							&& thrState != null && thrState != null) {
						overtime.setAuditFirState("通过");
						overtime.setAuditSecState("通过");
						overtime.setAuditThrState("通过");
						overtime.setAuditFouState("通过");
						overtime.setEndAuditState("通过");

					} else if ("n".equals(firState) || "n".equals(secState)
							|| "n".equals(thrState) || "n".equals(fouState)) {
						overtime.setAuditFirState(firState == "y" ? "通过"
								: "未通过");
						overtime.setAuditSecState(secState == "y" ? "通过"
								: "未通过");
						overtime.setAuditThrState(thrState == "y" ? "通过"
								: "未通过");
						overtime.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						overtime.setEndAuditState("驳回");
					}
				} else if ("n".equals(firState) || "n".equals(secState)
						|| "n".equals(thrState) || "n".equals(fouState)) {
					overtime.setEndAuditState("驳回");
				} else {

					overtime.setEndAuditState("待审核");
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
				overtime.setCopytoId(fir);
			}
			if ("y".equals(isHoliday)) {
				overtime.setIsHoliday("是");

			} else {
				overtime.setIsHoliday("否");
			}
			if (officeIds == null || ("").equals(officeIds)) {

			} else {
				overtime.setOfficeNames(this.getApplyOfficeNames(allOfficeIds));
			}

		}
		return reportList;

	}

	public Page<OaOvertime> findWithNames(Page<OaOvertime> page,
			OaOvertime oaOvertime) {
		oaOvertime.setPage(page);
		page.setList(this.getAllApplyNames(oaOvertime));
		return page;
	}

	public String returnFlag(OaOvertime oaOvertime) {
		String onePerson = oaOvertime.getAuditFirId();
		String oneFlag = oaOvertime.getAuditFirState();
		String twoPerson = oaOvertime.getAuditSecId();
		String twoFlag = oaOvertime.getAuditSecState();
		String threePerson = oaOvertime.getAuditThrId();
		String threeFlag = oaOvertime.getAuditThrState();
		String fourPerson = oaOvertime.getAuditFouId();
		String fourFlag = oaOvertime.getAuditFouState();
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