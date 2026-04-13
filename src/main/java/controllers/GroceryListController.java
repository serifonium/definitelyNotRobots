package controllers;

import com.example.definitelynotrobots.GroceryItem;
import com.example.definitelynotrobots.GroceryListDAO;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class GroceryListController {
    private ListView<GroceryItem> groceryListView;
    private final GroceryListDAO groceryListDAO = new GroceryListDAO();

    @FXML
    public Label errorText;

    @FXML
    public void initialize() {
        List<GroceryItem> groceryItems = groceryListDAO.getByID(UserAccountDAO.currentAccount.getID());
        if(groceryItems.getFirst() != null) System.out.print(groceryItems.getFirst().toString());
    }
}
