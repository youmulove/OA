/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * 
 * @author ThinkGem
 * @version 2013-03-10
 */
public class OfficeType {
	private static Map<String, String> map = new HashMap<String, String>();
	private static OfficeService officeService = SpringContextHolder
			.getBean(OfficeService.class);

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue111(String val) {
		for (Office e : UserUtils.getOfficeList()) {
			if (StringUtils.trimToEmpty(val).equals(e.getName())) {

				return e;
			}
		}
		return null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Office) val).getName() != null) {
			return ((Office) val).getName();
		}
		return "";
	}

	public static Object getValue(String val) {
		String[] vals = val.split("-");
		List<Office> officeList = UserUtils.getOfficeList();

		if (vals.length == 1) {
			for (Office e : officeList) {
				if (StringUtils.trimToEmpty(vals[0]).equals(e.getName())) {
					map.put("e", e.getId());
					return e;
				}
			}
		}

		if (vals.length == 2) {
			Office office1 = officeService.getOfficeByName(vals[0]);
			List<Office> chilidOffice = officeService.getChilidOffice(office1);
			for (Office office2 : chilidOffice) {
				if (office2.getName().equals(vals[1])) {
					return office2;
				} else {
					continue;

				}
			}
			return null;

		}

		if (vals.length == 3) {
			Office officeDept = officeService.getOfficeByName(vals[0]);
			String deptId = officeDept.getId();

			List<Office> offices = officeService.getOfficeByNames(vals[1]);
			for (Office office : offices) {

				if (deptId.equals(office.getParentId())) {
					List<Office> chilidOffice = officeService
							.getChilidOffice(office);
					for (Office office3 : chilidOffice) {

						if (office3.getName().equals(vals[2])) {
							return office3;
						} else {
							continue;
						}
					}
					return null;
				} else {
					continue;
				}
			}
			return null;
		}

		if (vals.length == 4) {
			Office officeDept = officeService.getOfficeByName(vals[0]);
			String deptId = officeDept.getId();

			List<Office> offices = officeService.getOfficeByNames(vals[1]);
			for (Office office : offices) {

				if (deptId.equals(office.getParentId())) {
					String twoId = office.getId();
					List<Office> chilidOffice = officeService
							.getChilidOffice(office);
					for (Office office3 : chilidOffice) {

						if (office3.getName().equals(vals[2])) {
							// String depThrId = office3.getId();
							List<Office> offices2 = officeService
									.getOfficeByNames(vals[2]);

							for (Office office4 : offices2) {
								if (twoId.equals(office4.getParentId())) {
									List<Office> chilidOffice2 = officeService
											.getChilidOffice(office4);
									for (Office office5 : chilidOffice2) {
										if (vals[3].equals(office5.getName())) {
											return office5;
										} else {
											continue;
										}
									}
									return null;
								} else {
									continue;
								}
							}
							return null;
						} else {
							continue;
						}
					}
					return null;

				} else {
					continue;
				}
			}
			return null;
		}
		/*
		 * if (vals.length == 5) { Office office4 =
		 * officeService.getOfficeByName(vals[3]); List<Office> chilidOffice =
		 * officeService.getChilidOffice(office4); for (Office office5 :
		 * chilidOffice) { if (office5.getName().equals(vals[4])) { return
		 * office5; } else { continue; } } return null; }
		 * 
		 * if (vals.length == 6) { Office office5 =
		 * officeService.getOfficeByName(vals[4]); List<Office> chilidOffice =
		 * officeService.getChilidOffice(office5); for (Office office6 :
		 * chilidOffice) { if (office6.getName().equals(vals[5])) { return
		 * office6; } else { continue; } } return null; }
		 */

		/*
		 * if (vals.length >= 2) { for (int i = 0; i < vals.length; i++) {
		 * Office office1 = officeService.getOfficeByName(vals[i]); List<Office>
		 * chilidOffice = officeService .getChilidOffice(office1); for (Office
		 * office2 : chilidOffice) { if (office2.getName().equals(vals[i + 1]))
		 * { List<Office> chilidOffice2 = officeService
		 * .getChilidOffice(office2); for (Office office3 : chilidOffice2) { if
		 * (office3.getName().equals(vals[i + 2])) { List<Office> chilidOffice3
		 * = officeService .getChilidOffice(office3); for (Office office4 :
		 * chilidOffice3) { if (office4.getName().equals(vals[i + 3])) { return
		 * office4; } } return office3; } } return office2; } } } }
		 */
		return null;
	}

	public static Object getValue2(String val) {
		String[] vals = val.split(",");
		for (int i = 0; i < vals.length; i++) {
			Office office1 = officeService.getOfficeByName(vals[0]);
			List<Office> chilidOffice = officeService.getChilidOffice(office1);
			for (Office office : chilidOffice) {

				if (office.getName().equals(vals[4])) {
					return office;
				} else {
					continue;
				}
			}
		}

		return null;
	}
}
