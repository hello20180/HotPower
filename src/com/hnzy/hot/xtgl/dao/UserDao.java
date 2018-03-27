package com.hnzy.hot.xtgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.xtgl.pojo.User;

public interface UserDao extends BaseDao<User>{
	public User findNamePass(User user);
	//根据密码查找
	//public int findpwd(String passWord);
	public User findPassWord(String passWord);
	/**
	 * 判断名字是否重复
	 * @param name
	 * @return
	 */
	public int addCheck(@Param("userName")String userName);
	//查找插入的Id
	public int findID(@Param("userName")String userName);
	public void delete(String id);
	public List<User> findJSName();
}
