var search;

layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#start_time'
    });
    laydate.render({
        elem: '#end_time'
    });
});

var searchMember = function () {

    function init() {
        search =
            $('#main-table').DataTable({
                "searching": false,
                "processing": true,
                "serverSide": true,
                "ajax": {
                    "url": "/member/list/condition",
                    "type": "get",
                    "data": function (d) {
                        d.keyword = $("#keyword").val();
                        d.startDate = $("#start_time").val();
                        d.endDate = $("#end_time").val();
                        d.gender = $("#Sex").val();
                        d.isSeed = $("#yorn").val();
                        d.clubId = $("#club").val();
                        d.status = $("#state").val();
                        return d;
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-12 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-12' p v>>",
                "columns": [
                    {"data": "id", "defaultContent": ""},
                    {"data": "name", "defaultContent": ""},
                    {"data": "mobile", "defaultContent": ""},
                    {"data": "gender", "defaultContent": ""},
                    {"data": "clubName", "defaultContent": ""},
                    {"data": "totalCompetePoint", "defaultContent": ""},
                    {"data": "totalFinishPoint", "defaultContent": ""},
                    {"data": "status", "defaultContent": ""},
                    {"data": "do", "defaultContent": ""},
                ],
                "columnDefs": [
                    {"name": "id", "targets": "0"},
                    {
                        "targets": 1,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                          var html=  detail(name,id);
                            return html;
                        }
                    },
                    {"name": "mobile", "targets": "2"},
                    {"name": "gender", "targets": "3"},
                    {"name": "clubName", "targets": "4"},
                    {"name": "totalCompetePoint", "targets": "5"},
                    {"name": "totalFinishPoint", "targets": "6"},
                    {"name": "status", "targets": "7"},
                    {
                        "targets": 8,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                            var html = change(name,id);
                            var s = row['isSeed'];
                            var Status = status(id,s);
                            var del = row['status'];
                            var DelBtn = delBtn(id,del);
                            return html + Status + DelBtn;
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
    	function compareDate () {
            var a = new Date($("#start_time").val());
            var b = new Date($("#end_time").val());
            if( a > b && a!=null && b!=null ){
                alert("开始时间不能晚于结束时间!")
            }
        }
        search.ajax.reload(compareDate);
    });

    init();

};

function change(name,id) {
    var btn = $("<a title='修改' href='/member/info/"+id+"' class='layui-btn margin_0 change_info'>修改</a>");
    return btn.prop("outerHTML");
}

function status(id,status) {
    if(status==0){
        var btn = $("<button title='置为黑榜' onclick=\"changeStatus("+id+","+status+")\" class='layui-btn margin_0 change_status'>置为黑榜</button>");

    }else {
        var btn = $("<button title='取消黑榜' onclick=\"changeStatus("+id+","+status+")\" class='layui-btn margin_0 change_status'>取消黑榜</button>");

    }
    return btn.prop("outerHTML");
}

function delBtn(id,status) {
    if(status=="活跃"){
        status = 'E'
    }else if (status=="初始") {
        status = 'F'
    }else{
        status = 'X'
    }
    var btn = $("<button title='注销' onclick=\"delStatus("+id+",'"+status+"')\" class='layui-btn margin_0 memdetail'>注销</button>");
    return btn.prop("outerHTML");
}


function detail(name,id) {
    var btn = $("<a title='详情' onclick='memDetail("+id+")'>"+name+"</a>");
    return btn.prop("outerHTML");
}


function memDetail(id){
    console.log(id);
    frame_show('会员详情',"/member/detail/"+id);
}

function changeStatus(id,status) {
    layer.confirm('请确认修改', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/member/status/" + id + "/" + status,
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('置黑成功!', {icon: 1, time: 1000});

                }else {
                    return
                }
                search.ajax.reload();

            }
        })
    });
}

function delStatus(id, status) {
    layer.confirm('请确认是否注销', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/member/logout/" + id + "/" + status,
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('注销成功!', {icon: 1, time: 1000})
                }else {
                    return "已经是注销状态，无需操作！"
                }
                search.ajax.reload();

            }
        })
    });
}