<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"
	media="all" />
<script src="https://code.jquery.com/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js"
	type="text/javascript"></script>
<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
  <link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function Sx(){
	window.location.reload(); 
}
function selectAll(){
	 var checklist = document.getElementsByName ("selected");
	   if(document.getElementById("controlAll").checked)
	   {
	   for(var i=0;i<checklist.length;i++)
	   {
	      checklist[i].checked = true;
	   } 
	 }else{
	  for(var j=0;j<checklist.length;j++)
	  {
	     checklist[j].checked = false;
	  }
	 }
	}
	
function deleteInfo(){	

		var ids = "";
 		var r = document.getElementsByName("selected");
 		for (var i = 0; i < r.length; i++) {
 			if (r[i].checked) {
 				ids += r[i].value + ",";
 			}
 		}
 		if (ids == "") { 
 			alert("请选择要删除的信息");   
 			return false;
 		}
 		var id = ids.substring(0, ids.length - 1);
	   			$.ajax({
	   				url:"/HotPower/dcgqCon/delete.action",
	   				async:false,
	   				dataType:"json",
	   				data:{
	   					"ids":id,
	   				},
	   				success:function(data){
	   					window.location.reload(); 
	   				}
	   				
	   			});
}
</script>

</head>
<body>
<form action="">
     <span style="padding-top: 2px; padding-left: 30px; margin-top: 2px;"> <a href="javascript:history.back(-1)">返回</a></span>
  <button type="button" class="btn btn-success" onclick="Sx()">刷新</button>
    <button type="button" class="btn btn-success" onclick="deleteInfo()">删除</button>
<div class="panel-body"  style="width: 50%; height: 85%; position: absolute; overflow:auto;">
	<table >
	<thead>
	<tr>
      		<th><input type="checkbox" onclick="selectAll()" name="controlAll"  id="controlAll"/></th>
            <th align="center">阀门ID</th>
            <th align="center">传感器ID</th>
     </tr>
  </thead>
  <tbody id="fmInfo">

		<c:forEach  var="cgq" items="${dcgq}" varStatus="status">
		 <tr align="center" <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
	    <td><input type="checkbox" value="${cgq.id}"  name="selected"  /></td>
		<td align="center">${cgq.fmId}</td>
		<td align="center">${cgq.cgqId}</td>
	 </tr>
	</c:forEach>

  </tbody>

</table>
</div>

</form>
</body>
</html>