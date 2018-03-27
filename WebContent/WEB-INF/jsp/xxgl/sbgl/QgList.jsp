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
			height: 500,
			colModal: [
				{ width:50, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 110, align: 'center' },
				{ width: 110, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 130, align: 'center' },
				{ width: 260, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
</head>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">区域管理</div>
		
		<div class="panel-body">
		<div id="top">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success" data-toggle="modal"
				data-target="#add">添加</button>&nbsp;&nbsp;
			<button type="button" class="btn btn-success"
				onClick="openEditUserPage()">修改</button>&nbsp;&nbsp;
			<button type="button" class="btn btn-success"
				onClick="openDeletePage()">删除</button>
				</div>
				<hr />
		<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
						<th>序号</th>
            <th>区管ID</th>
            <th>所属集中器</th>
            <th>小区名称</th>
            <th>安装位置</th>
            <th>区管状态</th>
            <th>区管版本</th>
            <th>模式</th>
            <th>更新周期</th>
            <th>阀门起始地址</th>
            <th>阀门结束地址</th>
            <th>更新时间</th>
					</tr>
				</thead>
				<tbody class="text-center" id="qginfo"> 
					<c:forEach var="qg" items="${qg}" varStatus="status">
						<tr class="info" <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
							<td><input type="checkbox" value="${qg.id }" /></td>
							<td>${qg.qgID}</td>
						<td>${qg.jzqID}</td>
						<td>${qg.xqName }</td>
						<td>${qg.installAd}</td>
						<c:if test="${qg.qgStatus==0}">
							<td>离线</td>
						</c:if>
						<c:if test="${qg.qgStatus==1}">
							<td>在线</td>
						</c:if>
						<td>${qg.version }</td>
						<td>${qg.mode}</td>
						<td>${qg.readCycle}</td>
						<td>${qg.vALstID }</td>
						<td>${qg.vALedID}</td>
						<td><fmt:formatDate value="${qg.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
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
					<h4 class="text-center" style="padding: 0; margin: 0;">添加</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="Insert.action"
						method="post">
						<div class="form-group">
							<label for="add_qgID"
								class="col-sm-2 col-sm-offset-3 control-label">区管ID</label>
							<div class="col-sm-4">
								<input id="add_qgID" type="text" name="qgID"
									class="form-control" placeholder="区管ID" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_jzqID"
								class="col-sm-2 col-sm-offset-3 control-label">所属集中器</label>
							<div class="col-sm-4">
								<input id="add_jzqID" type="text" name="jzqID"
									class="form-control" placeholder="所属集中器" />
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
							<label for="add_installAd"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="add_installAd" type="text" name="installAd"
									class="form-control" placeholder="安装位置" />
							</div>
						</div>

						<div class="form-group">
							<label for="add_version"
								class="col-sm-2 col-sm-offset-3 control-label">区管版本</label>
							<div class="col-sm-4">
								<input id="add_version" type="text" name="version"
									class="form-control" placeholder="区管版本" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_mode"
								class="col-sm-2 col-sm-offset-3 control-label">模式</label>
							<div class="col-sm-4">
								<input id="add_mode" type="text" name="mode"
									class="form-control" placeholder="模式" />
							</div>
						</div>

						<div class="form-group">
							<label for="add_readCycle"
								class="col-sm-2 col-sm-offset-3 control-label">更新周期</label>
							<div class="col-sm-4">
								<input id="add_readCycle" type="text" name="readCycle"
									class="form-control" placeholder="更新周期" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_vALstID"
								class="col-sm-2 col-sm-offset-3 control-label">阀门起始地址</label>
							<div class="col-sm-4">
								<input id="add_vALstID" type="text" name="vALstID"
									class="form-control" placeholder="阀门起始地址" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_vALedID"
								class="col-sm-2 col-sm-offset-3 control-label">阀门结束地址</label>
							<div class="col-sm-4">
								<input id="add_vALedID" type="text" name="vALedID"
									class="form-control" placeholder="阀门结束地址" />
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
					<form class="form form-horizontal" action="update.action"
						method="post">
						<input type="hidden" id="edit_id" name="id" />
						<div class="form-group">
							<label for="edit_qgID"
								class="col-sm-2 col-sm-offset-3 control-label">区管ID</label>
							<div class="col-sm-4">
								<input id="edit_qgID" type="text" name="qgID"
									class="form-control" placeholder="区管ID" readonly="readonly" />


							</div>
						</div>
						<div class="form-group">
							<label for="edit_jzqID"
								class="col-sm-2 col-sm-offset-3 control-label">所属集中器</label>
							<div class="col-sm-4">
								<input id="edit_jzqID" type="text" name="jzqID"
									class="form-control" placeholder="所属集中器" />

							</div>
						</div>
						<div class="form-group">
							<label for="edit_xqName"
								class="col-sm-2 col-sm-offset-3 control-label">小区名称</label>
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名称" readonly="readonly"/>
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
							<label for="edit_qgStatus"
								class="col-sm-2 col-sm-offset-3 control-label">区管状态</label>
							<div class="col-sm-4">
								<select id="edit_qgStatus" name="qgStatus" class="form-control"
									placeholder="区管状态">
									
									<option value=0>离线</option>
									<option value=1>在线</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit_version"
								class="col-sm-2 col-sm-offset-3 control-label">区管版本</label>
							<div class="col-sm-4">
								<input id="edit_version" type="text" name="version"
									class="form-control" placeholder="区管版本" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mode"
								class="col-sm-2 col-sm-offset-3 control-label">模式</label>
							<div class="col-sm-4">
								<input id="edit_mode" type="text" name="mode"
									class="form-control" placeholder="模式" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_readCycle"
								class="col-sm-2 col-sm-offset-3 control-label">更新周期</label>
							<div class="col-sm-4">
								<input id="edit_readCycle" type="text" name="readCycle"
									class="form-control" placeholder="更新周期" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_vALstID"
								class="col-sm-2 col-sm-offset-3 control-label">阀门起始地址</label>
							<div class="col-sm-4">
								<input id="edit_vALstID" type="text" name="vALstID"
									class="form-control" placeholder="阀门起始地址" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_vALedID"
								class="col-sm-2 col-sm-offset-3 control-label">阀门结束地址</label>
							<div class="col-sm-4">
								<input id="edit_vALedID" type="text" name="vALedID"
									class="form-control" placeholder="阀门结束地址" />
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
<script src="../js/main.js"></script>
<script type="text/javascript">
//添加时数据不能为空
function add(){
	var qgID=$("#add input[name=qgID]");
	var jzqID=$("#add input[name=jzqID]");
	var xqName=$("#add input[name=xqName]");
	var installAd=$("#add input[name=installAd]");
	var version=$("#add input[name=version]");
	var mode=$("#add input[name=mode]");
	var readCycle=$("#add input[name=readCycle]");
	var vALstID=$("#add input[name=vALstID]");
	var vALedID=$("#add input[name=vALedID]");
		 if(qgID.val()==null||qgID.val()==""||jzqID.val()==null||jzqID.val()==""||xqName.val()==null||xqName.val()==""||installAd.val()==null||installAd.val()==""||version.val()==null||version.val()==""|| mode.val()==null|| mode.val()==""||readCycle.val()==null||readCycle.val()==""||vALstID.val()==null||vALstID.val()==""||vALedID.val()==null||vALedID.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		 var readCycle1=document.getElementById("add_readCycle").value;
		 if(isNaN(readCycle1)){
				 sAlert('更新周期必须是数字！');
				document.getElementById("add_readCycle").value="";
				return;
			} 
		 var vALstID1=document.getElementById("add_vALstID").value;
		 if(isNaN(vALstID1)){
				 sAlert('起始地址必须是数字！');
				document.getElementById("add_vALstID").value="";
				return;
			} 
		 var vALedID1=document.getElementById("add_vALedID").value;
		 if(isNaN(vALedID1)){
				 sAlert('结束地址必须是数字！');
				document.getElementById("add_vALedID").value="";
				return;
			} 
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
		var jzqID=$("#edit input[name=jzqID]");
		var installAd=$("#edit input[name=installAd]");
		var version=$("#edit input[name=version]");
		var mode=$("#edit input[name=mode]");
		var readCycle=$("#edit input[name=readCycle]");
		var vALstID=$("#edit input[name=vALstID]");
		var vALedID=$("#edit input[name=vALedID]");
			 if(jzqID.val()==null||jzqID.val()==""||installAd.val()==null||installAd.val()==""||version.val()==null||version.val()==""|| mode.val()==null|| mode.val()==""||readCycle.val()==null||readCycle.val()==""||vALstID.val()==null||vALstID.val()==""||vALedID.val()==null||vALedID.val()==""){
				 sAlert('信息不能为空，请填写完整!');
				return false;
			} 
			 var readCycle1=document.getElementById("edit_readCycle").value;
			 if(isNaN(readCycle1)){
					 sAlert('更新周期必须是数字！');
					document.getElementById("edit_readCycle").value="";
					return;
				}
			 var vALstID2=document.getElementById("edit_vALstID").value;
			 if(isNaN(vALstID2)){
					 sAlert('起始地址必须是数字！');
					document.getElementById("edit_vALstID").value="";
					return;
				} 
			 var vALedID2=document.getElementById("edit_vALedID").value;
			 if(isNaN(vALedID2)){
					 sAlert('结束地址必须是数字！');
					document.getElementById("edit_vALedID").value="";
					return;
				} 
		$("#edit form").submit();
	} 
//修改
	function openEditUserPage() {
		var ckbs = $("#qginfo input[type=checkbox]:checked");
		if (ckbs.length == 0) {
			sAlert("请选择要修改的信息");
			return false;
		}
		if (ckbs.length > 1) {
			sAlert("对不起一次只能修改一条信息，您选择了" + ckbs.length + "条信息");
			return false;
		}
		var id = ckbs.val();
		var qgID = ckbs.parent().next().next().text();
		var jzqID = ckbs.parent().next().next().next().text();
		var xqName = ckbs.parent().next().next().next().next().text();
		var installAd = ckbs.parent().next().next().next().next().next().text();
		var qgStatus = ckbs.parent().next().next().next().next().next().next().text();
		var version = ckbs.parent().next().next().next().next().next().next().next().text();
		var mode = ckbs.parent().next().next().next().next().next().next().next().next().text();
		var readCycle = ckbs.parent().next().next().next().next().next().next().next().next().next().text();
		var vALstID = ckbs.parent().next().next().next().next().next().next().next().next().next().next().text();
		var vALedID = ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().text();
		$("#edit_id").val(id);
		$("#edit_qgID").val(qgID);
		$("#edit_jzqID").val(jzqID);
		$("#edit_xqName").val(xqName);
		$("#edit_installAd").val(installAd);
		$("#edit_qgStatus").val(qgStatus);
		$("#edit_version").val(version);
		$("#edit_mode").val(mode);
		$("#edit_readCycle").val(readCycle);
		$("#edit_vALstID").val(vALstID);
		$("#edit_vALedID").val(vALedID);

		$("#edit").modal({
			keyboard : false
		});
	}
	
	//删除
	function openDeletePage(){
			var ckbs=$("#qginfo input[type=checkbox]:checked");
			if(ckbs.length==0){
				sAlert("请选择要删除的区管");
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
			$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个区管吗？<br/>"+"</span></h4>");
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
						window.location.href="QYGfindList.action";
					}
				});		
			});
			
			$("#deleteUser").modal({keyboard:false});
		}
</script>
</html>