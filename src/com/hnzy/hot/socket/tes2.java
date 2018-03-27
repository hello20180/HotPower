package com.hnzy.hot.socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hnzy.hot.socket.util.DatabaseUtil;
import com.hnzy.hot.socket.util.oracleHelper;

public class tes2
{
	public static void main(String[] args) throws SQLException
	{//连接数据库
		// 连接SQLServer数据库
		DatabaseUtil SqldbUtil = DatabaseUtil.getInstance();
		Connection Sqlconnc = SqldbUtil.getConnection();
		// 连接oracle数据库
		oracleHelper OracledbUtil = oracleHelper.getInstance();
		Connection Oracleconnc = OracledbUtil.getConnection();
		  DatabaseUtil dbUtil = DatabaseUtil.getInstance();   
	      Connection connc = dbUtil.getConnection(); 
	      ResultSet rstqg = null;
	      ResultSet rst;
	      PreparedStatement psqg = null;
	      PreparedStatement ps;
	      String fmId;
	      String state;
	      String sts;
		PreparedStatement Oracleps;
		ResultSet Oraclerst ;
		PreparedStatement Oraclep;
		ResultSet Oraclers ;
		int rs = 0;
		
		String YHBM =null;
		String xqName="嘉亿城市广场";
		String LH="58";
		String DYH="1";
		String CS="17";
		String SH="7";
		String LXDH="";
//		String xqName="";
//		String LH="";
//		String DYH="";
//		String CS="";
//		String SH="";
		String sfjf="";
//		String YHMC="";
		String jy="";
//		String oral="select  *  from SF_JMXXJF_V  where XQ='金领小区'and LH='2#' and DYH='1' and CS='12' and SH='2'";
		String oral="select * from SF_JMXXJF_V where XQ = '"+xqName+"' and LH='"+LH+"' and DYH='"+DYH+"' and CS='"+CS+"' and SH='"+SH+"'";
//		String oral="select  *  from SF_JMXXJF_V  where YHKH='8005554'";
//		String oral="select count(*) as jy from SF_JMXXJF_V where XQ = '"+xqName+"' and LH='"+LH+"' and DYH='"+DYH+"' and CS='"+CS+"' and SH='"+SH+"'";
//		String oral=" select * from sf_jmxx_v  where XQ='嘉亿城市广场'";
//		String oral=" select count(*) as jy from SF_JMXXJF_V  where XQ='嘉亿城市广场'";
		Oracleps = Oracleconnc.prepareStatement(oral);
		Oraclerst = Oracleps.executeQuery();
		int jfzt =3;
		String yhbm ="";
		String xq =null;
		String yhkh="";
		String Place="";
		String HESName="";
		double JFSJ;
		int col = Oraclerst.getMetaData().getColumnCount();
		System.out.println(col);
		while (Oraclerst.next())
		{
//			System.out.println("----------lll"+Double.valueOf(24.1).intValue());
//			jy=Oraclerst.getString("jy");
//			System.out.println("----jy------"+jy );
//			Place = Oraclerst.getString("FGS");
//			JFSJ = Oraclerst.getDouble("JFSJ");
			jfzt=Oraclerst.getInt("jfzt");
		 	xq=Oraclerst.getString("XQ");
		 	LH=Oraclerst.getString("LH");
		 	DYH = Oraclerst.getString("DYH");
		 	CS=Oraclerst.getString("CS");
		 	SH=Oraclerst.getString("SH");
		 	yhbm=Oraclerst.getString("YHBM");
		 	yhkh=Oraclerst.getString("YHKH");
		 	LXDH=Oraclerst.getString("LXDH");
//		 	System.out.println("--JFSJ---------"+JFSJ);
			System.out.println("Place----------"+Place);
			System.out.println("HESName----------"+HESName);
		 	System.out.println("LXDH----------"+LXDH);
		 	System.out.println("xq----------"+xq);
		 	System.out.println("LH----------"+LH);
			System.out.println("CS----------"+CS);
			System.out.println("SH----------"+SH);
			System.out.println("jfzt----------"+jfzt);
			System.out.println("yhkh----------"+yhkh);
			System.out.println("yhbm----------"+yhbm);
			
		}
//		if (jfzt==0)
//		{
//			sfjf="是";
//		}else{
//			sfjf ="否";
//		}
//		
//		String update="update T_YhInfo set SFJF='"+sfjf+"',YHBM='"+yhbm+"',IDNum='"+yhkh+"'  where XqName='"+xqName+"' and BuildNO='"+buildNo+"' and CellNO='"+cellNo+"' and HouseNO='"+houseNo+"'";
//		
//		ps = Sqlconnc.prepareStatement(update);
//		rs = ps.executeUpdate();
//	Oracleps.close();
//	Oracleconnc.close();
//	Sqlrst.close();
//	slqps.close();
//	ps.close();
//	Sqlconnc.close();
}
	 
}
