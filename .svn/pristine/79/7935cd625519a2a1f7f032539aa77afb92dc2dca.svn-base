package com.hnzy.hot.xtgl.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.home.pojo.Menu;
import com.hnzy.hot.home.service.MenuService;
import com.hnzy.hot.xtgl.pojo.Role;
import com.hnzy.hot.xtgl.pojo.User;
import com.hnzy.hot.xtgl.service.RoleService;
/**
 * 角色管理控制器
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	private List<Role> roles;

	@RequestMapping("getqx")
	@ResponseBody
	public JSONObject getAmend(Integer id){
		//查询角色目前所用户的菜单
		List<Menu> roleMenu=menuService.findMenuByRId(id);
		//查詢所有的菜單
		List<Menu>menuAll=menuService.find();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("roleMenu", roleMenu);
		jsonObject.put("MenuAll",menuAll);
		return jsonObject;
	}
	
	// 修改角色并返回到用户页面
	@RequestMapping(value = "/amend")
	public String editrole(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) String id) {
		String[] roleId = request.getParameterValues("userRoleList");
		menuService.editURole(roleId, id);
		return "redirect:list.action";
	}

	
	//查询角色列表
	@RequestMapping("/list")
	public String list(HttpServletRequest request)
	{
		roles=roleService.findAllRole();
		request.setAttribute("role", roles);
		return "xtgl/user/roleList";
	}
	
	//添加一个角色
		@RequestMapping("/add")
		public String addUser(Role role){
			role.setCreateTime(new Date());
			roleService.save(role);
			return "redirect:list.action";
		}

		//修改一个角色
		@RequestMapping("/edit")
		public String editUser(Role role){
			role.setLastEditTime(new Date());
			roleService.edit(role);
			return "redirect:list.action";
		}
		//删除角色，支持一次删除多个
		@ResponseBody
		@RequestMapping("/delete")
		public void deleteUser(@RequestParam String id){
			roleService.delete(id);
		}
	

		//查询某个角色下的所有用户
		@ResponseBody
		@RequestMapping(value="/findUsers")
		public JSONObject findUsers(String id){
			JSONObject json=new JSONObject();
			List<User> userList=roleService.findUsers(id);
			json.put("userList", userList);
			return json;
		}
	@RequestMapping("getAmend")
    public JSONObject getAmend(){
		return null;
	  
     }
	
	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	
}
