package com.hnzy.hot.home.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hnzy.hot.home.pojo.Menu;
import com.hnzy.hot.home.service.RoleMenuService;
import com.hnzy.hot.xtgl.pojo.User;
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Override
	public boolean isAccessible(User user, Integer id) {

		List<Menu> menus=user.getMenus();
		for (int i = 0; i < menus.size(); i++)
		{
			if(id.equals(user.getMenus().get(i).getId())){
				return true;
			}
		}
		return false;
	}

}
