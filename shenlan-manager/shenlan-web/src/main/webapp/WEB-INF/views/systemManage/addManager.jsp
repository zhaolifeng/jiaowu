<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="textml; charset=UTF-8">
  <title>添加管理员</title>
  <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/dialog.css"/>
  <script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
  
  <script type="text/javascript">
  		var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
		
		var zNode =[
			{ id:0, parentId:-1, name:"随意勾选 1", open:true},
			{ id:11, parentId:0, name:"随意勾选 1-1"},
			{ id:12, parentId:0, name:"随意勾选 1-2"}
		];
        $(function(){
        	
       	    $.fn.zTree.init($("#treeRole"), setting, ${lTree});
       	    
            $("#managerSubmitId").bind("click", function () {
            	var treeObj = $.fn.zTree.getZTreeObj("treeRole");
				var nodes = treeObj.getCheckedNodes(true);
				
				//if(nodes.length>=2)
				//{
				//	top.Dialog.alert("只能选取一个角色");
				//}
				var roleStr = "";
				for(i in nodes)
				{
					if(nodes[i].id!='-1')
					{
						roleStr += nodes[i].id+",";
					}
				}
				$("#roleStr").attr("value",roleStr.substr(0,roleStr.length-1));//填充内容 
            	//alert(roleStr);
            	//alert(roleStr.substr(0,roleStr.length-1));
            	 	//console.log($('#managerFormId').serialize());
            	 
            	 var username = $("#username").val();
            	 var nickname = $("#nickname").val();
            	 var password = $("#password").val();
      
            	 if(''==username){
            	 	top.Dialog.alert("账户名不可为空");
            	 	return;
            	 }
            	  if(''==nickname)
            	 {
            	 	top.Dialog.alert("姓名不可为空");
            	 	return;
            	 }
            	  if(''==password)
            	 {
            	 	top.Dialog.alert("密码不可为空");
            	 	return;
            	 }
            	 if(nodes.length==0)
				{
					top.Dialog.alert("请选择一个角色");
					return;
				}
				$("#managerSubmitId").attr("disabled","disabled").val("提交中...");
				
				$.ajax({
					cache: true,
					type: "POST",
					url:"${ctx }/systemUser/saveManager",
					data:$('#managerFormId').serialize(),
					async: false,//false 为同步
				    error: function(request) {
				        top.Dialog.alert("保存失败");
				    },
				    success: function(data) {
					    if(data.success == true)
					    {
					   		// top.Dialog.close();
					    	//top.Dialog.alert("提示成功",function(){top.Dialog.close();})//弹出提示，点击确定以后关闭弹出窗口
					    	top.Dialog.alert("保存成功",function(){parent.location.reload();});
					    	
					    }else if(data.success == false){
					    	top.Dialog.alert("账户名重复");
					    	$("#managerSubmitId").attr("disabled",false).val("提交");
					    }
				    }
				});
            	 
            })
        });
        
        
   </script>

</head>
<body>
<div id="forlogin" class="dialog-info">
 <form  method="post" action="${ctx }/systemUser/saveManager" id="managerFormId">
	 <input type="hidden" name="token" value="${token}">
	 <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
	    <tr>
	      <td><label>账户名：</label></td>
	      <td><input type="text" name="username" id="username"></td>
	    </tr>
	    <tr>
	      <td><label>姓名：</label></td>
	      <td><input type="text" name="nickname" id="nickname"></td>
	
	    </tr>
	    <tr>
	      <td><label>密码：</label></td>
	      <td><input style="width: 240px;height: 30px" type="password" name="password" id="password"></td>
	    </tr>
	    <tr>
	    	<td><label>角色：</label></td>
	    	<td>
			<ul id="treeRole" class="ztree"></ul>
			<input  type="hidden" name="roleStr" id="roleStr">
			</td>
	    </tr>
	   <%-- <tr>
	      <td><label>角色：</label></td>
	      <td><select name="srId">
	      		<!--<option value="-1">请选择</option>-->
	      		<c:forEach var="r" items="${systemUser.systemRole}">
                    <option value="${r.id }">${r.name }</option>
				</c:forEach>
	      	  </select>
	      </td>
	    </tr>--%>
	    <tr>
	      <td colspan="2" align="left" style="padding-left:160px;"><input class="buttonStyle sure" type="button" value="确定" id="managerSubmitId"></td>
	    </tr>
	  </table>
	  
  </form>
</div>
</body>
	<!--<link rel="stylesheet" href="${ctx }/static/css/demo.css" type="text/css"> -->
	<link rel="stylesheet" href="${ctx }/static/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/static/js/jquery.ztree.all.min.js"></script>
</html>