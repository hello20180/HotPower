package com.hnzy.hot.tjfx.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.tjfx.pojo.TjfxHistory;
import com.hnzy.hot.tjfx.service.TjfxHistoryService;

@Controller
@RequestMapping("TjfxCon")
public class TjfxHistoryController
{
	@Autowired
	private TjfxHistoryService tjfxHistoryService;
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	private List<TjfxHistory> tjfxList;
	@RequestMapping("findTjfx")
	public String findTjfx(ModelMap map,HttpServletRequest request){
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		map.addAttribute("yhInfoList",yhInfoList);
		request.setAttribute("findTjfx",tjfxHistoryService.findTjfx());
		return "tjfx/tjfxHistory";
	}
	
	@RequestMapping("searchInfo")
	@ResponseBody
	public JSONObject searchInfo(String xqName,String recordTime1,String recordTime2) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		tjfxList=tjfxHistoryService.searchInfo(xqName, recordTime1, recordTime2);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("tjfx", tjfxList);
		return jsonObject;
		
	}
}
