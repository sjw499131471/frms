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
<div class="container">
    <div class="row">
        <#list resources as resource>
            <div class="col-xs-6 col-md-3 col-lg-2">
                <a href="#" class="thumbnail" data-toggle="modal" data-target="#myModal" onclick="javascript:$('#image').attr('src','/image/test1.jpg');">
<#--                    <img src="/img/resource/${resource.memberId}/${resource.resourceId}.png" onerror="this.src='/img/profile.png'" title="物资" style="width: 100px;height: 100px;">-->
                    <img src="resource/${resource.memberId}/${resource.resourceId}" onerror="this.src='/img/profile.png'" title="物资" style="width: 100px;height: 100px;">
                </a>
                <div class="caption">
                    <h3 style="text-align: center;">${resource?counter}.${resource.resourceName}</h3>
                    <div style="text-align: center;">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                物资管理 <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">删除物资</a></li>
                                <li><a href="#">编辑物资</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    let layer;
    $(document).ready(function (){
        layui.use('layer', function(){
            layer = layui.layer;
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