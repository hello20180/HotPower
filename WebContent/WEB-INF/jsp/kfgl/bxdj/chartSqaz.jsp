<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-2.2.2.min.js"></script>
<script src="../js/highcharts.js"></script> 

<body>
<div>
<div align="left">
           <a href="javascript:history.back(-1)">返回</a>
         </div>
	<label for="xqNameId">小区名称</label> 
		<select id="xqNameId" name="xqName">
			<c:if test="${!empty yhInfolist }">
			   <option>--选择小区--</option>
				<c:forEach items="${yhInfolist}" var="yhList">
					<option>${yhList.xqName}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;

<input onclick="chaxun()" type="button" class="btn btn-success" value="搜索" /> &nbsp;&nbsp;
	</div>
		<hr />
		<br />
未接单：<span id="wjd"></span>
已接单：<span id="yjd"></span>
已完成：<span id="ywc"></span>

	<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
	
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
							text : '故障状态进行比例'
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
	function chaxun() {
		var arr = [];
		var xqName = $('#xqNameId').val();
		var kffl=1;
		$.ajax({
			type : 'get',
			url : "chartSearch.action?xqName="+xqName+"&kffl="+kffl,//请求数据的地址
			beforeSend : function(XMLHttpRequest) {
				$('#loading').show();
				$('#contentDIV').hide();
			},
			
			success : function(data) {
				$("#wjd").html(data.wjd);
		        $("#yjd").html(data.yjd);
		        $("#ywc").html(data.ywc);
				chart.series[0].setData(data.data);//数据填充到highcharts上面 
				
			},
			error : function(e) {
				alert("不好意思，请要访问的图标跑到火星去了。。。。");
			}
		});
	};
		</script>

</body>
</html>