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
		$("#courseSubmitId").bind("click", function() {
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
			var courseName=$("#courseName").val().trim();
			var coursePeriod=$("#coursePeriod").val().trim()
			if ("" == courseName || null == courseName) {
				top.Dialog.alert("请输入课程名称");
				return false;
			}
			if ("" == coursePeriod || null == coursePeriod) {
				top.Dialog.alert("请输课时");
				return false;
			}
			$("#courseSubmitId").attr("disabled","disabled").val("提交中...");
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwCourse/addCourse",
				data : $('#courseId').serialize(),
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
<title>课程</title>
</head>
<body>
	<div id="forlogin" class="dialog-info">
		<form method="post" action="${ctx }/jwCourse/addCourse" id="courseId">
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
				<tr>
					<td><label>课程名称：</label></td>
					<td>
						<input type="text" id="courseName" name="courseName" placeholder="" />
					</td>
				</tr>
				<tr>
					<td><label>教学方式：</label></td>
					<td>
						<select id="courseMethods" name="courseMethods">
							<option value="0">远程</option>
							<option value="1">面授</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>学时：</label></td>
					<td><input type="text" id="coursePeriod" name="coursePeriod" placeholder=""/></td>
				</tr>
				<tr>
					<td><label>状态：</label></td>
					<td>
						<select id="courseStatus" name="courseStatus">
							<option value="1">开放</option>
							<option value="0">关闭</option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="确定" id="courseSubmitId"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>