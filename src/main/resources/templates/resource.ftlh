<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form">
                <input type="hidden" id="resourceId" name="resourceId" value="${(data.resourceId)!""}">
                <input type="hidden" id="memberId" name="memberId" value="${(data.memberId)!memberId}">
                <div class="layui-form-item">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="resourceName" value="${(data.resourceName)!""}" required  lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">类别</label>
                    <div class="layui-input-block">
                        <select name="resourceType" value="${(data.resourceType)!""}" lay-verify="required">
                            <option value="" selected>——请选择——</option>
                            <#list types as type>
                                <#if !(type.parentType??)>
                                <optgroup label="${type.typeName}">
                                    <#list types as type2>
                                        <#if type2.parentType?? && type2.parentType=type.typeId>
                                            <option value="${type2.typeId}">${type2.typeName}</option>
                                        </#if>
                                    </#list>
                                </optgroup>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-block">
                        <input type="text" name="price" value="${(data.price)!""}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">数量</label>
                    <div class="layui-input-block">
                        <input type="text" name="count" value="${(data.count)!"1"}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">图片</label>
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn" id="picImage">
                            <i class="layui-icon">&#xe67c;</i>选择图片
                        </button>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    let layer,upload;
    var uploadInst;
    $(document).ready(function (){
        layui.use(['layer','form','upload'], function(){
            layer = layui.layer;
            var form = layui.form;
            upload = layui.upload;
            form.on('submit(formDemo)', function(data){
                // layer.msg(JSON.stringify(data.field));
                $.post("/resource/addOrEdit"
                    ,data.field
                    ,function (result){
                        if(result.message == "1"){
                            $("#resourceId").val(result.resourceId);
                            uploadInst.upload();
                            //parent.cancel();
                            //parent.showMsg("成功");
                            layer.msg("成功", {
                                offset: '300px',
                                icon: 1,
                                time: 1000 //2秒关闭（如果不配置，默认是3秒）
                            });
                        }else {
                            layer.alert(result.message);
                        }
                    }
                );
                return false;
            });

            uploadInst = upload.render({
                elem: '#picImage'
                ,url: '/resource/upload'
                ,auto: false
                ,choose: function(obj){
                    obj.preview(function(index, file, result){
                        //对上传失败的单个文件重新上传，一般在某个事件中使用
                        //obj.upload(index, file);
                    });
                }
                ,before: function () {
                    // console.log($("#resourceId").val());
                    this.data = {
                        resourceId:$("#resourceId").val(),
                        memberId:$("#memberId").val()
                    }
                }
            });
        });
    })

    function cancel() {
        layer.closeAll();
    }

    function showMsg(msg) {
        layer.msg(msg, {
            offset: '300px',
            icon: 1,
            time: 1000 //2秒关闭（如果不配置，默认是3秒）
        });
    }
</script>
</body>

</html>