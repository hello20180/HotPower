package com.hnzy.hot.home.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.home.dao.MenuDao;
import com.hnzy.hot.home.pojo.Menu;
import com.hnzy.hot.home.service.MenuService;
import com.hnzy.hot.xtgl.pojo.RoleMenu;

@Service
public class MenuServiceImpl implements MenuService
{
	@Autowired
	private MenuDao menuDao;
	
	//编辑指定用户的角色
	@Override
	public int editURole(String[] roleId,String id) {
		menuDao.delURole(id);
		int addURole=0;
		RoleMenu userRole=new RoleMenu();
		for(int i=0;i<roleId.length;i++){
			userRole.setRoleId(id);
			userRole.setMenuId(roleId[i]);;
			addURole=menuDao.addURole(userRole)+addURole;
		}
		return addURole;
	}


	@Override
	public List<Menu> getSec(int pid)
	{
		return menuDao.getSec(pid);
	}

	@Override
	public List<Menu> find()
	{
		return menuDao.find();
	}

	@Override
	public List<Menu> xxglFind()
	{
		return menuDao.xxglFind();
	}

public void save(Menu menu) {
	if("".equals(menu.getUrl())||menu.getUrl()==null){
		menu.setUrl("#");
	}
  if("".equals(menu.getId())||menu.getId()==0)
   {
	menu.setPid(-1);
   }else if("".equals(menu.getPid())|| menu.getPid()==null)
		{
		menu.setPid(menu.getId());
	}
	
	 menuDao.Insert(menu);
}

public void update(Menu menu) {
  menuDao.update(menu);	
}

@Override
public void delete(int id) {
	menuDao.delete(id);
}

@Override
public List<Menu> getMenu() {
	return menuDao.getMenu();
}

@Override
public List<Menu> findMenuByRId(Integer id) {
	return menuDao.findMenuByRId(id);
}

@Override
public List<Menu> findMenuByRoleId(String id) {
	return menuDao.findMenuByRoleId(id);
}

@Override
public List<Menu> findMenuByUserId(int id) {
	return menuDao.findMenuByUserId(id);
}
}

