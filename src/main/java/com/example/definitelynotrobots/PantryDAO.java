package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PantryDAO implements InterfaceDAO<PantryItem> {
    private final Connection connection;

    public PantryDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    @Override
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS pantryList ("
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

    @Override
    public void insertItem(PantryItem item) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO pantryList (userId, item, amount, amountType, foodType, notes) VALUES (?, ?, ?, ?, ?, ?)"
            );
            insertStatement.setInt(1, item.getUserID());
            insertStatement.setString(2, item.getName());
            insertStatement.setDouble(3, item.getAmount());
            insertStatement.setString(4, item.getAmountType());
            insertStatement.setString(5, item.getFoodType().toString());
            insertStatement.setString(6, item.getNotes());
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void updateItem(PantryItem item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE pantryList SET item = ?, amount = ?, amountType = ?, foodType = ?, notes = ? WHERE id = ?"
            );
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getAmount());
            preparedStatement.setString(3, item.getAmountType());
            preparedStatement.setString(4, item.getFoodType().toString());
            preparedStatement.setString(5, item.getNotes());
            preparedStatement.setInt(6, item.getID());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void deleteItem(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pantryList WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public List<PantryItem> getByUserID(Integer userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pantryList WHERE userId = ?");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<PantryItem> groceryList = new ArrayList<PantryItem>() {};

            while(resultSet.next()) {
                groceryList.add(new PantryItem(
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
