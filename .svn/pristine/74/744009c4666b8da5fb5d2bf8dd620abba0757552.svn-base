<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Village Menu</title>
<link rel="stylesheet" href="../css/dtree/dtree.css" type="text/css">
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/dtree/mydtree.js"></script>
</head>
<body>
	<div class="dtree">
	<p>${msg}</p>
		<p>
			<a href="javascript: tree.openAll();">菜单打开</a> | <a
				href="javascript: tree.closeAll();">菜单关闭</a> | <a
				href="javascript: tree.openTo(11, true);">默认打开</a>
		</p>
		<script type="text/javascript">
			var tree = new dTree('tree');
			$.ajax({
				url : 'treeMenu.action',
				type : 'post',
				dataType : 'xml', 
				error : function(json) {
					alert("not lived!");
				},
				async : false,
				success : function(xml) {
					$(xml).find("node").each(
							function() {
								var nodeId = $(this).attr("nodeId");
								var parentId = $(this).attr("parentId");
								var hrefAddress = $(this).attr("hrefAddress");
								var nodeName = $(this).text();
								tree.add(nodeId, parentId, nodeName,
										hrefAddress, "", "", "", "", false);
							});
				}
			});
			document.write(tree);
		</script>
	</div>
	
</body>
</html>