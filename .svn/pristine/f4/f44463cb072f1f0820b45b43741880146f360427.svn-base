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

import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.service.QgService;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/QYG")
public class QYGController
{
	@Autowired
	private QgService qgService;
	@Autowired
	private RzService rzService;
	
	private List<Rz> rzs;
	private List<Qg> qgs;
	//列表页面
	@RequestMapping("/QYGfindList")
	public String findList(HttpServletRequest request){
		qgs=qgService.find();
		request.setAttribute("qg",qgs);
		return "xxgl/sbgl/QgList";
	}	
	//添加
	@RequestMapping(value="/Insert",method=RequestMethod.POST)
	public String Insert(HttpSession session ,Qg qg,Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("添加区管id为:"+qg.getQgID());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//添加区管信息
		qg.setRecordTime(new Date());
		qgService.Insert(qg);
		return "redirect:QYGfindList.action";
	}
	
	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpSession session ,Qg qg,Rz rz,@RequestParam("id")String id){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("删除区管");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//删除区管信息
		qgService.delete(id);
	
	}

	//更新
	@RequestMapping("/update")
	public String update(HttpSession session ,Qg qg,Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("修改区管id为:"+qg.getQgID());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//修改区管信息
		qgService.update(qg);
		return "redirect:QYGfindList.action";
	}
	
	public List<Qg> getQgs()
	{
		return qgs;
	}
	public void setQgs(List<Qg> qgs)
	{
		this.qgs = qgs;
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
