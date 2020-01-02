$(function () {
    personalOrders.bindEvent();
})
var personalOrders = {
    bindEvent: function () {
        personalOrders.method.orderList(null, '#order-list-table');
    },
    event: {

    },
    method: {
        orderList: function (orderStatus, model) {
            layui.use('table', function () {
                if (model == '#order-list-table') {
                    $("#order-list").removeClass('layui-hide');
                    $("#order-deal-list").addClass('layui-hide');
                    $("#order-over-list").addClass('layui-hide');
                }
                if (model == 'order-deal-list-table') {
                    $("#order-deal-list").removeClass('layui-hide');
                    $("#order-list").addClass('layui-hide');
                    $("#order-over-list").addClass('layui-hide');
                }
                if (model == 'order-over-list-table') {
                    $("#order-over-list").removeClass('layui-hide');
                    $("#order-list").addClass('layui-hide');
                    $("#order-deal-list").addClass('layui-hide');
                }
                personalOrders.method.orderPublicTable(orderStatus, model);
            });
        },
        orderPublicTable: function (orderStatus, model) {
            var table = layui.table;
            table.render({
                elem: '#order-list-table'
                , height: 485
                , url: '/order/personal-orders'
                , where: {
                    orderStatus: orderStatus
                }
                , method: 'post'
                , page: true //开启分页
                , limits: [5, 10, 20]
                , limit: 10
                , cols: [[ //表头
                    {field: 'order_number', title: '订单号', width: 190}
                    , {
                        field: 'orginal_image', title: '原始图片', width: 100, templet: function (d) {
                            return '<div onclick="personalOrders.method.show_img(this)" ><img src="' + d.orginal_image + '" alt="" width="50px" height="50px"></a></div>';
                        }
                    }
                    , {field: 'hidden_data', title: '藏入信息', width: 220}
                    , {field: 'payment_amout', title: '已付金额', width: 95}
                    , {
                        field: 'result_image1', title: '结果图1', width: 100, templet: function (d) {
                            if (d.payment_status_format == '待支付') {
                                d.result_image1 = d.result_image1 + "?x-oss-process=style/resultImage_style2";
                            }
                            return '<div onclick="personalOrders.method.show_img(this)" ><img src="' + d.result_image1 + '" alt="" width="50px" height="50px"></a></div>';
                        }
                    }
                    , {
                        field: 'result_image2', title: '结果图2', width: 100, templet: function (d) {
                            if (d.payment_status_format == '待支付') {
                                d.result_image2 = d.result_image2 + "?x-oss-process=style/resultImage_style2";
                            }
                            return '<div onclick="personalOrders.method.show_img(this)" ><img src="' + d.result_image2 + '" alt="" width="50px" height="50px"></a></div>';
                        }
                    }
                    , {field: 'payment_status_format', title: '付款状态', width: 100}
                    , {field: 'order_time', title: '订单生成时间', width: 175}
                    , {field: 'operate', title: '操作', width: 253, toolbar: "#order-list-table-operate"}
                ]]
            });
            table.on('tool(order-list-table-fit)', function (obj) {
                if (obj.event == 'info') {
                    $.ajax({
                        url: '/order/download-image',
                        type: 'get',
                        data: {
                          order_number: obj.data.order_number
                        },
                        success: function (result) {
                            if (result.status_code == 200) {
                                personalOrders.method.downloadForCros(result.vo.result_image1, result.vo.order_number + "_result_1.bmp");
                                personalOrders.method.downloadForCros(result.vo.result_image2, result.vo.order_number + "_result_2.bmp");
                            } else {
                                layer.msg(result.message);
                            }
                        },
                        error: function () {
                            layer.msg('数据请求异常');
                        }
                    })
                }
                if (obj.event == 'edit') {
                    var data = {};
                    data.id = obj.data.id;
                    data.out_trade_no = personalOrders.method.generateOrderNumber();
                    $.ajax({
                        url: '/order/add-order-number',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify(data),
                        success: function (result) {
                            if (result.status_code == 200) {
                                personalOrders.method.payment(result.vo);
                            }
                        }
                    })
                }
                if (obj.event == 'del') {
                    layui.use('layer', function (layer) {
                        layer.confirm('确定删除该订单吗？删除之后无法恢复哦', {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            $.ajax({
                                url: '/order/delete',
                                type: 'get',
                                data: {
                                    id: obj.data.id
                                },
                                success: function (result) {
                                    if (result.status_code == 200) {
                                        layer.msg("删除成功");
                                        setTimeout(function () {
                                            location.href = "/orderView/personalOrders";
                                        },1000)
                                    } else {
                                        layer.msg(result.message);
                                    }
                                },
                                error: function () {
                                    layer.msg('数据异常');
                                }
                            })
                        });
                    })
                }
            });
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
        downloadForCros: function (imageUrl, imageName) {//跨域请求OSS图片 并下载
            var x = new XMLHttpRequest();
            x.open("GET", imageUrl, true);
            x.responseType = 'blob';
            x.onload=function(e) {
                var url = window.URL.createObjectURL(x.response)
                var a = document.createElement('a');
                a.href = url
                a.download = imageName;
                a.click()
            }
            x.send();
        },
        generateOrderNumber: function () {
            var vNow = new Date();
            var sNow = "";
            sNow += String(vNow.getFullYear());
            sNow += String(vNow.getMonth() + 1);
            sNow += String(vNow.getDate());
            sNow += String(vNow.getHours());
            sNow += String(vNow.getMinutes());
            sNow += String(vNow.getSeconds());
            sNow += String(vNow.getMilliseconds());
            return sNow;
        },
        payment: function (orderNumber) {
            var data = {};
            data.out_trade_no = orderNumber;
            data.total_amount = "5.00";
            data.subject = "图像隐写在线服务平台资源下载支付";
            data.body = "在您完成付款之后，平台会自动将生成的图片下载到您本地";
            $.ajax({
                url: '/order/pay',
                type: 'post',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function (result) {
                    location.href = "/orderView/href";
                },
                error: function () {
                    layer.msg('数据异常')
                }
            })
        }
    }
}