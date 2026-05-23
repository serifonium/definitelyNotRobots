package com.example.definitelynotrobots;

/**
 * Parse AI output to Recipe format.
 * */
public class RecipeParser {

    public static Recipe parse(String aiText, Integer userId) {
        return new Recipe(
                userId,
                extract(aiText, "Title:", "Prep Time:"),
                parseInt(extract(aiText, "Prep Time:", "Cook Time:")),
                parseInt(extract(aiText, "Cook Time:", "Servings:")),
                parseInt(extract(aiText, "Servings:", "Ingredients:")),
                true,
                extract(aiText, "Ingredients:", "Method:"),
                extract(aiText, "Method:", null)
        );
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

