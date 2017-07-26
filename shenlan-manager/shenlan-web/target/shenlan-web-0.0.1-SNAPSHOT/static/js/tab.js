/*选项卡组件
 *  一、初始化方法
 * 		var t1=new Tab();
		t1.init();
	二、初始化 init函数 4个基础参数
		参数1、按钮 父级    默认为class                 全称书写 如：'.oBtnParent'
		参数2、按钮子项     可以为class数组 或者 标签数组   全称书写 如：'li' 或者 '.oBtn'
		参数3、选项卡内容 父级   同1
		参数4、选项卡内容 子项   同2
	三、触发选项卡 类名规定
		1、选项卡按钮  选中状态 类样式为    	.active
		2、选项卡内容  选中状态 显示样式为     .block
	四、暂时只支持点击切换(后期增加功能)
 *
 */
$(function(){
	var t1=new Tab();
	t1.init('.div1','li','.div2','div');
})
	
function Tab(){};
	Tab.prototype.init=function(oP1,oels,oP2,dels){
		var _this=this;
		this.oParent1=$(oP1);
		this.oParent2=$(oP2);
		this.aBtn=this.oParent1.find(oels);
		this.aDiv=this.oParent2.find(dels);
		this.aBtn.each(function(index){
			$(this).click(function(){
				_this.change(index)
			})
		})
	}
	Tab.prototype.change=function(index){
		var _this=this;
		this.aBtn.each(function(){
			$(this).removeClass('tab-active')
			_this.aDiv.eq($(this).index()).removeClass('block')
		});
		this.aBtn.eq(index).addClass('tab-active')
		this.aDiv.eq(index).addClass('block')
	}