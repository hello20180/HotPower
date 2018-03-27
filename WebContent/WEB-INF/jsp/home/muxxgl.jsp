<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>jQuery可展开收缩三级下拉菜单代码 - 站长素材</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0
}

body {
	font-size: 300px;
	font-family: "宋体", "微软雅黑";
}

ul, li {
	list-style: none;
}

a:link, a:visited {
	text-decoration: none;
}

.list {
	width: 265px;
	border-bottom: solid 1px #316a91;
	
}

.list ul li {
	background-color: #15B7A2;
	border: solid 1px #316a91;
	border-bottom: 0;
}

.list ul li a {
	padding-left: 35px;
	color: #000000;
	font-size: 15px;
	display: block;
	font-weight: bold;
	height: 45px;
	line-height: 45px;
	position: relative;
}
/* .list ul li .inactive{ background:url(../images/off.png) no-repeat 184px center;}
.list ul li .inactives{background:url(../images/on.png) no-repeat 184px center;}  */
.list ul li ul {
	display: none;
}

.list ul li ul li {
	border-left: 0;
	border-right: 0;
	background-color: #82C9C2;
	border-color: #FFFFFF;
}

.list ul li ul li ul {
	display: none;
}

.list ul li ul li a {
	padding-left: 50px;
}

.last {
	background-color: #15B7A2;
	border-color: #FFFFFF;
}
</style>

<script type="text/javascript" src="<%=basePath %>/js/jquery-2.2.2.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('.inactive').click(function(){
		if($(this).siblings('ul').css('display')=='none'){
			$(this).parent('li').siblings('li').removeClass('inactives');
			$(this).addClass('inactives');
			$(this).siblings('ul').slideDown(100).children('li');
			if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
				$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
				$(this).parents('li').siblings('li').children('ul').slideUp(100);

			}
		}else{
			//控制自身变成+号
			$(this).removeClass('inactives');
			//控制自身菜单下子菜单隐藏
			$(this).siblings('ul').slideUp(100);
			//控制自身子菜单变成+号
			$(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
			//控制自身菜单下子菜单隐藏
			$(this).siblings('ul').children('li').children('ul').slideUp(100);

			//控制同级菜单只保持一个是展开的（-号显示）
			$(this).siblings('ul').children('li').children('a').removeClass('inactives');
		}
	})
});
</script>
</head>

<body>
<!--页面内容区域 -->
<!-- 页面主题 -->
<div class="container-fluid">
    <div class=" col-xs-3 leftMenu">
      <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="list">
					<ul class="yiji">
						<c:forEach items="${menus}" var="menu" varStatus="status">
							<li><a class="inactive"> ${menu.name }</a> <c:if
									test="${menu.childMenus!=null&&menu.childMenus.size()>0}">
									<ul style="display: none">

										<li><c:forEach  items="${menu.childMenus  }" var="item">
												<a href="${item.url }" target="iframer"> ${item.name }</a>
											</c:forEach>
										</li>
									</ul>
								</c:if>
							</li>

						</c:forEach>
					</ul>
		</div>
      </div>
    </div>

  </div>

</body>
</html>