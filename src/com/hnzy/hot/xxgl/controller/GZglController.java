package com.hnzy.hot.xxgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.FmHistoryService;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.xxgl.pojo.GZgl;
import com.hnzy.hot.xxgl.service.GZglService;
import com.hnzy.hot.xxgl.service.RzService;


@Controller
@RequestMapping("/gzglController")
public class GZglController
{
	@Autowired
	private GZglService gzglservice;
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	@Autowired
	private FmHistoryService fmHistoryService;
	@RequestMapping("gzgl")
	public String find(ModelMap map,HttpServletRequest request)
	{
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		map.addAttribute("yhInfoList",yhInfoList);
		List<GZgl> gzglList = gzglservice.find();
		request.setAttribute("gzglList", gzglList);
		return "xxgl/gzgl/gzgl";
	}
	@ResponseBody
	@RequestMapping("gzglList")
	public JSONObject findList(HttpServletRequest request)
	{
		
		List<GZgl> gzglList = gzglservice.findgz();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("gzglList", gzglList);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("Search")
	public JSONObject Search(HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		List<GZgl> gzglList = gzglservice.Search( xqName, buildNo, cellNo, houseNo);
		jsonObject.put("gzglList", gzglList);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("SearchFmId")
	public JSONObject SearchFmId(String ValAd){
		JSONObject jsonObject=new JSONObject();
		List<GZgl> gzglList = gzglservice.SearchFmId(ValAd);
		jsonObject.put("gzglList", gzglList);
		return jsonObject;
	}
	
/*	//获取所有的小区
		@RequestMapping("findYhNameList")
		public String findYhNameList(@RequestParam(value="pageNum",required=false)String pageNum,
				ModelMap map,HttpServletRequest request,YhInfo yhInfo){
			yhInfoList=yhInfoService.findYhNameList();
			request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
			request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
			map.addAttribute("yhInfoList",yhInfoList);
			
			return "sjbb/YhInfo";		
		}*/
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
		public List<YhInfo> getYhInfoList()
		{
			return yhInfoList;
		}
		public void setYhInfoList(List<YhInfo> yhInfoList)
		{
			this.yhInfoList = yhInfoList;
		}
	
}
