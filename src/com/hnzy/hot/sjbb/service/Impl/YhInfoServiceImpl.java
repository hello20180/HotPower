package com.hnzy.hot.sjbb.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.datasource.DataSourceContextHolder;
import com.hnzy.hot.datasource.DataSourceType;
import com.hnzy.hot.sjbb.dao.YhInfoDao;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.util.ExcelUtil;

@Service
public class YhInfoServiceImpl implements YhInfoService {
@Autowired 
private YhInfoDao yhInfoDao;

@Override
public List<YhInfo> findYhNameList() {
	return yhInfoDao.find();
}

@Override
public List<YhInfo> findYhBuildNObyXqName(String xqName) {
	return yhInfoDao.findYhBuildNObyXqName(xqName);
}

@Override
public List<YhInfo> findYhCellNOByBuild(int build,String xqName) {
	return yhInfoDao.findYhCellNOByBuild(build, xqName);
}

@Override
public List<YhInfo> findYhHouseNOByBuild(int house, int build, String xqName)
{
	return yhInfoDao.findYhHouseNOByBuild(house,build,xqName);
}

@Override
public List<YhInfo> findXqInfoFmHistory() {
	return yhInfoDao.findXqInfoFmHistory();
}

@Override
public void exportExcelHistory(List<YhInfo> yhInfosList, ServletOutputStream outputStream) throws IOException
{
	ExcelUtil.exportExcel(yhInfosList, outputStream);
}
//导出
@Override
public void exportExcel(List<YhInfo> yhInfosList, ServletOutputStream outputStream) throws IOException {
	ExcelUtil.exportExcel(yhInfosList, outputStream);
}


@Override
public List<YhInfo> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("status")String status,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1") String roomTemp1,@Param("roomTemp2") String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2){
	return yhInfoDao.searchInfo(xqName, buildNo, cellNo, houseNo, status,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2);
}

@Override
public List<YhInfo> yhInfoJFStatus() {
	return yhInfoDao.yhInfoJFStatus();
}


@Override
public Double chartgrfxStatusK() {
	return yhInfoDao.chartgrfxStatusK();
}

@Override
public Double chartgrfxStatusG() {
	return yhInfoDao.chartgrfxStatusG();
}

@Override
public Double ChartgrfxZ() {
	return yhInfoDao.ChartgrfxZ();
}

