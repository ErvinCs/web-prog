package ubb.web.lab8.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.web.lab8.model.Board;
import ubb.web.lab8.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

public class BoardController {
    private static final Logger logger = LogManager.getLogger(BoardController.class);

    private BoardRepository boards;
    private static BoardController Controller = new BoardController();

    private BoardController() { this.boards = new BoardRepository();}

    public static BoardController GetInstance() { return Controller;}

    public List<Board> getBoards() {
        logger.log(Level.INFO, "Get Boards: {}", this.boards);
        return boards.getAll();
    }

    public void addBoard(Board board) {
        logger.log(Level.INFO, "Add Board: {}", board);
        this.boards.add(board);
    }

    public void deleteBoard(Long id) {
        logger.log(Level.INFO, "Delete Board (By Id): {}", id);
        this.boards.delete(id);
    }

    public void updateBoard(Board board) {
        logger.log(Level.INFO, "Update Board: {}", board);
        this.boards.update(board);
    }

    public Optional<Board> getBoardById(Long id) {
        logger.log(Level.INFO, "Get Board By Id: {}", id);
        return this.boards.getById(id);
    }


}
