package ubb.exam.repository;

import ubb.exam.config.DataSourceConfig;
import ubb.exam.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemRepository implements IRepository<Item> {
    private final String table_name = "items";

    public ItemRepository() {
    }

    private Item parseItem(ResultSet rs) throws SQLException {
        Long itemId = rs.getLong("item_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer value = rs.getInt("value");
        Long userId = rs.getLong("user_id");

        Item item = new Item(name, description, value, userId);
        item.setId(itemId);

        return item;
    }

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO " + table_name + " (name, description, value, user_id) VALUES (?,?,?,?)";
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setInt(3, item.getValue());
            stmt.setLong(4, item.getUserId());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM " + table_name + " WHERE item_id=?";
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void update(Item item) {
        String sql = "UPDATE " + table_name + " set name=?, description=?, value=?, user_id=? where item_id=?";
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getValue());
            statement.setLong(4, item.getUserId());
            statement.setLong(5, item.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Item> getById(Long id) {
        String sql = "SELECT * FROM " + table_name + " WHERE item_id=?";
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Item item = this.parseItem(rs);
                connection.close();
                return Optional.of(item);
            } else {
                connection.close();
                return Optional.empty();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }


    @Override
    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name;
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Item item = this.parseItem(rs);
                itemList.add(item);
            }
            connection.close();
            return itemList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Item> getItemsByUser(Long id) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name + " WHERE user_id=?";
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Item item = this.parseItem(rs);
                itemList.add(item);
            }
            connection.close();
            return itemList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
