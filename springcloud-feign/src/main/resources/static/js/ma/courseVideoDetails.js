define(["JsShare",
        "SSO",
        "VideoJs", 
        "Router",
        "CourseVideoBBS", 
        "VideoPlay",
        "Dialog",
        "TickerTapeTime",
        "jquery", 
        "VideoJsLang"], function(JsShare,SSO,VideoJs, Router,
        		CourseVideoBBS,VideoPlay,Dialog,TickerTapeTime){

	// 资源集合
	var sourceArr = [];
	// 当前资源
	var current;
	var courseId=_courseId;
	// 播放器
	var player;
	
//	var video;
	// sso
	var sso = SSO();
	
	var init=function() {
		
		initSourceArr();//从页面上拿到资源集合
		initRouter();//初始化router
		CourseVideoBBS();
		initSSO();//初始化SSO
		initRec();//初始化课程推荐
		bindActive();//绑定视频切换事件
		
		//课程分享相关
		var url=getQrUrl();
		JsShare({
			url:_shareUrl,
			title:"我正在学习爱课程视频公开课《"+_courseTitle+"》，推荐给大家！",
			pic:_totalPicUrl,
			qrUrl:url
//			qrUrl:getServer()+"/icourse/page/imgs/app_code.png"
		});
	}
	
	//二维码URL拼装
	function getQrUrl(){
		var url="http:" + _shareUrl;
		var totalUrl=getServer()+ "/sword/portal?SwordControllerName=portal/weChatVideoShare&url="+url;
		return totalUrl;
	}
	
	//获取课程推荐
	var initRec=function(){
		$.ajax({
			url:getServer()+"/sword/portal/getVideoRec",
			type:"post",
			dataType:"html",
			success:function(data){
				$("#videoRecDiv").append(data);
			}
		});
	}
	
	//初始化SSO
	var initSSO=function(){
		// 判断是否登录
		sso.isLogin({
			success: function(user) {
				if (isAddStudy(user)) {
					$(".video-other .icon-item").removeClass("lock");
				}
			}
		});
	}
	
	//SSO登录调用
	var ssoLogin=function(fun){
		sso.login({
			success: function(user) {
				//登录成功，并已加入学习，则删除锁
				if (isAddStudy(user)) {
					$(".video-other .icon-item").removeClass("lock");
				}
			}
		});
	}
	
	//是否加入学习判断逻辑
	var isAddStudy=function(user){
		var is=false;
		$.ajax({
			url:getServer()+"/sword/portal/video/isJoin",
			type:"post",
			dataType:"json",
			data:{
				"courseId":_courseId,
				"userId":user.userId
				},
			async:false,
			success:function(data){
				is=data.model.data.isJoin;
			}
		});
		
		return is;
	}
	
	//判断是否需要加入学习
	var joinToStudy=function(user){
		if (confirm("加入课程后会保存你的学习记录,是否加入学习？")) {
			addJoin(user);
		}
//		Dialog.confirm("加入课程后会保存你的学习记录,是否加入学习？",function(){
//			addJoin(user);
//		});
	}
	
	//到后台加入学习
	var addJoin=function(user){
		$.ajax({
			url:getServer()+"/sword/portal/video/addJoin",
			type:"post",
			dataType:"json",
			data:{"courseId":_courseId,"userId":user.userId},
			success:function(data){
				if (data.model.success===true) {
					$(".video-other .icon-item").removeClass("lock");
				}
			}
		});
	}
	
	//资源切换事件
	var bindActive=function(){
		$(".video-other").off("click",".icon-item").on("click",".icon-item",function(){
			$(".video-other .icon-item").removeClass("active");
			$(this).addClass("active");
			
			if ($(".video-other .icon-item.active").hasClass("lock")==true) {
				sso.isLogin({
					//已登录，则判断是否已经加入学习
					success:function(user){
						//已加入学习则判断当前资源是否存在
						if (isAddStudy(user)===true) {
						//未加入学习，则提示是否加入学习
						}else {
							joinToStudy(user);
						}
					},
					//未登录，弹出登录框
					error: function() {
						ssoLogin();
					}
				});
			}
		});
	}
	
	//给视频播放按钮绑定正常播放事件
	function bindPlayBtn(player) {
//		$(".hep-video-play-btn").on("click", function() {
		//选中视频是否带锁，不带锁直接播放，带锁则先判断是否登录
		if ($(".video-other .icon-item.active").hasClass("lock")==true) {
			sso.isLogin({
				//已登录，则判断是否已经加入学习
				success:function(user){
					//已加入学习则判断当前资源是否存在
					if (isAddStudy(user)===true) {
						// 判断当前资源是否存在，存在则直接播放
						if (current) {
							if (!player.src()) {
								player.src(getSourceVideoSrc(current));
							}
							player.play();
						}else {
							alert("资源不存在!");//Dialog.
						}
					//未加入学习，则提示是否加入学习
					}else {
						joinToStudy(user);
					}
				},
				//未登录，弹出登录框
				error: function() {
					ssoLogin();
				}
			});
		}else {
			if (current) {
				if (!player.src()) {
					player.src(getSourceVideoSrc(current));
				}
				player.play();
			}
		}
//		});
	}
	
	//初始化数据，获取source数据
	function initSourceArr() {
		if (_sourceArrStr&&_sourceArrStr!=null) {
			//转换为json对象
			sourceArr=_sourceArrStr;
		}
	}
	
	//初始化路由
	function initRouter() {
		routerObj = Router({
			onRouter: function(router, param) {
				if (param && validateRes(param.resId)) {
					loadSource(router, param);
				} else {
					routerObj.setRouter("/", {
						resId: sourceArr[0].id
					});
				}
			}
		});
	}
	
	//验证资源有效性
	function validateRes(resId) {
		if (resId != null) {
			var resObj = getSourceById(resId);
			if (resObj != null) return true;
		}
		return false;
	}
	
	//初始化播放器
	function initPlayer() {
		player=VideoPlay({
			autoplay:false,
			countId:"video",
			src:"",
			"onPlayBtn":function(){
				bindPlayBtn(player);
			}
		});
//		player.poster("http://210.14.140.4/static1/video/vcourseimg/videoPic/201306/b8fdfce217eb527d781c7a3a2c17d45e.jpg")
	}
	
	//路由切换执行事件
	function loadSource(router, param) {
		//绑定所有其他视频，切换active
		$(".video-other .icon-item").removeClass("active");
		$(this).addClass("active");
		//获取resId
		var resId = param["resId"];
		
		//绑定所有其他视频，切换active
		$(".video-other .icon-item").removeClass("active");
		$("#source-"+resId).addClass("active");
		$("#sourceIndex").html($("#source-"+resId).data("index"));
		
		// 记录当前
		current = resId;
		initPlayer();//初始化播放器
		
		// 重置player
		resetPlayer();
		
		//根据resId获取资源
		var item = getSourceById(resId);
		//改变本讲介绍
		$("#source-description").text(item.description);
		//改变当前讲数
		$("#source-index").text(item.index);
		//改变时长(转换数据格式）
		var duration = formatTime(item.duration)
		$("#source-duration-hour").text(duration[0]);
		$("#source-duration-minute").text(duration[1]);
		$("#source-duration-second").text(duration[2]);
		
		//改变顶部面包屑二级文字
		$("#source-title").text(item.title);
		player.src(getSourceVideoSrc(current));
		
		TickerTapeTime.tickerVideo({
			url:getServer()+"/sword/portal/ticker/tickerVideo",//打点记录保存后台地址
			player:player,//播放器
			courseId:courseId,//课程id
			resId:current,//资源id
			courseType:"video",//课程类型
			intervalTime:30,//打点间隔时间（秒）
			serverDataName:"tickerData",//后台接收数据key默认为tickerData
			onTicker:function(played){//打点时回调（played为当前视频已看的对象，请自行console查看格式）
				
			}
		});
	}
	
	// 重置player
	function resetPlayer() {
		//重置完播放，显示播放按钮
		$(".hep-video-modal").removeClass("hide");
		player.reset();
	}
	
	//根据resID获取资源
	function getSourceById(resId) {
		if (resId) {
			for (var i = 0; i < sourceArr.length; i++) {
				if (sourceArr[i].id==resId) {
					return sourceArr[i];
				}
			}
		}
		return null;
	}
	
	function getSourceVideoSrc(resId) {
		var source=getSourceById(resId);
		return source.fullLinkUrl;
	}
	
	//格式化时间
	function formatTime(time){
		if(time){
			time = time.substring(0,8).split(":");
		}else{
			time = [0,0,0];
		}
		$.each(time, function(index,element){
			if(element[0] === "0"){
				element = element[1];
			}
			time[index] = element;
		});
		return time;
	}
	return init;
});
