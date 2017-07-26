// jQuery Alert Dialogs Plugin
//
// Version 1.0
// Download by http://www.codefans.net
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 29 December 2008
//
// Visit http://abeautifulsite.net/notebook/87 for more information
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
// License:
// 
//		This plugin is licensed under the GNU General Public License: http://www.gnu.org/licenses/gpl.html
//
(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .01,                // transparency level of overlay
		overlayColor: '#FFF',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods


		mydefine: function(title, htmlCode,callback,type) {
			if( title == null ) title = 'MyDefine';
			if(type=="tranTopic"){
				$.alerts._show(title,htmlCode, function( tranTopic, isAgree) {
					if( callback ) callback( tranTopic, isAgree);
				},type);
			}else if(type=="quxiaoAtte"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="setRemark"){
				$.alerts._show(title,htmlCode, function(remarkInput) {
					if( callback ) callback(remarkInput);
				},type);
			}else if(type=="blackList"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="delFans"){
				$.alerts._show(title,htmlCode, function(isInBlack) {
					if( callback ) callback(isInBlack);
				},type);
			}else if(type=="atteAllFans"){
				$.alerts._show(title,htmlCode, function(ids) {
					if( callback ) callback(ids);
				},type);
			}else if(type=="deleteGroup"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="removeCollect"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="setTaGroup"){
				$.alerts._show(title,htmlCode, function(groupid) {
					if( callback ) callback(groupid);
				},type);
			}else if(type=="atTa"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="recommend"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="deleteTopic"){
				$.alerts._show(title,htmlCode, function() {
					if( callback ) callback();
				},type);
			}else if(type=="sendLetter"){
				$.alerts._show(title,htmlCode, function(lettercont) {
					if( callback ) callback(lettercont);
				},type);
			}else {
				$.alerts._show(title,htmlCode);
			}
		},
		
		// Private methods
		
		_show: function(title,htmlCode , callback,type) {
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1><span id="closespan"></span>' +
			    '<div id="popup_content">' +
					'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
		
			$("#popup_content").append(htmlCode);
			
			$("#closespan").click( function() {
				//var val = $("#popup_prompt").val();
				$.alerts._hide();
				$("#bottomfloor").hide();
			});
			
			//================自己实现的HTML代码功能=========================
			if(type){
				switch( type ) {
					case 'tranTopic':	//转发弹出层
						$("#topicContent").autoResize({
				    	    // On resize:
				    	    onResize : function() {
				    	        $(this).css({opacity:0.8});
				    	    },
				    	    // After resize:
				    	    animateCallback : function() {
				    	        $(this).css({opacity:1});
				    	    },
				    	    // Quite slow animation:
				    	    animateDuration : 200,
				    	    // More extra space:
				    	    extraSpace : 15,
				    		limit: 150
				    	});
						
						//初始化数字限制
						$("#exacturetxt").maxinput({
							showtext 	: true,
							limit		: 140
						});
						
						$("#quxiaoTopic").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#zhuanfaTopic").click( function() {
							
							var tranTopic = $("#topicContent").val();
							var isAgree=false;
							if($("#agreeTopic").attr("checked")==true){
								isAgree=true;
							}
							//alert(tranTopic+"=================="+isAgree);
							if(!tranTopic){
								$("#topicContent").val('说点什么吧！');
								return ;
							}
							
							$.alerts._hide();
							if( callback ) callback( tranTopic, isAgree);
							$("#bottomfloor").hide();
						});
					break;
					case 'quxiaoAtte'://取消关注弹出层
						$("#qxbutt0Atte").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0Atte").click( function() {
							$.alerts._hide();
							if( callback ) callback();
							$("#bottomfloor").hide();
						});
					break;
					case 'setRemark'://设置备注弹出层
						$("#qxbutt0Remark").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0Remark").click( function() {
							var remarkInput = $("#remarkInput").val();
							$.alerts._hide();
							if( callback ) callback(remarkInput);
							$("#bottomfloor").hide();
						});
					break;
					case 'blackList':
						$("#qxbutt0black").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0black").click( function() {
							$.alerts._hide();
							if( callback ) callback();
							$("#bottomfloor").hide();
						});
					break;
					case 'delFans':
						$("#qxbutt0DelFans").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0DelFans").click( function() {
							var isInBlack=false;
							
							if($("#isInBlackInput").attr("checked")==true){
								isInBlack=true;
							}
							
							$.alerts._hide();
							if( callback ) callback(isInBlack);
							$("#bottomfloor").hide();
						});
					break;
					case 'atteAllFans':
						$("#qxbutt0atteFans").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0atteFans").click( function() {
							var ids='';
							$(".idvalue").each(function(i){
								 ids=ids+$(this).text()+',';
							});
							$.alerts._hide();
							ids=ids.substring(0, ids.length-1);
							if( callback ) callback(ids);
						});
					break;
					case 'deleteGroup':
						$("#qxbutt0delGrp").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0delGrp").click( function() {
							$.alerts._hide();
							if( callback ) callback();
							$("#bottomfloor").hide();
						});
					break;
					case 'removeCollect':
						$("#qxbutt0RmCol").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0RmCol").click( function() {
							$.alerts._hide();
							if( callback ) callback();
							$("#bottomfloor").hide();
						});
					break;
					case 'setTaGroup':
						$("#cjinpbutt0reset").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#cjinpbuttsubmitgrp").click( function() {
							var groupid=$("input[@type=radio][@name=mygroup][@checked]").val(); 
							$.alerts._hide();
							if( callback ) callback(groupid);
							$("#bottomfloor").hide();
						});
					break;
					case 'atTa':
						$("#topicContent").autoResize({
				    	    // On resize:
				    	    onResize : function() {
				    	        $(this).css({opacity:0.8});
				    	    },
				    	    // After resize:
				    	    animateCallback : function() {
				    	        $(this).css({opacity:1});
				    	    },
				    	    // Quite slow animation:
				    	    animateDuration : 200,
				    	    // More extra space:
				    	    extraSpace : 15,
				    		limit: 150
				    	});
						
						//初始化数字限制
						$("#exacturetxt").maxinput({
							showtext 	: true,
							limit		: 140
						});
					break;
					case 'recommend':
						$("#topicContent").autoResize({
				    	    // On resize:
				    	    onResize : function() {
				    	        $(this).css({opacity:0.8});
				    	    },
				    	    // After resize:
				    	    animateCallback : function() {
				    	        $(this).css({opacity:1});
				    	    },
				    	    // Quite slow animation:
				    	    animateDuration : 200,
				    	    // More extra space:
				    	    extraSpace : 15,
				    		limit: 150
				    	});
						
						//初始化数字限制
						$("#exacturetxt").maxinput({
							showtext 	: true,
							limit		: 140
						});
						
						$("#qxbuttrecomSeason").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#qdbuttrecomSeason").click( function() {
							
							var tranTopic = $("#topicContent").val();
							$.alerts._hide();
							if( callback ) callback( tranTopic );
							$("#bottomfloor").hide();
						});
					break;
					case 'deleteTopic':
						$("#qxbutt0deleteTopic").click( function() {
							$.alerts._hide();
							$("#bottomfloor").hide();
						});
						
						$("#enterbutt0deleteTopic").click( function() {
							
							$.alerts._hide();
							if( callback ) callback();
							$("#bottomfloor").hide();
						});
					break;
					case 'sendLetter':
						$("#sendLetterTo").click( function() {
							var lettercont=$("#lettercont").val();
							$.alerts._hide();
							if( callback ) callback(lettercont);
							$("#bottomfloor").hide();
						});
					break;
				}
			}
		
			//如果为调用以上弹出层都没执行，表示普通弹出层。即：里面不需要进行操作。
			
			//=======================================================
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', function() {
							$.alerts._reposition();
						});
					break;
					case false:
						$(window).unbind('resize');
					break;
				}
			}
		}
	}

	jMyDefine = function(title , htmlCode , callback,type) {
		$.alerts.mydefine(title , htmlCode , callback,type);
	}
	
})(jQuery);