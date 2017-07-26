<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>管理员管理</title>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">

        function baiduapi(){
            $.ajax({
                url: "http://aip.baidubce.com/rpc/2.0/nlp/v1/wordpos",
                type: 'post',
                dataType: 'jsonp',
                async:false,
                data: {query: "百度是个大公司",lang_id:1},
                success: function (result){
                    alert(result);
                }
            });
        }


     	/*function loadManager(username)
         {
             $.ajax({
                 url:"${ctx }/systemUser/queryManager",
                 data:{username:username},
                 dataType:"html",
                 success:function(result)
                 {
                     $("#managerContentId").html(result);
                 }
             });
         }

        loadManager.call();*/
        
       // $(function(){
           // $("#managerId").bind("click", function () {
            // 	loadManager.call();
           // });
            
          //  $("#roleId").bind("click", function () {
           ////  	loadRole.call();
           // })
      // });
    </script>
    <script type="text/javascript">
        $(function(){
            $("#queryManagerId").bind("click", function () {
                document.forms[0].submit();
            })
        });
    </script>
    <script type="text/javascript">
    	/* 删除管理员 */
		function deleteManager(managerId,vId){
			 if(0==vId){
            	 	top.Dialog.alert("已删除的管理员不允许重复删除");
            	 	return;
            	 }
		    Dialog.confirm('您确认要删除么？',function(){
		        //window.location.href="./deleteManager";
		        $.ajax({
					type: "POST",
					url:"${ctx }/systemUser/deleteManager",
					data:{id:managerId},
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
<body class="nw-body">
<div class="dx-right-content">
    <div class="nw-foot">
            <ul>
                <li class="tab-active" id="managerId">管理员管理</li>
                <li id="roleId"><a href="${ctx }/systemRole/roleList">角色管理</a></li>
            </ul>
        </div>
            <div class="dx-search">
                <div class="dx-lab1">
                    <form action="${ctx }/systemUser/list" method="post">
                        <label >账户名：</label><input  class="date_form" type="text" name="username" value="${systemUser.username}" id="usernameId">
                        <shiro:hasPermission name="manager:manager:view"><button class="dx-find" id="queryManagerId">查询</button></shiro:hasPermission>
                        <shiro:hasPermission name="manager:manager:add"><a class="dx-find-a" href="javascript:void(0);" onclick="addManager();">添加管理员</a></shiro:hasPermission>

                        <%--<input type="button" name="test" onclick="baiduapi()" value="sdfasdf">--%>

                    </form>

                </div>
            </div>
            <div class="dx-cell">
                <table>
                    <tr class="table_head">
                        <th>序号</th>
                        <th>账户名</th>
                        <th>姓名</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    
                    <c:forEach var="p" items="${pageList.data}">
                    	<tr>
	                        <td>${p.id }</td>
	                        <td>${p.username }</td>
	                        <td>${p.nickname }</td>
	                        <td>${p.isValid }</td>
	                        <td><a href="javascript:;" onclick="deleteManager(${p.id },${p.isValid })">删除</a> | <a href="javascript:;" onclick="updateManager(${p.id },${p.isValid })">修改</a></td>
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
