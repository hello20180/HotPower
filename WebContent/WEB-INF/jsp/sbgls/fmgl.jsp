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
	
<!-- Bootstrap -->
  <link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
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
			margin: 5px auto;
		}
		
		.ft_container table tr th {
			background: url(../images/secai.jpg);
		}
	</style>
	<script>
	$(function () {
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 560,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 60, align: 'center' },
			{ width: 55, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 160, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 70, align: 'center' },	
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 120, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>


<script type="text/javascript">
/*页面加载就开始执行js*/
$(document).ready(function() {
	$("#xqNameId").change(
	function(){	
	  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val(),function(data) {
		var d=data.xqlist;
		$("#buildNoId option:gt(0)").remove();
		$("#cellNoId option:gt(0)").remove();
		for(var i=0;i<d.length;i++){
			var buildNo=d[i].buildNO;
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
					var cellNo=d[i].cellNO;
					var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
					$("#cellNoId").append(opt);
				}
				});
			 });	
  });
  
 function kFm(){
		 var fmkd=$('#fmkd').val();
		var ckbs=$("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if(ckbs.length==0){
			alert("请选择要打开的阀门");
			return false;
		}
		var ids=[];
		$.each(ckbs,function(index,data){
			ids[index]=$(data).val();
		})
		var apiContentStr="";
		for(var i=0;i<ids.length;i++){
	   			 apiContentStr=ids[i];
	   			$.ajax({
	   				url:"/HotPower/FmglController/kFm.action",
	   				async:false,
	   				dataType:"json",
	   				data:{	
	   					"ids":apiContentStr,
	   					"fmkd":fmkd
	   				},
	   				success:function(data){
	   					msg=data.js
	   					if(msg=="0"){
	   						alert(data.fmId+"开阀接收指令成功!")
	   					}
	   					if(msg=="1"){
	   						alert(data.fmId+" 开阀失败!")
	   					}
	   					if(msg=="2"){
	   						alert(data.fmId+" 数据失败");
	   					}
	   					if(msg=="3"){
	   						alert(data.fmId+" 超时")
	   					}
	   					if(msg=="4"){
	   					    alert(data.fmId+" 集中器不在线");
	   						
	   					}
	   					if(msg=="5"){
	   					    alert("该用户无权限!");
	   						
	   					}
	   				}
	   				
	   			});
	   	}	
		searchInfo();
}
 function gFm(){
	 var ckbs=$("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if(ckbs.length==0){
			alert("请选择要关闭的阀门");
			return false;
		}
		var ids=[];
		$.each(ckbs,function(index,data){
			ids[index]=$(data).val();
		})
		var apiContentStr="";
		for(var i=0;i<ids.length;i++){
	   			 apiContentStr=ids[i];
	   			$.ajax({
	   				url:"/HotPower/FmglController/gFm.action",
	   				async:false,
	   				dataType:"json",
	   				data:{	
	   					"ids":apiContentStr
	   				},
	   				success:function(data){
	   					msg=data.js;
	   					if(msg=="0"){
	   						alert(data.fmId+" 关阀接收指令成功!");
	   					}
	   					if(msg=="1"){
	   						alert(data.fmId+" 关阀失败!");
	   					}
	   					if(msg=="2"){
	   						alert(data.fmId+" 数据失败");
	   					}
	   					if(msg=="3"){
	   						alert(data.fmId+" 超时");
	   					}
	   					if(msg=="4"){
	   						alert(data.fmId+" 集中器不在线");
	   					}
	   					if(msg=="5"){
	   					    alert("该用户无权限!");
	   						
	   					}
	   				}
	   			});
	   	}	
		searchInfo();
}
 
 function dFm(){	
	 var ckbs=$("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if(ckbs.length==0){
			alert("请选择要读取的阀门");
			return false;
		}
		var ids=[];
		$.each(ckbs,function(index,data){
			ids[index]=$(data).val();
		})
		var apiContentStr="";
		for(var i=0;i<ids.length;i++){
	   			 apiContentStr=ids[i];
	   			$.ajax({
	   				url:"/HotPower/FmglController/dFm.action",
	   				async:false,
	   				dataType:"json",
	   				data:{
	   					"ids":apiContentStr,
	   				},
	   				success:function(data){
	   					msg=data.js;
	   					if(msg=="0"){
	   						alert(data.fmId+"读阀接收指令成功!")
	   					}
	   					if(msg=="1"){
	   						alert(data.fmId+"超时!")
	   					}
	   					if(msg=="2"){
	   						alert(data.fmId+"读阀失败!");
	   					}if(msg=="3"){	
	   						alert(data.fmId+"集中器不在线!");
	   					}
	   				}
	   				
	   			});
	   	}	
		searchInfo();
 }
 //读传感器地址
