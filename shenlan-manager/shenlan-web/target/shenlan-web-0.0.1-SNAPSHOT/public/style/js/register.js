   		$(document).ready(function(){
   		          
   		          var emailValid=true;
   		          var mes=$("#mes").val();
				    if(mes=='error'){
					    $("#valNum_mes0").css("display",'none');
					    $("#valNum_mes1").css("display",'');
					    $("#valNum_mes2").css("display",'none');
				    }
				    
				 
	   		     $("#mail").blur(function(){
	   		    	var reg = /^([a-zA-Z0-9_|\-|\.])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	   		          var flag=true;
					  var mail=$("#mail").val();
					  
					  if(mail==''){
			              $("#mail_mes0").css("display",'');
	                      $("#mail_mes1").css("display",'none');
	                      $("#mail_mes2").css("display",'none');
	                      $("#mail_mes3").css("display",'none');
			              flag=false;
			             
			           }
			           
			           if(mail!=''&&!reg.test(mail)){
			              $("#mail_mes0").css("display",'none');
	                      $("#mail_mes1").css("display",'');
	                      $("#mail_mes2").css("display",'none');
	                      $("#mail_mes3").css("display",'none');
			              flag=false;
			           }
			           if(flag){
			        	   $.post("checkAction.action",{mail:$("#mail").val()},function(returnData){

				 				  if(returnData.mail=='fail'){
			 					  $("#mail_mes0").css("display",'none');
			                      $("#mail_mes1").css("display",'none');
			                      $("#mail_mes2").css("display",'');
			                      $("#mail_mes3").css("display",'none');
			                      emailValid=false;
			                      
			 				  }else{
			                      $("#mail_mes0").css("display",'none');
			                      $("#mail_mes1").css("display",'none');
			                      $("#mail_mes2").css("display",'none');
			                      $("#mail_mes3").css("display",'');
			                      emailValid=true;
			 				  }
			 		  	   });
			           }
				   });
				   
				  
				
				//密码校验  
				$("#password").blur(function(){
				    var reg =/^([a-zA-Z0-9])+/;
				    var pass=$("#password").val();
				    if(pass==''){
					    $("#pass_mes0").css("display",'');
						$("#pass_mes1").css("display",'none');
						$("#pass_mes2").css("display",'none');
						$("#pass_mes3").css("display",'none');
				    }else if(!reg.test(pass)){
				        $("#pass_mes0").css("display",'none');
						$("#pass_mes1").css("display",'');
						$("#pass_mes2").css("display",'none');
						$("#pass_mes3").css("display",'none');
				    
				    }else if(pass.length<4||pass.length>16){
				        $("#pass_mes0").css("display",'none');
						$("#pass_mes1").css("display",'none');
						$("#pass_mes2").css("display",'');
						$("#pass_mes3").css("display",'none');
				    
				    }else{
				        $("#pass_mes0").css("display",'none');
						$("#pass_mes1").css("display",'none');
						$("#pass_mes2").css("display",'none');
						$("#pass_mes3").css("display",'');
				    
				    }
					
				}); 
				
				
				//确认密码校验  
				$("#confirmpass").blur(function(){
				    var reg =/^([a-zA-Z0-9])+/;
				    var password=$("#password").val();
					var confirmpass=$("#confirmpass").val();
					
				    if(confirmpass==''){
					    $("#repass_mes0").css("display",'');
						$("#repass_mes1").css("display",'none');
						$("#repass_mes2").css("display",'none');
				    }else if(confirmpass!=password){
				        $("#repass_mes0").css("display",'none');
						$("#repass_mes1").css("display",'');
						$("#repass_mes2").css("display",'none');
				    }else{
				        $("#repass_mes0").css("display",'none');
						$("#repass_mes1").css("display",'none');
						$("#repass_mes2").css("display",'');
				    
				    }
					
				}); 
				
				//校验码
				$("#valNum").blur(function(){
				    var reg =/^([a-zA-Z0-9])+/;
				     var valNum=$("#valNum").val();
				    if(valNum==''){
					    $("#valNum_mes0").css("display",'');
					    $("#valNum_mes1").css("display",'none');
					    $("#valNum_mes2").css("display",'none');
				    }else{
				        $("#valNum_mes0").css("display",'none');
					    $("#valNum_mes1").css("display",'none');
					    $("#valNum_mes2").css("display",'');
				    
				    }
					
				}); 
				
				
				//条款
				$("#accept").change(function(){
				    
				    var accept=$("#accept").attr("checked");
				    if(!accept){
					    $("#accept_mes0").css("display",'');
						$("#accept_mes1").css("display",'none');
				    }else{
				        $("#accept_mes0").css("display",'none');
						$("#accept_mes1").css("display",'');
				    }
					
				});
			    //验证是否为空
			   $("#tijiao").click(function(){
			          var flag=true;
					  var mail=$("#mail").val();
					  var password=$("#password").val();
					  var confirmpass=$("#confirmpass").val();
					  var valNum=$("#valNum").val();
					  var accept=$("#accept").attr("checked");
					  
			           if(mail==''){
			              $("#mail_mes0").css("display",'');
	                      $("#mail_mes1").css("display",'none');
	                      $("#mail_mes2").css("display",'none');
	                      $("#mail_mes3").css("display",'none');
			              flag=false;
			           }
			           if(mail!=''&&!emailValid){
			              $("#mail_mes0").css("display",'none');
	                      $("#mail_mes1").css("display",'none');
	                      $("#mail_mes2").css("display",'');
	                      $("#mail_mes3").css("display",'none');
			              flag=false;
			           }
			           if(password==''){
				           $("#pass_mes0").css("display",'');
						   $("#pass_mes1").css("display",'none');
						   $("#pass_mes2").css("display",'none');
						   $("#pass_mes3").css("display",'none');
				            flag=false;
				           }
			           if(confirmpass==''){
				           $("#repass_mes0").css("display",'');
						   $("#repass_mes1").css("display",'none');
						   $("#repass_mes2").css("display",'none');
				             flag=false;
				           }
				       if(valNum==''){
				           $("#valNum_mes0").css("display",'');
						   $("#valNum_mes1").css("display",'none');
						   $("#valNum_mes2").css("display",'none');
				              flag=false;
				           }
			           if(!accept){
				           $("#accept_mes0").css("display",'');
						   $("#accept_mes1").css("display",'none');
				              flag=false;
				           }
			           		
			           if(flag){
			              document.forms[0].submit();
			           }			   
		               
				   });
   		
   		});
   		
   		
   		
				
   		function refresh(){

        //重新获取验证码图片的src属性
        document.getElementById("validate").src="valiPicAction.action?now="+new Date();
    }
    function isEmail(str){ 
           var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
           return reg.test(str); 
        }
