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
@RequestMapping("/kdtj")
public class FamKdController
{
	
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private FmHistoryService fmHistoryService;

	private List<YhInfo> yhInfoList;

	// 阀门开度
	@RequestMapping("/famkd")
	@ResponseBody
	public Map<String, Object> famkd(HttpServletResponse response, HttpServletRequest request,
			int apiContentStr[], @Param("xqName")String xqName, @Param("buildNo")int buildNo,
			@Param("cellNo") int cellNo, @Param("sfjf") String sfjf, @Param("status") String status)
			throws UnsupportedEncodingException
	    {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		status = new String(status.getBytes("ISO-8859-1"), "utf-8") + "";
		sfjf = new String(sfjf.getBytes("ISO-8859-1"), "utf-8") + "";
		Map<String, Object> map = new HashMap<String, Object>();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONArray members = new JSONArray();
		for (int i = 0; i < apiContentStr.length; i++)
		{
			if (apiContentStr[i] == 1)
			{
				JSONArray member = new JSONArray();
				//开度100
				int chartA = yhInfoService.findFamKdA(xqName, buildNo, cellNo, sfjf, status);
				member.add("开度100");
				member.add(chartA);
				members.add(member);
				map.put("chartA",chartA );
			}
			if (apiContentStr[i] == 3)
			{
				//开度0-15
				int chartC = yhInfoService.findFamKdC(xqName, buildNo, cellNo, sfjf, status);
				JSONArray member = new JSONArray();
				request.setAttribute("chartC", chartC);
				member.add("开度0-15");
				member.add(chartC);
				members.add(member);
				map.put("chartC",chartC );
			}
			
			if (apiContentStr[i] == 6)
			{
				//开度0%
				int chartF = yhInfoService.findFamKdF(xqName, buildNo, cellNo, sfjf, status);
				JSONArray member = new JSONArray();
				request.setAttribute("chartF", chartF);
				member.add("开度0");
				member.add(chartF);
				members.add(member);
				map.put("chartF",chartF );
			}

		}
		map.put("data", members);
		return map;

	}

	
	 //根据name查找具体信息
	@RequestMapping("findFkd")
	@ResponseBody
	public JSONObject findFkd(@Param("name")String name,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status) throws UnsupportedEncodingException{
		name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		status=new String(status.getBytes("ISO-8859-1"),"utf-8")+"";	
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		//判断是否包含-
		if(name.contains("-")&& name.length()==6){
			//判断在xy-dy之间
			String xy=name.substring(2,3);
			String dy=name.substring(4,6);
            //判断在55-75
			if(xy.equals("0")&&dy.equals("15")){
				yhInfoList=yhInfoService.findFKdC(xqName, buildNo, cellNo, sfjf, status);
				jsonObject.put("yhInfoList", yhInfoList);
			}
		}else{
			//判断是否0
			if(name.substring(2).equals("0")){
				yhInfoList=yhInfoService.findFKdF(xqName, buildNo, cellNo, sfjf, status);
				jsonObject.put("yhInfoList", yhInfoList);
			}else{
				//开度100%
				yhInfoList=yhInfoService.findFKdA(xqName, buildNo, cellNo, sfjf, status);
				jsonObject.put("yhInfoList", yhInfoList);
			}
		}
		return jsonObject;
	}
	
	
	// 获取所有的小区
	@RequestMapping("findYhNameList")
	public String findYhNameList(@RequestParam(value = "pageNum", required = false) String pageNum, ModelMap map,
			HttpServletRequest request, YhInfo yhInfo)
	{
		yhInfoList = yhInfoService.findYhNameList();
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		request.setAttribute("yhInfoJFStatus", yhInfoService.yhInfoJFStatus());
		request.setAttribute("chartgrfx", yhInfo);
		map.addAttribute("yhInfoList", yhInfoList);
		return "tjfx/kdtj";
	}

	// 根据小区获取楼栋号
	@RequestMapping("findYhBuildNObyXqName")
	@ResponseBody
	public JSONObject findYhBuildNObyXqName(String xqName) throws UnsupportedEncodingException
	{
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoList = yhInfoService.findYhBuildNObyXqName(xqName);
		JSONObject jsonObject = new JSONObject();
		if (yhInfoList != null)
		{
			jsonObject.put("xqlist", yhInfoList);
		} else
		{
			jsonObject.put("fail", null);
		}
		return jsonObject;
	}

	// 根据小区楼栋号获取单元号
	@RequestMapping("findYhCellNOByBuild")
	@ResponseBody
	public JSONObject findYhCellNOByBuild(@Param("build") int build, @Param("xqName") String xqName)
			throws UnsupportedEncodingException
	{
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoList = yhInfoService.findYhCellNOByBuild(build, xqName);
		JSONObject jsonObject = new JSONObject();
		if (yhInfoList != null)
		{
			jsonObject.put("cellList", yhInfoList);
		} else
		{
			jsonObject.put("fail", null);
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