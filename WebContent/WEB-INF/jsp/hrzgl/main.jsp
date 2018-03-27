<%@page import="com.hnzy.hot.hrzgl.pojo.HeatWX"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%  
  String path=request.getContextPath();
  String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<style>
/* .max{width:60%;height:auto;}
.min{width:350px;height:auto;} */
</style>
</head>
<body style="padding-left: 10px;">
	<div class="panel panel-success">
		<div class="panel-heading">台帐详情</div>
	
		<div align="left">
           <a href="javascript:history.back(-1)">返回</a>
         </div>
			<div style="width: 99%; height: 90%; position: absolute; overflow:auto;" align="center">
					<input type="hidden" value="${heat.id }" name="id" />
					<table align="center" >
					
					<tr align="center" style="border-style:none;">
						<td style="border-style:none;" colspan="2">
						<h4>${heat.title}</h4>
						</td>
					</tr>
					<tr align="center" style="border-style:none;">
						<td style="border-style:none;" colspan="2">
						<h5>${heat.wx}&nbsp;&nbsp;&nbsp;
					<fmt:formatDate value="${heat.wxTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></h5>
								</td>
					</tr>
					<tr class="panel-heading" style="border-style:none;" >
					<td style="border-style:none;" colspan="2">
					<h5>维修过程：</h5>
					</td>
					</tr>
					<tr align="center" style="border-style:none;" >
						<td style="border-style:none;" colspan="2">
						<h6>
						<textarea style="overflow-x: hidden; width:700px;height:120px;" class="form-control" name="article">${heat.article}</textarea>
						</h6>
						</td>
					</tr>
					<tr class="panel-heading" style="border-style:none;" >
					<td style="border-style:none;" colspan="2">
					<h5>维修结果：</h5>
					</td>
					</tr>
					<tr align="center" style="border-style:none;">
						<td style="border-style:none;" colspan="2">
						<h5>
						<textarea style="overflow-x: hidden; width:700px;height:70px;" class="form-control" name="article">${heat.remark}</textarea>
					</h5>
						</td>
					</tr>
					<tr>
					<tr class="panel-heading" style="border-style:none;" >
					<td style="border-style:none;" colspan="2">
					<h5>维修附图：</h5>
					</td>
					</tr>
				</table>
				<div style="padding:8px;border:1px solid #000000; width:600px;height:auto;">
				<c:forEach items="${imag }" var="imag" varStatus="status">
						<img id="img" width="300" height="200" onMouseOver="this.width='550'; this.height='400';" onMouseOut="this.width='300'; this.height='200'" src="<%=basePath %>${imag}"/>
				</c:forEach>
				</div>
			</div>

		</div>
	<script src="../js/jquery-1.9.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-table.js"></script>
	<script src="../js/bootstrap-datetimepicker.min.js"></script>
	<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
<script>
/* $('#img').click(function(){
$(this).toggleClass('min');
$(this).toggleClass('max');
}); */
</script>
</html>