function cgqads(){
	var ckbs=$("#fmInfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		
		alert("请选择要读传感器地址的阀门");


		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能读取一个");
		return false;
	}
	var id=ckbs.val();
	var fmId=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().text();
	var qgId=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text();
	$.ajax({
		url:"/HotPower/FmglController/cgqads.action",
		async:false,
		dataType:"json",
		data:{
			"fmId":fmId,
			"qgId":qgId
		},
		success:function(data){
			msg=data;
			if(msg=="0"){
				alert("发送接收指令成功!")
			}
			if(msg=="1"){
				alert("集中器不在线!")
			}
		}
		
	});
	searchInfo();
}
 //发送参数
 function FsCs(){
	    var wdsd = $('#wdsd').val();
	    var tjzq=$('#tjzq').val();
	    var tjcs=$('#tjcs').val();
	    var sdbs=$('#sdbs').val();
	    var ckbs=$("#fmInfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要发送的阀门");
			return false;
		}
		if(ckbs.length>1){
			alert("对不起一次只能读取一个");
			return false;
		}
		var id=ckbs.val();
		var fmId=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().text();
		var qgId=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text();		
	
		$.ajax({
			url:"/HotPower/FmglController/FsCs.action",
			async:false,
			dataType:"json",
			data:{
				"fmId":fmId,
				"qgId":qgId,
				"wdsd":wdsd,
				"tjzq":tjzq,
				"tjcs":tjcs,
				"sdbs":sdbs,
			},
			success:function(data){
				msg=data;
					if(msg=="0"){
						alert("发送接收指令成功!")
					}
					if(msg=="1"){
						alert("发送失败!")
					}
					if(msg=="2"){
						alert("数据失败");
					}
					if(msg=="3"){
						alert("超时")
					}
					if(msg=="4"){
						alert("集中器不在线");
					}
					if(msg=="5"){
   					    alert("该用户无权限!");
   						
   					}
			}
			
		});
		searchInfo();
 }
 
/* function check() 
 {
	 var tjcs=$('#tjcs').val();
	 if(!IsNum(tjcs)){
		 alert("请输入0-255的数字!")
		 $("#tjcs").val(""); // 清空并获得焦点
		 return false;
		 }
	 function IsNum(num){
		  var reNum=/^([0-9]|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])$/;
		  return(reNum.test(num));
		}
 }  */
