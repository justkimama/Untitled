<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int row = (int)request.getAttribute("row");
    if(row==1){
%>
<script>
    location.replace("board_list.do");
</script>
<%
}else{
%>
<script>
    alert("Something Wrong!");
    location.replace("board_list.do");
</script>
<%
    }
%>