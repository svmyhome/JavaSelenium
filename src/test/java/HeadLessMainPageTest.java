import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;

//TODO SCREENSHOTS

public class HeadLessMainPageTest {
    private static WebDriver driver;
    private static ScreenRecorder screenRecorder;
    private static MainPage mainpage;
    private static String pathChromedriver = "libs/chromedriver.exe";


    @BeforeAll
    static void setUpAll() {
        GraphicsConfiguration gconfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        try {
            screenRecorder = new ScreenRecorder(gconfig,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
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
        try {
            screenRecorder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://github.com/");
        Date dateNow = new Date();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("C:\\TEMP", resourceName));
    }

    public static String getFilename() { //TODO move to helper
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String filename = format.format(dateNow) + ".png";
        return filename;
    }

    public static void TakeScreen(String filename) { //TODO move to helper
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\TEMP\\" + filename)); //нужно импортнуть зависимость  implementation 'commons-io:commons-io:2.11.0'
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Verify that when button 'SignIn' has been clicked a new page has been opened")
    void goTologinPage() throws IOException {
        String filenameBefore = getFilename();
        TakeScreen(filenameBefore);
        getBytes(filenameBefore);
        LoginPage loginPage = mainpage.clickSignIn();
        String heading = loginPage.getHeadingText();
        assertEquals("Sign in to GitHub", heading);
        String filenameAfter = getFilename();
        TakeScreen(filenameAfter);
        getBytes(filenameAfter);
    }

    @RepeatedTest(2)
    @DisplayName("Verify that when button 'SignUP' has been clicked a new page has been opened")
    void doToclickSignUp() throws IOException {
        String filenameBefore = getFilename();
        TakeScreen(filenameBefore);
        getBytes(filenameBefore);
        SignUpPage signUpPage = mainpage.clickSignUp();
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
        String filenameAfter = getFilename();
        TakeScreen(filenameAfter);
        getBytes(filenameAfter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdg@mail.ru", "svmyho111111@mail.ru", "asddewd2222dg@mail.ru"})
    @DisplayName("Verify that when email field can be filled")
    void doRegister(String arg) throws IOException {
        String filenameBefore = getFilename();
        TakeScreen(filenameBefore);
        getBytes(filenameBefore);
        SignUpPage signUpPage = mainpage.register(arg);
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
        String filenameAfter = getFilename();
        TakeScreen(filenameAfter);
        getBytes(filenameAfter);
    }

    @DisplayName("Verify that the email is filled values in the Csv")
    @ParameterizedTest(name = "The value of {index} subtest ==> the rank of ''{0}'' the number is {1}")
    @CsvFileSource(resources = "/githubLogin.csv")
    void parareterizedTestCsvFile(String actual, int rank) throws IOException {
        String filenameBefore = getFilename();
        TakeScreen(filenameBefore);
        getBytes(filenameBefore);
        SignUpPage signUpPage = mainpage.register(actual);
        String heading = signUpPage.getAdventure();
        assertEquals("Welcome to GitHub!\n" + "Let’s begin the adventure", heading);
        String filenameAfter = getFilename();
        TakeScreen(filenameAfter);
        getBytes(filenameAfter);
    }

    @AfterEach
    void tearDownEach() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
