package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Data Access Object for the pantry list.
 */
public class PantryDAO implements InterfaceDAO<PantryItem> {
    /**
     * Driver connection to the database.
     */
    private final Connection connection;

    /**
     * Connect driver to the database
     */
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
    public void addItem(PantryItem item) {
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

    /**
     * Insert an item of the same type to fill in matching rows.
     * @param inputItem The item to insert into the database.
     */
    public void insertItem(PantryItem inputItem) {
        List<PantryItem> allItems = getByUserID(inputItem.getUserID());

        PantryItem matchingItem = null;
        for (PantryItem item : allItems) {
            if(item.getName().equals(inputItem.getName())) {
                matchingItem = item;
                break;
            }
        }
        if(matchingItem == null) { addItem(inputItem); return; }

        if(matchingItem.getAmountType().equals(inputItem.getAmountType())) {
            inputItem.setID(matchingItem.getID());
            inputItem.setAmount(matchingItem.getAmount() + inputItem.getAmount());
            updateItem(inputItem);
            return;
        }

        List<MetricConversion> metricConversionList = new ArrayList<>();
        metricConversionList.add(new MetricConversion("L", "mL", 1000d));

        for (MetricConversion metricConversion : metricConversionList) {
            if(metricConversion.isApplicableTypes(inputItem.getAmountType(), matchingItem.getAmountType())) {
                Double newAmount = metricConversion.addValues(matchingItem.getAmount(), inputItem.getAmount(), matchingItem.getAmountType());
                inputItem.setID(matchingItem.getID());
                inputItem.setAmount(newAmount);
                inputItem.setAmountType(matchingItem.getAmountType());
                updateItem(inputItem);
            }
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
