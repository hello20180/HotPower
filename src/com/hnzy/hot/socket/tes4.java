package com.hnzy.hot.socket;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hnzy.hot.socket.util.DatabaseUtil;
import com.hnzy.hot.socket.util.oracleHelper;
import java.io.BufferedReader;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.SocketTimeoutException;  
import java.net.URL;  
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.filefilter.FalseFileFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class tes4
{

    /** 
     *  
     * 获取实时天气1<br> 
     * 方 法 名： getTodayWeather <br> 
     *  
     * @param Cityid 
     *            城市编码 
     */  
    public static Map<String, Object> getTodayWeather1(String Cityid)  
            throws IOException, NullPointerException {  
        // 连接中央气象台的API  
        URL url = new URL("https://api.seniverse.com/v3/weather/now.json?key=ouotopxpj4qadrxg&location=%E9%83%91%E5%B7%9E&language=zh-Hans&unit=c");
        System.out.println(url);
        URLConnection connectionData = url.openConnection();  
        connectionData.setConnectTimeout(2000);  
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    connectionData.getInputStream(), "UTF-8"));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = br.readLine()) != null)  
                sb.append(line);  
            String datas = sb.toString();  

            System.out.println("输出1的结果：" + datas);  
        } catch (SocketTimeoutException e) {  
            System.out.println("连接超时");  
        } catch (FileNotFoundException e) {  
            System.out.println("加载文件出错");  
        }  
  
        return map;  
  
    }  
    
    public static String xml2JSON(String xml) {
    	return new XMLSerializer().read(xml).toString();
    	}

    
	public static void main(String[] args) throws SQLException, NullPointerException, IOException, ParseException
	{
//	    String string ="f01a0401c1e3b0cb040997277f0164000121061201061499cbfff01a0401c1e3b0cb04099727800164000124070e08061499d3ff";
//	    if(string.contains("fff")){
//	    	String[] strings=string.split("fff");
//		    for(int i=0;i<strings.length;i++){
//		    	if(i==0){
//		    		String string2=strings[0]+"ff";
//		    		System.out.println(string2);
//		    	}else if(i==strings.length-1){
//		    		String string2="f"+strings[i];
//		    		System.out.println(string2);
//		    	}else{
//		    		String string2="f"+strings[i]+"ff";
//		    		System.out.println(string2);
//		    	}
//		    }
//	    }
//	    
		
//		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
//		  Date  date = df.parse("2018-02-25T16:15:00+08:00");
//		System.out.println(date);
		
		
//		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
//		  Date  date = df.parse("2018-02-25T16:15:00+08:00");
//		  SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
//		  Date date1 =  df1.parse(date.toString());
//	    System.out.println(date1);
	    
	    
	    String date = "2018-02-25T16:40:00+08:00"; 
	    date = date.replace("Z", " UTC");//注意是空格+UTC
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");//注意格式化的表达式
	    Date d = format.parse(date );
	    System.out.println(d );
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dates = df.format(d);
		System.out.println(dates );
		
//		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
//		  Date  date = df.parse("2018-02-25T16:40:00+08:00");
//		  SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
//		  Date date1 =  df1.parse(date.toString());
//		  System.out.println(date1);
//		  DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		
		
	    
	}
	 
}
