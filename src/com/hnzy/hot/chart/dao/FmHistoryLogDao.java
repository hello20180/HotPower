package com.hnzy.hot.chart.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.chart.pojo.FmHistoryLog;

public interface FmHistoryLogDao extends BaseDao<FmHistoryLog> {

	public List<FmHistoryLog> findFmHistoryLine(FmHistoryLog fmHistory);
}
