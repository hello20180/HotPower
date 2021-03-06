<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
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
			height: 600,
			colModal: [
				{ width: 70, align: 'center' },
				{ width: 150, align: 'center' },
				{ width: 90, align: 'center' },
				{ width: 140, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 150, align: 'center' },
				{ width: 150, align: 'center' },
				{ width: 180, align: 'center' },
				{ width: 180, align: 'center' },
				{ width: 150, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">集中器</div>
 <div class="panel-body">
<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add">添加</button>&nbsp;&nbsp;
   <button type="button" class="btn btn-success"
				onClick="openEditUserPage()">修改</button>&nbsp;&nbsp;
				 <button type="button" class="btn btn-success"
				onClick="openDeletePage()">删除</button>
			<hr />
	         <form >
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
						<th></th>
						<th>集中器ID</th>
						<th>本地端口</th>
						<th>集中器IP</th>
						<th>集中器端口</th>
						<th>状态</th>
						<th>更新时间</th>
						<th>小区名称</th>
						<th>换热站名称</th>
						<th>安装位置</th>
						<th>备注</th>

					</tr>
				</thead>
				<tbody id="jzqinfo">
					<c:forEach  var="jzq" items="${jzq}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td ><input type="checkbox" value="${jzq.id }" /></td>
                                     <td>${jzq.jzqID}</td>
                                     <td>${jzq.socket}</td>
                                      <td>${jzq.jzqIP}</td>
                                     <td>${jzq.jzqPort}</td>
                                     <c:if test="${jzq.status==0}">
										<td>离线</td>
									</c:if>
									<c:if test="${jzq.status==1}">
										<td>在线</td>
									</c:if>
                                     <td><fmt:formatDate value="${jzq.updateTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                      <td>${jzq.xqName}</td>
                                     <td>${jzq.hesName}</td>
                                     <td>${jzq.installAd}</td>
                                     <td>${jzq.remark}</td>
                           
					</c:forEach>
				</tbody>
			</table>
</div>
</form>
</div>
</div>
<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="InsertJzq.action" method="post">
						<div class="form-group">
							<label for="add_jzqID"
								class="col-sm-2 col-sm-offset-3 control-label">集中器ID</label>
							<div class="col-sm-4">
								<input id="add_jzqID" type="text" name="jzqID"
									class="form-control" placeholder="集中器ID" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_socket"
								class="col-sm-2 col-sm-offset-3 control-label">本地端口</label>
							<div class="col-sm-4">
								<input id="add_socket" type="text" name="socket"
									class="form-control" placeholder="本地端口" />
							</div>
						</div>
								<div class="form-group">
							<label for="add_jzqIP"
								class="col-sm-2 col-sm-offset-3 control-label">集中器IP</label>
							<div class="col-sm-4">
								<input id="add_jzqIP" type="text" name="jzqIP"
									class="form-control" placeholder="集中器IP" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_jzqPort"
								class="col-sm-2 col-sm-offset-3 control-label">集中器端口</label>
							<div class="col-sm-4">
								<input id="add_jzqPort" type="text" name="jzqPort"
									class="form-control" placeholder="集中器端口" />
							</div>
						</div>
					
								<div class="form-group">
							<label for="add_xqName"
								class="col-sm-2 col-sm-offset-3 control-label">小区名称</label>
							<div class="col-sm-4">
								<input id="add_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名称" />
							</div>
						</div>
								<div class="form-group">
							<label for="add_hesName"
								class="col-sm-2 col-sm-offset-3 control-label">换热站名称</label>
							<div class="col-sm-4">
								<input id="add_hesName" type="text" name="hesName"
									class="form-control" placeholder="换热站名称" />
							</div>
						</div>
								<div class="form-group">
							<label for="add_installAd"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="add_installAd" type="text" name="installAd"
									class="form-control" placeholder="安装位置" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="add_remark"
								class="col-sm-2 col-sm-offset-3 control-label">备注</label>
							<div class="col-sm-4">
								<input id="add_remark" type="text" name="remark"
									class="form-control" placeholder="备注" />
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
					<h4 class="text-center" style="padding: 0; margin: 0;">修改</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="updateJzq.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									<div class="form-group">
							<label for="edit_jzqID"
								class="col-sm-2 col-sm-offset-3 control-label">集中器ID</label>
							<div class="col-sm-4">
								<input id="edit_jzqID" type="text" name="jzqID"
									class="form-control" readonly="readonly" placeholder="集中器ID" />
									
							</div>
						</div>
						<div class="form-group">
							<label for="edit_socket"
								class="col-sm-2 col-sm-offset-3 control-label">本地端口</label>
							<div class="col-sm-4">
								<input id="edit_socket" type="text" name="socket"
									class="form-control" placeholder="本地端口"/>
									
							</div>
						</div>
								<div class="form-group">
							<label for="edit_jzqIP"
								class="col-sm-2 col-sm-offset-3 control-label">集中器IP</label>
							<div class="col-sm-4">
								<input id="edit_jzqIP" type="text" name="jzqIP"
									class="form-control" placeholder="集中器IP" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_jzqPort"
								class="col-sm-2 col-sm-offset-3 control-label">集中器端口</label>
							<div class="col-sm-4">
								<input id="edit_jzqPort" type="text" name="jzqPort"
									class="form-control" placeholder="集中器端口" />
							</div>
						</div>
							<div class="form-group">
							<label for="edit_status"	class="col-sm-2 col-sm-offset-3 control-label">连接状态</label>

							<div class="col-sm-4">

									<select id="edit_status" name="status" class="form-control" placeholder="连接状态" >
										<option value=0>离线</option>
										<option value=1>在线</option>
									</select>
									
							</div>
						</div>
					
								<div class="form-group">
							<label for="edit_xqName"
								class="col-sm-2 col-sm-offset-3 control-label">小区名称</label>
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名称" />
								
							</div>
						</div>
							<div class="form-group">
							<label for="edit_hesName"
								class="col-sm-2 col-sm-offset-3 control-label">换热站名称</label>
							<div class="col-sm-4">
								<input id="edit_hesName" type="text" name="hesName"
									class="form-control" placeholder="换热站名称" />
								
							</div>
						</div>
								<div class="form-group">
							<label for="edit_installAd"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="edit_installAd" type="text" name="installAd"
									class="form-control" placeholder="安装位置" />
										
									
							</div>
						</div>
							
						<div class="form-group">
							<label for="edit_remark"
								class="col-sm-2 col-sm-offset-3 control-label">备注</label>
							<div class="col-sm-4">
								<input id="edit_remark" type="text" name="remark"
									class="form-control" placeholder="备注" />
								
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



</body>
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript">
//添加时数据不能为空
function add(){
	var jzqID=$("#add input[name=jzqID]");
	var socket=$("#add input[name=socket]");
	var jzqIP=$("#add input[name=jzqIP]");
	var jzqPort=$("#add input[name=jzqPort]");
	var xqName=$("#add input[name=xqName]");
	var hesName=$("#add input[name=hesName]");
	var installAd=$("#add input[name=installAd]");
	
		 if(jzqID.val()==null||jzqID.val()==""||socket.val()==null||socket.val()==""||jzqIP.val()==null||jzqIP.val()==""||jzqPort.val()==null||jzqPort.val()==""||xqName.val()==null|| xqName.val()==""||hesName.val()==null||hesName.val()==""||installAd.val()==null||installAd.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		 var socket1=document.getElementById("add_socket").value;
		 if(isNaN(socket1)){
				
				 sAlert('本地端口号必须是数字！');
				document.getElementById("add_socket").value="";
				return;
			} 
		 var jzqPort1=document.getElementById("add_jzqPort").value;
		 if(isNaN(jzqPort1)){
				
				 sAlert('集中器端口号必须是数字！');
				document.getElementById("add_jzqPort").value="";
				return;
			} 
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	var socket=$("#edit input[name=socket]");
	var jzqIP=$("#edit input[name=jzqIP]");
	var jzqPort=$("#edit input[name=jzqPort]");
	var xqName=$("#edit input[name=xqName]");
	var hesName=$("#edit input[name=hesName]");
	var installAd=$("#edit input[name=installAd]");
		 if(socket.val()==null||socket.val()==""||jzqIP.val()==null||jzqIP.val()==""||jzqPort.val()==null||jzqPort.val()==""||xqName.val()==null|| xqName.val()==""||hesName.val()==null||hesName.val()==""||installAd.val()==null||installAd.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		 var socket2=document.getElementById("edit_socket").value;
		 if(isNaN(socket2)){
				
				 sAlert('本地端口号必须是数字！');
				document.getElementById("edit_socket").value="";
				return;
			} 
		 var jzqPort2=document.getElementById("edit_jzqPort").value;
		 if(isNaN(jzqPort2)){
				
				 sAlert('集中器端口号必须是数字！');
				document.getElementById("edit_jzqPort").value="";
				return;
			} 

		$("#edit form").submit();
	} 

//修改
function openEditUserPage(){
	var ckbs=$("#jzqinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	var jzqID=ckbs.parent().next().text();
	var socket=ckbs.parent().next().next().text();
	var jzqIP=ckbs.parent().next().next().next().text();
	var jzqPort=ckbs.parent().next().next().next().next().text();
	var status=ckbs.parent().next().next().next().next().next().text();
	
	var xqName=ckbs.parent().next().next().next().next().next().next().next().text();
	var hesName=ckbs.parent().next().next().next().next().next().next().next().next().text();
	var installAd=ckbs.parent().next().next().next().next().next().next().next().next().next().text();
	var remark=ckbs.parent().next().next().next().next().next().next().next().next().next().next().text();
	
	$("#edit_id").val(id);
	$("#edit_jzqID").val(jzqID);
	$("#edit_socket").val(socket);
	$("#edit_jzqIP").val(jzqIP);
	$("#edit_jzqPort").val(jzqPort);
	$("#edit_status").val(status);
	$("#edit_xqName").val(xqName);
	$("#edit_hesName").val(hesName);
	$("#edit_installAd").val(installAd);
	$("#edit_remark").val(remark);
	
	$("#edit").modal({keyboard:false}); 
}
 
//删除
function openDeletePage(){
		var ckbs=$("#jzqinfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的集中器");
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
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个集中器吗？<br/>"+"</span></h4>");
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
				url :"deleteJzq.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="JzqfindList.action";
				}
			});		
		});
		$("#deleteUser").modal({keyboard:false});
	}
 
</script>
</html>