package com.example.definitelynotrobots;

public abstract class BaseItem {
    /**
     * The unique ID of the item.
     */
    private Integer ID;
    public Integer getID() { return ID; }
    public void setID(Integer id) { ID = id; }

    /**
     * The ID of the user who the item belongs to.
     */
    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public void setUserID(Integer userID) { UserID = userID; }

    /**
     * The name of the item.
     */
    private String Name;
    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    /**
     * The amount of the item.
     */
    private Double Amount;
    public Double getAmount() { return Amount; }
    public void setAmount(Double amount) { Amount = amount; }
    public String getAmountToString() {
        if (getAmount() % 1 != 0) return getAmount().toString();
        return Integer.toString(getAmount().intValue());
    }

    /**
     * Optional notes that the user can leave for themselves.
     */
    private String Notes;
    public String getNotes() { return Notes; }
    public void setNotes(String notes) { Notes = notes; }

    /**
     * The unit of measurement used by Amount.
     */
    private String AmountType;
    public String getAmountType() { return AmountType; }
    public void setAmountType(String amountType) { AmountType = amountType; }

    /**
     * The category of food of which the item falls under.
     */
    private FoodTypesEnum FoodType;
    public FoodTypesEnum getFoodType() { return FoodType; }
    public void setFoodType(FoodTypesEnum foodType) { FoodType = foodType; }
}
