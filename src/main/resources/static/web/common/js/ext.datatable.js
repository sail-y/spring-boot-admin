(function(){
//初始化datatables默认属性
$.extend($.fn.DataTable.defaults, {

    "processing": false,
    "serverSide": true,
    "paging" : true,
    "ajax": {
        url:"/user/tables",
        type:"post",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", token);
        },
        contentType: "application/json; charset=utf-8",
        "data": function ( d ) {
            return JSON.stringify(d);
        }
    },
    "pagingType":   "full_numbers",
    "sZeroRecords": "暂无数据",
    stateSave: true,
    "searching": false,
    "dom": 'rt<"bottom"iflp<"clear">>',
    "language": {
        "processing": "玩命加载中...",
        "lengthMenu": "显示 _MENU_ 项结果",
        "zeroRecords": "没有匹配结果",
        "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "infoFiltered": "(由 _MAX_ 项结果过滤)",
        "infoPostFix": "",
        "url": "",
        "paginate": {
            "first":    "首页",
            "previous": "上一页",
            "next":     "下一页",
            "last":     "末页"
        }
    }
});

})()