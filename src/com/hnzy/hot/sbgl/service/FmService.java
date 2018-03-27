package com.hnzy.hot.sbgl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.sbgl.pojo.Fm;
import com.hnzy.hot.sjbb.pojo.YhInfo;

public interface FmService {
	/**
	 * @author zy
	 * 
	 */
	public List<Fm> find();
	
	public List<Fm> findZ(@Param("xqName")String xqName,@Param("buildNO")Integer buildNO,@Param("cellNO")Integer cellNO);
	  //查找集中器IP和端口号
    public Fm findJzq(String fmId);
    public Fm findIDbyqgvd(String id);
    public Fm findbyVad(String fmId);
}
