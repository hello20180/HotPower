package com.hnzy.hot.home.service;

import com.hnzy.hot.xtgl.pojo.User;

public interface RoleMenuService {
	/**
	 *判断用户是否有code对应的权限
	 * @param user 用户
	 * @param code 子系统的权限标识
	 * @return true or false
	 */
	public boolean isAccessible(User user,Integer id);
}
