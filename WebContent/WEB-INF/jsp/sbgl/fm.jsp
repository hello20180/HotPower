<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>阀门信息列表</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<style>
		html, body {
			font-family: Arial,​​sans-serif;
			font-size: 13px;
			margin: 0;
			padding: 0;
			background-color: #f2f2f2;
		}
		
		div.container {
			padding: 5px 10px;
			width: 2330px;
			margin: 10px auto;
		}
		
		.ft_container table tr th {
			background: url(../images/secai.jpg);
		}
	</style>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 500,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 90, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
	
	
<body>
<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
			<th>序号</th>
            <th>用户姓名</th>
            <th>小区名称</th>
            <th>楼栋号</th>
            <th>单元号</th>
            <th>户号</th>
            <th>阀门状态</th>
            <th>阀门开度</th>
            <th>室内温度</th>
            <th>管道温度</th>
             <th>锁定标识</th>
            <th>更新时间</th>
            <th>用户卡号</th>
            <th>阀门地址</th>
            <th>传感器地址</th>
            <th>缴费状态</th>
            <th>用户面积</th>
            <th>区管ID</th>
            <th>室外温度</th>
            <th>用户类别</th>
            <th>用户分区</th>
	</tr>
	</thead>

   
  <tbody id="fmInfo">
	<c:forEach  var="fm" items="${Fm}" varStatus="status">
	
	   <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>	  
		<td>${fm.id}</td>		
		<c:choose>
   		<c:when test="${fm.yh.yhfl=='重点监控'}">
				<td title='${fm.yh.bz }'><a href="/HotPower/FmHController/goHistoryLine2.action?xqName=${fm.yh.xqName}&buildNO=${fm.yh.buildNO}&cellNO=${fm.yh.cellNO}&houseNO=${fm.yh.houseNO}"><font color="red">${fm.yh.yhName}</font></a></td>
		</c:when>
		<c:when test="${fm.yh.yhfl=='特殊情况'}">
		<td title='${fm.yh.bz }'><a href="/HotPower/FmHController/goHistoryLine2.action?xqName=${fm.yh.xqName}&buildNO=${fm.yh.buildNO}&cellNO=${fm.yh.cellNO}&houseNO=${fm.yh.houseNO}"><font color="blue">${fm.yh.yhName}</font></a></td>
		</c:when>
		<c:when test="${fm.yh.yhfl=='退费停暖'}">
				<td title='${fm.yh.bz }'><a href="/HotPower/FmHController/goHistoryLine2.action?xqName=${fm.yh.xqName}&buildNO=${fm.yh.buildNO}&cellNO=${fm.yh.cellNO}&houseNO=${fm.yh.houseNO}"><font color="#FF8C00">${fm.yh.yhName}</font></a></td>
		</c:when>
		<c:otherwise>
		  <td title="${fm.yh.bz }"><a href="/HotPower/FmHController/goHistoryLine2.action?xqName=${fm.yh.xqName}&buildNO=${fm.yh.buildNO}&cellNO=${fm.yh.cellNO}&houseNO=${fm.yh.houseNO}">${fm.yh.yhName}</a></td>
		</c:otherwise>
		</c:choose>
		<td>${fm.yh.xqName}</td>
		<c:choose>
		<c:when test="${fm.yh.YLFQ=='高区'}">
		<td><font color="green">${fm.yh.buildNO}</font></td>
		<td><font color="green">${fm.yh.cellNO}</font></td>
		<td><font color="green">${fm.yh.houseNO}</font></td>
		</c:when>
		<c:when test="${fm.yh.YLFQ=='中区'}">
		<td><font color="#DAA520">${fm.yh.buildNO}</font></td>
		<td><font color="#DAA520">${fm.yh.cellNO}</font></td>
		<td><font color="#DAA520">${fm.yh.houseNO}</font></td>
		</c:when>
		<c:otherwise>
		<td>${fm.yh.buildNO}</td>
		<td>${fm.yh.cellNO}</td>
		<td>${fm.yh.houseNO}</td>
	
		</c:otherwise>
		</c:choose>
		<c:if test="${fm.status=='开'}">
			<td><font color="green" >${fm.status}</font></td>	
		</c:if>	
		<c:if test="${fm.status=='关'}">
			<td>${fm.status}</td>	
		</c:if>		
		<td>${fm.famKd}</td>
		<c:choose>
		<c:when test="${fm.yh.bjxx=='低温'}">
		<td><font color="#FF8C00">${fm.roomTemp}</font></td>
		</c:when>
		<c:when test="${fm.yh.bjxx=='高温'}">
		<td><font color="red">${fm.roomTemp}</font></td>
		</c:when>
		<c:otherwise>
		<td><font color="blue" >${fm.roomTemp}</font></td>	
		</c:otherwise>
		</c:choose>
		<td>${fm.valTemp}</td>
		<td>${fm.lockSt}</td>
		<td><fmt:formatDate value="${fm.recordTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${fm.yh.idNum}</td>
		<td>${fm.valAd}</td>
		<td>${fm.yh.WCAd}</td>
		<td>${fm.yh.sFJF}</td>
		<td>${fm.yh.heatArea}</td>
		<td>${fm.qgID}</td>
		<td>${fm.tqyb}</td>
		<td>${fm.yh.yhfl}</td> 
		<td>${fm.yh.YLFQ}</td>  	
	 </tr>
	
	</c:forEach>
  </tbody>
</table>
</div>

</body>
</html>