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
                elem: '#companyApplyList',
                url:'/apply/getCompanyApplyList',
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
                    ,{field:'company_name', title:'公司名',align:'center'}
                    ,{field:'company_contacts', title:'联系人',align:'center'}
                    ,{field:'company_contacts_phone', title:'联系电话',align:'center'}
                    ,{field:'company_contacts_email', title:'联系邮箱',align:'center'}
                    ,{field:'company_industry', title:'公司规模',align:'center'}
                    ,{field:'company_location', title:'所在地',align:'center'}
                    ,{field:'company_staff_size', title:'人员规模',align:'center'}
                    ,{field:'company_introduction', title:'公司简介',align:'center'}
                    ,{field:'jobConfirm', title:'营业执照',align:'center', templet: '<div>' +
                            '<img src="..{{ d.company_business_license }}" style="width:30px; height:30px;" onclick="picPreview(\'{{ d.company_business_license }}\',\'营业执照\')">' +
                            '</div>'}
                    ,{title:'操作',align:'center', toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(applyTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'Confirm'){
                    openUser(data,"审核页面");
                }
            });

            //监听提交
            form.on('submit(userSubmit)', function(data){
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
        data: $("#addCompanyUserForm").serialize(),
        url: "/apply/judgeCompanyUser",
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

    $("#company_id").val(data.id);
    $("#company_contacts_email").val(data.company_contacts_email);
    $("#company_contacts_phone").val(data.company_contacts_phone);
    $("#username").val(data.company_name);

    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#addCompanyUser'),
        end:function(){
            document.getElementById("addCompanyUserForm").reset();
            document.getElementById("no").style.display='none';
            document.getElementById("yes").style.display='';
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


