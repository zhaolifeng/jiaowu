<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<div class="dx-left">
    <ul>
        <a href="javascript:;"><li class="current"><img src="${ctx }/static/images/u67.png" alt="">首页</li></a>
        <c:forEach var="m" items="${menuList}">
			<a href="${ctx }/${m.url}"><li><img src="${ctx }/static/images/${m.icon}" alt="">${m.name }</li></a>
		</c:forEach>
    </ul>
</div>