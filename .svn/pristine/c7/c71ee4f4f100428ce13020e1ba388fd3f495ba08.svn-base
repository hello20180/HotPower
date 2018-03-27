package com.hnzy.hot.xtgl.service.Impl;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xtgl.dao.RoleDao;
import com.hnzy.hot.xtgl.dao.UserDao;
import com.hnzy.hot.xtgl.pojo.User;
import com.hnzy.hot.xtgl.pojo.UserRole;
import com.hnzy.hot.xtgl.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	@Autowired
    private RoleDao roleDao;
	@Override
	public List<User> findUser()
	{
		return userDao.find();
	}
    
	@Override
	public void InsertUser(User user)
	{
		userDao.Insert(user);
		UserRole userRole = new UserRole();
		userRole.setRoleId(String.valueOf(user.getRole().getId()));
		userRole.setUserId(String.valueOf(userDao.findID(user.getUserName())));
		roleDao.addURole(userRole);	
	}

	@Override
	public void deleteUser(String id)
	{
		userDao.delete(Integer.parseInt(id));
	}

	@Override
	public User findById(int id)
	{
		return userDao.findById(id);
	}

	@Override
	public void updateUse(User user)
	{
		userDao.update(user);
	}

	@Override
	public boolean findNamePass(String userName, String passWord)
	{
		boolean flag = false;
		User user = new User();
		user.setPassWord(passWord);
		user.setUserName(userName);
		User us = userDao.findNamePass(user);

		if (us != null)
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	@Override
	public User findPassWord(String passWord)
	{
		return userDao.findPassWord(passWord);
	}

	@Override
	public int addCheck(String userName)
	{
		return userDao.addCheck(userName);
	}

	@Override
	public List<User> findJSName()
	{
		return userDao.findJSName();
	}


}
