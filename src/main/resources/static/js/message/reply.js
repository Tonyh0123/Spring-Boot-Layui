/**
 * 题目管理
 */
var pageCurr;
var form;
var userName = document.getElementById("userName").innerText;
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        // tableIns=table.render({
        //     elem: '#projectListForStudent',
        //     url:'/declaration/getPersonalProjectEstablishList',
        //     where: {"userId":userId},
        //     method: 'post', //默认：get请求
        //     cellMinWidth: 300,
        //     page: true,
        //     request: {
        //         pageName: 'pageNum', //页码的参数名称，默认：pageNum
        //         limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
        //     },
        //     response:{
        //         statusName: 'code', //数据状态的字段名称，默认：code
        //         statusCode: 200, //成功的状态码，默认：0
        //         countName: 'totals', //数据总数的字段名称，默认：count
        //         dataName: 'list' //数据列表的字段名称，默认：data
        //     },
        //     done: function(res, curr, count){
        //         pageCurr=curr;
        //     }
        // });
        //
        // //监听工具条
        // table.on('tool(projectForStudentTable)', function(obj){
        //     var data = obj.data;
        //     if(obj.event === 'del'){
        //         del(data,data.id);
        //
        //     } else if(obj.event === 'projectDetail'){
        //         projectDetail(data,data.id);
        //
        //     }else if(obj.event === 'JDBG_CXJD'){
        //         JDBG(data,"FQJDBG_CXJD");
        //
        //     }else if(obj.event === 'JDBG_CZJD'){
        //         JDBG(data,"FQJDBG_CZJD");
        //
        //     }else if(obj.event === 'addFinance'){
        //         addFinance(data);
        //     }
        // });

        form.on('submit(addReplySubmit)', function(data){
            //提交前的字符串拼接/填充
            // TODO 校验
            $.ajax({
                type: "POST",
                data: $("#addReplyForm").serialize(),
                url: "/reply/addReply",
                success: function (data) {
                    if (data.code == 1) {
                        layer.msg(data.msg,{
                            icon: 1,
                            time: 1000
                        },function(){
                            layer.closeAll();
                            $("#reply_content").val('');
                            document.getElementById("addReplyForm").reset();
                            location.reload();
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                }
            });
            return false;
        });

    });


});

function addReply(message_id) {
    $("#message_id").val(message_id);
    $("#reply_user_name").val(userName);
    layer.open({
        type: 1 //此处以iframe举例
        , title: '回复留言'
        , area: ['650px','450px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#addReply")
        , btn: ['关闭']
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("addReplyForm").reset();
        }
    });

}





