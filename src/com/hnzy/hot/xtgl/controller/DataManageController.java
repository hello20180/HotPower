package com.hnzy.hot.xtgl.controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.util.DataBaseUtil;
import com.hnzy.hot.xtgl.pojo.DataManage;
import com.hnzy.hot.xtgl.service.DataManageService;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("DataManage")
public class DataManageController {
@Autowired
private DataManageService dataManageService;
@Autowired 
private RzService rzService;
@RequestMapping("findDataManage")
public String finddataManage(HttpServletRequest request,DataManage dataManage){
	dataManage=dataManageService.findDataManage();
	request.setAttribute("dm", dataManage);
	
	return "xtgl/DataManage/DataManage";
	
}
@RequestMapping("backupData")
public String  doSqlServerBackUp(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
	
	String mssqlBackupName=request.getParameter("mssqlBackupName");//自定义备份数据库名称
	String mssqlBackupPath=request.getParameter("mssqlBackupPath");//自定义备份数据库保存路径
	
	mssqlBackupPath=new String(mssqlBackupPath.getBytes("ISO-8859-1"), "UTF-8")+"";
	String dbName="DB_Znsbf";//被备份的数据库名称
  boolean flag=false;
  try {
	  File file = new File(mssqlBackupPath); 
	  if(!file.exists()){
		  file.mkdirs();
		  file=new File(mssqlBackupPath);
	  }
	   String path = file.getPath() + "\\"  
	+ mssqlBackupName + ".bak";//备份生成的数据路径及文件名 
	   String bakSql = "backup database " 
	+dbName+" to disk=? with init";//备份数据库SQL语句 
	   PreparedStatement bak = DataBaseUtil.getConnection() 
	     .prepareStatement(bakSql); 
	   bak.setString(1, path);//path必须是绝对路径 
	   bak.execute(); //备份数据库 
	   bak.close(); 
	   flag=true; 


} catch (SQLException e) {
	flag=false;
	e.printStackTrace();
}
  response.setCharacterEncoding("utf-8");
 if(flag==true){
	
		response.getWriter().print("<script type=\"text/javascript\">alert('SQLSERVER 备份成功！')</script>");
 }
		else {
			response.getWriter().print("<script type=\"text/javascript\">alert('SQLSERVER 备份失败！')</script>");
		}
 //日志
	Rz rz =new Rz();
	rz.setCz("数据备份文件名称："+mssqlBackupName);
	rz.setCzr((String)session.getAttribute("userName"));
	rz.setCzsj(new Date());
	rzService.insert(rz);
	return "xtgl/DataManage/success";
 }
}
