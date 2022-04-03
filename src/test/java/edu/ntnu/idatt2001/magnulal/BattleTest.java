package edu.ntnu.idatt2001.magnulal;
import edu.ntnu.idatt2001.magnulal.model.simulatorclasses.Army;
import edu.ntnu.idatt2001.magnulal.model.simulatorclasses.Battle;
import edu.ntnu.idatt2001.magnulal.unitclasses.CavalryUnit;
import edu.ntnu.idatt2001.magnulal.unitclasses.InfantryUnit;
import edu.ntnu.idatt2001.magnulal.unitclasses.RangedUnit;
import edu.ntnu.idatt2001.magnulal.unitclasses.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    @Nested
    @DisplayName("Constructor tests for the class 'Battle'")
    class testingConstructor {
        @Nested
        @DisplayName("Positive tests for the constructor")
        class positivelyTestingConstructor {
            @Test
            @DisplayName("Checking that the constructor of Battle class works with correct input")
            public void checkConstructorOfBattleClass() {
                try {
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(new CavalryUnit("GrandLancer", 95, 12, 10));
                    unitsTestArray.add(new CavalryUnit("ApexAxeRider", 65, 15, 10));
                    unitsTestArray.add(new RangedUnit("CrossbowMan", 30, 10, 2));
                    ArrayList<Unit> unitsTestArray2 = new ArrayList<>();
                    unitsTestArray2.add(new CavalryUnit("GrandWhipper", 75, 16, 10));
                    unitsTestArray2.add(new CavalryUnit("FastLancer", 85, 10, 10));
                    unitsTestArray2.add(new InfantryUnit("Footman", 40, 10, 5));
                    Army testArmy = new Army("Alliance", unitsTestArray);
                    Army testArmy2 = new Army("Britforce", unitsTestArray2);
                    assertDoesNotThrow(() -> new Battle(testArmy, testArmy2));
                } catch (Exception e) {
                    fail(e.getMessage());
                }
            }
        }
        @Nested
        @DisplayName("Negative tests for the constructor")
        class negativelyTestingConstructor {
            @Test
            @DisplayName("Checking that the constructor of Battle class throws with wrong input first")
            public void checkConstructorOfBattleClassWrongParameterFirst() {
                try {
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(new CavalryUnit("GrandLancer", 95, 12, 10));
                    unitsTestArray.add(new CavalryUnit("ApexAxeRider", 65, 15, 10));
                    unitsTestArray.add(new RangedUnit("CrossbowMan", 30, 10, 2));
                    Army testArmy = new Army("Alliance");
                    Army testArmy2 = new Army("Britforce", unitsTestArray);
                    new Battle(testArmy, testArmy2);
                } catch (IllegalArgumentException e) {
                    assertEquals("The first army inputted did not have any units, please try again with " +
                            "two armies containing units.", e.getMessage());
                }
            }

            @Test
            @DisplayName("Checking that the constructor of Battle class throws with wrong input second")
            public void checkConstructorOfBattleClassWrongParameterSecond() {
                try {
                    ArrayList<Unit> unitsTestArray = new ArrayList<>();
                    unitsTestArray.add(new CavalryUnit("GrandLancer", 95, 12, 10));
                    unitsTestArray.add(new CavalryUnit("ApexAxeRider", 65, 15, 10));
                    unitsTestArray.add(new RangedUnit("CrossbowMan", 30, 10, 2));
                    Army testArmy = new Army("Alliance", unitsTestArray);
                    Army testArmy2 = new Army("Britforce");
                    new Battle(testArmy, testArmy2);
                } catch (IllegalArgumentException e) {
                    assertEquals("The second army inputted did not have any units, please try again with " +
                            "two armies containing units.", e.getMessage());
                }
            }
        }
    }
    @Nested
    @DisplayName("Simulate-method test for the class 'Battle'")
    class testingSimulate {
        @Test
        @DisplayName("Checking that the simulate-method returns correctly")
        public void checkSimulateMethodOfBattleClass() {
            ArrayList<Unit> unitsTestArray = new ArrayList<>();
            unitsTestArray.add(new CavalryUnit("GrandLancer", 95, 12, 10));
            unitsTestArray.add(new CavalryUnit("ApexAxeRider", 65, 15, 10));
            unitsTestArray.add(new RangedUnit("CrossbowMan", 30, 10, 2));
            ArrayList<Unit> unitsTestArray2 = new ArrayList<>();
            unitsTestArray2.add(new CavalryUnit("GrandWhipper", 75, 16, 10));
            unitsTestArray2.add(new CavalryUnit("FastLancer", 85, 10, 10));
            unitsTestArray2.add(new InfantryUnit("Footman", 40, 10, 5));
            Army testArmy = new Army("Alliance", unitsTestArray);
            Army testArmy2 = new Army("Britforce", unitsTestArray2);
            Army testVictoriousArmy = new Battle(testArmy, testArmy2).simulate();
            assertTrue(testVictoriousArmy.equals(testArmy) || testVictoriousArmy.equals(testArmy2));
        }
    }
}
