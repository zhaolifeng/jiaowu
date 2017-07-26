<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>菜单展示</title>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/index.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx }/static/css/common.css"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery.min.js"></script>
    <script type="text/javascript">
    $(function(){
        //alert(${menuId});
        $("#menusId").children().removeClass("current");
        $("#"+${menuId}+"").addClass("current");
        if(${menuId}==-1)
        {
        	$("#hId").attr("src","${ctx }/static/images/u67.png");//u67.png是首页选中时候的图片
        }
     })
    </script>
</head>
<body>
        <div class="dx-left">
            <ul  id = "menusId">
            	<a id=-1 href="${ctx }/homepage/list?id=-1" class=""><li><img id="hId" src="${ctx }/static/images/u255.png" alt="" />首页</li></a>
       			 <c:forEach var="m" items="${menuList}">
					<a href="${ctx }/${m.url}?id=${m.id } " id="${m.id }" ><li><img src="${ctx }/static/images/${m.uicon}" alt="">${m.name }</li></a>
				</c:forEach>              
            </ul>
        </div>
        
</body>
</html>
