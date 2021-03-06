package com.hnzy.hot.kfgl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.kfgl.pojo.Repair;
import com.hnzy.hot.sjbb.pojo.YhInfo;

public interface RepairService
{
	//查找处
	public List<Repair> findplace();
	//根据处查找换热站
		public List<Repair> findHESName(String place);
//		public Repair findHes(String xqName,String buildNo,String cellNo,String cs,String sh);
	/**
	 * @author Administrator 查找报修信息
	 */
	public List<Repair> findRepair(int kffl);
	
//	public List<Repair> findPlace(String xqName);
	/**
	 * @author Administrator 添加保修单信息
	 */
	public void InsertRepair(Repair repair);
	
	/**
	 * @author Administrator 删除
	 */
	public void deleteRepair(String id);

	/**
	 * @author Administrator 根据id查找信息
	 */
	public Repair findRepairById(int id);

	/**
	 * @author Administrator 更新信息
	 */
	public void updateRepair(Repair  repair);
	
	/**
	 * @author Administrator 根据状态查找信息
	 */
	public List<Repair> findByState(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public List<Repair> findState(@Param("kffl")int kffl);
	public int sum(Repair repair,@Param("kffl")int kffl);
	public int state0(@Param("kffl")int kffl);
	public int state1(@Param("kffl")int kffl);
	public int state2(@Param("kffl")int kffl);
	
	public List<Map<String,Object>> chartSearch(@Param("xqName")String xqName,@Param("kffl")int kffl);
  
	public int stateCx(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int stateywc(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int stateyjd(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int statewjd(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
}
