<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>
<style type="text/css">
#top{
padding-top: 10px;
}

</style>
</head>

<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.2.js"></script>
<script type="text/javascript"
	src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-2.2.2.min.js"></script>
<script src="../js/highcharts.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(function() {
		$("#hesNameId").change(function() {
			$.get("findHeatNameList.action?hesName=" + $("#hesNameId").val())
		});
	});
function rh() {
	var InsertRhId = $('#InsertRhId').val();
	var Rh = $('#Rh').val();
	var rTimeId = $('#rTimeId').val();
	$.ajax({
		url : "InsertRh.action",
		async : false,
		dataType : "json",
		data : {
			"InsertRhId" : InsertRhId,
			"Rh":Rh,
			"readTime":rTimeId,
		},
		success : function(data) {
			var d = data.data;
			alert(d);
		}

	})
}
</script>
<body>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
	<form action="">
		<div id="top">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="hesNameId">请选择能耗类型：</label> 
			<select id="rhId">
             <option value='当天能耗'>当天能耗</option>
				<option value='累计能耗'>累计能耗</option>
			</select> &nbsp;&nbsp;&nbsp; 
			<label for="readTime">选择时间</label>
			 <input type="text" id="readTimeId1" name="readTime1" class="Wdate" onfocus="WdatePicker();" />-
				<input type="text" name="readTime2" id="readTimeId2" class="Wdate" onfocus="WdatePicker();" />
		 &nbsp;&nbsp;&nbsp; 
		 <input onclick="chaxun()" type="button"	class="btn btn-success"  value="搜索" /> &nbsp;&nbsp;
		 <br>
	&nbsp;&nbsp;&nbsp;&nbsp; 请选择能耗类型：<select id="InsertRhId">
		<option value='当天能耗'>当天能耗</option>
		<option value='累计能耗'>累计能耗</option>
		 </select>
		 热耗值：<input type="text" id="Rh"/>
		 <input type="text" name="readTime2" id="rTimeId" class="Wdate" onfocus="WdatePicker();" />
		 <input type="button" onclick="rh()"class="btn btn-success"  value="热耗提交"/>
		</div>
		<hr />
		<br />
		<div id="container"
			style="width: 1200px; height: 400px; margin: 0 auto"></div>
		<script type="text/javascript">
			var chart;
			$(function() {
				$(document)
						.ready(
								function() {
									
									chart = new Highcharts.Chart(
											{
												chart : {
													renderTo : 'container',
													type : 'line',
													zoomType : 'xy'
												},
												title : { //头部
													text : '整体能耗分析', //text：标题的文本
													x : -20
												},

												xAxis : { //X坐标轴   categories类别
													 
													categories : [],
								
						},
						yAxis : { //Y坐标轴
							title : {
								text : '热量 (°C)'
							},
							  max:1, // 定义Y轴 最大值  
				                min:0, // 定义最小值  
				                minPadding: 0.1,   
				                maxPadding: 0.1,  
				                tickInterval:0.1, // 刻度值  
							plotLines : [ { //plotLines：标示线
								value : 0.38, //定义在哪个值上显示标示线，这里是在x轴上刻度为3的值处垂直化一条线
								width : 2, //标示线的宽度，2px
								dashStyle : 'solid', //默认值是solid实线，这里定义为虚线
								color : 'red',//线的颜色，定义为红色
							} ]
						},
						//比如说 valuePrefix: '￥', valueSuffix: '元'
						legend : { //图例
							layout : 'vertical', //图例内容布局方式，有水平布局及垂直布局可选，对应的配置值是： “horizontal(水平)”， “vertical(垂直)”
							align : 'left', //图例在图表中的对齐方式，有 “left”, "center", "right" 可选
							verticalAlign : 'middle', //垂直对齐方式，有 'top'， 'middle' 及 'bottom' 可选
							borderWidth : 2
						//边框宽度
						},
						series : [ //数据列
						{ //数据列 
							name : '1',
						} ]
					});
				});
				chaxun();
			});
			function chaxun() {
				var arr = [];
				var rhLx = $('#rhId').val();
				var readTime1 = $('#readTimeId1').val();
				var readTime2 = $('#readTimeId2').val();
				
				$.ajax({
					type : 'get',
					url : "findHeat.action?rhLx=" + rhLx+"&readTime1="+readTime1+"&readTime2="+readTime2,//请求数据的地址
					beforeSend : function(XMLHttpRequest) {
						$('#loading').show();
						$('#contentDIV').hide();
					},

					success : function(data) {
						$('#contentDIV').show();
						$('#loading').hide();
						chart.series[0].setData(data.data);//数据填充到highcharts上面 

						//chart.xAxis.categories[data].setData(data.data);//数据填充到highcharts上面 
					},
					error : function(e) {
						alert("不好意思，请要访问的图标跑到火星去了。。。。");
					}
				});
			};
		</script>

	</form>
</div>
</body>
</html>