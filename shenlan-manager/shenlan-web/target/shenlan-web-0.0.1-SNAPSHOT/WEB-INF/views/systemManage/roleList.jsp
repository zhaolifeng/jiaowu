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
     	
       /* function loadRole(rolename)
         {
         
             $.ajax({
                 url:"${ctx }/systemRole/queryRole",
                 data:{name:rolename},
                 dataType:"html",
                 success:function(result)
                 {
                     $("#roleContentId").html(result);
                 }
             });
         }
        loadRole.call();*/
        
       // $(function(){
            
            //$("#roleId").bind("click", function () {
             	//loadRole.call();
            //})
        //});
        
         
    </script>
    <script type="text/javascript">
        $(function(){
            $("#queryRoleId").bind("click", function () {
                var rolename = $("#rolenameId").val();
                window.location.href="${ctx }/systemRole/roleList?name="+rolename;
                //$(window.parent.loadRole(rolename));
                //loadRole(rolename);

                $(function(){
                    $("#queryManagerId").bind("click", function () {
                        document.forms[0].submit();
                    })
                });
            })

        });
    </script>

    <script type="text/javascript">
        /* 删除角色 */
        function deleteRole(roleId,vId){
        	if(0==vId){
           	 	top.Dialog.alert("已删除的角色不允许重复删除");
           	 	return;
            }
        	
        	$.ajax({
		        type: "POST",
		        url:"./checkRoleUser",
		        data:{id:roleId},
		        async: false,//false 为同步
		        error: function(request) {
		            top.Dialog.alert("删除失败");
		        },
		        success: function(data) {
		        	if(data.success == true)
		            {
		        		top.Dialog.alert("该角色已被："+data.data+"使用，不允许删除!");
		            }else{
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
		        }
		    });
        	
            
        }
    </script>
</head>
<body class="nw-body">
<div class="dx-right-content">
    <div class="nw-foot">
            <ul>
                <li><a href="${ctx }/systemUser/list">管理员管理</a></li>
                <li class="tab-active" >角色管理</li>
            </ul>
        </div>

		<!--角色管理-->
        <div class="dx-search">
            <div class="dx-lab1">
                <form action="${ctx }/systemRole/roleList" method="post">
                    <label>角色名：</label><input class="date_form" type="text" name="name" value="${systemRole.name}" id="rolenameId" >
                    <shiro:hasPermission name="manager:role:view"><button class="dx-find" id="queryRoleId">查询</button></shiro:hasPermission>
                    <shiro:hasPermission name="manager:role:add"><a class="dx-find-a" href="javascript:void(0);" onclick="addRole();">添加角色</a></shiro:hasPermission>
                </form>
            </div>
        </div>

        <div class="dx-cell">
            <table>
                <tr class="table_head">
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
                        <td>
                        	<a href="javascript:;" onclick="deleteRole(${p.id },${p.isValid })">删除</a> |
                        	<a href="javascript:;" onclick="updateRole(${p.id },${p.isValid })">修改</a>|
                        	<a href="javascript:;" onclick="viewRole(${p.id },${p.isValid })">查看</a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <jsp:include page="../common/pagination.jsp"/>
</body>
<script type="text/javascript" src="${ctx }/static/js/tab.js"></script>
<script type="text/javascript" src="${ctx }/static/js/laypage.js"></script>
<script type="text/javascript" src="${ctx }/static/js/index.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8" ></script>
</html>

<!--<script type="text/javascript" src="js/check.js"></script>-->
