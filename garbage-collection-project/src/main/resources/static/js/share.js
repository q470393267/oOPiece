$(function(){
	
});
function OnInput(event){
	if($(".NewPsw1").val().length<8){
		$(".tip1").text("密码必须大于八位哦！");
		$(".tip1").css("color",'red');
		$(".tip2").text("");
	}else{
		$(".tip1").text("");
		if($(".NewPsw1").val()===$(".NewPsw2").val()){
			$(".tip2").text("Well done,密码一致√");
			$(".tip2").css("color",'green');
			$("#confirm").removeAttr("disabled");
		}else if($(".NewPsw2").val() != ""){
			$(".tip2").text("两次密码不一致，请修改×");
			$(".tip2").css("color",'red');
			$("#confirm").attr("disabled","disabled");
		}else{
			$(".tip2").text("");
		}
	}
	
	
}