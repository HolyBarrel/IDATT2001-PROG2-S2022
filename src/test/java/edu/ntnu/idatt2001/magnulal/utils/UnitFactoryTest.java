package edu.ntnu.idatt2001.magnulal.utils;

import edu.ntnu.idatt2001.magnulal.utils.exceptions.BlankStringException;
import edu.ntnu.idatt2001.magnulal.utils.exceptions.NegativeIntegerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UnitFactoryTest {
    @Nested
    @DisplayName("Negative tests for the class 'UnitFactory' throws exceptions")
    public class MethodsThrowsExceptions {
        @Test
        @DisplayName("creatListOfUnits throws 'BlankStringException'")
        public void createListOfUnitsThrowsBlankStringException() {
            try {
                UnitFactory.createListOfUnits(UnitTypes.COMMANDER, "", 200, 2);
            } catch (BlankStringException b) {
                assertEquals("A unit's name cannot be inputted as a blank string,  please try again.",
                        b.getMessage());
            }
        }
        @Test
        @DisplayName("creatListOfUnits throws 'NegativeIntegerException'")
        public void createListOfUnitsThrowsNegativeIntExpt() {
            try {
                UnitFactory.createListOfUnits(UnitTypes.COMMANDER, "Footman", 200, -1);
            } catch (NegativeIntegerException n) {
                assertEquals("Cannot create a list of a negative amount of units. For input: -1",
                        n.getMessage());
            }
        }
    }
}

