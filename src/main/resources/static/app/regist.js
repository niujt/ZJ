function back(){
    location.href="/zj";
}
function doRegist(){
    // 密码长度校验
    var password = $("#password").val();
    if (password.length < 8) {
        $("#message").html("密码长度必须至少8位");
        return false;
    }
    
    $("#regist").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
    });
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}