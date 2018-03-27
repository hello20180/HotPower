$(document).ready(function(){

    //本地数据
    var items = [];
    //保留两位小数
    var fixed2 = function(val){
        return val.toFixed(2);
    }

    //加百分号
    var fixed2percentage = function(val){
        return fixed2(val)+'%';
    }
    //高亮
    var highliht = function(val){
        if(val > 0){
            return '<span style="color: #b00">' + fixed2(val) + '</span>';
        }else if(val < 0){
            return '<span style="color: #0b0">' + fixed2(val) + '</span>';
        }
        return fixed2(val);
    };
    //列
    var cols = [
       
       
    ];

    //本地示例
    $('#table2-1').mmGrid({
        cols: cols,
        items: items
    });
    //AJAX示例
    $('#table2-2').mmGrid({
        cols: cols,
        url: 'data/stockQuote.json',
        method: 'get'
    });





    var cols3 = [
                   
                ];

	
    //客户端排序示例
    $('#table3-1').mmGrid({
        cols: cols3,
        items: items,
        sortName: 'DAYCHANGERATE',
        sortStatus: 'desc'
    });
    //服务器端排序示例
    $('#table3-2').mmGrid({
        cols: cols3,
        url: 'data/stockQuote.json',
        method: 'get',
        remoteSort:true ,
        sortName: 'SECUCODE',
        sortStatus: 'asc'
    });



    //锁定列宽
    var cols41 = [
       
    ];
    $('#table4-1').mmGrid({
        height: 200,
        cols: cols41,
        items: items
    });

    //隐藏列
    var cols42 = [
        {title:'行情', name:'',width: 30, align: 'center', renderer: function(val,item,rowIndex){
            return '<div class="btnPrice"></div>';
        }},
        { title:'股票代码', name:'SECUCODE' ,width:80, align:'center', hidden: true },
        { title:'股票名称', name:'SECUABBR' ,width:80, align:'center'},
        { title:'今收盘', name:'CLOSINGPRICE' ,width:60, align:'right', renderer: fixed2},
        { title:'涨跌幅', name:'DAYCHANGERATE' ,width:60, align:'right',renderer: highliht}
    ];
    $('#table4-2').mmGrid({
        height: 200,
        cols: cols42,
        items: items
    });

    //锁定列显示状态
    var cols43 = [
        {title:'行情', name:'',width: 30, align: 'center',lockDisplay: true, renderer: function(val,item,rowIndex){
            return '<div class="btnPrice"></div>';
        }},
        { title:'股票代码', name:'SECUCODE' ,width:80, align:'center' },
        { title:'股票名称', name:'SECUABBR' ,width:80, lockDisplay: true,align:'center'},
        { title:'今收盘', name:'CLOSINGPRICE' ,width:60, align:'right',lockDisplay: true, renderer: fixed2},
        { title:'涨跌幅', name:'DAYCHANGERATE',width:60, align:'right',renderer: highliht }
    ];
    $('#table4-3').mmGrid({
        height: 200,
        cols: cols43,
        items: items
    });


    //表头分组
    var groupCols = [
        {title:'1', name:'', width: 130, align: 'center', renderer: function(val,item,rowIndex){
            return '<div class="btnAppend">1111</div>';
        }},
        { title:'2', name:'CLOSINGPRICE' ,width:60, align:'right' ,sortable: true, renderer: fixed2},
        { title:'3盘', name:'CLOSINGPRICE' ,width:60, align:'right' ,sortable: true, renderer: fixed2},
        { title:'4', name:'DAYCHANGERATE' ,width:60, align:'right' ,sortable: true,renderer: highliht},
        { title:'5', name:'DAYCHANGE' ,width:60, align:'right' ,sortable: true, renderer: highliht},
        { title:'6', name:'AMPLITUDE' ,width:60, align:'right' ,sortable: true, renderer: fixed2percentage},
       
        { title:'7', name:'PREVCLOSINGPRICE' ,width:60, align:'right' ,sortable: true, renderer: fixed2},
        { title:'8', name:'OPENINGPRICE',width:60, align:'right' ,sortable: true, renderer: fixed2},
        { title:'9', name:'HIGHESTPRICE' ,width:60, align:'right' ,sortable: true, renderer: fixed2},
        { title:'10', name:'LOWESTPRICE' ,width:60, align:'right' ,sortable: true, renderer: fixed2}
    ];
    $('#table12-1').mmGrid({
        cols: groupCols,
        items: items
    });
	$("#table12-1").click(function(){
      window.open("http://www.baidu.com/",'新开googleWin',"fullscreen=1");
  });
});
