<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
			{ width: 90, align: 'center' },
			{ width: 300, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 200, align: 'center' },
			{ width: 350, align: 'center' },
			{ width: 400, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<style type="text/css">
#top{ padding-top: 10px;}
</style>
<script type="text/javascript">
//添加时数据不能为空
function add(){
	var hesName=$("#add input[name=hesName]");
	var lxrName=$("#add input[name=lxrName]");
	var lxrPhone=$("#add input[name=lxrPhone]");
	var hesAd=$("#add input[name=hesAd]");
	
		 if(hesName.val()==null||hesName.val()==""||lxrName.val()==null||lxrName.val()==""||lxrPhone.val()==null||lxrPhone.val()==""||hesAd.val()==null||hesAd.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		 var lxrPhone1=document.getElementById("add_lxrPhone").value;
		 if(isNaN(lxrPhone1)){
				 sAlert('联系电话必须是数字！');
				document.getElementById("add_lxrPhone").value="";
				return;
			} 
		
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	 	var hesName=$("#edit input[name=hesName]");
		var lxrName=$("#edit input[name=lxrName]");
		var lxrPhone=$("#edit input[name=lxrPhone]");
		var hesAd=$("#edit input[name=hesAd]");
		
			 if(hesName.val()==null||hesName.val()==""||lxrName.val()==null||lxrName.val()==""||lxrPhone.val()==null||lxrPhone.val()==""||hesAd.val()==null||hesAd.val()==""){
				 sAlert('信息不能为空，请填写完整!');
				return false;
			} 
			 var lxrPhone2=document.getElementById("edit_lxrPhone").value;
			 if(isNaN(lxrPhone2)){
					 sAlert('联系电话必须是数字！');
					document.getElementById("edit_lxrPhone").value="";
					return;
				} 
		$("#edit form").submit();
	} 
//修改
function openEditUserPage(){
	var ckbs=$("#hesinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	var hesName=ckbs.parent().next().text();
	var lxrName=ckbs.parent().next().next().text();
	var lxrPhone=ckbs.parent().next().next().next().text();
	var hesAd=ckbs.parent().next().next().next().next().text();
	
	$("#edit_id").val(id);
	$("#edit_hesName").val(hesName);
	$("#edit_lxrName").val(lxrName);
	$("#edit_lxrPhone").val(lxrPhone);
	$("#edit_hesAd").val(hesAd);
	
	$("#edit").modal({keyboard:false});
}
//删除
function openDeletePage(){
		var ckbs=$("#hesinfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的换热站");
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
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个换热站吗？<br/>"+"</span></h4>");
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
 <div class="panel panel-success">
 <div class="panel-heading">换热站</div>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
 <div id="top">
<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add">添加</button>
   &nbsp;&nbsp;<button type="button" class="btn btn-success"
				onClick="openEditUserPage()">修改</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-success"
				onClick="openDeletePage()">删除</button>
</div>	
			<hr />
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
						<th></th>
						<th>换热站名</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>换热站地址</th>
						<th>备注</th>
							
					</tr>
				</thead>
				<tbody id="hesinfo">
					<c:forEach  var="hes" items="${hes}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${hes.id }" /></td>
                                     <td>${hes.hesName}</td>
                                     <td>${hes.lxrName}</td>
                                      <td>${hes.lxrPhone}</td>
                                     <td>${hes.hesAd}</td>
                                     <td>无</td>
                                     
					</c:forEach>
				</tbody>
			</table>
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
					<form class="form form-horizontal" action="Insert.action" method="post">
						<div class="form-group">
							<label for="add_hesName"
								class="col-sm-2 col-sm-offset-3 control-label">换热站名称</label>
							<div class="col-sm-4">
								<input id="add_hesName" type="text" name="hesName"
									class="form-control" placeholder="换热站名称" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_lxrName"
								class="col-sm-2 col-sm-offset-3 control-label">联系人</label>
							<div class="col-sm-4">
								<input id="add_lxrName" type="text" name="lxrName"
									class="form-control" placeholder="联系人" />
							</div>
						</div>
								<div class="form-group">
							<label for="add_lxrPhone"
								class="col-sm-2 col-sm-offset-3 control-label">联系电话</label>
							<div class="col-sm-4">
								<input id="add_lxrPhone" type="text" name="lxrPhone"
									class="form-control" placeholder="联系电话" />
							</div>
						</div>
						<div class="form-group">
							<label for="add_nhPrice"
								class="col-sm-2 col-sm-offset-3 control-label">换热站地址</label>
							<div class="col-sm-4">
								<input id="add_hesAd" type="text" name="hesAd"
									class="form-control" placeholder="换热站地址" />
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
					<form class="form form-horizontal" action="update.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									<div class="form-group">
							<label for="edit_hesName"
								class="col-sm-2 col-sm-offset-3 control-label">换热站名</label>
							<div class="col-sm-4">
								<input id="edit_hesName" type="text" name="hesName"
									class="form-control" placeholder="换热站名" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_lxrName"
								class="col-sm-2 col-sm-offset-3 control-label">联系人</label>
							<div class="col-sm-4">
								<input id="edit_lxrName" type="text" name="lxrName"
									class="form-control" placeholder="联系人" />
							</div>
						</div>
								<div class="form-group">
							<label for="edit_lxrPhone"
								class="col-sm-2 col-sm-offset-3 control-label">联系电话</label>
							<div class="col-sm-4">
								<input id="edit_lxrPhone" type="text" name="lxrPhone"
									class="form-control" placeholder="联系电话" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_nhPrice"
								class="col-sm-2 col-sm-offset-3 control-label">换热站地址</label>
							<div class="col-sm-4">
								<input id="edit_hesAd" type="text" name="hesAd"
									class="form-control" placeholder="换热站地址" />
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