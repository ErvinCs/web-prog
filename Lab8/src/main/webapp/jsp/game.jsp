<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.web.lab8.model.User" %>
<%@ page import="ubb.web.lab8.controller.BoardController" %>
<%@ page import="java.util.Optional" %>
<%@ page import="ubb.web.lab8.model.Board" %>
<%  User currentUser = (User)(session.getAttribute("currentSessionUser"));
    String username = "";
    Long userId = Long.valueOf(-1);
    boolean valid = false;
    if (currentUser == null) {
        session.invalidate();
        response.sendRedirect("../index.html");
    } else {
        username = currentUser.getUsername();
        userId = currentUser.getId();
        valid = true;
    }
    int numberOfMoves = 0;
    BoardController instance = BoardController.GetInstance();
    Optional<Board> optionalBoard = instance.getByUserId(currentUser.getId());
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
<html>
<head>
    <title>Game</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/game.css" />
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">
        function UpdatePage() {

        }


        $(document).ready(function(){
            $(".cell").click(function(){
                var div = $("div[name='game-cell']").eq($(this).index());
                var id = div.attr("id");
                var value = div.attr("value");
                var userId = $("#user_id").attr("value");
                $.get('gameServlet', {
                    cellId: id,
                    cellVal: value,
                    userId: userId
                });

            });
        });

    </script>
</head>
<body>
    <div id="user-info">
        <br/>
        Playing as: <%= username %>
        <br/>
        Number of moves: <%= numberOfMoves %>
        <br/>
        <form action="${pageContext.request.contextPath}/gameServlet" method="post">
            <input type="submit" name="reset" value="Reset">
        </form>
    </div>

    <%
        int[] tiles = (int[])request.getAttribute("board");
        for(int i = 0; i < Board.BoardSize; i++) {
    %>



    <%
        }
    %>
    <div class="game-grid">
        <div id="0" name="game-cell" value="<%=board == null ? 0 : String.valueOf(board.getTileAt(0))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(0)) %>.png">
        </div>
        <div id="1" name="game-cell" value="<%= board == null ? 0 : String.valueOf(board.getTileAt(1)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(1)) %>.png">
        </div>
        <div id="2" name="game-cell" value="<%=board == null ? 0 : String.valueOf(board.getTileAt(2))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(2)) %>.png">
        </div>
        <div id="3" name="game-cell" value="<%= board == null ? 0 : String.valueOf(board.getTileAt(3)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(3)) %>.png">
        </div>
        <div id="4" name="game-cell" value="<%=board == null ? 0 : String.valueOf(board.getTileAt(4))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(4)) %>.png">
        </div>
        <div id="5" name="game-cell" value="<%= board == null ? 0 : String.valueOf(board.getTileAt(5)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(5)) %>.png">
        </div>
        <div id="6" name="game-cell" value="<%=board == null ? 0 : String.valueOf(board.getTileAt(6))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(6)) %>.png">
        </div>
        <div id="7" name="game-cell" value="<%= board == null ? 0 : String.valueOf(board.getTileAt(7)) %>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(7)) %>.png">
        </div>
        <div id="8" name="game-cell" value="<%=board == null ? 0 : String.valueOf(board.getTileAt(8))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(8)) %>.png">
        </div>
    </div>

    <input type="hidden" id="user_id" name="user_id" value="<%=userId%>">
</body>
</html>
