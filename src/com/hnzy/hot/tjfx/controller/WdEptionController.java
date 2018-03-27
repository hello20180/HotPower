package com.hnzy.hot.tjfx.controller;

import java.io.IOException;
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
@RequestMapping("WdCon")
public class WdEptionController
{
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	 //开度和管道异常
	@RequestMapping("Wd")
	public String WdEption(HttpServletRequest request,String famKd,String valTemp){
		yhInfoList=yhInfoService.WdEption(famKd, valTemp);
		request.setAttribute("yhList", yhInfoList);
		return "tjfx/WdEption";
	}
	
	//搜索室内温度和管道温度异常用户
		@ResponseBody
		@RequestMapping("WdEptionSear")
		public JSONObject WkqEptionSear(HttpServletRequest request ,String famKd,String valTemp ){
			yhInfoList=yhInfoService.WdEption(famKd, valTemp);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("yhList", yhInfoList);
			return jsonObject;
		}
		
		//导出
			@RequestMapping("WkqdoExportExcel")
			public void  YhInfodoExportExcel(YhInfo yhInfo,HttpSession session,HttpServletResponse response,@Param("famKd")String famKd,@Param("valTemp")String valTemp) throws IOException{
				//告诉浏览器要弹出的文档类型
				response.setContentType("application/x-execl");
				//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
				response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
				//获取输出流

						ServletOutputStream outputStream=response.getOutputStream();
						yhInfoService.exportExcel(yhInfoService.WdEption(famKd, valTemp), outputStream);
				//日志
//				Rz rz=new Rz();
//				rz.setCz("导出:小区名称："+xqName+",楼栋号："+buildNo+",单元号："+cellNo);
//				rz.setCzr((String)session.getAttribute("userName"));
//				rz.setCzsj(new Date());
//				rzService.insert(rz);
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
