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
<!-- Bootstrap -->
  <link href="../css/bootstrap.css" rel="stylesheet">
  <script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script type="text/javascript">

//添加菜单
function addMenu(){
	var id=$("#add_id").val();
	var childMenus =$("#add_childMenus").val();
	var url =$("#add_url").val();
	if(childMenus==null||childMenus==""){
		$("#warn_addMenu_childMenus").remove();
		$("#add_childMenus").parent().after("<div id='warn_addMenu_childMenus'  class='col-sm-3 text-left control-label'><div class='small text-left  text-danger'>请输入菜单名称</div></div>");
		return false;
	}
	$("#addMenu form").submit();
}
function editMenu(){
	var name=$("#editMenu input[name=name]");
	var id=$("#editMenu input[name=id]");
	var url=$("#editMenu input[name=url]");
	if(name.val()==null||name.val()==""){
		$("#warn_editMenu_name").remove();
		name.parent().after("<div id='warn_editMenu_name' class='col-sm-3 text-left control-label'><div class='small text-left  text-danger'>子菜单不能为空</div></div>");
		return false;
	}
	
	$("#editMenu form").submit();
	
}
//修改
function openEditMenuPage(){
	var usersckbs=$("#users input[type=checkbox]:checked");
	var ckbs=$("#menu input[type=checkbox]:checked");
	if(usersckbs.length==0&&ckbs.length==0){
		alert("请选择要修改的菜单");
		return false;
	}
	if(ckbs.length>1||usersckbs.length>1){
		alert("对不起一次只能修改一个菜单，您选择了"+ckbs.length+"个菜单");
		return false;
	}
	if(ckbs.length==1&&usersckbs.length==1){
		var id=ckbs.val();
		var name=ckbs.parent().text().trim();
		var menuname=ckbs.parent().parent().parent().parent().parent().parent().text().trim();
		var url=ckbs.next().next().val();
		var pid=ckbs.parent().parent().parent().parent().parent().prev().children() .val();
		$("#edit_childMenus").val(name);
		$("#edit_menuname").val(menuname);
		$("#edit_url").val(url);
		$("#edit_id").val(id);
		$("#edit_pid").val(pid);
		$("#editMenu").modal({keyboard:false});
	}else if(ckbs.length==0&&usersckbs.length==1){
		var usersid=usersckbs.val();
		var usersname=usersckbs.parent().text().trim();
		var url=usersckbs.next().val().trim();
		var userpid=usersckbs.next().val().trim();
		$("#edit_childMenus").val(usersname);
		$("#edit_url").val(url);
		$("#edit_id").val(usersid);
		$("#editMenu").modal({keyboard:false});
	}else{
		alert(usersckbs.length);
		alert(ckbs.length);
	}
}

//删除
function openDeleteMenuPage(){
		var ckbs=$("#users input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要删除的菜单");
			return false;
		}
		var id=[];
		$.each(ckbs,function(index,data){
			id[index]=$(data).val();
		});
		var names=ckbs.parent().next();
		var deleteUserNames=[];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个菜单吗？<br/>"+"</span></h4>");
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
					window.location.href="Userlist.action";
				}
				
			});		
		});
		
		$("#deleteUser").modal({keyboard:false});
	}


</script>
</head>
<body>
<div class="panel panel-success">
  <div class="panel-heading">菜单管理</div>
  <div class="panel-body">
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addMenu">添加菜单</button>
   	<button type="button" class="btn btn-success" onClick="openEditMenuPage()">修改</button>
    <button type="button" class="btn btn-danger" onClick="openDeleteMenuPage()">删除</button>
    <br/><br/>
    <table class="table table-hover table-bordered table-condensed  ">
      <thead>
      <th class="text-center">主菜单</th>
      <th class="text-center">子菜单</th>
      </thead>
      <tbody class="text-center" id="users">
        <c:forEach items="${menus}" var="menu" varStatus="status">
	  	<c:choose>
	  			<c:when test="${status.index%2==0 }">
	  				  <tr class="info">
		  				<td>
		  				<input type="checkbox" value="${menu.id}" id="${menu.id}"/>
		  				<input type="hidden" value="${menu.url}" />
		  				<label for="${menu.id}">
		  				${menu.name}
		  				</label>
		  				</td>					  <td>
					  		<table >
					  		<tbody class="text-center" id="menu">
					  		<tr align="center">
					 			
	 						<c:if test="${menu.childMenus!=null&&menu.childMenus.size()>0}">
		               		 <c:forEach items="${menu.childMenus}" var="item">
		                	<td>
		                		
		                		<input type="checkbox" value="${item.id}" id="${item.id}"/><label for="${item.id}">${item.name}</label>
		                		<input type="hidden" value="${item.url}"/>
		                	</td>
		                	</c:forEach>
	          				</c:if>
	          	
					  		</tr>
					  		</tbody>
					  	</table>
		  			
	          		</td>
	          
	       </tr>
	  		</c:when>
	  		<c:otherwise>
		   <tr>
		  				<td>
					<input type="checkbox" value="${menu.id}" id="${menu.id}"/>
		  				<input type="hidden" value="${menu.url}" />
		  				<label for="${menu.id}">
		  				${menu.name}
		  				</label>				 
		  				 <td>
					  		<table>
					  	<tbody class="text-center" id="menu">
					  		<tr align="center">
					 			
	 						<c:if test="${menu.childMenus!=null&&menu.childMenus.size()>0}">
		               		 <c:forEach items="${menu.childMenus}" var="item">
		                	<td>
		                		<input type="checkbox" value="${item.id}" id="${item.id}"/><label for="${item.id}">${item.name}</label>
		                		<input type="hidden" value="${item.url}"/>
		                		
		                	</td>
		                </c:forEach>
	          				</c:if>
					  		</tr>
					  		</tbody>
					  	</table>
	          		</td>
	       </tr>
	    	</c:otherwise>
	  	</c:choose>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<!-- ------------------------添加菜单------------------------------ -->
