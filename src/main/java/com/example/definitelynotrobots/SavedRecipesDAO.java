package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SavedRecipesDAO {
    public static Recipe currentRecipe;

    private final Connection connection;

    public SavedRecipesDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS savedRecipes ("
                            + "userAccountRecipeID INTEGER NOT NULL,"
                            + "recipeTitle VARCHAR PRIMARY KEY, "
                            + "prepTime INTEGER NOT NULL, "
                            + "cookTime INTEGER NOT NULL, "
                            + "servings INTEGER NOT NULL,"
                            + "isSaved VARCHAR NOT NULL"
                            + "ingredients VARCHAR NOT NULL"
                            + "method VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insertRecipe(Recipe recipe) {
        try {
            if(recipe.getIsSaved()) {
                PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO savedRecipes (userAccountIDRecipe, recipeTitle, prepTime, cookTime, servings, isSaved, ingredients, method) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );
                insertStatement.setInt(1, recipe.getUserAccountIDRecipe());
                insertStatement.setString(2, recipe.getRecipeTitle());
                insertStatement.setInt(3, recipe.getPrepTime());
                insertStatement.setInt(4, recipe.getCookTime());
                insertStatement.setInt(5, recipe.getServings());
                insertStatement.setBoolean(6, recipe.getIsSaved());
                insertStatement.setString(7, recipe.getIngredients());
                insertStatement.setString(8, recipe.getMethod());
                insertStatement.execute();
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteSavedRecipe(String recipeTitle) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM savedRecipes WHERE recipeTitle = ?");
            preparedStatement.setString(1, recipeTitle);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Recipe> getAllSavedRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet resultSet = getAll.executeQuery("SELECT * FROM savedRecipes");
            while (resultSet.next()) {
                recipes.add(
                        new Recipe(
                                resultSet.getInt("user account ID"),
                                resultSet.getString("recipe title"),
                                resultSet.getInt("prep time"),
                                resultSet.getInt("cook time"),
                                resultSet.getInt("servings"),
                                resultSet.getBoolean("saved status"),
                                resultSet.getString("ingredients"),
                                resultSet.getString("method")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return recipes;
    }

    public Recipe getByTitle(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM savedRecipes WHERE title = ?");
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Recipe(
                        resultSet.getInt("user account ID"),
                        resultSet.getString("recipe title"),
                        resultSet.getInt("prep time"),
                        resultSet.getInt("cook time"),
                        resultSet.getInt("servings"),
                        resultSet.getBoolean("saved status"),
                        resultSet.getString("ingredients"),
                        resultSet.getString("method")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
