package com.hnzy.hot.kfgl.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.kfgl.pojo.Address;
import com.hnzy.hot.kfgl.service.AddressService;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;


@Controller
@RequestMapping("/Address")
public class AddressController
{
	@Autowired 
	private AddressService addressService;
	private List<Address> addressList;
	@Autowired
	private RzService rzService;
	
	private List<Rz> rzs;
	//到添加页面
	@RequestMapping("/list")
	public String list(HttpServletRequest request)
	{
		addressList=addressService.find();
		request.setAttribute("address", addressList);
		return "kfgl/txl/address";
	}
	// 发布公告信息
		@RequestMapping(value = "Insert", method = RequestMethod.POST)
		public String InsertNotice(HttpSession session ,Address address,Rz rz)
		{	//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("添加:"+address.getName());//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			//发布公告信息

			addressService.Insert(address);
			return "redirect:list.action";
		}



		//更新信息
		@RequestMapping("update")
		public String updateNotice(HttpSession session,Address address, Rz rz){
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("修改:"+address.getName());//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
		
			addressService.update(address);
			return "redirect:list.action";
			
		}
		//删除信息
		@RequestMapping("delete")
		@ResponseBody
		public void delete(HttpSession session,Address address,Rz rz,@RequestParam("id")String id){
			//向日志表添加操做
			rz.setCzr((String)session.getAttribute("userName"));//获取操作人
			rz.setCz("删除发布公告信息");//获取操作内容
			rz.setCzsj(new Date());//获取操作时间
			rzService.insert(rz);
			//删除公告
			addressService.delete(id);
			
		}
		public List<Address> getAddressList()
		{
			return addressList;
		}
		public void setAddressList(List<Address> addressList)
		{
			this.addressList = addressList;
		}
		public List<Rz> getRzs()
		{
			return rzs;
		}
		public void setRzs(List<Rz> rzs)
		{
			this.rzs = rzs;
		}
		
		
}