<div class="modal fade" id="addMenu">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			<h4 class="text-center" style="padding:0;margin:0;">添加菜单</h4></div>
			<div class="modal-body bg-info">
				<form class="form form-horizontal" method="post"  action="<%=basePath %>xtgllist/save.action">
					<div class="form-group">
						<label for="add_name" class="col-sm-2 col-sm-offset-3 control-label">父菜单</label>
						<div class="col-sm-4">
							<select id="add_id"  name="id" class="form-control" >
								 <option value="">--请选择主菜单--</option>
								 <c:forEach items="${menus}" var="menu" varStatus="status">
								 	<option value="${menu.id}">${menu.name}</option>
								 </c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="add_childMenus" class="col-sm-2 col-sm-offset-3 control-label">菜单名称</label>
						<div class="col-sm-4">
							<input id="add_childMenus" type="text" name="name" class="form-control" placeholder="childMenus" />
						</div>
					</div>
					<div class="form-group">
						<label for="add_url" class="col-sm-2 col-sm-offset-3 control-label">连接地址</label>
						<div class="col-sm-4">
							<input id="add_url" type="text" name="url" class="form-control" placeholder="url" />
						</div>
					</div>
					
				
					
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5">
							<button type="button" onClick="addMenu()" class="btn btn-primary btn-sm">提交</button>
							<button type="reset" class="btn btn-primary btn-sm">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
	<!-- ------------------------修改页面------------------------------ -->
<div class="modal fade" id="editMenu">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary"><h4 class="text-center" style="padding:0;margin:0;">修改菜单</h4></div>
			<div class="modal-body bg-info">
				<form class="form form-horizontal" action="<%=basePath%>/xtgllist/update.action" method="post">
					<div class="form-group">
						<label for="edit_menuname" class="col-sm-2 col-sm-offset-3 control-label">父菜单</label>
						<div class="col-sm-4">
							<input id="edit_menuname" type="text" name="menuname" class="form-control" readonly="readonly"/>
						</div>        
					</div>
					<div class="form-group">
						<div class="col-sm-4">
							<input id="edit_pid" type="hidden" name="pid" class="form-control"/>
							<input id="edit_id" type="hidden" name="id" class="form-control"/>
						</div>        
					</div>
					<div class="form-group">
						<label for="edit_childMenus" class="col-sm-2 col-sm-offset-3 control-label">菜单名称</label>
						<div class="col-sm-4">
							<input id="edit_childMenus" type="text" name="name" class="form-control" placeholder="childMenus" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_url" class="col-sm-2 col-sm-offset-3 control-label">url</label>
						<div class="col-sm-4">
							<input id="edit_url" type="text" name="url" class="form-control" placeholder="url" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5">
							<button type="button" onClick="editMenu()" class="btn btn-primary btn-sm">提交</button>
							<button type="reset" onclick="cls()" class="btn btn-primary btn-sm">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
	
<!---------------------------删除页面开始 --------------------------------------------------------- -->
<div class="modal fade bs-example-modal-sm" id="deleteUser">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary"><h4 class="text-center" style="padding:0;margin:0;">删除用户</h4></div>
			<div class="modal-body bg-info">
				<div class="row">
					<div class="col-sm-7 col-sm-offset-3">
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>