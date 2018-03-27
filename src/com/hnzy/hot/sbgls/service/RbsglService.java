package com.hnzy.hot.sbgls.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sjbb.pojo.YhInfo;

public interface RbsglService {
	public List<Yh> findYhNameList();
	public List<Yh> findYhBuildNObyXqName(String xqName);
	public List<Yh> findYhCellNOByBuild(String xqName,int build);
	public List<Yh> findList();
    public List<Yh> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
  //发送热表信息
    public Yh SelRb(@Param("rbAd")String rbAd);
  //搜索热表历史数据
    public List<Yh> searchHis(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
  //导出
    public void exportExcel(List<Yh>yhInfosList,ServletOutputStream outputStream) throws IOException;
    //导出
    public void exportExcelHistory(List<Yh>yhInfosList,ServletOutputStream outputStream) throws IOException;
    //热表异常供水温度大于40回水温度大于30温差负数
    public List<Yh> searEp(Double gs,Double sh,Double wc);
    //热表有瞬时流量，供水温度大于40，回水温度大于30度，累计热量不累加
    public List<Yh> SelRbExp();
    //查询热表前两条
    public List<Yh> SelRbEp(@Param("rbAd")String rbAd);
    //更新热表异常数据
    public void UpExp(@Param("rbAd")String rbAd,int RbExp);
}
