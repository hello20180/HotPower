package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.XqInfoDao;
import com.hnzy.hot.xxgl.pojo.XqInfo;
import com.hnzy.hot.xxgl.service.XqInfoService;

@Service
public class XqInfoServiceImpl implements XqInfoService
{

	@Autowired
	private XqInfoDao xqInfoDao;

	/**
	 * @author Administrator 查找信息
	 */
	public List<XqInfo> find()
	{
		return xqInfoDao.find();
	}

	@Override
	public void Insert(XqInfo xqInfo)
	{
		xqInfoDao.Insert(xqInfo);
	}

	@Override
	public void delete(String id)
	{
		xqInfoDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(XqInfo xqInfo)
	{
		xqInfoDao.update(xqInfo);
	}

	@Override
	public List<XqInfo> findAvgWdByXqName()
	{
		return xqInfoDao.findAvgWdByXqName();
	}

}
