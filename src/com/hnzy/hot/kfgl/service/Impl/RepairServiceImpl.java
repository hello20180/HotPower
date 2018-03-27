package com.hnzy.hot.kfgl.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.kfgl.dao.RepairDao;
import com.hnzy.hot.kfgl.pojo.Repair;
import com.hnzy.hot.kfgl.service.RepairService;
@Service
public class RepairServiceImpl implements RepairService
{
	@Autowired
	private RepairDao repairDao;
	
	@Override
	public List<Repair> findRepair(int kffl)
	{		
		return repairDao.findRepair(kffl);
	}
	@Override
	public void InsertRepair(Repair repair)
	{
		repairDao.Insert(repair);
	}

	@Override
	public void deleteRepair(String id)
	{
		repairDao.delete(Integer.parseInt(id));
	}

	@Override
	public Repair findRepairById(int id)
	{
		return repairDao.findById(id);
	}

	@Override
	public void updateRepair(Repair repair)
	{
		repairDao.update(repair);
	}

	@Override
	public List<Repair> findByState(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl)
	{
		return repairDao.findByState(place,hesName,state,kffl);
	}

	@Override
	public int sum(Repair repair,int kffl)
	{
		
		return repairDao.sum(repair,kffl);
	}

	@Override
	public int state0(int kffl)
	{
		return repairDao.state0( kffl);
	}

	@Override
	public int state1(int kffl)
	{
		return repairDao.state1( kffl);
	}

	@Override
	public int state2(int kffl)
	{
		return repairDao.state2(kffl);
	}

	@Override
	public List<Map<String,Object>>  chartSearch(String xqName,int kffl){
	List<Map<String,Object>> wxList=new ArrayList<Map<String,Object>>();
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("未接单", repairDao.statePlace0(xqName,kffl));
	   map.put("已接单", repairDao.statePlace1(xqName,kffl));
	   map.put("已完成", repairDao.statePlace2(xqName,kffl));
	   wxList.add(map);
		return wxList;

	 }

	@Override
	public List<Repair> findState(int kffl)
	{
		return repairDao.findState(kffl);
	}

	@Override
	public int stateCx(String place, String hesName, String state,int kffl)
	{
		return repairDao.stateCx(place, hesName, state,kffl);
	}

	@Override
	public int stateywc(String place, String hesName, String state,int kffl)
	{
		return repairDao.stateywc(place, hesName, state,kffl);
	}

	@Override
	public int stateyjd(String place, String hesName, String state,int kffl)
	{
		return repairDao.stateyjd(place, hesName, state,kffl);
	}

	@Override
	public int statewjd(String place, String hesName, String state,int kffl)
	{
		return repairDao.statewjd(place, hesName, state,kffl);
	}

	@Override
	public List<Repair> findplace()
	{
		return repairDao.find();
	}

	@Override
	public List<Repair> findHESName(String place)
	{
		return repairDao.findHESName(place);
	}

//	@Override
//	public Repair findHes(String xqName, String buildNo, String cellNo, String cs, String sh)
//	{
//		return repairDao.findHes(xqName, buildNo, cellNo, cs, sh);
//	}

}
