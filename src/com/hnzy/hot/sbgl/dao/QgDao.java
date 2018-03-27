package com.hnzy.hot.sbgl.dao;



import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.sbgl.pojo.Qg;

public interface QgDao extends BaseDao<Qg> {

  public Qg findQgID(String id);
 //根据jzqID查找区管
 public List<Qg> findJzqIDByQg(String JzqID);
 //根据jzqID查找Qg总数
 public int QgCount(String JzqID);
//查找刷卡器信息
	 public List<Qg> findSkq();
//根据设备号查询集中器IP和端口号
	 public Qg findByIPSocket(String sbh);
}
