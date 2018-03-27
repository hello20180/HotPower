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
	function fmId(){
		var ValAd = $('#FmId').val();
		var html = "";
		$.ajax({
			url : "SearchFmId.action",
			async : false,
			dataType : "json",
			data : {
				"ValAd" : ValAd,
			},
			success : function(data) {
				$("#SearchId").empty();
				var d = data.gzglList;
				for (var i = 0; i < d.length; i++) {
					var yhInfo = d[i].yhInfo.yhName;
					var xqName = d[i].yhInfo.xqName;
					var buildNo = d[i].yhInfo.buildNo;
					var cellNo = d[i].yhInfo.cellNo;
					var houseNo = d[i].yhInfo.houseNo;
					var deviceType = d[i].deviceType;
					var deviceId = d[i].deviceId;
					var errinfor = d[i].errinfor;
					var recordTime = FormatDate(d[i].recordTime);
					html += "<tr>";
					html += "<td class='text-center'>" + yhInfo + "</td>";
					html += "<td class='text-center'>" + xqName + "</td>";
					html += "<td class='text-center'>" + buildNo + "</td>";
					html += "<td class='text-center'>" + cellNo+ "</td>";
					html += "<td class='text-center'>" + houseNo + "</td>";
					html += "<td class='text-center'>" + deviceType + "</td>";
					html += "<td class='text-center'>" + deviceId + "</td>";
					html += "<td class='text-center'>" + errinfor + "</td>";
					html += "<td class='text-center'>" + recordTime + "</td>";
					html += "</tr>";
				}
				$("#SearchId").append(html);
			}

		})
	}
	
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
				$("#SearchId").empty();
				var d = data.gzglList;
				for (var i = 0; i < d.length; i++) {
					var yhInfo = d[i].yhInfo.yhName;
					var xqName = d[i].yhInfo.xqName;
					var buildNo = d[i].yhInfo.buildNo;
					var cellNo = d[i].yhInfo.cellNo;
					var houseNo = d[i].yhInfo.houseNo;
					var deviceType = d[i].deviceType;
					var deviceId = d[i].deviceId;
					var errinfor = d[i].errinfor;
					var recordTime = FormatDate(d[i].recordTime);
					html += "<tr>";
					html += "<td class='text-center'>" + yhInfo + "</td>";
					html += "<td class='text-center'>" + xqName + "</td>";
					html += "<td class='text-center'>" + buildNo + "</td>";
					html += "<td class='text-center'>" + cellNo+ "</td>";
					html += "<td class='text-center'>" + houseNo + "</td>";
					html += "<td class='text-center'>" + deviceType + "</td>";
					html += "<td class='text-center'>" + deviceId + "</td>";
					html += "<td class='text-center'>" + errinfor + "</td>";
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
	 $(function () {
         $("#ts").hide();
         $("#dj").hover(function () {
             $("#ts").show();
         }, function () {
             $("#ts").hide();
         })
         // 鼠标移动到list的div上的时候list div不会被隐藏
         $("#ts").hover(function () {
             $("#ts").show();
         }, function () {
             $("#ts").hide();
         })
     });
	</script>
	
	
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 600,
			colModal: [
			{ width: 130, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 400, align: 'center' },
			{ width: 150, align: 'center' },
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
</head>
<body>
<div class="panel panel-success">
 		<div class="panel-heading">故障管理</div>
				<div id="tablediv">
				<form id="myForm">
					<div class="dwrapper">
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
			</br>
     		阀门地址：<input type="text" id="FmId">
  			<input type="button" onclick="fmId()" value="搜索" class="btn btn-success" /> 
  			<span id="dj"><font color="red">*操作提示</font></span>
      		 <span id="ts" >
              <font color="red">:选择小区、楼栋、单元、户号搜索是最新的数据、根据阀门地址搜索是阀门的历史数据</font> 
       		</span>
	<table id="fixed_hdr2">
	<thead>
	<tr>
						<th>业主</th>
						<th>小区名称</th>
						<th>楼栋号</th>
						<th>单元号</th>
						<th>户号</th>
						<th>故障类型</th>
						<th>阀门地址</th>
						<th>故障信息</th>
						<th>记录时间</th>
					</tr>
				</thead>
				<tbody id=SearchId>
					<c:forEach  var="gzgl" items="${gzglList}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
								    <td>${gzgl.yhInfo.yhName}</td>
                                     <td>${gzgl.yhInfo.xqName}</td>
									<td>${gzgl.yhInfo.buildNo}</td>
								<td>${gzgl.yhInfo.cellNo}</td>
									<td>${gzgl.yhInfo.houseNo}</td>
								    <td>${gzgl.deviceType}</td>
                                     <td>${gzgl.deviceId}</td>
									<td title="${gzgl.errinfor}">${gzgl.errinfor}</td>
									<td title="<fmt:formatDate value="${gzgl.recordTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${gzgl.recordTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
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