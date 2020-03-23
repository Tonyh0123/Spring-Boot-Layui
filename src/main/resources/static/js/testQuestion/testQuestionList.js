/**
 * 题目管理
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
                elem: '#testQuestionList',
                url:'/testQuestion/getTestQuestionList',
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
                    ,{field:'testQuestion', title:'题目内容',align:'center'}
                    ,{field:'testAnswerA', title:'答案A',align:'center'}
                    ,{field:'testAnswerA_score', title:'答案A分值',align:'center'}
                    ,{field:'testAnswerB', title:'答案B',align:'center'}
                    ,{field:'testAnswerB_score', title:'答案B分值',align:'center'}
                    ,{field:'testAnswerC', title:'答案C',align:'center'}
                    ,{field:'testAnswerC_score', title:'答案C分值',align:'center'}
                    ,{field:'testAnswerD', title:'答案D',align:'center'}
                    ,{field:'testAnswerD_score', title:'答案D分值',align:'center'}
                    ,{field:'testType', title: '题目类型',align:'center'}
                    ,{title:'操作',align:'center', toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    //得到数据总量
                    //console.log(count);
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(testQuestionTable)', function(obj){
                var data = obj.data;
                console.log(obj.event);
                if(obj.event === 'del'){
                    //删除
                    del(data,data.id);
                } else if(obj.event === 'edit'){
                    //编辑
                    openTestQuestionUpdate(data);
                }
            });

            //监听提交
            form.on('submit(userSubmit)', function(data){
                // TODO 校验
                formSubmit(data);
                return false;
            });
        }



    });


});

//提交表单
function formSubmit(obj){
    console.log("提交表单中的数据---->"+obj);
    $.ajax({
        type: "POST",
        data: $("#testQuestionForm").serialize(),
        url: "/testQuestion/addTestQuestion",
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

function openTestQuestionUpdate(data) {
    $("#id").val(data.id);
    $("#testQuestion").val(data.testQuestion);
    $("#testAnswerA").val(data.testAnswerA);
    $("#testAnswerB").val(data.testAnswerB);
    $("#testAnswerC").val(data.testAnswerC);
    $("#testAnswerD").val(data.testAnswerD);
    $("#testAnswerA_score").val(data.testAnswerA_score);
    $("#testAnswerB_score").val(data.testAnswerB_score);
    $("#testAnswerC_score").val(data.testAnswerC_score);
    $("#testAnswerD_score").val(data.testAnswerD_score);
    $("#testType").val(data.testType);

    console.log(data.testType);
    form.render('select');
    layer.open({
        type: 1 //此处以iframe举例
        , title: '编辑测试题目'
        , area: ['1000px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['auto']
        , content: $("#addTestQuestion")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空原输入框的值
            $("#testQuestion").val("");
            $("#testAnswerA").val("");
            $("#testAnswerB").val("");
            $("#testAnswerC").val("");
            $("#testAnswerD").val("");
            $("#id").val("");

        }
    });
}

function openTestQuestionInsert() {
    $("#testAnswerA_score").val('');
    $("#testAnswerB_score").val('');
    $("#testAnswerC_score").val('');
    $("#testAnswerD_score").val('');
    $("#testType").val('');
    form.render('select');
    layer.open({
        type: 1 //此处以iframe举例
        , title: '新增测试题目'
        , area: ['1000px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['auto']
        , content: $("#addTestQuestion")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空原输入框的值
            $("#testQuestion").val("");
            $("#testAnswerA").val("");
            $("#testAnswerB").val("");
            $("#testAnswerC").val("");
            $("#testAnswerD").val("");
            $("#id").val("");

        }
    });
}


//删除
function del(obj,id) {
    if(null!=id){
        layer.confirm('您确定要删除吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/testQuestion/del",{"id":id},function(data){
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


