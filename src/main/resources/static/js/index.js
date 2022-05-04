let openedMenu = new Set();
var element;
layui.use('element', function(){
    element = layui.element;
    //一些事件触发
    element.on('tab(demo)', function(data){
        console.log(data);
        console.log(this.getAttribute('lay-id'));
    });
    element.on('tabDelete(demo)', function(data){
        console.log(this); //当前Tab标题所在的原始DOM元素
        console.log(data.index); //得到当前Tab的所在下标
        console.log(data.elem); //得到当前的Tab大容器
        openedMenu.remove(this.parentNode.getAttribute('lay-id'));
    });
});

function showContent(id){
    if (openedMenu.has(id)){
        element.tabChange('demo', id);
    }else{
        element.tabAdd('demo', {
            title: '选项卡的标题'
            ,content: '<iframe width="100%" height="100%" src="/jsp/system/404.html" frameborder="0"></iframe>' //支持传入html
            ,id: id
        });
        element.tabChange('demo', id);
        openedMenu.add(id);
    }
}