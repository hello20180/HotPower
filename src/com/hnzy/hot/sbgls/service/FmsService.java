package com.hnzy.hot.sbgls.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.pojo.Cb;

public interface FmsService {
	
	public List<Yh> findYhNameList();
	public List<Yh> findYhBuildNObyXqName(String xqName);
	public List<Yh> findYhCellNOByBuild(String xqName,int build);
	public List<Yh> findList();
    public List<Yh> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo, @Param("sfjf") String sfjf);
    //根据Qg查找阀门个数
    //     public  int fmCount(Qg qgId);
    //查找阀门地址实时数据
    public Cb findFmId();
    // 搜索分类
    public List<Yh> yhflSear(@Param("yhfl")String yhfl);
    //更新用户开关
    public void updateType(String type,String fmId);
    //根据室外天气更新金盾园室内温度
    public void updateRooTemp(@Param("tqyb")int tqyb);
}
