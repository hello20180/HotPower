package com.hnzy.hot.xtgl.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hnzy.hot.home.pojo.Menu;


public class Role implements Serializable
{
	private Integer id;//主键
	private String name;//角色名称
	private List<Menu> menus;//菜单列表
	private List<User> users;//用户列表
	private Date createTime;//创建时间
	private Date lastEditTime;//最后修改时间
	private User user; //与用户表关联的字段
	private List<RoleMenu> roleMenus;
	
	public Role()
	{
		super();
	}
	public Role(Integer id)
	{
		super();
		this.id = id;
	}
	public Role(Integer id, String name, List<Menu> menus, List<User> users, Date createTime, Date lastEditTime,
			User user, List<RoleMenu> roleMenus)
	{
		super();
		this.id = id;
		this.name = name;
		this.menus = menus;
		this.users = users;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.user = user;
		this.roleMenus = roleMenus;
	}

	public List<RoleMenu> getRoleMenus()
	{
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenu> roleMenus)
	{
		this.roleMenus = roleMenus;
	}

	public List<Menu> getMenus()
	{
		return menus;
	}

	public void setMenus(List<Menu> menus)
	{
		this.menus = menus;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getLastEditTime()
	{
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime)
	{
		this.lastEditTime = lastEditTime;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
	
	
	
	
}
