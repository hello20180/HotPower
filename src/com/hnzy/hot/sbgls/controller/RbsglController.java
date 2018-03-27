package com.hnzy.hot.sbgls.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.service.RbsglService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtils;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;


@Controller
@RequestMapping("RbsglController")
public class RbsglController {

	private List<Yh> yhInfoList;
	@Autowired
	private RbsglService rbsglService;
	@Autowired
	private RzService rzService;
	private static Log log = LogFactory.getLog(FmsglController.class);
	String param = null;
	@RequestMapping("searchInfo")
	@ResponseBody
	public JSONObject searchInfo(HttpServletRequest request, ModelMap map, @Param("xqName") String xqName,
			@Param("buildNo") int buildNo, @Param("cellNo") int cellNo, @Param("houseNo") Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2)
			throws UnsupportedEncodingException {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		request.setAttribute("houseNo", houseNo);
		request.setAttribute("xqName", xqName);
		request.setAttribute("buildNo", buildNo);
		request.setAttribute("cellNo", cellNo);
		JSONObject jsonObject = new JSONObject();
		if (houseNo == null) {
			houseNo = 0;
			jsonObject.put("findList", rbsglService.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2));
		}else{
			jsonObject.put("findList", rbsglService.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2));
		}
		// 小区名称
		yhInfoList = rbsglService.findYhNameList();
		// 列表
		return jsonObject;
	}
	
	
	@RequestMapping("searchRb")
	@ResponseBody
	public JSONObject searchRb(HttpServletRequest request, ModelMap map, @Param("xqName") String xqName,
			@Param("buildNo") int buildNo, @Param("cellNo") int cellNo, @Param("houseNo") Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2)
			throws UnsupportedEncodingException {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		request.setAttribute("houseNo", houseNo);
		request.setAttribute("xqName", xqName);
		request.setAttribute("buildNo", buildNo);
		request.setAttribute("cellNo", cellNo);
		JSONObject jsonObject = new JSONObject();
		if (houseNo == null) {
			houseNo = 0;
			jsonObject.put("findList", rbsglService.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2));
		}else{
			jsonObject.put("findList", rbsglService.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2));
		}
		// 小区名称
		yhInfoList = rbsglService.findYhNameList();
		// 列表
		return jsonObject;
	}
	@RequestMapping("RbdoExportExcel")
	@ResponseBody
	public JSONObject RbdoExportExcel(HttpServletRequest request ,HttpServletResponse response, ModelMap map, @Param("xqName") String xqName,
			@Param("buildNo") int buildNo, @Param("cellNo") int cellNo, @Param("houseNo") Integer houseNo,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2)
			throws IOException {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		request.setAttribute("houseNo", houseNo);
		request.setAttribute("xqName", xqName);
		request.setAttribute("buildNo", buildNo);
		request.setAttribute("cellNo", cellNo);
		//告诉浏览器要弹出的文档类型
				response.setContentType("application/x-execl");
				//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
				response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
				//获取输出流
		JSONObject jsonObject = new JSONObject();
		if (houseNo == null) {
			houseNo = 0;
			ServletOutputStream outputStream=response.getOutputStream();
			rbsglService.exportExcel(rbsglService.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2), outputStream);
			if(outputStream!=null){
				outputStream.close();
			}	
		}else{
			ServletOutputStream outputStream=response.getOutputStream();
			rbsglService.exportExcel(rbsglService.searchInfo(xqName, buildNo, cellNo, houseNo, recordTime1, recordTime2), outputStream);
			if(outputStream!=null){
				outputStream.close();
			}
		}
		// 小区名称
		yhInfoList = rbsglService.findYhNameList();
		// 列表
		return jsonObject;
	}
	// 数据报表
	@RequestMapping("sbSjbb")
	public String sbSjbb(ModelMap map){
		List<Yh> findList = rbsglService.findList();
		yhInfoList = rbsglService.findYhNameList();
		map.addAttribute("yhInfoList", yhInfoList);
		map.addAttribute("findList", findList);
		return "sbgls/rbglSjbb";
		
		
	}
	
	// 获取所有的小区
	@RequestMapping("findYhNameList")
	public String findYhNameList(@RequestParam(value = "pageNum", required = false) String pageNum, ModelMap map) {
		yhInfoList = rbsglService.findYhNameList();
		List<Yh> findList = rbsglService.findList();
		map.addAttribute("yhInfoList", yhInfoList);
		map.addAttribute("findList", findList);
		return "sbgls/rbgl";
	}

	// 根据小区获取楼栋号
	@RequestMapping("findYhBuildNObyXqName")
	@ResponseBody
	public JSONObject findYhBuildNObyXqName(String xqName) throws UnsupportedEncodingException {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoList = rbsglService.findYhBuildNObyXqName(xqName);
		JSONObject jsonObject = new JSONObject();
		if (yhInfoList != null) {
			jsonObject.put("xqlist", yhInfoList);
		} else {
			jsonObject.put("fail", null);
		}
		return jsonObject;
	}

	// 根据小 区楼栋号获取单元号
	@RequestMapping("findYhCellNOByBuild")
	@ResponseBody
	public JSONObject findYhCellNOByBuild(@Param("build") int build, @Param("xqName") String xqName)
			throws UnsupportedEncodingException {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		yhInfoList = rbsglService.findYhCellNOByBuild(xqName, build);
		JSONObject jsonObject = new JSONObject();
		if (yhInfoList != null) {
			jsonObject.put("cellList", yhInfoList);
		} else {
			jsonObject.put("fail", null);
		}
		return jsonObject;

	}
	// 读热表
		@RequestMapping("rebInfo")
		@ResponseBody
		public JSONObject Crb(YhInfo yhInfo ,HttpSession session,Yh yh, String ids) {
			String qgId = null;
			String param = ""+ids+"";
			MapUtilsDf.getMapUtils().add("dRbParam", param);
			yh =rbsglService.SelRb(ids);
			qgId = yh.getFm().getQgID();
			JSONObject jsonObject=new JSONObject();
	    	String xqName = yh.getXqName();
	    	Integer BuildNo = yh.getBuildNO();
	    	Integer CellNo = yh.getCellNO();
	    	Integer HouseNo = yh.getHouseNO();
	    	//日志
			Rz rz=new Rz();
			rz.setCz("读取热表,热表地址为:"+ids+"用户地址:"+xqName+"-"+BuildNo+"-"+CellNo+"-"+HouseNo);
			rz.setCzr((String)session.getAttribute("userName"));
			rz.setCzsj(new Date());
			rzService.insert(rz);
				// 区管ID
				String ip = yh.getFm().getQg().getJzq().getJzqIp();
				// 端口号
				int port = yh.getFm().getQg().getJzq().getJzqPort();
				// IP地址和端口号
				String pt = "/" + ip + ":" + port;
				// fmId十进制
				String ja = "F0160200" + qgId +"FFFFF"+ids.trim()+"0103901F0B";
				log.info("读热表发送数据："+ja);
				boolean sessionmap = cz(ja, pt);
				try {
    				Thread.sleep(3000);

    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
				if (sessionmap == true &&  MapUtils.getMapUtils().get("Crb")!=null && MapUtils.getMapUtils().get("Crb").equals("success")) {
					MapUtils.getMapUtils().add("Crb", null);
					jsonObject.put("js", "0");
					jsonObject.put("rbId", "小区:" + xqName + "楼栋号:" + BuildNo + "单元号:" + CellNo + "户号:" + HouseNo);
					return jsonObject;
				} else if (sessionmap == true &&  MapUtils.getMapUtils().get("Crb")!=null && MapUtils.getMapUtils().get("Crb").equals("fail")) {
					MapUtils.getMapUtils().add("Crb", null);
					jsonObject.put("js", "2");
					jsonObject.put("rbId", "小区:" + xqName + "楼栋:" + BuildNo + "单元:" + CellNo + "户号:" + HouseNo);
					return jsonObject;
				} else if (sessionmap == true && MapUtils.getMapUtils().get("Crb")!=null  && MapUtils.getMapUtils().get("Crb").equals("cs")) {
					MapUtils.getMapUtils().add("Crb", null);
					jsonObject.put("js", "3");
					jsonObject.put("rbId", "小区:" + xqName + "楼栋:" + BuildNo + "单元:" + CellNo + "户号:" + HouseNo);
					return jsonObject;
				} else if (sessionmap == false && MapUtils.getMapUtils().get("Crb") == null) {
					MapUtils.getMapUtils().add("Crb", null);
					jsonObject.put("js", "4");
					jsonObject.put("rbId", "小区:" + xqName + "楼栋:" + BuildNo + "单元:" + CellNo + "户号:" + HouseNo);
					log.info("发送数据失败,集中器不在线");
					return jsonObject;
				} else {
					MapUtils.getMapUtils().add("Crb", null);
					jsonObject.put("js", "1");
					jsonObject.put("rbId", "小区:" + xqName + "楼栋:" + BuildNo + "单元:" + CellNo + "户号:" + HouseNo);
					return jsonObject;
			   }
			}
		// 抽取相同部分
		public boolean cz(String ja, String pt) {

			// 把十六进制数，转换为十进制相加
			int jia = CzUtil.FsZh(ja);
			// 十进制转换为十六进制
			String hex = Integer.toHexString(jia);
			// 截取相加结果后两位
			String je = null;
			for (int j = 0; j < hex.length() - 1; j++) {
				je = hex.charAt(hex.length() - 2) + "" + hex.charAt(hex.length() - 1);
			}
			String[] keys = new String[] { pt };
			String mString = ja + je + "FF";
			// 解码
			byte[] b = CzUtil.jm(mString);
			ServerSessionMap sessionMap = ServerSessionMap.getInstance();
			boolean sessionmap = sessionMap.sendMessage(keys, b);
			return sessionmap;
		}

	public List<Yh> getYhInfoList() {
		return yhInfoList;
	}

	public void setYhInfoList(List<Yh> yhInfoList) {
		this.yhInfoList = yhInfoList;
	}
}
