package com.hnzy.hot.sbgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.sbgl.pojo.Fm;
import com.hnzy.hot.sjbb.pojo.YhInfo;

public interface FmDao extends BaseDao<Fm> {
	public List<Fm> find();
	
	public List<Fm> findz(@Param("xqName")String xqName,@Param("buildNO")Integer buildNO,@Param("cellNO")Integer cellNO);
	
	public List<YhInfo> findYhNameList();
	public List<YhInfo> findYhBuildNObyXqName(@Param("xqName")String xqName);
	public List<YhInfo> findYhCellNOByBuild(@Param("build")int build,@Param("xqName")String xqName);
	  //查找集中器IP和端口号
    public Fm findJzq(String fmId);
    //根据ID查找区管，ID
    public Fm findIDbyqgvd(String id);
    //根据阀门查询用户信息
    public Fm findbyVad(String fmId);

}
