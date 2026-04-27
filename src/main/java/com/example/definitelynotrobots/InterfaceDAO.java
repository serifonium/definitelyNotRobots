package com.example.definitelynotrobots;

import java.util.List;

public interface InterfaceDAO<ItemType> {
    void createTable();
    void addItem(ItemType item);
    void updateItem(ItemType item);
    void deleteItem(Integer id);
    List<ItemType> getByUserID(Integer userID);
}
