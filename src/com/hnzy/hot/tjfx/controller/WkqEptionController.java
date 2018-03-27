package com.hnzy.hot.tjfx.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;

@Controller
@RequestMapping("WkqCon")
public class WkqEptionController
{
	@Autowired
	private YhInfoService yhInfoService;
	
	private List<YhInfo> yhInfoList;
	//搜索室内温度和管道温度异常用户
	@RequestMapping("Wkq")
	public String WkqEption(HttpServletRequest request ,String roomTemp,String valTemp,String sfjf ) throws UnsupportedEncodingException{
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		yhInfoList=yhInfoService.WkqEption(roomTemp, valTemp,sfjf);
		request.setAttribute("yhList", yhInfoList);
		
		return "tjfx/WkqEption";
	}
	//搜索室内温度和管道温度异常用户
	@ResponseBody
	@RequestMapping("WkqEptionSear")
	public JSONObject WkqEptionSear(HttpServletRequest request ,String roomTemp,String valTemp,String sfjf ) throws UnsupportedEncodingException{
		sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
		yhInfoList=yhInfoService.WkqEption(roomTemp, valTemp,sfjf);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("yhList", yhInfoList);
		return jsonObject;
	}
	
	//导出
		@RequestMapping("WkqdoExportExcel")
		public void  YhInfodoExportExcel(YhInfo yhInfo,HttpSession session,HttpServletResponse response,@Param("valTemp")String valTemp,@Param("roomTemp")String roomTemp,@Param("sfjf")String sfjf) throws IOException{
			//告诉浏览器要弹出的文档类型
			response.setContentType("application/x-execl");
			//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
			//获取输出流
					sfjf=new String(sfjf.getBytes("ISO-8859-1"),"utf-8")+"";
					ServletOutputStream outputStream=response.getOutputStream();
					yhInfoService.exportExcel(yhInfoService.WkqEption(roomTemp, valTemp,sfjf), outputStream);
			//日志
//			Rz rz=new Rz();
//			rz.setCz("导出:小区名称："+xqName+",楼栋号："+buildNo+",单元号："+cellNo);
//			rz.setCzr((String)session.getAttribute("userName"));
//			rz.setCzsj(new Date());
//			rzService.insert(rz);
		}
	
	
	public String WkqEptionSe(String roomTemp,String valTemp){
		
		
		return valTemp;
		
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
