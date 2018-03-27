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

import com.hnzy.hot.xxgl.pojo.HeatESInfo;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.HeatESInfoService;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("/HeatESInfo")
public class HeatESInfoController
{
	@Autowired
	private HeatESInfoService heatESInfoService;
	private List<HeatESInfo> heatESInfos;
	@Autowired
	private RzService rzService;

	private List<Rz> rzs;

	// 列表页面
	@RequestMapping("findList")
	public String findList(HttpServletRequest request)
	{
		heatESInfos = heatESInfoService.find();
		request.setAttribute("hes", heatESInfos);
		return "xxgl/yhgl/hesList";
	}

	// 添加
	@RequestMapping(value = "Insert", method = RequestMethod.POST)
	public String Insert(HttpSession session, HeatESInfo heatESInfo, Rz rz)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("添加换热站名为:" + heatESInfo.getHesName());// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		// 添加换热站信息
		heatESInfoService.Insert(heatESInfo);
		return "redirect:findList.action";
	}

	// 删除
	@RequestMapping("delete")
	@ResponseBody
	public void delete(HttpSession session, HeatESInfo heatESInfo, Rz rz, @RequestParam("id")String id)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("删除换热站");// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		//删除换热站信息
		heatESInfoService.delete(id);
	}

	// 更新
	@RequestMapping("update")
	public String update(HttpSession session,HeatESInfo heatESInfo,Rz rz)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("修改换热站名为:" + heatESInfo.getHesName());// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		//修改换热站信息
		heatESInfoService.update(heatESInfo);
		return "redirect:findList.action";
	}

	public List<HeatESInfo> getHeatESInfos()
	{
		return heatESInfos;
	}

	public void setHeatESInfos(List<HeatESInfo> heatESInfos)
	{
		this.heatESInfos = heatESInfos;
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
