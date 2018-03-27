<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>

<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<script type="text/javascript"
	src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">

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
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 510,
			colModal: [
			{ width: 130, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 190, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 110, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' }
			],
			sort: true
		});
		
	});
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
<script type="text/javascript">
	function searchInfo() {
		var xqName = $('#xqNameId').val();
		var buildNo = $('#buildNoId').val();
		var cellNo = $('#cellNoId').val();
		var houseNo = $('#houseNoId').val();
		var sfjf = $('#JFStatusId').val();
		var valTemp1=$('#valTempId1').val();
		var valTemp2=$('#valTempId2').val();
		var roomTemp1=$('#roomTempId1').val();
		var roomTemp2=$('#roomTempId2').val();
		var famKd=$('#famKdId').val();
		var recordTime1 = $('#recordTime1').val();
		var recordTime2 = $('#recordTime2').val();
		var html = "";
		$.ajax({
			url : "searchInfo.action",
			async : false,
			dataType : "json",
			data : {
				"xqName" : xqName,
				"buildNo" : buildNo,
				"cellNo" : cellNo,
				"houseNo" : houseNo,
				"sfjf" : sfjf,
				"valTemp1":valTemp1,
				"valTemp2":valTemp2,
				"roomTemp1":roomTemp1,
				"roomTemp2":roomTemp2,
				"recordTime1":recordTime1,
				"recordTime2":recordTime2,
				"famKd":famKd,
			},
			success : function(data) {
				$("#SearchId").empty();
				var d = data.findXqInfoFmHistory;
				
				for (var i = 0; i < d.length; i++) {
					var xqName = d[i].xqName;
					var buildNo = d[i].buildNo;
					var cellNo = d[i].cellNo;
					var houseNo = d[i].houseNo;
					var status = d[i].fmHistory.status;
					var famKd = d[i].fmHistory.famKd;
					var valTemp = d[i].fmHistory.valTemp;
					var roomTemp = d[i].fmHistory.roomTemp;
					var recordTime = FormatDate(d[i].fmHistory.recordTime);
					var lockSt = d[i].fmHistory.lockSt;
					var sfjf = d[i].sfjf;
					var hTemSet = d[i].fmHistory.hTemSet;
					var mTemSet = d[i].fmHistory.mTemSet;
					var lTemSet = d[i].fmHistory.lTemSet;
					var tqyb = d[i].fmHistory.tqyb;
					html += "<tr>";
					html += "<td class='text-center'>" + xqName + "</td>";
					html += "<td class='text-center'>" + buildNo + "</td>";
					html += "<td class='text-center'>" + cellNo + "</td>";
					html += "<td class='text-center'>" + houseNo + "</td>";
					if(status=='开'){
						html += "<td class='text-center'><font color='green'>" + status + "</font></td>";
					}else{
						html += "<td class='text-center'>" + status + "</td>";
					}
					html += "<td class='text-center'>" + famKd + "</td>";
					html += "<td class='text-center'>" + valTemp + "</td>";
					html += "<td class='text-center'>" + roomTemp + "</td>";
					html += "<td class='text-center'>" + tqyb + "</td>";
					html += "<td class='text-center' title='"+recordTime+"'>" + recordTime
							+ "</td>";
					html += "<td class='text-center'>" + sfjf + "</td>";
					html += "<td class='text-center'>" + hTemSet + "</td>";
					html += "<td class='text-center'>" + mTemSet + "</td>";
					html += "<td class='text-center'>" + lTemSet + "</td>";
					

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
	//导出
	function doExportExcel() {
		var xqName = $('#xqNameId').val();
		var buildNo = $('#buildNoId').val();
		var cellNo = $('#cellNoId').val();
		var houseNo = $('#houseNoId').val();
		var sfjf = $('#JFStatusId').val();
		var valTemp1=$('#valTempId1').val();
		var valTemp2=$('#valTempId2').val();
		var roomTemp1=$('#roomTempId1').val();
		var roomTemp2=$('#roomTempId2').val();
		var famKd=$('#famKdId').val();
		var recordTime1 = $('#recordTime1').val();
		var recordTime2 = $('#recordTime2').val();
		window.open("YhInfodoExportExcel.action?xqName=" + xqName + "&buildNo="
				+ buildNo + "&cellNo=" + cellNo + "&houseNo=" + houseNo
				+ "&sfjf=" + sfjf+ "&valTemp1=" + valTemp1+"&valTemp2=" + valTemp2+"&roomTemp1="+ roomTemp1+"&roomTemp2="+ roomTemp2+"&recordTime1="+ recordTime1+"&recordTime2="+ recordTime2+"&famKd=" + famKd);
	}
</script>

</head>
<body>
<ul class="nav nav-tabs">
<li class="active"><a href="#">锁闭阀数据报表</a></li>
<li><a href="/HotPower/RbsglController/sbSjbb.action">双系统热表数据</a></li>
<li><a href="/HotPower/RbrlCon/fdRbrlUser.action">单系统热表数据</a></li>
</ul>
 <div class="panel panel-success">
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
				<label for="fmStatusId">选择交费状态</label> 
				<select id="JFStatusId" name="sfjf">
			    <c:if test="${!empty yhInfoList }">
			   <option>--选择缴费状态--</option>
			     <option>是</option>
			     <option>否</option>
			    </c:if>
				</select>

				<br> 
			<label for="readMTime">管道温度:</label>
			<input type="text" name="valTemp" size="10px" id="valTempId1" value="${valTemp}" /> 
		-	<input type="text" name="valTemp" size="10px" id="valTempId2" value="${valTemp}" />
		<label for="readMTime">室内温度:</label>
			<input type="text" name="roomTemp" size="10px" id="roomTempId1" value="${roomTemp}" /> 
		-<input type="text" name="roomTemp" size="10px" id="roomTempId2" value="${roomTemp}" /> 
		<label for="readMTime">阀门开度:</label>
		 	<input type="text" name="famKd"  id="famKdId" value="${famKd}" /> 
		  <label for="readMTime">选择时间:</label>
		  <input type="text" id="recordTime1" name="recordTime1" class="Wdate" onfocus="WdatePicker();" />
		-<input type="text" id="recordTime2"  name="recordTime2"  class="Wdate" onfocus="WdatePicker();" />	
			 <input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" /> 
			<input type="button" value="导出"class="btn btn-success" onclick="doExportExcel()" />
			<div id="tablediv">
				<form id="myForm">
					<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
									<th>小区名称</th>
									<th>楼栋号</th>
									<th>单元号</th>
									<th>户号</th>
									<th>阀门状态</th>
									<th>阀门开度</th>
									<th>管道温度</th>
									<th>室内温度</th>
									<th>室外温度</th>
									<th>采集时间</th>
									<th>缴费状态</th>
									<th>采集周期</th>
									<th>设定温度</th>
									<th>阀门参数</th>
									
								</tr>
							</thead>
							<tbody id=SearchId>
								<c:forEach var="findXqFm" items="${findXqInfoFmHistory}">
									<tr>
										<td>${findXqFm.xqName }</td>
										<td>${findXqFm.buildNo }</td>
										<td>${findXqFm.cellNo }</td>
										<td>${findXqFm.houseNo }</td>
										<c:if test="${findXqFm.fmHistory.status  =='开'}">
										<td><font color="green" >${findXqFm.fmHistory.status }</font></td>	
										</c:if>	
										<c:if test="${findXqFm.fmHistory.status =='关'}">
										<td>${findXqFm.fmHistory.status }</td>	
										</c:if>	
										<td>${findXqFm.fmHistory.famKd }</td>
										<td>${findXqFm.fmHistory.valTemp }</td>
										<td>${findXqFm.fmHistory.roomTemp}</td>
										<td>${findXqFm.fmHistory.tqyb}</td>
										<td
											title="<fmt:formatDate value="${findXqFm.fmHistory.recordTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"><fmt:formatDate
												value="${findXqFm.fmHistory.recordTime }"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${findXqFm.sfjf }</td>
										<td>${findXqFm.fmHistory.hTemSet}</td>
										<td>${findXqFm.fmHistory.mTemSet}</td>
										<td>${findXqFm.fmHistory.lTemSet}</td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
					</div>
				</form>
			</div>
			</div>
</body>
</html>