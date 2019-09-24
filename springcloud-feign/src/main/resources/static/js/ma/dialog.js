define(["jquery"],function($){
//	
//	var style='<style type="text/css">'+
//				'.prompt-modal-wrapper {position: fixed;top: 0;right: 0;bottom: 0;left: 0;z-index: 203;display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column;flex-direction: column;-webkit-box-pack: center;-ms-flex-pack: center;overflow-x: hidden;overflow-y: auto;transition: opacity .3s ease-out;padding-top: 100px;}'+
//				'.prompt-modal-backdrop {position: absolute;top: 0;right: 0;bottom: 0;left: 0;z-index: 0;background-color: rgba(0,0,0,.2);transition: background-color .3s ease-out;}'+
//				'.prompt-modal {position: relative;z-index: 1;display: flex;width: 500px;background: #fff;margin: 0 auto;outline: 0;box-shadow: 0 5px 20px 0 rgba(0,34,77,.3);transition: max-height .8s ease;border-radius: 6px;}'+
//				'.prompt-collect-box{position: relative;width: 100%;height: 100%;}'+
//				'.prompt-box{width: 100%;height: 100%;}'+
//				'.prompt-box-text{margin-top: 50px;min-height: 24px;line-height: 24px;font-size: 16px;color: #333;padding: 10px 25px;}'+
//				'.prompt-btn{margin: 15px 0;}'+
//				'.prompt-btn a{display: inline-block;width: 90px;height: 40px;line-height: 40px;margin-right: 10px;border-radius: 4px;border: 1px solid #ddd;text-align: center;text-decoration: none;color: #333;}'+
//				'.prompt-btn a:focus,.prompt-btn a.active{text-decoration: none;background: #1583bd;color: #fff;border: 1px solid #1583bd;}'+
//				'.prompt-btn a:hover{background: #4cacde;border: 1px solid #4cacde;color: #fff;text-decoration: none;}'+
//				'.prompt-box-gb{margin: 10px 5px 0 0;font-size: 15px;padding: 5px;cursor: pointer;color: #a7a7a7;}'+
//			   '</style>';
//	
//	function getHtmlById(id){
//		var html='<div id="'+id+'" class="prompt-modal-wrapper">'+
//					style+
//				      '<div class="prompt-modal-backdrop"></div>'+
//				      '<div class="prompt-modal">'+
//				          '<div class="prompt-box clearfix">'+
//				           ' <div id="esc-x-btn" class="pull-right prompt-box-gb fa fa-times"></div>'+
//				            '<p id="content-text" class="prompt-box-text"></p>'+
//				            '<div class="prompt-btn pull-right">'+
//				             ' <a id="button-judge-yes" href="javascript:;" class="active">确认</a>'+
//				              '<a id="button-judge-no" href="javascript:;">取消</a>'+
//				            '</div>'+
//				          '</div>'+
//				      '</div>'+
//				  '</div>';
//		return html;
//	}
//	
//	/**  alert提示框  */
//	function _alert(text){
//		
//		var timestamp=new Date().getTime();
//		var alertId="alert-myModal-"+timestamp;
//		
//		if (!text) {
//			text="无内容";
//		}
//		
//		//添加页面到HTML
//		var html=getHtmlById(alertId);
//		$(document.body).append(html);
//		
//		$('#'+alertId).find("#button-judge-no").hide();
//		
//		//当弹出框隐藏时删除弹出框
//		$('#'+alertId).on('hidden.bs.modal', function () {
//			$('#'+alertId).remove();
//		});
//		
//		// 确认
//		$('#'+alertId).on('click','#button-judge-yes', function() {
//			$('#'+alertId).modal('hide');
//		});
//		
//		$('#'+alertId).on('click','#esc-x-btn', function() {
//			$('#'+alertId).modal('hide');
//		});
//		
//		$('#content-text').append(text);
//		
//		//显示弹出框
//		$('#'+alertId).modal('show');
//	}
//	
//	/**  判断提示框  **/
//	function _confirm(text,funYes,funNo){
//		
//		var timestamp=new Date().getTime();
//		var confirmId="confirm-myModal-"+timestamp;
//		
//		var configs={
//				text:text,
//				yes:funYes,
//				no:funNo
//		}
//		if (!configs) {
//			configs={};
//		}
//		var config=$.extend({
//			text:"",
//			yes:function(){
//			},
//			no:function(){
//			}
//		},configs);
//		var runYes=config.yes;
//		var runNo=config.no;
//		// 添加页面到HTML
//		var html=getHtmlById(confirmId);
//		$(document.body).append(html);
//		
//		// X
//		$('#'+confirmId).on('click','#esc-x-btn', function() {
//			$('#'+confirmId).modal('hide');
//		});
//		
//		// 是
//		$('#'+confirmId).on('click','#button-judge-yes', function() {
//			runYes();
//			$('#'+confirmId).modal('hide');
//		});
//		
//		// 否
//		$('#'+confirmId).on('click','#button-judge-no', function() {
//			runNo();
//			$('#'+confirmId).modal('hide');
//		});
//	
//		// 当弹出框隐藏时删除弹出框
//		$('#'+confirmId).on('hidden.bs.modal', function() {
//			$('#'+confirmId).remove();
//		});
//		
//		$('#content-text').append(text);
//
//		// 显示弹出框
//		$('#'+confirmId).modal('show');
//	}
//	
//	return {
//		alert:_alert,
//		confirm:_confirm
//	}
})