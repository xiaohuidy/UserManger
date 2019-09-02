<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/21
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <h2>登录</h2>
    <form name="myform" method="post" action="/UserManage/loginServlet">
        姓名：<input type="text" name="username" value="${testuser.name}"/><br />
        密码：<input type="password" name="pwd" value="${testuser.pwd}"/><br />
        验证码：<input type="text" name="vcode"/><img src="/UserManage/codeServlet"><a href="login.jsp">看不清，换一张</a><br/>
        <input type="checkbox" name="autoLogin" value="1"/>一周内自动登录<br/>
        <input type="submit" value="登录"/><br/>
        ${err}
    </form>
</body>
</html>
