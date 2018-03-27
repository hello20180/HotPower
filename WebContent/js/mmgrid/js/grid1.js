$(document)
		.ready(
				function() {

					var items = [ {
						AMPLITUDE : 0.9309,
						PREVCLOSINGPRICE : 7.52,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 7.56,
						TURNOVERVOL : 36268940,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 18653471415,
						SECUCODE : "600000",
						EPS : 0.9217,
						LOWESTPRICE : 7.49,
						OPENINGPRICE : 7.51,
						SECUABBR : "浦发银行",
						ALISTEDSHARE : 14922777132,
						ID : 3131258,
						WCOSTAVG : 8.7968,
						NETCASHFLOWOPERPS : 1.125,
						SECUNAME : "上海浦东发展银行股份有限公司",
						CLOSINGPRICE : 7.51,
						DAYCHANGERATE : -0.133,
						TURNOVERVAL : 272732527,
						BVPS : 8.686,
						DAYCHANGE : -0.01,
						PE : 5.134,
						TURNOVERRATE : 0.243,
						ADJUSTCLOSINGPRICE : 51.8586,
						PB : 0.9409
					}, {
						AMPLITUDE : 2.0472,
						PREVCLOSINGPRICE : 6.35,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 6.41,
						TURNOVERVOL : 1278011,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 1150000000,
						SECUCODE : "600004",
						EPS : 0.1567,
						LOWESTPRICE : 6.28,
						OPENINGPRICE : 6.34,
						SECUABBR : "白云机场",
						ALISTEDSHARE : 1150000000,
						ID : 3131387,
						WCOSTAVG : 6.8846,
						NETCASHFLOWOPERPS : 0.23,
						SECUNAME : "广州白云国际机场股份有限公司",
						CLOSINGPRICE : 6.39,
						DAYCHANGERATE : 0.6299,
						TURNOVERVAL : 8115183,
						BVPS : 6.13,
						DAYCHANGE : 0.04,
						PE : 10.5415,
						TURNOVERRATE : 0.1111,
						ADJUSTCLOSINGPRICE : 9.429,
						PB : 1.0697
					}, {
						AMPLITUDE : 1.5504,
						PREVCLOSINGPRICE : 2.58,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 2.61,
						TURNOVERVOL : 10685141,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 10093779823,
						SECUCODE : "600005",
						EPS : 0.0043,
						LOWESTPRICE : 2.57,
						OPENINGPRICE : 2.59,
						SECUABBR : "武钢股份",
						ALISTEDSHARE : 10093779823,
						ID : 3131671,
						WCOSTAVG : 3.0629,
						NETCASHFLOWOPERPS : 0.09,
						SECUNAME : "武汉钢铁股份有限公司",
						CLOSINGPRICE : 2.6,
						DAYCHANGERATE : 0.7752,
						TURNOVERVAL : 27677196,
						BVPS : 3.562,
						DAYCHANGE : 0.02,
						PE : 24.2193,
						TURNOVERRATE : 0.1059,
						ADJUSTCLOSINGPRICE : 13.4817,
						PB : 0.7308
					}, {
						AMPLITUDE : 2.069,
						PREVCLOSINGPRICE : 2.9,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 2.95,
						TURNOVERVOL : 2511165,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 2000000000,
						SECUCODE : "600006",
						EPS : 0.0266,
						LOWESTPRICE : 2.89,
						OPENINGPRICE : 2.9,
						SECUABBR : "东风汽车",
						ALISTEDSHARE : 2000000000,
						ID : 3131903,
						WCOSTAVG : 3.5362,
						NETCASHFLOWOPERPS : -0.4041,
						SECUNAME : "东风汽车股份有限公司",
						CLOSINGPRICE : 2.9,
						DAYCHANGERATE : 0,
						TURNOVERVAL : 7316381,
						BVPS : 3.0533,
						DAYCHANGE : 0,
						PE : 12.4963,
						TURNOVERRATE : 0.1256,
						ADJUSTCLOSINGPRICE : 10.0741,
						PB : 0.9581
					}, {
						AMPLITUDE : 1.9704,
						PREVCLOSINGPRICE : 10.15,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 10.35,
						TURNOVERVOL : 155609,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 1007282534,
						SECUCODE : "600007",
						EPS : 0.0887,
						LOWESTPRICE : 10.15,
						OPENINGPRICE : 10.15,
						SECUABBR : "中国国贸",
						ALISTEDSHARE : 1007282534,
						ID : 3132032,
						WCOSTAVG : 9.8783,
						NETCASHFLOWOPERPS : 0.22,
						SECUNAME : "中国国际贸易中心股份有限公司",
						CLOSINGPRICE : 10.26,
						DAYCHANGERATE : 1.0837,
						TURNOVERVAL : 1599848,
						BVPS : 4.5,
						DAYCHANGE : 0.11,
						PE : 53.2288,
						TURNOVERRATE : 0.0154,
						ADJUSTCLOSINGPRICE : 16.142,
						PB : 2.3261
					}, {
						AMPLITUDE : 2.7211,
						PREVCLOSINGPRICE : 4.41,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 4.53,
						TURNOVERVOL : 3872525,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 2200000000,
						SECUCODE : "600008",
						EPS : 0.0714,
						LOWESTPRICE : 4.41,
						OPENINGPRICE : 4.42,
						SECUABBR : "首创股份",
						ALISTEDSHARE : 2200000000,
						ID : 3131442,
						WCOSTAVG : 5.2878,
						NETCASHFLOWOPERPS : 0.055,
						SECUNAME : "北京首创股份有限公司",
						CLOSINGPRICE : 4.49,
						DAYCHANGERATE : 1.8141,
						TURNOVERVAL : 17341208,
						BVPS : 2.3832,
						DAYCHANGE : 0.08,
						PE : 18.8918,
						TURNOVERRATE : 0.176,
						ADJUSTCLOSINGPRICE : 15.1655,
						PB : 1.8324
					}, {
						AMPLITUDE : 0.7389,
						PREVCLOSINGPRICE : 12.18,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 12.22,
						TURNOVERVOL : 1332194,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 1926958448,
						SECUCODE : "600009",
						EPS : 0.1909,
						LOWESTPRICE : 12.13,
						OPENINGPRICE : 12.19,
						SECUABBR : "上海机场",
						ALISTEDSHARE : 1093476397,
						ID : 3131171,
						WCOSTAVG : 12.8369,
						NETCASHFLOWOPERPS : -0.04,
						SECUNAME : "上海国际机场股份有限公司",
						CLOSINGPRICE : 12.14,
						DAYCHANGERATE : -0.3284,
						TURNOVERVAL : 16207539,
						BVPS : 8.16,
						DAYCHANGE : -0.04,
						PE : 15.5997,
						TURNOVERRATE : 0.1218,
						ADJUSTCLOSINGPRICE : 33.1878,
						PB : 1.523
					}, {
						AMPLITUDE : 3.9604,
						PREVCLOSINGPRICE : 6.06,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 6.11,
						TURNOVERVOL : 95948038,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 6423643659,
						SECUCODE : "600010",
						EPS : 0.0017,
						LOWESTPRICE : 5.87,
						OPENINGPRICE : 6.06,
						SECUABBR : "包钢股份",
						ALISTEDSHARE : 6423643659,
						ID : 3133528,
						WCOSTAVG : 5.6692,
						NETCASHFLOWOPERPS : -0.09,
						SECUNAME : "内蒙古包钢钢联股份有限公司",
						CLOSINGPRICE : 5.99,
						DAYCHANGERATE : -1.1551,
						TURNOVERVAL : 572896242,
						BVPS : 2.01,
						DAYCHANGE : -0.07,
						PE : 77.53,
						TURNOVERRATE : 1.4937,
						ADJUSTCLOSINGPRICE : 17.6883,
						PB : 2.9846
					}, {
						AMPLITUDE : 2.0864,
						PREVCLOSINGPRICE : 6.71,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 6.8,
						TURNOVERVOL : 8203502,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 14055383440,
						SECUCODE : "600011",
						EPS : 0.1571,
						LOWESTPRICE : 6.66,
						OPENINGPRICE : 6.78,
						SECUABBR : "华能国际",
						ALISTEDSHARE : 10000000000,
						ID : 3132159,
						WCOSTAVG : 5.7905,
						NETCASHFLOWOPERPS : 0.91,
						SECUNAME : "华能国际电力股份有限公司",
						CLOSINGPRICE : 6.68,
						DAYCHANGERATE : -0.4471,
						TURNOVERVAL : 55167434,
						BVPS : 3.66,
						DAYCHANGE : -0.03,
						PE : 74.0314,
						TURNOVERRATE : 0.082,
						ADJUSTCLOSINGPRICE : 23.7094,
						PB : 1.875
					}, {
						AMPLITUDE : 0.7712,
						PREVCLOSINGPRICE : 3.89,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 3.91,
						TURNOVERVOL : 418515,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 1658610000,
						SECUCODE : "600012",
						EPS : 0.1278,
						LOWESTPRICE : 3.88,
						OPENINGPRICE : 3.89,
						SECUABBR : "皖通高速",
						ALISTEDSHARE : 1165600000,
						ID : 3133590,
						WCOSTAVG : 4.4675,
						NETCASHFLOWOPERPS : 0.25,
						SECUNAME : "安徽皖通高速公路股份有限公司",
						CLOSINGPRICE : 3.9,
						DAYCHANGERATE : 0.2571,
						TURNOVERVAL : 1632075,
						BVPS : 3.8226,
						DAYCHANGE : 0.01,
						PE : 7.5503,
						TURNOVERRATE : 0.0359,
						ADJUSTCLOSINGPRICE : 6.945,
						PB : 1.0322
					}, {
						AMPLITUDE : 0.8037,
						PREVCLOSINGPRICE : 8.71,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 8.75,
						TURNOVERVOL : 14473038,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 6849725776,
						SECUCODE : "600015",
						EPS : 0.8869,
						LOWESTPRICE : 8.68,
						OPENINGPRICE : 8.71,
						SECUABBR : "华夏银行",
						ALISTEDSHARE : 4990528316,
						ID : 3133403,
						WCOSTAVG : 10.581,
						NETCASHFLOWOPERPS : 2,
						SECUNAME : "华夏银行股份有限公司",
						CLOSINGPRICE : 8.7,
						DAYCHANGERATE : -0.1148,
						TURNOVERVAL : 126109959,
						BVPS : 10.01,
						DAYCHANGE : -0.01,
						PE : 6.4621,
						TURNOVERRATE : 0.29,
						ADJUSTCLOSINGPRICE : 16.1875,
						PB : 0.9326
					}, {
						AMPLITUDE : 1.0135,
						PREVCLOSINGPRICE : 5.92,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 5.94,
						TURNOVERVOL : 47828421,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 28365585227,
						SECUCODE : "600016",
						EPS : 0.3433,
						LOWESTPRICE : 5.88,
						OPENINGPRICE : 5.91,
						SECUABBR : "民生银行",
						ALISTEDSHARE : 22587602387,
						ID : 3132062,
						WCOSTAVG : 6.1797,
						NETCASHFLOWOPERPS : -1.61,
						SECUNAME : "中国民生银行股份有限公司",
						CLOSINGPRICE : 5.91,
						DAYCHANGERATE : -0.1689,
						TURNOVERVAL : 282428002,
						BVPS : 5.2,
						DAYCHANGE : -0.01,
						PE : 6.0043,
						TURNOVERRATE : 0.2117,
						ADJUSTCLOSINGPRICE : 83.8993,
						PB : 1.2936
					}, {
						AMPLITUDE : 1.8657,
						PREVCLOSINGPRICE : 2.68,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 2.72,
						TURNOVERVOL : 2086859,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 3075653888,
						SECUCODE : "600017",
						EPS : 0.1249,
						LOWESTPRICE : 2.67,
						OPENINGPRICE : 2.69,
						SECUABBR : "日照港",
						ALISTEDSHARE : 2630631660,
						ID : 3133407,
						WCOSTAVG : 3.0421,
						NETCASHFLOWOPERPS : 0.122,
						SECUNAME : "日照港股份有限公司",
						CLOSINGPRICE : 2.71,
						DAYCHANGERATE : 1.1194,
						TURNOVERVAL : 5634455,
						BVPS : 2.674,
						DAYCHANGE : 0.03,
						PE : 17.342,
						TURNOVERRATE : 0.0793,
						ADJUSTCLOSINGPRICE : 8.5585,
						PB : 1.2871
					} ];

					var fixed2 = function(val) {
						if (typeof val != 'number') {
							return '';
						}
						return val.toFixed(2);
					}
					var cols = [
							{
								title : '序号',
								name : 'id',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '用户姓名',
								name : 'yhName',
								width : 100,
								sortable : true,
								align : 'center'
							},

							{
								title : '小区名称',
								name : 'xqName',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '楼栋号',
								name : 'buildNo',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '单元号',
								name : 'cellNo',
								width : 100,
								sortable : true,
								align : 'center'
							},

							{
								title : '户号',
								name : 'houseNo',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '阀门状态',
								name : 'status',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '阀门开度',
								name : 'famKd',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '室内温度',
								name : 'roomTemp',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '管道温度',
								name : 'valTemp',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '更新时间',
								name : 'recordTime',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '阀门地址',
								name : 'valAd',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '传感器地址',
								name : 'wCAd',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '锁定标识',
								name : 'lockSt',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '缴费标识',
								name : 'jFSt',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '调节周期',
								name : 'hTemSet',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '设定温度',
								name : 'mTemSet',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '调节参数',
								name : 'lTemSet',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '区管ID',
								name : 'qgID',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '缴费状态',
								name : 'sFJF',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '欠费状态',
								name : 'sFQF',
								width : 100,
								sortable : true,
								align : 'center'
							},
							{
								title : '停热状态',
								name : 'sFTR',
								width : 100,
								sortable : true,
								align : 'center'
							}
							
							];

					var mmg = $('.mmg').mmGrid({
						height : 280

						,
						cols : cols,
						url : '../run/selectFm.jspx',
						method : 'get',
						remoteSort : true,
						items : items,
						sortName : 'id',
						sortStatus : 'asc',
						multiSelect : true,
						checkCol : true,
						fullWidthRows : true,
						autoLoad : false,
						plugins : [ $('#pg').mmPaginator({}) ]/*,
						params : function() {
							// 如果这里有验证，在验证失败时返回false则不执行AJAX查询。
							return {
								secucode : $('#secucode').val()
							}
						}*/
					});

					mmg.on(
							'cellSelected',
							function(e, item, rowIndex, colIndex) {
								console.log('cellSelected!');
								console.log(this);
								console.log(e);
								console.log(item);
								console.log(rowIndex);
								console.log(colIndex);
								// 查看
								if ($(e.target).is('.btn-info, .btnPrice')) {
									e.stopPropagation(); // 阻止事件冒泡
									alert(JSON.stringify(item));
								} else if ($(e.target).is('.btn-danger')
										&& confirm('你确定要删除该行记录吗?')) {
									e.stopPropagation(); // 阻止事件冒泡
									mmg.removeRow(rowIndex);
								}
							}).on('loadSuccess', function(e, data) {
						// 这个事件需要在数据加载之前注册才能触发
						console.log('loadSuccess!');
						console.log(data);
						console.log(mmg.rowsLength());
					}).on('rowInserted', function(e, item, index) {
						console.log('rowInserted!');
						console.log(item);
						console.log(index);
						console.log(mmg.rowsLength());
					}).on('rowUpdated', function(e, oldItem, newItem, index) {
						console.log('rowUpdated!');
						console.log(oldItem);
						console.log(newItem);
						console.log(index);
					}).on('rowRemoved', function(e, item, index) {
						console.log('rowRemoved!');
						console.log(item);
						console.log(index);
						console.log(mmg.rowsLength());
					}).load();

					var item1 = {
						AMPLITUDE : 2.069,
						PREVCLOSINGPRICE : 2.9,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 2.95,
						TURNOVERVOL : 2511165,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 2000000000,
						SECUCODE : "100000",
						EPS : 0.0266,
						LOWESTPRICE : 2.89,
						OPENINGPRICE : 2.9,
						SECUABBR : "新加股票",
						ALISTEDSHARE : 2000000000,
						ID : 3131903,
						WCOSTAVG : 3.5362,
						NETCASHFLOWOPERPS : -0.4041,
						SECUNAME : "东风汽车股份有限公司",
						CLOSINGPRICE : 2.9,
						DAYCHANGERATE : 0,
						TURNOVERVAL : 7316381,
						BVPS : 3.0533,
						DAYCHANGE : 0,
						PE : 12.4963,
						TURNOVERRATE : 0.1256,
						ADJUSTCLOSINGPRICE : 10.0741,
						PB : 0.9581
					};
					var item2 = {
						AMPLITUDE : 0.7389,
						PREVCLOSINGPRICE : 12.18,
						TURNOVERDEALS : 0,
						HIGHESTPRICE : 12.22,
						TURNOVERVOL : 1332194,
						TRADINGDAY : 1345478400000,
						TOTALSHARE : 1926958448,
						SECUCODE : "100001",
						EPS : 0.1909,
						LOWESTPRICE : 12.13,
						OPENINGPRICE : 12.19,
						SECUABBR : "新加股票2",
						ALISTEDSHARE : 1093476397,
						ID : 3131171,
						WCOSTAVG : 12.8369,
						NETCASHFLOWOPERPS : -0.04,
						SECUNAME : "上海国际机场股份有限公司",
						CLOSINGPRICE : 12.14,
						DAYCHANGERATE : -0.3284,
						TURNOVERVAL : 16207539,
						BVPS : 8.16,
						DAYCHANGE : -0.04,
						PE : 15.5997,
						TURNOVERRATE : 0.1218,
						ADJUSTCLOSINGPRICE : 33.1878,
						PB : 1.523
					};

					$('#btnAdd').on('click', function() {
						mmg.deselect('all');
						mmg.addRow(item1, 2);
						mmg.select(2);
					});

					$('#btnAddArr').on('click', function() {
						mmg.deselect('all');
						mmg.addRow([ item1, item2 ], 1);
						mmg.select(function(item, index) {
							if (index === 1 || index === 2) {
								return true;
							}
						});
					});

					$('#btnRemove').on('click', function() {
						mmg.removeRow(1);
					});
					$('#btnRemoveAll').on('click', function() {
						mmg.removeRow();
					});

					$('#btnRemoveSelected').on('click', function() {
						var selectedIndexes = mmg.selectedRowsIndex();
						mmg.removeRow(selectedIndexes);
					});

					$('#btnUpdate').on('click', function() {
						var updateItem = mmg.row(1);
						if (updateItem) {
							mmg.updateRow(item1, 1);
						}

					});

					$('#btnSearch').on('click', function() {
						// 点击查询时页码置为1
						mmg.load({
							page : 1
						});
					});

				});// JavaScript Document