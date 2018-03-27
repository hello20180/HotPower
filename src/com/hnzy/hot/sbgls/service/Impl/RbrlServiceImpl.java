package com.hnzy.hot.sbgls.service.Impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgls.dao.RbNameDao;
import com.hnzy.hot.sbgls.dao.RbrlDao;
import com.hnzy.hot.sbgls.pojo.RbName;
import com.hnzy.hot.sbgls.pojo.RbrlUser;
import com.hnzy.hot.sbgls.service.RbrlService;
import com.hnzy.hot.util.ExcelRlUtilRb;

@Service
public class RbrlServiceImpl implements RbrlService
{

	@Autowired
	private RbrlDao rbrlDao;
	@Autowired
	private RbNameDao rbNameDao;
	@Override
	public List<RbrlUser> findRbrlUser()
	{
		return rbrlDao.findRbrlUser();
	}
	@Override
	public List<RbName> findXqName()
	{
		return rbNameDao.findXqName();
	}
	@Override
	public List<RbrlUser> SearFind(String xqName, String recordTime1, String recordTime2)
	{
		return rbrlDao.SearFind(xqName, recordTime1, recordTime2);
	}
	@Override
	public List<RbrlUser> findUserInfo()
	{
		return rbrlDao.findUserInfo();
	}
	@Override
	public List<RbrlUser> SearFindInfo(String xqName, String recordTime1, String recordTime2)
	{
		return rbrlDao.SearFindInfo(xqName, recordTime1, recordTime2);
	}
	@Override
	public void exportExcel(List<RbrlUser> yhInfosList, ServletOutputStream outputStream) throws IOException
	{
		ExcelRlUtilRb.exportExcel(yhInfosList, outputStream);		
	}

}
