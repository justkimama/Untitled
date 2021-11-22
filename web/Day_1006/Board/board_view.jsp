<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
    <%--    <script>--%>
    <%--        function send(){--%>
    <%--            location.href("/board_write.do");--%>
    <%--        }--%>
    <%--    </script>--%>
</head>
<script>
    function board_delete() {
        if (confirm("Are you sure?")) {
            document.board.submit();
        }
    }
</script>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<!-- 본문의 시작 section  -->
<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Free Board </h2>
            <table width="413">
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <tr>
                    <td width="0">&nbsp;</td>
                    <td align="center" width="76">Views</td>
                    <td width="400" style="text-align: left;">${vo.readcnt}</td>
                    <td width="0">&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <tr>
                    <td width="0">&nbsp;</td>
                    <td align="center" width="76">Name</td>
                    <td width="400" style="text-align: left;">${vo.name}</td>
                    <td width="0">&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <tr>
                    <td width="0">&nbsp;</td>
                    <td align="center" width="76">Date</td>
                    <td width="400" style="text-align: left;"><fmt:parseDate var="parsedDate" value="${vo.regdate}"
                                                   pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:formatDate var="newFormattedDateString" value="${parsedDate}" pattern="yyyy-MM-dd"/>
                        ${newFormattedDateString}</td>
                    <td width="0">&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <tr>
                    <td width="0">&nbsp;</td>
                    <td align="center" width="76">Subject</td>
                    <td width="400" style="text-align: left;">${vo.subject}</td>
                    <td width="0">&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <td width="0" style="font-size: 7px">&nbsp;</td>
                <tr>
                    <td width="200">&nbsp;</td>
                    <td width="400" colspan="2" style="text-align: left; vertical-align: top;">
                        <div style="white-space:pre; font-size: 17px"><c:out value="${vo.contents}" /></div>
                    <td width="200">&nbsp;</td>
                </tr>
                <td width="0" style="font-size: 7px">&nbsp;</td>
                <tr height="1" bgcolor="#dddddd">
                    <td colspan="4" width="407"></td>
                </tr>
                <td style="border: hidden;" align="right" colspan="5">
                    <br>
                    <c:if test="${sessionScope.userid == vo.userid}">
                        <a href="board_modify.do?idx=${vo.idx}"><input type="button" value="Modify"></a>&nbsp;
                        <a href="javascript:board_delete()"><input type="button" value="Delete"></a>&nbsp;
                    </c:if>
                    <input type="button" value="Back" onclick="history.back()">
                </td>&nbsp;&nbsp;&nbsp;
            </table>
        </div>
    </section>
</div>
<form name="board" action="board_delete.do?idx=${vo.idx}" method="post">
    <input type="hidden" name="idx" value=${vo.idx}>
</form>
</body>
</html>