/**
 * 题目管理
 */
var pageCurr;
var form;
var userId = document.getElementById("userId").innerText;
console.log("userId"+userId);
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        var checkRolePermission = document.getElementById("checkThat").innerText;
        if(checkRolePermission!='2' && checkRolePermission!='1'){
            window.location.href="/myError"
        }else {
            tableIns=table.render({
                elem: '#testResultList',
                url:'/testQuestion/getTestResultListByUserId',
                where: {"userId":userId},
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
                    ,{field:'userId', title:'用户ID',width:80, align:'center'}
                    ,{field:'userName', title:'用户名称',width:90,align:'center'}
                    ,{field:'zysz', title:'职业素质',width:110,align:'center',sort:true}
                    ,{field:'cysz', title:'创业素质',width:110,align:'center',sort:true}
                    ,{field:'gssfzs', title:'公司商法知识',align:'center',sort:true}
                    ,{field:'cwyxzs', title:'财务营销知识',align:'center',sort:true}
                    ,{field:'yyglzs', title:'运营管理知识',align:'center',sort:true}
                    ,{field:'cyjbnl', title:'创业基本能力',align:'center',sort:true}
                    ,{field:'yyglnl', title:'运营管理能力',align:'center',sort:true}
                    ,{field:'scyxnl', title: '市场营销能力',align:'center',sort:true}
                    ,{field:'startTime', title:'开始时间',align:'center',sort:true}
                    ,{field:'endTime', title: '结束时间',align:'center',sort:true}
                    ,{title:'操作',align:'center', width:160, toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    //得到数据总量
                    //console.log(count);
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(testResultTable)', function(obj){
                var data = obj.data;
                console.log(obj.event);
                if(obj.event === 'del'){
                    //删除
                    del(data,data.id);
                } else if(obj.event === 'look'){
                    checkResult(data.id);
                    //编辑

                }
            });

        }



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

function checkResult(recordId) {
    getDataById(recordId);
    layer.open({
        type: 1 //此处以iframe举例
        , title: '查看报告'
        , area: ['1000px','900px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#checkResult")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空值
            $("#testResult").val("");
        }
    });
}

function getDataById(recordId){
    $.ajax({
        type: "POST",
        data: {"recordId": recordId},
        url:"/testQuestion/getTestResultById",
        success:function(data) {
            //填充数据
            var _data = data[data.length-1];
            document.getElementById("testResult").innerHTML =  _data.testResult;
            var radarChartData = {
                labels : ["职业素质","创业素质","公司商法知识","财务营销知识","运营管理知识","创业基本能力","运营管理能力","市场营销能力"],
                datasets : [

                    {
                        fillColor : "rgba(151,187,205,0.5)",
                        strokeColor : "rgba(151,187,205,1)",
                        pointColor : "rgba(151,187,205,1)",
                        pointStrokeColor : "#fff",
                        // data : [51,50,79,44,50,80,33,50]
                        data : [_data.zysz,_data.cysz,_data.gssfzs,_data.cwyxzs,_data.yyglzs,_data.cyjbnl,_data.yyglnl,_data.scyxnl]
                    }
                ]

            }

            new Chart(document.getElementById("canvas").getContext("2d")).Line(radarChartData);

        }
        ,error: function (data) {
            // console.log(data);
        }
    });
}

$(function() {
    $("#printId").find('.print-link').on('click', function() {
        var canvas=document.getElementById("canvas");
        var image = document.getElementById("iii");
        image.src = canvas.toDataURL("image/png");
        canvas.style.display='none';
        $.print("#printId");
    });
});


