package com.hnzy.hot.sbgls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.sbgl.pojo.Yh;

public interface RbsglDao extends BaseDao<Yh> {
	   //查找所有的小区
		public List<Yh> findYhNameList();
		//根据小区名称查找楼栋号
		public List<Yh> findYhBuildNObyXqName(@Param("xqName")String xqName);
		//根据小区名称和楼栋号查找单元号
		public List<Yh> findYhCellNOByBuild(@Param("xqName")String xqName,@Param("build")int build);
		//获取所有的信息
	    public List<Yh> findList();
	    //搜索
	    public List<Yh> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
	    //发送热表信息
	    public Yh SelRb(@Param("rbAd")String rbAd);
	    
	    //搜索
	    public List<Yh> searchHis(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
	    //热表异常供水温度大于40回水温度大于30温差负数
	    public List<Yh> searEp(@Param("gs")Double gs,@Param("hs")Double hs,@Param("wc")Double wc);
	    //热表有瞬时流量，供水温度大于40，回水温度大于30度，累计热量不累加查询实时表数据
	    public List<Yh> SelRbExp();
	    //查询热表前两条
	    public List<Yh> SelRbEp(@Param("rbAd")String rbAd);
	    //更新热表异常数据
	    public void UpExp(@Param("rbAd")String rbAd,@Param("rbExp") int rbExp);
}
