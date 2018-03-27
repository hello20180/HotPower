package com.hnzy.hot.xxgl.pojo;

import java.io.Serializable;

public class HeatESInfo implements Serializable
{

	private int id;
	private String hesName;
	private String lxrName;
	private String lxrPhone;
	private String hesAd;
	private String place;
	
	public String getPlace()
	{
		return place;
	}
	public void setPlace(String place)
	{
		this.place = place;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getHesName()
	{
		return hesName;
	}
	public void setHesName(String hesName)
	{
		this.hesName = hesName;
	}
	public String getLxrName()
	{
		return lxrName;
	}
	public void setLxrName(String lxrName)
	{
		this.lxrName = lxrName;
	}
	public String getLxrPhone()
	{
		return lxrPhone;
	}
	public void setLxrPhone(String lxrPhone)
	{
		this.lxrPhone = lxrPhone;
	}
	public String getHesAd()
	{
		return hesAd;
	}
	public void setHesAd(String hesAd)
	{
		this.hesAd = hesAd;
	}

	

	
}
