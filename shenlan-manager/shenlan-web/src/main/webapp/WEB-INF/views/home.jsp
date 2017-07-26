<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>数据统计</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css" />
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.5.1.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx }/static/css/lyz.calendar.css" />
    <script type="text/javascript" src="${ctx }/static/js/lyz.calendar.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/dateselectupper.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var series = {};

            chart = new Highcharts.Chart(options);
            chart.addSeries(series);

            chart1 = new Highcharts.Chart(options1);
            chart1.addSeries(series);

            chart2 = new Highcharts.Chart(options2);
            chart2.addSeries(series);

            chart3 = new Highcharts.Chart(options3);
            chart3.addSeries(series);

            $("#uvpvsub").click(function(){

                var timeBegin = $("#timeBegin").val();
                var timeEnd = $("#timeEnd").val();
                var pageCode = $("#pageCode").val();
                var startD = new Date(Date.parse(timeBegin.replace(/-/g, "/")));
                var endD = new Date(Date.parse(timeEnd.replace(/-/g, "/")));
                var days = parseInt((endD.getTime() - startD.getTime()) / (1000 * 60 * 60 * 24));

                if (timeBegin > timeEnd) {
                    top.Dialog.alert("开始时间不能大于结束时间！！");
                    return;
                }
                if (days > 30) {
                    top.Dialog.alert("查询间隔不能大于30天！！");
                    return false;
                }

//                chart.showLoading('正在加载数据...');
                loadUserData(timeBegin,timeEnd,"${ctx}");
                loadPvUvData(timeBegin,timeEnd,pageCode,"${ctx}");
            });
            var timeBegin = $("#timeBegin").val();
            var timeEnd = $("#timeEnd").val();
            var pageCodeAndName = $("#pageCode").val();
            var pageCode = pageCodeAndName.split("|")[0];
            loadUserData(timeBegin,timeEnd,"${ctx}");
            loadPvUvData(timeBegin,timeEnd,pageCode,"${ctx}");
            loadNetTypeData("${ctx}");
            loadBrowserTypeData("${ctx}");
        });

    </script>
    <%
        Date now = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -10);  //设置为前10天
        dBefore = calendar.getTime();   //得到前10天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天

        Date oneDayBefore = new Date();
        Calendar calendar1 = Calendar.getInstance(); //得到日历
        calendar1.setTime(now);//把当前时间赋给日历
        calendar1.add(Calendar.DAY_OF_MONTH, -1);  //设置为前1天
        oneDayBefore = calendar1.getTime();   //得到前1天的时间
        String defaultEndDate = sdf.format(oneDayBefore);    //格式化前一天
    %>
</head>
<body>
<div  style="width:99%" align="center">
    <table border="0" border="0" cellpadding="0" cellspacing="0" style="font-size:14.5px;font-family: 'Microsoft Yahei'">
        <tr style="height:80px;" align="center">
            <td colspan="2" class="dx-lab2">
                <label>起始日期:</label>
                <input class="txtEndDate date_form"  id="timeBegin" name="timeBegin" value="<%=defaultStartDate%>" />
                <label>结束日期:</label>
                <input class="txtBeginDate date_form" id="timeEnd" name="imeEnd" value="<%=defaultEndDate%>" />

                <label>统计页面:</label>
                <select  id="pageCode" style="height:30px;width: 120px;">
                    <c:forEach var="pageCode" items="${pageCodeList}">
                        <option value="${pageCode.code}@${pageCode.name}">${pageCode.name}</option>
                    </c:forEach>
                </select>
                <button class="dx-find" type="button" id="uvpvsub">查询</button>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <div class="dx-view" id="container1" style="width:100%">

                </div>
            </td>
            <td width="50%">
                <div class="dx-view" id="container3" style="width:100%">

                </div>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <div class="dx-view" id="container2" style="width:100%">
                </div>
            </td>
            <td width="50%">
                <div class="dx-view" id="container4" style="width:100%">

                </div>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript" src="${ctx }/static/js/zDrag.js"></script>
<script type="text/javascript" src="${ctx }/static/js/zDialog.js"></script>
<script type="text/javascript" src="${ctx }/static/js/diolog.js" charset="utf-8" ></script>
<script type="text/javascript" src="${ctx }/static/js/highcharts.js"></script>
<script type="text/javascript" src="${ctx }/static/js/chart.js" charset="utf-8"></script>
</html>