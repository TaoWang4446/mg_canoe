$(function () {
    //加载弹出层
    layui.use(['form', 'element'],
        function () {
            layer = layui.layer;
            element = layui.element;
        });

    //触发事件
    var tab = {
        tabAdd: function (title, url, id) {
            //新增一个Tab项
            element.tabAdd('xbs_tab', {
                title: title
                ,
                content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="x-iframe"></iframe>'
                ,
                id: id
            })
        }
        , tabDelete: function (othis) {
            //删除指定Tab项
            element.tabDelete('xbs_tab', '44'); //删除：“商品管理”


            othis.addClass('layui-btn-disabled');
        }
        , tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('xbs_tab', id); //切换到：用户管理
        }
    };


    tableCheck = {
        init: function () {
            $(".layui-form-checkbox").click(function (event) {
                if ($(this).hasClass('layui-form-checked')) {
                    $(this).removeClass('layui-form-checked');
                    if ($(this).hasClass('header')) {
                        $(".layui-form-checkbox").removeClass('layui-form-checked');
                    }
                } else {
                    $(this).addClass('layui-form-checked');
                    if ($(this).hasClass('header')) {
                        $(".layui-form-checkbox").addClass('layui-form-checked');
                    }
                }

            });
        },
        getData: function () {
            var obj = $(".layui-form-checked").not('.header');
            var arr = [];
            obj.each(function (index, el) {
                arr.push(obj.eq(index).attr('data-id'));
            });
            return arr;
        }
    }

    //开启表格多选
    tableCheck.init();


    $('.container .left_open i').click(function (event) {
        if ($('.left-nav').css('left') == '0px') {
            $('.left-nav').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
            $('.page-content-bg').hide();
        } else {
            $('.left-nav').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            if ($(window).width() < 768) {
                $('.page-content-bg').show();
            }
        }

    });

    $('.page-content-bg').click(function (event) {
        $('.left-nav').animate({left: '-221px'}, 100);
        $('.page-content').animate({left: '0px'}, 100);
        $(this).hide();
    });

    $('.layui-tab-close').click(function (event) {
        $('.layui-tab-title li').eq(0).find('i').remove();
    });

    //左侧菜单效果
    // $('#content').bind("click",function(event){
    $('.left-nav #nav li').click(function (event) {

        if ($(this).children('.sub-menu').length) {
            if ($(this).hasClass('open')) {
                $(this).removeClass('open');
                $(this).find('.nav_right').html('&#xe697;');
                $(this).children('.sub-menu').stop().slideUp();
                $(this).siblings().children('.sub-menu').slideUp();
            } else {
                $(this).addClass('open');
                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                $(this).children('.sub-menu').stop().slideDown();
                $(this).siblings().children('.sub-menu').stop().slideUp();
                $(this).siblings().find('.nav_right').html('&#xe697;');
                $(this).siblings().removeClass('open');
            }
        } else {

            var url = $(this).children('a').attr('_href');
            var title = $(this).find('cite').html();
            var index = $('.left-nav #nav li').index($(this));

            for (var i = 0; i < $('.x-iframe').length; i++) {
                if ($('.x-iframe').eq(i).attr('tab-id') == index + 1) {
                    tab.tabChange(index + 1);
                    event.stopPropagation();
                    return;
                }
            }
            ;

            tab.tabAdd(title, url, index + 1);
            tab.tabChange(index + 1);
        }

        event.stopPropagation();

    })

})

/*弹出层*/

/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function frame_show(title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    }
    ;
    if (url == null || url == '') {
        url = "404.html";
    }
    ;
    if (w == null || w == '') {
        w = ($(window).width() * 0.9);
    }
    ;
    if (h == null || h == '') {
        h = ($(window).height() - 50);
    }
    ;
    layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade: 0.4,
        title: title,
        content: url
    });
}

function frame_error(msg) {
    layer.alert(msg, {icon: 2});
}

function frame_ok(msg) {
    layer.alert(msg, {icon: 1});
}

function frame_back(url) {

    window.location.href = url
    // history.go(-1);location.reload()
}

/*关闭弹出框口*/
function x_admin_close() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}


function buttonWindow(href, title, pers) {
    var btn = $("<a title='详情' onclick=frame_show('" + title + "','" + href + "') href='javascript:;'><i class='layui-icon'>详情</i></a>");
    return btn.prop("outerHTML");
}


function buttonEdit(href, permission, pers) {
    var btn = $("<a title='编辑' href='" + href + "' href='javascript:;'><i class='layui-icon'></i>编辑</a>");
    // var btn = $("<a title='禁用' onclick='" + href + "'  class='btn btn-xs btn-info'>禁用</a>");
    return btn.prop("outerHTML");
}

function buttonDel(id, permission, pers) {
    var btn = $("<a title='删除' onclick='del(this," + id + ")' href='javascript:void(0);'><i class='layui-icon'></i>删除</a>");
    return btn.prop("outerHTML");
}

