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
    <blockquote class="layui-elem-quote">家庭成员</blockquote>
    <div class="row">
        <#list members as member>
            <div class="col-xs-6 col-md-3 col-lg-2">
                <a href="#" class="thumbnail" data-toggle="modal" data-target="#myModal" onclick="javascript:$('#image').attr('src','/image/test1.jpg');">
                    <img src="/img/member/${member.memberId}.png" onerror="this.src='/img/profile.png'" title="头像" style="width: 100px;height: 100px;">
                </a>
                <div class="caption">
                    <h3 style="text-align: center;">${member.memberName}</h3>
                    <div style="text-align: center;">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                物资管理 <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:viewResources('${member.memberId}')">查看物资</a></li>
                                <li><a href="javascript:addResource('${member.memberId}')">添加物资</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-6 col-md-3 col-lg-2" style="padding: 30px;">
                <a class="fa fa-plus" style="font-size:50px; " aria-hidden="true" onclick="javascript:addOrEdit();"></a>
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
    function deleteData(id) {
        $.get("/test/del?id="+id,function (result){
            if (result.message=="1"){
                showMsg("成功");
                reload();
            }else {
                showMsg(result.message);
            }
        });
    }

    function viewResources(memberId){
        layer.open({
            title: '查看物资'
            ,type:2
            ,area:['90vw','90vh']
            ,offset:'15px'
            ,content: '/resourceType?memberId='+memberId
        });
    }

    function addResource(memberId){
        layer.open({
            title: '添加物资'
            ,type:2
            ,area:['500px','80vh']
            ,offset:'15px'
            ,content: '/resource/addOrEditPage?memberId='+memberId
        });
    }

    function addOrEdit() {
        layer.open({
            type:1
            ,title:"添加成员"
            ,content: '<div id="memberNameDiv" style="padding: 20px;"><input type="text" id="memberName" placeholder="请输入姓名" autocomplete="off" class="layui-input"> </div>'
            ,btn:'确定'
            ,yes: function(index, layero){
                //console.log($("#memberName").val());
                $.get('/member/add?memberName='+$("#memberName").val(),function (data){
                    if (data.message='1'){
                        showMsg('添加成功');
                    }else {
                        showMsg(data.message);
                    }
                });
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        });
    }

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