<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
    <script>
        function send() {
            var mem = document.member;
            if (mem.userid.value == "") {
                alert("Enter ID.");
                mem.userid.focus();
                return;
            }
            if (mem.passwd.value == "") {
                alert("Enter Password.");
                mem.passwd.focus();
                return;
            }
            mem.submit();
        }
    </script>
</head>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Login </h2>
            <form name="member" method="post" action="member_login.do">
                <table class="key" style="width: 300px">
                    <tr>
                        <th>ID</th>
                        <td><input style="background-color: white" type="text" name="userid"></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><input style="background-color: white" type="password" name="passwd"></td>
                    </tr>
                    <tr>
                        <td style="border: hidden;" align="right" colspan="2">
                            <br>
                            <a href="javascript:send()"><input type="button" value="Login" ></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </section>
</div>
</body>
</html>