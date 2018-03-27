package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.SkxxDao;
import com.hnzy.hot.xxgl.pojo.Skxx;
import com.hnzy.hot.xxgl.service.SkxxService;

@Service
public class SkxxServiceImpl implements SkxxService
{
	@Autowired
	private SkxxDao skxxDao;
	
	@Override
	public List<Skxx> findSk()
	{
		return skxxDao.find();
	}

	@Override
	public List<Skxx> Search(String xqName, int buildNo, int cellNo, Integer houseNo)
	{
		return skxxDao.Search(xqName, buildNo, cellNo, houseNo);
	}

//	@Override
//	public List<Skxx> Search(String kh)
//	{
//		return skxxDao.Search(kh);
//	}

}
