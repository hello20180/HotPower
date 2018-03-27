package com.hnzy.hot.xtgl.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.xtgl.pojo.ServerSet;
import com.hnzy.hot.xtgl.service.ServerSetService;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("/ServerSet")
public class ServerSetController {
@Autowired
private ServerSetService serverSetService;
@Autowired
private RzService rzService;

@RequestMapping("ServletSetlist")
public String  findServerSet(HttpServletRequest request,ServerSet serverSet){
	serverSet=serverSetService.findServerSet();
	 request.setAttribute("st",serverSet);
	return "xtgl/ServletSet/ServletSetInsert";
	 
}

@RequestMapping("updateServerSet")
public String updateServerSet(HttpSession session,ServerSet serverSet){
	serverSetService.updateServerSet(serverSet);
	Rz rz =new Rz();
	rz.setCz("修改服务器设置 服务器名称");
	rz.setCzr((String )session.getAttribute("userName"));
	rz.setCzsj(new Date());
	rzService.insert(rz);
	return"xtgl/ServletSet/success";
}




}
