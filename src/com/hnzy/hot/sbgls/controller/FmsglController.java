package com.hnzy.hot.sbgls.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sbgl.pojo.Fm;
import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgl.service.FmService;
import com.hnzy.hot.sbgls.pojo.Cb;
import com.hnzy.hot.sbgls.service.FmsService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtils;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("/FmglController")
public class FmsglController {
	@Autowired
	private FmsService fmsService;
	@Autowired
	private FmService fmService;
	@Autowired
	private YhInfoService yhInfoService;
	private List<Yh> yhInfoList;
	private List<YhInfo> yhInfoLt;
	@Autowired
	private RzService rzService;
	String param = null;
	String fmId;
	String qgId;
	String xqName;
	Integer BuildNo;
	Integer CellNo;
	Integer HouseNo;
    private static Log log = LogFactory.getLog(FmsglController.class);
    
    
    //修改无线传感器地址
    @RequestMapping("XCgq")
    @ResponseBody
    public String XCgq(YhInfo yhInfo,HttpSession session,Fm f, String fmId, String qgId,String CgqId){
    
    	yhInfo=yhInfoService.findFmId(fmId);
    	xqName=yhInfo.getXqName();
    	BuildNo=yhInfo.getBuildNo();
    	CellNo=yhInfo.getCellNo();
    	HouseNo=yhInfo.getHouseNo();
    	//日志
		Rz rz=new Rz();
		rz.setCz("修改无线传感器地址用户地址:"+xqName+"-"+BuildNo+"-"+CellNo+"-"+HouseNo+",阀门地址为"+fmId+"传感器地址修改为:"+CgqId);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		if(session.getAttribute("userName").equals("供热管理处")){
			return"5";
		}else {
    			// 把FmID转换为int类型
    			int fInteger = Integer.valueOf(fmId);
    			// FmID十进制转换为十六进制
    			String fmd = Integer.toHexString(fInteger);
    			String UppFmd = CzUtil.Uppercase(fmd).toString();
    			//传感器地址转换为int
    			int cgqId=Integer.valueOf(CgqId);
    			//串改暖气十进制转换为十六进制
    			String Cgq=Integer.toHexString(cgqId);
    			// 阀门ID
    			Fm fm = fmService.findJzq(fmId);
    			// 区管ID
    			String ip = fm.getQg().getJzq().getJzqIp();
    			// 端口号
    			int port = fm.getQg().getJzq().getJzqPort();
    			// IP地址和端口号
    			String pt = "/" + ip + ":" + port;

    			// fmId十进制
    			String ja = "F0155300"+qgId+"0"+UppFmd+"679A000"+Cgq+"";
    			log.info("修改传感器发送数据："+ja);
    			boolean sessionmap = cz(ja, pt);
    			try {
    				Thread.sleep(3000);

    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			if (sessionmap == true &&  MapUtils.getMapUtils().get("Xcgq")!=null && MapUtils.getMapUtils().get("Xcgq").equals("success")) {
    				MapUtils.getMapUtils().add("Xcgq", null);

    				return "0";
    			} else if (sessionmap == true && MapUtils.getMapUtils().get("Xcgq")!=null&& MapUtils.getMapUtils().get("Xcgq").equals( "fail")
    					&& MapUtils.getMapUtils().get("Xcgq") != null) {
    				MapUtils.getMapUtils().add("Xcgq", null);
    				return "2";
    			} else if (sessionmap == true &&  MapUtils.getMapUtils().get("Xcgq")!=null && MapUtils.getMapUtils().get("Xcgq").equals("cs")
    					|| sessionmap == true && MapUtils.getMapUtils().get("Xcgq") == null) {
    				MapUtils.getMapUtils().add("Xcgq", null);
    				return "3";
    			} else if (sessionmap == false && MapUtils.getMapUtils().get("Xcgq") == null) {
    				log.info("发送数据失败,集中器不在线");
    				return "4";
    			} else {
    				MapUtils.getMapUtils().add("Xcgq", null);
    				return "1";
    			}
		}
    }
    
    
	// 读阀
	@ResponseBody
	@RequestMapping("dFm")
	public JSONObject duFm(YhInfo yhInfo,HttpSession session ,HttpServletRequest request, Fm f, String ids, Fm fm) {
		String param = ""+ids+"";
		MapUtilsDf.getMapUtils().add("dFmParam", param);
		yhInfo=yhInfoService.findFmId(ids);
    	xqName=yhInfo.getXqName();
    	BuildNo=yhInfo.getBuildNo();
    	CellNo=yhInfo.getCellNo();
    	HouseNo=yhInfo.getHouseNo();
    	//根据阀门获取用户信息
		fm = fmService.findbyVad(ids);
		int buildNo = fm.getYh().getBuildNO();
		int cellNo = fm.getYh().getCellNO();
		int houseNo = fm.getYh().getHouseNO();
		f = fmService.findIDbyqgvd(ids);
		fmId = f.getValAd();
		qgId = f.getQgID();
		// 把FmID转换为int类型
		int fInteger = Integer.valueOf(fmId);
		// FmID十进制转换为十六进制
		String fmd = Integer.toHexString(fInteger);
		// 阀门ID
		fm = fmService.findJzq(fmId);
		// 区管ID
		String ip = fm.getQg().getJzq().getJzqIp();
		// 端口号
		int port = fm.getQg().getJzq().getJzqPort();
		// // IP地址和端口号
		String pt = "/" + ip + ":" + port;
		// fmId十进制
		String ja = "F00F0400" + qgId + "040" + fmd;
		log.info("读阀发送数据："+ja);
		boolean sessionmap = cz(ja, pt);
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		if (sessionmap == true && MapUtilsDf.getMapUtils().get("dFm")!=null && MapUtilsDf.getMapUtils().get("dFm").equals("success")) {
			MapUtilsDf.getMapUtils().add("dFm", null);
			jsonObject.put("js", "0");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		} else if (sessionmap == true && MapUtilsDf.getMapUtils().get("dFm")!=null && MapUtilsDf.getMapUtils().get("dFm").equals("超时") 
				|| sessionmap == true && MapUtilsDf.getMapUtils().get("dFm") == null) {
			MapUtilsDf.getMapUtils().add("dFm", null);
			jsonObject.put("js", "1");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		} else if (sessionmap == false && MapUtilsDf.getMapUtils().get("dFm") == null) {
			log.info("发送数据失败,集中器不在线");
			MapUtilsDf.getMapUtils().add("dFm", null);
			jsonObject.put("js", "3");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		}else {
			MapUtilsDf.getMapUtils().add("dFm", null);
			jsonObject.put("js", "2");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		}

	}

	// 开阀门
	@RequestMapping("kFm")
	@ResponseBody
	public JSONObject KFm(YhInfo yhInfo ,HttpSession session,Fm fm, String ids , String  fmkd) {
		JSONObject kString = null;
		int fmkds=0;
		int FmKd=0;
		fm = fmService.findIDbyqgvd(ids);
		//更新控制人员开
		fmsService.updateType("操控开",fmId);
		fmId = fm.getValAd();
		qgId = fm.getQgID();
		if(fmkd.equals("")){
			fmkds=255;
		}else{
			 fmkds=Integer.valueOf(fmkd);
		}
		//日志
		Rz rz=new Rz();
		if(fmkds==255){
		   FmKd=100;
		}else{
			FmKd=fmkds;
		}
		JSONObject jsonObject=new JSONObject();
		yhInfo=yhInfoService.findFmId(ids);
    	xqName=yhInfo.getXqName();
    	BuildNo=yhInfo.getBuildNo();
    	CellNo=yhInfo.getCellNo();
    	HouseNo=yhInfo.getHouseNo();
		rz.setCz("阀门打开,阀门地址为:"+ids+"用户地址:"+xqName+"-"+BuildNo+"-"+CellNo+"-"+HouseNo+"阀门开度"+FmKd);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		if(session.getAttribute("userName").equals("供热管理处")){
			jsonObject.put("js", "5");
			return jsonObject;
		}else{
		
		kString = kfm(fm, fmId, qgId,fmkds);
		return kString;
		}

	}
	String UppWdsd;
	String UppTjzq;
	String UppTjcs;
	// 发送参数
	@RequestMapping("FsCs")
	@ResponseBody
	public String FsCs(YhInfo yhInfo,HttpSession session,Fm f, String fmId, String qgId, String wdsd, String tjzq, String tjcs,String sdbs) {
		yhInfo=yhInfoService.findFmId(fmId);
    	xqName=yhInfo.getXqName();
    	BuildNo=yhInfo.getBuildNo();
    	CellNo=yhInfo.getCellNo();
    	HouseNo=yhInfo.getHouseNo();
		//日志
		Rz rz=new Rz();
		rz.setCz("发送参数,阀门地址为:"+fmId+"用户地址:"+xqName+"-"+BuildNo+"-"+CellNo+"-"+HouseNo+"调节周期："+wdsd+"室温设定："+tjzq+"调节参数："+tjcs);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		if(session.getAttribute("userName").equals("供热管理处")){
			return "5";
		}else {
		if(wdsd!=""&& tjzq!=""&&tjcs!=""){
		// 参数十进制转换为十六进制
		int WDsd=Integer.valueOf(wdsd);
		int TJzq=Integer.valueOf(tjzq);
		int TJcs=Integer.valueOf(tjcs);
		String Wdsd = "00" + Integer.toHexString(WDsd);
		String subwdsd = Wdsd.substring(Wdsd.length() - 2);
		String Tjzq = "00" + Integer.toHexString(TJzq);
		String subtjzq = Tjzq.substring(Tjzq.length() - 2);
		String Tjcs = "00" + Integer.toHexString(TJcs);
		String subtjcs = Tjcs.substring(Tjcs.length() - 2);
		 UppWdsd = CzUtil.Uppercase(subwdsd).toString();
		 UppTjzq = CzUtil.Uppercase(subtjzq).toString();
		 UppTjcs = CzUtil.Uppercase(subtjcs).toString();
		}else{
			UppWdsd="FF";
			UppTjzq="FF";
			UppTjcs="FF";
		}
		param = "FsCs";
		MapUtils.getMapUtils().add("param", param);
		// 根据fmID查找阀门状态
//		f = fmService.findIDbyqgvd(fmId);
//		String status = f.getStatus();
//		String sts = null;
//		if (status.equals("开")) {
//			sts = "01";
//		} else {
//			sts = "00";
//		}
		if(sdbs.equals("02")){
			sdbs="FF";
		}
		// 把FmID转换为int类型
		int fInteger = Integer.valueOf(fmId);
		// FmID十进制转换为十六进制
		String fmd = Integer.toHexString(fInteger);
		String UppFmd = CzUtil.Uppercase(fmd).toString();
		// 阀门ID
		Fm fm = fmService.findJzq(fmId);
		// 区管ID
		String ip = fm.getQg().getJzq().getJzqIp();
		// 端口号
		int port = fm.getQg().getJzq().getJzqPort();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port;

		// fmId十进制
		String ja = "F0160900" + qgId + "040" + UppFmd + "FFFF"+sdbs+"FF" + UppWdsd + "" + UppTjzq + "" + UppTjcs
				+ "";
		log.info("发送参数发送数据："+ja);
		boolean sessionmap = cz(ja, pt);
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (sessionmap == true &&  MapUtils.getMapUtils().get("FsCs")!=null && MapUtils.getMapUtils().get("FsCs").equals("success")) {
			MapUtils.getMapUtils().add("FsCs", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "0";
		} else if (sessionmap == true && MapUtils.getMapUtils().get("sb")!=null&& MapUtils.getMapUtils().get("sb").equals( "fail")
				&& MapUtils.getMapUtils().get("FsCs") != null) {
			MapUtils.getMapUtils().add("FsCs", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "2";
		} else if (sessionmap == true &&  MapUtils.getMapUtils().get("cs")!=null && MapUtils.getMapUtils().get("cs").equals("超时")
				|| sessionmap == true && MapUtils.getMapUtils().get("FsCs") == null) {
			MapUtils.getMapUtils().add("FsCs", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "3";
		} else if (sessionmap == false && MapUtils.getMapUtils().get("FsCs") == null) {
			log.info("发送数据失败,集中器不在线");
			return "4";
		} else {
			MapUtils.getMapUtils().add("FsCs", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "1";
		}
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

	public JSONObject kfm(Fm fm, String fmId, String qgId,int fmkd) {
		param = "kFm";
		String FsFmkd ;
		MapUtils.getMapUtils().add("param", param);
		MapUtils.getMapUtils().add("kfSuc", fmId);
		// 根据阀门查找信息
		fm = fmService.findbyVad(fmId);
		//更新控制人员开
		fmsService.updateType("操控开",fmId);
		int buildNo = fm.getYh().getBuildNO();
		int cellNo = fm.getYh().getCellNO();
		int houseNo = fm.getYh().getHouseNO();
		// 把FmID转换为int类型
		int fInteger = Integer.valueOf(fmId);
		// FmID十进制转换为十六进制
		String fmd = Integer.toHexString(fInteger);
		// FmID十进制转换为十六进制
		 FsFmkd = Integer.toHexString(fmkd);
		if(FsFmkd.length()==1){
			FsFmkd="0"+FsFmkd;
			}
		// 阀门ID
		fm = fmService.findJzq(fmId);
		// 区管ID
		String ip = fm.getQg().getJzq().getJzqIp();
		// 端口号
		int port = fm.getQg().getJzq().getJzqPort();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port;
		// fmId十进制
		String ja = "F0160900" + qgId + "040" + fmd + "01"+FsFmkd+"FF01FFFFFF";
		log.info("开阀发送数据："+ja);
		boolean sessionmap = cz(ja, pt);
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		if (sessionmap == true &&  MapUtils.getMapUtils().get("kFm")!=null && MapUtils.getMapUtils().get("kFm").equals("success")) {
			MapUtils.getMapUtils().add("kFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			jsonObject.put("js", "0");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			
			return jsonObject;
		} else if (sessionmap == true &&  MapUtils.getMapUtils().get("sb")!=null && MapUtils.getMapUtils().get("sb").equals("fail")
				&& MapUtils.getMapUtils().get("kFm") != null) {
			MapUtils.getMapUtils().add("kFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			jsonObject.put("js", "2");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		} else if (sessionmap == true && MapUtils.getMapUtils().get("cs")!=null  && MapUtils.getMapUtils().get("cs").equals("超时")
				|| sessionmap == true && MapUtils.getMapUtils().get("kFm") == null) {
			MapUtils.getMapUtils().add("kFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			jsonObject.put("js", "3");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		} else if (sessionmap == false && MapUtils.getMapUtils().get("kFm") == null) {
			MapUtils.getMapUtils().add("kFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			jsonObject.put("js", "4");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			log.info("发送数据失败,集中器不在线");
			return jsonObject;
		} else {
			MapUtils.getMapUtils().add("kFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			jsonObject.put("js", "1");
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			return jsonObject;
		}
	}

	// 关阀门
	@RequestMapping("gFm")
	@ResponseBody
	public JSONObject gFm(YhInfo yhInfo ,HttpSession session,Fm fm, String ids) {
		yhInfo=yhInfoService.findFmId(ids);
    	xqName=yhInfo.getXqName();
    	BuildNo=yhInfo.getBuildNo();
    	CellNo=yhInfo.getCellNo();
    	HouseNo=yhInfo.getHouseNo();
    	//更新控制人员关
    	fmsService.updateType("操控关",fmId);
		//日志
		Rz rz=new Rz();
		rz.setCz("阀门关闭,阀门地址为:"+ids+"用户地址:"+xqName+"-"+BuildNo+"-"+CellNo+"-"+HouseNo);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		JSONObject jsonObject=new JSONObject();
		JSONObject kString = null;
		if(session.getAttribute("userName").equals("供热管理处")){
			jsonObject.put("js", "5");
			return jsonObject;
		}else {
		fm = fmService.findIDbyqgvd(ids);
		fmId = fm.getValAd();
		qgId = fm.getQgID();
		kString = gf(fm, fmId, qgId);
		return kString;
		}
	}

	public JSONObject gf(Fm fm, String fmId, String qgId) {
		param = "gFm";
		MapUtils.getMapUtils().add("param", param);
		MapUtils.getMapUtils().add("gfSuc", fmId);
		// 根据阀门查找信息
		fm = fmService.findbyVad(fmId);
		int buildNo = fm.getYh().getBuildNO();
		int cellNo = fm.getYh().getCellNO();
		int houseNo = fm.getYh().getHouseNO();
		// 把FmID转换为int类型
		int fInteger = Integer.valueOf(fmId);
		// FmID十进制转换为十六进制
		String fmd = Integer.toHexString(fInteger);
		// 阀门ID
		fm = fmService.findJzq(fmId);
		// 区管ID
		String ip = fm.getQg().getJzq().getJzqIp();
		// 端口号
		int port = fm.getQg().getJzq().getJzqPort();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port;
		// fmId十进制
		String ja = "F0160900" + qgId + "040" + fmd + "00FFFF00FFFFFF";
		log.info("关阀发送数据："+ja);
		boolean sessionmap = cz(ja, pt);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		if (sessionmap == true && MapUtils.getMapUtils().get("gFm")!=null && MapUtils.getMapUtils().get("gFm").equals("success")) {
			MapUtils.getMapUtils().add("gFm", null);
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("sb", null);
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			jsonObject.put("js", "0");
			return jsonObject;
		} else if (sessionmap == true && MapUtils.getMapUtils().get("sb")!=null && MapUtils.getMapUtils().get("sb").equals("fail")) {
			
			MapUtils.getMapUtils().add("gFm", null);
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("sb", null);
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			jsonObject.put("js", "2");
			return jsonObject;
		} else if (sessionmap == true && MapUtils.getMapUtils().get("cs")!=null && MapUtils.getMapUtils().get("cs").equals("超时") 
				|| sessionmap == true && MapUtils.getMapUtils().get("gFm") == null) {
			MapUtils.getMapUtils().add("gFm", null);
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("sb", null);
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			jsonObject.put("js", "3");
			return jsonObject;
		} else if (sessionmap == false && MapUtils.getMapUtils().get("kFm") == null) {
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			jsonObject.put("js", "4");
			MapUtils.getMapUtils().add("gFm", null);
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("sb", null);
			log.info("发送数据失败,集中器不在线");
			return jsonObject;
		} else {
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("gFm", null);
			MapUtils.getMapUtils().add("cs", null);
			jsonObject.put("fmId", "阀门号:" + fmId + "楼栋号:" + buildNo + "单元号:" + cellNo + "户号:" + houseNo);
			jsonObject.put("js", "1");
			return jsonObject;
		}

	}

	// 读传感器地址
	@RequestMapping("cgqads")
	@ResponseBody
	public String cgqads(YhInfo yhInfo,HttpSession session,String fmId, String qgId) {
		yhInfo=yhInfoService.findFmId(fmId);
    	xqName=yhInfo.getXqName();
    	BuildNo=yhInfo.getBuildNo();
    	CellNo=yhInfo.getCellNo();
    	HouseNo=yhInfo.getHouseNo();
		//日志
		Rz rz=new Rz();
		rz.setCz("读传感器地址,阀门地址为:"+fmId+"用户地址:"+xqName+"-"+BuildNo+"-"+CellNo+"-"+HouseNo);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		
		// 把FmID转换为int类型
		int fInteger = Integer.valueOf(fmId);
		// FmID十进制转换为十六进制
		String fmd = Integer.toHexString(fInteger);
		// 阀门ID
		Fm fm = fmService.findJzq(fmId);
		// 区管ID
		String ip = fm.getQg().getJzq().getJzqIp();
		// 端口号
		int port = fm.getQg().getJzq().getJzqPort();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port;
		String ja = "F0115200" + qgId + "0" + fmd + "679A00";
		log.info("读传感器地址发送数据："+ja);
		boolean sessionmap = cz(ja, pt);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		if(sessionmap==true){
			return "0";
		}else{
			return "1";
		}
	}

	// 搜索
		@RequestMapping("searchInfo")
		@ResponseBody
		public JSONObject searchInfo(HttpServletRequest request, ModelMap map, @Param("xqName") String xqName,
				@Param("buildNo") int buildNo, @Param("cellNo") int cellNo, @Param("houseNo") Integer houseNo, @Param("sfjf") String sfjf)
				throws UnsupportedEncodingException {
			xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
			sfjf = new String(sfjf.getBytes("ISO-8859-1"), "utf-8") + "";
			JSONObject jsonObject = new JSONObject();
			if (houseNo == null) {
				houseNo = 0;
				jsonObject.put("findList", fmsService.searchInfo(xqName, buildNo, cellNo, houseNo,sfjf));
			}else{
				jsonObject.put("findList", fmsService.searchInfo(xqName, buildNo, cellNo, houseNo,sfjf));	
			}
			// 小区名称
			yhInfoList = fmsService.findYhNameList();
			// 列表
			
			return jsonObject;
		}
		
		//返回搜索-------------------
				@RequestMapping("searchInfoxq")
				public String searchInfoxq(HttpServletRequest request, ModelMap map, @Param("xqName") String xqName,
						@Param("buildNo") int buildNo, @Param("cellNo") int cellNo, @Param("houseNo") Integer houseNo)
						throws UnsupportedEncodingException {
					xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
					List<Yh>yhInfos =fmsService.searchInfo(xqName, buildNo, cellNo, houseNo,"--选择缴费状态--");
					System.out.println(yhInfos.size());
					if (houseNo == null) {
						houseNo = 0;
						
						map.put("findList", fmsService.searchInfo(xqName, buildNo, cellNo, houseNo,"--选择缴费状态--"));
					}else{
						map.put("findList", fmsService.searchInfo(xqName, buildNo, cellNo, houseNo,"--选择缴费状态--"));	
					}
					// 小区名称
					yhInfoList = fmsService.findYhNameList();
					map.put("yhInfoList", yhInfoList);
					return "sbgls/fmgl";
				}
				
				
		// 获取所有的小区
		@RequestMapping("findYhNameList")
		public String findYhNameList(@RequestParam(value = "pageNum", required = false) String pageNum, ModelMap map) {
			yhInfoList = fmsService.findYhNameList();
			List<Yh> findList = fmsService.findList();
			map.addAttribute("yhInfoList", yhInfoList);
			map.addAttribute("findList", findList);
			return "sbgls/fmgl";
		}

		// 根据小区获取楼栋号
		@RequestMapping("findYhBuildNObyXqName")
		@ResponseBody
		public JSONObject findYhBuildNObyXqName(String xqName) throws UnsupportedEncodingException {
			xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
			yhInfoList = fmsService.findYhBuildNObyXqName(xqName);
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
			yhInfoList = fmsService.findYhCellNOByBuild(xqName, build);
			JSONObject jsonObject = new JSONObject();
			if (yhInfoList != null) {
				jsonObject.put("cellList", yhInfoList);
			} else {
				jsonObject.put("fail", null);
			}
			return jsonObject;

		}
		
		@RequestMapping("findFmId")
		@ResponseBody
        public JSONObject findFmId(Cb cb){
			JSONObject jsonObject = new JSONObject();
        	cb=fmsService.findFmId();
        	jsonObject.put("cb", cb.getFmId());
			return jsonObject;
        }
	
		@RequestMapping("yhflSear")
		@ResponseBody
		public JSONObject yhflSear(HttpServletRequest request,String yhfl ) throws UnsupportedEncodingException{
			if(yhfl!=null){
				yhfl = new String(yhfl.getBytes("ISO-8859-1"), "utf-8") + "";
				}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("findList", fmsService.yhflSear(yhfl));
			request.setAttribute("yhgl", yhInfoList);
			return jsonObject;
		}
		
		
		@RequestMapping("HistorySj")
		@ResponseBody
		public JSONObject HistorySj(String fmId){
			yhInfoLt= yhInfoService.findHist(fmId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("fList", yhInfoLt);
			return jsonObject;
			
		}
		public List<YhInfo> getYhInfoLt()
		{
			return yhInfoLt;
		}


		public void setYhInfoLt(List<YhInfo> yhInfoLt)
		{
			this.yhInfoLt = yhInfoLt;
		}


		public List<Yh> getYhInfoList() {
			return yhInfoList;
		}

		public void setYhInfoList(List<Yh> yhInfoList) {
			this.yhInfoList = yhInfoList;
		}

	}
