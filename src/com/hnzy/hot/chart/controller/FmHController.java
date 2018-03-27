package com.hnzy.hot.chart.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.chart.pojo.Village;
import com.hnzy.hot.chart.pojo.FmHistoryLog;
import com.hnzy.hot.chart.service.FmHistoryLogService;
import com.hnzy.hot.chart.service.VillageService;
import com.hnzy.hot.custompojo.LineDataModel;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.util.DateUtil;

@Controller
@RequestMapping("/FmHController")
public class FmHController {

	@Autowired
	private FmHistoryLogService fmHistoryService;
	@Autowired
	private VillageService villageService;

	private List<FmHistoryLog> fmHistories;
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	
	

	public List<YhInfo> getYhInfoList()
	{
		return yhInfoList;
	}

	public void setYhInfoList(List<YhInfo> yhInfoList)
	{
		this.yhInfoList = yhInfoList;
	}

	//搜索出现图
	@RequestMapping("/goHistoryLine")
	public String goHistoryChartPage(HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNO")Integer buildNO,@Param("cellNO")Integer cellNO,@Param("houseNO") Integer houseNO) {
		try {
			xqName=	new String(xqName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		Village village=new Village();
		village.setXqName(xqName);
		village.setBuildNO(buildNO);
		village.setCellNO(cellNO);
		village.setHouseNO(houseNO);
		List<Village> info = villageService.findYhValAd(village);
		for(int i=0;i<info.size();i++)
		{
			String fmValAd = info.get(i).getValAd();
			request.setAttribute("fmValAd", fmValAd);
		}
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("yhInfoList",yhInfoList );
		return "chart/fmHChart";
	}
	
	//搜索出现图
	@RequestMapping("/goHistoryLine2")
	public String goHistoryChartPage2(HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNO")Integer buildNO,@Param("cellNO")Integer cellNO,@Param("houseNO") Integer houseNO) {
		try {
			xqName=	new String(xqName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		Village village=new Village();
		village.setXqName(xqName);
		village.setBuildNO(buildNO);
		village.setCellNO(cellNO);
		village.setHouseNO(houseNO);
		List<Village> info = villageService.findYhValAd(village);
		for(int i=0;i<info.size();i++)
		{
			String fmValAd = info.get(i).getValAd();
			request.setAttribute("fmValAd", fmValAd);
		}
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("yhInfoList",yhInfoList );
		return "chart/fmHChart2";
	}
	
	
	@RequestMapping("/historyLine")
	@ResponseBody
	public Map<String,Object> listYhFmHistory(String fmValAd) { 

		String date = DateUtil.get2WeekAgoDateStr(DateUtil.YYYYMMDDHHMMSS);
		FmHistoryLog fmHistory = new FmHistoryLog();
		fmHistory.setValAd(fmValAd);
		fmHistory.setRecordTime(date);
		fmHistories = this.fmHistoryService.findHistory(fmHistory);
		List<LineDataModel> lines = new ArrayList<LineDataModel>();
		int count = fmHistories.size();
		Object[] wd = new Object[count];
		Object[] fm = new Object[count];
		Object[] kd = new Object[count];
		Object[] tq = new Object[count];
		Object[] xy = new Object[count];
		for (int i = 0; i < count; i++) {
			FmHistoryLog history = fmHistories.get(i);
			BigDecimal roomWD = history.getRoomTemp();
			int fmkd = history.getFamKd();
			BigDecimal fmWD=history.getValTemp();
			String record = history.getRecordTime();
			record = record.substring(2, 16);
			int tqyb=history.getTqyb();
			wd[i] = roomWD;
			kd[i] = fmkd;
			tq[i] = tqyb;
			xy[i] = record;
			fm[i]= fmWD;
		}
		LineDataModel model = new LineDataModel();
		model.setName("室内温度");
		model.setData(wd);
		lines.add(model);
		LineDataModel model3 = new LineDataModel();
		model3.setName("室外温度");
		model3.setData(tq);
		lines.add(model3);
		LineDataModel model2 = new LineDataModel();
		model2.setName("阀门开度");
		model2.setData(kd);
		lines.add(model2);
		LineDataModel mode4 = new LineDataModel();
		mode4.setName("管道温度");
		mode4.setData(fm);
		lines.add(mode4);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("xy", xy);
		json.put("data", lines);
		return json;
	}

	public List<FmHistoryLog> getFmHistories() {
		return fmHistories;
	}

	public void setFmHistories(List<FmHistoryLog> fmHistories) {
		this.fmHistories = fmHistories;
	}

}
