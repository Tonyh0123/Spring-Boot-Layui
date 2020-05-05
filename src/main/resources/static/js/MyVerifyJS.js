layui.use('form', function() {
    var form = layui.form
//自定义验证规则
    form.verify({
        pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
        , confirmPass: function (value) {
            if ($('input[name=sysUserPwd]').val() !== value)
                return '两次密码输入不一致！';
        }
        , username: function (value, item) {
            if (!new RegExp("^[a-zA-Z0-9_\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
        }
        , stuID: function (value, item) {
            if (!new RegExp("^[0-9_\\s·]+$").test(value)) {
                return '学号只能为全数字组合';
            }
        }
        , verifyCode: function () {
            if (document.getElementById("verifyCode").value == MainVerifyCode) {
                verifyed = true;

            } else if (document.getElementById("verifyCode").value == "") {
                layer.msg('请验证邮箱！', {
                    time: 3000, //3s后自动关闭
                });
            } else {
                return '验证码不正确！';
            }
        }

    });
})