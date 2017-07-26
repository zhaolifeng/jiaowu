<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>深蓝教务</title>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery.min.js"></script>
    <script type="text/javascript">
        if(this.parent!=this){
            this.parent.window.location.href='./login';
        }
    </script>
</head>
<body>
    <div class="dx-all">
        <div class="dx-head">管理平台</div>
        <div class="dx-cont">
            <div class="dx-login">
                <h1>深蓝教务管理平台</h1>
                
                <form  id="aa" name=lforn method="post" action="${ctx }/login">
	                <input type="text" placeholder="请输入用户名" name="username">
	                <input type="password" placeholder="请输入密码" name="password">
                
                <span class="dx-error">${message }</span>
                <%--<span class="forget">忘记密码</span>--%>
                <button class="dx-btn">登录</button>
                <!-- <input name="submit" type="submit"  value="登录" /> -->
                </form>
            </div>
        </div>
    </div>
</body>
<!--<script type="text/javascript" src="js/check.js"></script>-->
</html>