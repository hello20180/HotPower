package com.hnzy.hot.xtgl.pojo;

import java.io.Serializable;
import java.util.List;

import com.hnzy.hot.home.pojo.Menu;

public class User implements Serializable {
	private int id;
	private String userName;
	private String passWord;
	private List<Role> roles;
	private List<Menu> menus;
	private int tlz;
	
public int getTlz()
	{
		return tlz;
	}

	public void setTlz(int tlz)
	{
		this.tlz = tlz;
	}

private List<UserRole> ur;

	public List<UserRole> getUr()
{
	return ur;
}

public void setUr(List<UserRole> ur)
{
	this.ur = ur;
}

	private Role role;
	
	public User()
	{
		super();
	}

	public User(int id, String userName, String passWord, List<Role> roles, List<Menu> menus,Role role)
	{
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
		this.menus = menus;
		this.role = role;
	}

	

	@Override
	public String toString()
	{
		return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", roles=" + roles + ", menus="
				+ menus + ", role=" + role + "]";
	}

	public List<Menu> getMenus()
	{
		return menus;
	}

	public void setMenus(List<Menu> menus)
	{
		this.menus = menus;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
