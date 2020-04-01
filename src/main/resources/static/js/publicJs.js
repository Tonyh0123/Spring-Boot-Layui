layui.use(['form', 'layedit', 'laydate','layer'], function(){
    var form = layui.form
        ,layer = layui.layer

});
function chooseRegi(){
    console.log("tanchu");
    layer.msg('<center><b>请选择注册类型</b></center>', {
        offset: '100px',
        time: 20000,
        // title: '注册类型',
        btn: ['学生注册', '院校申请', '企业申请'],
        // skin: 'demo-class',
        yes: function () {
            window.location.href="/registration";
        },
        btn2: function () {
            window.location.href="/registrationOfSchool";
        }
    });
}

function confirmTest() {
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: ['我知道了', '关闭']
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 40px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">即将前往<br><b>大学生综合创业能力测试</b><br><br>请您确保以下注意事项：<br>时间充足<br>系统正常<br>网络正常<br>请勿中途退出<br><br>本次测试限时&nbsp;&nbsp;<b>40</b>&nbsp;&nbsp;分钟<br></div>'
        ,success: function(layero){
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: '/testPaper'
                ,target: '_blank'
            });
        }
    });
}