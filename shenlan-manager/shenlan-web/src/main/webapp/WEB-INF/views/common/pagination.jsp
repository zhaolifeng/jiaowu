<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title></title>
    <style>
        .mun{
            height: 24px;
            width: 25px;
            float:left;
            text-align:center;
            padding-top: 5px;
            font-family:"microsoft yahei";
            font-size:14.5px;
        }
    </style>
</head>


<body >

<%
    int pageSize;
    int currPage;
    int total;
    String url="";

    int step=10;
    int group;
    int curGroup;
    int curGroupEnd;
    int pageCount;
    int curGroupStart;
//    String url= request.getRequestURI();;
//    url=url.substring(url.lastIndexOf("views")+5,url.indexOf("."));


    url=(String)request.getAttribute("url");
    //获取用户想要显示的页数：
    String integer=(String)request.getAttribute("currPage");
    if(integer==null){
        integer="1";
    }

    try{currPage=Integer.parseInt(integer);
    }catch(NumberFormatException e){
        currPage=1;
    }

    String pageint=(String)request.getAttribute("pageSize");
    if(pageint==null){
        pageSize=20;
    }

    try{pageSize=Integer.parseInt(pageint);
    }catch(NumberFormatException e){
        pageSize=20;
    }

    String count= (String) request.getAttribute("total");
    if(count==null){
        total=0;
    }

    try{
        total=Integer.parseInt(count);
    }catch(NumberFormatException e){
        total=0;
    }

    //计算分页后的总数
    pageCount=(total%pageSize==0)?(total/pageSize):(total/pageSize+1);
%>
    <%--<%=pageCount+"pageCount"%>--%>
<%--<%=total+"total"%>--%>
<%
    if(pageCount>1){
        %>
<div style="width:98%;background-color: #F9F9F9;padding:10px;float: left;border:1px solid #E4E4E4;height:30px;width: 96%;margin-left:13px;" align="right">
    <div style="float:right;padding-right:50px;">
        <div class="page">
<%
        String param="";
        Map<String, String[]> map =new HashMap(request.getParameterMap());
        if(map.size()>=1){
        map.remove("currPage");
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        Iterator<Map.Entry<String, String[]>> it = set.iterator();
        while (it.hasNext()) {
        Map.Entry<String, String[]> entry = it.next();
        String key=entry.getKey();
        String value="";
        for (String i : entry.getValue()) {
        value+=i;
        }
        param=param+key+"="+value+"&";
        }
        %>
        <%--<%=param+"|map"%>--%>
        <%
        //        param=param.substring(0,param.length()-1);
        }

        %>






        <%	//根据pageCount的值显示每一页的数字并附加上相应的超链接
            //计算总共分几组
            group=(pageCount%step==0)?(pageCount/step):(pageCount/step+1);
            //计算每组的起始数和终止数
            curGroup=(currPage%step>0)?(currPage/step+1):(currPage/step);
        %>
        <%--<%=currPage+"currPage"%>--%>
        <%--<%=step+"step"%>--%>
        <%--<%=group+"group"%>--%>
        <%--<%=curGroup+"curGroup"%>--%>
        <%
            //    123 456 78
            curGroupEnd=(step*curGroup<=pageCount)?(curGroup*step):(pageCount);
            curGroupStart=curGroupEnd-step+1;
            if(curGroupStart<=0){
                curGroupStart=1;
            }
        %>
        <%
            if(param!=null&&!param.equals("")){
                url= url+"?"+param+"currPage=";
            }else{
                url=url+"?currPage=";
            }
        %>

        <%--<%=param+"||||Param<br>"%>--%>
        <%--<%=url+"||||url"%>--%>
        <%
            if(currPage>1){
        %>

        <%="<div style='height:25px;float:left;padding-top:5px;margin-left:10px;'><a href='"+url+(currPage-1)+"'>上一页</a></div>"%>
        <%
        }else {
        %>
        <%="<div style='height:25px;float:left;display:none;'>上一页</div>"%>
        <%
            }
        %>
        <%--<%=curGroupStart+"curGroupStart"%>--%>
        <%--<%=curGroupEnd+"curGroupEnd"%>--%>
        <%
            for(int i=curGroupStart;i<=curGroupEnd;i++){
                if(currPage==i){
        %>
        <%="<div class='mun' style='border: 1px solid #ccc;background-color:#eaeaea;'><a href='"+url+i+"'>"+i+"</a></div>"%>
        <%
        }else{
        %>
        <%="<div class='mun'><a href='"+url+i+"' style='color:black'>"+i+"</a></div>"%>
        <%
                }
            }
        %>

        <%
            if(currPage<pageCount){
        %>
        <%="<div style='height:25px;float:left;padding-top:5px'><a href='"+url+(currPage+1)+"'>下一页</a></div>"%>
        <%
        }else {
        %>
        <%="<div style='height:25px;float:left;display:none'>下一页</div>"%>
        <%
            }
        %>
        <%="<div style='height:25px;float:left;padding-top:0px;'>&nbsp;&nbsp;到<select id='goPage' style='margin:0 5px;width:50px;' >"%>
        <%
            for(int i=1;i<=pageCount;i++){
                if(currPage==i){
        %>
        <%="<option value="+i+" selected>"+i+"</option>"%>
        <%
        }else{
        %>

        <%="<option value="+i+">"+i+"</option>"%>
        <%
            }
        %>

        <%
            }
        %>
        <%="</select>页 <a href='#' onClick=\"goPage('"+url+"')\">确定</a>&nbsp;共"+total+"条</div>"%>
        </div>
    </div>
<%
    }
%>

<%--末页--%>

    </div>
</body>
<script type="text/javascript">
    function goPage(url){
        var  myselect=document.getElementById("goPage");
        var index=myselect.selectedIndex ;
        var selectVal= myselect.options[index].value;
        window.location.href=url+selectVal;

    }
</script>
</html>