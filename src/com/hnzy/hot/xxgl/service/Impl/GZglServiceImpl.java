package com.hnzy.hot.xxgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xxgl.dao.GZglDao;
import com.hnzy.hot.xxgl.pojo.GZgl;
import com.hnzy.hot.xxgl.service.GZglService;

@Service
public class GZglServiceImpl implements GZglService{
@Autowired
private GZglDao gzgldao;
	@Override
	public List<GZgl> find() {
		return gzgldao.find();
	}
	@Override
	public List<GZgl> findgz() {
		return gzgldao.findgz();
	}
	@Override
	public List<GZgl> Search(String xqName, int buildNo, int cellNo, Integer houseNo)
	{
		return gzgldao.Search( xqName, buildNo, cellNo, houseNo);
	}
	@Override
	public List<GZgl> SearchFmId(String ValAd)
	{
		return gzgldao.SearchFmId(ValAd);
	}


}
