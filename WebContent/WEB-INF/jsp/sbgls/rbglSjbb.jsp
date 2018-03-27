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
	<script src="../js/main.js"></script>
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
			{ width: 70, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 120, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>

  <link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(function() {
		$("#xqNameId").change(
		function(){	
		  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val(),function(data) {
			var d=data.xqlist;
			$("#buildNoId option:gt(0)").remove();
			$("#cellNoId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var buildNo=d[i].buildNO;
				var opt="<option value='"+buildNo+"'>"+buildNo+"</option>"
				$("#buildNoId").append(opt);
			}
			});
		});
		
		$("#buildNoId").change(
			function() {
				$.get("findYhCellNOByBuild.action?build="+ $("#buildNoId").val()+"&xqName="+ $("#xqNameId").val(),function(data) {
					var dd=data;
					var d=dd.cellList;
					$("#cellNoId option:gt(0)").remove();
					for(var i=0;i<d.length;i++){
						var cellNo=d[i].cellNO;
						var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
						$("#cellNoId").append(opt);
					}
					});
				 });	
      });

 </script>
 <script type="text/javascript">
 //搜索
function searchInfo(){
    var   xqName = $('#xqNameId').val();
    var   buildNo=$('#buildNoId').val();
    var   cellNo=$('#cellNoId').val();
    var   houseNo=$('#houseNoId').val();
    var   recordTime1=$('#recordTime1').val();
    var   recordTime2=$('#recordTime2').val();
    var html ="";
	$.ajax({ 
		        url:"searchRb.action",
				async : false,
				dataType : "json",
				data : {
					"xqName" : xqName,
					"buildNo" : buildNo,
					"cellNo" : cellNo,
					"houseNo" : houseNo,
					"houseNo" : houseNo,
					"recordTime1" : recordTime1,
					"recordTime2" : recordTime2,
				},
				success : function(data) {
					$("#RbInfo").empty();
					var d=data.findList;
					for(var i=0;i<d.length;i++){
						var yhName=d[i].yhName;
						var xqName=d[i].xqName;
						var buildNo=d[i].buildNO;
						var cellNo=d[i].cellNO;
						var houseNo =d[i].houseNO ;
						var rbAd=d[i].rb.rbAd;
						var fmId=d[i].fm.valAd;
						var energy=d[i].rb.energy;	
						var power=d[i].rb.power;
						var flow=d[i].rb.flow;
						var inTmp=d[i].rb.inTmp;
						var outTmp=d[i].rb.outTmp;	
						var diffTmp=d[i].rb.diffTmp;
						var operTime=d[i].rb.operTime ;
						var qgId=d[i].fm.qgID;
						var energyLj=d[i].rb.energyLj;
						var readMTime=FormatDate(d[i].rb.readMTime) ;
						var recordTime=FormatDate(d[i].rb.recordTime) ;
						html+="<tr>";
						html+="<td class='text-center'>"+yhName+"</td>";
						html+="<td class='text-center'>"+xqName+"</td>";
						html+="<td class='text-center'>"+buildNo+"</td>";
						html+="<td class='text-center'>"+cellNo+"</td>";
						html+="<td class='text-center'>"+houseNo+"</td>";
						html+="<td class='text-center'>"+rbAd+"</td>";
						html+="<td class='text-center'>"+fmId+"</td>";
						html+="<td class='text-center'>"+energy+"</td>";
						html+="<td class='text-center'>"+flow+"</td>";
						html+="<td class='text-center'>"+energyLj+"</td>";
						html+="<td class='text-center'>"+power+"</td>";
						html+="<td class='text-center'>"+inTmp+"</td>";
						html+="<td class='text-center'>"+outTmp+"</td>";
						html+="<td class='text-center'>"+diffTmp+"</td>";
						html+="<td class='text-center' title='"+operTime+"'>"+operTime+"</td>";
						html+="<td class='text-center' title='"+recordTime+"'>"+recordTime+"</td>";
						html+="<td class='text-center' title='"+qgId+"'>"+qgId+"</td>";
						html+="<td class='text-center' title='"+readMTime+"'>"+readMTime+"</td>";
						
						html+="</tr>";
						}
					  html+="</tbody>"
					  html+="</table>";
					  html+="</div>";
					$("#RbInfo").append(html);
				}

			})
		}
