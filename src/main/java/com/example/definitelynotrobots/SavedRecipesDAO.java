package com.example.definitelynotrobots;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for generated recipes.
 */
public class SavedRecipesDAO {

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
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userAccountIDRecipe INTEGER NOT NULL, "
                            + "recipeTitle TEXT NOT NULL, "
                            + "prepTime INTEGER NOT NULL, "
                            + "cookTime INTEGER NOT NULL, "
                            + "servings INTEGER NOT NULL, "
                            + "isSaved BOOLEAN NOT NULL, "
                            + "ingredients TEXT NOT NULL, "
                            + "method TEXT NOT NULL"
                            + ")"
            );


        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insertRecipe(Recipe recipe) {
        try {
            PreparedStatement ps = connection.prepareStatement("""
            INSERT INTO savedRecipes 
            (userAccountIDRecipe, recipeTitle, prepTime, cookTime, servings, isSaved, ingredients, method)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """);

            ps.setInt(1, recipe.getUserAccountIDRecipe());
            ps.setString(2, recipe.getRecipeTitle());
            ps.setInt(3, recipe.getPrepTime());
            ps.setInt(4, recipe.getCookTime());
            ps.setInt(5, recipe.getServings());
            ps.setBoolean(6, recipe.getIsSaved());
            ps.setString(7, recipe.getIngredients());
            ps.setString(8, recipe.getMethod());

            ps.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    public Recipe getFirstRecipeInList(int userId){
        List<Recipe> recipes = getSavedRecipesForUser(userId);
        Recipe lastRecipe = recipes.getLast();
        return lastRecipe;
    }


    public List<Recipe> getSavedRecipesForUser(int userId) {
        List<Recipe> recipes = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM savedRecipes WHERE userAccountIDRecipe = ?"
            );
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recipe recipe = new Recipe();
                recipe.setUserAccountIDRecipe(rs.getInt("userAccountIDRecipe"));
                recipe.setRecipeTitle(rs.getString("recipeTitle"));
                recipe.setPrepTime(rs.getInt("prepTime"));
                recipe.setCookTime(rs.getInt("cookTime"));
                recipe.setServings(rs.getInt("servings"));
                recipe.setIsSaved(rs.getBoolean("isSaved"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setMethod(rs.getString("method"));
                recipes.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }


}
