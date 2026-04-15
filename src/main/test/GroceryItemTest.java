import com.example.definitelynotrobots.FoodTypesEnum;
import com.example.definitelynotrobots.GroceryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryItemTest {
    private GroceryItem groceryItem;

    @BeforeEach
    public void setUp() {
        groceryItem = new GroceryItem(1, 3, "Bread", 2, "x", FoodTypesEnum.Baking, "Get before Friday");
    }

    @Test
    public void testGetID() { assertEquals(1, groceryItem.getID()); }
    @Test
    public void testGetUserID() { assertEquals(3, groceryItem.getUserID()); }
    @Test
    public void testGetName() { assertEquals("Bread", groceryItem.getName()); }
    @Test
    public void testGetAmount() { assertEquals(2, groceryItem.getAmount()); }
    @Test
    public void testGetAmountType() { assertEquals("x", groceryItem.getAmountType()); }
    @Test
    public void testGetFoodType() { assertEquals(FoodTypesEnum.Baking, groceryItem.getFoodType()); }
    @Test
    public void testGetNotes() { assertEquals("Get before Friday", groceryItem.getNotes()); }

    @Test
    public void testSetID() { groceryItem.setID(6); assertEquals(6, groceryItem.getID()); }
    @Test
    public void testSetUserID() { groceryItem.setUserID(4); assertEquals(4, groceryItem.getUserID()); }
    @Test
    public void testSetName() { groceryItem.setName("Steak"); assertEquals("Steak", groceryItem.getName()); }
    @Test
    public void testSetAmount() { groceryItem.setAmount(400); assertEquals(400, groceryItem.getAmount()); }
    @Test
    public void testSetAmountType() { groceryItem.setAmountType("g"); assertEquals("g", groceryItem.getAmountType()); }
    @Test
    public void testSetFoodType() { groceryItem.setFoodType(FoodTypesEnum.Meat); assertEquals(FoodTypesEnum.Meat, groceryItem.getFoodType()); }
    @Test
    public void testSetNotes() { groceryItem.setNotes("Get before Saturday"); assertEquals("Get before Saturday", groceryItem.getNotes()); }
}
