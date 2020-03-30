
$(function() {
    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, layer = layui.layer
            , laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#noticeDate'
        });

        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //查询后端数据
            reloadNotice();
            return false;
        });

        laydate.render({
            elem: '#policyDate'
        });

        //监听搜索框
        form.on('submit(policySearchSubmit)', function (data) {
            //查询后端数据
            reloadPolicy();
            return false;
        });
    });

});