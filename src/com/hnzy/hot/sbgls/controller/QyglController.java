package com.hnzy.hot.sbgls.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.sbgl.pojo.Fm;
import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.service.QgService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtils;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("QyglController")
public class QyglController {
	@Autowired
	private QgService qgService;
	@Autowired
	private RzService rzService;
	private List<Qg> qglist;
	private List<Fm> fmlist;
	boolean sessionmap = false;
    public String ip;
    public int port;
    public String pString;
    private static Log log = LogFactory.getLog(FmsglController.class);
	// 实时数据
	@RequestMapping("ssj")
	@ResponseBody
	public String ssj(HttpSession session,Qg qg, String ids) {
		qg = qgService.findQgID(ids);
		 ip = qg.getJzq().getJzqIp();
		 port = qg.getJzq().getJzqPort();
		 pString = sj(ip, port, ids);
		return pString;
	}
	// 实时热表数据
	@RequestMapping("SsRb")
	@ResponseBody
	public String SsRb(HttpSession session,Qg qg, String ids) {
		qg = qgService.findQgID(ids);
		 ip = qg.getJzq().getJzqIp();
		 port = qg.getJzq().getJzqPort();
		 pString = sjRb(ip, port, ids);
		return pString;
	}
	// 批量开阀
	@RequestMapping("PlKf")
	@ResponseBody
	public String PlKf(HttpSession session,Qg qg, String ids) {
		//日志
		Rz rz=new Rz();
		rz.setCz("批量开阀门,区管地址为:"+ids);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		if(session.getAttribute("userName").equals("供热管理处")){
			return "5";
		}else {
		qg = qgService.findQgID(ids);
		 ip = qg.getJzq().getJzqIp();
		 port = qg.getJzq().getJzqPort();
		 pString = plKf(ip, port, ids);
		return pString;
		}

	}

	@RequestMapping("PlGf")
	@ResponseBody
	public String PlGf(HttpSession session,Qg qg, String ids) {
		//日志
		Rz rz=new Rz();
		rz.setCz("批量关阀门,区管地址为:"+ids);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		if(session.getAttribute("userName").equals("供热管理处")){
			return "5";
		}else {
		qg = qgService.findQgID(ids);
		 ip = qg.getJzq().getJzqIp();
		 port = qg.getJzq().getJzqPort();
		 pString = PlGf(ip, port, ids);
		return pString;
		}
	}

	@RequestMapping("QgTS")
	@ResponseBody
	public String QgTS(HttpSession session,Qg qg, String ids) {
		//日志
		Rz rz=new Rz();
		rz.setCz("查询通讯状态,区管地址为:"+ids);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		qg = qgService.findQgID(ids);
		 ip = qg.getJzq().getJzqIp();
		 port = qg.getJzq().getJzqPort();
		 pString = qgTs(ip, port, ids);
		return pString;

	}

	@RequestMapping("TjfmId")
	@ResponseBody
	public String TjfmId(HttpSession session,Qg qg, String ids) {
		//日志
		Rz rz=new Rz();
		rz.setCz("添加阀门ID,区管地址为:"+ids);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		
		qg = qgService.findQgID(ids);
		 ip = qg.getJzq().getJzqIp();
		 port = qg.getJzq().getJzqPort();
		 pString = tjfmId(ip, port, ids);
		return pString;

	}

	// 读取阀门ID
	@RequestMapping("DqfmId")
	@ResponseBody
	public JSONObject DqfmId(HttpSession session,Qg qg, String ids) {
		//日志
		Rz rz=new Rz();
		rz.setCz("读取阀门ID,区管地址为:"+ids);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		
		qg = qgService.findQgID(ids);
		ip = qg.getJzq().getJzqIp();
		port = qg.getJzq().getJzqPort();
		JSONObject pString = DqfmId(ip, port, ids);
		return pString;
	}

