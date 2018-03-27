package com.hnzy.hot.chart.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.chart.pojo.Village;

public interface VillageDao extends BaseDao<Village> {

	public List<Village> findValAdByYh(Village village);
	
    public List<Village> findXQ();
	
	public List<Village> findBO(Village village);
	
	public List<Village> findCO(Village village);
	public List<Village>findByXqName(String xqName);
}
