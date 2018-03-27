package com.hnzy.hot.sjbb.pojo;

import java.io.Serializable;
import java.util.Date;

public class YhInfo implements Serializable {
private int id;
private String valAd;
private String xqName;
private Integer buildNo;
private Integer cellNo;
private Integer houseNo;
private String yhName;
private String idNum;
private String rbAd;
private String rbType;
private String telephone;
private String mobilephone;
private String email;
private Double buileArea;
private Double userArea;
private Double heatArea;
private String billway;
private String sfjf;
private String sfqf;
private String sftr;
private String wcad;//传感器地址
private String azwz;
private String ylfq;//用户分区
private Double  kArea;
private Double  gArea;
private Double sumArea;
private String yhbm ;
private String  jfsj;
private String bz;//备注
private Date time;
private String yhfl;//用户分类，普通用户，重点监控，
private Integer  cou;
private String  bjxx; //是否报警
private Date bjTime;//报警时间
private String LH;
private String CS;
private String SH;
private int jFZT;//是否缴费
private int yhRb;//普通用户与热表用户 0普通用户，1 热表用户
private int cjq;//是否安装采集器0未安装1安装

public int getCjq()
{
	return cjq;
}

public void setCjq(int cjq)
{
	this.cjq = cjq;
}
private String yHKH;
//小区
private String xq;
//单元号
private String dYH;
//换热站
private String hESName;
public int getjFZT()
{
	return jFZT;
}

public int getYhRb()
{
	return yhRb;
}

public void setYhRb(int yhRb)
{
	this.yhRb = yhRb;
}

public void setjFZT(int jFZT)
{
	this.jFZT = jFZT;
}
public String getyHKH()
{
	return yHKH;
}
public void setyHKH(String yHKH)
{
	this.yHKH = yHKH;
}


public String getLH()
{
	return LH;
}
public void setLH(String lH)
{
	LH = lH;
}
public String getCS()
{
	return CS;
}
public void setCS(String cS)
{
	CS = cS;
}
public String getSH()
{
	return SH;
}
public void setSH(String sH)
{
	SH = sH;
}
public String getBjxx()
{
	return bjxx;
}
public void setBjxx(String bjxx)
{
	this.bjxx = bjxx;
}
public Date getBjTime()
{
	return bjTime;
}
public void setBjTime(Date bjTime)
{
	this.bjTime = bjTime;
}
public Integer getCou()
{
	return cou;
}
public void setCou(Integer cou)
{
	this.cou = cou;
}
public String getYhfl()
{
	return yhfl;
}
public void setYhfl(String yhfl)
{
	this.yhfl = yhfl;
}


public Date getTime()
{
	return time;
}
public void setTime(Date time)
{
	this.time = time;
}
public String gethESName()
{
	return hESName;
}
public void sethESName(String hESName)
{
	this.hESName = hESName;
}
public String getBz()
{
	return bz;
}
public void setBz(String bz)
{
	this.bz = bz;
}

public String getXq()
{
	return xq;
}
public void setXq(String xq)
{
	this.xq = xq;
}
public String getdYH()
{
	return dYH;
}
public void setdYH(String dYH)
{
	this.dYH = dYH;
}
private FmHistory fmHistory;


public String getYhbm()
{
	return yhbm;
}
public void setYhbm(String yhbm)
{
	this.yhbm = yhbm;
}

public String getJfsj()
{
	return jfsj;
}
public void setJfsj(String jfsj)
{
	this.jfsj = jfsj;
}
public Double getSumArea() {
	return sumArea;
}
public void setSumArea(Double sumArea) {
	this.sumArea = sumArea;
}
public Double getkArea() {
	return kArea;
}
public void setkArea(Double kArea) {
	this.kArea = kArea;
}
public Double getgArea() {
	return gArea;
}
public void setgArea(Double gArea) {
	this.gArea = gArea;
}
private String faultInfor;

public String getYhName() {
	return yhName;
}
public void setYhName(String yhName) {
	this.yhName = yhName;
}
public String getIdNum() {
	return idNum;
}
public void setIdNum(String idNum) {
	this.idNum = idNum;
}
public String getRbAd() {
	return rbAd;
}
public void setRbAd(String rbAd) {
	this.rbAd = rbAd;
}
public String getRbType() {
	return rbType;
}
public void setRbType(String rbType) {
	this.rbType = rbType;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getMobilephone() {
	return mobilephone;
}
public void setMobilephone(String mobilephone) {
	this.mobilephone = mobilephone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public Double getBuileArea() {
	return buileArea;
}
public void setBuileArea(Double buileArea) {
	this.buileArea = buileArea;
}
public Double getUserArea() {
	return userArea;
}
public void setUserArea(Double userArea) {
	this.userArea = userArea;
}
public Double getHeatArea() {
	return heatArea;
}
public void setHeatArea(Double heatArea) {
	this.heatArea = heatArea;
}
public String getBillway() {
	return billway;
}
public void setBillway(String billway) {
	this.billway = billway;
}
public String getSfjf() {
	return sfjf;
}
public void setSfjf(String sfjf) {
	this.sfjf = sfjf;
}

public String getSfqf() {
	return sfqf;
}
public void setSfqf(String sfqf) {
	this.sfqf = sfqf;
}
public String getSftr() {
	return sftr;
}
public void setSftr(String sftr) {
	this.sftr = sftr;
}
public String getWcad() {
	return wcad;
}
public void setWcad(String wcad) {
	this.wcad = wcad;
}
public String getAzwz() {
	return azwz;
}
public void setAzwz(String azwz) {
	this.azwz = azwz;
}
public String getYlfq() {
	return ylfq;
}
public void setYlfq(String ylfq) {
	this.ylfq = ylfq;
}
public String getFaultInfor() {
	return faultInfor;
}
public void setFaultInfor(String faultInfor) {
	this.faultInfor = faultInfor;
}

public FmHistory getFmHistory() {
	return fmHistory;
}
public void setFmHistory(FmHistory fmHistory) {
	this.fmHistory = fmHistory;
}
public String getValAd() {
	return valAd;
}
public void setValAd(String valAd) {
	this.valAd = valAd;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Integer getBuildNo() {
	return buildNo;
}
public void setBuildNo(Integer buildNo) {
	this.buildNo = buildNo;
}
public Integer getCellNo() {
	return cellNo;
}
public void setCellNo(Integer cellNo) {
	this.cellNo = cellNo;
}
public Integer getHouseNo() {
	return houseNo;
}
public void setHouseNo(Integer houseNo) {
	this.houseNo = houseNo;
}
public String getXqName() {
	return xqName;
}
public void setXqName(String xqName) {
	this.xqName = xqName;
}

public void setBuildNo(int buildNo) {
	this.buildNo = buildNo;
}

public void setHouseNo(int houseNo) {
	this.houseNo = houseNo;
}
}
