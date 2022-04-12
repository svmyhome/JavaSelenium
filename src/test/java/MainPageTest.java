import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//TODO refactoring path "/libs/chromedriver"
public class MainPageTest {
    private static WebDriver driver;
    private static MainPage mainpage;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        mainpage = new MainPage(driver);
    }

    @BeforeEach
    void setUpEach() {
        driver.get("https://github.com/");
    }


    @Test
    void goTologinPage() {
        LoginPage loginPage = mainpage.clickSignIn();
        String heading = loginPage.getHeadingText();
        assertEquals("Sign in to GitHub", heading);
    }

    @Test
    void doToclickSignUp() {
        SignUpPage signUpPage = mainpage.clickSignUp();
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @Test
    void doRegister() {
        SignUpPage signUpPage = mainpage.register("asdg@mail.ru");
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
