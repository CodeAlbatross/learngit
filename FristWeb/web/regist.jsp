<%--
  Created by IntelliJ IDEA.
  User: Lzyyy
  Date: 2017/8/27
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <title>注册页面</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        form {
            width: 400px;
            height: 400px;
            border: 1px solid #ccc;
            position: absolute;
            top: 35%;
            left: 50%;
            margin-top: -120px;
            margin-left: -200px;
            text-align: center;
        }
        h2 {
            position: absolute;
            width: 120px;
            height: 40px;
            line-height: 40px;
            top: -20px;
            left: 30px;
            background-color: white;
        }
        input, select {
            display: block;
            border-radius: 5px;
            background-color: white;
            border: 1px solid #ccc;
            margin: 30px 25px;
            height: 30px;
            font-size: 18px;
            text-indent: 1em;
            width: 350px;
        }
        button {
            display: block;
            width: 150px;
            text-align: center;
            height: 40px;
            line-height: 40px;
            color: white;
            background-color: rgb(54,133,250);
            font-size: 18px;
            border: 0;
            border-radius: 5px;
            margin: 0 auto;
        }
    </style>
</head>

<body>
<center>
    <form action="RegistServlet" method="post">
        <h2>注册</h2>
        请输入帐号：<input type="text" name="username">
        请输入密码：<input type="password" name="password">
        请确认密码：<input type="password" name="rpsw">
        <button type="submit">注册</button>
        <font color="red" size="2"> ${msg }</font>
    </form>

</center>
</body>
</html>