//查看历史数据
 function HistorySj(){
	 var ckbs=$("#fmInfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要查看的用户");
			return false;
		}
		if(ckbs.length>1){
			alert("对不起一次只能选择一个");
			return false;
		}
		var id=ckbs.val();
		$("#edit").modal({keyboard:false});
		$.ajax({
			url:"/HotPower/FmglController/HistorySj.action",
			async:false,
			dataType:"json",
			data:{
				"fmId":id,
			},
			success:function(data){
				d=data.fList;
				$("#edit .modal-body .col-sm-7").empty();
				for (var i = 0; i < d.length; i++) {
					var RoomTemp = d[i].fmHistory.roomTemp;
					var date = FormatDate(d[i].fmHistory.recordTime);	
					$("#edit .modal-body .col-sm-7").append("<h6><span class='text-danger'>室内温度:"+RoomTemp+" &nbsp;   采集时间:"+date+"<br/>"+"</span></h6>");
					}
				$("#edit").modal({keyboard:false});
			}
			
		});	
 }
 
 function XCgq(){
		 var CgqId = $('#CgqId').val();
	    var ckbs=$("#fmInfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要修改的地址");
			return false;
		}
		if(ckbs.length>1){
			alert("对不起一次只能修改一个");
			return false;
		}
		if(CgqId==""){
			alert("请填写新的传感器地址");
			return false;
		}
		var id=ckbs.val();
		var fmId=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().text();
		var qgId=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().text();		
		$.ajax({
			url:"/HotPower/FmglController/XCgq.action",
			async:false,
			dataType:"json",
			data:{
				"fmId":fmId,
				"qgId":qgId,
				"CgqId":CgqId,
			},
			success:function(data){
				msg=data;
					if(msg=="0"){
						alert("发送接收指令成功!")
					}
					if(msg=="1"){
						alert("发送失败!")
					}
					if(msg=="2"){
						alert("数据失败");
					}
					if(msg=="3"){
						alert("超时")
					}
					if(msg=="4"){
						alert("集中器不在线");
					}
					if(msg=="5"){
   					    alert("该用户无权限!");
   						
   					}
		
			}
			
		});
		searchInfo();
	 
	 
	 
 }
 
 function yhflSear(){
	    var yhfl = $('#yhfl').val();
	    var html ="";
		$.ajax({ 
			        url:"yhflSear.action",
					async : false,
					dataType : "json",
					data : {
						"yhfl" : yhfl,
					},
					success : function(data) {
						$("#fmInfo").empty();
						$("#fmInfo").html("");
						var d=data.findList;
						for(var i=0;i<d.length;i++){
							var yhName=d[i].yhName;
							var xqName=d[i].xqName;
							var buildNo=d[i].buildNO;
							var cellNo=d[i].cellNO;
							var houseNo =d[i].houseNO ;
							var status=d[i].fm.status;
							var famKd=d[i].fm.famKd;
							var valTemp=d[i].fm.valTemp;	
							var roomTemp=d[i].fm.roomTemp;
							var recordTime=FormatDate(d[i].fm.recordTime) ;
							var valAd=d[i].fm.valAd;
							var WcAd=d[i].wCAd;
							var lockSt=d[i].fm.lockSt;	
							var hTemSet=d[i].fm.hTemSet;
							var mTemSet=d[i].fm.mTemSet;	
							var lTemSet=d[i].fm.lTemSet;
							var qgID=d[i].fm.qgID;
							var sFJF=d[i].sFJF;
							var sFQF=d[i].sFQF;
							var sFTR=d[i].sFTR;
							var yhfl=d[i].yhfl;
							var bz=d[i].bz;
							var bjxx=d[i].bjxx;
							var bjTime=d[i].bjTime;
							var yhRb=d[i].yhRb;
							var type=d[i].fm.type;
							if(type==undefined){
								type='';
							}
							if(bjxx==undefined){
								bjxx='';
							}
							if(bjTime==undefined){
								BjTime='';
							}else{
							var BjTime=FormatDate(bjTime) ;
							}
							if(yhfl==undefined){
								yhfl='';
							}
							if(bz==undefined){
								bz='';
							}
							html+="<tr>";					
							html+="<td class='text-center'><input type='checkbox'  value='"+valAd+"'/></td>";
							if(yhfl=='重点监控'){
							html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'><font color='red'>"+yhName+"</font></a></td>";
							}else if(yhfl=='特殊情况'){
							html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'><font color='blue'>"+yhName+"</font></a></td>";
							}else if(yhfl=='退费停暖'){
								html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'><font color='#FF8C00'>"+yhName+"</font></a></td>";	
							}else{
								html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'>"+yhName+"</a></td>";	
							}
							html+="<td class='text-center'>"+xqName+"</td>";
							html+="<td class='text-center'>"+buildNo+"</td>";
							html+="<td class='text-center'>"+cellNo+"</td>";
							html+="<td class='text-center'>"+houseNo+"</td>";
							html+="<td class='text-center'>"+status+"</td>";
							html+="<td class='text-center'>"+sFJF+"</td>";
							html+="<td class='text-center'>"+famKd+"</td>";
							if(bjxx=='低温'){
								html+="<td class='text-center'><font color='#FF8C00'>"+roomTemp+"</font></td>";	
							}else if(bjxx=='高温'){
								html+="<td class='text-center'><font color='red'>"+roomTemp+"</font></td>";
							}else{
								html+="<td class='text-center'>"+roomTemp+"</td>";
							}
							html+="<td class='text-center'>"+valTemp+"</td>";
							html+="<td class='text-center'>"+lockSt+"</td>";
							html+="<td  class='text-center' title='"+recordTime+"'>"+recordTime+"</td>";
							html+="<td class='text-center'>"+valAd+"</td>";
							html+="<td class='text-center'>"+WcAd+"</td>";
							html+="<td class='text-center'>"+hTemSet+"</td>";
							html+="<td class='text-center'>"+mTemSet+"</td>";
							html+="<td class='text-center'>"+lTemSet+"</td>";
							html+="<td class='text-center'>"+qgID+"</td>";
							html+="<td class='text-center'>"+type+"</td>";
							html+="<td class='text-center'>"+yhfl+"</td>";
							if(yhRb==0){
								html+="<td class='text-center'>非热表用户</td>";
							}else{
								html+="<td class='text-center'>热表用户</td>";
							}
							html+="<td class='text-center'>"+bjxx+"</td>";
							html+="<td  class='text-center' title='"+BjTime+"'>"+BjTime+"</td>";
						
						html+="</tr>";
							}
						$("#fmInfo").append(html);
					}

				})
	 
 }
 
