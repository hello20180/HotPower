<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司公告</title>
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
			{ width: 100, align: 'center' },
			{ width: 700, align: 'center' },
			{ width: 400, align: 'center' },
			{ width: 300, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="../js/main.js"></script>
</head>


<body>
	<div class="panel panel-success">
		<div class="panel-heading">公司公告</div>
		<div class="panel-body" >
		 <button type="button" class="btn btn-success" data-toggle="modal"
				data-target="#add">添加</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-success" onClick="openEditUserPage()">修改</button>
	&nbsp;&nbsp;
	 <button type="button" class="btn btn-success" onClick="openDeletePage()">删除</button>
	&nbsp;&nbsp;
			<hr />
			<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
							<th></th>
							<th>公告标题</th>
							<th>发布时间</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody id="xsInfos">
						<c:forEach items="${list }" var="noti" varStatus="status">
							<tr>
								<td><input type="checkbox" value="${noti.id }" /></td>
								<td><a href="findId.action?id=${noti.id}">${noti.title}</a></td>
								<td><fmt:formatDate value="${noti.creatTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>无</td>
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
					<h4 class="text-center" style="padding: 0; margin: 0;">发布公告</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="InsertNotice.action" method="post">
					
							<div class="form-group">						
							<label for="title" class="col-sm-2 col-sm-offset-3 control-label">标题</label>
						<div class="col-sm-4"><input id="title"  type="text" name="title"
									class="form-control" placeholder="title" ></div>
						</div>

						<div class="form-group">						
						<label for="article" class="col-sm-2 col-sm-offset-3 control-label">公告内容</label>
							<div class="col-sm-4">
							<textarea id="article" style="overflow-x: hidden;width:300px;height:100px;"
							class="form-control" name="article" placeholder="article"></textarea>
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
<!---------------------------修改页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="editxg">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">修改公告内容</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="updateNotice.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									
						
						<div class="form-group">						
							<label for="edit_title" class="col-sm-2 col-sm-offset-3 control-label">标题</label>
							<div class="col-sm-4">
								<input id="edit_title" type="text" name="title"
									class="form-control" placeholder="title" />
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="edit_article" class="col-sm-2 col-sm-offset-3 control-label">公告内容</label>
							<div class="col-sm-4">
								<textarea id="edit_article" name="article" placeholder="article" style="overflow-x: hidden;width:300px;height:100px;" class="form-control"></textarea>
							</div>					
						</div>
						
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
							<button type="button" onClick="edit()"
									class="btn btn-primary btn-sm">提交</button>
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

<script type="text/javascript">
//添加时数据不能为空
function add(){
		var title=$("#add input[name=title]");
		 var article=$("#add textarea[name=article]");
		
		if(title.val()==null||title.val()==""||article.val()==null||article.val()==""){
			 sAlert('信息不能为空，请填写完整!');
				return false;
		}			
		$("#add form").submit();
	}
//修改时数据不允许为空

function edit(){
		var title=$("#editxg input[name=title]");
		 var article=$("#editxg textarea[name=article]");
		if(title.val()==null||title.val()==""||article.val()==null||article.val()==""){
				 sAlert('信息不能为空，请填写完整!');
					return false;
			}	
		
		$("#editxg form").submit();
	}
//修改
function openEditUserPage(){
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
	var title=ckbs.parent().next().text();
	
	$("#edit_id").val(id);
	$("#edit_title").val(title);
	
	
	$("#editxg").modal({
		keyboard : false
	});
}
//删除
function openDeletePage(){
		var ckbs=$("#xsInfos input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的公司公告");
			return false;
		}
		var id=[];
		$.each(ckbs,function(index,data){
			id[index]=$(data).val();
		});
		var names=ckbs.parent().next();
		var deleteUserNames=[];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个公司公告吗？<br/>"+"</span></h4>");
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
					window.location.href="Noticelist.action";
				}
				
			});		
		});	
		$("#deleteUser").modal({keyboard:false});
	}
</script>
	
</html>