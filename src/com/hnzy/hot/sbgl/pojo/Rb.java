package com.hnzy.hot.sbgl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Rb implements Serializable {
	
	
	private int id;
	//热表类型
	private String rbType;
	//热表地址
	private String rbAd;
	//累计热量
	private Double energy;
	//热量单位
	private String energyUnit;
	//瞬时热量
	private String power;
	//瞬时流量
	private String flow;
	//进水温度
	private Double inTmp;
	//回水温度
	private Double outTmp;
	//温差
	private Double diffTmp;
	//工作时间
	private Integer operTime;
	//错误代码
	private String errCode;
	//热表时间
	private Date readMTime;
	//更新时间
	private Date recordTime;
	//累计流量
	private Double energyLj;
	//热表异常
	private int rbExp;
	private Yh yh;
	//热表异常
	private int rbGHExp;
	//热量差 ,字段现在无用
	private Double rlc;
	
	public Double getRlc()
	{
		return rlc;
	}

	public void setRlc(Double rlc)
	{
		this.rlc = rlc;
	}

	public int getRbGHExp()
	{
		return rbGHExp;
	}

	public void setRbGHExp(int rbGHExp)
	{
		this.rbGHExp = rbGHExp;
	}

	public int getRbExp()
	{
		return rbExp;
	}

	public void setRbExp(int rbExp)
	{
		this.rbExp = rbExp;
	}

	public Double getEnergyLj()
	{
		return energyLj;
	}

	public void setEnergyLj(Double energyLj)
	{
		this.energyLj = energyLj;
	}
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

	public String getRbAd() {
		return rbAd;
	}

	public void setRbAd(String rbAd) {
		this.rbAd = rbAd;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public String getEnergyUnit() {
		return energyUnit;
	}

	public void setEnergyUnit(String energyUnit) {
		this.energyUnit = energyUnit;
	}

	public String getPower()
	{
		return power;
	}

	public void setPower(String power)
	{
		this.power = power;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public Double getInTmp() {
		return inTmp;
	}

	public void setInTmp(Double inTmp) {
		this.inTmp = inTmp;
	}

	public Double getOutTmp() {
		return outTmp;
	}

	public void setOutTmp(Double outTmp) {
		this.outTmp = outTmp;
	}

	public Double getDiffTmp() {
		return diffTmp;
	}

	public void setDiffTmp(Double diffTmp) {
		this.diffTmp = diffTmp;
	}

	public Integer getOperTime() {
		return operTime;
	}

	public void setOperTime(Integer operTime) {
		this.operTime = operTime;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public Date getReadMTime() {
		return readMTime;
	}

	public void setReadMTime(Date readMTime) {
		this.readMTime = readMTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Yh getYh() {
		return yh;
	}

	public void setYh(Yh yh) {
		this.yh = yh;
	}
}
