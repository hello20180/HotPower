package com.hnzy.hot.kfgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.kfgl.pojo.Repair;
import com.hnzy.hot.kfgl.service.RepairService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.xtgl.pojo.User;
import com.hnzy.hot.xtgl.service.UserService;
import com.hnzy.hot.xxgl.pojo.HeatESInfo;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.HeatESInfoService;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("/Repair")
public class RepairController
{
	@Autowired
	private RepairService repairService;
	@Autowired
	private RzService rzService;
	
	private List<Rz> rzs;
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private UserService userService;
	private List<Repair> repairs;
	private List<YhInfo> yhInfoList;
	private List<Repair> hes;
	//查找信息
	@RequestMapping("/Repairlist")
	public String list(HttpServletRequest request,Repair repair){
		
		//零代表报修登记
		repairs=repairService.findRepair(0);
		request.setAttribute("rep",repairs);
		request.setAttribute("st",repairService.findState(0));
		request.setAttribute("list",repairService.findplace());
		request.setAttribute("sums",repairService.sum(repair,0));
		request.setAttribute("wjd",repairService.state0(0));
		request.setAttribute("yjd",repairService.state1(0));
		request.setAttribute("ywc",repairService.state2(0));
		return "kfgl/bxdj/repair";
	}
	
	//查找信息申请安装 
		@RequestMapping("/RepairlistS")
		public String lists(HttpServletRequest request,Repair repair){
			//壹代申请安装
			repairs=repairService.findRepair(1);
			request.setAttribute("rep",repairs);
			request.setAttribute("st",repairService.findState(1));
			request.setAttribute("list",repairService.findplace());
			request.setAttribute("sums",repairService.sum(repair,1));
			request.setAttribute("wjd",repairService.state0(1));
			request.setAttribute("yjd",repairService.state1(1));
			request.setAttribute("ywc",repairService.state2(1));
			return "kfgl/bxdj/repairSqaz";
		}
	
	//根据处获取换热站
			@RequestMapping("findHes")
			@ResponseBody
			public JSONObject findHes(String place) throws UnsupportedEncodingException{
				place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
				hes=repairService.findHESName(place);
				JSONObject jsonObject=new JSONObject() ;
				if(hes!=null){
					jsonObject.put("heslist", hes);
				}else{
					jsonObject.put("fail", null);
				}
				return jsonObject;
			}

	//添加故障申报
	@RequestMapping(value="InsertRepair" , method=RequestMethod.POST)
	public String  InsertRepair(HttpSession session,Repair repair,Rz rz ) throws UnsupportedEncodingException{
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("添加"+repair.getXqName()+"小区"+repair.getBuildNo()+"楼"+repair.getCellNo()+"户号报修内容");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		repair.setType("故障申报");
		repair.settJname(new String(((String) session.getAttribute("userName"))));
		repair.settJtime(new Date());
		repairService.InsertRepair(repair);
		return "redirect:Repairlist.action";
		
	}	

	
	//添加申请安装
		@RequestMapping(value="InsertRepairS" , method=RequestMethod.POST)
		public String  InsertRepairS(HttpSession session,Repair repair,Rz rz ) throws UnsupportedEncodingException{
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("添加"+repair.getXqName()+"小区"+repair.getBuildNo()+"楼"+repair.getCellNo()+"户号报修内容");//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			repair.setType("申请安装");
			repair.settJname(new String(((String) session.getAttribute("userName"))));
			repair.settJtime(new Date());
			repairService.InsertRepair(repair);
			return "redirect:RepairlistS.action";
			
		}
	//更新信息
	@RequestMapping(value="updateRepair", method=RequestMethod.POST)
	public String updateRepair(HttpSession session,Repair repair,Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("修改"+repair.getXqName()+"小区"+repair.getBuildNo()+"楼"+repair.getCellNo()+"单元"+"户号报修内容");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//修改
		repair.settJname(new String(((String) session.getAttribute("userName"))));
		repair.settJtime(new Date());
		repairService.updateRepair(repair);
		return "redirect:Repairlist.action";		
	}
	//更新信息
	@RequestMapping(value="updateRepairS", method=RequestMethod.POST)
	public String updateRepairS(HttpSession session,Repair repair,Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("修改"+repair.getXqName()+"小区"+repair.getBuildNo()+"楼"+repair.getCellNo()+"单元"+"户号报修内容");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//修改
		repair.settJname(new String(((String) session.getAttribute("userName"))));
		repair.settJtime(new Date());
		repairService.updateRepair(repair);
		return "redirect:RepairlistS.action";		
	}
	//删除信息
		@RequestMapping("delete")
		@ResponseBody
		public void delete(HttpSession session,Repair repair,Rz rz,@RequestParam("id")String id){
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("删除报修信息");//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			//删除报修信息
			repairService.deleteRepair(id);
		}

