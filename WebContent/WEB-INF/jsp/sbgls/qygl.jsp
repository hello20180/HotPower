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
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
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
			width: 2000px;
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
			height: 560,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<!-- Bootstrap -->
  <link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

	//读取阀门ID
	function DqfmId(){
		var ckbs=$("#fmInfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要读取的阀门ID");
			return false;
		}
		if(ckbs.length>1){
			alert("对不起一次只能选择一个");
			return false;
		}
		var id=ckbs.val();
		$.ajax({
			url:"/HotPower/QyglController/DqfmId.action",
			async:false,
			dataType:"json",
			data:{
				"ids":id
			},
			success:function(data){
				msg=data.js;
					if(msg=="0"){
						alert("读取接收指令成功! 阀门ID :"+data.fmId);
						window.location.reload(); 
					}
					if(msg=="1"){
						alert("读取失败!");
						window.location.reload(); 
					}
					if(msg=="2"){
						alert("超时");
						window.location.reload(); 
					}
					if(msg=="3"){
						alert("集中器不在线!");
						window.location.reload(); 
					}	
					
				}
				
			});
			 
	}

//添加fmID
function TjfmId(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要添加fmID");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}
	var id=ckbs.val();
	$.ajax({
		url:"/HotPower/QyglController/TjfmId.action",
		async:false,
		dataType:"json",
		data:{
			"ids":id
		},
		success:function(data){
			msg=data;
				msg=data;
				if(msg=="0"){
					alert("添加接收指令成功!")
					window.location.reload(); 
				}
				if(msg=="1"){
					alert("添加失败!")
					window.location.reload(); 
				}
				if(msg=="2"){
					alert("超时");
					window.location.reload(); 
				}
				if(msg=="3"){
					alert("集中器不在线!")
					window.location.reload(); 
				}	
				
			}
			
		});
		 
}


//查询通讯状态
function QgTS(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要查询的阀门");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}
	var id=ckbs.val();
	$.ajax({
		url:"/HotPower/QyglController/QgTS.action",
		async:false,
		dataType:"json",
		data:{
			"ids":id
		},
		success:function(data){
			msg=data;
				if(msg=="0"){
					alert("查询接收指令成功!")
					window.location.reload(); 
				}
				if(msg=="1"){
					alert("查询失败!")
					window.location.reload(); 
				}
				if(msg=="2"){
					alert("超时!");
					window.location.reload(); 
				}
				if(msg=="3"){
					alert("集中器不在线!");
					window.location.reload();
				}
				
			}
			
		});
		 
}






//实时数据
function ssj(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要读取的阀门");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}
	var id=ckbs.val();
	$.ajax({
		url:"/HotPower/QyglController/ssj.action",
		async:false,
		dataType:"json",
		data:{
			"ids":id
		},
		success:function(data){
				msg=data;
				if(msg=="0"){
					alert("读阀接收指令成功!")
					window.location.reload(); 
				}
				if(msg=="1"){
					alert("读阀失败!")
					window.location.reload(); 
				}
				if(msg=="2"){
					alert("超时")
					window.location.reload(); 
				}
				if(msg=="3"){
					alert("集中器不在线!")
					window.location.reload(); 
				}
				
			}
			
		});
		 
}

//实时热表数据
function SsRb(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要读取的阀门");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}
	var id=ckbs.val();
	$.ajax({
		url:"/HotPower/QyglController/SsRb.action",
		async:false,
		dataType:"json",
		data:{
			"ids":id
		},
		success:function(data){
				msg=data;
				if(msg=="0"){
					alert("读热表接收指令成功!")
					window.location.reload(); 
				}
				if(msg=="1"){
					alert("读热表失败!")
					window.location.reload(); 
				}
				if(msg=="2"){
					alert("超时")
					window.location.reload(); 
				}
				if(msg=="3"){
					alert("集中器不在线!")
					window.location.reload(); 
				}
				
			}
			
		});
		 
}
//批量开阀门
function plKf(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要打开的阀门");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}
	var id=ckbs.val();
	$.ajax({
		url:"/HotPower/QyglController/PlKf.action",
		async:false,
		dataType:"json",
		data:{
			"ids":id
		},
		success:function(data){
			msg=data;
				if(msg=="0"){
					alert("开阀接收指令成功!")
					window.location.reload(); 
				}
				if(msg=="1"){
					alert("开阀失败!")
					window.location.reload(); 
				}
				if(msg=="2"){
					alert("数据失败");
					window.location.reload(); 
				}
				if(msg=="3"){
					alert("超时")
					window.location.reload(); 
				}
				if(msg=="4"){
					alert("集中器不在线!")
					window.location.reload(); 
				}
				if(msg=="5"){
					    alert("该用户无权限!");
						window.location.reload(); 
						
					}
			}
			
		});
		 
}






