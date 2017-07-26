<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css"  href="${ctx }/static/css/dialog.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/lyz.calendar.css" />
	<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/lengthControl.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".txtEndDate").calendar();
			$("#checkInId").bind("click", function() {
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwStudent/checkIn",
					data : $('#checkIn').serialize(),
					async : false,//false 为同步
					error : function(request) {
						top.Dialog.alert("签到失败");
					},
					success : function(data) {
						top.Dialog.alert("签到成功",function(){
							parentDialog.close();
						});
					}
				});

			})
		});
	</script>
	<title>签到</title>
</head>
<body>


<div id="forlogin" class="dialog-info">
	<form method="post" action="" id="checkIn">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<tr>
				<td><label>姓名：</label></td>
				<td>
					<input type="hidden"  value="${jwStudentAttendance.id}" name="id" placeholder="" />
					<input type="hidden"  value="${jwStudentAttendance.studentId}" name="studentId" placeholder="" />
					<input type="hidden"  value="${jwStudentAttendance.classesId}" name="classesId" placeholder="" />
					<input type="hidden"  value="${jwStudentAttendance.courseId}" name="courseId" placeholder="" />
					${student.name}
				</td>
			</tr>
			<tr>
				<td><label>签到日期：</label></td>
				<td align="">
					<input class="txtEndDate"  type="text" id="dateTime" name="dateTime" placeholder="" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td><label>学习内容：</label></td>
				<td align="">
					<textarea name="note" rows="5" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"  style=" text-align: center;">
					<input class="buttonStyle sure" type="button" value="确定" id="checkInId"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>