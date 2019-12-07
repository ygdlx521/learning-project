<%@ page import="java.util.List" %>
<%@ page import="online.daliang.login.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: daliang
  Date: 2019/12/6
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<head>
    <title>Title</title>
    <style>
        h1 {
            color: blue;
            align-items: center;
        }
    </style>
</head>
<body>
    <h1 align="center"> 欢迎 ${sessionScope.loginUser.username} 登录</h1>
    <br>
    <br>
    <h2 align="center">用户密码列表</h2>
    <table border="1px" width="70%" align="center" cellpadding="0px">
        <tr>
            <th>ID</th>
            <th>user_name</th>
            <th>password</th>
        </tr>
        <%
            List<User> allUsers =  (List<User>) request.getAttribute("allUsers");
            if(allUsers != null){
                for (User user: allUsers
                     ) {
                %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getUsername()%></td>
            <td><%=user.getPassword()%></td>

                <%
                }
            } else {
                %>
            <td>暂无数据</td>
            <%
            }
        %>
        </tr>
    </table>
</body>
</html>