function buttonDel1(id, permission, pers) {
    var btn = $("<a title='删除' onclick='del1(this," + id + ")' href='javascript:;'><i class='layui-icon'></i>删除</a>");
    return btn.prop("outerHTML");
}

function buttonOperation(id, type) {
    var btn = $("<a title='操作' onclick='Operation(this," + id + ")' href='javascript:;' data-membertype=" + type + "><i class='layui-icon'></i>操作</a>");
    return btn.prop("outerHTML");
}

$(function () {
    $(document).ajaxError(function (event, xhr, options, exc) {
        switch (xhr.status) {
            case(40301):
                layer.alert(xhr.responseJSON.message, {
                    skin: 'layui-layer-lan' //样式类名
                    , closeBtn: 0
                });
                break;
            case(401):

                break;
            case(403):

                break;
            case(408):

                break;
            default:
                alert("未知错误");
        }
    })
});


$(document).bind("ajaxSend", function () {

}).bind("ajaxComplete", function () {

})

function FormatDate(date, strFormat) {
    try {
        if (date === undefined) {
            this.curDate = new Date();
        } else if (!(date instanceof Date)) {
            return date;
        } else {
            this.curDate = date;
        }
        return this.formatCurrentDate(strFormat);
    } catch (e) {
    }
}

function DateAdd(interval, number, date) {
    switch (interval) {
        case "y": {
            date.setFullYear(date.getFullYear() + number);
            return date;
            break;
        }
        case "q": {
            date.setMonth(date.getMonth() + number * 3);
            return date;
            break;
        }
        case "w": {
            date.setDate(date.getDate() + number * 7);
            return date;
            break;
        }
        case "d": {
            date.setDate(date.getDate() + number);
            return date;
            break;
        }
        case "h": {
            date.setHours(date.getHours() + number);
            return date;
            break;
        }
        case "m": {
            date.setMinutes(date.getMinutes() + number);
            return date;
            break;
        }
        case "M": {
            date.setMonth(date.getMonth() + number);
            return date;
            break;
        }
        case "s": {
            date.setSeconds(date.getSeconds() + number);
            return date;
            break;
        }
        default: {
            date.setDate(date.getDate() + number);
            return date;
            break;
        }
    }
}

var DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd";

function formatCurrentDate(strFormat) {
    try {
        var tempFormat = strFormat === undefined ? this.DEFAULT_DATETIME_FORMAT : strFormat;
        var dates = this.getDateObject(this.curDate);
        if (/(y+)/.test(tempFormat)) {
            var fullYear = this.curDate.getFullYear() + "";
            var year = RegExp.$1.length == 4 ? fullYear : fullYear.substr(4 - RegExp.$1.length);
            tempFormat = tempFormat.replace(RegExp.$1, year);
        }
        for (var i in dates) {
            if (new RegExp("(" + i + ")").test(tempFormat)) {
                var target = RegExp.$1.length == 1 ? dates[i] : ("0" + dates[i]).substr(("" + dates[i]).length - 1);
                tempFormat = tempFormat.replace(RegExp.$1, target);
            }
        }
        return tempFormat;
    } catch (e) {
        console.log('格式化日期出现异常：' + e.message);
    }
}

function getFirstAndLastMonthDay(year, month) {
    // console.log(year + "_____" + month)
    var day = new Date(year, month, 0);
    var lastdate = year + '-' + month + '-' + day.getDate();//获取当月最后一天日期
    //给文本控件赋值。同下
    return lastdate;
}


function getDateObject(date) {
    if (!(date instanceof Date)) {
        date = new Date();
    }
    return {
        "M+": date.getMonth() + 1,
        "d+": date.getDate(),
        "H+": date.getHours(),
        "m+": date.getMinutes(),
        "s+": date.getSeconds()
    };
}

function outputdollars(number) {
    if (typeof  number !== 'string') {
        number = number + "";
    }
    if (number.length <= 3)
        return (number === '' ? '0' : number);
    else {
        var mod = number.length % 3;
        var output = (mod === 0 ? '' : (number.substring(0, mod)));
        for (var i = 0; i < Math.floor(number.length / 3); i++) {
            if ((mod === 0) && (i === 0))
                output += number.substring(mod + 3 * i, mod + 3 * i + 3);
            else
                output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (output);
    }
}

function subStringObj(obj) {
    return obj;
    if (obj) {
        if (obj.indexOf('.') >= 0) {
            return obj.substr(0, obj.indexOf('.'));
        }
        return obj;
    }

}

function getLastMonthFirstOrLastDay(date, type) {

    var nowdays = new Date(date);
    var year = nowdays.getFullYear();
    var month = nowdays.getMonth();
    if (month == 0) {
        month = 12;
        year = year - 1;
    }
    if (month < 10) {
        month = "0" + month;
    }

    if (type == '1') {
        return year + "-" + month + "-" + "01";//上个月的第一天
    } else {
        var myDate = new Date(year, month, 0);
        return year + "-" + month + "-" + myDate.getDate();//上个月的最后一天
    }

}






