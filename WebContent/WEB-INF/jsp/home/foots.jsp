<%@page import="com.hnzy.hot.xxgl.pojo.Rz"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap -->
<script type="text/javascript" src="../js/jquery-2.2.2.min.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>

<script type="text/javascript">
function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
$(function(){	 
		$.ajax({
			url :"/HotPower/rz/rzindex.action",
			type : "post",
			dataType : "json",
			success : function(data) {
				var d = eval(data).log;
				$("#indexList").empty();
				for(var i=0;i<d.length;i++){
			     $("#indexList").append("<li>"+d[i].czr+" "+d[i].cz+"<i> "+FormatDate(d[i].czsj)+ "</i></li>");
				}
			
			}
		});		
});	
</script>
</head>
<body>
	<div style="float: left; width: 100%; height: 100%; margin: 3px;">
		<div
			style="border: 1px solid #0c9986; float: left; width: 14%; height: 100%; border-radius: 10px; background-color: #FFFFFF;">
			<div
				style="float: center; background-color:#00a18f; width: 100%; height: 8%; border-top-left-radius: 10px; border-top-right-radius: 10px;">
			<p style="margin-top:0px; line-height:25px; color:#fff;"><img style="width: 20px; margin-left: 15px; margin-top:px; margin-right:10px;display: inline-block;" src="../images/01.png"><b style="display: inline-block;line-height: 40px;">列表</b></p>
			</div>


			<br />
			<div style="float: center; width: 100%; height: 90%;">
				<iframe id="iframerl" name="iframerl" class="iframerl"
					marginwidth="0" marginheight="0" frameborder="0" scrolling="auto"
					width="100%" height="100%" src="<%=basePath%>Sbgl/ztree.action">
					
				</iframe>
			</div>
		</div>
		<div
			style="background-color: #FFFFFF; border: 1px solid #0c9986; float: right; width: 85%; height: 100%; border-radius: 10px; margin-right: 5px;">

			<div
				style="width: 100%; height: 8%; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom: 1px dashed #CCCCCC;">
				<div style="float: left; width: 14%; height: 100%;">
					<img src="../images/znsbf.png" height="35" align="middle" />&nbsp;<a
						style="font-size: 13px; margin-top: 8%; text-align: center;"
						target="iframer" href="<%=basePath%>Sbgl/Fm.action"
						target="iframer">智能锁闭阀</a>
				</div>
				<div
					style="float: left; width: 14%; height: 100%; border: 1px dashed #CCCCCC;">
					<img src="../images/qgglq.png" height="35" align="middle">&nbsp;<a
						style="font-size: 13px;" href="<%=basePath%>/Sbgl/Qg.action"
						target="iframer">区域管理器</a>
				</div>
				<div style="float: left; width: 14%; height: 100%;">
					<img src="../images/jzqgl.png" height="35" align="middle">&nbsp;<a
						style="font-size: 13px;" href="<%=basePath%>Sbgl/Jzq.action"
						target="iframer">集中管理器</a>
				</div>
				<div
					style="float: left; width: 14%; height: 100%; border: 1px dashed #CCCCCC;">
					<img src="../images/rlb.png" height="35" align="middle">&nbsp;<a
						style="font-size: 13px;" href="<%=basePath%>Sbgl/Rb.action"
						target="iframer">热量表</a>
				</div>
			</div>
			<div style="width: 100%; height: 75%; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom: 1px dashed #00ffff;">
				<iframe id="iframer" name="iframer" class="iframer" marginwidth="0"
					marginheight="0" frameborder="0" scrolling="yes" width="100%"
					height="100%" src="<%=basePath%>Sbgl/Fm.action"> </iframe>
			</div>
			<div
				style="width: 100%; height: 30%; border-top-left-radius: 10px; border-top-right-radius: 10px; position: absolute; overflow: auto; border-bottom: 1pxdashed #00ffff; margin-top: 10px;">
				<marquee onmouseout=this.start() onmouseover=this.stop()
					behavior="scroll" direction="up" width="70%" height="80px"
					SCROLLDELAY="300">
						<div>
						<ul id="indexList">
						</ul>
						</div>
				</marquee>

			</div>

		</div>
	</div>
</body>

</html>