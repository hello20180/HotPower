package com.hnzy.hot.hrzgl.service;

import java.util.List;

import com.hnzy.hot.hrzgl.pojo.HeatWX;

public interface HeatWXService
{
	/**
	 * @author Administrator 显示金领时代列表信息
	 */
	public List<HeatWX> findJLSD();
	/**
	 * @author Administrator 显示砥柱大厦列表信息
	 */
	public List<HeatWX> findDZDS();	/**
	 * @author Administrator 显示天发首府列表信息
	 */
	public List<HeatWX> findTFSF();
	/**
	 * @author Administrator 显示德馨苑列表信息
	 */
	public List<HeatWX> findDXY();
	/**
	 * @author Administrator 显示壹号城邦列表信息
	 */
	public List<HeatWX> findYHCB();
	/**
	 * @author Administrator 根据id查找信息
	 */
	public HeatWX findById(int id);
}
