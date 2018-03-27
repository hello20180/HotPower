package com.hnzy.hot.sjbb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;


@Controller
@RequestMapping("/YhInfo")
public class YhInfoController {
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private FmHistoryService fmHistoryService;
	@Autowired
	private RzService rzService;
	private List<YhInfo> yhInfoList;
	//搜索并显示
	@RequestMapping("searchInfo")
	@ResponseBody
	public JSONObject searchInfo(HttpServletRequest request,ModelMap map,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		//houseNo为null查询实时表，则查询历史表
		if(houseNo==null){
			 houseNo=0;
			 yhInfoList= yhInfoService.searchInfo(xqName, buildNo, cellNo, houseNo, sfjf,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2);
			jsonObject.put("findXqInfoFmHistory",yhInfoList);
		}else{
			 yhInfoList= yhInfoService.searchFmHistory(xqName, buildNo, cellNo, houseNo, sfjf,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2);
			jsonObject.put("findXqInfoFmHistory",yhInfoList );	
		}
		return jsonObject;		
	}

	//导出
	@RequestMapping("YhInfodoExportExcel")
	public void  YhInfodoExportExcel(YhInfo yhInfo,HttpSession session,HttpServletResponse response,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2) throws IOException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		//告诉浏览器要弹出的文档类型
		response.setContentType("application/x-execl");
		//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
		response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
		//获取输出流
		if(houseNo==null){
			 houseNo=0;
				ServletOutputStream outputStream=response.getOutputStream();
				yhInfoService.exportExcel(yhInfoService.searchInfo(xqName, buildNo, cellNo, houseNo, sfjf,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2), outputStream);
				if(outputStream!=null){
					outputStream.close();
				}	 
		}else{
		ServletOutputStream outputStream=response.getOutputStream();
		yhInfoService.exportExcel(yhInfoService.searchFmHistory(xqName, buildNo, cellNo, houseNo, sfjf,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2), outputStream);
			 
		if(outputStream!=null){
			outputStream.close();
		  }
	    }
		//日志
		Rz rz=new Rz();
		rz.setCz("导出:小区名称："+xqName+",楼栋号："+buildNo+",单元号："+cellNo);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
	}
	
	//获取所有的小区
	@RequestMapping("findYhNameList")
	public String findYhNameList(@RequestParam(value="pageNum",required=false)String pageNum,
			ModelMap map,HttpServletRequest request,YhInfo yhInfo){
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		map.addAttribute("yhInfoList",yhInfoList);
		
		return "sjbb/YhInfo";		
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
