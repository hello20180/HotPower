package com.hnzy.hot.xxgl.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.xxgl.pojo.JzqInfo;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.JzqInfoService;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("/Jzq")
public class JzqInfoController
{
	@Autowired
	private JzqInfoService jzqInfoService;
	@Autowired
	private RzService rzService;
	
	private List<Rz> rzs;
	
	private List<JzqInfo> jzqInfos;
	
	
	//列表页面
	@RequestMapping("/JzqfindList")
	
	public String findList(HttpServletRequest request){
		jzqInfos=jzqInfoService.find();
		request.setAttribute("jzq",jzqInfos);
		return "xxgl/sbgl/JzqList";
	}	
	//添加
	@RequestMapping(value="/InsertJzq",method=RequestMethod.POST)
	public String Insert(HttpSession session,JzqInfo jzqInfo,Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("添加集中器名为:"+jzqInfo.getJzqID());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//添加集中器信息
		jzqInfo.setUpdateTime(new Date());
		jzqInfoService.Insert(jzqInfo);
		
		return "redirect:JzqfindList.action";
	}
	
	//删除
	@RequestMapping("/deleteJzq")
	@ResponseBody
	public void delete(HttpSession session,JzqInfo jzqInfo,Rz rz,@RequestParam("id")String id){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("删除集中器");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//删除集中器信息
		jzqInfoService.delete(id);
		
	}

	//更新
	@RequestMapping("/updateJzq")
	public String update(HttpSession session,JzqInfo jzqInfo,Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("修改集中器名为:"+jzqInfo.getJzqID());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//修改集中器信息
		jzqInfo.setUpdateTime(new Date());
		jzqInfoService.update(jzqInfo);
		return "redirect:JzqfindList.action";
	}

	
	public List<JzqInfo> getJzqInfos()
	{
		return jzqInfos;
	}

	public void setJzqInfos(List<JzqInfo> jzqInfos)
	{
		this.jzqInfos = jzqInfos;
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
