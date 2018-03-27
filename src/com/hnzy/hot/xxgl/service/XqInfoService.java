package com.hnzy.hot.xxgl.service;

import java.util.List;

import com.hnzy.hot.xxgl.pojo.XqInfo;

public interface XqInfoService
{
	/**
	 * @author Administrator 查找信息
	 */
	public List<XqInfo> find();
	/**
	 * @author Administrator 插入信息
	 */
	public void Insert(XqInfo  xqInfo);

	/**
	 * @author Administrator 删除
	 */
	public void delete(String id);

	/**
	 * @author Administrator 更新信息
	 */
	public void update(XqInfo  xqInfo);
	//根据小区查找阀门的室内平均温度
	public List<XqInfo> findAvgWdByXqName();
}

