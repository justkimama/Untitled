<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style type="text/css">
    a:link {text-decoration: none; color: #ffffff;}
    a:visited {text-decoration: none; color: #ffffff;}
    a:hover {text-decoration: none; color: #ffe000;}
</style>
<div class="header">
    <header>
        <h1 class="logo"><a style="color: white; font-size: 50px; font-family: 'Old English Text MT'" class="john" href="home.do">Untitled</a></h1>
        <%if (session.getAttribute("user") == null) {%>
        <div class="navi">
            <navi>
                <ul class="navi">
                    <li><a class="john" href="/home.do">Main</a></li>
                    <li><a href="/board_list.do">Free Board</a></li>
                    <li><a href="/archive_list.do">Archive</a></li>
                    <li><a href="/member_login.do">Login</a></li>
                    <li><a href="/member_insert.do">Sign Up</a></li>
                </ul>
            </navi>
        </div>
        <%
        } else {
        %>
        <div class="navi">
            <navi>
                <ul class="navi">
                    <li><a href="/home.do">Main</a></li>
                    <li><a href="/board_list.do">Free Board</a></li>
                    <li><a href="/archive_list.do">Archive</a></li>
                    <li><a href="/member_modify.do">${user.name}</a></li>
                    <li><a href="/member_logout.do">Logout</a></li>
                </ul>
            </navi>
        </div>
        <%
            }
        %>
    </header>
</div>
</html>
