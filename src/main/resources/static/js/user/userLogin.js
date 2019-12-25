$(document).ready(function () {
    $(".layui-btn-fluid").click(function () {
        var data = {};
        data.mobile = $("#LAY-user-login-username").val();
        data.password = $("#LAY-user-login-password").val();
        $.ajax({
            url: '/user/login',
            contentType: "application/json",
            data: JSON.stringify(data) ,
            success: function (result) {
                if (result == "true") {
                    layer.msg('登录成功');
                    setTimeout(function () {
                        data = $("#LAY-user-login-username").val();
                        $.session.set('userPhone',data);
                        location.href = "/userView/index";
                    },1000)
                } else {
                    layer.msg('用户名或密码错误');
                }
            },
            error: function () {
                layer.msg('数据请求异常');
            }
        })
    })
})