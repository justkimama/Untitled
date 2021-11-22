<%@ page import="exServlet.model.board.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="exServlet.model.pds.PdsVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BoardVO> list = (List) request.getAttribute("list");
    List<PdsVO> list2 = (List) request.getAttribute("list2");
%>
<html>
<%if (session.getAttribute("user") == null) {%>
<script>
    window.location.replace("/member_login.do");
</script>
<%}%>
<head>
    <meta charset="UTF-8">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
    <script>
        function member_delete() {
            var del = document.member_delete;
            var pwd = sha256(del.password.value)
            if (confirm("Are you sure?")) {
                if (pwd == del.confirm.value) {
                    del.submit();
                } else {
                    alert("Incorrect Password.")
                    del.password.focus();
                }
            }
        }
    </script>
    <script>
        function send() {
            var mem = document.member;
            var regType = /^[A-Za-z0-9+]*$/;
            if (mem.name.value == "") {
                alert("Enter your name.");
                mem.name.focus();
                return;
            }
            if (mem.email.value == "") {
                alert("Enter E-mail.");
                mem.email.focus();
                return;
            }
            if (mem.password.value == "") {
                alert("Enter Password.");
                mem.password.focus();
                return;
            } else if (mem.password.value.length < 4) {
                alert("Password must be 4 characters or more.");
                mem.password.focus();
                return;
            } else if (!regType.test(mem.password.value)) {
                alert("Password must be Alphabet or numbers.");
                mem.password.focus();
                return;
            } else if (mem.password.value !== mem.confirm.value) {
                alert("Password did not match.");
                mem.password.focus();
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
            <h2 class="title"> User Info </h2>
            <form name="member" method="post" action="member_modify.do" onsubmit="return false">
                <table class="key" style="width: 400px">
                    <tr>
                        <th>Name</th>
                        <td><input type="text" name="name" value=${vo.name}></td>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <td><input type="text" name="userid" readonly value=${vo.userid}></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                    <tr>
                        <th>Confirm Password</th>
                        <td><input type="password" name="confirm"></td>
                    </tr>
                    <tr>
                        <th>E-Mail</th>
                        <td><input type="text" name="email" value=${vo.email}></td>
                    </tr>
                    <tr>
                        <td style="border: hidden;" align="right" colspan="2">
                            <br>
                            <a href="javascript:send()"><input type="button" value="Submit"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </tr>
                </table>
            </form>
            <h2 class="title"> History </h2>
            <table>
                <tr>
                    <th>Num.</th>
                    <th>Subject</th>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Views</th>
                </tr>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="5" width="407"></td>
                </tr>
                <td width="0" style="font-size: 7px">&nbsp;</td>
                <%
                    for (int x = 0; x < list.size(); x++) {
                %>
                <tr class="text_center">
                    <td width="50px"><%=list.get(x).getIdx()%>
                    </td>
                    <td><a style="color: #121212"
                           href="board_view.do?idx=<%=list.get(x).getIdx()%>"><%=list.get(x).getSubject()%>
                    </a></td>
                    <td width="80px"><%=list.get(x).getName()%>
                    </td>
                    <td width="80px"><%=list.get(x).getRegdate()%>
                    </td>
                    <td width="50px"><%=list.get(x).getReadcnt()%>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <h2 class="title"> Files </h2>
            <table>
                <tr>
                    <th>Num.</th>
                    <th>File</th>
                    <th>Name</th>
                    <th>Date</th>
                </tr>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <td width="0" style="font-size: 7px">&nbsp;</td>
                <%
                    for (PdsVO pdsVO : list2) {
                %>
                <tr class="text_center">
                    <td width="50px"><%=pdsVO.getIdx()%>
                    </td>
                    <td><a style="color: #121212"
                           href="/archive_view.do?idx=<%=pdsVO.getIdx()%>"><%=pdsVO.getFilename()%>
                    </a></td>
                    <td width="80px"><%=pdsVO.getName()%>
                    </td>
                    <td width="80px"><%=pdsVO.getRegdate()%>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>

            <h2 class="title"> Delete My Account </h2>
            <form name="member_delete" method="post" action="/member_delete.do" onsubmit="return false">
                <table class="key" style="width: 350px">
                    <tr>
                        <th>Password</th>
                        <input type="hidden" name="confirm" value=${vo.passwd}>
                        <input type="hidden" name="userid" value=${vo.userid}>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td style="border: hidden;" align="right" colspan="2">
                            <br>
                            <a href="javascript:member_delete ()"><input type="button" value="Delete"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <br><br><br><br><br><br>
                    </tr>
                </table>
            </form>
        </div>
    </section>
</div>
</body>
</html>