package controllers;

import com.example.definitelynotrobots.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
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
        itemAmountField.setText(groceryItem.getAmountToString() + groceryItem.getAmountType());
        itemNotesField.setText(groceryItem.getNotes());
        foodTypeField.setValue(groceryItem.getFoodType());
    }

    @FXML
    private void onEditConfirm() {
        errorText.setText("");
        boolean amountIsCorrect = itemAmountField.getText().matches("[0-9.]+ ?[a-zA-Z]*");
        if(!amountIsCorrect) {
            errorText.setText("Amount field must be an integer with optional unit");
            return;
        }
        Double amount = Double.parseDouble(itemAmountField.getText().replaceAll("[a-zA-Z]*", "").replaceAll(" +", ""));
        String amountType = itemAmountField.getText().replaceAll("[0-9.]+", "").replaceAll(" +", "");
        if(amountType.isEmpty()) amountType = "x";

        GroceryItem selectedItem = groceryListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;


        selectedItem.setName(itemNameField.getText());
        selectedItem.setAmount(amount);
        selectedItem.setAmountType(amountType);
        selectedItem.setNotes(itemNotesField.getText());
        selectedItem.setFoodType(foodTypeField.getValue());

        groceryListDAO.updateItem(selectedItem);
        syncGroceryList();
        selectGroceryItem(selectedItem);
    }

    private ListCell<GroceryItem> renderCell(ListView<GroceryItem> contactListView) {
        return new ListCell<>() {
            private void onContactSelected(MouseEvent mouseEvent) {
                ListCell<GroceryItem> clickedCell = (ListCell<GroceryItem>) mouseEvent.getSource();
                GroceryItem selectedItem = clickedCell.getItem();
                if (selectedItem != null) selectGroceryItem(selectedItem);
            }

            protected void updateItem(GroceryItem groceryItem, boolean empty) {
                super.updateItem(groceryItem, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || groceryItem == null || groceryItem.getName() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onContactSelected);
                } else {
                    setText(groceryItem.getAmountToString() + groceryItem.getAmountType() + " " + groceryItem.getName());
                }
            }
        };
    }

    @FXML
    private void onDelete() {
        GroceryItem selectedItem = groceryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            groceryListDAO.deleteItem(selectedItem.getID());
            syncGroceryList();
        }
    }

    @FXML
    private void onAdd() {
        final String DEFAULT_NAME = "Name";
        final Double DEFAULT_AMOUNT = 0d;
        final String DEFAULT_NOTES = "";
        final String DEFAULT_AMOUNT_TYPE = "x";
        final FoodTypesEnum DEFAULT_FOOD_TYPE = FoodTypesEnum.Oil;
        GroceryItem newItem = new GroceryItem(UserAccountDAO.currentAccount.getID(), DEFAULT_NAME, DEFAULT_AMOUNT, DEFAULT_AMOUNT_TYPE, DEFAULT_FOOD_TYPE, DEFAULT_NOTES);

        groceryListDAO.addItem(newItem);
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
        GroceryItem firstItem = groceryListView.getSelectionModel().getSelectedItem();
        if (firstItem != null) {
            selectGroceryItem(firstItem);
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

    public void onPushToPantry() {
        GroceryItem selectedItem = groceryListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        PantryDAO pantryDAO = new PantryDAO();
        PantryItem pantryItem = new PantryItem(
                selectedItem.getUserID(),
                selectedItem.getName(),
                selectedItem.getAmount(),
                selectedItem.getAmountType(),
                selectedItem.getFoodType(),
                selectedItem.getNotes()
        );
        pantryDAO.insertItem(pantryItem);
        groceryListDAO.deleteItem(selectedItem.getID());
        syncGroceryList();
    }

    public void goToPantryView() throws IOException {
        Stage stage = (Stage) errorText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantry-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    public void EnterToSave(javafx.scene.input.KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) onEditConfirm();
    }

    public void EnterToSelect(javafx.scene.input.KeyEvent event) {
        if(!event.getCode().equals(KeyCode.ENTER)) return;
        selectGroceryItem(groceryListView.getFocusModel().getFocusedItem());
    }
}
