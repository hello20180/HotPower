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
		input{
		text-align:center
		}
	</style>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 510,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 120, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
  <link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 //搜索
function searchInfo(){
    var   gs=$('#gs').val();
    var   hs=$('#hs').val();
    var   wc=$('#wc').val();
    var html ="";
	$.ajax({ 
		        url:"SearebInfo.action",
				async : false,
				dataType : "json",
				data : {
					"gs" : gs,
					"hs" : hs,
					"wc" : wc,
				},
				success : function(data) {
					$("#RbInfo").empty();
					var d=data.findList;
					for(var i=0;i<d.length;i++){
						var yhName=d[i].yhName;
						var xqName=d[i].xqName;
						var buildNo=d[i].buildNO;
						var cellNo=d[i].cellNO;
						var houseNo =d[i].houseNO ;
						var rbAd=d[i].rb.rbAd;
						var fmId=d[i].fm.valAd;
						var energy=d[i].rb.energy;	
						var power=d[i].rb.power;
						var flow=d[i].rb.flow;
						var inTmp=d[i].rb.inTmp;
						var outTmp=d[i].rb.outTmp;	
						var diffTmp=d[i].rb.diffTmp;
						var operTime=d[i].rb.operTime ;
						var rbType=d[i].rb.rbType ;
						var qgId=d[i].fm.qgID;
						var bz=d[i].bz;
						var energyLj=d[i].rb.energyLj;
						var readMTime=FormatDate(d[i].rb.readMTime) ;
						var recordTime=FormatDate(d[i].rb.recordTime) ;
						html+="<tr>";
						html+="<td class='text-center'><input type='checkbox'  value='"+rbAd+"'/></td>";
						html+="<td class='text-center' title='"+bz+"'>"+yhName+"</td>";
						html+="<td class='text-center'>"+xqName+"</td>";
						html+="<td class='text-center'>"+buildNo+"</td>";
						html+="<td class='text-center'>"+cellNo+"</td>";
						html+="<td class='text-center'>"+houseNo+"</td>";
						html+="<td class='text-center'>"+rbAd+"</td>";
						html+="<td class='text-center'>"+fmId+"</td>";
						html+="<td class='text-center'>"+energy+"</td>";
						html+="<td class='text-center'>"+flow+"</td>";
						html+="<td class='text-center'>"+energyLj+"</td>";
						html+="<td class='text-center'>"+power+"</td>";
						html+="<td class='text-center'>"+inTmp+"</td>";
						html+="<td class='text-center'>"+outTmp+"</td>";
						html+="<td class='text-center'>"+diffTmp+"</td>";
						html+="<td class='text-center' title='"+operTime+"'>"+operTime+"</td>";
						html+="<td class='text-center' title='"+recordTime+"'>"+recordTime+"</td>";
						html+="<td class='text-center' title='"+rbType+"'>"+rbType+"</td>";
						html+="<td class='text-center' title='"+qgId+"'>"+qgId+"</td>";
						html+="<td class='text-center' title='"+readMTime+"'>"+readMTime+"</td>";
						html+="</tr>";
						}
					  html+="</tbody>"
					  html+="</table>";
					  html+="</div>";
					$("#RbInfo").append(html);
				}

			})
		}
//时间转换
function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
</script>
<script type="text/javascript">
	//导出
	function doExportExcel() {
		    var   gs=$('#gs').val();
		    var   hs=$('#hs').val();
		    var   wc=$('#wc').val();
		window.open("RbExportExcel.action?gs=" + gs + "&hs="+hs+ "&wc="+wc);
	}
</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">热表管理</div>
 <div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
	<p>
	供水温度大于:<input type="text"  size="3px" name="gs" id="gs" value="40" />°C
	回水温度大于:<input type="text" size="3px" name="hs" id="hs" value="30"  />°C
	<input name="wc" id="wc" type="checkbox" checked="checked" value="0" /> 温差为负数
	<input class="btn btn-success" type="button" onclick="searchInfo()" value="搜索">
	<input type="button" value="导出"class="btn btn-success" onclick="doExportExcel()" />
    <hr>
    <div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr>
      			<th></th>
				<th>用户姓名</th>
				<th>小区名称</th>
				<th>楼栋号</th>
				<th>单元号</th>
				<th>户号</th>
				<th>热表编号</th>
				<th>阀门地址</th>
				<th>累计热量</th>
				<th>瞬时流量</th>
				<th>累计流量</th>
				<th>热功率</th>
				<th>进水温度</th>
				<th>回水温度</th>
				<th>温差</th>
				<th>工作时间</th>
				<th>更新时间</th>
				<th>热表类型</th>
				<th>区管地址</th>
				<th>实时时间</th>
			
     </tr>
  </thead>

  <tbody id="RbInfo">

		<c:forEach  var="r" items="${findList}" varStatus="status">
	 <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>  >
	              <td><input class='text-center' type="checkbox" value="${r.rb.rbAd}" /></td>
					<td title="${r.bz}">${r.yhName}</td>
					<td>${r.xqName}</td>
					<td>${r.buildNO}</td>
					<td>${r.cellNO}</td>
					<td>${r.houseNO}</td>
					<td>${r.rb.rbAd}</td>
					<td>${r.fm.valAd}</td>
					<td>${r.rb.energy}</td>
					<td>${r.rb.flow}</td>
					<td>${r.rb.energyLj}</td>
					<td>${r.rb.power}</td>
					<td>${r.rb.inTmp}</td>
					<td>${r.rb.outTmp}</td>
					<td>${r.rb.diffTmp}</td>
					<td>${r.rb.operTime}</td>
					<td><fmt:formatDate value="${r.rb.recordTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					<td>${r.rb.rbType}</td>
					<td>${r.fm.qgID}</td>
					<td><fmt:formatDate value="${r.rb.readMTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>
</div>
</div>
</body>
</html>