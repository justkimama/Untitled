<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="exServlet.model.board.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BoardVO> list = (List) request.getAttribute("list");
    int pageSize = 10;
    int count = (int) request.getAttribute("count");
    int currentPage = (int) request.getAttribute("currentPage");
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
            location.href("board_write.do");
        }
    </script>
</head>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Free Board </h2>
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
                    int number = count - (currentPage -1) * pageSize;
                        for (int x = 0; x < list.size(); x++) {
                %>
                <tr class="text_center">
                    <td width="50px"><%=(int)(number-x)%>
                    </td>
                    <td><a style="color: #121212"
                           href="/board_view.do?idx=<%=list.get(x).getIdx()%>"><%=list.get(x).getSubject()%>
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
                <td style="border: hidden;" align="right" colspan="5">
                    <br>
                    <form action="board_search.do" method="get">
                        <table class="key" style="width: 320px">
                            <tr>
                                <td><select name="search" size="1">
                                    <option value="subject">Subject</option>
                                    <option value="contents">Contents</option>
                                    <option value="userid">ID</option>
                                </select></td>
                                <td><input type="text" name="search_text"></td>
                                <td><input type="submit" value="Search"></td>
                            </tr>
                        </table>
                    </form>
                    <a href="board_write.do"><input type="button" value="Write"></a>&nbsp;
                    <br>
                </td>
                <table class="key" style="width: 350px">&nbsp;&nbsp;&nbsp;
                    <tr>
                        <td>
                            <%
                                int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
                                int pageBlock = 10;
                                int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
                                int endPage = startPage + pageBlock - 1;

                                if (endPage > pageCount) {
                                    endPage = pageCount;

                                    if (startPage > pageBlock) {
                            %>
                            <a style="color: #121212" href="/board_list.do?pageNum=<%=startPage - 10%>">Prev</a>
                            <%
                                }
                                for (int i = startPage; i <= endPage; i++) {
                                    if (i == currentPage) {
                            %>
                            <%=i%>
                            <%
                            } else {
                            %>
                            <a style="color: #121212" href="/board_list.do?pageNum=<%=i%>"><%=i%>
                            </a>
                            <%
                                    }
                                }
                                if (endPage < pageCount) {
                            %>
                            <a style="color: #121212" href="/board_list.do?pageNum=<%=startPage + 10%>">Next</a>
                            <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                </table>
        </div>
    </section>
</div>
</body>
</html>