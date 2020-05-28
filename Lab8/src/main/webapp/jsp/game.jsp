<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.web.lab8.model.User" %>
<%@ page import="ubb.web.lab8.controller.BoardController" %>
<%@ page import="java.util.Optional" %>
<%@ page import="ubb.web.lab8.model.Board" %>
<html>
<head>
    <title>Game</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/game.css" />
    <script lang="javascript">
        function GetBoard() {

        }
    </script>
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
            int numberOfMoves = 0;
            BoardController instance = BoardController.GetInstance();
            Optional<Board> optionalBoard = instance.getBoardById(currentUser.getId());
            Board board = null;
            if (valid) {
                if (optionalBoard.isEmpty()) {
                    board = new Board(currentUser.getId());
                    instance.addBoard(board);
                } else {
                    board = optionalBoard.get();
                }
                numberOfMoves = board.getNumberOfMoves();
            }
        %>
        <br/>
        Playing as: <%= username %>
        <br/>
        Number of moves: <%= numberOfMoves %>
        <br/>
        <form action="${pageContext.request.contextPath}/gameServlet" method="post">
            <input type="submit" name="reset" value="Reset">
        </form>
    </div>

    <div class="game-grid">
        <div cellId="0" cellValue="<%=board == null ? 0 : String.valueOf(board.getTileAt(0))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(0)) %>.png">
        </div>
        <div cellId="1" cellValue="<%= board == null ? 0 : String.valueOf(board.getTileAt(1)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(1)) %>.png">
        </div>
        <div cellId="2" cellValue="<%=board == null ? 0 : String.valueOf(board.getTileAt(2))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(2)) %>.png">
        </div>
        <div cellId="3" cellValue="<%= board == null ? 0 : String.valueOf(board.getTileAt(3)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(3)) %>.png">
        </div>
        <div cellId="4" cellValue="<%=board == null ? 0 : String.valueOf(board.getTileAt(4))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(4)) %>.png">
        </div>
        <div cellId="5" cellValue="<%= board == null ? 0 : String.valueOf(board.getTileAt(5)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(5)) %>.png">
        </div>
        <div cellId="6" cellValue="<%=board == null ? 0 : String.valueOf(board.getTileAt(6))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(6)) %>.png">
        </div>
        <div cellId="7" cellValue="<%= board == null ? 0 : String.valueOf(board.getTileAt(7)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(7)) %>.png">
        </div>
        <div cellId="8" cellValue="<%=board == null ? 0 : String.valueOf(board.getTileAt(8))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(8)) %>.png">
        </div>
    </div>
</body>
</html>
