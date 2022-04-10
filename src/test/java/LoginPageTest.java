import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginPageTest {
    public static WebDriver driver;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void errorEmptyLoginPassword() {
        loginPage.LoginWithInvalidCreds("","");
        String errText = loginPage.getErrorText();
        Assertions.assertEquals("Incorrect username or password.", errText);
    }

    @Test
    public void errorEmptyPassword() {
        loginPage.LoginWithInvalidCreds("ssdsdas","");
        String errText = loginPage.getErrorText();
        Assertions.assertEquals("Incorrect username or password.", errText);
    }

    @Test
    public void errorEmptyLogin() {
        loginPage.LoginWithInvalidCreds("","sdsdsdsd");
        String errText = loginPage.getErrorText();
        Assertions.assertEquals("Incorrect username or password.", errText);
    }

    @Test
    public void goToAnAccount() {
        SignUpPage signUpPage = loginPage.createAnAccount();
        String heading = signUpPage.getAdventure();
        Assertions.assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}