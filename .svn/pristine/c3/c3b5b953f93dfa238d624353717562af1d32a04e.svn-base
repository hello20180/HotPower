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
<script type="text/javascript">
function toSqlServerBackUp(){
	
	var mssqlBackupName=document.getElementById("mssqlBackupName").value;
	var mssqlBackupPath=document.getElementById("mssqlBackupPath").value;
	document.location.href="backupData.action?mssqlBackupPath="+mssqlBackupPath+"&mssqlBackupName="+mssqlBackupName;
}
</script>
</head>
<body>
<div class="panel panel-success">
<div class="panel-heading">数据管理</div>
<div class="panel-body">
<form action="backupData.action">
<table align="center">
<tr><td class="Zt"><font size="4px" >备份文件名称：</font><input type="text" id="mssqlBackupName" name="mssqlBackupName" value="DB_Znsbf20170419"/></td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="4px" >备份地址：</font><input id="mssqlBackupPath" type="text" value="${dm.savePath}" name="mssqlBackupPath" />
<hr>
<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="backup" name="backup" type="button"  class="btn btn-success" value="数据库备份" onclick="toSqlServerBackUp();"/></td></tr>

</table>
</form>
</div>
</div>
</body>
</html>