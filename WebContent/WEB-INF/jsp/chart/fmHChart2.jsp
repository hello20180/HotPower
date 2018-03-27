<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="../css/fmhistorypage.css" />
<link rel="stylesheet" type="text/css"
	href="../my-iconfont/1.0.8/iconfont.css" />
<style type="text/css">
.charts_grid {
	width: 60%;
	height: 60%;
	border: 1px solid #CCCCCC;
	margin: 10px;
}
h2{
padding-left: 50%;
padding-top: 0px;
margin-top: 0px;
}
</style>
<script src="../js/selectmenu.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.2.js"></script>
<script src="../js/jquery-2.2.2.min.js"></script>
<script src="../echarts-3.5.3/echarts.min.js"></script>
</head>
<body>

&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;
          <span style="padding-top: 2px; padding-left: 30px; margin-top: 2px;"> <a href="javascript:history.back(-1)">返回</a>
          </span>
         
          <% request.setCharacterEncoding("utf-8"); %>
          <% String xqName=new String(request.getParameter("xqName").getBytes("ISO8859-1"),"UTF-8");%>
          <% String cellNO=request.getParameter("cellNO"); %>
          <% String buildNO=request.getParameter("buildNO"); %>
          <% String houseNO=request.getParameter("houseNO"); %>
       
         &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;小区名字：<%=xqName %> &nbsp; 楼栋号：<%=buildNO %> &nbsp; 单元号：<%=cellNO %>&nbsp; 户号：<%=houseNO %>
<h2>历史数据</h2>
<div id="yhfmhistory" class="charts_grid" style="width: 1060px; height: 360px; margin: 0 auto"></div>
</body>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../echarts-3.5.3/echarts.min.js"></script>
<script type="text/javascript">
window.onresize = function(){
myChart.resize();
}
</script>
<script type="text/javascript">
var date = new Date();
var time = date.toLocaleDateString();
var myChart = echarts.init(document.getElementById('yhfmhistory'));
var option = {
title: {
    text: '温控阀记录',   
    subtext: time
},
tooltip: {
    trigger: 'axis'
},
legend: {
    data:[] 
},
toolbox: {
    show: true,
    feature: {
        magicType: {type: ['line', 'bar']},
        saveAsImage: {}
    }
},
dataZoom: [
    {   type: 'slider', 
        start: 50,      
        end: 100       
    },
    {    type: 'inside',
        start: 50,  
        end: 100  
    }
],
xAxis:  {
    type: 'category',
    boundaryGap: false,
    data: []
},
yAxis: {
    type: 'value',
    axisLabel: {
        formatter: '{value} '
    },
    splitNumber:10
},
series: []
};
myChart.setOption(option);
myChart.showLoading();
var seriess=new Array();
var xAxiss=new Array();
var legends=new Array();
$.ajax({
	type:'GET',
	async: true,
	url:'historyLine.action',
	data:{fmValAd:"${fmValAd}"},
	dataType:'json',
	success:function(result) {
		console.log(result.xy);
	xAxiss=result.xy;
	 $.each(result.data, function(index, comment){
	   legends[index]=comment.name;
	        seriess.push({
            name: comment.name,
            type : 'line',
            data: comment.data,
            markPoint: {
            data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
            ]
        },
            markLine: {
            data: [
                {type: 'average', name: '平均值'}
            ]
        }
           });
      });
      myChart.hideLoading();
	  myChart.setOption({
		    legend:{
		        data : legends
		    },
			xAxis : {
				data : xAxiss
			},
			series : seriess
		});
    },
	error : function(errorMsg) {
        myChart.hideLoading();
		alert("图表请求数据失败!");
	}
	});
</script>

</html>