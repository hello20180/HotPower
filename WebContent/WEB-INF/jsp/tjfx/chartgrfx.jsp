<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.2.js"></script>
<script src="../js/jquery-2.2.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />

<script src="../js/highcharts.js"></script> 
<style type="text/css">
span {
	font-weight:bold;
	color:#ff9955;
	}
	
#top{
padding-top: 10px;
}
	
</style>
<script language="JavaScript">
var chart;
$(function () {

    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },title: {
                text: '供热面积分析图'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            credits: {  
                enabled:false  
            }, 
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '所占比例',
            }]
        });
    });
      //调用查询，加载数据
      chaxun();
});
function chaxun(){

    var arr = [];
    var   xqName = $('#xqNameId').val();
    var   buildNo=$('#buildNoId').val();
    var   cellNo=$('#cellNoId').val();
    var   FqId=$('#FqId').val();
    $.ajax({
        type:'get',
        url:"chartSearch.action?xqName="+xqName+"&buildNo="+buildNo+"&cellNo="+cellNo+"&ylfq="+FqId,//请求数据的地址
        beforeSend:function(XMLHttpRequest){ 
               $('#loading').show();
               $('#contentDIV').hide();
           }, 
         success: function(data){
           chart.series[0].setData(data.data);//数据填充到highcharts上面 
           $("#gr").html(data.gr);
           $("#ngr").html(data.ngr);
           
        },
        error:function(e){
            alert("不好意思，请要访问的图标跑到火星去了。。。。");
        } 
    });
};
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
		$("#xqNameId").change(
				function(){	
				  $.get("houFq.action?xqName="+ $("#xqNameId").val(),function(data) {
					var d=data.houFq;
					$("#FqId option:gt(0)").remove();
					for(var i=0;i<d.length;i++){
						var ylfq=d[i].ylfq;
						var opt="<option value='"+ylfq+"'>"+ylfq+"</option>"
						$("#FqId").append(opt);
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
		
  });
 </script>
</head>

<body>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
<div id="top">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="xqNameId">选择小区</label> 
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
			<option value=0 >--选择楼栋号--</option>
		</select>
	&nbsp;&nbsp;&nbsp;
		<label for="cellNoId">单元号</label> 
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>
		
		<select name="FqId" id="FqId">
			<option value="--选择分区--">--选择分区--</option>
		</select>
		
<input onclick="chaxun()"  class="btn btn-success" type="button" value="搜索"/>
</div>
<hr />
<br />
<div id="container" style="width: 500px; height: 400px; margin: 0 auto"></div>

供热面积：<span id="gr"></span>
未供热面积：<span id="ngr"></span>
</div>
</body>
</html>