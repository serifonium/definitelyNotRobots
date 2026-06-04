import com.example.definitelynotrobots.FoodTypesEnum;
import com.example.definitelynotrobots.GroceryItem;
import com.example.definitelynotrobots.PantryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PantryItemTest {
    private PantryItem pantryItem;

    @BeforeEach
    public void setUp() {
        pantryItem = new PantryItem(1, 3, "Bread", 2d, "x", FoodTypesEnum.Baking, "Get before Friday");
    }

    @Test
    public void testGetID() { assertEquals(1, pantryItem.getID()); }
    @Test
    public void testGetUserID() { assertEquals(3, pantryItem.getUserID()); }
    @Test
    public void testGetName() { assertEquals("Bread", pantryItem.getName()); }
    @Test
    public void testGetAmount() { assertEquals(2d, pantryItem.getAmount()); }
    @Test
    public void testGetAmountType() { assertEquals("x", pantryItem.getAmountType()); }
    @Test
    public void testGetFoodType() { assertEquals(FoodTypesEnum.Baking, pantryItem.getFoodType()); }
    @Test
    public void testGetNotes() { assertEquals("Get before Friday", pantryItem.getNotes()); }

    @Test
    public void testSetID() { pantryItem.setID(6); assertEquals(6, pantryItem.getID()); }
    @Test
    public void testSetUserID() { pantryItem.setUserID(4); assertEquals(4, pantryItem.getUserID()); }
    @Test
    public void testSetName() { pantryItem.setName("Steak"); assertEquals("Steak", pantryItem.getName()); }
    @Test
    public void testSetAmount() { pantryItem.setAmount(420.5); assertEquals(420.5, pantryItem.getAmount()); }
    @Test
    public void testSetAmountType() { pantryItem.setAmountType("g"); assertEquals("g", pantryItem.getAmountType()); }
    @Test
    public void testSetFoodType() { pantryItem.setFoodType(FoodTypesEnum.Meat); assertEquals(FoodTypesEnum.Meat, pantryItem.getFoodType()); }
    @Test
    public void testSetNotes() { pantryItem.setNotes("Get before Saturday"); assertEquals("Get before Saturday", pantryItem.getNotes()); }
}
