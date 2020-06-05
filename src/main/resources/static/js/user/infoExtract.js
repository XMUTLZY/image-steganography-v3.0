$(function () {
    infoExtract.bindEvent();
});

var infoExtract = {
    bindEvent: function () {
        infoExtract.event.majorImageUpload();
        infoExtract.event.assistImageUpload();
    },
    event: {
        majorImageUpload: function() {
            layui.use('upload', function () {
                var $ = layui.jquery
                    , upload = layui.upload;
                //普通图片上传
                upload.render({
                    elem: '#major-image-upload'
                    , url: '/api/image-upload-oss'
                    , accept: 'images'
                    , before: function () {
                        layer.load();
                    }
                    , done: function (res) {
                        layer.closeAll('loading');
                        $("#major-image").attr('src', res.image_url);
                    }
                    , error: function (index, upload) {
                        layer.msg("错误");
                    }
                });
            });
        },
        assistImageUpload: function() {
            layui.use('upload', function () {
                var $ = layui.jquery
                    , upload = layui.upload;
                //普通图片上传
                upload.render({
                    elem: '#assist-image-upload'
                    , url: '/api/image-upload-oss'
                    , accept: 'images'
                    , before: function () {
                        layer.load();
                    }
                    , done: function (res) {
                        layer.closeAll('loading');
                        $("#assist-image").attr('src', res.image_url);
                    }
                    , error: function (index, upload) {
                        layer.msg("错误");
                    }
                });
            });
        }
    },
    method: {
        downloadOrginalImage: function() {
            var imgUrl = $("#orginal-image").attr("src");
            var imageName = "orginal.bmp";
            if (!imgUrl) {
                layer.msg("没有发现需要下载的图像");
                return;
            }
            infoExtract.method.downloadForCros(imgUrl, imageName);
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
        },
        extractImage: function () {
            layer.load();
            var majorImage = $("#major-image").attr("src");
            var assistImage = $("#assist-image").attr("src");
            if (!majorImage || !assistImage) {
                layer.msg("请先上传图像");
                return;
            }
            var data = {};
            data.major_image = majorImage;
            data.assist_image = assistImage;
            $.ajax({
                url: '/user/extract-image',
                type: 'post',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function (result) {
                    if (result.status_code == 200) {
                        $("#orginal-image").attr("src", result.result_image);
                        $("#extract-info").html(result.result_date);
                        layer.closeAll();
                    } else {
                        layer.closeAll();
                        layer.msg(result.message);
                    }
                },
                error: function () {
                    layer.msg("数据请求异常");
                    layer.closeAll();
                }
            })
        }
    }
}