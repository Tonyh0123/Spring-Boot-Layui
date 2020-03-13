/**
 * 用户管理
 */
var pageCurr;
var form;
$(function() {

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#caseList',
            url:'/case/getCaseList',
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
                ,{field:'caseIdentifier', title:'案例编号',align:'center'}
                ,{field:'caseTitle', title:'案例标题',align:'center'}
                ,{field:'casePicUrl', title:'图片url',align:'center', templet: '<div>' +
                        '<img src="..{{ d.casePicUrl }}" style="width:30px; height:30px;" onclick="previewPhotos(\'{{ d.casePicUrl }}\')">' +
                        '</div>'}
                ,{field:'caseRoleName', title: '案例姓名',align:'center'}
                ,{field:'caseGraSchoolMajor', title: '毕业院校/专业',align:'center'}
                ,{field:'caseEntrepreneurialJourney', title: '创业历程',align:'center'}
                ,{title:'操作',align:'center', toolbar:'#optBar'}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                $("[data-field='userStatus']").children().each(function(){
                    if($(this).text()=='1'){
                        $(this).text("有效")
                    }else if($(this).text()=='0'){
                        $(this).text("失效")
                    }
                });
                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听工具条
        table.on('tool(caseTable)', function(obj){
            var data = obj.data;
            console.log(obj.event);
            if(obj.event === 'del'){
                //删除
                del(data,data.id);
            } else if(obj.event === 'edit'){
                //编辑
                openCaseUpdate(data);
            }
        });

        //监听提交
        form.on('submit(userSubmit)', function(data){
            // TODO 校验
            formSubmit(data);
            return false;
        });


    });


});

//提交表单
function formSubmit(obj){
    console.log("提交表单中的数据---->"+obj);
    $.ajax({
        type: "POST",
        data: $("#caseForm").serialize(),
        url: "/case/addCase",
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




function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function openCaseUpdate(data) {
    $("#id").val(data.id);
    $("#caseTitle").val(data.caseTitle);
    $("#caseRoleName").val(data.caseRoleName);
    $("#caseGraSchoolMajor").val(data.caseGraSchoolMajor);
    $("#caseEntrepreneurialJourney").val(data.caseEntrepreneurialJourney);
    $("#casePicUrl").val(data.casePicUrl);
    document.getElementById("previewButton").style.display='';

    layer.open({
        type: 1 //此处以iframe举例
        , title: '编辑成功案例'
        , area: ['1000px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['auto']
        , content: $("#addSuccessfulCase")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空原输入框的值
            $("#caseTitle").val("");
            $("#caseRoleName").val("");
            $("#caseGraSchoolMajor").val("");
            $("#caseEntrepreneurialJourney").val("");
            $("#casePicUrl").val("");
            $("#id").val("");
            //弹窗关闭后隐藏【预览图片】按钮
            document.getElementById("previewButton").style.display='none';

        }
    });
}

function openCaseInsert() {
    layer.open({
        type: 1 //此处以iframe举例
        , title: '新增成功案例'
        , area: ['1000px','650px']
        , shade: 0
        , maxmin: true
        , offset: ['auto']
        , content: $("#addSuccessfulCase")
        , btn: ['关闭'] //只是为了演示
        , btn2: function () {
            layer.closeAll();
        }
        ,end: function () {
            //弹窗关闭后清空原输入框的值
            $("#caseTitle").val("");
            $("#caseRoleName").val("");
            $("#caseGraSchoolMajor").val("");
            $("#caseEntrepreneurialJourney").val("");
            $("#casePicUrl").val("");
            $("#id").val("");
            //弹窗关闭后隐藏【预览图片】按钮
            document.getElementById("previewButton").style.display='none';

        }
    });
}

function previewPhotos(picUrl) {
    var element = document.getElementById('hhhhh');
    element.src = picUrl;
    layer.open({
        type: 1,
        title: false,
        closeBtn: 1,
        area: ['auto'],
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: $('#photottt')
    });
}


function caseShow() {
    window.location.href="/case/caseShow"
}

function load(obj){
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
            $.post("/case/del",{"id":id},function(data){
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


