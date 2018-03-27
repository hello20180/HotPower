package com.hnzy.hot.xxgl.pojo;

import java.io.Serializable;
import java.util.Date;

import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sjbb.pojo.YhInfo;

public class Skxx implements Serializable
{
   private int id;
   private String skqSbh;
   private String kh;
   private String fmState;
   private String skSuccess;
   private String sfJf;
   private Date dateTime;
   private Qg qg;
   private Yh yh; 
public Yh getYh()
{
	return yh;
}
public void setYh(Yh yh)
{
	this.yh = yh;
}
public String getSfJf()
{
	return sfJf;
}
public void setSfJf(String sfJf)
{
	this.sfJf = sfJf;
}
public Qg getQg()
{
	return qg;
}
public void setQg(Qg qg)
{
	this.qg = qg;
}
public String getFmState()
{
	return fmState;
}
public void setFmState(String fmState)
{
	this.fmState = fmState;
}
public String getSkSuccess()
{
	return skSuccess;
}
public void setSkSuccess(String skSuccess)
{
	this.skSuccess = skSuccess;
}
public int getId()
{
	return id;
}
public void setId(int id)
{
	this.id = id;
}

public String getSkqSbh()
{
	return skqSbh;
}
public void setSkqSbh(String skqSbh)
{
	this.skqSbh = skqSbh;
}
public String getKh()
{
	return kh;
}
public void setKh(String kh)
{
	this.kh = kh;
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