//时间转换
function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
</script>
	<script type="text/javascript">
	//导出
	function doExportExcel() {
		var xqName = $('#xqNameId').val();
		var buildNo = $('#buildNoId').val();
		var cellNo = $('#cellNoId').val();
		var houseNo = $('#houseNoId').val();
		var recordTime1=$('#recordTime1').val();
		var recordTime2=$('#recordTime2').val();
		window.open("RbdoExportExcel.action?xqName=" + xqName + "&buildNo="
				+ buildNo + "&cellNo=" + cellNo + "&houseNo=" + houseNo
			+"&recordTime1="+ recordTime1+"&recordTime2="+ recordTime2);
	}
</script>
</head>
<body style="padding-left: 10px;">
 <ul class="nav nav-tabs">
<li> <a href="/HotPower/YhInfo/findYhNameList.action">锁闭阀数据报表</a></li>
<li class="active"><a href="#">双系统热表数据</a></li>
<li><a href="/HotPower/RbrlCon/fdRbrlUser.action">单系统热表数据</a></li>
</ul>
		<label for="xqNameId">选择小区</label> 
		<select id="xqNameId" name="xqName">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择小区名称--</option>
				<c:forEach items="${yhInfoList}" var="yhInfolist">
					<option>${yhInfolist.xqName}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;

		<label for="buildNoId">楼栋号</label>
		 <select name="buildNo" id="buildNoId">
			<option value=0>--选择楼栋号--</option>
		</select>
	&nbsp;&nbsp;&nbsp;
		<label for="cellNoId">单元号</label> 
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>

	户号：<input type="text" name="houseNo" id="houseNoId" value="${houseNo}"  />
		<label for="readMTime">选择时间:</label>
		  <input type="text" id="recordTime1" name="recordTime1" class="Wdate" onfocus="WdatePicker();" />
		-<input type="text" id="recordTime2"  name="recordTime2"  class="Wdate" onfocus="WdatePicker();" />	
<input class="btn btn-success" type="button" onclick="searchInfo()" value="搜索">
<input type="button" value="导出"class="btn btn-success" onclick="doExportExcel()" />
<hr>
<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
				<th>用户姓名</th>
				<th>小区名称</th>
				<th>楼栋号</th>
				<th>单元号</th>
				<th>户号</th>
				<th>热表编号</th>
				<th>阀门地址</th>
				<th>累计热量</th>
				<th>瞬时流量</th>
				<th>累计流量</th>
				<th>热功率</th>
				<th>进水温度</th>
				<th>回水温度</th>
				<th>温差</th>
				<th>工作时间</th>
				<th>更新时间</th>
				<th>区管地址</th>
				<th>实时时间</th>
     </tr>
  </thead>

  <tbody id="RbInfo">

		<c:forEach  var="r" items="${findList}" varStatus="status">
	 <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>  >
					<td>${r.yhName}</td>
					<td>${r.xqName}</td>
					<td>${r.buildNO}</td>
					<td>${r.cellNO}</td>
					<td>${r.houseNO}</td>
					<td>${r.rb.rbAd}</td>
					<td>${r.fm.valAd}</td>
					<td>${r.rb.energy}</td>
					<td>${r.rb.flow}</td>
					<td>${r.rb.energyLj}</td>
					<td>${r.rb.power}</td>
					<td>${r.rb.inTmp}</td>
					<td>${r.rb.outTmp}</td>
					<td>${r.rb.diffTmp}</td>
					<td>${r.rb.operTime}</td>
					<td><fmt:formatDate value="${r.rb.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					<td>${r.fm.qgID}</td>
					<td><fmt:formatDate value="${r.rb.readMTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>
</body>
</html>