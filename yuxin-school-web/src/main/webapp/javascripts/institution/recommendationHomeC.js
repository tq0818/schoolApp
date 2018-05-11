$(function () {

    // var zTreeObj;
    // // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    // var setting = {};
    // // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    // var zNodes = [
    //     {name:"一级分类", open:true, children:[
    //         {name:"二级分类"}, {name:"二级分类"}]},
    //     {name:"一级分类", open:true, children:[
    //         {name:"二级分类"}, {name:"二级分类"}]}
    // ];
    // $(document).ready(function(){
    //     zTreeObj = $.fn.zTree.init($("#ztree"), setting, zNodes);
    // });
    //z-tree

    function createTree(url, treeId) {
        var zTree; //用于保存创建的树节点
        var setting = { //设置
            check: {
                enable: true
            },
            view: {
                showLine: true, //显示辅助线
                dblClickExpand: true,
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 0
                }
            }
        };

        // $.ajax({ //请求数据,创建树
        //     type: 'GET',
        //     url: url,
        //     dataType: "json", //返回的结果为json
        //     success: function(data) {
        //         zTree = $.fn.zTree.init($(treeId), setting, data); //创建树
                zTree = $.fn.zTree.init($(treeId), setting, url); //创建树
        //     },
        //     error: function(data) {
        //         alert("创建树失败!");
        //     }
        // });
    }

    $(document).ready(function() {
        var jsonData =
            [
                {name:"一级分类", open:true, children:[
                    {name:"二级分类"}, {name:"二级分类"}]},
                {name:"一级分类", open:true, children:[
                    {name:"二级分类"}, {name:"二级分类"}]}
            ];

        createTree(jsonData, "#ztree"); //创建
        $("#myButton").click(function() {
            var treeObj = $.fn.zTree.getZTreeObj("ztree");
            var nodes = treeObj.getCheckedNodes(true);
            if(0 === nodes.length) {
                alert("请选择!");
                return;
            }
            var dataNodes = "";
            for(var i = 0; i < nodes.length; i++) {
                dataNodes += nodes[i].name + ",";
            }
            alert(dataNodes); //获取选中节点的值
        });
    });

});