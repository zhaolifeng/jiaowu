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
			$("#studentSubmitId").bind("click", function() {
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
				var linkman=$("#linkman").val().trim()
				var tel=$("#tel").val().trim();
				var age=$("#age").val().trim();
				if ("" == name || null == name) {
					top.Dialog.alert("请输入学生姓名");
					return false;
				}
				if ("" == linkman || null == linkman) {
					top.Dialog.alert("请输入联系人");
					return false;
				}
				var t = /^\+?[1-9][0-9]*$/;
				if(!t.test(age)) {
					top.Dialog.alert("年龄必须为正整数！！");
					return;
				}
				if ("" == tel || null == tel) {
					top.Dialog.alert("请输入联系人电话");
					return false;
				}
				$("#studentSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwStudent/editStudent",
					data : $('#student').serialize(),
					async : false,//false 为同步
					error : function(request) {
						top.Dialog.alert("保存失败");
						$("#studentSubmitId").remove("disabled").val("确定");
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
	<form method="post" action="${ctx }/jwTeacher/editTeacher" id="student">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<tr>
				<td><font color="red">*</font><label>姓名：</label></td>
				<td>
					<input type="hidden"  value="${student.id}" name="id" placeholder="" />
					<input type="hidden"  value="${student.studentNo}" name="studentNo" placeholder="" />
					<input type="text" id="name" value="${student.name}" name="name" placeholder="" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td><label>校区：</label></td>
				<td>
					<select id="campusId" name="campusId">
						<c:forEach items="${campuses}" var="listObj">
							<option value="${listObj.id}" <c:if test="${listObj.id == student.campusId}"> selected="selected"</c:if>>${listObj.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>性别：</label></td>
				<td>
					<select id="sex" name="sex" >
						<option value="0" <c:if test="${student.sex == 0}"> selected="selected"</c:if>>男</option>
						<option value="1" <c:if test="${student.sex == 1}"> selected="selected"</c:if>>女</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>年龄：</label></td>
				<td><input type="text" id="age" name="age"  value="${student.age}"  placeholder="" maxlength="3"/></td>
			</tr>
			<tr>
				<td><label>生日：</label></td>
				<td><input type="text"  class="txtEndDate"  id="birthday" name="birthdayParam" value="${birthday}" placeholder=""/></td>
			</tr>
			<tr>
				<td><label>学校：</label></td>
				<td><input type="text" id="school" name="school" value="${student.school}" placeholder="" maxlength="50"/></td>
			</tr>
			<tr>
				<td><label>年级：</label></td>
				<td><input type="text" id="grade" name="grade" value="${student.grade}" placeholder="" maxlength="50"/></td>
			</tr>
			<tr>
				<td><font color="red">*</font><label>联系人：</label></td>
				<td><input type="text" id="linkman" name="linkman" value="${student.linkman}" placeholder="" maxlength="50"/></td>
			</tr>
			<tr>
				<td><font color="red">*</font><label>电话：</label></td>
				<td>
					<input type="text" id="tel" name="tel" value="${student.tel}" placeholder="" maxlength="11"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="确定" id="studentSubmitId"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>