<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
	<script src="../js/jquery-1.9.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-table.js"></script>
<script src="../js/main.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var html;
	setInterval(function() {
		$.ajax({
			url : "heatStation.action",
			type : "post",
			dataType : "json",
			data : {

			},
			success : function(data) {
					$("#ygsStress").html(data.ygsStress)
					$("#ygsTpt").html(data.ygsTpt);
					$("#yhsStress").html(data.yhsStress);
					$("#yhsTpt").html(data.yhsTpt);
					$("#rbgsTpt").html(data.rbgsTpt);
					$("#rbghsTpt").html(data.rbghsTpt);
					$("#ysHeat").html(data.ysHeat);
					$("#ysFlow").html(data.ysFlow);
					$("#ylHeat").html(data.ylHeat);
					$("#ylFlow").html(data.ylFlow);
					$("#zsStress").html(data.zsStress);
					$("#zsFlow").html(data.zsFlow);
					$("#ydl").html(data.ydl);
					$("#tjFmTickling").html(data.tjFmTickling);
					$("#egsStress").html(data.egsStress);
					$("#egsTpt").html(data.egsTpt);
					$("#ehsStress").html(data.ehsStress);
					$("#ehsTpt").html(data.ehsTpt);
					$("#xpTickling").html(data.xpTickling);
					$("#xyElectricity").html(data.xyElectricity);
					$("#yxTpt").html(data.yxTpt);
					$("#exTpt").html(data.exTpt);
					$("#esFlow").html(data.esFlow);
					$("#elFlow").html(data.elFlow);
					$("#swTpt").html(data.swTpt);
					$("#bspTickling").html(data.bspTickling);
					$("#bsyElectricity").html(data.bsyElectricity);
					$("#bsFlow").html(data.bsFlow);
					$("#blFlow").html(data.blFlow);
			},
			
		});
	}, 4000);
});
</script>
<style type="text/css">
td{font-size:8px;  width:100px}
#center{float:left}
#right{float:left; padding-top:40px;  }
#left{float:left; padding-top:40px;  padding-left:20px; }
</style>
</head>
<body>
<ul class="nav nav-tabs">
<li class="active"><a href="#">实时数据</a></li>
<li><a href="HeatStationYHCBwx.action">维修台帐</a></li>
</ul>
<form action="">
<div id="left">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width=190 align=center >
<tr>
<td>一次供水压力</td>
<td id="ygsStress"></td>
</tr>
<tr>
<td>一次供水温度</td>
<td id="ygsTpt"></td>
</tr>
<tr>
<td>一次回水压力</td>
<td id="yhsStress"></td>
</tr>
<tr>
<td>一次回水温度</td>
<td id="yhsTpt"></td>
</tr>
<tr>
<td>热表供水温度</td>
<td id="rbgsTpt"></td>
</tr>
<tr>
<td>热表回水温度</td>
<td id="rbghsTpt"></td>
</tr>
<tr>
<td>一次瞬时流量</td>
<td id="ysFlow"></td>
</tr>
<tr>
<td>一次瞬时热量</td>
<td id="ysHeat"></td>
</tr>
<tr>
<td>一次累计热量</td>
<td id="ylHeat"></td>
</tr>
<tr>
<td>一次累计流量</td>
<td id="ylFlow"></td>
</tr>
<tr>
<td>自来水压力</td>
<td id="zsStress"></td>
</tr>
<tr>
<td>自来水流量</td>
<td id="zsFlow"></td>
</tr>
<tr>
<td>用电量 </td>
<td id="ydl"></td>
</tr>
<tr>
<td>调节阀开度反馈</td>
<td id="tjFmTickling"></td>
</tr>
<tr>
<td>补水累积流量</td>

<td id="blFlow"></td>
</tr>
</table>

</div>
<div id="center"><img width="700px" src="../images/hrzglYHCB.png"></div>
<div id="right">
<table style="BORDER-COLLAPSE: collapse" height=20 cellPadding=1 width=190 align=center >
<tr>
<td>二次供水压力</td>
<td id="egsStress"></td>
</tr>
<tr>
<td>二次供水温度</td>
<td id="egsTpt"></td>
</tr>
<tr>
<td>二次回水压力</td>
<td id="ehsStress"></td>
</tr>
<tr>
<td>二次回水温度</td>
<td id="ehsTpt"></td>
</tr>
<tr>
<td>循环泵频率反馈</td>
<td id="xpTickling"></td>
</tr>

<tr>
<td>循环泵运行电流</td>
<td id="xyElectricity"></td>
</tr>

<tr>
<td>1#循环泵温度</td>

<td id="yxTpt"></td>
</tr>
<tr>
<td>2#循环泵温度</td>

<td id="exTpt"></td>
</tr>
<tr>
<td>二次瞬时流量</td>

<td id="esFlow"></td>
</tr>
<tr>
<td>二次累积流量</td>

<td id="elFlow"></td>
</tr>
<tr>
<td>室外温度</td>

<td id="swTpt"></td>
</tr>
<tr>
<td>补水泵频率反馈</td>
<td id="bspTickling"></td>
</tr>
<tr>
<td>补水泵运行电流</td>
<td id="bsyElectricity"></td>
</tr>
<tr>
<td>补水瞬时流量	</td>
<td id="bsFlow"></td>
</tr>

</table>
</div>

</form>
</body>
</html>