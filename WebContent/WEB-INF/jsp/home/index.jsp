<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<link rel="stylesheet" href="../css/maingonggao.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.leoweather.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/modernizr-2.6.2.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>

<style type="text/css">

ul{
	display: block;
	margin: 0 auto;
	position: relative;
	overflow: hidden;
	}	
#foot{
padding-top: 20px;
}
</style>

<script language="javascript">
	setInterval(
			"timer.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",
			1000);

function tc(){
	document.forms[0].action="<%=basePath%>ruser/loginOut.action";
		document.forms[0].submit();
	}
 function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
	
<%-- $(function(){	 
		$.ajax({
			url :"/HotPower/Notice/noticelist.action",
			type : "post",
			dataType : "json",
			success : function(data) {
				var d = eval(data).list;
				$("#indexList").empty();
				for(var i=0;i<d.length;i++){
				     $("#indexList").append("<li><b>公告</b>&nbsp;&nbsp;&nbsp;<a href='<%=basePath%>Notice/findId.action?id="+d[i].id+"' target='iframemain'>"+d[i].title+"</a><i>     "+FormatDate(d[i].creatTime)+ "</i><a target='iframer' href='<%=basePath%>Notice/Noticelist.action'>更多+</a></li>");
				}
			
			}
		});		
});	 --%>

$(function(){	 
	$.ajax({
		url :"/HotPower/gzglController/gzglList.action",
		type : "post",
		dataType : "json",
		success : function(data) {
			var d = eval(data).gzglList;
			$("#gz").empty();
			for(var i=0;i<d.length;i++){
				var Info=d[i].errinfor;
				var xqName=d[i].yhInfo.xqName;
				var buildNo=d[i].yhInfo.buildNo;
				var cellNo=d[i].yhInfo.cellNo
				var houseNo=d[i].yhInfo.houseNo
				var subInfo=Info.substring(0,6);
			     $("#gz").append("<li>报警&nbsp;&nbsp;&nbsp;<font color='#000000'><a href='<%=basePath%>gzglController/gzgl.action' target='iframemain'>"+xqName+":"+buildNo+"-"+cellNo+"-"+houseNo+","+subInfo+".."+"</a></font><i>"+FormatDate(d[i].recordTime)+ "</i></font>&nbsp;&nbsp;<a target='iframer' href='<%=basePath%>gzglController/gzgl.action'>更多+</a></li>");
			}
		
		}
	});		
});	

 $(function(){	
	 setInterval(function() {
	$.ajax({
		url :"/HotPower/XqInfo/xqNameAVG.action",
		type : "post",
		dataType : "json",
		success : function(data) {
			var d = eval(data).xqInfn;
			$("#Avg").empty();
			for(var i=0;i<d.length;i++){
				var xq=d[i].xqName
				var xqs=xq.slice(0,2);
				var avgs=d[i].avgWd;
				if(avgs<19.00||avgs>22.00){
					 $("#Avg").append(xqs+":<font color='red'>"+d[i].avgWd+"</font>,");
				}else{
					  $("#Avg").append(xqs+":"+d[i].avgWd+",");
				}
			}
		
		}
      });	
	},1000);
});
</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />

</head>

<body class="picLUp">
<!-- 最外层div开始 -->
	<div id="topdiv"
		style="min-width: 1300px; width: 99%; height: 100%; position: absolute;align:center">
			<div style="min-width: 1200px; height: 17%; border-radius: 10px;">
				<div style="width: 100%; height: 70%;">

					<form action="">
						<div
							style="line-height: 35px; width: 250px; background: #00736d; border-top-left-radius: 18px; border-bottom-left-radius: 18px; border: #06383f solid 2px; margin-top: 20px; color: #fff; text-align: center; float: right">
							登录用户：<%=request.getSession().getAttribute("userName")%><a
								type="button" onclick="tc()"
								style="display: inline-block; width: 80px; background: #10ca79; border-radius: 10px; color: #fff; height: 30px; margin-left: 20px; line-height: 30px; text-decoration: none;">退出</a>
						</div>
					</form>
				</div>
				<div style="min-width: 99%; height: 30%;">
					<div class="a">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							<a href="<%=basePath%>home/xtsz.action" target="iframemain"
								style="color: #ffffff">系统设置</a>
						</p>
					</div>
					<div class="b">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>home/nhfx.action"
								target="iframemain" style="color: #ffffff">能耗分析</a>
						</p>
					</div>
					<div class="c">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							&nbsp;&nbsp;<a href="<%=basePath%>home/hrzgl.action"
								target="iframemain" style="color: #ffffff">换热站管理</a>
						</p>
					</div>
					<div class="d">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">

							<a href="<%=basePath%>home/xxgl.action" target="iframemain"
								style="color: #ffffff">信息管理</a>
						</p>
					</div>
					<div class="e">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							<a href="<%=basePath%>home/sjbb.action" target="iframemain"
								style="color: #ffffff">数据报表</a>
						</p>
					</div>
					<div class="f">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							&nbsp;<a href="<%=basePath%>home/tjfx.action" target="iframemain"
								style="color: #ffffff">统计分析</a>
						</p>
					</div>
					<div class="g">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							<a href="<%=basePath%>home/kfgl.action" target="iframemain"
								style="color: #ffffff">客服管理</a>
						</p>
					</div>
					<div class="i">
						<p
							style="margin-top: 8%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							<a href="<%=basePath%>home/sbgl.action
						"
								target="iframemain" style="color: #ffffff">设备管理</a>
						</p>
					</div>
					<div class="h">
						<p
							style="margin-top: 8%; margin-left: 25%; text-align: center; color: #FFFFFF; font-size: 12px; font-weight: 300;">
							<a href="<%=basePath%>home/foots.action" target="iframemain"
								style="color: #ffffff">运行管理</a>
						</p>
					</div>
					<p id="timer"
						style="float: left; margin-top: 10px; font-size: 12px; width: 18%;"></p>
				</div>
			</div>

			<div
				style="background-color: #eef3fa; width: 100%; height: 77%; border: 1px solid #eef3fa; border-radius: 10px;overflow:hidden;"
				align="center">

				<div
					style="width: 100%; height: 5.5%; border-top-left-radius: 10px; border-top-right-radius: 10px; background-color: #b3e2da;overflow:hidden;">
					<div
						Style="width: 45%;min-width:280px; height: 80%; float: left; margin-top: 0.6%;">
						<span>供热用户平均温度:</span><span id="Avg"></span>
					</div>

					<div
						style="width: 32%; height: 80%; float: right; color: #000000; margin-top: 0.6%;">
						<p id="gz"></p>
					</div>
				</div>

<!-- 中间 -->
				<div style="float: left; width: 100%; height: 100%; margin: 3px;">
					<iframe name="iframemain" id="iframemain" marginwidth="0"
						marginheight="0" frameborder="0" width="100%" height="100%"
						src="<%=basePath%>home/foots.action"> </iframe>
				</div>
			</div>
<!-- 			下面 -->
			<div id="foot">
				<p align="center">
					<font color="white">河南众源系统工程有限公司 版权所有</font>
				</p>
			</div>
		</div>
</body>


</html>