package com.hnzy.hot.home.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hnzy.hot.xtgl.pojo.Role;



public class Menu implements Serializable {
	private int id;//主键
	private String name;//菜单名称
	private String url;//菜单地址
	private Integer pid;//父id
	private List<Menu> childMenus;//子菜单
	private Date createTime;//创建时间
	private Date lastEditTime;//最后修改时间
	private List<Role> roles;
	
	public Menu()
	{
	}
	
	
	public Menu(int id, String name, String url, Integer pid, List<Menu> childMenus, Date createTime, Date lastEditTime,
			List<Role> roles)
	{
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.pid = pid;
		this.childMenus = childMenus;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.roles = roles;
	}


	public List<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public List<Menu> getChildMenus()
	{
		return childMenus;
	}
	public void setChildMenus(List<Menu> childMenus)
	{
		this.childMenus = childMenus;
	}
	@Override
	public String toString()
	{
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", pid=" + pid + ", childMenus=" + childMenus
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", roles=" + roles + "]";
	}

	

}
