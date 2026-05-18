package com.example.definitelynotrobots;

public class Fitnessgoal {
    private Integer ID;
    public Integer getID() { return ID; }
    public Fitnessgoal setID(Integer id) { ID = id; return this; }

    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public Fitnessgoal setUserID(Integer userID) { UserID = userID; return this; }

    private Double Calories;
    public Double getCalories() { return Calories; }
    public void setCalories(Double calories) { Calories = calories; }
    public String getAmountToString() {
        if (getCalories() % 1 != 0) return getCalories().toString();
        return Integer.toString(getCalories().intValue());
    }

    private Double Carbs;
    public Double getCarbs() { return Carbs; }
    public void setCarbs(Double carbs) { Carbs = carbs; }
    public String getCarbsToString() {
        if (getCarbs() % 1 != 0) return getCarbs().toString();
        return Integer.toString(getCarbs().intValue());
    }

    private Double Fats;
    public Double getFats() { return Fats; }
    public void setFats(Double fats) { Fats = fats; }
    public String getFatsToString() {
        if (getFats() % 1 != 0) return getFats().toString();
        return Integer.toString(getFats().intValue());
    }

    private Double Protein;
    public Double getProtein() { return Protein; }
    public void setProtein(Double protein) { Protein = protein; }
    public String getProteinToString() {
        if (getProtein() % 1 != 0) return getProtein().toString();
        return Integer.toString(getProtein().intValue());
    }

    public Fitnessgoal(Integer userID, Double calories, Double carbs, Double fats, Double protein) {
        UserID = userID;
        Calories = calories;
        Carbs = carbs;
        Fats = fats;
        Protein = protein;
    }
    public Fitnessgoal(Integer id, Integer userID, Double calories, Double carbs, Double fats, Double protein ) {
        ID = id;
        UserID = userID;
        Calories = calories;
        Carbs = carbs;
        Fats = fats;
        Protein = protein;
    }

    @Override
    public String toString() {
        return "Fitnessgoal{" +
                "ID=" + ID +
                ", UserID=" + UserID +
                ", Calories='" + Calories + '\'' +
                ", Carbs=" + Carbs +
                ", Fats='" + Fats + '\'' +
                ", Proteins='" + Protein + '\'' +
                '}';
    }








}
