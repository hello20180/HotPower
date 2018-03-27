package com.hnzy.hot.xxgl.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.xxgl.pojo.XqInfo;

public interface XqInfoDao extends BaseDao<XqInfo>
{
	//根据小区查找阀门的室内平均温度
	public List<XqInfo> findAvgWdByXqName();
	
	
}
