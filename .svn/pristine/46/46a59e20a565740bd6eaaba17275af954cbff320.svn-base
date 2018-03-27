package com.hnzy.hot.xtgl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.xtgl.pojo.User;

public interface UserService {
	/**
	 * @author Administrator 查找用户信息
	 */
	public List<User> findUser();
	/**
	 * @author Administrator
	 *根据用户名和密码查找
	 */
	public boolean findNamePass(String userName,String passWord);
    //根据密码查找
	public User findPassWord(String passWord);
	/**
	 * @author Administrator 插入用户信息
	 */
	public void InsertUser(User user);

	/**
	 * @author Administrator 删除用户
	 */
	public void deleteUser(String id);

	/**
	 * @author Administrator 根据id查找用戶信息
	 */
	public User findById(int id);

	/**
	 * @author Administrator 更新用户信息
	 */
	public void updateUse(User user);
	/**
	 * 判断名字是否重复
	 * @param name
	 * @return
	 */
	public int addCheck(@Param("userName")String userName);
	public List<User> findJSName();
}
