<%@page language="java"  contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>

<html>
<body>
    <!-- 给服务端发送 testRest请求， 并且提交 username=dahai&age=44 参数-->
    <a href="testRest/dahai/44"> 测试 RESTful URL</a>
    <br/>

    <a href="testRedirect">测试Springmvc 重定向</a>
    <br/>
    <a href="testResponseData">测试Springmvc处理响应数据</a>
    <br/>
    <a href="testRequestParam?name=tom&age=22">测试 Springmvc处理请求参数</a>
    <br/>
    <a href="hello"> Hello Springmvc </a>
</body>
</html>
