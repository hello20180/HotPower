package com.hnzy.hot.xxgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.FmHistoryService;
import com.hnzy.hot.sjbb.service.YhInfoService;


@RequestMapping("BzCon")
@Controller
public class BzController
{
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private FmHistoryService fmHistoryService;
	private List<YhInfo> yhInfoList;
	@RequestMapping("Bz")
	public String Bz(String bz,String fmId) throws UnsupportedEncodingException{
		bz = new String(bz.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoService.UpBz(bz,fmId,new Date());
		return "xxgl/yhgl/Bz";
	}
	@RequestMapping("toBz")
	public String toBz(ModelMap map,HttpServletRequest request){
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		map.addAttribute("yhInfoList",yhInfoList);
		return "xxgl/yhgl/Bz";
		
	}
	//用户分类
	@RequestMapping("SubYhlb")
	@ResponseBody
    public JSONObject SubYhlb( YhInfo yhInfo,String yhlb,String fmId,HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo) throws UnsupportedEncodingException{
		JSONObject jsonObject=new JSONObject() ;
		if(xqName!=""&& yhlb!=""&&buildNo!=0&&cellNo!=0&&houseNo!=null){
			xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
			yhlb = new String(yhlb.getBytes("ISO-8859-1"), "utf-8") + "";
			 yhInfoService.UpYhlb(yhlb,xqName,buildNo,cellNo,houseNo);
			 yhInfo=yhInfoService.SeYhlb(xqName, buildNo, cellNo, houseNo);
			 jsonObject.put("SucYhlb",yhInfo );
		}else{
			 jsonObject.put("SucYhlb", "失败");
		}
		 return jsonObject;
    }
	
	//用户备注
	@RequestMapping("UpYhBz")
	@ResponseBody
    public JSONObject UpYhBz( YhInfo yhInfo,String bz,String fmId,HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo) throws UnsupportedEncodingException{
		JSONObject jsonObject=new JSONObject() ;
		if(xqName!=""&& bz!=""&&buildNo!=0&&cellNo!=0&&houseNo!=null){
			xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
			bz = new String(bz.getBytes("ISO-8859-1"), "utf-8") + "";
			 yhInfoService.UpYhBz(bz, xqName, buildNo, cellNo, houseNo);
			 yhInfo=yhInfoService.SeYhlb(xqName, buildNo, cellNo, houseNo);
			 jsonObject.put("SucYhlb",yhInfo );
		}else{
			 jsonObject.put("SucYhlb", "失败");
		}
		 return jsonObject;
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
			//根据小区楼栋号获取单元号
			@RequestMapping("ObrSel")
			@ResponseBody
			public JSONObject ObrSel(YhInfo yhInfo ,HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo) throws UnsupportedEncodingException{
				JSONObject jsonObject=new JSONObject();
				if(xqName!=""&&buildNo!=0&&cellNo!=0&&houseNo!=null){
					xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
					yhInfo= yhInfoService.ObrSel(xqName, buildNo, cellNo, houseNo);
					 jsonObject.put("ObrSel",yhInfo );
				}else{
					 jsonObject.put("ObrSel", "失败");
				}
				 return jsonObject;
			}
}
