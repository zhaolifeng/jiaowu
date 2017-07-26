<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>数据统计</title>
    <%--<link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>--%>
    <%--<link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>--%>
    <script type="text/javascript" src="${ctx }/static/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".dx-left li").click(function(){
                try{
                var url=$(this).children("div").text();
                var icons0=$(this).children("span").text().split("|");
                $(".dx-left li").each(function(){
                    var icons=$(this).children("span").text().split("|");
                    $(this).children("img").attr("src",icons[1]);
                });
                $(".dx-left li").removeClass("current");
                $(this).addClass("current");
                $(this).children("img").attr("src",icons0[0]);

                    <%--$.ajax({--%>
                        <%--url:"${ctx }/"+url,--%>
                        <%--dataType:"html",--%>
                        <%--success:function(result)--%>
                        <%--{--%>
                            <%--$(".content").html("");--%>
                            <%--$(".content").html(result);--%>
                        <%--}--%>
                    <%--});--%>
                    try{
                        var url=$(this).children("div").text();
                        $("#frame").attr("src",url);
                    }catch (e){
                        alert(e.message);
                    }
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
        <%--<div class="dx-left">--%>
            <ul>
                <c:forEach items="${menusList}" var="menus" varStatus="status">
                <c:choose>
                    <c:when test="${status.index==0}">
                        <li class="current"><div class="murl" style="display: none;">${menus.url}</div></di><span style="display:none">${ctx }/static/images/${menus.sicon}|${ctx }/static/images/${menus.icon}</span><img src="${ctx }/static/images/${menus.sicon}" alt="">${menus.name }</li>
                    </c:when>
                    <c:otherwise>
                        <li><div class="murl" style="display: none;">${menus.url}</div></di><span style="display:none">${ctx }/static/images/${menus.sicon}|${ctx }/static/images/${menus.icon}</span><img src="${ctx }/static/images/${menus.icon}" alt="">${menus.name }</li>
                    </c:otherwise>
                </c:choose>
                </c:forEach>
            </ul>
        <%--</div>--%>
</body>
</html>
