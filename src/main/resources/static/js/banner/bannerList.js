/**
 * 公告管理
 */
var pageCurr;
var form;
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        var checkRolePermission = document.getElementById("checkThat").innerText;
        if(checkRolePermission!='1'){
            window.location.href="/myError"
        }else {
            tableIns=table.render({
                elem: '#bannerList',
                url:'/banner/getBannerList',
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
                    {type:'numbers'}
                    ,{field:'hrefLink', title:'图片a标签链接',align:'center'}
                    ,{field:'imgUrl', title:'图片路径',align:'center'}
                    ,{field:'imgTitle', title:'图片提示信息',align:'center'}
                    ,{title:'操作',align:'center', toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){

                    console.log(curr);
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(bannerTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'previewFile'){
                    //文件预览
                    pdfView(data, data.policyUrl);
                } else if(obj.event === 'del'){
                    //删除
                    del(data,data.id);
                }
            });

            // 监听提交
            form.on('submit(bannerSubmit)', function(data){
                // TODO 校验
                formSubmit(data);
                return false;
            });
        }



    });


});

// 提交表单
function formSubmit(obj){
    console.log("提交表单中的数据---->"+obj);
    $.ajax({
        type: "POST",
        data: $("#bannerForm").serialize(),
        url: "/banner/addBanner",
        success: function (data) {
            console.log(data.code);
            if (data.code == 1) {
                layer.alert(data.msg,function(){
                    layer.closeAll();
                    load(obj);
                });
            } else {
                console.log(data.msg);
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}




function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}



function openBannerInsert() {
    layer.open({
        type: 1 //此处以iframe举例
        , title: '新增banner'
        , area: ['1000px','550px']
        , shade: 0
        , maxmin: true
        , offset: ['auto']
        , content: $("#addBanner")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空原输入框的值
            $("#hrefLink").val("");
            $("#imgUrl").val("");
            $("#imgTitle").val("");
            document.getElementById("previewButton").style.display='none';
        }
    });
}

function pdfView(data) {
    var uul = data.imgUrl;
    console.log(uul);
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            title: data.imgTitle,
            type: 2,
            area: ['1900px', '950px'],
            fixed: false, //不固定
            maxmin: true,
            content: uul
        });
    });
};

function del(obj,id) {
    if(null!=id){
        layer.confirm('您确定要删除吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/banner/del",{"id":id},function(data){
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


