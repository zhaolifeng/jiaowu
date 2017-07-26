<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/dialog.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/lyz.calendar.css"/>
    <title>首页</title>
</head>
<body class="nw-body">
<div id="forlogin" class="dialog-info">
    <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
        <input type="hidden" id="id" name="id" value="${userInfo.id}">
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>姓名：</label></td>
            <td style="width:50%">
                ${userInfo.name}
            </td>
        </tr>
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>性别：</label></td>
            <c:if test="${userInfo.sex == 'female'}">
                <td>女</td>
            </c:if>
            <c:if test="${userInfo.sex == 'male'}">
                <td>男</td>
            </c:if>
        </tr>
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>年龄：</label></td>
            <td>
                ${userInfo.age}
            </td>
        </tr>
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>地址：</label></td>
            <td>
                ${userInfo.address}
            </td>
        </tr>
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>手机号：</label></td>
            <td>
                ${userInfo.mobile}
            </td>
        </tr>
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>手机验证状态：</label></td>
            <c:if test="${userInfo.checkMobile == 'mobile_no'}">
                <td>未验证</td>
            </c:if>
            <c:if test="${userInfo.checkMobile == 'mobile_yes'}">
                <td>已验证</td>
            </c:if>
        </tr>
        <tr>
            <td style="width:50%;float:right;margin-left:100px"><label>邮箱：</label></td>
            <td>
                ${userInfo.mail}
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
</html>