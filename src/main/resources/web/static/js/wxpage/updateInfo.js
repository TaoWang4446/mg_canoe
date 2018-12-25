function addRows() {
    var addBtn = $(".add-btn");
    var rows = $(".rows");
    var txt = rows.html();
    // console.log(txt);
    var _txt = null;
    if (!_txt) {
        _txt = txt;
    }
    ;
    addBtn.on("click", function () {
        $(_txt).appendTo(rows);
        var len = rows.children().length;
        console.log(len);
    });
};

function delBtn(obj) {
    $(obj).parent().remove();
};
