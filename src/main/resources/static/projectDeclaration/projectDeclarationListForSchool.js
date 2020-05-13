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
                url:'/declaration/getProjectEstablishListForSchool',
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
                }else if(obj.event === 'setPassOtNot'){
                    projectPassed(data);
                }else if(obj.event === 'setLXOrNot'){
                    projectLX(data);
                }else if(obj.event === 'setTimeOfCXJD'){
                    setTimeOfDB(data,"CXJDBG_DBSJYAP");
                }else if(obj.event === 'setTimeOfCZJD'){
                    setTimeOfDB(data,"CZJDBG_DBSJYAP");
                }else if(obj.event === 'agreeCXJDBG'){
                    agreeOrNotJDBG("agreeCXJDBG",data);
                }else if(obj.event === 'agreeCZJDBG'){
                    agreeOrNotJDBG("agreeCZJDBG",data);
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
                //testing
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
                ,{field:'finance_money', title:'融资金额（万）',align:'center'}
                ,{field:'finance_type', title:'融资类型',align:'center',sort:true}
                ,{field:'finance_get_time', title:'融资获得时间',align:'center',sort:true}
            ]],
            done: function(res, curr, count){
                pageCurr=curr;
            }
        });

    });

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

function projectPassed(obj) {
    document.getElementById("primaryKey").innerText = obj.id;
    layer.open({
        type: 1 //此处以iframe举例
        , title: '项目审核'
        , area: ['600px','600px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#projectPassed")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            // location.reload();
            document.getElementById("projectPassedForm").reset();
            document.getElementById("shyj").style.display="none";
            document.getElementById("dbsj").style.display="none";
        }
    });
}

function projectLX(obj) {
    document.getElementById("primaryKey").innerText = obj.id;
    layer.open({
        type: 1 //此处以iframe举例
        , title: '项目审核'
        , area: ['600px','600px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#projectLX")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            // location.reload();
            document.getElementById("projectLXForm").reset();
            document.getElementById("XFTGTJ").style.display="none";
            $("#project_current_JD").val("");
        }
    });
}

function setTimeOfDB(obj,passStatus) {
    document.getElementById("primaryKey").innerText = obj.id;
    $("#project_pass_status_For_JDBG").val(passStatus);
    var title = "";
    if(passStatus == "CXJDBG_DBSJYAP"){
        var fileUrl = obj.project_JDBG_SQFJ.replace("/noticeFile/","F:/noticeFile/");
        $("#tips").after('<button style="margin-bottom: 10px; margin-left: 80%;  width: 100px" type="button" class="layui-btn" onclick="downloadFile(\''+ fileUrl +'\')">下载附件</button>');
        title = "创新阶段变更-答辩安排";
        document.getElementById("project_CZJD_DBSJ_div").style.display="none";
    }else if(passStatus == "CZJDBG_DBSJYAP"){
        var fileUrl = obj.project_JDBG_SQFJ.replace("/noticeFile/","F:/noticeFile/");
        $("#tips").after('<button style="margin-bottom: 25px; margin-left: 60%; width: 100px;" type="button" class="layui-btn" onclick="downloadFile(\''+ fileUrl +'\')">下载附件</button>');
        title = "成长阶段变更-答辩安排";
        document.getElementById("project_CXJD_DBSJ_div").style.display="none";
    }
    layer.open({
        type: 1 //此处以iframe举例
        , title: title
        , area: ['600px','600px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#setTimeOfDB")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            // location.reload();
            document.getElementById("setTimeOfDBForm").reset();
        }
    });
}

