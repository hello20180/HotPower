<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报修登记</title>
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
			font-family: Arial,??sans-serif;
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
			height: 520,
			colModal: [
				{ width: 50, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 60, align: 'center' },
				{ width: 60, align: 'center' },
				{ width: 60, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 300, align: 'center' },
				{ width: 70, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 70, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 70, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 70, align: 'center' },
				{ width: 70, align: 'center' }

			],
			sort: true
		});
		
	});
	</script>
	
	




<link href="../css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="../js/main.js"></script>
</head>
 <script type="text/javascript">  
	/*页面加载就开始执行js*/
	$(document).ready(function() {
		$("#placeId").change(
		function(){	
		  $.get("findHes.action?place="+ $("#placeId").val(),function(data) {
			var dd=data;
			var d=dd.heslist;
			$("#hesId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var hesName=d[i].hESName;
				var opt="<option value='"+hesName+"'>"+hesName+"</option>"
				$("#hesId").append(opt);
			}
			});
		});
  });
	</script>
	 <script type="text/javascript"> 
	//搜索
	function chaxun() {
		var place = $('#placeId').val();
		var hesName = $('#hesId').val();
		var state = $('#statesId').val();
		var html ="";
		var sc="";
		$.ajax({
			data : {
				"place" : place,
				"hesName" : hesName,
				"state" : state,
		        },
			url:"listState1.action",
			async : false,
			dataType : "json",
			success : function(data) {
				$("#stateCount").empty();
				$("#xsInfos").empty();
				var d=data.rep;
				var c=data.cx;
				var y=data.ywc;	
				var w=data.wjd;
				var z=data.yjd;
				sc+="<h4><span>  总记录："+c+"条  &nbsp;&nbsp;  未接单："+w+" 条  &nbsp;&nbsp;   已完成："+y+"条 &nbsp;&nbsp;   已接单："+z+"条</span></h4>"
					for(var i=0;i<d.length;i++){
						var id=d[i].id;	
						
						if(d[i].hea.place==undefined){
							var place="";
						}else{
							var place=d[i].hea.place;	
						}
						
						if(d[i].hea.hesName==undefined){
							var hesName="";
						}else{
					 		var hesName=d[i].hea.hesName; 
						}
						
						if(d[i].xqName==undefined){
							var xqName="";
						}else{
							var xqName=d[i].xqName;
						}
						
						if(d[i].buildNo==undefined){
							var buildNo="";
						}else{
					 		var buildNo=d[i].buildNo;
						}

						if(d[i].cellNo==undefined){
							var cellNo="";
						}else{
							var cellNo=d[i].cellNo;
						}

						if(d[i].cs==undefined){
							var cs="";
						}else{
							var cs =d[i].cs ;
						}
						if(d[i].sh==undefined){
							var sh="";
						}else{
							var sh =d[i].sh ;
						}

						if(d[i].name==undefined){
							var name="";
						}else{
							var name=d[i].name;
						}
						
						if(d[i].telephone==undefined){
							var telephone="";
						}else{
							var telephone=d[i].telephone;
						}
						
						if(d[i].problem==undefined){
							var problem="";
						}else{
							var problem=d[i].problem;	
						}

						if(d[i].tJname==undefined){
							var tJname="";
						}else{
							var tJname=d[i].tJname;
						}

						if(d[i].tJtime==undefined){
							var tJtime="";
						}else{
		 					var tJtime=FormatDate(d[i].tJtime);
						}
						
	 					if(d[i].jSname==undefined){
							var jSname="";
						}else{
							var jSname=d[i].jSname;	
						}
						
						if(d[i].jStime==undefined){
							var jStime="";
						}else{
							var jStime=FormatDate(d[i].jStime);
						}
						
						if(d[i].wCname==undefined){
							var wCname="";
						}else{
							var wCname=d[i].wCname;
						}
						
						if(d[i].wCtime==undefined){
							var wCtime="";
						}else{
							var wCtime=FormatDate(d[i].wCtime);
						}
						
						if(d[i].state==undefined){
							var state="";
						}else{
							var state=d[i].state; 
						}
						html+="<tr>";
						if(state=="已完成"){
						html+="<td bgcolor='#99CCFF'><input type='checkbox' value='"+id+"'/></td>";
						html+="<td bgcolor='#99CCFF'  align='center' nowrap='nowrap' title='"+place+"'>"+place+"</td>";
						html+="<td bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+hesName+"'>"+hesName+"</td>"; 
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+xqName+"'>"+xqName+"</td>";
						html+="<td bgcolor='#99CCFF'  align='center' nowrap='nowrap' title='"+buildNo+"'>"+buildNo+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+cellNo+"'>"+cellNo+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+cs+"'>"+cs+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+sh+"'>"+sh+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+name+"'>"+name+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+telephone+"'>"+telephone+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+problem+"'>"+problem+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+tJname+"'>"+tJname+"</td>";
	 			 		html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+tJtime+"'>"+tJtime+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+jSname+"'>"+jSname+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+jStime+"'>"+jStime+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+wCname+"'>"+wCname+"</td>";
	 					html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+wCtime+"'>"+wCtime+"</td>";
						html+="<td  bgcolor='#99CCFF' align='center' nowrap='nowrap' title='"+state+"'>"+state+"</td>"; 
						}else{
							html+="<td><input type='checkbox' value='"+id+"'/></td>";
							html+="<td  align='center' nowrap='nowrap' title='"+place+"'>"+place+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+hesName+"'>"+hesName+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+xqName+"'>"+xqName+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+buildNo+"'>"+buildNo+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+cellNo+"'>"+cellNo+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+cs+"'>"+cs+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+sh+"'>"+sh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+name+"'>"+name+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+telephone+"'>"+telephone+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+problem+"'>"+problem+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+tJname+"'>"+tJname+"</td>";
		 			 		html+="<td align='center' nowrap='nowrap' title='"+tJtime+"'>"+tJtime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jSname+"'>"+jSname+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jStime+"'>"+jStime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+wCname+"'>"+wCname+"</td>";
		 					html+="<td align='center' nowrap='nowrap' title='"+wCtime+"'>"+wCtime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+state+"'>"+state+"</td>"; 
						}
						html+="</tr>";
					}
				  $("#stateCount").append(sc);
				$("#xsInfos").append(html);
			},
		});
	};
	function FormatDate (strTime) {
	    var date = new Date(strTime);
	    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
 </script>


<body>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">报修登记详情</div>
		<div class="panel-body"  >
  <button type="button" class="btn btn-success" onClick="openaddUserPage()">添加</button>
		
			&nbsp;&nbsp;
			   <button type="button" class="btn btn-success"
				onClick="openEditUserPage()">修改</button>
	&nbsp;&nbsp;
	 <button type="button" class="btn btn-success"
				onClick="openDeletePage()">删除</button>
	&nbsp;&nbsp;
			<button type="button" class="btn btn-success btn-sm"><a href="StateChart.action">图表分析</a></button>
&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="placeId">选择处</label> 
		<select id="placeId" name="place">
			<c:if test="${!empty list }">
			   <option>--选择处--</option>
				<c:forEach items="${list}" var="list">
					<option >${list.place}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;
		<label for="hesId">换热站名称</label>
		 <select name="hesName" id="hesId">
			<option>--选择换热站--</option>
		</select>
		&nbsp;
		<label for="statesId">状态</label> 
		<select id="statesId" name="state">
		<c:if test="${!empty st }">
			   <option>--状态--</option>
				<c:forEach items="${st}" var="st">
					<option value="${st.state}">${st.state}</option>
				</c:forEach>
		</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;
		<input onclick="chaxun()" type="button" class="btn btn-success" value="搜索" /> &nbsp;&nbsp;
		
			<div id="stateCount"><h4><span> 总记录：${sums}条  &nbsp;&nbsp;  未接单：${wjd} 条  &nbsp;&nbsp;   已完成：${ywc}条 &nbsp;&nbsp;   已接单：${yjd}条</span></h4></div>
		</div>
			<div id="tablediv" >
			<form>
			<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
							<th></th>
							<th>管理处</th>
							<th>交换站</th>
							<th>小区名</th>
							<th>楼栋号</th>
							<th>单元号</th>
							<th>楼层号</th>
							<th>门牌号</th>
							<th>联系人</th>
							<th>联系电话</th>
							<th>故障</th>
							<th>提交人</th>
							<th>提交时间</th>
							<th>接收人</th>
							<th>接收时间</th>
							<th>维修人</th>
							<th>维修时间</th>
							<th>状态</th>
							</tr>
					</thead>
				
					<tbody id="xsInfos">
						<c:forEach items="${rep}" var="repa" varStatus="status">

							<tr>
							<c:choose>
							
							<c:when test="${repa.state=='已完成'}">
								<td bgcolor="#99CCFF"><input type="checkbox" value="${repa.id }" /></td>
								<td bgcolor="#99CCFF" title="${repa.hea.place}">${repa.hea.place}</td>
								<td bgcolor="#99CCFF" title="${repa.hea.hesName}">${repa.hea.hesName}</td>
								
								<td bgcolor="#99CCFF" title="${repa.xqName}">${repa.xqName}</td>
								<td bgcolor="#99CCFF" title="${repa.buildNo}">${repa.buildNo}</td>
								<td bgcolor="#99CCFF" title="${repa.cellNo}">${repa.cellNo}</td>

								<td bgcolor="#99CCFF" title="${repa.cs}">${repa.cs}</td>
								<td bgcolor="#99CCFF" title="${repa.sh}">${repa.sh}</td>
								<td bgcolor="#99CCFF" title="${repa.name}">${repa.name}</td>
								<td bgcolor="#99CCFF" title="${repa.telephone}">${repa.telephone}</td>


								<td bgcolor="#99CCFF" title="${repa.problem}">${repa.problem}</td>
								<td bgcolor="#99CCFF" title="${repa.tJname}">${repa.tJname}</td>
								<td bgcolor="#99CCFF" title="<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" />">
								<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>

								<td bgcolor="#99CCFF" title="${repa.jSname}">${repa.jSname}</td>
								<td bgcolor="#99CCFF" title="<fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" />" ><fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td bgcolor="#99CCFF" title="${repa.wCname}">${repa.wCname}</td>

								<td bgcolor="#99CCFF" title="<fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td bgcolor="#99CCFF" title="${repa.state}">${repa.state}</td>
							</c:when>
							<c:otherwise>
								<td><input type="checkbox" value="${repa.id }" /></td>
								<td title="${repa.hea.place}">${repa.hea.place}</td>
								<td title="${repa.hea.hesName}">${repa.hea.hesName}</td>
								<td  title="${repa.xqName}">${repa.xqName}</td>
								<td  title="${repa.buildNo}">${repa.buildNo}</td>
								<td title="${repa.cellNo}">${repa.cellNo}</td>
								<td  title="${repa.cs}">${repa.cs}</td>
								<td  title="${repa.sh}">${repa.sh}</td>
								<td title="${repa.name}">${repa.name}</td>
								<td  title="${repa.telephone}">${repa.telephone}</td>
								<td  title="${repa.problem}">${repa.problem}</td>
								<td  title="${repa.tJname}">${repa.tJname}</td>
								<td title="<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" />">
								<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td  title="${repa.jSname}">${repa.jSname}</td>
								<td  title="<fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" />" ><fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td  title="${repa.wCname}">${repa.wCname}</td>
								<td title="<fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td  title="${repa.state}">${repa.state}</td>
							</c:otherwise>
							</c:choose>
							</tr>

						</c:forEach>
	  </tbody>
</table>
</div>
</form>
</div>
</div>
<!---------------------------删除页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="deleteUser">
		<div class="modal-dialog">
			<div class="modal-content">
			<form action="">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">删除</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-3"></div>
					</div>
				</div>
				<div class="modal-footer bg-primary"
					style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!---------------------------删除页面结束 --------------------------------------------------------- -->
	<!---------------------------修改页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="edit">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">修改报修单</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="updateRepair.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									
						<div class="form-group">						
							<label for="edit_xqName" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
							
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName" readonly="readonly" 
									class="form-control" placeholder="xqName" />
							</div>					
						</div>
						
						<div class="form-group">
							<label for="edit_buildNo" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
							<div class="col-sm-4">
								<input id="edit_buildNo" type="text" name="buildNo" readonly="readonly"
									class="form-control" placeholder="buildNo" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_cellNo" class="col-sm-2 col-sm-offset-3 control-label">单元号</label>
							<div class="col-sm-4">
								<input id="edit_cellNo" type="text" name="cellNo" readonly="readonly"
									class="form-control" placeholder="cellNo" />
							</div>
						</div>
						
						
						
							<div class="form-group">						
							<label for="edit_cs" class="col-sm-2 col-sm-offset-3 control-label">楼层号</label>
							
							<div class="col-sm-4">
								<input id="edit_cs" type="text" name="cs"
									class="form-control" placeholder="cs" />
							</div>					
						</div>
						
								<div class="form-group">						
							<label for="edit_sh" class="col-sm-2 col-sm-offset-3 control-label">门牌号</label>
							
							<div class="col-sm-4">
								<input id="edit_sh" type="text" name="sh"
									class="form-control" placeholder="sh" />
							</div>					
						</div>
						
							
						
						<div class="form-group">						
							<label for="edit_name" class="col-sm-2 col-sm-offset-3 control-label">联系人</label>
							
							<div class="col-sm-4">
								<input id="edit_name" type="text" name="name"
									class="form-control" placeholder="name" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_telephone" class="col-sm-2 col-sm-offset-3 control-label">电话</label>
							
							<div class="col-sm-4">
								<input id="edit_telephone" type="text" name="telephone"
									class="form-control" />
							</div>					
						</div>
							
						
						<div class="form-group">	
							<label for="jSname" class="col-sm-2 col-sm-offset-3 control-label">接单人</label>
							<div class="col-sm-4">
						<select name="jSname" id="jSnameID">
							<option value=>--请选择接收人--</option>
						</select>		
						</div>
						</div>	
							<div class="form-group">						
							<label for="edit_problem" class="col-sm-2 col-sm-offset-3 control-label">故障</label>
							
							<div class="col-sm-4">
								<textarea id="edit_problem" name="problem" style="overflow-x:hidden;width:300px;height:100px;" class="form-control" placeholder="problem"></textarea>
							</div>					
						</div>
						
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
								<button type="button" onClick="edit()" class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------修改页面结束 --------------------------------------------------------- -->
	
<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加报修单</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="<c:url value='InsertRepair.action'/>" method="post">
						
						
						<!-- 获取所有的处 -->		
						<div class="form-group">						
						<label for="place" class="col-sm-2 col-sm-offset-3 control-label">管理处:</label>
						<div  class="col-sm-4" >
						<select name="place" id="placeID">
							<option value=>--请选择处--</option>
						</select>
						</div>					
						</div>			
						
						<!-- 根据处获取所有的换热站-->		
						<div class="form-group">						
						<label for="hESName" class="col-sm-2 col-sm-offset-3 control-label">交换站:</label>
						<div  class="col-sm-4" >
						<select name="hESName" id="hESNameID">
							<option value=>--请选择交换站--</option>
						</select>
						</div>					
						</div>			
						
						
							<!-- 获取所有的小区 -->										
						<div class="form-group">						
						<label for="xqName" class="col-sm-2 col-sm-offset-3 control-label">小区名:</label>
						<div  class="col-sm-4" >
						<select name="xqName" id="xqNameId">
							<option value=>--请选择小区--</option>
						</select>
						</div>
					
						</div>								
						<!-- 获取楼栋号 -->	
						<div class="form-group" >
						<label for="buildNoId" class="col-sm-2 col-sm-offset-3 control-label">楼栋号:</label>
						<div class="col-sm-4">
						 <select name="buildNo" id="buildNoId" >
							<option value=>--选择楼栋号--</option>
						</select>
						</div>
						</div>
						<!-- 获取楼单元号 -->	
						<div class="form-group">
						<label for="cellNoId" class="col-sm-2 col-sm-offset-3 control-label">单元号:</label> 
						<div class="col-sm-4">
						<select name="cellNo" id="cellNoId" >
							<option value=>--选择单元号--</option>
						</select>
						</div>
						</div>		
						<!-- 获取层号 -->					
						<div class="form-group">						
							<label for="CS" class="col-sm-2 col-sm-offset-3 control-label">楼层号:</label>
							
							<div class="col-sm-4">
								<input id="CSId" type="text" name="cs" onblur="findcs()"
									class="form-control" placeholder="cs" />
							</div>					
						</div>
						<!-- 获取室号 -->
						<div class="form-group">						
							<label for="SH" class="col-sm-2 col-sm-offset-3 control-label">门牌号:</label>
							
							<div class="col-sm-4">
								<input id="SHId" type="text" name="sh" onblur="findsh()"
									class="form-control" placeholder="sh" />
							</div>					
						</div>		
							<div class="form-group">						
							<label for="name" class="col-sm-2 col-sm-offset-3 control-label">联系人:</label>
						<div class="col-sm-4">
						<input id="name"  type="text" name="name" 
								class="form-control" placeholder="name" >
						</div>
						</div>
						<div class="form-group">						
							<label for="telephone" class="col-sm-2 col-sm-offset-3 control-label">电话:</label>
						<div class="col-sm-4"><input id="telephone" type="text" name="telephone" onblur="findDH()" class="form-control" placeholder="telephone" ></div>
						</div>
						
						<div class="form-group">	
							<label for="jSname" class="col-sm-2 col-sm-offset-3 control-label">接单人:</label>
							<div class="col-sm-4">
						<select name="jSname" id="jSnameId" >
							<option value=>--请选择接收人--</option>
						</select>		
						</div>
						</div>
					
						<div class="form-group">						
						<label for="problem" class="col-sm-2 col-sm-offset-3 control-label">故障:</label>
							<div class="col-sm-4">
							<textarea id="problem"  style="overflow-x: hidden;width:300px;height:100px;" class="form-control" name="problem" placeholder="problem"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
							<button type="button" onClick="add()"
									class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<!-- 添加用户结束 -------------------------------------------------- -->
</body>
<script type="text/javascript">
function add(){
	
	var xqName=$("#add select[name=xqName]");
	var buildNo=$("#add select[name=buildNo]");
	var cellNo=$("#add select[name=cellNo]");
	var CS=$("#add input[name=cs]");
	var SH=$("#add input[name=sh]");
	var jSname=$("#add select[name=jSname]");
	var name=$("#add input[name=name]");
	var telephone=$("#add input[name=telephone]");
	var problem=$("#add textarea[name=problem]");
	
	
	
	var CsId=document.getElementById("CSId").value;
	 if(isNaN(CsId)){
			 sAlert('层号必须是数字！');
			document.getElementById("CSId").value="";
			return false;
	 }	
	
	var ShId=document.getElementById("SHId").value;
			 if(isNaN(ShId)){
					 sAlert('室号必须是数字！');
					document.getElementById("SHId").value="";
					return false;
			 }
	
	if(jSname.val()==null||jSname.val()==""||xqName.val()==null||xqName.val()==""||buildNo.val()==null||buildNo.val()==""||cellNo.val()==null||cellNo.val()==""||CS.val()==null||CS.val()==""||SH.val()==null||SH.val()==""||name.val()==null||name.val()==""||telephone.val()==null||telephone.val()==""||problem.val()==null||problem.val()==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}
	
	var telephone1=document.getElementById("telephone").value;
	 if(isNaN(telephone1)){
			 sAlert('联系方式请输入数字');
			document.getElementById("telephone").value="";
			return false;
	 }
	 
	$("#add form").submit();
}


//添加	
function openaddUserPage(){
		$.ajax({
		type:"post",
		url:"findYhNameList.action",//获取json数据
		dataType:"json",
		success:function(data){
		
			var dd=data;
			var d=dd.listPlace;
			$("#placeID  option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var place=d[i].place;
				var opt="<option value='"+place+"'>"+place+"</option>";
				$("#placeID").append(opt);
			}
		},
	})
	
	/*根据处选择交换站  */
	$("#placeID").change(
  function(){	
  $.get("findHes.action?place="+ $("#placeID").val(),function(data) {
	var dd=data;
	var d=dd.heslist; 
	$("#xqNameId option:gt(0)").remove();
	$("#buildNoId option:gt(0)").remove();
	$("#cellNoId option:gt(0)").remove();
	$("#hESNameID option:gt(0)").remove();
	for(var i=0;i<d.length;i++){
		var hesName=d[i].hESName;
		var opt="<option value='"+hesName+"'>"+hesName+"</option>"
		$("#hESNameID").append(opt);
	}
	});
});	
	

	/*根据处,换热站选择小区名称   */
		$("#hESNameID").change(
	function(){	
	  $.get("findXqNamebyPlace.action?place="+ $("#placeID").val()+"&hESName="+ $("#hESNameID").val(),function(data) {
		var dd=data;
		var d=dd.xqNameList; 
		var js=dd.jsname;
		$("#xqNameId option:gt(0)").remove();
		$("#buildNoId option:gt(0)").remove();
		$("#cellNoId option:gt(0)").remove();
		$("#jSnameId option:gt(0)").remove();
		for(var i=0;i<d.length;i++){
			var xqName=d[i].xqName;
			var opt="<option value='"+xqName+"'>"+xqName+"</option>"
			$("#xqNameId").append(opt);
			
		}
		for(var i=0;i<js.length;i++){
			var userName=js[i].userName;
			var opt="<option value='"+userName+"'>"+userName+"</option>";
			$("#jSnameId").append(opt);
			
		}
		});
	});	
	//根据处,换热站选择小区
		$("#xqNameId").change(
	function(){	
	  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val()+"&place="+ $("#placeID").val()+"&hESName="+ $("#hESNameID").val(),function(data) {
		var dd=data;
		var d=dd.xqlist;
		$("#buildNoId option:gt(0)").remove();
		$("#cellNoId option:gt(0)").remove();
		for(var i=0;i<d.length;i++){
			
			var buildNo=d[i].lH;
			var opt="<option value='"+buildNo+"'>"+buildNo+"</option>"
			$("#buildNoId").append(opt);
		}
		});
	});
	
	$("#buildNoId").change(
			function() {
				$.get("findYhCellNOByBuild.action?build="+ $("#buildNoId").val()+"&xqName="+ $("#xqNameId").val()+"&place="+ $("#placeID").val()+"&hESName="+ $("#hESNameID").val(),function(data) {
					$("#cellNoId option:gt(0)").remove();
					var dd=data;
					var d=dd.cellList;
					$("#cellNoId option:gt(0)").remove();
					for(var i=0;i<d.length;i++){
						var cellNo=d[i].dYH;
						var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
						$("#cellNoId").append(opt);
					}
					});
				 });
	$("#add").modal({keyboard:false}); 
}

