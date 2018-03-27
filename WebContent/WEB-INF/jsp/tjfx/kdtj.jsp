<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
span {
	font-weight:bold;
	color:#ff9955;
	}

#top{
padding-top: 10px;
}
div p{margin:0 auto;width:200px;}
</style>

</head>
<script type="text/javascript"> 
	/*页面加载就开始执行js*/
	$(document).ready(function() {
		$("#xqNameId").change(
		function(){	
		  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val(),function(data) {
			var dd=data;
			var d=dd.xqlist;
			$("#buildNoId option:gt(0)").remove();
			$("#cellNoId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var buildNo=d[i].buildNo;
				
				var opt="<option value='"+buildNo+"'>"+buildNo+"</option>"
				$("#buildNoId").append(opt);
			}
			});
		});
		
		$("#buildNoId").change(
			function() {
				$.get("findYhCellNOByBuild.action?build="+$("#buildNoId").val()+"&xqName="+ $("#xqNameId").val(),function(data) {
					var dd=data;
					var d=dd.cellList;
					$("#cellNoId option:gt(0)").remove();
					for(var i=0;i<d.length;i++){
						var cellNo=d[i].cellNo;
						var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
						$("#cellNoId").append(opt);
					}
					});
				 });
		
  });
 </script>
<body>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
	<form action="" id="MainForm">
	<div id="top">
		<label for="xqNameId">选择小区</label> 
		<select id="xqNameId" name="xqName">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择小区名称--</option>
				<c:forEach items="${yhInfoList}" var="yhInfolist">
					<option  <c:if test="${ yhInfolist.xqName==xqName }">selected</c:if>>${yhInfolist.xqName}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;

		<label for="buildNoId">楼栋号</label>
		 <select name="buildNo" id="buildNoId">
			<option value=0 <c:if test="${ yhInfolist.buildNo==buildNo }">selected</c:if>>--选择楼栋号--</option>
		</select>
	&nbsp;
		<label for="cellNoId">单元号</label> 
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>
&nbsp;
		<label for="fmStatusId">选择阀门状态</label> 
		<select id="fmStatusId" name="status">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择阀门状态--</option>
				<c:forEach items="${fmHistoriesStatus}" var="fmHistoriesStatus">
					<option value="${fmHistoriesStatus.status}">${fmHistoriesStatus.status}</option>
				</c:forEach>
			</c:if>
		</select>
