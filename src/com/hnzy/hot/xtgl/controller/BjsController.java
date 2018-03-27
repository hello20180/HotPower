package com.hnzy.hot.xtgl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.xtgl.pojo.Bsj;
import com.hnzy.hot.xtgl.service.BsjService;

@Controller
@RequestMapping("BsjController")
public class BjsController
{

	@Autowired
	private BsjService bsjService;
	private List<Bsj> listBsj;
	
	@RequestMapping("findBsj")
	public String findBsj(HttpServletRequest request){
		listBsj=bsjService.findBsj();
		request.setAttribute("listBsj",listBsj);
		return "xtgl/Bsj/listBsj";
	}
}
