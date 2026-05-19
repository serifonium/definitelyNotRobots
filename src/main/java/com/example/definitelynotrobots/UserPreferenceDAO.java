package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPreferenceDAO {
    /**
     * Driver connection to the database.
     */
    private final Connection connection;

    /**
     * Connect driver to the database.
     */
    public UserPreferenceDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Drop the table for data reformatting purposes.
     */
    public void dropTable() {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "DROP TABLE userPreferences"
            );
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Create the initial table if it does not exist.
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS userPreferences ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userId INTEGER NOT NULL, "
                            + "content VARCHAR NOT NULL, "
                            + "preferenceType VARCHAR NOT NULL "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Insert a preference in the database.
     * @param userPreference The preference to add to the database.
     */
    public void insertPreference(UserPreference userPreference) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO userPreferences (userId, content, preferenceType) VALUES (?, ?, ?)"
            );
            insertStatement.setInt(1, userPreference.getUserID());
            insertStatement.setString(2, userPreference.getContent());
            insertStatement.setString(3, userPreference.getPreferenceType().toString());
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Update a preference in the database with the same UID.
     * @param userPreference The preference to update.
     */
    public void updatePreference(UserPreference userPreference) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userPreferences SET userId = ?, content = ?, preferenceType = ? WHERE id = ?");
            preparedStatement.setInt(1, userPreference.getUserID());
            preparedStatement.setString(2, userPreference.getContent());
            preparedStatement.setString(3, userPreference.getPreferenceType().toString());
            preparedStatement.setInt(4, userPreference.getID());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Delete a preference in the database with the same UID.
     * @param id The UID of the preference to delete.
     */
    public void deleteUser(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userPreferences WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Get all preferences from the database associated with a user.
     * @param userID The user ID to query for.
     */
    public List<UserPreference> getByUserID(Integer userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userPreferences WHERE userId = ?");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<UserPreference> preferenceList = new ArrayList<UserPreference>() {};

            while(resultSet.next()) {
                preferenceList.add(new UserPreference(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getString("content"),
                        PreferenceTypeEnum.valueOf(resultSet.getString("preferenceType"))
                ));
            }

            return preferenceList;
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
