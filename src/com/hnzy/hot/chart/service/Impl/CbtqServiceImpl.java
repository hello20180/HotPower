package com.hnzy.hot.chart.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.chart.dao.CbtqDao;
import com.hnzy.hot.chart.service.CbtqService;

@Service
public class CbtqServiceImpl implements CbtqService
{

	@Autowired
	private CbtqDao cbtqDao;

	@Override
	public void InsertTq(int wd, String date)
	{
		cbtqDao.InsertTq(wd, date);
	}
	

}
