package ubb.web.lab8.repository;

import ubb.web.lab8.config.DataSourceConfig;
import ubb.web.lab8.model.Board;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BoardRepository extends IRepository<Board> {
    private final String table_name = "boards";

    public BoardRepository() {}

    private Board parseBoard(ResultSet rs) throws SQLException {
        Long id = rs.getLong("board_id");

        Array tilesArray = rs.getArray("tiles");
        Integer[] tiles = (Integer[])tilesArray.getArray();
        Long userId = rs.getLong("user_id");
        Integer numberOfMoves = rs.getInt("number_of_moves");

        Board board = new Board(tiles, userId, numberOfMoves);
        board.setId(id);

        return board;
    }

    //TODO
    @Override
    public void add(Board board) {
        String sql = "INSERT INTO " + table_name + " (...) VALUES (?,?,?,?,?,?...)";

        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //TODO
    @Override
    public void update(Board user) {
        String sql = "UPDATE" + table_name + "set ...=?, ...=? where board_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM " + table_name + " WHERE board_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Board> getById(Long id) {
        String sql = "SELECT * FROM " + table_name + " WHERE board_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Board board = this.parseBoard(rs);
                connection.close();
                return Optional.of(board);
            }
            else {
                connection.close();
                return Optional.empty();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Board> getAll() {
        List<Board> boardList = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name;
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Board board = this.parseBoard(rs);
                boardList.add(board);
            }
            connection.close();
            return boardList;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
