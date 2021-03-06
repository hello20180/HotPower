package com.hnzy.hot.xtgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xtgl.dao.RoleDao;
import com.hnzy.hot.xtgl.pojo.Role;
import com.hnzy.hot.xtgl.pojo.User;
import com.hnzy.hot.xtgl.pojo.UserRole;
import com.hnzy.hot.xtgl.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	

	//添加新角色
	@Override
	public void save(Role role) {
		roleDao.Insert(role);
	}
	//修改角色
	@Override
	public void edit(Role role) {
		roleDao.update(role);
	}
	//删除角色
	@Override
	public void delete(String id) {
		roleDao.delete(Integer.parseInt(id));
		
	}
	//查询某个角色下的所有用户
	@Override
	public List<User> findUsers(String id) {
		return roleDao.findUsers(id);
	}
	//查询所有角色
	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}
	//根据用户Id查询用户角色
	@Override
	public List<Role> findRoleByUId(String userName) {
		return roleDao.findRoleByUId(userName);
	}

	//编辑指定用户的角色
	@Override
	public int editURole(String[] roleId,String id) {
		roleDao.delURole(id);
		int addURole=0;
		UserRole userRole=new UserRole();
		for(int i=0;i<roleId.length;i++){
			userRole.setUserId(id);
			userRole.setRoleId(roleId[i]);
			addURole=roleDao.addURole(userRole)+addURole;
		}
		return addURole;
	}
	@Override
	public List<Role> findRoleId(String id)
	{
		return roleDao.findRoleId(id);
	}
}
