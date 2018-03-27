<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.2.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-2.2.2.min.js"></script>
<script src="../js/highcharts.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
#top{
padding-top: 10px;
}
</style>
<script type="text/javascript">
function UpNh() {
	var xqName = $('#xqName').val();
	var nhz = $('#nhz').val();
	var readTime = $('#readTime').val();
	$.ajax({
		url : "UpNhfx.action",
		async : false,
		dataType : "json",
		data : {
			"xqName" : xqName,
			"nhz":nhz,
			"readTime" : readTime
		},
		success : function(data) {
			var d = data.data;
			alert(d);
		}

	})
}
</script>
<script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(
			function() {
				$("#xqId").change(
						function() {
							$.get("findNhfxTime.action?xqName="
									+ $("#xqId").val(), function(data) {
								var dd = data;
								var d = dd.nhfList;
								$("#TimeListId option:gt(0)").remove();
								$("#TimeListSId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var readTime = d[i].readTime;
									var opt = "<option value='"+readTime+"'>"
											+ readTime + "</option>"
									$("#TimeListId").append(opt);
								}
							});
						});
				
				$("#TimeListId").change(
						function() {
							$.get("findTimeListS.action?readTime="
									+ $("#TimeListId").val() + "&xqName="
									+ $("#xqId").val(), function(data) {
								var dd = data;
								var d = dd.nhfSList;
								$("#TimeListSId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var readTime = d[i].readTime;
									var opt = "<option value='"+readTime+"'>"
											+ readTime + "</option>"
											$("#TimeListSId").append(opt);
								}
							});
						});
			});
	</script>
	<script type="text/javascript">
		function SearTime(){
			var html ="";
			var xqName = $('#xqId').val();
			var TimeListId = $('#TimeListId').val();
			var TimeListSId = $('#TimeListSId').val();
			$.ajax({
				url : "findNhz.action",
				async : false,
				dataType : "json",
				data : {
					"xqName" : xqName,
					"TimeListId":TimeListId,
					"TimeListSId" : TimeListSId
				},
				success : function(data) {
					var Nhst = data.Nhst;
					var time = data.time;
					var xqName = data.Xq;
					var times = data.times;
					$("#NhCz").empty();
					html += "<table align='center'  style='height: 30px; width: 50%; border:0px;'>";
					html += "<tr>"
					html += "<th >小区名字"
					html += "</th>"
					html += "<th> 能差值耗值"
					html += "</th>"
					html += "<th> 时间"
					html += "</th>"
					html += "<th> 时间"
					html += "</th>"
					html += "</tr>"
					html += "<tr>";
					html += "<td>" + xqName + "</td>";
					html += "<td>" + Nhst + "</td>";
					html += "<td>" + time+"</td>";
					html += "<td>" + times+"</td>";
					html += "</tr>";
				    html += "</tbody>"
					html += "</table>";
				    $("#NhCz").append(html);
				}

			})
		}
	
	</script>
</head>
<body>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
  <div id="top">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="xqNameId">选择小区</label>
	 <select id="xqNameId" name="xqName">
				<c:if test="${!empty yhInfoList }">
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option
							<c:if test="${ yhInfolist.xqName==xqName }">selected</c:if>>${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
	</select>
	 &nbsp;&nbsp;&nbsp; <label for="readMTime">选择时间</label>
	<input type="text" id="readTime1" name="readTime1" class="Wdate" onfocus="WdatePicker();" />
	-<input type="text" name="readTime2" id="readTime2" class="Wdate" onfocus="WdatePicker();" />
	<input type="button" value="搜索" class="btn btn-success"  onclick="chaxun()">
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 小区名称:<select id="xqName" name="xqName">
				<c:if test="${!empty yhInfoList }">
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option
							<c:if test="${ yhInfolist.xqName==xqName }">selected</c:if>>${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
	</select>
	热表读数:<input type="text" id="nhz" name="nhz" >
	更新时间：<input type="text" id="readTime" name="readTime" class="Wdate" onfocus="WdatePicker();" />
	<button type="button" class="btn btn-success"  onclick="UpNh()">提交</button>
	
	<br>
	
	
	
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 小区名称:<select id="xqId" name="xqName">
				<c:if test="${!empty yhInfoList }">
				<option>--选择小区名称--</option>
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option
							<c:if test="${ yhInfolist.xqName==xqName }">selected</c:if>>${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
	</select>
	时间：<label for="TimeList">时间</label> 
	 <select name="TimeList" id="TimeListId">
				<option value=0>--选择时间--</option>
	</select>
	-<label for="TimeListS">时间</label> 
	 <select name="TimeListS" id="TimeListSId">
				<option value=0>--选择时间--</option>
	</select>
	<button onclick="SearTime()" class="btn btn-success">搜索</button>
	<div id="NhCz">
	
	</div>
	</div>
		<div id="container"
			style="width: 1000px; height: 500px; margin: 0 auto"></div>
		 <div id="tablediv"  >
		
		</div>
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
													text : '小区能耗分析', //text：标题的文本
													x : -20
												},

												xAxis : { //X坐标轴   categories类别

													categories : [
															],

															plotLines : [ { //plotLines：标示线
																
															}  ]
												},
												yAxis : { //Y坐标轴
													title : {
														text : '(总耗热量/天数)/选择小区的供热面积*120'
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
													}  ]
												},
												//比如说 valuePrefix: '￥', valueSuffix: '元'
												legend : { //图例
													

												//边框宽度
												},
												series : [ //数据列
												{ //数据列 
													name : '面积能耗分析',
												
												
												} ]
											});
								});
				chaxun();
			});

			function chaxun() {
				var html ="";
				var arr = [];
				var xqName = $('#xqNameId').val();
				var readTime1 = $('#readTime1').val();
				var readTime2 = $('#readTime2').val();
				$.ajax({
					type : 'get',
					url : "HeatAreaByxqName.action?xqName=" + xqName + "&readTime1="+ readTime1 + "&readTime2=" + readTime2,//请求数据的地址
					beforeSend : function(XMLHttpRequest) {
						$('#loading').show();
						$('#contentDIV').hide();
					},
					success : function(data) {
						$('#contentDIV').show();
						$("#tablediv").empty();
						
						$('#loading').hide();
						chart.series[0].setData(data.data);//数据填充到highcharts上面 
						var d = data.nhfx;
						html += "<table align='center'  style='height: 30px; width: 50%; border:0px;'>";
						html += "<tr>"
						html += "<th >小区名字"
						html += "</th>"
						html += "<th> 热表读数"
						html += "</th>"
						html += "<th> 更新时间"
						html += "</th>"
						html += "</tr>"
						html += "<tbody class='text-center'>"
							for(var i=0;i<d.length;i++){
								var xqName = d[i].xqName;
								var nhz = d[i].nhz;
								var recordTime = FormatDate(d[i].readTime);
								html += "<tr>";
								html += "<td>" + xqName + "</td>";
								html += "<td>" + nhz + "</td>";
								html += "<td>" + recordTime+"</td>";
								html += "</tr>";
							}
							    html += "</tbody>"
								html += "</table>";
							
								$("#tablediv").append(html);
					},
					error : function(e) {
						alert("不好意思，请要访问的图标跑到火星去了。。。。");
					}
				});
			};
			function FormatDate(strTime) {
				var date = new Date(strTime);
				return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
						+ date.getDate() + " " + date.getHours() + ":"
						+ date.getMinutes() + ":" + date.getSeconds();
			}
		</script>
		</div>	
</body>
</html>