// JavaScript Document
var textDiv = document.getElementById("rollText");
var textList = textDiv.getElementsByTagName("li");
if(textList.length > 2){
	var textDat = textDiv.innerHTML;
	var br = textDat.toLowerCase().indexOf("<br",textDat.toLowerCase().indexOf("<br")+3);
	//var textUp2 = textDat.substr(0,br);
	textDiv.innerHTML = textDat+textDat+textDat.substr(0,br);
	textDiv.style.cssText = "position:absolute; top:0";
	var textDatH = textDiv.offsetHeight;MaxRoll();
}
var minTime,maxTime,divTop,newTop=0;
function MinRoll(){
	newTop++;
	if(newTop<=divTop+35){
		textDiv.style.top = "-" + newTop + "px";
	}else{
		clearInterval(minTime);
		maxTime = setTimeout(MaxRoll,2000);
	}
}
function MaxRoll(){
	divTop = Math.abs(parseInt(textDiv.style.top));
	if(divTop>=0 && divTop<textDatH-35){
		minTime = setInterval(MinRoll,1);
	}else{
		textDiv.style.top = 0;divTop = 0;newTop=0;MaxRoll();
	}
}