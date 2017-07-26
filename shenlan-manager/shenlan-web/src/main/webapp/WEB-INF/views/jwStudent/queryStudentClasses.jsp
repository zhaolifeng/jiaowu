<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>学生管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/common.css" />
<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
<script type="text/javascript">
	//查询按钮
	function search(){
		var campusId=$("#campusId").val().trim();
		var classesId=$("#classesId").val().trim()
		if ("" == campusId || null == campusId) {
			top.Dialog.alert("请选择校区");
			return false;
		}
		if ("" == classesId || null == classesId) {
			top.Dialog.alert("请选择班级");
			return false;
		}
		document.forms[0].action="${ctx}/jwStudent/queryStudentClass";
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

	function isCheckIn(classesId,courseId,studentId){
		var flag=false;
		$.ajax({
			cache : true,
			type : "POST",
			url : "${ctx }/jwStudent/isCheckIn",
			data : {"classesId":classesId,
				    "courseId":courseId,
				    "studentId":studentId},
			async : false,//false 为同步
			success : function(data) {
                     if(data !='ok'){
						 flag=true;
					 }
			}
		});
		return flag;
	}

	function toCheckIn(stId,classesId,courseId,studentId){
		if(isCheckIn(classesId,courseId,studentId)){
			Dialog.alert("今日已经签到")
			return false;
		}

		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 300;
		diag.Title = "签到";
		diag.URL = "../jwStudent/initCheckIn?id="+stId+"&classesId="+classesId+"&studentId="+studentId+"&courseId="+courseId;
		diag.show();

		<%--$.ajax({--%>
			<%--cache : true,--%>
			<%--type : "POST",--%>
			<%--url : "${ctx }/jwStudent/checkIn",--%>
			<%--data : {"classesId":classesId,--%>
				<%--"courseId":courseId,--%>
				<%--"studentId":studentId},--%>
			<%--async : false,//false 为同步--%>
			<%--success : function(data) {--%>
				<%--if(data=='ok'){--%>
					<%--Dialog.alert("签到成功")--%>
				<%--}--%>
			<%--}--%>
		<%--});--%>
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
						<option value="${campus.id}" <c:if test="${campus.id == classes.campusId}"> selected="selected"</c:if>>${campus.name}</option>
					</c:forEach>
				</select>
				<font color="red">*</font><label>班级:</label>
				<select class="date_form" id="classesId" name="classesId">
					<option value="">请选择</option>
					<c:forEach var="allClasse" items="${allClasses}" varStatus="s1">
						<option value="${allClasse.id}" <c:if test="${allClasse.id == classes.id}"> selected="selected"</c:if>>${allClasse.classesName}</option>
					</c:forEach>
				</select>
				<label>学生姓名:</label>
				<input class="date_form" type="text" id="name" name="name" value="${student.name}"/>
				<label>缴费状态:</label>
				<select class="date_form" id="payStatus" name="payStatus">
					<option value="">请选择</option>

					<option value="1" <c:if test="${studentClass.payStatus == 1}"> selected="selected"</c:if> >已缴费</option>
					<option value="0" <c:if test="${studentClass.payStatus == 0}"> selected="selected"</c:if> >未交费</option>
				</select>
				<button class="dx-find" onclick="return search();">查询</button>
			</div>
		</form>
	</div>
	<div class="dx-cell">
		<table>
			<tr class="table_head">
				<th width="8%">学生姓名</th>
				<th width="4%">学号</th>
				<th width="14%">班级名称</th>
				<th width="10%">所属校区</th>
				<th width="10%">课程</th>
				<th width="5%">学时</th>
				<th width="5%">学费</th>
				<th width="5%">出勤次数</th>
				<th width="10%">缴费状态</th>
				<th width="10%">操作</th>
			</tr>
			<tbody>
			<c:forEach items="${studentClasseses}" var="listObj">
				<tr style="text-align: center">
					<td >${listObj.student.name}</td>
					<td >${listObj.student.studentNo}</td>
					<td >${listObj.classes.classesName}</td>
					<td >
						<c:forEach items="${campuses}" var="listObj1">
							<c:if test="${listObj.classes.campusId==listObj1.id}">
								${listObj1.name}
							</c:if>
						</c:forEach>
					</td>
					<td >${listObj.course.courseName}</td>
					<td >${listObj.course.coursePeriod}</td>
					<td >${listObj.classes.fee}</td>
					<td >
						<a href="javascript:;" onclick="showStudentCheckInList(${listObj.student.id},${listObj.classes.campusId},${listObj.classes.id})">${listObj.attendanceCount}</a>
					</td>
					<td >
						<c:if test="${listObj.payStatus == 0}">未交费</c:if>
						<c:if test="${listObj.payStatus == 1}">已缴费</c:if>
					</td>
					<td >
						<c:if test="${listObj.payStatus == 0}">
							<a href="javascript:;" onclick="initFee(${listObj.id})">交费</a>|
						</c:if>
						   <a href="javascript:;" onclick="toCheckIn(${listObj.id},${listObj.classes.id},${listObj.course.id},${listObj.student.id})">签到</a>
						<c:if test="${listObj.attendanceCount>0}">
							|<a href="./downLoadQuery?classesId=${listObj.classes.id}&courseId=${listObj.course.id}&studentId=${listObj.student.id}">学习进度</a>
						</c:if>
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