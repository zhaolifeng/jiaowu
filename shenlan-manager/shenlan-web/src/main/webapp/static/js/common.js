function loadMessage()
{
	$.ajax({
		url:"/systemMenu/getMenu",
		dataType:"html",
		success:function(result)
		{
			$(".dx-left").html(result);
		}
	});
}

function loadPage()
{
	$.ajax({
		url:"/homepage/home",
		dataType:"html",
		success:function(result)
		{
			$(".content").html(result);
		}
	});
}
loadMessage();
loadPage();