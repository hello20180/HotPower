package com.hnzy.hot.tjfx.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
@Controller
@RequestMapping("chartGrfxController")
public class ChartGrfxController {
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	
	@RequestMapping("chartSearch")
	@ResponseBody
	public Map<String, Object>chartSearch(HttpServletResponse response,HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("ylfq")String ylfq) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		ylfq=new String(ylfq.getBytes("ISO-8859-1"),"utf-8")+"";
		Map<String,Object> map=new HashMap<String,Object>(); 
		
		 JSONArray members=new JSONArray();
		 List<Object> listmap=new ArrayList<Object>();
		 
		 List<Map<String,Object>> search=yhInfoService.chartSearch(xqName, buildNo, cellNo,ylfq);
            for(Map<String, Object> m:search){
            	for(String k:m.keySet()){
            		JSONArray member=new JSONArray();
            		member.add(k);
            		member.add(m.get(k));
            		members.add(member);
            		listmap.add(m.get(k));
            	}
            }
              map.put("data", members);
              
              Object grString=listmap.get(0);
              Object ngrString=listmap.get(1);
              if(grString==null){
            	  grString=0;
              }
              if(ngrString==null){
            	  ngrString=0;
              }
              map.put("gr", grString);
              map.put("ngr",ngrString);
		  return map;
	}
	//获取所有的小区
	@RequestMapping("findList")
	public String findYhNameList(HttpServletRequest request,YhInfo yhInfo){	
		Double GArea=yhInfoService.chartgrfxStatusG();
		Double KArea=yhInfoService.chartgrfxStatusK();
		Double sumArea=yhInfoService.ChartgrfxZ();
		request.setAttribute("sumArea", sumArea);
		request.setAttribute("chartgrfxStatusg",GArea);
		request.setAttribute("chartgrfxStatusk",KArea);
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("yhInfoList",yhInfoList );
		return "tjfx/chartgrfx";		
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
	
	//根据小区楼栋号获取户号
		@RequestMapping("findYhHouseNOByBuild")
		@ResponseBody
		public JSONObject findYhCellNOByBuild(@Param("house")int house,@Param("build")int build,@Param("xqName")String xqName) throws UnsupportedEncodingException{
			xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList=yhInfoService.findYhHouseNOByBuild(house,build, xqName);
			JSONObject jsonObject=new JSONObject();
			if(yhInfoList!=null){
				jsonObject.put("houList",yhInfoList);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
			
		}
		@RequestMapping("houFq")
		@ResponseBody	
		public JSONObject houFq(@Param("xqName")String xqName) throws UnsupportedEncodingException{
			xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList=yhInfoService.findFq(xqName);
			JSONObject jsonObject=new JSONObject();
			if(yhInfoList!=null){
				jsonObject.put("houFq",yhInfoList);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
 }
}