</script>

<script type="text/javascript">

//数据不允许为空
function edit(){
		var jSname=$("#edit select[name=jSname]");
		var cs=$("#edit input[name=cs]");
		var sh=$("#edit input[name=sh]");
		var name=$("#edit input[name=name]");
		var telephone=$("#edit input[name=telephone]");
		var problem=$("#edit textarea[name=problem]");
		if(jSname.val()==null||jSname.val()==""||sh.val()==null||sh.val()==""||cs.val()==null||cs.val()==""||name.val()==null||name.val()==""||telephone.val()==null||telephone.val()==""||problem.val()==null||problem.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		}
		var css=document.getElementById("edit_cs").value;
		 if(isNaN(css)){
				 sAlert('层号必须是数字！');
				document.getElementById("edit_cs").value="";
				return false;
		 }
			var shs=document.getElementById("edit_sh").value;
			 if(isNaN(shs)){
					 sAlert('室号必须是数字！');
					document.getElementById("edit_sh").value="";
					return false;
			 }
		 var telephone1=document.getElementById("edit_telephone").value;
		 if(isNaN(telephone1)){
				 sAlert('联系方式请输入数字');
				document.getElementById("edit_telephone").value="";
				return false;
		 }
		$("#edit form").submit();
	}

function openEditUserPage(){
	$.ajax({
		type:"post",
		url:"findYhNameList.action",//获取json数据
		dataType:"json",
		success:function(data){
			var dd=data;
			var js=dd.jsname;
			$("#jSnameID option:gt(0)").remove();
			for(var i=0;i<js.length;i++){
				var userName=js[i].userName;
				var opt="<option value='"+userName+"'>"+userName+"</option>";
				$("#jSnameID").append(opt);
			}
		},
	});
	var ckbs=$("#xsInfos input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	var xqName=ckbs.parent().next().next().next().text();
	var buildNo=ckbs.parent().next().next().next().next().text();
	var cellNo=ckbs.parent().next().next().next().next().next().text();
	var cs=ckbs.parent().next().next().next().next().next().next().text();
	var sh=ckbs.parent().next().next().next().next().next().next().next().text();
	var name=ckbs.parent().next().next().next().next().next().next().next().next().text();
	var telephone=ckbs.parent().next().next().next().next().next().next().next().next().next().text();
	var problem=ckbs.parent().next().next().next().next().next().next().next().next().next().next().text();
	$("#edit_id").val(id);
	$("#edit_xqName").val(xqName);
	$("#edit_buildNo").val(buildNo);
	$("#edit_cellNo").val(cellNo);
	$("#edit_cs").val(cs);
	$("#edit_sh").val(sh);
	$("#edit_name").val(name);
	$("#edit_telephone").val(telephone);
	$("#edit_problem").val(problem);
	
	$("#edit").modal({keyboard:false}); 
}
 
//删除
function openDeletePage(){
		var ckbs=$("#xsInfos input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的报修登记");
			return false;
		}
		var id=[];
		$.each(ckbs,function(index,data){
			id[index]=$(data).val();
		});
		var names=ckbs.parent().next();
		var deleteUserNames=[];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个报修登记吗？<br/>"+"</span></h4>");
		$.each(names,function(index,data){
			deleteUserNames[index]=$(data).text();
			$("#deleteUser .modal-body h4").append("<span class='small  '>"+"&nbsp;&nbsp;["+$(data).text()+"]&nbsp;&nbsp;"+"</span>");
		});
		$("#deleteUser .modal-body .col-sm-7").append("<div><button type='button' id='btn_delete_user' class='btn btn-sm btn-primary'>确定</button>&nbsp;<button id='closeDeleteUserPage' class='btn btn-sm btn-primary' type='button'>取消</button></div>");
		$("#closeDeleteUserPage").click(function(){
			$("#deleteUser").modal("hide");
		});
		$("#btn_delete_user").click(function(){
				$.ajax({
				url :"delete.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="Repairlist.action";
				}
				
			});		
		});
		$("#deleteUser").modal({keyboard:false});
	}

</script>
</html>
	