package com.hnzy.hot.sbgls.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.sbgl.pojo.Qg;
import com.hnzy.hot.sbgl.service.QgService;
import com.hnzy.hot.sbgls.service.SkqService;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtilsSkq;


@RequestMapping("SkqController")
@Controller
public class SkqController
{
	@Autowired
	private SkqService skqService;
	@Autowired
	private QgService qgService;
	private List<Qg> skqList;
	private static Log log = LogFactory.getLog(FmsglController.class);
	@RequestMapping("findSkqList")
	public String findSkq(HttpServletRequest request){
		//获取刷卡器集合
		skqList=qgService.findSkq();
		request.setAttribute("Skq", skqList);
		return "sbgls/skqList";
	}
	public List<Qg> getSkqList()
	{
		return skqList;
	}
	public void setSkqList(List<Qg> skqList)
	{
		this.skqList = skqList;
	}
	
	@RequestMapping("SkqState")
	@ResponseBody
	public String SkqState(Qg qg,String sbh){
		//根据设备号查询集中器IP地址和端口号
		qg=qgService.findByIPSocket(sbh);
		
		String ip = qg.getJzq().getJzqIp();
		// 端口号
		int port = qg.getJzq().getJzqPort();
		// // IP地址和端口号
		String pt = "/" + ip + ":" + port;
		int Sbh=Integer.valueOf(sbh);
		// 十进制转换为十六进制
		String skqSbh = Integer.toHexString(Sbh);
				// fmId十进制
				String ja = "F00F3A000" + skqSbh + "0000000000";
				log.info("刷卡器发送数据："+ja);
				boolean sessionmap = cz(ja, pt);
				try {
					Thread.sleep(3000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (sessionmap == true && MapUtilsSkq.getMapUtils().get("Dskq")!=null && MapUtilsSkq.getMapUtils().get("Dskq").equals("success")) {
					MapUtilsSkq.getMapUtils().add("Dskq", null);
					return "0";
				} else if (sessionmap == true && MapUtilsSkq.getMapUtils().get("Dskq")!=null && MapUtilsSkq.getMapUtils().get("Dskq").equals("cs") 
						|| sessionmap == true && MapUtilsSkq.getMapUtils().get("Dskq") == null) {
					MapUtilsSkq.getMapUtils().add("Dskq", null);
					return "1";
				} else if (sessionmap == false && MapUtilsSkq.getMapUtils().get("Dskq") == null) {
					log.info("发送数据失败,集中器不在线");
					MapUtilsSkq.getMapUtils().add("Dskq", null);
					return "3";
				}else {
					MapUtilsSkq.getMapUtils().add("Dskq", null);
					return "2";
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
		System.out.println("发送数据===="+mString);
		// 解码
		byte[] b = CzUtil.jm(mString);
		ServerSessionMap sessionMap = ServerSessionMap.getInstance();
		boolean sessionmap = sessionMap.sendMessage(keys, b);
		return sessionmap;

	}

}
