package com.hnzy.hot.kfgl.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.kfgl.pojo.Repair;
import com.hnzy.hot.sjbb.pojo.FmHistory;

public interface RepairDao extends BaseDao<Repair>
{
	/**
	 * @author Administrator 根据状态查找信息
	 */
	public List<Repair> findplace();
	public List<Repair> findRepair(@Param("kffl")int kffl );
	//根据小区，楼栋，单元，户号查找换热站
//	public Repair findHes(@Param("xqName") String xqName,@Param("buildNo") String buildNo,@Param("cellNo") String cellNo, @Param("cs") String cs ,@Param("sh") String sh);
	//根据处查找换热站
	public List<Repair> findHESName(String place);
//	public List<Repair> findPlace(String xqName);
	public List<Repair> findByState(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public List<Repair> findState(@Param("kffl")int kffl);
	public int sum(Repair repair,@Param("kffl")int kffl);
	public int state0(@Param("kffl")int kffl);
	public int state1(@Param("kffl")int kffl);
	public int state2(@Param("kffl")int kffl);
	public int statePlace0(@Param("xqName")String xqName,@Param("kffl")int kffl);
	public int statePlace1(@Param("xqName")String xqName,@Param("kffl")int kffl);
	public int statePlace2(@Param("xqName")String xqName,@Param("kffl")int kffl);
	
	public int stateCx(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int stateywc(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int stateyjd(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int statewjd(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
}
