package com.hnzy.hot.sbgl.service;


import java.util.List;

import com.hnzy.hot.sbgl.pojo.Jzq;
import com.hnzy.hot.sbgl.pojo.Qg;

public interface QgService {
	
	public List<Qg> find();
	/**
	 * @author Administrator 插入信息
	 */
	public void Insert(Qg qg);

	/**
	 * @author Administrator 删除
	 */
	public void delete(String id);


	/**
	 * @author Administrator 更新信息
	 */
	public void update(Qg qg);
	/**
	 * @author 根据区管ID 阀门
	 */

	//根据区管ID查找
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
