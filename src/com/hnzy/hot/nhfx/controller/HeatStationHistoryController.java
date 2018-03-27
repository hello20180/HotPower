package com.hnzy.hot.nhfx.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.nhfx.pojo.HeatStationHistory;
import com.hnzy.hot.nhfx.pojo.Znh;
import com.hnzy.hot.nhfx.service.HeatStationHistoryService;
import com.hnzy.hot.nhfx.service.NhfxtxxService;
import com.hnzy.hot.xxgl.pojo.HeatESInfo;
import com.hnzy.hot.xxgl.service.HeatESInfoService;

@Controller
@RequestMapping("nhfx")
public class HeatStationHistoryController
{
	@Autowired
	private HeatESInfoService heatESInfoService;
	@Autowired
	private HeatStationHistoryService heatSService;
	private List<HeatESInfo> heatESInfos;
	@Autowired
	private NhfxtxxService nhfxtxxService;
	// 获取所有的小区
	@RequestMapping("findHeatNameList")
	public String findYhNameList(ModelMap map, HttpServletRequest request, HeatESInfo heatESInfo)
	{
		heatESInfos = heatESInfoService.findByName();
		map.addAttribute("heatESInfos", heatESInfos);
   
		return "nhfx/zhfx";
	}
//
//	@RequestMapping("findHeat")
//	@ResponseBody
//	public Map<Object,Object> findHeat(HeatStationHistory heatStationHistory,HttpServletResponse response, HttpServletRequest request, @Param("hesName")String hesName,@Param("readTime1")String readTime1,@Param("readTime2")String readTime2) throws UnsupportedEncodingException, ParseException
//	{
//		hesName = new String(hesName.getBytes("ISO-8859-1"), "utf-8") + "";
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		List<HeatStationHistory> heats=heatSService.findHeat(hesName, readTime1,readTime2);	
//		JSONArray members = new JSONArray();
//		for (int i = 0; i < heats.size(); i++)
//		{
//			JSONArray member = new JSONArray();
//			Date date=heats.get(i).getReadTime();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dateString = formatter.format(date);
//			member.add(dateString);
//			member.add(heats.get(i).getYlHeat());
//			members.add(member);
//		}
//		map.put("data", members);
//		return map;
//	}

	@RequestMapping("findHeat")
	@ResponseBody
	public Map<Object,Object> findHeat(HeatStationHistory heatStationHistory,HttpServletResponse response, HttpServletRequest request, @Param("rhLx")String rhLx,@Param("readTime1")String readTime1,@Param("readTime2")String readTime2) throws UnsupportedEncodingException, ParseException
	{
		rhLx = new String(rhLx.getBytes("ISO-8859-1"), "utf-8") + "";
		if(rhLx.equals("当天能耗")){
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<Znh> heats=heatSService.find(rhLx, readTime1, readTime2);
		JSONArray members = new JSONArray();
		for (int i = 0; i < heats.size(); i++)
		{
			JSONArray member = new JSONArray();
			String date=heats.get(i).getReadTime();
			Double rh=heats.get(i).getRh();
			double  rhz=(rh/5800000)*120;
			BigDecimal bg = new BigDecimal(rhz);
            double Rhz = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
			member.add(date);
			member.add(Rhz);
			members.add(member);
		}
		map.put("data", members);
		return map;
		}else{
			
			Map<Object, Object> map = new HashMap<Object, Object>();
			List<Znh> heats=heatSService.find(rhLx, readTime1, readTime2);
			JSONArray members = new JSONArray();
			for (int i = 0; i < heats.size(); i++)
			{
				JSONArray member = new JSONArray();
				//当前日期
				String date=heats.get(i).getReadTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date Sdata=formatter.parse(date);
				Double rh=heats.get(i).getRh();
				
				//上次时间
				SimpleDateFormat SfimpleDataForma=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
				Date Kdata=SfimpleDataForma.parse("2017-11-15 0:00:00");
				
				//所用天数
				long day=(Sdata.getTime()-Kdata.getTime())/(24*60*60*1000)+2;
				double  rhz=(rh/5800000)/day*120;
				
				BigDecimal bg = new BigDecimal(rhz);
	            double Rhz = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				member.add(date);
				member.add(Rhz);
				members.add(member);
			}
			map.put("data", members);
			return map;
			
		}
	}
	@RequestMapping("InsertRh")
	@ResponseBody
	public JSONObject InsertRh(String InsertRhId,Double Rh, String readTime) throws UnsupportedEncodingException{
		InsertRhId=new String(InsertRhId.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		if(InsertRhId!=null&& Rh!=null &&readTime!=""){
			heatSService.InsertRh(InsertRhId, Rh, readTime);
			jsonObject.put("data", "成功");
		}else{
			jsonObject.put("data","失败");
		}
		return jsonObject;
	}
	public List<HeatESInfo> getHeatESInfos()
	{
		return heatESInfos;
	}

	public void setHeatESInfos(List<HeatESInfo> heatESInfos)
	{
		this.heatESInfos = heatESInfos;
	}
}
