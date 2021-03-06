package com.hnzy.hot.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.hnzy.hot.home.pojo.Menu;
import com.hnzy.hot.home.service.MenuService;
@Controller
@RequestMapping("xtgllist")
public class MenuController  {
	
@Autowired
private MenuService menuService;
public List<Menu> menus ;

//左邊菜單
@RequestMapping("xtglmenuList")
public ModelAndView  menuList( HttpServletRequest request ,int pid ){
	ModelAndView mv=new ModelAndView();
	mv.setViewName("home/Left");
	List<Menu> menu = menuService.getSec(pid);
	mv.addObject("menus", menu);
	return mv;	
}
@RequestMapping("mu")
public String mu(){
	return "home/mu";
}
@ResponseBody
@RequestMapping("/delete")
public void delete(int id){
	menuService.delete(id);
}
@RequestMapping("/update")
public String update(Menu menu){
	menuService.update(menu);
	return "redirect:menulist.action";
}
//查询所有的菜单
@RequestMapping("/menulist")
public ModelAndView find(){
	menus=menuService.find();
	ModelAndView mView=new ModelAndView();
	mView.setViewName("xtgl/user/menuList");
	mView.addObject("menus",menus);
	return mView;
}
@RequestMapping("/xxglFind")
public ModelAndView xxglFind(){
	menus=menuService.xxglFind();
	ModelAndView modelAndView=new ModelAndView();
	modelAndView.setViewName("home/muxxgl");
	modelAndView.addObject("menus",menus);
	return 	modelAndView;
}
@RequestMapping("/save")
public String save(Menu menu){
	menuService.save(menu);
	return "redirect:menulist.action";
}
public List<Menu> getMenus() {
	return menus;
}
public void setMenus(List<Menu> menus) {
	this.menus = menus;
}
}
