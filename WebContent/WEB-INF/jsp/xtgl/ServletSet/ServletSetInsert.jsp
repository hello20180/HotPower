<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert</title>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">

</head>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">服务器设置</div>
		<div class="panel-body">
			<form action="updateServerSet.action">
				<input type="hidden" name="id" value="${st.id}" />
				<div>
					<table id="table" align="center">
				
						<tr>
							<td align="right">服务器名称：</td>
							<td align="left"><input type="text" name="sname" value="${st.sname}"></td>
						</tr>
					
						<tr>
							<td align="right">服务器IP:</td>
							<td align="left"><input type="text" name="sip" value="${st.sip}"></td>
						</tr>
						<tr>
							<td align="right">服务器端口：</td>
							<td align="left"><input type="text" name="sport" 	value="${st.sport}"></td>
						</tr>
						<tr>
							<td align="right">数据库登录名：</td>
							<td align="left"><input type="text" name="sqlname" value="${st.sqlname}"></td>
						</tr>
						<tr>
							<td align="right">数据库密码：</td>
							<td align="left"><input type="text" name="sqlpass" value="${st.sqlpass}"></td>
						<tr>
							<td align="center"><input type="submit" class="btn btn-success" value="提交" /></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>