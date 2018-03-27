package com.hnzy.hot.chart.pojo;

import java.io.Serializable;

public class Cbtq implements Serializable
{
  private int id;
  private String fmId;
  private int tqyb;
  private String date;
  
public String getDate()
{
	return date;
}
public void setDate(String date)
{
	this.date = date;
}
public int getId()
{
	return id;
}
public void setId(int id)
{
	this.id = id;
}
public String getFmId()
{
	return fmId;
}
public void setFmId(String fmId)
{
	this.fmId = fmId;
}
public int getTqyb()
{
	return tqyb;
}
public void setTqyb(int tqyb)
{
	this.tqyb = tqyb;
}

}
