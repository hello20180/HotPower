package com.hnzy.hot.kfgl.pojo;

import java.io.Serializable;
import java.util.Date;

import com.hnzy.hot.xxgl.pojo.HeatESInfo;

public class Repair implements Serializable {
	
	private int id;
	private String xqName;
	private String buildNo;
	private String cellNo;
//	private String houseNo;
	private String cs;
	private String sh;
	private String name;
   
	private String telephone;
	private String problem;
	private String tJname;
	private Date tJtime;
	private String jSname;

	private Date jStime;
	private String wCname;
	private Date wCtime;
	private String state;
	private HeatESInfo hea;


	//	保修登记
	private String hESName;
	private String place;
	private String type;
	

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getCs()
	{
		return cs;
	}

	public void setCs(String cs)
	{
		this.cs = cs;
	}

	public String getSh()
	{
		return sh;
	}

	public void setSh(String sh)
	{
		this.sh = sh;
	}

	public String gethESName()
	{
		return hESName;
	}

	public void sethESName(String hESName)
	{
		this.hESName = hESName;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	public HeatESInfo getHea()
	{
		return hea;
	}

	public void setHea(HeatESInfo hea)
	{
		this.hea = hea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getXqName() {
		return xqName;
	}

	public void setXqName(String xqName) {
		this.xqName = xqName;
	}


	public String getCellNo()
	{
		return cellNo;
	}

	public void setCellNo(String cellNo)
	{
		this.cellNo = cellNo;
	}



	public void setBuildNo(String buildNo)
	{
		this.buildNo = buildNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String gettJname() {
		return tJname;
	}

	public void settJname(String tJname) {
		this.tJname = tJname;
	}

	public Date gettJtime() {
		return tJtime;
	}

	public void settJtime(Date tJtime) {
		this.tJtime = tJtime;
	}

	public String getjSname() {
		return jSname;
	}

	public void setjSname(String jSname) {
		this.jSname = jSname;
	}

	public Date getjStime() {
		return jStime;
	}

	public void setjStime(Date jStime) {
		this.jStime = jStime;
	}

	public String getwCname() {
		return wCname;
	}

	public void setwCname(String wCname) {
		this.wCname = wCname;
	}

	public Date getwCtime() {
		return wCtime;
	}

	public void setwCtime(Date wCtime) {
		this.wCtime = wCtime;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getBuildNo()
	{
		return buildNo;
	}


	
	
}
