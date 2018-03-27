package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.JzqInfoDao;
import com.hnzy.hot.xxgl.pojo.JzqInfo;
import com.hnzy.hot.xxgl.service.JzqInfoService;
@Service
public class JzqInfoServiceImpl implements JzqInfoService
{
	@Autowired
	private JzqInfoDao jzqInfoDao;

	@Override
	public List<JzqInfo> find()
	{
		return jzqInfoDao.find();
	}

	@Override
	public void Insert(JzqInfo jzqInfo)
	{
		jzqInfoDao.Insert(jzqInfo);
	}

	@Override
	public void delete(String id)
	{
		jzqInfoDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(JzqInfo jzqInfo)
	{
		jzqInfoDao.update(jzqInfo);
	}
	
}
