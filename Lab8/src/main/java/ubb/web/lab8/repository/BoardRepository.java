package ubb.web.lab8.repository;

import ubb.web.lab8.config.DataSourceConfig;
import ubb.web.lab8.model.Board;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardRepository implements IRepository<Board> {
    private final String table_name = "boards";

    public BoardRepository() {}

    private Board parseBoard(ResultSet rs) throws SQLException, IOException {
        Long id = rs.getLong("board_id");

        //Array tilesArray = rs.getArray("tiles");
        //Integer[] tiles = (Integer[])tilesArray.getArray();

        Integer[] tiles = new Integer[Board.BoardSize];
        byte[] asBytes = rs.getBytes("tiles");
        ByteArrayInputStream bin = new ByteArrayInputStream(asBytes);
        DataInputStream din = new DataInputStream(bin);
        for (int i = 0; i < Board.BoardSize; i++) {
            tiles[i] = din.readInt();
        }

        Long userId = rs.getLong("user_id");
        Integer numberOfMoves = rs.getInt("number_of_moves");

        Board board = new Board(tiles, userId, numberOfMoves);
        board.setId(id);

        return board;
    }

    @Override
    public void add(Board board) throws IOException{
        String sql = "INSERT INTO " + table_name + " (user_id, number_of_moves, tiles) VALUES (?,?,?)";

        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            //Array tileArray = connection.createArrayOf("VARCHAR", board.getTiles());

            Integer[] tiles = board.getTiles();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(bout);
            for (Integer integer : tiles) {
                dout.writeInt(integer);
            }
            dout.close();
            byte[] asBytes = bout.toByteArray();

            stmt.setLong(1, board.getUserId());
            stmt.setInt(2, board.getNumberOfMoves());
            stmt.setBytes(3, asBytes);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Board board) throws IOException{
        String sql = "UPDATE" + table_name + "set user_id=?, number_of_moves=?, tiles=? where board_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            //Array tileArray = connection.createArrayOf("VARCHAR", board.getTiles());
            Integer[] tiles = board.getTiles();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(bout);
            for (Integer integer : tiles) {
                dout.writeInt(integer);
            }
            dout.close();
            byte[] asBytes = bout.toByteArray();

            statement.setLong(1, board.getUserId());
            statement.setInt(2, board.getNumberOfMoves());
            statement.setBytes(3, asBytes);
            statement.setLong(4, board.getId());
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
    public Optional<Board> getById(Long id) throws IOException{
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
    public List<Board> getAll() throws IOException {
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