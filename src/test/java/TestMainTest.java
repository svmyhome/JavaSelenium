import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AssertTimeout chapter 25
 */
//TODO @extendWith()

public class TestMainTest {

    @BeforeAll
    static void beforeAllMethod() {
        System.out.println("All class");
    }

    @BeforeEach
    void setUp() {
        System.out.println("each method");
    }

    @Test
    @DisplayName("Verify that values are equal")
    void assertTrueMethod1() {
        assertTrue(2 == 2, "Value are not true!");
    }

    @Test
    @DisplayName("Verify that values are not equal")
    void assertFalseMethod2() {
        assertFalse(2 == 2, "Value are not false!");
    }

    @Test
    @DisplayName("Verify that values are Null negative")
    void assertNullMethod3() {
        assertNull(2, "Value are not null!");
    }

    @Test
    @DisplayName("Verify that values are Null positive")
    void assertNullMethod31() {
        assertNull(null, "Value are not null!");
    }

    @Test
    @DisplayName("Verify that values are Not Null positive")
    void assertNotNullMethod4() {
        assertNotNull(2, "Value are null!");
    }

    @Test
    @DisplayName("Verify that values are equal negative")
    void assertEqualsMethod5() {
        assertEquals(20, 10 + 5, "Value are not equals!");
    }

    @Test
    @DisplayName("Verify that values are equal positive")
    void assertNotEqualsMethod6() {
        assertNotEquals(20, 10 + 5, "Value are equals!");
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)    //  Проверка по таимауту
    @DisplayName("Verify that the test time does not exceed")
    void assertArraysMethod7True() {
        int[] expected = {1, 3, 2, 6};
        int[] actual = {1, 3, 2, 6};
        assertArrayEquals(expected, actual, "Arrays are not equals");
    }

    @RepeatedTest(3)
    @DisplayName("Verify that array values are equal positive")
    void assertArraysMethod7False() {
        int[] expected = {1, 3, 2, 6};
        int[] actual = {1, 2, 2, 6};
        assertArrayEquals(expected, actual, "Arrays are not equals");
    }

    @Test
    @DisplayName("Verify that the test time does not exceed Old JUNIT4")
    void assertArraysTimeoutMethod() {
        int[] expected = {1, 3, 2, 6};
        int[] actual = {1, 2, 2, 6};
        //assertArrayEquals(expected, actual, "Arrays are not equals");
        assertTimeout(Duration.ofMillis(1), () -> Thread.sleep(10000), () -> "Testing prductivity");
    }

    @AfterEach
    void tearDown() {
        System.out.println("each method");
    }

    @AfterAll
    static void afterAllMethod() {
        System.out.println("All class");
    }
}
