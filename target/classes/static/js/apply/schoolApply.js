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
                elem: '#schoolApplyList',
                url:'/apply/getSchoolApplyList',
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
                    ,{field:'schoolName', title:'学校',align:'center'}
                    ,{field:'schoolManagerName', title:'联系人姓名',align:'center'}
                    ,{field:'schoolManagerJob', title:'职位',align:'center'}
                    ,{field:'jobConfirm', title:'工作证明',align:'center', templet: '<div>' +
                            '<img src="..{{ d.jobConfirm }}" style="width:30px; height:30px;" onclick="picPreview(\'{{ d.jobConfirm }}\',\'工作证明信息\')">' +
                            '</div>'}
                    ,{field:'schoolConfirm', title:'学校证明',align:'center', templet: '<div>' +
                            '<img src="..{{ d.schoolConfirm }}" style="width:30px; height:30px;" onclick="picPreview(\'{{ d.schoolConfirm }}\',\'学校证明信息\')">' +
                            '</div>'}
                    ,{field:'schoolManagerPhone', title: '手机号',align:'center'}
                    ,{field:'schoolManagerEmail', title: '邮箱',align:'center'}
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
                if(obj.event === 'del'){
                    //删除
                    delUser(data,data.id,data.sysUserName);
                } else if(obj.event === 'accessConfirm'){
                    openUser(data,"同意申请并为该高校开通账户");
                } else if(obj.event === 'edit'){
                    //编辑
                    openUser(data,"编辑");
                }else if(obj.event === 'recover'){
                    //恢复
                    recoverUser(data,data.id);
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
        data: $("#addSchoolUserForm").serialize(),
        url: "/apply/regSchoolUser",
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


function openUser(data,title){
    console.log("data"+data);

    $("#keyid").val(data.keyid);
    $("#managerEmail").val(data.schoolManagerEmail);
    $("#username").val(data.schoolManagerName);
    $("#mobile").val(data.schoolManagerPhone);
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);
    // $.ajax({
    //     url:'/role/getRoles',
    //     dataType:'json',
    //     async: true,
    //     success:function(data){
    //         $.each(data,function(index,item){
    //             if(!roleId){
    //                 var option = new Option(item.roleName,item.id);
    //             }else {
    //                 var option = new Option(item.roleName,item.id);
    //                 // // 如果是之前的parentId则设置选中
    //                 if(item.id == roleId) {
    //                     option.setAttribute("selected",'true');
    //                 }
    //             }
    //             $('#roleId').append(option);//往下拉菜单里添加元素
    //             form.render('select'); //这个很重要
    //         })
    //     }
    // });

    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#addSchoolUserAndUpdateIt'),
        end:function(){
            cleanUser();
        }
    });
}

function delUser(obj,id,name) {
    var currentUser=$("#currentUser").html();
    if(null!=id){
        if(currentUser==id){
            layer.alert("对不起，您不能执行删除自己的操作！");
        }else{
            layer.confirm('您确定要删除'+name+'用户吗？', {
                btn: ['确认','返回'] //按钮
            }, function(){
                $.post("/user/updateUserStatus",{"id":id,"status":0},function(data){
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
}
//恢复
function recoverUser(obj,id) {
    if(null!=id){
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/user/updateUserStatus",{"id":id,"status":1},function(data){
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

function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function confirmApply() {
    openUser(null,"同意申请并为该高校开通账户");
}

function cleanUser(){
    $("#username").val("");
    $("#mobile").val("");
    $("#password").val("");
    $('#roleId').html("");
}
function registration() {
    window.location.href="/registration"
}
function registrationSchool() {
    window.location.href="/registrationOfSchool"
}


