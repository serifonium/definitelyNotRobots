package com.example.definitelynotrobots;
import javafx.scene.image.Image;

public class Recipe {

    private Integer UserAccountIDRecipe;
    public Integer getUserAccountIDRecipe(){return UserAccountIDRecipe;}

    private Image RecipeImage;
    public Image getRecipeImage(){return RecipeImage;}
    public void setRecipeImage(Image recipeImage){RecipeImage = recipeImage;}

    private String RecipeTitle;
    public String getRecipeTitle(){return RecipeTitle;}
    public void setRecipeTitle(String recipeTitle){RecipeTitle = recipeTitle;}

    private Integer PrepTime;
    public Integer getPrepTime(){return PrepTime;}
    public void setPrepTime(Integer prepTime){PrepTime = prepTime;}

    private Integer CookTime;
    public Integer getCookTime(){return CookTime;}
    public void setCookTime(Integer cookTime){CookTime = cookTime;}

    private Integer Servings;
    public Integer getServings(){return Servings;}
    public void setServings(Integer servings){Servings = servings;}

    private Boolean IsSaved;
    public Boolean getIsSaved(){return IsSaved;}
    public void setIsSaved(Boolean isSaved){IsSaved = isSaved;}

    private String Ingredients;
    public String getIngredients(){return Ingredients;}
    public void setIngredients(String ingredients){Ingredients = ingredients;}

    private String Method;
    public String getMethod(){return Method;}
    public void setMethod(String method){Method = method;}

    public Recipe(Integer userAccountIDRecipe, Image recipeImage, String recipeTitle, Integer prepTime, Integer cookTime, Integer servings, Boolean isSaved, String ingredients, String method){
        UserAccountIDRecipe = userAccountIDRecipe;
        RecipeImage = recipeImage;
        RecipeTitle = recipeTitle;
        PrepTime = prepTime;
        CookTime = cookTime;
        Servings = servings;
        IsSaved = isSaved;
        Ingredients = ingredients;
        Method = method;
    }
    public Recipe(Integer userAccountIDRecipe, String recipeTitle, Integer prepTime, Integer cookTime, Integer servings, Boolean isSaved, String ingredients, String method){
        UserAccountIDRecipe = userAccountIDRecipe;
        RecipeTitle = recipeTitle;
        PrepTime = prepTime;
        CookTime = cookTime;
        Servings = servings;
        IsSaved = isSaved;
        Ingredients = ingredients;
        Method = method;
    }
}
