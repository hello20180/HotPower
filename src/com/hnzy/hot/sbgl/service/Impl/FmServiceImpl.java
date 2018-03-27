package com.hnzy.hot.sbgl.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgl.dao.FmDao;
import com.hnzy.hot.sbgl.pojo.Fm;
import com.hnzy.hot.sbgl.service.FmService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
@Service
public class FmServiceImpl implements FmService {
	@Autowired
	private FmDao fmDao;

	@Override
	public List<Fm> find() {
		
		return fmDao.find();
	}

	@Override
	public List<Fm> findZ(@Param("xqName")String xqName,@Param("buildNO")Integer buildNO,@Param("cellNO")Integer cellNO) {
		return fmDao.findz(xqName, buildNO, cellNO);
	}

	@Override
	public Fm findJzq(String fmId) {
		return fmDao.findJzq(fmId);
	}

	@Override
	public Fm findIDbyqgvd(String id) {
		return fmDao.findIDbyqgvd(id);
	}

	@Override
	public Fm findbyVad(String fmId) {
		return fmDao.findbyVad(fmId);
	}
}
