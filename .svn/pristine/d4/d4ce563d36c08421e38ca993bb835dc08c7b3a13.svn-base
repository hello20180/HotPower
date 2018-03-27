package com.hnzy.hot.sjbb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.sjbb.pojo.FmHistory;

public interface FmHistoryDao extends BaseDao<FmHistory> {
	//阀门状态查询
 public List<FmHistory>fmHistoriesStatus();
//散点图查询
	public List<FmHistory> chartSearchSd(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
//根据id查询具体信息
	public FmHistory findSd(int id);
}
