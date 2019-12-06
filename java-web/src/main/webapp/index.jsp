<%--
  Created by IntelliJ IDEA.
  User: daliang
  Date: 2019/11/21
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        function clickButton() {
            // alert("是谁点我！！！！")
            var mySpan = window.document.getElementById("s1");
            mySpan.innerHTML = "呵呵呵呵";
        }
        $(function () { //文档加载完毕就执行

        })
        $(function () {
            $("#d2").click(function () {
                var msg = $("#s1").html();
                $("#i1").val(msg)
            })
        })
    </script>
</head>
<body>
    <%
        String str = "ABC";
        System.out.println("ABC");
    %>
    <%=5>3?"大于1":"小于"%>
    <input type="button" value="点我" onclick="clickButton();">
    <span id="s1">哈哈哈哈</span>
    <br>
    <input id="i1" type="text" name="msg" />
    <input type="button" id="d2" value="测试jQuery">
    <a href="CookieServlet">Test Cookie</a>
</body>
</html>
