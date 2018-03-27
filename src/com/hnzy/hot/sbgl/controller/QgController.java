package com.hnzy.hot.sbgl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.service.QgService;

@Controller
@RequestMapping("/Sbgl")
public class QgController {
	
	@Autowired
	private QgService qgService;
	
	private List<Qg> qglist;
	
	//查找区管信息
	@RequestMapping("Qg")
	public String qglist(HttpServletRequest request){
		qglist = qgService.find();
		request.setAttribute("Qg", qglist);
		return "sbgl/qg";
	}

}
