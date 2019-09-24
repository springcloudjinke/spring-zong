define(["SSO"], function(SSO) {

	var comObj = {} 
	
	var ssoObj = SSO();//登录验证;
	
	comObj.validateLogin = function(funcObj) {
		ssoObj.isLogin({
			success: function(user) {
				funcObj();
			},
			error: function() {
				ssoObj.login({
				});
			}
		}); 
	}	
	 
	
	return comObj;
});