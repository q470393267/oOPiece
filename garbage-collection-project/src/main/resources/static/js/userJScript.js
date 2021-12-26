var garbDireAppIndex;
$(function(){
	//项目简介
	var $headerLeftA=$("#detail");
	$headerLeftA.mouseover(function(){
		$("#project_intro",".header_left").css("display","block");
		$headerLeftA.css("transform","scale(1.1)");
	});
	$headerLeftA.mouseleave(function(){
		$("#project_intro",".header_left").css("display","none");
		$headerLeftA.css("transform","scale(1.0)");
	});
	//各模块切换
	$("#garbRecy").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbDire").attr("class","layui-btn");
		$("#garbNoti").attr("class","layui-btn");
		$("#information").attr("class","layui-btn");
		$("#contIfra").attr("src","garbageRecycle.html");
	});
	$("#garbDire").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbNoti").attr("class","layui-btn");
		$("#garbRecy").attr("class","layui-btn");
		$("#information").attr("class","layui-btn");
		$("#contIfra").attr("src","garbageDirection.html");
	});
	$("#garbNoti").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbDire").attr("class","layui-btn");
		$("#garbRecy").attr("class","layui-btn");
		$("#information").attr("class","layui-btn");
		$("#contIfra").attr("src","systemNotice.html");
	});
	$("#information").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbNoti").attr("class","layui-btn");
		$("#garbDire").attr("class","layui-btn");
		$("#garbRecy").attr("class","layui-btn");
		$("#contIfra").attr("src","userInfo.html");
	});
});
var addFavorite=function(href,title){
		layer.alert(href, {
			title:title
			,content:"请按 Ctrl+D 键添加到收藏夹 ^_^"
			,time:2000
		    ,skin: 'layui-layer-molv'
		    ,closeBtn: 0
		    ,anim: 5//动画类型
			,offset:'t',
		  });
	}
var flag=0;
var addMenu=function(){
	if(!flag){
		$("#logo").css("display","flex");
		flag=1;
	}else{
		$("#logo").css("display","none");
		flag=0;
	}
}