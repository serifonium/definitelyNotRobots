import com.example.definitelynotrobots.FitnessGoal;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitnessGoalTest {
    private FitnessGoal fitnessGoal;

    @BeforeEach
    void createFitnessGoal() {
        fitnessGoal = new FitnessGoal(1, 1, 300.0, 400.0, 250.0, 50.0);
    }

    @Test
    public void testGetID() { assertEquals(1, fitnessGoal.getID()); }
    @Test
    public void testGetUserID() { assertEquals(1, fitnessGoal.getUserID()); }
    @Test
    public void testGetCalories() { assertEquals(300.0, fitnessGoal.getCalories()); }
    @Test
    public void testGetCarbs() { assertEquals(400.0, fitnessGoal.getCarbs()); }
    @Test
    public void testGetFats() { assertEquals(250.0, fitnessGoal.getFats()); }
    @Test
    public void testGetProtein() { assertEquals(50.0, fitnessGoal.getProtein()); }

    @Test
    public void testSetID() { fitnessGoal.setID(2); assertEquals(2, fitnessGoal.getID()); }
    @Test
    public void testSetUserID() { fitnessGoal.setUserID(2); assertEquals(2, fitnessGoal.getUserID()); }
    @Test
    public void testSetCalories() { fitnessGoal.setCalories(299.6); assertEquals(299.6, fitnessGoal.getCalories()); }
    @Test
    public void testSetCarbs() { fitnessGoal.setCarbs(425.2); assertEquals(425.2, fitnessGoal.getCarbs()); }
    @Test
    public void testSetFats() { fitnessGoal.setFats(200.0); assertEquals(200.0, fitnessGoal.getFats()); }
    @Test
    public void testSetProtein() { fitnessGoal.setProtein(100.0); assertEquals(100.0, fitnessGoal.getProtein()); }
}
