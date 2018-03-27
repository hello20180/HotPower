package com.hnzy.hot.xtgl.controller;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.home.pojo.Menu;
import com.hnzy.hot.home.service.MenuService;
import com.hnzy.hot.util.MD5Util;
import com.hnzy.hot.xtgl.pojo.Role;
import com.hnzy.hot.xtgl.pojo.User;
import com.hnzy.hot.xtgl.service.RoleService;
import com.hnzy.hot.xtgl.service.UserService;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("ruser")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RzService rzService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	private List<User> userlist;
		//获取用户角色，与角色列表
		@ResponseBody
		@RequestMapping("getAmend")
		public JSONObject getAmend(String id){
			//查询用户角色
			List<Role> userRole=roleService.findRoleId(id);
			//查询所有角色
			List<Role> roleAll=roleService.findAllRole();
			//定义一个map集合，并添加数据
			HashMap<String,String> map=new HashMap<String,String>();
			if(userRole==null){
				for(Role role:roleAll){
					map.put(role.getId().toString(),"0");
				}
			}else{
				for(Role role:roleAll){
					for(Role uRole: userRole){
						if(uRole.getId().equals(role.getId())){
							map.put(role.getId().toString(),uRole.getId().toString());
							break;
						}else{
							map.put(role.getId().toString(),"0");
						}
					}
					
				}
			}
			//返回JSON数据
			JSONObject json=new JSONObject();
			json.put("map", map);
			json.put("roles",roleAll);
			return json;
		}
		
		//修改角色并返回到用户页面
		@RequestMapping("amend")
		public String editrole(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="id",required=false)String id){
			String[] roleId= request.getParameterValues("userRoleList");
			roleService.editURole(roleId, id);
			return "redirect:Userlist.action"; 
		}
	
	/**
	 * 判断名字是否重复
	 */
	@RequestMapping("addCheck")
	@ResponseBody
	public JSONObject addCheck(String userName){
		JSONObject json = new JSONObject();
		int i = userService.addCheck(userName);
		json.put("checkName", i);
		return json;
		
	}
	//获取所有的角色
	@ResponseBody
	@RequestMapping("getRole")
	public JSONObject getRole(){
		JSONObject json = new JSONObject();
		List<Role> list = roleService.findAllRole();
		json.put("role", list);
		return json;
	}
	//添加用戶信息
	@RequestMapping(value="InsertUser" , method=RequestMethod.POST)
	public String  InsertUser( HttpSession session ,User user ){
		if(user!=null&&user.getUserName()!=null&&user.getUserName()!=""&&user.getPassWord()!=null&&user.getPassWord()!=""){
			user.setPassWord(MD5Util.string2MD5(user.getPassWord()));
			user.setTlz(0);
			userService.InsertUser(user);
			//日志
			Rz rz=new Rz();
			rz.setCz("添加用户名:"+user.getUserName());
			rz.setCzr((String)session.getAttribute("userName"));
			rz.setCzsj(new Date());
			rzService.insert(rz);
			return "redirect:Userlist.action";
		}

		return "redirect:Userlist.action";
	}
	public void   Insert(User user ){
		
			userService.InsertUser(user);
		
	}
	public void ins(String message){
		HttpServletRequest request = null;
		request.setAttribute("me", message);
		User user =new User();
		user.setId(4);
		user.setUserName("张三");
		userService.InsertUser(user);
	}
	
	//查找用戶信息
	@RequestMapping("Userlist")
	public String list(HttpServletRequest request){		
		 userlist=userService.findUser();
		 request.setAttribute("userList",userlist);
		return "xtgl/user/UserList";
	}

	//查找用戶信息
	public List<User> ulist(){	
	
		 userlist=userService.findUser();
		 
		 return userlist;
	}
	//刪除用戶信息
	@RequestMapping("delUser")
	@ResponseBody
	public void deleteUser(HttpSession session,@RequestParam("id")String id){
		userService.deleteUser(id);
		//日志
		Rz rz =new Rz();
		rz.setCz("删除用户");
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
	}

	//更新用戶信息
	@RequestMapping("updateUse")
	public String updateUse(HttpSession session ,User user){
		user.setPassWord(MD5Util.string2MD5(user.getPassWord()));
		userService.updateUse(user);
		
		//日志
		Rz rz =new Rz();
		rz.setCz("修改用户密码："+user.getPassWord());
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		return "redirect:Userlist.action";
	}
	//登录
	@RequestMapping("login")
	public String tologin(){
		return"home/login";
	}
	
	//登录
	@RequestMapping("loginUser")
	@ResponseBody
	public Map<String, Object>  findNamePass(HttpSession session, HttpServletRequest request , HttpServletResponse response,@Param("userName")String userName,User user ) throws UnsupportedEncodingException {
		userName=new String(userName.getBytes("ISO-8859-1"),"utf-8")+"";
		Map<String,Object> map=new HashMap<String,Object>();
		user.setPassWord(MD5Util.string2MD5(user.getPassWord()));
		String passWord=user.getPassWord();
		
		if(userService.findNamePass(userName, passWord)){
			//查询用户角色
			List<Role> userRole=roleService.findRoleByUId(userName);
			//user.setRoles(userRole);
			List<Menu> rolemenuid=menuService.findMenuByRId(userRole.get(0).getId());
			user.setMenus(rolemenuid); 
			session.setAttribute("usermenu", user);
			session.setAttribute("userName",userName);
//			Cookie cookie=new Cookie("userName", userName);
//			Cookie cookie2=new Cookie("passWord",passWord);
//			cookie.setPath("/");
//			cookie2.setPath("/");
//			cookie.setMaxAge(608400);
//			cookie2.setMaxAge(608400);
//			response.addCookie(cookie);
//			response.addCookie(cookie2);
			map.put("msg",1);
		}else{
			map.put("msg",0);
		}

		return map;
	}

	/**
	    * 退出
	    * @param req
	    * @param res
	    * @return
	    */
	   @RequestMapping(value="loginOut")
	   public String loginOut(HttpServletRequest req,HttpServletResponse res){
			try{
		        Enumeration<String> em = req.getSession().getAttributeNames();
		        while (em.hasMoreElements()) {
		        	req.getSession().removeAttribute(em.nextElement().toString());
		        }
		        req.getSession().removeAttribute("usermenu");
		        req.getSession().invalidate();
			}catch(Exception e){
				e.getCause();
			}
			return "home/login";
	   }
	
	
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
   
}
