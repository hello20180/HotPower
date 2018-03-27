<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-2.2.2.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-2.2.2.min.js"></script>

<script src="../js/highcharts.js"></script> 
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
		
  });
	
 </script>
 
</head>
<body>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
<form action="">

   <div id="top">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="xqNameId">选择小区</label> 
		<select id="xqNameId" name="xqName">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择小区名称--</option>
				<c:forEach items="${yhInfoList}" var="yhInfolist">
					<option  <c:if test="${ yhInfolist.xqName==xqName }">selected</c:if>>${yhInfolist.xqName}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;

		<label for="buildNoId">楼栋号</label>
		 <select name="buildNo" id="buildNoId">
			<option value=0 <c:if test="${ yhInfolist.buildNo==buildNo }">selected</c:if>>--选择楼栋号--</option>
		</select>
	&nbsp;&nbsp;&nbsp;
		<label for="cellNoId">单元号</label> 
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>


		<label for="fmStatusId">选择阀门状态</label> 
		<select id="fmStatusId" name="status">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择阀门状态--</option>
				<c:forEach items="${fmHistoriesStatus}" var="fmHistoriesStatus">
					<option value="${fmHistoriesStatus.status}">${fmHistoriesStatus.status}</option>
				</c:forEach>
			</c:if>
		</select>

		<label for="JFStatusId">选择缴费状态</label> 
		<select id="JFStatusId" name="sfjf">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择缴费状态--</option>
			     <option selected="selected">是</option>
			     <option>否</option>
			</c:if>
		</select>

<input type="button" value="搜索" class="btn btn-success" onclick="chaxun()"/>
</form>
<hr />
	<br />
		<div >
			<table id="shuju" align="center"  style="height: 30px; width: 70%; border:0px;"  >
		 
			</table> 
		</div>
</div>
<div id="container" style="width: 1000px; height: 400px; margin: 0 auto;"></div>

<script type="text/javascript">
var chart;
 $(function () {
	 
	 $(document).ready(function() {
	 chart = new Highcharts.Chart({
	        chart:{
	            renderTo:'container',
	            type:'scatter',//显示类型 散点图
	            zoomType: 'xy'
	        },
	        title:{
	            text:'室内温度散点图' //图表的标题
	        },
	        xAxis:{
	            title: {
	                enabled: true,
	                text: '用户ID'
	                },
	            startOnTick: true,
	            endOnTick: true,
	            showLastLabel: true,
	       
	        },
	        yAxis:{
	            title:{
	                text:'温度' //Y轴的名称
	            },

	        },
	        legend: {
	            layout: 'vertical',
	            align: 'left',
	            verticalAlign: 'top',
	            x: 100,
	            y: 90,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
	            borderWidth: 1
	        },
	        credits:{
	            enabled:false
	        },
	        plotOptions: {
	        	series: {
	                cursor: 'pointer',
	                events: {
	                    click: function(e) {
	                   var x = event.point.x
	        		ShowMaxSubValues(x);
	                   
	        	    }
	        	},
	            },
                    
	            scatter: {
	                marker: {
	                    radius: 3,
	                    states: {
	                        hover: {
	                            enabled: true,
	                            lineColor: 'rgb(100,100,100)'
	                        }
	                    }
	                },

	                credits: {  
	                    enabled:false  
	                }, 
	                states: {
	                    hover: {
	                        marker: {
	                            enabled: false
	                        }
	                    }
	                },
	                tooltip: {
	                    headerFormat: '<b>{series.name}</b><br>',
	                    pointFormat: '{point.x}, {point.y}',
	                }
	            }
	        },
	        series: [{
	            name: '温度',
	            color: 'rgba(223, 83, 83, .5)',
	        }]
	    });
	   });
	 //调用查询，加载数据
	    chaxun();

    });
 
 
 function ShowMaxSubValues(x) {
	 var html ="";
	 $.ajax({
			url:"findSd.action?id="+x,
			async:false,
			dataType:"json",
			
			success:function(data){
				$("#shuju").empty();
				var xqName=data.xqName;
				var buildNo=data.buildNo;
				var cellNo=data.cellNo;
				var houseNo=data.houseNo;
				var roomTemp=data.roomTemp;
				var valTemp=data.valTemp;
				var famKd=data.famKd;
				var recordTime=data.recordTime;
				var status=data.status;
				var recordTime = FormatDate(data.recordTime);
			    html+="<tr>";
			    html+="<td> 小区名字："+xqName+"</td>";
			    html+="<td> 楼栋号："+buildNo+"</td>";
			    html+="<td> 单元号："+cellNo+"</td>";
			    html+="<td> 户号："+houseNo+"</td>";
			    html+="<td> 室内温度："+roomTemp+"</td>";
			    html+="<td> 管道温度："+valTemp+"</td>";
			    html+="<td> 阀门开度："+famKd+"</td>";
			    html+="<td> 阀门状态："+status+"</td>";
			    html+="<td> 更新时间："+recordTime+"</td>";
			    html+="<td class='text-center'> <button type='button' id='btnsearch' class='btn btn-success' >历史数据</td>";
				html+="</tr>";
				html+="</tr>";
				$("#shuju").append(html);
				
				$("#btnsearch").click(function(){
					window.location.href="/HotPower/fmSdController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo;
				});
			}
			
		})
 }
	function FormatDate(strTime) {
		var date = new Date(strTime);
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate() + " " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds();
	}
 function chaxun(){
	    var arr = [];
	    var xqName = $('#xqNameId').val();
	    var   buildNo=$('#buildNoId').val();
	    var   cellNo=$('#cellNoId').val();
	    var   status=$('#fmStatusId').val();
	    var   sfjf=$('#JFStatusId').val();
	    $.ajax({
	        type:'get',
	        url:"chartSearchSd.action?xqName="+xqName+"&buildNo="+buildNo+"&cellNo="+cellNo+"&status="+status+"&sfjf="+sfjf,//请求数据的地址
	        beforeSend:function(XMLHttpRequest){ 
	               $('#loading').show();
	               $('#contentDIV').hide();
	           }, 
	         success: function(data){
	            $('#contentDIV').show();
	            $('#loading').hide();
	           chart.series[0].setData(data.data);//数据填充到highcharts上面 
	        
	           chart.yAxis[0].addPlotLine({           //在x轴上增加
	        	    value:18,                           //在值为2的地方
	        	    width:2,                           //标示线的宽度为2px
	        	    color: 'green',                  //标示线的颜色
	        	    id: 'plot-line-2'                  //标示线的id，在删除该标示线的时候需要该id标示
	        	});
	           chart.yAxis[0].addPlotLine({           //在x轴上增加
	        	    value:22,                           //在值为2的地方
	        	    width:2,                           //标示线的宽度为2px
	        	    color: 'green',                  //标示线的颜色
	        	    id: 'plot-line-2'                  //标示线的id，在删除该标示线的时候需要该id标示
	        	});
	        },
	        error:function(e){
	            alert("不好意思，请要访问的图标跑到火星去了。。。。");
	        } 
	    });
	};

 
</script>

</div>
</body>
</html>