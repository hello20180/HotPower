package com.hnzy.hot.sbgl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Jzq implements Serializable {

	private static final long serialVersionUID = -4072646355182930123L;
	private int id;
	private String jzqId;
	private String xqName;
	private int status;
	private String jzqIp;
	private int jzqPort;
	private String hrzName;
	private String installAd;
	private Date dateTime;
    private Qg qg;
    
	public Qg getQg() {
		return qg;
	}

	public void setQg(Qg qg) {
		this.qg = qg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJzqId() {
		return jzqId;
	}

	public void setJzqId(String jzqId) {
		this.jzqId = jzqId;
	}

	public String getXqName() {
		return xqName;
	}

	public void setXqName(String xqName) {
		this.xqName = xqName;
	}



	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getJzqIp() {
		return jzqIp;
	}

	public void setJzqIp(String jzqIp) {
		this.jzqIp = jzqIp;
	}

	public int getJzqPort() {
		return jzqPort;
	}

	public void setJzqPort(int jzqPort) {
		this.jzqPort = jzqPort;
	}

	public String getHrzName() {
		return hrzName;
	}

	public void setHrzName(String hrzName) {
		this.hrzName = hrzName;
	}

	public String getInstallAd() {
		return installAd;
	}

	public void setInstallAd(String installAd) {
		this.installAd = installAd;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
