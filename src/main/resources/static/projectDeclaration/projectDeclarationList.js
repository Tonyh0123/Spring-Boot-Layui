/**
 * 题目管理
 */
var pageCurr;
var form;
var userId = document.getElementById("userId").innerText;
var userName = document.getElementById("userName").innerText;
var index = 1; //填写表单时项目成员的个数
var divBox = 1;
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;
        var checkRolePermission = document.getElementById("checkThat").innerText;
        if(checkRolePermission!='2' && checkRolePermission!='1'){
            window.location.href="/myError"
        }else {
            tableIns=table.render({
                elem: '#projectListForStudent',
                url:'/declaration/getPersonalProjectEstablishList',
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
                    ,{field:'projectOwnerName', title:'申请人',width:350, align:'center'}
                    ,{field:'projectId', title:'项目编号',width:350,align:'center'}
                    ,{field:'projectName', title:'项目名称',width:500,align:'center',sort:true}
                    ,{title:'操作',align:'center', width:434, toolbar:'#optBar'}
                ]],
                done: function(res, curr, count){
                    //得到数据总量
                    //console.log(count);
                    pageCurr=curr;
                }
            });

            //监听工具条
            table.on('tool(projectForStudentTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    del(data,data.id);

                } else if(obj.event === 'projectDetail'){
                    projectDetail(data,data.id);

                }else if(obj.event === 'JDBG_CXJD'){
                    JDBG(data,"FQJDBG_CXJD");

                }else if(obj.event === 'JDBG_CZJD'){
                    JDBG(data,"FQJDBG_CZJD");

                }else if(obj.event === 'addFinance'){
                    addFinance(data);
                }else if(obj.event === 'checkDBSJ'){
                    checkDBSJ(data);
                }else if(obj.event === 'checkCXDBSJ'){
                    checkCXDBSJ(data);
                }else if(obj.event === 'checkCZDBSJ'){
                    checkCZDBSJ(data);
                }
            });

            form.on('submit(userSubmit)', function(data){
                //提交前的字符串拼接/填充
                var startTime = document.getElementById("startTime").value;
                var endTime = document.getElementById("endTime").value;
                var projectImplementationTime = startTime + '~' +endTime;
                $("#projectImplementationTime").val(projectImplementationTime);
                var userId = document.getElementById("userId").innerText;
                var userName = document.getElementById("userName").innerText;
                $("#projectOwnerId").val(userId);
                $("#projectOwnerName").val(userName);
                var projectMembers = [];
                for(var i = 1; i<=index; i++){
                    // console.log("No."+ i + ":"+" memberForm"+i.toString())
                    if(document.getElementById("memberForm"+i.toString())){
                        var records = {name:"",grade:"",stuID:"",major:"",phone:"",Email:""};
                        records.name = document.getElementById("projectMemberName"+i.toString()).value;
                        records.grade = document.getElementById("projectMemberGrade"+i.toString()).value;
                        records.stuID = document.getElementById("projectMemberStudentID"+i.toString()).value;
                        records.major = document.getElementById("projectMemberMajor"+i.toString()).value;
                        records.phone = document.getElementById("projectMemberPhone"+i.toString()).value;
                        records.Email = document.getElementById("projectMemberEmail"+i.toString()).value;
                        projectMembers.push(records);
                    }
                // console.log(JSON.stringify(projectMembers).toString());
                }
                $("#projectMembers").val(JSON.stringify(projectMembers).toString());

                var project_belong_fields = "";
                var r = document.getElementsByName("belong_fields");
                for (var i = 0; i < r.length; i++) {
                    if (r[i].checked) {
                        project_belong_fields += "," + r[i].value;
                    }
                }
                if (project_belong_fields.length > 0) {
                    project_belong_fields = project_belong_fields.substring(1);
                }
                $("#project_belong_fields").val(project_belong_fields);
                console.log(project_belong_fields);
                // TODO 校验
                formSubmit(data);
                return false;
            });

            form.on('submit(JDBGApplySubmit)', function(data){
                var primaryKey = document.getElementById("primaryKey").innerText;
                $.ajax({
                    type: "POST",
                    data: $.param({"id":primaryKey}) + '&' + $("#JDBGApplyForm").serialize(),
                    url: "/declaration/JDBGApply",
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

            form.on('submit(addFinanceSubmit)', function(data){
                $.ajax({
                    type: "POST",
                    data: $("#addFinanceForm").serialize(),
                    url: "/finance/addProjectFinance",
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
    $("#project_belong_fields_show").val(obj.project_belong_fields);
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
            '                    <td>'+members[i].phone+'</td>\n' +
            '                    <td>'+members[i].Email+'</td>\n' +
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
            document.getElementById("projectAddFormShow").reset();
            $("#project_name_message").val("");
            $("#message_owner").val("");
            $("#message_sender").val("");
        }
    });
}

function addProjectDeclaration() {
    layer.open({
        type: 1 //此处以iframe举例
        , title: '查看报告'
        , area: ['1000px','900px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#addProjectDeclaration")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("projectAddForm").reset();
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

function JDBG(obj,status) {
    $("#project_pass_status").val(status);
    document.getElementById("primaryKey").innerText = obj.id;
    layer.open({
        type: 1 //此处以iframe举例
        , title: '阶段变更'
        , area: ['650px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#JDBGApply")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("JDBGApplyForm").reset();
        }
    });
}

function addFinance(obj) {
    $("#project_id_finance").val(obj.projectId);
    $("#project_name_finance").val(obj.projectName);
    layer.open({
        type: 1
        , title: '添加融资条目'
        , area: ['650px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#addFinance")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            document.getElementById("addFinanceForm").reset();
        }
    });
}

function checkDBSJ(obj) {
    $("#LXDBSJ").val(obj.project_LX_DBSJ);
    layer.open({
        type: 1
        , title: '答辩时间'
        , area: ['650px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#checkDBSJ")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            $("#LXDBSJ").val("");
        }
    });
}

function checkCXDBSJ(obj) {
    $("#CXDBSJ").val(obj.project_CXJD_DBSJ);
    layer.open({
        type: 1
        , title: '答辩时间'
        , area: ['650px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#checkCXDBSJ")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            $("#CXDBSJ").val("");
        }
    });
}

function checkCZDBSJ(obj) {
    $("#CZDBSJ").val(obj.project_CZJD_DBSJ);
    layer.open({
        type: 1
        , title: '答辩时间'
        , area: ['650px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['60px']
        , content: $("#checkCZDBSJ")
        , btn: ['关闭'] //只是为了演示
        ,end: function () {
            //弹窗关闭后清空值
            $("#CZDBSJ").val("");
        }
    });
}




