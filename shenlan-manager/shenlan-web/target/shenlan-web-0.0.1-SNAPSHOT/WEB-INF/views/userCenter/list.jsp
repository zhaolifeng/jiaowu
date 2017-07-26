<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
        td{width:70px;word-break:break-all;word-wrap:break-word;}
    </style>
</head>
<body>
<form method="post" name="excelForm" id="excelForm">
    <div class="dx-right-content">
            <div class="dx-search">
                <form method="post" name="sousuo" id="sousuo" action="${ctx }/userCenter/query">
                    <div class="dx-lab2">
                        <label id="name">&nbsp;用户名：</label><input class="date_form" id="userName" name="userName"
                                                                  value="${userInfo.userName}" type="text"
                                                                  style="width:160px">
                        <label id="platforms">注册平台：</label>
                        <select  class="date_form" name="platform" id="platform">
                            <option value="">全部</option>
                            <c:forEach var="platform" items="${platformList}" varStatus="s1">
                                <option value="${platform.code}" <c:if
                                        test="${userInfo.platform == platform.code}"> selected="selected"</c:if>>${platform.name}</option>
                            </c:forEach>
                        </select>
                        <label id="userStatus">状态：</label>
                        <select class="date_form" name="status" id="status">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${userInfo.status == 1}"> selected="selected"</c:if>>正常
                            </option>
                            <option value="0" <c:if test="${userInfo.status ==0}"> selected="selected"</c:if>>黑名单
                            </option>
                        </select>
                    </div>
                    <div class="dx-lab2" style="padding-top: 10px;">
                        <label>&nbsp;手机是否验证：</label>
                        <select class="date_form" name="checkMobile" id="checkMobile" style="width:105px">
                            <option value="">全部</option>
                            <c:forEach var="check" items="${checkMobileList}" varStatus="s1">
                                <option value="${check.code}" <c:if
                                        test="${userInfo.checkMobile == check.code}"> selected="selected"</c:if>>${check.name}</option>
                            </c:forEach>
                        </select>
                        <label>起始日期：</label>
                        <input class="txtBeginDate date_form" id="createTime" name="createTime"
                               value="<fmt:formatDate  value="${userInfo.createTime}" pattern="yyyy-MM-dd" />"/>
                        <label>结束日期：</label>
                        <input class="txtEndDate date_form" id="upateTime" name="upateTime"
                               value="<fmt:formatDate  value="${userInfo.upateTime}"  pattern="yyyy-MM-dd" />"/>
                        <button class="dx-find" onclick="searchUser();">查询</button>
                        <a class="dx-find-a" href="javascript:void(0);" onclick="excel();">导出excel</a>
                    </div>
                </form>
            </div>
                <div class="dx-cell">
                    <table >
                        <tr >
                            <th width="25%">注册用户总数：${TotalUserCount}人 &nbsp; &nbsp; &nbsp;当前黑名单用户数：${TotalForbiddenUserCount}人</th>
                        </tr>
                    </table>
                    <table>
                        <tr style="background-color: #2B96DE;color: #ffffff;">
                            <th width="9%">用户名</th>
                            <th width="7%">注册平台</th>
                            <th width="7%">用户状态</th>
                            <th width="7%">手机验证</th>
                            <th width="7%">用户积分</th>
                            <th width="7%">登陆次数</th>
                            <th width="7%">用户积分</th>
                            <th width="15%">注册时间</th>
                            <th width="15%">最后登录时间</th>
                            <th width="18%">操作</th>
                        </tr>
                        <c:forEach var="map" items="${pageList.data}">
                            <tr>
                                <td>${map.userName}</td>
                                <c:if test="${map.platform == 'plat_001'}">
                                    <td>portal</td>
                                </c:if>
                                <c:if test="${map.platform == 'plat_002'}">
                                    <td>app</td>
                                </c:if>
                                <c:if test="${map.platform != 'plat_001'}">
                                    <c:if test="${map.platform != 'plat_002'}">
                                        <td></td>
                                    </c:if>
                                </c:if>
                                <c:if test="${map.status == '1'}">
                                    <td>正常</td>
                                </c:if>
                                <c:if test="${map.status == '0'}">
                                    <td>黑名单</td>
                                </c:if>
                                <c:if test="${empty map.status}">
                                    <td></td>
                                </c:if>
                                <c:if test="${map.checkMobile == 'mobile_no'}">
                                    <td>未验证</td>
                                </c:if>
                                <c:if test="${map.checkMobile == 'mobile_yes'}">
                                    <td>已验证</td>
                                </c:if>
                                <td><a href="javascript:;" onclick="show(${map.id})">查看</a></td>
                                <td>${map.loginCount}</td>
                                <td>${map.totalIntegral}</td>
                                <td><fmt:formatDate value="${map.createTime}" type="both"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${map.upateTime}" type="both"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>
                                    <c:if test="${map.status == '1'}">
                                        <a href="javascript:;" onclick="changeStatus(${map.id })">加入黑名单</a>|
                                    </c:if>
                                    <c:if test="${map.status == '0'}">
                                        <a href="javascript:;" onclick="changeUseStatus(${map.id })">移除黑名单</a>|
                                    </c:if>
                                    <a href="javascript:;" onclick="userdetail(${map.id })">详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
    </div>
            <jsp:include page="../common/pagination.jsp"/>