	// 读取阀门ID
	public JSONObject DqfmId(String jzqIp, int jzqPort, String ids) {

		String pt = "/" + jzqIp + ":" + jzqPort;
		String ja = "F00A2900" + ids;
		log.info("读取阀门ID发送数据："+ja);
		sessionmap = cz(ja, pt);
		
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		if (sessionmap == true && MapUtils.getMapUtils().get("DqfmId")!=null &&MapUtils.getMapUtils().get("DqfmId") .equals("success") ) {

			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("DqfmId", null);
			String fmId = MapUtils.getMapUtils().get("fmId");
			jsonObject.put("fmId", fmId);
			jsonObject.put("js", "0");
		} else if (sessionmap == true && MapUtils.getMapUtils().get("cs")!=null && MapUtils.getMapUtils().get("cs").equals("超时")) {
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("DqfmId", null);
			jsonObject.put("js", "2");
			return jsonObject;
		} else if (sessionmap == false && MapUtils.getMapUtils().get("DqfmId")==null) {
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("DqfmId", null);
			jsonObject.put("js", "3");
			log.info("发送数据失败,集中器不在线");
			return jsonObject;
		} else {
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("DqfmId", null);
			jsonObject.put("js", "1");
			return jsonObject;
		}
		return jsonObject;

	}

