package com.hnzy.hot.tjfx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.pojo.XqAvg;

public interface XqAvgService
{
	public List<XqAvg> findAvg();
	  public void InTAvg(XqAvg xqAvg);
	  public List<TjfxKdHis>searchInfo( @Param("xqName")String xqName,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
	  public List<XqAvg> findPjWdJl();
	//根据小区查找平均值
	  public List<XqAvg>findHisAvg(@Param("xqName")String xqName,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
}
