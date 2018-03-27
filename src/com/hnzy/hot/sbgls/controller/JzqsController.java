package com.hnzy.hot.sbgls.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.sbgl.pojo.Jzq;
import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.service.JzqService;
import com.hnzy.hot.sbgl.service.QgService;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtils;

@RequestMapping("JzqsController")
@Controller
public class JzqsController {
	@Autowired
	private JzqService jzqService;
	@Autowired
	private QgService qgService;
	private List<Jzq> jzqlist;
	private List<Qg> qgList;
	private static Log log = LogFactory.getLog(FmsglController.class);
	@RequestMapping("UpQg")
	@ResponseBody 
	public String UpQg(String jzqId,String jzqIp,String jzqPort){
		String pt = "/" + jzqIp + ":" + jzqPort;
		qgList=qgService.findJzqIDByQg(jzqId);
		int count=qgService.QgCount(jzqId);
		String qg="";
        for(int i=0;i<qgList.size();i++){
        	qg+=qgList.get(i).getQgID();
        }
       int ct=count *4+10;
        String cot = Integer.toHexString(ct);
   
        if(cot.length()-1<1){
        	cot="0"+cot;
        }
		String ja = "F0"+ cot+"0300" + jzqId +qg;
		log.info("更新区管ID发送数据："+ja);
		boolean sessionmap = cz(ja, pt);
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(sessionmap==true && MapUtils.getMapUtils().get("UpQg")!=null && MapUtils.getMapUtils().get("UpQg").equals("success")){
			return "0";
		}else if(sessionmap==true && MapUtils.getMapUtils().get("UpQg")!=null && MapUtils.getMapUtils().get("UpQg").equals("fail")){
			return "1";
		}else if(sessionmap==false){
			log.info("发送数据失败,集中器不在线");
			return "3";
		}else {
			return "2";
		}	
		
	}
	
	@RequestMapping("Cxzt")
	@ResponseBody 
	public String Cxzt(String jzqId,String jzqIp,String jzqPort){
		String pt = "/" + jzqIp + ":" + jzqPort;
		String ja = "F00A0100" + jzqId ;
		boolean sessionmap = cz(ja, pt);
		log.info("查询状态发送数据："+ja);
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(sessionmap==true && MapUtils.getMapUtils().get("jzq")!=null && MapUtils.getMapUtils().get("jzq").equals("success")){
			 MapUtils.getMapUtils().add("jzq", null);
			return "0";
		}else if(sessionmap==true && MapUtils.getMapUtils().get("jzq")!=null && MapUtils.getMapUtils().get("jzq").equals("fail")){
			 MapUtils.getMapUtils().add("jzq", null);
			return "1";
		}else if(sessionmap==false){
			 MapUtils.getMapUtils().add("jzq", null);
			log.info("发送数据失败,集中器不在线");
			return "3";
		}else {
			 MapUtils.getMapUtils().add("jzq", null);
			return "2";
		}	
	}
	//抽取相同部分
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
		System.out.println("开阀数据**********" + mString);
		// 解码
		byte[] b = CzUtil.jm(mString);
		ServerSessionMap sessionMap = ServerSessionMap.getInstance();
		boolean sessionmap = sessionMap.sendMessage(keys, b);
		return sessionmap;

	}
	
	//集中器列表
	@RequestMapping(value="Jzqlist")
	public String jzqlist(HttpServletRequest request){
		jzqlist = jzqService.find();
		request.setAttribute("jzqlist", jzqlist);
		return "sbgls/jzq";
	}

	public List<Jzq> getJzqlist() {
		return jzqlist;
	}

	public void setJzqlist(List<Jzq> jzqlist) {
		this.jzqlist = jzqlist;
	}

	public QgService getQgService() {
		return qgService;
	}

	public void setQgService(QgService qgService) {
		this.qgService = qgService;
	}

}
