import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO SCREENSHOTS

public class HeadLessMainPageTest {
    private static WebDriver driver;
    private static MainPage mainpage;
    private static String pathChromedriver = "libs/chromedriver.exe";


    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        ChromeOptions chromeOptions = new ChromeOptions(); // включение режима не отображени хрома chromeOptions.setHeadless(true); //
        //   chromeOptions.setHeadless(true); // // вместо этого используем  chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--headless"); //включение режима не отображени хрома
        chromeOptions.addArguments("window-size=1800x900");
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        // IN HEADLESS DOSEN'T WORK IF YOU WANT TO SET RESOLUTION ON YOURS SCREEN YOU MUST USE window().setSize(1240, 800)driver.manage().window().maximize(); // установка максимального размера экрана
        // driver.manage().window().setSize(new Dimension(1024,768)); // вместо этого используем  chromeOptions.addArguments("window-size=1800x900");
        mainpage = new MainPage(driver);
    }

    @BeforeEach
    void setUpEach() {
        driver.get("https://github.com/");
    }


    @Test
    @DisplayName("Verify that when button 'SignIn' has been clicked a new page has been opened")
    void goTologinPage() {
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String filename = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        LoginPage loginPage = mainpage.clickSignIn();
        try {
            FileUtils.copyFile(screenshot, new File("C:\\TEMP\\" + filename)); //нужно импортнуть зависимость  implementation 'commons-io:commons-io:2.11.0'
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    @ValueSource(strings = {"asdg@mail.ru", "svmyho111111@mail.ru", "asddewd2222dg@mail.ru"})
    @DisplayName("Verify that when email field can be filled")
    void doRegister(String arg) {
        SignUpPage signUpPage = mainpage.register(arg);
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @DisplayName("Verify that the email is filled values in the Csv")
    @ParameterizedTest(name = "The value of {index} subtest ==> the rank of ''{0}'' the number is {1}")
    @CsvFileSource(resources = "/githubLogin.csv")
    void parareterizedTestCsvFile(String actual, int rank) {
        SignUpPage signUpPage = mainpage.register(actual);
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
