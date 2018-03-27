package com.hnzy.hot.sbgl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.sbgl.pojo.Rb;
import com.hnzy.hot.sbgl.service.RbService;

@Controller
@RequestMapping("/Sbgl")
public class RbController {
	
	@Autowired
	private RbService rbService;
	private List<Rb> rblist;
	
	@RequestMapping("Rb")
	public String rblist(HttpServletRequest request){
		rblist = rbService.find();
		request.setAttribute("Rb", rblist);
		return "/sbgl/rb";
	}

	public List<Rb> getRblist() {
		return rblist;
	}

	public void setRblist(List<Rb> rblist) {
		this.rblist = rblist;
	}
	
	

	
}
