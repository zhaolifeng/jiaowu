(function($) {
	
	$.fn.maxinput = function(options) {
		var opts = $.extend({}, $.fn.maxinput.defaults, options);
		return this.each(function() {
			$this = $(this);
			var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
			$.fn.limit(o,$this);			
		});
	};
	$.fn.maxinput.defaults = {
		limit 		: 140,
		showtext  	: false,
		message     : 'characters left'
	};
	$.fn.limit = function(o,obj){
			if(!$('.jMax-text',obj).length){
				var _jMaxtext		= $(document.createElement('div')).addClass('jMax-text');
				_jMaxtext.html('<span id="numberSpan">'+o.limit+'</span>');		
				
				var _jMaxtextarea 	= $("#topicContent");		
				
				var _jMaxsubmit	= $(document.createElement('div')).addClass('jMax-submit').css('float','right');
				
			
				obj.append(_jMaxtext).append(_jMaxtextarea).append(_jMaxsubmit);
				if(o.showtext)
					$(document.createElement('span')).html(' 个字').insertAfter($("#numberSpan"));
					$(document.createElement('span')).attr("id","prefixChar").insertBefore($("#numberSpan"));
			}
			var currlength = $("#topicContent").val().length ;
			
			if((o.limit - currlength)<0){
				$('.jMax-text #numberSpan',obj).html(Math.abs(o.limit - currlength));
				$("#prefixChar").html("已经超过 ");
				$(".jMax-text").css("color","red");
			}else{
				$('.jMax-text #numberSpan',obj).html(o.limit - currlength);
				if(o.limit==currlength){
					$('.jMax-text #numberSpan',obj).html("0");
				}
				$("#prefixChar").html("还可以写 ");
				$(".jMax-text").css("color","#008800");
			}
			
			if((currlength > 0)&&(currlength <= o.limit))
				$('#zhuanfaTopic').removeAttr('disabled').removeClass('disabled').addClass('enabled');
			else
				$('#zhuanfaTopic').attr('disabled','true').removeClass('enabled').addClass('disabled');
			$("#topicContent").one('keydown',function(){
				var d = function() { obj.maxinput(o) };
				timeout = setTimeout(d,1);
			});
	}
})(jQuery);