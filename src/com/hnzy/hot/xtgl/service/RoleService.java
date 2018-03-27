package com.hnzy.hot.xtgl.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.LazyDynaList;

import com.hnzy.hot.xtgl.pojo.Role;
import com.hnzy.hot.xtgl.pojo.User;

/**
 * 
 *
 */
public interface RoleService {
	/**
	 * 查询角色列表
	 * @return
	 */
	public List<Role> findAllRole();
	/**
	 * 添加一个角色
	 * @param user
	 */
	public void save(Role role);
	/**
	 * 修改一个角色
	 * @param user
	 */
	public void edit(Role role);
	/**
	 * 删除角色，支持一次删除多个
	 * @param ids
	 */
	public void delete(String id);
	/**
	 * 查询某个角色下的所有用户
	 */
	public List<User> findUsers(String id);
	//根据用户的id查询用户的角色
	public List<Role> findRoleByUId(String userName);
	public List<Role>findRoleId(String id);
	/**
	 * 根据用户的ID查询该用户的角色
	 * @param id
	 * @return
	 */
	//public List<Role> findRoleNotByUId(String id);
	//修改用户角色
	//public int editURole(String[] roleId,String id);
	int editURole(String[] roleId, String id);
	
}
