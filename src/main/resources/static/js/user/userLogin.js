$(document).ready(function () {
    $(".layui-btn-fluid").click(function () {
        var data = {};
        data.mobile = $("#LAY-user-login-username").val();
        data.password = $("#LAY-user-login-password").val();
        $.ajax({
            url: '/user/login',
            contentType: "application/json",
            data: JSON.stringify(data),
            type: 'post',
            success: function (result) {
                if (result.status_code == 200) {
                    layer.msg(result.message);
                    setTimeout(function () {
                        data = $("#LAY-user-login-username").val();
                        location.href = "/userView/index";
                    },1000)
                } else {
                    layer.msg(result.message);
                }
            },
            error: function () {
                layer.msg('数据请求异常');
            }
        })
    })
})