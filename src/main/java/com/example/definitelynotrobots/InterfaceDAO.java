package com.example.definitelynotrobots;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InterfaceDAO<ItemType> {
    void createTable();
    void insertItem(ItemType item);
    void updateItem(ItemType item);
    void deleteItem(Integer id);
    List<ItemType> getByUserID(Integer userID);
}
