<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<style type="text/css">
td{ width: 200px; overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;}
</style>
</head>

<body style="padding-left: 10px;">
<ul class="nav nav-tabs">
<li ><a href="HeatStationstate.action">实时数据</a></li>
<li class="active"><a href="#">维修台帐</a></li>
</ul>
<div class="panel panel-success">
<div class="panel-heading">金领时代换热站</div>
<br>
<div style="width: 99%; height: 80%; position: absolute; overflow:auto;">
				<table id="tableSort" style="table-layout:fixed;" >
				
					<thead >
						<tr>
							<th class="text-center" nowrap="nowrap" align="center">主题</th>
							<th class="text-center" nowrap="nowrap" align="center">故障详情</th>
							<th class="text-center" nowrap="nowrap" align="center">维修人</th>
							<th class="text-center" nowrap="nowrap" align="center">维修时间</th>
							<th class="text-center" nowrap="nowrap" align="center">备注</th>
					
						</tr>
					</thead>
					<tbody class="text-center" id="xsInfos">
						<c:forEach items="${list }" var="heat" varStatus="status">
							<tr class="info" <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
								<td align="center" nowrap="nowrap"><a href="findById.action?id=${heat.id}">${heat.title}</a></td>
								<td align="center" nowrap="nowrap" >${heat.article}</td>
								<td align="center" nowrap="nowrap">${heat.wx}</td>
								<td align="center" nowrap="nowrap"><fmt:formatDate value="${heat.wxTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center" nowrap="nowrap">${heat.remark}</td>
								
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
</div>

</body>
</html>