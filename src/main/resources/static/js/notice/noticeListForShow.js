/**
 * 公告管理
 */
var pageCurr;
var form;
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        tableIns=table.render({
            elem: '#allNotices',
            url:'/notice/getAllNoticesList',
            method: 'post', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response:{
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[

                {field:'noticeTitle', title:'公告标题',width:'85%', align:'center'}
                ,{field:'noticeDate', title:'发布日期',width:'15%',align:'center',sort:true}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听行单击事件
        table.on('row(allNoticesTable)', function(obj){
            pdfView(obj.data, obj.data.noticeUrl);
        });


    });


});

function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}



function openNoticeInsert() {
    layer.open({
        type: 1 //此处以iframe举例
        , title: '新增通知公告'
        , area: ['1000px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['auto']
        , content: $("#addNotice")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空原输入框的值
            $("#noticeTitle").val("");
            $("#noticeDate").val("");
            $("#noticeUrl").val("");
            document.getElementById("previewButton").style.display='none';
        }
    });
}

function pdfView(data) {
    var uul = data.noticeUrl;
    console.log(uul);
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            title: data.noticeTitle,
            type: 2,
            area: ['1000px', '750px'],
            fixed: false, //不固定
            maxmin: false,
            content: uul
        });
    });
};

function del(obj,id) {
    if(null!=id){
        layer.confirm('您确定要删除吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/notice/del",{"id":id},function(data){
                if (data.code == 1) {
                    layer.alert(data.msg,function(){
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}


