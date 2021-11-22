<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="exServlet.model.pds.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<PdsVO> list = (List) request.getAttribute("list");
%>
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
    <script>
        function send() {
            location.href("archive_write.do");
        }
    </script>
</head>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<!-- 본문의 시작 section  -->

<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Archive </h2>
            <table>
                <tr>
                    <th>Num.</th>
                    <th>File</th>
                    <th>Name</th>
                    <th>Date</th>
                </tr>
                <%
                    for (PdsVO pdsVO : list) {
                %>
                <tr class="text_center">
                    <td width="50px"><%=pdsVO.getIdx()%>
                    </td>
                    <td><a style="color: #121212"
                           href="archive_view.do?idx=<%=pdsVO.getIdx()%>"><%=pdsVO.getFilename()%>
                    </a></td>
                    <td width="80px"><%=pdsVO.getName()%>
                    </td>
                    <td width="80px"><%=pdsVO.getRegdate()%>
                    </td>
                </tr>
                <%
                    }
                %>
                <td style="border: hidden;" align="right" colspan="5">
                    <br>
                    <form action="archive_search.do" method="get">
                        <table class="key" style="width: 350px">
                            <tr>
                                <td><select name="search" size="1">
                                    <option value="userid">ID</option>
                                    <option value="filename">File</option>
                                </select></td>
                                <td><input type="text" name="search_text"></td><td><input type="submit" value="Search"></td>
                            </tr>
                        </table>
                    </form>
                    <a href="archive_write.do"><input type="button" value="Write"></a>&nbsp;
                </td>&nbsp;&nbsp;&nbsp;
            </table>
        </div>
    </section>
</div>
</body>
</html>