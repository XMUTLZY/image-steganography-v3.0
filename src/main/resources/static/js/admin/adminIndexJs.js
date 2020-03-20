$(function () {
    adminIndexJs.bindEvent();
});
var adminIndexJs = {
    bindEvent: function () {
        adminIndexJs.event.userList();
        adminIndexJs.event.imageUpload();
    },
    event: {
        imageUpload: function() {
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
        userList: function () {
            layui.use('table', function () {
                var table = layui.table;
                $("#user-list").removeClass('layui-hide');
                $("#admin-list").addClass('layui-hide');
                $("#order-list").addClass('layui-hide');
                $("#admin-operate-list").addClass('layui-hide');
                //第一个实例
                table.render({
                    elem: '#user-list-table'
                    , height: 485
                    , url: '/admin/user-list'
                    , page: true //开启order-list分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'mobile', title: '手机', width: 120}
                        , {field: 'account_name', title: '用户名', width: 100}
                        , {field: 'real_name', title: '姓名', width: 100}
                        , {field: 'city', title: '城市', width: 100}
                        , {field: 'email', title: '邮箱', width: 160}
                        , {field: 'company', title: '单位', width: 190}
                        , {field: 'career', title: '职业', width: 110}
                        , {
                            field: 'portrait', title: '头像', width: 100, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {field: 'update_time', title: '更新时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.update_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {
                            field: 'operate',
                            title: '操作',
                            width: 147,
                            fixed: 'right',
                            toolbar: "#user-list-table-operate"
                        }
                    ]]
                });
                table.on('tool(user-list-table-fit)', function (obj) {
                    if (obj.event === 'del') {
                        layer.confirm('确定删除该用户？', function (index) {
                            $.ajax({
                                url: '/admin/user-delete',
                                data: {
                                    mobile: obj.data.mobile
                                },
                                type: 'get',
                                success: function (result) {
                                    if (result.code == 0) {
                                        layer.msg("删除成功");
                                        adminIndexJs.event.userList();
                                    }
                                },
                                error: function () {
                                    layer.msg("数据请求异常");
                                }
                            })
                        })
                    } else {
                        $.ajax({
                            url: '/admin/user-get',
                            data: {
                                mobile: obj.data.mobile
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
                                        , title: '编辑用户'
                                        , id: 'layer-id'
                                        , area: ['600px', '700px']
                                        , content: $("#dialog-edit-user-info")
                                        , btn: ['确定', '取消']
                                        , shade: 0.5 //不显示遮罩
                                        , end: function () {
                                            $("#dialog-edit-user-info").css("display", "none");
                                        }
                                        , yes: function () {
                                            adminIndexJs.method.updateUserBtn();
                                        },
                                        btn2: function () {

                                        }
                                    });
                                });
                            }
                        })
                    }
                })
            });
        }
    },
    method: {
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
        userSearch: function () {
            var data = {};
            data.mobile = $("#search-mobile").val();
            data.company = $("#search-company").val();
            data.account_name = $("#search-account-name").val();
            layui.use('table', function () {
                var table = layui.table;
                //第一个实例
                table.render({
                    elem: '#user-list-table'
                    , height: 485
                    , where: {
                        mobile: $("#search-mobile").val(),
                        company: $("#search-company").val(),
                        account_name: $("#search-account-name").val()
                    }
                    , method: 'post'
                    , contentType: 'application/json'
                    , url: '/admin/user-search'
                    , page: true //开启分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'mobile', title: '手机', width: 120}
                        , {field: 'account_name', title: '用户名', width: 100}
                        , {field: 'real_name', title: '姓名', width: 100}
                        , {field: 'city', title: '城市', width: 100}
                        , {field: 'email', title: '邮箱', width: 160}
                        , {field: 'company', title: '单位', width: 190}
                        , {field: 'career', title: '职业', width: 110}
                        , {
                            field: 'portrait', title: '头像', width: 100, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {field: 'update_time', title: '更新时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.update_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {
                            field: 'operate',
                            title: '操作',
                            width: 147,
                            fixed: 'right',
                            toolbar: "#user-list-table-operate"
                        }
                    ]]
                });
            });
        },
        orderList: function () {
            layui.use('table', function () {
                var table = layui.table;
                $("#order-list").removeClass('layui-hide');
                $("#admin-list").addClass('layui-hide');
                $("#user-list").addClass('layui-hide');
                $("#admin-operate-list").addClass('layui-hide');
                //第一个实例
                table.render({
                    elem: '#order-list-table'
                    , height: 485
                    , url: '/admin/order-list'
                    , page: true //开启分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'order_number', title: '订单号', width: 220, sort: true, fixed: 'left'}
                        , {field: 'user_mobile', title: '手机号', width: 120}
                        , {field: 'user_account_name', title: '用户名', width: 100}
                        , {
                            field: 'orginal_image', title: '原始图片', width: 100, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.orginal_image + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {
                            field: 'result_image1', title: '结果图1', width: 100, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.result_image1 + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {
                            field: 'result_image2', title: '结果图2', width: 100, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.result_image2 + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'hidden_data', title: '藏入信息', width: 130}
                        , {field: 'payment_status_format', title: '付款状态', width: 105}
                        , {field: 'payment_amout', title: '已付金额', width: 105}
                        , {field: 'order_time', title: '订单时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.order_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                    ]]
                });
            });
        },
        adminList: function () {
            layui.use('table', function () {
                var table = layui.table;
                $("#admin-list").removeClass('layui-hide');
                $("#user-list").addClass('layui-hide');
                $("#order-list").addClass('layui-hide');
                $("#admin-operate-list").addClass('layui-hide');
                //第一个实例
                table.render({
                    elem: '#admin-list-table'
                    , height: 485
                    , url: '/admin/admin-list'
                    , page: true //开启分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'user_name', title: '用户名', width: 120}
                        , {field: 'mobile', title: '手机号', width: 120}
                        , {field: 'real_name', title: '姓名', width: 90}
                        , {field: 'role_name', title: '角色', width: 130}
                        , {field: 'email', title: '邮箱', width: 150}
                        , {
                            field: 'portrait', title: '头像', width: 90, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {field: 'update_time', title: '修改时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.update_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {
                            field: 'operate',
                            title: '操作',
                            width: 80,
                            toolbar: "#admin-list-table-operate",
                        }
                    ]]
                });
                table.on('tool(admin-list-table-fit)', function (obj) {
                    if (obj.event === 'del') {
                        layer.confirm('确定删除该管理员？', function (index) {
                            var dataRequest = {};
                            dataRequest.mobile = obj.data.mobile;
                            $.ajax({
                                url: '/admin/admin-delete',
                                data: JSON.stringify(dataRequest),
                                contentType: 'application/json',
                                type: 'post',
                                success: function () {
                                    layer.msg("删除成功");
                                    adminIndexJs.method.adminList();
                                }
                            })
                        })
                    }
                })
            });
        },
        adminSearch: function () {
            layui.use('table', function () {
                var table = layui.table;
                //第一个实例
                table.render({
                    elem: '#admin-list-table'
                    , height: 485
                    , where: {
                        id: $("#search-admin-id").val(),
                        user_name: $("#search-admin-user-name").val(),
                        mobile: $("#search-admin-phone").val(),
                        role_id: $('select[name="role-name-select"]').find('option:selected').val()
                    }
                    , method: 'post'
                    , contentType: 'application/json'
                    , url: '/admin/admin-search'
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'user_name', title: '用户名', width: 120}
                        , {field: 'mobile', title: '手机号', width: 120}
                        , {field: 'real_name', title: '姓名', width: 90}
                        , {field: 'role_name', title: '角色', width: 130}
                        , {field: 'email', title: '邮箱', width: 150}
                        , {
                            field: 'portrait', title: '头像', width: 90, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {field: 'update_time', title: '修改时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.update_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {
                            field: 'operate',
                            title: '操作',
                            width: 70,
                            toolbar: "#admin-list-table-operate",
                        }
                    ]]
                });
            });
        },
        addAdmin: function () {
            layui.use('layer', function (layer) {
                layer.open({
                    type: 1,
                    title: '添加管理员',
                    shift: 7,
                    area: 'auto',
                    maxWidth: 1000,
                    maxHeight: 800,
                    shadeClose: true,
                    content: $("#add-admin-panel")
                });
            });
        },
        subAdmin: function () {
            layer.close(layer.index);
            var data = {};
            data.mobile = $("#add-admin-mobile").val();
            data.password = $("#add-admin-password").val();
            data.user_name = $("#userName").val();
            data.role_id = $("#add-admin-role").val();
            $.ajax({
                url: '/admin/admin-add',
                type: 'post',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function (result) {
                    if (result.status_code == 200) {
                        layer.msg('添加管理员成功');
                        adminIndexJs.method.adminList();
                    } else {
                        layer.msg('添加管理员失败');
                    }
                },
                error: function () {
                    layer.msg('数据异常');
                }
            })
        },
        adminOperateRecords: function () {
            $("#admin-operate-list").removeClass('layui-hide');
            $("#admin-list").addClass('layui-hide');
            $("#user-list").addClass('layui-hide');
            $("#order-list").addClass('layui-hide');
            layui.use('laytpl', function () {
                var laytpl = layui.laytpl;
                var getTpl = document.getElementById("admin-operate-record-list").innerHTML;
                $.ajax({
                    url: '/admin/operate-records',
                    type: 'post',
                    success: function (result) {
                        laytpl(getTpl).render(result, function (html) {
                            document.getElementById("admin-operate-list").innerHTML = html;
                        });
                    },
                    error: function () {
                        layer.msg("数据请求异常");
                    }
                });
            })
        },
        adminOperateRecordsSearchBtn: function () {
            layui.use('laytpl', function () {
                var laytpl = layui.laytpl;
                var getTpl = document.getElementById("admin-operate-record-list").innerHTML;
                layer.load();
                $.ajax({
                    url: '/admin/operate-records',
                    data: {
                        key: $("#key-search").val()
                    },
                    type: 'post',
                    success: function (result) {
                        layer.closeAll('loading');
                        laytpl(getTpl).render(result, function (html) {
                            document.getElementById("admin-operate-list").innerHTML = html;
                        });
                    },
                    error: function () {
                        layer.msg("数据请求异常");
                    }
                })
            })
        },
        show_img: function (t) {
            var t = $(t).find("img");
            //页面层
            layer.open({
                type: 1,
                title: '头像',
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '80%'], //宽高
                shadeClose: true, //开启遮罩关闭
                end: function (index, layero) {
                    return false;
                },
                content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
            });
        },
        showLocationByMap: function (dom) {
            var id = $(dom).data("id");
            $.ajax({
                url: '/common/get-location',
                type: 'get',
                data: {
                    ip: $("#"+id).html(),
                },
                success: function (result) {
                    result = eval("(" + result.message + ")");
                    adminIndexJs.method.showMap(result.content.point.x, result.content.point.y)
                },
                error: function () {
                    layer.msg("获取定位失败")
                }
            })

        },
        showMap: function (x, y) {
            // 百度地图API功能
            var map = new BMap.Map("allmap");    // 创建Map实例
            map.centerAndZoom(new BMap.Point(x, y), 11);  // 初始化地图,设置中心点坐标和地图级别
            //添加地图类型控件
            map.addControl(new BMap.MapTypeControl({
                mapTypes:[
                    BMAP_NORMAL_MAP,
                    BMAP_HYBRID_MAP
                ]}));
            map.setCurrentCity("泉州");          // 设置地图显示的城市 此项是必须设置的
            map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            var new_point = new BMap.Point(x, y);
            var marker = new BMap.Marker(new_point);  // 创建标注
            map.addOverlay(marker);              // 将标注添加到地图中
            map.panTo(new_point);
            layui.use(['layer', 'form'], function (layer, form) {
                layer.open({
                    type: 1
                    , skin: 'examine-refuse-popup'
                    , offset: 'auto'
                    , title: '地图'
                    , id: 'layer-id'
                    , area: ['750px', '550px']
                    , content: $("#map-popup")
                    , btn: ['确定', '取消']
                    , shade: 0.5 //不显示遮罩
                    , end: function () {
                        $("#map-popup").css("display", "none");
                    }
                });
            });
        }
    }
}
