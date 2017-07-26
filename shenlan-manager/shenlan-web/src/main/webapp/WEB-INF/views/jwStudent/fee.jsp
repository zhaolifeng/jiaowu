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
			$("#feeSubmitId").bind("click", function() {
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
				var operator=$("#operator").val().trim();
				var cost=$("#cost").val().trim()
				if ("" == operator || null == operator) {
					top.Dialog.alert("请输入收款人");
					return false;
				}
				if ("" == cost || null == cost) {
					top.Dialog.alert("请输入金额");
					return false;
				}
				$("#feeSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwStudent/fee",
					data : $('#fee').serialize(),
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
	<title>交费</title>
</head>
<body>


<div id="forlogin" class="dialog-info">
	<form method="post" action="" id="fee">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
			<tr>
				<td><font color="red">*</font><label>收款人：</label></td>
				<td>
					<input type="hidden"  value="${id}" name="id" placeholder="" />
					<input type="hidden"  value="${studentId}" name="studentId" placeholder="" />
					<input type="hidden"  value="${classesId}" name="classesId" placeholder="" />
					<input type="text" id="operator"  name="operator" placeholder="" />
				</td>
			</tr>
			<tr>
				<td><label>金额：</label></td>
				<td>
					<input type="text" id="cost"  name="cost" placeholder="" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="确定" id="feeSubmitId"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>