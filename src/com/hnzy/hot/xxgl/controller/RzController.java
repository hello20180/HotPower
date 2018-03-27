package com.hnzy.hot.xxgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

import net.sf.ehcache.config.Searchable;

@Controller
@RequestMapping("rz")
public class RzController
{
	@Autowired
	private RzService rzService;
	private Rz rz;
	
	@RequestMapping("Search")
	public String  Search(HttpServletRequest request,String cz) throws UnsupportedEncodingException
	{
	    if(cz!=null){
	    cz=new String(cz.getBytes("ISO-8859-1"),"utf-8")+"";
	    }
		List<Rz> rzList = rzService.Search("%"+cz+"%");
		request.setAttribute("cz",cz);
		request.setAttribute("log", rzList);
		return "xxgl/rzgl/log";
	}
    //查询列表
	@RequestMapping("rzList")
	public String rzList(HttpServletRequest request)
	{
	
		List<Rz> rzList = rzService.rzList();
		request.setAttribute("log", rzList);
		return "xxgl/rzgl/log";
	}
	//index页面显示
	@ResponseBody
	@RequestMapping("rzindex")
	public JSONObject rzindex(HttpServletRequest request)
	{
	
		List<Rz> rzList = rzService.rzList();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("log", rzList);
		return jsonObject;
	}
//添加
	@RequestMapping("insert")
	public String insert(Rz rz)
	{
		rzService.insert(rz);
		return "redirect:rzList.action";
	}
	public Rz getRz()
	{
		return rz;
	}
	public void setRz(Rz rz)
	{
		this.rz = rz;
	}

}
