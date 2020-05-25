/**
 * 申请使用管理
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
                elem: '#studentApplyList',
                url:'/apply/getStudentApplyList',
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
                    ,{field:'userName', title:'用户名',align:'center'}
                    ,{field:'schoolName', title:'学校',align:'center'}
                    ,{field:'studentName', title:'姓名',align:'center'}
                    ,{field:'studentId', title:'学号',align:'center'}
                    ,{field:'studentEmail', title:'邮箱',align:'center'}
                    ,{field:'studentConfirm', title:'学生证',align:'center', templet: '<div>' +
                            '{{#  if( d.studentConfirm != null){ }}' +
                            '<img src="/images/idCard.png" style="width:30px; height:30px;" onclick="picPreview(\'{{ d.studentConfirm }}\',\'学生证\')">' +
                            '{{#  } else { }}' +
                            '<p>未上传</p>' +
                            '{{#  } }}' +
                            '</div>'}
                    ,{field:'SFZZM', title:'身份证（正面）',align:'center', templet: '<div>' +
                            '{{#  if( d.sfzzm != null){ }}' +
                            '<img src="/images/idCard.png" style="width:30px; height:30px;" onclick="picPreview(\'{{ d.sfzzm }}\',\'身份证（正面）\')">' +
                            '{{#  } else { }}' +
                            '<p>未上传</p>' +
                            '{{#  } }}' +
                            '</div>'}
                    ,{field:'SFZBM', title:'身份证（背面）',align:'center', templet: '<div>' +
                            '{{#  if( d.sfzbm != null){ }}' +
                            '<img src="/images/idCard.png" style="width:30px; height:30px;" onclick="picPreview(\'{{ d.sfzbm }}\',\'身份证（背面）\')">' +
                            '{{#  } else { }}' +
                            '<p>未上传</p>' +
                            '{{#  } }}' +
                            '</div>'}
                    ,{field:'studentStatus', title: '审核状态',align:'center'}
                    ,{field:'studentMajor', title: '专业',align:'center'}
                    ,{title:'操作',align:'center', toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    //console.log(res);
                    //得到当前页码
                    console.log(curr);
                    // $("[data-field='userStatus']").children().each(function(){
                    //     if($(this).text()=='1'){
                    //         $(this).text("有效")
                    //     }else if($(this).text()=='0'){
                    //         $(this).text("失效")
                    //     }
                    // });
                    //得到数据总量
                    //console.log(count);
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(applyTable)', function(obj){
                var data = obj.data;

                if(obj.event === 'accept'){
                    //删除
                    acceptApply(data.id);
                } else if(obj.event === 'denied'){
                    refuseApply(data.id);
                }
            });

            //监听提交
            form.on('submit(verifySubmit)', function(data){
                // TODO 校验
                formSubmit(data);
                return false;
            });
        }

    });


});

function acceptApply(id) {
    var verifyDetail = "同意";
    var studentStatus = '1';
    $.ajax({
        type: "POST",
        data: {
            "id":id,
            "verifyDetail":verifyDetail,
            "studentStatus":studentStatus
        },
        url: "/apply/verifyStudentStatus",
        success: function (data) {
            if (data.code == 1) {
                layer.msg(data.msg,{icon:1,time:2000},function(){
                    tableIns.reload({
                        page: {
                            curr: pageCurr //从当前页码开始
                        }
                    });
                });
            } else {
                layer.alert(data.msg);
            }
        }
    });
}

function refuseApply(id) {
    openVerify(id);
}

function openVerify(id){

    $("#id").val(id);
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    layer.open({
        type:1,
        title: "审核意见",
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#verifyStudentStatus'),
        end:function(){
            $("#id").val("");
            $("#verifyDetail").val('');
        }
    });
}

//提交表单
function formSubmit(obj){
    $.ajax({
        type: "POST",
        data: $("#verifyStudentStatusForm").serialize(),
        url: "/apply/verifyStudentStatus",
        success: function (data) {
            console.log(data.code);
            if (data.code == 1) {
                layer.msg(data.msg,{icon:1,time:2000},function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
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




// function delUser(obj,id,name) {
//     var currentUser=$("#currentUser").html();
//     if(null!=id){
//         if(currentUser==id){
//             layer.alert("对不起，您不能执行删除自己的操作！");
//         }else{
//             layer.confirm('您确定要删除'+name+'用户吗？', {
//                 btn: ['确认','返回'] //按钮
//             }, function(){
//                 $.post("/user/updateUserStatus",{"id":id,"status":0},function(data){
//                     if (data.code == 1) {
//                         layer.alert(data.msg,function(){
//                             layer.closeAll();
//                             load(obj);
//                         });
//                     } else {
//                         layer.alert(data.msg);
//                     }
//                 });
//             }, function(){
//                 layer.closeAll();
//             });
//         }
//     }
// }
// //恢复
// function recoverUser(obj,id) {
//     if(null!=id){
//         layer.confirm('您确定要恢复吗？', {
//             btn: ['确认','返回'] //按钮
//         }, function(){
//             $.post("/user/updateUserStatus",{"id":id,"status":1},function(data){
//                 if (data.code == 1) {
//                     layer.alert(data.msg,function(){
//                         layer.closeAll();
//                         load(obj);
//                     });
//                 } else {
//                     layer.alert(data.msg);
//                 }
//             });
//         }, function(){
//             layer.closeAll();
//         });
//     }
// }



// function confirmApply() {
//     openUser(null,"同意申请并为该高校开通账户");
// }

// function cleanUser(){
//     $("#username").val("");
//     $("#mobile").val("");
//     $("#password").val("");
//     $('#roleId').html("");
// }
// function registration() {
//     window.location.href="/registration"
// }
// function registrationSchool() {
//     window.location.href="/registrationOfSchool"
// }





