package com.hnzy.hot.xxgl.service;

import java.util.List;

import com.hnzy.hot.xxgl.pojo.HeatESInfo;


public interface HeatESInfoService
{

	/**
	 * @author Administrator 查找信息
	 */
	public List<HeatESInfo> find();
	/**
	 * @author Administrator 插入信息
	 */
	public void Insert(HeatESInfo heatESInfo);

	/**
	 * @author Administrator 删除
	 */
	public void delete(String id);

	/**
	 * @author Administrator 更新信息
	 */
	public void update(HeatESInfo heatESInfo);
	public List<HeatESInfo> findByName();
	public List<HeatESInfo> findHes(String place);
	public List<HeatESInfo> findPlace();

}
