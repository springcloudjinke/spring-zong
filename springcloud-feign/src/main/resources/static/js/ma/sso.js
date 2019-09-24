define(["jquery"], function() {
	
	function init() {
		return new SSO();
	}
	
	function SSO() {
		var _this = this;
		
		// 验证登录地址
		_this.isLoginUrl = getServer() + "sword/sso/ssoIsLogin?1=1";
		// 登录地址
		_this.loginUrl = getServer() + "/sword/sso/ssoLogin?1=1&prefix=portalWebMiniLogin";//登录页请求参数，大登录页不需要设置。
		// 退出地址
		_this.loginoutUrl = getServer() + "/sword/sso/ssoLoginout?1=1";
	}
	
	// 是否登录
	SSO.prototype.isLogin = function(config) {
		var _this = this;
		
		var loginSuccess = config.success;
		var loginError = config.error;
		$.ajax({
			url: _this.isLoginUrl,
			dataType: "jsonp",
			jsonp: "callback",
//			jsonpCallback: "jsonpCallback",
			jsonpCallback: SSO.getRandomFunction(),
			crossDomain: true,
			success: function(user) {
				if (user && user.userId) { // 退出成功
					typeof loginSuccess == "function" && loginSuccess(user);
				} else {
					typeof loginError == "function" && loginError();
				}
			},
			error: function() {
				typeof loginError == "function" && loginError();
			}
		});
	}	
	
	// 登录
	SSO.prototype.login = function(config) {
		var _this = this;
		// 成功回调
		var loginSuccess = config.success;
		
		// 判断登录框是否存在
		var $loginModal = $("#loginModal");
		if (!$loginModal[0]) {
			$loginModal = $(loginModal).appendTo($(document.body));
		}
		
		// 回调
		var iframeCallbackName = "_loginSuccess";
//		window[iframeCallbackName] = function(user) {
//			$loginModal.modal("hide");
//			typeof loginSuccess == "function" && loginSuccess(user);
//			window.location.reload();
//		}
$(window).on("message", function(event) {
   var messageEvt = event.originalEvent;
   console.log(messageEvt)
   $loginModal.modal("hide");
   typeof loginSuccess == "function" && loginSuccess(messageEvt);
   window.location.reload();
  });
		
		var loginUrl = _this.loginUrl + "&callback=" + iframeCallbackName;
		
		SSO.createIframe.call(_this, loginUrl, $loginModal.find(".modal-body"));
		
		$loginModal.modal('show');
	}
	
	// 退出
	SSO.prototype.loginout = function(config) {
		var _this = this;
		
		var loginoutSuccess = config.success;
		var loginoutError = config.error;
		$.ajax({
			url: _this.loginoutUrl,
			dataType: "jsonp",
			jsonp: "callback",
			//jsonpCallback: "jsonpCallback",
			jsonpCallback: SSO.getRandomFunction(),
			crossDomain: true,
			success: function(user) {
				if (!user || !user.userId) { // 退出成功
					typeof loginoutSuccess == "function" && loginoutSuccess();
				} else {
					typeof loginoutError == "function" && loginoutError();
				}
				window.location.reload();
			}, error: function() {
				typeof loginoutSuccess == "function" && loginoutSuccess();
			}
		});
	}
	
	SSO.getRandomFunction = function() {
		var random = Math.ceil((1000000 - 10000) * Math.random() + 10000);
		return "jsonpCallback" + random;
	}
	
	SSO.createIframe = function(src, $parent) {
		$parent.html('<iframe src="' + src + '" height="100%" width="100%" frameBorder="0"></iframe>');
	}
	
	var loginModal = 
		'<!-- 登录 -->' +
		'<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
			'<div class="modal-dialog">' +
				'<div class="modal-content">' +
					'<div class="modal-header" style="border-bottom: 0px solid #e5e5e5;">' +
						'<button type="button" class="close" data-dismiss="modal">' +
							'<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
						'</button>' +
//						'<h4 class="modal-title" id="myModalLabel">请登录</h4>' +
					'</div>' +
					'<div class="modal-body" style="height:450px;padding: 0px;"></div>' +
					/*
					'<div class="modal-footer">' +
						'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
					'</div>' +
					*/
				'</div>' +
			'</div>' +
		'</div>';
	
	return init;
});
