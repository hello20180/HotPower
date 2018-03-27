package com.hnzy.hot.nhfx.controller;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.nhfx.pojo.HeatStationHistory;
import com.hnzy.hot.nhfx.pojo.Nhfxtxx;
import com.hnzy.hot.nhfx.service.HeatStationHistoryService;
import com.hnzy.hot.nhfx.service.NhfxtxxService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

@Controller
@RequestMapping("MjnhController")
public class MjnhController {
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private NhfxtxxService nhfxtxxService;
	private List<YhInfo> yhInfoList;
	private List<Nhfxtxx> nhfList;
	public  List<HeatStationHistory> heatStationHistoryList;
	// 获取所有的小区
			@RequestMapping("findYhNameList")
			public String findYhNameList(ModelMap map,HttpServletRequest request, YhInfo yhInfo,@Param("xqName")String xqName)
			{
				yhInfoList = yhInfoService.findYhNameList();
		
				map.addAttribute("yhInfoList", yhInfoList);
				return "nhfx/mjnhfx";
			}
			@RequestMapping("findNhfxTime")
			@ResponseBody
			public JSONObject findNhfxTime(@Param("xqName")String xqName) throws UnsupportedEncodingException{
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				nhfList=nhfxtxxService.findList(xqName );
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("nhfList", nhfList);
				return jsonObject;
			}
			@RequestMapping("findTimeListS")
			@ResponseBody
			public JSONObject findTimeListS(@Param("xqName")String xqName, @Param("readTime") String readTime) throws UnsupportedEncodingException{
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				nhfList=nhfxtxxService.findTimeListS(xqName, readTime);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("nhfSList", nhfList);
				return jsonObject;
				
			}
			
			@RequestMapping("HeatAreaByxqName")
			@ResponseBody
			public Map<String, Object> HeatAreaByxqName(@Param("xqName")String xqName , @Param("readTime1") String readTime1,
					@Param("readTime2") String readTime2) throws UnsupportedEncodingException, ParseException{
				Map<String, Object> map=new HashMap<>();
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				//根据小区查找供热面积
				Double sumHeat=yhInfoService.HeatAreaByxqName(xqName);
				if(xqName.equals("金领小区")){
					sumHeat=30690.27;	
				}else if(xqName.equals("嘉亿城市广场")){
					sumHeat=25160.51;
				}
				List<Nhfxtxx> NhList=nhfxtxxService.sumYlHeat(xqName, readTime1, readTime2);
				JSONArray json=new JSONArray();
				for(int i=0;i<NhList.size();i++){
					JSONArray jsonArray=new JSONArray();
					if(i==0){
						String heatTime=NhList.get(i).getReadTime();
						SimpleDateFormat fimpleDataForma=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
						Date data=fimpleDataForma.parse(heatTime);
						String datas=fimpleDataForma.format(data);
						//用户用的能耗
	                    Double nhz=NhList.get(i).getNhz();
						jsonArray.add(datas);
						jsonArray.add(nhz);
						json.add(jsonArray);	
					}else{
						//上次时间
						String SheatTime=NhList.get(i-1).getReadTime();
						SimpleDateFormat SfimpleDataForma=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
						Date Sdata=SfimpleDataForma.parse(SheatTime);
						//当前时间
						String heatTime=NhList.get(i).getReadTime();
						SimpleDateFormat fimpleDataForma=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
						Date data=fimpleDataForma.parse(heatTime);
						String datas=fimpleDataForma.format(data);
						//所用天数
						long day=(data.getTime()-Sdata.getTime())/(24*60*60*1000);
						//用户上次用的能耗
						  Double Snhz=NhList.get(i-1).getNhz();
						//用户当前用的能耗
	                    Double nhz=NhList.get(i).getNhz();
	                    Double GNhz=nhz-Snhz;
	                    Double Jheat= ((GNhz/day)/sumHeat)*120;
						BigDecimal bg = new BigDecimal(Jheat);
			            double JHeat = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
						jsonArray.add(datas);
						jsonArray.add(JHeat);
						json.add(jsonArray);	
						
					}
					
			
				}
				map.put("nhfx", NhList);
				map.put("data",json);
				return  map;
			}
			@RequestMapping("findNhz")
			@ResponseBody
			public JSONObject findNhz(@Param("xqName")String xqName , @Param("TimeListId") String TimeListId,
					@Param("TimeListSId") String TimeListSId) throws UnsupportedEncodingException{
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				Nhfxtxx NhList=nhfxtxxService.findNhz(xqName, TimeListId);
				Nhfxtxx NhSList=nhfxtxxService.findNhz(xqName, TimeListSId);
				double nhz=NhSList.getNhz()-NhList.getNhz();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("time",TimeListId);
				jsonObject.put("times",TimeListSId);
				jsonObject.put("Xq",xqName);
				jsonObject.put("Nhst", nhz);
				 return jsonObject;
				
				
			}
			@RequestMapping("UpNhfx")
			@ResponseBody
			public JSONObject UpNhfx(ModelMap map,String xqName,String readTime,Double nhz) throws UnsupportedEncodingException{
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				JSONObject jsonObject=new JSONObject();
				if(xqName!=null&& nhz!=null &&readTime!=""){
					nhfxtxxService.UpNhxx(xqName, nhz, readTime);
					jsonObject.put("data", "成功");
				}else{
					jsonObject.put("data","失败");
				}
				return jsonObject;
				
			}
			public List<YhInfo> getYhInfoList() {
				return yhInfoList;
			}
			public void setYhInfoList(List<YhInfo> yhInfoList) {
				this.yhInfoList = yhInfoList;
			}
			public List<HeatStationHistory> getHeatStationHistoryList() {
				return heatStationHistoryList;
			}
			public void setHeatStationHistoryList(List<HeatStationHistory> heatStationHistoryList) {
				this.heatStationHistoryList = heatStationHistoryList;
			}

			public List<Nhfxtxx> getNhfList()
			{
				return nhfList;
			}

			public void setNhfList(List<Nhfxtxx> nhfList)
			{
				this.nhfList = nhfList;
			}
			
	
}
