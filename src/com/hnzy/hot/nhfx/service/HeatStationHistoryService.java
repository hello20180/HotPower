package com.hnzy.hot.nhfx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.nhfx.pojo.HeatStationHistory;
import com.hnzy.hot.nhfx.pojo.Znh;

public interface HeatStationHistoryService
{
	public List<HeatStationHistory> findHeat(@Param("hesName") String hesName,@Param("readTime1")String readTime1,@Param("readTime2")String readTime2);
//	 public List<HeatStationHistory>sumYlHeat(@Param("xqName")String xqName,@Param("readTime1") String readTime1,@Param("readTime2") String readTime2);
	//插入总能耗
	public void InsertRh(@Param("rhLx")String rhLx,@Param("Rh")Double rh,@Param("readTime") String readTime);
	//查询
	public List<Znh> find(@Param("rhLx")String rhLx,@Param("readTime1") String readTime1,@Param("readTime2") String readTime2);
	
}
