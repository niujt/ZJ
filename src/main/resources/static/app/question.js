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
            $("#chapter").val(data.ChoiceQuestion.chapter);
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
 * 添加应用题
 */
function addapplicationQue(){
    $("#addapplicationQue").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转

}

/**
 * 应用题详情页面
 */
function loadQueInfo(id){
    $.ajax({
        url: "/zj/applicationQue/"+id,
        type:"GET",
        success:function(data){
            console.log(data);
            $("#text").val(data.application.text);
            $("#id").val(data.application.id);
            $("#questiontypeid").val(data.application.questiontypeid);
            $("#chapter").val(data.application.chapter);
            $("#imgurl").attr('src',data.application.imgurl).attr('style','width:50px;height:50px');
            $("#answer").val(data.application.answer);
        }
    });
}

/**
 * 更新应用题
 */
function updateApplicationQue(){
    $("#updateApplicationQue").ajaxSubmit(function(message) {
        console.log(message);
        $("#message").html(message.message);
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
/**
 * 填空，简答，判断题通用删除方法
 */
function delOther(type,id){
    console.log(type+id);
    if(confirm("确认要删除么")){
        $.ajax({
            url: "/zj/delOthers?id="+id+"&type="+type,
            type:"DELETE",
            success:function(data){
                console.log(data);
            }
        });
    }
    location.reload();
}

/**
 * 填空，简答，判断题通用加载方法
 */
function loadOtherQue(type,id){
    $.ajax({
        url: "/zj/findOther?id="+id+"&type="+type,
        type:"GET",
        success:function(data){
            console.log(data);
            $("#text").val(data.otherQue.text);
            $("#id").val(data.otherQue.id);
            $("#questiontypeid").val(data.otherQue.questiontypeid);
            $("#chapter").val(data.otherQue.chapter);
            $("#answer").val(data.otherQue.answer);
        }
    });
}
/**
 * 填空，简答，判断题通用更新方法
 */
function upOthers(){
    $("#upQuestion").ajaxSubmit(function(message) {
        console.log(message);
        $("#message2").html(message.message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

/**
 * 删除应用题
 * @param id
 */
function delAppQue(id){
    if(confirm("确认要删除么")){
        $.ajax({
            url: "/zj/ApplicationQuestion/"+id,
            type:"DELETE",
            success:function(data){
                console.log(data);
            }
        });
    }
    location.reload();
}