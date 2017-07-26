<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>首页</title>
	<link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>
	<link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>
	<script type="text/javascript" src="${ctx }/static/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".dx-left li").click(function(){
				try{
					var url=$(this).children("div").text();
					var icons0=$(this).children("span").text().split("|");
					$(".dx-left li").each(function(index,element){
						var icons=$(this).children("span").text().split("|");
						$(this).children("img").attr("src",icons[1]);
						$(this).addClass("uncurrent");
					});
					$(".dx-left li").removeClass("current");
					$(this).addClass("current");
					$(this).children("img").attr("src",icons0[0]);
					var url=$(this).children("div").text();
					$("#frame").attr("src",url);
				}catch (e){
					alert(e.message);
				}
			});

			function initMenu(){
				var icons0=$(this).children("span").text().split("|");
				$(".dx-left li:first-child").addClass("current");
				$(".dx-left li:first-child").children("img").attr("src",icons0[1]);
			}
			initMenu.call();
		});
	</script>
</head>
<body>
<div class="dx-all">
	<div class="dx-head">
		<%--<img src="${ctx }/static/images/logo.png" class="full dx-logo"> --%>管理平台
		<span><%=request.getSession().getAttribute("userName")%>丨<a style="color: #fff;cursor: pointer" onclick="exitLogin();" id="dx_exit">退出</a></span></div>
	<div class="dx-clear">
		<div class="dx-left" style="background-color: #FFFFFF;padding-bottom:50px">
			<ul>
				<c:forEach items="${menusList}" var="menus" varStatus="status">
					<c:choose>
						<c:when test="${status.index==0}">
							<li class="current"><div class="murl" style="display: none;">${menus.url}</div></di><span style="display:none">${ctx }/static/images/${menus.icon}|${ctx }/static/images/${menus.sicon}</span><img src="${ctx }/static/images/${menus.icon}" alt="">${menus.name }</li>
						</c:when>
						<c:otherwise>
							<li class="uncurrent"><div class="murl" style="display: none;">${menus.url}</div></di><span style="display:none">${ctx }/static/images/${menus.sicon}|${ctx }/static/images/${menus.icon}</span><img src="${ctx }/static/images/${menus.icon}" alt="">${menus.name }</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		<div class="dx-right block" id="frameBox" align="center">
			<table style="height:950px;width: 100%;" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td >
						<iframe id="frame" name="frame" scrolling="auto" style="border:0px solid red" src="${ctx}/jwStudent/queryStudentClass" width="100%" height="100%"   frameborder="no">
						</iframe>
				    </td>
				</tr>
			</table>

			<%--<script type="text/javascript">--%>
				<%--function aa(){--%>
					<%--var newHeight = document.body.+20+ "px";--%>
					<%--alert(newHeight);--%>
					<%--window.parent.document.getElementById("frameBox").style.height = newHeight;--%>
<%--//					window.parent.document.getElementById("frame").style.height = newHeight;--%>
				<%--}--%>
			<%--</script>--%>

		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${ctx }/static/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8" ></script>
<script type="text/javascript" >

	/* 退出登录 */
	function exitLogin( ){
		Dialog.confirm('您确认要退出登录吗？',function(){
			window.location.href="${ctx}/logout";
		});
	}
</script>
</html>