package com.hnzy.hot.sjbb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.sjbb.pojo.FmHistory;

public interface FmHistoryService {
  public List<FmHistory> findFmHistory();
  public List<FmHistory>fmHistoriesStatus();
   //散点图查询
	public List<FmHistory> chartSearchSd(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	//根据id查询具体信息
	public FmHistory findSd(int id);
}
