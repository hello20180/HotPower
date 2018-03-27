package com.hnzy.hot.xtgl.dao;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.xtgl.pojo.ServerSet;

public interface ServerSetDao extends BaseDao<ServerSet> {
	/**
	 * @author Administrator
	 *查找所有的信息
	 */
	 public ServerSet findServerSet();
}
