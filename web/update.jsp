<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    function returnTo() {
        location.href="/UserManage/listUserServlet";
    }
</script>
<body>
<h2>修改用户</h2>
<form name="myform" method="post" action="/UserManage/updateServlet">
    姓名：<input type="text" name="username" value="${user.name}"/><br />
    年龄：<input type="text" name="age" value="${user.age}"/><br/>
    生日：<input type="date" name="birthday" value="${user.birthday}"/><br />
    密码：<input type="password" name="pwd" value="${user.pwd}"/><br />
    <input type="hidden" name="id" value="${user.id}"/><br />
    <input type="submit" value="修改"/>
    <input type="button" value="返回" name="return" onclick="returnTo()"/>
</form>
</body>
</html>
