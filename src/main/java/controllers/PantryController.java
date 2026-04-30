package controllers;

import com.example.definitelynotrobots.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PantryController {
    @FXML
    private ListView<PantryItem> pantryListView;
    private final PantryDAO pantryDAO = new PantryDAO();

    @FXML
    public Label errorText;
    public TextField itemNameField;
    public TextField itemAmountField;
    public TextArea itemNotesField;
    public ChoiceBox<FoodTypesEnum> foodTypeField;

    @FXML
    public Button backButton;

    @FXML
    private void selectPantryItem(PantryItem pantryItem) {
        pantryListView.getSelectionModel().select(pantryItem);
        itemNameField.setText(pantryItem.getName());
        itemAmountField.setText(pantryItem.getAmountToString() + pantryItem.getAmountType());
        itemNotesField.setText(pantryItem.getNotes());
        foodTypeField.setValue(pantryItem.getFoodType());
    }
    private void syncPantry() {
        pantryListView.getItems().clear();
        List<PantryItem> groceries = pantryDAO.getByUserID(UserAccountDAO.currentAccount.getID());
        boolean hasList = !groceries.isEmpty();
        if (hasList) {
            pantryListView.getItems().addAll(groceries);
        }
    }
    @FXML
    public void initialize() {
        foodTypeField.getItems().setAll(FoodTypesEnum.values());
        foodTypeField.setValue(FoodTypesEnum.Oil);

        pantryListView.setCellFactory(this::renderCell);
        syncPantry();

        pantryListView.getSelectionModel().selectFirst();
        PantryItem firstContact = pantryListView.getSelectionModel().getSelectedItem();
        if (firstContact != null) {
            selectPantryItem(firstContact);
        }

        ScaleMainView();
    }
    public void goToHomeView() throws IOException {
        Stage stage = (Stage) errorText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    private ListCell<PantryItem> renderCell(ListView<PantryItem> contactListView) {
        return new ListCell<>() {
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<PantryItem> clickedCell = (ListCell<PantryItem>) mouseEvent.getSource();
                PantryItem selectedItem = clickedCell.getItem();
                if (selectedItem != null) selectPantryItem(selectedItem);
            }

            protected void updateItem(PantryItem pantryItem, boolean empty) {
                super.updateItem(pantryItem, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || pantryItem == null || pantryItem.getName() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(pantryItem.getAmountToString() + pantryItem.getAmountType() + " " + pantryItem.getName());
                }
            }
        };
    }

    public void EnterToSelect(javafx.scene.input.KeyEvent event) {
        if(!event.getCode().equals(KeyCode.ENTER)) return;
        selectPantryItem(pantryListView.getFocusModel().getFocusedItem());
    }
    public void EnterToSave(javafx.scene.input.KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) onEditConfirm();
    }
    public void onAdd() {
        final String DEFAULT_NAME = "Name";
        final Double DEFAULT_AMOUNT = 0d;
        final String DEFAULT_NOTES = "";
        final String DEFAULT_AMOUNT_TYPE = "x";
        final FoodTypesEnum DEFAULT_FOOD_TYPE = FoodTypesEnum.Oil;
        PantryItem newItem = new PantryItem(UserAccountDAO.currentAccount.getID(), DEFAULT_NAME, DEFAULT_AMOUNT, DEFAULT_AMOUNT_TYPE, DEFAULT_FOOD_TYPE, DEFAULT_NOTES);

        pantryDAO.addItem(newItem);
        syncPantry();
        selectPantryItem(pantryDAO.getByUserID(UserAccountDAO.currentAccount.getID()).getLast());
        itemNameField.requestFocus();
    }
    public void onEditConfirm() {
        errorText.setText("");
        boolean amountIsCorrect = itemAmountField.getText().matches("[0-9.]+ ?[a-zA-Z]*");
        if(!amountIsCorrect) {
            errorText.setText("Amount field must be an integer with optional unit");
            return;
        }
        Double amount = Double.parseDouble(itemAmountField.getText().replaceAll("[a-zA-Z]*", "").replaceAll(" +", ""));
        String amountType = itemAmountField.getText().replaceAll("[0-9.]+", "").replaceAll(" +", "");
        if(amountType.isEmpty()) amountType = "x";

        PantryItem selectedItem = pantryListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;


        selectedItem.setName(itemNameField.getText());
        selectedItem.setAmount(amount);
        selectedItem.setAmountType(amountType);
        selectedItem.setNotes(itemNotesField.getText());
        selectedItem.setFoodType(foodTypeField.getValue());

        pantryDAO.updateItem(selectedItem);
        syncPantry();
        selectPantryItem(selectedItem);
    }
    public void onCancel() {
        PantryItem selectedItem = pantryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectPantryItem(selectedItem);
        }
    }
    public void onDelete() {
        PantryItem selectedItem = pantryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            pantryDAO.deleteItem(selectedItem.getID());
            syncPantry();
        }
    }
    public void markUnavailable() {
        String ACTIVE_TEXT = "( ✕ ) Mark As Unavailable";
        String INACTIVE_TEXT = "( ✓ ) Mark As Available";

        PantryItem selectedItem = pantryListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        GroceryListDAO groceryListDAO = new GroceryListDAO();
        GroceryItem groceryItem = new GroceryItem(
                selectedItem.getUserID(),
                selectedItem.getName(),
                selectedItem.getAmount(),
                selectedItem.getAmountType(),
                selectedItem.getFoodType(),
                selectedItem.getNotes()
        );
        groceryListDAO.insertItem(groceryItem);
        pantryDAO.deleteItem(selectedItem.getID());
        syncPantry();

        System.out.print(groceryItem);
    }

    public void goToPage(String fxmlName) throws IOException {
        Stage stage = (Stage) errorText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlName+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public HBox PantryRoot; //This Hbox is the main parent.


    private void ScaleMainView() {
        PantryRoot.setScaleX(1.6); //Scales root parent by 1.6
        PantryRoot.setScaleY(1.6);
    }

    public void goToGroceryList() throws IOException { goToPage("grocery-view"); }
    public void goToAIPage() throws IOException { goToPage("ai-view"); }
    public void goToPreferences() throws IOException { goToPage("preferences-view"); }
    public void goToFitnessTargets() throws IOException { goToPage("fitness-targets-view"); }
    public void goToRecipeView() throws IOException { goToPage("recipe-view"); }
}

