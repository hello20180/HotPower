package com.hnzy.hot.tjfx.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.tjfx.dao.TjfxHistoryDao;
import com.hnzy.hot.tjfx.pojo.TjfxHistory;
import com.hnzy.hot.tjfx.service.TjfxHistoryService;

@Service
public class TjfxHistoryServiceImpl implements TjfxHistoryService
{
	@Autowired
	private TjfxHistoryDao tjfxHistoryDao;
	@Override
	public void InTjfx(TjfxHistory tjfxHistory)
	{
		tjfxHistoryDao.InTjfx(tjfxHistory);		
	}
	@Override
	public List<TjfxHistory> findTjfx()
	{
		return tjfxHistoryDao.findTjfx();
	}
	@Override
	public List<TjfxHistory> searchInfo(String xqName, String recordTime1, String recordTime2)
	{
		return tjfxHistoryDao.searchInfo(xqName, recordTime1, recordTime2);
	}
	@Override
	public List<TjfxHistory> findSwJl()
	{
		return tjfxHistoryDao.findSwJl();
	}

}
