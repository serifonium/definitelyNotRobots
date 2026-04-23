package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroceryListDAO implements InterfaceDAO<GroceryItem> {
    private final Connection connection;

    public GroceryListDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS groceryList ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userId INTEGER NOT NULL, "
                            + "item VARCHAR NOT NULL, "
                            + "amount DOUBLE NOT NULL, "
                            + "amountType VARCHAR DEFAULT 'x',"
                            + "foodType VARCHAR NOT NULL,"
                            + "notes VARCHAR "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    // ENUM('Baking', 'Seasoning', 'Oil', 'Starch', 'Dairy', 'Meat', 'Fruit', 'Vegetable')

    public void addItem(GroceryItem groceryItem) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO groceryList (userId, item, amount, amountType, foodType, notes) VALUES (?, ?, ?, ?, ?, ?)"
            );
            insertStatement.setInt(1, groceryItem.getUserID());
            insertStatement.setString(2, groceryItem.getName());
            insertStatement.setDouble(3, groceryItem.getAmount());
            insertStatement.setString(4, groceryItem.getAmountType());
            insertStatement.setString(5, groceryItem.getFoodType().toString());
            insertStatement.setString(6, groceryItem.getNotes());
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void updateItem(GroceryItem groceryItem) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE groceryList SET item = ?, amount = ?, amountType = ?, foodType = ?, notes = ? WHERE id = ?"
            );
            preparedStatement.setString(1, groceryItem.getName());
            preparedStatement.setDouble(2, groceryItem.getAmount());
            preparedStatement.setString(3, groceryItem.getAmountType());
            preparedStatement.setString(4, groceryItem.getFoodType().toString());
            preparedStatement.setString(5, groceryItem.getNotes());
            preparedStatement.setInt(6, groceryItem.getID());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteItem(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM groceryList WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<GroceryItem> getByUserID(Integer userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groceryList WHERE userId = ?");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<GroceryItem> groceryList = new ArrayList<GroceryItem>() {};

            while(resultSet.next()) {
                groceryList.add(new GroceryItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getString("item"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("amountType"),
                        FoodTypesEnum.valueOf(resultSet.getString("foodType")),
                        resultSet.getString("notes")
                ));
            }

            return groceryList;
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
