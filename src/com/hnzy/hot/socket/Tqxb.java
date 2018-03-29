package com.hnzy.hot.socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.socket.util.MapUtils;

import oracle.net.aso.i;


public class Tqxb
{
	
	 /** 
     * 获取一周天气<br> 
     * 方 法 名：getWeekWeatherMap <br> 
     * @param Cityid  城市编码 
     */  
   
    public static Map<String, String> getTodayWeather1(String Cityid)  
            throws IOException, NullPointerException {
    	String result =null;
    	String date =null;
    	String sj =null;
    	Map<String, String > tqMap =new HashMap<>();
        // 连接中央气象台的API  
    	URL url = new URL("https://api.seniverse.com/v3/weather/now.json?key=ouotopxpj4qadrxg&location="+Cityid+"&language=zh-Hans&unit=c"); 
        URLConnection connectionData = url.openConnection();  
        connectionData.setConnectTimeout(1000);  
        Map<String, Object > map = new HashMap < String, Object>();  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    connectionData.getInputStream(), "UTF-8"));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = br.readLine()) != null)  
                sb.append(line);  
            String datas = sb.toString();  
            tqsT bni = JSONObject.parseObject(datas,tqsT.class);
             result=bni.getResults().get(0).getNow().getTemperature();
             tqMap.put("wd", result);
             sj=bni.getResults().get(0).getLast_update();
             date = sj.replace("Z", " UTC");//注意是空格+UTC
     	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");//注意格式化的表达式
     	    Date d;
			try
			{
				d = format.parse(date );
		     	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String dates = df.format(d);
		        tqMap.put("date", dates);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
     	   
           
        } 
        catch (SocketTimeoutException e) 
        {  
            System.out.println("连接超时");  
        }
        catch (FileNotFoundException e) 
        {  
            System.out.println("加载文件出错");  
        }  
        return tqMap;  
    } 	 
}
