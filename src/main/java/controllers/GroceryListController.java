package controllers;

import com.example.definitelynotrobots.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    public ChoiceBox<FoodTypesEnum> foodTypeField;

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
        if (selectedItem == null) return;


        selectedItem.setName(itemNameField.getText());
        selectedItem.setAmount(Integer.parseInt(itemAmountField.getText()));
        selectedItem.setNotes(itemNotesField.getText());
        selectedItem.setFoodType(foodTypeField.getValue());

        groceryListDAO.updateGroceryItem(selectedItem);
        syncGroceryList();
        selectGroceryItem(selectedItem);
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
                    setText(groceryItem.getAmount().toString() + groceryItem.getAmountType() + " " + groceryItem.getName());
                }
            }
        };
    }

    @FXML
    private void onDelete() {
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
        final String DEFAULT_AMOUNT_TYPE = "x";
        final FoodTypesEnum DEFAULT_FOOD_TYPE = FoodTypesEnum.Baking;
        GroceryItem newItem = new GroceryItem(UserAccountDAO.currentAccount.getID(), DEFAULT_NAME, DEFAULT_AMOUNT, DEFAULT_AMOUNT_TYPE, DEFAULT_FOOD_TYPE, DEFAULT_NOTES);

        groceryListDAO.insertGroceryItem(newItem);
        syncGroceryList();
        selectGroceryItem(groceryListDAO.getByUserID(UserAccountDAO.currentAccount.getID()).getLast());
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
        foodTypeField.getItems().setAll(FoodTypesEnum.values());
        foodTypeField.setValue(FoodTypesEnum.Oil);

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
        List<GroceryItem> groceries = groceryListDAO.getByUserID(UserAccountDAO.currentAccount.getID());
        boolean hasList = !groceries.isEmpty();
        if (hasList) {
            groceryListView.getItems().addAll(groceries);
        }
    }

    public void goToPantryView() throws IOException {
        Stage stage = (Stage) errorText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantry-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
