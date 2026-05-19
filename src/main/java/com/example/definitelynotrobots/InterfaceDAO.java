package com.example.definitelynotrobots;

import java.util.List;

/**
 * An interface containing CRUD functions for DB access
 */
public interface InterfaceDAO<ItemType> {
    /**
     * Create the initial table if it does not exist
     */
    void createTable();
    /**
     * Insert an item in the database.
     * @param item The item to add to the database.
     */
    void addItem(ItemType item);
    /**
     * Update an item in the database with the same UID
     * @param item The item to update.
     */
    void updateItem(ItemType item);
    /**
     * Delete an item in the database with the same UID
     * @param id The UID of the item to delete.
     */
    void deleteItem(Integer id);
    /**
     * Get all items with the same userID
     * @param userID The userID to search for.
     */
    List<ItemType> getByUserID(Integer userID);
}
