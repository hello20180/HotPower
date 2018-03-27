package com.hnzy.hot.xtgl.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.xtgl.dao.ServerSetDao;
import com.hnzy.hot.xtgl.pojo.ServerSet;
import com.hnzy.hot.xtgl.service.ServerSetService;


@Service
public class ServerSetServiceImpl implements ServerSetService
{
	@Autowired
	private ServerSetDao serverSetDao;

	public ServerSet findServerSet()
	{
		return serverSetDao.findServerSet();

	}

	@Override
	public void updateServerSet(ServerSet serverSet)
	{
		serverSetDao.update(serverSet);
	}

}
