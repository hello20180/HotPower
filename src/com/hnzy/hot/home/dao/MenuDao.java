package com.hnzy.hot.home.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.home.pojo.Menu;
import com.hnzy.hot.xtgl.pojo.RoleMenu;

public interface MenuDao extends BaseDao<Menu>{
    //根据pid查找子菜单,查询二级菜单
	public List<Menu>getSec(int pid);
	//xxgl左边菜单
	 public List<Menu>xxglFind();
	 //查询一级菜单
	 public List<Menu >getMenu();
	 //根据角色的Id查询该用户的菜单
	 public List<Menu> findMenuByRId(Integer rid);
	 //根据角色的ID查询该用户的菜单，结果含一级菜单和二级裁断
	 public List<Menu> findMenuByRoleId(String id);
		/**
		 * 增加用户角色
		 */
		public int addURole(RoleMenu userRole);
		/**
		 * 删除用户角色
		 */
		public int delURole(String id);
		/**
		 * 根据用户id 查询拥有的菜单
		 * @param id
		 * @return
		 */
		public List<Menu> findMenuByUserId(int id);
	
}
