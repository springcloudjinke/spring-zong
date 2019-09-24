define([ "Ckeditor", "Handlebars","BBSCommon","Bootstrap","jquery" ], function(Ckeditor,Handlebars,BBSCommon) {
	
	//初始化
	function init() {
		initVars();//初始化变量
		getTopicList();//加载视频公开课评论列表
		initEvent();//注册事件
	}
	
	var $discussBtn,$discussContent,$textareaForReply,$textareaForDiscuss;
	var pageSize;
	
	//初始化变量
	function initVars(){
		pageSize = 10;
	    $discussBtn = $(".discuss");//写评论按钮对象
	    $discussContent = $(".discuss-content");//评论列表区域
		$textareaForReply = $(".discuss-textarea-module");//回复内容输入区域
		$textareaForDiscuss = $(".discuss-textarea-module");//评论内容输入区域
	}
	
	var getCVBbsServerUrl = function() {
		return getServer() + '/sword/user/bbs/web/courseVideo/';
	};
	
	//注册事件
	function initEvent() {
		//发布公开课评论
		$discussBtn.on("click", function() {
			var content = $("#discussArea").val();
			if(!content){
				alert("请输入评论内容！");
				return;
			}
			$discussBtn.text("发表中...");
			BBSCommon.validateLogin(saveTopic);
		});
		//展开-回复列表
		$discussContent.on('shown.bs.collapse',".collapse", function () {
			$('.collapse.in').not(this).each(function(){
				 $("#"+this.id).collapse('hide');
			});
			var topicId = this.id.substr(6);
			getReplyList(topicId, $(this));
			$(this).show();//显示回复列表区域
		});
		//收回-回复列表
		$discussContent.on('hide.bs.collapse',".collapse", function () {
			$(this).empty();//清空
			$(this).hide();
		});
		//发表-回复
		$textareaForDiscuss.on("click", "#saveReplyBtn", function() {
			var replyContent = $discussContent.find("#replyContent").val();
			if(!replyContent){
				alert("请输入回复内容！");
				return false;
			}
			var topicId = $(this).parents('.discuss-content-i').data("id");
			var $replyNumSpan =  $(this).parents('.discuss-content-i').find('.reply_num');
			var $otherOutDom = $(this).parents('.reply-netfriend-other-out');
			BBSCommon.validateLogin(function(){//验证是否登录，登录后保存
				saveReply(topicId,$otherOutDom,$replyNumSpan);
			});
		});
		//取消-回复
		$textareaForDiscuss.on("click", "#cancelReplyBtn", function() {
			$discussContent.find("#replyContent").val("");//清空回复输入域
			var topicId = $(this).parents('.discuss-content-i').data("id");
			$("#topic_"+topicId).collapse('hide');//隐藏回复区域
		});
	}
	
	//加载视频公开课评论列表（从主题表中查询）
	function getTopicList(curPage) {
		if(typeof(curPage)=="undefined"){
			curPage = 1;
		}
		$.ajax({
			url : getCVBbsServerUrl() + "getAllTopics",
			data : {
				classId : _courseId,
				curPage : curPage,
				pageSize : pageSize
			},
			type : 'post',
			cache : false,
			dataType : "html",
			success : function(html) {
				$discussContent.html(html);
				var totalRows = $("#totalRows").val();
				if(totalRows=='0'){
					$(".plnum").html(0);//评论总数
					$(".ynum").html(0);//评论页数
					$(".courseVideoPlay-interaction .discuss-content .bbs-larPagination-box").hide();
				}else{
					$(".plnum").html($("#totalRows").val());//评论总数
					$(".ynum").html($("#totalPages").val());//评论页数
				}
			}
		});
	}
	 
	//加载公开课评论的回复列表
	function getReplyList(topicId, $htmlDom) {
		$.ajax({
			url : getCVBbsServerUrl() + 'getAllReplys',
			data : {
				topicId : topicId
			},
			type : 'post',
			cache : false,
			dataType : "html",
			success : function(html) {
				$htmlDom.html(html);
				$htmlDom.find(".reply-netfriend-other-out").html($textareaForReply.clone(true));//将回复框放入div中
				$htmlDom.find(".reply-netfriend-other-out").find(".discuss-textarea-module").show();//显示评论回复输入框
			}
		});
	}
 
	//保存评论信息（保存到主题表）
	function saveTopic() {
		var content = $("#discussArea").val();
		if(!content){
			alert("请输入评论内容！");
			return false;
		}
		var obj = {
			classId : _courseId,
			topicTitle : _courseTitle,//视频公开课，topicTitle存课程名称
			topicContent : content
		}
		$.ajax({
			url : getCVBbsServerUrl() + 'saveTopic',
			data : obj,
			type : 'post',
			cache : false,
			async : false,
			dataType:"json",
			success : function(data) {
				if(data.model){
					if (data.model.success) {
						$("#discussArea").val('');
						var topicInfo = data.model.data;
						topicInfo.lastModifyTime = topicInfo.lastModifyTime.substr(0,16);
						topicInfo.createTime = topicInfo.createTime.substr(0,16);
						var json = {
							topic:topicInfo
						};
						var reviewStatus = topicInfo.reviewStatus
						if(reviewStatus==1 || reviewStatus==3){ // 1-草稿 3-审核通过
							var mainHtml = $("#topicTemplate").html();//评论内容模板
							var template = Handlebars.compile(mainHtml);
							var views = template(json);
							
							$discussContent.find("#discussUl").prepend(views);//将评论信息拼接到ul里
							$(".plnum").html((parseInt($(".plnum").html())+1));//评论总数
							$(".bbs-noContent").hide();
						}else{
							alert(data.model.message);
						}
					}else{
						alert(data.model.message);
					}
				}else{
					alert(data.msg);
				}
				$discussBtn.text("写评论");
			}
		});
	}
	
	//保存评论的回复信息(保存到回复表中)
	function saveReply(id,$htmlDom,$replyNumSpan){
		var replyContent = $discussContent.find("#replyContent").val();
		if(!replyContent){
			alert("请输入回复内容！");
			return false;
		}
		var reply = {
				topicId:id,
				isAnonymous:'N',
				replyContentInfo:replyContent
		}
		var replyContentInfo = {
				replyContent : replyContent
			}
		var obj = {
				classId : _courseId,
			    replyContentInfo : replyContentInfo,
				replyInfo:reply
		}
		$.ajax({
			url : getCVBbsServerUrl() + "saveReply",
			data : obj,
			type : 'post',
			cache : false,
			async : false,
			dataType : "json",
			success : function(data) {
				if(data.model){
					if (data.model.success) {
							var replyInfo = data.model.data.replyInfo;
					        var reply = {};
					        reply =  replyInfo;
							reply.replyInfo.lastModifyTime = replyInfo.replyInfo.lastModifyTime.substr(0,16);
							reply.replyInfo.createTime = replyInfo.replyInfo.createTime.substr(0,16);
							
					        var reviewStatus = replyInfo.replyInfo.reviewStatus
							if(reviewStatus==1 || reviewStatus==3){ // 2-未审核 3-审核通过
							    var mainHtml = $("#replyTemplate").html();//获取模板内容
						        var template = Handlebars.compile(mainHtml);
								var views = template(reply);
								$htmlDom.after(views);//将新增的回复信息拼接到回复输入框下方
								
								$replyNumSpan.html((parseInt($replyNumSpan.html())+1));//回复数+1
							}else{
								alert(data.model.message);
							}
					        $discussContent.find("#replyContent").val("");//清空回复框内容
					}else{
						alert(data.model.message);
					}
				}else{
					alert(data.msg);
				}
			}
		});
	}
	
	
	window.skipToPage = function(page) {//注册分页方法
		var totalPages  = parseInt($("#totalPages").val());
		page = $.trim(page);
		if(!isNaN(page)){//输入为数字
			var currentPageNum  = parseInt(page);
			if(currentPageNum<1 || currentPageNum>totalPages){//判断输入值是否为数字，且在合法范围内
				alert('请输入正确页码！');
				return;
			}
		}else{//输入不为数字
			alert('请输入正确页码！');
			return;
		}
		getTopicList(page);
	}
	
	return init;
});