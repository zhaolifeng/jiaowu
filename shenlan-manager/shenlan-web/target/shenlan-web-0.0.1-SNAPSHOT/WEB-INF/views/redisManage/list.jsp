<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/dialog.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/lyz.calendar.css"/>
    <script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
    <style>
        td {
            width: 70px;
            word-break: break-all;
            word-wrap: break-word;
        }
    </style>
</head>
<body>
<div class="dx-right-content">
    <div class="dx-search">
        <form method="post" name="sousuo" id="sousuo" action="${ctx }/redisCenter/query">
            <div class="dx-lab2">
                <label>&nbsp;&nbsp;&nbsp;&nbsp;key值：</label><input class="date_form" id="redisKey" name="redisKey"
                                                                   value="${redisKey}" type="text"
                                                                   style="width:160px">
                <button class="dx-find" onclick="searchKey();">查询</button>
            </div>
            <div style="margin-top:40px">
                <label id="name">&nbsp;value值：</label>&nbsp;&nbsp;${value}
            </div>
        </form>
    </div>
</div>
<jsp:include page="../common/pagination.jsp"/>
</body>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8"></script>
<script language="javascript">

    function searchKey() {
        document.forms[0].action = "${ctx }/redisCenter/query";
        document.forms[0].submit();
    }

</script>
</html>
