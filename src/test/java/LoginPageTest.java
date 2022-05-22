import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;

//TODO is needed to add in all methods @Displayname
//TODO must be added to some methods @RepeatedTest
public class LoginPageTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    private static ScreenRecorder screenRecorder;
    private static String pathChromedriver = "libs/chromedriver.exe";

    @BeforeAll
    static void setUpAll() {
        GraphicsConfiguration gconfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        try {
            screenRecorder = new ScreenRecorder(gconfig,
                    new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
                            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, (int) 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, (int) (15 * 60)),
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);
    }
    @BeforeEach
    void setUpEach() {
        try {
            screenRecorder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDownEach() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
