import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String pathChromedriver = "libs/chromedriver.exe";
    private static Actions actions;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://demo.guru99.com/test/drag_drop.html");
    }

    @Test
    @DisplayName("Verify that dran androp is work")
    void dradAndDropTest() {
        WebElement bank = driver.findElement(By.xpath("//a[contains(text(), 'BANK')]"));
        wait.until(ExpectedConditions.visibilityOf(bank));
        WebElement account = driver.findElement(By.xpath("//ol[@id='bank']/li"));
        actions.dragAndDrop(bank, account)
                .pause(5000)
                .build()
                .perform();
        String result = driver.findElement(By.xpath("//ol[@id='bank']/li[@data-id='5']")).getText();
        assertEquals("BANK", result);
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
