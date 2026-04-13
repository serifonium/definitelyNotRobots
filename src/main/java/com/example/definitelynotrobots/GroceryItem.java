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

    public GroceryItem(String name, Integer amount) {
        Name = name;
        Amount = amount;
        Notes = "";
    }
    public GroceryItem(String name, Integer amount, String notes) {
        Name = name;
        Amount = amount;
        Notes = notes;
    }
    public GroceryItem(Integer userID, String name, Integer amount) {
        UserID = userID;
        Name = name;
        Amount = amount;
        Notes = "";
    }
    public GroceryItem(Integer userID, String name, Integer amount, String notes) {
        UserID = userID;
        Name = name;
        Amount = amount;
        Notes = notes;
    }
    public GroceryItem(Integer id, Integer userID, String name, Integer amount, String notes) {
        ID = id;
        UserID = userID;
        Name = name;
        Amount = amount;
        Notes = notes;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "ID=" + ID +
                ", UserID=" + UserID +
                ", Name='" + Name + '\'' +
                ", Amount=" + Amount +
                ", Notes='" + Notes + '\'' +
                '}';
    }
}
