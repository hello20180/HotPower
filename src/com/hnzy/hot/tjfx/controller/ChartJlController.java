package com.hnzy.hot.tjfx.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.chart.pojo.FmHistoryLog;
import com.hnzy.hot.chart.pojo.Village;
import com.hnzy.hot.custompojo.LineDataModel;
import com.hnzy.hot.tjfx.pojo.TjfxHistory;
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.pojo.XqAvg;
import com.hnzy.hot.tjfx.service.TjfxHistoryService;
import com.hnzy.hot.tjfx.service.TjfxKdHisService;
import com.hnzy.hot.tjfx.service.XqAvgService;
import com.hnzy.hot.util.DateUtil;

@Controller
@RequestMapping("ChartJlCont")
public class ChartJlController
{
	@Autowired
	private TjfxHistoryService tjfxHistoryService;
	@Autowired
	private TjfxKdHisService tjfxKdHisService;
	@Autowired
	private XqAvgService xqAvgService;
	
	//搜索出现图
		@RequestMapping("/goHistoryLine")
		public String goHistoryChartPage() {
			return "tjfx/CharJl";
		}
	
	@RequestMapping("/historyLine")
	@ResponseBody
	public Map<String,Object> listYhFmHistory(String fmValAd) { 
		
		String date = DateUtil.get2WeekAgoDateStr(DateUtil.YYYYMMDDHHMMSS);
		List<TjfxHistory> findSw=tjfxHistoryService.findSwJl();
		List<TjfxKdHis>findKd=tjfxKdHisService.findKdJl();
		List<XqAvg> findavg=xqAvgService.findPjWdJl();
		List<LineDataModel> lines = new ArrayList<LineDataModel>();
		int count = tjfxHistoryService.findSwJl().size();
		Object[] wd = new Object[count];
		Object[] fm = new Object[count];
		Object[] kd = new Object[count];
		Object[] xy = new Object[count];
		
		//室温18-22占比
		for(int i=0;i<findSw.size();i++){
			String roomWD=findSw.get(i).getOctTw();
			Date record = findSw.get(i).getDate();
			SimpleDateFormat fimpleDataForma=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			String datas=fimpleDataForma.format(record);
			roomWD = roomWD.substring(0,roomWD.length()-1);
			wd[i] = roomWD;
			xy[i] = datas;
		}
		//开度零
		for(int i=0;i<findKd.size();i++){
			String fmkd=findKd.get(i).getKdLd();
			fmkd = fmkd.substring(0,fmkd.length()-1);
			kd[i] = fmkd;
		}
		//小区平均温度
		for(int i=0;i<findavg.size();i++){
			String fmAvgWd=findavg.get(i).getAvg();
		
			fm[i] = fmAvgWd;
		}
		
		LineDataModel model = new LineDataModel();
		model.setName("室内温度18-22");
		model.setData(wd);
		lines.add(model);
		LineDataModel model2 = new LineDataModel();
		model2.setName("阀门开度为0");
		model2.setData(kd);
		lines.add(model2);
		LineDataModel mode4 = new LineDataModel();
		mode4.setName("小区平均温度");
		mode4.setData(fm);
		lines.add(mode4);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("xy", xy);
		json.put("data", lines);
		return json;
	}
	
	
	
}
