<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="../css/fmhistorypage.css" />
<link rel="stylesheet" type="text/css"
	href="../my-iconfont/1.0.8/iconfont.css" />
<style type="text/css">
.charts_grid {
	width: 90%;
	height: 90%;
	border: 1px solid #CCCCCC;
	margin: 20px;
}
</style>
<script src="../js/selectmenu.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.2.js"></script>
<script src="../js/jquery-2.2.2.min.js"></script>
<script src="../echarts-3.5.3/echarts.min.js"></script>
<script type="text/javascript">
	function getSelectText() {
		var xqName = $("#xqNameId").val();
		var buildNO = $("#buildNoId").val();
		var cellNO = $("#cellNoId").val();
		var houseNO = $("#houseNoId").val();
		location.href = "goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNO+"&cellNO="+cellNO+"&houseNO="+houseNO;
	}

</script>

<script type="text/javascript"> 
	/*页面加载就开始执行js*/
	$(document).ready(function() {
		
		$("#xqNameId").change(
		function(){	
		  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val(),function(data) {
			var dd=data;
			var d=dd.xqlist;
			$("#buildNoId option:gt(0)").remove();
			$("#cellNoId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var buildNo=d[i].buildNo;
				
				var opt="<option value='"+buildNo+"'>"+buildNo+"</option>"
				$("#buildNoId").append(opt);
			}
			});
		});
		
		$("#buildNoId").change(
			function() {
				$.get("findYhCellNOByBuild.action?build="+ $("#buildNoId").val()+"&xqName="+ $("#xqNameId").val(),function(data) {
					var dd=data;
					var d=dd.cellList;
					$("#cellNoId option:gt(0)").remove();
					for(var i=0;i<d.length;i++){
						var cellNo=d[i].cellNo;
						var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
						$("#cellNoId").append(opt);
					}
					});
				 });
		
		$("#cellNoId").change(		
				function() {
					$.get("findYhHouseNOByBuild.action?house="+ $("#cellNoId").val()+"&build="+ $("#buildNoId").val()+"&xqName="+ $("#xqNameId").val(),function(data) {
						var dd=data;
						var d=dd.houList;
						$("#houseNoId option:gt(0)").remove();
						for(var i=0;i<d.length;i++){
							var houseNo=d[i].houseNo;
							var opt="<option value='"+houseNo+"'>"+houseNo+"</option>"
							$("#houseNoId").append(opt);
						}
						});
					 });
		
  });
 </script>
</head>
<body>
<div>
	<label for="xqNameId">选择小区</label> 
		<select id="xqNameId" name="xqName">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择小区名称--</option>
				<c:forEach items="${yhInfoList}" var="yhList">
					<option>${yhList.xqName}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;

		<label for="buildNoId">楼栋号</label>
		 <select name="buildNo" id="buildNoId">
			<option value=0>--选择楼栋号--</option>
		</select>
	&nbsp;&nbsp;&nbsp;
		<label for="cellNoId">单元号</label> 
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>
		<label for="houseNoId">户号</label> 
		<select name="houseNo" id="houseNoId">
			<option value=0>--选择户号--</option>
		</select>
<button type="button"  onclick="getSelectText();" class="btn btn-success radius"> 
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
			
			</div>
<br>
<br>


<div id="yhfmhistory" class="charts_grid" style="width: 1200px; height: 550px; margin: 0 auto"></div>
</body>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../echarts-3.5.3/echarts.min.js"></script>
<script type="text/javascript">
window.onresize = function(){
myChart.resize();
}
</script>
<script type="text/javascript">
var date = new Date();
var time = date.toLocaleDateString();
var myChart = echarts.init(document.getElementById('yhfmhistory'));
var option = {
title: {
    text: '温控阀记录',   
    subtext: time
},
tooltip: {
    trigger: 'axis'
},
legend: {
    data:[] 
},
toolbox: {
    show: true,
    feature: {
        magicType: {type: ['line', 'bar']},
        saveAsImage: {}
    }
},
dataZoom: [
    {   type: 'slider', 
        start: 50,      
        end: 100       
    },
    {    type: 'inside',
        start: 50,  
        end: 100  
    }
],
xAxis:  {
    type: 'category',
    boundaryGap: false,
    data: []
},
yAxis: {
    type: 'value',
    axisLabel: {
        formatter: '{value} '
    },
    splitNumber:10
},
series: []
};
myChart.setOption(option);
myChart.showLoading();
var seriess=new Array();
var xAxiss=new Array();
var legends=new Array();
$.ajax({
	type:'GET',
	async: true,
	url:'historyLine.action',
	data:{fmValAd:"${fmValAd}"},
	dataType:'json',
	success:function(result) {
		console.log(result.xy);
	xAxiss=result.xy;
	 $.each(result.data, function(index, comment){
	   legends[index]=comment.name;
	        seriess.push({
            name: comment.name,
            type : 'line',
            data: comment.data,
            markPoint: {
            data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
            ]
        },
            markLine: {
            data: [
                {type: 'average', name: '平均值'}
            ]
        }
           });
      });
      myChart.hideLoading();
	  myChart.setOption({
		    legend:{
		        data : legends
		    },
			xAxis : {
				data : xAxiss
			},
			series : seriess
		});
    },
	error : function(errorMsg) {
        myChart.hideLoading();
		alert("图表请求数据失败!");
	}
	});
</script>

</html>