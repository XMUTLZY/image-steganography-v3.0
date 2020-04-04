$(function () {
    infoEmbed.bindEvent();
});

var infoEmbed = {
    bindEvent: function () {
        infoEmbed.event.orginalImageUpload();
    },
    event: {
        orginalImageUpload: function () {
            layui.use('upload', function () {
                var $ = layui.jquery
                    , upload = layui.upload;
                //普通图片上传
                upload.render({
                    elem: '#orginal-image-upload'
                    , url: '/api/image-upload-oss'
                    , accept: 'images'
                    , before: function () {
                        layer.load();
                    }
                    , done: function (res) {
                        layer.closeAll('loading');
                        $("#original-image").attr('src', res.image_url);
                    }
                    , error: function (index, upload) {
                        layer.msg("错误");
                    }
                });
            });
        }
    },
    method: {
        generateImage: function () {
            var data = {};
            data.hidden_data = $("#input-info").val();
            data.orginal_image = $("#original-image").attr("src");
            if (!data.orginal_image) {
                layer.msg("请先上传图片");
                return;
            }
            if (!data.hidden_data) {
                layer.msg("请输入需要藏入的信息");
                return;
            }
            layer.load();
            $.ajax({
                url: '/order/generate-image',
                data: JSON.stringify(data),
                type: 'post',
                contentType: 'application/json',
                success: function (result) {
                    layer.closeAll('loading');
                    layer.msg('信息藏入成功！')
                    $("#resultImage1").attr("src", result.result_image_map["resultImageOne"]);
                    $("#resultImage2").attr("src", result.result_image_map["resultImageTwo"]);
                    $("#psnr1").html("（主）:" + result.result_psnr_map["resultImageOne"]);
                    $("#psnr2").html("（辅）:" + result.result_psnr_map["resultImageTwo"]);
                },
                error: function () {
                    layer.msg('数据异常');
                }
            })
        },
        payAndDownload: function () {
            if (!$("#original-image").attr("src")) {
                layer.msg("请先上传图片");
                return;
            }
            if (!$("#input-info").val()) {
                layer.msg("请先输入您要藏入的信息");
                return;
            }
            if (!$("#resultImage1").attr("src") || !$("#resultImage2").attr("src")) {
                layer.msg("暂未发现有可支付的订单");
                return;
            }
            window.location.href = "/orderView/details";
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
        show_img: function (t) {
            layer.open({
                type: 1,
                title: '预览',
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '80%'], //宽高
                shadeClose: true, //开启遮罩关闭
                end: function (index, layero) {
                    return false;
                },
                content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
            });
        }
    }
}