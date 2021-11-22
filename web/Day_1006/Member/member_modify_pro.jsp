<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int row = (int)request.getAttribute("row");
    if(row==1){
%>
<script>
    location.href="home.do";
</script>
<%
}else{
%>
<script>
    alert("Something Wrong!");
    history.back();
</script>
<%
    }
%>