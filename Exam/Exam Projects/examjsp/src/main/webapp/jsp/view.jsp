<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.exam.model.User" %>
<%@ page import="ubb.exam.controller.UserController"%>
<%
    User currentUser = (User)(session.getAttribute("currentSessionUser"));
    if (currentUser == null) {
        session.invalidate();
        response.sendRedirect("../index.html");
    }
%>

<html>
<head>
    <title>Items</title>
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">

        $(document).ready(function() {
            $("#").click(function(){
                $.getJSON('servlet',
                    {},
                    func
                );
            });
        });

    </script>
</head>
<body>
    <h3>  </h3>
    <h5>Logged in as: <%=currentUser.getUsername()%></h5>

    <input id="x" type="button" value="">

    <div id="y">
    </div>
    <br/>

    <br/>
    <input id="log-out-btn" type="button" value="Log Out" onclick="document.location.href='logout.jsp'">

    <input type="hidden" id="user_id" name="user_id" value="<%=currentUser.getId()%>">
</body>
</html>
