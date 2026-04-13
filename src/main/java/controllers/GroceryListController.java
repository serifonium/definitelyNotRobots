package controllers;

import com.example.definitelynotrobots.GroceryItem;
import com.example.definitelynotrobots.GroceryListDAO;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class GroceryListController {
    @FXML
    private ListView<GroceryItem> groceryListView;
    private final GroceryListDAO groceryListDAO = new GroceryListDAO();

    @FXML
    public Label errorText;
    public TextField itemNameField;
    public TextField itemAmountField;
    public TextArea itemNotesField;

    @FXML
    private void selectGroceryItem(GroceryItem groceryItem) {
        groceryListView.getSelectionModel().select(groceryItem);
        itemNameField.setText(groceryItem.getName());
        itemAmountField.setText(groceryItem.getAmount().toString());
        itemNotesField.setText(groceryItem.getNotes());
    }

    @FXML
    private void onEditConfirm() {
        try {
            Integer.parseInt(itemAmountField.getText());
        } catch (NumberFormatException e) {
            errorText.setText("Amount field must be an integer");
            return;
        }

        // Get the selected contact from the list view
        GroceryItem selectedItem = groceryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem.setName(itemNameField.getText());
            selectedItem.setAmount(Integer.parseInt(itemAmountField.getText()));
            selectedItem.setNotes(itemNotesField.getText());
            groceryListDAO.updateGroceryItem(selectedItem);
            syncGroceryList();
            selectGroceryItem(selectedItem);
        }
    }

    private ListCell<GroceryItem> renderCell(ListView<GroceryItem> contactListView) {
        return new ListCell<>() {
            /**
             * Handles the event when a contact is selected in the list view.
             * @param mouseEvent The event to handle.
             */
            private void onContactSelected(MouseEvent mouseEvent) {
                ListCell<GroceryItem> clickedCell = (ListCell<GroceryItem>) mouseEvent.getSource();
                GroceryItem selectedItem = clickedCell.getItem();
                if (selectedItem != null) selectGroceryItem(selectedItem);
            }

            /**
             * Updates the item in the cell by setting the text to the contact's full name.
             * @param groceryItem The contact to update the cell with.
             * @param empty Whether the cell is empty.
             */

            protected void updateItem(GroceryItem groceryItem, boolean empty) {
                super.updateItem(groceryItem, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || groceryItem == null || groceryItem.getName() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onContactSelected);
                } else {
                    setText(groceryItem.getAmount().toString() + "x " + groceryItem.getName());
                }
            }
        };
    }

    @FXML
    private void onDelete() {
        // Get the selected contact from the list view
        GroceryItem selectedItem = groceryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            groceryListDAO.deleteGroceryItem(selectedItem.getID());
            syncGroceryList();
        }
    }

    @FXML
    private void onAdd() {
        final String DEFAULT_NAME = "Name";
        final Integer DEFAULT_AMOUNT = 0;
        final String DEFAULT_NOTES = "";
        GroceryItem newItem = new GroceryItem(UserAccountDAO.currentAccount.getID(), DEFAULT_NAME, DEFAULT_AMOUNT, DEFAULT_NOTES);

        groceryListDAO.insertGroceryItem(newItem);
        syncGroceryList();
        selectGroceryItem(newItem);
        itemNameField.requestFocus();
    }

    @FXML
    private void onCancel() {
        GroceryItem selectedItem = groceryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectGroceryItem(selectedItem);
        }
    }

    @FXML
    public void initialize() {
        groceryListView.setCellFactory(this::renderCell);
        syncGroceryList();

        groceryListView.getSelectionModel().selectFirst();
        GroceryItem firstContact = groceryListView.getSelectionModel().getSelectedItem();
        if (firstContact != null) {
            selectGroceryItem(firstContact);
        }
    }

    private void syncGroceryList() {
        groceryListView.getItems().clear();
        List<GroceryItem> groceries = groceryListDAO.getByID(UserAccountDAO.currentAccount.getID());
        boolean hasList = !groceries.isEmpty();
        if (hasList) {
            groceryListView.getItems().addAll(groceries);
        }
        // Show / hide based on whether there are contacts
        //contactContainer.setVisible(hasList);
    }
}
