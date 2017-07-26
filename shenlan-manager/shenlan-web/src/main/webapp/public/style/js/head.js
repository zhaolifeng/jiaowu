$(document).ready(function(){
	 $(".menu_child").click(function(e){

		    e.cancelBubble = true;
            
			var index=$(this).attr("id");
			
			for(var i=1;i<=4;i++){
				if(index=="menu_child"+i){
					$(".menu_child"+i).css("display","inline");
				}else{
					$(".menu_child"+i).css("display","none");
				}
			}


      
         });
	 
     $(".menus").click(function(e){

    	 for(var i=1;i<=4;i++){
					$(".menu_child"+i).css("display","none");
			}
         });

		 $("body").click(function(event){
			 srcElement =  event.srcElement ? event.srcElement : event.target;
			 if(srcElement.name){
				 for(var i=1;i<=4;i++){
						if(srcElement.name=="fun"+i){
							$(".menu_child"+i).css("display","inline");
						}else{
							$(".menu_child"+i).css("display","none");
						}
					}
			 }else{

				 for(var i=1;i<=4;i++){
					   $(".menu_child"+i).css("display","none");
					}
			 }
			 
		 });
	   
	 });

