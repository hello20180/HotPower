package com.hnzy.hot.xxgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.FmHistoryService;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.xxgl.pojo.GZgl;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;
import com.hnzy.hot.xxgl.service.YZglService;

@Controller
@RequestMapping("yzglController")
public class YZglController
{
	@Autowired
	private YZglService yzglservice;
	@Autowired
	private YhInfoService yhInfoService;
	private List<YhInfo> yhInfoList;
	@Autowired
	private RzService rzService;
	@Autowired
	private FmHistoryService fmHistoryService;
	private List<Rz> rzs;

	//搜索用户类别
	@RequestMapping("yhflSear")
	public String yhflSear(ModelMap map,HttpServletRequest request,String yhfl) throws UnsupportedEncodingException{
		if(yhfl!=null){
		yhfl = new String(yhfl.getBytes("ISO-8859-1"), "utf-8") + "";
		}
		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		map.addAttribute("yhInfoList",yhInfoList);
		yhInfoList=yzglservice.yhflSear(yhfl);
		request.setAttribute("yhgl", yhInfoList);
		return "xxgl/yhgl/yzgl";
	}
	
	// 查找所有
	@RequestMapping("findYhInfo")
	public String findYhInfo(ModelMap map,HttpServletRequest request)
	{

		yhInfoList=yhInfoService.findYhNameList();
		request.setAttribute("findXqInfoFmHistory", yhInfoService.findXqInfoFmHistory());
		request.setAttribute("fmHistoriesStatus", fmHistoryService.fmHistoriesStatus());
		map.addAttribute("yhInfoList",yhInfoList);
		List<YhInfo> yhInfoList = yzglservice.findYhInfo();
		request.setAttribute("yhgl", yhInfoList);
		return "xxgl/yhgl/yzgl";
	}

	// 更新
	@RequestMapping("update")
	public String update(HttpSession session, YhInfo yhInfo, Rz rz)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("修改" + yhInfo.getXqName() + "小区" + yhInfo.getBuildNo() + "楼" + yhInfo.getCellNo() + "单元"
				+ yhInfo.getHouseNo() + "户号信息");// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		// 修改业主信息
		yzglservice.update(yhInfo);
		return "redirect:findYhInfo.action";

	}

	// 插入
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String Insert(HttpSession session, YhInfo yhInfo, Rz rz)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("添加" + yhInfo.getXqName() + "小区" + yhInfo.getBuildNo() + "楼" + yhInfo.getCellNo() + "单元"
				+ yhInfo.getHouseNo() + "户号信息");// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		// 添加业主信息
		yzglservice.Insert(yhInfo);
		return "redirect:findYhInfo.action";
	}

	// 删除
	@RequestMapping("delete")
	@ResponseBody
	public void delete(HttpSession session, YhInfo yhInfo, Rz rz,@RequestParam("id")String id)
	{
		// 向日志表添加操做
		rz.setCzr((String) session.getAttribute("userName"));// 获取操作人
		rz.setCz("删除业主信息");// 获取操作内容
		rz.setCzsj(new Date());// 获取操作时间
		rzService.insert(rz);
		// 删除业主信息
		yzglservice.delete(id);
	}

	// 获取所有的小区
	@RequestMapping("findYhNameList")
	@ResponseBody
	public JSONObject findYhNameList(YhInfo yhInfo)
	{
		yhInfoList = yhInfoService.findYhNameList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("yhInfolist", yhInfoList);
		return jsonObject;
	}

	// 根据小区获取楼栋号
	@RequestMapping("findYhBuildNObyXqName")
	@ResponseBody
	public JSONObject findYhBuildNObyXqName(String xqName) throws UnsupportedEncodingException
	{
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoList = yhInfoService.findYhBuildNObyXqName(xqName);
		JSONObject jsonObject = new JSONObject();
		if (yhInfoList != null)
		{
			jsonObject.put("xqlist", yhInfoList);
		} else
		{
			jsonObject.put("fail", null);
		}
		return jsonObject;
	}

	// 根据小区楼栋号获取单元号
	@RequestMapping("findYhCellNOByBuild")
	@ResponseBody
	public JSONObject findYhCellNOByBuild(@Param("build") int build, @Param("xqName") String xqName)
			throws UnsupportedEncodingException
	{
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoList = yhInfoService.findYhCellNOByBuild(build, xqName);
		JSONObject jsonObject = new JSONObject();
		if (yhInfoList != null)
		{
			jsonObject.put("cellList", yhInfoList);
		} else
		{
			jsonObject.put("fail", null);
		}
		return jsonObject;

	}
	
	
	@ResponseBody
	@RequestMapping("Search")
	public JSONObject Search(HttpServletRequest request,@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")Integer houseNo) throws UnsupportedEncodingException{
		xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		List<YhInfo> YhList = yhInfoService.Search(xqName, buildNo, cellNo, houseNo);
		jsonObject.put("YhList", YhList);
		return jsonObject;
	}
	
	public List<YhInfo> getYhInfoList()
	{
		return yhInfoList;
	}

	public void setYhInfoList(List<YhInfo> yhInfoList)
	{
		this.yhInfoList = yhInfoList;
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
