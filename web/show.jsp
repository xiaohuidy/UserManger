<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/26
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情页面</title>
</head>
<script>
    function returnTo() {
        location.href="/UserManage/listUserServlet";
    }
</script>
<body>

<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>age</td>
        <td>birthday</td>
        <td>pwd</td>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.age}</td>
        <td>${user.birthday}</td>
        <td>${user.pwd}</td>
    </tr>
</table>
    <input type="button" value="返回" onclick="returnTo()"/>
</body>
</html>
