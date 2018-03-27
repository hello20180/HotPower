<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
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
			{ width: 80, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 170, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 170, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<title>热表信息列表</title>
</head>
<body>

<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
				<th>用户姓名</th>
				<th>小区名称</th>
				<th>楼栋号</th>
				<th>单元号</th>
				<th>户号</th>
				<th>热表编号</th>
				<th>阀门地址</th>
				<th>累计热量</th>
				<th>瞬时热量</th>
				<th>累计流量</th>
				<th>瞬时流量</th>
				<th>进水温度</th>
				<th>回水温度</th>
				<th>温差</th>
				<th>工作时间</th>
				<th>更新时间</th>
				<th>热表类型</th>
				<th>区管地址</th>
				<th>实时时间</th>
			</tr>
		</thead>
			<c:forEach var="rb" items="${Rb}" varStatus="status">
				<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if> data-toggle="modal"
				data-target="#add">
				<c:choose>
				<c:when test="${rb.rbExp==1}">
					<td title="${rb.yh.bz}"><font color="red">${rb.yh.yhName}</font></td>
				</c:when>
				<c:when test="${rb.rbExp==2}">
				<td title="${rb.yh.bz}"><font color="blue">${rb.yh.yhName}</font></td>
				</c:when>
				<c:otherwise>
				<td title="${rb.yh.bz}">${r.yhName}</td>
				</c:otherwise>
				</c:choose>
				
					<td>${rb.yh.xqName}</td>
					<td>${rb.yh.buildNO}</td>
					<td>${rb.yh.cellNO}</td>
					<td>${rb.yh.houseNO}</td>
					<td>${rb.rbAd}</td>
					<td>${rb.yh.fm.valAd}</td>
					<td>${rb.energy}</td>
					<td>${rb.power}</td>
					<td>${rb.energyLj}</td>
					<td>${rb.flow}</td>
					<td>${rb.inTmp}</td>
					<td>${rb.outTmp}</td>
					<td>${rb.diffTmp}</td>
					<td>${rb.operTime}</td>
					<td><fmt:formatDate value="${rb.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					<td>${rb.rbType}</td>
					<td>${rb.yh.fm.qgID}</td>
					<td><fmt:formatDate value="${rb.readMTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-table.js"></script>
	<script src="../js/bootstrap-datetimepicker.min.js"></script>
	<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>	
</body>
</html>