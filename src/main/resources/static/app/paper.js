/**
 * 试卷详情
 */
function paperInfo(id){
    location.href="/zj/paperInfo/"+id;
}
function back(){
    location.href="/zj/paper";
}


/**
 * 获得试卷答案
 */
function showAnswer(id){
   location.href="/zj/answer/"+id;
}

/**
 * 更换新增试卷类型
 */
function changeType(text){
if(text.value==="manual"){
    $("#manual").css('display','');
    $("#auto").css('display','none');
}
else {
    $("#auto").css('display','');
    $("#manual").css('display','none');
}
}

/**
 * 自动选题
 * @returns {boolean}
 */
function autoPaper(){
    $("#autoPaper").ajaxSubmit(function(message) {
        console.log(message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

function manualPaper(){
    $("#manualPaper").ajaxSubmit(function(message) {
        console.log(message);
    });
    parent.location.reload();
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

/**
 * 做考卷
 */
function doIt(id){
    alert('即将进入考试界面，考试时间120分钟');
    location.href="/zj/student/homework/"+id;
}


function showchoice(){
    $("#choiceinfo").css('display','');
}

function offchoice(){
    $("#choiceinfo").css('display','none');
}
function showcomp(){
    $("#compinfo").css('display','');
}
function offcomp(){
    $("#compinfo").css('display','none');
}
function showjudge(){
    $("#judgeinfo").css('display','');
}
function offjudge(){
    $("#judgeinfo").css('display','none');
}
function showdesign(){
    $("#designinfo").css('display','');
}
function offdesign(){
    $("#designinfo").css('display','none');
}
function showapp(){
    $("#appinfo").css('display','');
}
function offapp(){
    $("#appinfo").css('display','none');
}

