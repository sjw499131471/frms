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
        <div class="layui-col-xs6 layui-col-md3 layui-col-lg1">

        </div>
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