layui.use('laydate', function(){
    var laydate = layui.laydate;
    laydate.render({
        elem: '#match_time'
    });
});


var addGroup = function () {
    var addBtn = $(".add_group_btn");
    var forms = $(".forms");
    var txt = $(".forms").html();
    var _txt = null;
    if(!_txt){
        _txt = txt;
    }
    addBtn.on("click",function () {
        $(_txt).appendTo(forms);
        // $(_txt).insertAfter(forms);
        var len = forms.children().length;
        console.log(len);
        form.render('select');

    });

};
function delGroup (obj) {
    $(obj).parent().remove();
}
var form;
layui.use('form', function () {
    form = layui.form;
    form.on('select(province)', function (data) {
        $.ajax({
            url: "/setting/city/" + data.value,
            type: "POST",
            data: {data: name},
            success: function (result) {
                console.log(result);
                var city = $("#city").empty();
                // var html="";
                if (result.status == 200) {
                    for (var i = 0; i < result.data.length; i++) {
                        // html+="<option value='"+ result.data[i].cid+"'>"+ result.data[i].cname+"</option>";
                        city.append("<option value='" + result.data[i].cname + "'>" + result.data[i].cname + "</option>")
                    }
                    // $("#city").html(html);

                }
                form.render('select');

            }
        })
    });
});

var search;
var searchMatch = function () {

    function init() {
        search =
            $('#main-table').DataTable({
                "searching": false,
                "processing": true,
                "serverSide": true,
                "ajax": {
                    "url": "/match/list/condition",
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
                    {"data": "raceDate", "defaultContent": ""},
                    {"data": "cityName", "defaultContent": ""},
                    {"data": "address", "defaultContent": ""},
                    {"data": "status", "defaultContent": ""},
                    {"data": "modifiedBy", "defaultContent": ""},
                    {"data": "modifiedOn", "defaultContent": ""},
                    {"data": "do", "defaultContent": ""},
                ],
                "columnDefs": [
                    {"name": "id", "targets": "0"},
                    {"name": "name", "targets": "1"},
                    {"name": "raceDate", "targets": "2"},
                    {"name": "cityName", "targets": "3"},
                    {"name": "address", "targets": "4"},
                    {"name": "status", "targets": "5"},
                    {"name": "modifiedBy", "targets": "6"},
                    {"name": "modifiedOn", "targets": "7"},
                    {
                        "targets": 8,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                            var html = change(name,id);
                            var Match = match(name,id);
                            var Score = score(id);
                            var DelBtn = delBtn(id);
                            return html + Match + Score + DelBtn;
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
    var btn = $("<a title='修改' href='/match/findRaceDetailById/"+id+"' class='layui-btn margin_0 change_info'>修改</a>");
    return btn.prop("outerHTML");
}

function match(id) {
    var btn = $("<a title='比赛成绩' href='/score/page' class='layui-btn margin_0 change_info'>比赛成绩</a>");
    return btn.prop("outerHTML");
}

function score(id) {
    var btn = $("<button title='成绩发布' onclick=\"publishScore("+id+")\" class='layui-btn margin_0 change_status'>成绩发布</button>");
    return btn.prop("outerHTML");
}

function delBtn(id) {
    var btn = $("<button title='删除' onclick=\"delStatus("+id+")\" class='layui-btn margin_0 memdetail'>删除</button>");
    return btn.prop("outerHTML");
}


function delStatus(id) {
    layer.confirm('请确认是否删除', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/match/deleteRace/"+id,
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('删除成功!', {icon: 1, time: 1000})
                }else {
                    layer.msg('此赛事已录入成绩，不可删除!', {icon: 1, time: 1000})
                }
                search.ajax.reload();

            }
        })
    });
}

function publishScore(id) {
    layer.confirm("请确认是否发布成绩",{btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/match/publishScore/"+id,
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('发布成功!', {icon: 1, time: 1000})
                }else {
                	layer.msg('发布失败!', {icon: 1, time: 1000})
                }
                search.ajax.reload();
            }
        })
    })
}