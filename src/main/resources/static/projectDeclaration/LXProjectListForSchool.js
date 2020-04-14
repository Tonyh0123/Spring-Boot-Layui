/**
 * 题目管理
 */
var pageCurr;
var form;
var userId = document.getElementById("userId").innerText;
var index = 1; //填写表单时项目成员的个数
var divBox = 1;
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        var checkRolePermission = document.getElementById("checkThat").innerText;
        if(checkRolePermission!='3' && checkRolePermission!='1'){
            window.location.href="/myError"
        }else {
            tableIns=table.render({
                elem: '#projectListForSchool',
                url:'/declaration/getLXProjectListForSchool',
                where: {"userId":userId},
                method: 'post', //默认：get请求
                cellMinWidth: 300,
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
                    ,{field:'projectOwnerName', title:'申请人',width:180, align:'center'}
                    ,{field:'projectId', title:'项目编号',width:350,align:'center'}
                    ,{field:'projectName', title:'项目名称',width:350,align:'center',sort:true}
                        ,{field:'project_current_JD', title:'项目所处阶段',width:300,align:'center',sort:true}
                    ,{title:'操作',align:'center', width:453, toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    //得到数据总量
                    //console.log(count);
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(projectForSchoolTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'projectDetail'){
                    projectDetail(data,data.id);
                }else if(obj.event === 'recommend2Company'){
                    recommend2Company(data,"1");
                }else if(obj.event === 'deleteRecommended'){
                    recommend2Company(data,"0");
                }
            });

            form.on('submit(userSubmit)', function(data){
                // TODO 校验
                formSubmit(data);
                return false;
            });

        }



    });


});

function formSubmit(obj){
    $.ajax({
        type: "POST",
        data: $("#projectAddForm").serialize(),
        url: "/declaration/addProjectEstablishApply",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg,function(){
                    layer.closeAll();
                    // location.reload();
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
        where: {"userId":userId}
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
            $.post("/declaration/del",{"id":id},function(data){
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

function projectDetail(obj,id) {
    document.getElementById("primaryKey").innerText = obj.id;
    $("#projectIdShow").val(obj.projectId);
    $("#projectNameShow").val(obj.projectName);
    $("#projectKeyWordsShow").val(obj.projectKeyWords);
    $("#projectTypeShow").val(obj.projectType);
    $("#projectBelongSchoolShow").val(obj.projectBelongSchool);
    if(obj.projectEstablishTime==null || obj.projectEstablishTime==""){
        $("#projectEstablishTimeShow").val("未立项");
    }else {
        $("#projectEstablishTimeShow").val(obj.projectEstablishTime);
    }
    $("#projectIntroductionShow").val(obj.projectIntroduction);
    $("#projectSSXKMLShow").val(obj.projectSSXKML);
    $("#projectSSZYDLShow").val(obj.projectSSZYDL);
    $("#projectApplyReasonShow").val(obj.projectApplyReason);
    $("#projectApplyTableShow").val(obj.projectApplyTable);
    $("#projectPlanningBookShow").val(obj.projectPlanningBook);
    $("#projectImplementationTimeShow").val(obj.projectImplementationTime);
    $("#projectExpectedResultsShow").val(obj.projectExpectedResults);
    $("#projectMoneyBudgetsShow").val(obj.projectMoneyBudgets);

    if(obj.projectXFTGTJ==null || obj.projectXFTGTJ==""){
        $("#projectXFTGTJShow").val("未立项");
    }else {
        $("#projectXFTGTJShow").val(obj.projectXFTGTJ);
    }

    $("#projectGuidanceTeacherShow").val(obj.projectGuidanceTeacher);
    $("#projectMembersShow").val(obj.projectMembers);

    var members = JSON.parse(obj.projectMembers);

    for(var i = 0; i<members.length; i++){
        console.log(members[i].grade);
        $('#tbody').append('<tr>\n' +
            '                    <td>'+members[i].name+'</td>\n' +
            '                    <td>'+members[i].grade+'</td>\n' +
            '                    <td>'+members[i].stuID+'</td>\n' +
            '                    <td>'+members[i].major+'</td>\n' +
            '                    <td>'+members[i].phone+'</td>\n' +
            '                    <td>'+members[i].Email+'</td>\n' +
            '                </tr>');

    }

    layer.open({
        type: 1 //此处以iframe举例
        , title: '项目详情'
        , area: ['1000px','900px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#showProjectDetail")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空值
            location.reload();
            // document.getElementById("projectAddFormShow").reset();
        }
    });
}

function recommend2Company(obj,sign) {
    $.ajax({
        type: "POST",
        data: {"id":obj.id,"project_recommend_sign":sign},
        url: "/declaration/recommendOrNot",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg,function(){
                    location.reload();
                });
            } else {
                layer.alert(data.msg);
            }
        }
    });
}







