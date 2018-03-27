package com.hnzy.hot.tjfx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.service.RbsglService;
import com.hnzy.hot.sjbb.pojo.YhInfo;

@Controller
@RequestMapping("RbEptionCon")
public class RbEptionController
{
	@Autowired
	private RbsglService rbsglService;
	@RequestMapping("rebInfo")
	public String rebInfo(ModelMap map,Double gs,Double hs,Double wc){
//		List<Yh> findList = rbsglService.findList();
		List<Yh> findLists=rbsglService.searEp(gs, hs, wc);
		map.addAttribute("findList", findLists);
		return "tjfx/rbgl";
	}
	
	@ResponseBody
	@RequestMapping("SearebInfo")
	public JSONObject SearebInfo(Double gs,Double hs,Double wc){
		JSONObject jsonObject=new JSONObject();
//		List<Yh> findList = rbsglService.findList();
		List<Yh> findLists=rbsglService.searEp(gs, hs, wc);
		jsonObject.put("findList", findLists);
		return jsonObject;
	}
	
	//导出
	@RequestMapping("RbExportExcel")
	public void  RbExportExcel(YhInfo yhInfo,HttpSession session,HttpServletResponse response,Double gs,Double hs,Double wc) throws IOException{
		//告诉浏览器要弹出的文档类型
		response.setContentType("application/x-execl");
		//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
		response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
		//获取输出流

				ServletOutputStream outputStream=response.getOutputStream();
				rbsglService.exportExcel(rbsglService.searEp(gs, hs, wc), outputStream);
	}

	
}
