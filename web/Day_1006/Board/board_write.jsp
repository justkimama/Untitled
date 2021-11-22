<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%if (session.getAttribute("user") == null) {%>
<script>
    window.location.replace("member_login.do");
</script>
<%}%>
<head>
    <meta charset="UTF-8">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
    <script>
        function send() {
            var bo = document.board;
            if (bo.subject.value == "") {
                alert("Please enter the subject.");
                bo.subject.focus();
                return;
            }
            if (bo.contents.value == "") {
                alert("Please enter the contents.");
                bo.contents.focus();
                return;
            }
            bo.submit();
        }
    </script>
</head>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Post </h2>
            <form name="board" method="post" action="board_write.do">
                <input type="hidden" name="userid" value=${user.userid}>
                <table>
                    <tr>
                        <th>Name</th>
                        <td style="padding: 0 0 0 10px"><input type="text" name="name" readonly value=${user.name}></td>
                    </tr>
                    <tr>
                        <th>Subject</th>
                        <td style="padding: 0 0 0 10px"><input type="text" name="subject"></td>
                    </tr>
                    <tr>
                        <th>Contents</th>
                        <td style="padding: 0 0 0 10px"><textarea style="resize: none; width:650px; height:350px;" name="contents" wrap="hard"
                                      rows="10" cols="60"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="btn_group">
                            <br>
                            <a href="javascript:send()"><input type="button" value="Submit"></a>&nbsp;
                            <a href="/home.do"><input type="button" value="Cancel"></a></td>
                    </tr>
                </table>
            </form>
        </div>
    </section>
</div>
</body>
</html>