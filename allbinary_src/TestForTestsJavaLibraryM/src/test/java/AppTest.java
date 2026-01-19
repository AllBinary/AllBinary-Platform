import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    @DisplayName("Simple test to verify that 1 + 1 equals 2")
    void testAddition() {
        // Assertion to check if the result of 1 + 1 is equal to 2
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    @DisplayName("Test string equality")
    void testStringEquality() {
        String expected = "Hello World!";
        String actual = "Hello World!";
        // Assertion to check if two strings are equal
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test boolean assertions")
    void testBooleanAssertions() {
        // Assertion to check if a condition is true
        Assertions.assertTrue(true);
        // Assertion to check if a condition is false
        Assertions.assertFalse(false);
    }
}
