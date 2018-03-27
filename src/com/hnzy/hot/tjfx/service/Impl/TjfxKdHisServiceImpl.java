package com.hnzy.hot.tjfx.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.tjfx.dao.TjfxKdHisDao;
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.service.TjfxKdHisService;
@Service
public class TjfxKdHisServiceImpl implements TjfxKdHisService
{

	@Autowired
	private TjfxKdHisDao  tjfxKdHisDao;
	@Override
	public void InKd(TjfxKdHis tjfxKdHis)
	{
		tjfxKdHisDao.InKd(tjfxKdHis);
	}
	@Override
	public List<TjfxKdHis> findKd()
	{
		return tjfxKdHisDao.findKd();
	}
	@Override
	public List<TjfxKdHis> searchInfo(String xqName, String recordTime1, String recordTime2)
	{
		return tjfxKdHisDao.searchInfo(xqName, recordTime1, recordTime2);
	}
	@Override
	public List<TjfxKdHis> findKdJl()
	{
		return tjfxKdHisDao.findKdJl();
	}

}
