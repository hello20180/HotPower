package com.hnzy.hot.sbgls.pojo;

import java.io.Serializable;
import java.util.Date;

public class Skq implements Serializable
{
   private int id;
   private String sph;
   private String kh;
   private String state;
   private Date dateTime;
   
public String getKh()
{
	return kh;
}
public void setKh(String kh)
{
	this.kh = kh;
}
public int getId()
{
	return id;
}
public void setId(int id)
{
	this.id = id;
}
public String getSph()
{
	return sph;
}
public void setSph(String sph)
{
	this.sph = sph;
}
public String getState()
{
	return state;
}
public void setState(String state)
{
	this.state = state;
}
public Date getDateTime()
{
	return dateTime;
}
public void setDateTime(Date dateTime)
{
	this.dateTime = dateTime;
}
   
   
}
