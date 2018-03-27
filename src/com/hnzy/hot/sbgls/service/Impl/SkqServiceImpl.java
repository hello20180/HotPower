package com.hnzy.hot.sbgls.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgls.dao.SkqDao;
import com.hnzy.hot.sbgls.pojo.Skq;
import com.hnzy.hot.sbgls.service.SkqService;

@Service
public class SkqServiceImpl implements SkqService
{
	@Autowired
	private SkqDao skqDao;
	@Override
	public List<Skq> findSkq()
	{
		return skqDao.find();
	}

}
