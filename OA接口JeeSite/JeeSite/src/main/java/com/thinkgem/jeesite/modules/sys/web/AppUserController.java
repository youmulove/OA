package com.thinkgem.jeesite.modules.sys.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.utils.VerifyCode;

/**
 * 移动端用户Controller
 * 
 * @author ThinkGem
 * @version 2017.09.19
 */
@Controller
@RequestMapping("appuser")
public class AppUserController extends BaseController {
	// 此处是定义一个全局变量params,下面能够用到map的都可以使用params这个变量。
	private Map<String, Object> imageCode = new HashMap<String, Object>();
	private Map<String,Object>  Msg=new HashMap<String,Object>();
	private Map<String,Object>  mailCode=new HashMap<String,Object>();
	
	@SuppressWarnings("unused")
	@Autowired
	private UserDao userDao;

	@Autowired
	private SystemService systemService;
    @Autowired
    private OfficeService officeService;
	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return systemService.getUser(id);
		} else {
			return new User();
		}
	}

	/**
	 * 柳杰(App中图片验证码的显示接口)
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "appVerifyCode")
	@ResponseBody
	public JsonResult<Map<String, Object>> appVerifyCode(
			HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1.生成图片 2.保存图片上的文本 到session域中 3.把图片显示给客户端
		 */

		VerifyCode vc = new VerifyCode();// 首先应该拿到VerifyCode类
		BufferedImage image = vc.getImage();
		request.getSession().setAttribute("session_vcode", vc.getText());
		// 保存图片上的文本到session域当中
		String code = (String) request.getSession().getAttribute(
				"session_vcode");
		String loginName=UserUtils.getUser().getLoginName();
		imageCode.put(loginName, code);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", code);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"获取图片验证码成功", map);
	}

	/**
	 * 柳杰(App中用户信息显示及保存 )
	 * 
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "appinfo")
	@ResponseBody
	public JsonResult<Map<String, Object>> appinfo(HttpServletRequest request,
			HttpServletResponse response, User user,
			@RequestParam String loginName) {
		try {
			request.setCharacterEncoding("UTF-8"); // 请求编码post
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");// 设置响应编码
		Map<String,Object> map=new HashMap<String,Object>();
		user = UserUtils.getByLoginName(loginName);
		user.setLoginName(user.getLoginName());
		user.setName(user.getName());
		user.setNo(user.getNo());
		user.setEmail(user.getEmail());
		user.setMobile(user.getMobile());
		user.setOffice(user.getOffice());
		user.setState(user.getState());
		User user1 = systemService.updateUserInfo1(user);
	    Office office=user.getOffice();
	    String ids=office.getParentIds();
	    String[] str=ids.split(",");
	    Office officeNew=null;
	    if(str.length>2){
	    	officeNew=officeService.get(str[2]);
	    }else if(str.length==2){
	    	officeNew=office;
	    }else{
	    	officeNew=office;
	    }
	    map.put("officeName", officeNew.getName());
		map.put("user", user1);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"用户信息展示", map);
	}

	/**
	 * 柳杰(移动端的修改个人用户密码的接口 )
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "appModifyPwd")
	@ResponseBody
	public JsonResult<Map<String, Object>> appModifyPwd(
			@RequestParam String loginName, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		new HashMap<String, Object>();
		User user = new User();
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}
		user.setLoginName(loginName);
		user.setNewPassword(newPassword);
		user.setOldPassword(oldPassword);
		if (StringUtils.isNotBlank(oldPassword)
				&& StringUtils.isNotBlank(newPassword)) {
			if (SystemService.validatePassword(oldPassword, user.getPassword())) {
				systemService.updatePasswordById(user.getId(),
						user.getLoginName(), newPassword);
				return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
						"修改密码成功", null);
			} else {
				return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
						"修改密码失败", null);
			}
		}
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"修改密码成功", null);
	}

	/**
	 * 柳杰(用户修改手机号第一步 )
	 * 
	 * 
	 * @param newPhone
	 * @param verifycode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "appsendSms")
	@ResponseBody
	public JsonResult<Map<String, Object>> appsendSms(User user,
			HttpServletRequest request, @RequestParam String newPhone,
			@RequestParam String verifycode, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String,Object>  map=new HashMap<String,Object>();
		String code = (String) imageCode.get(UserUtils.getUser().getLoginName());
//		String oldPhone = user.getLoginName();
		String regex = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,0-9]))\\d{8}";
		if (StringUtils.isBlank(newPhone)) {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"手机号不能为空", null);
		}
		if (StringUtils.isNotBlank(newPhone) && !newPhone.matches(regex)) {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"手机号格式不一样", null);
		} else {
			if (verifycode.equalsIgnoreCase(code)) {
				Random a = new Random();
				int b = (a.nextInt(8999) + 1000);
				Long code1 = (long) b;
				String code2 = code1 + "";
				String msgcode1 = code2.replaceAll(" ", "");
				URLEncoder.encode(msgcode1, "utf-8");
				System.out.println(msgcode1 + newPhone);
				//AppSmsApi.SmsCodeSend(newPhone, msgcode1, response);
				SmsApi.SmsCodeSend(newPhone, msgcode1);
				Msg.put(UserUtils.getUser().getLoginName(), msgcode1);
				map.put("newPhone", newPhone);
				
				return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
						"图片验证码一致，将发送短信验证码到您的手机，请注意查收", map);
			} else {
				return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
						"输入验证码和图片验证码不一致，请您重新输入", null);
			}
		}

	}

	/**
	 * 柳杰(修改个人手机号第二步的接口)
	 * 
	 * @param loginName
	 * @param newPhone
	 * @return
	 */
	@RequestMapping(value = "appModifyPhone")
	@ResponseBody
	public JsonResult<Map<String, Object>> appModifyPhone(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam String newPhone, @RequestParam String loginName,
			@RequestParam String msgcode) {

		User user = null;
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}
		if (StringUtils.isBlank(newPhone)) {
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"新手机号不允许为空", null);
		}
		String msgcode1 = (String) Msg.get(UserUtils.getUser().getLoginName());
		if (msgcode.equalsIgnoreCase(msgcode1)) {
			systemService.updatePhoneById(user.getId(), newPhone);
			UserUtils.getSubject().logout();
			UserUtils.clearCache(user);
			// // 清除权限缓存
		   // systemRealm.clearAllCachedAuthorizationInfo();
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"用户修改手机号成功", null);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"用户修改手机号失败", null);
		}
	}

	/**
	 * 柳杰( 修改个人邮箱第一步的接口)
	 * 
	 * @param loginName
	 * @param email
	 * @return
	 */
	@RequestMapping(" appModifyEmail1")
	@ResponseBody
	public JsonResult<Map<String, Object>> appModifyEmail1(
			HttpServletRequest request, @RequestParam String verifycode,
			@RequestParam String toMailAddr) {
		String code = (String) imageCode.get(UserUtils.getUser().getLoginName());
		if (verifycode.equalsIgnoreCase(code)) {

			Long code1 = (long) Math
					.round((int) (Math.random() * (9999 - 1000 + 1)) + 1000);

		
			String code2 = code1 + "";
			String mailcode1 = code2.replaceAll(" ", "");
			String mail = "您的邮箱验证码为：" + mailcode1;
			AppMailUtils.send(toMailAddr, mail);
			mailCode.put(UserUtils.getUser().getLoginName(),mailcode1);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"邮箱验证码已经发到您的邮箱，请注意查收，不要泄露哦", null);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"输入验证码和图片验证码不一致，请重新输入", null);
		}
	}

	/**
	 * 柳杰( 修改个人邮箱第二步的接口)
	 * 
	 * @param email
	 * @param mailcode
	 * @return
	 */
	@RequestMapping(" appModifyEmail2")
	@ResponseBody
	public JsonResult<Map<String, Object>> appModifyEmail2(
			HttpServletRequest request, @RequestParam String email,
			@RequestParam String mailcode, @RequestParam String loginName) {
		User user = null;
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}
		System.out.println(user);
		if (StringUtils.isBlank(email)) {
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"邮箱不允许为空", null);
		}
		String mailcode1 = (String) mailCode.get(UserUtils.getUser().getLoginName());
		if (mailcode.equalsIgnoreCase(mailcode1)) {
			systemService.updateEmailById(user.getId(), mailcode, email);
			UserUtils.clearCache(user);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"用户修改邮箱成功", null);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"用户修改邮箱失败", null);
		}
	}

	/**
	 * 柳杰( 修改个人邮箱的接口)
	 * 
	 * @param loginName
	 * @param email
	 * @return
	 */
	@RequestMapping(" appModifyEmail-yuan")
	@ResponseBody
	public JsonResult<Map<String, Object>> appModifyEmailyuan(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam String loginName) {
		User user = null;
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}
		if (StringUtils.isBlank(email)) {
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"邮箱不允许为空", null);
		}
		if (true) {
			systemService.updateEmailById(user.getId(), loginName, email);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"用户修改邮箱成功", null);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"用户修改邮箱失败", null);
		}
	}

	/**
	 * 柳杰(修改用户性别接口)
	 * 
	 * @param loginName
	 * @param mobile性别
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(" appModifyMobile")
	@ResponseBody
	public JsonResult<Map<String, Object>> appModifyMobile(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 下面是对于请求和响应中文乱码时进行处理
		request.setCharacterEncoding("UTF-8"); // 请求编码post
		response.setContentType("text/html;charset=UTF-8");// 设置响应编码
		String loginName = request.getParameter("loginName");
		String mobile = request.getParameter("mobile");
		User user = null;
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}
		if (StringUtils.isBlank(mobile)) {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"性别不允许为空", null);
		}
		if (true) {
			systemService.updateMobileById(loginName, mobile);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"性别修改成功", null);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"性别修改失败", null);
		}
	}

	/*
	 * 柳杰 (app端进行接受消息的接口)
	 * 
	 * @param loginName
	 * 
	 * @param state
	 */
	@RequestMapping(value = "/appSetMessage")
	@ResponseBody
	public JsonResult<Map<String, Object>> appSetMessage(User user,
			HttpServletRequest request, @RequestParam String loginName,
			@RequestParam String state) throws IOException {
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}

		if (StringUtils.isBlank(state)) {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"状态不允许为空", null);
		}
		if (true) {
			systemService.updateStateById(loginName, state);
			request.getSession().setAttribute("state", state);
			return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
					"状态修改成功", null);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"状态修改失败", null);
		}
	}

	/*
	 * 柳杰 (app端记录接受消息后的在数据库的消息状态state)
	 * 
	 * @param loginName
	 * 
	 * @param recordstate
	 */
	
	@RequestMapping(value = "/appSetMessageAfter")
	@ResponseBody
	public JsonResult<Map<String, Object>> appSetMessageAfter(User user,
			HttpServletRequest request, @RequestParam String loginName,
			HttpServletResponse response) throws IOException {
		if (null != UserUtils.getByLoginName(loginName)) {
			user = UserUtils.getByLoginName(loginName);
		} else {
			return new JsonResult<Map<String, Object>>(JsonResult.ERROR,
					"error", null);
		}
		String state = systemService.getStateByid(loginName);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state", state);
		return new JsonResult<Map<String, Object>>(JsonResult.SUCCESS,
				"查询成功", map);
	}
}
