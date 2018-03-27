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
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.service.TjfxKdHisService;

@RequestMapping("tjfxKdCon")
@Controller
public class TjfxKdController
{
	@Autowired
   private TjfxKdHisService tjfxKdHisService;
   @Autowired
   private YhInfoService yhInfoService;
   private List<YhInfo> yhInfoList;
   private List<TjfxKdHis> findKdList;
   @RequestMapping("findKd")
   public String findKd(ModelMap map,HttpServletRequest request){
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		map.addAttribute("yhInfoList",yhInfoList);
		findKdList=tjfxKdHisService.findKd();
	   request.setAttribute("Tjfxkd",findKdList);
	   return "tjfx/tjfxKdHistory";
   }
   @RequestMapping("searchInfo")
   @ResponseBody
   public JSONObject search(String xqName,String recordTime1,String recordTime2) throws UnsupportedEncodingException{
	   xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
	   JSONObject jsonObject=new JSONObject();
	   jsonObject.put("tjfxKd", tjfxKdHisService.searchInfo(xqName, recordTime1, recordTime2));
	return jsonObject;
	  
   }
}
