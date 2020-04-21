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
        tableIns=table.render({
            elem: '#financeInfo',
            url:'/investorFinance/getInvestments',
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
                {field:'investor_name', title:'姓名', align:'center'}
                ,{field:'investor_position', title:'职位',align:'center'}
                ,{field:'belong_province', title:'所在省份',align:'center'}
                ,{field:'main_turn', title:'主投轮次',align:'center'}
                ,{field:'investment_field', title:'投资领域',align:'center'}
            ]],
            skin:'line',
            size:'lg',
            done: function(res, curr, count){
                pageCurr=curr;
            }
        });

        //监听行单击事件
        table.on('row(financeInfoTable)', function(obj){
            viewInvestorDetail(obj.data);
        });
    });

});

function viewInvestorDetail(obj) {

    document.getElementById("investor_name").innerText = obj.investor_name;
    document.getElementById("belong_company").innerText = obj.belong_company;
    document.getElementById("investor_position").innerText = obj.investor_position;
    document.getElementById("investor_work_years").innerText = obj.investor_work_years;
    document.getElementById("belong_province").innerText = obj.belong_province;
    document.getElementById("main_turn").innerText = obj.main_turn;
    document.getElementById("single_investment").innerText = obj.single_investment;
    $('#investment_field').append('<button class="layui-btn layui-btn-xs">'+obj.investment_field+'</button>');
    document.getElementById("personal_introduction").innerText = obj.personal_introduction;
    document.getElementById("organization_name").innerText = obj.organization_name;
    document.getElementById("organization_location").innerText = obj.organization_location;
    document.getElementById("organization_ZJGM").innerText = obj.organization_ZJGM;
    document.getElementById("organization_introduction").innerText = obj.organization_introduction;


    // 为了留言
    $("#message_owner").val(obj.platform_user_name);
    $("#message_sender").val(userName);

    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            title: '投资信息详情',
            type: 1,
            area: ['1900px', '950px'],
            fixed: false, //不固定
            maxmin: true,
            content: $("#viewFinanceDetail"),
            skin: 'layerTC',
            end: function () {
                //弹窗关闭后清空值
                $("#investment_field").html('');
                $("#message_owner").val("");
                $("#message_sender").val("");
            }
        });
    });
};





