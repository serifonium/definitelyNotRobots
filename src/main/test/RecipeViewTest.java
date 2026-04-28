import com.example.definitelynotrobots.UserAccount;
import com.example.definitelynotrobots.Recipe;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeViewTest{
    private Recipe recipe;
    private UserAccount userAccount;

    @BeforeEach
    public void setUp(){
        recipe = new Recipe(1, null, "Butter Chicken", 15, 30, 4, false, "chicken, yoghurt, tumeric, garam masala, chili powder, cumin, rice, ginger, garlic, butter", "1. Combine ingredients in bowl and marinate chicken, 2. Cook chicken, 3. Cook rice, 4.Serve with yoghurt on side and naan");
        userAccount = new UserAccount(1, "John", "myP4ssword$%^", "John", "Smith");
    }
    @Test
    public void testGetRecipeTitle() {
        assertEquals("Butter Chicken", recipe.getRecipeTitle());
    }
    @Test
    public void testGetIsSaved() {
        assertFalse(recipe.getIsSaved());
    }
    @Test
    public void testSetIsSaved() {
        recipe.setIsSaved(true);
        assertTrue(recipe.getIsSaved());
    }
    @Test
    public void testImageNotFound(){
        assertNull(recipe.getRecipeImage());
    }
    @Test
    public void testGetPrepTime() {
        assertEquals(15, recipe.getPrepTime());
    }
    @Test
    public void testGetCookTime() {
        assertEquals(30, recipe.getCookTime());
    }
    @Test
    public void testGetServings(){assertEquals(4, recipe.getServings());}
    @Test
    public void testSetServings() {
        recipe.setServings(6);
        assertEquals(6, recipe.getServings());
    }
    @Test
    public void testGetIngredients() {
        assertEquals("chicken, yoghurt, tumeric, garam masala, chili powder, cumin, rice, ginger, garlic, butter", recipe.getIngredients());
    }
    @Test
    public void testGetMethod(){assertEquals("1. Combine ingredients in bowl and marinate chicken, 2. Cook chicken, 3. Cook rice, 4.Serve with yoghurt on side and naan", recipe.getMethod());}
}
