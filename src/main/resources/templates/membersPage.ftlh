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
<div>
    <button class="layui-btn" onclick="addOrEdit();"> <i class="fa fa-plus-circle"></i> 添加</button>
    <hr>
    <div class="row">
        <#list members as member>
            <div class="col-xs-6 col-md-3 col-lg-1">
                <a href="#" class="thumbnail" data-toggle="modal" data-target="#myModal" onclick="javascript:$('#image').attr('src','/image/test1.jpg');">
                    <img src="/img/member/${member.memberId}.png" onerror="this.src='/img/profile.png'" title="头像" style="width: 100px;height: 100px;">
                </a>
                <div class="caption">
                    <h3 style="text-align: center;">${member.memberName}</h3>
                    <p style="text-align: center;"><a href="#" class="btn btn-primary" role="button">查看物资</a></p>
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
    $("document").ready(function (){
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