 //搜索方法
	@RequestMapping("/listState1")
	@ResponseBody
	public JSONObject listState1(HttpServletRequest request,Repair repair,@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl) throws UnsupportedEncodingException{		
		place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
		hesName=new String(hesName.getBytes("ISO-8859-1"),"utf-8")+"";
		state=new String(state.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject json=new JSONObject();
		repairs= repairService.findByState(place,hesName,state,kffl);
		int Cx=repairService.stateCx(place, hesName, state,kffl);
		int ywc=repairService.stateywc(place, hesName, state,kffl);
		int yjd=repairService.stateyjd(place, hesName, state,kffl);
		int wjd=repairService.statewjd(place, hesName, state,kffl);
		json.put("wjd", wjd);
		json.put("yjd", yjd);
		json.put("ywc", ywc);
		json.put("cx", Cx);
		json.put("rep",repairs);
		return json;
	}

	//根据状态比例显示饼状图
	@RequestMapping("/StateChart")
	public String State(HttpServletRequest request,Repair repair){	
		//获取所有的小区
		request.setAttribute("yhInfolist", yhInfoService.findrepair());
		return "kfgl/bxdj/chart";
	}
	
	
	//根据状态比例显示饼状图
	@RequestMapping("/StateCharts")
	public String States(HttpServletRequest request,Repair repair){	
		//获取所有的小区
		request.setAttribute("yhInfolist", yhInfoService.findrepair());
		return "kfgl/bxdj/chartSqaz";
	}
	
	//根据小区名字显示饼图
	@RequestMapping("chartSearch")
	@ResponseBody
	public Map<String, Object>chartSearch(HttpServletResponse response,HttpServletRequest request,@Param("xqName")String xqName,@Param("kffl")int kffl) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		Map<String,Object> map=new HashMap<String,Object>(); 
		 JSONArray members=new JSONArray();
		 List<Object> listmap=new ArrayList<Object>();
		 List<Map<String,Object>> search=repairService.chartSearch(xqName,kffl);
            for(Map<String, Object> m:search){
            	for(String k:m.keySet()){
            		JSONArray member=new JSONArray();
            		member.add(k);
            		member.add(m.get(k));
            		members.add(member);
            		listmap.add(m.get(k));
            	}
            }
              map.put("data", members);
              map.put("yjd", listmap.get(2));
              map.put("ywc", listmap.get(1));
              map.put("wjd", listmap.get(0));
             
		  return map;
	}
	//获取所有的小区
	@RequestMapping("findYhNameList")
	@ResponseBody
	public JSONObject findYhNameList(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("yhInfolist",yhInfoService.findrepair());
		jsonObject.put("jsname", userService.findJSName());
		//获取所有的处
		jsonObject.put("listPlace",repairService.findplace());
		
		return jsonObject;		
	}
	//根据处,换热站获取所有的小区名字
	@RequestMapping("findXqNamebyPlace")
	@ResponseBody
	public JSONObject findXqNamebyPlace(String place,String hESName) throws UnsupportedEncodingException{
		 hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
		place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
		yhInfoList=yhInfoService.findXqNamebyPlace(place,hESName);
		JSONObject jsonObject=new JSONObject() ;
		if(yhInfoList!=null){
			jsonObject.put("xqNameList", yhInfoList);
			jsonObject.put("jsname", userService.findJSName());
		}else{
			jsonObject.put("fail", null);
		}
		return jsonObject;
		
	}
	
	//根据小区和处获取楼栋号
		@RequestMapping("findYhBuildNObyXqName")
		@ResponseBody
		public JSONObject findYhBuildNObyXqName(String xqName,String place ,String hESName) throws UnsupportedEncodingException{
			 xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			 place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
			 hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
			 yhInfoList=yhInfoService.findYhBuildNObyXqNamerepair(xqName,place,hESName);
			JSONObject jsonObject=new JSONObject() ;
			if(yhInfoList!=null){
				jsonObject.put("xqlist", yhInfoList);
			}else{
				jsonObject.put("fail", null);
			}
			return jsonObject;
		}
		//根据小区楼栋号，处获取单元号
		@RequestMapping("findYhCellNOByBuild")
		@ResponseBody
		public JSONObject findYhCellNOByBuild(@Param("hESName")String hESName,@Param("build")String build,@Param("xqName")String xqName,@Param("place")String place) throws UnsupportedEncodingException{
			place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
			xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			build=new String(build.getBytes("ISO-8859-1"),"utf-8")+"";
			hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList=yhInfoService.findYhCellNOByBuildrepair(build, xqName,place,hESName);
			JSONObject jsonObject=new JSONObject();
			if(yhInfoList!=null){
				jsonObject.put("cellList",yhInfoList);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
			
		}
		
	public List<Repair> getHes()
		{
			return hes;
		}

		public void setHes(List<Repair> hes)
		{
			this.hes = hes;
		}

	public List<Repair> getRepairs()
	{
		return repairs;
	}

	public void setRepairs(List<Repair> repairs)
	{
		this.repairs = repairs;
	}
	
	public List<YhInfo> getYhInfoList()
	{
		return yhInfoList;
	}
	public void setYhInfoList(List<YhInfo> yhInfoList)
	{
		this.yhInfoList = yhInfoList;
	}

	public List<Rz> getRzs()
	{
		return rzs;
	}

	public void setRzs(List<Rz> rzs)
	{
		this.rzs = rzs;
	}

	
}
