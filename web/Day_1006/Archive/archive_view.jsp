<%@ page import="java.io.File" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<%--<%if(session.getAttribute("user") == null) {%>--%>
<%--<script>--%>
<%--    window.location.replace("member_login.do");--%>
<%--</script>--%>
<%--<%}%>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
</head>
<script>
    function archive_delete() {
        if (confirm("Are you sure?")) {
            document.archive.submit();
        }
    }
</script>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<!-- 본문의 시작 section  -->
<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Archive </h2>
            <table>
                <input type="hidden" name="filename" value=${vo.filename}>
                <tr class="text_center">
                    <c:set var="filename" value="${vo.filename}"/>
                    <c:set var="fileNm" value="${fn:toLowerCase(filename)}"/>
                    <c:forTokens var="token" items="${fileNm }" delims="." varStatus="status">
                        <c:if test="${status.last}">
                            <c:choose>
                                <c:when test="${token eq 'jpg' || token eq 'jpeg' || token eq 'gif' ||
                                token eq 'png' || token eq 'bmp' }">
                                    <td><img src="/Day_1006/Archive/Storage/${vo.filename}" border="0" width="440px"></td>
                                </c:when>
                                <c:otherwise>
                                    <td><img src="/Day_1006/Archive/Image/txt.png" border="0"></td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forTokens>
                </tr>
                <td class="text_center">
                    <br>
                    <%if(session.getAttribute("user") != null) {%>
                    <a href="archive_download.do?filename=${vo.filename}"><img
                            src="/Day_1006/Archive/Image/download.png"
                            border="0"></a>
                    <c:if test="${sessionScope.userid == vo.userid}">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="archive_delete.do?idx=${vo.idx}"><img src="/Day_1006/Archive/Image/delete.png"
                        border="0"></a>
                    </c:if>
                    <%}%><br><br><br><br><br><br>
                </td>&nbsp;&nbsp;&nbsp;
            </table>
        </div>
    </section>
</div>
<form name="archive" action="archive_delete.do" jh method="post">
    <input type="hidden" name="idx" value=${vo.idx}>
</form>
</body>
</html>
