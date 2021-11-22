<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int row = (int)request.getAttribute("row");
    if(row==1){
%>
<script>
    alert("Welcome!");
    location.href="home.do";
</script>
<%
}else{
%>
<script>
    alert("You already have an account.");
    history.back();
</script>
<%
    }
%>