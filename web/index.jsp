<%@ page import="com.user.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/20
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>跳转页面</title>
    <script src="js/jquery-3.0.0.min.js"></script>
    <script>
            /*function checkAll(check){
                var checkbox=document.getElementsByName("checked");
                var isChecked = check.checked;
                for(var i=0;i<checkbox.length;i++){
                        checkbox[i].checked = isChecked;
                }
            }*/

           function add(){
                location.href = "/UserManage/add.jsp";
            }
            $().ready(function () {
                $(":checkbox[name=checkAll]").click(function () {
                    $(":checkbox[name=check]").prop("check",this.checked);
                });

                $("#showed").change(function () {
                    var pageSize = $("#showed").val();
                    location.href = "/UserManage/listUserServlet?page=1"+"&pageSize="+pageSize;
                });
                $("#go").click(function () {
                    var page = $("#p").val();
                    var pageSize = $("#showed").val();
                    location.href = "/UserManage/listUserServlet?page="+page+"&pageSize="+pageSize;
                });

            });
            
            function deleteChecked(){
                var checkbox=document.getElementsByName("check");
                var checkedId = [];
                for(var i in checkbox){
                    if(checkbox[i].checked){
                        checkedId.push(checkbox[i].id);
                        //alert(checkedId);
                    }
                }
                location.href = "/UserManage/deleteCheckedServlet?idList=" + checkedId;
            }
    </script>
</head>
<body>
<c:if test="${User==null}"><a href="/UserManage/toLoginServlet">登录页面</a></c:if>
<c:if test="${User!=null}">欢迎你，${User.name}<a href="/UserManage/login.jsp">重新登录</a></c:if>
<br/>
    <input type="button" value="增加" name="add" onclick="add()"/>
    <input type="button" value="批量删除" name="deleteChecked" onclick="deleteChecked()"/>
    <%
        //String uname = request.getAttribute("uname")==null?null:request.getAttribute("uname").toString();
        //request.setAttribute("uname",uname);
    %>

    <table border="1">
        <tr>
            <td><input type="checkbox" name="checkAll" />全选</td>
            <td>id</td>
            <td>name</td>
            <td>age</td>
            <td>birthday</td>
            <td>pwd</td>
            <td colspan="3" align="center" >操作</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><input type="checkbox" name="check" id="${user.id}"/></td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birthday}</td>
                <td>${user.pwd}</td>
                <td><a href="${pageContext.request.contextPath}/toUpdateServlet?id=${user.id}" >修改</a></td>
                <td><a href="${pageContext.request.contextPath}/deleteServlet?id=${user.id}" >删除</a></td>
                <td><a href="${pageContext.request.contextPath}/showServlet?id=${user.id}" >查询</a></td>
            </tr>
        </c:forEach>
    </table>


        <a href="/UserManage/listUserServlet?page=1&pageSize=${pageSize}">首页</a> &nbsp;
        <c:if test="${curPage == 1}">上一页&nbsp;&nbsp;</c:if>
        <c:if test="${curPage != 1}">
            <a href="/UserManage/listUserServlet?page=${curPage-1}&pageSize=${pageSize}">上一页</a> &nbsp;
        </c:if>
        <c:if test="${curPage == lastPage}">下一页&nbsp;&nbsp;</c:if>
        <c:if test="${curPage != lastPage}">
        <a href="/UserManage/listUserServlet?page=${curPage+1}&pageSize=${pageSize}">下一页</a> &nbsp;
        </c:if>
        <a href="/UserManage/listUserServlet?page=${lastPage}&pageSize=${pageSize}">尾页</a><br/>
    每页显示<select id="showed" name="showed" >
                <option name="show" value="2" ${pageSize eq '2'?'selected':''} >2</option>
                <option name="show" value="4" ${pageSize eq '4'?'selected':''} >4</option>
                <option name="show" value="6" ${pageSize eq '6'?'selected':''} >6</option>
                <option name="show" value="8" ${pageSize eq '8'?'selected':''} >8</option>
            </select><br/>
    第<input type="text" size="6" id="p"/>页
    <input type="button" value="go" id="go"/>
</body>
</html>