//搜索
function searchInfo(){
    var   xqName = $('#xqNameId').val();
    var   buildNo=$('#buildNoId').val();
    var   cellNo=$('#cellNoId').val();
    var   houseNo=$('#houseNoId').val();
    var   sfjf=$('#JFStatusId').val();
    var html ="";
	$.ajax({ 
		        url:"searchInfo.action",
				async : false,
				dataType : "json",
				data : {
					"xqName" : xqName,
					"buildNo" : buildNo,
					"cellNo" : cellNo,
					"houseNo" : houseNo,
					"houseNo" : houseNo,
					"sfjf" : sfjf,
				},
				success : function(data) {
					$("#fmInfo").empty();
					$("#fmInfo").html("");
					var d=data.findList;
					for(var i=0;i<d.length;i++){
						var yhName=d[i].yhName;
						var xqName=d[i].xqName;
						var buildNo=d[i].buildNO;
						var cellNo=d[i].cellNO;
						var houseNo =d[i].houseNO ;
						var status=d[i].fm.status;
						var famKd=d[i].fm.famKd;
						var valTemp=d[i].fm.valTemp ;	
						var roomTemp=d[i].fm.roomTemp;
						var recordTime=FormatDate(d[i].fm.recordTime);
						var valAd=d[i].fm.valAd;
						var WcAd=d[i].wCAd;
						var lockSt=d[i].fm.lockSt;	
						var hTemSet=d[i].fm.hTemSet;
						var mTemSet=d[i].fm.mTemSet;	
						var lTemSet=d[i].fm.lTemSet;
						var qgID=d[i].fm.qgID;
						var sFJF=d[i].sFJF;
						var sFQF=d[i].sFQF;
						var sFTR=d[i].sFTR;
						var bz=d[i].bz;
						var yhfl=d[i].yhfl;
						var yhRb=d[i].yhRb;
						var bjxx=d[i].bjxx;
						var bjTime=d[i].bjTime;
						var type=d[i].fm.type;
						if(bjxx==undefined){
							bjxx='';
						}
						if(bjTime==undefined){
							BjTime='';
						}else{
						var BjTime=FormatDate(bjTime) ;
						}
						if(yhfl==undefined){
							yhfl='';
						}
						if(bz==undefined){
							bz='';
						}
						if(type==undefined){
							type='';
						}
							html+="<tr>";					
							html+="<td class='text-center'><input type='checkbox'  value='"+valAd+"'/></td>";
							if(yhfl=='重点监控'){
							html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'><font color='red'>"+yhName+"</font></a></td>";
							}else if(yhfl=='特殊情况'){
							html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'><font color='blue'>"+yhName+"</font></a></td>";
							}else if(yhfl=='退费停暖'){
								html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'><font color='#FF8C00'>"+yhName+"</font></a></td>";	
							}else{
								html+="<td class='text-center' title='"+bz+"'><a href='/HotPower/FmHController/goHistoryLine.action?xqName="+xqName+"&buildNO="+buildNo+"&cellNO="+cellNo+"&houseNO="+houseNo+"'>"+yhName+"</a></td>";	
							}
							html+="<td class='text-center'>"+xqName+"</td>";
							html+="<td class='text-center'>"+buildNo+"</td>";
							html+="<td class='text-center'>"+cellNo+"</td>";
							html+="<td class='text-center'>"+houseNo+"</td>";
							html+="<td class='text-center'>"+status+"</td>";
							html+="<td class='text-center'>"+sFJF+"</td>";
							html+="<td class='text-center'>"+famKd+"</td>";
							if(bjxx=='低温'){
								html+="<td class='text-center'><font color='#FF8C00'>"+roomTemp+"</font></td>";	
							}else if(bjxx=='高温'){
								html+="<td class='text-center'><font color='red'>"+roomTemp+"</font></td>";
							}else{
								html+="<td class='text-center'>"+roomTemp+"</td>";
							}
							html+="<td class='text-center'>"+valTemp+"</td>";
							html+="<td class='text-center'>"+lockSt+"</td>";
							html+="<td  class='text-center' title='"+recordTime+"'>"+recordTime+"</td>";
							html+="<td class='text-center'>"+valAd+"</td>";
							html+="<td class='text-center'>"+WcAd+"</td>";
							html+="<td class='text-center'>"+hTemSet+"</td>";
							html+="<td class='text-center'>"+mTemSet+"</td>";
							html+="<td class='text-center'>"+lTemSet+"</td>";
							html+="<td class='text-center'>"+qgID+"</td>";
							html+="<td class='text-center'>"+type+"</td>";
							html+="<td class='text-center'>"+yhfl+"</td>";
							if(yhRb==0){
								html+="<td class='text-center'>非热表用户</td>";
							}else{
								html+="<td class='text-center'>热表用户</td>";
							}
							html+="<td class='text-center'>"+bjxx+"</td>";
							html+="<td  class='text-center' title='"+BjTime+"'>"+BjTime+"</td>";
						
						html+="</tr>";
						}
					$("#fmInfo").append(html);
				}

			})
		}
 //时间转换
