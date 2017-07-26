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
			$("#classRoomSubmitId").bind("click", function() {
				var name=$("#name").val().trim();
				var total=$("#total").val().trim()
				if ("" == name || null == name) {
					top.Dialog.alert("请输入教室名称");
					return false;
				}
				if ("" == total || null == total) {
					top.Dialog.alert("请输容纳人数");
					return false;
				}
				$("#classRoomSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwClassRoom/editClassRoom",
					data : $('#classRoom').serialize(),
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
	<title>校区</title>
</head>
<body>
<div id="forlogin" class="dialog-info">
	<form method="post"  id="classRoom">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<tr>
				<td><label>教室名称：</label></td>
				<td>
					<input type="text" id="name" name="name" value="${classRoom.name}" placeholder="" />
					<input type="hidden" id="id" name="id" value="${classRoom.id}"/>
				</td>
			</tr>
			<tr>
				<td><label>所属校区：</label></td>
				<td>
					${campus.name}
				</td>
			</tr>
			<tr>
				<td><label>容纳人数：</label></td>
				<td><input type="text" id="total" name="total" value="${classRoom.total}" placeholder="" /></td>
			</tr>
			<tr>
				<td><label>状态：</label></td>
				<td>
					<select id="status" name="status" >
						<option value="0" <c:if test="${classRoom.status== 0}"> selected="selected"</c:if>>开放</option>
						<option value="1" <c:if test="${classRoom.status == 1}"> selected="selected"</c:if>>关闭</option>
					</select>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="确定" id="classRoomSubmitId">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>