@Override
public int chartSearchA(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchA(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public int chartSearchB(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchB(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public int chartSearchC(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchC(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public int chartSearchD(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchD(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public int chartSearchE(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchE(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public int chartSearchF(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchF(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public int chartSearchG(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartSearchG(xqName, buildNo, cellNo, sfjf, status);
}


@Override
public int findFamKdA(String xqName,int buildNo,int cellNo,String sfjf,String status)
{
	return yhInfoDao.findFamKdA(xqName, buildNo, cellNo, sfjf, status);
}
@Override
public int findFamKdC(String xqName,int buildNo,int cellNo,String sfjf,String status)
{
	return yhInfoDao.findFamKdC(xqName, buildNo, cellNo, sfjf, status);
}
@Override
public int findFamKdF(String xqName,int buildNo,int cellNo,String sfjf,String status)
{
	return yhInfoDao.findFamKdF(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public Double HeatAreaByxqName(String xqName) {
	return yhInfoDao.HeatAreaByxqName(xqName);
}
@Override
public List<Map<String,Object>>  chartSearch(String xqName, int buildNo, int cellNo,String ylfq) {
	List<Map<String,Object>>grList=new ArrayList<Map<String,Object>>();
   Map<String,Object> map=new HashMap<String,Object>();
   map.put("供热面积", yhInfoDao.chartSearchGr(xqName, buildNo, cellNo,ylfq));
   map.put("未供热面积", yhInfoDao.chartSearchNoGr(xqName, buildNo, cellNo,ylfq));
   grList.add(map);
	return grList;

 }

@Override
public  List<YhInfo> chartNameA(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameA(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public  List<YhInfo> chartNameB(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameB(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public  List<YhInfo> chartNameC(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameC(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public  List<YhInfo> chartNameD(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameD(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public  List<YhInfo> chartNameE(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameE(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public  List<YhInfo> chartNameF(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameF(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public  List<YhInfo> chartNameG(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.chartNameG(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public List<YhInfo> findFKdA(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.findFKdA(xqName, buildNo, cellNo, sfjf, status);
}


@Override
public List<YhInfo> findFKdC(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.findFKdC(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public List<YhInfo> findFKdF(String xqName, int buildNo, int cellNo, String sfjf, String status) {
	return yhInfoDao.findFKdF(xqName, buildNo, cellNo, sfjf, status);
}

@Override
public List<YhInfo> findYhBuildNObyXqNamerepair(String xqName,String place,String hESName)
{
	return yhInfoDao.findYhBuildNObyXqNamerepair(xqName,place,hESName);
} 

@Override
public List<YhInfo> findYhCellNOByBuildrepair(String LH, String xqName,String place,String hESName)
{
	return yhInfoDao.findYhCellNOByBuildrepair(LH, xqName,place,hESName);
}

@Override
public List<YhInfo> findYhHouseNOByBuildrepair(String house, String build, String xqName)
{
	return yhInfoDao.findYhHouseNOByBuildrepair(house, build, xqName);
}

@Override
public List<YhInfo> findrepair()
{
	return yhInfoDao.findrepair();
}

@Override
public List<YhInfo> findXqNamebyPlace(String place,String hESName)
{
	return yhInfoDao.findXqNamebyPlace(place,hESName);
}

@Override
public List<YhInfo> WkqEption(String roomTemp, String valTemp,String sfjf)
{
	return yhInfoDao.WkqEption(roomTemp, valTemp,sfjf);
}

@Override
public List<YhInfo> SbfEption(String sFJF, String valTemp)
{
	return yhInfoDao.SbfEption(sFJF, valTemp);
}

@Override
public List<YhInfo> WdEption(String famKd, String valTemp)
{
	return yhInfoDao.WdEption(famKd, valTemp);
}

@Override
public void UpBz(String bz,String fmId,Date date)
{
	yhInfoDao.UpBz(bz, fmId, date);
}

//@Override
//public void UpYhlb(String yhlb, String fmId)
//{
//	yhInfoDao.UpYhlb(yhlb, fmId);
//}

@Override
public List<YhInfo> findFq(String xqName)
{
	return yhInfoDao.findFq(xqName);
}

@Override
public YhInfo findFmId(String valAd)
{
	return yhInfoDao.findFmId(valAd);
}

@Override
public List<YhInfo> searchFmHistory(String xqName, int buildNo, int cellNo, int houseNo, String sfjf, String valTemp1,
		String valTemp2, String roomTemp1, String roomTemp2, Integer famKd, String recordTime1, String recordTime2)
{
	return yhInfoDao.searchFmHistory(xqName, buildNo, cellNo, houseNo, sfjf, valTemp1, valTemp2, roomTemp1, roomTemp2, famKd, recordTime1, recordTime2);
}

@Override
public void UpYhlb(String yhlb, String xqName, int buildNo, int cellNo, Integer houseNo)
{
	yhInfoDao.UpYhlb(yhlb, xqName, buildNo, cellNo, houseNo);
}

@Override
public YhInfo SeYhlb(String xqName, int buildNo, int cellNo, Integer houseNo)
{
	return yhInfoDao.SeYhlb(xqName, buildNo, cellNo, houseNo);
	
}

@Override
public void UpYhBz(String bz, String xqName, int buildNo, int cellNo, Integer houseNo)
{
	yhInfoDao.UpYhBz(bz, xqName, buildNo, cellNo, houseNo);
}

@Override
public List<YhInfo> Search(String xqName, int buildNo, int cellNo, Integer houseNo)
{
	return yhInfoDao.Search(xqName, buildNo, cellNo, houseNo);
}

@Override
public List<YhInfo> findVd()
{
	DataSourceContextHolder. setDbType(DataSourceType.dse); 
	return yhInfoDao.findVd();
}

@Override
public List<YhInfo> findHist(String valAd)
{
	return yhInfoDao.findHist(valAd);
}

@Override
public void upBjxx(Date bjTime,String bjxx,String valAd)
{
	yhInfoDao.upBjxx(bjTime,bjxx,valAd);	
}

@Override
public YhInfo ObrSel(String xqName, int buildNo, int cellNo, Integer houseNo)
{
	return yhInfoDao.ObrSel(xqName, buildNo, cellNo, houseNo);
}
}
