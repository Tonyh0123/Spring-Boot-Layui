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
        tableIns=table.render({
            elem: '#projectInfo',
            url:'/declaration/getLXProjectList',
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
                {field:'projectName', title:'项目', align:'center'}
                ,{field:'projectIntroduction', title:'项目简介',align:'center'}
                ,{field:'projectSSZYDL', title:'所属领域（专业大类）',align:'center'}
                ,{field:'projectBelongSchool', title:'所属高校',align:'center'}
                ,{field:'project_current_JD', title:'项目阶段',align:'center'}
            ]],
            skin:'line',
            size:'lg',
            done: function(res, curr, count){
                pageCurr=curr;
            }
        });

        //监听行单击事件
        table.on('row(projectInfoTable)', function(obj){
            viewProjectDetail(obj.data);
        });
    });

});

function viewProjectDetail(obj) {
    document.getElementById("project_name").innerText = obj.projectName;

    document.getElementById("belong_school").innerText = obj.projectBelongSchool;

    document.getElementById("project_keyWords").innerText = obj.projectKeyWords;

    document.getElementById("project_introduction").innerText = obj.projectIntroduction;

    document.getElementById("project_current_JD").innerText = obj.project_current_JD;


    $('#belong_fields').append('<button class="layui-btn layui-btn-xs">'+obj.projectSSZYDL+'</button>');


    //为了留言
    $("#project_name_message").val(obj.projectName);
    $("#message_owner").val(obj.projectOwnerName);
    $("#message_sender").val(userName);


    var members = JSON.parse(obj.projectMembers);

    for(var i = 0; i<members.length; i++){
        console.log(members[i].grade);
        $('#tbody').append('<tr>\n' +
            '                    <td>'+members[i].name+'</td>\n' +
            '                    <td>'+members[i].grade+'</td>\n' +
            '                    <td>'+members[i].stuID+'</td>\n' +
            '                    <td>'+members[i].major+'</td>\n' +
            '                </tr>');

    }

    //加载融资情况
    layui.use('table', function(){
        var table = layui.table;
        FinanceTable=table.render({
            elem: '#projectFinance',
            url:'/finance/getProjectFinanceByProjectId',
            where: {"projectId":obj.projectId},
            method: 'post', //默认：get请求
            page: false,
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
                ,{field:'finance_company', title:'融资方', align:'center'}
                ,{field:'finance_money', title:'融资金额',align:'center'}
                ,{field:'finance_type', title:'融资类型',align:'center',sort:true}
                ,{field:'finance_get_time', title:'融资获得时间',align:'center',sort:true}
            ]],
            done: function(res, curr, count){
                pageCurr=curr;
            }
        });

    });
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            title: obj.projectName,
            type: 1,
            area: ['1900px', '950px'],
            fixed: false, //不固定
            maxmin: true,
            content: $("#viewProjectDetail"),
            skin: 'layerTC',
            end: function () {
            //弹窗关闭后清空值
                $("#belong_fields").html('');
                $("#tbody").html('');
                document.getElementById("project_name").innerText = "";
                document.getElementById("belong_school").innerText = "";
                document.getElementById("project_keyWords").innerText = "";
                document.getElementById("project_introduction").innerText = "";
                document.getElementById("project_current_JD").innerText = "";
                $("#project_name_message").val("");
                $("#message_owner").val("");
                $("#message_sender").val("");
        }
        });
    });
};





