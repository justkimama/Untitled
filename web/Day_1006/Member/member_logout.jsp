<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
<%
    session.invalidate();//세션의 모든 속성 제거
    response.sendRedirect("member_login.do");
%>
</body>
</html>