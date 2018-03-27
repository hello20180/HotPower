package com.hnzy.hot.xxgl.pojo;

import java.io.Serializable;
import java.util.Date;

import com.hnzy.hot.sjbb.pojo.YhInfo;

public class GZgl implements Serializable {
private int id;
private String deviceType;
private String deviceId;
private String errinfor;
private Date  recordTime;
private YhInfo yhInfo;


public YhInfo getYhInfo()
{
	return yhInfo;
}
public void setYhInfo(YhInfo yhInfo)
{
	this.yhInfo = yhInfo;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDeviceType() {
	return deviceType;
}
public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
}
public String getDeviceId() {
	return deviceId;
}
public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
}
public String getErrinfor() {
	return errinfor;
}
public void setErrinfor(String errinfor) {
	this.errinfor = errinfor;
}
public Date getRecordTime() {
	return recordTime;
}
public void setRecordTime(Date recordTime) {
	this.recordTime = recordTime;
}
}
