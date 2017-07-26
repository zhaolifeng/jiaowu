<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>学生管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/index.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css" />
	<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
	<script type="text/javascript">
		//查询按钮
		function search(){
			var campusId=$("#campusId").val().trim();
			if ("" == campusId || null == campusId) {
				top.Dialog.alert("请选择校区");
				return false;
			}
			document.forms[0].action="${ctx}/jwTeacher/queryClassTeacher";
			document.forms[0].submit();
		}

		function updateClasses(campusId){
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwStudent/queryClassByCampus",
				data : {"campusId":$("#campusId").val()},
				async : false,//false 为同步
				success : function(data) {
					var strOpt="<option value=>请选择</option>";
					$.each(data,function(index,item){
						strOpt=strOpt+"<option value="+item.id+">"+item.classesName+"</option>";
					});
					$("#classesId").html(strOpt);
				}
			});
		}

		function teahcerCheckIn(classesId,teacherId,campusId){
			var diag = new Dialog();
			diag.Width = 500;
			diag.Height = 370;
			diag.Title = "签到";
			diag.URL = "../jwTeacher/initCheckIn?classesId="+classesId+"&teacherId="+teacherId;
			diag.show();
		}
	</script>
</head>
<body class="nw-body">
<div class="dx-right-content">
	<div class="dx-search">
		<form  method="post" name="querySearchForm" id="querySearchForm">
			<div class="dx-lab2">
				<font color="red">*</font><label>校区:</label>
				<select class="date_form" id="campusId" name="campusId" onchange="updateClasses();">
					<option value="">请选择</option>
					<c:forEach var="campus" items="${campuses}" varStatus="s1">
						<option value="${campus.id}" <c:if test="${campus.id == campusId}"> selected="selected"</c:if>>${campus.name}</option>
					</c:forEach>
				</select>
				<label>班级:</label>
				<select class="date_form" id="classesId" name="classesId">
					<option value="">请选择</option>
					<c:forEach var="allClasse" items="${allClasses}" varStatus="s1">
						<option value="${allClasse.id}" <c:if test="${allClasse.id == classesId}"> selected="selected"</c:if>>${allClasse.classesName}</option>
					</c:forEach>
				</select>
				<button class="dx-find" onclick="return search();">查询</button>
			</div>
		</form>
	</div>
	<div class="dx-cell">
		<table>
			<tr class="table_head">
				<th width="8%">老师姓名</th>
				<th width="14%">工号</th>
				<th width="10%">班级名称</th>
				<%--<th width="10%">出勤次数</th>--%>
				<th width="10%">操作</th>
			</tr>
			<tbody>
			<c:forEach items="${teachers}" var="teacher">
				<tr style="text-align: center">
					<td >${teacher.name}</td>
					<td >${teacher.teacherNo}</td>
					<td >
						<c:forEach items="${allClasses}" var="allClasse">
							<c:if test="${allClasse.id==teacher.classesId}">
								${allClasse.classesName}
							</c:if>
						</c:forEach>
					</td>
					<%--<td >--%>
						<%--<a href="javascript:;" onclick="showStudentCheckInList(${teacher.id},${teacher.classesId})">${teacher.attendanceCount}</a>--%>
					<%--</td>--%>
					<td >
						<a href="javascript:;" onclick="teahcerCheckIn(${teacher.classesId},${teacher.id})">签到</a>
						|<a href="./downLoadQuery?teacherId=${teacher.id}">出勤下载</a>
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