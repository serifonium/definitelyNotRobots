package com.example.definitelynotrobots;

/**
 * A model that represents a user's fitness goals
 * */
public class FitnessGoal {
    /**
     * A unique ID to identify the goal.
     * */
    private Integer ID;
    public Integer getID() { return ID; }
    public FitnessGoal setID(Integer id) { ID = id; return this; }

    /**
     * The ID of the user who made the goal.
     * */
    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public FitnessGoal setUserID(Integer userID) { UserID = userID; return this; }

    /**
     * The calories target for the goal.
     * */
    private Double Calories;
    public Double getCalories() { return Calories; }
    public void setCalories(Double calories) { Calories = calories; }
    public String getAmountToString() {
        if (getCalories() % 1 != 0) return getCalories().toString();
        return Integer.toString(getCalories().intValue());
    }

    /**
     * The carbs target for the goal.
     * */
    private Double Carbs;
    public Double getCarbs() { return Carbs; }
    public void setCarbs(Double carbs) { Carbs = carbs; }
    public String getCarbsToString() {
        if (getCarbs() % 1 != 0) return getCarbs().toString();
        return Integer.toString(getCarbs().intValue());
    }

    /**
     * The fat target for the goal.
     * */
    private Double Fats;
    public Double getFats() { return Fats; }
    public void setFats(Double fats) { Fats = fats; }
    public String getFatsToString() {
        if (getFats() % 1 != 0) return getFats().toString();
        return Integer.toString(getFats().intValue());
    }

    /**
     * The protein target for the goal.
     * */
    private Double Protein;
    public Double getProtein() { return Protein; }
    public void setProtein(Double protein) { Protein = protein; }
    public String getProteinToString() {
        if (getProtein() % 1 != 0) return getProtein().toString();
        return Integer.toString(getProtein().intValue());
    }

    /**
     * Create a fitness goal with no UUID.
     * @param userID The ID of the user who made the goal.
     * @param calories The calories target for the goal.
     * @param carbs The carbs target for the goal.
     * @param fats The fats target for the goal.
     * @param protein The protein target for the goal.
     * */
    public FitnessGoal(Integer userID, Double calories, Double carbs, Double fats, Double protein) {
        UserID = userID;
        Calories = calories;
        Carbs = carbs;
        Fats = fats;
        Protein = protein;
    }
    /**
     * Create a fitness goal with a UUID.
     * @param id The UUID of the goal.
     * @param userID The ID of the user who made the goal.
     * @param calories The calories target for the goal.
     * @param carbs The carbs target for the goal.
     * @param fats The fats target for the goal.
     * @param protein The protein target for the goal.
     * */
    public FitnessGoal(Integer id, Integer userID, Double calories, Double carbs, Double fats, Double protein ) {
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