&nbsp;&nbsp;&nbsp;
		<label for="JFStatusId">选择缴费状态</label> 
		<select id="JFStatusId" name="sfjf">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择缴费状态--</option>
			     <option selected="selected">是</option>
			     <option>否</option>
			</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;
		<input onclick="chaxun()" type="button" class="btn btn-success" value="搜索" /> &nbsp;&nbsp;
		</div>
		<hr />

		<br />
		<div>
			<table align="right"
				style="overflow-x: hidden; overflow-y: auto; height: 200px; width: 20%;" border="0">
				<tr >
					<td align="center" colspan="2" >开度分析</td>
				</tr>
				<tr>
					<td align="left">
						<input name="wd" type="checkbox" checked="checked" value="1" />开度100</td>
					<td align="left"><span id="chartA"></span></td>
				</tr>
				<tr>
					<td align="left">
						<input name="wd" type="checkbox" checked="checked" value="3" />开度在0-15之间
					</td>
					<td align="left"><span id="chartC"></span></td>
				</tr>
				<tr>
					<td align="left">
						<input name="wd" type="checkbox" checked="checked" value="6" />开度0
					</td>
					<td align="left"><span id="chartF"></span></td>
				</tr>
			</table>
		</div>

		<div id="container" style="width: 500px; height: 400px; margin: 0 auto">
		</div>
		<div id="zt"><p style="center"><font color="red">* 点击色块可以显示用户信息</font></p></div>
		<div id="tablediv"  >
		
		</div>
		<script type="text/javascript">
			var chart;
			$(function(){
				$(document).ready(function() {
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'container',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
							},
						title : {
							text : '阀门开度分析图'
							},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
							},
						 credits: {  
				                    enabled:false  
				                }, 
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									color : '#000000',
									connectorColor : '#000000',
									format : '<b>{point.name}</b>: {point.percentage:.1f} %'
									},
									 events:{
					                        click: function(e) {
					 							 var name = e.point.name;
					 							ShowMaxSubValues(name);
					                        }
					                    },
								showInLegend : true
								}
							},
						series : [ {
							type : 'pie',
							name : '所占比例',
							} ]
					});
			});
				//调用查询，加载数据
			chaxun();
	});
				
			
			var html ="";
			function ShowMaxSubValues(name) {
				
				    var html ="";
				    var xqName = $('#xqNameId').val();
					var buildNo = $('#buildNoId').val();
					var cellNo = $('#cellNoId').val();
					var status = $('#fmStatusId').val();
					var sfjf = $('#JFStatusId').val(); 
				 $.ajax({
				 url:"findFkd.action?xqName="+xqName+"&buildNo="+buildNo+"&cellNo="+cellNo+"&status="+status+"&sfjf="+sfjf+"&name="+name,
						  async:false,
						  dataType:"json",
						   success:function(data){
							$("#tablediv").empty();
							var d = data.yhInfoList;
							html += "<table align='center'  style='height: 30px; width: 50%; border:0px;'>";
							html += "<tr>"
							html += "<th >小区名字"
							html += "</th>"
							html += "<th> 楼栋号"
							html += "</th>"
							html += "<th> 单元号"
							html += "</th>"
							html += "<th> 户号"
							html += "</th>"
							html += "<th> 室内温度"
							html += "</th>"
							html += "<th> 管道温度"
							html += "</th>"
							html += "<th> 阀门开度"
							html += "</th>"
							html += "<th> 阀门状态"
							html += "</th>"
							html += "<th> 缴费状态"
							html += "</th>"
							html += "<th> 更新时间"
							html += "</th>"
							html += "</tr>"
							html += "<tbody class='text-center'>"
							for(var i=0;i<d.length;i++){
								var xqName = d[i].xqName;
								var buildNo = d[i].buildNo;
								var cellNo = d[i].cellNo;
								var houseNo = d[i].houseNo;
								var famKd=d[i].fmHistory.famKd
								var roomTemp=d[i].fmHistory.roomTemp;
								var valTemp=d[i].fmHistory.valTemp;
								var status = d[i].fmHistory.status;
								var sfjf = d[i].sfjf; 
								var recordTime = FormatDate(d[i].fmHistory.recordTime);
								html += "<tr>";
								html += "<td>" + xqName + "</td>";
								html += "<td>" + buildNo + "</td>";
								html += "<td>" + cellNo + "</td>";
								html += "<td>" + houseNo + "</td>";
								html += "<td>" + roomTemp + "</td>";
								html += "<td>" + valTemp + "</td>";
								html += "<td>" + famKd + "</td>";
								html += "<td>" + status + "</td>";
								html += "<td>" + sfjf + "</td>";
								html += "<td>" + recordTime+ "</td>";
								html += "</tr>";
							}
							    html += "</tbody>"
								html += "</table>";
							
								$("#tablediv").append(html);
						}
						
					})
			}
			
			function FormatDate(strTime) {
				var date = new Date(strTime);
				return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
						+ date.getDate() + " " + date.getHours() + ":"
						+ date.getMinutes() + ":" + date.getSeconds();
			}
	function chaxun() {
		$("#tablediv").empty();
		var arr = [];
		var xqName = $('#xqNameId').val();
		var buildNo = $('#buildNoId').val();
		var cellNo = $('#cellNoId').val();
		var status = $('#fmStatusId').val();
		var sfjf = $('#JFStatusId').val();
		var box = document.getElementsByName("wd");
		var objArray = box.length;
		var apiContentStr = "";
		for (var i = 0; i < objArray; i++) {
			if (box[i].checked == true) {
				apiContentStr += box[i].value + ",";
			}
		}
		apiContentStr = apiContentStr.substring(0,apiContentStr.length - 1);
		$.ajax({
			type : 'get',
			url : "famkd.action?xqName=" + xqName + "&buildNo="+ buildNo + "&cellNo=" + cellNo + "&status="
					+ status + "&sfjf=" + sfjf + "&apiContentStr="
					+ apiContentStr,//请求数据的地址
			beforeSend : function(XMLHttpRequest) {
				$('#loading').show();
				$('#contentDIV').hide();
			},
			success : function(data) {
				$('#contentDIV').show();
				$('#loading').hide();
			 	 $("#chartA").html(data.chartA+"户")
			 	 $("#chartB").html(data.chartB+"户")
	        	 $("#chartC").html(data.chartC+"户")
	        	 $("#chartD").html(data.chartD+"户")
	        	 $("#chartE").html(data.chartE+"户")
	        	 $("#chartF").html(data.chartF+"户")
				chart.series[0].setData(data.data);//数据填充到highcharts上面 
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