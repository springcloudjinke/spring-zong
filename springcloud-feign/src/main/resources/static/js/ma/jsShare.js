define(["jquery"],function($){
	
	//分享按钮组最外层
	var $shar_box;
	//微信分享按钮
	var $wx;
	
	var init=function(config){
		var _conf=$.extend({
			url:"",//分享的地址
			title:"",//分享的标题
			pic:"",//分享的图片
			qrUrl:""//微信分享的二维码图片url
		},config);
		initShare();
		shareToSina(_conf);
		shareToQQ(_conf);
		shareToQQweixin(_conf);
	}
	
	//初始化课程分享
	var initShare=function(){
		$shar_box=$(".course-share-icon")
		$wx_shareBtn = $shar_box.find(".share-wx");
		var $wx_box = $shar_box.find('.share-weixin-code');
		var $wrap = $shar_box.find(".wx-box");
		$wx_shareBtn.on("click",function(){
			
			$wx_box.toggleClass("active");
			var hidden = $wx_box.is(":hidden");
			if (hidden) {
				$(document).off("click");
			} else { // show
				$(document).on("click", function(event) {
					var target = event.target;
					if (!$wrap.has(target)[0]) {
						$wx_box.removeClass("active");
						$(document).off("click");
					}
				});
			}
			return false;
			
		});
	}
	
	//分享至新浪微博
	var shareToSina=function(config){
		if (!config) {
			config={};
		}
		var _config={
//			count:"",//表示是否显示当前页面被分享数量(1显示)(可选，允许为空)
			url:config.url,//将页面地址转成短域名，并显示在内容文字后面。(可选，允许为空)
//			appkey:"",//用于发布微博的来源显示，为空则分享的内容来源会显示来自互联网。(可选，允许为空)
			title:config.title,//分享时所示的文字内容，为空则自动抓取分享页面的title值(可选，允许为空)
			pic:config.pic,//自定义图片地址，作为微博配图(可选，允许为空)
//			ralateUid:"",//转发时会@相关的微博账号(可选，允许为空)
			language:"zh_cn"//语言设置(zh_cn|zh_tw)(可选)
		}
		
		var str="http://service.weibo.com/share/share.php?";
		
//		str+=_config.count==""?"":"count="+escape(_config.count)+"&";
		str+=_config.url==""?"":"url="+escape(_config.url)+"&";
//		str+=_config.appkey==""?"":"appkey="+escape(_config.appkey)+"&";
		str+=_config.title==""?"":"title="+_config.title+"&";
		str+=_config.pic==""?"":"pic="+escape(_config.pic)+"&";
//		str+=_config.ralateUid==""?"":"ralateUid="+escape(_config.ralateUid)+"&";
		str+=_config.language==""?"":"language="+escape(_config.language);
		
		$shar_box.find(".share-wb").on("click",function(){
			window.open(str);
		});
	}
	
	//分享至QQ好友
	var shareToQQ=function(config){
		if (!config) {
			config={};
		}
		var _config=$.extend({
			url:config.url,//分享的网址
//			desc:"",//默认分享理由(可选)
			title:config.title,//分享标题(可选)
			pics:config.pic,//分享图片的路径(可选)
//			summary:"",//分享摘要(可选)
			site:"爱课程",//分享来源 如：腾讯网(可选)
		},config);
		
		var str="http://connect.qq.com/widget/shareqq/index.html?";
		str+=_config.url==""?"":"url="+escape(_config.url);
		str+=_config.title==""?"":"&title="+_config.title;
		str+=_config.pics==""?"":"&pics="+escape(_config.pics);
//		str+=_config.desc==""?"":"&desc="+_config.desc;
//		str+=_config.summary==""?"":"&summary="+_config.summary;
		str+=_config.site==""?"":"&site="+_config.site;
		$shar_box.find(".share-qq").on("click",function(){
			window.open(str);
		});
	}
	
	//分享至微信
	var shareToQQweixin=function(config){
		$wx_shareBtn.on("click",function(){
			var urls=$shar_box.find("img").attr("src");
			if (config.qrUrl!=urls) {
				$shar_box.find("img").attr("src",config.qrUrl);
			}
		});
	}
	
	return init;
});
