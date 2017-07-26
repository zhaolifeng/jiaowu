<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="textml; charset=utf-8">
  <title>添加角色</title>
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
        	$.fn.zTree.init($("#treeMenu"), setting, ${lTree});
        
        
            $("#roleSubmitId").bind("click", function () {
            	var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
				var nodes = treeObj.getCheckedNodes(true);
            	
            	var menuStr = "";
				for(i in nodes)
				{
					if(nodes[i].id!='-1')
					{
						menuStr += nodes[i].id+",";
					}
				}
				$("#menuStr").attr("value",menuStr.substr(0,menuStr.length-1));//填充内容 
            
            	 var name = $("#name").val();
            	 var roleLevel = $("#roleLevel").val();
            	 if(''==name){
            	 	top.Dialog.alert("角色不可为空");
            	 	return;
            	 }
            	 if(''==roleLevel){
            	 	top.Dialog.alert("等级不可为空");
            	 	return;
            	 }
            	  if(nodes.length==0)
				{
					top.Dialog.alert("请选择一个权限");
					return;
				}
				$("#roleSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache: true,
					type: "POST",
					url:"${ctx }/systemRole/saveRole",
					data:$('#roleFormId').serialize(),
					async: false,//false 为同步
				    error: function(request) {
				    	alert("保存失败");
				        top.Dialog.alert("保存失败");
				    },
				    success: function(data) {
					    if(data.success == true)
					    {
					    	//top.Dialog.alert("提示成功",function(){parent.location.reload();});
					    	//top.Dialog.alert("提示成功",function(){top.Dialog.close();)//弹出提示，点击确定以后关闭弹出窗口
					    	top.Dialog.alert("保存成功",function(){parent.location.reload();});
					    }else if(data.success == false){
					    	top.Dialog.alert("角色名重复");
					    	$("#roleSubmitId").attr("disabled",false).val("提交");
					    }
				    }
				});
            	 
            })
        });
   </script>
</head>
<body>
<div id="forlogin" class="dialog-info">
	<form  method="post" action="${ctx }/systemRole/saveRole" id="roleFormId">
		<input type="hidden" name="token" value="${token}">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
		    <tr>
		      <td><label>角色：</label></td>
		      <td><input type="text" name="name" id="name"></td>
		    </tr>
			  <input type="hidden" name="roleLevel" id="roleLevel" readonly value="1">
		    <%--<tr style="visibility: hidden">--%>
		      <%--<td><label>等级：</label></td>--%>
		      <%--<!--<td><select name="roleLevel"><option>请选择</option></select></td> -->--%>
		      <%--<td><input type="hidden" name="roleLevel" id="roleLevel" readonly value="1"></td>--%>
		   <%--</tr>--%>
		     <tr>
		    	<td><label>权限：</label></td>
		    	<td>
				<ul id="treeMenu" class="ztree"></ul>
				<input  type="hidden" name="menuStr" id="menuStr">
				</td>
		    </tr>
		   <!-- <tr>
		      <td><label>权限：</label></td>
		      <td><select><option value="-1">请选择</option></select></td>
		    </tr>-->
		    <tr>
		      <td colspan="2" align="left" style="padding-left:160px;"><input class="buttonStyle sure" type="button" value="确定" id="roleSubmitId"></td>
		    </tr>
		  </table>
	</form>
</div>
</body>
</html>