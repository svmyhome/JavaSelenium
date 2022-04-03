package WIndowHander;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
/** 53 Work with different windows */
public class WindowsHandler {
    private static WebDriver driver;

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://ru.ebay.com/");
        String windowEbay = driver.getWindowHandle();
        System.out.println(windowEbay);

    }
}
