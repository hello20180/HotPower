package com.hnzy.hot.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("home")
public class home {
	//主页
	@RequestMapping("index")
	public String homet() {
		
		return "home/index";
	}
	//运行管理
	@RequestMapping("foots")
	public String homets() {
		
		return "home/foots";
	}
	@RequestMapping("sbgl")
	public String sbgl() {
		
		return "home/sbgl";
	}
	//客服管理
	@RequestMapping("kfgl")
	public String kfgl() {
		
		return "home/kfgl";
	}
	//统计分析
	@RequestMapping("tjfx")
	public String tjfx() {
		
		return "home/tjfx";
	}
	//信息管理
	@RequestMapping("xxgl")
	public String xxgl() {
		
		return "home/xxgl";
	}
	//数据报表
	@RequestMapping("sjbb")
	public String sjbb() {
		
		return "home/sjbb";
	}
	//系统设置
	@RequestMapping("xtsz")
	public String xtsz() {
		
		return "home/xtsz";
	}
	//换热站管理
	@RequestMapping("hrzgl")
	public String hrzgl() {
		
		return "home/hrzgl";
	}
	//能耗分析
	@RequestMapping("nhfx")
	public String nhfx() {
		
		return "home/nhfx";
	}

}
