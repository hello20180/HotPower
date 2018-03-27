package com.hnzy.hot.tjfx.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.custompojo.LineDataModel;
import com.hnzy.hot.nhfx.pojo.Nhfxtxx;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.tjfx.pojo.TjfxHistory;
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.pojo.TqwdAvg;
import com.hnzy.hot.tjfx.pojo.XqAvg;
import com.hnzy.hot.tjfx.service.XqAvgService;
import com.hnzy.hot.util.DateUtil;
import com.hnzy.hot.xxgl.pojo.XqInfo;
import com.hnzy.hot.xxgl.service.XqInfoService;

@Controller
@RequestMapping("XqAvgCon")
public class XqAvgController
{
	@Autowired
	private XqAvgService xqAvgService;
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	private List<XqInfo> xqInfos;
	private List<XqAvg> XqAvgList;
	@Autowired
	private XqInfoService xqInfoService;
	@RequestMapping("findXqAvg")
	private String findXqAvg(ModelMap map,HttpServletRequest request){
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		map.addAttribute("yhInfoList",yhInfoList);
	   request.setAttribute("avgXq",xqAvgService.findAvg());
	   return "tjfx/xqAvg";
	}
	
	 @RequestMapping("searchInfo")
	   @ResponseBody
	public JSONObject Serrch(String xqName,String recordTime1,String recordTime2) throws UnsupportedEncodingException{
		  xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		   JSONObject jsonObject=new JSONObject();
		   jsonObject.put("xqAvg",  xqAvgService.searchInfo(xqName, recordTime1, recordTime2));
		   return jsonObject;
	}
	 
	//搜索出现图
			@RequestMapping("/goHistoryLine")
			public String goHistoryChartPage(ModelMap map) {
				yhInfoList=yhInfoService.findYhNameList();
				map.addAttribute("yhInfoList",yhInfoList);
				return "tjfx/CharXqAvg";
			}
//			@RequestMapping("XqNameAvg")
//			@ResponseBody
//			public String HeatAreaByxqName(@Param("xqName")String xqName ) throws ParseException, JsonGenerationException, JsonMappingException, IOException{
//				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
//				//根据小区查找供热面积
//				XqAvgList=xqAvgService.findHisAvg(xqName);
//				 ObjectMapper mapper = new ObjectMapper(); 
//				List<TqwdAvg> tqavg=new ArrayList<TqwdAvg>();
//				for(int i=0;i<XqAvgList.size();i++){
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			        String dates = df.format(XqAvgList.get(i).getDate());
//					tqavg.add(new TqwdAvg(XqAvgList.get(i).getTqyb(),XqAvgList.get(i).getAvg(),dates));
//				}
//				 String json = mapper.writeValueAsString(tqavg);
//				return  json;
//			}
			
			
			//小区平均温度曲线
			@RequestMapping("XqNameAvg")
			@ResponseBody
			public String xqAvgChar(String xqName,String recordTime1,String recordTime2) throws JsonGenerationException, JsonMappingException, IOException{
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				//根据小区平均温度
				XqAvgList=xqAvgService.findHisAvg(xqName,recordTime1, recordTime2);
				 ObjectMapper mapper = new ObjectMapper(); 
				List<TqwdAvg> tqavg=new ArrayList<TqwdAvg>();
				for(int i=0;i<XqAvgList.size();i++){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String dates = df.format(XqAvgList.get(i).getDate());
					tqavg.add(new TqwdAvg(XqAvgList.get(i).getTqyb(),XqAvgList.get(i).getAvg(),dates));
				}
				 String json = mapper.writeValueAsString(tqavg);
				return  json;
			}
			
}
