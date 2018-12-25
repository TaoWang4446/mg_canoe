var login = function () {

    $("#password").focus(function () {
        $(this).parent().children(".login-empty").show();
    });

    $("#password").blur(function () {
        if($(this).val() ==""){
            $(this).parent().children(".login-empty").hide();
        }
    });
    $(".login-empty").click(function () {
        $(this).parent().find('input').val('');
        $(this).hide();
    });

    var lg = function () {
        var phone = $("#phone-num").val();
        var password = $("#password").val();
        if(!phone || !phone.trim()){
            errorMessage("手机号不能为空！")
        }else if(!password || !password.trim()){
            errorMessage("密码不能为空！")
        }
    },
        errorMessage = function (errorMessage) {
            $("#errorMessage").remove();
            $("#tisp").append('<span id="errorMessage">' + errorMessage + '</span>');
            $("#errorMessage").addClass('fadeInUp');
            setTimeout(function () {
                setTimeout(function () {
                    $("#errorMessage").remove();
                }, 1500);
            }, 1500);
        };
    $("#login-btn").click(function () {
        lg();
    })
}();

