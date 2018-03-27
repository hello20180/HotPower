package com.hnzy.hot.sbgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgl.dao.QgDao;
import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.service.QgService;

@Service
public class QgServiceImpl implements QgService {

	@Autowired
	private QgDao qgDao;

	@Override
	public List<Qg> find() {
		return qgDao.find();
	}

	@Override
	public void Insert(Qg qg)
	{
		qgDao.Insert(qg);
	}

	@Override
	public void delete(String id)
	{
		qgDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(Qg qg)
	{
		qgDao.update(qg);
	}

	@Override
	public Qg findQgID(String id) {
		return qgDao.findQgID(id);
	}

	@Override
	public List<Qg> findJzqIDByQg(String JzqID) {
		return qgDao.findJzqIDByQg(JzqID);
	}

	@Override
	public int QgCount(String JzqID) {
		return qgDao.QgCount(JzqID);
	}

	@Override
	public List<Qg> findSkq()
	{
		return qgDao.findSkq();
	}

	@Override
	public Qg findByIPSocket(String sbh)
	{
		return qgDao.findByIPSocket(sbh);
	}
	
}
