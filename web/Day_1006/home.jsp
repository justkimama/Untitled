<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<%if (session.getAttribute("user") == null) {%>
<div class="section">
    <section>
        <div class="message">
            <h2 class="title">Hello?</h2>
        </div>
    </section>
</div>
<%} else {%>
<div class="section">
    <section>
        <div class="message">
            <h2 class="title">Hello, ${user.name}!</h2>
        </div>
    </section>
</div>
<%}%>
</body>
</html>