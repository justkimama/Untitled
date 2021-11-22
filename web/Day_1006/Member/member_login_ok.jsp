<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int row = (int)request.getAttribute("row");
    if(row==0){
%>
<script>
    alert("Wrong Password!");
    history.back();
</script>
<%
}else if(row==-1){
%>
<script>
    alert("Wrong ID!");
    history.back();
</script>
<%
}else if(session.getAttribute("user") != null){
    //로그인 성공시 리스트 페이지로 이동
%>
<script>
    location.href="home.do";
</script>

<%
    }
%>
