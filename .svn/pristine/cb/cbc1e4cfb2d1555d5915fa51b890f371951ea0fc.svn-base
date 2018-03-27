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

import com.hnzy.hot.xxgl.pojo.ReadInfo;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.ReadInfoService;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("read")
public class ReadInfoController
{
	@Autowired
	private ReadInfoService readInfoService;
	private List<ReadInfo> lInfos;
	@Autowired
	private RzService rzService;
	
	private List<Rz> rzs;

	//列表页面
	@RequestMapping("/findList")
	
	public String findList(HttpServletRequest request){
		lInfos=readInfoService.find();
		request.setAttribute("readInfo",lInfos);
		return "xxgl/sbgl/readList";
	}	
	//添加
		@RequestMapping(value="/Insert",method=RequestMethod.POST)
		public String Insert(HttpSession session,ReadInfo readInfo,Rz rz){
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("添加刷卡器名为:"+readInfo.getiReadId());//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			//添加读卡器信息
			readInfo.setRecordTime(new Date());
			readInfoService.Insert(readInfo);
			
			return "redirect:findList.action";
		}
		
		//删除
		@RequestMapping("/delete")
		@ResponseBody
		public void delete(HttpSession session,ReadInfo readInfo,Rz rz,@RequestParam("id")String id){
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("删除刷卡器");//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			//删除读卡器信息
			readInfoService.delete(id);
			
		}

		//更新
		@RequestMapping("/update")
		public String update(HttpSession session,ReadInfo readInfo,Rz rz){
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("修改集中器名为:"+readInfo.getiReadId());//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			//修改读卡器信息
			readInfo.setRecordTime(new Date());
			readInfoService.update(readInfo);
			return "redirect:findList.action";
		}

	
	
	
	public List<ReadInfo> getlInfos()
	{
		return lInfos;
	}

	public void setlInfos(List<ReadInfo> lInfos)
	{
		this.lInfos = lInfos;
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
