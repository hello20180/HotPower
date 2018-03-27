package com.hnzy.hot.chart.service;

import java.util.List;

import com.hnzy.hot.chart.pojo.Village;

public interface VillageService {
	
	public List<Village> findYhValAd(Village village);

    public List<Village> findXQ();
	
	public List<Village> findBOByXQ(Village village);
	
	public List<Village> findCOByXQAndBO(Village village);
	public List<Village>findByXqName(String xqName);
}
