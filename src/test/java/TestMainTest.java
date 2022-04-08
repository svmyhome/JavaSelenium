import org.junit.jupiter.api.*;

public class TestMainTest {

    @BeforeAll
    public static void beforeAllMethod() {
        System.out.println("All class");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("each method");
    }

    @Test
    public void assertTrueMethod1() {
        Assertions.assertTrue(2 == 2, "Value are not true!");
    }

    @Test
    public void assertFalseMethod2() {
        Assertions.assertFalse(2 == 2, "Value are not false!");
    }
    @Test
    public void assertNullMethod3() {
        Assertions.assertNull(2, "Value are not null!");
    }
    @Test
    public void assertNotNullMethod4() {
        Assertions.assertNotNull(2,"Value are null!");
    }
    @Test
    public void assertEqualsMethod5() {
        Assertions.assertEquals(20, 10 + 5, "Value are not equals!");
    }
    @Test
    public void assertNotEqualsMethod6() {
        Assertions.assertNotEquals(20, 10 + 5, "Value are equals!");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("each method");
    }
    @AfterAll
    public static void afterAllMethod() {
        System.out.println("All class");
    }
}
