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
	$("#staffManage").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#adminManage").attr("class","layui-btn");
		$("#information").attr("class","layui-btn");
		$("#contIfra").attr("src","staffManage.html");
	});
	$("#adminManage").click(function(){
		$(this).addClass("layui-btn-lg");	
		$("#staffManage").attr("class","layui-btn");
		$("#information").attr("class","layui-btn");
		$("#contIfra").attr("src","adminManage.html");
	});
	$("#information").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#adminManage").attr("class","layui-btn");
		$("#staffManage").attr("class","layui-btn");
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

function showNotice(title,text){
	$("#noticeTitle").text('djfalsjl');
	$("#content_area").text(text);
}