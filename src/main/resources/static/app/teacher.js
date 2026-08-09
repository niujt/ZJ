/**
* 添加学生
*/
function addTea(){
    // jquery 表单提交
 $("#addTea").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
    });
 parent.location.reload();
 return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转

 }
 /**
 * 删除学生
 **/
function delTea(id){
    if(confirm("确认要删除该老师的信息么")){
        $.ajax({
            url: "/zj/teacher/"+id,
            type:"DELETE",
            success:function(data){
                console.log(data);
            }
        });
    }
     location.reload();
}