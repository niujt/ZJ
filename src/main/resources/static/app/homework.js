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
