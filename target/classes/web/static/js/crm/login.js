var Login = function () {
    var cs = function () {
            var mobile = $("#username").val();
            var password = $("#password").val();
            var code = $("#yzm_code").val();
            if (!mobile || !mobile.trim()) {
                errorMessage('用户名不能为空！');
            } else if (!password || !password.trim()) {
                errorMessage('密码不能为空！');
            } else if (!code || !code.trim()) {
                errorMessage('请填写验证码！');
            } else{
            	login(mobile.trim(), password.trim(), code.trim());
            }
            /*else {
                $.ajax({
                    url: "",
                    type:"POST",
                    data:{key:mobile},
                    success:function (data) {
                        if(data.code == 200){
                            var res = data.responseDomain;
                            setMaxDigits(130);
                            var key = new RSAKeyPair(res.publicExponent, "", res.modulus);
                            password = encodeURIComponent(password);
                            var encryptedPwd = encryptedString(key, password);
                            //login(mobile.trim(), encryptedPwd.trim(), code.trim());
                            login(mobile.trim(), password.trim());
                        }
                    }
                });
            }*/
        }, errorMessage = function (errorMessage) {
            $("#errorMessage").remove();
            $("#tisp").append('<span id="errorMessage">' + errorMessage + '</span>');
            $("#errorMessage").addClass('fadeInUp');
            setTimeout(function () {
                setTimeout(function () {
                    $("#errorMessage").remove();
                }, 1500);
            }, 1500);
        },
        login = function (userName, password, code) {
            $.ajax({
                type: "POST",
                url: "/login",
                data: {mobile: userName, password: password, code: code},
                success: function (data) {
                    if (data.status == 200) {
                        window.location.href = "/pageIndex";
                    } else {
                        imgcode($('#img_code'));
                        errorMessage(data.message);
                    }
                }
            })
        },
        enter = function (id) {
            id.bind('keypress', function (event) {
                if (event.keyCode === 13) {
                    cs();
                }
            })
        },
        bindEvent = function () {
            imgcode($('#img_code'));
            enter($("#username"));
            enter($("#password"));
            enter($("#img_code"));
            $("#login").click(function () {
                cs();
            })
        },
        loginout = function () {
            $.ajax({
                type: "POST",
                url: "/logout",
                success: function (data) {
                    imgcode($('#img_code'));
                }
            })
        },
        imgcode = function (id) {
            id.attr("src", "/getVerifyCode");
            id.click(function () {
                id.attr("src", "/getVerifyCode?" + Math.random());
            })
        };
    return {
        init: function () {
            loginout();
            bindEvent();
        }
    };
}();
Login.init();


