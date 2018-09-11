package com.thinkgem.jeesite.modules.sys.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 
 * <p>
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2014年3月3日 　<br/>
 * <p>
 * 
 * @author zhou-baicheng
 * 
 * @version 1.0,2014年3月3日
 * 
 *          Shiro管理下的Token工具类
 */
public class TokenManager {
	// 用户session管理
	public static final CustomSessionManager customSessionManager = SpringContextUtil
			.getBean("customSessionManager", CustomSessionManager.class);

	/**
	 * 获取当前登录的用户User对象
	 * 
	 * @return
	 */
	public static User getToken() {
		User token = (User) SecurityUtils.getSubject().getPrincipal();
		return token;
	}

	/**
	 * 获取当前用户的Session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 获取当前用户NAME
	 * 
	 * @return
	 */
	public static String getNickname() {
		return getToken().getName();
	}

	/**
	 * 获取当前用户ID
	 * 
	 * @return
	 */
	public static String getUserId() {
		return getToken() == null ? null : getToken().getId();
	}

	/**
	 * 把值放入到当前登录用户的Session里
	 * 
	 * @param key
	 * @param value
	 */
	public static void setVal2Session(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 从当前登录用户的Session里取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getVal2Session(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 获取验证码，获取一次后删除
	 * 
	 * @return
	 */
	public static String getYZM() {
		String code = (String) getSession().getAttribute("CODE");
		getSession().removeAttribute("CODE");
		return code;
	}

}
