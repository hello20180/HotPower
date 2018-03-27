package com.hnzy.hot.chart.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FmHistoryLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5754651866692470777L;

	private Integer id;

	private String valAd;// ���ŵ�ַ�����ֶ����û���Ϣ��ͬ���ֶι�����

	private String status;// ����״̬ �� ��

	private Integer famKd;// ���ſ��� Int

	private String lockSt;// ������ʶ nvarchr�� ��

	private String JFSt;// �ɷѱ�ʾ nvarchr�� ��

	private BigDecimal valTemp;// �����¶� decimal(4, 2)С����ʽ

	private BigDecimal roomTemp;// �����¶� decimal(4, 2)С����ʽ

	private Integer HTemSet;// ���¶ȵ�

	private Integer MTemSet;// ���¶ȵ�(�趨�¶�)

	private Integer LTemSet;// ���¶ȵ�

	private String recordTime;// ����ʱ��
	private Integer tqyb;
	
	
	public Integer getTqyb()
	{
		return tqyb;
	}

	public void setTqyb(Integer tqyb)
	{
		this.tqyb = tqyb;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getFamKd() {
		return famKd;
	}

	public void setFamKd(Integer famKd) {
		this.famKd = famKd;
	}

	public String getLockSt() {
		return lockSt;
	}

	public void setLockSt(String lockSt) {
		this.lockSt = lockSt;
	}

	public String getJFSt() {
		return JFSt;
	}

	public void setJFSt(String jFSt) {
		JFSt = jFSt;
	}

	public BigDecimal getValTemp() {
		return valTemp;
	}

	public void setValTemp(BigDecimal valTemp) {
		this.valTemp = valTemp;
	}

	public BigDecimal getRoomTemp() {
		return roomTemp;
	}

	public void setRoomTemp(BigDecimal roomTemp) {
		this.roomTemp = roomTemp;
	}

	public Integer getHTemSet() {
		return HTemSet;
	}

	public void setHTemSet(Integer hTemSet) {
		HTemSet = hTemSet;
	}

	public Integer getMTemSet() {
		return MTemSet;
	}

	public void setMTemSet(Integer mTemSet) {
		MTemSet = mTemSet;
	}

	public Integer getLTemSet() {
		return LTemSet;
	}

	public void setLTemSet(Integer lTemSet) {
		LTemSet = lTemSet;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FmHistory [id=" + id + ", valAd=" + valAd + ", status=" + status + ", famKd=" + famKd + ", lockSt="
				+ lockSt + ", JFSt=" + JFSt + ", valTemp=" + valTemp + ", roomTemp=" + roomTemp + ", HTemSet=" + HTemSet
				+ ", MTemSet=" + MTemSet + ", LTemSet=" + LTemSet + ", recordTime=" + recordTime + "]";
	}
	
	
}
