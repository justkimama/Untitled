<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%if(session.getAttribute("user") == null) {%>
<script>
    window.location.replace("member_login.do");
</script>
<%}%>
<head>
    <meta charset="UTF-8">
    <title>Untitled</title>
    <link href="/Day_1006/CSS/style.css" rel="stylesheet">
    <script>
        function send(){
            pds.submit();
        }

        //파일을 올리는 순간 용량 체크하기(2M)
        function checkSize(input){
            if(input.files && input.files[0].size > (2*1024*1024)){
                alert("The file is too large.");
                input.value=null;
            }
        }
    </script>
</head>
<body>
<%@ include file="/Day_1006/Include/header.jsp" %>
<div class="section">
    <section>
        <div class="content">
            <h2 class="title"> Post </h2>
            <form name="pds" method="post" enctype="multipart/form-data" action="archive_write.do">
                <input type="hidden" name="userid" value=${user.userid}>
                <table style="width: 100px;">
                    <tr>
                        <th>Name</th>
                        <td style="text-align: left; padding: 0 0 0 60px"><input type="text" name="name" readonly value=${user.name}></td>
                    </tr>
                    <tr>
                        <th>File</th>
                        <td style="text-align: left; padding: 0 0 0 60px"><input type="file" id="image_uploads" name="filename" onchange="checkSize(this)"></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="btn_group">
                            <br>
                            <a href="javascript:send()"><input type="button" value="Submit"></a>&nbsp;
                            <a href="home.do"><input type="button" value="Cancel"></a></td>
                    </tr>
                </table>
            </form>
        </div>
    </section>
</div>
</body>
</html>