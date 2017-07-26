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
			$("#courseTeacherSubmitId").bind("click", function() {
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
				if($("input[name='courseIds']:checked").length == 0)
				{
					top.Dialog.alert("请选择课程");
					return false;
				}
				$("#courseTeacherSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwTeacher/teacheAddCourse",
					data : $('#courseTeacherId').serialize(),
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
	    <form method="post" action="" id="courseTeacherId">
		<table width="80%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<input type="hidden" name="teacherId" value="${teacherId}">
			<tr>
				<th>
					课程
				</th>
				<th>
					学时
				</th>
			</tr>
			<c:forEach items="${jwCourseTeachers}" var="listObj">
			<tr>
				<td>
					<input type="checkbox" <c:if test="${listObj.teacherId!=null}">checked</c:if> name="courseIds" value="${listObj.courseId}" > ${listObj.courseName}
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2" align="center">
					<input class="buttonStyle sure" type="button" value="确定" id="courseTeacherSubmitId">
				</td>
			</tr>
		</table>
		</form>
</div>
</body>
</html>