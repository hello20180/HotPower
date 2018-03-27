<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			height: 600,
			colModal: [
			{ width: 90, align: 'center' },
			{ width: 330, align: 'center' },
			{ width: 330, align: 'center' },
			{ width: 330, align: 'center' },
			{ width: 400, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script type="text/javascript">
//添加时数据不能为空
function add(){
	var rbType=$("#add input[name=rbType]");
	var typeCode=$("#add input[name=typeCode]");
	var company=$("#add input[name=company]");
	
		 if(rbType.val()==null||rbType.val()==""||typeCode.val()==null||typeCode.val()==""||company.val()==null||company.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	 	var rbType=$("#edit input[name=rbType]");
		var typeCode=$("#edit input[name=typeCode]");
		var company=$("#edit input[name=company]");
		
			 if(rbType.val()==null||rbType.val()==""||typeCode.val()==null||typeCode.val()==""||company.val()==null||company.val()==""){
				 sAlert('信息不能为空，请填写完整!');
				return false;
			} 
			
		$("#edit form").submit();
	}
//修改
function openEditUserPage(){
	var ckbs=$("#Rbinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要修改的用户");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一个用户，您选择了"+ckbs.length+"个用户");
		return false;
	}
	var id=ckbs.val();
	var rbType=ckbs.parent().next().text();
	var typeCode=ckbs.parent().next().next().text();
	var company=ckbs.parent().next().next().next().text();
	$("#edit_id").val(id);
	$("#edit_rbType").val(rbType);
	$("#edit_typeCode").val(typeCode);
	$("#edit_company").val(company);
	
	$("#edit").modal({keyboard:false}); 
}
//删除
function openDeletePage(){
		var ckbs=$("#Rbinfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的热表");
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
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个热表吗？<br/>"+"</span></h4>");
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
					window.location.href="findRb.action";
				}
				
			});		
		});
		
		$("#deleteUser").modal({keyboard:false});
		
	}
</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">热表管理</div>
 <div class="panel-body">
<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add">添加</button>
   &nbsp;&nbsp;<button type="button" class="btn btn-success"
				onClick="openEditUserPage()">修改</button>&nbsp;&nbsp;
			<button type="button" class="btn btn-success"
				onClick="openDeletePage()">删除</button>
			<hr />
		<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
						<th></th>
						<th>热表品牌</th>
						<th>厂商代码</th>
						<th>厂商名称</th>
						<th>备注</th>
						
					</tr>
				</thead>
				<tbody id="Rbinfo">
					<c:forEach  var="rb" items="${rbList}" varStatus="status">
								    <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${rb.id }" /></td>
                                     <td>${rb.rbType}</td>
                                      <td>${rb.typeCode}</td>
                                      <td>${rb.company}</td>
                                      <td>无</td>
                                     
					</c:forEach>
				</tbody>
			</table>
      </div>
</div>
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加热表信息</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="add.action" method="post">
						<div class="form-group">						
							<label for="rbType" class="col-sm-2 col-sm-offset-3 control-label">热表品牌</label>
							
							<div class="col-sm-4">
								<input id="rbType" type="text" name="rbType"
									class="form-control" placeholder="用户姓名" />
							</div>					
						</div>
						
						
						<div class="form-group">						
							<label for="typeCode" class="col-sm-2 col-sm-offset-3 control-label">厂商代码</label>
							
							<div class="col-sm-4">
								<input id="typeCode" type="text" name="typeCode"
									class="form-control" placeholder="小区名称" />
							</div>					
						</div>
						
						<div class="form-group">
							<label for="company" class="col-sm-2 col-sm-offset-3 control-label">厂商名称</label>
							<div class="col-sm-4">
								<input id="company" type="text" name="company"
									class="form-control" placeholder="楼栋号" />
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
					<h4 class="text-center" style="padding: 0; margin: 0;">修改信息</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="update.action" method="post">
					
					 <input type="hidden" id="edit_id" name="id" />
						
						<div class="form-group">						
							<label for="edit_rbType" class="col-sm-2 col-sm-offset-3 control-label">热表类型</label>
							
							<div class="col-sm-4">
								<input id="edit_rbType" type="text" name="rbType"
									class="form-control" placeholder="热表类型" />
							</div>					
						</div>
						
						
						<div class="form-group">						
							<label for="edit_typeCode" class="col-sm-2 col-sm-offset-3 control-label">类型代码</label>
							
							<div class="col-sm-4">
								<input id="edit_typeCode" type="text" name="typeCode"
									class="form-control" placeholder="类型代码" />
							</div>					
						</div>
						
						<div class="form-group">
							<label for="edit_company" class="col-sm-2 col-sm-offset-3 control-label">生产厂家</label>
							<div class="col-sm-4">
								<input id="edit_company" type="text" name="company"
									class="form-control" placeholder="生产厂家" />
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
</div>
</body>
</html>