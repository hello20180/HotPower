package com.hnzy.hot.socket.server;

import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.DatabaseUtil;
import com.hnzy.hot.socket.util.MapUtils;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.hot.socket.util.MapUtilsSkq;
import com.hnzy.hot.socket.util.Utils;
import com.hnzy.hot.socket.util.oracleHelper;

public class ServerHandler extends IoHandlerAdapter
{

	PreparedStatement ps;
	ResultSet rst;
	int rs = 0;

	PreparedStatement Oracleps;
	ResultSet Oraclerst;
	int Oraclers = 0;
	String kfSt;
	String gfSt;
	String SJtring;
	String jaString;
	String dfStatus;
	boolean sessionmap;
	String param;
	private final static Logger log = LoggerFactory.getLogger(ServerHandler.class);
	ServerSessionMap sessionMap = ServerSessionMap.getInstance();
	// 日志文件
	private static Log logs = LogFactory.getLog(ServerHandler.class);

	/**
	 * 当一个新客户端连接后触发此方法
	 * 
	 */
	@Override
	public void sessionCreated(IoSession session)
	{
		logs.info("服务器创建链路成功!"+ session.getRemoteAddress());
	}

	/**
	 * 当连接打开时调用
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception
	{
		logs.info("服务器打开了的连接，Session ID为"+ session.getRemoteAddress()+ session.getId());
		SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
		String clientIp = remoteAddress.toString();

		sessionMap.add(clientIp, session);
		int port = 0;
		String Ip = null;
		String id = null;

		DatabaseUtil dbUtil = DatabaseUtil.getInstance();
		Connection connc = dbUtil.getConnection();

		// 获取集中器IP
		for (int i = 0; i < clientIp.length(); i++)
		{
			String[] ipPortString = clientIp.split(":");
			String IP = ipPortString[0];

			String[] ip = IP.split("/");
			port = Integer.valueOf(ipPortString[1]);
			Ip = ip[1];
		}
		SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String time = Sdate.format(new Date());

		// 查找集中器ID
		String sqlcx = "select JzqID from T_JzqInfo where JzqIp='" + Ip + "'";
		ps = connc.prepareStatement(sqlcx);
		rst = ps.executeQuery();
		int col = rst.getMetaData().getColumnCount();
		while (rst.next())
		{
			id = rst.getString("JzqID");
		}
		// 如果集中器ID不为空
		if (id != null)
		{
			String sql = "update T_JzqInfo set JzqPort='" + port + "',Status='1',UpdateTime='" + time
					+ "' where JzqIp='" + Ip + "'";
			ps = connc.prepareStatement(sql);
			rs = ps.executeUpdate();
			String date = "F00A0100" + id + "";
			String jString = CzUtil.jyh(date);
			String mString = date + "" + jString + "" + "FF";
			// 解码
			byte[] b = CzUtil.jm(mString);
			String[] keys = new String[]
			{ clientIp };
			logs.info("集中器ID存在发送数据" + mString);
			// 发送数据
			sessionMap.sendMessage(keys, b);

		} else
		{

			String mString = "F00A0100AAAAAAAAA3FF";
			// 解码
			byte[] b = CzUtil.jm(mString);
			String[] keys = new String[]
			{ clientIp };
			logs.info("集中器ID不存在发送数据" + mString);
			// 发送数据
			sessionMap.sendMessage(keys, b);
		}
		DatabaseUtil.close(rst, ps, connc);

	}

	/**
	 * 当实现IOHandlerer的类抛出异常时调用
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		logs.info("{}出现异常{}"+session.getRemoteAddress()+ cause);
		sessionMap.remove(session);
	}

	/**
	 * 当接受了一个消息时调用
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
	{
		SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
		String clientIp = remoteAddress.toString();
		DatabaseUtil dbUtil = DatabaseUtil.getInstance();
		Connection connc = dbUtil.getConnection();
		byte[] base = (byte[]) message;
		String stringMR = Utils.bytesToHexString(base);
		String md = null;
		// 接收数据不能为空并且长度大于15
		if (stringMR != null && stringMR.length() > 15)
		{
			for (int i = 0; i < stringMR.length() - 1; i++)
			{
				md = stringMR.substring(4, 6);
			}
			// 开关阀门，批量开关
			if (md.equals("09"))
			{
				try
				{
					kg(base,connc);
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				} catch (SQLException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				}

			}else if (md.equals("04"))// 读阀
			{
				try
				{
					df(base,connc);
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				} catch (SQLException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				}
			}else if (md.equals("52"))// 读传感器地址
			{
				try
				{
					dcgq(base,connc);
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				} catch (SQLException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				}
			}else if (md.equals("0f")) // 阀门故障查询
			{
				try
				{
					gzcx(base,connc);
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				} catch (SQLException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				}
			}else if (md.equals("01"))// 集中器查询状态
			{
				try
				{
					jzqCx(base,connc);
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				} catch (SQLException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				}
			}else if (md.equals("05"))// 区管查询
			{
				try
				{
					try
					{
						qgCx(base,connc);
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				} 
			}else if (md.equals("03"))// 更新区管ID
			{
				UpQg(base);
			}else if (md.equals("1C"))
			{
				// 添加阀门ID
				tjfmId(base);
			}else if (md.equals("29"))
			{
				// 读取阀门ID
				DqfmId(base);
			}else if (md.equals("3b"))
			{
				// 读卡器
				try
				{
					DkQ(base, session, message, clientIp,connc);
				} catch (SQLException e)
				{
					e.printStackTrace();
					// 错误日志打印信息
					logs.error(e, e.fillInStackTrace());
				}
			}else if (md.equals("3a"))
			{
				DkqJs(base);
			}else if(md.equals("53")){//修改无线传感器地址
				XCgq(base);
			}else if(md.equals("02")){
				//抄取热表
				try
				{
					CqRb(base,connc);
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(md.equals("11")){//微信开
				try
				{
					KfWx(base,connc);
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(md.equals("12")){//微信关
				try
				{
					GfWx(base,connc);
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try
		{
			DatabaseUtil.close(ps,connc);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 抽取相同部分
	public boolean cz(String ja, String pt)
	{

		// 把十六进制数，转换为十进制相加
		int jia = CzUtil.FsZh(ja);
		// 十进制转换为十六进制
		String hex = Integer.toHexString(jia);
		// 截取相加结果后两位
		String je = null;
		for (int j = 0; j < hex.length() - 1; j++)
		{
			je = hex.charAt(hex.length() - 2) + "" + hex.charAt(hex.length() - 1);
		}
		String[] keys = new String[]{ pt };
		String mString = ja + je + "FF";
		// 转换为大写
		String stringsj = CzUtil.Uppercase(mString).toString();
		// 解码
		byte[] b = CzUtil.jm(stringsj);
		ServerSessionMap sessionMap = ServerSessionMap.getInstance();
		boolean sessionmap = sessionMap.sendMessage(keys, b);
		return sessionmap;
	}

	public String CzUtils(String jaString)
	{
		// 把十六进制数，转换为十进制相加
		int jia = CzUtil.FsZh(jaString);
		// 十进制转换为十六进制
		String hex = Integer.toHexString(jia);
		// 截取相加结果后两位
		String jeq = null;
		for (int j = 0; j < hex.length() - 1; j++)
		{
			jeq = hex.charAt(hex.length() - 2) + "" + hex.charAt(hex.length() - 1);
		}

		String stringSJ = jaString + jeq + "FF";
		// 转换为大写
		String stringsj = CzUtil.Uppercase(stringSJ).toString();
		return stringsj;
	}
	
	String jzqPort;
	String qgID;
	String JzqIP;
	
	String hTemSet;
	String mTemSet;
	String lTemSet;
	
	public void KfWx(byte[] base,Connection connc) throws SQLException{
		//接收数据
		String stringH = Utils.bytesToHexString(base);
		String varAd=stringH.substring(6,14);
		//转换十进制
		int Fms = Integer.parseInt("" + varAd + "", 16);
		//根据阀门地址查找区管地址,IP地址端口号
	 		//更新
			String Upsql = "update T_FmInfo set Type='微信开' where ValAd='"+Fms+"'";
			ps=connc.prepareStatement(Upsql);
			rs=ps.executeUpdate();
	     
         String sqlcx = "select distinct fm.HTemSet,fm.MTemSet,fm.LTemSet,fm.QgID,jzq.JzqIP,jzq.JzqPort"
         		+ " from T_FmInfo fm,T_QgInfo qg,T_JzqInfo jzq "
         		+ " where fm.QgID=qg.QgID and qg.JzqID=jzq.JzqID and fm.ValAd='"+Fms+"'";
			ps = connc.prepareStatement(sqlcx);
			rst = ps.executeQuery();
			int col = rst.getMetaData().getColumnCount();
			while (rst.next())
			{
				 jzqPort = rst.getString("jzqPort");
				 qgID = rst.getString("QgID");
				 JzqIP = rst.getString("JzqIP");
				 hTemSet = rst.getString("HTemSet");
				 mTemSet = rst.getString("MTemSet");
				 lTemSet = rst.getString("LTemSet");
			}
			// 参数十进制转换为十六进制
			int WDsd=Integer.valueOf(hTemSet);
			int TJzq=Integer.valueOf(mTemSet);
			int TJcs=Integer.valueOf(lTemSet);
			String Wdsd = "00" + Integer.toHexString(WDsd);
			String subwdsd = Wdsd.substring(Wdsd.length() - 2);
			String Tjzq = "00" + Integer.toHexString(TJzq);
			String subtjzq = Tjzq.substring(Tjzq.length() - 2);
			String Tjcs = "00" + Integer.toHexString(TJcs);
			String subtjcs = Tjcs.substring(Tjcs.length() - 2);
			String UppWdsd = CzUtil.Uppercase(subwdsd).toString();
			String UppTjzq = CzUtil.Uppercase(subtjzq).toString();
			String UppTjcs = CzUtil.Uppercase(subtjcs).toString();
			String ja = "F0160900" + qgID + "04" + varAd + "01FFFF01" + UppWdsd + "" + UppTjzq + "" + UppTjcs+ "";	     
			// 微信开阀门
//			String ja = "F0160900" + qgID + "04" + varAd + "01FFFF01FFFFFF";
			// IP地址和端口号
			String pt = "/" + JzqIP + ":" + jzqPort;
			log.info("微信开阀门发送数据："+ja);
			boolean sessionmap = cz(ja, pt);
			try
			{
				Thread.sleep(8000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			// fmId十进制
			String jas = "F00F0400" + qgID + "04" + varAd;
			log.info("微信读阀发送数据："+jas);
			boolean sessionmap1 = cz(jas, pt);
	}
	public void GfWx(byte[] base,Connection connc) throws SQLException{
				//接收数据
				String stringH = Utils.bytesToHexString(base);
				String varAd=stringH.substring(6,14);
			   	//转换十进制
		 			int Fms = Integer.parseInt("" + varAd + "", 16);
		          String sqlcx = "select distinct fm.HTemSet,fm.MTemSet,fm.LTemSet,fm.QgID,jzq.JzqIP,jzq.JzqPort"
		         		+ " from T_FmInfo fm,T_QgInfo qg,T_JzqInfo jzq "
		         		+ " where fm.QgID=qg.QgID and qg.JzqID=jzq.JzqID and fm.ValAd='"+Fms+"'";
		         	//更新
					String Upsql = "update T_FmInfo set Type='微信关' where ValAd='"+Fms+"'";
					ps=connc.prepareStatement(Upsql);
					rs=ps.executeUpdate();
		         
					ps = connc.prepareStatement(sqlcx);
					rst = ps.executeQuery();
					int col = rst.getMetaData().getColumnCount();
					while (rst.next())
					{
						 jzqPort = rst.getString("jzqPort");
						 qgID = rst.getString("QgID");
						 JzqIP = rst.getString("JzqIP");
						 hTemSet = rst.getString("HTemSet");
						 mTemSet = rst.getString("MTemSet");
						 lTemSet = rst.getString("LTemSet");
					}
					// 参数十进制转换为十六进制
					int WDsd=Integer.valueOf(hTemSet);
					int TJzq=Integer.valueOf(mTemSet);
					int TJcs=Integer.valueOf(lTemSet);
					String Wdsd = "00" + Integer.toHexString(WDsd);
					String subwdsd = Wdsd.substring(Wdsd.length() - 2);
					String Tjzq = "00" + Integer.toHexString(TJzq);
					String subtjzq = Tjzq.substring(Tjzq.length() - 2);
					String Tjcs = "00" + Integer.toHexString(TJcs);
					String subtjcs = Tjcs.substring(Tjcs.length() - 2);
					String UppWdsd = CzUtil.Uppercase(subwdsd).toString();
					String UppTjzq = CzUtil.Uppercase(subtjzq).toString();
					String UppTjcs = CzUtil.Uppercase(subtjcs).toString();
					// fmId十进制
					String ja = "F0160900" + qgID + "04" + varAd + "00FFFF00" + UppWdsd + "" + UppTjzq + "" + UppTjcs+ "";	     
					// 微信关阀门
//					String ja = "F0160900" + qgID + "04" + varAd + "00FFFF00FFFFFF";
					// IP地址和端口号
					String pt = "/" + JzqIP + ":" + jzqPort;
					log.info("微信关阀门发送数据："+ja);
					boolean sessionmap = cz(ja, pt);
					try
					{
						Thread.sleep(8000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					// fmId十进制
					String jas = "F00F0400" + qgID + "04" + varAd;
					log.info("微信读阀发送数据："+jas);
					boolean sessionmap1 = cz(jas, pt);
	}
	//抄取热表
		public void CqRb(byte[] base,Connection connc) throws SQLException{
			logs.info("抄取热表接收数据："+Utils.bytesToHexString(base));	
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 获取发送的时间
			String fTime = MapUtils.getMapUtils().get("time");
			String jTime = df.format(new Date());
			int ms = 0;
			String sumTime = CzUtil.getSubtract(fTime, jTime);
			if (sumTime == null)
			{
				sumTime = "00:00:01";
			}
			if (sumTime != null)
			{
				ms = CzUtil.transform(sumTime);
				// 判断时间是否超时
				if (ms <= 60)
				{
			//接收数据
			String stringH = Utils.bytesToHexString(base);
			// 转换为大写
			String stringHandler = CzUtil.Uppercase(stringH).toString();
			// 截取效验数据
			String jy=CzUtil.getJy(stringHandler);
			// 判断开始和结束
			String start = null;
			String end = null;			
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
			//判断和校验
			String je=CzUtil.getJe(stringHandler);
			if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + "")){ 
				//区管ID
				String qgID=stringHandler.substring(8,16);
				 //热表ID
				String RbID=stringHandler.substring(21,30);
				//当前热量
				String DqRL=stringHandler.substring(50,60);
				String DqRLS=DqRL.substring(0,6);
				String DqRLF=DqRL.substring(6,8);
				int dqrls=Integer.valueOf(DqRLS);
				int dqrlf=Integer.valueOf(DqRLF);
				//累计热量
				String DqRLSF=dqrls+"."+dqrlf;
				//热功率
				String RGL=stringHandler.substring(60,70);
				String RGLS=RGL.substring(0,6);
				String RGLF=RGL.substring(6,8);
				int rgls=Integer.valueOf(RGLS);
				int rglf=Integer.valueOf(RGLF);
				//拼接热功率
				String RGLSF=rgls+"."+rglf;
				//顺时流量
				String LM=stringHandler.substring(70,80);
				String LMS=LM.substring(0,4);
				int lms=Integer.valueOf(LMS);
				String LMF=LM.substring(4,8);
				int lmf=Integer.valueOf(LMF);
				//顺时流量
				String LMSF=lms+"."+lmf;
				//累计流量
				String JLM=stringHandler.substring(80,90);
				String JLMS=JLM.substring(0,6);
				String JLMF=JLM.substring(6,8);
				int jlms=Integer.valueOf(JLMS);
				int jlmf=Integer.valueOf(JLMF);
				//累计流量拼接
				String JLMSF=jlms+"."+jlmf;
				//供水温度
				String GsWd=stringHandler.substring(90,96);
				String GsWdS=GsWd.substring(0,4);
				String GsWdF=GsWd.substring(4);
				int gswds=Integer.valueOf(GsWdS);
				int gswdf=Integer.valueOf(GsWdF);
				//供水温度
				String GsWdSF=gswds+"."+gswdf;
				//回水温度
				String HsWd=stringHandler.substring(96,102);
				
				String HsWdS=HsWd.substring(0,4);
				String HsWdF=HsWd.substring(4);
				int hswds=Integer.valueOf(HsWdS);
				int hswdf=Integer.valueOf(HsWdF);
				//回水温度
				String HsWdSF=hswds+"."+hswdf;
				// 累计工作时间 2小时
				String GzTime=stringHandler.substring(102,108);
				int time=Integer.valueOf(GzTime);
				// 实时时间
				String SsTime=stringHandler.substring(108,122);
				Integer nString=Integer.valueOf(SsTime.substring(0,4));
				Integer sString=Integer.valueOf(SsTime.substring(4,6));
				Integer mString=Integer.valueOf(SsTime.substring(6,8));
				Integer fString=Integer.valueOf(SsTime.substring(8,10));
				Integer fStrings=Integer.valueOf(SsTime.substring(10,12));
				Integer fStrings1=Integer.valueOf(SsTime.substring(12,14));
				String time1=nString+"/"+sString+"/"+mString+" "+fString+":"+fStrings+":"+fStrings1;
				
				//更新
				String Upsql = "update T_RbInfo set RbAd='" + RbID + "',energy='" + DqRLSF + "',EnergyLj='" + JLMSF + "',power='" + RGLSF
						+ "',flow='" + LMSF + "',In_Tmp='" + GsWdSF + "',OperTime='" + time + "',Out_Tmp='" + HsWdSF + "',RecordTime='"+jTime+"',ReadMTime='"+time1+"'  where RbAd='"+RbID+"'";
				ps=connc.prepareStatement(Upsql);
				rs=ps.executeUpdate();
				//插入
				String Insql = "insert into T_RbHistory (RbAd,energy,EnergyLj,power,flow,In_Tmp,Out_Tmp,OperTime,ReadMTime,RecordTime)values ('" + RbID +
						"','" + DqRLSF + "','" +JLMSF + "','"  + RGLSF + "','" + LMSF + "','"
						+ GsWdSF + "','" + HsWdSF + "','" + time + "','" + time1 + "','" + jTime + "')";
				
				ps=connc.prepareStatement(Insql);
				ps.execute();
				if(MapUtilsDf.getMapUtils().get("dRbParam")!=null&&MapUtilsDf.getMapUtils().get("dRbParam").equals(""+RbID+"")){
					MapUtils.getMapUtils().add("Crb", "success");
				}else if(MapUtilsDf.getMapUtils().get("PlDRb")!=null && MapUtilsDf.getMapUtils().get("PlDRb").equals("rb")){
					MapUtilsDf.getMapUtils().add("PldRb", "success");
				}
				
				logs.info("读取热表成功");
					}else if(stringHandler.length()-1>129){
						String[] str=stringHandler.split("F04102");
						for(int i=0;i<str.length;i++){
							String iString ="F04102"+str[i];
							if(iString.length()-1==129){
								RbCz( iString, jTime,connc);
							}else{
								MapUtilsSkq.getMapUtils().add("Crb", "fail");
								logs.info("读取热表失败");
							}
							
						}
					}else
					{
						MapUtilsSkq.getMapUtils().add("Crb", "fail");
						logs.info("读取热表失败");
					}
				} else
				{
					MapUtilsSkq.getMapUtils().add("Crb", "cs");
					logs.info("读取热表超时");
				}
			}		 
		}	
		
		
		public void RbCz(String stringHandler,String jTime,Connection connc) throws SQLException{
			// 截取效验数据
			String jy=CzUtil.getJy(stringHandler);
			//判断和校验
			String je=CzUtil.getJe(stringHandler);
			String end = null;			
			end = stringHandler.charAt(stringHandler.length() - 2) + ""
					+ stringHandler.charAt(stringHandler.length() - 1);
			if ( end.equals("FF") && je.equals("" + jy + "")){ 
				//区管ID
				String qgID=stringHandler.substring(8,16);
				 //热表ID
				String RbID=stringHandler.substring(21,30);
				//当前热量
				String DqRL=stringHandler.substring(50,60);
				String DqRLS=DqRL.substring(0,6);
				String DqRLF=DqRL.substring(6,8);
				int dqrls=Integer.valueOf(DqRLS);
				int dqrlf=Integer.valueOf(DqRLF);
				//拼接当前热量
				String DqRLSF=dqrls+"."+dqrlf;
				//热功率
				String RGL=stringHandler.substring(60,70);
				String RGLS=RGL.substring(0,6);
				String RGLF=RGL.substring(6,8);
				int rgls=Integer.valueOf(RGLS);
				int rglf=Integer.valueOf(RGLF);
				//拼接热功率
				String RGLSF=rgls+"."+rglf;
				//顺时流量
				String LM=stringHandler.substring(70,80);
				String LMS=LM.substring(0,4);
				int lms=Integer.valueOf(LMS);
				String LMF=LM.substring(4,8);
				int lmf=Integer.valueOf(LMF);
				//顺时流量
				String LMSF=lms+"."+lmf;
				//累计流量
				String JLM=stringHandler.substring(80,90);
				String JLMS=JLM.substring(0,6);
				String JLMF=JLM.substring(6,8);
				int jlms=Integer.valueOf(JLMS);
				int jlmf=Integer.valueOf(JLMF);
				//累计流量拼接
				String JLMSF=jlms+"."+jlmf;
				//供水温度
				String GsWd=stringHandler.substring(90,96);
				String GsWdS=GsWd.substring(0,4);
				String GsWdF=GsWd.substring(4);
				int gswds=Integer.valueOf(GsWdS);
				int gswdf=Integer.valueOf(GsWdF);
				//供水温度
				String GsWdSF=gswds+"."+gswdf;
				//回水温度
				String HsWd=stringHandler.substring(96,102);
				
				String HsWdS=HsWd.substring(0,4);
				String HsWdF=HsWd.substring(4);
				int hswds=Integer.valueOf(HsWdS);
				int hswdf=Integer.valueOf(HsWdF);
				//回水温度
				String HsWdSF=hswds+"."+hswdf;
				// 累计工作时间 2小时
				String GzTime=stringHandler.substring(102,108);
				int time=Integer.valueOf(GzTime);
				// 实时时间
				String SsTime=stringHandler.substring(108,122);
				Integer nString=Integer.valueOf(SsTime.substring(0,4));
				Integer sString=Integer.valueOf(SsTime.substring(4,6));
				Integer mString=Integer.valueOf(SsTime.substring(6,8));
				Integer fString=Integer.valueOf(SsTime.substring(8,10));
				Integer fStrings=Integer.valueOf(SsTime.substring(10,12));
				Integer fStrings1=Integer.valueOf(SsTime.substring(12,14));
				String time1=nString+"/"+sString+"/"+mString+" "+fString+":"+fStrings+":"+fStrings1;
				//更新
				String Upsql = "update T_RbInfo set RbAd='" + RbID + "',energy='" + DqRLSF + "',EnergyLj='" + JLMSF + "',power='" + RGLSF
						+ "',flow='" + LMSF + "',In_Tmp='" + GsWdSF + "',OperTime='" + time + "',Out_Tmp='" + HsWdSF + "',RecordTime='"+jTime+"',ReadMTime='"+time1+"'  where RbAd='"+RbID+"'";
				ps=connc.prepareStatement(Upsql);
				rs=ps.executeUpdate();
				//插入
				String Insql = "insert into T_RbHistory (RbAd,energy,EnergyLj,power,flow,In_Tmp,Out_Tmp,OperTime,ReadMTime,RecordTime)values ('" + RbID +
						"','" + DqRLSF + "','" +JLMSF + "','"  + RGLSF + "','" + LMSF + "','"
						+ GsWdSF + "','" + HsWdSF + "','" + time + "','" + time1 + "','" + jTime + "')";
				
				ps=connc.prepareStatement(Insql);
				ps.execute();
				if(MapUtilsDf.getMapUtils().get("dRbParam")!=null&&MapUtilsDf.getMapUtils().get("dRbParam").equals(""+RbID+"")){
					MapUtils.getMapUtils().add("Crb", "success");
				}else if(MapUtilsDf.getMapUtils().get("PlDRb")!=null && MapUtilsDf.getMapUtils().get("PlDRb").equals("rb")){
					MapUtilsDf.getMapUtils().add("PldRb", "success");
				}
				logs.info("读取热表成功");
					}else{
						MapUtilsSkq.getMapUtils().add("Crb", "fail");
						logs.info("读取热表失败");
					}
		}
    //取卡器信息
	public void DkqJs(byte[] base)
	{
		logs.info("点击读卡器接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 6)
			{
				// 接收的数据
				String stringH = Utils.bytesToHexString(base);
				// 转换为大写
				String stringHandler = CzUtil.Uppercase(stringH).toString();
				// 截取效验数据
				String jy = CzUtil.getJy(stringHandler);
				// 判断开始和结束
				String start = null;
				String end = null;
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
				// 判断和校验
				String je = CzUtil.getJe(stringHandler);
				if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
				{
					logs.info("读卡成功：" + stringHandler);
					MapUtilsSkq.getMapUtils().add("Dskq", "success");
					logs.info("读卡成功");
				} else
				{
					MapUtilsSkq.getMapUtils().add("Dskq", "fail");
					logs.info("读卡失败");
				}
			} else
			{
				MapUtilsSkq.getMapUtils().add("Dskq", "cs");
				logs.info("读卡超时");
			}
		}
	}

	// 读卡
	public void DkQ(byte[] base, IoSession session, Object message, String clientIp,Connection connc) throws SQLException
	{
		logs.info("刷卡器接收数据：" + Utils.bytesToHexString(base));
		// 接收的数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);
		String je = CzUtil.getJe(stringHandler);
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			// 截取读卡器ID号,用户编码
			String DkqId = stringHandler.substring(8, 16);
			// 截取卡片卡号
			String KhId = stringHandler.substring(16, 24);
			String khId = stringHandler.substring(17, 24);
			String Oraclesql = "select distinct YHBM,JFZT,XQ,LH,DYH,SH,CS,FGS   from SF_JMXXJF_V where YHKH='" + khId
					+ "'";
			// 连接oracle数据库
			oracleHelper OracledbUtil = oracleHelper.getInstance();
			Connection Oracleconnc = OracledbUtil.getConnection();
			// 执行查询oracle
			Oracleps = Oracleconnc.prepareStatement(Oraclesql);
			Oraclerst = Oracleps.executeQuery();
			// 获得数据oracle
			int col = Oraclerst.getMetaData().getColumnCount();
			String XQ = null;
			String LH = null;
			String SH = null;
			String YHBM = null;
			String CS = null;
			String Yhfl=null;
			int JFZT = 0;
			int buildNo = 0;
			int cellNo = 0;
			int houseNo = 0;
			String fmID;
			while (Oraclerst.next())
			{
				// 用户编码
				YHBM = Oraclerst.getString("YHBM");
				// 缴费状态
				JFZT = Oraclerst.getInt("JFZT");
				// 小区
				XQ = Oraclerst.getString("XQ");
				LH = Oraclerst.getString("LH");
				// 楼号
				buildNo = Integer.parseInt(LH.replaceAll("\\D+", ""));
				cellNo = Integer.parseInt(Oraclerst.getString("DYH"));
				SH = Oraclerst.getString("SH");
				CS = Oraclerst.getString("CS");
				if (SH.length() == 1)
				{
					houseNo = Integer.parseInt(CS + "0" + SH);
				} else
				{
					houseNo = Integer.parseInt(CS + "" + SH);
				}
			}
			if (YHBM != null)
			{
				String jfzt = null;
				if (JFZT == 0)
				{
					jfzt = "是";
				} else
				{
					jfzt = "否";
				}
				// 更新数据库
				String Upsql = "update T_YhInfo set YHBM='" + YHBM + "',SFJF='" + jfzt + "' where XqName='" + XQ
						+ "' and BuildNO='" + buildNo + "' and CellNO='" + cellNo + "' and HouseNO='" + houseNo + "'";
				// 执行更新sqlserver
				ps = connc.prepareStatement(Upsql);
				rs = ps.executeUpdate();
				// 查找阀门是否开关
				String SqlSelect = "select fm.Status,fm.QgID,fm.RoomTemp,yh.Yhfl,yh.ValAd,yh.SFJF from T_YhInfo yh,T_FmInfo fm where yh.ValAd=fm.ValAd and yh.YHBM='"
						+ YHBM + "'";
				// 执行查询sql
				ps = connc.prepareStatement(SqlSelect);
				rst = ps.executeQuery();
				int coln = rst.getMetaData().getColumnCount();
				String valAd = null;
				String qgID = null;
				String SFJF=null;
				double roomTemp =0;
				while (rst.next())
				{
					valAd = rst.getString("ValAd");
					qgID = rst.getString("QgID");
					Yhfl = rst.getString("Yhfl");
					roomTemp = rst.getDouble("RoomTemp");
					SFJF = rst.getString("SFJF");
				}
				if (valAd != null)
				{
					// 把FmID转换为int类型
					int fInteger = Integer.valueOf(valAd);
					// FmID十进制转换为十六进制 
					String fmd =  CzUtil.Uppercase( Integer.toHexString(fInteger)).toString();
					// 发送读阀门指令
					SJtring = "F00F0400" + qgID + "040" + fmd;
					logs.info("读阀发送指令数据：" + SJtring);
					sessionmap = cz(SJtring, clientIp);
					try
					{
						Thread.sleep(3000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					// 读阀门是否成功
					dfStatus = MapUtilsSkq.getMapUtils().get("df");
					fmID=MapUtilsSkq.getMapUtils().get("fmID");
					// 如果读阀门失败
					if (dfStatus == null)
					{
						// 发送读阀门指令
						SJtring = "F00F0400" + qgID + "040" + fmd;
						logs.info("读阀发送指令数据：" + SJtring);
						sessionmap = cz(SJtring, clientIp);
						try
						{
							Thread.sleep(2000);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						dfStatus = MapUtilsSkq.getMapUtils().get("df");
						fmID=MapUtilsSkq.getMapUtils().get("fmID");
						if (dfStatus == null)
						{
							// 发送读阀门指令
							// fmId十进制
							SJtring = "F00F0400" + qgID + "040" + fmd;
							logs.info("读阀发送指令数据：" + SJtring);
							sessionmap = cz(SJtring, clientIp);
							try
							{
								Thread.sleep(2000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							dfStatus = MapUtilsSkq.getMapUtils().get("df");
							fmID=MapUtilsSkq.getMapUtils().get("fmID");
						}
					}
					// 0已经交费，开阀门
					if (JFZT == 0)
					{
						if (dfStatus!= null && dfStatus.equals("关")&& fmID!=null&&fmID.equals("0"+fmd+""))
						{
							MapUtils.getMapUtils().add("df", null);
							SJtring = "F0160900" + qgID + "040" + fmd + "01FFFF01FFFFFF";
							MapUtils.getMapUtils().add("param", "kFm");
							
							if(Yhfl!=null&&Yhfl.equals("重点监控")||Yhfl!=null&&Yhfl.equals("退费停暖")||roomTemp==0.0 )
							{
								// 返回读卡器指令
								 jaString = "F00F3B00" + DkqId + "" + KhId + "02";
									// 如果查出数据则插入数据库刷卡器表
									int dkqId = Integer.parseInt("" + DkqId + "", 16);
									// 返回读卡器指令
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String date = df.format(new Date());
									sessionmap = cz(jaString, clientIp);
									String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
											+ dkqId + "','" + khId + "','语音提示已关闭','成功','已交费','" + date + "')";
									ps = connc.prepareStatement(SkqInsert);
									ps.execute();
								 
								 logs.info("关阀门--刷卡器发送数据"+jaString);
								 cz(jaString, clientIp);
							}else{
								try
								{
									Thread.sleep(100);
								} catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								// 发送开阀门指令
								sessionmap = cz(SJtring, clientIp);
							try
							{
								Thread.sleep(3000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}

							String kfm = MapUtils.getMapUtils().get("Kfsuc");
							// 判断开阀门是否成功
							if (kfm == null)
							{
								// fmId十进制
								SJtring = "F0160900" + qgID + "040" + fmd + "01FFFF01FFFFFF";
								MapUtils.getMapUtils().add("param", "kFm");
								// 发送开阀门指令
								sessionmap = cz(SJtring, clientIp);
							}
							// 停留
							try
							{
								Thread.sleep(3000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							
							if (sessionmap == true)
							{	
								MapUtils.getMapUtils().add("Kfsuc", null);
								// 如果查出数据则插入数据库刷卡器表
								int dkqId = Integer.parseInt("" + DkqId + "", 16);
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = df.format(new Date());
								String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
										+ dkqId + "','" + khId + "','开阀门','成功','已交费','" + date + "')";
								ps = connc.prepareStatement(SkqInsert);
								ps.execute();
								try
								{
									Thread.sleep(3000);
								} catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								// 返回读卡器指令
								jaString = "F00F3B00" + DkqId + "" + KhId + "01";
								sessionmap = cz(jaString, clientIp);
								logs.info("开阀门--刷卡器发送数据"+jaString);
								try
								{
									Thread.sleep(1000);
								} catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								// 读阀门发送指令
								SJtring = "F00F0400" + qgID + "040" + fmd;
								logs.info("读阀门发送指令数据：" + SJtring);
								sessionmap = cz(SJtring, clientIp);
							} else
							{
								// 如果查出数据则插入数据库刷卡器表
								int dkqId = Integer.parseInt("" + DkqId + "", 16);
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = df.format(new Date());
								String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
										+ dkqId + "','" + khId + "','开阀门','通讯失败','已交费','" + date + "')";
								ps = connc.prepareStatement(SkqInsert);
								ps.execute();
							}
						}
						} else if (dfStatus!= null && dfStatus.equals("开")&& fmID!=null&&fmID.equals("0"+fmd+""))
						{
							if(SFJF!=null && SFJF.equals("是")){
								try
								{
									Thread.sleep(1000);
								} catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								// 如果查出数据则插入数据库刷卡器表
								int dkqId = Integer.parseInt("" + DkqId + "", 16);
								// 返回读卡器指令
								jaString = "F00F3B00" + DkqId + "" + KhId + "01";
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = df.format(new Date());
								sessionmap = cz(jaString, clientIp);
								String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
										+ dkqId + "','" + khId + "','语音提示已开阀','成功','已交费','" + date + "')";
								ps = connc.prepareStatement(SkqInsert);
								ps.execute();
							}else{
								
							MapUtils.getMapUtils().add("df", null);

							SJtring = "F0160900" + qgID + "040" + fmd + "00FFFF01FFFFFF";
							MapUtils.getMapUtils().add("param", "gFm");
							// 发送关阀指令
							sessionmap = cz(SJtring, clientIp);
							try
							{
								Thread.sleep(3000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							String Gfm = MapUtils.getMapUtils().get("Gfsuc");
							if (Gfm == null)
							{
								SJtring = "F0160900" + qgID + "040" + fmd + "00FFFF01FFFFFF";
								MapUtils.getMapUtils().add("param", "gFm");
								// 发送关阀指令
								sessionmap = cz(SJtring, clientIp);
							}
							try
							{
								Thread.sleep(3000);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							if (sessionmap == true)
							{	
								MapUtils.getMapUtils().add("Gfsuc", null);
								// 如果查出数据则插入数据库刷卡器表
								int dkqId = Integer.parseInt("" + DkqId + "", 16);
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = df.format(new Date());
								String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
										+ dkqId + "','" + khId + "','关阀门','成功','已交费','" + date + "')";
								ps = connc.prepareStatement(SkqInsert);
								ps.execute();
								try
								{
									Thread.sleep(3000);
								} catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								// 返回读卡器指令
								 jaString = "F00F3B00" + DkqId + "" + KhId + "02";
								 logs.info("关阀门--刷卡器发送数据"+jaString);
								sessionmap = cz(jaString, clientIp);
								try
								{
									Thread.sleep(1000);
								} catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								// 读阀门发送指令
								SJtring = "F00F0400" + qgID + "040" + fmd;
								logs.info("读阀门发送指令数据：" + SJtring);
								sessionmap = cz(SJtring, clientIp);
								
							} else
							{

								// 如果查出数据则插入数据库刷卡器表
								int dkqId = Integer.parseInt("" + DkqId + "", 16);
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = df.format(new Date());
								String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
										+ dkqId + "','" + khId + "','','通讯失败','已交费','" + date + "')";
								ps = connc.prepareStatement(SkqInsert);
								ps.execute();
							}
							// 1未交费
							}
						} else
						{
							// 如果查出数据则插入数据库刷卡器表
							int dkqId = Integer.parseInt("" + DkqId + "", 16);
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String date = df.format(new Date());
							String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
									+ dkqId + "','" + khId + "','','通讯失败','已交费','" + date + "')";
							ps = connc.prepareStatement(SkqInsert);
							ps.execute();
						}
						try
						{
							DatabaseUtil.close(Oraclerst, Oracleps, Oracleconnc);
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					} else
					{
						// 如果查出数据则插入数据库刷卡器表
						int dkqId = Integer.parseInt("" + DkqId + "", 16);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String date = df.format(new Date());
						String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('"
								+ dkqId + "','" + khId + "','','失败','未交费','" + date + "')";
						ps = connc.prepareStatement(SkqInsert);
						ps.execute();
						try
						{
							Thread.sleep(3000);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						// 返回读卡器指令，未交费
						String jaString = "F00F3B00" + DkqId + "" + KhId + "03";
						sessionmap = cz(jaString, clientIp);
					}
				} else
				{

					// 如果查出数据则插入数据库刷卡器表
					int dkqId = Integer.parseInt("" + DkqId + "", 16);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = df.format(new Date());
					String SkqInsert = "insert into Skq (SkqSbh,Kh,fmState,skSuccess,SfJf,DateTime) values('" + dkqId
							+ "','" + khId + "','','失败','未交费','" + date + "')";
					ps = connc.prepareStatement(SkqInsert);
					ps.execute();
					try
					{
						Thread.sleep(3000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					// 返回读卡器指令,未交费
					String jaString = "F00F3B00" + DkqId + "" + KhId + "03";
					sessionmap = cz(jaString, clientIp);
				}
			}
		}

	}

	
	public void XCgq(byte[] base){
		
		logs.info("修改无线传感器地址接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 6)
			{
				// 接收的数据
				String stringH = Utils.bytesToHexString(base);
				// 转换为大写
				String stringHandler = CzUtil.Uppercase(stringH).toString();
				// 截取效验数据
				String jy = CzUtil.getJy(stringHandler);
				// 判断开始和结束
				String start = null;
				String end = null;
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
				// 判断和校验
				String je = CzUtil.getJe(stringHandler);
				if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
				{
						String subSutaus = stringHandler.substring(stringHandler.length() - 6, stringHandler.length() - 4);
						if(subSutaus.equals("00")){
							MapUtils.getMapUtils().add("Xcgq", "success");	
							logs.info("修改成功");
						}else{
							MapUtils.getMapUtils().add("Xcgq", "fail");
							logs.info("修改失败");
						}
						
					
				} else
				{
					MapUtils.getMapUtils().add("Xcgq", "fail");
					logs.info("修改失败");
				}
			} else
			{

				MapUtils.getMapUtils().add("Xcgq", "cs");
				logs.info("修改超时");
			}
		}
		
	}
	// 读取阀门ID
	public void DqfmId(byte[] base)
	{
		logs.info("读阀接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 6)
			{
				// 接收的数据
				String stringH = Utils.bytesToHexString(base);
				// 转换为大写
				String stringHandler = CzUtil.Uppercase(stringH).toString();
				// 截取效验数据
				String jy = CzUtil.getJy(stringHandler);
				// 判断开始和结束
				String start = null;
				String end = null;
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
				// 判断和校验
				String je = CzUtil.getJe(stringHandler);
				String fmString = "";
				if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
				{
					if (stringHandler.length() > 20)
					{
						logs.info("读阀成功：" + stringHandler);
						String subString = stringHandler.substring(16, stringHandler.length() - 4);
						for (int i = 0; i < subString.length(); i++)
						{

							fmString += Integer.parseInt(subString.substring(i, i + 8), 16) + " ";
							i = i + 7;
						}
						MapUtils.getMapUtils().add("fmId", fmString);
						MapUtils.getMapUtils().add("DqfmId", "success");
						logs.info("读阀成功");
					}
				} else
				{
					MapUtils.getMapUtils().add("DqfmId", "fail");
					logs.info("读阀失败");
				}
			} else
			{

				MapUtils.getMapUtils().add("DqfmId", "cs");
				logs.info("读阀超时");
			}
		}
	}

	// 添加阀门ID
	public void tjfmId(byte[] base)
	{
		logs.info("添加阀门ID接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{

			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 8)
			{
				// 接收的数据
				String stringH = Utils.bytesToHexString(base);
				// 转换为大写
				String stringHandler = CzUtil.Uppercase(stringH).toString();
				// 截取效验数据
				String jy = CzUtil.getJy(stringHandler);
				// 判断开始和结束
				String start = null;
				String end = null;
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
				String je = CzUtil.getJe(stringHandler);
				if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
				{
					logs.info("添加阀门ID成功：" + stringHandler);
					logs.info("添加阀门ID成功");
					MapUtils.getMapUtils().add("tjfmId", "success");

				} else
				{
					MapUtils.getMapUtils().add("tjfmId", "fail");
					logs.info("添加阀门ID失败");
				}
			} else
			{
				MapUtils.getMapUtils().add("tjfmId", "cs");
				logs.info("添加阀门ID超时");
			}
		}

	}

	// 更新区管ID
	public void UpQg(byte[] base)
	{
		logs.info("更新区管ID接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 6)
			{
				// 接收的数据
				String stringH = Utils.bytesToHexString(base);
				// 转换为大写
				String stringHandler = CzUtil.Uppercase(stringH).toString();
				// 截取效验数据
				String jy = CzUtil.getJy(stringHandler);
				// 判断开始和结束
				String start = null;
				String end = null;
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
				String je = CzUtil.getJe(stringHandler);
				if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
				{
					String jeq = stringHandler.substring(16, 18);
					if (jeq.equals("01"))
					{
						logs.info("更新区管ID成功：" + stringHandler);
						logs.info("更新区管ID成功");
						MapUtils.getMapUtils().add("UpQg", "success");
					} else
					{
						logs.info("更新区管ID失败");
						MapUtils.getMapUtils().add("UpQg", "fail");
					}
				}
			} else
			{
				MapUtils.getMapUtils().add("UpQg", "cs");
				logs.info("更新区管ID超时");
			}
		}
	}

	// 区管查询
	public void qgCx(byte[] base,Connection connc) throws SQLException, ClassNotFoundException
	{
		logs.info("区管查询接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 6)
			{
				String qgId = null;
				SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 获取发送的时间
				String time = Sdate.format(new Date());
				// 接收的数据
				String stringH = Utils.bytesToHexString(base);
				// 转换为大写
				String stringHandler = CzUtil.Uppercase(stringH).toString();

				// 截取效验数据
				String jy = CzUtil.getJy(stringHandler);
				// 判断开始和结束
				String start = null;
				String end = null;
				start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
				end = stringHandler.charAt(stringHandler.length() - 2) + ""
						+ stringHandler.charAt(stringHandler.length() - 1);
				String je = CzUtil.getJe(stringHandler);
				if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
				{
					// 截取qgID,
					qgId = stringHandler.substring(8, 16);
					// 截取是否为成功
					String sT = stringHandler.substring(16, 18);

					if (sT.equals("01"))
					{
						String sql = "update T_QgInfo set QgStatus='1',RecordTime='" + time + "' where QgID='" + qgId
								+ "'";
						ps = connc.prepareStatement(sql);
						rs = ps.executeUpdate();
						logs.info("区管查询成功：" + stringHandler);
						MapUtils.getMapUtils().add("jzq", "success");
						logs.info("区管查询成功");
					} else
					{
						MapUtils.getMapUtils().add("jzq", "fail");
						logs.info("区管查询失败");
					}
				} else
				{
					MapUtils.getMapUtils().add("jzq", "fail");
					logs.info("区管查询失败");
				}
			} else
			{
				MapUtils.getMapUtils().add("jzq", "cs");
				logs.info("区管查询超时");
			}
		}
	}

	// 集中器查询
	public void jzqCx(byte[] base ,Connection connc) throws SQLException, ClassNotFoundException
	{
		logs.info("集中器查询状态接收数据：" + Utils.bytesToHexString(base));
		String jzqPort = null;
		SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String time = Sdate.format(new Date());
		// 接收的数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取jzqID
		String JzqId = stringHandler.substring(8, 16);

		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);
		String je = CzUtil.getJe(stringHandler);
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			// 根据集中器 ID查找集中器端口
			String sqlcx = "select JzqPort from T_JzqInfo where JzqID='" + JzqId + "'";
			ps = connc.prepareStatement(sqlcx);
			rst = ps.executeQuery();
			int col = rst.getMetaData().getColumnCount();
			while (rst.next())
			{
				jzqPort = rst.getString("jzqPort");
			}
			// 根据集中器ID更新集中器端口
			String sql = "update T_JzqInfo set JzqPort='" + jzqPort + "',Status='1',UpdateTime='" + time
					+ "' where JzqID='" + JzqId + "'";
			ps = connc.prepareStatement(sql);
			rs = ps.executeUpdate();
			
			logs.info("集中器查询状态成功接收数据：" + stringHandler);
		}
	}

	// 阀门故障查询
	public void gzcx(byte[] base,Connection connc) throws SQLException, ClassNotFoundException
	{
		logs.info("阀门故障查询接收数据：" + Utils.bytesToHexString(base));
		// 接收的数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);
		String je = CzUtil.getJe(stringHandler);
		String ycString = "";
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			logs.info("阀门故障查询成功：" + stringHandler);
			String fmID = stringHandler.substring(32, 40);
			String dk = stringHandler.substring(40, 42);
			String gb = stringHandler.substring(42, 44);
			String kd = stringHandler.substring(44, 46);
			String kt = stringHandler.substring(46, 48);
			String fmwd = stringHandler.substring(48, 50);
			String cgqtx = stringHandler.substring(50, 52);
			String cgqwd = stringHandler.substring(52, 54);

			SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = Sdate.format(new Date());
			// 十六进制转换为十进制数
			int fmId = Integer.parseInt(fmID, 16);
			if (dk.equals("01"))
			{

				ycString += "阀门打开异常,";
			}
			if (gb.equals("01"))
			{
				ycString += "阀门关闭异常,";
			}
			if (kd.equals("01"))
			{
				ycString += "阀门开度异常,";
			}
			if (kt.equals("01"))
			{
				ycString += "阀门壳体拆开,";
			}
			if (fmwd.equals("01"))
			{
				ycString += "超温报警,";
			}
			if (cgqtx.equals("01"))
			{
				ycString += "失联,";
			}
			if (cgqwd.equals("01"))
			{
				ycString += "失联";
			}
			String Insql = "insert into T_AlarmInfor values ('阀门','" + fmId + "','" + ycString + "','" + time
					+ "')";
			String Upsql = "update T_AlarmInforsX set ErrInfor='" + ycString + "',RecordTime='" + time + "' where DeviceID='" + fmId + "'";
			ps = connc.prepareStatement(Insql);
			ps.execute();
			ps = connc.prepareStatement(Upsql);
			rs = ps.executeUpdate();
		}
	}

	// 读取传感器地址
	public void dcgq(byte[] base,Connection connc) throws SQLException, ClassNotFoundException
	{
		logs.info("读取传感器地址接收数据：" + Utils.bytesToHexString(base));
		int ms = 0;
		String stringH = Utils.bytesToHexString(base);
		SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间

		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = Sdate.format(new Date());
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 120)
			{
				if (stringH != null && jTime != null)
				{

					String jy = CzUtil.getJy(stringHandler);
					// 判断开始和结束
					String start = null;
					String end = null;
					start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
					end = stringHandler.charAt(stringHandler.length() - 2) + ""
							+ stringHandler.charAt(stringHandler.length() - 1);
					String je = CzUtil.getJe(stringHandler);

					if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
					{

						// 截取阀门ID
						String fmId = stringHandler.substring(22, 30);
						String cgqID = stringHandler.substring(30, 38);
						int fmID= Integer.parseInt(fmId , 16);
						int cgqId= Integer.parseInt(cgqID , 16);
						String Insql = "insert into dcgq values ('" + fmID + "','" + cgqId + "')";
						ps = connc.prepareStatement(Insql);
						ps.execute();
						MapUtils.getMapUtils().add("dcgq", "success");
						logs.info("读取传感器地址成功：" + stringHandler);
						logs.info("读取传感器地址成功");
					} else
					{
						MapUtils.getMapUtils().add("dcgq", "fail");
						logs.info("读取传感器地址失败");
					}
				}
			} else
			{
				logs.info("读取传感器地址超时");
				MapUtils.getMapUtils().add("dcgq", "超时");
			}
		}
	}

	// 读阀
	@SuppressWarnings("static-access")
	public void df(byte[] base,Connection connc) throws SQLException, ClassNotFoundException
	{
		logs.info("读阀接收数据：" + Utils.bytesToHexString(base));
		String stringH = Utils.bytesToHexString(base);
		SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = Sdate.format(new Date());
		// 时间相减
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		int ms = 0;
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 60)
			{
				if (stringH != null)
				{
					// 转换为大写
					String stringHandler = CzUtil.Uppercase(stringH).toString();
					String jy = CzUtil.getJy(stringHandler);
					// 判断开始和结束
					String start = null;
					String end = null;
					int tqyb =0;
					 String RoomTemp;
					start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
					end = stringHandler.charAt(stringHandler.length() - 2) + ""
							+ stringHandler.charAt(stringHandler.length() - 1);
					String je = CzUtil.getJe(stringHandler);
					if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
					{
						logs.info("读阀成功：" + stringHandler);
						// 区管ID
						String qgID = stringHandler.substring(8, 16);
						// 阀门ID
						String fmID = stringHandler.substring(18, 26);
						// 阀门状态
						String fmState = stringHandler.substring(26, 28);
						// 阀门开度
						String fmkd = stringHandler.substring(28, 30);// 十进制
						// 阀门锁定
						String fmLock = stringHandler.substring(30, 32);
						// 缴费标识
						String fmJF = stringHandler.substring(32, 34);
						// 阀门温度
						String fmTemp = stringHandler.substring(34, 38);// 十进制
						// 室内温度
						String fmRoomTemp = stringHandler.substring(38, 42);// 十进制
						// 调节周期
						String HTemSet = stringHandler.substring(42, 44);// 十进制
						// 设定温度
						String MTemSet = stringHandler.substring(44, 46);// 十进制
						// 调节参数
						String LTemSet = stringHandler.substring(46, 48);// 十进制
						String fms = fmTemp.substring(0, 2);
						String fmf = fmTemp.substring(2);
						String fmRs = fmRoomTemp.substring(0, 2);
						String fmRf = fmRoomTemp.substring(2);
						// 转换十进制
						int Fms = Integer.parseInt("" + fms + "", 16);
						int Fmf = Integer.parseInt("" + fmf + "", 16);
						int FmRs = Integer.parseInt("" + fmRs + "", 16);
						int FmRf = Integer.parseInt("" + fmRf + "", 16);
						int FmID = Integer.parseInt("" + fmID + "", 16);
						int Fmkd = Integer.parseInt("" + fmkd + "", 16);
						int hTemSet = Integer.parseInt("" + HTemSet + "", 16);
						int mTemSet = Integer.parseInt("" + MTemSet + "", 16);
						int lTemSet = Integer.parseInt("" + LTemSet + "", 16);
						String fmS = String.valueOf(Integer.parseInt("" + Fms + ""));
						String fmF = String.valueOf(Integer.parseInt("" + Fmf + ""));
						String fmRS = String.valueOf(Integer.parseInt("" + FmRs + ""));
						String fmRF = String.valueOf(Integer.parseInt("" + FmRf + ""));
						// 阀门温度
						String FmTemp = fmS + "." + fmF;
						// 室内温度
						String FmRoomTemp = fmRS + "." + fmRF;
						if (fmState.equals("00"))
						{
							fmState = "关";
						} else
						{
							fmState = "开";
						}
						if (fmLock.equals("00"))
						{
							fmLock = "否";
						} else
						{
							fmLock = "是";
						}
						if (fmJF.equals("00"))
						{
							fmJF = "否";
						} else
						{
							fmJF = "是";
						}
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String date = df.format(new Date());
						String sqlcx = "select top 1 Tqyb from T_Cbtq order by id desc ";
						ps = connc.prepareStatement(sqlcx);
						rst = ps.executeQuery();
						int col = rst.getMetaData().getColumnCount();
						while (rst.next())
						{
							tqyb = rst.getInt("Tqyb");
						}
						if(Double.valueOf(FmRoomTemp).intValue()<40){
						
						String Upsql = "update T_FmInfo set QgID='" + qgID + "',Status='" + fmState + "',FamKd='" + Fmkd
								+ "',LockSt='" + fmLock + "',JFSt='" + fmJF + "',valTemp='" + FmTemp + "',RoomTemp='"
								+ FmRoomTemp + "',HTemSet='" + hTemSet + "',MTemSet='" + mTemSet + "',LTemSet='"
								+ lTemSet + "',RecordTime='" + date + "',Tqyb='"+tqyb+"' where ValAd='" + FmID + "'";
						ps = connc.prepareStatement(Upsql);
						rs = ps.executeUpdate();
						}
						String Insql = "insert into T_FmHistory values ('" + FmID + "','" + fmState + "','" + Fmkd
								+ "','" + fmLock + "','" + fmJF + "'," + "'" + FmTemp + "','" + FmRoomTemp + "','"
								+ hTemSet + "','" + mTemSet + "','" + lTemSet + "','" + date + "','"+tqyb+"')";
						ps = connc.prepareStatement(Insql);
					
						ps.execute();
						//获取刷卡阀门状态
						MapUtilsSkq.getMapUtils().add("df", fmState);
						//获取刷卡阀门地址
						MapUtilsSkq.getMapUtils().add("fmID", fmID);
						
						if(MapUtilsDf.getMapUtils().get("dFmParam")!=null&&MapUtilsDf.getMapUtils().get("dFmParam").equals(""+FmID+"")){
							MapUtilsDf.getMapUtils().add("dFm", "success");
						}else if(MapUtilsDf.getMapUtils().get("PlDF")!=null && MapUtilsDf.getMapUtils().get("PlDF").equals("sj")){
							MapUtilsDf.getMapUtils().add("PldFm", "success");
						}
						logs.info("读阀成功");

					} else if(stringHandler.length()>52){
						if(stringHandler.contains("FFF")){
							String[] strings=stringHandler.split("FFF");
						    for(int i=0;i<strings.length;i++){
						    	if(i==0){
						    		String string=strings[0]+"ff";
						    		if(string.length()==52){
						    			cs(string,connc);	
						    		}		
						    	}else if(i==strings.length-1){
						    		
						    		String string2="f"+strings[i];
						    		if(string2.length()==52){
						    			cs(string2,connc);	
						    		}
	
						    	}else{
						    		String string3="f"+strings[i]+"ff";
						    		if(string3.length()==52){
						    			cs(string3,connc);	
						    		}
						    	}
						    }   
						    
						}else{
							MapUtilsDf.getMapUtils().add("dFm", "fail");
							logs.info("读阀失败");
						}	
					}else
					{
						MapUtilsDf.getMapUtils().add("dFm", "fail");
						logs.info("读阀失败");
					}
				} else
				{
					MapUtilsDf.getMapUtils().add("dFm", "超时");
					logs.info("读阀超时");
				}
			} else
			{
				MapUtilsDf.getMapUtils().add("dFm", "超时");
			}
		}
	}

	public void cs(String stringHandler,Connection connc) throws SQLException{
		int tqyb =0;
		String je = CzUtil.getJe(stringHandler);
		String jy = CzUtil.getJy(stringHandler);
		if ( je.equals("" + jy + ""))
		{
			logs.info("读阀成功：" + stringHandler);
			// 区管ID
			String qgID = stringHandler.substring(8, 16);
			// 阀门ID
			String fmID = stringHandler.substring(18, 26);
			// 阀门状态
			String fmState = stringHandler.substring(26, 28);
			// 阀门开度
			String fmkd = stringHandler.substring(28, 30);// 十进制
			// 阀门锁定
			String fmLock = stringHandler.substring(30, 32);
			// 缴费标识
			String fmJF = stringHandler.substring(32, 34);
			// 阀门温度
			String fmTemp = stringHandler.substring(34, 38);// 十进制
			// 室内温度
			String fmRoomTemp = stringHandler.substring(38, 42);// 十进制
			// 调节周期
			String HTemSet = stringHandler.substring(42, 44);// 十进制
			// 设定温度
			String MTemSet = stringHandler.substring(44, 46);// 十进制
			// 调节参数
			String LTemSet = stringHandler.substring(46, 48);// 十进制
			String fms = fmTemp.substring(0, 2);
			String fmf = fmTemp.substring(2);
			String fmRs = fmRoomTemp.substring(0, 2);
			String fmRf = fmRoomTemp.substring(2);
			// 转换十进制
			int Fms = Integer.parseInt("" + fms + "", 16);
			int Fmf = Integer.parseInt("" + fmf + "", 16);
			int FmRs = Integer.parseInt("" + fmRs + "", 16);
			int FmRf = Integer.parseInt("" + fmRf + "", 16);
			int FmID = Integer.parseInt("" + fmID + "", 16);
			int Fmkd = Integer.parseInt("" + fmkd + "", 16);
			int hTemSet = Integer.parseInt("" + HTemSet + "", 16);
			int mTemSet = Integer.parseInt("" + MTemSet + "", 16);
			int lTemSet = Integer.parseInt("" + LTemSet + "", 16);
			String fmS = String.valueOf(Integer.parseInt("" + Fms + ""));
			String fmF = String.valueOf(Integer.parseInt("" + Fmf + ""));
			String fmRS = String.valueOf(Integer.parseInt("" + FmRs + ""));
			String fmRF = String.valueOf(Integer.parseInt("" + FmRf + ""));
			// 阀门温度
			String FmTemp = fmS + "." + fmF;
			// 室内温度
			String FmRoomTemp = fmRS + "." + fmRF;
			if (fmState.equals("00"))
			{
				fmState = "关";
			} else
			{
				fmState = "开";
			}
			if (fmLock.equals("00"))
			{
				fmLock = "否";
			} else
			{
				fmLock = "是";
			}
			if (fmJF.equals("00"))
			{
				fmJF = "否";
			} else
			{
				fmJF = "是";
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			String sqlcx = "select top 1 Tqyb from T_Cbtq order by id desc ";
			ps = connc.prepareStatement(sqlcx);
			rst = ps.executeQuery();
			int col = rst.getMetaData().getColumnCount();
			while (rst.next())
			{
				tqyb = rst.getInt("Tqyb");
			}
			if(Double.valueOf(FmRoomTemp).intValue()<40){
			
			String Upsql = "update T_FmInfo set QgID='" + qgID + "',Status='" + fmState + "',FamKd='" + Fmkd
					+ "',LockSt='" + fmLock + "',JFSt='" + fmJF + "',valTemp='" + FmTemp + "',RoomTemp='"
					+ FmRoomTemp + "',HTemSet='" + hTemSet + "',MTemSet='" + mTemSet + "',LTemSet='"
					+ lTemSet + "',RecordTime='" + date + "',Tqyb='"+tqyb+"' where ValAd='" + FmID + "'";
			ps = connc.prepareStatement(Upsql);
			rs = ps.executeUpdate();
			}
			String Insql = "insert into T_FmHistory values ('" + FmID + "','" + fmState + "','" + Fmkd
					+ "','" + fmLock + "','" + fmJF + "'," + "'" + FmTemp + "','" + FmRoomTemp + "','"
					+ hTemSet + "','" + mTemSet + "','" + lTemSet + "','" + date + "','"+tqyb+"')";
			ps = connc.prepareStatement(Insql);
			ps.execute();
			//获取刷卡阀门状态
			MapUtilsSkq.getMapUtils().add("df", fmState);
			//获取刷卡阀门地址
			MapUtilsSkq.getMapUtils().add("fmID", fmID);
			
			if(MapUtilsDf.getMapUtils().get("dFmParam")!=null&&MapUtilsDf.getMapUtils().get("dFmParam").equals(""+FmID+"")){
				MapUtilsDf.getMapUtils().add("dFm", "success");
			}else if(MapUtilsDf.getMapUtils().get("PlDF")!=null && MapUtilsDf.getMapUtils().get("PlDF").equals("sj")){
				MapUtilsDf.getMapUtils().add("PldFm", "success");
			}
			logs.info("读阀成功");
		}else{
			MapUtilsDf.getMapUtils().add("dFm", "fail");
			logs.info("读阀失败");
		}
	}
	
	
	// 当执行开关指令时候执行
	public void kg(byte[] base,Connection connc) throws SQLException, ClassNotFoundException
	{
		logs.info("执行开关指令接收数据：" + Utils.bytesToHexString(base));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String fTime = MapUtils.getMapUtils().get("time");
		String jTime = df.format(new Date());
		int ms = 0;
		String sumTime = CzUtil.getSubtract(fTime, jTime);
		if (sumTime == null)
		{
			sumTime = "00:00:01";
		}
		if (sumTime != null)
		{
			ms = CzUtil.transform(sumTime);
			// 判断时间是否超时
			if (ms <= 60)
			{
				// 转换为十六进制
				String stringH = Utils.bytesToHexString(base);
				if (stringH != null)
				{
					String stringHandler = CzUtil.Uppercase(stringH).toString();
					// 截取效验数据
					String jy = CzUtil.getJy(stringHandler);
					// 判断开始和结束
					String start = null;
					String end = null;
					start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
					end = stringHandler.charAt(stringHandler.length() - 2) + ""
							+ stringHandler.charAt(stringHandler.length() - 1);
					String je = CzUtil.getJe(stringHandler);
					String string = MapUtils.getMapUtils().get("param");

					if (string != null)
					{
						// 截取状态
						String Status = stringHandler.substring(stringHandler.length() - 6, stringHandler.length() - 4);

						if (string.equals("kFm"))
						{
							// 截取阀门ID
							String sub = stringHandler.substring(16, 24);
							// 把十六进制数，转换为十进制
							int pm = Integer.parseInt("" + sub + "", 16);
							if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
							{
								logs.info("开阀成功：" + stringHandler);
								if (Status.equals("00"))
								{
									String params = "kFm";
									String sql = "update T_FmInfo set Status='开' where ValAd='" + pm + "'";
									ps = connc.prepareStatement(sql);
									rs = ps.executeUpdate();
									logs.info("开阀是否成功====" + rs);
									if (rs == 1&& MapUtils.getMapUtils().get("kfSuc").equals(""+pm+""))
									{
										// MapUtils.getMapUtils().add("kfSt",
										// "1");
										MapUtils.getMapUtils().add(params, "success");
									} else
									{
										MapUtils.getMapUtils().add("sb", "fail");
									
									}
									logs.info("开阀是否成功====" + rs);
									MapUtils.getMapUtils().add("Kfsuc", "kf");
									logs.info("开阀成功");
								} else
								{
									MapUtils.getMapUtils().add("sb", "fail");
									logs.info("开阀失败");
								}

							} else
							{
								MapUtils.getMapUtils().add("sb", "fail");
								logs.info("开阀失败");
							}
						}
						if (string.equals("PlKFm"))
						{
							// 截取区管ID
							String subQg = stringHandler.substring(8, 16);
							String params = "PlKFm";
							if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
							{
								logs.info("批量开阀成功：" + stringHandler);
								if (Status.equals("00"))
								{

									String sql = "update T_FmInfo set Status='开' where QgID='" + subQg + "'";
									ps = connc.prepareStatement(sql);
									rs = ps.executeUpdate();
                                    if(MapUtils.getMapUtils().get("PlKf").equals(""+subQg+"")){
									MapUtils.getMapUtils().add(params, "success");
                                    }
									logs.info("批量开阀成功");

								} else
								{
									MapUtils.getMapUtils().add("sb", "fail");
									logs.info("批量开阀失败");
								}
							} else
							{
								MapUtils.getMapUtils().add("sb", "fail");
								logs.info("批量开阀失败");
							}
						}
						if (string.equals("PlGfm"))
						{
							// 截取区管ID
							String subQg = stringHandler.substring(8, 16);
							String params = "PlGfm";
							if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
							{
								logs.info("批量关阀成功：" + stringHandler);
								if (Status.equals("00"))
								{

									String sql = "update T_FmInfo set Status='关' where QgID='" + subQg + "'";
									ps = connc.prepareStatement(sql);
									rs = ps.executeUpdate();
									if(MapUtils.getMapUtils().get("PlGf").equals(""+subQg+"")){
										
									MapUtils.getMapUtils().add(params, "success");
									}
									logs.info("批量关阀成功");
								} else
								{
									MapUtils.getMapUtils().add("sb", "fail");
									logs.info("批量关阀失败");
								}
							} else
							{
								MapUtils.getMapUtils().add("sb", "fail");
								logs.info("批量关阀失败");
							}

						}
						if (string.equals("gFm"))
						{
							// 截取区管ID
							String sub = stringHandler.substring(16, 24);
							// 把十六进制数，转换为十进制相加
							int pm = Integer.parseInt("" + sub + "", 16);
							if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
							{
								logs.info("关阀成功：" + stringHandler);
								if (Status.equals("00"))
								{
									String params = "gFm";

									String sql = "update T_FmInfo set Status='关' where ValAd='" + pm + "'";
									ps = connc.prepareStatement(sql);
									rs = ps.executeUpdate();
									if (rs == 1 && MapUtils.getMapUtils().get("gfSuc").equals(""+pm+""))
									{
										MapUtils.getMapUtils().add(params, "success");
									} else
									{
										MapUtils.getMapUtils().add("sb", "fail");
									}
									logs.info("关阀是否成功====" + rs);
									MapUtils.getMapUtils().add("Gfsuc", "Gf");
									logs.info("关阀成功");
								} else
								{
									MapUtils.getMapUtils().add("sb", "fail");
									logs.info("关阀失败");
								}
							} else
							{
								MapUtils.getMapUtils().add("sb", "fail");
								logs.info("关阀失败");
							}
						}

						if (string.equals("FsCs"))
						{
							String params = "FsCs";
							if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
							{
								if (Status.equals("00"))
								{

									MapUtils.getMapUtils().add(params, "success");
									logs.info("发送参数成功");
								} else
								{
									MapUtils.getMapUtils().add("sb", "fail");
									logs.info("发送参数失败");
								}
							} else
							{
								MapUtils.getMapUtils().add("sb", "fail");
								logs.info("发送参数失败");
							}
						}

					} else
					{
						MapUtils.getMapUtils().add("sb", "fail");
						logs.info("失败");
					}

				}
			} else
			{
				MapUtils.getMapUtils().add("cs", "超时");
				logs.info("超时");
			}
		}
	}

	/**
	 * 当连接进入空闲状态时调用̬
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception
	{
		logs.info("当前连接"+ session.getRemoteAddress()+"处于空闲状态:{}"+status);
	}

	/**
	 * 当消息已经送给客户端后触发此方法
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception
	{
		logs.info("服务器发送给"+session.getRemoteAddress()+"的消息:"+message);
	}

	/**
	 * 当关闭时调用
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception
	{
		@SuppressWarnings("deprecation")
		CloseFuture closeFuture = session.close(true);
		closeFuture.addListener(new IoFutureListener<IoFuture>()
		{
			public void operationComplete(IoFuture future)
			{
				if (future instanceof CloseFuture)
				{
					((CloseFuture) future).setClosed();
					logs.info("sessionClosed CloseFuture setClosed-->"+ future.getSession().getId());
				}
			}
		});
		sessionMap.remove(session);
		logs.info("关闭当前session："+ session.getId()+ session.getRemoteAddress()+"..已移除");
	}

}
