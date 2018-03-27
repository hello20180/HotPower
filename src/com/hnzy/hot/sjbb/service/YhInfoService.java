package com.hnzy.hot.sjbb.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.sjbb.pojo.YhInfo;
public interface YhInfoService {
	
	public List<YhInfo> findYhNameList();
	public List<YhInfo> findYhBuildNObyXqName(String xqName);
	public List<YhInfo> findYhCellNOByBuild(int build,String xqName);
	public List<YhInfo> findYhHouseNOByBuild(int house,int build,String xqName);
	//查找客服管理小区楼栋单元
	//根据处查找小区
	public List<YhInfo> findXqNamebyPlace(String place,String hESName);
		public List<YhInfo> findYhBuildNObyXqNamerepair(String xqName,String place,String hESName);
		public List<YhInfo> findYhCellNOByBuildrepair(String LH,String xqName,String place,String hESName);
		public List<YhInfo> findYhHouseNOByBuildrepair(String house,String LH,String xqName);
		public List<YhInfo> findrepair();
		public List<YhInfo> findFq(String xqName);
	//一对一的查询
    public List<YhInfo> findXqInfoFmHistory();
    //导出
    public void exportExcel(List<YhInfo>yhInfosList,ServletOutputStream outputStream) throws IOException;
    //导出
    public void exportExcelHistory(List<YhInfo>yhInfosList,ServletOutputStream outputStream) throws IOException;
   //搜索
    public List<YhInfo> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
    //缴费状态
    public List<YhInfo> yhInfoJFStatus();
   
    //阀门开度
    public Double  chartgrfxStatusK();
    //阀门关度
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
  	//根据搜索条件进行查询温度温度温度为0
  	public int chartSearchG(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

  	
	public int findFamKdA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

	public int findFamKdF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//小区供热面积
	public Double HeatAreaByxqName(@Param("xqName")String xqName);
	//小区供热面积搜索
	public List<Map<String,Object>> chartSearch(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("ylfq")String ylfq);

	
	
	  		//根据搜索条件进行查询温度大于26度的小区名字
			public  List<YhInfo> chartNameA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
		    //根据搜索条件进行查询温度温度小于16度的小区名字
			public  List<YhInfo> chartNameB(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
			//根据搜索条件进行查询温度温度18-20度的小区名字
			public  List<YhInfo> chartNameC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
			//根据搜索条件进行查询温度温度20-22度的小区名字
			public  List<YhInfo> chartNameD(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
			//根据搜索条件进行查询温度温度22-24度的小区名字
			public  List<YhInfo> chartNameE(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
			//根据搜索条件进行查询温度温度24-26度的小区名字
			public  List<YhInfo> chartNameF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
			//根据搜索条件进行查询温度温度为0的小区名字
			public  List<YhInfo> chartNameG(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);



			//根据阀门开度查找信息
			public List<YhInfo> findFKdA(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

			public List<YhInfo> findFKdC(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);

			public List<YhInfo> findFKdF(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);


			//搜索室内温度和管道温度异常用户
		    public List<YhInfo> WkqEption(@Param("valTemp")String roomTemp,@Param("valTemp")String valTemp,@Param("sfjf")String sfjf);
		    //搜索没有交费和管道温度异常用户
		    public List<YhInfo> SbfEption(@Param("sFJF")String sFJF,@Param("valTemp")String valTemp);
		    //开度和管道异常
		    public List<YhInfo> WdEption(@Param("famKd")String famKd,@Param("valTemp")String valTemp);
		    
			//更新备注
			public  void UpBz(String bz,String fmId,Date date);
			//根据阀门Id查找用户信息
			public YhInfo findFmId(@Param("valAd")String valAd);
			//用户类别
			public  void UpYhlb(@Param("yhfl")String yhfl,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
			//设备管理搜索
		    public List<YhInfo> searchFmHistory(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
			//用户类别
			public  YhInfo SeYhlb(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo); 
			//用户备注
			public  void UpYhBz(@Param("bz")String bz,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
			public List<YhInfo>Search(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
			public List<YhInfo> findVd();
			public List<YhInfo> findHist(String valAd);
			public void upBjxx(@Param("bjTime")Date date,@Param("bjxx")String string,@Param("valAd")String valAd);
			public YhInfo ObrSel(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
}
