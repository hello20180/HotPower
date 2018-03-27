package com.hnzy.hot.nhfx.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.nhfx.dao.HeatStationHistoryDao;
import com.hnzy.hot.nhfx.dao.ZnhDao;
import com.hnzy.hot.nhfx.pojo.HeatStationHistory;
import com.hnzy.hot.nhfx.pojo.Znh;
import com.hnzy.hot.nhfx.service.HeatStationHistoryService;
@Service
public class HeatStationHistoryServiceImpl implements HeatStationHistoryService
{
	@Autowired
    private HeatStationHistoryDao heatStationHistoryDao;
	@Autowired 
	public HeatStationHistoryDao heatSDao;
	@Autowired 
	public ZnhDao znhDao;
//	@Override
//	public List<HeatStationHistory> sumYlHeat(String xqName,String readTime1, String readTime2) {
//		return heatStationHistoryDao.sumYlHeat(xqName, readTime1, readTime2);
//	}
	@Override
	public List<HeatStationHistory> findHeat(String hesName, String readTime1, String readTime2)
	{
		return heatSDao.findHeat(hesName,readTime1, readTime2);
	}
	@Override
	public void InsertRh(String InsertRhId, Double Rh, String readTime)
	{
		znhDao.InsertRh(InsertRhId, Rh, readTime);
	}
	@Override
	public List<Znh> find(String rhLx, String readTime1, String readTime2)
	{
		return znhDao.find(rhLx, readTime1, readTime2);
	}


}
