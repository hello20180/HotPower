package com.hnzy.hot.xtgl.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xtgl.dao.CsTimeDao;
import com.hnzy.hot.xtgl.pojo.CsTime;
import com.hnzy.hot.xtgl.service.CsTimeService;


@Service
public class CsTimeServiceImpl implements CsTimeService
{

	@Autowired
	private CsTimeDao csTimeDao;
	@Override
	public CsTime findCsTiem()
	{
		return csTimeDao.findCsTiem();
	}
	@Override
	public void updateCsTime(int cstime)
	{
		csTimeDao.updateCsTime(cstime);
	}

}
