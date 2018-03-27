package com.hnzy.hot.sbgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgl.dao.JzqDao;
import com.hnzy.hot.sbgl.pojo.Jzq;
import com.hnzy.hot.sbgl.service.JzqService;

@Service
public class JzqServiceImpl implements JzqService {

	@Autowired
	private JzqDao jzqDao;
	@Override
	public List<Jzq> find() {
		
		return jzqDao.find();
	}

}
