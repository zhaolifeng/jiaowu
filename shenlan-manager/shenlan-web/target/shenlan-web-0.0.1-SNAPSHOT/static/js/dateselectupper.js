$(function () {

    var preDate = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);  //前一天

    $(".txtBeginDate").calendar({
        controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
        speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
        complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
        readonly: true,                                      // 目标对象是否设为只读，默认：true
        upperLimit: new Date()
    });
    $(".txtEndDate").calendar();
});