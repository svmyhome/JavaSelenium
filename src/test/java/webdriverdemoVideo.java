import java.io.IOException;
import java.time.Duration;
import java.awt.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;


public class webdriverdemoVideo {
    private static ScreenRecorder screenRecorder;
    private static String pathChromedriver = "libs/chromedriver.exe";
    public static WebDriver driver;

    public static void main(String[] args) throws IOException, AWTException {
        GraphicsConfiguration gconfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

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

        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();

        // Start Capturing the Video
        screenRecorder.start();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://github.com/login");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Close the Browser.
        driver.close();

        // Stop the ScreenRecorder
        screenRecorder.stop();
    }
}