define(["jquery", "Bootstrap"], function() {
	
	var noRight=function(id){
		$("#"+id).on("selectstart",function(e){
			if (e.which=="3") {
				return false;
			}
		});
		$("#"+id).on("contextmenu",function(e){
			if (e.which=="3") {
				return false;
			}
		});
	}
	return {
		noRight:noRight
	}
});