<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css"
		  href="${ctx }/static/css/index.css" />
	<link rel="stylesheet" type="text/css"
		  href="${ctx }/static/css/common.css" />
	<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
	<script type="text/javascript">

		//查询按钮
		function searchTask(){
			document.forms[0].action="${ctx }/integralCenter/list";
			document.forms[0].submit();
		}
	</script>
</head>
<body class="nw-body">
<div class="dx-right-content">
	<%--<div class="dx-search">--%>
	<%--<form method="post" name="searchTaskForm" id="searchTaskForm" action="${ctx }/integralCenter/list">--%>
	<%--<div class="dx-lab1">--%>
	<%--&lt;%&ndash;<td><label>：</label></td>&ndash;%&gt;--%>
	<%--&lt;%&ndash;<td><select class="date_form" id="platform" name="platform">&ndash;%&gt;--%>
	<%--&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
	<%--&lt;%&ndash;<c:forEach var="platform" items="${platformList}" varStatus="s1">&ndash;%&gt;--%>
	<%--&lt;%&ndash;<option value="${platform.code}" <c:if test="${integralTask.platform == platform.code}"> selected="selected"</c:if>>${platform.name}</option>&ndash;%&gt;--%>
	<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
	<%--&lt;%&ndash;</select>&ndash;%&gt;--%>
	<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
	<%--&lt;%&ndash;<button class="dx-find" onclick="searchTask();">查询</button>&ndash;%&gt;--%>
	<%--<a class="dx-find-a" href="javascript:void(0);" onclick="addTeacher();">添加老师</a>--%>
	<%--</div>--%>
	<%--</form>--%>
	<%--</div>--%>
	<div class="dx-cell">
		<table>
			<tr class="table_head">
				<th width="8%">班级</th>
				<th width="5%">课程</th>
				<th width="5%">签到时间</th>
			</tr>
			<tbody>
			<c:forEach items="${checkIns}" var="listObj">
				<tr style="text-align: center">
					<td >
						<c:forEach items="${classeses}" var="listObj1">
							<c:if test="${listObj.classesId==listObj1.classesId}">
								${listObj1.classesName}
							</c:if>
						</c:forEach>
					</td>
					<td >
						<c:forEach items="${classeses}" var="listObj1">
							<c:if test="${listObj.courseId==listObj1.courseId}">
								${listObj1.courseName}
							</c:if>
						</c:forEach>
					</td>
					<td >
						<fmt:formatDate value="${listObj.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="../common/pagination.jsp" />
</body>
<script type="text/javascript" src="${ctx }/static/js/tab.js"></script>
<script type="text/javascript" src="${ctx }/static/js/laypage.js"></script>
<script type="text/javascript" src="${ctx }/static/js/index.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8"></script>
</html>