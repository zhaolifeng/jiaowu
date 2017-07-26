<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="textml; charset=utf-8">
  <title>查看角色</title>
  <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/dialog.css"/>
  <link rel="stylesheet" href="${ctx }/static/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
  <script type="text/javascript" src="${ctx }/static/js/jquery.ztree.all.min.js"></script>
    
  <script type="text/javascript">
  		var setting = {
					check: {
						enable: false
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
        $(function(){
        	$.fn.zTree.init($("#treeMenu"), setting, ${lTree});
        	
        });
   </script>
</head>
<body>
<div id="forlogin" class="dialog-info">
	<form  method="post" action="${ctx }/systemRole/updRole" id="roleFormId">
		  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			  <input type="hidden" name="id" value="${systemRole.id }" readonly></td>
		    <%--<tr>--%>
		      <%--<td><label>序号：</label></td>--%>
		      <%--<td><input type="text" name="id" value="${systemRole.id }" readonly></td>--%>
		    <%--</tr>--%>
		    <tr>
		      <td><label>角色：</label></td>
		      <td><input type="text" name="name" value="${systemRole.name }" id="name"  readonly></td>
		    </tr>
				<%--<input type="text" name="roleLevel" value="${systemRole.roleLevel }" id="roleLevel" readonly value="1">--%>
		    <%--<tr>--%>
		      <%--<td><label>等级：</label></td>--%>
		      <%--<!--<td><select name="roleLevel"><option>请选择</option></select></td> -->--%>
		      <%--<td><input type="text" name="roleLevel" value="${systemRole.roleLevel }" id="roleLevel" readonly value="1"></td>--%>
		   <%--</tr>--%>
		   	 <tr>
		    	<td><label>权限：</label></td>
		    	<td>
				<ul id="treeMenu" class="ztree"></ul>
				<input  type="hidden" name="menuStr" id="menuStr">
				</td>
		    </tr>
		    <!--<tr>
		      <td><label>权限：</label></td>
		      <td><select>
		      		<option value="-1">请选择</option>
		      		<%--<c:forEach var="m" items="${systemRole.systemMenu}">--%>
	                    <%--<option value="${m.id }">${m.name }</option>--%>
					<%--</c:forEach>  --%>
		      	  </select>
	     	  </td>
		    </tr> -->
		   <tr>
		      <td colspan="2" align="left" style="padding-left:160px;">
				  <!--  input class="buttonStyle sure" type="button" value="确定" id="roleViewId"></td>-->
		    </tr>
		  </table>
	</form>
</div>
</body>
</html>