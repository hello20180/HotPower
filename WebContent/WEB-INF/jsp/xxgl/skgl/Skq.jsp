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
		var khId = $('#khId').val();
		window.location="Search.action?kh="+khId
	}
	</script>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 600,
			colModal: [
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
	<script type="text/javascript">
	function searchInfo() {
		var xqName = $('#xqNameId').val();
		var buildNo = $('#buildNoId').val();
		var cellNo = $('#cellNoId').val();
		var houseNo = $('#houseNoId').val();
		var html = "";
		$.ajax({
			url : "Search.action",
			async : false,
			dataType : "json",
			data : {
				"xqName" : xqName,
				"buildNo" : buildNo,
				"cellNo" : cellNo,
				"houseNo" : houseNo,
			},
			success : function(data) {
				debugger;
				$("#SearchId").empty();
				var d = data.SkxxList;
				for (var i = 0; i < d.length; i++) {
					var skqSbh = d[i].skqSbh;
					var kh = d[i].kh;
					var fmState = d[i].fmState;
					var skSuccess = d[i].skSuccess;
					var xqName = d[i].qg.xqName;
					var sfJf = d[i].sfJf;
					var buildNO = d[i].yh.buildNO;
					var cellNO = d[i].yh.cellNO;
					var houseNO = d[i].yh.houseNO;
					var installAd = d[i].qg.installAd;
					var recordTime = FormatDate(d[i].dateTime);
					html += "<tr>";
					html += "<td class='text-center'>" + skqSbh + "</td>";
					html += "<td class='text-center'>" + kh + "</td>";
					html += "<td class='text-center'>" + fmState + "</td>";
					html += "<td class='text-center'>" + skSuccess+ "</td>";
					html += "<td class='text-center'>" + sfJf+ "</td>";
					html += "<td class='text-center'>" + xqName + "</td>";
					html += "<td class='text-center'>" + buildNO+ "</td>";
					html += "<td class='text-center'>" + cellNO  + "</td>";
					html += "<td class='text-center'>" + houseNO  + "</td>";
					html += "<td class='text-center'>" + installAd  + "</td>";
					html += "<td class='text-center'>" + recordTime + "</td>";
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
	<script type="text/javascript">
		/*页面加载就开始执行js*/
	$(document).ready(
		
			function() {
				$("#xqNameId").change(
						function() {
							$.get("findYhBuildNObyXqName.action?xqName="
									+ $("#xqNameId").val(), function(data) {
								var dd = data;
								var d = dd.xqlist;
								$("#buildNoId option:gt(0)").remove();
								$("#cellNoId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var buildNo = d[i].buildNo;

									var opt = "<option value='"+buildNo+"'>"
											+ buildNo + "</option>"
									$("#buildNoId").append(opt);
								}
							});
						});

				$("#buildNoId").change(
						function() {
							$.get("findYhCellNOByBuild.action?build="
									+ $("#buildNoId").val() + "&xqName="
									+ $("#xqNameId").val(), function(data) {
								var dd = data;
								var d = dd.cellList;
								$("#cellNoId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var cellNo = d[i].cellNo;
									var opt = "<option value='"+cellNo+"'>"
											+ cellNo + "</option>"
									$("#cellNoId").append(opt);
								}
							});
						});
			});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
</head>
<body>
<div class="panel panel-success" style="width: 99%; height: 100%; position: absolute; overflow: auto;">
 <div class="panel-heading">刷卡器信息</div>
 
					<label for="xqNameId">选择小区</label> <select id="xqNameId"
				name="xqName">
				<c:if test="${!empty yhInfoList }">
					<option>--选择小区名称--</option>
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option value="${yhInfolist.xqName}">${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
			</select> &nbsp;&nbsp;&nbsp; <label for="buildNoId">楼栋号</label> <select
				name="buildNo" id="buildNoId">
				<option value=0>--选择楼栋号--</option>
			</select> &nbsp;&nbsp;&nbsp; <label for="cellNoId">单元号</label> <select
				name="cellNo" id="cellNoId">
				<option value=0>--选择单元号--</option>
			</select> 
			户号：<input type="text" name="houseNo" id="houseNoId" value="${houseNo}" /> 
			<input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" /> 
 
 
 
<!--   卡号：<input type="text" id="khId">
  <input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" />  -->
<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
					<tr>
						<th>设备号</th>
						<th>卡号</th>
						<th>阀门</th>
						<th>刷卡状态</th>
						<th>缴费状态</th>
						<th>小区名字</th>
						<th>楼栋号</th>
						<th>单元号</th>
						<th>户号</th>
						<th>安装位置</th>
						<th>时间</th>
					</tr>
				</thead>
				<tbody id=SearchId>
					<c:forEach  var="sk" items="${skxxList}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
								    <td>${sk.skqSbh}</td>
                                     <td>${sk.kh}</td>
                                     <td>${sk.fmState}</td>
                                      <td>${sk.skSuccess}</td>
                                      <td>${sk.sfJf}</td>
                                      <td>${sk.qg.xqName}</td>
                                      <td>${sk.yh.buildNO}</td>
                                      <td>${sk.yh.cellNO}</td>
                                      <td>${sk.yh.houseNO}</td>
                                      <td>${sk.qg.installAd}</td>
									<td><fmt:formatDate value="${sk.dateTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
                                </tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
</body>
</html>