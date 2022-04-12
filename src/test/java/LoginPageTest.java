import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void errorEmptyLoginPassword() {
        loginPage.LoginWithInvalidCreds("", "");
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
    void errorEmptyLogin() {
        loginPage.LoginWithInvalidCreds("", "sdsdsdsd");
        String errText = loginPage.getErrorText();
        assertEquals("Incorrect username or password.", errText);
    }

    @Test
    void goToAnAccount() {
        SignUpPage signUpPage = loginPage.createAnAccount();
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
