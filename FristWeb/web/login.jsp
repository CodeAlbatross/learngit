<%--
  Created by IntelliJ IDEA.
  User: Lzyyy
  Date: 2017/8/27
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <script src="jquery-3.2.1.min.js"></script>
    <script>
        function refresh() {
            var url = "/CheckCodeServlet";
            url = convertURL(url);

            var imge = "<img class="+"/CheckCodeServlet"+" src="+"\""+"CheckCodeServlet"+"\""+" id="+"img>";
            $.ajax({
                url:url,
                type:"get",
                success:function (data) {
                    $("#img").html(imge+Math.random())
                }
            })
        }

        function verify() {
            var url = "/LoginServlet?uuid="+$("#uuid").val()+"&passwd="+$("#passwd").val()+"&checkcode="+$("#checkcode").val();

            url = convertURL(url);

        $.ajax({
            url:url,
            type:"get",
            success:function (data1) {
                data1 = data1.replace(/^\s*/, "");
                data1 = data1.replace(/\s*$/, "" );
                if(data1 == '1')
                {
                    alert("登陆成功！");
                    window.location.href="function.jsp";
                }
                else if(data1 == "验证码错误") {
                    alert(data1);
                    //refresh()
                    //window.location.href="login.jsp"
                }
                else {
                    alert(data1);
                }
            }
          })
        }
        function convertURL(url){
            //获取时间戳
            var timstamp = (new Date()).valueOf();
            //将时间戳信息拼接到url上
            if(url.indexOf("?") >=0){
                url = url + "&t=" + timstamp;
            }else{
                url = url + "?t=" + timstamp;
            }
            return url;
        }
    </script>
    <meta charset="UTF-8">
    <title>系统登录</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        lalala {
            width: 400px;
            height: 240px;
            border: 1px solid #ccc;
            position: absolute;
            top: 50%;
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
            height: 35px;
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

<lalala>

    <h2>欢迎登录</h2>
    <input type="text" id="uuid" placeholder="用户名" />
    <input type="password" id="passwd" placeholder="用户密码" />
    <input type="text" id="checkcode" placeholder="验证码" /><img class="/CheckCodeServle" src="CheckCodeServlet" onclick='this.src=this.src+"?c="+Math.random()'/>

    <button type="submit" onclick="verify()">确认登录</button> <a href = "regist.jsp"><submit type="submit">点击注册</submit></a>
</lalala>


</body>
</html>