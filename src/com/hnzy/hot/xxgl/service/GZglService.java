package com.hnzy.hot.xxgl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.xxgl.pojo.GZgl;

public interface GZglService {
	public List<GZgl> find();
	public List<GZgl> findgz();
	public List<GZgl>Search(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
	public List<GZgl>SearchFmId(@Param("ValAd")String ValAd);
}
