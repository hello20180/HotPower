package com.hnzy.hot.xxgl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.xxgl.pojo.Skxx;

public interface SkxxService
{
	//查询所有
		public List<Skxx> findSk();
//		public List<Skxx> Search(@Param("kh") String kh);
       public List<Skxx> Search(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
}
