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

		function updateClasses(campusId){
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwStudent/queryClassByCampus",
				data : {"campusId":$("#campusId").val()},
				async : false,//false 为同步
				success : function(data) {
					var strOpt="<option value=>请选择</option>";
					$.each(data,function(index,item){
						strOpt=strOpt+"<option value="+item.id+">"+item.classesName+"</option>";
					});
					$("#classesId").html(strOpt);
				}
			});
		}
		function isCheckIn(classesId,teacherId,campusId){
			var flag=false;
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwTeacher/isCheckIn",
				data : {"classesId":classesId,
					"teacherId":teacherId,
					"campusId":campusId},
				async : false,//false 为同步
				success : function(data) {
					if(data !='ok'){
						flag=true;
					}
				}
			});
			return flag;
		}

		$(function() {
			$("#checkInId").bind("click", function() {
				var campusId=$("#campusId").val();
				var classesId=$("#classesId").val();
				var teacherId=$("#teacherId").val();

				if(campusId=="-1"){
					top.Dialog.alert("请选择校区");
					return ;
				}
				if(classesId=="-1"){
					top.Dialog.alert("请选择班级");
					return ;
				}

				if(isCheckIn(classesId,teacherId,campusId)){
					top.Dialog.alert("所选校区班级今日已经签到")
					return false;
				}
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwTeacher/checkIn",
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
					<input type="hidden"  value="${jwTeacherAttendance.teacherId}" name="teacherId" placeholder="" />
					${teacher.name}
				</td>
			</tr>
			<tr>
				<td><label>校区：</label></td>
				<td align="">
					<select class="date_form" name="campusId" id="campusId" onchange="updateClasses();">
						<option value="-1">请选择</option>
						<c:forEach items="${campuses}" var="campuse">
							<option value="${campuse.id}">${campuse.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>班级：</label></td>
				<td align="">
					<select class="date_form" id="classesId" name="classesId">
						<option value="-1">请选择</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>备注：</label></td>
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