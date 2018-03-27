package com.hnzy.hot.xxgl.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.xxgl.pojo.Rbgl;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RbglService;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("rbglController")
public class RbglController
{
	@Autowired
	private RbglService rbglService;
	@Autowired
	private RzService rzService;
	private List<Rz> rz;
//列表
	@RequestMapping("findRb")
	public String findRb(HttpServletRequest request)
	{
		List<Rbgl> rbList = rbglService.find();
		request.setAttribute("rbList", rbList);
		return "xxgl/Rbgl/rbList";

	}
//添加
	@RequestMapping("add")
	public String add(HttpSession session ,Rbgl rbgl,Rz rz)
	{
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("添加热表类型为:"+rbgl.getRbType());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//添加热表信息
		rbglService.Insert(rbgl);
		return "redirect:findRb.action";
	}
//修改
	@RequestMapping("update")
	public String update(HttpSession session ,Rbgl rbgl,Rz rz)
	{
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("修改热表类型为:"+rbgl.getRbType());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//修改热表信息
		rbglService.update(rbgl);
		return "redirect:findRb.action";
	}
//删除
	@RequestMapping("delete")
	@ResponseBody
	public void delete(HttpSession session ,Rbgl rbgl,Rz rz,@RequestParam("id")String id)
	{
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("删除热表");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//删除热表信息
		rbglService.delete(id);
	}

	public List<Rz> getRz()
	{
		return rz;
	}

	public void setRz(List<Rz> rz)
	{
		this.rz = rz;
	}
	

}
