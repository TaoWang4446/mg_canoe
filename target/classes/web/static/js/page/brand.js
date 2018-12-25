layui.use('upload',function () {
    var upload = layui.upload;
    upload.render({
        elem: '#upload_btn'
        ,url: '/setting/upload'
        ,accept: 'file'
        ,done: function(res){
        	if (res.status == 200) {
                layer.msg('上传成功!', {icon: 1, time: 1000});
                $("#logo").val(res.data);
            } else {
                layer.msg(res.message, {icon: 2, time: 1000});
            }
        }
    });
});

layui.use('form', function () {
    var form = layui.form;
    form.on('select(province)', function (data) {
        $.ajax({
            url: "/setting/city/" + data.value,
            type: "POST",
            data: {data: name},
            success: function (result) {
                console.log(result)
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

var searchBrand = function () {

    function init() {
        search =
            $('#main-table').DataTable({
                "searching": false,
                "processing": true,
                "serverSide": true,
                "ajax": {
                    "url": "/setting/brand/condition",
                    "type": "get",
                    "data": function (d) {
                        d.keyword = $("#keyword").val();
                        return d;
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-12 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-12' p v>>",
                "columns": [
                    {"data": "id", "defaultContent": ""},
                    {"data": "brandName", "defaultContent": ""},
                    {"data": "name", "defaultContent": ""},
                    {"data": "contactName", "defaultContent": ""},
                    {"data": "mobile", "defaultContent": ""},
                    {"data": "mail", "defaultContent": ""},
                    {"data": "status", "defaultContent": ""},
                    {"data": "modifiedBy", "defaultContent": ""},
                    {"data": "modifiedOn", "defaultContent": ""},
                    {"data": "do", "defaultContent": ""},
                ],
                "columnDefs": [
                    {"name": "id", "targets": "0"},
                    {"name": "brandName", "targets": "1"},
                    {"name": "name", "targets": "2"},
                    {"name": "contactName", "targets": "3"},
                    {"name": "mobile", "targets": "4"},
                    {"name": "mail", "targets": "5"},
                    {"name": "status", "targets": "6"},
                    {"name": "modifiedBy", "targets": "7"},
                    {"name": "modifiedOn", "targets": "8"},
                    {
                        "targets": 9,
                        "render": function (data, type, row) {
                            var name = row['name'];
                            var id = row['id'];
                            var html = change(name,id);
                            var s = row['isSponsor'];
                            var Status = status(id,s);
                            var DelBtn = delBtn(id);
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
        search.ajax.reload();
    });

    init();

};

function change(name,id) {
    var btn = $("<a title='修改' href='/setting/brand/detail/"+id+"' class='layui-btn margin_0 change_info'>修改</a>");
    return btn.prop("outerHTML");
}

function status(id,IsSponsor) {
    if(IsSponsor==0){
        var btn = $("<button title='置为赞助商' onclick=\"changeStatus("+id+","+IsSponsor+")\" class='layui-btn margin_0 change_status'>置为赞助商</button>");

    }else {
        var btn = $("<button title='取消赞助商' onclick=\"changeStatus("+id+","+IsSponsor+")\" class='layui-btn margin_0 change_status'>取消赞助商</button>");

    }
    return btn.prop("outerHTML");
}

function delBtn(id) {
    var btn = $("<button title='删除' onclick=\"delStatus("+id+")\" class='layui-btn margin_0 memdetail'>删除</button>");
    return btn.prop("outerHTML");
}
function delStatus(id) {
    layer.confirm('请确认是否删除', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/setting/deleteBrand/"+id,
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



function changeStatus(id,IsSponsor) {
    layer.confirm('请确认修改', {btn: ['确认', '取消'], area: '500px'},function () {
        $.ajax({
            url: "/setting/updateIsSponsor/" + id + "/" + IsSponsor,
            type: "POST",
            success: function (result) {
                if(result.status==200){
                    layer.msg('操作成功!', {icon: 1, time: 1000});

                }else {
                    return
                }
                search.ajax.reload();

            }
        })
    });
}