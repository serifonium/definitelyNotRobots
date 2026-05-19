package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FitnessDAO {
    public static Fitnessgoal currentFitnessgoal;

    private final Connection connection;

    public FitnessDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

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

    public void insertFitnessgoal(Fitnessgoal fitnessgoal) {
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

    public List<Fitnessgoal> getAllfitnessgoals() {
        List<Fitnessgoal> goals = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fitnessgoals");

            while (rs.next()) {
                goals.add(new Fitnessgoal(
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

    public Fitnessgoal getByUserID(Integer userID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Fitnessgoals WHERE userID = ?");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Fitnessgoal(
                        resultSet.getInt("ID"),
                        resultSet.getInt("userID"),
                        resultSet.getDouble("calories"),
                        resultSet.getDouble("carbs"),
                        resultSet.getDouble("fats"),
                        resultSet.getDouble("protein")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
