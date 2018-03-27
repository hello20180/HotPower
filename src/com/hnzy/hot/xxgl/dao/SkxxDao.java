package com.hnzy.hot.xxgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.xxgl.pojo.GZgl;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.pojo.Skxx;

public interface SkxxDao extends BaseDao<Skxx>
{
//	public List<Skxx> Search(@Param("kh") String kh);
	public List<Skxx>Search(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo);
}
