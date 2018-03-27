<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert</title>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
function check() 
{
	 var tjcs=$('#cstimeId').val();
	 if(!IsNum(tjcs)){
		 alert("请输入大于等于1的整数!")
		  $("#tjcs").val(1); // 清空并获得焦点
		 return false;
		 }
	 function IsNum(num){
		  var reNum=/^[0-9]*[1-9][0-9]*$/;
		  return(reNum.test(num));
		}
}
</script>
</head>
<body>
<div class="panel panel-success">
<div class="panel-heading">抄表时间设置</div>
<div class="panel-body">
<form action="updateCsTime.action">
<table align="center">
<tr><td><font size="4px" >抄表时间设置：</font><input id="cstimeId" type="text" value="${CsTime.cstime}" name="cstime" onblur="return check()" />
<hr>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-success" value="提交">
</table>
</form>
</div>
</div>
</body>
</html>