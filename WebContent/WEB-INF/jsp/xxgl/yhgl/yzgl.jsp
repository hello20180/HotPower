<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 550,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 65, align: 'center' },
			{ width: 65, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script src="../js/main.js"></script>
<script type="text/javascript">

//数据不允许为空

function add(){
	var yhName=$("#add input[name=yhName]");
	var xqName=$("#add select[name=xqName]");
	var buildNo=$("#add select[name=buildNo]");
	var cellNo=$("#add select[name=cellNo]");
	var houseNo=$("#add input[name=houseNo]");
		
	if(yhName.val()==null||yhName.val()==""||xqName.val()==null||xqName.val()==""||buildNo.val()==null||buildNo.val()==""||cellNo.val()==null||cellNo.val()==""||houseNo.val()==null||houseNo.val()==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}
	var houseNo1=document.getElementById("houseNo").value;
	 if(isNaN(houseNo1)){
			
			 sAlert('户号必须是数字！');
			document.getElementById("houseNo").value="";
			return;
	 }
	
	$("#add form").submit();
}

//修改时数据不能为空
function edit(){
	var yhName=$("#edit input[name=yhName]");
	var xqName=$("#edit input[name=xqName]");
	var buildNo=$("#edit input[name=buildNo]");
	var cellNo=$("#edit input[name=cellNo]");
	var houseNo=$("#edit input[name=houseNo]");
		
	if(yhName.val()==null||yhName.val()==""||xqName.val()==null||xqName.val()==""||buildNo.val()==null||buildNo.val()==""||cellNo.val()==null||cellNo.val()==""||houseNo.val()==null||houseNo.val()==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}
	var houseNo2=document.getElementById("edit_houseNo").value;
	 if(isNaN(houseNo2)){
			
			 sAlert('户号必须是数字！');
			document.getElementById("edit_houseNo").value="";
			return;
	 }
	
	$("#edit form").submit();
}


function openaddUserPage(){
	$.ajax({
		type:"post",
		url:"findYhNameList.action",//获取json数据
		dataType:"json",
		success:function(data){
			var dd=data;
			var d=dd.yhInfolist;
			$("#xqNameId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var xqName=d[i].xqName;
				var opt="<option value='"+xqName+"'>"+xqName+"</option>";
				$("#xqNameId").append(opt);
				
			}
		},
	})
	
  $("#xqNameId").change(
	function(){	
	  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val(),function(data) {
		var dd=data;
		var d=dd.xqlist;
		$("#buildNoId option:gt(0)").remove();
		$("#cellNoId option:gt(0)").remove();
		for(var i=0;i<d.length;i++){
			var buildNo=d[i].buildNo;
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
						var cellNo=d[i].cellNo;
						var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
						$("#cellNoId").append(opt);
					}
					});
				 });
	$("#add").modal({keyboard:false});
	
}
function openEditUserPage(){
	var ckbs=$("#users input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要修改的用户");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一个用户，您选择了"+ckbs.length+"个用户");
		return false;
	}
	var id=ckbs.val();
	var yhName=ckbs.parent().next().text();
	var xqName=ckbs.parent().next().next().text();
	var buildNo=ckbs.parent().next().next().next().text();
	var cellNo=ckbs.parent().next().next().next().next().text();
	var houseNo=ckbs.parent().next().next().next().next().next().text();
	var valAd=ckbs.parent().next().next().next().next().next().next().text();
	var yhbm=ckbs.parent().next().next().next().next().next().next().next().text();
	var rbAd=ckbs.parent().next().next().next().next().next().next().next().next().text();
	var rbType=ckbs.parent().next().next().next().next().next().next().next().next().next().text();
	var telephone=ckbs.parent().next().next().next().next().next().next().next().next().next().next().text(); 
	var mobilephone=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var email=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var buileArea=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var userArea=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var heatArea=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var billway=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var sfjf=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var sfqf=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text();
	var sftr=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var wcad=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var azwz=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var ylfq=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var faultInfor=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	$("#edit_yhName").val(yhName);
	$("#edit_xqName").val(xqName);
	$("#edit_buildNo").val(buildNo);
	$("#edit_cellNo").val(cellNo);
	$("#edit_houseNo").val(houseNo);
	$("#edit_valAd").val(valAd);
	$("#edit_yhbm").val(yhbm);
	$("#edit_rbAd").val(rbAd);
	$("#edit_rbType").val(rbType);
	$("#edit_telephone").val(telephone);
	$("#edit_mobilephone").val(mobilephone);
	$("#edit_email").val(email);
	$("#edit_buileArea").val(buileArea);
	$("#edit_userArea").val(userArea);
	$("#edit_heatArea").val(heatArea);	
	$("#edit_billway").val(billway);
	$("#edit_sfjf").val(sfjf);
	$("#edit_sfqf").val(sfqf);
	$("#edit_sftr").val(sftr);
	$("#edit_wcad").val(wcad);
	$("#edit_azwz").val(azwz);	
	$("#edit_ylfq").val(ylfq);
	$("#edit_faultInfor").val(faultInfor);
	$("#edit_id").val(id);	
	$("#edit").modal({keyboard:false});
}
//删除
function openDeletePage(){
		var ckbs=$("#users input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的用户");
			return false;
		}
		if(ckbs.length>1){
			 sAlert('对不起一次只能删除一个');
			return false;
		}
		var id=[];
		$.each(ckbs,function(index,data){
			id[index]=$(data).val();
		});
		var names=ckbs.parent().next();
		var deleteUserNames=[];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个用户吗？<br/>"+"</span></h4>");
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
					window.location.href="findYhInfo.action";
				}
			});		
		});
		
		$("#deleteUser").modal({keyboard:false});
	}
