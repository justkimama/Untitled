<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style>
    table {
        margin-left:auto;
        margin-right:auto;
    }
</style>
<head>
    <meta charset="UTF-8">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
    <script>
        function send() {
            var mem = document.member;
            var regType = /^[A-Za-z0-9+]*$/;
            var regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
            if (mem.name.value == "") {
                alert("Enter your name.");
                mem.name.focus();
                return;
            }
            if (mem.userid.value == "") {
                alert("Enter ID.");
                mem.userid.focus();
                return;
            } else if (mem.userid.value.length < 5) {
                alert("ID must be 5 characters or more.");
                mem.userid.focus();
                return;
            } else if (!regType.test(mem.userid.value)) {
                alert("ID must be Alphabet or numbers.");
                mem.userid.focus();
                return;
            }
            if (mem.email.value == "") {
                alert("Enter E-mail.");
                mem.email.focus();
                return;
            } else if(!regEmail.test(mem.email.value)) {
                alert("Invalid E-Mail address.");
                mem.email.focus();
                return;
            }
            if (mem.passwd.value == "") {
                alert("Enter Password.");
                mem.passwd.focus();
                return;
            } else if (mem.passwd.value.length < 4) {
                alert("Password must be 4 characters or more.");
                mem.passwd.focus();
                return;
            } else if (!regType.test(mem.passwd.value)) {
                alert("Password must be Alphabet or numbers.");
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
            <h2 class="title"> Sign Up </h2>
            <form name="member" method="post" action="member_insert.do">
                <table class="key" style="width: 300px">
                    <tr>
                        <th>Name</th>
                        <td><input style="background-color: white" type="text" name="name"></td>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <td><input style="background-color: white" type="text" name="userid"></td>
                    </tr>
                    <tr>
                        <th>E-Mail</th>
                        <td><input style="background-color: white" type="text" name="email"></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><input style="background-color: white" type="password" name="passwd"></td>
                    </tr>
                    <tr>
                        <td style="border: hidden;" align="right" colspan="2">
                            <br>
                            <a href="javascript:send()"><input type="button" value="Submit"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    </tr>
                </table>
            </form>
        </div>
    </section>
</div>
</body>
</html>