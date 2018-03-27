package com.hnzy.hot.chart.dao;

import org.apache.ibatis.annotations.Param;

public interface CbtqDao 
{
	 public void InsertTq(@Param("wd") int wd,@Param("date")  String date);
}
