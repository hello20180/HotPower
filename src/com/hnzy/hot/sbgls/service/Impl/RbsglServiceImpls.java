package com.hnzy.hot.sbgls.service.Impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.dao.RbsglDao;
import com.hnzy.hot.sbgls.service.RbsglService;
import com.hnzy.hot.util.ExcelUtilRb;


@Service
public class RbsglServiceImpls implements RbsglService {
 
	@Autowired
	private RbsglDao rbsglDao;
	@Override
	public List<Yh> findYhNameList() {
		return rbsglDao.findYhNameList();
	}

	@Override
	public List<Yh> findYhBuildNObyXqName(String xqName) {
		return rbsglDao.findYhBuildNObyXqName(xqName);
	}

	@Override
	public List<Yh> findYhCellNOByBuild(String xqName, int build) {
		return rbsglDao.findYhCellNOByBuild(xqName, build);
	}

	@Override
	public List<Yh> findList() {
		return rbsglDao.findList();
	}

	@Override
	public Yh SelRb(String rbAd)
	{
		return rbsglDao.SelRb(rbAd);
	}
	@Override
	public void exportExcel(List<Yh> yhInfosList, ServletOutputStream outputStream) throws IOException
	{
		ExcelUtilRb.exportExcel(yhInfosList, outputStream);		
	}

	@Override
	public void exportExcelHistory(List<Yh> yhInfosList, ServletOutputStream outputStream) throws IOException
	{
		ExcelUtilRb.exportExcel(yhInfosList, outputStream);		
		
	}
	@Override
	public List<Yh> searEp(Double gs, Double hs, Double wc)
	{
		return rbsglDao.searEp(gs, hs, wc);
	}

	@Override
	public List<Yh> SelRbExp()
	{
		return rbsglDao.SelRbExp();
	}

	@Override
	public List<Yh> SelRbEp(String rbAd)
	{
		return rbsglDao.SelRbEp(rbAd);
	}

	@Override
	public void UpExp(String rbAd, int rbExp)
	{
		rbsglDao.UpExp(rbAd, rbExp);
	}

	@Override
	public List<Yh> searchInfo(String xqName, int buildNo, int cellNo, Integer houseNo, String recordTime1,
			String recordTime2)
	{
		return rbsglDao.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2);
	}

	@Override
	public List<Yh> searchHis(String xqName, int buildNo, int cellNo, Integer houseNo, String recordTime1,
			String recordTime2)
	{
		return rbsglDao.searchHis(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2);
	}

}
