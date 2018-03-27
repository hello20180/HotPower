// JavaScript Document
var textDiv1 = document.getElementById("rollText1");
var textList1 = textDiv1.getElementsByTagName("li");
if(textList1.length > 2){
	var textDat1 = textDiv1.innerHTML;
	var br = textDat1.toLowerCase().indexOf("<br",textDat1.toLowerCase().indexOf("<br")+3);
	//var textUp2 = textDat.substr(0,br);
	textDiv1.innerHTML = textDat1+textDat1+textDat1.substr(0,br);
	textDiv1.style.cssText = "position:absolute; top:0";
	var textDatH1 = textDiv1.offsetHeight;MaxRoll1();
}
var minTime1,maxTime1,divTop1,newTop1=0;
function MinRoll1(){
	newTop1++;
	if(newTop1<=divTop1+35){
		textDiv1.style.top = "-" + newTop1 + "px";
	}else{
		clearInterval(minTime1);
		maxTime1 = setTimeout(MaxRoll1,2000);
	}
}
function MaxRoll1(){
	divTop1 = Math.abs(parseInt(textDiv1.style.top));
	if(divTop1>=0 && divTop1<textDatH1-35){
		minTime1 = setInterval(MinRoll1,1);
	}else{
		textDiv1.style.top = 0;divTop1 = 0;newTop1=0;MaxRoll1();
	}
}