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
import com.hnzy.hot.sjbb.pojo.FmHistory;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.FmHistoryService;
import com.hnzy.hot.sjbb.service.YhInfoService;

@Controller
@RequestMapping("SwfxSdController")
public class ChartSwfxSdController {
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private FmHistoryService fmHistoryService;
	private List<YhInfo> yhInfoList;
	private List<FmHistory> fmHiststoryList;
	
	//散点图
	@RequestMapping("chartSearchSd")
	@ResponseBody
	public Map<String, Object>chartSearchSd(FmHistory fmHistory,HttpServletResponse response,HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		status=new String(status.getBytes("ISO-8859-1"),"utf-8")+"";	
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		fmHiststoryList=fmHistoryService.chartSearchSd(xqName, buildNo, cellNo, sfjf, status);
		Map<String,Object> map=new HashMap<String,Object>(); 
		   JSONArray members = new JSONArray();
		   for(int i=0;i<fmHiststoryList.size();i++){
			   JSONArray member=new JSONArray();
			   member.add(fmHiststoryList.get(i).getId());
			   member.add(fmHiststoryList.get(i).getRoomTemp());
			   members.add(member);
		   }
		  map.put("data",members);
		return map;
	}
	//根据id查找具体信息
	@RequestMapping("findSd")
	@ResponseBody
	public JSONObject findSd(int id,FmHistory fmHistory){
		fmHistory=fmHistoryService.findSd(id);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("xqName", fmHistory.getYhInfo().getXqName());
		jsonObject.put("buildNo", fmHistory.getYhInfo().getBuildNo());
		jsonObject.put("cellNo", fmHistory.getYhInfo().getCellNo());
		jsonObject.put("houseNo", fmHistory.getYhInfo().getHouseNo());
		jsonObject.put("roomTemp", fmHistory.getRoomTemp());
		jsonObject.put("valTemp", fmHistory.getValTemp());
		jsonObject.put("famKd", fmHistory.getFamKd());
		jsonObject.put("recordTime", fmHistory.getRecordTime());
		jsonObject.put("status", fmHistory.getStatus());
		return jsonObject;
		
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
				return "tjfx/ChartSwfxSd";		
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

			public List<FmHistory> getFmHiststoryList() {
				return fmHiststoryList;
			}

			public void setFmHiststoryList(List<FmHistory> fmHiststoryList) {
				this.fmHiststoryList = fmHiststoryList;
			}

}
