/**
 * 进入试题详情页面
 * @param type
 */
function queInfo(type){
    console.log(type);
   location.href="/zj/questionInfo/"+type;

}

/**
 * 添加选择题
 */
function addChoiceQue(){
    $("#addChoiceQue").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

/**
 * 删除选择题
 */
function delChoiceQue(id){
    if(confirm("确认要删除该选择题么")){
        $.ajax({
            url: "/zj/choiceQuestion/"+id,
            type:"DELETE",
            success:function(data){
                console.log(data);
            }
        });
    }
    location.reload();
}

/**
 * 加载选择题详情页
 */
function loadChoiceQue(id){
    $.ajax({
        url: "/zj/choiceQuestion/"+id,
        type:"GET",
        success:function(data){
            console.log(data);
            $("#text").val(data.ChoiceQuestion.text);
            $("#id").val(data.ChoiceQuestion.id);
            $("#questiontypeid").val(data.ChoiceQuestion.questiontypeid);
            $("#option1").val(data.ChoiceQuestion.option1);
            $("#option2").val(data.ChoiceQuestion.option2);
            $("#option3").val(data.ChoiceQuestion.option3);
            $("#option4").val(data.ChoiceQuestion.option4);
            $("#option5").val(data.ChoiceQuestion.option5);
            $("#answer").val(data.ChoiceQuestion.answer);
        }
    });
}

/**
 * 更新选择题
 */
function upChoiceQue(){
    $("#upChoiceQue").ajaxSubmit(function(message) {
        console.log(message);
       $("#message2").html(message.message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

/**
 * 填空，简答，判断题通用添加方法
 */
function addOthers(){
    $("#addQuestion").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转

}