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
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-2.2.2.min.js"></script>
<script src="../js/highcharts.js"></script>
<script src="../js/echarts.min.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
#top{
padding-top: 10px;
}
</style>
	<script type="text/javascript">
		function SearTime(){
			var html ="";
			var xqName = $('#xqId').val();
			var TimeListId = $('#TimeListId').val();
			var TimeListSId = $('#TimeListSId').val();
			$.ajax({
				url : "findNhz.action",
				async : false,
				dataType : "json",
				data : {
					"xqName" : xqName,
					"TimeListId":TimeListId,
					"TimeListSId" : TimeListSId
				},
				success : function(data) {
					var Nhst = data.Nhst;
					var time = data.time;
					var xqName = data.Xq;
					var times = data.times;
					$("#NhCz").empty();
					html += "<table align='center'  style='height: 30px; width: 50%; border:0px;'>";
					html += "<tr>"
					html += "<th >小区名字"
					html += "</th>"
					html += "<th> 能差值耗值"
					html += "</th>"
					html += "<th> 时间"
					html += "</th>"
					html += "<th> 时间"
					html += "</th>"
					html += "</tr>"
					html += "<tr>";
					html += "<td>" + xqName + "</td>";
					html += "<td>" + Nhst + "</td>";
					html += "<td>" + time+"</td>";
					html += "<td>" + times+"</td>";
					html += "</tr>";
				    html += "</tbody>"
					html += "</table>";
				    $("#NhCz").append(html);
				}

			})
		}
	
	</script>
</head>
<body>
<label for="xqNameId">选择小区</label>
	 <select id="xqNameId" name="xqName">
				<c:if test="${!empty yhInfoList }">
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option value="${yhInfolist.xqName}">${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
	</select>
	<input type="button" value="搜索" class="btn btn-success"  onclick="loadTwoLine()"/>
<div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;"></div>
  <div id="top">
	
		<!-- <div id="container"
			style="width: 1000px; height: 500px; margin: 0 auto"></div> -->
		 <div id="tablediv"  >
		
		</div>
		<p id="TwoLineChart" style="width:1000; height:400px;"></p>
		
	<script type="text/javascript">
		function loadTwoLine() {
    var myChart = echarts.init(document.getElementById('TwoLineChart'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: '小区平均温度曲线'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['平均温度', '室外温度']
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        calculable: true,
        xAxis: {
        	name: '时间',
            type: 'category',
            boundaryGap: false, //取消左侧的间距
            data: []
        },
        yAxis: {
            type: 'value',
            splitLine: { show: false },//去除网格线
            name: ''
        },
        series: [{
            name: '平均温度',
            type: 'line',
            symbol: 'emptydiamond',    //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            data: []
        },
        {
            name: '室外温度',
            type: 'line',
            symbol: 'emptydiamond',    //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            data: []
        }]
    });
    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var names = [];    //类别数组（实际用来盛放X轴坐标值）    
    var series1 = [];
    var series2 = [];
    var xqName = $('#xqNameId').val();
    $.ajax({
        type: 'get',
        url:"XqNameAvg.action?xqName=" + xqName,//请求数据的地址//请求数据的地址
        dataType: "json",        //返回数据形式为json
        success: function (data) {
        	var json = eval('(' + data + ')');
			   for(var i=0;i<json.length;i++){
				   names.push(json[i].date);
				   series1.push(json[i].avg);
				   series2.push(json[i].tqyb);
			   }
            myChart.hideLoading();    //隐藏加载动画
            myChart.setOption({        //加载数据图表
                xAxis: {
                    data: names
                },
                series: [
                {
                    data: series1
                },{
                    data: series2
                }]
            });
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    });
};
loadTwoLine();
</script>
</div>	
</body>
</html>