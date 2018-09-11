/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
		if (vals.length == 1) {
			Office office1 = officeService.getOfficeByName(vals[0]);
			if (office1 != null) {
				return office1;
			} else {
				User user = UserUtils.getUser();
				Office newOffice = new Office();
				Office company = officeService.get(user.getCompany());
				if (company.getParentId().equals("0")
						&& company.getParentIds().equals("0,")) {
					newOffice.setId(IdGen.uuid());
					newOffice.setParent(company);
					newOffice.setParentIds("0," + company.getId() + ",");
					newOffice.setName(StringUtils.trimToEmpty(vals[0]));
					newOffice.setType("2");

					if (StringUtils.isNotBlank(user.getId())) {
						newOffice.setUpdateBy(user);
						newOffice.setCreateBy(user);
					}
					newOffice.setUpdateDate(new Date());
					newOffice.setCreateDate(new Date());
					newOffice.setIsNewRecord(true);
					newOffice.setGrade("1");
					newOffice.setArea(user.getOffice().getArea());
					newOffice.setUseable(Global.YES);
					officeService.save(newOffice);
					return newOffice;
				}

			}
		}

		if (vals.length == 2) {
			Office office1 = officeService.getOfficeByName(vals[0]);
			if (office1 != null) {
				List<Office> chilidOffice = officeService
						.getChilidOffice(office1);
				if (chilidOffice.size() < 1) {
					User user = UserUtils.getUser();
					Office company = officeService.get(user.getCompany());
					Office newOffice2 = new Office();
					newOffice2.setId(IdGen.uuid());
					newOffice2.setParent(office1);
					newOffice2.setParentIds("0," + company.getId() + ","
							+ office1.getId() + ",");
					newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
					newOffice2.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice2.setUpdateBy(user);
						newOffice2.setCreateBy(user);
					}
					newOffice2.setUpdateDate(new Date());
					newOffice2.setCreateDate(new Date());
					newOffice2.setIsNewRecord(true);
					newOffice2.setGrade("2");
					newOffice2.setArea(user.getOffice().getArea());
					newOffice2.setUseable(Global.YES);
					officeService.save(newOffice2);
					return newOffice2;
				} else {
					int flag = 1;
					Office office0 = new Office();
					for (Office office2 : chilidOffice) {
						if (office2.getName().equals(
								StringUtils.trimToEmpty(vals[1]))) {
							flag = 0;
							office0 = office2;
							break;
						}
					}
					if (flag == 1) {
						User user = UserUtils.getUser();
						Office company = officeService.get(user.getCompany());
						Office newOffice2 = new Office();
						newOffice2.setId(IdGen.uuid());
						newOffice2.setParent(office1);
						newOffice2.setParentIds("0," + company.getId() + ","
								+ office1.getId() + ",");
						newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
						newOffice2.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice2.setUpdateBy(user);
							newOffice2.setCreateBy(user);
						}
						newOffice2.setUpdateDate(new Date());
						newOffice2.setCreateDate(new Date());
						newOffice2.setIsNewRecord(true);
						newOffice2.setGrade("2");
						newOffice2.setArea(user.getOffice().getArea());
						newOffice2.setUseable(Global.YES);
						officeService.save(newOffice2);
						return newOffice2;
					} else {
						return office0;
					}
				}
			} else {
				User user = UserUtils.getUser();
				Office newOffice = new Office();
				Office newOffice2 = new Office();
				Office company = officeService.get(user.getCompany());
				if (company.getParentId().equals("0")
						&& company.getParentIds().equals("0,")) {
					newOffice.setId(IdGen.uuid());
					newOffice.setParent(company);
					newOffice.setParentIds("0," + company.getId() + ",");
					newOffice.setName(StringUtils.trimToEmpty(vals[0]));
					newOffice.setType("2");

					if (StringUtils.isNotBlank(user.getId())) {
						newOffice.setUpdateBy(user);
						newOffice.setCreateBy(user);
					}
					newOffice.setUpdateDate(new Date());
					newOffice.setCreateDate(new Date());
					newOffice.setIsNewRecord(true);
					newOffice.setGrade("1");
					newOffice.setArea(user.getOffice().getArea());
					newOffice.setUseable(Global.YES);
					officeService.save(newOffice);

					newOffice2.setId(IdGen.uuid());
					newOffice2.setParent(newOffice);
					newOffice2.setParentIds("0," + company.getId() + ","
							+ newOffice.getId() + ",");
					newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
					newOffice2.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice2.setUpdateBy(user);
						newOffice2.setCreateBy(user);
					}
					newOffice2.setUpdateDate(new Date());
					newOffice2.setCreateDate(new Date());
					newOffice2.setIsNewRecord(true);
					newOffice2.setGrade("2");
					newOffice2.setArea(user.getOffice().getArea());
					newOffice2.setUseable(Global.YES);
					officeService.save(newOffice2);
					return newOffice2;
				}
			}
		}

		if (vals.length == 3) {
			Office office1 = officeService.getOfficeByName(vals[0]);
			if (office1 != null) {
				List<Office> chilidOffice = officeService
						.getChilidOffice(office1);
				if (chilidOffice.size() > 0) {
					int flag = 1;
					Office office0 = new Office();
					for (Office office2 : chilidOffice) {
						if (office2.getName().equals(
								StringUtils.trimToEmpty(vals[1]))) {
							flag = 0;
							office0 = office2;
							break;
						}
					}
					if (flag == 1) {
						User user = UserUtils.getUser();
						Office company = officeService.get(user.getCompany());
						Office newOffice2 = new Office();
						Office newOffice3 = new Office();
						newOffice2.setId(IdGen.uuid());
						newOffice2.setParent(office1);
						newOffice2.setParentIds("0," + company.getId() + ","
								+ office1.getId() + ",");
						newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
						newOffice2.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice2.setUpdateBy(user);
							newOffice2.setCreateBy(user);
						}
						newOffice2.setUpdateDate(new Date());
						newOffice2.setCreateDate(new Date());
						newOffice2.setIsNewRecord(true);
						newOffice2.setGrade("2");
						newOffice2.setArea(user.getOffice().getArea());
						newOffice2.setUseable(Global.YES);
						officeService.save(newOffice2);

						newOffice3.setId(IdGen.uuid());
						newOffice3.setParent(newOffice2);
						newOffice3.setParentIds("0," + company.getId() + ","
								+ office1.getId() + "," + newOffice2.getId()
								+ ",");
						newOffice3.setName(StringUtils.trimToEmpty(vals[2]));
						newOffice3.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice3.setUpdateBy(user);
							newOffice3.setCreateBy(user);
						}
						newOffice3.setUpdateDate(new Date());
						newOffice3.setCreateDate(new Date());
						newOffice3.setIsNewRecord(true);
						newOffice3.setGrade("3");
						newOffice3.setArea(user.getOffice().getArea());
						newOffice3.setUseable(Global.YES);
						officeService.save(newOffice3);
						return newOffice3;
					} else {
						List<Office> chilidOffice2 = officeService
								.getChilidOffice(office0);
						if (chilidOffice2.size() > 0) {
							int flag1 = 1;
							Office office00 = new Office();
							for (Office office3 : chilidOffice2) {
								if (office3.getName().equals(
										StringUtils.trimToEmpty(vals[2]))) {
									flag1 = 0;
									office00 = office3;
									break;
								}
							}
							if (flag1 == 1) {
								User user = UserUtils.getUser();
								Office newOffice3 = new Office();
								Office company = officeService.get(user
										.getCompany());
								newOffice3.setId(IdGen.uuid());
								newOffice3.setParent(office0);
								newOffice3.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ","
										+ office0.getId() + ",");
								newOffice3.setName(StringUtils
										.trimToEmpty(vals[2]));
								newOffice3.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice3.setUpdateBy(user);
									newOffice3.setCreateBy(user);
								}
								newOffice3.setUpdateDate(new Date());
								newOffice3.setCreateDate(new Date());
								newOffice3.setIsNewRecord(true);
								newOffice3.setGrade("3");
								newOffice3.setArea(user.getOffice().getArea());
								newOffice3.setUseable(Global.YES);
								officeService.save(newOffice3);
								return newOffice3;
							} else {
								return office00;
							}
						} else {
							User user = UserUtils.getUser();
							Office newOffice2 = new Office();
							Office newOffice3 = new Office();
							Office company = officeService.get(user
									.getCompany());
							if (company.getParentId().equals("0")
									&& company.getParentIds().equals("0,")) {
								newOffice2.setId(IdGen.uuid());
								newOffice2.setParent(office1);
								newOffice2.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ",");
								newOffice2.setName(StringUtils
										.trimToEmpty(vals[1]));
								newOffice2.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice2.setUpdateBy(user);
									newOffice2.setCreateBy(user);
								}
								newOffice2.setUpdateDate(new Date());
								newOffice2.setCreateDate(new Date());
								newOffice2.setIsNewRecord(true);
								newOffice2.setGrade("2");
								newOffice2.setArea(user.getOffice().getArea());
								newOffice2.setUseable(Global.YES);
								officeService.save(newOffice2);

								newOffice3.setId(IdGen.uuid());
								newOffice3.setParent(newOffice2);
								newOffice3.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ","
										+ newOffice2.getId() + ",");
								newOffice3.setName(StringUtils
										.trimToEmpty(vals[2]));
								newOffice3.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice3.setUpdateBy(user);
									newOffice3.setCreateBy(user);
								}
								newOffice3.setUpdateDate(new Date());
								newOffice3.setCreateDate(new Date());
								newOffice3.setIsNewRecord(true);
								newOffice3.setGrade("3");
								newOffice3.setArea(user.getOffice().getArea());
								newOffice3.setUseable(Global.YES);
								officeService.save(newOffice3);
								return newOffice3;
							}
						}
					}
				} else {
					User user = UserUtils.getUser();
					Office newOffice2 = new Office();
					Office newOffice3 = new Office();
					Office company = officeService.get(user.getCompany());
					if (company.getParentId().equals("0")
							&& company.getParentIds().equals("0,")) {
						newOffice2.setId(IdGen.uuid());
						newOffice2.setParent(office1);
						newOffice2.setParentIds("0," + company.getId() + ","
								+ office1.getId() + ",");
						newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
						newOffice2.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice2.setUpdateBy(user);
							newOffice2.setCreateBy(user);
						}
						newOffice2.setUpdateDate(new Date());
						newOffice2.setCreateDate(new Date());
						newOffice2.setIsNewRecord(true);
						newOffice2.setGrade("2");
						newOffice2.setArea(user.getOffice().getArea());
						newOffice2.setUseable(Global.YES);
						officeService.save(newOffice2);

						newOffice3.setId(IdGen.uuid());
						newOffice3.setParent(newOffice2);
						newOffice3.setParentIds("0," + company.getId() + ","
								+ office1.getId() + "," + newOffice2.getId()
								+ ",");
						newOffice3.setName(StringUtils.trimToEmpty(vals[2]));
						newOffice3.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice3.setUpdateBy(user);
							newOffice3.setCreateBy(user);
						}
						newOffice3.setUpdateDate(new Date());
						newOffice3.setCreateDate(new Date());
						newOffice3.setIsNewRecord(true);
						newOffice3.setGrade("3");
						newOffice3.setArea(user.getOffice().getArea());
						newOffice3.setUseable(Global.YES);
						officeService.save(newOffice3);
						return newOffice3;
					}
				}
			} else {
				User user = UserUtils.getUser();
				Office newOffice = new Office();
				Office newOffice2 = new Office();
				Office newOffice3 = new Office();
				Office company = officeService.get(user.getCompany());
				if (company.getParentId().equals("0")
						&& company.getParentIds().equals("0,")) {
					newOffice.setId(IdGen.uuid());
					newOffice.setParent(company);
					newOffice.setParentIds("0," + company.getId() + ",");
					newOffice.setName(StringUtils.trimToEmpty(vals[0]));
					newOffice.setType("2");

					if (StringUtils.isNotBlank(user.getId())) {
						newOffice.setUpdateBy(user);
						newOffice.setCreateBy(user);
					}
					newOffice.setUpdateDate(new Date());
					newOffice.setCreateDate(new Date());
					newOffice.setIsNewRecord(true);
					newOffice.setGrade("1");
					newOffice.setArea(user.getOffice().getArea());
					newOffice.setUseable(Global.YES);
					officeService.save(newOffice);

					newOffice2.setId(IdGen.uuid());
					newOffice2.setParent(newOffice);
					newOffice2.setParentIds("0," + company.getId() + ","
							+ newOffice.getId() + ",");
					newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
					newOffice2.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice2.setUpdateBy(user);
						newOffice2.setCreateBy(user);
					}
					newOffice2.setUpdateDate(new Date());
					newOffice2.setCreateDate(new Date());
					newOffice2.setIsNewRecord(true);
					newOffice2.setGrade("2");
					newOffice2.setArea(user.getOffice().getArea());
					newOffice2.setUseable(Global.YES);
					officeService.save(newOffice2);

					newOffice3.setId(IdGen.uuid());
					newOffice3.setParent(newOffice2);
					newOffice3.setParentIds("0," + company.getId() + ","
							+ newOffice.getId() + "," + newOffice2.getId()
							+ ",");
					newOffice3.setName(StringUtils.trimToEmpty(vals[2]));
					newOffice3.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice3.setUpdateBy(user);
						newOffice3.setCreateBy(user);
					}
					newOffice3.setUpdateDate(new Date());
					newOffice3.setCreateDate(new Date());
					newOffice3.setIsNewRecord(true);
					newOffice3.setGrade("3");
					newOffice3.setArea(user.getOffice().getArea());
					newOffice3.setUseable(Global.YES);
					officeService.save(newOffice3);
					return newOffice3;
				}
			}
		}

		if (vals.length == 4) {
			Office office1 = officeService.getOfficeByName(vals[0]);
			if (office1 != null) {
				List<Office> chilidOffice = officeService
						.getChilidOffice(office1);
				if (chilidOffice.size() > 0) {
					int flag = 1;
					Office office0 = new Office();
					for (Office office2 : chilidOffice) {
						if (office2.getName().equals(
								StringUtils.trimToEmpty(vals[1]))) {
							flag = 0;
							office0 = office2;
							break;
						}
					}
					if (flag == 1) {
						User user = UserUtils.getUser();
						Office company = officeService.get(user.getCompany());
						Office newOffice2 = new Office();
						Office newOffice3 = new Office();
						Office newOffice4 = new Office();
						newOffice2.setId(IdGen.uuid());
						newOffice2.setParent(office1);
						newOffice2.setParentIds("0," + company.getId() + ","
								+ office1.getId() + ",");
						newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
						newOffice2.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice2.setUpdateBy(user);
							newOffice2.setCreateBy(user);
						}
						newOffice2.setUpdateDate(new Date());
						newOffice2.setCreateDate(new Date());
						newOffice2.setIsNewRecord(true);
						newOffice2.setGrade("2");
						newOffice2.setArea(user.getOffice().getArea());
						newOffice2.setUseable(Global.YES);
						officeService.save(newOffice2);

						newOffice3.setId(IdGen.uuid());
						newOffice3.setParent(newOffice2);
						newOffice3.setParentIds("0," + company.getId() + ","
								+ office1.getId() + "," + newOffice2.getId()
								+ ",");
						newOffice3.setName(StringUtils.trimToEmpty(vals[2]));
						newOffice3.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice3.setUpdateBy(user);
							newOffice3.setCreateBy(user);
						}
						newOffice3.setUpdateDate(new Date());
						newOffice3.setCreateDate(new Date());
						newOffice3.setIsNewRecord(true);
						newOffice3.setGrade("3");
						newOffice3.setArea(user.getOffice().getArea());
						newOffice3.setUseable(Global.YES);
						officeService.save(newOffice3);

						newOffice4.setId(IdGen.uuid());
						newOffice4.setParent(newOffice3);
						newOffice4.setParentIds("0," + company.getId() + ","
								+ office1.getId() + "," + newOffice2.getId()
								+ "," + newOffice3.getId() + ",");
						newOffice4.setName(StringUtils.trimToEmpty(vals[3]));
						newOffice4.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice4.setUpdateBy(user);
							newOffice4.setCreateBy(user);
						}
						newOffice4.setUpdateDate(new Date());
						newOffice4.setCreateDate(new Date());
						newOffice4.setIsNewRecord(true);
						newOffice4.setGrade("4");
						newOffice4.setArea(user.getOffice().getArea());
						newOffice4.setUseable(Global.YES);
						officeService.save(newOffice4);
						return newOffice4;
					} else {
						List<Office> chilidOffice2 = officeService
								.getChilidOffice(office0);
						if (chilidOffice2.size() > 0) {
							int flag1 = 1;
							Office office00 = new Office();
							for (Office office3 : chilidOffice2) {
								if (office3.getName().equals(
										StringUtils.trimToEmpty(vals[2]))) {
									flag1 = 0;
									office00 = office3;
									break;
								}
							}
							if (flag1 == 1) {
								User user = UserUtils.getUser();
								Office newOffice3 = new Office();
								Office newOffice4 = new Office();
								Office company = officeService.get(user
										.getCompany());
								newOffice3.setId(IdGen.uuid());
								newOffice3.setParent(office0);
								newOffice3.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ","
										+ office0.getId() + ",");
								newOffice3.setName(StringUtils
										.trimToEmpty(vals[2]));
								newOffice3.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice3.setUpdateBy(user);
									newOffice3.setCreateBy(user);
								}
								newOffice3.setUpdateDate(new Date());
								newOffice3.setCreateDate(new Date());
								newOffice3.setIsNewRecord(true);
								newOffice3.setGrade("3");
								newOffice3.setArea(user.getOffice().getArea());
								newOffice3.setUseable(Global.YES);
								officeService.save(newOffice3);

								newOffice4.setId(IdGen.uuid());
								newOffice4.setParent(newOffice3);
								newOffice4.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ","
										+ office0.getId() + ","
										+ newOffice3.getId() + ",");
								newOffice4.setName(StringUtils
										.trimToEmpty(vals[3]));
								newOffice4.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice4.setUpdateBy(user);
									newOffice4.setCreateBy(user);
								}
								newOffice4.setUpdateDate(new Date());
								newOffice4.setCreateDate(new Date());
								newOffice4.setIsNewRecord(true);
								newOffice4.setGrade("4");
								newOffice4.setArea(user.getOffice().getArea());
								newOffice4.setUseable(Global.YES);
								officeService.save(newOffice4);
								return newOffice4;
							} else {
								List<Office> chilidOffice3 = officeService
										.getChilidOffice(office00);
								if (chilidOffice3.size() > 0) {
									int flag11 = 1;
									Office office000 = new Office();
									for (Office office4 : chilidOffice3) {
										if (office4.getName().equals(
												StringUtils
														.trimToEmpty(vals[3]))) {
											flag11 = 0;
											office000 = office4;
											break;
										}
									}
									if (flag11 == 1) {
										User user = UserUtils.getUser();
										Office newOffice4 = new Office();
										Office company = officeService.get(user
												.getCompany());
										newOffice4.setId(IdGen.uuid());
										newOffice4.setParent(office00);
										newOffice4.setParentIds("0,"
												+ company.getId() + ","
												+ office1.getId() + ","
												+ office0.getId() + ","
												+ office00.getId() + ",");
										newOffice4.setName(StringUtils
												.trimToEmpty(vals[3]));
										newOffice4.setType("2");
										if (StringUtils
												.isNotBlank(user.getId())) {
											newOffice4.setUpdateBy(user);
											newOffice4.setCreateBy(user);
										}
										newOffice4.setUpdateDate(new Date());
										newOffice4.setCreateDate(new Date());
										newOffice4.setIsNewRecord(true);
										newOffice4.setGrade("4");
										newOffice4.setArea(user.getOffice()
												.getArea());
										newOffice4.setUseable(Global.YES);
										officeService.save(newOffice4);
										return newOffice4;
									} else {
										return office000;
									}
								} else {
									User user = UserUtils.getUser();
									Office newOffice4 = new Office();
									Office company = officeService.get(user
											.getCompany());
									newOffice4.setId(IdGen.uuid());
									newOffice4.setParent(office00);
									newOffice4.setParentIds("0,"
											+ company.getId() + ","
											+ office1.getId() + ","
											+ office0.getId() + ","
											+ office00.getId() + ",");
									newOffice4.setName(StringUtils
											.trimToEmpty(vals[3]));
									newOffice4.setType("2");
									if (StringUtils.isNotBlank(user.getId())) {
										newOffice4.setUpdateBy(user);
										newOffice4.setCreateBy(user);
									}
									newOffice4.setUpdateDate(new Date());
									newOffice4.setCreateDate(new Date());
									newOffice4.setIsNewRecord(true);
									newOffice4.setGrade("4");
									newOffice4.setArea(user.getOffice()
											.getArea());
									newOffice4.setUseable(Global.YES);
									officeService.save(newOffice4);
									return newOffice4;
								}
							}
						} else {
							User user = UserUtils.getUser();
							Office newOffice2 = new Office();
							Office newOffice3 = new Office();
							Office newOffice4 = new Office();
							Office company = officeService.get(user
									.getCompany());
							if (company.getParentId().equals("0")
									&& company.getParentIds().equals("0,")) {
								newOffice2.setId(IdGen.uuid());
								newOffice2.setParent(office1);
								newOffice2.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ",");
								newOffice2.setName(StringUtils
										.trimToEmpty(vals[1]));
								newOffice2.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice2.setUpdateBy(user);
									newOffice2.setCreateBy(user);
								}
								newOffice2.setUpdateDate(new Date());
								newOffice2.setCreateDate(new Date());
								newOffice2.setIsNewRecord(true);
								newOffice2.setGrade("2");
								newOffice2.setArea(user.getOffice().getArea());
								newOffice2.setUseable(Global.YES);
								officeService.save(newOffice2);

								newOffice3.setId(IdGen.uuid());
								newOffice3.setParent(newOffice2);
								newOffice3.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ","
										+ newOffice2.getId() + ",");
								newOffice3.setName(StringUtils
										.trimToEmpty(vals[2]));
								newOffice3.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice3.setUpdateBy(user);
									newOffice3.setCreateBy(user);
								}
								newOffice3.setUpdateDate(new Date());
								newOffice3.setCreateDate(new Date());
								newOffice3.setIsNewRecord(true);
								newOffice3.setGrade("3");
								newOffice3.setArea(user.getOffice().getArea());
								newOffice3.setUseable(Global.YES);
								officeService.save(newOffice3);

								newOffice4.setId(IdGen.uuid());
								newOffice4.setParent(newOffice3);
								newOffice4.setParentIds("0," + company.getId()
										+ "," + office1.getId() + ","
										+ newOffice2.getId() + ","
										+ newOffice3.getId() + ",");
								newOffice4.setName(StringUtils
										.trimToEmpty(vals[3]));
								newOffice4.setType("2");
								if (StringUtils.isNotBlank(user.getId())) {
									newOffice4.setUpdateBy(user);
									newOffice4.setCreateBy(user);
								}
								newOffice4.setUpdateDate(new Date());
								newOffice4.setCreateDate(new Date());
								newOffice4.setIsNewRecord(true);
								newOffice4.setGrade("4");
								newOffice4.setArea(user.getOffice().getArea());
								newOffice4.setUseable(Global.YES);
								officeService.save(newOffice4);
								return newOffice4;
							}
						}
					}
				} else {
					User user = UserUtils.getUser();
					Office newOffice2 = new Office();
					Office newOffice3 = new Office();
					Office newOffice4 = new Office();
					Office company = officeService.get(user.getCompany());
					if (company.getParentId().equals("0")
							&& company.getParentIds().equals("0,")) {
						newOffice2.setId(IdGen.uuid());
						newOffice2.setParent(office1);
						newOffice2.setParentIds("0," + company.getId() + ","
								+ office1.getId() + ",");
						newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
						newOffice2.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice2.setUpdateBy(user);
							newOffice2.setCreateBy(user);
						}
						newOffice2.setUpdateDate(new Date());
						newOffice2.setCreateDate(new Date());
						newOffice2.setIsNewRecord(true);
						newOffice2.setGrade("2");
						newOffice2.setArea(user.getOffice().getArea());
						newOffice2.setUseable(Global.YES);
						officeService.save(newOffice2);

						newOffice3.setId(IdGen.uuid());
						newOffice3.setParent(newOffice2);
						newOffice3.setParentIds("0," + company.getId() + ","
								+ office1.getId() + "," + newOffice2.getId()
								+ ",");
						newOffice3.setName(StringUtils.trimToEmpty(vals[2]));
						newOffice3.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice3.setUpdateBy(user);
							newOffice3.setCreateBy(user);
						}
						newOffice3.setUpdateDate(new Date());
						newOffice3.setCreateDate(new Date());
						newOffice3.setIsNewRecord(true);
						newOffice3.setGrade("3");
						newOffice3.setArea(user.getOffice().getArea());
						newOffice3.setUseable(Global.YES);
						officeService.save(newOffice3);

						newOffice4.setId(IdGen.uuid());
						newOffice4.setParent(newOffice3);
						newOffice4.setParentIds("0," + company.getId() + ","
								+ office1.getId() + "," + newOffice2.getId()
								+ "," + newOffice3.getId() + ",");
						newOffice4.setName(StringUtils.trimToEmpty(vals[3]));
						newOffice4.setType("2");
						if (StringUtils.isNotBlank(user.getId())) {
							newOffice4.setUpdateBy(user);
							newOffice4.setCreateBy(user);
						}
						newOffice4.setUpdateDate(new Date());
						newOffice4.setCreateDate(new Date());
						newOffice4.setIsNewRecord(true);
						newOffice4.setGrade("4");
						newOffice4.setArea(user.getOffice().getArea());
						newOffice4.setUseable(Global.YES);
						officeService.save(newOffice4);
						return newOffice4;
					}
				}
			} else {
				User user = UserUtils.getUser();
				Office newOffice = new Office();
				Office newOffice2 = new Office();
				Office newOffice3 = new Office();
				Office newOffice4 = new Office();
				Office company = officeService.get(user.getCompany());
				if (company.getParentId().equals("0")
						&& company.getParentIds().equals("0,")) {
					newOffice.setId(IdGen.uuid());
					newOffice.setParent(company);
					newOffice.setParentIds("0," + company.getId() + ",");
					newOffice.setName(StringUtils.trimToEmpty(vals[0]));
					newOffice.setType("2");

					if (StringUtils.isNotBlank(user.getId())) {
						newOffice.setUpdateBy(user);
						newOffice.setCreateBy(user);
					}
					newOffice.setUpdateDate(new Date());
					newOffice.setCreateDate(new Date());
					newOffice.setIsNewRecord(true);
					newOffice.setGrade("1");
					newOffice.setArea(user.getOffice().getArea());
					newOffice.setUseable(Global.YES);
					officeService.save(newOffice);

					newOffice2.setId(IdGen.uuid());
					newOffice2.setParent(newOffice);
					newOffice2.setParentIds("0," + company.getId() + ","
							+ newOffice.getId() + ",");
					newOffice2.setName(StringUtils.trimToEmpty(vals[1]));
					newOffice2.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice2.setUpdateBy(user);
						newOffice2.setCreateBy(user);
					}
					newOffice2.setUpdateDate(new Date());
					newOffice2.setCreateDate(new Date());
					newOffice2.setIsNewRecord(true);
					newOffice2.setGrade("2");
					newOffice2.setArea(user.getOffice().getArea());
					newOffice2.setUseable(Global.YES);
					officeService.save(newOffice2);

					newOffice3.setId(IdGen.uuid());
					newOffice3.setParent(newOffice2);
					newOffice3.setParentIds("0," + company.getId() + ","
							+ newOffice.getId() + "," + newOffice2.getId()
							+ ",");
					newOffice3.setName(StringUtils.trimToEmpty(vals[2]));
					newOffice3.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice3.setUpdateBy(user);
						newOffice3.setCreateBy(user);
					}
					newOffice3.setUpdateDate(new Date());
					newOffice3.setCreateDate(new Date());
					newOffice3.setIsNewRecord(true);
					newOffice3.setGrade("3");
					newOffice3.setArea(user.getOffice().getArea());
					newOffice3.setUseable(Global.YES);
					officeService.save(newOffice3);

					newOffice4.setId(IdGen.uuid());
					newOffice4.setParent(newOffice3);
					newOffice4.setParentIds("0," + company.getId() + ","
							+ newOffice.getId() + "," + newOffice2.getId()
							+ "," + newOffice3.getId() + ",");
					newOffice4.setName(StringUtils.trimToEmpty(vals[3]));
					newOffice4.setType("2");
					if (StringUtils.isNotBlank(user.getId())) {
						newOffice4.setUpdateBy(user);
						newOffice4.setCreateBy(user);
					}
					newOffice4.setUpdateDate(new Date());
					newOffice4.setCreateDate(new Date());
					newOffice4.setIsNewRecord(true);
					newOffice4.setGrade("4");
					newOffice4.setArea(user.getOffice().getArea());
					newOffice4.setUseable(Global.YES);
					officeService.save(newOffice4);
					return newOffice4;
				}
			}
		}
		return null;
	}

}
