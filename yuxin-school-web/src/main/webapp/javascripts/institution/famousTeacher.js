$(function () {
    //    左侧active切换
    $selectSubMenus('teacherFamous ');
    //点击删除弹窗提醒
    $('.delete').click(function () {
        $.confirm("是否确定删除该老师？",function (data) {
            if(data){

            }
        });
    });


    getTeacherList();

});


//去掉字符串两端的空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}



function getTeacherList(){
    $.post(rootPath+'/institutionTeacher/teacherList',{insId:$('#insId').val()},function(json) {
       // console.log(json);

        if(json.status == 1){
            var html = '';
            var list = json.list;

            for(var i in list){
               html += "<div class=\"teacherList\">";
                html += "<div class=\"headerTeacher\">";
                html += " <span style=\"margin-left: 20px;\">"+list[i].name+"</span>";
                html += "<a href=\"##\" class='btn btn-default delete teacherDelBtn' data-id='"+list[i].id+"' style=\"float: right;margin: 3px 50px 0 0;\">删除</a>";
                html += "<a href=\"##\" class='btn btn-default teacherInfoBtn'  data-id='"+list[i].id+"' style=\"float: right;margin: 3px 20px 0 0;\">老师详情</a>";
                html += " </div>";
                html += " <div  class=\"contentTeacher\">";
                html += "<div>";
                html += " <label>姓名:</label>";
                html += "<span>"+list[i].name+"</span>";
                html += "</div>";
                html += "<div>";
                for(var j in list[i].label){
                    if(trim(list[i].label[j].name) != ''){
                        html += "<a href=\"##\" class=\"btn btn-default\">"+list[i].label[j].name+"</a>";
                    }

                }

                html += " </div>";
                html += "<div>";
                html += "<label>毕业院校:</label>";
                html += "<span>"+list[i].school+"</span>";
                html += "</div></div></div>";

            }

            $("#teacherContainer").html(html);


            $('.teacherInfoBtn').click(function(){
                window.location.href = "/InsInfoBase/addFamousTeacher?id="+$("#insId").val()+"&tid="+$(this).attr('data-id');
            })


            $('.teacherDelBtn').click(function(){
                var id = $(this).attr('data-id');
                $.confirm('是否确定删除该老师?',function (data) {
                    if(data){
                        $.post(rootPath + '/institutionTeacher/delTeacher', {tid: id,insId:$('#insId').val()}, function (json) {
                           if(json.status == 1){
                               $.msg('操作成功');
                               getTeacherList();
                           }else{
                               $.msg('操作失败');
                           }

                        })
                    }
                })
            })









        }

    })
}


function openAdd(){
 // /InsInfoBase/addFamousTeacher?id=${insId}
    var len = $('#teacherContainer').find('.teacherList').length;
    console.log('len = '+len);

    if(len >= 8){
        $.msg('已超过最大老师数量');
        return;
    }

    window.location.href = "/InsInfoBase/addFamousTeacher?id="+$('#insId').val()


}
