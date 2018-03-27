package com.hnzy.hot.hrzgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.hrzgl.dao.HeatWXDao;
import com.hnzy.hot.hrzgl.pojo.HeatWX;
import com.hnzy.hot.hrzgl.service.HeatWXService;
@Service
public class HeatWXServiceImpl implements HeatWXService
{
	@Autowired
	private HeatWXDao heatWXDao;
	
	@Override
	public List<HeatWX> findJLSD()
	{
		return heatWXDao.findJLSD();
	}

	@Override
	public List<HeatWX> findDZDS()
	{
		return heatWXDao.findDZDS();
	}

	@Override
	public List<HeatWX> findTFSF()
	{
		return heatWXDao.findTFSF();
	}

	@Override
	public List<HeatWX> findDXY()
	{
		return heatWXDao.findDXY();
	}

	@Override
	public List<HeatWX> findYHCB()
	{
		return heatWXDao.findYHCB();
	}
	
	@Override
	public HeatWX findById(int id)
	{
		return heatWXDao.findById(id);
	}

}
