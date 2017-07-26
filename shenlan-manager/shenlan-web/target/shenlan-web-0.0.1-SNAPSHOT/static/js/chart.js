var chart;
// 用户数 折线图
var options = {
    chart: {
        renderTo: 'container1',
        type:'line',
        height: 400,
        width:530
    },
    title: {
        text: '东行记用户数',
        x: 0 //center
    },
    xAxis: {
        categories: []
    },
    yAxis: {
        title: { text: '用户数' },
        min: 0
    },
    //series: [{}]
    series: [{
        name: '新增用户数',
        data: []
    }, {
        name: '累计用户数',
        data: []
    }]
};

// uv/pv折线图
var options1 = {
    chart: {
        renderTo: 'container2',
        type:'line',
        height: 400,
        width:530
    },
    title: {
        text: 'pv&uv数',
        x: 0 //center
    },
    xAxis: {
        categories: []
    },
    yAxis: {
        title: { text: '' },
        min: 0
    },
    //series: [{}]
    series: [{
        name: 'pv',
        data: []
    }, {
        name: 'uv',
        data: []
    }]
};

// 网络类型饼图
var options2 = {
    chart: {
        renderTo: 'container3',
        height: 400,
        width:530
    },
    title: {
        text: '网络类型'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                color: '#666666',
                connectorColor: '#666666',
                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
            }
        }
    },
    series: [{
        type: 'pie',
        name: '占比',
        data: []
    }]
};

// 浏览器类型柱状图
var options3 = {
    chart : {
        renderTo : 'container4',
        type : 'bar',
        height: 400,
        width:530
    },
    title : {
        text : '浏览器版本'
    },
    xAxis : {
        categories : [],
        title : {
            text : ''
        }

    },
    yAxis : {
        min : 0,
        title : {
            text : ''
        }
    },
    legend: {
        enabled: false
    },
    tooltip : {
        formatter : function() {
            return '' + this.series.name + ': ' + this.y + '';
        }
    },
    credits : {
        enabled: false
    },
    plotOptions : {
        series : {
            stacking : 'normal'
        }
    },
    series : [ {
        name : '个',
        data : []
    } ]
};


// 获取user数据
function loadUserData(timeBegin,timeEnd,contextPath) {
    $.ajax({
        type: "post",
        url: contextPath+"/userCenter/queryUserDayCounts",
        dataType: "json",
        cache: false,
        data:{
            timeBegin:timeBegin,
            timeEnd:timeEnd
        },
        success: function (data) {
            var d = [];
            var d1 = [];
            var categories = [];
            if (data != null) {
                var userDayCount = data.userDayCount;
                var userDayCounts = data.userDayCounts;
                $.each(userDayCount, function (index, item) {
                    d.push(item.user_day_count);
                    categories.push(item.create_date);
                });
                $.each(userDayCounts, function (index, item) {
                    d1.push(item.user_day_counts);
                    categories.push(item.create_date);
                });
                chart = new Highcharts.Chart(options);
                chart.series[0].setData(d);
                chart.xAxis[0].setCategories(categories);
                chart.series[1].setData(d1);
            }
        },
        error: function () {
            alert("请求超时，请重试！");
        }
    });
}

// 获取pv/uv数据
function loadPvUvData(timeBegin,timeEnd,pageCode,contextPath) {
    $.ajax({
        type: "post",
        url: contextPath+"/dataStatistics/queryPVUVdataBypage",
        dataType: "json",
        cache: false,
        data:{
            timeBegin:timeBegin,
            timeEnd:timeEnd,
            pageCode:pageCode
        },
        success: function (data) {
            var d = [];
            var d1 = [];
            var categories = [];
            if (data != null) {
                var pvdataBypage = data.pvdataBypage;
                var uvdataBypage = data.uvdataBypage;
                $.each(pvdataBypage, function (index, item) {
                    d.push(item.pv_count);
                    categories.push(item.visit_date);
                });
                $.each(uvdataBypage, function (index, item) {
                    d1.push(item.uv_count);
                    //categories.push(item.visit_date);
                });
                var selectName=$("#pageCode").val().split("@")[1];
                var name={};
                name.text=selectName + "pv&uv";
                options1.title=name;
                options1.series[0].name=selectName+"pv";
                options1.series[1].name=selectName+"uv";

                chart = new Highcharts.Chart(options1);
                chart.series[0].setData(d);
                chart.xAxis[0].setCategories(categories);
                chart.series[1].setData(d1);
            }
        },
        error: function () {
            alert("请求超时，请重试！");
        }
    });
}

//获取网络类型数据
function loadNetTypeData(contextPath) {
    $.ajax({
        type: "post",
        url: contextPath+"/dataStatistics/queryNetType",
        dataType: "json",
        cache: false,
        success: function (data) {
            var datas = [];
            if (data != null) {
                $.each(data, function (index, item) {
                    var netType = ['机载wifi','地面网络']
                    datas.push([netType[index],item.net_count]);
                });
                chart = new Highcharts.Chart(options2);
                chart.series[0].setData(datas)
            }
        },
        error: function () {
            alert("请求超时，请重试！");
        }
    });
}

//获取浏览器类型
function loadBrowserTypeData(contextPath) {
    $.ajax({
        type: "post",
        url: contextPath+"/dataStatistics/queryBrowserVersion",
        dataType: "json",
        cache: false,
        success: function (data) {
            var datas = [];
            var categories = [];
            if (data != null) {
                $.each(data, function (index, item) {
                    datas.push(item.browser_count);
                    categories.push(item.browser_version);
                });
                chart = new Highcharts.Chart(options3);
                chart.series[0].setData(datas);
                chart.xAxis[0].setCategories(categories);
            }

        },
        error: function () {
            alert("请求超时，请重试！");
        }
    });
}