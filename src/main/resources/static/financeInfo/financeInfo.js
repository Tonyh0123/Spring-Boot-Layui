/**
 * 题目管理
 */
var pageCurr;
var form;
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

            //监听工具条
            // table.on('tool(personalInvestmentTable)', function(obj){
            //     var data = obj.data;
            //     if(obj.event === 'del'){
            //         del(data,data.id);
            //     } else if(obj.event === 'edit'){
            //         editInvestment(data);
            //     }
            // });

            // form.on('submit(addInvestmentSubmit)', function(data){
            //     $.ajax({
            //         type: "POST",
            //         data: $("#addInvestmentForm").serialize(),
            //         url: "/investorFinance/addInvestment",
            //         success: function (data) {
            //             if (data.code == 1) {
            //                 layer.alert(data.msg,function(){
            //                     layer.closeAll();
            //                     location.reload();
            //                 });
            //             } else {
            //                 layer.alert(data.msg);
            //             }
            //         }
            //     });
            //     return false;
            // });
    });

});





