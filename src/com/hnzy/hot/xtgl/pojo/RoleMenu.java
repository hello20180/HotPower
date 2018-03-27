package com.hnzy.hot.xtgl.pojo;

import java.io.Serializable;
import java.util.Date;

import com.hnzy.hot.home.pojo.RoleMenuId;

public class RoleMenu implements Serializable{
	private Integer id;//菜单-角色表的主键ID
	private String menuId;//菜单表的主键ID
	private String roleId;//角色表的主键ID
	private Date createTime;//创建时间
	private Date lastEditTime;//最后修改时间
	private RoleMenuId rmid;

	public RoleMenu() {
		super();
	}
	public RoleMenu(Integer id, String menuId, String roleId, Date createTime, Date lastEditTime,RoleMenuId rmid) {
		super();
		this.id = id;
		this.menuId = menuId;
		this.roleId = roleId;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.rmid = rmid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public RoleMenuId getRmid()
	{
		return rmid;
	}
	public void setRmid(RoleMenuId rmid)
	{
		this.rmid = rmid;
	}

	
}
