import com.example.definitelynotrobots.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountTest {
    private UserAccount userAccount;

    @BeforeEach
    public void setUp() {
        userAccount = new UserAccount("John", "myP4ssword$%^", "", "");
    }

    @Test
    public void testGetID() {
        userAccount.setID(1);
        assertEquals(1, userAccount.getID());
    }
    @Test
    public void testGetUsername() {
        assertEquals("John", userAccount.getUsername());
    }
    @Test
    public void testSetUsername() {
        userAccount.setUsername("Johnathan");
        assertEquals("Johnathan", userAccount.getUsername());
    }
    @Test
    public void testGetPassword() {
        assertEquals("myP4ssword$%^", userAccount.getPassword());
    }
    @Test
    public void testSetPassword() {
        userAccount.setPassword("myN3wP4ssword$%#^");
        assertEquals("myN3wP4ssword$%#^", userAccount.getPassword());
    }
}