//批量关阀门
function PlGf(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要关闭的阀门");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能选择一个");
		return false;
	}
	var id=ckbs.val();
	$.ajax({
		url:"/HotPower/QyglController/PlGf.action",
		async:false,
		dataType:"json",
		data:{
			"ids":id
		},
		success:function(data){
			msg=data;
				if(msg=="0"){
					alert("关阀接收指令成功!")
					window.location.reload(); 
				}
				if(msg=="1"){
					alert("关阀失败!")
					window.location.reload(); 
				}
				if(msg=="2"){
					alert("数据失败");
					window.location.reload(); 
				}
				if(msg=="3"){
					alert("超时")
					window.location.reload(); 
				}
				if(msg=="4"){
					alert("集中器不在线!")
					window.location.reload(); 
				}
				if(msg=="5"){
					    alert("该用户无权限!");
					    window.location.reload(); 
						
					}
			}
			
		});
		 
}
//发送参数
function FsCs(){
	    var wdsd = $('#wdsd').val();
	    var tjzq=$('#tjzq').val();
	    var tjcs=$('#tjcs').val();
	    var sdbs=$('#sdbs').val();
	    var ckbs=$("#fmInfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要发送的区管");
			return false;
		}
		if(ckbs.length>1){
			alert("对不起一次只能发送一个区管");
			return false;
		}
		var id=ckbs.val();
		var qgId=ckbs.parent().next().text();	
		$.ajax({
			url:"/HotPower/QyglController/Fscs.action",
			async:false,
			dataType:"json",
			data:{
				"qgId":qgId,
				"wdsd":wdsd,
				"tjzq":tjzq,
				"tjcs":tjcs,
				"sdbs":sdbs,
			},
			success:function(data){
				msg=data;
					if(msg=="0"){
						alert("发送接收指令成功!")
					}
					if(msg=="1"){
						alert("集中器不在线!")
					}	
					if(msg=="5"){
   					    alert("该用户无权限!");
   						
   					}
					document.location.reload();
			}
		
		});
}
</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">区管管理</div>
 
<p>
<button class="btn btn-success" onclick="QgTS()" >查询通信状态</button>
<button type="button" class="btn btn-success" onclick="ssj()">实时数据</button>
<button type="button" class="btn btn-success" onclick="SsRb()">实时热表数据</button>
<button type="button"  class="btn btn-success" onclick="plKf()">批量开阀</button>
<button type="button"  class="btn btn-success" onclick="PlGf()">批量关阀</button>
<button type="button" class="btn btn-success" onclick="TjfmId()">添加阀门ID</button>
<button type="button" class="btn btn-success" onclick="DqfmId()">读取阀门ID</button>
调节周期 <input type="text" id="wdsd" name="wdsd" size="5" value="6"/>
室温设定<input type="text" id="tjzq" name="tjzq" size="5" value="20"/>
调节参数<input type="text" id="tjcs" name="tjcs" size="5" value="153"/>
锁定标识   <select name="sdbs" id="sdbs">
		<option value=02>请选择锁定标识</option>
		<option value=00>自动</option>
		<option value=01>锁定</option>
	  </select>
	  &nbsp;&nbsp;&nbsp;
<button class="btn btn-success" onclick="FsCs()" >发送参数</button>
<div class="dwrapper">

<table id="fixed_hdr2">
	<thead>
      <tr>
      				<th></th>
					<th >区管ID</th>
					<th >所属集中器</th>
					<th >小区名称</th>
					<th >安装位置</th>
					<th >更新时间</th>
					<th >区管版本</th>
					<th >模式</th>
					<th >更新周期</th>
					<th >阀门起始地址</th>
					<th >阀门结束地址</th>
					<th >区管分类</th>
     </tr>
  </thead>

  <tbody id="fmInfo">
		<c:forEach var="qg" items="${Qg}" varStatus="status">

					<tr>
					<td><input type="checkbox" value="${qg.qgID}" name="selected"  /></td>
						<td>${qg.qgID}</td>
						<td>${qg.jzqID}</td>
						<td>${qg.xqName }</td>
						<td>${qg.installAd}</td>
						<td><fmt:formatDate value="${qg.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${qg.version }</td>
						<td>${qg.mode}</td>
						<td>${qg.readCycle}</td>
						<td>${qg.vALstID }</td>
						<td>${qg.vALedID}</td>
						<c:if test="${qg.qgFl==0}">
						<td>单系统</td>
						</c:if>
						<c:if test="${qg.qgFl==1}">
						<td>双系统</td>
						</c:if>
					</tr>
				</c:forEach>
  </tbody>
</table>
</div>
</div>
</body>
</html>