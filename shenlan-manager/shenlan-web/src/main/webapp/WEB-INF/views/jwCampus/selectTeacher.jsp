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
			$("#campusTeacherSubmitId").bind("click", function() {
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
					if($("input[name='teacherIds']:checked").length == 0)
					{
						top.Dialog.alert("请选择老师");
						return false;
					}
				var teacherIds=new Array();
				$("input[type='checkbox']:checked").each(function(i,item){
					teacherIds[i]=item.value;
				});
//				document.forms[0].teacherIds=teacherIds;
				$("#campusTeacherSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwCampusSelectCourse/campusAddteacher",
					data :$('#campusTeacherId').serialize(),
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
	    <form method="post" action="" id="campusTeacherId">
		<table width="80%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<input type="hidden" id="campusId" name="campusId" value="${campusId}">
			<tr>
				<th>
					老师
				</th>
				<th>
					课程
				</th>
			</tr>
			<c:forEach items="${jwCourseTeachers}" var="listObj">
			<tr>
				<td>
					<input type="checkbox" <c:if test="${listObj.selected}">checked</c:if> name="teacherIds" value="${listObj.teacherId}" > ${listObj.teacherName}
				</td>
				<td>
					${listObj.courseName}
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2" align="center">
					<input class="buttonStyle sure" type="button" value="确定" id="campusTeacherSubmitId">
				</td>
			</tr>
		</table>
		</form>
</div>
</body>
</html>