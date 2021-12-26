$(function(){
	/* if(sessionStorage.getItem('userId')==''){
		$("#content").css("display","none");
	} */
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
		$("#garbDire").attr("class","layui-btn layui-btn-normal");
		$("#garbNoti").attr("class","layui-btn layui-btn-normal");
		$("#staffManage").attr("class","layui-btn layui-btn-normal");
		$("#information").attr("class","layui-btn layui-btn-normal");
		$("#contIfra").attr("src","garbageRecycleManage.html");
	});
	$("#garbDire").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbNoti").attr("class","layui-btn layui-btn-normal");
		$("#garbRecy").attr("class","layui-btn layui-btn-normal");
		$("#staffManage").attr("class","layui-btn layui-btn-normal");
		$("#information").attr("class","layui-btn layui-btn-normal");
		$("#contIfra").attr("src","garbageDirectionManage.html");
	});
	$("#garbNoti").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbDire").attr("class","layui-btn layui-btn-normal");
		$("#garbRecy").attr("class","layui-btn layui-btn-normal");
		$("#staffManage").attr("class","layui-btn layui-btn-normal");
		$("#information").attr("class","layui-btn layui-btn-normal");
		$("#contIfra").attr("src","systemNoticeManage.html");
	});
	$("#staffManage").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbDire").attr("class","layui-btn layui-btn-normal");
		$("#garbRecy").attr("class","layui-btn layui-btn-normal");
		$("#garbNoti").attr("class","layui-btn layui-btn-normal");
		$("#information").attr("class","layui-btn layui-btn-normal");
		$("#contIfra").attr("src","staffManage.html");
	});
	$("#information").click(function(){
		$(this).addClass("layui-btn-lg");
		$("#garbDire").attr("class","layui-btn layui-btn-normal");
		$("#garbRecy").attr("class","layui-btn layui-btn-normal");
		$("#garbNoti").attr("class","layui-btn layui-btn-normal");
		$("#staffManage").attr("class","layui-btn layui-btn-normal");
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