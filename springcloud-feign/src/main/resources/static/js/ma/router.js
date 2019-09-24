define([], function() {
	
	function init(options) {
		return new Router(options);
	}
	
	function Router(options) {
		var _this = this;
		
		var _options = {
			defaultRouter: null,
			event: {
				onRouter: null
			}
		}
		
		this._options = $.extend(true, _options, options);
		this._$this = $(document.body);
		
		Router.init.call(_this);
	}
	
	Router.init = function() {
		var _this = this,
			_$this = _this._$this,
			options = _this._options;
		
		Router.bindEvent.call(_this);
		
		var router = _this.getRouter();
		setTimeout(function() {
			//if (router) {
				_$this.trigger("routerChange", [_this.getRouter(), _this.getRouterParam()]);
			//} else {
				//options.defaultRouter && _this.setRouter(options.defaultRouter);
			//}
		}, 30);
	}
	
	Router.bindEvent = function() {
		var _this = this,
			_$this = _this._$this,
			options = _this._options,
			onRouter = options.onRouter;
		
		_$this.on("routerChange", function(e, router, param) {
			typeof onRouter == "function" && onRouter(router, param);
		});
		
		$(window).on("hashchange", function() {
			_$this.trigger("routerChange", [_this.getRouter(), _this.getRouterParam()]);
		});
		
	}
	
	Router.prototype.setRouter = function(router, param) {
		var _this = this;
		
		var paramStr = "";
		if (param) {
			for (var key in param) {
				paramStr = paramStr.concat(key + "=" + param[key] + "&");
			}
			if (paramStr) {
				paramStr = paramStr.substr(0, paramStr.length - 1);
				paramStr = "?".concat(paramStr);
			}
		}
		
		window.location.hash = router + paramStr;
	}
	
	Router.prototype.getHash = function() {
		var _this = this;
		
		var hash = window.location.hash;
		
		if (hash) {
			// #
			hash = hash.substr(1);
			
			var result = {};
			
			var router = hash.split("?")[0];
			var query = hash.split("?")[1];
			var param = {};
			
			if (query) {
				var queryArr = query.split("&");
				for (var i=0; i<queryArr.length; i++) {
					param[queryArr[i].split("=")[0]] = queryArr[i].split("=")[1];
				}
			}
			
			result.router = router;
			result.param = param;
			
			return result;
		}
		
		return null;
	}
	
	Router.prototype.getRouterParam = function() {
		var _this = this;
		
		var hash = _this.getHash();
		if (hash) {
			return hash.param;
		}
		
		return {};
	}
	
	Router.prototype.getRouter = function() {
		var _this = this;
		
		var hash = _this.getHash();
		if (hash) {
			return hash.router;
		}
		
		return null;
	}
	
	return init;
});