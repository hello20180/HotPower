<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
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
			{ width: 60, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 220, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
</head>
<body>
<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
			<th>序号</th>
            <th>区管ID</th>
            <th>所属集中器</th>
            <th>小区名称</th>
            <th>安装位置</th>
            <th>区管状态</th>
            <th>区管版本</th>
            <th>模式</th>
            <th>更新周期</th>
            <th>阀门起始地址</th>
            <th>阀门结束地址</th>
            <th>更新时间</th>
     </tr>
 			 </thead>
 				 <tbody>
					<c:forEach var="qg" items="${Qg}" varStatus="status">
					<tr>
						<td>${qg.id }</td>
						<td>${qg.qgID}</td>
						<td>${qg.jzqID}</td>
						<td>${qg.xqName }</td>
						<td>${qg.installAd}</td>
						<c:if test="${qg.qgStatus==0}">
							<td>离线</td>
						</c:if>
						<c:if test="${qg.qgStatus==1}">
							<td>在线</td>
						</c:if>
						<td>${qg.version }</td>
						<td>${qg.mode}</td>
						<td>${qg.readCycle}</td>
						<td>${qg.vALstID }</td>
						<td>${qg.vALedID}</td>
						<td><fmt:formatDate value="${qg.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
</body>
</html>