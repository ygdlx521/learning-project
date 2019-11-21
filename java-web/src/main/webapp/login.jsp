<%--
  Created by IntelliJ IDEA.
  User: daliang
  Date: 2019/11/21
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body{
            background-color:pink;
        }
        span{
            color: red;
        }
    </style>

</head>
<!--<body bgcolor="pink"> -->
<body bgcolor="pink">
<h1>欢迎登录</h1>
<form action="/login" method="post">
    用户名称 :<input type="text" name="username"/>
<%--    <%--%>
<%--        //获取req对象（九大对象）--%>
<%--        String loginMsg = (String) request.getAttribute("login_msg");--%>
<%--    %>--%>
<%--    <span><%=loginMsg == null?"":loginMsg%></span>--%>
    <span>${login_msg}</span>
    <br/>
    用户密码 :<input type="password" name="password"/>
    <br/>
    <input type="submit" value="Login"/>

</form>
</body>

</html>
