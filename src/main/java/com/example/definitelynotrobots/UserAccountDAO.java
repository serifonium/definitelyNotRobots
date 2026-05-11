package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the user accounts.
 */
public class UserAccountDAO {
    /**
     * The current account the user is logged into.
     */
    public static UserAccount currentAccount;

    /**
     * Driver connection to the database.
     */
    private final Connection connection;

    /**
     * Connect driver to the database.
     */
    public UserAccountDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Drop the table for data reformatting purposes.
     */
    public UserAccountDAO dropTable() {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "DROP TABLE userAccounts"
            );
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Create the initial table if it does not exist.
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                "CREATE TABLE IF NOT EXISTS userAccounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username VARCHAR NOT NULL, "
                    + "password VARCHAR NOT NULL, "
                    + "firstname VARCHAR NOT NULL,"
                    + "lastname VARCHAR NOT NULL"
                    + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Insert a user in the database.
     * @param userAccount The user to add to the database.
     */
    public void insertUser(UserAccount userAccount) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO userAccounts (username, password, firstname, lastname) VALUES (?, ?, ?, ?)"
            );
            insertStatement.setString(1, userAccount.getUsername());
            insertStatement.setString(2, userAccount.getPassword());
            insertStatement.setString(3, userAccount.getFirstname());
            insertStatement.setString(4, userAccount.getLastname());
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Update a user in the database with the same UID.
     * @param userAccount The account to update.
     */
    public void updateUser(UserAccount userAccount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userAccounts SET username = ?, password = ?, firstname = ? WHERE id = ?");
            preparedStatement.setString(1, userAccount.getUsername());
            preparedStatement.setString(2, userAccount.getPassword());
            preparedStatement.setString(3, userAccount.getFirstname());
            preparedStatement.setInt(4, userAccount.getID());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Delete a user in the database with the same UID.
     * @param id The UID of the user to delete.
     */
    public void deleteUser(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userAccounts WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Get all users from the database.
     */
    public List<UserAccount> getAllUsers() {
        List<UserAccount> users = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet resultSet = getAll.executeQuery("SELECT * FROM userAccounts");
            while (resultSet.next()) {
                users.add(
                    new UserAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                    )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }

    /**
     * Get all users from the database.
     * @param id The UID of the item to delete.
     */
    public UserAccount getByID(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userAccounts WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Check for a user with matching username and password.
     * @param username The username to query for.
     * @param password The password to query for.
     * @return A matching userAccount or null if no match is found.
     */
    public UserAccount queryDetails(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userAccounts WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserAccount(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
