package com.hnzy.hot.tjfx.pojo;

import java.util.Date;

public class TjfxKdHis
{
  private int id;
  private String xqName;//小区名称
  private String kdLd;//开度零度
  private String kdSW;//开度3-15
  private String kdYb;//开度100
  private Date date;
  private int tqyb;//室外温度
  
public int getTqyb()
{
	return tqyb;
}
public void setTqyb(int tqyb)
{
	this.tqyb = tqyb;
}
public int getId()
{
	return id;
}
public void setId(int id)
{
	this.id = id;
}
public String getKdLd()
{
	return kdLd;
}
public void setKdLd(String kdLd)
{
	this.kdLd = kdLd;
}
public String getKdSW()
{
	return kdSW;
}
public void setKdSW(String kdSW)
{
	this.kdSW = kdSW;
}
public String getKdYb()
{
	return kdYb;
}
public void setKdYb(String kdYb)
{
	this.kdYb = kdYb;
}
public Date getDate()
{
	return date;
}
public void setDate(Date date)
{
	this.date = date;
}
public String getXqName()
{
	return xqName;
}
public void setXqName(String xqName)
{
	this.xqName = xqName;
}
  
  
}
