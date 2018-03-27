package com.hnzy.hot.sbgl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hnzy.hot.xxgl.pojo.JzqInfo;
import com.hnzy.hot.xxgl.service.JzqInfoService;

@Controller
@RequestMapping("/Sbgl")
public class JzqController {
	@Autowired
	private JzqInfoService jzqInfoService;
	private List<JzqInfo> jzqInfos;

	//列表页面
	@RequestMapping("/Jzq")
	
	public String findList(HttpServletRequest request){
		jzqInfos=jzqInfoService.find();
		request.setAttribute("jzq",jzqInfos);
		return "sbgl/jzq";
	}	
	
	public List<JzqInfo> getJzqInfos()
	{
		return jzqInfos;
	}

	public void setJzqInfos(List<JzqInfo> jzqInfos)
	{
		this.jzqInfos = jzqInfos;
	}
}
