define([ "VideoJs", "VideoJsLang" ],
		function(VideoJs) {

			function init(config) {
				return new Videos(config).player;
			}

			function Videos(config) {
				var _this = this;
				var _config = {
					"countId" : "",//video标签id
					"src" : "",//默认资源路径
					"controls" : true,// 是否控制视频，默认为true ，改为false无法控制视频播放
					"autoplay" : false,// 是否自动播放
					"preload" : "auto",// 是否预加载
					"language" : "zh-CN",//语言类型
					"loop" : false,// 是否循环
					"inline" : true,// 音量条是否在同一行
					"vertical" : true,// 是否旋转音量条
					"fullscreenToggle" : true,// 全屏按钮是否显示
					"onBeforePlay" : function() {},// 播放之前
					"onPlayBtn" : function(){},//点击播放按钮触发
					"onPlay" : function() {},// 播放时触发
					"onPause" : function() {},// 暂停时触发
					"onEnd" : function() {},// 结束时触发
					"endedBar" : {//播放完毕按钮组
						"isShowNext" : false,// 如果设置false 结束时不显示重新播放按钮
						"isShowReplay" : false,// 如果设置false 结束时不显示重新播放按钮
						"onPlayNext" : function() {},// 播放下一个触发
						"onBeforeReplay" : function() {}// 从新播放前触发
					}
				};
				if (!config) {
					config = {};
				}
				this._config = $.extend(_config, config);
				Videos.init.call(_this);
			}

			Videos.init = function() {
				
				var _this = this, 
					_config = _this._config, 
					_$this = _this._$this;
					_this.player = "";
					
//					_this.player = VideoJs(_config.countId, {
//						  playbackRates: [0.5, 1, 1.5, 2]
//					});
					
				_this.player = VideoJs(_config.countId, {
//					"techOrder": ["flash","html"],
					"controls" : _config.controls,// 是否控制视频，默认为true
													// ，改为false无法控制视频播放
					"autoplay" : _config.autoplay,// 是否自动播放
					"preload" : _config.preload,// 是否预加载
					"language" : _config.language,
					"loop" : _config.loop,// 是否循环
					playbackRates : [1, 1.5, 2,2.5,3],
					controlBar : {// 控制栏
						progressControl : true,// 是否显示进度条
						currentTimeDisplay : true,//当前时间
						timeDivider:true,//分隔符
						durationDisplay :true,//总时长
						remainingTimeDisplay : false,// 是否显示剩余时间
//						captionsButton : true,// 标题按钮
//						chaptersButton : true,// 章按钮
//						playbackRateMenuButton : true,// 返回率菜单按钮
//						LiveDisplay : true,// 现场显示
//						subtitlesButton : true,// 字母按钮
						volumePanel: {
						      inline: false
						},
						fullscreenToggle : _config.fullscreenToggle,// 全屏按钮是否显示
//						TimeDivider:false
					}
				});
				
				_this.player.src(_config.src);

				$(".vjs-big-play-button").remove();
				
				setTimeout(function() {
					if (_config.autoplay==true) {
						_this.player.src(_config.src);
						_this.player.play();
					}
					Videos.bindEvent.call(_this);
				}, 300);
			}

			Videos.bindEvent = function() {

				var _this = this, 
					_config = _this._config, 
					_$this = _this._$this, 
					player = _this.player;
				// 是否切换了资源
				var switchFlag = false;

				// 视频地址
				var videoSrc = _config.src;
				
				// 播放按钮事件
				$(".hep-video-play-btn").off("click").on("click", function() {
					_config.onBeforePlay();
					_config.onPlayBtn();
				});
				
				$("#nextorloop").addClass("hide");

				// 绑定播放事件
				player.on("play", function() {
					$(".hep-video-modal").addClass("hide");
					$(".hep-video-modal").css("bottom","36px");
					_config.onPlay();
				});

				// 绑定暂停按钮
				player.on("pause", function() {
					$(".hep-video-modal").removeClass("hide");
					_config.onPause();
				});

				// 绑定结束事件
				player.on("ended", function() {
					if (_config.endedBar.isShowNext==true) {
						$("#nextPlayDiv").removeClass("hide");
						$("#nextPlay").off("click").on("click",function(){
							$("#nextPlayDiv").addClass("hide");
							_config.endedBar.onPlayNext();
						});
					}
					if (_config.endedBar.isShowReplay==true) {
						$("#loopPlayDiv").removeClass("hide");
						$("#loopPlay").off("click").on("click",function(){
							$("#loopPlayDiv").addClass("hide");
							_config.endedBar.onBeforeReplay();
						});
					}
					_config.onEnd();
				});

				// 设置视频资源地址
				function setVideo(src) {
					videoSrc = src;
					switchFlag = true;
				}
				
			}

			return init;
		})