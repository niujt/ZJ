function back(){
    location.href="/zj";
}
function doRegist(){
    $("#regist").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
    });
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}