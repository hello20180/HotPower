package com.hnzy.hot.nhfx.dao;

import java.util.List;

import java.util.Date;
import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.nhfx.pojo.HeatStationHistory;

public interface HeatStationHistoryDao extends BaseDao<HeatStationHistory>
{
// public List<HeatStationHistory>sumYlHeat(@Param("xqName")String xqName,@Param("readTime1") String readTime1,@Param("readTime2") String readTime2);

	public List<HeatStationHistory> findHeat(@Param("hesName") String hesName,@Param("readTime1") String readTime1,
			@Param("readTime2")String readTime2);


}
