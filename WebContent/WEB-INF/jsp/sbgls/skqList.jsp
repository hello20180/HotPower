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
<style>
html, body {
	font-family: Arial, ​​sans-serif;
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
			fixedCols : 0,
			width : "100%",
			height : 500,
			colModal : [
			{ width: 50, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' }
			
			],
			sort : true
		});
		
	});
	</script>


<script type="text/javascript">
//读取阀门ID
function Skzt(){
	var ckbs=$("#SkqInfo input[type=checkbox]:checked");
	
	if(ckbs.length==0){
		alert("请选择要读取的阀门ID");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}

	
	 var id=ckbs.val();
	 var sbh=ckbs.parent().next().text();
	$.ajax({
		url:"/HotPower/SkqController/SkqState.action",
		async:false,
		dataType:"json",
		data:{
			"sbh":sbh
		},
		success:function(data){
			msg=data;
			if(msg=="0"){
				alert("读卡成功!")
				window.location.reload(); 
			}
			if(msg=="1"){
				alert("超时!")
				window.location.reload(); 
			}
			if(msg=="2"){
				alert("短卡失败!");
				window.location.reload(); 
			}
			if(msg=="3"){
				alert("集中器不在线!");
				window.location.reload();
			}
				
			}
			
		});
		 
}


 </script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">刷卡器管理</div>
 
<p>
<button type="button" class="btn btn-success" onclick="Skzt()">读刷卡器状态</button>
<div class="panel-body"  style="width: 99%; height: 85%; position: absolute; overflow:auto;">
	
		<div class="dwrapper">
		<table id="fixed_hdr2">
			<thead>
					<tr>
						<th></th>
						<th>设备号</th>
						<th>集中器</th>
						<th>区管</th>
						<th>小区名字</th>
						<th>安装位置</th>
					</tr>
				</thead>
				<tbody id="SkqInfo">
					<c:forEach  var="sk" items="${Skq}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
										<td><input type="checkbox" value="${sk.id}" /></td>
								   		 <td>${sk.skqSbh}</td>
                                       	<td>${sk.jzqID}</td>
                                       	<td>${sk.qgID}</td>
                                       	<td>${sk.xqName}</td>
                                      	 <td>${sk.installAd}</td>
                                </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
    </div>
	</div>
</body>
</html>