</form>
</body>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8"></script>
<script language="javascript">

    function searchUser() {
        document.forms[0].action = "${ctx }/userCenter/query";
        document.forms[0].submit();
    }

    function changeStatus(userId) {
        top.Dialog.confirm("确定要加入黑名单?", function () {
            window.location.href = '${ctx }/userCenter/addForbid?userId=' + userId;
        })
    }

    function changeUseStatus(userId) {
        top.Dialog.confirm("确定要移除黑名单，恢复正常吗?", function () {
            window.location.href = '${ctx }/userCenter/moveForbid?userId=' + userId;
        })
    }

    function show(taskId) {

        document.forms[0].action = "${ctx }/userCenter/getUserIntegralById?id=" + taskId;
        document.forms[0].submit();
    }

    function excel() {
        var createTime = $("#createTime").val();
        var upateTime = $("#upateTime").val();
        var startD = new Date(Date.parse(createTime.replace(/-/g, "/")));
        var endD = new Date(Date.parse(upateTime.replace(/-/g, "/")));
        var days = parseInt((endD.getTime() - startD.getTime()) / (1000 * 60 * 60 * 24));
        if ((createTime > upateTime) && (upateTime != null) && ('' != upateTime)) {
            top.Dialog.alert("导出开始时间不能大于结束时间！！");
            return;
        } else if (days > 30) {
                top.Dialog.alert("导出数据日期范围应在一个月之内！！");
                return false;
        }else if(createTime==null||upateTime==null||('' == createTime)||('' == upateTime)){
            top.Dialog.alert("请设置起始和结束日期后再查询并导出");
            return;
        }

        $("#excelForm")[0].action = "${ctx }/userCenter/DownLoadFiles";
        $("#excelForm")[0].method = 'post';
        $("#excelForm")[0].submit();
        $("#excelForm")[0].action = "";
        out.clear();
        out = pageContext.pushBody();
    }
    $(".txtBeginDate").calendar({
        controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
        speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
        complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
        readonly: true                                       // 目标对象是否设为只读，默认：true
//             upperLimit: new Date(),                               // 日期上限，默认：NaN(不限制)
//             lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
    });
    $(".txtEndDate").calendar();

    $(function () {
        var createTime = $("#createTime").val();
        var upateTime = $("#upateTime").val();
        if ((createTime>upateTime)&&(upateTime!=null)&&(''!=upateTime)) {
            top.Dialog.alert("开始时间不能大于结束时间！！");
            return;
        }
    })
</script>
</html>
