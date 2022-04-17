import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IframeTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String pathChromedriver = "libs/chromedriver.exe";

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://www.random.org/");
    }
    @Test //TODo WebDriver, технологии, настройка – Step 3 – Stepik.mp
    void iframetest() {
        driver.findElement(By.xpath("//button[text()='Allow All']")).click();
        WebElement iframe = driver.findElement(By.xpath("//div[@id='homepage-generator']/iframe"));
        System.out.println(iframe.getText());
        driver.switchTo().frame(iframe);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       driver.findElement(By.xpath("//input[@id='geevopfmhv-button']")).click();
//        System.out.println(driver.getWindowHandle());
//        WebElement dr = driver.findElement(By.xpath("//div[@id='homepage-generator']//iframe"));
//        System.out.println(String.valueOf(dr));
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(dr));
////        System.out.println(driver.getWindowHandle());
////        driver.switchTo().window(String.valueOf(dr));
//        System.out.println(driver.getWindowHandle());
////        WebElement generate = driver.findElement(By.xpath("//div[@id='homepage-generator']//iframe"));
////        System.out.println("lllllll" + generate.getText());
////        wait.until(ExpectedConditions.elementToBeClickable(generate));
//        driver.findElement(By.xpath("//input[@id='geevopfmhv-button']")).click();

    }
//    @AfterAll
//    static void tearDown(){
//        driver.quit();
//    }


}
