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

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.pojo.XqInfo;
import com.hnzy.hot.xxgl.service.RzService;
import com.hnzy.hot.xxgl.service.XqInfoService;

@Controller
@RequestMapping("/XqInfo")
public class XqInfoController
{
	@Autowired
	private XqInfoService xqInfoService;
	private List<XqInfo> xqInfos;
	@Autowired
	private RzService rzService;

	private List<Rz> rzs;

	// 列表页面
	@RequestMapping("findList")
	public String findList(HttpServletRequest request)
	{
		xqInfos = xqInfoService.find();
		request.setAttribute("xq", xqInfos);
		return "xxgl/yhgl/xqList";
	}

	// 添加
	@RequestMapping(value = "Insert", method = RequestMethod.POST)
	public String Insert(HttpSession session,XqInfo xqInfo,Rz rz)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("添加小区名为:" + xqInfo.getXqName());// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		//添加小区信息
		xqInfoService.Insert(xqInfo);
		return "redirect:findList.action";
	}

	// 删除
	@RequestMapping("delete")
	@ResponseBody
	public void delete(HttpSession session,XqInfo xqInfo,Rz rz,@RequestParam("id")String id)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("删除集中器");// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		//删除小区信息
		xqInfoService.delete(id);
	}

	// 更新
	@RequestMapping("update")
	public String update(HttpSession session,XqInfo xqInfo,Rz rz)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("修改集中器名为:" + xqInfo.getXqName());// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		//修改小区信息
		xqInfoService.update(xqInfo);
		return "redirect:findList.action";
	}
	//小区的平均温度
	@RequestMapping("xqNameAVG")
	@ResponseBody
	public JSONObject xqNameAVG(){
		JSONObject jsonObject=new JSONObject();
		xqInfos=xqInfoService.findAvgWdByXqName();
		jsonObject.put("xqInfn", xqInfos);
		return jsonObject;
	}

	public List<XqInfo> getXqInfos()
	{
		return xqInfos;
	}

	public void setXqInfos(List<XqInfo> xqInfos)
	{
		this.xqInfos = xqInfos;
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