	// 根据qg添加阀门ID
	public String tjfmId(String jzqIp, int jzqPort, String ids) {
		String pt = "/" + jzqIp + ":" + jzqPort;
		String fmString = "";
		// 阀门总数
		int ValstID = Integer.valueOf(qgService.findQgID(ids).getvALstID());
		int ValedID = Integer.valueOf(qgService.findQgID(ids).getvALedID());
		int fmcount = ValedID - ValstID + 1;
		int fmnt = fmcount * 4 + 11;
		String fmt = Integer.toHexString(fmnt);

		// 开始阀门
		String valstId = qgService.findQgID(ids).getvALstID();
		// 结束阀门
		String valedId = qgService.findQgID(ids).getvALedID();
		// 获取所有的阀门
		for (int i = 0; i < fmcount; i++) {
			int sted = Integer.valueOf(valstId);
			int sd = sted + i;
			fmString += "0" + Integer.toHexString(sd);
		}
		// 如果fm总数在某些范围内
		String fms = "";
		String fmstr = "";
		if (fmcount > 50 && fmcount <= 100) {
			log.info("根据qg添加阀门ID 阀门ID大于50小于100");
			fms = CzUtil.Uppercase(fmString).toString();
			if (fmcount > 0) {
				fmstr = fms.substring(0, 400);
				// 不到四个字节补0
				String jas = "F0D21C00" + "" + ids + fmstr + "01";
				log.info("根据qg添加阀门ID 阀门ID大于0发送数据："+jas);
				sessionmap = cz(jas, pt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (fmcount > 50) {
				fmstr = fmString.substring(400);
				String jas = "F0D21C00" + "" + ids + fmstr + "02";
				log.info("根据qg添加阀门ID 阀门ID大于50 发送数据："+jas);
				sessionmap = cz(jas, pt);
			}

		} else if (fmcount > 100 && fmcount <= 150) {
			log.info("根据qg添加阀门ID 阀门ID大于100小于150");
			if (fmcount > 0) {
				fms = CzUtil.Uppercase(fmString).toString();
				fmstr = fmString.substring(0, 400);
				String jas = "F0D21C00" + "" + ids + fmstr + "01";
				log.info("根据qg添加阀门ID 阀门ID大于0发送数据："+jas);
				sessionmap = cz(jas, pt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (fmcount > 50) {
				fms = CzUtil.Uppercase(fmString).toString();
				fmstr = fmString.substring(400, 800);
				String ja = "F0D21C00" + "" + ids + fmstr + "02";
				log.info("根据qg添加阀门ID 阀门ID大于50发送数据："+ja);
				sessionmap = cz(ja, pt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (fmcount > 100) {
				fmstr = fmString.substring(800);
				fms = CzUtil.Uppercase(fmString).toString();
				String jas = "F0D21C00" + "" + ids + fmstr + "03";
				log.info("根据qg添加阀门ID 阀门ID大于100发送数据："+jas);
				sessionmap = cz(jas, pt);
			}
		} else if (fmcount > 150 && fmcount <= 200) {
			log.info("根据qg添加阀门ID 阀门ID大于150小于200");
			if (fmcount > 0) {
				fms = CzUtil.Uppercase(fmString).toString();
				fmstr = fmString.substring(0, 400);
				String ja = "F0D21C00" + "" + ids + fmstr + "01";
				log.info("根据qg添加阀门ID 阀门ID大于0发送数据："+ja);
				sessionmap = cz(ja, pt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (fmcount > 50) {
				fms = CzUtil.Uppercase(fmString).toString();
				fmstr = fmString.substring(400, 800);
				String ja = "F0D21C00" + "" + ids + fmstr + "02";
				log.info("根据qg添加阀门ID 阀门ID大于50发送数据："+ja);
				sessionmap = cz(ja, pt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (fmcount > 100) {

				fms = CzUtil.Uppercase(fmString).toString();
				fmstr = fmString.substring(800, 1200);
				String ja = "F0D21C00" + "" + ids + fmstr + "03";
				log.info("根据qg添加阀门ID 阀门ID大于100发送数据："+ja);
				sessionmap = cz(ja, pt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (fmcount > 150) {
				fms = CzUtil.Uppercase(fmString).toString();
				fmstr = fmString.substring(1200);
				String jas = "F0D21C00" + "" + ids + fmstr + "04";
				log.info("根据qg添加阀门ID 阀门ID大于150");
				sessionmap = cz(jas, pt);
			}
		} else if (fmcount <= 50) {
 
			fms = CzUtil.Uppercase(fmString).toString();
			String jas = "F0" + fmt + "1C00" + ids + "" + fms + "01";
			log.info("根据qg添加阀门ID 阀门ID小于50发送数据："+jas);
			sessionmap = cz(jas, pt);
		}
		try {
			Thread.sleep(4000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (sessionmap == true && MapUtils.getMapUtils().get("tjfmId")!=null&& MapUtils.getMapUtils().get("tjfmId").equals("success")) {
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("tjfmId", null);

			return "0";
		} else if (sessionmap == true && MapUtils.getMapUtils().get("cs")!=null && MapUtils.getMapUtils().get("cs").equals("超时")) {
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("tjfmId", null);
			return "2";
		} else if (sessionmap == false && MapUtils.getMapUtils().get("tjfmId")==null) {
			log.info("发送数据失败,集中器不在线");
			return "3";
		} else {
			MapUtils.getMapUtils().add("cs", null);
			MapUtils.getMapUtils().add("tjfmId", null);
			return "1";
		}
	}
//查询通信状态
	public String qgTs(String jzqIp, int jzqPort,String ids) {
		String pt = "/" + jzqIp + ":" + jzqPort;
		String ja = "F00A0500" + ids;//改为区管地址 F0 0A 05 00 AA AA AA AA XX FF 
		sessionmap = cz(ja, pt);//改为区管地址 F0 0A 05 01 D0 D0 D0 11 XX FF 
		
		log.info("查询通信状态发送数据："+ja);
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (sessionmap == true && MapUtils.getMapUtils().get("jzq")!=null && MapUtils.getMapUtils().get("jzq") .equals("success")) {
			 MapUtils.getMapUtils().add("jzq", null);
			return "0";
		} else if (sessionmap == true  && MapUtils.getMapUtils().get("jzq")!=null && MapUtils.getMapUtils().get("jzq") .equals("fail")) {
			 MapUtils.getMapUtils().add("jzq", null);
			return "1";
		} else if (sessionmap == false) {
			 MapUtils.getMapUtils().add("jzq", null);
			log.info("发送数据失败,集中器不在线");
			return "3";
		} else {
			 MapUtils.getMapUtils().add("jzq", null);
			return "2";
		}
	}

	String param = null;
//批量关阀
	public String PlGf(String ip, int port, String qgId) {
		param = "PlGfm";
		MapUtils.getMapUtils().add("param", param);
		MapUtils.getMapUtils().add("PlGf", qgId);
		// IP地址和端口号
		String pt = "/" + ip + ":" + port;
		String ja = "F00B2100" + qgId + "01";
		log.info("批量关阀发送数据："+ja);
		sessionmap = cz(ja, pt);
		try {
			Thread.sleep(4000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (sessionmap == true  && MapUtils.getMapUtils().get("PlGfm")!=null && MapUtils.getMapUtils().get("PlGfm").equals("success")) {
			MapUtils.getMapUtils().add("PlGfm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "0";
		} else if (sessionmap == true  && MapUtils.getMapUtils().get("sb")!=null && MapUtils.getMapUtils().get("sb").equals("fail") 
				&& MapUtils.getMapUtils().get("PlGfm") != null) {
			MapUtils.getMapUtils().add("PlGfm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "2";
		} else if (sessionmap == true   && MapUtils.getMapUtils().get("cs")!=null && MapUtils.getMapUtils().get("cs") .equals("超时")
				|| sessionmap == true && MapUtils.getMapUtils().get("PlGfm") == null) {
			MapUtils.getMapUtils().add("PlGfm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "3";
		} else if (sessionmap == false) {
			MapUtils.getMapUtils().add("PlGfm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			log.info("发送数据失败,集中器不在线");
			return "4";
		} else {
			MapUtils.getMapUtils().add("PlGfm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "1";
		}
	}
//批量开阀
	public String plKf(String ip, int port, String qgId) {
		param = "PlKFm";
		MapUtils.getMapUtils().add("param", param);
		MapUtils.getMapUtils().add("PlKf", qgId);
		// IP地址和端口号
		String pt = "/" + ip + ":" + port;
		String ja = "F00B2000" + qgId + "00";
		log.info("批量开阀发送数据："+ja);
		sessionmap = cz(ja, pt);
		try {
			Thread.sleep(4000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (sessionmap == true && MapUtils.getMapUtils().get("PlKFm")!=null && MapUtils.getMapUtils().get("PlKFm").equals("success")) {
			MapUtils.getMapUtils().add("PlKFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "0";
		} else if (sessionmap == true && MapUtils.getMapUtils().get("sb")!=null && MapUtils.getMapUtils().get("sb") .equals("fail")
				&& MapUtils.getMapUtils().get("PlKFm") != null) {
			MapUtils.getMapUtils().add("PlKFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "2";
		} else if (sessionmap == true && MapUtils.getMapUtils().get("cs")!=null && MapUtils.getMapUtils().get("cs").equals("超时") 
				|| sessionmap == true && MapUtils.getMapUtils().get("PlKFm") == null) {
			MapUtils.getMapUtils().add("PlKFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "3";
		} else if (sessionmap == false) {
			MapUtils.getMapUtils().add("PlKFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			log.info("发送数据失败,集中器不在线");
			return "4";
		} else {
			MapUtils.getMapUtils().add("PlKFm", null);
			MapUtils.getMapUtils().add("sb", null);
			MapUtils.getMapUtils().add("cs", null);
			return "1";
		}

	}
	
	//实时数据
		public String sjRb(String ip, int port, String qgId) {
			
			MapUtilsDf.getMapUtils().add("PlDRb", "rb");
			//IP地址和端口号
			String pt = "/" + ip + ":" + port;
			// fmId十进制
			String ja = "F00A0300" + qgId;
			log.info("实时数据发送数据："+ja);
			sessionmap = cz(ja, pt);
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (sessionmap == true && MapUtilsDf.getMapUtils().get("PldRb")!=null && MapUtilsDf.getMapUtils().get("PldRb").equals("success")) {
				MapUtils.getMapUtils().add("PldRb", null);
				return "0";
			}else if (sessionmap == true && MapUtils.getMapUtils().get("PldRb")!=null && MapUtils.getMapUtils().get("PldRb").equals("fail")) {
				MapUtils.getMapUtils().add("PldRb", null);
				return "1";
			} else if (sessionmap == false) {
				log.info("发送数据失败,集中器不在线");
				return "3";
			}else{
				MapUtils.getMapUtils().add("PldRb", null);
				return "2";
			}
			
		}
//实时数据
	public String sj(String ip, int port, String qgId) {
		
		MapUtilsDf.getMapUtils().add("PlDF", "sj");
//		 IP地址和端口号
		String pt = "/" + ip + ":" + port;
		// fmId十进制
		String ja = "F00A0800" + qgId;
		log.info("实时数据发送数据："+ja);
		sessionmap = cz(ja, pt);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (sessionmap == true && MapUtilsDf.getMapUtils().get("PldFm")!=null && MapUtilsDf.getMapUtils().get("PldFm").equals("success")) {
			MapUtils.getMapUtils().add("PldFm", null);
			MapUtils.getMapUtils().add("dFm", null);
			return "0";
		}else if (sessionmap == true && MapUtils.getMapUtils().get("dFm")!=null && MapUtils.getMapUtils().get("dFm").equals("fail")) {
			MapUtils.getMapUtils().add("PldFm", null);
			MapUtils.getMapUtils().add("dFm", null);
			return "1";
		} else if (sessionmap == false) {
			log.info("发送数据失败,集中器不在线");
			return "3";
		}else{
			MapUtils.getMapUtils().add("PldFm", null);
			MapUtils.getMapUtils().add("dFm", null);
			return "2";
		}
		
	}

	// 抽取相同部分
	public boolean cz(String ja, String pt) {
		// 把十六进制数，转换为十进制相加
		int jia = CzUtil.FsZh(ja);
		// 十进制转换为十六进制
		String hex = Integer.toHexString(jia);
		StringBuffer stringBuffer = new StringBuffer();
		// 转换为大写
		if (hex != null) {
			for (int i = 0; i < hex.length(); i++) {
				char c = hex.charAt(i);
				if (!Character.isDigit(c)) {
					stringBuffer.append(Character.toUpperCase(c));
				} else {
					stringBuffer.append(c);
				}
			}
		}
		String sH = stringBuffer.toString();
		// 截取相加结果后两位
		String je = null;
		for (int j = 0; j < sH.length() - 1; j++) {
			je = sH.charAt(sH.length() - 2) + "" + sH.charAt(sH.length() - 1);
		}
		String[] keys = new String[] { pt };
		String mString = ja + "" + je + "FF";
		// 解码
		byte[] b = CzUtil.jm(mString);
		ServerSessionMap sessionMap = ServerSessionMap.getInstance();
		boolean sessionmap = sessionMap.sendMessage(keys, b);
		return sessionmap;
	}

	// 查找区管信息
	@RequestMapping("qglist")
	public String qglist(HttpServletRequest request) {
		qglist = qgService.find();
		request.setAttribute("Qg", qglist);
		return "sbgls/qygl";
	}
	String UppWdsd;
	String UppTjzq;
	String UppTjcs;
	@RequestMapping("Fscs")
	@ResponseBody
	public String Fscs(Qg qg,YhInfo yhInfo,HttpSession session,Fm f, String qgId, String  wdsd, String  tjzq, String tjcs,String sdbs) {
		//日志
		Rz rz=new Rz();
		rz.setCz("批量发送参数 区管ID"+qgId+"调节周期："+wdsd+"室温设定："+tjzq+"调节参数："+tjcs);
		rz.setCzr((String)session.getAttribute("userName"));
		rz.setCzsj(new Date());
		rzService.insert(rz);
		if(session.getAttribute("userName").equals("供热管理处")){
			return "5";
		}else {
		// 参数十进制转换为十六进制
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
				qg = qgService.findQgID(qgId);
				 ip = qg.getJzq().getJzqIp();
				 port = qg.getJzq().getJzqPort();
					if(sdbs.equals("02")){
						sdbs="FF";
					}
				 //IP地址和端口号
				String pt = "/" + ip + ":" + port;
				String ja = "F0160900" + qgId + "04AAAAAAAAFFFF"+sdbs+"FF"+UppWdsd + "" + UppTjzq + "" + UppTjcs;
				log.info("发送参数发送数据："+ja);
				sessionmap = cz(ja, pt);
				if(sessionmap==true){
					return "0";
				}else{
					return "1";
				}
		}
	}
	
	public List<Qg> getQglist() {
		return qglist;
	}

	public void setQglist(List<Qg> qglist) {
		this.qglist = qglist;
	}

	public List<Fm> getFmlist() {
		return fmlist;
	}

	public void setFmlist(List<Fm> fmlist) {
		this.fmlist = fmlist;
	}

}
