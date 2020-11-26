<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>邮箱系统</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link rel="stylesheet" href="css/style.css">
    <style>
        img {
            vertical-align: middle;
        }
    </style>
</head>

<body>
    <div id="mainContent">
        <div id="loginForm">
            <br><hr><br>

            <h1>登录</h1>
            <br>
            <form action="user.do" method="POST">
                <input type="hidden" name="behavior" value="login">
                <div>
                    账号:
                    <input type="text" name="username" id="loginUsername">
                </div>
                <div>
                    密码:
                    <input type="password" name="password" id="loginPassword">
                </div>
                <!-- for alignment -->
                <div style="height: 87px;">&nbsp;</div>
                <div>
                    <input type="submit" value="登录" id="loginButton">
                </div>
            </form>
        </div>

        <div id="registerForm" style="display: none;">
            <br><hr><br>

            <h1>注册</h1>
            <br>
            <form action="user.do" method="POST">
                <input type="hidden" name="behavior" value="register">
                <div>
                    账号:
                    <input type="text" name="username" id="registerUsername">
                </div>
                <div>
                    邮箱:
                    <input type="text" name="email" id="registerEmail">
                </div>
                <div>
                    密码:
                    <input type="password" name="password" id="registerPassword">
                </div>
                <div>
                    确认密码:
                    <input type="password" name="repassword" id="registerRepassword">
                </div>
                <div>
                    验证码:
                    <input type="text" name="verify" id="registerVerify">
                    <img height="20px" src="/MessageSystem/kaptcha.jpg" alt="kaptcha">
                </div>
                <div>
                    <input type="submit" value="注册" id="registerButton">
                </div>
            </form>
        </div>

        <br><hr><br>

        <div><button id="switchButton">切换 登录 / 注册</button></div>

        <br><hr><br>
    </div>
    
    <script>
        $(() => {
            $('#loginButton').on('click', () => {
                if ($('#loginUsername').val() == "") {
                    alert("请输入账号。");
                    return false;
                } else if ($('#loginPassword').val() == "") {
                    alert("请输入密码。");
                    return false;
                }
            });

            $('#registerButton').on('click', () => {
                if ($('#registerUsername').val() == "") {
                    alert("请输入账号。");
                    return false;
                } else if ($('#registerEmail').val() == "") {
                    alert("请输入邮箱。");
                    return false;
                } else if ($('#registerPassword').val() == "") {
                    alert("请输入密码。");
                    return false;
                } else if ($('#registerRepassword').val() == "") {
                    alert("请确认密码。");
                    return false;
                } else if ($('#registerPassword').val() != $('#registerRepassword').val()) {
                    alert("两次密码不一致。");
                    return false;
                } else if ($('#registerVerify').val() == "") {
                    alert("请输入验证码");
                    return false;
                }
            })

            $('#switchButton').on('click', () => {
                $('#registerForm').toggle();
                $('#loginForm').toggle();
            });
        });
    </script>
</body>

</html>