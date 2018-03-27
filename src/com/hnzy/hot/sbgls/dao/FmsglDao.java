package com.hnzy.hot.sbgls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.pojo.Cb;

public interface FmsglDao extends BaseDao<Yh> {
	//查找所有的小区
	public List<Yh> findYhNameList();
	//根据小区名称查找楼栋号
	public List<Yh> findYhBuildNObyXqName(@Param("xqName")String xqName);
	//根据小区名称和楼栋号查找单元号
	public List<Yh> findYhCellNOByBuild(@Param("xqName")String xqName,@Param("build")int build);
	//获取所有的信息
    public List<Yh> findList();
    //搜索
    public List<Yh> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo, @Param("sfjf") String sfjf);
   //根据Qg查找阀门个数
   //    public  int fmCount(Qg qgId);
   // 搜索分类
    public List<Yh> yhflSear(@Param("yhfl")String yhfl);
    //更新用户开关
    public void updateType(@Param("type")String type,@Param("fmId")String fmId);
    //根据室外天气更新金盾园室内温度
    public void updateRooTemp(@Param("tqyb")int tqyb);
}