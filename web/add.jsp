<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/20
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增用户</title>
</head>
<script src="js/jquery-3.0.0.min.js"></script>
<script>
    $().ready(
        function () {
            $(":text[name=username]").blur(function () {
                $.ajax({
                    type:"POST",
                    url:"${pageContext.request.contextPath}/isSavedServlet",
                    data:"username="+this.value,
                    success:function (checkSavedMessage) {
                        $("span").prop("value","");
                        document.getElementById("checkedname").innerHTML=checkSavedMessage;
                        //$("span").val("用户名已存在");
                    }
                });
            });
        }
    );
</script>
<body>
    <h2>新增用户</h2>
    <form name="myform" method="post" action="/UserManage/addUserServlet">
        姓名：<input type="text" name="username" />
        <span id="checkedname"></span>
        <br />
        年龄：<input type="text" name="age"/><br/>
        生日：<input type="date" name="birthday"/><br />
        密码：<input type="password" name="pwd"/><br />
        <input type="submit" value="确定"/>
    </form>
</body>
</html>
