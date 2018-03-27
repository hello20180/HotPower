package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.HeatESInfoDao;
import com.hnzy.hot.xxgl.pojo.HeatESInfo;
import com.hnzy.hot.xxgl.service.HeatESInfoService;

@Service
public class HeatESInfoServiceImpl implements HeatESInfoService
{
	@Autowired
	private HeatESInfoDao heatESInfoDao;

	@Override
	public List<HeatESInfo> find()
	{
		return heatESInfoDao.find();
	}

	@Override
	public void Insert(HeatESInfo heatESInfo)
	{
		heatESInfoDao.Insert(heatESInfo);
	}

	@Override
	public void delete(String id)
	{
		heatESInfoDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(HeatESInfo heatESInfo)
	{
		heatESInfoDao.update(heatESInfo);
	}

	@Override
	public List<HeatESInfo> findHes(String place)
	{
		return heatESInfoDao.findHes(place);
	}

	@Override
	public List<HeatESInfo> findPlace()
	{
		return heatESInfoDao.findPlace();
	}

	@Override
	public List<HeatESInfo> findByName()
	{
		return heatESInfoDao.findByName();
	}

}
