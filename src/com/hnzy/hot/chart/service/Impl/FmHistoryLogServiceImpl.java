package com.hnzy.hot.chart.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.chart.dao.FmHistoryLogDao;
import com.hnzy.hot.chart.pojo.FmHistoryLog;
import com.hnzy.hot.chart.service.FmHistoryLogService;
@Service
public class FmHistoryLogServiceImpl implements FmHistoryLogService{

	@Autowired
	private FmHistoryLogDao fmHistoryDao;
	@Override
	public List<FmHistoryLog> findHistory(FmHistoryLog fmHistory) {
		return fmHistoryDao.findFmHistoryLine(fmHistory);  
	}

}
