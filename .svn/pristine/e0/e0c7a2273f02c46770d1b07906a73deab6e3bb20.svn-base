package com.hnzy.hot.hrzgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.hrzgl.dao.HeatStationDao;
import com.hnzy.hot.hrzgl.pojo.HeatStation;
import com.hnzy.hot.hrzgl.service.HeatStationService;

@Service
public class HeatStationServiceImpl implements HeatStationService {
@Autowired
private HeatStationDao heatStationDao;

public HeatStation findById(int id){
  return 	heatStationDao.findById(id);
 }

@Override
public List<HeatStation> findList(int id) {
	return heatStationDao.findList(id);
}
}
