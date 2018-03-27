package com.hnzy.hot.xtgl.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xtgl.dao.DataManageDao;
import com.hnzy.hot.xtgl.pojo.DataManage;
import com.hnzy.hot.xtgl.service.DataManageService;
@Service
public class DataManageServiceImpl implements DataManageService{
   @Autowired
	private DataManageDao dataManageDao;
   
	@Override
	public DataManage findDataManage()
	{
		return dataManageDao.findDataManage();
	}

}
