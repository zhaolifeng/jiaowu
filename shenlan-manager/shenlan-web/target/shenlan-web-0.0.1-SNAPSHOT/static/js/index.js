$(function(){
	//选项卡初始化
	var t1=new Tab();
	t1.init('.nw-foot','a','.nw-body','.dx-right');

});

//分页
var nums = 5; //每页出现的数量
var pages = 18;//    Math.ceil(data.length/nums); //得到总页数
var thisDate = function(curr){
	//此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
	var str = '', last = curr*nums - 1;
//        last = last >= data.length ? (data.length-1) : last;
//        for(var i = (curr*nums - nums); i <= last; i++){
//            str += '<li>'+ data[i] +'</li>';
//        }
	return str;
};
laypage({
	cont: 'dx_data',
	pages: pages,
	skip: true,
	skin: 'yahei',
	groups: 7,
	jump: function(obj){
		document.getElementById('dx_list').innerHTML = thisDate(obj.curr);
	}
});
