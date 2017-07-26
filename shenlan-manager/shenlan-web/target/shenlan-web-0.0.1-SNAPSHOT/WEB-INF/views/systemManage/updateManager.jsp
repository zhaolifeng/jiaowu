<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="textml; charset=UTF-8">
  <title>修改管理员</title>
  <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/dialog.css"/>
  <link rel="stylesheet" href="${ctx }/static/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
  <script type="text/javascript" src="${ctx }/static/js/jquery.ztree.all.min.js"></script>
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
  
        $(function(){
        	$.fn.zTree.init($("#treeRole"), setting, ${lTree});
        
            $("#managerUpdateId").bind("click", function () {
            	var treeObj = $.fn.zTree.getZTreeObj("treeRole");
				var nodes = treeObj.getCheckedNodes(true);
				
				var roleStr = "";
				for(i in nodes)
				{
					if(nodes[i].id!='-1')
					{
						roleStr += nodes[i].id+",";
					}
				}
				$("#roleStr").attr("value",roleStr.substr(0,roleStr.length-1));//填充内容 
            
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
				$("#managerUpdateId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache: true,
					type: "POST",
					url:"${ctx }/systemUser/updManager",
					data:$('#managerFormId').serialize(),
					async: false,//false 为同步
				    error: function(request) {
				        top.Dialog.alert("修改失败");
				    },
				    success: function(data) {
					    if(data.success == true)
					    {
					   		top.Dialog.alert("修改成功",function(){parent.location.reload();});
					    }else if(data.success == false){
					    	top.Dialog.alert("账户名重复");
					    	$("#managerUpdateId").attr("disabled",false).val("提交");
					    }
				    }
				});
            	 
            })
        });
   </script>
</head>
<body>
<div id="forlogin" class="dialog-info">
 <form  method="post" action="${ctx }/systemUser/updManager" id="managerFormId">
	  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
		  <input type="hidden" name="id" value="${systemUser.id }" readonly>
	    <tr>
	      <td><label>账户名：</label></td>
	      <td><input type="text" name="username" value="${systemUser.username }" id="username"></td>
	    </tr>
	    <tr>
	      <td><label>姓名：</label></td>
	      <td><input type="text" name="nickname" value="${systemUser.nickname }" id="nickname"></td>
	
	    </tr>
	    <tr>
	      <td><label>密码：</label></td>
	      <td><input style="width: 240px;height: 30px" type="password" name="password" value="${systemUser.password }" id="password"></td>
	    </tr>
	     <tr>
	    	<td><label>角色：</label></td>
	    	<td>
			<ul id="treeRole" class="ztree"></ul>
			<input  type="hidden" name="roleStr" id="roleStr">
			</td>
	    </tr>
	    <%--<tr>
	      <td><label>角色：</label></td>
	      <td><select name="srId">
	      		<!--<option value="-1">请选择</option>-->
	      		<c:forEach var="r" items="${systemRole}">
      				<option value="${r.id }" <c:if test="${systemUser.srId == r.id}"> selected="selected"</c:if>>
	      				${r.name }
	      			</option>

                   <!-- <option value="${r.id }">${r.name }</option>-->
				</c:forEach>
	      	  </select>
	      </td>
	    </tr>--%>
	    <tr>
	      <td colspan="2" align="left" style="padding-left:160px;"><input class="buttonStyle sure" type="button" value="确定" id="managerUpdateId"></td>
	    </tr>
	  </table>
	  
  </form>
</div>
</body>
</html>