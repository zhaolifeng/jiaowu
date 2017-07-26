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
		$("#teacherSubmitId").bind("click", function() {
			var name=$("#name").val().trim();
			var mobile=$("#mobile").val().trim();
			var entrytime=$("#entrytime").val().trim();
			var age=$("#age").val().trim();
			if ("" == name || null == name) {
				top.Dialog.alert("请输入老师名称");
				return false;
			}
			if ("" == mobile || null == mobile) {
				top.Dialog.alert("请输入电话");
				return false;
			}
			var t = /^\+?[1-9][0-9]*$/;
			if(!t.test(age)) {
				top.Dialog.alert("年龄必须为正整数！！");
				return;
			}

			if ("" == entrytime || null == entrytime) {
				top.Dialog.alert("请输入入职时间");
				return false;
			}
			$("#teacherSubmitId").attr("disabled","disabled").val("提交中...");
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwTeacher/addTeacher",
				data : $('#teacherId').serialize(),
				async : false,//false 为同步
				error : function(request) {
					top.Dialog.alert("保存失败");
					$("#teacherSubmitId").removeAttr("disabled").val("确定");
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
<title>教师</title>
</head>
<body>
	<div id="forlogin" class="dialog-info">
		<form method="post" action="${ctx }/jwTeacher/addTeacher" id="teacherId">
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
				<tr>
					<td><label>姓名：</label></td>
					<td>
						<input type="text" id="name" name="name" placeholder="" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td><label>性别：</label></td>
					<td>
						<select id="sex" name="sex">
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>年龄：</label></td>
					<td><input type="text" id="age" name="age" placeholder="" maxlength="3"/></td>
				</tr>
				<tr>
					<td><label>入职时间：</label></td>
					<td>
						<input class="txtEndDate"  type="text" id="entrytime" name="entrytimeParam" placeholder=""/>
					</td>
				</tr>
				<tr>
					<td><label>学历：</label></td>
					<td>
						<select id="educate" name="educate">
							<option value="0">本科</option>
							<option value="1">硕士</option>
							<option value="2">博士</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>电话：</label></td>
					<td>
						<input type="text" id="mobile" name="mobile" placeholder="" maxlength="11"/>
					</td>
				</tr>
				<tr>
					<td><label>微信：</label></td>
					<td>
						<input type="text" id="wechat" name="wechat" placeholder="" maxlength="50"/>
					</td>
				</tr>

				<tr>
					<td><label>授课模式：</label></td>
					<td>
						<select id="teacherType" name="teacherType">
							<option value="1">全职</option>
							<option value="0">兼职</option>
						</select>
					</td>
				</tr>

				<tr>
					<td><label>状态：</label></td>
					<td>
						<select id="teacherStatus" name="teacherStatus">
							<option value="1">在职</option>
							<option value="0">离职</option>
							<option value="2">休假</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" style="padding-left: 160px;">
					   <input class="buttonStyle sure" type="button" value="确定" id="teacherSubmitId">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>