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
					<%--&lt;%&ndash;<li class="tab-active">校区管理</li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li ><a href="../jwCourse/course">课程管理</a></li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li ><a href="../jwTeacher/teacher">老师管理</a></li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li ><a href="../jwClassRoom/classRooms">教室管理</a></li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li ><a href="../jwClasses/classes">班级管理</a></li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li ><a href="../jwStudent/students">学生管理</a></li>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li ><a href="../jwStudent/queryStudentClass">综合管理</a></li>&ndash;%&gt;--%>
				<%--</ul>--%>
	<%--</div>--%>

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
				<a class="dx-find-a" href="javascript:void(0);" onclick="addCampus();">添加校区</a>
			</div>
		</form>
	</div>
	<div class="dx-cell">
		<table>
			<tr class="table_head">
				<th width="14%">校区名称</th>
				<th width="10%">校区地址</th>
				<th width="5%">教室数</th>
				<th width="8%">联系人</th>
				<th width="8%">联系人电话</th>
				<th width="9%">合作方式</th>
				<th width="9%">校区状态</th>
				<th width="20%">操作</th>
			</tr>
			<tbody>
				<c:forEach items="${campuses}" var="listObj">
					<%-- console.log(${listObj}); --%>
					<tr style="text-align: center">
						<td >${listObj.name}</td>
						<td >${listObj.address}</td>
						<td >${listObj.rooms}</td>
						<td >${listObj.tel}</td>
						<td >${listObj.manager}</td>
						<td >
							<c:if test="${listObj.campusType==1}">直营</c:if>
							<c:if test="${listObj.campusType==2}">加盟</c:if>
							<c:if test="${listObj.campusType==3}">合作</c:if>
						</td>
						<td >
							<c:if test="${listObj.campusStauts==1}">开放</c:if>
							<c:if test="${listObj.campusStauts==0}">关闭</c:if>
						</td>
						<td ><a href="javascript:;" onclick="editCampus(${listObj.id })">编辑</a>|
							<a href="javascript:;" onclick="initTeacher(${listObj.id })">选老师</a>
							<%--<a href="javascript:;" onclick="editCampus(${listObj.id })">创建教室</a>|--%>
							<%--<a href="javascript:;" onclick="editCampus(${listObj.id })">创建班级</a>--%>
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