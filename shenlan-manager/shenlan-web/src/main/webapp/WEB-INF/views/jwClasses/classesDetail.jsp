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
			alert(111)
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
<title>班级详情</title>
</head>
<body>
	<div id="forlogin" class="dialog-info">
		<form method="post"  id="classes">
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
				<tr>
					<td><label>班级名称：</label></td>
					<td>
						${classes.classesName}
					</td>
				</tr>
				<tr>
					<td><label>所属校区：</label></td>
					<td>
						${campus.name}
					</td>
				</tr>
				<tr>
					<td><label>课程：</label></td>
					<td>
						${course.courseName}
					</td>
				</tr>
				<tr>
					<td><label>学费：</label></td>
					<td>
						${classes.fee}
					</td>
				</tr>
			</table>

			<table>
				<tr class="table_head">
					<th width="14%">上课日期</th>
					<th width="10%">上课时段</th>
					<th width="10%">教室</th>
					<th width="10%">老师</th>
					<th width="8%">开始</th>
					<th width="8%">结束</th>
				</tr>
				<tbody>
				<c:forEach items="${classesConfigures}" var="listObj">
					<tr style="text-align: center">
						<td >
							<c:if test="${listObj.classDate==0}">每日</c:if>
							<c:if test="${listObj.classDate==1}">周一</c:if>
							<c:if test="${listObj.classDate==2}">周二</c:if>
							<c:if test="${listObj.classDate==3}">周三</c:if>
							<c:if test="${listObj.classDate==4}">周四</c:if>
							<c:if test="${listObj.classDate==5}">周五</c:if>
							<c:if test="${listObj.classDate==6}">周六</c:if>
							<c:if test="${listObj.classDate==7}">周日</c:if>
						</td>
						<td >
							<c:if test="${listObj.classTime==1}">8:00～10:00</c:if>
							<c:if test="${listObj.classTime==2}">10:00～12:00</c:if>
							<c:if test="${listObj.classTime==3}">13:30～15:30</c:if>
							<c:if test="${listObj.classTime==4}">15:30～17:30</c:if>
							<c:if test="${listObj.classTime==5}">17:00～19:00</c:if>
						</td>
						<td >
							<c:forEach items="${classRooms}" var="listObj1">
								<c:if test="${listObj.classRoomId==listObj1.id}">
									${listObj1.name}
								</c:if>
							</c:forEach>
						</td>
						<td >
							<c:forEach items="${teachers}" var="listObj1">
								<c:if test="${listObj.teacherId==listObj1.id}">
									${listObj1.name}
								</c:if>
							</c:forEach>
						</td>
						<td ><fmt:formatDate  value="${listObj.startTime}" type="both" pattern="yyyy-MM-dd" /></td>
						<td ><fmt:formatDate  value="${listObj.endTime}" type="both" pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>