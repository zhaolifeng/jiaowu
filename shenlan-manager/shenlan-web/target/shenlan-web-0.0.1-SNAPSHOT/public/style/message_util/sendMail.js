	function show(title,content){
		var html ='';
		var wHegiht=$(window).height();
		var wWidth=$(window).width();
		var leftScroll=$(window).scrollLeft();
		var topScroll=$(window).scrollTop();
		var top=topScroll+(wHegiht-150)/2;
		var left=leftScroll+(wWidth-404)/2;
			html+="<div class=\"window\" style=\"padding:2px; margin:2px; background:url('../message_util/setmessbg.gif') no-repeat scroll center top transparent; width:404px; height:158px;z-index:9999\">";
			html+="<div class=\"title\" style=\"width:369px; margin:0 auto; height:29px; padding-top:7px; overflow:hidden;\">";
			html+="<img onclick=\"ok();\" id=\"close\" onclick=\"close();\" src=\"../message_util/closebutt.gif\" class=\"closebox\" style=\" float:right; margin-top:2px; cursor:pointer;\" />";
			html+="<span style=\"display:block; float:left; height:29px; line-height:29px;font-size:14pt; color:#6b6b6b;\">"+title+"</span>";
			html+="</div>"
			html+="<div class=\"content\" style=\"width:369px; margin:0 auto; overflow:hidden;\">";
			html+="<dl style=\"display:block; width:369px; margin:0; padding:0; padding:15px 0 6px 0; float:left;\">";
			html+="<dt style=\"display:block; margin:0; padding:0; width:50px; float:left;\"><img src=\"../message_util/rightsign.gif\" /></dt>";
			html+="<dd style=\"margin:0; padding:0; font-size:14px;color:#333333; width:318px; float:left;\">";
			html+="<span style=\"display:block; line-height:28px;\">"+content+"</span>";
			html+="<span><a id=\"button\" href=\"#\" onclick=\"ok();\" class=\"enterbutt\" style=\"display:block; width:74px; height:27px; background:url(../message_util/enterbutt.gif) no-repeat; margin:13px 0 0 97px;\"></a></span>";
			html+="</dd>";
			html+="</dl>";
			html+="</div>";
			html+="</div>";
		  
		$("body").append(html);
		$(".window").css('display','none').css('padding','2px').css('margin','2px').css('background','url("../message_util/setmessbg.gif") no-repeat scroll center top transparent').css('width','404px').css('height','152px').css('position','absolute').css('left',left).css('top',top);
		$(".title").css('padding','8px').css('height','20px');
		$(".title img").css('padding','5px').css('float','right');
		$(".cotent").css('background','#FFFFFF').css('height','100px');;
		$(".buttom").css('align','center');
		
		$(".window").show('slow');
		$("#close").click(function(){
			$(".window").hide('slow');	
		});
		$("#button").click(function(){
			$(".window").hide('slow');
		});
		
	
	}
	
	function ok(){
		$(".window").hide('slow');
    }