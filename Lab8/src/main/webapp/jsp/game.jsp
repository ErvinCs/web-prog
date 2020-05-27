<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.web.lab8.model.User" %>
<%@ page import="ubb.web.lab8.controller.BoardController" %>
<%@ page import="java.util.Optional" %>
<%@ page import="ubb.web.lab8.model.Board" %>
<html>
<head>
    <title>Game</title>
</head>
<body>
    <div id="user-info">
        <%  User currentUser = (User)(session.getAttribute("currentSessionUser"));
            String username = "";
            boolean valid = false;
            if (currentUser == null) {
                session.invalidate();
                response.sendRedirect("../index.html");
            } else {
                username = currentUser.getUsername();
                valid = true;
            }
        %>
        <br/>
        Playing as: <%= username %>
        <br/>
        <%
            int numberOfMoves = 0;
            if (valid) {
                BoardController instance = BoardController.GetInstance();
                Optional<Board> optionalBoard = instance.getBoardById(currentUser.getId());
                Board board;
                if (optionalBoard.isEmpty()) {
                    board = new Board(currentUser.getId());
                    instance.addBoard(board);
                } else {
                    board = optionalBoard.get();
                }
                numberOfMoves = board.getNumberOfMoves();
            }
        %>
        Number of moves: <%= numberOfMoves %>
        <br/>
        <form action="${pageContext.request.contextPath}/gameServlet" method="post">
            <input type="submit" name="reset" value="Reset">
        </form>
    </div>

    <h2 class="game-state"></h2>
    <div class="game-grid">
        <div cellId="0" class="cell">

        </div>
        <div cellId="1" class="cell">

        </div>
        <div cellId="2" class="cell">

        </div>
        <div cellId="3" class="cell">

        </div>
        <div cellId="4" class="cell">

        </div>
        <div cellId="5" class="cell">

        </div>
        <div cellId="6" class="cell">

        </div>
        <div cellId="7" class="cell">

        </div>
        <div cellId="8" class="cell">

        </div>
    </div>
</body>
</html>
