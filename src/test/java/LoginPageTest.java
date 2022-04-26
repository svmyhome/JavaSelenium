import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
//TODO is needed to add in all methods @Displayname
//TODO must be added to some methods @RepeatedTest
public class LoginPageTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    private static String pathChromedriver = "libs/chromedriver.exe";

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test //TODO refactoring to parametrized test
    @DisplayName("Verify that when login and password is empty the error is exist")
    @ParameterizedTest(name = "The value of {index} subtest ==> the rank of ''{0}'' is {2}")
    @CsvSource(value = {", , 1", "QWERTY, qwerty1, 2", "123456, 123456, 3"})
    void errorEmptyLoginPassword(String username1, String password1, int rank) {
        loginPage.LoginWithInvalidCreds(username1, password1);
        String errText = loginPage.getErrorText();
        assertEquals("Incorrect username or password.", errText);
    }

    @Test
    void errorEmptyPassword() {
        loginPage.LoginWithInvalidCreds("ssdsdas", "");
        String errText = loginPage.getErrorText();
        assertEquals("Incorrect username or password.", errText);
    }

    @Test
    @Step("login is {username}")
    void errorEmptyLogin() {
        loginPage.LoginWithInvalidCreds("", "sdsdsdsd");
        String errText = loginPage.getErrorText();
        assertEquals("Incorrect username or password.", errText);
    }

    @Test
    void colorButtonTes() {
        String colorButton = loginPage.getColorButton();
        assertEquals("rgba(45, 164, 78, 1)", colorButton);
    }

    @Test
    void goToAnAccount() {
        SignUpPage signUpPage = loginPage.createAnAccount();
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" +
                "Let’s begin the adventure", heading);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
