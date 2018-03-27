<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%  
  String path=request.getContextPath();
  String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap -->
<script type="text/javascript" src="../js/jquery-2.2.2.min.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="../js/main.js"></script>

<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<style>
		html, body {
			font-family: Arial,​​sans-serif;
			font-size: 15px;
			margin: 0;
			padding: 0;
			background-color: #f2f2f2;
		}
		
		div.container {
			padding: 5px 10px;
			width: 1330px;
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
			height: 500,
			colModal: [
			{ width: 200, align: 'center' },
			{ width:800, align: 'center' },
			{ width: 500, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>



</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">版本升级</div>
 <div class="dwrapper">
	<table id="fixed_hdr2" style="overflow:scroll;">
				<thead>
					<tr>
						
						<th class="text-center" nowrap="nowrap" align="center">升级人</th>
						<th class="text-center" nowrap="nowrap" align="center">升级内容 </th>
						<th class="text-center" nowrap="nowrap" align="center">升级时间</th>
					</tr>
				</thead>

				<tbody  class="text-center" id="users">
					<c:forEach  var="bsj" items="${listBsj}" varStatus="status">

								<tr class="hover" >
                                     <td align="center" nowrap="nowrap">${bsj.cjr}</td>
                                     <td align="center" nowrap="nowrap">${bsj.cjNr}</td>
                                     <td align="center" nowrap="nowrap"><fmt:formatDate value="${bsj.cjsj}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</c:forEach>
				</tbody>
			</table>
</div>
</div>

</body>
</html>