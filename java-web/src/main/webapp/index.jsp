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
    <script type="text/javascript">
        function clickButton() {
            // alert("是谁点我！！！！")
            var mySpan = window.document.getElementById("s1");
            mySpan.innerHTML = "呵呵呵呵";
        }
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
</body>
</html>
