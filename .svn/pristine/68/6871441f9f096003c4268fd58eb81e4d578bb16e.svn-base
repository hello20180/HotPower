<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告详情</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
</head>
<body>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">公告详情</div>
		<div style="width: 99%; height: 100%; position: absolute; overflow:auto;" align="center">
		<div align="left">
           <a href="javascript:history.back(-1)">返回</a>
         </div>
			<hr />
			<br />
			<div
				style="overflow-x: hidden; overflow-y: auto; height: 400px; width: 60%;">
				<input type="hidden" value="${noti.id }" name="id" />

				<table align="center">

					<tr align="center">
						<td>
						
						<h3>${noti.title}</h3>
						</td>
					</tr>
					<tr align="center">
					
						<td>
						<h4>
						<textarea style="overflow-x: hidden; width:500px;height:200px;" class="form-control" name="article">${noti.article}</textarea>
					</h4>
						</td>
					</tr>
					<tr align="right">
						<td>
						<h5>${noti.spokesMan}</h5>
						</td>
					</tr>
					<tr align="right">
					<td><h4>
					<fmt:formatDate value="${noti.creatTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></h4>
								</td>

					</tr>

				</table>
			</div>

		</div>
	</div>
	<script src="../js/jquery-1.9.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-table.js"></script>
	<script src="../js/bootstrap-datetimepicker.min.js"></script>
	<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
</body>

</body>
</html>