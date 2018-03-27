package com.hnzy.hot.chart.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.chart.dao.VillageDao;
import com.hnzy.hot.chart.pojo.Village;
import com.hnzy.hot.chart.service.VillageService;
@Service
public class VillageServiceImpl implements VillageService {

	@Autowired
	private VillageDao villageDao;
	@Override
	public List<Village> findYhValAd(Village village) {
		return villageDao.findValAdByYh(village);
	}
	@Override
	public List<Village> findXQ() {
		return villageDao.findXQ();
	}
	@Override
	public List<Village> findBOByXQ(Village village) {
		return villageDao.findBO(village);
	}
	@Override
	public List<Village> findCOByXQAndBO(Village village) {
		return villageDao.findCO(village); 
	}
	@Override
	public List<Village> findByXqName(String xqName) {
		return villageDao.findByXqName(xqName);
	}

}
