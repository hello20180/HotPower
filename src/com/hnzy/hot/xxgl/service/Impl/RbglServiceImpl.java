package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.RbglDao;
import com.hnzy.hot.xxgl.pojo.Rbgl;
import com.hnzy.hot.xxgl.service.RbglService;
@Service
public class RbglServiceImpl implements RbglService {

	@Autowired
	private RbglDao rbglDao;
	@Override
	public List<Rbgl> find() {
		return rbglDao.find();
	}

	@Override
	public void update(Rbgl rbgl) {
		rbglDao.update(rbgl);
	}

	@Override
	public void Insert(Rbgl rbgl) {
		rbglDao.Insert(rbgl);
	}

	@Override
	public void delete(String id) {
		rbglDao.delete(Integer.parseInt(id));
	}
	

}
