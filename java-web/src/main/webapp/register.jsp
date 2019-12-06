<%--
  Created by IntelliJ IDEA.
  User: daliang
  Date: 2019/11/23
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>注册</title>
    <style>
        span{
            color: red;
        }
    </style>
    <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
    <script>
        function getXhr() {
            var xhr = new XMLHttpRequest();
            return xhr;
        }
        function checkUsername() {
            var username = document.getElementById("username").value;
            var xhr = getXhr();
            xhr.open("get","checkusername?username="+username,true);
            xhr.send();
            xhr.onreadystatechange=function () {
                if(xhr.readyState === 4){
                    if(xhr.status=== 200){
                        var msg = xhr.responseText;
                        document.getElementById("register_span").innerHTML=msg;
                    }
                }
            }

        }
        $(function () {
            var usernameFlag = false;
            var passwordFlag = false;
            $("#username").blur(function () {
                var username = $("#username").val();
                $.ajax({
                    url:"checkusername",
                    type:"post",
                    data:"username=" + username,
                    success:function (data) {
                        if(data == 0){
                            $("#register_span").html("用户名可以使用")
                            usernameFlag = true;
                        } else {
                            $("#register_span").html("用户名不可以使用")
                        }
                    }
                })
            })
            $("#repassword").blur(function () {
                var password = $("#password").val();
                var repassword = $("#repassword").val();
                if(password !== null && password !== "" && password === repassword){
                    $("#pass_span").html("密码一致")
                    passwordFlag = true
                } else {
                    $("#pass_span").html("密码不一致")
                }
            })

            $("form").submit(function () {
                return usernameFlag && passwordFlag;
            })

        })


    </script>
</head>
<body>
    <h1>欢迎注册</h1>
    <form action="register" method="post">
<%--        用户名称 :<input type="text" name="username" id="username" onblur="checkUsername()"/><span id="register_span">${register_msg}</span>--%>
        用户名称 :<input type="text" name="username" id="username" /><span id="register_span">${register_msg}</span>
        <br/>
        用户密码 :<input type="password" name="password" id="password"/>
        <br/>
        确认密码 :<input type="password" name="repassword" id="repassword"><span id="pass_span"></span>

        <input type="submit" value="注册"/>
    </form>
</body>
</html>