function agreeOrNotJDBG(BGJD,data) {
    var title = "";
    if(BGJD=="agreeCXJDBG"){
        title = "创新阶段";
        layer.confirm('请审核'+title+'阶段变更', {
            btn: ['通过','不通过'] //按钮
        }, function(){
            agreeJDBG(data,"创新阶段","CXJD_BGCG");
            layer.closeAll();
        }, function(){
            agreeJDBG(data,"已立项","CXJDBG_FAILED");
            layer.closeAll();
        });
    }else if(BGJD=="agreeCZJDBG"){
        title = "成长阶段";
        layer.confirm('请审核'+title+'阶段变更', {
            btn: ['通过','不通过'] //按钮
        }, function(){
            agreeJDBG(data,"成长阶段","CZJD_BGCG");
            layer.closeAll();
        }, function(){
            agreeJDBG(data,"创新阶段","CZJDBG_FAILED");
            layer.closeAll();
        });
    }

}

function agreeJDBG(obj,JD,status) {
    $.ajax({
        type: "POST",
        data: {"id":obj.id,"project_current_JD":JD,"project_pass_status":status},
        url: "/declaration/setCurrentJD",
        success: function (data) {
            if (data.code == 1) {
                layer.alert("审核操作成功",function(){
                    location.reload();
                });
            } else {
                layer.alert(data.msg);
            }
        }
    });
}

function addProjectMembers() {
    index += 1;
    divBox += 1;
    var addStep = index ;
    $("#memberForm1").after('<div id="memberForm'+ addStep +'" style="padding: 20px">\n' +
        '                <div style="border: #009688 solid 2px;">\n' +
        '                <div id="delete'+ addStep +'" style="position: absolute; margin-bottom: -20px; margin-left: 88%; z-index:9999; display: none">\n' +
        '                <button type="button" class="layui-btn layui-btn-xs layui-btn-danger" onclick="removeMembersDiv(\'memberForm'+addStep+'\')"> X删除该项 </button>\n' +
        '                </div>\n' +
        '                <div class="layui-form-item">\n' +
        '                    <label class="layui-form-label ">姓名</label>\n' +
        '                    <div class="layui-input-block">\n' +
        '                        <input id="projectMemberName'+ addStep +'"  lay-verify="required" autocomplete="off" class="layui-input" type="text"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="layui-form-item">\n' +
        '                    <label class="layui-form-label label-required-prev">年级</label>\n' +
        '                    <div class="layui-input-block">\n' +
        '                        <input id="projectMemberGrade'+ addStep +'"  lay-verify="required" autocomplete="off" class="layui-input" type="text"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="layui-form-item">\n' +
        '                    <label class="layui-form-label label-required-prev">学号</label>\n' +
        '                    <div class="layui-input-block">\n' +
        '                        <input id="projectMemberStudentID'+ addStep +'"  lay-verify="required" autocomplete="off" class="layui-input" type="text"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="layui-form-item">\n' +
        '                    <label class="layui-form-label label-required-prev">专业</label>\n' +
        '                    <div class="layui-input-block">\n' +
        '                        <input id="projectMemberMajor'+ addStep +'"  lay-verify="required" autocomplete="off" class="layui-input" type="text"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="layui-form-item">\n' +
        '                    <label class="layui-form-label label-required-prev">联系电话</label>\n' +
        '                    <div class="layui-input-block">\n' +
        '                        <input id="projectMemberPhone'+ addStep +'"  lay-verify="required" autocomplete="off" class="layui-input" type="text"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="layui-form-item">\n' +
        '                    <label class="layui-form-label label-required-prev">Email</label>\n' +
        '                    <div class="layui-input-block">\n' +
        '                        <input id="projectMemberEmail'+ addStep +'"  lay-verify="required" autocomplete="off" class="layui-input" type="text"/>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            </div>');
    if(divBox > 1){
        $( "[id^='delete']" ).css('display','block');
    }

    console.log("after"+index);
}

function removeMembersDiv(divId) {
    var flag = parseInt(divId.charAt(divId.length-1));

    var box = document.getElementById(divId);
    box.parentNode.removeChild(box);
    divBox -= 1;
    if(divBox == 1){
        $( "[id^='delete']" ).css('display','none');
    }
}





