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
import com.thinkgem.jeesite.modules.oa.dao.OaApplyProjectorDao;
import com.thinkgem.jeesite.modules.oa.entity.OaApplyProjector;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 投影仪申请Service
 * 
 * @author jhm
 * @version 2017-09-28
 */
@Service
@Transactional(readOnly = true)
public class OaApplyProjectorService extends
		CrudService<OaApplyProjectorDao, OaApplyProjector> {
	@Autowired
	private OaApplyProjectorDao oaApplyProjectorDao;
	@Autowired
	private OfficeService officeService;

	public OaApplyProjector get(String id) {
		return super.get(id);
	}

	public List<OaApplyProjector> findList(OaApplyProjector oaApplyProjector) {
		return super.findList(oaApplyProjector);
	}

	public Page<OaApplyProjector> findPage(Page<OaApplyProjector> page,
			OaApplyProjector oaApplyProjector) {
		return super.findPage(page, oaApplyProjector);
	}

	@Transactional(readOnly = false)
	public void save(OaApplyProjector oaApplyProjector) {
		super.save(oaApplyProjector);
	}

	@Transactional(readOnly = false)
	public void delete(OaApplyProjector oaApplyProjector) {
		super.delete(oaApplyProjector);
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
		StringBuffer buf = new StringBuffer();
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

	public List<OaApplyProjector> getAllApplyNames(
			OaApplyProjector oaApplyProjector) {
		List<OaApplyProjector> reportList = oaApplyProjectorDao
				.findList(oaApplyProjector);
		for (OaApplyProjector projector : reportList) {
			String firId = projector.getAuditFirId();
			String secId = projector.getAuditSecId();
			String thrId = projector.getAuditThrId();
			String fouId = projector.getAuditFouId();
			String copyId = projector.getCopytoId();
			String firState = projector.getAuditFstate();
			String secState = projector.getAuditSstate();
			String thrState = projector.getAuditTstate();
			String fouState = projector.getAuditFouState();
			String isAdapter = projector.getAdapter();
			String officeParentId = projector.getOffice().getId();
			String officeIds = projector.getYuliu2();
			String allOfficeIds = officeIds + "," + officeParentId;

			// SimpleDateFormat sdf = new SimpleDateFormat(
			// " yyyy-MM-dd HH:mm:ss " );
			// String endTime = sdf.format(projector.getEndTime());
			// System.out.println("结束日期"+endTime);
			if (firId == null && secId == null && thrId == null
					&& fouId == null) {
				// System.out.println("一级审批都没有");
			} else {
				if (firId != null && secId == null && thrId == null
						&& fouId == null) {
					// System.out.println("有一级审批");
					projector.setAuditFirId(this.getApplyNames(firId));
					if ("y".equals(firState)) {
						projector.setAuditFstate("通过");
						projector.setEndAuditState("通过");
					} else if ("n".equals(firState)) {
						projector.setAuditFstate("未通过");
						projector.setEndAuditState("驳回");
					} else {
						projector.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId == null
						&& fouId == null) {
					// System.out.println("有二级审批");

					projector.setAuditFirId(this.getApplyNames(firId));
					projector.setAuditSecId(this.getApplyNames(secId));

					if (firState != null && secState != null) {
						if ("y".equals(firState) && "y".equals(secState)) {
							projector.setAuditFstate("通过");
							projector.setAuditSstate("通过");
							projector.setEndAuditState("通过");
						} else {
							projector.setAuditFstate((firState == "y") ? "通过"
									: "未通过");
							projector.setAuditSstate((secState == "y") ? "通过"
									: "未通过");
							projector.setEndAuditState("驳回");
						}

					} else if (firState == null && secState == null) {
						projector.setEndAuditState("待审核");
					} else if (firState != null && secState == null) {
						projector.setAuditFstate(("y".equals(firState)) ? "通过"
								: "未通过");
						projector
								.setEndAuditState(("y".equals(firState)) ? "待审核"
										: "驳回");
					} else if (firState == null && secState != null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate(("y".equals(secState)) ? "通过"
								: "未通过");
						projector
								.setEndAuditState(("y".equals(secState)) ? "通过"
										: "驳回");
					}

				} else if (firId != null && secId != null && thrId != null
						&& fouId == null) {
					// System.out.println("有三级审批");
					projector.setAuditFirId(this.getApplyNames(firId));
					projector.setAuditSecId(this.getApplyNames(secId));
					projector.setAuditThrId(this.getApplyNames(thrId));
					if (firState != null && secState != null
							&& thrState != null) {
						if ("y".equals(firState) && "y".equals(secState)
								&& "y".equals(thrState)) {
							projector.setAuditFstate("通过");
							projector.setAuditSstate("通过");
							projector.setAuditTstate("通过");
							projector.setEndAuditState("通过");
						} else if ("n".equals(firState) || "n".equals(secState)
								|| "n".equals(thrState)) {
							projector
									.setAuditFstate("y".equals(firState) ? "通过"
											: "未通过");
							projector
									.setAuditSstate("y".equals(secState) ? "通过"
											: "未通过");
							projector
									.setAuditTstate("y".equals(thrState) ? "通过"
											: "未通过");
							projector.setEndAuditState("驳回");
						}
					} else if (firState != null && secState != null
							&& thrState == null) {
						projector.setAuditFstate("y".equals(firState) ? "通过"
								: "未通过");
						projector.setAuditSstate("y".equals(secState) ? "通过"
								: "未通过");
						// projector.setAuditTstate("y".equals(thrState)?"通过":"未通过");
						if ("y".equals(firState) && "y".equals(secState)) {

							projector.setAuditTstate("待审核");
						}
						projector.setEndAuditState("驳回");
					} else if (firState != null && secState == null
							&& thrState == null) {
						projector.setAuditFstate("y".equals(firState) ? "通过"
								: "未通过");
						// projector.setAuditSstate(secState=="y"?"通过":"未通过");
						projector
								.setEndAuditState(("y".equals(firState)) ? "待审核"
										: "驳回");

					} else if (firState == null && secState == null
							&& thrState == null) {
						projector.setEndAuditState("待审核");
					} else if (firState == null && secState == null
							&& thrState != null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate("无");
						projector.setAuditTstate("y".equals(thrState) ? "通过"
								: "未通过");
						projector
								.setEndAuditState(("y".equals(thrState)) ? "通过"
										: "驳回");
					} else if (firState == null && secState != null
							&& thrState != null) {
						projector.setAuditFstate("无");
						if ("y".equals(secState) && "y".equals(thrState)) {
							projector.setAuditSstate("通过");
							projector.setAuditTstate("通过");
							projector.setEndAuditState("通过");
						} else {
							projector.setAuditSstate("通过");
							projector
									.setAuditTstate("y".equals(thrState) ? "通过"
											: "未通过");
							projector
									.setEndAuditState(("y".equals(thrState)) ? "通过"
											: "驳回");
						}
					} else if (firState == null && secState != null
							&& thrState == null) {
						projector.setAuditFstate("无");
						if ("y".equals(secState)) {
							projector.setAuditSstate("通过");
							projector.setEndAuditState("待审核");
						} else {
							projector.setAuditSstate("未通过");
							projector.setEndAuditState("驳回");
						}
					} else {
						projector.setEndAuditState("待审核");
					}
				} else if (firId != null && secId != null && thrId != null
						&& fouId != null) {
					// System.out.println("有四级审批");
					projector.setAuditFirId(this.getApplyNames(firId));
					projector.setAuditSecId(this.getApplyNames(secId));
					projector.setAuditThrId(this.getApplyNames(thrId));
					projector.setAuditFouId(this.getApplyNames(fouId));
					if (firState != null && secState != null
							&& thrState != null && thrState != null) {
						if ("y".equals(firState) && "y".equals(secState)
								&& "y".equals(thrState) && "y".equals(fouState)) {
							projector.setAuditFstate("通过");
							projector.setAuditSstate("通过");
							projector.setAuditTstate("通过");
							projector.setAuditFouState("通过");
							projector.setEndAuditState("通过");
						} else if ("n".equals(firState) || "n".equals(secState)
								|| "n".equals(thrState) || "n".equals(fouState)) {
							projector.setAuditFstate(firState == "y" ? "通过"
									: "未通过");
							projector.setAuditSstate(secState == "y" ? "通过"
									: "未通过");
							projector.setAuditTstate(thrState == "y" ? "通过"
									: "未通过");
							projector.setAuditFouState(fouState == "y" ? "通过"
									: "未通过");
							projector.setEndAuditState("驳回");
						}
					} else if (firState == null && secState == null
							&& thrState == null && fouState == null) {
						projector.setEndAuditState("待审核");
					} else if (firState != null && secState == null
							&& thrState == null && fouState == null) {
						projector
								.setAuditFstate(firState == "y" ? "通过" : "未通过");
						projector.setEndAuditState(firState == "y" ? "待审核"
								: "驳回");
					} else if (firState != null && secState != null
							&& thrState == null && fouState == null) {
						projector.setAuditFstate("通过");
						projector
								.setAuditSstate(secState == "y" ? "通过" : "未通过");
						projector.setEndAuditState(secState == "y" ? "待审核"
								: "驳回");
					} else if (firState != null && secState != null
							&& thrState != null && fouState == null) {
						projector.setAuditFstate("通过");
						projector.setAuditSstate("通过");
						projector
								.setAuditTstate(thrState == "y" ? "通过" : "未通过");
						projector.setEndAuditState(thrState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState == null
							&& thrState == null && fouState != null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate("无");
						projector.setAuditTstate("无");
						projector.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						projector.setEndAuditState(fouState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState == null
							&& thrState != null && fouState == null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate("无");
						projector
								.setAuditTstate(thrState == "y" ? "通过" : "未通过");
						projector.setEndAuditState(thrState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState == null
							&& thrState != null && fouState != null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate("无");
						projector.setAuditTstate("通过");
						projector.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						projector.setEndAuditState(fouState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState != null
							&& thrState == null && fouState == null) {
						projector.setAuditFstate("无");
						projector
								.setAuditSstate(secState == "y" ? "通过" : "未通过");
						projector.setEndAuditState(secState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState != null
							&& thrState != null && fouState == null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate("通过");
						projector
								.setAuditTstate(thrState == "y" ? "通过" : "未通过");
						projector.setEndAuditState(thrState == "y" ? "待审核"
								: "驳回");
					} else if (firState == null && secState != null
							&& thrState != null && fouState != null) {
						projector.setAuditFstate("无");
						projector.setAuditSstate("通过");
						projector.setAuditTstate("通过");
						projector.setAuditFouState(fouState == "y" ? "通过"
								: "未通过");
						projector.setEndAuditState(fouState == "y" ? "待审核"
								: "驳回");
					} else {
						projector.setEndAuditState("待审核");
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
				projector.setCopytoId(fir);
			}

			if (officeIds == null || ("").equals(officeIds)) {

			} else {
				projector
						.setOfficeNames(this.getApplyOfficeNames(allOfficeIds));
			}
			if ("y".equals(isAdapter)) {
				projector.setAdapter("是");

			} else {
				projector.setAdapter("否");
			}
		}

		return reportList;

	}

	public Page<OaApplyProjector> findWithNames(Page<OaApplyProjector> page,
			OaApplyProjector oaApplyProjector) {
		oaApplyProjector.setPage(page);
		page.setList(this.getAllApplyNames(oaApplyProjector));
		return page;

	}

	public String returnFlag(OaApplyProjector oaApplyProjector) {
		String onePerson = oaApplyProjector.getAuditFirId();
		String oneFlag = oaApplyProjector.getAuditFstate();
		String twoPerson = oaApplyProjector.getAuditSecId();
		String twoFlag = oaApplyProjector.getAuditSstate();
		String threePerson = oaApplyProjector.getAuditThrId();
		String threeFlag = oaApplyProjector.getAuditTstate();
		String fourPerson = oaApplyProjector.getAuditFouId();
		String fourFlag = oaApplyProjector.getAuditFouState();
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