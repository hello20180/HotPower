package com.hnzy.hot.tjfx.pojo;

import java.util.Date;

public class XqAvg
{
  private int id;
  private String xqName;
  private String avg;
  private Date date;
  private int tqyb;
  private String tqybDate;

public String getTqybDate()
{
	return tqybDate;
}
public void setTqybDate(String tqybDate)
{
	this.tqybDate = tqybDate;
}
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
public String getXqName()
{
	return xqName;
}
public void setXqName(String xqName)
{
	this.xqName = xqName;
}
public String getAvg()
{
	return avg;
}
public void setAvg(String avg)
{
	this.avg = avg;
}
public Date getDate()
{
	return date;
}
public void setDate(Date date)
{
	this.date = date;
}
  
}
