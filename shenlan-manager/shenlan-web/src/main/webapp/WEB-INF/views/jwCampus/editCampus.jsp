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
		$("#campusSubmitId").bind("click", function() {
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
			var campusName=$("#campusName").val().trim();
			var address=$("#address").val().trim();
			var rooms=$("#rooms").val().trim();
			var manager=$("#manager").val().trim();
			var tel=$("#tel").val().trim();
			if ("" == campusName || null == campusName) {
				top.Dialog.alert("请输入校区名称");
				return false;
			}
			if ("" == address || null == address) {
				top.Dialog.alert("请输入地址");
				return false;
			}
			if ("" == manager || null == manager) {
				top.Dialog.alert("请输入联系人");
				return false;
			}
			if ("" == tel || null == tel) {
				top.Dialog.alert("请输入联系电话");
				return false;
			}
			$("#campusSubmitId").attr("disabled","disabled").val("提交中...");
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwCampus/editCampus",
				data : $('#campusId').serialize(),
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
		<form method="post" action="${ctx }/jwCampus/editCampus" id="campusId">
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
				<tr>
					<td><label>校区名称：</label></td>
					<td>
						<input type="hidden"  name="id" value="${campuse.id}"/>
						<input type="text" id="campusName" name="name" value="${campuse.name}" placeholder="" />
					</td>
				</tr>
				<tr>
					<td><label>地址：</label></td>
					<td><input type="text" id="address" name="address" value="${campuse.address}" placeholder="" /></td>
				</tr>
				<tr>
					<td><label>校区状体：</label></td>
					<td>
						<select id="campusStauts" name="campusStauts">
							<option value="1" <c:if test="${campuse.campusStauts == 1}"> selected="selected"</c:if>>开放</option>
							<option value="0" <c:if test="${campuse.campusStauts == 0}"> selected="selected"</c:if>>关闭</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>教室数：</label></td>
					<td><input type="text" id="rooms" name="rooms" value="${campuse.rooms}" placeholder=""/></td>
				</tr>
				<tr>
					<td><label>联系人：</label></td>
					<td><input type="text" id="manager" name="manager" value="${campuse.manager}" placeholder=""/></td>
				</tr>
				<tr>
					<td><label>电话：</label></td>
					<td><input type="text" id="tel" name="tel" value="${campuse.tel}" placeholder=""/></td>
				</tr>
				<tr>
					<td><label>合作方式：</label></td>
					<td>
						<select name="campusType">
							<option value="1" <c:if test="${listObj.campusType==1}">selected </c:if>>直营</option>
							<option value="2" <c:if test="${listObj.campusType==2}">selected </c:if>>加盟</option>
							<option value="3" <c:if test="${listObj.campusType==3}">selected </c:if>>合作</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="确定" id="campusSubmitId"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>