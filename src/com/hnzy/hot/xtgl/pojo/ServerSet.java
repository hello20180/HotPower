package com.hnzy.hot.xtgl.pojo;

import java.io.Serializable;

public class ServerSet implements Serializable {

	private String sname;
	private String sip;
	private int sport;
	private String sqlname;
	private String sqlpass;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSip() {
		return sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public String getSqlname() {
		return sqlname;
	}

	public void setSqlname(String sqlname) {
		this.sqlname = sqlname;
	}

	public String getSqlpass() {
		return sqlpass;
	}

	public void setSqlpass(String sqlpass) {
		this.sqlpass = sqlpass;
	}

	@Override
	public String toString() {
		return "ServerSet [sname=" + sname + ", sip=" + sip + ", sport=" + sport + ", sqlname=" + sqlname + ", sqlpass="
				+ sqlpass + "]";
	}


	
}
