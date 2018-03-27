package com.hnzy.hot.xtgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xtgl.dao.BsjDao;
import com.hnzy.hot.xtgl.pojo.Bsj;
import com.hnzy.hot.xtgl.service.BsjService;

@Service
public class BsjServiceImpl implements BsjService
{

	@Autowired
	private BsjDao bsjDao;
	@Override
	public List<Bsj> findBsj()
	{
		return bsjDao.find();
	}

}
