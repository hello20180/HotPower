<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<title>main</title>
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
				width="100%" height="100%" src="<%=basePath%>xtgllist/xtglmenuList.action?pid=6">
			</iframe>
		</div>
	</div>
	<div
		style="background-color: #FFFFFF; border: 1px solid #0c9986; float: right; width: 85%; height: 100%; border-radius: 10px; margin-right: 5px;">
		<div
			style="width: 100%; height: 100%; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom: 1px dashed #00ffff;">
			<iframe id="iframer" name="iframer" class="iframer" marginwidth="0"
				marginheight="0" frameborder="0" scrolling="yes" width="100%"
				height="100%" src="<%=basePath%>/HeatStation/HeatStationstate.action"> </iframe>
		</div>
	</div>
	</div>
</body>
</html>