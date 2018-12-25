var search;

var searchEq = function () {

    function init() {
        search =
            $('#main-table').DataTable({
                "searching": false,
                "processing": true,
                "serverSide": true,
                "ajax": {
                    "url": "/setting/equipment",
                    "type": "get",
                    "data": function (d) {
                        d.keyword = $("#keyword").val();
                        return d;
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-12 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-12' p v>>",
                "columns": [
                    {"data": "id", "defaultContent": ""},
                    {"data": "name", "defaultContent": ""},
                    {"data": "status", "defaultContent": ""},
                    {"data": "modifiedBy", "defaultContent": ""},
                    {"data": "modifiedOn", "defaultContent": ""},
                    {"data": "do", "defaultContent": ""},
                ],
                "columnDefs": [
                    {"name": "id", "targets": "0"},
                    {"name": "name", "targets": "1"},
                    {"name": "status", "targets": "2"},
                    {"name": "modifiedBy", "targets": "3"},
                    {"name": "modifiedOn", "targets": "4"},
                    {
                        "targets": 5,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                            var html = change(name,id);
                            var DelBtn = delBtn(id);
                            return html  + DelBtn;
                        }
                    },
                ],
                "drawCallback": function () {
                    var len = $("#sample-table_wrapper tr").length - 1;
                    $(".dataTables_info").css("top", -len * 40 - 74 + 'px');
                }

            });
    }

    init();

};


function change(name,id) {
    var btn = $("<a title='修改' href='/setting/equipment/detail/"+id+"' class='layui-btn margin_0 change_info'>修改</a>");
    return btn.prop("outerHTML");
}

function delBtn(id) {
    var btn = $("<button title='删除' onclick=\"delStatus("+id+")\" class='layui-btn margin_0 memdetail'>删除</button>");
    return btn.prop("outerHTML");
}

function delStatus(id) {
    layer.confirm('请确认是否删除', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/setting/deleteEquipment/"+id,
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('删除成功!', {icon: 1, time: 1000})
                }else {
                    return
                }
                search.ajax.reload();

            }
        })
    });
}