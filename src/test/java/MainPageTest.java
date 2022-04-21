import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class MainPageTest {
    private static WebDriver driver;
    private static MainPage mainpage;
    private static String pathChromedriver = "libs/chromedriver.exe";
   // private static ChromeOptions chromeOptions =new ChromeOptions(); // включение режима не отображени хрома chromeOptions.setHeadless(true); //



    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();
       // chromeOptions.setHeadless(true); // включение режима не отображени хрома
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        mainpage = new MainPage(driver);
    }

    @BeforeEach
    void setUpEach() {
        driver.get("https://github.com/");
    }


    @Test
    @DisplayName("Verify that when button 'SignIn' has been clicked a new page has been opened")
    void goTologinPage() {
        LoginPage loginPage = mainpage.clickSignIn();
        String heading = loginPage.getHeadingText();
        assertEquals("Sign in to GitHub", heading);
    }

    @RepeatedTest(2)
    @DisplayName("Verify that when button 'SignUP' has been clicked a new page has been opened")
    void doToclickSignUp() {
        SignUpPage signUpPage = mainpage.clickSignUp();
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @ParameterizedTest
    @ValueSource( strings = {"asdg@mail.ru", "svmyho111111@mail.ru", "asddewd2222dg@mail.ru"})
    @DisplayName("Verify that when email field can be filled")
    void doRegister(String arg) {
        SignUpPage signUpPage = mainpage.register(arg);
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @DisplayName("Verify that the email is filled values in the Csv")
    @ParameterizedTest (name = "The value of {index} subtest ==> the rank of ''{0}'' the number is {1}")
    @CsvFileSource(resources = "/githubLogin.csv")
    void parareterizedTestCsvFile(String actual,  int rank) {
        SignUpPage signUpPage = mainpage.register(actual);
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
