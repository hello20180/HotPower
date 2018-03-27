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
import com.hnzy.hot.xxgl.pojo.Skxx;
import com.hnzy.hot.xxgl.service.SkxxService;


@RequestMapping("/Skxx")
@Controller
public class SkxxController
{
	@Autowired
	private SkxxService skxxService;
	private List<Skxx> SkxxList;
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	@Autowired
	private FmHistoryService fmHistoryService;
	@RequestMapping("findSkq")
	public String  findSkq(HttpServletRequest request,ModelMap map){
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		map.addAttribute("yhInfoList",yhInfoList);
		
		SkxxList=skxxService.findSk();
		request.setAttribute("skxxList",SkxxList);
		return "xxgl/skgl/Skq";
	}
	@ResponseBody
	@RequestMapping("Search")
	public JSONObject Search(HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		 SkxxList = skxxService.Search(xqName, buildNo, cellNo, houseNo);
		jsonObject.put("SkxxList", SkxxList);
		return jsonObject;
	}
	/*//获取所有的小区
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
	public List<Skxx> getSkxxList()
	{
		return SkxxList;
	}

	public void setSkxxList(List<Skxx> skxxList)
	{
		SkxxList = skxxList;
	}
	
}
