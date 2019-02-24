window.onload = function() {
    setContentHeight();
    window.onresize = setContentHeight;
    function setContentHeight () {
        var windowHeight = document.documentElement.clientHeight;
        document.getElementsByClassName('content')[0].style.height = windowHeight - 102 - 138 + 'px';
    }
}

/**
 * 登录
 * @returns {boolean}
 */
function doLogin(){
    console.log($("#identity").val());
     $("#login").ajaxSubmit(function(message) {
         console.log(message);
        $("#message").html(message.login);
        if(message.login=='登录成功'){
           if($("#identity").val()=="teacher"){
               alert('老师');
                location.href="/zj/teacher/index";
            }
            else if($("#identity").val()=="student"){
               alert('学生');
                location.href="/zj/student/index";
            }
            else {
               alert('管理员');
                location.href="/zj/index";
            }
        }
     });
     return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
function regist(){
    window.location.href="/zj/regist";
}