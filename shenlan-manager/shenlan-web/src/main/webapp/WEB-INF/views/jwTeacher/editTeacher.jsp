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
			$("#teacherSubmitId").bind("click", function() {
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
				var name=$("#name").val().trim();
				var mobile=$("#mobile").val().trim();
				var age=$("#age").val().trim();
				if ("" == name || null == name) {
					top.Dialog.alert("请输入老师名称");
					return false;
				}
				var t = /^\+?[1-9][0-9]*$/;
				if(!t.test(age)) {
					top.Dialog.alert("年龄必须为正整数！！");
					return;
				}
				if ("" == mobile || null == mobile) {
					top.Dialog.alert("请输电话");
					return false;
				}
				$("#teacherSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwTeacher/editTeacher",
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
	<form method="post" action="${ctx }/jwTeacher/editTeacher" id="teacherId">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<tr>
				<td><label>姓名：</label></td>
				<td>
					<input type="hidden"  value="${teachers.id}" name="id" placeholder="" />
					<input type="hidden"  value="${teachers.teacherNo}" name="teacherNo" placeholder="" />
					<input type="text" id="name" value="${teachers.name}" name="name" placeholder="" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td><label>性别：</label></td>
				<td>
					<select id="sex" name="sex" >
						<option value="0" <c:if test="${teachers.sex == 0}"> selected="selected"</c:if>>男</option>
						<option value="1" <c:if test="${teachers.sex == 1}"> selected="selected"</c:if>>女</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>年龄：</label></td>
				<td><input type="text" id="age" name="age"  value="${teachers.age}"  placeholder="" maxlength="3"/></td>
			</tr>
			<%--<tr>--%>
				<%--<td><label>入职时间：</label></td>--%>
				<%--<td>--%>
					<%--<input type="text" id="entrytime" name="entrytime"  value="${teachers.entrytime}"  placeholder=""/>--%>
				<%--</td>--%>
			<%--</tr>--%>
			<tr>
				<td><label>学历：</label></td>
				<td>
					<select id="educate" name="educate">
						<option value="0" <c:if test="${teachers.educate == 0}"> selected="selected"</c:if>>本科</option>
						<option value="1" <c:if test="${teachers.educate == 1}"> selected="selected"</c:if>>硕士</option>
						<option value="2" <c:if test="${teachers.educate == 2}"> selected="selected"</c:if>>博士</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>电话：</label></td>
				<td>
					<input type="text" id="mobile" name="mobile"  value="${teachers.mobile}"  placeholder="" maxlength="11"/>
				</td>
			</tr>
			<tr>
				<td><label>微信：</label></td>
				<td>
					<input type="text" id="wechat" name="wechat"  value="${teachers.wechat}"  placeholder="" maxlength="50"/>
				</td>
			</tr>

			<tr>
				<td><label>授课模式：</label></td>
				<td>
					<select id="teacherType" name="teacherType">
						<option value="1" <c:if test="${teachers.teacherType == 1}"> selected="selected"</c:if>>全职</option>
						<option value="0" <c:if test="${teachers.teacherType == 0}"> selected="selected"</c:if>>兼职</option>
					</select>
				</td>
			</tr>

			<tr>
				<td><label>状态：</label></td>
				<td>
					<select id="teacherStatus" name="teacherStatus">
						<option value="1" <c:if test="${teachers.teacherStatus == 1}"> selected="selected"</c:if>>在职</option>
						<option value="0" <c:if test="${teachers.teacherStatus == 0}"> selected="selected"</c:if>>离职</option>
						<option value="2" <c:if test="${teachers.teacherStatus == 2}"> selected="selected"</c:if>>休假</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="确定" id="teacherSubmitId"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>