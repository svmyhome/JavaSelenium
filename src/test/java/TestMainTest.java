import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AssertTimeout chapter 25
 */
//TODO @extendWith()

public class TestMainTest {

    public static WebDriver driver;
    public static LoginPage loginPage;
    private static String pathChromedriver = "libs/chromedriver.exe";

    @BeforeAll
    static void beforeAllMethod() {
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);
        System.out.println("All class");
    }



    @BeforeEach
    void setUp() {
        System.out.println("each method");
    }

    @Test
    @DisplayName("Verify that button is loacated")
    void buttonLocated(){
        WebElement butt = driver.findElement(By.xpath("//input[@name='commit']"));
        System.out.println("coordinats Left Up corner" + butt.getLocation()); //coordinats Left Up corner
        System.out.println("Length and wight in pixels" + butt.getSize()); //Length and wight in pixels
        System.out.println("Coodrinates and size elements" + butt.getRect()); //Coodrinates and size elements
        System.out.println("Get CSS Value" + butt.getCssValue("background-color")); //Get CSS Value
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

    @Test
    @DisplayName("Verify that two arrays are equals 1")
    void twoArraysEquals1() {
        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = new ArrayList<>(expected);
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("Verify that two arrays are equals 2")
    void twoArraysEquals2() {
        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = Arrays.asList("one two three".split(" "));
        assertIterableEquals(expected, actual);
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
