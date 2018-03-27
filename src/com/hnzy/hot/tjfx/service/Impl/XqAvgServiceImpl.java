package com.hnzy.hot.tjfx.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.tjfx.dao.XqAvgDao;
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.pojo.XqAvg;
import com.hnzy.hot.tjfx.service.XqAvgService;

@Service
public class XqAvgServiceImpl implements XqAvgService
{
	@Autowired
	private XqAvgDao xqAvgDao;

	@Override
	public List<XqAvg> findAvg()
	{
		return xqAvgDao.findAvg();
	}

	@Override
	public void InTAvg(XqAvg xqAvg)
	{
		xqAvgDao.InTAvg(xqAvg);
	}

	@Override
	public List<TjfxKdHis> searchInfo(String xqName, String recordTime1, String recordTime2)
	{
		return xqAvgDao.searchInfo(xqName, recordTime1, recordTime2);
	}

	@Override
	public List<XqAvg> findPjWdJl()
	{
		return xqAvgDao.findPjWdJl();
	}

	@Override
	public List<XqAvg> findHisAvg(String xqName,String recordTime1,String recordTime2)
	{
		return xqAvgDao.findHisAvg(xqName,recordTime1,recordTime2);
	}

}
