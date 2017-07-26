<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>数据统计</title>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/plugins/datePicker/WdatePicker.js" charset="gbk"/>
</head>
<body>
        <div class="dx-left">
            <ul>
                <a href="index.html"><li><img src="images/u255.png" alt="">首页</li></a>
                <a href="userCenter.html"><li><img src="images/u63.png" alt="">用户中心</li></a>
                <a href="contentManage.html"><li><img src="images/u65.png" alt="">内容管理</li></a>
                <a href="activeManage.html"><li><img src="images/u53.png" alt="">活动管理</li></a>
                <a href="integralCenter.html"><li><img src="images/u55.png" alt="">积分中心</li></a>
                <a href="advertManage.html"><li><img src="images/u57.png" alt="">广告管理</li></a>
                <a href="dataCount.html"><li><img src="images/u59.png" alt="">数据统计</li></a>
                <a href="javascript:;"   class="current"><li><img src="images/u164.png" alt="">管理员管理</li></a>
            </ul>
        </div>
       <div>
           <ul>
               <c:forEach items="${integralTaskList}"   var="listObj">
               <li>
                   ${listObj}
               </li>
               </c:forEach>
           </ul>
           <input type="text" name="orderPaidStartTime" id="orderPaidStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
       </div>
</body>
</html>
