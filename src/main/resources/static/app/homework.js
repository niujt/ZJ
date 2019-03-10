/**
 * 进入批改页面
 */
function correction(id){
    console.log(id);
    location.href="/zj/teacher/homework/"+id;
}

/**
 * 进入题库列表
 */
function doHomework(){
    location.href="/zj/paper";
}

/**
 *
 * @param id
 */
function upd(id){
    $("#id").val(id);
}

/**
 * 学生查看评价和建议
 */
function check(evaluate,proposal){
    $("#evaluate").val(evaluate);
    $("#proposal").val(proposal);
}

/**
 * 提交作业
 */
function upHomework(){
    console.log('提交考卷');
        var d = {};
        var t = $('form').serializeArray();
        $.each(t, function () {
            d[this.name] = this.value;
        });
        var str=JSON.stringify(d);
       console.log(str);
        $.ajax({
            url:'/zj/student/subhomework',
            contentType: 'application/json; charset=UTF-8',
            type:'post',
            data:str,
            dataType:'json',
            success:function(data){
                console.log(data);
                location.href='/zj/student/homework';
            }

        });
    return false;
}

/**
 * 自测
 */
function doBySelf(){
    console.log('自测');
    var d = {};
    var t = $('form').serializeArray();
    $.each(t, function () {
        d[this.name] = this.value;
    });
    var str=JSON.stringify(d);
    console.log(str);
    $.ajax({
        url:'/zj/student/subhomeworkBySelf',
        contentType: 'application/json; charset=UTF-8',
        type:'post',
        data:str,
        dataType:'json',
        success:function(data){
            console.log(data);
            location.href='/zj/student/homework';
        }

    });
}

/**
 * 查看自测结果
 */
function checkBySelf(paperid,id){
    location.href='/zj/student/DoBySelf?paperid='+paperid+'&id='+id;
}

/**
 * 倒计时
 */
var time=119;
function leftTimer(){
    setInterval(function(){
       $("#ss").html(time+"分钟");
        time--;
    },1000*60);
}
$(function(){
    leftTimer();
    if($("#ss").html()==0){
        upHomework();
    }
});

function checkinfo(){
    location.href="/zj/teacher/check";
}

