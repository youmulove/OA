/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 * 
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {

	/**
	 * 根据登录名称查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 根据邮箱查询用户是否存在
	 * 
	 * @return
	 */
	public User getUserByEmail(String email);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);

	/**
	 * 通过sysrankId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUserBysysrankId(String sysrankId, String companyId);

	/**
	 * 查询全部用户数目
	 * 
	 * @return
	 */
	public long findAllCount(User user);

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int updateEamilById(User user);

	/**
	 * 更新用户手机号
	 * 
	 * @param user
	 * @return
	 */
	public int updatePhoneById(User user);

	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * 
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);

	/**
	 * 插入用户角色关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	public List<User> findUserId();

	/**
	 * ======= 更新性别
	 * 
	 * @param user
	 * @return
	 */
	public int updateMobileById(User user);

	/**
	 * ======= 更新用户接受信息状态
	 * 
	 * @param user
	 * @return
	 */
	public int updateStateById(User user);

	/**
	 * 插入好友
	 */
	public int insertFriend(@Param("id") String id,
			@Param("userId") String userId, @Param("friendId") String friendId);

	/**
	 * 查找好友
	 */
	public User findFriend(@Param("userId") String userId,
			@Param("friendId") String friendId);

	/**
	 * 删除好友
	 */
	public void deleteFriend(@Param("userId") String userId,
			@Param("friendId") String friendId);

	/**
	 * 
	 * 获取我的好友列表
	 * 
	 */
	public List<User> findFriends(User currentUser);

	/**
	 * 
	 * 查询用户-->用来添加到常用联系人
	 * 
	 */
	public List<User> searchUsers(User user);

	/**
	 * 
	 */

	public List<User> findListByOffice(User user);

}
