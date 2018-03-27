package com.hnzy.hot.tjfx.pojo;

import java.util.Date;

public class TjfxHistory
{
	private String id;
	private String xqName;
	private String  zerot;//温度为零
	private String zFift;//温度在0-15
	private String fiftOct;//温度在15-18
	private String octTw;//温度在18-22
	private String twSix;//温度在22-26
	private String twentySix;//温度大于26
	private Date date;
	private int tqyb; //室外温度
	
	
	public int getTqyb()
	{
		return tqyb;
	}
	public void setTqyb(int tqyb)
	{
		this.tqyb = tqyb;
	}
	public String getXqName()
	{
		return xqName;
	}
	public void setXqName(String xqName)
	{
		this.xqName = xqName;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getZerot()
	{
		return zerot;
	}
	public void setZerot(String zerot)
	{
		this.zerot = zerot;
	}
	public String getzFift()
	{
		return zFift;
	}
	public void setzFift(String zFift)
	{
		this.zFift = zFift;
	}
	public String getFiftOct()
	{
		return fiftOct;
	}
	public void setFiftOct(String fiftOct)
	{
		this.fiftOct = fiftOct;
	}
	public String getOctTw()
	{
		return octTw;
	}
	public void setOctTw(String octTw)
	{
		this.octTw = octTw;
	}
	public String getTwSix()
	{
		return twSix;
	}
	public void setTwSix(String twSix)
	{
		this.twSix = twSix;
	}
	public String getTwentySix()
	{
		return twentySix;
	}
	public void setTwentySix(String twentySix)
	{
		this.twentySix = twentySix;
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
