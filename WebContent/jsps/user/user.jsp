<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>个人信息</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="/bookStore/css/css.css">
<link rel="stylesheet" type="text/css" href="/bookStore/jsps/css/user/pwd.css">

</head>

<body>
    <header>
     	<img src="<c:url value='/images/ysmall_logo.png'/>">
     	<h1>个人信息</h1>		
    </header>

    <section>
        <div class="div">
            <form action="/bookStore/userServlet?method=updateUser&id=${user.uid }" method="post"  target="_top">
                <!-- <input type="hidden" name="method" value="updateUser" /> -->
                <table>                   
                    <tr>
                        <td align="right">登录名：</td>
                        <td><input class="input"  name="loginname" id="loginname" value="${user.loginname}" /></td>
                        
                    </tr>
                    <tr>
                        <td align="right">邮箱：</td>
                        <td><input class="input"  name="email" id="email" value="${user.email}" /></td>
                        
                    </tr>
                        <tr>
                        <td align="right">性别：</td>
                        <td><input class="input"  name="gender" id="gender" value="${user.gender}" /></td>
                        
                    </tr>
                    <tr>
                        <td align="right">昵称：</td>
                        <td><input class="input" name="nickname" id="nickname" value="${user.nickname}" /></td>                       
                    </tr>

                    <tr>
                        <td align="right"></td>
                        <td><input id="submit" type="submit" value="确认修改" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </section>
</body>
</html>
