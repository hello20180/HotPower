package com.hnzy.hot.xxgl.pojo;

import java.io.Serializable;
import java.text.NumberFormat;

public class XqInfo implements Serializable
{

	private int id;
	private String xqName;
	private String lxrName;
	private String lxrPhone;
	private Double nhPrice;
	private Double areaPriceOne;
	private Double areaPriceTwo;
	private String hesName;
	private String xqAd;
	private String Jd;
	private String Wd;
	private String avgWd;
	
	public String getAvgWd()
	{
		return avgWd;
	}
	public void setAvgWd(float avgWd)
	{
		NumberFormat ddf1=NumberFormat.getNumberInstance() ;
		ddf1.setMaximumFractionDigits(2); 
		String avgWds= ddf1.format(avgWd) ; 
		
		this.avgWd = avgWds;
	}
	public String getJd()
	{
		return Jd;
	}
	public void setJd(String jd)
	{
		Jd = jd;
	}
	public String getWd()
	{
		return Wd;
	}
	public void setWd(String wd)
	{
		Wd = wd;
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
	public Double getNhPrice()
	{
		return nhPrice;
	}
	public void setNhPrice(Double nhPrice)
	{
		this.nhPrice = nhPrice;
	}
	public Double getAreaPriceOne()
	{
		return areaPriceOne;
	}
	public void setAreaPriceOne(Double areaPriceOne)
	{
		this.areaPriceOne = areaPriceOne;
	}
	public Double getAreaPriceTwo()
	{
		return areaPriceTwo;
	}
	public void setAreaPriceTwo(Double areaPriceTwo)
	{
		this.areaPriceTwo = areaPriceTwo;
	}
	public String getHesName()
	{
		return hesName;
	}
	public void setHesName(String hesName)
	{
		this.hesName = hesName;
	}
	public String getXqAd()
	{
		return xqAd;
	}
	public void setXqAd(String xqAd)
	{
		this.xqAd = xqAd;
	}
	
}
