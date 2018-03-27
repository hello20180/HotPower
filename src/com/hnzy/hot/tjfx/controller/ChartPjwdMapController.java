package com.hnzy.hot.tjfx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.xxgl.pojo.XqInfo;
import com.hnzy.hot.xxgl.service.XqInfoService;

@Controller
@RequestMapping("ChartPjwdMapController")
public class ChartPjwdMapController
{

	@Autowired
	private XqInfoService xqInfoService;
	private List<XqInfo> xqInfoList;
	
	
	//根据小区查找阀门的室内平均温度
	@RequestMapping("findJW")
	@ResponseBody
	public JSONObject findJW(String userid){
		JSONObject jsonObject=new JSONObject();
		xqInfoList=xqInfoService.findAvgWdByXqName();
		jsonObject.put("xqList",xqInfoList);
		return jsonObject;
		
	}
	
	@RequestMapping("StMap")
	public String StateMap(){
		
		return "tjfx/PjMap";
		
	}

	public List<XqInfo> getXqInfoList()
	{
		return xqInfoList;
	}

	public void setXqInfoList(List<XqInfo> xqInfoList)
	{
		this.xqInfoList = xqInfoList;
	}
	
	
	
}
