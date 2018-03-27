package com.hnzy.hot.socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.DatabaseUtil;

public class ISCSynAllData {
	//定时执行任务的类
	private static Logger log = Logger.getLogger(ISCSynAllData.class);
	String qgId = null;
	String JzqIP = null;
	int JzqPort = 0;
	public void run() {
		
		try
		{
			//发送抄表数据方法
			doSomething();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	
	}

	private void doSomething() throws SQLException {
		//连接数据库
		  DatabaseUtil dbUtil = DatabaseUtil.getInstance();   
	      Connection connc = dbUtil.getConnection(); 
	      ResultSet rstqg = null;
	      ResultSet rst;
	      PreparedStatement psqg = null;
	      PreparedStatement ps;
		String sqlcx = "select distinct T_JzqInfo.Socket,T_JzqInfo.jzqIP,T_JzqInfo.jzqport,T_QgInfo.QgID from T_JzqInfo,T_QgInfo where T_JzqInfo.JzqID=T_QgInfo.JzqID and T_JzqInfo.Status='1' and T_QgInfo.Mode='自动' order by T_QgInfo.QgID";
		 ps=connc.prepareStatement(sqlcx);
		  rst = ps.executeQuery();
		int col = rst.getMetaData().getColumnCount();
		while (rst.next()) {
			qgId = rst.getString("QgID");
			JzqIP = rst.getString("jzqIP");
			JzqPort = rst.getInt("jzqport");
			//根据区管ID查询阀门总数
			String sqlqg="select Count(ValAd)  as fmCount from T_FmInfo where  QgID='"+qgId+"'";
		     psqg=connc.prepareStatement(sqlqg);
			 rstqg = psqg.executeQuery();
			 int rowCount = 0;      
			 if(rstqg.next())      
			 {      
			    rowCount = rstqg.getInt(1);  
			 }   
			 String cot=String.valueOf(rowCount);
			//输出日志
			 log.info(qgId+"区管下阀门数量"+rowCount);  
			//根据阀门总数设置停留时间
			try {
				Thread.sleep(rowCount*500);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// IP地址和端口号
			String pt = "/" + JzqIP + ":" + JzqPort;
			String ja = "F00A0800" + qgId;
			String je=CzUtil.getTimerJe(ja);
			String[] keys = new String[] { pt };
			String mString = ja + "" + je + "FF";
			//输出日志
			 log.info("抄表发送数据"+mString);  
			// 解码
			byte[] b = CzUtil.jm(mString);
			ServerSessionMap sessionMap = ServerSessionMap.getInstance();
			boolean sessionmap = sessionMap.sendMessage(keys, b);
			
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//输出日志
			 log.info("1秒后抄表发送数据"+mString); 
			boolean sessionmap1 = sessionMap.sendMessage(keys, b);
			
		}
		try
		{
			DatabaseUtil.close(rst,ps, connc);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
