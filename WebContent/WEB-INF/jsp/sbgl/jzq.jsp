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
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
			{ width: 170, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 170, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 160, align: 'center' },
			{ width: 160, align: 'center' },
			{ width: 160, align: 'center' },
			{ width: 160, align: 'center' },
			{ width: 200, align: 'center' }
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
						<th>户号</th>
						<th>集中器ID</th>
						<th>本地端口</th>
						<th>集中器IP</th>
						<th>集中器端口</th>
						<th>状态</th>
						<th>更新时间</th>
						<th>小区名称</th>
						<th>换热站名称</th>
						<th>安装位置</th>
						<th>备注</th>

					</tr>
				</thead>
				<tbody id="jzqinfo">
					<c:forEach  var="jzq" items="${jzq}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td>${jzq.id}</td>
                                     <td>${jzq.jzqID}</td>
                                     <td>${jzq.socket}</td>
                                      <td>${jzq.jzqIP}</td>
                                     <td>${jzq.jzqPort}</td>
                                     <c:if test="${jzq.status==0}">
										<td>离线</td>
									</c:if>
									<c:if test="${jzq.status==1}">
										<td>在线</td>
									</c:if>
                                     <td><fmt:formatDate value="${jzq.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                      <td>${jzq.xqName}</td>
                                     <td>${jzq.hesName}</td>
                                     <td>${jzq.installAd}</td>
                                     <td>${jzq.remark}</td>
					</c:forEach>
				</tbody>
			</table>
			</div>
