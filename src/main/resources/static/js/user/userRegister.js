$(function () {
    nums = 60;
    userRegisterJs.bindEvent();
});

var userRegisterJs = {
    bindEvent: function () {
        userRegisterJs.event.listenUserIsRegister();
        userRegisterJs.event.sendCode();
        userRegisterJs.event.passwordCheck();
        userRegisterJs.method.saveBtn();
    },
    event: {
        listenUserIsRegister: function () {
            //监听手机号是否已经被注册
            $("#LAY-user-login-cellphone").blur(function () {
                var data = {};
                data.mobile = $("#LAY-user-login-cellphone").val();
                $.ajax({
                    url: '/user/get',
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    type: 'post',
                    success: function (result) {
                        if (result.is_register == 1) {
                            layer.msg('该手机号已被注册');
                        }
                    },
                    error: function () {
                        layer.msg('数据请求异常');
                    }
                })
            })
        },
        sendCode: function () {
            $("#LAY-user-getsmscode").click(function () {
                var data = {};
                data.mobile = $("#LAY-user-login-cellphone").val();
                //调用短信api接口发送验证码
                $.ajax({
                    url: '/api/send-code',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    type: 'post',
                    success: function (result) {
                        if (result.status_code == 200) {
                            if (result.code_status == "1") {
                                layer.msg('验证码发送成功');
                                userRegisterJs.method.codeBtnChange();
                            } else if (result.code_status == "-4") {
                                layer.msg('手机号码格式不正确');
                            } else if (result.code_status == "-41") {
                                layer.msg('请输入手机号码');
                            } else layer.msg(result.message);
                        }
                    },
                    error: function () {
                        layer.msg('数据异常');
                    }
                })
            })
        },
        passwordCheck: function () {
            var password = $("#LAY-user-login-password").val();
            var repass = $("#LAY-user-login-repass").val();
            if (password != repass) {
                return false;
            } else {
                return true;
            }
        }
    },
    method: {
        codeBtnChange: function () {
            if (nums == 0) {
                $("#LAY-user-getsmscode").attr("disabled", false);//设置按钮可点击
                $("#LAY-user-getsmscode").html("获取验证码"); //设置按钮内容
                nums = 60;
            } else {
                $("#LAY-user-getsmscode").attr("disabled", true);//设置按钮不可点击
                $("#LAY-user-getsmscode").html(nums + "s后重新获取"); //设置按钮内容
                nums--;
                setTimeout(function () {
                    userRegisterJs.method.codeBtnChange();
                }, 1000)
            }
        },
        saveBtn: function () {
            $("#register-button").click(function () {
                var data = {};
                data.mobile = $("#LAY-user-login-cellphone").val();
                data.code = $("#LAY-user-login-vercode").val();
                data.password = $("#LAY-user-login-password").val();
                data.account_name = $("#LAY-user-login-nickname").val();
                if (!userRegisterJs.event.passwordCheck()) {
                    layer.msg('两次输入的密码不一致');
                } else {
                    $.ajax({
                        url: '/user/register',
                        data: JSON.stringify(data),
                        contentType: 'application/json',
                        type: 'post',
                        success: function (result) {
                            if (result.status_code == 200) {
                                layer.msg('注册成功');
                                setTimeout(function () {
                                    location.href = "/userView/login";
                                }, 1000)
                            } else {
                                layer.msg(result.message);
                            }
                        },
                        error: function () {
                            layer.msg('数据异常');
                        }
                    })
                }
            })
        }
    }
}
