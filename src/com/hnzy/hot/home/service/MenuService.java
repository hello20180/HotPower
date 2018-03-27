package com.hnzy.hot.home.service;

import java.util.List;

import com.hnzy.hot.home.pojo.Menu;

public interface MenuService {
	public List<Menu> getSec(int pid);
   //查询所有的菜单
    public List<Menu>find();
    //查询左边下拉页面
    public List<Menu>xxglFind();
    //保存菜单
    public void save(Menu menu);
    //修改菜单
    public void update(Menu menu);
    //根据id删除菜单
    public void delete(int id);
    //查询一级菜单
    public List<Menu>getMenu();
	 //根据角色的Id查询该用户的菜单
	 public List<Menu> findMenuByRId(Integer id);
	 //根据角色的ID查询该用户的菜单，结果含一级菜单和二级裁断
	 public List<Menu>findMenuByRoleId(String id);
	int editURole(String[] roleId, String id);
	/**
	 * 根据用户id 查询拥有的菜单
	 * @param id
	 * @return
	 */
	public List<Menu> findMenuByUserId(int id);
}
