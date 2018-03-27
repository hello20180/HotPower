var alertObj = new Object(); 
var generalStyle = { 
zIndex: 0, 
width: "400px", 
height: "200px", 
border: "thick solid #CCCCCC", 
background: "#FFFFFF", 
position: "absolute", 
top: "35%", 
left: "40%"
} 
var txtStyle = { 
textAlign: "center"
} 
var btnStyle = { 
position: "absolute", 
left: "40%", 
top: "70%", 
color: "#333333", 
fontWeight: "bold", 
outlineColor:"#3366FF", 
outlineStyle:"ridge", 
outlineWidth:"thin", 
//outline: "thin ridge #3366FF", 
innerHTML: "OK"
} 
alertObj = { 
generalSet: generalStyle, 
txtSet: txtStyle, 
btnSet: btnStyle, 
isExist: false
} 
alertObj.createComponent = function() { 
var component = document.createElement(arguments[0]); 
var styles = arguments[1]; 
for (var property in styles) { 
if (styles[property] != null) { 
try{ 
component.style[property] = styles[property]; 
}catch(err){ 
document.write(err.name+":"+property+"<br/>");//set property error! 
} 
} 
} 
return component; 
} 
alertObj.show = function() { 
if(!this.isExist){ 
this.isExist = true; 
var bodyObj = document.body; 
bodyObj.style.zIndex = -1; 
bodyObj.style.background = "#999999"; 
var divObj = this.createComponent("div", this.generalSet); 
 
var txtObj = this.createComponent("p", this.txtSet); 
txtObj.innerHTML = arguments[0]; 
var btnObj = this.createComponent("button", this.btnSet); 
btnObj.innerHTML = this.btnSet.innerHTML; 
btnObj.onclick = function() { 
bodyObj.style.zIndex=0; 
bodyObj.style.background=""; 
bodyObj.removeChild(divObj); 
if(alertObj.isExist){ 
alertObj.isExist = false; 
} 
} 
divObj.appendChild(txtObj); 
divObj.appendChild(btnObj); 
bodyObj.appendChild(divObj); 
} 
} 
function show(s) { 
alertObj.show(s); 
} 