</script>
<script type="text/javascript">
  function yhflSear(){
		var yhfl = $('#yhfl').val();
		window.location="yhflSear.action?yhfl="+yhfl
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
			$("#users").empty();
			var d = data.YhList;
			for (var i = 0; i < d.length; i++) {
				var yhInfo = d[i].yhName;
				var xqName = d[i].xqName;
				var buildNo = d[i].buildNo;
				var cellNo = d[i].cellNo;
				var houseNo = d[i].houseNo;
				var valAd = d[i].valAd;
				var yhbm = d[i].yhbm;
				var rbAd = d[i].rbAd;
				var bu = "";
				var telephone = d[i].telephone;
				var mobilephone = d[i].mobilephone;
				var email = d[i].email;
				var buileArea = d[i].buileArea;
				var userArea = "";
				var heatArea = d[i].heatArea;
				var billway = d[i].billway;
				var sfjf = d[i].sfjf;
				var sfqf = d[i].sfqf;
				var sftr = d[i].sftr;
				var wcad = d[i].wcad;
				var azwz = d[i].azwz;
				var ylfq = d[i].ylfq;
				var faultInfor ="";
				var yhfl = d[i].yhfl;
				var id= d[i].id;
				if(yhfl==undefined){
					yhfl='';
				}
				if(azwz==undefined){
					azwz=''
				}
				if(ylfq==undefined){
					ylfq=''
				}
				html += "<tr>";
				html+="<td class='text-center'><input type='checkbox'  value='"+id+"'/></td>";
				html += "<td class='text-center'>" + yhInfo + "</td>";
				html += "<td class='text-center'>" + xqName + "</td>";
				html += "<td class='text-center'>" + buildNo + "</td>";
				html += "<td class='text-center'>" + cellNo+ "</td>";
				html += "<td class='text-center'>" + houseNo + "</td>";
				html += "<td class='text-center'>" + valAd + "</td>";
				html += "<td class='text-center'>" + yhbm + "</td>";
				html += "<td class='text-center'>" + rbAd + "</td>";
				html += "<td class='text-center'>" + bu + "</td>";
				html += "<td class='text-center'>" + telephone + "</td>";
				html += "<td class='text-center'>" + mobilephone + "</td>";
				html += "<td class='text-center'>" + email + "</td>";
				html += "<td class='text-center'>" + buileArea+ "</td>";
				html += "<td class='text-center'>" + userArea + "</td>";
				html += "<td class='text-center'>" + heatArea + "</td>";
				html += "<td class='text-center'>" + billway + "</td>";
				html += "<td class='text-center'>" + sfjf + "</td>";
				html += "<td class='text-center'>" + sfqf + "</td>";
				html += "<td class='text-center'>" + sftr + "</td>";
				html += "<td class='text-center'>" + wcad + "</td>";
				html += "<td class='text-center'>" + azwz + "</td>";
				html += "<td class='text-center'>" + ylfq+ "</td>";
				html += "<td class='text-center'>" + faultInfor + "</td>";
				html += "<td class='text-center'>" + yhfl + "</td>";
				html += "</tr>";
			}
			$("#users").append(html);
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
 <div class="panel panel-success">
 <div class="panel-heading">业主信息</div>
 <div class="panel-body">
 <div id="top">
  <button type="button" class="btn btn-success" onClick="openaddUserPage()">添加</button>&nbsp;&nbsp;
   <button type="button" class="btn btn-success" onClick="openEditUserPage()">修改</button>&nbsp;&nbsp;
			 <button type="button" class="btn btn-success" onClick="openDeletePage()">删除</button>
			用户类别： 
			<select id="yhfl" name="yhfl">
			<option value="">--选择用户类别--</option>
			<option value="普通用户">普通用户</option>
			<option value="重点监控">重点监控</option>
			<option value="特殊情况">特殊情况</option>
			</select>
   <button type="button" class="btn btn-success" onClick="yhflSear()">搜索</button>
   
   
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
			 </div>
			 <div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
						<th></th>
						<th>用户名</th>
						<th>小区名称</th>
						<th>楼栋号</th>
						<th>单元号</th>
						<th>户号</th>
						<th>阀门地址</th>
						<th>用户编号</th>
						<th>热表地址</th>
						<th>热表品牌</th>
						<th>联系方式</th>
						<th>手机号码</th>
						<th>电子邮箱</th>
						<th>建筑面积</th>
						<th>使用面积</th>
						<th>供热面积</th>
						<th>计费方式</th>
						<th>缴费情况</th>
						<th>欠费情况</th>
						<th>供热状态</th>
						<th>温度传感器地址</th>
						<th>安装位置</th>
						<th>压力分区</th>
						<th>备注</th>
						<th>用户类别</th>
					</tr>
				</thead>
				<tbody id="users">
					<c:forEach  var="yh" items="${yhgl}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${yh.id }" /></td>
                                     <td>${yh.yhName}</td>
                                     <td>${yh.xqName}</td>
                                       <td>${yh.buildNo}</td>
                                     <td>${yh.cellNo}</td>
                                       <td>${yh.houseNo}</td>
                                       <td>${yh.valAd}</td>
                                     <td>${yh.yhbm}</td>
                                       <td>${yh.rbAd}</td>
                                       <td></td>
                                     <td>${yh.telephone}</td>
                                     <td>${yh.mobilephone}</td>
                                       <td>${yh.email}</td>
                                     <td>${yh.buileArea}</td>
                                     <td>${yh.userArea}</td>
                                       <td>${yh.heatArea}</td>
                                     <td>${yh.billway}</td>
                                       <td>${yh.sfjf}</td>
                                     <td>${yh.sfqf}</td>
                                       <td>${yh.sftr}</td>
                                     <td>${yh.wcad}</td>
                                     <td>${yh.azwz}</td>
                                     <td>${yh.ylfq}</td>
                                     <td>${yh.faultInfor}</td> 
                                   <td>${yh.yhfl}</td> 
					</c:forEach>
				</tbody>
			</table>
			</div>
</div>
</div>

<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加用户</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="add.action" method="post">
						<div class="form-group">						
							<label for="yhName" class="col-sm-2 col-sm-offset-3 control-label">用户名</label>
							
							<div class="col-sm-4">
								<input id="yhName" type="text" name="yhName"
									class="form-control" placeholder="用户名" />
							</div>					
						</div>
												
						<div class="form-group">						
						<label for="xqName" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
							
							
						<select name="xqName" id="xqNameId">
						<option value=-1>--请选择--</option>
						</select>								
						</div>								
							
						<div class="form-group" >
							
						<label for="buildNoId" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
						 <select name="buildNo" id="buildNoId">
							<option value=0>--选择楼栋号--</option>
						</select>
						</div>
						
						<div class="form-group">
						<label for="cellNoId" class="col-sm-2 col-sm-offset-3 control-label">单元号</label> 
						<select name="cellNo" id="cellNoId">
							<option value=0>--选择单元号--</option>
						</select>
						</div>	
						
											
						<div class="form-group">						
							<label for="houseNo" class="col-sm-2 col-sm-offset-3 control-label">户号</label>
							
							<div class="col-sm-4">
								<input id="houseNo" type="text" name="houseNo"
									class="form-control" placeholder="houseNo" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="valAd" class="col-sm-2 col-sm-offset-3 control-label">阀门地址</label>
							
							<div class="col-sm-4">
								<input id="valAd" type="text" name="valAd"
									class="form-control" placeholder="阀门地址" />
							</div>					
						</div>
												
						
							<div class="form-group">						
							<label for="yhbm" class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							
							<div class="col-sm-4">
								<input id="yhbm" type="text" name="yhbm"
									class="form-control" placeholder="用户编号" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="rbAd" class="col-sm-2 col-sm-offset-3 control-label">热表地址</label>
							
							<div class="col-sm-4">
								<input id="rbAd" type="text" name="rbAd"
									class="form-control" placeholder="热表地址" />
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="rbType" class="col-sm-2 col-sm-offset-3 control-label">热表类型</label>
							
							<div class="col-sm-4">
								<input id="rbType" type="text" name="rbType"
									class="form-control" placeholder="热表类型" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="telephone" class="col-sm-2 col-sm-offset-3 control-label">联系电话</label>
							
							<div class="col-sm-4">
								<input id="telephone" type="text" name="telephone"
									class="form-control" placeholder="联系电话" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="mobilephone" class="col-sm-2 col-sm-offset-3 control-label">移动电话</label>
							
							<div class="col-sm-4">
								<input id="mobilephone" type="text" name="mobilephone"
									class="form-control" placeholder="移动电话" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="email" class="col-sm-2 col-sm-offset-3 control-label">E-mail</label>
							
							<div class="col-sm-4">
								<input id="email" type="text" name="email"
									class="form-control" placeholder="E-mail" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="buileArea" class="col-sm-2 col-sm-offset-3 control-label">建筑面积</label>
							
							<div class="col-sm-4">
								<input id="buileArea" type="text" name="buileArea"
									class="form-control" placeholder="建筑面积" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="userArea" class="col-sm-2 col-sm-offset-3 control-label">使用面积</label>
							
							<div class="col-sm-4">
								<input id="userArea" type="text" name="userArea"
									class="form-control" placeholder="使用面积" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="heatArea8" class="col-sm-2 col-sm-offset-3 control-label">供热面积</label>
							
							<div class="col-sm-4">
								<input id="heatArea" type="text" name="heatArea"
									class="form-control" placeholder="供热面积" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="billway" class="col-sm-2 col-sm-offset-3 control-label">计费方式</label>
							
							<div class="col-sm-4">
								<input id="billway" type="text" name="billway"
									class="form-control" placeholder="计费方式" />
							</div>					
						</div>
						
						<div class="form-group">						
							<label for="sfjf" class="col-sm-2 col-sm-offset-3 control-label">缴费情况</label>
							
							<div class="col-sm-4">
							<select id="sfjf" name="sfjf"
									class="form-control" placeholder="缴费情况">
							   <option value="是">是</option>
							    <option value="否">否</option>
							</select>
						    </div>
							
						</div>
							<div class="form-group">						
							<label for="sfqf" class="col-sm-2 col-sm-offset-3 control-label">欠费情况</label>
							<div class="col-sm-4">
							<select id="sfqf"  name="sfqf"
									class="form-control" placeholder="欠费情况">
							   <option value="是">是</option>
							    <option value="否">否</option>
							</select>					
							</div>					
						</div>
							<div class="form-group">						
							<label for="sftr" class="col-sm-2 col-sm-offset-3 control-label">供热状态</label>
							
							<div class="col-sm-4">
							<select id="sftr" name="sftr"
									class="form-control" placeholder="供热状态">
							   <option value="是">是</option>
							    <option value="否">否</option>
							</select>							
							</div>					
						</div>
							<div class="form-group">						
							<label for="wcad" class="col-sm-2 col-sm-offset-3 control-label">温度传感器地址</label>
							
							<div class="col-sm-4">
								<input id="wcad" type="text" name="wcad"
									class="form-control" placeholder="温度传感器地址" />
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="azwz" class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							
							<div class="col-sm-4">
								<input id="azwz" type="text" name="azwz"
									class="form-control" placeholder="安装位置" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="ylfq" class="col-sm-2 col-sm-offset-3 control-label">压力分区</label>
							
							<div class="col-sm-4">
								<input id="ylfq" type="text" name="ylfq"
									class="form-control" placeholder="压力分区" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="faultInfor" class="col-sm-2 col-sm-offset-3 control-label">故障信息</label>
							
							<div class="col-sm-4">
								<input id="faultInfor" type="text" name="faultInfor"
									class="form-control" placeholder="故障信息" />
							</div>					
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
							<button type="button" onclick="add()" class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<!-- 添加用户结束 -------------------------------------------------- -->
<!---------------------------修改页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="edit">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">修改业主信息</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="update.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
						
						<div class="form-group">						
							<label for="edit_yhName" class="col-sm-2 col-sm-offset-3 control-label">用户名</label>
							
							<div class="col-sm-4">
								<input id="edit_yhName" type="text" name="yhName"
									class="form-control" placeholder="用户名"  readonly="readonly"/>
							</div>					
						</div>
						
						
						<div class="form-group">						
							<label for="edit_xqName" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
							
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名" readonly="readonly"/>
							</div>					
						</div>
						
						<div class="form-group">
							<label for="edit_buildNo" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
							<div class="col-sm-4">
								<input id="edit_buildNo" type="text" name="buildNo"
									class="form-control" placeholder="楼栋号" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_cellNo" class="col-sm-2 col-sm-offset-3 control-label">单元号</label>
							<div class="col-sm-4">
								<input id="edit_cellNo" type="text" name="cellNo"
									class="form-control" placeholder="单元号" readonly="readonly"/>
							</div>
						</div>
							<div class="form-group">						
							<label for="edit_houseNo" class="col-sm-2 col-sm-offset-3 control-label">户号</label>
							
							<div class="col-sm-4">
								<input id="edit_houseNo" type="text" name="houseNo"
									class="form-control" placeholder="户号" readonly="readonly"/>
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_valAd" class="col-sm-2 col-sm-offset-3 control-label">阀门地址</label>
							
							<div class="col-sm-4">
								<input id="edit_valAd" type="text" name="valAd"
									class="form-control" placeholder="阀门地址" />
							</div>					
						</div>
												
						
							<div class="form-group">						
							<label for="edit_yhbm" class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							
							<div class="col-sm-4">
								<input id="edit_yhbm" type="text" name="yhbm"
									class="form-control" placeholder="用户编号" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_rbAd" class="col-sm-2 col-sm-offset-3 control-label">热表地址</label>
							
							<div class="col-sm-4">
								<input id="edit_rbAd" type="text" name="rbAd"
									class="form-control" placeholder="热表地址" />
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="edit_rbType" class="col-sm-2 col-sm-offset-3 control-label">热表类型</label>
							
							<div class="col-sm-4">
								<input id="edit_rbType" type="text" name="rbType"
									class="form-control" placeholder="热表类型" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_telephone" class="col-sm-2 col-sm-offset-3 control-label">联系方式</label>
							
							<div class="col-sm-4">
								<input id="edit_telephone" type="text" name="telephone"
									class="form-control" placeholder="联系方式" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_mobilephone" class="col-sm-2 col-sm-offset-3 control-label">移动电话</label>
							
							<div class="col-sm-4">
								<input id="edit_mobilephone" type="text" name="mobilephone"
									class="form-control" placeholder="移动电话" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_email" class="col-sm-2 col-sm-offset-3 control-label">E-mail</label>
							
							<div class="col-sm-4">
								<input id="edit_email" type="text" name="email"
									class="form-control" placeholder="E-mail" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_buileArea" class="col-sm-2 col-sm-offset-3 control-label">建筑面积</label>
							
							<div class="col-sm-4">
								<input id="edit_buileArea" type="text" name="buileArea"
									class="form-control" placeholder="建筑面积" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_userArea" class="col-sm-2 col-sm-offset-3 control-label">使用面积</label>
							
							<div class="col-sm-4">
								<input id="edit_userArea" type="text" name="userArea"
									class="form-control" placeholder="使用面积" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_heatArea8" class="col-sm-2 col-sm-offset-3 control-label">供热面积</label>
							
							<div class="col-sm-4">
								<input id="edit_heatArea" type="text" name="heatArea"
									class="form-control" placeholder="供热面积" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_billway" class="col-sm-2 col-sm-offset-3 control-label">计费方式</label>
							
							<div class="col-sm-4">
								<input id="edit_billway" type="text" name="billway"
									class="form-control" placeholder="计费方式" />
							</div>					
						</div>
			<!-- 				<div class="form-group">						
							<label for="edit_sfjf" class="col-sm-2 col-sm-offset-3 control-label">缴费情况</label>
							
							<div class="col-sm-4">
								<input id="edit_sfjf" type="text" name="sfjf"
									class="form-control" placeholder="缴费情况" />
							</div>					
						</div> -->
					
						<div class="form-group">						
							<label  for="edit_sfjf" class="col-sm-2 col-sm-offset-3 control-label">缴费情况</label>
							
							<div class="col-sm-4">
							<select id="edit_sfjf" name="sfjf"
									class="form-control" placeholder="缴费情况">
							   <option value="是">是</option>
							    <option value="否">否</option>
							</select>
						    </div>
							
						</div>
							<div class="form-group">						
							<label for="edit_sfqf" class="col-sm-2 col-sm-offset-3 control-label">欠费情况</label>
							<div class="col-sm-4">
							<select id="edit_sfqf"  name="sfqf"
									class="form-control" placeholder="欠费情况">
							   <option value="是">是</option>
							    <option value="否">否</option>
							</select>					
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_sftr" class="col-sm-2 col-sm-offset-3 control-label">供热状态</label>
							
							<div class="col-sm-4">
							<select id="edit_sftr" name="sftr"
									class="form-control" placeholder="供热状态">
							   <option value="是">是</option>
							    <option value="否">否</option>
							</select>							
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="edit_wcad" class="col-sm-2 col-sm-offset-3 control-label">温度传感器地址</label>
							
							<div class="col-sm-4">
								<input id="edit_wcad" type="text" name="wcad"
									class="form-control" placeholder="温度传感器地址" />
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="edit_azwz" class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							
							<div class="col-sm-4">
								<input id="edit_azwz" type="text" name="azwz"
									class="form-control" placeholder="安装位置" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_ylfq" class="col-sm-2 col-sm-offset-3 control-label">压力分区</label>
							
							<div class="col-sm-4">
								<input id="edit_ylfq" type="text" name="ylfq"
									class="form-control" placeholder="压力分区" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_faultInfor" class="col-sm-2 col-sm-offset-3 control-label">故障信息</label>
							
							<div class="col-sm-4">
								<input id="edit_faultInfor" type="text" name="faultInfor"
									class="form-control" placeholder="故障信息" />
							</div>					
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
								<button type="button" onclick="edit()" class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------修改页面结束 --------------------------------------------------------- -->

	<!---------------------------删除页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="deleteUser">
		<div class="modal-dialog">
			<div class="modal-content">
			<form action="">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">删除用户</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-3"></div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!---------------------------删除页面结束 --------------------------------------------------------- -->



</body>
</html>