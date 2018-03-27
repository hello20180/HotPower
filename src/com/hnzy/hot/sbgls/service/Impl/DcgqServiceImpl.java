package com.hnzy.hot.sbgls.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgls.dao.DcgqDao;
import com.hnzy.hot.sbgls.pojo.Dcgq;
import com.hnzy.hot.sbgls.service.DcgqService;

@Service
public class DcgqServiceImpl implements DcgqService
{

	@Autowired
	private DcgqDao  dcgqDao;
	@Override
	public List<Dcgq> find()
	{
		return dcgqDao.find();
	}
	public void  delete(int id)
	{
		dcgqDao.delete(id);
	}
}
