<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.web.lab8.model.User" %>
<%@ page import="ubb.web.lab8.controller.BoardController" %>
<html>
<head>
    <title>Game</title>
</head>
<body>
    <div id="user-info">
        <% User currentUser = (User)(session.getAttribute("currentSessionUser"));%>
        Playing as: <%= currentUser.getUsername() %>
        Number of moves: <%= BoardController.GetInstance().getBoardById(currentUser.getId()).get().getNumberOfMoves() %>
        <form action="${pageContext.request.contextPath}/gameServlet" method="post">
            <input type="submit" name="reset" value="Reset">
        </form>
    </div>

    <h2 class="game-state"></h2>
    <div class="game-grid">
        <div cellId="0" class="cell"></div>
        <div cellId="1" class="cell"></div>
        <div cellId="2" class="cell"></div>
        <div cellId="3" class="cell"></div>
        <div cellId="4" class="cell"></div>
        <div cellId="5" class="cell"></div>
        <div cellId="6" class="cell"></div>
        <div cellId="7" class="cell"></div>
        <div cellId="8" class="cell"></div>
    </div>
</body>
</html>
