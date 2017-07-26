<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>角色管理</title>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#queryRoleId").bind("click", function () {
            	var rolename = $("#rolenameId").val();
               //$(window.parent.loadRole(rolename));
               loadRole(rolename);
            })
           
        });
    </script>
 
    <script type="text/javascript">
    	/* 删除角色 */
		function deleteRole(roleId){
		    Dialog.confirm('您确认要删除么？',function(){
		        $.ajax({
					type: "POST",
					url:"${ctx }/systemRole/deleteRole",
					data:{id:roleId},
					async: false,//false 为同步
				    error: function(request) {
				        top.Dialog.alert("删除失败");
				    },
				    success: function(data) {
					    if(data.success == true)
					    {
					   		top.Dialog.alert("删除成功",function(){location.reload();});
					    }
				    }
				});
            	 
		    });
		}
    </script>
</head>
<body>
			<div class="dx-search">
                <div class="dx-lab1">
                    <label for="name">角色名：</label><input type="text" id="rolenameId">
                    <button class="dx-find" id="queryRoleId">查询</button>
                </div>
            </div>
            
            <div class="dx-cell">
            <table>
                <tr>
                    <td colspan="5"><button class="dx-find" onclick="addRole();">添加角色</button></td>
                </tr>
                <tr>
                    <th>序号</th>
                    <th>角色</th>
                    <th>等级</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                
               <c:forEach var="p" items="${pageList.data}">
                    	<tr>
	                        <td>${p.id }</td>
	                        <td>${p.name }</td>
	                        <td>${p.roleLevel }</td>
	                        <td>${p.isValid }</td>
	                        <td><a href="javascript:;" onclick="deleteRole(${p.id })">删除</a> | <a href="javascript:;" onclick="updateRole(${p.id })">修改</a></td>
	                    </tr>
			  </c:forEach>  
                
            </table>
        </div>
   
<div class="dx-page">
    <ul id="dx_list"></ul>
    <div id="dx_data"></div>
</div>

</body>
<script type="text/javascript" src="${ctx }/static/js/tab.js"></script>
<script type="text/javascript" src="${ctx }/static/js/laypage.js"></script>
<script type="text/javascript" src="${ctx }/static/js/index.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8" ></script>
</html>

