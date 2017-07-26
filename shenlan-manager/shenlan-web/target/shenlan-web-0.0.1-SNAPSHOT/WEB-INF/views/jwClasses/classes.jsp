<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/dialog.css" />
	<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/stat ic/css/lyz.calendar.css" />
	<script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/lengthControl.js"></script>
<script type="text/javascript">
	$(function() {
		$("#classesSubmitId").bind("click", function() {
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
			var classesName=$("#classesName").val().trim();
			var fee=$("#fee").val().trim()
			if ("" == classesName || null == classesName) {
				top.Dialog.alert("请输入班级名称");
				return false;
			}
			if ("" == fee || null == fee) {
				top.Dialog.alert("请输入学费");
				return false;
			}
			$("#classesSubmitId").attr("disabled","disabled").val("提交中...");
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwClasses/addClasses",
				data : $('#classes').serialize(),
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
<title>班级</title>
</head>
<body>
	<div id="forlogin" class="dialog-info">
		<form method="post"  id="classes">
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
				<tr>
					<td><font color="red">*</font><label>班级名称：</label></td>
					<td>
						<input type="text" id="classesName" name="classesName" placeholder="" />
					</td>
				</tr>
				<tr>
					<td><label>所属校区：</label></td>
					<td>
						<select id="campusId" name="campusId">
							<c:forEach items="${jwCampuses}" var="listObj">
								<option value="${listObj.id}">${listObj.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>课程：</label></td>
					<td>
						<select id="courseId" name="courseId">
							<c:forEach items="${jwCourses}" var="listObj">
								<option value="${listObj.id}">${listObj.courseName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font><label>学费：</label></td>
					<td>
						<input type="text" id="fee" name="fee" placeholder="" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" style="padding-left: 160px;">
						<input class="buttonStyle sure" type="button" value="确定" id="classesSubmitId">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>