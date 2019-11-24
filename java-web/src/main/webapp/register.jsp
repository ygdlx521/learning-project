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
    <script>
        function getXhr() {
            var xhr = new XMLHttpRequest();
            return xhr;
        }
    </script>
</head>
<body>
    <h1>欢迎注册</h1>
    <form action="register" method="post">
        用户名称 :<input type="text" name="username"/><span>${register_msg}</span>
        <br/>
        用户密码 :<input type="password" name="password"/>
        <br/>
        确认密码 :<input type="password" name="repassword">

        <input type="submit" value="注册"/>
    </form>
</body>
</html>
