require.config({
	baseUrl: getStaticServer() + "/icourse",
	paths: {
		// lib
		"jquery": "lib/jquery/jquery-2.1.4.min",
		"Bootstrap": "lib/bootstrap/js/bootstrap.min",
		"Ckeditor":"lib/ckeditor/ckeditor",
		"Mustache":"lib/mustache/mustache.min",
		"JQuery.validate" :  "lib/jquery/plugins/validate/jquery.validate.min",
		"JQuery.validate.extra" :  "lib/jquery/plugins/validate/additional-methods",
		"JQuery.validate.message" :  "lib/jquery/plugins/validate/localization/messages_zh",
		"Handlebars" : "lib/Handlebars/handlebars-v4.0.5",
		//videojs
		"VideoJs": "lib/videojs/video",
		"VideoJsLang": "lib/videojs/lang/zh-CN",
		"VideoPlay":"page/common/js/videoPlay",
		"FileUtil":"page/common/js/fileUtil",
		// lib self
		"Router": "page/common/js/router",
		"SSO": "page/common/js/sso",
		"BBSCommon": "page/common/js/bbsCommon",
		
		// alias
		// common
		"Base": "page/common/js/base",												// 基础
		"Common": "page/common/js/common",											// 通用
		"CommonHeaderCarousel": "page/common/js/header_carousel",					// header轮播图
		"CommonHeaderLogin": "page/common/js/header_login2",						// header登录条
		"CommonFixedToolBar": "page/common/js/icourse_right_tools",					// 固定工具条
		"CommonIcourseSortItem": "page/common/js/icourse_sort_item",				// icourseItem效果
		"CommonJsonSubjectLevel": "page/common/js/jsonSubjectLevel",				// 资源共享课和资源共享课搜索所用课程分类
		// home
		"Home": "page/home/home",
		//courseShare
		"CourseShare": "page/courseShare/courseShare",
		//courseShareIntroduction
		"CourseShareIntroduction": "page/courseShareIntroduction/courseShareIntroduction",
		//courseUniversity
		"CourseUniversity": "page/courseUniversity/courseUniversity",
		"CourseGold":"page/goldCourse/courseGold",
		//courseVideo
		"CourseVideo": "page/courseVideo/courseVideo",
		//courseVideoDetails
		"CourseVideoDetails": "page/courseVideoDetails/courseVideoDetails",
		//teacherTeam
		"TeacherTeam":"page/teacherTeam/teacherTeam",
		//teacherDetails
		"TeacherDetails":"page/teacherDetails/teacherDetails",
		//register
		"Register":"page/user/register/registerCtrl",
		"RegisterLoad":"page/user/register/registerLoadCtrl",
		//findPwd
		"FindPwd":"page/user/findPwd/findPwdCtrl",
		"FindPwdLoad":"page/user/findPwd/findPwdLoadCtrl",
		"CourseCenter":"page/courseCenter/courseCenter",
		"CourseEducation":"page/courseEducation/courseEducation",
		"CourseOpen":"page/courseOpen/courseOpen",
		// courseShareDetails
		"CourseShareDetails": "page/courseShareDetails/courseShareDetails",
		//courseVideoBBS
		"CourseVideoBBS": "page/courseVideoDetails/courseVideoBBS",
		"Play":"page/common/js/play",
		"JsShare":"page/common/js/jsShare",
		"Dialog":"page/common/js/dialog",
	    //search
		"Search":"page/common/js/search",
		"HomeSearch":"page/homeSearch/homeSearch",
		"TeacherAttestation":"page/teacherAttestation/teacherAttestation",
		"JsonProvinceSchool":"page/teacherAttestation/jsonProvinceSchool",
		"MoocChannel":"page/moocChannel/moocChannel",
		"MoocGraduateChannel":"page/moocGraduateChannel/moocGraduateChannel",
		//个人中心相关
		"UserCenter": "page/userCenter/userCenter",	
		"UserCenterInfo": "page/userCenter/info",            // 个人中心--个人信息
		"UserCenterPwd": "page/userCenter/pwd",                  // 个人中心--密码修改
		"UserCenterCourse": "page/userCenter/course",          // 个人中心--我的课程
		"UserCenterAttestation": "page/userCenter/attestation", 	 // 个人中心--教师认证
		"UserCenterMaster":"page/userCenter/teacherCourse",
		"SourcePlay" : "page/rp/sourcePlay",   //资源播放
		"TickerTapeTime":"page/common/js/tickerTapeTime",//打点计时
		"HepConfig":"page/common/js/hepConfig",  //配置文件加载
		//最美MOOC评选
		"CourseVoteDir":"page/courseVote"
	},
	shim: {
		"Bootstrap": ["jquery"],
		"VideoJsLang": ["VideoJs"]
	}
})