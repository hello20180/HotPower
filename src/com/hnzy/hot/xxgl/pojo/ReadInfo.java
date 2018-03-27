package com.hnzy.hot.xxgl.pojo;

import java.io.Serializable;
import java.util.Date;

public class ReadInfo implements Serializable
{
	private int id;
	private String iReadId;
	private String jzqId;
	private String xqName;
	private String status;
	private String installAd;
	private Date recordTime;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getiReadId()
	{
		return iReadId;
	}
	public void setiReadId(String iReadId)
	{
		this.iReadId = iReadId;
	}
	public String getJzqId()
	{
		return jzqId;
	}
	public void setJzqId(String jzqId)
	{
		this.jzqId = jzqId;
	}
	public String getXqName()
	{
		return xqName;
	}
	public void setXqName(String xqName)
	{
		this.xqName = xqName;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getInstallAd()
	{
		return installAd;
	}
	public void setInstallAd(String installAd)
	{
		this.installAd = installAd;
	}
	public Date getRecordTime()
	{
		return recordTime;
	}
	public void setRecordTime(Date recordTime)
	{
		this.recordTime = recordTime;
	}
	
}
