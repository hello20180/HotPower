package com.hnzy.hot.home.pojo;

import java.io.Serializable;

import com.hnzy.hot.xtgl.pojo.Role;


public class RoleMenuId implements Serializable
{
	private Role role;
	private Integer id;
	public RoleMenuId()
	{
	}
	
	public RoleMenuId(Role role, Integer id)
	{
		super();
		this.role = role;
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleMenuId other = (RoleMenuId) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null)
		{
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	public Role getRole()
	{
		return role;
	}
	public void setRole(Role role)
	{
		this.role = role;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
}
