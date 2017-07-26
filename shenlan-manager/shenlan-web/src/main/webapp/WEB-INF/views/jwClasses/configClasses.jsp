<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/dialog.css" />
	<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/lyz.calendar.css" />
	<script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/lengthControl.js"></script>
	<script type="text/javascript">
		$(function() {

			$(".txtBeginDate").calendar(
//					{
//				controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
//				speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
//				complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
//				readonly: true                                      // 目标对象是否设为只读，默认：true
////				lowerLimit: new Date()
//			}
			);
			$(".txtEndDate").calendar();

			$("#classesConfigSubmitId").bind("click", function() {
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

				$("#classesConfigSubmitId").attr("disabled","disabled").val("提交中...");
				$.ajax({
					cache : true,
					type : "POST",
					url : "${ctx }/jwClasses/addClassesConfig",
					data : $('#classesConfig').serialize(),
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
	<script type="text/javascript">
		function loadRes(){
			var startTimeParam=$("#startTime").val().trim();
			var endTimeParam=$("#endTime").val().trim()
			if ("" == startTimeParam || null == startTimeParam) {
				top.Dialog.alert("请输入开始时间");
				return false;
			}
			if ("" == endTimeParam || null == endTimeParam) {
				top.Dialog.alert("请输入结束时间");
				return false;
			}
			$("#next").attr("disabled","disabled").val("加载数据中...");
			var flag=false;
			$.ajax({
				cache : true,
				type : "POST",
				url : "${ctx }/jwClasses/selectRoom",
				data : $('#classesConfig').serialize(),
				async : false,//false 为同步
				error : function(request) {
					$("#next").attr("disabled","").val("下一步");
				},
				success : function(data) {
					var obj = eval('(' + data + ')');
					var teachers=obj.teachers;
					var classRooms=obj.classRooms;
					var teachersStr="";
					$.each(teachers,function(index,item){
						teachersStr=teachersStr+'<option value='+item.id+'>'+item.name+'</option>';
					});
					$("#teacherId").html(teachersStr);
					var classRoomStr="";
					$.each(classRooms,function(index,item){
						classRoomStr=classRoomStr+'<option value='+item.id+'>'+item.name+'</option>';
					});
					$("#classRoomId").html(classRoomStr);
					flag=true;
				}
			});

            if(flag){
				$("#datetime").css("display","none");
				$("#teacherRoom").css("display","")
			}
		}

		function settime(){
			$("#next").attr("disabled","").val("下一步");
			$("#teacherRoom").css("display","none")
			$("#datetime").css("display","");
		}
	</script>
	<title>校区</title>
</head>
<body>
<div id="forlogin" class="dialog-info">
	<form method="post"  id="classesConfig">
		<input type="hidden" value="${classes.id}" name="classesId">
		<input type="hidden" value="${classes.courseId}" name="courseId">
		<input type="hidden" value="${classes.campusId}" name="campusId">
		<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666"  id="datetime">
		<tr>
			<td><label>开始日期：</label></td>
			<td><input class="txtBeginDate"  id="startTime" name="startTimeParam"  /></td>
		</tr>
		<tr>
			<td><label>结束日期：</label></td>
			<td><input class="txtEndDate"  id="endTime" name="endTimeParam"  /></td>
		</tr>
		<tr>
			<td><label>上课日期：</label></td>
			<td>
				<select id="classDate" name="classDate" >
					<option value="0">每日</option>
					<option value="1">周一</option>
					<option value="2">周二</option>
					<option value="3">周三</option>
					<option value="4">周四</option>
					<option value="5">周五</option>
					<option value="6">周六</option>
					<option value="7">周日</option>
				</select>
			</td>
		</tr>

		<tr>
			<td><label>上课时段：</label></td>
			<td>
				<select id="classTime" name="classTime">
					<option value="1">8:00～10:00</option>
					<option value="2">10:00～12:00</option>
					<option value="3">13:30～15:30</option>
					<option value="4">15:30～17:30</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left" style="padding-left: 160px;">
				<input class="buttonStyle sure" type="button" value="下一步" onclick="loadRes()" id="next">
			</td>
		</tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666" id="teacherRoom" style="display: none">
			<tr>
				<td><label>老师：</label></td>
				<td>
					<select id="teacherId" name="teacherId">
					</select>
				</td>
			</tr>
			<tr>
				<td><label>教室：</label></td>
				<td>
					<select id="classRoomId" name="classRoomId">
					</select>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="left" style="padding-left: 160px;">
					<input class="buttonStyle sure" type="button" value="上一步" onclick="settime()">&nbsp;&nbsp;&nbsp;&nbsp;<input class="buttonStyle sure" type="button" value="确定" id="classesConfigSubmitId">
				</td>
			</tr>
	</table>
	</form>
</div>
</body>
</html>