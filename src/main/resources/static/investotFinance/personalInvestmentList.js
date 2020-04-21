/**
 * 题目管理
 */
var pageCurr;
var form;
var userId = document.getElementById("userId").innerText;
var userName = document.getElementById("userName").innerText;
$(function() {
    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        var checkRolePermission = document.getElementById("checkThat").innerText;
        if(checkRolePermission!='4' && checkRolePermission!='1'){
            window.location.href="/myError"
        }else {
            tableIns=table.render({
                elem: '#personalInvestment',
                url:'/investorFinance/getInvestmentByUserId',
                where: {"userId":userId},
                method: 'post', //默认：get请求
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
                    ,{field:'investor_name', title:'姓名', align:'center'}
                    ,{field:'belong_company', title:'所属公司',align:'center'}
                    ,{field:'investor_position', title:'职位',align:'center',sort:true}
                    ,{field:'investor_work_years', title:'工作年数', align:'center'}
                    ,{field:'belong_province', title:'所在省份',align:'center'}
                    ,{field:'main_turn', title:'主投轮次',align:'center',sort:true}
                    ,{field:'single_investment', title:'单笔投资额度', align:'center'}
                    ,{field:'investment_field', title:'投资领域',align:'center'}
                    ,{field:'personal_introduction', title:'个人介绍',align:'center',sort:true}
                    ,{field:'organization_name', title:'机构名称',align:'center'}
                    ,{field:'organization_location', title:'机构省份',align:'center',sort:true}
                    ,{field:'organization_ZJGM', title:'资金规模', align:'center'}
                    ,{field:'organization_introduction', title:'机构简介',align:'center'}
                    ,{title:'操作',align:'center',toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    console.log(count);
                    //得到数据总量
                    if(count == null){
                        document.getElementById("addInvestmentButton").style.display = "";
                    }
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(personalInvestmentTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    del(data,data.id);
                } else if(obj.event === 'edit'){
                    editInvestment(data);
                }
            });

            form.on('submit(addInvestmentSubmit)', function(data){
                $.ajax({
                    type: "POST",
                    data: $("#addInvestmentForm").serialize(),
                    url: "/investorFinance/addInvestment",
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
        }
    });


});

//删除
function del(obj,id) {
    if(null!=id){
        layer.confirm('您确定要删除吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/investorFinance/del",{"id":id},function(data){
                if (data.code == 1) {
                    layer.alert(data.msg,function(){
                        layer.closeAll();
                        location.reload();
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

function addInvestment() {
    $("#investor_id").val(userId);
    $("#platform_user_name").val(userName);
    layer.open({
        type: 1 //此处以iframe举例
        , title: '发布融资信息'
        , area: ['1000px','900px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#addInvestment")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("addInvestmentForm").reset();
        }
    });
}

function editInvestment(obj) {
    $("#investor_id").val(userId);
    $("#platform_user_name").val(userName);
    $("#id").val(obj.id);
    $("#investor_name").val(obj.investor_name);
    $("#belong_company").val(obj.belong_company);
    $("#investor_position").val(obj.investor_position);
    $("#investor_work_years").val(obj.investor_work_years);
    $("#belong_province").val(obj.belong_province);
    $("#main_turn").val(obj.main_turn);
    $("#single_investment").val(obj.single_investment);
    $("#investment_field").val(obj.investment_field);
    $("#personal_introduction").val(obj.personal_introduction);
    $("#organization_name").val(obj.organization_name);
    $("#organization_location").val(obj.organization_location);
    $("#organization_ZJGM").val(obj.organization_ZJGM);
    $("#organization_introduction").val(obj.organization_introduction);

    layer.open({
        type: 1 //此处以iframe举例
        , title: '发布融资信息'
        , area: ['1000px','900px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#addInvestment")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("addInvestmentForm").reset();
        }
    });
}






