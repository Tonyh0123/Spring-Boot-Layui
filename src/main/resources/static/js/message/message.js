/**
 * 题目管理
 */
var pageCurr;
var form;
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

        form.on('submit(addMessageSubmit)', function(data){
            //提交前的字符串拼接/填充
            // TODO 校验
            $.ajax({
                type: "POST",
                data: $("#addMessageForm").serialize(),
                url: "/message/addMessage",
                success: function (data) {
                    if (data.code == 1) {
                        layer.alert(data.msg,function(){
                            layer.closeAll();
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

function addMessage() {
    layer.open({
        type: 1 //此处以iframe举例
        , title: '留言'
        , area: ['650px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#addMessage")
        , btn: ['关闭']
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("addMessageForm").reset();
        }
    });

}





