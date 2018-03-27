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
			{ width: 50, align: 'center' },
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
 function drb(){
	var ckbs=$("#RbInfo input[type=checkbox]:checked");
	var apiContentStr;
	if(ckbs.length==0){
		alert("请选择要读取的热表");
		return false;
	}
	var ids=[];
	$.each(ckbs,function(index,data){
		ids[index]=$(data).val();
	})
	var apiContentStr="";
	for(var i=0;i<ids.length;i++){
   			 apiContentStr=ids[i];
   			$.ajax({
   				url:"/HotPower/RbsglController/rebInfo.action",
   				async:false,
   				dataType:"json",
   				data:{	
   					"ids":apiContentStr,
   				},
   				success:function(data){
   					msg=data.js
   					if(msg=="0"){
   						alert(data.rbId+"读热表接收指令成功!")
   					}
   					if(msg=="1"){
   						alert(data.rbId+" 读热表失败!")
   					}
   					if(msg=="2"){
   						alert(data.rbId+" 数据失败");
   					}
   					if(msg=="3"){
   						alert(data.rbId+" 超时")
   					}
   					if(msg=="4"){
   					    alert(data.rbId+" 集中器不在线");
   					}
   					if(msg=="5"){
   					    alert("该用户无权限!");
   						
   					}
   				}
   				
   			});
   	}	
	searchInfo();
}
 
 
 
 </script>
 <script type="text/javascript">
 //搜索
function searchInfo(){
    var   xqName = $('#xqNameId').val();
    var   buildNo=$('#buildNoId').val();
    var   cellNo=$('#cellNoId').val();
    var   houseNo=$('#houseNoId').val();
    var html ="";
	$.ajax({ 
		        url:"searchInfo.action",
				async : false,
				dataType : "json",
				data : {
					"xqName" : xqName,
					"buildNo" : buildNo,
					"cellNo" : cellNo,
					"houseNo" : houseNo,
					"houseNo" : houseNo,
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
						var rbType=d[i].rb.rbType ;
						var qgId=d[i].fm.qgID;
						var bz=d[i].bz;
						var energyLj=d[i].rb.energyLj;
						var readMTime=FormatDate(d[i].rb.readMTime) ;
						var recordTime=FormatDate(d[i].rb.recordTime) ;
						var rbExp=d[i].rb.rbExp;
						html+="<tr>";
						html+="<td class='text-center'><input type='checkbox'  value='"+rbAd+"'/></td>";
						if(rbExp==1){
							html+="<td class='text-center' title='"+bz+"'><font color='red'>"+yhName+"</font></td>";
						}else if(rbExp==2){
							html+="<td class='text-center' title='"+bz+"'><font color='blue'>"+yhName+"</font></td>";	
						}else{
							html+="<td class='text-center' title='"+bz+"'>"+yhName+"</td>";
						}
					
						html+="<td class='text-center'>"+xqName+"</td>";
						html+="<td class='text-center'>"+buildNo+"</td>";
						html+="<td class='text-center'>"+cellNo+"</td>";
						html+="<td class='text-center'>"+houseNo+"</td>";
						html+="<td class='text-center'>"+rbAd+"</td>";
						html+="<td class='text-center'>"+fmId+"</td>";
						html+="<td class='text-center'>"+energy+"</td>";
						html+="<td class='text-center'>"+power+"</td>";
						html+="<td class='text-center'>"+energyLj+"</td>";
						html+="<td class='text-center'>"+flow+"</td>";
						html+="<td class='text-center'>"+inTmp+"</td>";
						html+="<td class='text-center'>"+outTmp+"</td>";
						html+="<td class='text-center'>"+diffTmp+"</td>";
						html+="<td class='text-center' title='"+operTime+"'>"+operTime+"</td>";
						html+="<td class='text-center' title='"+recordTime+"'>"+recordTime+"</td>";
						html+="<td class='text-center' title='"+rbType+"'>"+rbType+"</td>";
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
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">热表管理</div>
 <div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">

	<p>
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

<input class="btn btn-success" type="button" onclick="searchInfo()" value="搜索">
<button type="button" class="btn btn-success" onclick="drb()">读取热表</button>
<hr>
<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
      			<th></th>
				<th>用户姓名</th>
				<th>小区名称</th>
				<th>楼栋号</th>
				<th>单元号</th>
				<th>户号</th>
				<th>热表编号</th>
				<th>阀门地址</th>
				<th>累计热量</th>
				<th>瞬时热量</th>
				<th>累计流量</th>
				<th>瞬时流量</th>
				<th>进水温度</th>
				<th>回水温度</th>
				<th>温差</th>
				<th>工作时间</th>
				<th>更新时间</th>
				<th>热表类型</th>
				<th>区管地址</th>
				<th>实时时间</th>
			
     </tr>
  </thead>

  <tbody id="RbInfo">

		<c:forEach  var="r" items="${findList}" varStatus="status">
	 <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>  >
	              <td><input class='text-center' type="checkbox" value="${r.rb.rbAd}" /></td>
	            <c:choose>
				<c:when test="${r.rb.rbExp==1}">
					<td title="${r.bz}"><font color="red">${r.yhName}</font></td>
				</c:when>
				<c:when test="${r.rb.rbExp==2}">
				<td title="${r.bz}"><font color="blue">${r.yhName}</font></td>
				</c:when>
				<c:otherwise>
				<td title="${r.bz}">${r.yhName}</td>
				</c:otherwise>
				</c:choose>
					<td>${r.xqName}</td>
					<td>${r.buildNO}</td>
					<td>${r.cellNO}</td>
					<td>${r.houseNO}</td>
					<td>${r.rb.rbAd}</td>
					<td>${r.fm.valAd}</td>
					<td>${r.rb.energy}</td>
					<td>${r.rb.power}</td>
					<td>${r.rb.energyLj}</td>
					<td>${r.rb.flow}</td>
					<td>${r.rb.inTmp}</td>
					<td>${r.rb.outTmp}</td>
					<td>${r.rb.diffTmp}</td>
					<td>${r.rb.operTime}</td>
					<td><fmt:formatDate value="${r.rb.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					<td>${r.rb.rbType}</td>
					<td>${r.fm.qgID}</td>
					<td><fmt:formatDate value="${r.rb.readMTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>
</div>
</div>
</body>
</html>