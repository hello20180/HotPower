<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
			width: 2330px;
			margin: 10px auto;
		}
		
		.ft_container table tr th {
			background: url(../images/secai.jpg);
		}
	</style>
	<script type="text/javascript">
	
	function searchInfo(){
		var czId = $('#czId').val();
		window.location="Search.action?cz="+czId
		
	}
	</script>
	<script type="text/javascript">
	</script>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 600,
			colModal: [
			{ width: 250, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 200, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function searchInfo() {
	var xqName = $('#xqNameId').val();
	var recordTime1 = $('#recordTime1').val();
	var recordTime2 = $('#recordTime2').val();
	var html = "";
	$.ajax({
		url : "searchInfo.action",
		async : false,
		dataType : "json",
		data : {
			"xqName" : xqName,
			"recordTime1":recordTime1,
			"recordTime2":recordTime2,
		},
		success : function(data) {
			$("#SearchId").empty();
			var d = data.tjfx;
			
			for (var i = 0; i < d.length; i++) {
				var xqName = d[i].xqName;
				var zerot = d[i].zerot;
				var zFift = d[i].zFift;
				var fiftOct = d[i].fiftOct;
				var octTw = d[i].octTw;
				var twSix = d[i].twSix;
				var twentySix = d[i].twentySix;
				var date = FormatDate(d[i].date);
				html += "<tr>";
				html += "<td class='text-center'>" + xqName + "</td>";
				html += "<td class='text-center'>" + zerot + "</td>";
				html += "<td class='text-center'>" + zFift + "</td>";
				html += "<td class='text-center'>" + fiftOct + "</td>";
				html += "<td class='text-center'>" + octTw + "</td>";
				html += "<td class='text-center'>" + twSix + "</td>";
				html += "<td class='text-center'>" + twentySix + "</td>";
				html += "<td class='text-center' title='"+date+"'>" + date
						+ "</td>";
				html += "</tr>";
			}
			$("#SearchId").append(html);
		}

	})
}
function FormatDate(strTime) {
	var date = new Date(strTime);
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":"
			+ date.getMinutes() + ":" + date.getSeconds();
}
</script>
</head>
<body>
<div class="panel panel-success" style="width: 99%; height: 100%; position: absolute; overflow: auto;">
 <div class="panel-heading">室温历史分析</div>

<div class="dwrapper">
  <label for="xqNameId">选择小区</label> <select id="xqNameId" name="xqName">
				<c:if test="${!empty yhInfoList }">
					<option>--选择小区名称--</option>
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option value="${yhInfolist.xqName}">${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
				</select>
  选择时间：  <label for="readMTime">选择时间:</label>
		  <input type="text" id="recordTime1" name="recordTime1" class="Wdate" onfocus="WdatePicker();" />
		-<input type="text" id="recordTime2"  name="recordTime2"  class="Wdate" onfocus="WdatePicker();" />	
  <input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" /> 
	<table id="fixed_hdr2">
	<thead>

	<tr>
						<th>小区名称</th>
						<th>0度</th>
						<th>0-15度</th>
						<th>15-18度</th>
						<th>18-22度</th>
						<th>22-26度</th>
						<th>大于26度</th>
						<th>日期</th>
					</tr>
				</thead>
				<tbody id=SearchId>
					<c:forEach  var="tjfx" items="${findTjfx}" varStatus="status">
					<tr>
								    <td>${tjfx.xqName}</td>
                                     <td>${tjfx.zerot}</td>
									<td>${tjfx.zFift}</td>
									<td>${tjfx.fiftOct}</td>
									<td>${tjfx.octTw}</td>
									<td>${tjfx.twSix}</td>
									<td>${tjfx.twentySix}</td>
									<td><fmt:formatDate value="${tjfx.date}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                </tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
</body>
</html>