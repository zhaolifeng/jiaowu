<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>积分变化列表</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/dialog.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/lyz.calendar.css"/>
</head>
<body>
<div class="dx-right block">
    <div class="dx-cell">
        <form method="post" name="sousuo" id="sousuo" action="${ctx }/userCenter/query">
            <div class="dx-lab1">
                <label>积分增减：</label>
                <select name="isAdd" id="isAdd">
                    <option value="">全部</option>
                    <c:forEach var="integral" items="${integralList}" varStatus="s1">
                        <option value="${integral.code}" <c:if
                                test="${userInfo.isAdd == integral.code}"> selected="selected"</c:if>>${integral.name}</option>
                    </c:forEach>
                </select>

            </div>
            <div class="dx-lab2">
                <button class="dx-find" onclick="searchIntegral(${userInfo.id});">查询</button>
            </div>
        </form>
        <table>
            <tr>
                <th width="25%">用户名：</th>
                <th width="25%">${userInfo.userName}</th>
                <th width="25%">当前积分总额：</th>
                <th width="25%">${userInfo.totalIntegral}</th>
            </tr>
        </table>
        <table>
            <input type="hidden" id="id" name="id" value="${userInfo.id}">
            <tr>
                <th width="25%">所属平台</th>
                <th width="25%">任务名称</th>
                <th width="25%">完成时间</th>
                <th width="25%">积分值变化</th>
            </tr>
            <c:forEach var="map" items="${IntegralRecordList}">
                <tr>
                    <c:if test="${map.platform == 'plat_001'}">
                        <td>portal</td>
                    </c:if>
                    <c:if test="${map.platform == 'plat_002'}">
                        <td>app</td>
                    </c:if>
                    <td>
                            ${map.name}
                    </td>
                    <td><fmt:formatDate value="${map.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <c:if test="${map.isAdd == '01'}">
                        <td>+${map.integral}</td>
                    </c:if>
                    <c:if test="${map.isAdd == '02'}">
                        <td>-${map.integral}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
<script language="javascript">
    function searchIntegral(id) {
        document.forms[0].action = "${ctx }/userCenter/getUserIntegralById?id=" + id;
        document.forms[0].submit();
    }
</script>
</html>