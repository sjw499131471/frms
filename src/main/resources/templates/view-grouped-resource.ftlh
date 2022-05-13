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
<body style="padding: 15px;">
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md3 layui-bg-gray" style="height: 100vh;padding: 15px;">
            <div class="layui-panel">
                <div id="tree" class="demo-tree demo-tree-box"></div>
            </div>
        </div>
        <div class="layui-col-md9" style="height: 100vh;">
            <div class="layui-row" style="height: 10%;">
            </div>
            <div class="layui-row" style="height: 90%;">
                <iframe src="resource?typeId=1&memberId=${memberId}" style="width: 100%;height: 100%;border:1px solid #eee;"></iframe>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/layui/layui.js"></script>
<script type="text/javascript">
    let layer,tree;
    $(document).ready(function (){
        layui.use(['tree', 'layer'], function(){
            layer = layui.layer,tree = layui.tree;

            //模拟数据1
            let data = [{
                title: '全部分类'
                ,id: 1
                ,spread:true
                ,children: [
                <#list types as type>
                    <#if !type.parentType??>
                    {
                        title: '${type.typeName}'
                        ,id: '${type.typeId}'
                        ,spread:true
                        ,children: [
                            <#list types as type2>
                                <#if type2.parentType?? && type2.parentType=type.typeId>
                                {
                                    title: '${type2.typeName}'
                                    ,id: '${type2.typeId}'
                                },
                                </#if>
                            </#list>
                        ]
                    },
                    </#if>
                </#list>
                ]
            }]
            //开启节点操作图标
            tree.render({
                elem: '#tree'
                ,data: data
                ,edit: ['add', 'update', 'del'] //操作节点的图标
                ,click: function(obj){
                    //layer.msg(JSON.stringify(obj.data));
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