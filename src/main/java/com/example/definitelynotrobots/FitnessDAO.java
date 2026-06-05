package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for fitness goals that the user has set.
 * */
public class FitnessDAO {
    /**
     * The connection to the database.
     * */
    private final Connection connection;

    /**
     * Remove the goals table for debugging purposes.
     * */
    public FitnessDAO dropTable() {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "DROP TABLE Fitnessgoals"
            );
            insertStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Creates the table for fitness goals.
     * */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Fitnessgoals ("
                            + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userID INTEGER NOT NULL,"
                            + "Calories DOUBLE NOT NULL, "
                            + "Carbs DOUBLE NOT NULL, "
                            + "Fats DOUBLE NOT NULL,"
                            + "Protein DOUBLE NOT NULL"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Initializes connection and creates the table.
     * */
    public FitnessDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Insert a fitness goal into the database.
     * @param fitnessgoal The fitness goal to insert into the database.
     * */
    public void insertFitnessgoal(FitnessGoal fitnessgoal) {
        try {
                PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO Fitnessgoals (userID, Calories, Carbs, Fats, Protein) VALUES ( ?, ?, ?, ?, ?)"
                );
                insertStatement.setDouble(1, fitnessgoal.getUserID());
                insertStatement.setDouble(2, fitnessgoal.getCalories());
                insertStatement.setDouble(3, fitnessgoal.getCarbs());
                insertStatement.setDouble(4, fitnessgoal.getFats());
                insertStatement.setDouble(5, fitnessgoal.getProtein());
                insertStatement.execute();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Get all fitness goal from the database.
     * @return A list of all returned fitness goals.
     * */
    public List<FitnessGoal> getAllfitnessgoals() {
        List<FitnessGoal> goals = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fitnessgoals");

            while (rs.next()) {
                goals.add(new FitnessGoal(
                        rs.getInt("userID"),
                        rs.getDouble("calories"),
                        rs.getDouble("carbs"),
                        rs.getDouble("fats"),
                        rs.getDouble("protein")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goals;
    }

    /**
     * Get a fitness goal from the database with the matching userID.
     * @param userID The id of the user to get goals from.
     * @return The returned fitness goals from the user ID.
     * */
    public FitnessGoal getByUserID(Integer userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Fitnessgoals WHERE userID = ?");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new FitnessGoal(
                        resultSet.getInt("ID"),
                        resultSet.getInt("userID"),
                        resultSet.getDouble("Calories"),
                        resultSet.getDouble("Carbs"),
                        resultSet.getDouble("Fats"),
                        resultSet.getDouble("Proteins")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
