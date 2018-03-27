package com.hnzy.hot.sbgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgl.dao.RbDao;
import com.hnzy.hot.sbgl.pojo.Rb;
import com.hnzy.hot.sbgl.service.RbService;
@Service
public class RbServiceImpl implements RbService {
	
	@Autowired
	private RbDao rbDao;

	@Override
	public List<Rb> find() {
		
		return rbDao.find();
	}

}
