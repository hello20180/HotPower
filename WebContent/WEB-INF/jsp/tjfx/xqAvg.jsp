<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	<script src="../js/echarts.min.js"></script>
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
	<script type="text/javascript">
	</script>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 600,
			colModal: [
			{ width: 250, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 200, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function searchInfo() {
	var xqName = $('#xqNameId').val();
	var recordTime1 = $('#recordTime1').val();
	var recordTime2 = $('#recordTime2').val();
	var html = "";
	$.ajax({
		url : "searchInfo.action",
		async : false,
		dataType : "json",
		data : {
			"xqName" : xqName,
			"recordTime1":recordTime1,
			"recordTime2":recordTime2,
		},
		success : function(data) {
			$("#SearchId").empty();
			var d = data.xqAvg;
			for (var i = 0; i < d.length; i++) {
				var xqName = d[i].xqName;
				var avg = d[i].avg;
				var tqyb = d[i].tqyb;
				var date = FormatDate(d[i].date);
				html += "<tr>";
				html += "<td class='text-center'>" + xqName + "</td>";
				html += "<td class='text-center'>" + avg + "</td>";
				html += "<td class='text-center'>" + tqyb + "</td>";
				html += "<td class='text-center' title='"+date+"'>" + date
						+ "</td>";
				html += "</tr>";
			}
			$("#SearchId").append(html);
		}

	})
}
function FormatDate(strTime) {
	var date = new Date(strTime);
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":"
			+ date.getMinutes() + ":" + date.getSeconds();
}
</script>
</head>
<body>
<div class="panel panel-success" style="width: 99%; height: 100%; position: absolute; overflow: auto;">
 <div class="panel-heading">历史温度</div>

<div class="dwrapper">
  <label for="xqNameId">选择小区</label> <select id="xqNameId" name="xqName">
				<c:if test="${!empty yhInfoList }">
					<option>--选择小区名称--</option>
					<c:forEach items="${yhInfoList}" var="yhInfolist">
						<option value="${yhInfolist.xqName}">${yhInfolist.xqName}</option>
					</c:forEach>
				</c:if>
				</select>
  选择时间：  <label for="readMTime">选择时间:</label>
		  <input type="text" id="recordTime1" name="recordTime1" class="Wdate" onfocus="WdatePicker();" />
		-<input type="text" id="recordTime2"  name="recordTime2"  class="Wdate" onfocus="WdatePicker();" />	
  <input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" />
 <button type="button" onclick="loadTwoLine()" class="btn btn-success btn-sm">图表分析</button>
<!--  <p id="TwoLineChart" style="width:100; height:300px;"></p> -->
	<table id="fixed_hdr2">
	<thead>

	<tr>
						<th>小区名称</th>
						<th>平均温度</th>
						<th>室外温度</th>
						<th>日期</th>
					</tr>
				</thead> 
				<tbody id=SearchId>
					<c:forEach  var="tjfx" items="${avgXq}" varStatus="status">
					<tr>
								    <td>${tjfx.xqName}</td>
                                     <td>${tjfx.avg}</td>
                                     <td>${tjfx.tqyb}</td>
									<td><fmt:formatDate value="${tjfx.date}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                </tr>
					</c:forEach>
				</tbody>
				
			</table>
			</div>
		</div>
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
    var recordTime1 = $('#recordTime1').val();
    var recordTime2 = $('#recordTime2').val();
    $.ajax({
        type: 'get',
        url:"XqNameAvg.action?xqName=" + xqName+"&recordTime1="+recordTime1+"&recordTime2"+recordTime2,//请求数据的地址//请求数据的地址
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
</body>
</html>