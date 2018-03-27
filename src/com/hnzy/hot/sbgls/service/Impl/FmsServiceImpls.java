package com.hnzy.hot.sbgls.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.dao.CbDao;
import com.hnzy.hot.sbgls.dao.FmsglDao;
import com.hnzy.hot.sbgls.pojo.Cb;
import com.hnzy.hot.sbgls.service.FmsService;


@Service
public class FmsServiceImpls implements FmsService{

	@Autowired
	private FmsglDao fmsglDao;
	@Autowired
	private CbDao cbDao;
	@Override
	public List<Yh> findYhNameList() {
		return fmsglDao.findYhNameList();
	}

	@Override
	public List<Yh> findYhBuildNObyXqName(String xqName) {
		return fmsglDao.findYhBuildNObyXqName(xqName);
	}

	@Override
	public List<Yh> findYhCellNOByBuild(String xqName,int build) {
		return fmsglDao.findYhCellNOByBuild(xqName, build);
	}

	@Override
	public List<Yh> findList() {
		return fmsglDao.findList();
	}

	@Override
	public List<Yh> searchInfo( String xqName, int buildNo, int cellNo,
			Integer houseNo,String sfjf) {
		return fmsglDao.searchInfo(xqName, buildNo, cellNo, houseNo,sfjf);
	}

	@Override
	public Cb findFmId()
	{
		return cbDao.findFmId();
	}

	@Override
	public List<Yh> yhflSear(String yhfl)
	{
		return fmsglDao.yhflSear(yhfl);
	}

	@Override
	public void updateType(String type,String fmId)
	{
		fmsglDao.updateType(type,fmId);
	}

	@Override
	public void updateRooTemp(int tqyb)
	{
		fmsglDao.updateRooTemp(tqyb);
	}

//	@Override
//	public int fmCount(Qg qgId) {
//		return fmsglDao.fmCount(qgId);
//	}

}
