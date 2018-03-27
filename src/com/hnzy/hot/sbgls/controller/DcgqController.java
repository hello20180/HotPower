package com.hnzy.hot.sbgls.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.sbgls.pojo.Dcgq;
import com.hnzy.hot.sbgls.service.DcgqService;

@Controller
@RequestMapping("dcgqCon")
public class DcgqController
{
	@Autowired
	private DcgqService  dcgqService;
	private List<Dcgq> listDcgq;
	
	@RequestMapping("findDcgq")
	private String findDcgq(HttpServletRequest request){
		listDcgq=dcgqService.find();
		request.setAttribute("dcgq", listDcgq);
		return "sbgls/dcgq";
	}
	@RequestMapping("delete")
	@ResponseBody
	private String  delete(int ids[]){
		for(int i=0;i<ids.length;i++){
			dcgqService.delete(ids[i]);
		}
		return "success";
	}
	public List<Dcgq> getListDcgq()
	{
		return listDcgq;
	}

	public void setListDcgq(List<Dcgq> listDcgq)
	{
		this.listDcgq = listDcgq;
	}
	

}
