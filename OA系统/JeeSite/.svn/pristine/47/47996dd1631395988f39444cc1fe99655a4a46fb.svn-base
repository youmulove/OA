package com.thinkgem.jeesite.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.SysRank;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * 
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {
	private Map<String, String> map = new HashMap<String, String>();
	@Autowired
	private SystemService systemService;

	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return systemService.getUser(id);
		} else {
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "index" })
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}

	@RequiresPermissions("sys:user:v")
	@RequestMapping(value = { "index1" })
	public String index1(User user, Model model) {
		return "modules/sys/userIndex1";
	}

	/*
	 * @RequiresPermissions("sys:user:view")
	 * 
	 * @RequestMapping(value = {"list", ""}) public String list(User user,
	 * HttpServletRequest request, HttpServletResponse response, Model model) {
	 * Page<User> page = systemService.findUser(new Page<User>(request,
	 * response), user); String roleNames=page.getList().get(1).getRoleNames();
	 * model.addAttribute("page", page); return "modules/sys/userList"; }
	 */

	/*
	 * 查询所有用户的角色信息
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "list", "" })
	public ModelAndView list(User user, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Page<User> page = systemService.findUser(new Page<User>(request,
				response), user);
		// model.addAttribute("page", page);
		List<User> roleList = systemService.findUserRoles();

		Map<String, String> RoleListMap = new HashMap<String, String>();
		Iterator<User> iter = roleList.iterator();
		while (iter.hasNext()) {
			User users = iter.next();
			RoleListMap.put(users.getId(), users.getRoleNames());
		}
		mv.addObject("page", page);
		mv.addObject("RoleLists", RoleListMap);
		mv.setViewName("modules/sys/userList");
		return mv;
	}

	/*
	 * 查询所有用户的角色信息
	 */
	@RequiresPermissions("sys:user:v")
	@RequestMapping(value = { "list1", "" })
	public ModelAndView list1(User user, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Page<User> page = systemService.findUser(new Page<User>(request,
				response), user);
		// model.addAttribute("page", page);
		List<User> roleList = systemService.findUserRoles();

		Map<String, String> RoleListMap = new HashMap<String, String>();
		Iterator<User> iter = roleList.iterator();
		while (iter.hasNext()) {
			User users = iter.next();
			RoleListMap.put(users.getId(), users.getRoleNames());
		}
		mv.addObject("page", page);
		mv.addObject("RoleLists", RoleListMap);
		mv.setViewName("modules/sys/userList1");
		return mv;
	}

	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "listData" })
	public Page<User> listData(User user, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request,
				response), user);
		return page;
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		if (user.getCompany() == null || user.getCompany().getId() == null) {
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice() == null || user.getOffice().getId() == null) {
			user.setOffice(UserUtils.getUser().getOffice());
		}
		if (user.getSysrank() == null || user.getSysrank().getId() == null) {
			user.setSysrank(UserUtils.getUser().getSysrank());
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userForm";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		user.setSysrank(new SysRank(request.getParameter("sysrank.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user
					.getNewPassword()));
		}
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(),
				user.getLoginName()))) {
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		List<Role> findAllRole = systemService.findAllRole();
		for (Role role : findAllRole) {
			String roleId = role.getId();
			if (role.getRoleType().equals("user"))
				roleIdList.add(roleId);
		}

		for (Role r : systemService.findAllRole()) {

			roleList.add(r);
			if (!r.getRoleType().equals("user")) {

			}
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.clearCache();
			// UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if (UserUtils.getUser().getId().equals(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		} else if (User.isAdmin(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		} else {
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 导出用户数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:e")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(User user, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "员工通讯录" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			// Page<User> page = systemService.findUser(new Page<User>(request,
			// response, -1), user);
			Page<User> page = systemService.findWithRoles(new Page<User>(
					request, response, -1), user);
			new ExportExcel("员工通讯录", User.class).setDataList(page.getList())
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 导入用户数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:i")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list) {
				try {
					if ("true".equals(checkLoginName("", user.getLoginName()))) {
						user.setPassword(SystemService
								.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					} else {
						failureMsg.append("<br/>手机号 " + user.getLoginName()
								+ " 已存在; ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>手机号 " + user.getLoginName()
							+ " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>手机 " + user.getLoginName()
							+ " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户"
					+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "员工通讯录导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			list.add(UserUtils.getUser());
			new ExportExcel("员工通讯录", User.class, 2).setDataList(list)
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 验证登录名是否有效
	 * 
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName != null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName != null
				&& systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}
			currentUser.setName(user.getName());
			currentUser.setLoginName(user.getLoginName());
			currentUser.setNo(user.getNo());
			currentUser.setEmail(user.getEmail());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回用户信息
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}

	/**
	 * 修改个人用户密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword,
			String confirmNewPassword, Model model) {
		User user = UserUtils.getUser();
		String re = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
		if (StringUtils.isNotBlank(oldPassword)
				&& StringUtils.isNotBlank(newPassword)) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			if (newPassword.length() < 6 || confirmNewPassword.length() < 6) {
				model.addAttribute("message", "修改密码失败，新密码位数应不少于六位！");
				return "modules/sys/userModifyPwd";
			}
			if (!newPassword.matches(re) || !confirmNewPassword.matches(re)) {
				model.addAttribute("message", "修改密码失败，新密码需由数字和字母组成且不能大于20位！");
				return "modules/sys/userModifyPwd";
			}
			if (newPassword.length() >= 6
					&& confirmNewPassword.length() >= 6
					&& newPassword.matches(re)
					&& confirmNewPassword.matches(re)
					&& SystemService.validatePassword(oldPassword,
							user.getPassword())) {
				systemService.updatePasswordById(user.getId(),
						user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param newPhone
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "sendSms")
	public String sendSms(String newPhone, Model model)
			throws UnsupportedEncodingException {
		User user = UserUtils.getUser();
		String oldPhone = user.getLoginName();
		model.addAttribute("newPhone", newPhone);
		String regex = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,0-9]))\\d{8}";
		if (StringUtils.isBlank(newPhone)) {
			model.addAttribute("message", "手机号不能为空!");
		}
		if (oldPhone.equals(newPhone)) {
			model.addAttribute("message", "新手机号不能与旧手机号相同!");
		}
		if (StringUtils.isNotBlank(newPhone) && !newPhone.matches(regex)) {
			model.addAttribute("message", "手机格式输入不正确!");
		} else {
			Long code1 = Math.round(Math.random() * 1000000);
			String code2 = code1 + "";
			String code = code2.replaceAll(" ", "");
			URLEncoder.encode(code, "utf-8");
			System.out.println(code + newPhone);
			SmsApi.SmsCodeSend(newPhone, code);
			map.put("newPhone", newPhone);
			map.put("code", code);
		}
		model.addAttribute("user", user);
		return "true";
		// return "modules/sys/userModifyPhone";

	}

	/**
	 * 修改个人手机号
	 * 
	 * @param phone
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPhone")
	public String modifyPhone(String newPhone, String verifycode,
			String validateCode, HttpServletRequest request, Model model) {
		// ValidateCodeServlet.validate(request, validateCode);
		User user = UserUtils.getUser();
		model.addAttribute("newPhone", newPhone);
		if (StringUtils.isNotBlank(newPhone)) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPhone";
			}
			if (!verifycode.equals(map.get("code"))) {
				model.addAttribute("message", "验证码输入错误");
			} else {
				systemService.updatePhoneById(user.getId(), newPhone);
				model.addAttribute("message", "修改手机号成功");
			}
		}
		model.addAttribute("verifycode", verifycode);
		model.addAttribute("user", user);
		return "modules/sys/userModifyPhone";
	}

	/**
	 * 图片验证码
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkCode")
	public String checkCode(HttpServletRequest request, String validateCode) {
		String a = ValidateCodeServlet.validate(request, validateCode) ? "true"
				: "false";
		System.out.println(a);
		return a;

	}

	/**
	 * 修改手机号和邮箱之前校验密码
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkPwd")
	public boolean checkPwd(HttpServletRequest request, String pwd) {
		User user = UserUtils.getUser();
		String password = user.getPassword();
		boolean validatePassword = systemService
				.validatePassword(pwd, password) ? true : false;
		System.out.println(validatePassword);
		return validatePassword;

	}

	/**
	 * 发送邮箱验证码
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "sendMail")
	public String sendMail(String email, Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("email", email);
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (StringUtils.isBlank(email)) {
			model.addAttribute("message", "邮箱地址不能为空!");
		}
		if (email.equals(user.getEmail())) {
			model.addAttribute("message", "新邮箱不能与旧邮箱相同!");
		}
		if (StringUtils.isNotBlank(email) && !email.matches(regex)) {
			model.addAttribute("message", "邮箱地址格式输入不正确!");
		} else {
			Long code1 = Math.round(Math.random() * 1000000);
			String code2 = code1 + "";
			String mailcode = code2.replaceAll(" ", "");
			MailUtils.send(email, mailcode);
			System.out.println("邮件发送成功!");
			map.put("email", email);
			map.put("mailcode", mailcode);
		}
		model.addAttribute("user", user);
		return "true";
	}

	/**
	 * 修改个人邮箱
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyEmail")
	public String modifyEmail(String email, String verifycode,
			String loginName, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(email)) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyEmail";
			}
			if (!verifycode.equals(map.get("mailcode"))) {
				model.addAttribute("message", "验证码输入错误");
			}
			if (verifycode.equals(map.get("mailcode"))) {
				systemService.updateEmailById(user.getId(), loginName, email);
				model.addAttribute("message", "修改邮箱成功");
			} else {
				model.addAttribute("message", "修改邮箱失败");
			}
		}
		model.addAttribute("email", email);
		model.addAttribute("user", user);
		return "modules/sys/userModifyEmail";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String officeId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i = 0; i < list.size(); i++) {
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_" + e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	// @InitBinder
	// public void initBinder(WebDataBinder b) {
	// b.registerCustomEditor(List.class, "roleList", new
	// PropertyEditorSupport(){
	// @Autowired
	// private SystemService systemService;
	// @Override
	// public void setAsText(String text) throws IllegalArgumentException {
	// String[] ids = StringUtils.split(text, ",");
	// List<Role> roles = new ArrayList<Role>();
	// for (String id : ids) {
	// Role role = systemService.getRole(Long.valueOf(id));
	// roles.add(role);
	// }
	// setValue(roles);
	// }
	// @Override
	// public String getAsText() {
	// return Collections3.extractToString((List) getValue(), "id", ",");
	// }
	// });
	// }
}
