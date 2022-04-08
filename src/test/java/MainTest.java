import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainTest {
    private static WebDriver driver;
    private MainPage mainpage;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
    }

    @BeforeEach
    public void setUpEach() {
        driver.get("https://github.com/");
    }

    @Test
    public void goTologinPage() {
        mainpage = new MainPage(driver);
        LoginPage loginPage = mainpage.clickSignIn();
        String heading = loginPage.getHeadingText();
        Assertions.assertEquals("Sign in to GitHub", heading);
    }
    @Test
    public void doRegister(){
        mainpage = new MainPage(driver);
        SignUpPage signUpPage = mainpage.register("asdg@mail.ru");
        String heading = signUpPage.getAdventure();
        Assertions.assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }
    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

}
