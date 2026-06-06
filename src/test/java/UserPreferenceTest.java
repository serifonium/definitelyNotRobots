import com.example.definitelynotrobots.PreferenceTypeEnum;
import com.example.definitelynotrobots.UserPreference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserPreferenceTest {
    private UserPreference userPreference;

    @BeforeEach
    public void setUp() {
        userPreference = new UserPreference(1, 2,"Gluten", PreferenceTypeEnum.Intolerant);
    }

    @Test
    public void testGetID() { assertEquals(1, userPreference.getID()); }
    @Test
    public void testGetUserID() { assertEquals(2, userPreference.getUserID()); }
    @Test
    public void testGetContent() { assertEquals("Gluten", userPreference.getContent()); }
    @Test
    public void testGetPreferenceType() { assertEquals(PreferenceTypeEnum.Intolerant, userPreference.getPreferenceType()); }

    @Test
    public void testSetID() { userPreference.setID(7); assertEquals(7, userPreference.getID()); }
    @Test
    public void testSetUserID() { userPreference.setUserID(4); assertEquals(4, userPreference.getUserID()); }
    @Test
    public void testSetContent() { userPreference.setContent("Dairy"); assertEquals("Dairy", userPreference.getContent()); }
    @Test
    public void testSetPreferenceType() { userPreference.setPreferenceType(PreferenceTypeEnum.Include); assertEquals(PreferenceTypeEnum.Include, userPreference.getPreferenceType()); }
}