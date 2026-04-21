package com.example.definitelynotrobots;

public class PantryItem {
    private Integer ID;
    public Integer getID() { return ID; }
    public PantryItem setID(Integer id) { ID = id; return this; }

    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public PantryItem setUserID(Integer userID) { UserID = userID; return this; }

    private String Name;
    public String getName() { return Name; }
    public PantryItem setName(String name) { Name = name; return this; }

    private Double Amount;
    public Double getAmount() { return Amount; }
    public PantryItem setAmount(Double amount) { Amount = amount; return this; }
    public String getAmountToString() {
        if (getAmount() % 1 != 0) return getAmount().toString();
        return Integer.toString(getAmount().intValue());
    }

    private String Notes;
    public String getNotes() { return Notes; }
    public PantryItem setNotes(String notes) { Notes = notes; return this; }

    private String AmountType;
    public String getAmountType() { return AmountType; }
    public PantryItem setAmountType(String amountType) { AmountType = amountType; return this; }

    private FoodTypesEnum FoodType;
    public FoodTypesEnum getFoodType() { return FoodType; }
    public PantryItem setFoodType(FoodTypesEnum foodType) { FoodType = foodType; return this; }

    public PantryItem(Integer userID, String name, Double amount, String amountType, FoodTypesEnum foodType, String notes) {
        UserID = userID;
        Name = name;
        Amount = amount;
        AmountType = amountType;
        FoodType = foodType;
        Notes = notes;
    }
    public PantryItem(Integer id, Integer userID, String name, Double amount, String amountType, FoodTypesEnum foodType, String notes) {
        ID = id;
        UserID = userID;
        Name = name;
        Amount = amount;
        AmountType = amountType;
        FoodType = foodType;
        Notes = notes;
    }

    public PantryItem() {
        ID = null;
        UserID = null;
        Name = null;
        Amount = null;
        AmountType = null;
        FoodType = null;
        Notes = null;
    }

    public Double evalNewAmount(Double amount, String amountType) {
        if(AmountType.equals(amountType)) {
            return Amount + amount;
        }
        return 0d;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "ID=" + ID +
                ", UserID=" + UserID +
                ", Name='" + Name + '\'' +
                ", Amount=" + Amount +
                ", AmountType='" + AmountType + '\'' +
                ", FoodType='" + FoodType + '\'' +
                ", Notes='" + Notes + '\'' +
                '}';
    }
}