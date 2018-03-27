package com.hnzy.hot.sbgl.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Yh implements Serializable {
	private static final long serialVersionUID = -4072646355182930123L;
	
   private int id;
	
	private String yhName; //�û�����
	
	private String numID;
	private String idNum;
	private String valAd; //���ŵ�ַ
	
	private String rbAd;   //�ȱ�ID
	
	private String rbType;
	
	private Integer buildNO;	//¥����
	
	private Integer cellNO;	//��Ԫ��
	
	private Integer houseNO; //����
	
	private String telephone;
	
	private String mobilephone;
	
	private String email;
	
	private String xqName;	//С������
	
	private Double buileArea;
	
	private Double useArea;
	
	private Double heatArea;
	
	private String billWay;
	
	private String sFJF;	//�Ƿ�ɷ�
	
	private String sFQF;	//�Ƿ�Ƿ��
	
	private String sFTR;	//�Ƿ�ͣ��
	
	private String WCAd;
	
	private String AZWZ;
    private String YLFQ;
    private String faultInfor;
    private Fm fm;
    private Rb rb;
    private String yhbm ;
	private String jfsj;
	private String bz;
	private String yhfl;
	private String  bjxx;
	private Date bjTime;
	private int yhRb;
	private int cjq;//是否安装采集器0未安装1安装
	
	public int getCjq()
	{
		return cjq;
	}

	public void setCjq(int cjq)
	{
		this.cjq = cjq;
	}

	public int getYhRb()
	{
		return yhRb;
	}

	public void setYhRb(int yhRb)
	{
		this.yhRb = yhRb;
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

	public String getYhfl()
	{
		return yhfl;
	}

	public void setYhfl(String yhfl)
	{
		this.yhfl = yhfl;
	}

	public String getIdNum()
	{
		return idNum;
	}

	public void setIdNum(String idNum)
	{
		this.idNum = idNum;
	}

	public String getBz()
	{
		return bz;
	}

	public void setBz(String bz)
	{
		this.bz = bz;
	}

	public Rb getRb() {
		return rb;
	}

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

	public void setRb(Rb rb) {
		this.rb = rb;
	}

	public Fm getFm() {
		return fm;
	}

	public void setFm(Fm fm) {
		this.fm = fm;
	}

	public String getYLFQ() {
		return YLFQ;
	}

	public void setYLFQ(String yLFQ) {
		YLFQ = yLFQ;
	}

	public String getFaultInfor() {
		return faultInfor;
	}

	public void setFaultInfor(String faultInfor) {
		this.faultInfor = faultInfor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYhName() {
		return yhName;
	}

	public void setYhName(String yhName) {
		this.yhName = yhName;
	}

	public String getNumID() {
		return numID;
	}

	public void setNumID(String numID) {
		this.numID = numID;
	}

	public String getValAd() {
		return valAd;
	}

	public void setValAd(String valAd) {
		this.valAd = valAd;
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

	public Integer getBuildNO() {
		return buildNO;
	}

	public void setBuildNO(Integer buildNO) {
		this.buildNO = buildNO;
	}

	public Integer getCellNO() {
		return cellNO;
	}

	public void setCellNO(Integer cellNO) {
		this.cellNO = cellNO;
	}

	public Integer getHouseNO() {
		return houseNO;
	}

	public void setHouseNO(Integer houseNO) {
		this.houseNO = houseNO;
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

	public String getXqName() {
		return xqName;
	}

	public void setXqName(String xqName) {
		this.xqName = xqName;
	}

	public Double getBuileArea() {
		return buileArea;
	}

	public void setBuileArea(Double buileArea) {
		this.buileArea = buileArea;
	}

	public Double getUseArea() {
		return useArea;
	}

	public void setUseArea(Double useArea) {
		this.useArea = useArea;
	}

	public Double getHeatArea() {
		return heatArea;
	}

	public void setHeatArea(Double heatArea) {
		this.heatArea = heatArea;
	}

	public String getBillWay() {
		return billWay;
	}

	public void setBillWay(String billWay) {
		this.billWay = billWay;
	}

	public String getsFJF() {
		return sFJF;
	}

	public void setsFJF(String sFJF) {
		this.sFJF = sFJF;
	}

	public String getsFQF() {
		return sFQF;
	}

	public void setsFQF(String sFQF) {
		this.sFQF = sFQF;
	}

	public String getsFTR() {
		return sFTR;
	}

	public void setsFTR(String sFTR) {
		this.sFTR = sFTR;
	}

	public String getWCAd() {
		return WCAd;
	}

	public void setWCAd(String wCAd) {
		WCAd = wCAd;
	}

	public String getAZWZ() {
		return AZWZ;
	}

	public void setAZWZ(String aZWZ) {
		AZWZ = aZWZ;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}