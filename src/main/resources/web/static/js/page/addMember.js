layui.use('form', function () {
    var form = layui.form;
    form.on('select(province)', function (data) {
        $.ajax({
            url: "/member/city/" + data.value,
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

    form.on('select(type)', function (data, brandName) {
        $.ajax({
            url: "/member/brandinfo/" + data.value,
            type: "POST",
            data: {data: brandName},
            success: function (result) {
                var type = $("#brand").empty();
                if (result.status == 200) {
                    for (var i = 0; i < result.data.length; i++) {
                        type.append("<option value='" + result.data[i].id + "'>" + result.data[i].brandName + "</option>")
                    }
                }
                form.render('select');
            }
        })
    })
});
