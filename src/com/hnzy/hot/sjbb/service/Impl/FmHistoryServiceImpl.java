package com.hnzy.hot.sjbb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sjbb.dao.FmHistoryDao;
import com.hnzy.hot.sjbb.pojo.FmHistory;
import com.hnzy.hot.sjbb.service.FmHistoryService;

@Service
public class FmHistoryServiceImpl implements FmHistoryService{

	@Autowired
	private FmHistoryDao fmHistoryDao;
	@Override
	public List<FmHistory> findFmHistory() {
		return 	fmHistoryDao.find();
	}
	@Override
	public List<FmHistory> fmHistoriesStatus() {
		return fmHistoryDao.fmHistoriesStatus();
	}
	@Override
	public List<FmHistory> chartSearchSd(String xqName, int buildNo, int cellNo, String sfjf, String status) {
		return fmHistoryDao.chartSearchSd(xqName, buildNo, cellNo, sfjf, status);
	}
	@Override
	public FmHistory findSd(int id) {
		return fmHistoryDao.findSd(id);
	}

}
