package com.hnzy.hot.sjbb.pojo;

import java.io.Serializable;
import java.util.Date;
public class FmHistory implements Serializable
{
	private int id;
	private String valAd;
	private String status;
	private int famKd;
	private String lockSt;
	private String jfSt;
	private double valTemp;
	private double roomTemp;
	private int hTemSet;
	private int mTemSet;
	private int lTemSet;
	private Date recordTime;
	private YhInfo yhInfo;
	private Integer tqyb;
	
	public Integer getTqyb()
	{
		return tqyb;
	}
	public void setTqyb(Integer tqyb)
	{
		this.tqyb = tqyb;
	}
	public YhInfo getYhInfo() {
		return yhInfo;
	}
	public void setYhInfo(YhInfo yhInfo) {
		this.yhInfo = yhInfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValAd() {
		return valAd;
	}
	public void setValAd(String valAd) {
		this.valAd = valAd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFamKd() {
		return famKd;
	}
	public void setFamKd(int famKd) {
		this.famKd = famKd;
	}
	public String getLockSt() {
		return lockSt;
	}
	public void setLockSt(String lockSt) {
		this.lockSt = lockSt;
	}
	public String getJfSt() {
		return jfSt;
	}
	public void setJfSt(String jfSt) {
		this.jfSt = jfSt;
	}
	public double getValTemp()
	{
		return valTemp;
	}
	public void setValTemp(double valTemp)
	{
		this.valTemp = valTemp;
	}
	public double getRoomTemp()
	{
		return roomTemp;
	}
	public void setRoomTemp(double roomTemp)
	{
		this.roomTemp = roomTemp;
	}
	public int gethTemSet() {
		return hTemSet;
	}
	public void sethTemSet(int hTemSet) {
		this.hTemSet = hTemSet;
	}
	public int getmTemSet() {
		return mTemSet;
	}
	public void setmTemSet(int mTemSet) {
		this.mTemSet = mTemSet;
	}
	public int getlTemSet() {
		return lTemSet;
	}
	public void setlTemSet(int lTemSet) {
		this.lTemSet = lTemSet;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	@Override
	public String toString() {
		return "FmHistory [id=" + id + ", valAd=" + valAd + ", status=" + status + ", famKd=" + famKd + ", lockSt="
				+ lockSt + ", jfSt=" + jfSt + ", valTemp=" + valTemp + ", roomTemp=" + roomTemp + ", hTemSet=" + hTemSet
				+ ", mTemSet=" + mTemSet + ", lTemSet=" + lTemSet + ", recordTime=" + recordTime + "]";
	}
}
