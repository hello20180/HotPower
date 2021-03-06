package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.RzDao;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Service
public class RzServiceImpl implements RzService {

	@Autowired
	private RzDao rzdao;
	@Override
	public List<Rz> rzList() {
		return rzdao.find();
	}

	@Override
	public void insert(Rz rz) {
		rzdao.Insert(rz);
		
	}

	@Override
	public List<Rz> Search(String cz)
	{
		return rzdao.Search(cz);
	}

}
