<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>校区管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/common.css" />
<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
<script type="text/javascript">

	//查询按钮
	function searchTask(){
		document.forms[0].action="${ctx }/integralCenter/list";
		document.forms[0].submit();
	}

	$(function() {
		$("#taskSubmitId").bind("click", function() {

			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/integralCenter/addIntegralTask",
				data : $('#taskFormId').serialize(),
				async : false,//false 为同步
				error : function(request) {
					top.Dialog.alert("保存失败");
				},
				success : function(data) {
					top.Dialog.alert("保存成功", function() {parent.location.reload();
					});
				}
			});

		})
	});
	
	//下线
	function publishIntegralTask(taskId){
	        //window.location.href="./deleteManager";
	        $.ajax({
				type: "POST",
				url:"${ctx }/integralCenter/publishIntegralTask",
				data:{taskId:taskId},
				async: false,
			    error: function(request) {
			        top.Dialog.alert("发布失败");
			    },
			    success: function(data) {
				   		top.Dialog.alert("发布成功",function(){
				   			refresh();
				   			});
				   		
			    }
			});
	}
	
	//上线
	function offlineIntegralTask(taskId){
        //window.location.href="./deleteManager";
		Dialog.confirm('您确认要下线么？',function(){
			$.ajax({
				type: "POST",
				url:"${ctx }/integralCenter/offlineIntegralTask",
				data:{taskId:taskId},
				async: false,
				error: function(request) {
					top.Dialog.alert("下线失败");
				},
				success: function(data) {
					top.Dialog.alert("下线成功",function(){
						refresh();
					});

				}
			});
		})
	}
	
	//删除
	function delIntegralTask(taskId){
        //window.location.href="./deleteManager";
        $.ajax({
			type: "POST",
			url:"${ctx }/integralCenter/delIntegralTask",
			data:{taskId:taskId},
			async: false,
		    error: function(request) {
		        top.Dialog.alert("删除失败");
		    },
		    success: function(data) {
			   		top.Dialog.alert("删除成功",function(){
			   			refresh();
			   			});
			   		
		    }
		});
	}
	
	function refresh(){
		$('#searchTaskForm').submit();
		<%--window.location.href="${ctx }/integralCenter/list";--%>
	}

</script>
</head>
<body class="nw-body">
<div class="dx-right-content">
	<%--<div class="nw-foot">--%>
				<%--<ul>--%>
					<%--<li ><a href="../jwCampus/campus">校区管理</a></li>--%>
					<%--<li class="tab-active" >课程管理</li>--%>
					<%--<li ><a href="../jwTeacher/teacher">老师管理</a></li>--%>
					<%--<li ><a href="../jwClassRoom/classRooms">教室管理</a></li>--%>
					<%--<li ><a href="../jwClasses/classes">班级管理</a></li>--%>
					<%--<li ><a href="../jwStudent/students">学生管理</a></li>--%>
					<%--<li ><a href="../jwStudent/queryStudentClass">综合管理</a></li>--%>
				<%--</ul>--%>
	<%--</div>--%>

<!--广告查询-->
	<div class="dx-search">
		<form method="post" name="searchTaskForm" id="searchTaskForm" action="${ctx }/integralCenter/list">
			<div class="dx-lab1">
				<%--<td><label>：</label></td>--%>
				<%--<td><select class="date_form" id="platform" name="platform">--%>
					<%--<option value="">请选择</option>--%>
					<%--<c:forEach var="platform" items="${platformList}" varStatus="s1">--%>
						<%--<option value="${platform.code}" <c:if test="${integralTask.platform == platform.code}"> selected="selected"</c:if>>${platform.name}</option>--%>
					<%--</c:forEach>--%>
				<%--</select>--%>
				<%--</td>--%>
				<%--<button class="dx-find" onclick="searchTask();">查询</button>--%>
				<a class="dx-find-a" href="javascript:void(0);" onclick="addCourse();">添加课程</a>
			</div>
		</form>
	</div>
	<div class="dx-cell">
		<table>
			<tr class="table_head">
				<th width="14%">课程名称</th>
				<th width="10%">教学方式</th>
				<th width="15%">总学时数</th>
				<th width="15%">课程状态</th>
				<th width="13%">操作</th>
			</tr>
			<tbody>
				<c:forEach items="${courses}" var="listObj">
					<%-- console.log(${listObj}); --%>
					<tr style="text-align: center">
						<td >${listObj.courseName}</td>
						<td >
							<c:if test="${listObj.courseMethods==0}">远程</c:if>
							<c:if test="${listObj.courseMethods==1}">面授</c:if>
						</td>
						<td >${listObj.coursePeriod}</td>
						<td >
							<c:if test="${listObj.courseStatus==1}">开放</c:if>
							<c:if test="${listObj.courseStatus==0}">关闭</c:if>
						</td>
						<td ><a href="javascript:;" onclick="editCourse(${listObj.id })">编辑</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
    </div>
	<jsp:include page="../common/pagination.jsp" />
</body>
<script type="text/javascript" src="${ctx }/static/js/tab.js"></script>
<script type="text/javascript" src="${ctx }/static/js/laypage.js"></script>
<script type="text/javascript" src="${ctx }/static/js/index.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js"
	charset="utf-8"></script>
</html>