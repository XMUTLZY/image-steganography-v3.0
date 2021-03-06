$(function () {
    userIndexJs.bindEvent();
});
var userIndexJs = {
    bindEvent: function () {
        userIndexJs.event.isToDownloadImage();
        userIndexJs.event.initBanner();
        userIndexJs.event.userInfoImageUpload();
        userIndexJs.event.linkToInfoEmbedPage();
    },
    event: {
        linkToInfoEmbedPage: function () {
            layer.load();
            $.ajax({
                url: '/userView/infoEmbed',
                type: 'get',
                success: function (result) {
                    $("#field-title").removeClass("layui-hide");
                    $("#user-page").html(result);
                    layer.closeAll();
                }
            })
        },
        isToDownloadImage: function() {
            $.ajax({
                url: '/order/get-no-download',
                type: 'post',
                data: JSON.stringify({}),
                contentType: 'application/json',
                success: function (result) {
                    if (result.vo != null) {
                        $("#my-order").addClass("layui-badge-dot");
                        $("#personal-select").addClass("layui-badge-dot");
                        layer.open({
                            type: 1
                            ,title: false //不显示标题栏
                            ,closeBtn: false
                            ,area: '300px;'
                            ,shade: 0.8
                            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                            ,btn: ['前往下载', '等等吧']
                            ,btnAlign: 'c'
                            ,moveType: 1 //拖拽模式，0或者1
                            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; ' +
                                'color: #fff; font-weight: 300;">亲！<br><br>发现您有已付款但未下载资源的订单哦！' +
                                '<br><br>建议您立即前往下载 ^_^</div>'
                            ,yes: function(){
                                location.href = "/orderView/personalOrders";
                            }
                            ,btn2: function() {
                            }
                        });
                    }
                },
                error: function () {
                    layer.msg('数据请求异常');
                }
            })
        },
        initBanner: function () {
            layui.use('carousel', function(){
                var carousel = layui.carousel;
                //建造实例
                carousel.render({
                    elem: '#banner'
                    ,width: '100%' //设置容器宽度
                    ,arrow: 'always' //始终显示箭头
                    //,anim: 'updown' //切换动画方式
                });
            });
        },
        userInfoImageUpload: function() {
            layui.use('upload', function () {
                var $ = layui.jquery
                    , upload = layui.upload;
                //普通图片上传
                upload.render({
                    elem: '#edit-portrait-btn'
                    , url: '/api/image-upload-oss'
                    , accept: 'images'
                    , before: function () {
                        layer.load();
                    }
                    , done: function (res) {
                        layer.closeAll('loading');
                        $("#edit-portrait-img").attr('src', res.image_url);
                    }
                    , error: function (index, upload) {
                        layer.msg("错误");
                    }
                });
            });
        },
    },
    method: {
        userInfoDialog: function () {
            $.ajax({
                url: '/user/user-get',
                data: {
                    mobile: $("#user-operate-list").data("mobile")
                },
                type: 'get',
                success: function (result) {
                    $("#edit-mobile").val(result.vo.mobile);
                    $("#edit-account-name").val(result.vo.account_name);
                    $("#edit-career").val(result.vo.career);
                    $("#edit-city").val(result.vo.city);
                    $("#edit-company").val(result.vo.company);
                    $("#edit-email").val(result.vo.email);
                    $("#edit-real-name").val(result.vo.real_name);
                    $("#edit-portrait-img").attr('src', result.vo.portrait);
                    layui.use(['layer', 'form'], function (layer, form) {
                        layer.open({
                            type: 1
                            , skin: 'examine-refuse-popup'
                            , offset: 'auto'
                            , title: '个人资料'
                            , id: 'layer-id'
                            , area: ['600px', '600px']
                            , content: $("#dialog-edit-user-info")
                            , btn: ['确定', '取消']
                            , shade: 0.5 //不显示遮罩
                            , end: function () {
                                $("#dialog-edit-user-info").css("display", "none");
                            }
                            , yes: function () {
                                userIndexJs.method.updateUserBtn();
                            },
                            btn2: function () {

                            }
                        });
                    });
                }
            })
        },
        updateUserBtn: function () {
            layer.closeAll();
            var data = {};
            data.mobile = $("#edit-mobile").val();
            data.account_name = $("#edit-account-name").val();
            data.real_name = $("#edit-real-name").val();
            data.city = $("#edit-city").val();
            data.email = $("#edit-email").val();
            data.company = $("#edit-company").val();
            data.career = $("#edit-career").val();
            data.portrait = $("#edit-portrait-img").attr("src");
            $.ajax({
                url: '/admin/user-update',
                data: JSON.stringify(data),
                contentType: 'application/json',
                type: 'post',
                success: function (result) {
                    if (result.status_code == 200) {
                        layer.msg("修改成功");
                        adminIndexJs.event.userList();
                    } else {
                        layer.msg(result.message);
                    }
                }
            })
        },
        updatePasswordDialog: function () {
            layui.use(['layer', 'form'], function (layer, form) {
                layer.open({
                    type: 1
                    , skin: 'examine-refuse-popup'
                    , offset: 'auto'
                    , title: '修改密码'
                    , id: 'layer-id'
                    , area: ['550px', '300px']
                    , content: $("#dialog-update-password")
                    , btn: ['确定', '取消']
                    , shade: 0.5 //不显示遮罩
                    , end: function () {
                        $("#dialog-update-password").css("display", "none");
                    }
                    , yes: function () {
                        var data = {};
                        data.old_password = $("#old-password").val();
                        if ($("#new-password").val() != $("#again-new-password").val()) {
                            $("#update-password-tip").removeClass("layui-hide");
                        } else {
                            data.new_password = $("#new-password").val();
                            userIndexJs.method.updateUserPassword(data);
                        }
                    },
                    btn2: function () {

                    }
                });
            });
        },
        updateUserPassword: function (data) {
            $.ajax({
                url: "/user/update-password",
                type: "post",
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    if (result.status_code == 200) {
                        layer.closeAll();
                        setTimeout(function () {
                            layer.msg("修改成功");
                        }, 1000);
                    } else {
                        layer.msg(result.message)
                    }

                },
                error: function () {
                    layer.msg("数据请求异常");
                }
            })
        },
        linkToInfoExtractPage: function () {
            layer.load();
            $.ajax({
                url: '/userView/infoExtract',
                type: 'get',
                success: function (result) {
                    $("#field-title").removeClass("layui-hide");
                    $("#user-page").html(result);
                    layer.closeAll();
                }
            })
        },
        linkToAlgorithmDetail: function () {
            layer.load();
            $.ajax({
                url: '/userView/algorithmDetail',
                type: 'get',
                success: function (result) {
                    $("#field-title").addClass("layui-hide");
                    $("#user-page").html(result);
                    layer.closeAll();
                }
            })
        }
    }
}
