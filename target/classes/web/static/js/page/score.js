var search;

var searchScore = function () {

    function init() {
        search =
            $('#main-table').DataTable({
                "searching": false,
                "processing": true,
                "serverSide": true,
                "ajax": {
                    "url": "/score/list/condition",
                    "type": "get",
                    "data": function (d) {
                        d.keyword = $("#keyword").val();
                        d.clubId = $("#club_name").val();
                        d.raceGroupId = $("#group_name").val();
                        return d;
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-12 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-12' p v>>",
                "columns": [
                    {"data": "playerName", "defaultContent": ""},
                    {"data": "clubName", "defaultContent": ""},
                    {"data": "groupName", "defaultContent": ""},
                    {"data": "finishTime", "defaultContent": ""},
                    {"data": "rank", "defaultContent": ""},
                    {"data": "totalCompetePoint", "defaultContent": ""},
                    {"data": "totalFinishPoint", "defaultContent": ""},
                    {"data": "typeName", "defaultContent": ""},
                    {"data": "brandName", "defaultContent": ""},
                    {"data": "equipmentName", "defaultContent": ""},
                    {"data": "do", "defaultContent": ""},
                ],
                "columnDefs": [
                    {"name": "playerName", "targets": "0"},
                    {"name": "clubName", "targets": "1"},
                    {"name": "groupName", "targets": "2"},
                    {"name": "finishTime", "targets": "3"},
                    {"name": "rank", "targets": "4"},
                    {"name": "totalCompetePoint", "targets": "5"},
                    {"name": "totalFinishPoint", "targets": "6"},
                    {"name": "typeName", "targets": "7"},
                    {"name": "brandName", "targets": "8"},
                    {"name": "equipmentName", "targets": "9"},
                    {
                        "targets": 10,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                            var html = change(name,id);
                            var DelBtn = delBtn(id);
                            return html + DelBtn;
                        }
                    },
                ],
                "drawCallback": function () {
                    var len = $("#sample-table_wrapper tr").length - 1;
                    $(".dataTables_info").css("top", -len * 40 - 74 + 'px');
                }

            });
    }
    $("#search_btn").click(function () {
        search.ajax.reload();
    });

    init();

};

function change(name,id) {
    var btn = $("<a title='修改' href='/score/info/"+id+"' class='layui-btn margin_0 change_info'>修改</a>");
    return btn.prop("outerHTML");
}

function delBtn(id) {
    var btn = $("<button title='删除' onclick=\"delStatus("+id+")\" class='layui-btn margin_0 memdetail'>删除</button>");
    return btn.prop("outerHTML");
}

function delStatus(id) {
    layer.confirm('请确认是否删除', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/score/deleteScore/"+id,
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

layui.use('upload',function () {
    var upload = layui.upload;
    upload.render({
        elem: '#upload_btn'
        ,url: '/score/scoreImport/'
        ,accept: 'file'
    	,done: function(res){
        	if (res.status == 200) {
                layer.msg('导入成功!', {icon: 1, time: 1000});
            } else {
                layer.msg(res.message, {icon: 2, time: 1000});
            }
        }
    });
})

var scoreList = function () {
    function init() {
        search =
            $('#main-table').DataTable({
                "searching": false,
                "processing": true,
                "serverSide": true,
                "ajax": {
                    "url": "/score/count",
                    "type": "get",
                    "data": function (d) {
                        d.keyword = $("#keyword").val();
                        d.clubId = $("#club_name").val();
                        d.raceGroupId = $("#group_name").val();
                        return d;
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-12 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-12' p v>>",
                "columns": [
                    {"data": "playerId", "defaultContent": ""},
                    {"data": "playerName", "defaultContent": ""},
                    {"data": "wechatOpenId", "defaultContent": ""},
                    {"data": "clubName", "defaultContent": ""},
                    {"data": "groupName", "defaultContent": ""},
                    {"data": "finishTime", "defaultContent": ""},
                    {"data": "rank", "defaultContent": ""},
                    {"data": "group", "defaultContent": ""},
                    {"data": "totalGroup", "defaultContent": ""},
                    {"data": "totalRank", "defaultContent": ""},
                    {"data": "competitionPoint", "defaultContent": ""},
                    {"data": "finishPoint", "defaultContent": ""},
                    {"data": "typeName", "defaultContent": ""},
                    {"data": "brandName", "defaultContent": ""},
                    {"data": "model", "defaultContent": ""},
                    {"data": "do", "defaultContent": ""},
                ],
                "columnDefs": [
                    {"name": "playerId", "targets": "0"},
                    {"name": "playerName", "targets": "1"},
                    {"name": "wechatOpenId", "targets": "2"},
                    {"name": "clubName", "targets": "3"},
                    {"name": "groupName", "targets": "4"},
                    {"name": "finishTime", "targets": "5"},
                    {"name": "rank", "targets": "6"},
                    {"name": "group", "targets": "7"},
                    {"name": "totalGroup", "targets": "8"},
                    {"name": "totalRank", "targets": "9"},
                    {"name": "competitionPoint", "targets": "10"},
                    {"name": "finishPoint", "targets": "11"},
                    {"name": "typeName", "targets": "12"}, 
                    {"name": "brandName", "targets": "13"},
                    {"name": "model", "targets": "14"},
                    {
                        "targets": 15,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                            var pointId = row['pointId'];
                            var html = _change(name,id);
                            var DelBtn = _delBtn(pointId);
                            return html + DelBtn;
                        }
                    },
                ],
                "drawCallback": function () {
                    var len = $("#sample-table_wrapper tr").length - 1;
                    $(".dataTables_info").css("top", -len * 40 - 74 + 'px');
                }

            });
    }
    $("#search_btn").click(function () {
        search.ajax.reload();
    });

    init();
}

function _change() {
    var btn = $("<a title='修改' href='javascript:;' class='layui-btn margin_0 change_info'>修改</a>");
    return btn.prop("outerHTML");
}

function _delBtn(id) {
    var btn = $("<button title='删除' onclick=\"_delStatus("+id+")\" class='layui-btn margin_0 memdetail'>删除</button>");
    return btn.prop("outerHTML");
}

function _delStatus(pointId) {
    layer.confirm('请确认是否删除', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/score/deletePoint/"+pointId,
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


$("#empty-btn").on("click",function () {
    layer.confirm('请确认是否清空所有成绩', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/score/clearAllScore",
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('清空成功!', {icon: 1, time: 1000})
                }else {
                    return
                }
                search.ajax.reload();

            }
        })
    });
});