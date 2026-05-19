package com.example.definitelynotrobots;

public class RecipeParser {

    public static Recipe parse(String aiText, Integer userId) {
        Recipe recipe = new Recipe();

        recipe.setUserAccountIDRecipe(userId);

        recipe.setRecipeTitle(extract(aiText, "Title:", "Prep Time:"));
        recipe.setPrepTime(parseInt(extract(aiText, "Prep Time:", "Cook Time:")));
        recipe.setCookTime(parseInt(extract(aiText, "Cook Time:", "Servings:")));
        recipe.setServings(parseInt(extract(aiText, "Servings:", "Ingredients:")));

        recipe.setIngredients(extract(aiText, "Ingredients:", "Method:"));
        recipe.setMethod(extract(aiText, "Method:", null));

        recipe.setIsSaved(true);

        return recipe;
    }

    private static String extract(String text, String start, String end) {
        int s = text.indexOf(start);
        if (s == -1) return "";
        s += start.length();

        int e = (end == null) ? text.length() : text.indexOf(end);
        if (e == -1) e = text.length();

        return text.substring(s, e).trim();
    }

    private static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }
}

