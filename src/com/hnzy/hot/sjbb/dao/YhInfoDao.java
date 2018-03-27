package com.hnzy.hot.sjbb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.xxgl.pojo.GZgl;
public interface YhInfoDao extends BaseDao<YhInfo>{
	public List<YhInfo> findYhBuildNObyXqName(String xqName);
	public List<YhInfo> findYhCellNOByBuild(int build,String xqName);
	public List<YhInfo> findYhHouseNOByBuild(int house,int build,String xqName);
	
	//查找客服管理小区楼栋单元
	//根据处查找小区
	public List<YhInfo> findXqNamebyPlace(@Param("place") String place,@Param("hESName") String hESName);
	public List<YhInfo> findYhBuildNObyXqNamerepair(@Param("xqName") String xqName,@Param("place") String place,@Param("hESName") String hESName);
	public List<YhInfo> findYhCellNOByBuildrepair(@Param("LH") String LH,@Param("xqName")  String xqName,@Param("place") String place,@Param("hESName") String hESName);
	public List<YhInfo> findYhHouseNOByBuildrepair(@Param("house") String house,@Param("LH") String LH,@Param("xqName")  String xqName);
	public List<YhInfo> findrepair();
	
	//一对一的查询
    public List<YhInfo> findXqInfoFmHistory();
    //设备管理搜索
    public List<YhInfo> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
    
    
  //设备管理搜索
    public List<YhInfo> searchFmHistory(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
    
    //业主管理
    public List<YhInfo> yzglFindAll();
    //缴费状态查询
    public List<YhInfo> yhInfoJFStatus();
    //根据用户分类搜索
    public List<YhInfo> yhflSear(@Param("yhfl")String yhfl);
    //阀门开度所占面积 
    public Double chartgrfxStatusK();
    //阀门关度所占面积 
    public Double chartgrfxStatusG();
    //总面积
    public Double ChartgrfxZ();
    //根据搜索条件进行查询温度大于26度
	public int chartSearchA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
    //根据搜索条件进行查询温度温度小于16度
	public int chartSearchB(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//根据搜索条件进行查询温度温度18-20度
	public int chartSearchC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//根据搜索条件进行查询温度温度20-22度
	public int chartSearchD(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//根据搜索条件进行查询温度温度22-24度
	public int chartSearchE(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//根据搜索条件进行查询温度温度24-26度
	public int chartSearchF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//根据搜索条件进行查询温度温度为0
	public int chartSearchG(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);


	public int findFamKdA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdB(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdD(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdE(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
   
	//根据小区名字获取高低去
	public List<YhInfo> findFq(@Param("xqName")String xqName);
	//小区供热面积
	public Double HeatAreaByxqName(@Param("xqName")String xqName);
   //小区供热
	public Double chartSearchGr(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("ylfq")String ylfq);
   //小区不供热
	public Double chartSearchNoGr(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("ylfq")String ylfq);
	
	
	    //根据搜索条件进行查询温度大于26度的信息
		public List<YhInfo> chartNameA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	    //根据搜索条件进行查询温度温度小于16度的信息
		public List<YhInfo> chartNameB(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		//根据搜索条件进行查询温度温度18-20度的信息
		public List<YhInfo> chartNameC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		//根据搜索条件进行查询温度温度20-22度的信息
		public List<YhInfo> chartNameD(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		//根据搜索条件进行查询温度温度22-24度的信息
		public List<YhInfo> chartNameE(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		//根据搜索条件进行查询温度温度24-26度的信息
		public List<YhInfo> chartNameF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		//根据搜索条件进行查询温度温度为0的信息
		public List<YhInfo> chartNameG(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		
		//根据阀门开度查找信息
		public List<YhInfo> findFKdA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

		public List<YhInfo> findFKdB(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

		public List<YhInfo> findFKdC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

		public List<YhInfo> findFKdD(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

		public List<YhInfo> findFKdE(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

		public List<YhInfo> findFKdF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	
        //搜索室内温度和管道温度异常用户
	    public List<YhInfo> WkqEption(@Param("roomTemp")String roomTemp,@Param("valTemp")String valTemp,@Param("sfjf")String sfjf);
	    //搜索没有交费和管道温度异常用户
	    public List<YhInfo> SbfEption(@Param("sFJF")String sFJF,@Param("valTemp")String valTemp);
	    //开度和管道异常
	    public List<YhInfo> WdEption(@Param("famKd")String famKd,@Param("valTemp")String valTemp);
		//更新备注
		public  void UpBz(@Param("bz")String bz,@Param("valAd")String fmId,@Param("date")Date  date);   
		//用户类别
		public  void UpYhlb(@Param("yhfl")String yhlb,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
		//用户备注
		public  void UpYhBz(@Param("bz")String bz,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
		//根据阀门Id查找用户信息
		public YhInfo findFmId(@Param("valAd")String valAd);
		//用户类别
		public  YhInfo SeYhlb(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);

		public List<YhInfo>Search(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
		public List<YhInfo> findVd();
		public List<YhInfo> findHist(@Param("valAd")String valAd);
		public void upBjxx(@Param("bjTime")Date bjTime,@Param("bjxx")String bjxx,@Param("valAd")String valAd);
		public YhInfo ObrSel(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
	}