function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
 
 </script>
 
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">阀门管理</div>
 
<p>
		选择小区
		<select id="xqNameId" name="xqName">
			<c:if test="${!empty yhInfoList }">
			   <option>--选择小区名称--</option>
				<c:forEach items="${yhInfoList}" var="yhInfolist">
					<option>${yhInfolist.xqName}</option>
				</c:forEach>
			</c:if>
		</select>
		&nbsp;&nbsp;&nbsp;

		楼栋号
		 <select name="buildNo" id="buildNoId">
			<option value=0>--选择楼栋号--</option>
		</select>
	&nbsp;&nbsp;&nbsp;
		单元号
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>
	户号：<input type="text" name="houseNo" id="houseNoId" size=6px value="${houseNo}"  />
	
	<label for="JFStatusId">选择缴费状态</label> 
		<select id="JFStatusId" name="sfjf">
			   <option>--选择缴费状态--</option>
			     <option selected="selected">是</option>
			     <option>否</option>
		</select>
<input type="button" class="btn btn-success" onclick="searchInfo()" value="搜索">

<br>
阀门开度设置<input type="text" size="5px" id="fmkd" name="fmkd"/>
<button type="button"  class="btn btn-success"  onclick="kFm()">开阀</button>
<button type="button" class="btn btn-success" onclick="gFm()">关阀</button>
<button type="button" class="btn btn-success" onclick="dFm()">读阀</button>
<button type="button" class="btn btn-success" onclick="cgqads()">读传感器地址</button>
&nbsp;&nbsp;&nbsp;
用户类别： 
			<select id="yhfl" name="yhfl">
			<option value="">--选择用户类别--</option>
			<option value="普通用户">普通用户</option>
			<option value="重点监控">重点监控</option>
			<option value="特殊情况">特殊情况</option>
			<option value="退费停暖">退费停暖</option>
			</select>
   <button type="button" class="btn btn-success" onClick="yhflSear()">搜索</button>
<button type="button" class="btn btn-success" onClick="HistorySj()">查看历史数据</button>
<br>
调节周期 <input type="text" id="wdsd" name="wdsd" size=6px value="6"/>
室温设定<input type="text" id="tjzq" name="tjzq" size=6px value="20"/>
调节参数<input type="text" id="tjcs" name="tjcs" size=6px value="153"/>
锁定标识   <select name="sdbs" id="sdbs">
		<option value=02>请选锁定标识</option>
		<option value=00>自动</option>
		<option value=01>锁定</option>
	  </select>
	  &nbsp;&nbsp;&nbsp;
<button class="btn btn-success" onclick="FsCs()" >发送参数</button>

无线传感器地址<input type="text" size="7px" id="CgqId"/>
<button class="btn btn-success" onclick="XCgq()">修改传感器地址</button>
<!-- 抄表数据：<input type="text" value="" id="cb"/> -->
<%-- <script type="text/javascript">

function load(){
    //document.all.oframe.CreateNew("Word.Document");   //此处是新建一个word
   window.open("<%=basePath%>uplod/1.docx");     //此处为打开给定地址的word
}

