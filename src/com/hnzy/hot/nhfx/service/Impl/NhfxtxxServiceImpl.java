package com.hnzy.hot.nhfx.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.nhfx.dao.NhfxtxxDao;
import com.hnzy.hot.nhfx.pojo.Nhfxtxx;
import com.hnzy.hot.nhfx.service.NhfxtxxService;

@Service
public class NhfxtxxServiceImpl implements NhfxtxxService
{

	@Autowired
	private NhfxtxxDao nhfxtxxDao;
	@Override
	public List<Nhfxtxx> sumYlHeat(String xqName, String readTime1, String readTime2)
	{
		return nhfxtxxDao.sumYlHeat(xqName, readTime1, readTime2);
	}
	@Override
	public void UpNhxx(String xqName, Double nhz, String readTime1)
	{
		nhfxtxxDao.UpNhxx(xqName, nhz, readTime1);
	}
	@Override
	public List<Nhfxtxx> findList(String xqName)
	{
		return nhfxtxxDao.findList(xqName);
	}
	@Override
	public List<Nhfxtxx> findTimeListS(String xqName, String readTime)
	{
		return nhfxtxxDao.findTimeListS(xqName, readTime);
	}
	@Override
	public Nhfxtxx findNhz(String xqName, String TimeListId)
	{
		return nhfxtxxDao.findNhz(xqName, TimeListId);
	}
	@Override
	public Nhfxtxx findNhzS(String xqName, String TimeListSId)
	{
		return nhfxtxxDao.findNhzS(xqName, TimeListSId);
	}
	

}
