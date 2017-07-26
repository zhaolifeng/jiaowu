<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css"  href="${ctx }/static/css/dialog.css"/>
	<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/lengthControl.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#studentClassesSubmitId").bind("click", function() {
//       	 	var exchangeRate = $("#exchangeRate").val().trim();
//       		var description = $("#description").val().trim();
//       		var t=/^\d+[:]\d+/;
//       		if(!t.test(exchangeRate)) {
//       		   top.Dialog.alert("兑换比例必须为整数:整数格式！！");
//       		   return;
//       		}
//       		if("" == description ||  null == description){
//       	 		top.Dialog.alert("规则描述不可为空");
//       	 		return;
//       	 	}else{
//				if(strlen(description) > 200){
//					top.Dialog.alert("规则描述不能超过200个字符！！");
//					return;
//				}
//			}
				$("#studentClassesSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwStudent/addStudentClasses",
					data : $('#studentClasses').serialize(),
					async : false,//false 为同步
					error : function(request) {
						top.Dialog.alert("保存失败");
					},
					success : function(data) {
						top.Dialog.alert("保存成功", function() {
							parent.location.reload();
						});
					}
				});

			})
		});
	</script>
	<title>课程列表</title>
</head>
<body>

<div id="forlogin" class="dialog-info">
	    <form method="post" action="${ctx }/jwTeacher/addTeacher" id="studentClasses">
			<input type="hidden" name="studentId" value="${student.id}">
			<table>
				<tr class="table_head">
					<th width="8%">选班</th>
					<th width="14%">班级名称</th>
					<th width="10%">所属校区</th>
					<th width="10%">课程</th>
					<th width="5%">学时</th>
					<th width="5%">学费</th>
				</tr>
				<tbody>
				<c:forEach items="${classes}" var="listObj">
					<tr style="text-align: center">
						<td ><input type="checkbox" name="classesIds" value="${listObj.id}"></td>
						<td >${listObj.classesName}</td>
						<td >
							<c:forEach items="${jwCampuses}" var="listObj1">
								<c:if test="${listObj1.id==student.campusId}">
									${listObj1.name}
								</c:if>
							</c:forEach>
						</td>
						<td >${listObj.courseName}</td>
						<td >${listObj.coursePeriod}</td>
						<td >${listObj.fee}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6" align="center" style="padding-left: 160px;">
						<input class="buttonStyle sure" type="button" value="确定" id="studentClassesSubmitId"></td>
				</tr>
				</tbody>
			</table>
		</form>
</div>
</body>
</html>