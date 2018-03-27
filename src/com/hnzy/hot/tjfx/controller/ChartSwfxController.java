package com.hnzy.hot.tjfx.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.FmHistoryService;
import com.hnzy.hot.sjbb.service.YhInfoService;
@Controller
@RequestMapping("SwfxController")
public class ChartSwfxController {
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private FmHistoryService fmHistoryService;
	private List<YhInfo> yhInfoList;
	
	   //根据id查找具体信息
    	@RequestMapping("findSwfx")
		@ResponseBody
		public JSONObject findSwfx(@Param("name")String name,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status) throws UnsupportedEncodingException{
    		name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
    		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
    		status=new String(status.getBytes("ISO-8859-1"),"utf-8")+"";	
    		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
			JSONObject jsonObject=new JSONObject();
    		//判断是否包含-
    		if(name.contains("-")&& name.length()==6){
    			
    			//判断在xy-dy之间
    			String xy=name.substring(0,2);
    			String dy=name.substring(3,5);
    			//判断在22-23
    			if(xy.equals("22")&&dy.equals("26")){
    				yhInfoList=yhInfoService.chartNameE(xqName, buildNo, cellNo, sfjf, status);
    				jsonObject.put("yhInfoList", yhInfoList);
    			}
    			
    			//判断在19-21
    			if(xy.equals("18")&&dy.equals("22")){
    				yhInfoList=yhInfoService.chartNameD(xqName, buildNo, cellNo, sfjf, status);
    				jsonObject.put("yhInfoList", yhInfoList);
    			}
    			//判断在16-18
    			if(xy.equals("15")&&dy.equals("18")){
    				yhInfoList=yhInfoService.chartNameC(xqName, buildNo, cellNo, sfjf, status);
    				jsonObject.put("yhInfoList", yhInfoList);
    			}
    			
    		}else if(name.contains("-")&& name.length()==5){
    			//判断在xy-dy之间
    			String xy=name.substring(0,1);
    			String dy=name.substring(2,4);
				//判断在0-15
    			if(xy.equals("0")&&dy.equals("15")){
    				yhInfoList=yhInfoService.chartNameB(xqName, buildNo, cellNo, sfjf, status);
    				jsonObject.put("yhInfoList", yhInfoList);
    			}
    			
    		}else{
    			//判断是否小于0
    			if(name.substring(0,1).equals("0")){
    				yhInfoList=yhInfoService.chartNameG(xqName, buildNo, cellNo, sfjf, status);
    				jsonObject.put("yhInfoList", yhInfoList);
    			}else{
    				//大于26
    				yhInfoList=yhInfoService.chartNameA(xqName, buildNo, cellNo, sfjf, status);
    				jsonObject.put("yhInfoList", yhInfoList);
    			}
    		}
			return jsonObject;
		}
	@RequestMapping("toeditApply")
	@ResponseBody
	public Map<String, Object>  toeditApply(HttpServletResponse response,HttpServletRequest request, int apiContentStr[],@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		status=new String(status.getBytes("ISO-8859-1"),"utf-8")+"";	
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		Map<String,Object> map=new HashMap<String,Object>(); 
		   JSONArray members = new JSONArray();
	for(int i=0;i<apiContentStr.length;i++){
		if(apiContentStr[i]==1){
			JSONArray member=new JSONArray();
			//温度大于26
			int chartA=yhInfoService.chartSearchA(xqName, buildNo, cellNo, sfjf, status);
	     	member.add("大于26度");
		    member.add(chartA);
		    members.add(member);
		    map.put("chartA", chartA);
		}
		if(apiContentStr[i]==2){
			//15-18度
			int chartC=yhInfoService.chartSearchC(xqName, buildNo, cellNo, sfjf, status);
			JSONArray member=new JSONArray();
			member.add("15-18度");
		    member.add(chartC);
		    members.add(member);
		    map.put("chartC", chartC);
		}
		if(apiContentStr[i]==3){
			
			int chartD=yhInfoService.chartSearchD(xqName, buildNo, cellNo, sfjf, status);
			JSONArray member=new JSONArray();
			member.add("18-22度");
		    member.add(chartD);
		    members.add(member);
		    map.put("chartD", chartD);
		}
          if(apiContentStr[i]==4){
			
			int chartE=yhInfoService.chartSearchE(xqName, buildNo, cellNo, sfjf, status);
			JSONArray member=new JSONArray();
			member.add("22-26度");
		    member.add(chartE);
		    members.add(member);
		    map.put("chartE", chartE);
		}
			if (apiContentStr[i] == 6) {

				int chartG = yhInfoService.chartSearchG(xqName, buildNo, cellNo, sfjf, status);
				JSONArray member = new JSONArray();
				member.add("0度");
				member.add(chartG);
				members.add(member);
				map.put("chartG", chartG);
			}
			if (apiContentStr[i] == 7) {

				int chartB = yhInfoService.chartSearchB(xqName, buildNo, cellNo, sfjf, status);
				JSONArray member = new JSONArray();
				member.add("0-15度");
				member.add(chartB);
				members.add(member);
				map.put("chartB", chartB);
			}
		}
		map.put("data", members);
		return map;

	}
	 //获取所有的小区
		@RequestMapping("findYhNameList")
		public String findYhNameList(@RequestParam(value="pageNum",required=false)String pageNum,
				ModelMap map,HttpServletRequest request,YhInfo yhInfo){
			 yhInfoList=yhInfoService.findYhNameList();
			request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
			request.setAttribute("yhInfoJFStatus", yhInfoService.yhInfoJFStatus());
			 request.setAttribute("chartgrfx", yhInfo);
		    map.addAttribute("yhInfoList",yhInfoList);
			return "tjfx/ChartSwfx";		
		}
		
		//根据小区获取楼栋号
		@RequestMapping("findYhBuildNObyXqName")
		@ResponseBody
		public JSONObject findYhBuildNObyXqName(String xqName) throws UnsupportedEncodingException{
			 xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList=yhInfoService.findYhBuildNObyXqName(xqName);
			JSONObject jsonObject=new JSONObject() ;
			if(yhInfoList!=null){
				jsonObject.put("xqlist", yhInfoList);
			}else{
				jsonObject.put("fail", null);
			}
			return jsonObject;
		}
		//根据小区楼栋号获取单元号
		@RequestMapping("findYhCellNOByBuild")
		@ResponseBody
		public JSONObject findYhCellNOByBuild(@Param("build")int build,@Param("xqName")String xqName) throws UnsupportedEncodingException{
			xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList=yhInfoService.findYhCellNOByBuild(build, xqName);
			JSONObject jsonObject=new JSONObject();
			if(yhInfoList!=null){
				jsonObject.put("cellList",yhInfoList);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
			
		}
		
		public List<YhInfo> getYhInfoList() {
			return yhInfoList;
		}
		public void setYhInfoList(List<YhInfo> yhInfoList) {
			this.yhInfoList = yhInfoList;
		}
  
}