</script>
<button onclick="load()">点击</button> --%>

<form>
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
            <th>阀门状态</th>
            <th>缴费状态</th>
            <th>阀门开度</th>
            <th>室内温度</th>
            <th>管道温度</th>
            <th>锁定标识</th>
            <th>更新时间</th>
            <th>阀门地址</th>
            <th>传感器地址</th>
            <th>调整时间</th>
            <th>设定温度</th>
            <th>设定参数</th>
            <th>区管ID</th>
            <th>操控</th>
            <th>用户类别</th>
            <th>用户分类</th>
            <th>是否报警</th>
            <th>报警时间</th>
     </tr>
  </thead>

  <tbody id="fmInfo">

		<c:forEach  var="yh" items="${findList}" varStatus="status">
		 <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>

   		<td><input type="checkbox" value="${yh.fm.valAd}" /></td>
   		<c:choose>
   		<c:when test="${yh.yhfl=='重点监控'}">
   	    <td title="${yh.bz}"><a href="/HotPower/FmHController/goHistoryLine.action?xqName=${yh.xqName}&buildNO=${yh.buildNO}&cellNO=${yh.cellNO}&houseNO=${yh.houseNO}"><font color="red">${yh.yhName}</font></a></td>
		</c:when>
		<c:when test="${yh.yhfl=='特殊情况'}">
   	    <td title="${yh.bz}"><a href="/HotPower/FmHController/goHistoryLine.action?xqName=${yh.xqName}&buildNO=${yh.buildNO}&cellNO=${yh.cellNO}&houseNO=${yh.houseNO}"><font color="blue">${yh.yhName}</font></a></td>
		</c:when>
		<c:when test="${yh.yhfl=='退费停暖'}">
   	    <td title="${yh.bz}"><a href="/HotPower/FmHController/goHistoryLine.action?xqName=${yh.xqName}&buildNO=${yh.buildNO}&cellNO=${yh.cellNO}&houseNO=${yh.houseNO}"><font color="#FF8C00">${yh.yhName}</font></a></td>
		</c:when>
		<c:otherwise>
		  <td title="${yh.bz}"><a href="/HotPower/FmHController/goHistoryLine.action?xqName=${yh.xqName}&buildNO=${yh.buildNO}&cellNO=${yh.cellNO}&houseNO=${yh.houseNO}">${yh.yhName}</a></td>
		</c:otherwise>
		</c:choose>
		<td>${yh.xqName}</td>
		<td>${yh.buildNO}</td>
		<td>${yh.cellNO}</td>
		<td>${yh.houseNO}</td>
		<td>${yh.fm.status}</td>
		<td>${yh.sFJF}</td>
		<td>${yh.fm.famKd}</td>
		<c:choose>
		<c:when test="${yh.bjxx=='低温'}">
		<td><font color="#FF8C00">${yh.fm.roomTemp}</font></td>
		</c:when>
		<c:when test="${yh.bjxx=='高温'}">
		<td><font color="red">${yh.fm.roomTemp}</font></td>
		</c:when>
		<c:otherwise>
		<td>${yh.fm.roomTemp}</td>
		</c:otherwise>
		</c:choose>
		
		<td>${yh.fm.valTemp}</td>
		<td>${yh.fm.lockSt}</td>
		<td><fmt:formatDate value="${yh.fm.recordTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${yh.fm.valAd}</td>
		<td>${yh.WCAd}</td>
		
		<td>${yh.fm.hTemSet}</td>
		<td>${yh.fm.mTemSet}</td>
		<td>${yh.fm.lTemSet}</td>
		<td>${yh.fm.qgID}</td>	
		<td>${yh.fm.type}</td>
   		<td>${yh.yhfl}</td>
   		<c:choose>
   		 <c:when test="${yh.yhRb==0}">
   		<td>非热表用户</td>
   		</c:when> 
   		<c:otherwise>
   		<td>热表用户</td>
   		</c:otherwise>
   		</c:choose>
		<td>${yh.bjxx}</td> 
   		 <td><font><fmt:formatDate value="${yh.bjTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></font></td>
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>
</form>
</div>
<div class="modal fade bs-example-modal-sm" id="edit">
		<div class="modal-dialog">
			<div class="modal-content">
			<form action="">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">历史数据</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-3"></div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>