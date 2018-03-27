package com.hnzy.hot.xxgl.pojo;

import java.io.Serializable;

public class Rbgl implements Serializable {
	private int id;
	private String rbType;
	private String typeCode;
	private String company;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRbType() {
		return rbType;
	}
	public void setRbType(String rbType) {
		this.rbType = rbType;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

}
