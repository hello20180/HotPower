package com.hnzy.hot.xtgl.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.xtgl.pojo.CsTime;
import com.hnzy.hot.xtgl.service.CsTimeService;


@Controller
@RequestMapping("CsTimeController")
public class CsTimeController
{
	@Autowired
  private CsTimeService csTimeService;
  @RequestMapping("findCsTiem")
  public String findCsTiem(HttpServletRequest request, CsTime csTime)
	{
	  csTime=csTimeService.findCsTiem();
	  request.setAttribute("CsTime", csTime);
	return "xtgl/CsTime/csTime";
	}
  
  
  @RequestMapping("updateCsTime")
  public String updateCsTime(int cstime){
	  csTimeService.updateCsTime(cstime);
		return "xtgl/ServletSet/success";
  }
}
