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
<!-- Bootstrap -->
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script type="text/javascript">
function Sub(){
	var bz = $('#bz').val();
	var fmId = $('#fmId').val();
	window.location="Bz.action?bz="+bz+"&fmId="+fmId
}
function SubYhlb(){
	var yhlb = $('#yhlb').val();
	var xqName = $('#xqNameId').val();
	var buildNo = $('#buildNoId').val();
	var cellNo = $('#cellNoId').val();
	var houseNo = $('#houseNoId').val();
	var html = "";
	$.ajax({
		url : "SubYhlb.action",
		async : false,
		dataType : "json",
		data : {
			"xqName" : xqName,
			"buildNo" : buildNo,
			"cellNo" : cellNo,
			"houseNo" : houseNo,
			"yhlb":yhlb,
		},
		success : function(data) {
			$("#SearchId").empty();
			var d = data.SucYhlb;
			if(d!="失败" && d!=undefined){
			html+='小区名称:';
			html+=d.xqName;
			html+='、楼栋号:';
			html+=d.buildNo;
			html+='、单元号:';
			html+=d.cellNo;
			html+='、户号:';
			html+=d.houseNo;
			html+='、用户类别:';
			html+=d.yhfl;
			$("#SearchId").append(html);
			}else{
				if(d==undefined){
					d="失败";
				}
				html+=d;
				$("#SearchId").append(html);	
			}
		}
	})
}
</script>
<script type="text/javascript">
function UpYhBz(){
	var bz = $('#bz').val();
	var xqName = $('#xqNameId').val();
	var buildNo = $('#buildNoId').val();
	var cellNo = $('#cellNoId').val();
	var houseNo = $('#houseNoId').val();
	var html = "";
	$.ajax({
		url : "UpYhBz.action",
		async : false,
		dataType : "json",
		data : {
			"xqName" : xqName,
			"buildNo" : buildNo,
			"cellNo" : cellNo,
			"houseNo" : houseNo,
			"bz":bz,
		},
		success : function(data) {
			$("#SearchId").empty();
			var d = data.SucYhlb;
			if(d!="失败" && d!=undefined){
			html+='小区名称:';
			html+=d.xqName;
			html+='、楼栋号:';
			html+=d.buildNo;
			html+='、单元号:';
			html+=d.cellNo;
			html+='、户号:';
			html+=d.houseNo;
			html+='、用户备注:';
			html+=d.bz;
			$("#SearchId").append(html);
			}else{
				if(d==undefined){
					d="失败";
				}
				html+=d;
				$("#SearchId").append(html);	
			}
		}
	})
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

        $(function () {
            $("#list").hide();
            $("#menu").hover(function () {
                $("#list").show();
            }, function () {
                $("#list").hide();
            })
            // 鼠标移动到list的div上的时候list div不会被隐藏
            $("#list").hover(function () {
                $("#list").show();
            }, function () {
                $("#list").hide();
            })
        });
        $(function () {
            $("#ts").hide();
            $("#dj").hover(function () {
                $("#ts").show();
            }, function () {
                $("#ts").hide();
            })
            // 鼠标移动到list的div上的时候list div不会被隐藏
            $("#ts").hover(function () {
                $("#ts").show();
            }, function () {
                $("#ts").hide();
            })
        });
        
    </script>
    <script type="text/javascript">
    
    function obr(){
    	var xqName = $('#xqNameId').val();
    	var buildNo = $('#buildNoId').val();
    	var cellNo = $('#cellNoId').val();
    	var houseNo = $('#houseNoId').val();
    	var html = "";
    	$.ajax({
    		url : "ObrSel.action",
    		async : false,
    		dataType : "json",
    		data : {
    			"xqName" : xqName,
    			"buildNo" : buildNo,
    			"cellNo" : cellNo,
    			"houseNo" : houseNo,
    		},
    		success : function(data) {
    			$("#SearchId").empty();
    			document.getElementById("bz").value="";
    			var d = data.ObrSel;
    			if(d!="失败" && d!=undefined){
    			document.getElementById("bz").value=d.bz;;
    			}else{
    					d="";
    					document.getElementById("bz").value=d;
    			}
    		}
    	})
    }
    </script>
</head>
<body style="padding-left:10px;">
<div class="panel panel-success">
  <div class="panel-heading">备注信息,添加用户类别</div>
  <div class="panel-body">
  <form action="Bz.action">
  	
  	
  	
  	<label for="xqNameId">选择小区</label> <select id="xqNameId"
				name="xqName">
				<c:if test="${!empty yhInfoList }">
					<option value="">--选择小区名称--</option>
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option value="${yhInfolist.xqName}">${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
			</select> 
			&nbsp;&nbsp;&nbsp; <label for="buildNoId">楼栋号</label> 
			<select name="buildNo" id="buildNoId">
				<option value=0>--选择楼栋号--</option>
			</select> &nbsp;&nbsp;&nbsp; <label for="cellNoId">单元号</label> <select
				name="cellNo" id="cellNoId">
				<option value=0>--选择单元号--</option>
			</select> 
			户号：<input type="text" name="houseNo" id="houseNoId" value="${houseNo}" onblur="obr()" /> 	
  	</br>
  	用户类别:
  			<select id="yhlb" name="yhlb">
			<option value="">--选择用户类别--</option>
			<option value="普通用户">普通用户</option>
			<option value="重点监控">重点监控</option>
			<option value="特殊情况">特殊情况</option>
			<option value="退费停暖">退费停暖</option>
			</select>
			<input type="button" onclick="SubYhlb()" value="提交" class="btn btn-success" />	
			<span id="menu"><font color="red">*操作提示</font></span>
      		 <span id="list" >
              <font color="red">:选择小区、楼栋、单元、户号、用户类别可以更新用户的类别</font> 
       		</span>
       			</br>
  	备注内容：
  	<input type="text" id="bz" size=60px/>
  	<!-- 阀门地址：<input type="text" id="fmId"> -->
  	<button class="btn btn-success" type="button" onclick="UpYhBz()">提交</button>
  	<span id="dj"><font color="red">*操作提示</font></span>
      		 <span id="ts" >
              <font color="red">:选择小区、楼栋、单元、户号、备注内容 可以更新用户的备注</font> 
       		</span>
  	<div>
  	<textarea rows="5" cols="90" id="SearchId">
  	
  	
  	</textarea>
  	</div>
  </form>
  </div>
</div>
</body>
</html>