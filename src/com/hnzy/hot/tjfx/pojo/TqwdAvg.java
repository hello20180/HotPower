package com.hnzy.hot.tjfx.pojo;

import java.util.Date;

public class TqwdAvg
{
   private int tqyb;//室外温度
   private String avg;//平均值
   private String date;//时间
   


public TqwdAvg(int tqyb, String avg, String date)
{
	super();
	this.tqyb = tqyb;
	this.avg = avg;
	this.date = date;
}
public int getTqyb()
{
	return tqyb;
}
public void setTqyb(int tqyb)
{
	this.tqyb = tqyb;
}
public String getDate()
{
	return date;
}
public void setDate(String date)
{
	this.date = date;
}
public String getAvg()
{
	return avg;
}
public void setAvg(String avg)
{
	this.avg = avg;
}


}
