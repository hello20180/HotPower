package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.ReadInfoDao;
import com.hnzy.hot.xxgl.pojo.ReadInfo;
import com.hnzy.hot.xxgl.service.ReadInfoService;
@Service
public class ReadInfoServiceImpl implements ReadInfoService
{
@Autowired
private ReadInfoDao readInfoDao;
	@Override
	public List<ReadInfo> find()
	{
		return readInfoDao.find();
	}

	@Override
	public void Insert(ReadInfo readInfo)
	{
		readInfoDao.Insert(readInfo);
	}

	@Override
	public void delete(String id)
	{
		readInfoDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(ReadInfo readInfo)
	{
		readInfoDao.update(readInfo);
	}

}
