package com.example.definitelynotrobots;

public class GroceryItem {
    private Integer ID;
    public Integer getID() { return ID; }
    public void setID(Integer id) { ID = id; }

    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public void setUserID(Integer userID) { UserID = userID; }

    private String Name;
    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    private Integer Amount;
    public Integer getAmount() { return Amount; }
    public void setAmount(Integer amount) { Amount = amount; }

    private String Notes;
    public String getNotes() { return Notes; }
    public void setNotes(String notes) { Notes = notes; }

    private String AmountType;
    public String getAmountType() { return AmountType; }
    public void setAmountType(String amountType) { AmountType = amountType; }

    private FoodTypesEnum FoodType;
    public FoodTypesEnum getFoodType() { return FoodType; }
    public void setFoodType(FoodTypesEnum foodType) { FoodType = foodType; }

    public GroceryItem(Integer userID, String name, Integer amount, String amountType, FoodTypesEnum foodType, String notes) {
        UserID = userID;
        Name = name;
        Amount = amount;
        AmountType = amountType;
        FoodType = foodType;
        Notes = notes;
    }
    public GroceryItem(Integer id, Integer userID, String name, Integer amount, String amountType, FoodTypesEnum foodType, String notes) {
        ID = id;
        UserID = userID;
        Name = name;
        Amount = amount;
        AmountType = amountType;
        FoodType = foodType;
        Notes = notes;
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
