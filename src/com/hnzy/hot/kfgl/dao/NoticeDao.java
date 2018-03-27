package com.hnzy.hot.kfgl.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.hot.kfgl.pojo.Notice;

public interface NoticeDao extends BaseDao<Notice>
{
	public List<Notice> findNoticega();
}
