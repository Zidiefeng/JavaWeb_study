var oldpassword = null;
var newpassword = null;
var rnewpassword = null;
var saveBtn = null;

$(function(){
	oldpassword = $("#oldpassword");
	newpassword = $("#newpassword");
	rnewpassword = $("#rnewpassword");
	saveBtn = $("#save");
	
	oldpassword.next().html("*");
	newpassword.next().html("*");
	rnewpassword.next().html("*");
	
	oldpassword.on("blur",function(){
		$.ajax({
			type:"GET",
			url:path+"/jsp/user.do",
			data:{method:"pwdmodify",oldpassword:oldpassword.val()},
			dataType:"json",
			success:function(data){
				if(data.result == "true"){//旧密码正确
					validateTip(oldpassword.next(),{"color":"green"},imgYes,true);
				}else if(data.result == "false"){//旧密码输入不正确
					validateTip(oldpassword.next(),{"color":"red"},imgNo + "The original password is wrong",false);
				}else if(data.result == "sessionerror"){//当前用户session过期，请重新登录
					validateTip(oldpassword.next(),{"color":"red"},imgNo + "Session out, please log in",false);
				}else if(data.result == "error"){//旧密码输入为空
					validateTip(oldpassword.next(),{"color":"red"},imgNo + " Please enter old password",false);
				}
			},
			error:function(data){
				//请求出错
				validateTip(oldpassword.next(),{"color":"red"},imgNo + " request error",false);
			}
		});
	}).on("focus",function(){
		validateTip(oldpassword.next(),{"color":"#666666"},"* Please enter old pwd",false);
	});
	
	newpassword.on("focus",function(){
		validateTip(newpassword.next(),{"color":"#666666"},"* 6~20",false);
	}).on("blur",function(){
		if(newpassword.val() != null && newpassword.val().length >= 6
				&& newpassword.val().length < 20 ){
			validateTip(newpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(newpassword.next(),{"color":"red"},imgNo + " Bad pwd format, please re-enter",false);
		}
	});
	
	
	rnewpassword.on("focus",function(){
		validateTip(rnewpassword.next(),{"color":"#666666"},"* should be same as above",false);
	}).on("blur",function(){
		if(rnewpassword.val() != null && rnewpassword.val().length >= 6
				&& rnewpassword.val().length < 20 && newpassword.val() == rnewpassword.val()){
			validateTip(rnewpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rnewpassword.next(),{"color":"red"},imgNo + " inconsistent, please re-enter",false);
		}
	});
	
	
	saveBtn.on("click",function(){
		oldpassword.blur();
		newpassword.blur();
		rnewpassword.blur();
		/*newpassword.attr("validateStatus") == true*/
		if("true"==oldpassword.attr("validateStatus") &&
			"true"==newpassword.attr("validateStatus") &&
			"true"==rnewpassword.attr("validateStatus")){
			if(confirm("Confirm to modify pwd?")){
				$("#userForm").submit();
			}
		}
	});
});