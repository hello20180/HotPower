<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 500,
			colModal: [
			{ width: 70, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 250, align: 'center' },
			{ width: 300, align: 'center' },
			{ width: 350, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<script src="../js/bootstrap.js"></script>
<script src="../js/bootstrap-table.js"></script>
<style type="text/css">
#top{
  padding-top: 10px;
}
</style>
<script type="text/javascript">
//添加时数据不能为空
function add(){
	var iReadId=$("#add input[name=iReadId]");
	var jzqId=$("#add input[name=jzqId]");
	var xqName=$("#add input[name=xqName]");
	var installAd=$("#add input[name=installAd]");
	
		 if(iReadId.val()==null||iReadId.val()==""||jzqId.val()==null||jzqId.val()==""||xqName.val()==null||xqName.val()==""||installAd.val()==null||installAd.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	 var iReadId=$("#edit input[name=iReadId]");
		var jzqId=$("#edit input[name=jzqId]");
		var xqName=$("#edit input[name=xqName]");
		var installAd=$("#edit input[name=installAd]");
		
			 if(iReadId.val()==null||iReadId.val()==""||jzqId.val()==null||jzqId.val()==""||xqName.val()==null||xqName.val()==""||installAd.val()==null||installAd.val()==""){
				 sAlert('信息不能为空，请填写完整!');
				return false;
			} 
		$("#edit form").submit();
	} 
	//修改
	function openEditUserPage() {
		var ckbs = $("#readinfo input[type=checkbox]:checked");
		if (ckbs.length == 0) {
			sAlert("请选择要修改的信息");
			return false;
		}
		if (ckbs.length > 1) {
			sAlert("对不起一次只能修改一条信息，您选择了" + ckbs.length + "条信息");
			return false;
		}
		var id = ckbs.val();
		var iReadId = ckbs.parent().next().text();
		var jzqId = ckbs.parent().next().next().text();
		var status = ckbs.parent().next().next().next().text();
		var xqName = ckbs.parent().next().next().next().next().text();
		var installAd = ckbs.parent().next().next().next().next().next().text();

		$("#edit_id").val(id);
		$("#edit_iReadId").val(iReadId);
		$("#edit_jzqId").val(jzqId);
		$("#edit_status").val(status);
		$("#edit_xqName").val(xqName);
		$("#edit_installAd").val(installAd);
		$("#edit").modal({
			keyboard : false
		});
	}
	
	//删除
	function openDeletePage(){
			var ckbs=$("#readinfo input[type=checkbox]:checked");
			if(ckbs.length==0){
				sAlert("请选择要删除的读卡器");
				return false;
			}
			var id=[];
			$.each(ckbs,function(index,data){
				id[index]=$(data).val();
			});
			var names=ckbs.parent().next();
			var deleteUserNames=[];
			$("#deleteUser .modal-body .col-sm-7").empty();
			$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个读卡器吗？<br/>"+"</span></h4>");
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
						window.location.href="findList.action";
					}
					
				});		
			});
			
			$("#deleteUser").modal({keyboard:false});
			
		}
</script>
</head>

<body>
	<form action="">

		<div class="panel panel-success">
			<div class="panel-heading">读卡器</div>
			<div style="width: 99%; height: 100%; position: absolute; overflow:auto;">
			<div id="top">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success"
					data-toggle="modal" data-target="#add">添加</button>

				&nbsp;&nbsp;
				<button type="button" class="btn btn-success"
					onClick="openEditUserPage()">修改</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-success" onClick="openDeletePage()">删除</button>
				&nbsp;&nbsp;
             </div>
				<hr />

				<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
								<th></th>
								<th>刷卡器ID</th>
								<th>所属集中器ID</th>
								<th>通讯状态</th>
								<th>所属小区名称</th>
								<th>安装位置</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody id="readinfo">
							<c:forEach items="${readInfo}" var="readInfo" varStatus="status">

								<tr
									<c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox"
										value="${readInfo.id }" /></td>
									<td>${readInfo.iReadId}</td>
									<td>${readInfo.jzqId}</td>
									<td>${readInfo.status}</td>
									<td>${readInfo.xqName}</td>
									<td>${readInfo.installAd}</td>
									<td>无</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</form>
	<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="Insert.action" method="post">
						<div class="form-group">						
							<label for="iReadId" class="col-sm-2 col-sm-offset-3 control-label">刷卡器ID:</label>
							
							<div class="col-sm-4">
								<input id="iReadId" type="text" name="iReadId" class="form-control" placeholder="刷卡器ID" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="jzqId" class="col-sm-2 col-sm-offset-3 control-label">所属集中器ID：</label>
						<div class="col-sm-4"><input id="jzqId"  type="text" name="jzqId"
									class="form-control" placeholder="所属集中器ID" ></div>
						</div>
						<div class="form-group">						
							<label for="status" class="col-sm-2 col-sm-offset-3 control-label">通讯状态：</label>
							<div class="col-sm-4">
							<select name="status" class="form-control" placeholder="通讯状态">
							   <option value="在线">在线</option>
							    <option value="离线">离线</option>
							</select>
						    </div>
						</div>
						<div class="form-group">						
							<label for="xqName" class="col-sm-2 col-sm-offset-3 control-label">所属小区名称：</label>
						<div class="col-sm-4"><input id="xqName"  type="text" name="xqName"
									class="form-control" placeholder="所属小区名称" ></div>
						</div>
						<div class="form-group">						
							<label for="installAd" class="col-sm-2 col-sm-offset-3 control-label">安装位置：</label>
						<div class="col-sm-4"><input id="installAd" type="text" name="installAd" class="form-control" placeholder="安装位置" ></div>
						
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
					<form class="form form-horizontal" action="update.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
							<div class="form-group">						
							<label for="edit_iReadId" class="col-sm-2 col-sm-offset-3 control-label">刷卡器ID:</label>
							
							<div class="col-sm-4">
								<input id="edit_iReadId" type="text" name="iReadId" class="form-control" placeholder="刷卡器ID" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_jzqId" class="col-sm-2 col-sm-offset-3 control-label">所属集中器ID:</label>
							
							<div class="col-sm-4">
								<input id="edit_jzqId" type="text" name="jzqId" class="form-control" placeholder="所属集中器ID" />
							</div>					
						</div>
					
						
							<div class="form-group">						
							<label for="edit_status" class="col-sm-2 col-sm-offset-3 control-label">通讯状态：</label>
							<div class="col-sm-4">
							<select name="status" id="edit_status" class="form-control" placeholder="通讯状态">
							   <option value="在线">在线</option>
							    <option value="离线">离线</option>
							</select>
						    </div>
						</div>
						
						<div class="form-group">						
							<label for="edit_xqName" class="col-sm-2 col-sm-offset-3 control-label">所属小区名称:</label>
							
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName"
									class="form-control" placeholder="所属小区名称" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_installAd" class="col-sm-2 col-sm-offset-3 control-label">安装位置:</label>
							
							<div class="col-sm-4">
								<input id="edit_installAd" type="text" name="installAd"
									class="form-control" placeholder="安装位置" />
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
</html>